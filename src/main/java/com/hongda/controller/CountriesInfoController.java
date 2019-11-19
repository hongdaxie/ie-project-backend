package com.hongda.controller;

import com.hongda.domain.CountriesInfo;
import com.hongda.dao.CountriesInfoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@EnableAutoConfiguration
@CrossOrigin
public class CountriesInfoController {

    @Autowired
    private CountriesInfoDao countriesRepository;

    @GetMapping("/countries")
    public List<CountriesInfo> getAllCountries(){
        return countriesRepository.findAll();
    }

    @GetMapping("/country/{id}")
    public Optional<CountriesInfo> getCountryInfoByName(@PathVariable(value = "id") Long id) {
        Optional<CountriesInfo> countriesInfo = countriesRepository.findById(id);
        return countriesInfo;
    }




}
