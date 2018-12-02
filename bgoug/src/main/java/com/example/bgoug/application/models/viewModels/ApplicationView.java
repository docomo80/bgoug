package com.example.bgoug.application.models.viewModels;

import com.example.bgoug.member.entities.Member;

import java.util.Set;

public class ApplicationView {

    private String name;

    private Set<Member> members;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Member> getMembers() {
        return members;
    }

    public void setMembers(Set<Member> members) {
        this.members = members;
    }
}
