package sba.sms.services;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sba.sms.models.Student;
import sba.sms.utils.CommandLine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

@FieldDefaults(level = AccessLevel.PRIVATE)
class StudentServiceTest {

    static StudentService studentService;

    @BeforeAll
    static void beforeAll() {
        // Initialize the StudentService and set up necessary data
        studentService = new StudentService();
        // Ensure CommandLine is configured to add data to the database
        CommandLine.addData();
    }

    @BeforeEach
    void setUp() {
        // Setup data for each test if needed
    }

    @Test
    void testValidateStudent() {
        Student student = new Student();
        student.setEmail("student2@example.com");
        student.setPassword("password");

        studentService.createStudent(student);

        // Validate student credentials
        boolean isValid = studentService.validateStudent("student2@example.com", "password");
        assertTrue(isValid, "Student credentials should be valid");
    }

}