package com.example.demo.Repository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface StudentRepo extends JpaRepository<StudentEntity,Integer>{
    
}