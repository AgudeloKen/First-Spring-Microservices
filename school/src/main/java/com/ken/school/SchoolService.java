package com.ken.school;

import com.ken.school.client.StudentClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SchoolService {

    private final SchoolRepository schoolRepository;
    private final StudentClient studentClient;

    public School saveStudent(School school){
        return schoolRepository.save(school);
    }

    public List<School> findAll(){
        return schoolRepository.findAll();
    }

    public FullSchoolResponse findSchoolsWithStudents(Long schoolId) {
        School school = schoolRepository.getReferenceById(schoolId);
        List<Student> students = studentClient.findAllStudentsBySchool(schoolId);
        return new FullSchoolResponse(school.getId(), school.getName(), school.getEmail(), students);
    }
}
