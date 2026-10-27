package controledegastos;

import java.time.LocalDate;

/**
 * Representa um lançamento financeiro.
 *
 * Um lançamento possui valor e data.
 * Serve como superclasse para receitas e despesas.
 */
public abstract class Lancamento {

    private double valor;
    private LocalDate data;

    /**
     * Cria um lançamento financeiro.
     *
     * @param valor do lançamento
     * @param data do lançamento
     */
    public Lancamento(double valor, LocalDate data) {
        this.valor = valor;
        this.data = data;
    }

    public double getValor() {
        return valor;
    }

    public LocalDate getData() {
        return data;
    }
}