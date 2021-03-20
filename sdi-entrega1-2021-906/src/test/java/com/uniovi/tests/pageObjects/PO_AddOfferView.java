package com.uniovi.tests.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.uniovi.tests.util.SeleniumUtils;

public class PO_AddOfferView extends PO_NavView {

	static public void fillForm(WebDriver driver, String titlep,String descripcionp,String pricep) {
		WebElement title = driver.findElement(By.name("title"));
		title.click();
		title.clear();
		title.sendKeys(titlep);
		WebElement descripcion = driver.findElement(By.name("description"));
		descripcion.click();
		descripcion.clear();
		descripcion.sendKeys(descripcionp);
		WebElement price = driver.findElement(By.name("price"));
		price.click();
		price.clear();
		price.sendKeys(pricep);
		
		By boton = By.className("btn");
		driver.findElement(boton).click(); 
	}
	
	static public void navigate(WebDriver driver,String element,int click) {
		
		List<WebElement> elementos = checkElement(driver, "free", element);
		elementos.get(click).click();
	}
	
	static public List<WebElement> checkKey(WebDriver driver, String key, int locale) {
		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "text", p.getString(key, locale), getTimeout());
		return elementos;
	}
}