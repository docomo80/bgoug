package com.example.bgoug.member.models.bindingModels;

import com.example.bgoug.annotation.IsPasswordMatching;
import com.example.bgoug.application.entities.Application;
import com.example.bgoug.company.entities.Company;
import com.example.bgoug.events.entities.Event;

import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@IsPasswordMatching
public class MemberModel {

    @Size(min = 4, message = "Името трябва да съдържа поне 4 символа")
    private String name;

    @Size(min = 6, max = 12, message = "Потребителя трябва да съдържа поне 6 символа")
    private String username;

    @Size(min = 6, message = "Паролата трябва да съдържа между 6 и 12 символа")
    private String password;

    private String conformedPassword;

    @Size(min = 5, message = "Позицията трябва да съдържа поне 5 символа")
    private String position;

    @Size(min = 10, message = "Адресът трябва да съдържа поне 5 символа")
    private String address;

    @Size(min = 9, message = "Телефонът трябва да съдържа поне 9 символа")
    private String telephoneNumber;

    private Boolean isEmployed;

    private String memberType;

    private Boolean membershipFee;

    private Company company;

    private String pcPlatform;

    private Set<Application> applications;

    private Set<Event> events;

    private Set<String> recommendedMembers;

    public MemberModel() {
        this.events = new HashSet<>();
        this.recommendedMembers = new HashSet<>();
        this.applications = new HashSet<>();
    }

    public Set<Application> getApplications() {
        return applications;
    }

    public void setApplications(Set<Application> applications) {
        this.applications = applications;
    }

    public Set<Event> getEvents() {
        return events;
    }

    public void setEvents(Set<Event> events) {
        this.events = events;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getEmployed() {
        return isEmployed;
    }

    public void setEmployed(Boolean employed) {
        isEmployed = employed;
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

    public String getConformedPassword() {
        return conformedPassword;
    }

    public void setConformedPassword(String conformedPassword) {
        this.conformedPassword = conformedPassword;
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


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
