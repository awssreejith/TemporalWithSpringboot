package com.sreejith.mainservice;

import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

@WorkflowInterface
public interface ImainService {

    @WorkflowMethod
    String execute(String country);
}
