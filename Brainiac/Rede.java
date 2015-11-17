package Brainiac;

import java.util.ArrayList;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.FileReader;
import java.io.BufferedReader;

public class Rede {
	private int quantidadeCamadas;
	private Camada camadaEntrada;
	private Camada camadaSaida;
	private ArrayList<Camada> camadas;
	private MalhaPesos malhaPesos[];
	private ConjuntoDados conjuntoTreinameto;
	private ConjuntoDados conjuntoValidacao;

	public Rede(){
		/* Construtor vazio */
	}

	public Rede(int quantidadeCamadas,
				int neuronioPorCamada[],
				int funcaoAtivacaoCamada[],
				String conjuntoTreinameto,
				String conjuntoValidacao){
		this.quantidadeCamadas = quantidadeCamadas;
		this.camadaEntrada = new Camada(neuronioPorCamada[0], Camada.ENTRADA, funcaoAtivacaoCamada[0]);
		this.camadaSaida = new Camada(neuronioPorCamada[quantidadeCamadas], Camada.SAIDA, funcaoAtivacaoCamada[quantidadeCamadas]);
		this.camadas = new ArrayList<Camada>();
		this.camadas.add(camadaEntrada);
		Camada camadaOculta;
		for (int i = 1; i < quantidadeCamadas - 1; i++) {
			camadaOculta = new Camada(neuronioPorCamada[i], Camada.OCULTA, funcaoAtivacaoCamada[i]);
			this.camadas.add(camadaOculta);
		}
		this.camadas.add(camadaSaida);
		malhaPesos = new MalhaPesos[quantidadeCamadas - 1];
		for (int i = 0; i < malhaPesos.length; i++) {
			this.malhaPesos[i] = new MalhaPesos(neuronioPorCamada[i + 1] - 1, neuronioPorCamada[i]);
			this.malhaPesos[i].inicializar();
		}
		this.conjuntoTreinameto = new ConjuntoDados();
		this.conjuntoTreinameto.carregarDados(conjuntoTreinameto, neuronioPorCamada[0]);
		this.conjuntoValidacao = new ConjuntoDados();
		this.conjuntoValidacao.carregarDados(conjuntoValidacao, neuronioPorCamada[0]);
	}

	/* Propagação */
	public double[] propagacao(Amostra dados){
		double resultado[] = new double[camadas.get(this.quantidadeCamadas - 1).getTamanhoCamada()];
		for (int i = 0; i < camadas.get(0).getTamanhoCamada();	i++) {
			camadaEntrada.getNeuronio(i).setPotencial(dados.getEntrada(i));
		}

		for (int i = 1; i < this.quantidadeCamadas; i++) {
			for (int j = 1; j < camadas.get(i).getTamanhoCamada(); j++) {
				camadas.get(i).getNeuronio(j).setPotencial(0);
				for (int k = 0; k < camadas.get(i-1).getTamanhoCamada(); k++) {
					camadas.get(i).getNeuronio(j).incrementoPotencial(camadas.get(i-1).getNeuronio(k).ativacao() * malhaPesos[i - 1].getPeso(j, k));
				}
			}
		}

		for (int i = 0; i < camadas.get(this.quantidadeCamadas - 1).getTamanhoCamada(); i++) {
			resultado[i] = camadaSaida.getNeuronio(i).ativacao();
		}

		return resultado;
	}

	public void retroPropagacao(){
		/* Backpropagation */
	}

	public void treinamento(ConjuntoDados conjuntoTreinameto,
	 						ConjuntoDados conjuntoValidacao,
							int epoca,
							double erro){
		/* Treinamento */
	}

	public void salvarRede(){
		/* escrever em arquivo */
		/*
			FileWriter escritor = new FileWriter(fonte);
			PrintWriter saida = new PrintWriter(escritor, true);

			saida.close();
			escritor.close();
		*/
	}

	public void carregarRede(String rede){
		try{
			File fonte = new File(rede);
			if (!fonte.exists() || !fonte.isFile()){
				return;
			}

			FileReader leitor = new FileReader(fonte);
			BufferedReader bufferLeitura = new BufferedReader(leitor);

			String linha = null, dados[];

			linha = bufferLeitura.readLine();
			dados = linha.split("\t");
			this.quantidadeCamadas = Integer.valueOf(dados[0]);

			linha = bufferLeitura.readLine();
			dados = linha.split("\t");
			int neuronioPorCamada[] = new int[quantidadeCamadas];
			for (int i = 0; i < quantidadeCamadas; i++) {
				neuronioPorCamada[i] = Integer.valueOf(dados[i]);
			}

			linha = bufferLeitura.readLine();
			dados = linha.split("\t");
			int funcaoAtivacaoCamada[] = new int[quantidadeCamadas];
			for (int i = 0; i < quantidadeCamadas; i++) {
				funcaoAtivacaoCamada[i] = Integer.valueOf(dados[i]);
			}

			this.camadaEntrada = new Camada(neuronioPorCamada[0], Camada.ENTRADA, funcaoAtivacaoCamada[0]);
			this.camadaSaida = new Camada(neuronioPorCamada[this.quantidadeCamadas - 1], Camada.SAIDA, funcaoAtivacaoCamada[this.quantidadeCamadas - 1]);
			this.camadas = new ArrayList<Camada>();
			this.camadas.add(this.camadaEntrada);
			Camada camadaOculta;
			for (int i = 1; i < quantidadeCamadas - 1; i++) {
				camadaOculta = new Camada(neuronioPorCamada[i], Camada.OCULTA, funcaoAtivacaoCamada[i]);
				this.camadas.add(camadaOculta);
			}
			this.camadas.add(this.camadaSaida);

			malhaPesos = new MalhaPesos[quantidadeCamadas - 1];

			for (int i = 0; i < quantidadeCamadas - 1; i++) {
				this.malhaPesos[i] = new MalhaPesos(neuronioPorCamada[i + 1], neuronioPorCamada[i] + 1);
				linha = bufferLeitura.readLine();
				dados = linha.split("\t");
				double pesos[] = new double[(neuronioPorCamada[i] + 1) * neuronioPorCamada[i+1]];
				for (int j = 0; j < ((neuronioPorCamada[i] + 1) * neuronioPorCamada[i+1]); j++) {
					pesos[j] = Double.valueOf(dados[j]);
				}
				this.malhaPesos[i].setPesos(pesos);
			}

			bufferLeitura.close();
			leitor.close();
		} catch (Exception e){
			System.out.println("Erro no carregamento da rede: " + e.getMessage());
		}
	}

	public void print(){
		System.out.println("Quantidade de camadas: " + quantidadeCamadas);
		for (int i = 0; i < quantidadeCamadas; i++) {
			System.out.println("Camada " + i + ": ");
			this.camadas.get(i).print();
		}
		for (int i = 0; i < quantidadeCamadas - 1; i++){
			System.out.println("Pesos da camada " + i + " para camada " + (i+1));
			this.malhaPesos[i].print();
		}
	}

	/* Gets e Sets */
	public int getQuantidadeCamadas(){
		return this.quantidadeCamadas;
	}

	public void setQuantidadeCamadas(int quantidadeCamadas){
		this.quantidadeCamadas = quantidadeCamadas;
	}

	public Camada getCamadaEntrada(){
		return this.camadaEntrada;
	}

	public void setCamadaEntrada(Camada camadaEntrada){
		this.camadaEntrada = camadaEntrada;
	}

	public Camada getCamadaSaida(){
		return this.camadaSaida;
	}

	public void setCamadaSaida(Camada camadaSaida){
		this.camadaSaida = camadaSaida;
	}

	public ArrayList<Camada> getCamadas(){
		return this.camadas;
	}

	public void setCamadas(ArrayList<Camada> camadas){
		this.camadas = camadas;
	}

	public Camada getCamada(int i){
		return this.camadas.get(i);
	}

	public void setCamada(Camada camada,int i){
		this.camadas.set(i, camada);
	}
}
