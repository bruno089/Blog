package com.minkatec.Blog.daos;

import com.minkatec.Blog.entities.ConfirmationCode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfirmationCodeDao extends JpaRepository<ConfirmationCode,Integer> {

    ConfirmationCode findByCode(String confirmationCode);

}
