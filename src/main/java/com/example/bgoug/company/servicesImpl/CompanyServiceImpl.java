package com.example.bgoug.company.servicesImpl;

import com.example.bgoug.company.entities.Company;
import com.example.bgoug.company.models.ViewModels.CompanyView;
import com.example.bgoug.company.models.bindingModels.AddCompanyModel;
import com.example.bgoug.company.models.bindingModels.EditCompanyModel;
import com.example.bgoug.company.repositories.CompanyRepository;
import com.example.bgoug.company.services.CompanyService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public void persist(AddCompanyModel addCompanyModel) {
        ModelMapper modelMapper = new ModelMapper();
        Company company = modelMapper.map(addCompanyModel, Company.class);
        this.companyRepository.saveAndFlush(company);
    }

    @Override
    public List<CompanyView> getAll() {
        List<Company> companies = this.companyRepository.findAll();
        List<CompanyView> companyViews = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();

        for (Company company : companies) {
            CompanyView companyView = modelMapper.map(company, CompanyView.class);
            companyViews.add(companyView);
        }

        return companyViews;
    }

    @Override
    public EditCompanyModel getByIdToEdit(Long id) {
        Company company = this.companyRepository.getOne(id);
        ModelMapper modelMapper = new ModelMapper();
        EditCompanyModel editCompanyModel = null;
        if (company != null){
            editCompanyModel = modelMapper.map(company, EditCompanyModel.class);
        }

        return editCompanyModel;

    }

    @Override
    public void update(EditCompanyModel editCompanyModel) {
        Company company = this.companyRepository.getOne(editCompanyModel.getId());
        company.setName(editCompanyModel.getName());
        company.setAddress(editCompanyModel.getAddress());
        company.setTypeOfBusiness(editCompanyModel.getTypeOfBusiness());
        this.companyRepository.saveAndFlush(company);
    }

    @Override
    public void delete(EditCompanyModel editCompanyModel) {
        Company company = this.companyRepository.getOne(editCompanyModel.getId());
        this.companyRepository.delete(company);
    }

    @Override
    public Company getCompanyByName(String name) {
        Company company = this.companyRepository.findCompanyByName(name);

        return company;
    }

    @Override
    public List<Object[]> findAllByMembersByCompany() {
        List<Object[]> objects = this.companyRepository.findAllByMembersByCompany();

        return objects;
    }

}
