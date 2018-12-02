package com.example.bgoug.member.models.bindingModels;

import com.example.bgoug.company.entities.Company;
import com.example.bgoug.member.entities.Member;

import java.util.Set;

public class EditMemberModel {

    private Long id;

    private String name;

    private String position;

    private String address;

    private String telephoneNumber;

    private int discount;

    private String memberType;

    private Boolean membershipFee;

    private Company company;

    private String pcPlatform;

    private Set<String> recommendedMembers;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public String getMemberType() {
        return memberType;
    }

    public void setMemberType(String memberType) {
        this.memberType = memberType;
    }

    public Boolean getMembershipFee() {
        return membershipFee;
    }

    public void setMembershipFee(Boolean membershipFee) {
        this.membershipFee = membershipFee;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getPcPlatform() {
        return pcPlatform;
    }

    public void setPcPlatform(String pcPlatform) {
        this.pcPlatform = pcPlatform;
    }

    public Set<String> getRecommendedMembers() {
        return recommendedMembers;
    }

    public void setRecommendedMembers(Set<String> recommendedMembers) {
        this.recommendedMembers = recommendedMembers;
    }
}
