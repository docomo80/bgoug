package com.example.bgoug.member.services;

import com.example.bgoug.company.entities.Company;
import com.example.bgoug.member.entities.Member;
import com.example.bgoug.member.models.bindingModels.EditMemberModel;
import com.example.bgoug.member.models.bindingModels.LoggedMember;
import com.example.bgoug.member.models.bindingModels.MemberModel;
import com.example.bgoug.member.models.veiwModels.MemberView;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface MemberService extends UserDetailsService {

    void persist(MemberModel memberModel);

    List<Object[]> getMembersByMembershipFeeIfFalse();

    List<MemberView> getAll();

    EditMemberModel findMemberByName(String name);

    void update(EditMemberModel editMemberModel);

//    @PreAuthorize("hasRole('ADMIN')")
    List<Object[]> findAllMembersByDiscount();

    LoggedMember findByUsernameAndPassword(String username, String password);

    List<MemberView> findAllByCompany(Company company);



}
