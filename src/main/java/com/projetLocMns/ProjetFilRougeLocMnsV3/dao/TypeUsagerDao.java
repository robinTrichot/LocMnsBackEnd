package com.projetLocMns.ProjetFilRougeLocMnsV3.dao;

import com.projetLocMns.ProjetFilRougeLocMnsV3.model.TypeUsager;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface TypeUsagerDao extends JpaRepository<TypeUsager, Integer> {

    Optional<TypeUsager> findByRole(String role);
}
