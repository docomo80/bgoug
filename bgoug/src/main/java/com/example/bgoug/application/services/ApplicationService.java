package com.example.bgoug.application.services;

import com.example.bgoug.application.entities.Application;
import com.example.bgoug.application.models.viewModels.ApplicationView;

import java.util.List;

public interface ApplicationService {

    List<ApplicationView> findAllApplications();

    Application findApplicationByName(String name);
}
