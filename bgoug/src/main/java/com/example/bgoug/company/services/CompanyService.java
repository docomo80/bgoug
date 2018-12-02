package com.example.bgoug.company.services;

import com.example.bgoug.company.entities.Company;
import com.example.bgoug.company.models.ViewModels.CompanyView;
import com.example.bgoug.company.models.bindingModels.AddCompanyModel;
import com.example.bgoug.company.models.bindingModels.EditCompanyModel;

import java.util.List;

public interface CompanyService {

    void persist(AddCompanyModel addCompanyModel);

    List<CompanyView> getAll();

    EditCompanyModel getByIdToEdit(Long id);

    void update(EditCompanyModel editCompanyModel);

    void delete(EditCompanyModel editCompanyModel);

    Company getCompanyByName(String name);

    List<Object[]> findAllByMembersByCompany();
}
