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
	private ConjuntoDados conjuntoTreinamento;
	private ConjuntoDados conjuntoValidacao;
	private String treino;

	public Rede(){
		/* Construtor vazio */
	}

	public Rede(int quantidadeCamadas,
				int neuronioPorCamada[],
				int funcaoAtivacaoCamada[],
				String conjuntoTreinamento,
				String conjuntoValidacao,
				String treino){
		this.quantidadeCamadas = quantidadeCamadas;
		this.camadaEntrada = new Camada(neuronioPorCamada[0] + 1, Camada.ENTRADA, funcaoAtivacaoCamada[0]);
		this.camadaSaida = new Camada(neuronioPorCamada[this.quantidadeCamadas - 1], Camada.SAIDA, funcaoAtivacaoCamada[this.quantidadeCamadas - 1]);
		this.camadas = new ArrayList<Camada>();
		this.camadas.add(camadaEntrada);
		Camada camadaOculta;
		for (int i = 1; i < quantidadeCamadas - 1; i++) {
			camadaOculta = new Camada(neuronioPorCamada[i] + 1, Camada.OCULTA, funcaoAtivacaoCamada[i]);
			this.camadas.add(camadaOculta);
		}
		this.camadas.add(camadaSaida);
		malhaPesos = new MalhaPesos[quantidadeCamadas - 1];
		for (int i = 0; i < malhaPesos.length; i++) {
			this.malhaPesos[i] = new MalhaPesos(neuronioPorCamada[i + 1], neuronioPorCamada[i] + 1);
			this.malhaPesos[i].inicializar();
		}
		this.conjuntoTreinamento = new ConjuntoDados();
		this.conjuntoTreinamento.carregarDados(conjuntoTreinamento, camadaEntrada.getTamanhoCamada() + camadaSaida.getTamanhoCamada());
		this.conjuntoValidacao = new ConjuntoDados();
		this.conjuntoValidacao.carregarDados(conjuntoValidacao, camadaEntrada.getTamanhoCamada() + camadaSaida.getTamanhoCamada());
		this.treino = treino;
	}

	/* Propagação */
	public double [] propagacao(Amostra dados){
		double resultado[] = new double[camadas.get(this.quantidadeCamadas - 1).getTamanhoCamada()];

		for (int i = 0; i < camadaEntrada.getTamanhoCamada(); i++) {
			camadaEntrada.getNeuronio(i).setPotencial(dados.getEntrada(i));
		}

		for (int i = 0; i < this.quantidadeCamadas - 1; i++) { // laço para camadas
			for (int j = 1; j < camadas.get(i + 1).getTamanhoCamada(); j++) { // laço para neuronios da camada i + 1
				camadas.get(i + 1).getNeuronio(j).setPotencial(0);
				for (int k = 0; k < camadas.get(i).getTamanhoCamada(); k++) { // laço para neuronios da camada i
					camadas.get(i + 1).getNeuronio(j).incrementoPotencial(camadas.get(i).getNeuronio(k).ativacao() * malhaPesos[i].getPeso(j-1, k));
				}
			}
		}

		for (int i = 0; i < camadaSaida.getTamanhoCamada(); i++) {
			camadaSaida.getNeuronio(i).setPotencial(0);
			for (int j = 0; j < camadas.get(this.quantidadeCamadas - 2).getTamanhoCamada(); j++) {
				camadaSaida.getNeuronio(i).incrementoPotencial(camadas.get(this.quantidadeCamadas - 2).getNeuronio(j).ativacao() * malhaPesos[this.quantidadeCamadas - 2].getPeso(i, j));
			}
		}

		for (int i = 0; i < camadaSaida.getTamanhoCamada(); i++) {
			resultado[i] = camadaSaida.getNeuronio(i).ativacao();
		}

		return resultado;
	}

	/* Backpropagation */
	public void retroPropagacao(double [] erro, double n){

		double gradiente_camada_saida[] = new double[camadaSaida.getTamanhoCamada()];
		double potencialAtivacao[] = new double[camadaSaida.getTamanhoCamada()];
	    double peso = 0;

		for( int i = 0; i < camadaSaida.getTamanhoCamada(); i++){

			int funcaoAtivacaoCamadasaida = camadaSaida.getFuncaoAtivacao();
			double derivada_y = 0;
			double y = 0;
			switch(funcaoAtivacaoCamadasaida){
				case 0:
					derivada_y = FuncaoAtivacao.derivadaDegrau(camadaSaida.getNeuronio(i).getPotencial());
					y = FuncaoAtivacao.degrau(camadaSaida.getNeuronio(i).getPotencial());
				break;

				case 1:
					derivada_y = FuncaoAtivacao.derivadaLinear(camadaSaida.getNeuronio(i).getPotencial());
					y = FuncaoAtivacao.linear(camadaSaida.getNeuronio(i).getPotencial());
				break;

				case 2:
					derivada_y = FuncaoAtivacao.derivadaSigmoide(camadaSaida.getNeuronio(i).getPotencial());
					y = FuncaoAtivacao.sigmoide(camadaSaida.getNeuronio(i).getPotencial());
				break;

				case 3:
					derivada_y = FuncaoAtivacao.derivadaTangenteHiperbolica(camadaSaida.getNeuronio(i).getPotencial());
					y = FuncaoAtivacao.tangenteHiperbolica(camadaSaida.getNeuronio(i).getPotencial());
				break;

				case 4:
					derivada_y = FuncaoAtivacao.derivadaSigmoideHeitor(camadaSaida.getNeuronio(i).getPotencial());
					y = FuncaoAtivacao.sigmoideHeitor(camadaSaida.getNeuronio(i).getPotencial());
				break;
			}
			gradiente_camada_saida[i] = erro[i]*derivada_y;
			potencialAtivacao[i] = y;
		}

		ArrayList<double[]> gradientes_por_camada= new ArrayList<double []>(this.quantidadeCamadas - 1);
		for (int i = 0; i < this.quantidadeCamadas - 1; i++) {
			gradientes_por_camada.add(new double[1]);
		}
		gradientes_por_camada.set((this.quantidadeCamadas - 2), gradiente_camada_saida);

		//calculo dos pesos da camada de saida
		for(int i = 0; i < camadas.get(this.quantidadeCamadas - 2).getTamanhoCamada(); i++)
		{
			for(int j = 0 ; j < camadaSaida.getTamanhoCamada(); j++){
				peso = malhaPesos[this.quantidadeCamadas - 2].getPeso(j, i);

				malhaPesos[this.quantidadeCamadas - 2].setPeso(j, i,
					(peso + n*gradiente_camada_saida[j]*camadas.get(this.quantidadeCamadas - 2).getNeuronio(i).ativacao()));
			}
		}

		//calculo das ocultas
		for(int i = this.quantidadeCamadas - 2; i > 0; i--){

			double gradiente_camada_oculta[] = new double[camadas.get(i).getTamanhoCamada() - 1];

			//percorre neuronios da camada
			for(int j = 1; j < camadas.get(i).getTamanhoCamada(); j++){

				//somatorio até o numero de neuronios da camada seguinte
				double sum = 0;

				for(int k = (i + 2 == this.quantidadeCamadas ? 0 : 1); k < camadas.get(i+1).getTamanhoCamada(); k++){
					//System.out.println("k: " + k + " i: " + i);
					//System.out.println("Tam grad: " + gradientes_por_camada.get(i).length);
					sum += gradientes_por_camada.get(i)[(i + 2 == this.quantidadeCamadas ? k : k - 1)]*
						  malhaPesos[i].getPeso((i + 2 == this.quantidadeCamadas ? k : k - 1), j);
				}

				double funcao_derivada_ativacao = funcao(
										camadas.get(i).getNeuronio(j).getFuncaoAtivacao(),
										camadas.get(i).getNeuronio(j).getPotencial())[0];

				gradiente_camada_oculta[j - 1] = funcao_derivada_ativacao*sum;
			}

			gradientes_por_camada.set(i - 1, gradiente_camada_oculta);

			//pesos
			for(int l = 0; l < camadas.get(i - 1).getTamanhoCamada(); l++){
				for(int m = 1; m < camadas.get(i).getTamanhoCamada(); m++){
					peso = malhaPesos[i-1].getPeso(m - 1, l);
					malhaPesos[i-1].setPeso(m - 1, l,
											peso + n*gradientes_por_camada.get(i-1)[m - 1]*
											camadas.get(i - 1).getNeuronio(l).ativacao());

				}
			}
		}
	}

	//recebe o tipo de funcao de ativação e potencial
	public double[] funcao(int index, double potencial){

		double funcao_e_derivada [] = new double[2];
		switch(index){

			case 0:
				funcao_e_derivada [0] = FuncaoAtivacao.derivadaDegrau(potencial);
				funcao_e_derivada [1] = FuncaoAtivacao.degrau(potencial);
			break;

			case 1:
				funcao_e_derivada [0] = FuncaoAtivacao.derivadaLinear(potencial);
				funcao_e_derivada [1] = FuncaoAtivacao.linear(potencial);
			break;

			case 2:
				funcao_e_derivada [0] = FuncaoAtivacao.derivadaSigmoide(potencial);
				funcao_e_derivada [1] = FuncaoAtivacao.sigmoide(potencial);
			break;

			case 3:
				funcao_e_derivada [0] = FuncaoAtivacao.derivadaTangenteHiperbolica(potencial);
				funcao_e_derivada [1] = FuncaoAtivacao.tangenteHiperbolica(potencial);
			break;

			case 4:
				funcao_e_derivada [0] = FuncaoAtivacao.derivadaSigmoideHeitor(potencial);
				funcao_e_derivada [1] = FuncaoAtivacao.sigmoideHeitor(potencial);
			break;
		}

		return funcao_e_derivada;
	}

	/* Treinamento */
	public void treinamento(int epoca, double erro, double n){
		Escrita arquivo = new Escrita();
		int count_epoca = 0; int count_dados = 0;
		double erro_med_quadrado_treinamento = 0;
		double erro_quadratico_treinamento = 0;
		double erro_treinamento = 0;

		double erro_med_quadrado_treinamento_vetor[] = new double[epoca];
		double erro_med_quadrado_validacao_vetor[] = new double[epoca];
		do{
			erro_med_quadrado_treinamento = 0;
			erro_quadratico_treinamento = 0;
			while(count_dados < conjuntoTreinamento.getTamanhoDados()){
				Amostra amostra = conjuntoTreinamento.proximaAmostra();
				double [] erro_treinamento_vetor = new double[camadaSaida.getTamanhoCamada()];
				double [] saida_propagacao = propagacao(amostra);
				double [] saidas = getSaidas(amostra, amostra.getTamanhoAmostra());

				for(int i = 0; i < saida_propagacao.length; i++){
					erro_treinamento = saidas[i] - saida_propagacao[i];
					erro_treinamento_vetor[i] = erro_treinamento;
					erro_quadratico_treinamento += Math.pow(erro_treinamento, 2);
				}

				retroPropagacao(erro_treinamento_vetor, n);

				count_dados++;
			}
			conjuntoTreinamento.resetarContador();

			int count_dados_validacao = 0;
			double erro_med_quadrado_validacao = 0;
			double erro_quadratico_validacao = 0;

			while(count_dados_validacao < conjuntoValidacao.getTamanhoDados()){
				Amostra amostra_validacao = conjuntoValidacao.proximaAmostra();
				double [] resultado_validacao = propagacao(amostra_validacao);
				double [] saidas = getSaidas(amostra_validacao, amostra_validacao.getTamanhoAmostra());
				for(int i = 0; i < resultado_validacao.length; i++){
					erro_quadratico_validacao += Math.pow(saidas[i] - resultado_validacao[i], 2);
				}
				count_dados_validacao++;
			}
			conjuntoValidacao.resetarContador();

			conjuntoTreinamento.embaralhar();
			conjuntoValidacao.embaralhar();

			erro_med_quadrado_validacao = erro_quadratico_validacao/conjuntoValidacao.getTamanhoDados();
			erro_med_quadrado_treinamento = erro_quadratico_treinamento/conjuntoTreinamento.getTamanhoDados();

			erro_med_quadrado_treinamento_vetor[count_epoca] = erro_med_quadrado_treinamento;
			erro_med_quadrado_validacao_vetor[count_epoca] = erro_med_quadrado_validacao;

			count_dados = 0;
			count_dados_validacao = 0;
			count_epoca++;
		} while((count_epoca < epoca) && (erro_med_quadrado_treinamento > erro));

		arquivo.escrever_erro("Treinamentos/" + treino + "_erro_treino.erro", erro_med_quadrado_treinamento_vetor, (count_epoca));
		arquivo.escrever_erro("Treinamentos/" + treino + "_erro_val.erro", erro_med_quadrado_validacao_vetor, (count_epoca));
	}

	public double [] getSaidas(Amostra amostra, int tamanhoAmostra){
		int index = camadaEntrada.getTamanhoCamada();
		int size = index;
		double [] saidas = new double[tamanhoAmostra - size];
		for(int i = 0; i < saidas.length; i++){
			saidas[i] = amostra.getEntrada(index);
			index++;
		}

		return saidas;
	}

	public void salvarRede(String destino){
		try{
			FileWriter escritor = new FileWriter("Redes/" + destino + ".rede");
			PrintWriter saida = new PrintWriter(escritor, true);

			saida.println(quantidadeCamadas);
			String neuronioPorCamada = new String();
			String funcaoAtivacaoCamada = new String();
			for (int i = 0; i < quantidadeCamadas - 1; i++){
				neuronioPorCamada += (camadas.get(i).getTamanhoCamada() - 1) + "\t";
				funcaoAtivacaoCamada += camadas.get(i).getFuncaoAtivacao() + "\t";
			}

			neuronioPorCamada += camadaSaida.getTamanhoCamada() + "\t";
			funcaoAtivacaoCamada += camadaSaida.getFuncaoAtivacao() + "\t";

			saida.println(neuronioPorCamada);
			saida.println(funcaoAtivacaoCamada);

			for (MalhaPesos malha : malhaPesos) {
				saida.println(malha.toString());
			}

			saida.close();
			escritor.close();
		} catch (Exception e){
			System.out.println("Erro no carregamento da rede: " + e.getMessage());
		}
	}

	public void carregarRedePesos(String rede){
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

			this.camadaEntrada = new Camada(neuronioPorCamada[0] + 1, Camada.ENTRADA, funcaoAtivacaoCamada[0]);
			this.camadaSaida = new Camada(neuronioPorCamada[this.quantidadeCamadas - 1], Camada.SAIDA, funcaoAtivacaoCamada[this.quantidadeCamadas - 1]);
			this.camadas = new ArrayList<Camada>();
			this.camadas.add(this.camadaEntrada);
			Camada camadaOculta;
			for (int i = 1; i < quantidadeCamadas - 1; i++) {
				camadaOculta = new Camada(neuronioPorCamada[i] + 1, Camada.OCULTA, funcaoAtivacaoCamada[i]);
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

			this.camadaEntrada = new Camada(neuronioPorCamada[0] + 1, Camada.ENTRADA, funcaoAtivacaoCamada[0]);
			this.camadaSaida = new Camada(neuronioPorCamada[this.quantidadeCamadas - 1], Camada.SAIDA, funcaoAtivacaoCamada[this.quantidadeCamadas - 1]);
			this.camadas = new ArrayList<Camada>();
			this.camadas.add(this.camadaEntrada);
			Camada camadaOculta;
			for (int i = 1; i < quantidadeCamadas - 1; i++) {
				camadaOculta = new Camada(neuronioPorCamada[i] + 1, Camada.OCULTA, funcaoAtivacaoCamada[i]);
				this.camadas.add(camadaOculta);
			}
			this.camadas.add(this.camadaSaida);

			malhaPesos = new MalhaPesos[quantidadeCamadas - 1];
			for (int i = 0; i < malhaPesos.length; i++) {
				this.malhaPesos[i] = new MalhaPesos(neuronioPorCamada[i + 1], neuronioPorCamada[i] + 1);
				this.malhaPesos[i].inicializar();
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

	public void inicializarPesos(){
		for (int i = 0; i < malhaPesos.length; i++) {
			this.malhaPesos[i].inicializar();
		}
	}

	public void carregarConjuntos(String conjuntoTreinamento, String conjuntoValidacao){
		this.conjuntoTreinamento = new ConjuntoDados();
		this.conjuntoTreinamento.carregarDados(conjuntoTreinamento, this.camadaEntrada.getTamanhoCamada() + this.camadaSaida.getTamanhoCamada());
		this.conjuntoValidacao = new ConjuntoDados();
		this.conjuntoValidacao.carregarDados(conjuntoValidacao, camadaEntrada.getTamanhoCamada() + camadaSaida.getTamanhoCamada());
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

	public String getTreino(){
		return this.treino;
	}

	public String setTreino(String treino){
		return this.treino = treino;
	}
}
