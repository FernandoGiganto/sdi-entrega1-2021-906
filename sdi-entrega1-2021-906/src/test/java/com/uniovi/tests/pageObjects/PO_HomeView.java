package com.uniovi.tests.pageObjects;

import org.openqa.selenium.WebDriver;

import com.uniovi.tests.util.SeleniumUtils;

public class PO_HomeView extends PO_NavView {

	static public void checkWelcome(WebDriver driver, int language) {
		// Esperamos a que se cargue el saludo de bienvenida en Español
		SeleniumUtils.EsperaCargaPagina(driver, "text", p.getString("welcome.message", language), getTimeout());
	}

	static public void check(WebDriver driver, int language, String message) {
		// Esperamos a que se cargue el saludo de bienvenida en Español
		SeleniumUtils.EsperaCargaPagina(driver, "text", p.getString(message, language), getTimeout());
	}

	static public void checkChangeIdiom(WebDriver driver, String textIdiom1, String textIdiom2, int locale1,
			int locale2) {

		// Esperamos a que se cargue el saludo de bienvenida en Español
		PO_HomeView.checkWelcome(driver, locale1);

		// Cambiamos a segundo idioma
		PO_HomeView.changeIdiom(driver, textIdiom2);

		// COmprobamos que el texto de bienvenida haya cambiado a segundo idioma
		PO_HomeView.checkWelcome(driver, locale2);

		// Volvemos a Español.
		PO_HomeView.changeIdiom(driver, textIdiom1);

		// Esperamos a que se cargue el saludo de bienvenida en Español
		PO_HomeView.checkWelcome(driver, locale1);
	}

	static public void checkChangeIdiomAddOffer(WebDriver driver, String textIdiom1, String textIdiom2, int locale1,
			int locale2) {

		// Esperamos a que se cargue el saludo de bienvenida en Español
		PO_HomeView.check(driver, locale1, "offer.list.titulo.message");
		PO_NavView.checkKey(driver, "nav.user.gestionOfertas.message", locale1);

		// Cambiamos a segundo idioma
		PO_HomeView.changeIdiom(driver, textIdiom2);

		// COmprobamos que el texto de bienvenida haya cambiado a segundo idioma
		PO_HomeView.check(driver, locale2, "offer.list.titulo.message");
		PO_NavView.checkKey(driver, "nav.user.gestionOfertas.message", locale2);

		// Volvemos a Español.
		PO_HomeView.changeIdiom(driver, textIdiom1);

		// Esperamos a que se cargue el saludo de bienvenida en Español
		PO_HomeView.check(driver, locale1, "offer.list.titulo.message");
		PO_NavView.checkKey(driver, "nav.user.gestionOfertas.message", locale1);
	}

	static public void checkChangeIdiomUserList(WebDriver driver, String textIdiom1, String textIdiom2, int locale1,
			int locale2) {

		// Esperamos a que se cargue el saludo de bienvenida en Español
		PO_HomeView.check(driver, locale1, "user.list.name.message");

		// Cambiamos a segundo idioma
		PO_HomeView.changeIdiom(driver, textIdiom2);

		// COmprobamos que el texto de bienvenida haya cambiado a segundo idioma
		PO_HomeView.check(driver, locale2, "user.list.name.message");

		// Volvemos a Español.
		PO_HomeView.changeIdiom(driver, textIdiom1);

		// Esperamos a que se cargue el saludo de bienvenida en Español
		PO_HomeView.check(driver, locale1, "user.list.name.message");

	}
}
