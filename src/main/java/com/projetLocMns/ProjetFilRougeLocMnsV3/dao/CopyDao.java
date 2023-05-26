package com.projetLocMns.ProjetFilRougeLocMnsV3.dao;

import com.projetLocMns.ProjetFilRougeLocMnsV3.model.Copy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface CopyDao extends JpaRepository<Copy, Integer> {

    @Query("SELECT COUNT(c) FROM Copy as c JOIN c.material as m WHERE c.status = 'free' ")
    List<Object[]> findByStatus();

    @Query("FROM Copy c JOIN c.material m ON m.id = c.material.id WHERE m.wording = :wording")
    List<Copy> findByWording(@Param("wording") String wording);

    @Query("FROM Copy c JOIN c.material m ON m.id = c.material.id WHERE m.id = :id")
    List<Copy> findCopyByMaterialId(@Param("id") Integer id);
}
