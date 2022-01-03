package com.sreejith.mainservice;

import io.temporal.activity.ActivityInterface;

import java.io.IOException;

@ActivityInterface
public interface IGetterService {
    String getWelcome(String input);
    String getCaptains(String country);
}
