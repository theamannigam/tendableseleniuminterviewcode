package introductionselenium;

import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.asserts.SoftAssert;

public class TendableTechChallenge {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		System.setProperty("webDriver.chrome.driver", "C:\\Users\\G15\\Downloads\\chromedriver-win32.exe");

		ChromeDriver driver=new ChromeDriver();
        SoftAssert softAssert = new SoftAssert();

		driver.manage().window().maximize();
	    driver.get("https://www.tendable.com/");
    
	     
	            // Task 1: Confirm accessibility of the top-level menus
	            String[] menus = {"Our Story", "Our Solution", "Why Tendable?"};
	            int l=menus.length;
	            for (int i=0;i<l;i++) {
	            	String menu=menus[i];
	                WebElement menuElement = driver.findElement(By.linkText(menu));
	                if(menuElement.isDisplayed()) {
	                System.out.println(menu + " menu is accessible.");
	                }
	                else
	                {
	                	System.out.println(menu + " menu is not accessible.");	
	                	}
	                     } //for loop closed
	            // Task 2: Verify "Request a Demo" button presence and Active
	            for (int i=0;i<l;i++) {
	            	String menu=menus[i];
	                driver.findElement(By.linkText(menu)).click();
	                WebElement RequestAdemoButton = driver.findElement(By.linkText("Request A Demo"));

	                if(RequestAdemoButton.isDisplayed()) {
	                System.out.println("In " +menu + " menu Request A Demo Button is presence & Active.");
	                }
	                else
	                {
	                	System.out.println("In " +menu + " menu Request A Demo Button is not presence & not Active.");	
	                	}
	            	
	            }
	            
	            // Task 3: Navigate to "Contact Us" section, choose "Contact" in marketing, and submit the form 
	            WebElement AboutUs = driver.findElement(By.xpath("//li/a[text()='About Us']"));
	            AboutUs.click();
	            driver.findElement(By.xpath("//li/a[text()='Contact us']")).click();
	            driver.findElement(By.xpath("(//div/button[text()='Contact'])[1]")).click();
               
	           driver.findElement(By.xpath("(//div/input[@name='fullName'])[1]")).sendKeys("Aman nigam");
	           driver.findElement(By.xpath("(//div/input[@name='organisationName'])[1]")).sendKeys("xyz");
	           driver.findElement(By.xpath("(//div/input[@name='cellPhone'])[1]")).sendKeys("9999889999");
	           driver.findElement(By.xpath("(//div/input[@name='email'])[1]")).sendKeys("iamanniagam@gmail.com");
               
	           WebElement jobrole= driver.findElement(By.xpath("(//div/select[@name='jobRole'])[1]"));
	           Select select=new Select(jobrole);
               select.selectByVisibleText("Other");
               WebElement RadioButton = driver.findElement(By.xpath("(//input[@name='consentAgreed'])[1]"));
	           
               
               JavascriptExecutor executor = (JavascriptExecutor) driver;
               executor.executeScript("arguments[0].click();", RadioButton);
               WebElement submitbutton = driver.findElement(By.xpath("(//div/button[@name='form_page_submit'])[1]"));
                 //Submitting the form
	           executor.executeScript("arguments[0].click();", submitbutton);
	           Thread.sleep(3000);
               //finding error class
	           WebElement errorMessage = driver.findElement(By.xpath("//div[@class='ff-form-errors']"));
	           //using assertion to check if error class is displayed pass the test
	           softAssert.assertTrue(errorMessage.isDisplayed(), "Error message is not displayed.");
	          //Task 4 generate Bug report if error message is not displayed
	           if (errorMessage.isDisplayed()) {
	              
	        	   System.out.println("Test for empty 'Message' field passed. Error message is displayed.");
	           } else {
	               System.out.println("Test for empty 'Message' field failed. No error message displayed.");
	               // Generate a bug report
	               TendableTechChallenge.generateBugReport();
	           } 
	           softAssert.assertAll();
	}
	
	//function to generateBugReport
	public static void generateBugReport() {
        // Create a new Word document
        XWPFDocument document = new XWPFDocument();

        // Add content to the document
        XWPFParagraph title = document.createParagraph();
        title.setAlignment(org.apache.poi.xwpf.usermodel.ParagraphAlignment.CENTER);
        XWPFRun titleRun = title.createRun();
        titleRun.setText("Bug Report");

        XWPFParagraph content = document.createParagraph();
        XWPFRun contentRun = content.createRun();
        contentRun.setText("Test for empty 'Message' field failed. No error message displayed.");

        // Save the document to a file
        try (FileOutputStream out = new FileOutputStream("BugReport.docx")) {
            document.write(out);
            System.out.println("Bug report generated successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }}


	
	
	
	
	


