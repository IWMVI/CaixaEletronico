package controller;

import java.util.ArrayList;

public class Caixa {
	private int deposito;
	private int saque;
	private ArrayList<Integer> movimentacao;

	public Caixa() {
		this.movimentacao = new ArrayList<Integer>();
	}

	public void setDeposito(int deposito) {
		this.deposito += deposito;
		movimentacao.add(deposito);
	}

	public int getDeposito() {
		return deposito;
	}

	public void setSaque(int saque) {
		this.saque = saque;
		deposito -= saque;
		movimentacao.add(-saque);
	}

	public int getSaque() {
		return saque;
	}

	public ArrayList<Integer> getMovimentacao() {
		return movimentacao;
	}

}
