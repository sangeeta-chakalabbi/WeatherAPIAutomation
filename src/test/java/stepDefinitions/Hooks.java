package stepDefinitions;

import org.apache.log4j.Logger;

import utils.TestProperties;
import helpers.MainHelper;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {
	
	public static String baseURL = new String(); 
	public static MainHelper helper = new MainHelper();
	String key = new String();

    static final Logger log = Logger.getLogger(Hooks.class);

	
	@Before
	public void starter() {
		//Fetches the pre-requisites for the test
		log.info("Setting up pre-requisites for the Test");
		baseURL = helper.getPropertyFromConfig(TestProperties.PARAM_BASE_URL);
		key = helper.getPropertyFromConfig(TestProperties.KEY);
		log.info("Setting up pre-requisites for the Test:=>"+"url:"+baseURL);
	}
	
	@After
	public void teardown() {
		log.info("End of the test");	}

}
