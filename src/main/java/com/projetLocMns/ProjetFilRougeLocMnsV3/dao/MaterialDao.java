package com.projetLocMns.ProjetFilRougeLocMnsV3.dao;

import com.projetLocMns.ProjetFilRougeLocMnsV3.model.Material;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MaterialDao extends JpaRepository<Material, Integer> {
    public Optional<Material> findByNotice(String noticeName);
}
