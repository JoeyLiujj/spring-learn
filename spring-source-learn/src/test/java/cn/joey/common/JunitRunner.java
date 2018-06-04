package cn.joey.common;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class JunitRunner {
	public static void main(String[] args) {
		Result classes = JUnitCore.runClasses(PrepareMybagTest.class);
		for(Failure failure:classes.getFailures()){
			System.out.println(failure.toString());
		}
		if(classes.wasSuccessful()){
			System.out.println("All tests finished successfully...");
		}
	}
}
