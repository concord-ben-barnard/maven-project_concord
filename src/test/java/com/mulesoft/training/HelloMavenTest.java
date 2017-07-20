package com.mulesoft.training;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.mule.api.MuleEvent;
import org.mule.tck.junit4.FunctionalTestCase;
import org.mule.tck.junit4.rule.DynamicPort;

public class HelloMavenTest extends FunctionalTestCase {

	@Rule
	public DynamicPort dynamicPort = new DynamicPort("http.port");
	
    @Test
    public void mavenFlowReturnsHelloMaven() throws Exception {
    	System.out.println("\n\n Dynamic Port: " + dynamicPort.getNumber() + "\n\n");
        runFlowAndExpect("mavenFlow", "Hello Maven");
    }
    
    @Override
    protected String[] getConfigFiles() {
        String files[] = {"maven-project.xml", "global.xml"};
    	return files;
    }

    @Test
    public void retrieveFlightsAddsAppropriateHeader() throws Exception {
      MuleEvent event = runFlow("retrieveFlightsFlow");
      String contentType = event.getMessage().getOutboundProperty("Content-Type");
      assertEquals("application/json", contentType);
    }
    
}
