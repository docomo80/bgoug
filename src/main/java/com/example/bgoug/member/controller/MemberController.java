package com.example.bgoug.member.controller;

import com.example.bgoug.application.entities.Application;
import com.example.bgoug.application.models.viewModels.ApplicationView;
import com.example.bgoug.application.services.ApplicationService;
import com.example.bgoug.company.entities.Company;
import com.example.bgoug.company.models.ViewModels.CompanyView;
import com.example.bgoug.company.services.CompanyService;
import com.example.bgoug.errors.Error;
import com.example.bgoug.events.entities.Event;
import com.example.bgoug.events.models.ViewModels.EventView;
import com.example.bgoug.events.models.bindingModels.EditEventModel;
import com.example.bgoug.events.services.EventService;
import com.example.bgoug.member.entities.Member;
import com.example.bgoug.member.enums.MemberType;
import com.example.bgoug.member.enums.PcPlatform;
import com.example.bgoug.member.models.bindingModels.EditMemberModel;
import com.example.bgoug.member.models.bindingModels.MemberModel;
import com.example.bgoug.member.models.veiwModels.MemberView;
import com.example.bgoug.member.models.veiwModels.MemberViewAnnualInstallment;
import com.example.bgoug.member.services.MemberService;
import com.example.bgoug.recommended_members.entities.RecommendedMember;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("members")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private EventService eventService;

    @Autowired
    private ApplicationService applicationService;

    @GetMapping("irregular")
    public String getMembersByMembershipFeeIsNotPayed(Model model) {
        List<Object[]> objects = this.memberService.getMembersByMembershipFeeIfFalse();
        List<MemberView> memberViews = new ArrayList<>();
        for (Object[] object : objects) {
            String name = (String) object[0];
            Boolean isPayed = Boolean.valueOf(object[1].toString());
            String address = (String) object[2];
            String memberType = (String) object[3];
            String position = (String) object[4];
            String telNumber = (String) object[5];
            MemberView memberView = new MemberView();
            memberView.setName(name);
            memberView.setMembershipFee(isPayed);
            memberView.setAddress(address);
            MemberType convertedType = MemberType.valueOf(memberType);
            memberView.setMemberType(convertedType);
            memberView.setPosition(position);
            memberView.setTelephoneNumber(telNumber);
            memberViews.add(memberView);
        }
        model.addAttribute("members", memberViews);
        model.addAttribute("view", "/members/irregular-members");
        return "base-layout";
    }

    @GetMapping("all")
    public String getAllMembersPage(Model model) {
        List<MemberView> memberViews = this.memberService.getAll();
        model.addAttribute("members", memberViews);
        model.addAttribute("view", "members/member-table");
        return "base-layout";
    }

    @GetMapping("membersOfCompany")
    public String getMembersOfCompanyPage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Member member = (Member) authentication.getPrincipal();
//        Long memberId = memberView.getId();
        List<MemberView> memberViews = this.memberService.findAllByCompany(member.getCompany());
        model.addAttribute("members", memberViews);
        model.addAttribute("view", "members/members-by-company");
        return "base-layout";
    }
    
    @GetMapping("/edit/{id}")
    public String getEditPage(Model model, @PathVariable Long id) {
        EditMemberModel editMemberModel = this.memberService.findMemberById(id);
        List<CompanyView> companies = this.companyService.getAll();
        List<ApplicationView> applicationViews = this.applicationService.findAllApplications();
        
        model.addAttribute("view", "/members/member-edit");
        model.addAttribute("type", "Edit");
        model.addAttribute("member", editMemberModel);
        model.addAttribute("companies", companies);
        model.addAttribute("applicationsList", applicationViews);
        return "base-layout";
    }
    
    @PostMapping("/edit/{id}")
    public String postEditPage(@ModelAttribute EditMemberModel editMemberModel,
    		                                   @PathVariable Long id,
    		                                   @RequestParam String pcPlatform,
    		                                   @RequestParam String isPayed,
    		                                   @RequestParam String companyName,
    		                                   @RequestParam List<String> applicationsList) {
                                    

        editMemberModel.setId(id);
        editMemberModel.setPcPlatform(pcPlatform);
        Company currentCompany = this.companyService.getCompanyByName(companyName);
        editMemberModel.setCompany(currentCompany);
        
        for (String applicationName : applicationsList) {
			Application application = this.applicationService.findApplicationByName(applicationName);
			editMemberModel.getApplications().add(application);
		}
        
        if (isPayed.equals("yes")) {
			editMemberModel.setMembershipFee(true);
		} else {
			editMemberModel.setMembershipFee(false);
		}
        this.memberService.update(editMemberModel);
        return "redirect:/members/all";
    }
    
    @GetMapping("/delete/{id}")
    public String getDeletePage(Model model, @PathVariable Long id){
        EditMemberModel editMemberModel = this.memberService.findMemberById(id);
        Company company = this.companyService.getCompanyByName(editMemberModel.getCompany().getName());
        model.addAttribute("company", company.getName());
        model.addAttribute("view", "/members/member-delete");
        model.addAttribute("type", "Confirm delete");
        model.addAttribute("member", editMemberModel);
        return "base-layout";
    }
    
    

    @PostMapping("/delete/{id}")
    public String postDeletePage(@ModelAttribute EditMemberModel editMemberModel, @PathVariable Long id){
        editMemberModel.setId(id);
        this.memberService.delete(editMemberModel);
        return "redirect:/members/all";
    }


    @GetMapping("register")
    public String getAddMemberPage(@ModelAttribute MemberModel memberModel, Model model) {
        List<CompanyView> companies = this.companyService.getAll();
        List<EventView> eventViews = this.eventService.getAllEvents();
        List<MemberView> memberViews = this.memberService.getAll();
        List<ApplicationView> applicationViews = this.applicationService.findAllApplications();
        model.addAttribute("applicationsList", applicationViews);
        model.addAttribute("membersViews", memberViews);
        model.addAttribute("events", eventViews);
        model.addAttribute("companies", companies);
        model.addAttribute("type", "Register");
//        model.addAttribute("members", new MemberModel());
        model.addAttribute("view", "members/member-add");
        return "base-layout";
    }

    @PostMapping("register")
    public String addMemberPage(@Valid @ModelAttribute MemberModel memberModel,
                                BindingResult bindingResult,
                                Model model,
                                @RequestParam String companyName,
                                @RequestParam String memberType,
                                @RequestParam Boolean isPayed,
                                @RequestParam String event,
//                              @RequestParam String recommendedName,
                                @RequestParam(required = false) Long id,
                                @RequestParam String pcPlatform,
                                @RequestParam List<String> applicationsList) {

        if (bindingResult.hasErrors()) {
            List<CompanyView> companies = this.companyService.getAll();
            List<EventView> eventViews = this.eventService.getAllEvents();
            List<MemberView> memberViews = this.memberService.getAll();
            List<ApplicationView> applicationViews = this.applicationService.findAllApplications();
            model.addAttribute("applicationsList", applicationViews);
            model.addAttribute("membersViews", memberViews);
            model.addAttribute("events", eventViews);
            model.addAttribute("companies", companies);
            model.addAttribute("view", "/members/member-add");
            return "base-layout";
        }

        Company company = this.companyService.getCompanyByName(companyName);
        memberModel.setCompany(company);

        if (!(event.equals(""))) {
            Event eventToPersist = this.eventService.getEventByName(event);
            memberModel.getEvents().add(eventToPersist);
        }

        memberModel.setMemberType(memberType);
        memberModel.setMembershipFee(isPayed);

        String value = PcPlatform.valueOf(pcPlatform).getKey();
        memberModel.setPcPlatform(value);

        if (id != null) {

            // find recommended member from database
            EditMemberModel editMemberModel = this.memberService.findMemberById(id);


            ModelMapper modelMapper = new ModelMapper();
//
            //convert recommended member to member
//            Member recommended = modelMapper.map(recommendedMember, Member.class);

            RecommendedMember recommendedMember = new RecommendedMember();
            Member member = modelMapper.map(memberModel, Member.class);
//            recommendedMember.setId(editMemberModel.getId());
            recommendedMember.setName(editMemberModel.getName());
//            recommendedMember.getMembers().add(member);

            memberModel.getRecommendedMembers().add(recommendedMember);

            // get all recommended Names from recommended member
//            Set<RecommendedMember> recommendedMemberNames = recommended.getRecommendedMembers();

            // add recommended member to currentModel
//            memberModel.getRecommendedMembers().add(recommendedName);

            // iterate over recommended names
//            if (recommendedMemberNames.size() > 0) {
//                for (RecommendedMember currentName : recommendedMemberNames) {
//
////                    EditMemberModel editMemberModel = this.memberService.findMemberByName();
////                    this.memberService.update(editMemberModel);
//                    memberModel.getRecommendedMembers().add(currentName);
//                }
//                this.memberService.update(recommendedMember);
//            } else {
//
//                this.memberService.update(recommendedMember);
//            }
        }

        for (String currentApplication : applicationsList) {
            Application application = this.applicationService.findApplicationByName(currentApplication);
            memberModel.getApplications().add(application);
        }

        this.memberService.persist(memberModel);

        return "redirect:/members/all";

    }

//    @PostMapping("register")
//    public String addMemberPage(@Valid @ModelAttribute MemberModel memberModel,
//                                BindingResult bindingResult,
//                                Model model,
//                                @RequestParam String companyName,
//                                @RequestParam String memberType,
//                                @RequestParam Boolean isPayed,
//                                @RequestParam String event,
//                                @RequestParam String recommendedName,
//                                @RequestParam String pcPlatform,
//                                @RequestParam List<String> applicationsList) {
//
//        if (bindingResult.hasErrors()) {
//            List<CompanyView> companies = this.companyService.getAll();
//            List<EventView> eventViews = this.eventService.getAllEvents();
//            List<MemberView> memberViews = this.memberService.getAll();
//            List<ApplicationView> applicationViews = this.applicationService.findAllApplications();
//            model.addAttribute("applicationsList", applicationViews);
//            model.addAttribute("membersViews", memberViews);
//            model.addAttribute("events", eventViews);
//            model.addAttribute("companies", companies);
//            model.addAttribute("view", "/member/member-add");
//            return "base-layout";
//        }
//
//        Company companyModel = this.companyService.getCompanyByName(companyName);
//        memberModel.setCompany(companyModel);
//
//        if (!(event.equals(""))) {
//            Event eventToPersist = this.eventService.getEventByName(event);
//            memberModel.getEvents().add(eventToPersist);
//        }
//
//        memberModel.setMemberType(memberType);
//        memberModel.setMembershipFee(isPayed);
//
//        String value = PcPlatform.valueOf(pcPlatform).getKey();
//        memberModel.setPcPlatform(value);
//
//        if (!(recommendedName.equals(""))) {
//
//            // find recommended member from database
//            EditMemberModel recommendedMember = this.memberService.findMemberByName(recommendedName);
//
//            ModelMapper modelMapper = new ModelMapper();
//
//            //convert recommended member to member
//            Member recommended = modelMapper.map(recommendedMember, Member.class);
//
//            // get all recommended Names from recommended member
//            Set<String> recommendedMemberNames = recommended.getRecommendedMembers();
//
//            // add recommended member to currentModel
//            memberModel.getRecommendedMembers().add(recommendedName);
//
//            // iterate over recommended names
//            if (recommendedMemberNames.size() > 0) {
//                for (String currentName : recommendedMemberNames) {
//
//                    EditMemberModel editMemberModel = this.memberService.findMemberByName(currentName);
//                    this.memberService.update(editMemberModel);
//                    memberModel.getRecommendedMembers().add(currentName);
//                }
//                this.memberService.update(recommendedMember);
//            } else {
//                this.memberService.update(recommendedMember);
//            }
//        }
//
//        for (String currentApplication : applicationsList) {
//            Application application = this.applicationService.findApplicationByName(currentApplication);
//            memberModel.getApplications().add(application);
//        }
//
//        this.memberService.persist(memberModel);
//
//        return "redirect:/member/all";
//
//    }

//    @GetMapping("/orderByInstallment")
//    public String getMemberAnnualInstallmentView(Model model) {
//        List<Object[]> objects = this.memberService.findAllMembersByDiscount();
//        List<MemberViewAnnualInstallment> memberViewAnnualInstallments = new ArrayList<>();
//        for (Object[] object : objects) {
//            String nameOfCompany = (String) object[0];
//            double installment = ((Number) object[1]).doubleValue();
//            MemberViewAnnualInstallment annualInstallment = new MemberViewAnnualInstallment();
//            annualInstallment.setName(nameOfCompany);
//            annualInstallment.setAnnualInstallment(installment);
//            memberViewAnnualInstallments.add(annualInstallment);
//        }
//
//        model.addAttribute("members", memberViewAnnualInstallments);
//        model.addAttribute("view", "/member/member-table-annual-installment");
//        return "base-layout";
//
//    }

    @GetMapping("/orderByInstallment")
    public String getMemberAnnualInstallmentView(Model model) {
        this.memberService.dropFunctionIfExist();
        this.memberService.createFunctionForDiscount();
        List<Object[]> objects = this.memberService.findAllMembersByDiscount();
        List<MemberViewAnnualInstallment> memberViewAnnualInstallments = new ArrayList<>();
        for (Object[] object : objects) {
            double installment = ((Number) object[2]).doubleValue();
            String name = String.valueOf(object[0]);
            MemberViewAnnualInstallment annualInstallment = new MemberViewAnnualInstallment();
            annualInstallment.setName(name);
            annualInstallment.setAnnualInstallment(installment);
            memberViewAnnualInstallments.add(annualInstallment);
        }

        model.addAttribute("members", memberViewAnnualInstallments);
        model.addAttribute("view", "/members/member-table-annual-installment");
        return "base-layout";

    }

    @GetMapping("login")
    public String getLoginPage(Model model, @RequestParam(required = false) String error) {
        if (error != null) {
            model.addAttribute("error", Error.INVALID_CREDENTIALS);
        }
        model.addAttribute("view", "login");
        return "base-layout";
    }

//    @PostMapping("login")
//    public String postLoginPage(@ModelAttribute MemberLogin memberLogin
//            , RedirectAttributes redirectAttributes, HttpSession httpSession) {
//
//        LoggedMember member = this.memberService
//                .findByUsernameAndPassword(memberLogin.getUsername(), memberLogin.getPassword());
//
////        if (member == null){
////            redirectAttributes.addAttribute("view", "login");
////            return "redirect:base-layout";
////        }
//
//        httpSession.setAttribute("member", member);
//        return "base-layout";
//    }

    @GetMapping("member")
    public String getMemberPage(Principal principal) {
        System.out.println(principal.getName());
        return "member";
    }

    @GetMapping("admin")
    public String getAdminPage() {

        return "admin";
    }
}
