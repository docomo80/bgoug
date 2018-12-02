package com.example.bgoug.events.repositories;

import com.example.bgoug.events.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    Event findEventByName(String name);

//    get all events, companies, members and comments
    @Query(value = "SELECT e.id,\n" +
            "\t  e.NAME,\n" +
            "\t  e.DATE,\n" +
            "\t  GROUP_CONCAT(DISTINCT c.NAME),\n" +
            "\t  GROUP_CONCAT(DISTINCT m.NAME),\n" +
            "\t  COUNT(distinct c.id) AS count_companies,\n" +
            "\t  COUNT(distinct m.id) AS count_member,\n" +
            "\t  COUNT(distinct comm.id) AS count_comment\n" +
            "FROM event AS e\n" +
            "LEFT JOIN members_event AS me ON e.id = me.event_id\n" +
            "LEFT JOIN member AS m ON m.id = me.member_id\n" +
            "LEFT\tJOIN company AS c ON c.id = m.company_id\n" +
            "LEFT JOIN comment AS comm ON comm.event_id = e.id\n" +
            "GROUP BY e.id\n" +
            "ORDER BY e.DATE , count_member DESC", nativeQuery = true)
    List<Object[]> getSortedEventsByCompanies();


}
