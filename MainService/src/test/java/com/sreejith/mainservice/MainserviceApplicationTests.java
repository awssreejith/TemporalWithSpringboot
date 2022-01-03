package com.sreejith.mainservice;

import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import io.temporal.testing.TestWorkflowEnvironment;
import io.temporal.testing.WorkflowReplayer;
import io.temporal.worker.Worker;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class MainserviceApplicationTests {

	private  static TestWorkflowEnvironment testEnv;
	private  static Worker worker;
	private  static WorkflowClient client;
	private  static String taskQueue = "TestQueue_For_Testing";
	private  static IGetterService activities = mock(IGetterService.class);

	@BeforeAll
	public static void myInit() {
		testEnv = TestWorkflowEnvironment.newInstance();
		worker = testEnv.newWorker(taskQueue);
		worker.registerWorkflowImplementationTypes(MainServiceImpl.class);
		worker.registerActivitiesImplementations(activities);
		client = testEnv.getWorkflowClient();
	}

	@AfterAll
	public static void myDestroy() {
		testEnv.close();
	}

	@Test
	public void testExecuteFunction()
	{
		// mock our workflow activities

		String country = "India";

		// mock activity methods
		when(activities.getCaptains(country)).thenReturn("Kohli");
		when(activities.getWelcome(country)).thenReturn("Hi India - welcome");
		testEnv.start();

		ImainService workflow =
				client.newWorkflowStub(
						ImainService.class, WorkflowOptions.newBuilder()
								.setTaskQueue(taskQueue).build());

		// Execute a workflow waiting for it to complete.
		String finalResult = workflow.execute(country);

		// Small checks
		Assertions.assertNotNull(finalResult);
	}

	//Always put the json file inside resource folder
	@Test
	public void testOnboardingReplay() throws Exception {
		WorkflowReplayer.replayWorkflowExecutionFromResource(
				"TestRun.json", MainServiceImpl.class);
	}

}
