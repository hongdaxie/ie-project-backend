package com.hongda.dao;

import com.hongda.domain.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HospitalDao extends JpaRepository<Hospital, Long> {
    @Query(value = "select * from hospitals where LOWER(suburb) LIKE LOWER(concat(?1,'%'))", nativeQuery = true)
    public List<Hospital> findBySuburb(String suburb);
}
