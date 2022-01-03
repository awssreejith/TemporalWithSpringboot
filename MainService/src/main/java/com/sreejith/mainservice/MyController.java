package com.sreejith.mainservice;

import io.temporal.client.WorkflowOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MyController {

    @Autowired
    private WorkFlowObserver workFlowObserver;

    @GetMapping("/{input}")
    public ResponseEntity<?> getWelcome(@PathVariable ("input") String input)
    {
        //Create a new workflow client for every invocation
        System.out.println("Sreejith - Going to create workflow client");
        ImainService workflow = workFlowObserver.getClient().newWorkflowStub(ImainService.class,
                WorkflowOptions.newBuilder().setTaskQueue("sreejith_task_queue").build());

        System.out.println("Sreejith - Created workflow client");
        String toReturn = workflow.execute(input);
        System.out.println("Sreejith - Execution complete and return");
        return ResponseEntity.status(HttpStatus.OK).body(toReturn);
    }
}
