package algoritmo;

import java.util.Vector;

public class Poupador extends ProgramaPoupador {
	
	boolean Logs = false;
	int semVisao = -2,
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
	Funcoes f = new Funcoes();
	int esq	= 4,
		dir	= 3,
		cim	= 1,
		bai	= 2,
		parado	= 0
		, esquerdaVisao	= 11
		, direitaVisao	= 12
		, cimaVisao		= 7
		, baixoVisao	= 16;

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
	public int sorteio(int number) {
		return (int) (Math.random() * number+1);
	}
	public boolean sorte() {
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
		int ret = sorteio(4);
		
		int[] livres = retornaLivres();
		f.dump(livres);
		
		int menorPeso = retornaMenorPeso(livres);
		f.dump(menorPeso);
		
		System.out.println("-");
		
		
		
		
		
		
		
		return menorPeso;
	}
	
	
	
	private int retornaMenorPeso(int[] livres) {
		// TODO Auto-generated method stub
		int menor	= 9999,
			atual	= 9999,
			dirTemp	= 9999,
			melhorDirecao	= 9999;
		int[] olfato = sensor.getAmbienteOlfatoPoupador();
		
		for (int i = 0; i < livres.length; i++) {
			dirTemp = Direcao(livres[i], 7); // Direção para olfatos.
			atual = olfato[dirTemp]; // Captura olfato da direção.
			if(atual < menor){ //Captura menor olfato
				menor = atual;
				melhorDirecao = livres[i];
			}
		}
//		if(!sorte()) {
//			melhorDirecao = sorteio(livres.length);
//		}
		return melhorDirecao;
	}
	
	// Retorna as direções.
	public int Direcao(int direcao, int tipo) {
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
		
		return f.intVector2intArr(v);
	}
	// Informa se a posição está livre
	public boolean posLivre(int pos) {
		boolean sucesso = false;
		int valor = sensor.getVisaoIdentificacao()[pos];
		if(pos>23 || pos<0){
			imprime("'.posLivre': Posição inválida.");
			sucesso = false;
		} else {

			if (valor == semVisao || valor == foraAmbiente || valor == parede) {
				imprime("'.posLivre': Valores invalidos");
				sucesso = false;
			}
			else if(valor == vazio || valor == banco || valor == moeda || valor == pastilha){
				imprime("'.posLivre': Posição livre.");
				sucesso = true;
			}
		}
		return sucesso;
	}
	// Imprime se logs Ativo.
	public void imprime(String mensagem) {
		if(getLogs()) {
			System.out.println(mensagem);
		}
	}
	
}