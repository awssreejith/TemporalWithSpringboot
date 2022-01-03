package com.sreejith.mainservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

public class MainServiceImpl implements ImainService{



    IGetterService getterService = ActivityFactory.getGetterServiceInstance();

    @Override
    public String execute(String country) {
        //Step-1 - Get the welcome message from provider service
        System.out.println("Sreejith - Step-1 Entry");
        String welcome = getterService.getWelcome(country);
        System.out.println("Sreejith - Step-1 Exit");

        //Step-2 - Get captain from file
        System.out.println("Sreejith - Step-2 Entry");
        String captain = getterService.getCaptains(country);
        System.out.println("Sreejith - Step-2 Exit");

        //Aggregate the result
        String finalResult = "Hi - "+welcome+captain;
        return finalResult;
    }
}
