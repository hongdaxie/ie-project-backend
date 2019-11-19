package com.hongda.dao;

import com.hongda.domain.CountriesInfo;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CountriesInfoDao extends JpaRepository<CountriesInfo, Long> {
}
