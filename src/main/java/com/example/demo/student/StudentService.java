package com.example.demo.student;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StudentService {
	private final StudentRepository studentRepository;

	@Autowired
	public StudentService(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}

    public List<Student> getStudents() {
		return studentRepository.findAll();
	}

	public Student getStudent(Long studentId) {
		Optional<Student> student = studentRepository.findById(studentId);
		
		if (!student.isPresent()) {
			throw new IllegalStateException("student with id " + studentId + " does not exists");
		}
		return student.get();
	}

	public void addNewStudent(Student student) {		
		if (!isEmailValid(student.getEmail())) {
			throw new IllegalStateException("email taken");
		}

		studentRepository.save(student);
	}

	private boolean isEmailValid(String email) {
		Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);

		if (studentOptional.isPresent()) {
			return false;
		}

		return true;
	}
	public void deleteStudent(Long studentId) {
		boolean exists = studentRepository.existsById(studentId);

		if (!exists) {
			throw new IllegalStateException("student with id " + studentId + " does not exists");
		}

		studentRepository.deleteById(studentId);
	}

	@Transactional
	public void updateStudent(Long studentId, Student student) {
		Student originalStudent= studentRepository.findById(studentId)
			.orElseThrow(() -> new IllegalStateException("student with id " + studentId + " does not exists"));
		
		if (student.getEmail() != null && student.getEmail().length() > 0 && !Objects.equals(originalStudent.getEmail(), student.getEmail())) {
			if (!isEmailValid(student.getEmail())) {
				throw new IllegalStateException("email taken");
			}
			originalStudent.setEmail(student.getEmail());
		}
				
		if (student.getName() != null && student.getName().length() > 0 && !Objects.equals(originalStudent.getName(), student.getName())) {
			originalStudent.setName(student.getName());
		}
		
		if (student.getDob() != null && !Objects.equals(originalStudent.getDob(), student.getDob())) {
			originalStudent.setDob(student.getDob());
		}
	}
}
