package com.example.bgoug.events.services;

import com.example.bgoug.company.models.bindingModels.EditCompanyModel;
import com.example.bgoug.events.entities.Event;
import com.example.bgoug.events.models.ViewModels.EventView;
import com.example.bgoug.events.models.bindingModels.EditEventModel;
import com.example.bgoug.events.models.bindingModels.EventModel;

import java.util.List;

public interface EventService {

    List<EventView> getAllEvents();

    void persist(EventModel eventModel);

    Event getEventByName(String name);

    List<Object[]> getSortedEventsByCompanies();

    List<Object[]> getEventByDate(String date);
    
    EditEventModel getByIdToEdit(Long id);

    void update(EditEventModel editEventModel);
    
    void delete(EditEventModel editEventModel);
}
