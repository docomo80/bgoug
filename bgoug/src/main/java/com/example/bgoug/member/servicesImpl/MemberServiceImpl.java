package com.example.bgoug.member.servicesImpl;

import com.example.bgoug.member.entities.Member;
import com.example.bgoug.member.models.bindingModels.EditMemberModel;
import com.example.bgoug.member.models.bindingModels.MemberModel;
import com.example.bgoug.member.models.veiwModels.MemberView;
import com.example.bgoug.member.repositories.MemberRepository;
import com.example.bgoug.member.services.MemberService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public void persist(MemberModel memberModel) {
        ModelMapper modelMapper = new ModelMapper();
        Member member = modelMapper.map(memberModel, Member.class);
        this.memberRepository.saveAndFlush(member);
    }

    @Override
    public List<Object[]> getMembersByMembershipFeeIfFalse() {
        List<Object[]> objects = this.memberRepository.getAllByMembershipFeeIfFalse();

        return objects;
    }

    @Override
    public List<MemberView> getAll() {
        List<Member> members = new ArrayList<>();
        members = this.memberRepository.getAll();

        List<MemberView> memberViews = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();
        for (Member member : members) {
            MemberView memberView = modelMapper.map(member, MemberView.class);
            memberViews.add(memberView);
        }
        return memberViews;
    }

    @Override
    public EditMemberModel findMemberByName(String name) {
        Member member = this.memberRepository.findMemberByName(name);
        ModelMapper modelMapper = new ModelMapper();
        EditMemberModel editMemberModel = modelMapper.map(member, EditMemberModel.class);

        return editMemberModel;
    }

    @Override
    public void update(EditMemberModel editMemberModel) {

        int discount = editMemberModel.getDiscount();

        this.memberRepository.update(discount + 1, editMemberModel.getId());
    }

    @Override
    public List<Object[]> findAllMembersByDiscount() {
        List<Object[]> objects = this.memberRepository.findAllMembersByDiscount();
        return objects;
    }

}
