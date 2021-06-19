package core.listener;

import io.cucumber.plugin.ConcurrentEventListener;
import io.cucumber.plugin.event.EventHandler;
import io.cucumber.plugin.event.EventPublisher;
import io.cucumber.plugin.event.TestCaseFinished;
import io.cucumber.plugin.event.TestCaseStarted;
import io.cucumber.plugin.event.TestRunFinished;
import io.cucumber.plugin.event.TestRunStarted;
import io.cucumber.plugin.event.TestStepFinished;
import io.cucumber.plugin.event.TestStepStarted;
import model.TestResult;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.google.common.flogger.FluentLogger;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;

import core.util.TestReportUtil;

public class TestListener implements ConcurrentEventListener
{
    private static final FluentLogger logger = FluentLogger.forEnclosingClass();
    private List<TestResult> testResults;
    private TestResult testResult;

    @Override
    public void setEventPublisher(EventPublisher publisher) 
    {
        publisher.registerHandlerFor(TestRunStarted.class,testRunStarted);
        publisher.registerHandlerFor(TestRunFinished.class,testRunFinished);
        publisher.registerHandlerFor(TestCaseStarted.class,testCaseStarted);
         publisher.registerHandlerFor(TestCaseFinished.class, testCaseFinished);
         publisher.registerHandlerFor(TestStepStarted.class, testStepStarted);
         publisher.registerHandlerFor(TestStepFinished.class,testStepFinished);
        
    }

    private EventHandler<TestRunStarted> testRunStarted = new EventHandler<TestRunStarted>() {
		@Override
		public void receive(TestRunStarted event) {
			testRunStarted(event);
		}
	};
    private EventHandler<TestRunFinished> testRunFinished = new EventHandler<TestRunFinished>() {
		@Override
		public void receive(TestRunFinished event) {
			testRunFinished(event);
		}
	};


    private EventHandler<TestCaseStarted> testCaseStarted = new EventHandler<TestCaseStarted>() {
		@Override
		public void receive(TestCaseStarted event) {
			testCaseStarted(event);
		}
	};

    private EventHandler<TestCaseFinished> testCaseFinished = new EventHandler<TestCaseFinished>() {
		@Override
		public void receive(TestCaseFinished event) {
			testCaseFinished(event);
		}
	};

    private EventHandler<TestStepStarted> testStepStarted = new EventHandler<TestStepStarted>() {
		@Override
		public void receive(TestStepStarted event) {
			stepStarted(event);
		}
	};

    private EventHandler<TestStepFinished> testStepFinished = new EventHandler<TestStepFinished>() {
		@Override
		public void receive(TestStepFinished event) {
			stepFinished(event);
		}
	};


    private synchronized void testRunStarted(TestRunStarted event) {
        logger.atInfo().log("Testrun '%s' started " , event.getInstant().toString());
        testResults= new ArrayList<TestResult>();
    };

    private synchronized void testRunFinished(TestRunFinished event) 
    {
        logger.atInfo().log("Testrun '%s' finished " , event.getInstant());
        TestReportUtil.writeResultsToFile(testResults);   
    };
    private synchronized void testCaseStarted(TestCaseStarted event) {
        logger.atInfo().log("Scenario '%s' started " , event.getTestCase().getName());
        testResult= new TestResult();
        testResult.testCaseName=event.getTestCase().getName();
        testResult.testStatus="In Progress";
    };

    private synchronized void testCaseFinished(TestCaseFinished event)  {
        logger.atInfo().log("Scenario '%s' finished " , event.getTestCase().getName());
        testResult.testStatus=event.getResult().getStatus().name();
        testResult.duration = event.getResult().getDuration().toMillis();
        testResults.add(testResult);
        logger.atInfo().log("Array " + testResults);

    }

    private synchronized void stepStarted(TestStepStarted event) {
        logger.atInfo().log("Step '%s' started " , event.getTestStep().getId());
    };

    private synchronized void stepFinished(TestStepFinished event) {
        logger.atInfo().log("Step '%s' finished " , event.getTestStep().getId());
    };

   

}