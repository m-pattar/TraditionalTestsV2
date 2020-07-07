package com.hackathon.projectspec;

import com.hackathon.utilgeneric.BaseTest;

public class Functions 
{
	public void verify_the_product_count_in_plp_for_selected_filter(String locator, String path) throws Exception {
		
		Thread.sleep(1000);
		BaseTest.log.info("Collect the product count in filter section");
		String countInFilterSection = BaseTest.l1.getWebElement(locator, path).getText();
		System.out.println("Count in the filter section:- "+ countInFilterSection);
		
		
		BaseTest.log.info("Collect the number of products in PLP");
		int productsCount = BaseTest.l1.getWebElements("product_tiles_in_plp", "Selectors\\Selectors.properties").size();
		System.out.println("Number of products in PLP:- "+ productsCount);
		
		BaseTest.sa.assertEquals(productsCount, Integer.parseInt(countInFilterSection), "Verify the products count in PLP with filter count in refinement section");
		
		BaseTest.sa.assertAll();
	}
	
	public void verifyTheProductImageInPDP() throws Exception {
		String style = BaseTest.l1.getWebElement("product_image_in_pdp", "Selectors\\Selectors.properties").getAttribute("style");
		BaseTest.sa.assertTrue(style.contains("background-image:"),"Verify the product image in PDP");
		BaseTest.sa.assertAll();
	}
}
