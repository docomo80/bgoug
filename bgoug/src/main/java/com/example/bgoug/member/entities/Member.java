package com.example.bgoug.member.entities;

import com.example.bgoug.application.entities.Application;
import com.example.bgoug.company.entities.Company;
import com.example.bgoug.events.entities.Event;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "member")
public class Member implements Serializable {

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

    private Set<Application> applications;

    private Set<Event> events;

    private Set<String> recommendedMembers;

    public Member() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(unique = true)
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


    public String getMemberType() {
        return memberType;
    }

    public void setMemberType(String memberType) {
        this.memberType = memberType;
    }

    public Boolean getMembershipFee() {
        return membershipFee;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public void setMembershipFee(Boolean membershipFee) {
        this.membershipFee = membershipFee;
    }


    @ManyToOne()
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @ManyToMany()
    @JoinTable(name = "members_applications",
            joinColumns = @JoinColumn(name = "member_id"),
            inverseJoinColumns = @JoinColumn(name = "application_id", referencedColumnName = "id"))
    public Set<Application> getApplications() {
        return applications;
    }

    public String getPcPlatform() {
        return pcPlatform;
    }

    public void setPcPlatform(String pcPlatform) {
        this.pcPlatform = pcPlatform;
    }

    public void setApplications(Set<Application> applications) {
        this.applications = applications;
    }

    @ManyToMany()
    @JoinTable(name = "members_event",
            joinColumns = @JoinColumn(name = "member_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id", referencedColumnName = "id"))
    public Set<Event> getEvents() {
        return events;
    }

    public void setEvents(Set<Event> events) {
        this.events = events;
    }

    @ElementCollection
    public Set<String> getRecommendedMembers() {
        return recommendedMembers;
    }

    public void setRecommendedMembers(Set<String> recommendedMembers) {
        this.recommendedMembers = recommendedMembers;
    }

}
