package com.example.bgoug.member.services;

import com.example.bgoug.company.entities.Company;
import com.example.bgoug.events.models.bindingModels.EditEventModel;
import com.example.bgoug.member.models.bindingModels.EditMemberModel;
import com.example.bgoug.member.models.bindingModels.LoggedMember;
import com.example.bgoug.member.models.bindingModels.MemberModel;
import com.example.bgoug.member.models.veiwModels.MemberView;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface MemberService extends UserDetailsService {

    void persist(MemberModel memberModel);

    List<Object[]> getMembersByMembershipFeeIfFalse();

    List<MemberView> getAll();

    EditMemberModel findMemberByName(String name);

    //    void update(EditMemberModel editMemberModel);
    void dropFunctionIfExist();

    void createFunctionForDiscount();

    //    @PreAuthorize("hasRole('ADMIN')")
    List<Object[]> findAllMembersByDiscount();

    LoggedMember findByUsernameAndPassword(String username, String password);

    List<MemberView> findAllByCompany(Company company);

    EditMemberModel findMemberById(Long id);
    
    void update(EditMemberModel editMemberModel);
    
    void delete(EditMemberModel editMemberModel);

}
