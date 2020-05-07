package com.minkatec.Blog.daos;

import com.minkatec.Blog.entities.ConfirmationCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ConfirmationCodeDao extends JpaRepository<ConfirmationCode,Integer> {

     ConfirmationCode findByCode(String confirmationCode);

}
