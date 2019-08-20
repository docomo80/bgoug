package com.example.bgoug.events.models.bindingModels;

public class EditEventModel {
	
	private Long id;
	
	private String name;
	
    private String description;
	
	private String location;
	
	private double cost;
	
	
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

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}
	
	public String getName() {
		return name;
	}
		
    public void setName(String name) {
    	this.name = name;
    }
    
    
    
    
}
