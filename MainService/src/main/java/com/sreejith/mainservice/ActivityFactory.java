package com.sreejith.mainservice;

import io.temporal.activity.ActivityOptions;
import io.temporal.workflow.Workflow;

import java.time.Duration;

public class ActivityFactory {

    public static IGetterService getGetterServiceInstance()
    {
        return Workflow.newActivityStub(IGetterService.class,
                ActivityOptions.newBuilder().setScheduleToCloseTimeout(Duration.ofSeconds(60)).build());
    }
}
