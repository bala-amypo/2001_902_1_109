// package com.example.demo.Repository;
// import org.springframework.web.bind.annotation.*;
// import org.springframework.web.data.jpa.repository.JpaRepository;
// import org.springframework.stereotype.Repository;
// @Repository
// public interface StudentRepo extends JpaRepository<StudentEntity,Integer>{
    
// }

package com.example.sql.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.sql.Entitydata.Student;

@Repository
public interface StudentRepo extends JpaRepository<Student,Integer> {

    
}