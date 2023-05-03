package com.ken.security.repository;

import com.ken.security.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    UserDetails findStudentByEmail(String Email);
}
