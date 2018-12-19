package com.example.bgoug.comment.entities;

import com.example.bgoug.events.entities.Event;

import javax.persistence.*;

@Entity
@Table(name = "comment")
public class Comment {

    private Long id;

    private String description;

    private Event event;

    public Comment() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "event_id", referencedColumnName = "id")
    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}
