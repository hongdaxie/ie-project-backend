package com.hongda.dao;

import com.hongda.domain.Grandparent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GrandparentDao extends JpaRepository<Grandparent, Long> {

    @Query(value = "select * from grandparent where userid = ?1", nativeQuery = true)
    List<Grandparent> getAllByUserid(Long userid);

    @Query(value = "select distinct email from grandparent", nativeQuery = true)
    List<String> getAllDistinctEmail();

}
