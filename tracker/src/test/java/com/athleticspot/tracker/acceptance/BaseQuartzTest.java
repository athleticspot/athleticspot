package com.athleticspot.tracker.acceptance;

import org.apache.camel.CamelContext;
import org.apache.camel.component.quartz2.QuartzComponent;
import org.apache.camel.test.junit4.CamelTestSupport;

/**
 * @author Tomasz Kasprzycki
 */
public class BaseQuartzTest extends CamelTestSupport {

    @Override
    protected boolean useJmx() {
        return true;
    }

    @Override
    protected CamelContext createCamelContext() throws Exception {
        CamelContext context = super.createCamelContext();

        QuartzComponent quartz = context.getComponent("quartz2", QuartzComponent.class);
        quartz.setEnableJmx(useJmx());

        return context;
    }

}
