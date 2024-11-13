package conta.controller;

import java.util.ArrayList;
import conta.model.Conta;
import conta.repository.ContaRepository;

public class ContaController implements ContaRepository {

	private

	ArrayList<Conta> listaContas = new ArrayList<Conta>();
	int numero = 0;

	public Conta buscarNaCollection(int numero) {
		for (var conta : listaContas) {
			if (conta.getNumero() == numero) {
				return conta;
			}

		}
		return null;

	}

	@Override
	public void listarTodas() {
		for (var conta : listaContas) {
			conta.visualizar();
		}

	}

	public int gerarNumero() {
		return ++numero;

	}

	@Override
	public void procurarPorNumero(int numero) {

		var conta = buscarNaCollection(numero);

		if (conta != null)
			conta.visualizar();
		else {
			System.out.println("Conta numero:  " + numero + "  não foi encontrada!");
		}

	}

	@Override
	public void cadastrar(Conta conta) {
		listaContas.add(conta);
		System.out.println("conta Número: " + conta.getNumero() + " Foi Criada com Sucesso!");

	}

	@Override
	public void atualizar(Conta conta) {
		var buscaConta = buscarNaCollection(conta.getNumero());

		if (buscaConta != null) {
			listaContas.set(listaContas.indexOf(buscaConta), conta);
			System.out.println("Conta numero:" + conta.getNumero() + " Foi atualidado com sucesso!");
		} else {
			System.out.println("Conta numero:" + conta.getNumero() + " Não Foi encontrado! ");

		}
	}

	@Override
	public void deletar(int numero) {
		var conta = buscarNaCollection(numero);

		if (conta != null) {
			if (listaContas.remove(conta) == true)
				System.out.println("Conta numero:" + numero + "  Foi deletado com sucesso!");
		} else {
			System.out.println("Conta numero:" + numero + "  Não foi encontrado!");

		}

	}

	@Override
	public void sacar(int numero, float valor) {
		var conta = buscarNaCollection(numero);
		if (conta != null) {
			if (conta.sacar(valor) == true) {
				System.out.println("O Saque na Conta numero:  " + numero + "  Foi efetuado com sucesso!");
			} else {
				System.out.println("Conta numero:  " + numero + "  Não foi encontrado!");
			}

		}

	}

	@Override
	public void depositar(int numero, float valor) {
		var conta = buscarNaCollection(numero);
		if (conta != null) {
			conta.depositar(valor);
			System.out.println("O deposito na Conta numero:  " + numero + "  Foi efetuado com sucesso!");
		} else {
			System.out.println(
					"Conta numero:  " + numero + "  Não foi encontrado ou a Conta Destino Não é uma conta corrente!");
		}

	}

	@Override
	public void transferir(int numeroOrigem, int numeroDestino, float valor) {
		var contaOrigem = buscarNaCollection(numeroOrigem);
		var contaDestino = buscarNaCollection(numeroDestino);

		if (contaOrigem != null && contaDestino != null) {

			if (contaOrigem.sacar(valor) == true) {
				contaDestino.depositar(valor);
				System.out.println("Transferência feita com sucesso!   ");
			} else {
				System.out.println("Conta origem e/ou Conta Destino não Foram Encontradas!");
			}

		}

	}

}
