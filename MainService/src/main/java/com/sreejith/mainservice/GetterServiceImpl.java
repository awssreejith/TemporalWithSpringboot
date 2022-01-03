package com.sreejith.mainservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.*;

public class GetterServiceImpl implements IGetterService{

    @Autowired
    private RestTemplate restTemplate = new RestTemplate();

    private final String welcomeUrl = "http://localhost:8089/api/welcome/";

    public String getWelcome(String input)
    {
        //String fullUrl = String.format(welcomeUrl,input);
        String fullUrl = welcomeUrl+input;
        //Step-1 - get the welcome note from provider service
        String welcomeNote =  restTemplate.getForEntity(fullUrl,String.class).getBody();
        return  welcomeNote;
    }

    public String getCaptains(String country) {
        String captain = null;
        try {
            captain = this.getCaptainFromFile(country);
        }
        catch (Exception ex)
        {
            captain = "Cant find a captain";
        }

        //Step-3 Aggregate the result
        String finalReturn = " - your captains is - "+captain;
        return finalReturn;
    }

    private String getCaptainFromFile(String country) throws IOException {
        File file = new File("/home/sreejith/MyStudy/MyJava/TemporalTuts/MainService/src/main/resources/captains.txt");
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line = null;
        while((line = br.readLine())!=null)
        {
            String countryFound = line.split(":")[0].stripLeading().stripTrailing();
            if(countryFound.equals(country))
            {
                return line.split(":")[1].stripLeading().stripTrailing();
            }
        }
        return "None";
    }
}
