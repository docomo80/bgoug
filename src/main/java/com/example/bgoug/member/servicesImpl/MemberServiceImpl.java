package com.example.bgoug.member.servicesImpl;

import com.example.bgoug.company.entities.Company;
import com.example.bgoug.errors.Error;
import com.example.bgoug.member.entities.Member;
import com.example.bgoug.member.models.bindingModels.EditMemberModel;
import com.example.bgoug.member.models.bindingModels.LoggedMember;
import com.example.bgoug.member.models.bindingModels.MemberModel;
import com.example.bgoug.member.models.veiwModels.MemberView;
import com.example.bgoug.member.repositories.MemberRepository;
import com.example.bgoug.member.services.MemberService;
import com.example.bgoug.role.Role;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

//    EntityManager entityManager;

    @Override
    public void persist(MemberModel memberModel) {
        ModelMapper modelMapper = new ModelMapper();
        Member member = modelMapper.map(memberModel, Member.class);
        String encryptedPassword = this.bCryptPasswordEncoder.encode(memberModel.getPassword());
        member.setPassword(encryptedPassword);
        member.setAccountNonExpired(true);
        member.setAccountNonLocked(true);
        member.setCredentialsNonExpired(true);
        member.setEnabled(true);
        if (getAll().isEmpty()) {
            Role role = new Role();
            role.setAuthority("ROLE_ADMIN");
            member.getAuthorities().add(role);
        } else {
            Role role = new Role();
            role.setAuthority("ROLE_USER");
            member.getAuthorities().add(role);
        }
        this.memberRepository.save(member);
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
    public void dropFunctionIfExist() {
        this.memberRepository.dropFunctionIfExist();
    }

    @Override
    public void createFunctionForDiscount() {
        this.memberRepository.createFunctionForDiscount();
    }

//    @Override
//    public void update(EditMemberModel editMemberModel) {
//
//        int discount = editMemberModel.getDiscount();
//
//        this.memberRepository.update(discount + 1, editMemberModel.getId());
//    }


    @Override
    public List<Object[]> findAllMembersByDiscount() {

        List<Object[]> objects = this.memberRepository.findAllMembersByDiscount();
        return objects;
    }

    @Override
    public LoggedMember findByUsernameAndPassword(String username, String password) {
        Member member = this.memberRepository.findByUsernameAndPassword(username, password);

        ModelMapper modelMapper = new ModelMapper();
        LoggedMember loggedMember = null;
        if (member != null) {
            loggedMember = modelMapper.map(member, LoggedMember.class);
        }

        return loggedMember;
    }

    @Override
    public List<MemberView> findAllByCompany(Company company) {
        List<Member> members = this.memberRepository.findAllByCompany(company);
        ModelMapper modelMapper = new ModelMapper();
        List<MemberView> memberViewList = new ArrayList<>();

        for (Member member : members) {
            MemberView memberView = modelMapper.map(member, MemberView.class);
            memberViewList.add(memberView);
        }

        return memberViewList;
    }

    @Override
    public EditMemberModel findMemberById(Long id) {
        Member member = this.memberRepository.findMemberById(id);

        ModelMapper modelMapper = new ModelMapper();
        EditMemberModel memberModel = modelMapper.map(member, EditMemberModel.class);

        return memberModel;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = this.memberRepository.findMemberByUsername(username);

        if (member == null) {
            throw new UsernameNotFoundException(Error.INVALID_CREDENTIALS);
        }
        return member;
    }

	@Override
	public void update(EditMemberModel editMemberModel) {
		// TODO Auto-generated method stub
		Member member = this.memberRepository.findMemberById(editMemberModel.getId());
		member.setAddress(editMemberModel.getAddress());
		/*
		 * Company company = new Company();
		 * company.setAddress(editMemberModel.getCompany().getAddress());
		 * company.setName(editMemberModel.getCompany().getName());
		 * company.setTypeOfBusiness(editMemberModel.getCompany().getTypeOfBusiness());
		 */
		member.setCompany(editMemberModel.getCompany());
		member.setMembershipFee(editMemberModel.getMembershipFee());
		member.setMemberType(editMemberModel.getMemberType());
		member.setName(editMemberModel.getName());
		member.setPosition(editMemberModel.getPosition());
		member.setPcPlatform(editMemberModel.getPcPlatform());
		member.setTelephoneNumber(editMemberModel.getTelephoneNumber());
		member.setApplications(editMemberModel.getApplications());
		this.memberRepository.save(member);
		
	}

	@Override
	public void delete(EditMemberModel editMemberModel) {
		// TODO Auto-generated method stub
		Member member = this.memberRepository.findMemberById(editMemberModel.getId());
		this.memberRepository.delete(member);
	}

}
