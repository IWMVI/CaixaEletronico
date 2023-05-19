package view;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import controller.Caixa;

public class Principal {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Caixa caixa = new Caixa();

		int opcao = 0;

		do {
			opcao = showMenu(sc);

			switch (opcao) {
			case 1:
				showCarregaNotas(sc, caixa);
				break;

			case 2:
				showRetirarNotas(sc, caixa);
				break;

			case 3:
				showEstatisticas(caixa);
				break;

			case 4:
				salvarDadosEmArquivo(sc, caixa);
				break;

			default:
				System.out.println("Opção inválida!");
				break;
			}

		} while (opcao != 9);

		sc.close();
	}

	private static int showMenu(Scanner sc) {
		System.out.println("---- MENU ----");
		System.out.println();
		System.out.println("01. Carregar notas");
		System.out.println("02. Retirar notas");
		System.out.println("03. Estatísticas");
		System.out.println("04. Salvar dados em arquivo");
		System.out.println("09. Sair");
		System.out.print("Opção: ");
		int opcao = sc.nextInt();
		System.out.println();
		System.out.println("--------------");
		System.out.println();
		return opcao;
	}

	private static void showCarregaNotas(Scanner sc, Caixa caixa) {

		System.out.println("---- ABASTECIMENTO ----");
		System.out.println();
		System.out.println("Informe a quantia que será depositada");
		System.out.print("R$ ");
		int deposito = sc.nextInt();
		caixa.setDeposito(deposito);
		System.out.println();
		System.out.println("--------------");
		System.out.println();
	}

	private static void showRetirarNotas(Scanner sc, Caixa caixa) {

		System.out.println("---- SAQUE ----");
		System.out.println();

		int saque;
		do {
			System.out.println("Informe o valor a ser sacado");
			System.out.print("R$ ");
			saque = sc.nextInt();
			caixa.setSaque(saque);
			if (saque > caixa.getDeposito()) {
				System.out.println("Valor indisponível");
				System.out.println();
			} else {
				int[] notas = { 100, 50, 20, 10, 5, 2 };
				int[] qtdNotas = new int[notas.length];

				for (int i = 0; i < notas.length; i++) {
					qtdNotas[i] = saque / notas[i];
					saque %= notas[i];
				}

				System.out.println("---- NOTAS ----");
				System.out.println();
				for (int i = 0; i < notas.length; i++) {
					System.out.println("Notas de " + notas[i] + ": " + qtdNotas[i]);
				}
			}
		} while (saque > caixa.getDeposito());

		System.out.println();
		System.out.println("--------------");
		System.out.println();

	}

	private static void showEstatisticas(Caixa caixa) {

		ArrayList<Integer> movimentacao = caixa.getMovimentacao();

		System.out.println("---- ESTATÍSTICAS ----");
		System.out.println();
		System.out.println("MOVIMENTAÇÕES:");
		System.out.println();

		for (int i = 0; i < movimentacao.size(); i++) {
			int valor = movimentacao.get(i);
			if (valor > 0) {
				System.out.println("Depósito: R$ " + valor);
			} else {
				Math.abs(valor);
				System.out.println("Saque: R$ " + Math.abs(valor));
			}
		}

		System.out.println("Saldo no caixa: " + caixa.getDeposito());
		System.out.println();
		System.out.println("--------------");
		System.out.println();
	}

	private static void salvarDadosEmArquivo(Scanner sc, Caixa caixa) {
		System.out.println("---- SALVAR DADOS ----");
		System.out.print("Digite o nome do arquivo: ");
		String nomeArquivo = sc.next();

		try {
			FileWriter arquivo = new FileWriter(nomeArquivo + ".txt");
			PrintWriter gravador = new PrintWriter(arquivo);

			ArrayList<Integer> movimentacao = caixa.getMovimentacao();
			for (int i = 0; i < movimentacao.size(); i++) {
				int valor = movimentacao.get(i);
				if (valor > 0) {
					gravador.println("Depósito: R$ " + valor);
				} else {
					Math.abs(valor);
					gravador.println("Saque: R$ " + Math.abs(valor));
				}
			}

			gravador.println("Saldo no caixa: " + caixa.getDeposito());

			gravador.close();
			arquivo.close();

			System.out.println("Dados salvos no arquivo " + nomeArquivo + ".txt");
		} catch (IOException e) {
			System.out.println("Erro ao salvar os dados no arquivo.");
		}

		System.out.println();
		System.out.println("--------------");
		System.out.println();
	}
}
