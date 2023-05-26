package com.projetLocMns.ProjetFilRougeLocMnsV3.dao;

import com.projetLocMns.ProjetFilRougeLocMnsV3.model.Copy;
import com.projetLocMns.ProjetFilRougeLocMnsV3.model.Hire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HireDao extends JpaRepository<Hire, Integer> {

@Query("SELECT h FROM Hire h INNER JOIN h.user u WHERE u.id = :userId")
List<Hire> findHireByIdUser(@Param("userId") Integer userId);
}
