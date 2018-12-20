package com.example.bgoug.member.entities;

import com.example.bgoug.application.entities.Application;
import com.example.bgoug.company.entities.Company;
import com.example.bgoug.events.entities.Event;
import com.example.bgoug.recommended_members.entities.RecommendedMember;
import com.example.bgoug.role.Role;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "member")
public class Member implements UserDetails {

    private Long id;

    private String name;

    public String username;

    public String password;

    private String position;

    private String address;

    private String telephoneNumber;

    private int discount;

    private String memberType;

    private Boolean membershipFee;

    private Company company;

    private String pcPlatform;

    private boolean isAccountNonExpired;

    private boolean isAccountNonLocked;

    private boolean isCredentialsNonExpired;

    private boolean isEnabled;

    private Set<Application> applications;

    private Set<Event> events;

//    private Set<String> recommendedMembers;

    private Set<RecommendedMember> recommendedMembers;

    private Set<Role> authorities;

    public Member() {
        this.authorities = new HashSet<>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    @Column(unique = true)
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

//    @ElementCollection
//    public Set<String> getRecommendedMembers() {
//        return recommendedMembers;
//    }

//    public void setRecommendedMembers(Set<String> recommendedMembers) {
//        this.recommendedMembers = recommendedMembers;
//    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    @ManyToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinTable(name = "members_roles",
    joinColumns = @JoinColumn(name = "member_id"),
    inverseJoinColumns = @JoinColumn(name = "role_id"))
    public Set<Role> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        isAccountNonExpired = accountNonExpired;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        isAccountNonLocked = accountNonLocked;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        isCredentialsNonExpired = credentialsNonExpired;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public void setAuthorities(Set<Role> authorities) {
        this.authorities = authorities;
    }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "members_recommendedMembers",
            joinColumns = @JoinColumn(name = "member_id"),
            inverseJoinColumns = @JoinColumn(name = "recommended_member_id", referencedColumnName = "id"))
    public Set<RecommendedMember> getRecommendedMembers() {
        return recommendedMembers;
    }

    public void setRecommendedMembers(Set<RecommendedMember> recommendedMembers) {
        this.recommendedMembers = recommendedMembers;
    }
}
