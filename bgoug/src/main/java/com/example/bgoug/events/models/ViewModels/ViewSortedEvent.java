package com.example.bgoug.events.models.ViewModels;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class ViewSortedEvent {

    private Long id;

    private String name;

    private Date date;

    private String namesOfCompanies;

    private String namesOfMembers;

    private int numberOfCompanies;

    private int numberOfMembers;

    private int numberOfComments;

    public ViewSortedEvent() {
    }

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getNameOfCompanies() {
        return namesOfCompanies;
    }

    public void setNameOfCompanies(String nameOfCompanies) {
        this.namesOfCompanies = nameOfCompanies;
    }

    public String getNamesOfMembers() {
        return namesOfMembers;
    }

    public void setNamesOfMembers(String namesOfMembers) {
        this.namesOfMembers = namesOfMembers;
    }

    public int getNumberOfCompanies() {
        return numberOfCompanies;
    }

    public void setNumberOfCompanies(int numberOfCompanies) {
        this.numberOfCompanies = numberOfCompanies;
    }

    public int getNumberOfMembers() {
        return numberOfMembers;
    }

    public void setNumberOfMembers(int numberOfMembers) {
        this.numberOfMembers = numberOfMembers;
    }

    public int getNumberOfComments() {
        return numberOfComments;
    }

    public void setNumberOfComments(int numberOfComments) {
        this.numberOfComments = numberOfComments;
    }
}
