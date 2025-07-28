package listenersUtility;

import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import baseClass.BaseClass;

public class ListenersImplementation implements ITestListener, ISuiteListener { 

	public ExtentSparkReporter spark;
	public ExtentReports report;
	public ExtentTest test;

	@Override 
	public void onStart(ISuite suite) { 
		Reporter.log("Report Configuration",true);
		Date d = new Date(); 
		String newdate = d.toString().replace(" ", "_").replace(":", "_");

		spark = new ExtentSparkReporter("./TestReports/report"+newdate+".html");
		spark.config().setDocumentTitle("Ninza CRM Test Report " + newdate);
		spark.config().setReportName("Jack Maa");
		spark.config().setTheme(Theme.DARK);

		report = new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("OS", "Windows11");
		report.setSystemInfo("Browser", "Chrome");
	} 

	@Override 
	public void onFinish(ISuite suite) {
		report.flush();
		Reporter.log("Report backup", true);
	} 

	@Override 
	public void onTestStart(ITestResult result) { 
		//		Project >> right click >> Properties >>JRE system library >>Edit >> choose correct one
		Reporter.log("onTestStart",true);
		test = report.createTest(result.getMethod().getMethodName());
		test.log(Status.INFO, "=> " +result.getMethod().getMethodName()+ " : Execution Started");
	} 

	@Override 
	public void onTestSuccess(ITestResult result) { 
		String testname = result.getMethod().getMethodName(); 
		Reporter.log("===="+testname+" Passed===",true); 
		test.log(Status.PASS, "=> "+testname + " : Pass");
	} 

	@Override 
	public void onTestFailure(ITestResult result) { 
		String testname = result.getMethod().getMethodName(); 
		Reporter.log("===="+testname+" Failed===",true); 
		Date d = new Date(); 
		String newdate = d.toString().replace(" ", "_").replace(":", "_");
		TakesScreenshot ts = (TakesScreenshot) BaseClass.sdriver;
		String temp = ts.getScreenshotAs(OutputType.BASE64);
		test.addScreenCaptureFromBase64String(temp, testname+newdate); 
		test.log(Status.FAIL, "=> "+testname + " : Fail");
	} 

	@Override
	public void onTestSkipped(ITestResult result) {
		String testname = result.getMethod().getMethodName(); 
		Reporter.log("<==== "+testname+" Skipped ===>",true); 
		test.log(Status.SKIP, "=> "+testname + " : Skipped");
	} 
}
