package com.minkatec.Diary.daos;

import com.minkatec.Diary.entities.Diary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiaryDao extends JpaRepository<Diary,Integer> {
}
