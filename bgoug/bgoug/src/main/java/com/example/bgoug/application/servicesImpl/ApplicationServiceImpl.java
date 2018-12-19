package com.example.bgoug.application.servicesImpl;

import com.example.bgoug.application.entities.Application;
import com.example.bgoug.application.models.viewModels.ApplicationView;
import com.example.bgoug.application.repositories.ApplicationRepository;
import com.example.bgoug.application.services.ApplicationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class ApplicationServiceImpl implements ApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Override
    public List<ApplicationView> findAllApplications() {
        List<Application> applications = this.applicationRepository.findAll();
        List<ApplicationView> applicationViews = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();

        for (Application application : applications) {
            ApplicationView applicationView = modelMapper.map(application, ApplicationView.class);
            applicationViews.add(applicationView);
        }

        return applicationViews;
    }

    @Override
    public Application findApplicationByName(String name) {
       Application application = this.applicationRepository.findByName(name);

       return application;
    }
}
