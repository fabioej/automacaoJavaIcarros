package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class HomePage {
	
	private WebDriver driver;
	
	private By botaoMarca = By.xpath("//button[@title='Marca']");
	
	private By inputMarca = By.cssSelector(".col-xs-6:nth-child(1) input.form-control");
	
	private By marcaListada = By.cssSelector("li.active a.opt");
	
	//private By botaoModelo = By.xpath("//button[@title='Modelo']");
	
	private By inputModelo = By.cssSelector(".col-xs-6:nth-child(2) input.form-control");
	
	private By modeloListado = By.cssSelector("div.col-xs-6:nth-child(2) li.active span.text");
	
	private By botaoAnoMin = By.cssSelector(".col-xs-3:nth-child(1) button");
	
	private By inputAnoMin = By.cssSelector(".col-xs-3:nth-child(1) input.form-control");
	
	private By anoMinListado = By.cssSelector(".col-xs-3:nth-child(1) li.active a span.text");
	
	private By botaoAnoMax = By.cssSelector(".col-xs-3:nth-child(2) button");
	
	private By inputAnoMax = By.cssSelector(".col-xs-3:nth-child(2) input.form-control");
	
	private By anoMaxListado = By.cssSelector(".col-xs-3:nth-child(2) li.active a span.text");
	
	private By botaoValorMinimo = By.cssSelector(".col-xs-3:nth-child(3) button");
	
	private By inputValorMinimo = By.cssSelector(".col-xs-3:nth-child(3) input.form-control");
	
	private By valorMinimo = By.cssSelector(".col-xs-3:nth-child(3) li.active a span.text");
	
	private By botaoValorMaximo = By.cssSelector(".col-xs-3:nth-child(4) button");
	
	private By inputValorMaximo = By.cssSelector(".col-xs-3:nth-child(4) input.form-control");
	
	private By valorMaximo = By.cssSelector(".col-xs-3:nth-child(4) li.active a span.text");
	
	private By alterarCep = By.cssSelector("div.box-location");
	
	private By Cep = By.id("cidade");
	
	private By sugestaoCep = By.cssSelector("div.tt-dataset-cities div.tt-suggestion");
	
	private By botaoBuscar = By.cssSelector("div.col-xs-4 button.button--large");
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
	}

	// ----------- SELECIONAR MARCA DO VEICULO ----------
	public String listarMarca(String marcaVeiculo) {
		driver.findElement(botaoMarca).click();
		driver.findElement(inputMarca).sendKeys(marcaVeiculo);
		return driver.findElement(marcaListada).getText();
	}
	
	public void selecionarMarca() {
		driver.findElement(marcaListada).click();
	}
	
	// ---------- SELECIONAR MODELO DO VEICULO ----------
	public String listarModelo(String modeloVeiculo) {
		driver.findElement(inputModelo).sendKeys(modeloVeiculo);
		return driver.findElement(modeloListado).getText();
	}
	
	public void selecionarModelo() {
		driver.findElement(modeloListado).click();
	}
	
	// ---------- ANO MINIMO DO VEICULO ----------
	public String listarAnoMin(String anoMinVeiculo) {
		driver.findElement(botaoAnoMin).click();
		driver.findElement(inputAnoMin).sendKeys(anoMinVeiculo);
		return driver.findElement(anoMinListado).getText().replace("De ", ""); 
		// retorna ex: De 2010 , removendo "De " para comparação posterior
	}
	
	public void selecionarAnoMin() {
		driver.findElement(anoMinListado).click();
	}
		
	// --------- ANO MAXIMO DO VEICULO ---------
	public String listarAnoMax(String anoMaxVeiculo) {
		driver.findElement(botaoAnoMax).click();
		driver.findElement(inputAnoMax).sendKeys(anoMaxVeiculo);
		return driver.findElement(anoMaxListado).getText().replace("Até ", ""); 
		// retorna ex: Até 2010 , removendo "Até " para comparação posterior
	}
	
	public void selecionarAnoMax() {
		driver.findElement(anoMaxListado).click();
	}
	
	// ---------- VALOR MINIMO ----------
	public String listarValorMinimo(String valorMinimoVeiculo) {
		driver.findElement(botaoValorMinimo).click();
		driver.findElement(inputValorMinimo).sendKeys(valorMinimoVeiculo);
		return driver.findElement(valorMinimo).getText().replace("De R$ ", "").replace(" mil", ""); 
		// retorna ex: Até 2010 , removendo "Até " para comparação posterior
	}
	
	public void selecionarValorMinimo() {
		driver.findElement(valorMinimo).click();
	}
	
	// ----------  VALOR MAXIMO ---------
	public String listarValorMaximo(String valorMaximoVeiculo) {
		driver.findElement(botaoValorMaximo).click();
		driver.findElement(inputValorMaximo).sendKeys(valorMaximoVeiculo);
		return driver.findElement(valorMaximo).getText().replace("Até R$ ", "").replace(" mil", "");
		// retorna ex: Até 2010 , removendo "Até " para comparação posterior
	}
	
	public void selecionarValorMaximo() {
		driver.findElement(valorMaximo).click();
	}
	
	// ----------  CEP  ----------
	public void incluirCep(String cepDesejado) {
		driver.findElement(alterarCep).click();
		driver.findElement(Cep).sendKeys(Keys.chord(cepDesejado));
		driver.findElement(sugestaoCep).click();
	}
	
	// ---------- BOTAO BUSCAR ---------
	public ListaProdutosPage clicarBuscar() {
		driver.findElement(botaoBuscar).click();
		return new ListaProdutosPage(driver);
	}

	public void carregarPaginaInicial() {
		driver.get("https://www.icarros.com.br/principal/index.jsp");
	}

	public String obterTituloPagina() {
		return driver.getTitle();
	}
}
