package com.example.bgoug.events.servicesImpl;

import com.example.bgoug.events.entities.Event;
import com.example.bgoug.events.models.ViewModels.EventView;
import com.example.bgoug.events.models.bindingModels.EventModel;
import com.example.bgoug.events.repositories.EventRepository;
import com.example.bgoug.events.services.EventService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;

    @Override
    public List<EventView> getAllEvents() {
        List<Event> events = this.eventRepository.findAll();
        ModelMapper modelMapper = new ModelMapper();
        List<EventView> eventViews = new ArrayList<>();
        for (Event event : events) {
            EventView eventView = modelMapper.map(event, EventView.class);
            eventViews.add(eventView);
        }

        return eventViews;
    }

    @Override
    public void persist(EventModel eventModel) {
        ModelMapper modelMapper = new ModelMapper();
        Event event = modelMapper.map(eventModel, Event.class);
        this.eventRepository.saveAndFlush(event);
    }

    @Override
    public Event getEventByName(String name) {
        Event event = this.eventRepository.findEventByName(name);
        return event;
    }

    @Override
    public List<Object[]> getSortedEventsByCompanies() {
        List<Object[]> objects = this.eventRepository.getSortedEventsByCompanies();

        return objects;
    }

    @Override
    public List<Object[]> getEventByDate(String date) {
        List<Object[]> getEventsByYear = this.eventRepository.getEventByDate(date);
        return getEventsByYear;
    }

}
