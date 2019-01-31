package com.example.bgoug.member.repositories;

import com.example.bgoug.company.entities.Company;
import com.example.bgoug.member.entities.Member;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends CrudRepository<Member, Long> {

    @Query("SELECT m.name, m.membershipFee, m.address, m.memberType, m.position, m.telephoneNumber  FROM Member AS m \n" +
            "            WHERE m.membershipFee = false ")
    List<Object[]> getAllByMembershipFeeIfFalse();

    @Query("select m from Member as m")
    List<Member> getAll();

    Member findMemberByName(String name);

//    @Query("SELECT m.name, (60.00 -(m.discount / 100))  AS fee\n" +
//            "\n" +
//            "FROM Member AS m")
//    List<Object[]> findAllMembersByDiscount();

//    @Query(value = "select (60.00 - ((select count(member_id)\n" +
//            "                  from (select rm.name, mrm.recommended_member_id, mrm.member_id\n" +
//            "                        from members_recommended_members as mrm\n" +
//            "                               join recommended_members as rm on rm.id = mrm.recommended_member_id\n" +
//            "                        order by recommended_member_id, member_id) products_sorted,\n" +
//            "                       (select @pv \\:= 1) initialisation\n" +
//            "                  where find_in_set(recommended_member_id, @pv)\n" +
//            "\n" +
//            "                    and length(@pv \\:= concat(@pv, ',', member_id)) / 100))) as discount,\n" +
//            "       (@pv \\:= @pv + 1)                                                     as count,\n" +
//            "       member.name\n" +
//            "from member", nativeQuery = true)
//    List<Object[]> findAllMembersByDiscount();

//    @Modifying
//    @Query("UPDATE Member AS m  SET m.discount = :discount WHERE m.id = :id")
//    void update(@Param("discount") int discount, @Param("id") Long id);

    @Modifying
    @Query(value = "drop function if exists isSubElement", nativeQuery = true)
    void dropFunctionIfExist();

    @Modifying
    @Query(value =
//            "drop function if exists isSubElement ^;" +
            "\n" +
                    "CREATE FUNCTION isSubElement(rrecommended_member_id INT, mmember_id INT) returns int(11)\n" +
                    "  DETERMINISTIC\n" +
                    "  READS SQL DATA\n" +
                    "BEGIN\n" +
                    "  DECLARE isChild,curId,curParent,lastParent int;\n" +
                    "  SET isChild = 0;\n" +
                    "  SET curId = mmember_id;\n" +
                    "  SET curParent = -1;\n" +
                    "  SET lastParent = -2;\n" +
                    "\n" +
                    "  WHILE lastParent <> curParent AND curParent <> 0 AND curId <> -1 AND curParent <> mmember_id AND isChild = 0 DO\n" +
                    "  SET lastParent = curParent;\n" +
                    "  SELECT recommended_member_id from members_recommended_members where member_id = curId limit 1 into curParent;\n" +
                    "\n" +
                    "  IF curParent = rrecommended_member_id THEN\n" +
                    "    SET isChild = 1;\n" +
                    "  END IF;\n" +
                    "  SET curId = curParent;\n" +
                    "  END WHILE ;\n" +
                    "\n" +
                    "  RETURN isChild;\n" +
                    "END ", nativeQuery = true)
    void createFunctionForDiscount();

    @Modifying
    @Query(value =
//            " set @subTreeId=1;\n" +
            "select m.name, @subTreeId \\:= m.id,(60.00 -(select count(member_id)\n" +
            "        FROM members_recommended_members as mrm\n" +
            "        WHERE isSubElement(@subTreeId, member_id) = 1\n" +
            "           OR recommended_member_id = @subTreeId)) as discount, (@subTreeId \\:= @subTreeId + 1) as count\n" +
//            "       m.name\n" +
            "from member as m", nativeQuery = true)
    List<Object[]> findAllMembersByDiscount();

    Member findMemberByUsername(String username);

    @Query("select m from Member as m where m.username = :username and m.password = :password")
    Member findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    List<Member> findAllByCompany(Company company);

    Member findMemberById(Long id);

}
