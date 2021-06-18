package core.listener;

import io.cucumber.plugin.ConcurrentEventListener;
import io.cucumber.plugin.event.EventHandler;
import io.cucumber.plugin.event.EventPublisher;
import io.cucumber.plugin.event.TestCaseFinished;
import io.cucumber.plugin.event.TestCaseStarted;

import com.google.common.flogger.FluentLogger;

public class TestListener implements ConcurrentEventListener
{
    private static final FluentLogger logger = FluentLogger.forEnclosingClass();

    @Override
    public void setEventPublisher(EventPublisher publisher) 
    {
        publisher.registerHandlerFor(TestCaseStarted.class,testCaseStarted);
         publisher.registerHandlerFor(TestCaseFinished.class, testCaseFinished);
        // publisher.registerHandlerFor(TestSourceRead.class, this::featureRead);
         publisher.registerHandlerFor(TestStepStarted.class, this::stepStarted);
        // publisher.registerHandlerFor(TestStepFinished.class, this::stepFinished);

        
    }

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

    private synchronized void testCaseStarted(TestCaseStarted event) {
        logger.atInfo().log("Scenario '%s' started " , event.getTestCase().getName());
    };

    private synchronized void testCaseFinished(TestCaseFinished event) {
        logger.atInfo().log("Scenario '%s' finished " , event.getTestCase().getName());
    };

   

}