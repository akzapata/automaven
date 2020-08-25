package source.pages;

import net.thucydides.core.annotations.DefaultUrl;

import org.apache.pdfbox.contentstream.operator.state.Concatenate;
import org.joda.time.Months;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

import net.serenitybdd.core.pages.WebElementFacade;
import java.util.stream.Collectors;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.annotations.findby.FindBy;

import net.thucydides.core.pages.PageObject;
import utilities.ExcelReader;

import java.text.SimpleDateFormat;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Set;

@DefaultUrl("http://tapproyecto.eafit.edu.co/mercurio/")
public class TemplatePage extends PageObject {

	ExcelReader excel = new ExcelReader();

	public void enter_login() {
		getDriver().switchTo().frame(0);
		getDriver().findElement(By.id("inputEmail")).sendKeys("lcuarta7");
		getDriver().findElement(By.id("inputPassword")).sendKeys("ccdfdhbh");
		getDriver().findElement(By.xpath("//input[@value='Entrar']")).click();

	}

	public void lookup_terms() {
		//menú carnetización
		getDriver().findElement(By.xpath("//*[@id='bs-example-navbar-collapse-1']/ul/li[4]/a")).click();
		WebDriverWait wait = new WebDriverWait(getDriver(), 6);
		//solicitud masiva
		getDriver().findElement(By.xpath("//*[@id='menu-lateral']/div[2]/a[2]")).click();
		//seleccionar lote
		Select lote = new Select(getDriver().findElement(By.id("formBuscar:loteImpSel")));
		lote.selectByVisibleText("Idiomas");
		//Seleccionar fecha desde
		getDriver().findElement(By.id("formBuscar:j_idt79_input")).click();
		seleccionar_fecha("01/01/2018");
		//Seleccionar fecha hasta
		getDriver().findElement(By.id("formBuscar:j_idt82_input")).click();
		seleccionar_fecha("30/08/2020");
		waitFor(5000);

	}

	public void seleccionar_fecha(String fecha_desde) {
		String MES[] = { "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre",
				"Octubre", "Noviembre", "Diciembre" };
		//Dividir la fecha en un vector de 3 posiciones fecha[0]=dia, fecha[1]=mes, fecha[2]=año
		String fecha[] = fecha_desde.split("/");
		// convertir la fecha en formato YYYY-mm-dd para usarla en la función ChronoUnit que calcula los meses
		fecha_desde = fecha[2] + "-" + fecha[1] + "-" + fecha[0];
		//función que calcula los meses entre la fecha anterior y la fecha actual
		long monthsBetween = ChronoUnit.MONTHS.between(LocalDate.parse(fecha_desde).withDayOfMonth(1),
				LocalDate.now().withDayOfMonth(1));//fecha actual
		int monthnumber = (int) (monthsBetween);
		//ciclo que hace click el numero de meses resultante de la resta de fechas
		for (int i = 0; i < monthnumber; i++) {
			getDriver().findElement(By.xpath("//*[@id='ui-datepicker-div']/div/a[1]/span")).click();
		}
		//calcula el entero del mes en la fecha_desde
		int m = Integer.parseInt(fecha[1]);
		// busca en el vector MES cual es el mes equivalente y lo compara con lo que tiene la página
		// tambien compara el año, si son igual comienza a buscar el día
		if (MES[m - 1].equals(getDriver().findElement(By.xpath("//*[@id='ui-datepicker-div']/div/div/span[1]")).getText())
				&& fecha[2].equals(getDriver().findElement(By.xpath("//*[@id=\'ui-datepicker-div\']/div/div/span[2]"))
						.getText())) {
			select_day_table(fecha[0]);
//			//convertir a entero para eliminar el cero si el día es menor que 10
//			int Dia1 = Integer.parseInt(fecha[0]);
//			//conveertir a string para poder comparar con lo que tiene cada dia del objeto en la página
//			String Dia = Integer.toString(Dia1);
//			//Calcula las filas de la tabla
//			int filas = Serenity.getWebdriverManager().getCurrentDriver().findElements(By.xpath("//*[@class='ui-datepicker-calendar']/tbody/tr")).size();
//			//Calcula las colunmas de la tabla
//			int columnas = Serenity.getWebdriverManager().getCurrentDriver().findElements(By.xpath("//*[@class='ui-datepicker-calendar']/tbody/tr/td")).size() / filas;
//			//ciclo para recorrer el calendario hasta encontrar el día igual a Dia
//			for (int i = 1; i <= filas; i++) {
//				for (int j = 1; j <= columnas; j++) {
//					String diaformat = Serenity.getWebdriverManager().getCurrentDriver().findElement(By.xpath("//*[@class='ui-datepicker-calendar']/tbody/tr[" + i + "]/td[" + j + "]"))
//							.getText();
//					if (diaformat.contentEquals(Dia)) {
//						//Si son iguales los selecciona
//						Serenity.getWebdriverManager().getCurrentDriver().findElement(By.xpath("//*[@class='ui-datepicker-calendar']/tbody/tr[" + i + "]/td[" + j + "]"))
//								.click();
//						break;
//					}
//				}
//			}
		}

	}
	
	public void select_day(String dia) {
		int Dia1 = Integer.parseInt(dia);
		String Dia = Integer.toString(Dia1);
		//Crea un objeto WebElement con toda la tabla donde estan los días	                                                     
		WebElement table = getDriver().findElement(By.xpath("//*[@id=\'ui-datepicker-div\']/table"));
		//crea un arreglo de WebElements que tienen tagname "a" que es donde está el día
		List<WebElement> list_element = table.findElements(By.tagName("a"));
		for (WebElement i:list_element){
			if(i.getText().equals(Dia)) //compara si el texto del tagname "a" es igual a Dia
			{
				i.click();
				break;
			}
		}
				
		waitFor(5000);
	}
	public void select_day_table(String dia) {
		//convertir a entero para eliminar el cero si el día es menor que 10
		int Dia1 = Integer.parseInt(dia);
		//conveertir a string para poder comparar con lo que tiene cada dia del objeto en la página
		String Dia = Integer.toString(Dia1);
		//Calcula las filas de la tabla
		int filas = Serenity.getWebdriverManager().getCurrentDriver().findElements(By.xpath("//*[@class='ui-datepicker-calendar']/tbody/tr")).size();
		//Calcula las colunmas de la tabla
		int columnas = Serenity.getWebdriverManager().getCurrentDriver().findElements(By.xpath("//*[@class='ui-datepicker-calendar']/tbody/tr/td")).size() / filas;
		//ciclo para recorrer el calendario hasta encontrar el día igual a Dia
		for (int i = 1; i <= filas; i++) {
			for (int j = 1; j <= columnas; j++) {
				String diaformat = Serenity.getWebdriverManager().getCurrentDriver().findElement(By.xpath("//*[@class='ui-datepicker-calendar']/tbody/tr[" + i + "]/td[" + j + "]"))
						.getText();
				if (diaformat.contentEquals(Dia)) {
					//Si son iguales los selecciona
					Serenity.getWebdriverManager().getCurrentDriver().findElement(By.xpath("//*[@class='ui-datepicker-calendar']/tbody/tr[" + i + "]/td[" + j + "]"))
							.click();
					break;
				}
			}
		}
	}

}