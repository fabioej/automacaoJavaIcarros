package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ListaProdutosPage {
	
	private WebDriver driver;
	
	List<WebElement> listaModelos = new ArrayList();
	
	List<WebElement> listaPrecos = new ArrayList();
	
	List<WebElement> listaAnos = new ArrayList();
	
	List<WebElement> listaKms = new ArrayList();
	
	List<WebElement> listaCores = new ArrayList();
	
	List<WebElement> listaCambio = new ArrayList();
	
	private By tituloPesquisa = By.cssSelector("div.sticky_conteudo h1.titulo");
	
	private By modelos = By.cssSelector("ul.listavertical  h2.titulo_anuncio");
	
	private By precos = By.cssSelector("ul.listavertical  h3.preco_anuncio");
	
	private By anos = By.cssSelector("div.dados_anuncio li.primeiro p");
	
	private By kms = By.cssSelector("div.dados_anuncio li.usado p");
	
	private By cores = By.xpath("//p[@itemprop=\"color\"]");
	
	private By cambio = By.cssSelector("div.dados_veiculo ul.listahorizontal li.ultimo p");
	
	public ListaProdutosPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public String obterTituloPesquisa() {
		return driver.findElement(tituloPesquisa).getText();
	}
	
	public int contarAnuncios() {
		carregarListaModelos();
		return listaModelos.size();
	}
	
	private void carregarListaModelos() {
		listaModelos = driver.findElements(modelos);
	}
	
	private void carregarListaPrecos() {
		listaPrecos = driver.findElements(precos);
	}
	
	private void carregarListaAnos() {
		listaAnos = driver.findElements(anos);
	}
	
	private void carregarListaKms() {
		listaKms = driver.findElements(kms);
	}
	
	private void carregarListaCores() {
		listaCores = driver.findElements(cores);
	}
	
	private void carregarListaCambio() {
		listaCambio = driver.findElements(cambio);
	}

	public String obterModelo(int posicao) {
		carregarListaModelos();
		return listaModelos.get(posicao).getText();
	}
	
	public Integer obterPreco(int posicao) {
		carregarListaPrecos();
		return Integer.parseInt(listaPrecos.get(posicao).getText()
				.replace("\npreço à vista","").replace("R$ ", "").replace(",00", "").replace(".", ""));
	}
	
	public String obterStringPreco(int posicao) {
		carregarListaPrecos();
		return listaPrecos.get(posicao).getText().replace("\npreço à vista","");
	}
	
	public String obterAno(int posicao) {
		carregarListaAnos();
		return listaAnos.get(posicao).getText();
	}
	
	public String obterKm(int posicao) {
		carregarListaKms();
		return listaKms.get(posicao).getText();
	}
	
	public String obterCor(int posicao) {
		carregarListaCores();
		return listaCores.get(posicao).getText();
	}
	
	public String obterCambio(int posicao) {
		carregarListaCambio();
		return listaCambio.get(posicao).getText();
	}
}
