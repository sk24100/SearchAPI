package steps;

import io.cucumber.java.Before;
import io.cucumber.java.After;
public class Hooks {

	@Before("@postgetapi")  //Means cucumber will say beforre i run @postgetapi scenario this scenario should run
	public void beforeScenaior() {
		//write a code which tells place id..
		//execute this code only if place id is null..
	}
	
	@After()
	public void afterScenario() {
		System.out.println("Running after scenarios have ran");
	}
	
}
