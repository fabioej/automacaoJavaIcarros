package homepage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.opencsv.CSVWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import base.BaseTests;
import pages.ListaProdutosPage;

public class HomePageTests extends BaseTests {

	//Dados veiculo
	String marcaVeiculo = "Honda";
	String modeloVeiculo = "City";
	String anoMin = "2010";
	String anoMax = "2013";
	String cep = "13073-010";
	String valorMinimo = "30";
	String valorMaximo = "35";
	
	ListaProdutosPage listaProdutoPage;
	
	@Test
	public void testProcuraPorCarrosUsados_retornaListaDeCarrosUsados() {
		carregarPaginaInicial();
		
		String marcaSelecionada;
		marcaSelecionada = homePage.listarMarca(marcaVeiculo);
		//verificar se a marca selecionada esta contida na lista de marca de veiculos disponiveis no site
		assertThat(marcaSelecionada, is(marcaVeiculo));
		//entao seleciona a marca
		homePage.selecionarMarca();
		
		String modeloSelecionado;
		modeloSelecionado = homePage.listarModelo(modeloVeiculo);
		//verificar se a modelo selecionado esta contido na lista de modelos da marca disponiveis no site
		assertThat(modeloSelecionado, is(modeloVeiculo));
		//entao seleciona o modelo
		homePage.selecionarModelo();
		
		//Inserir ano minimo e maximo do veiculo
		String anoMinSelecionado;
		anoMinSelecionado = homePage.listarAnoMin(anoMin);
		assertThat(anoMinSelecionado, is(anoMin));
		homePage.selecionarAnoMin();
		
		String anoMaxSelecionado;
		anoMaxSelecionado = homePage.listarAnoMax(anoMax);
		assertThat(anoMaxSelecionado, is(anoMax));
		homePage.selecionarAnoMax();
		
		//Inserir valores minimo e maximo do veiculo
		assertThat(homePage.listarValorMinimo(valorMinimo), is(valorMinimo));
		homePage.selecionarValorMinimo();
		
		assertThat(homePage.listarValorMaximo(valorMaximo), is(valorMaximo));
		homePage.selecionarValorMaximo();
		
		//incluir cep
		homePage.incluirCep(cep.replace("-", ""));
		
		//buscar por veiculos
		listaProdutoPage = homePage.clicarBuscar();
		
		//verificar e a busca foi de fato feita corretamente de acordo com os dados do veiculo e cep
		String pesquisaEsperada = marcaVeiculo + " " + modeloVeiculo + " em até 50km do cep " + cep;
		assertTrue(listaProdutoPage.obterTituloPesquisa().startsWith(pesquisaEsperada));
		
		//verificar se os anuncios retornados na busca sao no minimo 3
		assertTrue(listaProdutoPage.contarAnuncios() > 2);
		
	}
	
	
	
	int intValorMinimo = Integer.parseInt(valorMinimo) * 1000;
	int intValorMaximo = Integer.parseInt(valorMaximo) * 1000;
	
	@Test
	public void testValidaModeloeValorPrimeiroESegundoItem_valoresValidadosComSucesso() {
		testProcuraPorCarrosUsados_retornaListaDeCarrosUsados();
		
		for(int i = 0;i < 2; i++) {
			//verifica modelo veiculo
			assertTrue(listaProdutoPage.obterModelo(i).contains(modeloVeiculo));
			//verifica se valor esta compativel com limites fornecidos na busca
			assertTrue( listaProdutoPage.obterPreco(i) >= intValorMinimo 
					&&
					listaProdutoPage.obterPreco(i) <= intValorMaximo );
		}
		
	}
	
	@Test
	public void testlerListaResultados_criarArquivodeDados() throws IOException {
		testValidaModeloeValorPrimeiroESegundoItem_valoresValidadosComSucesso();
		
		String[] cabecalho = {"indice","MarcaModelo", "Ano", "KM","Cor","cambio","Valor"};
		List<String[]> linhas = new ArrayList<>();
		
		//iteracoes com os numero de itens exibidos na tela
		int iteracoes = listaProdutoPage.contarAnuncios();
		
		for(int i = 0; i < iteracoes; i++) {
			linhas.add(new String[] {
					Integer.toString(i),
					listaProdutoPage.obterModelo(i),
					listaProdutoPage.obterAno(i),
					listaProdutoPage.obterKm(i),
					listaProdutoPage.obterCor(i),
					listaProdutoPage.obterCambio(i),
					listaProdutoPage.obterStringPreco(i)
			}
			);
		}
		
		Writer writer = Files.newBufferedWriter(Paths.get("src\\test\\resources\\massaCarros.csv"), StandardCharsets.UTF_8);
        CSVWriter csvWriter = new CSVWriter(writer);
        
        csvWriter.writeNext(cabecalho);
        csvWriter.writeAll(linhas);
        
        csvWriter.flush();
        writer.close();
	}
	

	@ParameterizedTest
	@CsvFileSource(resources = "/massaCarros.csv", numLinesToSkip = 1, delimiter = ',')
	public void testBuscarVeiculos_validarSeDadosContinuamOsMesmos(String i,String modelo, String ano, String km, String cor, String cambio, String valor) {
		testProcuraPorCarrosUsados_retornaListaDeCarrosUsados();
		
		// validar se os dados estao corretos de acordo com a massa de tests
		// Marca/Modelo
		assertThat(listaProdutoPage.obterModelo(Integer.parseInt(i)), is(modelo));
		// Ano
		assertThat(listaProdutoPage.obterAno(Integer.parseInt(i)), is(ano));
		// KM rodados
		assertThat(listaProdutoPage.obterKm(Integer.parseInt(i)), is(km));
		// Cor
		assertThat(listaProdutoPage.obterCor(Integer.parseInt(i)), is(cor));
		// Cambio
		assertThat(listaProdutoPage.obterCambio(Integer.parseInt(i)), is(cambio));
		// Valor Veiculo
		assertThat(listaProdutoPage.obterStringPreco(Integer.parseInt(i)), is(valor));	
		
		
	}

}
