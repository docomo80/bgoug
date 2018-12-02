package com.example.bgoug.member.controller;

import com.example.bgoug.application.entities.Application;
import com.example.bgoug.application.models.viewModels.ApplicationView;
import com.example.bgoug.application.services.ApplicationService;
import com.example.bgoug.company.entities.Company;
import com.example.bgoug.company.models.ViewModels.CompanyView;
import com.example.bgoug.company.services.CompanyService;
import com.example.bgoug.events.entities.Event;
import com.example.bgoug.events.models.ViewModels.EventView;
import com.example.bgoug.events.services.EventService;
import com.example.bgoug.member.entities.Member;
import com.example.bgoug.member.enums.MemberType;
import com.example.bgoug.member.enums.PcPlatform;
import com.example.bgoug.member.models.bindingModels.EditMemberModel;
import com.example.bgoug.member.models.bindingModels.MemberModel;
import com.example.bgoug.member.models.veiwModels.MemberView;
import com.example.bgoug.member.models.veiwModels.MemberViewAnnualInstallment;
import com.example.bgoug.member.services.MemberService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("member")
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
        model.addAttribute("view", "/member/irregular-members");
        return "base-layout";
    }

    @GetMapping("all")
    public String getAllMembersPage(Model model) {
        List<MemberView> memberViews = this.memberService.getAll();
        model.addAttribute("members", memberViews);
        model.addAttribute("view", "member/member-table");
        return "base-layout";
    }

    @GetMapping("add")
    public String getAddMemberPage(Model model) {
        List<CompanyView> companies = this.companyService.getAll();
        List<EventView> eventViews = this.eventService.getAllEvents();
        List<MemberView> memberViews = this.memberService.getAll();
        List<ApplicationView> applicationViews = this.applicationService.findAllApplications();
        model.addAttribute("applicationsList", applicationViews);
        model.addAttribute("membersViews", memberViews);
        model.addAttribute("events", eventViews);
        model.addAttribute("companies", companies);
        model.addAttribute("members", new MemberModel());
        model.addAttribute("view", "member/member-add");
        return "base-layout";
    }

    @PostMapping("add")
    public String addMemberPage(@Valid @ModelAttribute MemberModel memberModel,
                                @RequestParam String companyName,
                                @RequestParam String memberType,
                                @RequestParam Boolean isPayed,
                                @RequestParam String event,
                                @RequestParam String recommendedName,
                                @RequestParam String pcPlatform,
                                @RequestParam List<String> applicationsList) {

        Company companyModel = this.companyService.getCompanyByName(companyName);
        memberModel.setCompany(companyModel);

        if (!(event.equals(""))){
            Event eventToPersist = this.eventService.getEventByName(event);
            memberModel.getEvents().add(eventToPersist);
        }

        memberModel.setMemberType(memberType);
        memberModel.setMembershipFee(isPayed);

        String value = PcPlatform.valueOf(pcPlatform).getKey();
        memberModel.setPcPlatform(value);

        if (!(recommendedName.equals(""))) {

            // find recommended member from database
            EditMemberModel recommendedMember = this.memberService.findMemberByName(recommendedName);

            ModelMapper modelMapper = new ModelMapper();

            //convert recommended member to member
            Member recommended = modelMapper.map(recommendedMember, Member.class);

            // get all recommended Names from recommended member
            Set<String> recommendedMemberNames = recommended.getRecommendedMembers();

            // add recommended member to currentModel
            memberModel.getRecommendedMembers().add(recommendedName);

            // iterate over recommended names
            if (recommendedMemberNames.size() > 0) {
                for (String currentName : recommendedMemberNames) {

                    EditMemberModel editMemberModel = this.memberService.findMemberByName(currentName);
                    this.memberService.update(editMemberModel);
                    memberModel.getRecommendedMembers().add(currentName);
                }
                this.memberService.update(recommendedMember);
            } else {

                this.memberService.update(recommendedMember);
            }
        }

        for (String currentApplication : applicationsList) {
            Application application = this.applicationService.findApplicationByName(currentApplication);
            memberModel.getApplications().add(application);
        }

        this.memberService.persist(memberModel);

        return "redirect:/member/all";

    }

    @GetMapping("/orderByInstallment")
    public String getMemberAnnualInstallmentView(Model model) {
        List<Object[]> objects = this.memberService.findAllMembersByDiscount();
        List<MemberViewAnnualInstallment> memberViewAnnualInstallments = new ArrayList<>();
        for (Object[] object : objects) {
            String nameOfCompany = (String) object[0];
            double installment = ((Number) object[1]).doubleValue();
            MemberViewAnnualInstallment annualInstallment = new MemberViewAnnualInstallment();
            annualInstallment.setName(nameOfCompany);
            annualInstallment.setAnnualInstallment(installment);
            memberViewAnnualInstallments.add(annualInstallment);
        }

        model.addAttribute("members", memberViewAnnualInstallments);
        model.addAttribute("view", "/member/member-table-annual-installment");
        return "base-layout";

    }
}
