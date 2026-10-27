package controledegastos;

import java.time.LocalDate;

/**
 * Representa um item exibido no extrato financeiro.
 */
public class ItemExtrato {

    private LocalDate data;
    private String descricao;
    private double valor;
    private double saldo;

    /**
     * Cria um item do extrato financeiro.
     *
     * @param data do lançamento
     * @param descricao descrição do lançamento
     * @param valor do lançamento
     * @param saldo acumulado após o lançamento
     */
    public ItemExtrato(LocalDate data, String descricao, double valor, double saldo) {
        this.data = data;
        this.descricao = descricao;
        this.valor = valor;
        this.saldo = saldo;
    }

    public LocalDate getData() {
        return data;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getValor() {
        return valor;
    }

    public double getSaldo() {
        return saldo;
    }
}