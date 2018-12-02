package com.example.bgoug.events.services;

import com.example.bgoug.events.entities.Event;
import com.example.bgoug.events.models.ViewModels.EventView;
import com.example.bgoug.events.models.bindingModels.EventModel;

import java.util.List;

public interface EventService {

    List<EventView> getAllEvents();

    void persist(EventModel eventModel);

    Event getEventByName(String name);

    List<Object[]> getSortedEventsByCompanies();

}
