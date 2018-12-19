package com.example.bgoug.member.models.veiwModels;

import com.example.bgoug.company.entities.Company;
import com.example.bgoug.member.enums.MemberType;

public class MemberView {

    private Long id;

    private String name;

    private String position;

    private String address;

    private String telephoneNumber;

    private MemberType memberType;

    private Boolean membershipFee;

    private Company company;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getMembershipFee() {
        return membershipFee;
    }

    public void setMembershipFee(Boolean membershipFee) {
        this.membershipFee = membershipFee;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public MemberType getMemberType() {
        return memberType;
    }

    public void setMemberType(MemberType memberType) {
        this.memberType = memberType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
