package com.athleticspot.tracker.acceptance;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.component.quartz2.QuartzComponent;
import org.junit.Ignore;
import org.junit.Test;

/**
 * @author Tomasz Kasprzycki
 */
public class QuartzAutoStartTest extends BaseQuartzTest {

    @Test
    @Ignore
    public void testQuartzAutoStart() throws Exception {
        MockEndpoint mock = getMockEndpoint("mock:one");
        mock.expectedMessageCount(0);

        QuartzComponent quartz = context.getComponent("quartz2", QuartzComponent.class);
        assertFalse("Should not have started scheduler", quartz.getScheduler().isStarted());

        Thread.sleep(2000);

        assertMockEndpointsSatisfied();

        mock.reset();
        mock.expectedMinimumMessageCount(1);

        // start scheduler

        quartz.getScheduler().start();

        assertMockEndpointsSatisfied();
    }


    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        return new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("quartz2://myGroup/myTimerName?cron=0/1+*+*+*+*+?&autoStartScheduler=false").to("mock:one");
            }
        };
    }
}
