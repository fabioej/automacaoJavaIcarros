package steps;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.google.common.io.Files;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import pages.HomePage;
import pages.ListaProdutosPage;


public class ListarAnunciosSteps {
	
	private static WebDriver driver;
	private HomePage homePage = new HomePage(driver);
	
	@Before
	public static void inicializar() {
		System.setProperty("webdriver.chrome.driver", "C:\\Tools\\selenium\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	@Dado("que estou na pagina inicial")
	public void que_estou_na_pagina_inicial() {
		homePage.carregarPaginaInicial();
		assertThat(homePage.obterTituloPagina(), is("Carros novos, usados e seminovos para comprar e vender - iCarros"));
	}

	//resultado que vai ser exibido no titulo da pesquisa
	String resultadoTituloPesquisaEsperada; 
	
	@Quando("incluo a {string} e {string} do veiculo desejado")
	public void incluo_a_e_do_veiculo_desejado(String marca, String modelo) {
	    assertThat(homePage.listarMarca(marca), is(marca));
	    homePage.selecionarMarca();
	    
	    assertThat(homePage.listarModelo(modelo), is(modelo));
	    homePage.selecionarModelo();
	    resultadoTituloPesquisaEsperada = marca + " " + modelo;
	}

	@Quando("incluo o ano entre {string} e {string} desejado")
	public void incluo_o_ano_entre_e_desejado(String anoMin, String anoMax) {
	   assertThat(homePage.listarAnoMin(anoMin), is(anoMin));
	   homePage.selecionarAnoMin();
	   
	   assertThat(homePage.listarAnoMax(anoMax), is(anoMax));
	   homePage.selecionarAnoMax();
	}

	@Quando("insiro os precos {string} e {string} do veiculo")
	public void insiro_os_precos_e_do_veiculo(String precoMin, String precoMax) {
	    assertThat(homePage.listarValorMinimo(precoMin), is(precoMin));
	    homePage.selecionarValorMinimo();
	    
	    assertThat(homePage.listarValorMaximo(precoMax), is(precoMax));
	    homePage.selecionarValorMaximo();
	}

	@Quando("forneco o meu {string}")
	public void forneco_o_meu(String cep) {
	    homePage.incluirCep(cep.replace("-", ""));
	    resultadoTituloPesquisaEsperada = resultadoTituloPesquisaEsperada + " em até 50km do cep " + cep;
	}

	ListaProdutosPage listaProdutosPage;
	@Quando("efetuo a busca")
	public void efetuo_a_busca() {
	    listaProdutosPage = homePage.clicarBuscar();
	    assertTrue(listaProdutosPage.obterTituloPesquisa().startsWith(resultadoTituloPesquisaEsperada));
	}

	@Entao("devo retornar uma lista de no minimo {int} de veiculos")
	public void devo_retornar_uma_lista_de_no_minimo_de_veiculos(Integer quant) {
	   assertTrue(listaProdutosPage.contarAnuncios() >= quant);
	}

	@Entao("o preco do primeiro e segundo veiculos listados deve estar entre {string} e {string}")
	public void o_preco_do_primeiro_e_segundo_veiculos_listados_deve_estar_entre_e(String valorMinimo, String valorMaximo) {
		
		int intValorMinimo = Integer.parseInt(valorMinimo) * 1000;
		int intValorMaximo = Integer.parseInt(valorMaximo) * 1000;
		
		for(int i = 0;i < 2; i++) {
			//verifica se valor esta compativel com limites fornecidos na busca
			assertTrue( listaProdutosPage.obterPreco(i) >= intValorMinimo 
					&&
					listaProdutosPage.obterPreco(i) <= intValorMaximo );
		}
	}

	@Entao("o modelo dos dois primeiro da lista devem ser {string}")
	public void o_modelo_dos_dois_primeiro_da_lista_devem_ser(String modelo) {
		
		for(int i = 0;i < 2; i++) {
			//verifica modelo veiculo retornado
			assertTrue(listaProdutosPage.obterModelo(i).contains(modelo));
		}
	}
	
	@After(order=1)
	public void capturarTela(Scenario scenario) {
		TakesScreenshot camera = (TakesScreenshot) driver;
		File capturaDeTela = camera.getScreenshotAs(OutputType.FILE);
		
		String scenarioId = scenario.getId().substring(scenario.getId().lastIndexOf(".feature") + 9);
		
		try {
			Files.move(capturaDeTela, new File("resources/screenshots/" + scenario.getName() + "_" + scenarioId +  "_" + scenario.getStatus() + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@After(order=0)
	public static void finalizar() {
		driver.quit();
	}

}
