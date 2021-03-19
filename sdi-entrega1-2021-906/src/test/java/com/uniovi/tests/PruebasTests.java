package com.uniovi.tests;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.uniovi.tests.pageObjects.PO_HomeView;
import com.uniovi.tests.pageObjects.PO_LoginView;
import com.uniovi.tests.pageObjects.PO_Properties;
import com.uniovi.tests.pageObjects.PO_RegisterView;
import com.uniovi.tests.pageObjects.PO_View;

//Ordenamos las pruebas por el nombre del método
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PruebasTests {

	// En Windows (Debe ser la versión 65.0.1 y desactivar las actualizacioens
	// automáticas)):
	static String PathFirefox65 = "C:\\Program Files\\Mozilla Firefox\\firefox.exe";
	static String Geckdriver024 = "C:\\Users\\ferna\\Desktop\\PL-SDI-Sesión5-material\\PL-SDI-Sesión5-material\\geckodriver024win64.exe";
	// Común a Windows y a MACOSX
	static WebDriver driver = getDriver(PathFirefox65, Geckdriver024);
	static String URL = "http://localhost:8090";

	public static WebDriver getDriver(String PathFirefox, String Geckdriver) {
		System.setProperty("webdriver.firefox.bin", PathFirefox);
		System.setProperty("webdriver.gecko.driver", Geckdriver);
		WebDriver driver = new FirefoxDriver();
		return driver;
	}

	// Antes de cada prueba se navega al URL home de la aplicación
	@Before
	public void setUp() throws Exception {
		driver.navigate().to(URL);
	}

	// Después de cada prueba se borran las cookies del navegador
	@After
	public void tearDown() throws Exception {
		driver.manage().deleteAllCookies();
	}

	// Antes de la primera prueba
	@BeforeClass
	static public void begin() {

	}

	// Al finalizar la última prueba
	@AfterClass
	static public void end() {
		// Cerramos el navegador al finalizar las pruebas
		driver.quit();
	}

	// Registro de Usuario con datos válidos
	@Test
	public void Prueba1() {
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
		PO_RegisterView.fillForm(driver, "prueba1@gmail.com", "Josefo", "Perez", "77777", "77777");
		PO_View.checkElement(driver, "text", "Esta es una zona privada la web");
	}

	// Registro de Usuario con datos inválidos
	@Test
	public void Prueba2() {
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
		PO_RegisterView.fillForm(driver, "", "", "", "", "");
		PO_RegisterView.checkKey(driver, "Error.empty.message", PO_Properties.getSPANISH());
	}

	// Registro de Usuario con error en passwordConfirm
	@Test
	public void Prueba3() {
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
		PO_RegisterView.fillForm(driver, "prueba1@gmail.com", "Josefo", "Perez", "77777", "fallo");
		PO_RegisterView.checkKey(driver, "Error.signup.passwordConfirm.coincidence.message",
				PO_Properties.getSPANISH());
	}

	// Registro de Usuario con error coincidencia del emial
	@Test
	public void Prueba4() {
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
		PO_RegisterView.fillForm(driver, "admin@gmail.com", "Josefo", "Perez", "77777", "77777");
		PO_RegisterView.checkKey(driver, "Error.signup.email.duplicate.message", PO_Properties.getSPANISH());
	}

	// Inicio de sesion como admin valido
	@Test
	public void Prueba5() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "admin@gmail.com", "admin");
		PO_View.checkElement(driver, "text", "Esta es una zona privada la web");

	}

	// Inicio de sesion como usuario valido
	@Test
	public void Prueba6() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "pedrodiaz@gmail.com", "123456");
		PO_View.checkElement(driver, "text", "Esta es una zona privada la web");

	}

	// Inicio de sesion como usuario invalido, vacio
	@Test
	public void Prueba7() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "", "");
		PO_View.checkElement(driver, "text", "El usuario o la contraseña no existen");

	}

	// Inicio de sesion como usuario invalido, constraseña incorrecta
	@Test
	public void Prueba8() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "pedrodiaz@gmail.com", "fallo");
		PO_View.checkElement(driver, "text", "El usuario o la contraseña no existen");

	}

	// Inicio de sesion como usuario invalido, email no existente
	@Test
	public void Prueba9() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "fallo", "fallo");
		PO_View.checkElement(driver, "text", "El usuario o la contraseña no existen");

	}
}
