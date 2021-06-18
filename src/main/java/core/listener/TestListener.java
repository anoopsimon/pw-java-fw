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

import com.google.common.flogger.FluentLogger;

public class TestListener implements ConcurrentEventListener
{
    private static final FluentLogger logger = FluentLogger.forEnclosingClass();

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
    };

    private synchronized void testRunFinished(TestRunFinished event) {
        logger.atInfo().log("Testrun '%s' finished " , event.getMessage());
    };
    private synchronized void testCaseStarted(TestCaseStarted event) {
        logger.atInfo().log("Scenario '%s' started " , event.getTestCase().getName());
    };

    private synchronized void testCaseFinished(TestCaseFinished event) {
        logger.atInfo().log("Scenario '%s' finished " , event.getTestCase().getName());
    };

    private synchronized void stepStarted(TestStepStarted event) {
        logger.atInfo().log("Step '%s' started " , event.getTestStep().getId());
    };

    private synchronized void stepFinished(TestStepFinished event) {
        logger.atInfo().log("Step '%s' finished " , event.getTestStep().getId());
    };

   

}