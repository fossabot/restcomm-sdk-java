package org.restcomm.connect.java.sdk;

import static org.junit.Assert.*;

import java.io.IOException;

import javax.xml.bind.JAXBException;
import java.io.File;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.restcomm.connect.java.sdk.Accounts.*;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockRule;

import org.restcomm.connect.java.sdk.Restcomm;
import org.restcomm.connect.java.sdk.Accounts.Account;


public class AccountTest extends BasicTest{
	
	@Rule 
    public WireMockRule wireMockRule = new WireMockRule(8080);
	
	@Test
	public void testHttpResponse() throws Exception { 
		WireMock.stubFor(WireMock.get(WireMock.urlPathMatching("/Accounts.json/"+Restcomm.getAuthID()))
				  .withBasicAuth(Restcomm.getAuthID(),Restcomm.getPassword())
				  .willReturn(WireMock.aResponse()
				  .withStatus(200)
				  .withHeader("Content-Type", "application/json")
				  .withBody(readFile("GetResponse.txt"))));
		
		Account a = Account.getAccount();
		
		WireMock.verify(WireMock.getRequestedFor(WireMock.urlEqualTo("/Accounts.json/"+Restcomm.getAuthID())));
		assertEquals(200, Restcomm.getStatusCode());
		

    }
	
	

	

}
