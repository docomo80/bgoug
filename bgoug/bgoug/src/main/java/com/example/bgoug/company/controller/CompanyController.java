package com.example.bgoug.company.controller;

import com.example.bgoug.company.models.ViewModels.CompanyView;
import com.example.bgoug.company.models.ViewModels.CompanyViewByNumberOfMember;
import com.example.bgoug.company.models.bindingModels.AddCompanyModel;
import com.example.bgoug.company.models.bindingModels.EditCompanyModel;
import com.example.bgoug.company.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("companies")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @GetMapping("all")
    public String getCompanyPage(Model model) {
        List<CompanyView> companyViews = this.companyService.getAll();
        model.addAttribute("companies", companyViews);
        model.addAttribute("view", "/company/company-table");
        return "base-layout";
    }

    @GetMapping("add")
    public String getAddCompanyPage(Model model) {
        model.addAttribute("view", "/company/company-modifiable");
        model.addAttribute("company", new AddCompanyModel());
        model.addAttribute("type", "Add");
        return "base-layout";
    }

    @PostMapping("add")
    public String addCompanyPage(@ModelAttribute AddCompanyModel addCompanyModel) {
        this.companyService.persist(addCompanyModel);
        return "redirect:/companies/all";
    }

    @GetMapping("/edit/{id}")
    public String getEditPage(Model model, @PathVariable Long id) {
        EditCompanyModel editCompanyModel = this.companyService.getByIdToEdit(id);
        model.addAttribute("view", "/company/company-modifiable");
        model.addAttribute("type", "edit");
        model.addAttribute("company", editCompanyModel);
        return "base-layout";
    }

    @PostMapping("/edit/{id}")
    public String postEditPage(@ModelAttribute EditCompanyModel editCompanyModel, @PathVariable Long id) {
        editCompanyModel.setId(id);
        this.companyService.update(editCompanyModel);
        return "redirect:/companies/all";
    }


    @GetMapping("/delete/{id}")
    public String getDeletePage(Model model, @PathVariable Long id){
        EditCompanyModel editCompanyModel = this.companyService.getByIdToEdit(id);
        model.addAttribute("view", "/company/company-modifiable");
        model.addAttribute("type", "Delete");
        model.addAttribute("company", editCompanyModel);
        return "base-layout";
    }

    @PostMapping("/delete/{id}")
    public String postDeletePage(@ModelAttribute EditCompanyModel editCompanyModel, @PathVariable Long id){
        editCompanyModel.setId(id);
        this.companyService.delete(editCompanyModel);
        return "redirect:/companies/all";
    }

    @GetMapping("/orderByMembers")
    public String getCompanyOrderByMembersDesc(Model model){
        List<Object[]> objects = this.companyService.findAllByMembersByCompany();
        List<CompanyViewByNumberOfMember> companyViews = new ArrayList<>();
        for (Object[] object : objects) {
                String nameOfCompany = (String)object[0];
                Long count = (Long) object[1];
                CompanyViewByNumberOfMember companyView = new CompanyViewByNumberOfMember();
                companyView.setName(nameOfCompany);
                companyView.setCount(count);
                companyViews.add(companyView);

        }
        model.addAttribute("companies", companyViews);
        model.addAttribute("view", "/company/company-ordered-by-members");
        return "base-layout";
    }
}
