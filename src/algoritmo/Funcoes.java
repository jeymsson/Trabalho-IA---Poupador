package algoritmo;

import java.util.Vector;

public class Funcoes {
	
	public <G> void dump(G value){
		System.out.println(value);
	}
	public void dump(boolean value){
		if(value) {
			System.out.println("true");
		} else {
			System.out.println("false");
		}
	}
	public void dump(int[] value){
		for (int g : value) {
			System.out.println(g);
		}
	}
	public void dump(int[][] value){
		for (int[] g1 : value) {
			for (int g2 : g1) {
				System.out.print(g2 + "  ");
			}
			System.out.println();
		}
	}
	public <G> void dumpE(G value){
		System.err.println(value);
	}
	public void dumpE(int[] value){
		int cont = 0;
		for (int g : value) {
			System.err.print((cont++) + ":  ");
			System.out.println(g);
		}
	}
	public void dumpE(int[][] value){
		int cont = 1;
		for (int[] g1 : value) {
			for (int g2 : g1) {
				System.err.print((cont++) + ":  ");
				System.out.print(g2 + "  ");
			}
			System.out.println();
		}
	}
	
	
	public int[] intVector2intArr(Vector<Integer> vector) {
		int[] a = new int[vector.size()];
		for (int i = 0; i < vector.size(); i++) {
			a[i] = vector.elementAt(i);
		}
		
		return a;
	}
	
}
