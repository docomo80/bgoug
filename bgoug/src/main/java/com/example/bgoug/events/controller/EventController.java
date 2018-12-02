package com.example.bgoug.events.controller;

import com.example.bgoug.comment.entities.Comment;
import com.example.bgoug.events.entities.Event;
import com.example.bgoug.events.models.ViewModels.EventView;
import com.example.bgoug.events.models.ViewModels.ViewSortedEvent;
import com.example.bgoug.events.models.bindingModels.EventModel;
import com.example.bgoug.events.services.EventService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("events")
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping("all")
    public String getAllCompaniesPage(Model model) {
        List<EventView> eventViews = this.eventService.getAllEvents();
        model.addAttribute("events", eventViews);
        model.addAttribute("view", "/event/event-table");
        return "base-layout";
    }

    @GetMapping("add")
    public String addEventsPage(Model model) {
        model.addAttribute("events", new EventModel());
        model.addAttribute("view", "/event/event-add");
        return "base-layout";
    }

    @PostMapping("add")
    public String postEventPage(@ModelAttribute EventModel eventModel, @RequestParam String comment) {
        Comment commentToAdd = new Comment();
        commentToAdd.setDescription(comment);
        ModelMapper modelMapper = new ModelMapper();
        Event event = modelMapper.map(eventModel, Event.class);
        commentToAdd.setEvent(event);

        eventModel.getComments().add(commentToAdd);
        this.eventService.persist(eventModel);

        return "redirect:/events/all";
    }

    @GetMapping("allEventsAndCompanies")
    public String getEventsByCommentsAndCompanies(Model model) {
        List<Object[]> objects = this.eventService.getSortedEventsByCompanies();

        List<ViewSortedEvent> sortedEvents = new ArrayList<>();
        Set<String> companyList = new HashSet<>();
        for (Object[] object : objects) {

            Long id = ((Number) object[0]).longValue();
            String nameOfEvent = (String) object[1];
            Date dateOfEvent = (Date) object[2];
            String companies = String.valueOf(object[3]);
            String members = String.valueOf(object[4]);
            int countOfCompanies = ((Number) object[5]).intValue();
            int countOfMembers = ((Number) object[6]).intValue();
            int countOfComments = ((Number) object[7]).intValue();

            ViewSortedEvent viewSortedEvent = new ViewSortedEvent();

            viewSortedEvent.setName(nameOfEvent);
            viewSortedEvent.setDate(dateOfEvent);
            viewSortedEvent.setNumberOfCompanies(countOfCompanies);
            viewSortedEvent.setNumberOfMembers(countOfMembers);
            viewSortedEvent.setNumberOfComments(countOfComments);
            viewSortedEvent.setId(id);
            viewSortedEvent.setNameOfCompanies(companies);
            viewSortedEvent.setNamesOfMembers(members);

            sortedEvents.add(viewSortedEvent);

        }

        model.addAttribute("events", sortedEvents);
        model.addAttribute("view", "/event/sorted-events");
        return "base-layout";
    }
}