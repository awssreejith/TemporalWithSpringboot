package com.sreejith.mainservice;

import io.temporal.client.WorkflowClient;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.worker.Worker;
import io.temporal.worker.WorkerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class WorkFlowObserver {

    private WorkflowClient workflowClient;
    private WorkerFactory workerFactory;

    //The queue which will be used to communicate to temporal server
    String taskQueue = "sreejith_task_queue";

    @PostConstruct
    void initialize()
    {
        WorkflowServiceStubs serviceStubs = WorkflowServiceStubs.newInstance();
        workflowClient = WorkflowClient.newInstance(serviceStubs);
        workerFactory = WorkerFactory.newInstance(workflowClient);
        Worker worker = workerFactory.newWorker(taskQueue);

        //worker client is created. Now register both WorkflowInterface and ActivityInterface
        //i.e Register mainserviceImpl and GetterServiceImpl
        worker.registerWorkflowImplementationTypes(MainServiceImpl.class);
        worker.registerActivitiesImplementations(new GetterServiceImpl());
        workerFactory.start();
    }

    public WorkflowClient getClient()
    {
        return workflowClient;
    }

    @PreDestroy
    void shutdown()
    {
        workerFactory.shutdown();
    }
}
