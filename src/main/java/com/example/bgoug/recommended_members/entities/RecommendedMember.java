package com.example.bgoug.recommended_members.entities;

import com.example.bgoug.member.entities.Member;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "recommended_members")
public class RecommendedMember {

    private Long id;

    private String name;

    private Set<Member> members;

    public RecommendedMember() {
        this.members = new HashSet<>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @ManyToMany(mappedBy = "recommendedMembers", cascade = CascadeType.ALL)
    public Set<Member> getMembers() {
        return members;
    }

    public void setMembers(Set<Member> members) {
        this.members = members;
    }
}
