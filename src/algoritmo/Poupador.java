package algoritmo;

import java.awt.Point;
import java.util.Vector;

public class Poupador extends ProgramaPoupador {
	
	private int sout = 0;
	private boolean Logs = false;
	private int semVisao = -2,
		foraAmbiente = -1,
		vazio = 0,
		parede = 1,
		banco = 3,
		moeda = 4,
		pastilha = 5
		, semCheiro =0
		, cheiroMuitoFraco = 5
		, cheiroFraco = 4
		, cheirando = 3
		, cheiroForte = 2
		, cheiroMuitoForte = 1;
	// Funcoes f = new Funcoes();
	private int esq	= 4,
		dir	= 3,
		cim	= 1,
		bai	= 2,
		parado	= 0
		, esquerdaVisao	= 11
		, direitaVisao	= 12
		, cimaVisao		= 7
		, baixoVisao	= 16;
	
	// Lembrança do mundo
	private int[][] mundo = new int[30][30];
	
//		sensor.getAmbienteOlfatoLadrao();
//		sensor.getAmbienteOlfatoPoupador();
//		sensor.getNumeroDeMoedas();
//		sensor.getNumeroDeMoedasBanco();
//		sensor.getNumeroJogadasImunes();
//		sensor.getPosicao();
//		sensor.getVisaoIdentificacao();
	
	private boolean getLogs() {
		// TODO Auto-generated method stub
		return this.Logs;
	}
	private int sorteio(int number) {
		return (int) (Math.random() * number+1);
	}
	private boolean sorte() {
		boolean ret = false;
		int resp = (int) (Math.random() * 1);
		if(resp == 1) {
			ret = true;
		}
		return ret;
	}
	
	
	
	public int acao() {
		int novaPosicao = melhorPosicao();
		
//		f.dumpE(sensor.getAmbienteOlfatoPoupador());
//		imprime("----");
		
		return novaPosicao;
		
	}
	
	
	private int melhorPosicao() {
		// TODO Auto-generated method stub
		int[] livres = retornaLivres();
		int menorPeso = 0;
		
		if (livres.length > 0) {
			dump(livres, "retornaLivres: ");
			
			menorPeso = retornaMenorPeso(livres);
			
			dump("retornaMenorPeso: " + menorPeso);
		}
		
		dump("-- Fim Calculo --");
		
		
		
		
		
		
		
		return menorPeso;
	}
	
	
	// Funçao para receber decidir quais são os movimentos 
	// de menor peso a partir dos lados andáveis.
	private int retornaMenorPeso(int[] livres) {
		int[] cheiro = new int[livres.length];
		// TODO Auto-generated method stub
		int menor	= 9999,
			atual	= 9999,
			dirTemp	= 9999,
			melhorDirecao	= 9999;
		int[] olfato = sensor.getAmbienteOlfatoPoupador();
		
		//Capturando cheiro
		for (int i = 0; i < livres.length; i++) {
			dirTemp = Direcao(livres[i], 7); // Direção para olfatos.
			atual = olfato[dirTemp]; // Captura olfato da direção.
			cheiro[i] = atual; // Capturando cheiro
		}
		//verificando menor cheiro
		int novoTam = livres.length;
		dump("");
		for (int i = 0; i < livres.length; i++) {
			if(i == 0) {
				menor = cheiro[i];
			} else if (cheiro[i] <= menor) {
				menor = cheiro[i];
				
				if(cheiro[i-1] > menor && cheiro[i-1] != -1) {
					cheiro[i-1] = -1;
				}
			} else {
				cheiro[i] = -1;
				livres[i] = -1;
				novoTam--;
			}
		}
		//criando novo array de pos
		int[] novoLivre = new int[novoTam];
		int[] novoCheiro = new int[novoTam];
		int cont = 0;
		dump("");
		for (int i = 0; i < livres.length; i++) {
			if(cheiro[i] != -1) {
				novoLivre[cont]  = livres[i];
				novoCheiro[cont] = cheiro[i];
				cont++;
			}
		}

		// melhorDirecao = melhorDirecao != -1 ?melhorDirecao:0;
		melhorDirecao = escolheMehorLivre(novoLivre, novoCheiro);
		return melhorDirecao;
	}
	
	// Função para decidir para onde vai o agente
	private int escolheMehorLivre(int[] novoLivre, int[] novoCheiro) {
		// TODO Auto-generated method stub
		int tamanho = novoLivre.length;
		int sorte = sorteio(tamanho);
		
		int melhorDirecao = novoLivre[sorte-1];
		melhorDirecao = melhorDirecao != -1 ?melhorDirecao:0;
		
		SetaLembranca();
		return melhorDirecao;
	}
	
	// Função para setar as lembranças de onde andou
	private void SetaLembranca() {
		
		Point posAtl = sensor.getPosicao();
		this.mundo[posAtl.y][posAtl.x] = this.mundo[posAtl.y][posAtl.x]+1;
		
		if(this.sout == 0) {
			dump(this.mundo);
			dump("__________________");
			this.sout++;
		} else {this.sout--;}
	}
	// Retorna as direções.
	private int Direcao(int direcao, int tipo) {
		int ret = -1;
		if(tipo == 23) {
			if(direcao == cim)
				ret = 7;
			if(direcao == esq)
				ret = 11;
			if(direcao == bai)
				ret = 16;
			if(direcao == dir)
				ret = 12;
		} else
		if(tipo == 7) {
			if(direcao == cim)
				ret = 1;
			if(direcao == esq)
				ret = 3;
			if(direcao == bai)
				ret = 6;
			if(direcao == dir)
				ret = 4;
		} else
		if(tipo == 4) {
			if(direcao == cim)
				ret = cim;
			if(direcao == esq)
				ret = esq;
			if(direcao == bai)
				ret = bai;
			if(direcao == dir)
				ret = dir;
		} else {
			ret = -1;
		}
		return ret;
	}
	// Retorna posiçoes livres
	private int[] retornaLivres() {
		int[] visao = sensor.getVisaoIdentificacao();
		Vector<Integer> v = new Vector<>();
		
		if(posLivre(cimaVisao)) {
			v.add(cim);
		}
		if(posLivre(baixoVisao)) {
			v.add(bai);
		}
		if(posLivre(esquerdaVisao)) {
			v.add(esq);
		}
		if(posLivre(direitaVisao)) {
			v.add(dir);
		}
		
		return intVector2intArr(v);
	}
	// Informa se a posição está livre
	private boolean posLivre(int pos) {
		boolean sucesso = false;
		int valor = sensor.getVisaoIdentificacao()[pos];
		if(pos>23 || pos<0){
			dump("'.posLivre': Posição inválida.");
			sucesso = false;
		} else {

			if (valor == semVisao || valor == foraAmbiente || valor == parede || valor >= 200) {
				dump("'.posLivre': Valores invalidos");
				sucesso = false;
			}
			else if(valor == vazio || valor == banco || valor == moeda || valor == pastilha){
				dump("'.posLivre': Posição livre.");
				sucesso = true;
			}
		}
		return sucesso;
	}
	
	// ---- Funcoes
	// Imprime se logs Ativo.
	private <G> void dump(G value){
		if(getLogs()) {
			System.err.println(value);
		}
	}
	private void dump(int[] value, String s){
		if(getLogs()) {
			System.err.println(s);
			for (int g : value) {
				System.out.println(g);
			}
		}
	}
	private void dump(int[][] value){
		if(getLogs()) {
			for (int[] g1 : value) {
				for (int g2 : g1) {
					System.out.print(g2 + "  ");
				}
				System.out.println();
			}
		}
	}
	private int[] intVector2intArr(Vector<Integer> vector) {
		int[] a = new int[vector.size()];
		for (int i = 0; i < vector.size(); i++) {
			a[i] = vector.elementAt(i);
		}
		
		return a;
	}
	
}