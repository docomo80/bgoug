package com.example.bgoug.member.services;

import com.example.bgoug.member.models.bindingModels.EditMemberModel;
import com.example.bgoug.member.models.bindingModels.MemberModel;
import com.example.bgoug.member.models.veiwModels.MemberView;

import java.util.List;

public interface MemberService {

    void persist(MemberModel memberModel);

    List<Object[]> getMembersByMembershipFeeIfFalse();

    List<MemberView> getAll();

    EditMemberModel findMemberByName(String name);

    void update(EditMemberModel editMemberModel);

    List<Object[]> findAllMembersByDiscount();

}
