package com.example.bgoug.company.repositories;

import com.example.bgoug.company.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

    List<Company> findAll();

    Company findCompanyByName(String name);

    @Query("select d.name, count (e.id) as memberNumbers \n" +
            " from Company d\n" +
            "left join Member as e on e.company.id = d.id \n" +
            " group by d.id, d.name order by memberNumbers desc")
    List<Object[]> findAllByMembersByCompany();

}
