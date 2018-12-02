package com.example.bgoug.application.repositories;

import com.example.bgoug.application.entities.Application;
import com.example.bgoug.application.models.viewModels.ApplicationView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationRepository  extends JpaRepository<Application, Long> {

    List<Application> findAll();

    Application findByName(String name);
}
