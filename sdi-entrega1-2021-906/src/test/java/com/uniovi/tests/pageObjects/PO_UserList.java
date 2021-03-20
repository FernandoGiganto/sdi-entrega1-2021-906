package com.uniovi.tests.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PO_UserList extends PO_NavView {

	static public void fillForm(WebDriver driver, String titlep) {
		WebElement title = driver.findElement(By.name("searchText"));
		title.click();
		title.clear();
		title.sendKeys(titlep);
		
		
		By boton = By.className("btn");
		driver.findElement(boton).click(); 
	}

}
