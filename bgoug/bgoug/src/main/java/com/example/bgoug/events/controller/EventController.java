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

    @GetMapping(value = "/chart")
    public String chart(Model model) {

        List<Object[]> objects = this.eventService.getSortedEventsByCompanies();
        List<Object[]> getEventsByDateSixteenth = this.eventService.getEventByDate("2016");
        List<Object[]> getEventsByDateSeventeenth = this.eventService.getEventByDate("2017");
        List<Object[]> getEventsByDateEighteenth = this.eventService.getEventByDate("2018");

        //       events from 2016
        int countCompaniesFromSixteenth = 0;
        int countMembersFromSixteenth = 0;
        int countCommentsFromSixteenth = 0;
        for (Object[] object : getEventsByDateSixteenth) {
            countCompaniesFromSixteenth += ((Number) object[2]).intValue();
            countMembersFromSixteenth += ((Number) object[3]).intValue();
            countCommentsFromSixteenth += ((Number) object[4]).intValue();
        }

        //        events from 2017
        int countCompaniesFromSeventeenth = 0;
        int countMembersFromSeventeenth = 0;
        int countCommentsFromSeventeenth = 0;
        for (Object[] object : getEventsByDateSeventeenth) {
            countCompaniesFromSeventeenth += ((Number) object[2]).intValue();
            countMembersFromSeventeenth += ((Number) object[3]).intValue();
            countCommentsFromSeventeenth += ((Number) object[4]).intValue();
        }


         //        events from 2018
        int countCompaniesFromEighteenth = 0;
        int countMembersFromEighteenth = 0;
        int countCommentsFromEighteenth = 0;
        for (Object[] object : getEventsByDateEighteenth) {
            countCompaniesFromEighteenth += ((Number) object[2]).intValue();
            countMembersFromEighteenth += ((Number) object[3]).intValue();
            countCommentsFromEighteenth += ((Number) object[4]).intValue();
        }

        List<ViewSortedEvent> sortedEvents = new ArrayList<>();
        Set<String> companyList = new HashSet<>();
//        List<Integer> numberOfCompanies = new ArrayList<>();
        int countOfCompanies = 0;
        int countOfComments = 0;
        int countOfMembers = 0;
        for (Object[] object : objects) {

            Long id = ((Number) object[0]).longValue();
            String nameOfEvent = (String) object[1];
            Date dateOfEvent = (Date) object[2];
            String companies = String.valueOf(object[3]);
            String members = String.valueOf(object[4]);
            countOfCompanies += ((Number) object[5]).intValue();
            countOfMembers += ((Number) object[6]).intValue();
            countOfComments += ((Number) object[7]).intValue();

        }


        //first, add the regional sales
//        Integer northeastSales = 17089;
//        Integer westSales = 10603;
//        Integer midwestSales = 1223;
//        Integer southSales = 10111;

        model.addAttribute("countOfCompanies", countOfCompanies);
        model.addAttribute("countOfMembers", countOfMembers);
        model.addAttribute("countOfComments", countOfComments);
//        model.addAttribute("westSales", westSales);

        //now add sales by lure type
        List<Integer> companies = Arrays.asList(
                countCompaniesFromSixteenth,
                countCompaniesFromSeventeenth,
                countCompaniesFromEighteenth);
        List<Integer> members = Arrays.asList(
                countMembersFromSixteenth,
                countMembersFromSeventeenth,
                countMembersFromEighteenth);
        List<Integer> comments = Arrays.asList(
                countCommentsFromSixteenth,
                countCommentsFromSeventeenth,
                countCommentsFromEighteenth);

        model.addAttribute("companies", companies);
        model.addAttribute("members", members);
        model.addAttribute("comments", comments);
//        model.addAttribute("view", "chart");


        return "chart";
    }
}