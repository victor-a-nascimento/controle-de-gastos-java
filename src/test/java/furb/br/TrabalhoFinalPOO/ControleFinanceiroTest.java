package furb.br.TrabalhoFinalPOO;

import org.junit.jupiter.api.Test;

import controledegastos.CategoriaDespesa;
import controledegastos.CategoriaReceita;
import controledegastos.ControleFinanceiro;
import controledegastos.Despesa;
import controledegastos.Receita;

import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class ControleFinanceiroTest {

    @Test
    void adicionarReceita() {

        ControleFinanceiro c = new ControleFinanceiro();

        Receita r = new Receita(1000, LocalDate.of(2024, 1, 1), CategoriaReceita.SALARIO
        );

        c.adicionarReceita(r);

        assertEquals(1, c.listarReceitas().size());
    }

    @Test
    void adicionarDespesa() {

        ControleFinanceiro c = new ControleFinanceiro();

        Despesa d = new Despesa(200, LocalDate.of(2024, 1, 1), CategoriaDespesa.ALIMENTACAO);

        c.adicionarDespesa(d);

        assertEquals(1, c.listarDespesas().size());
    }

    @Test
    void calcularSaldoTotal() {

        ControleFinanceiro c = new ControleFinanceiro();

        c.adicionarReceita(new Receita(1000, LocalDate.of(2024, 1, 1), CategoriaReceita.SALARIO));
        c.adicionarDespesa(new Despesa(300, LocalDate.of(2024, 1, 1), CategoriaDespesa.ALIMENTACAO));

        assertEquals(700, c.consultarSaldoTotal());
    }

    @Test
    void calcularSaldoAtual() {

        ControleFinanceiro c = new ControleFinanceiro();

        c.adicionarReceita(new Receita(1000, LocalDate.of(2024, 1, 1), CategoriaReceita.SALARIO));
        c.adicionarDespesa(new Despesa(200, LocalDate.of(2024, 1, 1), CategoriaDespesa.ALIMENTACAO));

        assertEquals(800, c.consultarSaldoAtual());
    }

    @Test
    void gerarExtrato() {

        ControleFinanceiro c = new ControleFinanceiro();

        c.adicionarReceita(new Receita(1000, LocalDate.of(2024, 1, 1), CategoriaReceita.SALARIO));
        c.adicionarDespesa(new Despesa(200, LocalDate.of(2024, 1, 1), CategoriaDespesa.ALIMENTACAO));

        assertEquals(2, c.gerarExtrato().size());
    }

    @Test
    void listarReceitas() {

        ControleFinanceiro c = new ControleFinanceiro();

        c.adicionarReceita(new Receita(500, LocalDate.of(2024, 1, 1), CategoriaReceita.SALARIO));

        assertEquals(1, c.listarReceitas().size());
    }

    @Test
    void listarDespesas() {

        ControleFinanceiro c = new ControleFinanceiro();

        c.adicionarDespesa(new Despesa(100, LocalDate.of(2024, 1, 1), CategoriaDespesa.ALIMENTACAO));

        assertEquals(1, c.listarDespesas().size());
    }

    @Test
    void listarLancamentosOrdenados() {

        ControleFinanceiro c = new ControleFinanceiro();

        c.adicionarReceita(new Receita(100, LocalDate.of(2024, 1, 2), CategoriaReceita.SALARIO));
        c.adicionarDespesa(new Despesa(50, LocalDate.of(2024, 1, 1), CategoriaDespesa.ALIMENTACAO));

        assertEquals(2, c.listarLancamentosOrdenados().size());

        assertTrue(c.listarLancamentosOrdenados().get(0).getData().isBefore(c.listarLancamentosOrdenados().get(1).getData()));
    }
}