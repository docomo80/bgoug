package com.example.bgoug.events.entities;

import com.example.bgoug.comment.entities.Comment;
import com.example.bgoug.company.entities.Company;
import com.example.bgoug.member.entities.Member;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "event")
public class Event {

    private Long id;

    private String name;

    private Date date;

    private String description;

    private String location;

    private Double cost;

    private Set<Member> members;

    private Set<Comment> comments;

    public Event() {
        this.members = new HashSet<>();
        this.comments = new HashSet<>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    @ManyToMany(mappedBy = "events", cascade = CascadeType.ALL)
    public Set<Member> getMembers() {
        return members;
    }

    public void setMembers(Set<Member> members) {
        this.members = members;
    }

    @OneToMany(mappedBy = "event", orphanRemoval = true,
    cascade = CascadeType.ALL)
    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
