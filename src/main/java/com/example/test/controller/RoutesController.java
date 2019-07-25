package com.example.test.controller;

import com.example.test.model.Login;
import com.example.test.repository.LoginRepository;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/test")
public class RoutesController {

    @Autowired
    private LoginRepository loginRepository;

    @GetMapping(value = "/dates")
    public List<Login> getDates() {
        return loginRepository.findAll();
    }


    @GetMapping(value = "/users")
    public List<Login> getUsers(@RequestParam(value = "start", required = false) String startDate, @RequestParam(value = "end", required = false) String endDate) {
        if(startDate == null){
            startDate = LocalDate.ofEpochDay(0).toString().replace("-","");
        }
        if(endDate == null){
            endDate = LocalDate.now().toString().replace("-","");
        }
        return loginRepository.findAllByLogin_timeBetween(startDate, endDate);
    }


    @GetMapping(value = "/logins")
    public List<?> getLogins(@RequestParam("start") String startDate, @RequestParam("end") String endDate,
                             @RequestParam(value = "attribute1", required = false) List<String> attribute1, @RequestParam(value = "attribute2", required = false) List<String>  attribute2,
                             @RequestParam(value = "attribute3", required = false) List<String>  attribute3, @RequestParam(value = "attribute4", required = false) List<String>  attribute4) {

        List<Map> formattedResults = new ArrayList<>();
        Gson gson = new Gson();
        List<?> resultSet = new ArrayList<>();

        if (attribute1 == null && attribute2 == null && attribute3 == null && attribute4 == null) {
            resultSet = loginRepository.findAll(startDate, endDate);
        } else if (attribute1 != null && attribute2 == null && attribute3 == null && attribute4 == null) {
            resultSet = loginRepository.findAll(startDate, endDate, attribute1);
        } else if (attribute1 != null && attribute2 != null && attribute3 == null && attribute4 == null) {
            resultSet = loginRepository.findAll(startDate, endDate, attribute1, attribute2);
        } else if (attribute1 != null && attribute2 == null && attribute3 != null && attribute4 == null) {
            resultSet = loginRepository.findAll(startDate, endDate, attribute1, attribute2, attribute3);
        } else if (attribute1 != null && attribute2 == null && attribute3 == null && attribute4 != null) {
            resultSet = loginRepository.findAll(startDate, endDate, attribute1, attribute2, attribute3, attribute4);
        }

        for (Object i : resultSet) {
            String object = String.valueOf(i).replace("\", ","");
            Map map = gson.fromJson(object, Map.class);
            formattedResults.add(map);
        }
       return formattedResults;

    }

}