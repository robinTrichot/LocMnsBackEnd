package com.projetLocMns.ProjetFilRougeLocMnsV3.dao;

import com.projetLocMns.ProjetFilRougeLocMnsV3.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDao extends JpaRepository<User, Integer>{

    Optional<User> findByMail(String mail);

}
