package controledegastos;

import java.time.LocalDate;

/**
 * Representa uma receita financeira.
 * Uma receita possui valor, data e categoria.
 */
public class Receita extends Lancamento {

    private CategoriaReceita categoria;

    /**
     * Cria uma nova receita.
     *
     * @param valor da receita
     * @param data do recebimento
     * @param categoria da receita
     */
    public Receita(double valor, LocalDate data, CategoriaReceita categoria) {
        super(valor, data);
        this.categoria = categoria;
    }

    public CategoriaReceita getCategoria() {
        return categoria;
    }
}
