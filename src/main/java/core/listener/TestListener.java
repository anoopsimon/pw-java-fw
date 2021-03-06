package core.listener;

import java.util.ArrayList;
import java.util.List;

import com.google.common.flogger.FluentLogger;

import core.util.TestReportUtil;
import io.cucumber.plugin.ConcurrentEventListener;
import io.cucumber.plugin.event.EventHandler;
import io.cucumber.plugin.event.EventPublisher;
import io.cucumber.plugin.event.PickleStepTestStep;
import io.cucumber.plugin.event.TestCaseFinished;
import io.cucumber.plugin.event.TestCaseStarted;
import io.cucumber.plugin.event.TestRunFinished;
import io.cucumber.plugin.event.TestRunStarted;
import io.cucumber.plugin.event.TestStepFinished;
import io.cucumber.plugin.event.TestStepStarted;
import io.cucumber.messages.Messages.GherkinDocument.Feature.Scenario.Examples;
import model.Step;
import model.TestResult;

public class TestListener implements ConcurrentEventListener {
    private static final FluentLogger logger = FluentLogger.forEnclosingClass();
    private List<TestResult> testResults;
    private TestResult testResult;
    private Step step;
    @Override
    public void setEventPublisher(EventPublisher publisher) {
        publisher.registerHandlerFor(TestRunStarted.class, testRunStarted);
        publisher.registerHandlerFor(TestRunFinished.class, testRunFinished);
        publisher.registerHandlerFor(TestCaseStarted.class, testCaseStarted);
        publisher.registerHandlerFor(TestCaseFinished.class, testCaseFinished);
        publisher.registerHandlerFor(TestStepStarted.class, testStepStarted);
        publisher.registerHandlerFor(TestStepFinished.class, testStepFinished);

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
        logger.atInfo().log("Testrun '%s' started ", event.getInstant().toString());
        testResults = new ArrayList<TestResult>();
    };

    private synchronized void testRunFinished(TestRunFinished event) {
        logger.atInfo().log("Testrun '%s' finished ", event.getInstant());
        TestReportUtil.writeResultsToFile(testResults);
    };

    private synchronized void testCaseStarted(TestCaseStarted event) {
        //event.getTestCase()
        
        logger.atInfo().log("Scenario '%s' started ", event.getTestCase().getName());
        testResult = new TestResult();
        testResult.testCaseName = event.getTestCase().getName();
        testResult.testStatus = "In Progress";
        testResult.tags = event.getTestCase().getTags();
    };

    private synchronized void testCaseFinished(TestCaseFinished event) {
        logger.atInfo().log("Scenario '%s' finished ", event.getTestCase().getName());
        testResult.testStatus = event.getResult().getStatus().name();
        testResult.duration = event.getResult().getDuration().toSeconds() < 1 ? event.getResult().getDuration().toMillis() : event.getResult().getDuration().toSeconds();
        testResult.durationDesc = event.getResult().getDuration().toSeconds() < 1 ?  event.getResult().getDuration().toMillis() + " Milliseconds": event.getResult().getDuration().toSeconds() + " Seconds";
        testResults.add(testResult);

    }

    private synchronized void stepStarted(TestStepStarted event) {
        logger.atInfo().log("Step '%s' started ", event.getTestStep().getId());
        PickleStepTestStep testStep = (PickleStepTestStep) event.getTestStep();
         step = new Step();
         step.stepName = testStep.getStep().getText();       
         
    };

    private synchronized void stepFinished(TestStepFinished event) {
        logger.atInfo().log("Step '%s' finished ", event.getTestStep().getId());
        step.stepStatus = event.getResult().getStatus().name();
        step.duration = event.getResult().getDuration().toSeconds();        
        testResult.testSteps.add(step);
    };

}