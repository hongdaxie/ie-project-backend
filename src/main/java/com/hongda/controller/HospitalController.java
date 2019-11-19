package com.hongda.controller;


import com.hongda.dao.HospitalDao;
import com.hongda.domain.Hospital;
import com.hongda.googleSearch.CustomerSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@EnableAutoConfiguration
@CrossOrigin
public class HospitalController {

    @Autowired
    private HospitalDao hospitalDao;

    @GetMapping("/hospitals")
    public List<Hospital> getAllHospitals(){
        return hospitalDao.findAll();
    }

    @GetMapping("/hospital/{id}")
    public Optional<Hospital> getHospitalById(@PathVariable(value = "id") Long id){
        Optional<Hospital> hospital = hospitalDao.findById(id);
        return hospital;
    }

    @GetMapping("/hospital/findBySuburb/{suburb}")
    public List<Hospital> getHospitalsBySuburb(@PathVariable(value = "suburb") String suburb){
        List<Hospital> hospitals = hospitalDao.findBySuburb(suburb.toUpperCase());
        for(Hospital hospital: hospitals){
            String imageSearchResult = CustomerSearch.googleSearch(hospital.getName(), new String[]{"num","searchType"}, new String[]{"1","image"});
            String imageURL = CustomerSearch.getImageSrc(imageSearchResult);
            hospital.setImage(imageURL);
        }
        return hospitals;
    }

    @GetMapping("/hospital/findAllSuburb")
    public Set<String> findAllSuburb(){
        List<Hospital> hospitals = new ArrayList<>();

        hospitals = hospitalDao.findAll();

        Set<String> resultList = new HashSet<>();
        for(Hospital hospital: hospitals){
            String hospitalName = hospital.getSuburb().trim().toUpperCase();
            resultList.add(hospitalName);
        }
        return resultList;
    }
}
