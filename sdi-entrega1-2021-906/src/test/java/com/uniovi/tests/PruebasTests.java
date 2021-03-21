package com.uniovi.tests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.uniovi.tests.pageObjects.PO_AddOfferView;
import com.uniovi.tests.pageObjects.PO_HomeView;
import com.uniovi.tests.pageObjects.PO_LoginView;
import com.uniovi.tests.pageObjects.PO_Properties;
import com.uniovi.tests.pageObjects.PO_RegisterView;
import com.uniovi.tests.pageObjects.PO_UserList;
import com.uniovi.tests.pageObjects.PO_View;
import com.uniovi.tests.util.SeleniumUtils;

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
	public void Prueba01() {
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
		PO_RegisterView.fillForm(driver, "prueba1@gmail.com", "Josefo", "Perez", "77777", "77777");
		PO_View.checkElement(driver, "text", "Esta es una zona privada la web");
	}

	// Registro de Usuario con datos inválidos
	@Test
	public void Prueba02() {
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
		PO_RegisterView.fillForm(driver, "", "", "", "", "");
		PO_RegisterView.checkKey(driver, "Error.empty.message", PO_Properties.getSPANISH());
	}

	// Registro de Usuario con error en passwordConfirm
	@Test
	public void Prueba03() {
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
		PO_RegisterView.fillForm(driver, "prueba1@gmail.com", "Josefo", "Perez", "77777", "fallo");
		PO_RegisterView.checkKey(driver, "Error.signup.passwordConfirm.coincidence.message",
				PO_Properties.getSPANISH());
	}

	// Registro de Usuario con error coincidencia del emial
	@Test
	public void Prueba04() {
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
		PO_RegisterView.fillForm(driver, "admin@gmail.com", "Josefo", "Perez", "77777", "77777");
		PO_RegisterView.checkKey(driver, "Error.signup.email.duplicate.message", PO_Properties.getSPANISH());
	}

	// Inicio de sesion como admin valido
	@Test
	public void Prueba05() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "admin@gmail.com", "admin");
		PO_View.checkElement(driver, "text", "Esta es una zona privada la web");

	}

	// Inicio de sesion como usuario valido
	@Test
	public void Prueba06() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "pedrodiaz@gmail.com", "123456");
		PO_View.checkElement(driver, "text", "Esta es una zona privada la web");

	}

	// Inicio de sesion como usuario invalido, vacio
	@Test
	public void Prueba07() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "", "");
		PO_View.checkElement(driver, "text", "El usuario o la contraseña no existen");

	}

	// Inicio de sesion como usuario invalido, constraseña incorrecta
	@Test
	public void Prueba08() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "pedrodiaz@gmail.com", "fallo");
		PO_View.checkElement(driver, "text", "El usuario o la contraseña no existen");

	}

	// Inicio de sesion como usuario invalido, email no existente
	@Test
	public void Prueba09() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "fallo", "fallo");
		PO_View.checkElement(driver, "text", "El usuario o la contraseña no existen");

	}

	// Hacer logout y redirige a la pagina de login
	@Test
	public void Prueba10() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "pedrodiaz@gmail.com", "123456");
		PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");
		PO_View.checkElement(driver, "text", "Identifícate");
	}

	// Comprobar que el btn de logout no aparece si no estas autentificado
	@Test
	public void Prueba11() {
		SeleniumUtils.EsperaCargaPaginaNoTexto(driver, "//li[contains(@id, 'logout')]/a", PO_View.getTimeout());
	}

	// Mostrar el listado de usarios y comprobar que se muestran todos
	@Test
	public void Prueba12() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "admin@gmail.com", "admin");
		PO_HomeView.clickOption(driver, "user/list", "class", "btn btn-primary");
		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr",
				PO_View.getTimeout());
		assertTrue(elementos.size() == 8);
	}

	// Borrar el primer usuario de la lista de usuarios
	@Test
	public void Prueba13() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "admin@gmail.com", "admin");
		PO_HomeView.clickOption(driver, "user/list", "class", "btn btn-primary");
		List<WebElement> elementosCheckBox=PO_View.checkElement(driver, "class", "checkbox");
		int pre= elementosCheckBox.size();
		elementosCheckBox.get(0).click();
		PO_View.checkElement(driver, "id", "btDelete").get(0).click();
		elementosCheckBox=PO_View.checkElement(driver, "class", "checkbox");
		int post= elementosCheckBox.size();
		assertFalse(pre==post);
	}

	// Borrar el ultimo usuario de la lista de usuarios
	@Test
	public void Prueba14() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "admin@gmail.com", "admin");
		PO_HomeView.clickOption(driver, "user/list", "class", "btn btn-primary");
		List<WebElement> elementosCheckBox=PO_View.checkElement(driver, "class", "checkbox");
		int pre= elementosCheckBox.size();
		elementosCheckBox.get(pre-1).click();
		PO_View.checkElement(driver, "id", "btDelete").get(0).click();
		elementosCheckBox=PO_View.checkElement(driver, "class", "checkbox");
		int post= elementosCheckBox.size();
		assertFalse(pre==post);

	}

	// Borrar 3 usuarios de la lista de usuarios
	@Test
	public void Prueba15() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "admin@gmail.com", "admin");
		PO_HomeView.clickOption(driver, "user/list", "class", "btn btn-primary");
		List<WebElement> elementosCheckBox=PO_View.checkElement(driver, "class", "checkbox");
		int pre= elementosCheckBox.size();
		elementosCheckBox.get(0).click();
		elementosCheckBox.get(1).click();
		elementosCheckBox.get(2).click();
		PO_View.checkElement(driver, "id", "btDelete").get(0).click();
		elementosCheckBox=PO_View.checkElement(driver, "class", "checkbox");
		int post= elementosCheckBox.size();
		assertTrue(pre-3==post);

	}

	// Dar de la alta nueva oferta con datos validos
	@Test
	public void Prueba16() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "pedrodiaz@gmail.com", "123456");
		PO_AddOfferView.navigate(driver, "//li[contains(@id, 'offers-menu')]/a", 0);
		PO_AddOfferView.navigate(driver, "//a[contains(@href, 'offer/add')]", 0);
		PO_AddOfferView.fillForm(driver, "tituloTest", "DescripcionTituloTest", "1000.0");
		PO_View.checkElement(driver, "text", "tituloTest");
	}

	// Dar de la alta nueva oferta con datos invalidos -> titulo vacio
	@Test
	public void Prueba17() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "pedrodiaz@gmail.com", "123456");
		PO_AddOfferView.navigate(driver, "//li[contains(@id, 'offers-menu')]/a", 0);
		PO_AddOfferView.navigate(driver, "//a[contains(@href, 'offer/add')]", 0);
		PO_AddOfferView.fillForm(driver, "", "DescripcionTituloTest", "1000.0");
		PO_AddOfferView.checkKey(driver, "Error.empty.message", PO_Properties.getSPANISH());
		SeleniumUtils.EsperaCargaPaginaNoTexto(driver, "Mis Ofertas", PO_View.getTimeout());

	}

	// Mostrar listado de ofertas propias y comprobar que estan todas
	@Test
	public void Prueba18() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "pedrodiaz@gmail.com", "123456");
		PO_AddOfferView.navigate(driver, "//li[contains(@id, 'offers-menu')]/a", 0);
		PO_AddOfferView.navigate(driver, "//a[contains(@href, 'offer/list')]", 0);
		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr",
				PO_View.getTimeout());
		assertTrue(elementos.size() == 5);
	}

	// Borrar primera oferta
	@Test
	public void Prueba19() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "pedrodiaz@gmail.com", "123456");
		PO_AddOfferView.navigate(driver, "//li[contains(@id, 'offers-menu')]/a", 0);
		PO_AddOfferView.navigate(driver, "//a[contains(@href, 'offer/list')]", 0);
		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr",
				PO_View.getTimeout());
		assertTrue(elementos.size() == 5);
		PO_AddOfferView.navigate(driver,
				"//td[contains(text(), 'Oferta A1')]/following-sibling::*/a[contains(@href, 'offer/delete')]", 0);
		elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr", PO_View.getTimeout());
		assertTrue(elementos.size() == 4);
		SeleniumUtils.EsperaCargaPaginaNoTexto(driver, "Oferta A1", PO_View.getTimeout());
	}

	// Borrar ultima oferta
	@Test
	public void Prueba20() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "pedrodiaz@gmail.com", "123456");
		PO_AddOfferView.navigate(driver, "//li[contains(@id, 'offers-menu')]/a", 0);
		PO_AddOfferView.navigate(driver, "//a[contains(@href, 'offer/list')]", 0);
		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr",
				PO_View.getTimeout());
		assertTrue(elementos.size() == 4);
		PO_AddOfferView.navigate(driver,
				"//td[contains(text(), 'Oferta A3')]/following-sibling::*/a[contains(@href, 'offer/delete')]", 0);
		elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr", PO_View.getTimeout());
		assertTrue(elementos.size() == 3);
		SeleniumUtils.EsperaCargaPaginaNoTexto(driver, "Oferta A3", PO_View.getTimeout());
	}

	// Hacer una busqueda con el campo vacio y se muestran todas las ofertas
	@Test
	public void Prueba21() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "pedrodiaz@gmail.com", "123456");
		PO_UserList.fillForm(driver, "");
		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr",
				PO_View.getTimeout());
		System.out.println(elementos.size());
		PO_AddOfferView.navigate(driver, "//a[contains(@class, 'page-link')]", 1);
	}

	// Hacer una busqueda con el campo no existente y no se muestran ofertas
	@Test
	public void Prueba22() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "pedrodiaz@gmail.com", "123456");
		PO_UserList.fillForm(driver, "fallando");
		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody", PO_View.getTimeout());
		assertTrue(elementos.size() == 1);
		PO_AddOfferView.navigate(driver, "//a[contains(@class, 'page-link')]", 1);
	}

	// Comprar una oferta que deje un saldo positivo.Comprobar saldo
	@Test
	public void Prueba23() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "pedrodiaz@gmail.com", "123456");
		PO_AddOfferView.navigate(driver,
				"//td[contains(text(), 'Oferta F2')]/following-sibling::*/a[contains(@href, 'offer/buy')]", 0);
		PO_View.checkElement(driver, "text", "Vendido");
		PO_AddOfferView.navigate(driver, "//li[contains(@id, 'usuario-menu')]/a", 0);
		PO_View.checkElement(driver, "text", "50.0");

	}

	// Comprar una oferta que deje un saldo 0.Comprobar saldo
	@Test
	public void Prueba24() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "pedrodiaz@gmail.com", "123456");
		PO_AddOfferView.navigate(driver,
				"//td[contains(text(), 'Oferta F3')]/following-sibling::*/a[contains(@href, 'offer/buy')]", 0);
		PO_View.checkElement(driver, "text", "Vendido");
		PO_AddOfferView.navigate(driver, "//li[contains(@id, 'usuario-menu')]/a", 0);
		PO_View.checkElement(driver, "text", "0.0");

	}

	// Comprar una oferta de precio superior al saldo
	@Test
	public void Prueba25() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "pedrodiaz@gmail.com", "123456");
		PO_View.checkElement(driver, "text", "No dinero suficiente");

	}

	// Ir a ofertas compradas y que aparezcan
	@Test
	public void Prueba26() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "pedrodiaz@gmail.com", "123456");
		PO_AddOfferView.navigate(driver, "//li[contains(@id, 'offers-menu')]/a", 0);
		PO_AddOfferView.navigate(driver, "//a[contains(@href, 'offer/boughtList')]", 0);
		PO_View.checkElement(driver, "text", "Oferta F2");
		PO_View.checkElement(driver, "text", "Oferta F3");
	}

	// Visualizar 4 paginas haciendo cambio español/ingles
	@Test
	public void Prueba27() {
		PO_HomeView.checkChangeIdiom(driver, "btnSpanish", "btnEnglish", PO_Properties.getSPANISH(),
				PO_Properties.getENGLISH());
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "pedrodiaz@gmail.com", "123456");
		PO_AddOfferView.navigate(driver, "//li[contains(@id, 'offers-menu')]/a", 0);
		PO_AddOfferView.navigate(driver, "//a[contains(@href, 'offer/add')]", 0);
		PO_HomeView.checkChangeIdiomAddOffer(driver, "btnSpanish", "btnEnglish", PO_Properties.getSPANISH(),
				PO_Properties.getENGLISH());
		PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");

		PO_LoginView.fillForm(driver, "admin@gmail.com", "admin");
		PO_AddOfferView.navigate(driver, "//li[contains(@id, 'user-menu')]/a", 0);
		PO_AddOfferView.navigate(driver, "//a[contains(@href, 'user/list')]", 0);
		PO_HomeView.checkChangeIdiomUserList(driver, "btnSpanish", "btnEnglish", PO_Properties.getSPANISH(),
				PO_Properties.getENGLISH());
	}

	// Intentar acceder al listado de usuarios sin estar autentificado
	@Test
	public void Prueba28() {
		driver.navigate().to(URL + "/user/list");
		PO_View.checkElement(driver, "text", "Identifícate");
	}

	// Intentar acceder al listado de ofertas sin estar autentificado
	@Test
	public void Prueba29() {
		driver.navigate().to(URL + "/offer/list");
		PO_View.checkElement(driver, "text", "Identifícate");
	}

	// Intentar acceder al listado de usuarios no siendo admin
	@Test
	public void Prueba30() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "pedrodiaz@gmail.com", "123456");
		PO_View.checkElement(driver, "free", "//li[not(contains(@id, 'user-menu'))]/a");
	}

}
