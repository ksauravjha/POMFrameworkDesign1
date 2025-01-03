package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By productHeader= By.tagName("h1");
	private By productImages=By.cssSelector("ul.thumbnails img");
	
	private By productMetaDta=By.xpath("(div[@id='content']//ul[@class='list-unstyled'])[position()=1]/li)");
	private By productPriceDta=By.xpath("(div[@id='content']//ul[@class='list-unstyled'])[position()=2]/li)");
	private By quantity= By.id("input-quantity");
	private By addToCartBtn= By.id("button-cart");
	private By CartSuccessPage = By.cssSelector("div.alert.alert-success");
	
	public ProductInfoPage(WebDriver driver)
	{
		this.driver=driver;
		eleUtil= new ElementUtil(driver);
	}
	
	public String getProductHeaderValue()
	{
		String productHeaderVal= eleUtil.doElementGetText(productHeader);
	    return productHeaderVal;
	}
	
	public int getProductImagesCount()
	{
		int imagesCount=eleUtil.waitForElementsVisible(productImages, AppConstants.DEFAULT_MEDIUM_TIME_OUT).size();
	    return imagesCount;
	}
	
	public void enterQuantity (int qty)
	{
		System.out.println(qty);
		eleUtil.doSendKeys(quantity,String.valueOf(qty));	
	}
	
	public String addProductToCart()
	{
		eleUtil.doClick(addToCartBtn);
		String successMessage = eleUtil.waitForElementVisible(CartSuccessPage, AppConstants.DEFAULT_MEDIUM_TIME_OUT).getText();
		StringBuilder sb = new StringBuilder(successMessage);
		sb.substring(0,successMessage.length()-1).replace("\n", "");
		return sb.toString();
	}
	
	
	public Map<String, String> getProductInfo()
	{
	//	Map<String, String> productInfoMap = new HashMap<String, String>();
	
	// If we want to maintain the order, then we will use Linked Hash map
		Map<String, String> productInfoMap = new LinkedHashMap<String, String>();
		List<WebElement> metalist = eleUtil.getElements(productMetaDta);
        for(WebElement e: metalist)
        {
        	String meta = e.getText();
        	String metaInfo[]=meta.split(":");
        	String key = metaInfo[0].trim();
        	String value = metaInfo[1].trim();
        	productInfoMap.put(key, value);
        	
        }
        
        List<WebElement> priceList = eleUtil.getElements(productPriceDta);
        String price= priceList.get(0).getText();
        String exTax=priceList.get(1).getText();
        String extaxVal= exTax.split(":")[1].trim();
        
        productInfoMap.put("productprice", price);
        productInfoMap.put("exTax", extaxVal);
        
        return productInfoMap;
         
	
	}
	
	

}
