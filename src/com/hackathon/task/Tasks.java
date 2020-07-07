package com.hackathon.task;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.xml.XmlTest;

import com.hackathon.utilgeneric.BaseTest;
import com.hackathon.utilgeneric.GetDriver;



public class Tasks extends BaseTest
{
//	@Test(groups= {"reg"}, description="Task 1 – Cross-Device Elements Test")
	
	@Test(description="1", priority = 1)
	public void cross_device_elements_test() throws Exception {
		
		log.info("Verify the search field element");
		if (gVar.mobileView())
		{
			sa.assertTrue(l1.getWebElement("header_search_icon_mobile", "Selectors\\Selectors.properties").isDisplayed(),"Mobile search box is displaying");
			sa.assertFalse(l1.getWebElement("desktop_search_box", "Selectors\\Selectors.properties").isDisplayed(), "Desktop search box should not display");
		}
		else
		{
			sa.assertTrue(l1.getWebElement("desktop_search_box", "Selectors\\Selectors.properties").isDisplayed(), "Desktop search box is displaying");
			sa.assertFalse(l1.getWebElement("header_search_icon_mobile", "Selectors\\Selectors.properties").isDisplayed(),"Mobile search box should not display");
		}


		sa.assertAll();
	}
	

	@Test( description="2", priority = 2)
	public void shopping_experience_test() throws Exception {
		
		log.info("Click on Filter icon for mobile view");
		if(gVar.mobileView()||gVar.tabletView()) {
			l1.getWebElement("filter_link_mobile", "Selectors\\Selectors.properties").click();
		}
		
		log.info("Click on Black filter refinement");
		l1.getWebElement("black_color_refinement", "Selectors\\Selectors.properties").click();
		
		log.info("Collect the product count in filter section");
		String countInFilterSection = l1.getWebElement("black_color_refinement_products_count", "Selectors\\Selectors.properties").getText();
		
		log.info("Click on filter button");
		l1.getWebElement("filter_button", "Selectors\\Selectors.properties").click();
		
		
		log.info("Collect the number of products in PLP");
		int productsCount = l1.getWebElements("product_tiles_in_plp", "Selectors\\Selectors.properties").size();
		
		sa.assertEquals(productsCount, Integer.parseInt(countInFilterSection), "Verify the products count in PLP with filter count in refinement section");
		
		sa.assertAll();
	}
	
	
	@Test(description="3", priority = 3)
	public void product_details_test() throws Exception {
		
		log.info("Collect the product price in PLP");
		String productPriceInPLP = l1.getWebElement("product_price_in_plp", "Selectors\\Selectors.properties").getText();
		
		log.info("Click on first product in PLP");
		l1.getWebElement("first_product_in_plp", "Selectors\\Selectors.properties").click();
		
		log.info("Verify the product image in PDP");
		sa.assertTrue(l1.getWebElement("product_image_in_pdp","Selectors\\Selectors.properties").isDisplayed(), "Verify the product image in PDP");
		
		log.info("Verify the product price in PDP");
		String productPriceInPDP = l1.getWebElement("product_price_in_pdp", "Selectors\\Selectors.properties").getText();
		sa.assertEquals(productPriceInPDP, productPriceInPLP, "Verify the PLP product price in PDP");
		
		log.info("Verify the selected Size in PDP");
		sa.assertEquals(l1.getWebElement("selected_size_in_pdp", "Selectors\\Selectors.properties").getText(), 
				"Small (S)", "Verify the selected size in size dropdown box");
		
		sa.assertAll();
		
	}
	
	
	/*@Test(description="1")
	public void cross_device_elements_test() throws Exception {
		
		gVar.hover();
		
		String script = "return window.getComputedStyle(document.querySelector('i.ti-heart'),':before').getPropertyValue('content')";
		System.out.println("script:- "+ script);
		JavascriptExecutor js = (JavascriptExecutor)driver;
		String content = (String) js.executeScript(script);
		System.out.println("**********8content:- "+content);
		    
		sa.assertAll();
	}*/
}
