package com.example.bgoug.member.repositories;

import com.example.bgoug.member.entities.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query("SELECT m.name, m.membershipFee, m.address, m.memberType, m.position, m.telephoneNumber  FROM Member AS m \n" +
            "            WHERE m.membershipFee = false ")
    List<Object[]> getAllByMembershipFeeIfFalse();

    @Query("select m from Member as m")
    List<Member> getAll();

    Member findMemberByName(String name);

    @Query("SELECT m.name, (60.00 -(m.discount / 100))  AS fee\n" +
            "\n" +
            "FROM Member AS m")
    List<Object[]> findAllMembersByDiscount();

    @Modifying
    @Query("UPDATE Member AS m  SET m.discount = :discount WHERE m.id = :id")
    void update(@Param("discount") int discount, @Param("id") Long id);
}
