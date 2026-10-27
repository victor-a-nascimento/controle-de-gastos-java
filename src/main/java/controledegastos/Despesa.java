package controledegastos;

import java.time.LocalDate;

/**
 * Representa uma despesa financeira.
 * Uma despesa possui valor, data e categoria.
 */
public class Despesa extends Lancamento {

    private CategoriaDespesa categoria;

    /**
     * Cria uma nova despesa.
     *
     * @param valor da despesa
     * @param data da despesa
     * @param categoria da despesa
     */
    public Despesa(double valor, LocalDate data, CategoriaDespesa categoria) {
        super(valor, data);
        this.categoria = categoria;
    }

    public CategoriaDespesa getCategoria() {
        return categoria;
    }
}