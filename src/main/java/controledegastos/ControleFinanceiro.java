package controledegastos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Responsável pelas regras de negócio do sistema.
 */
public class ControleFinanceiro {

    private final List<Receita> receitas;
    private final List<Despesa> despesas;
    private final PersistenciaCSV persistencia;

    /**
     * Classe responsável pelas regras de negócio do sistema financeiro.
     * Controla receitas, despesas, cálculo de saldo e geração de extrato.
     */
    public ControleFinanceiro() {

        persistencia = new PersistenciaCSV();

        receitas = new ArrayList<>(persistencia.carregarReceitas());

        despesas = new ArrayList<>(persistencia.carregarDespesas());
    }

    /**
     * Adiciona uma nova receita ao sistema e atualiza a persistência.
     *
     * @param receita a ser adicionado
     * @throws IllegalArgumentException se a receita for inválida
     */
    public void adicionarReceita(Receita receita) {

        if (receita == null) {
            throw new IllegalArgumentException("Receita inválida.");
        }

        receitas.add(receita);
        persistencia.salvarReceitas(receitas);
    }

    /**
     * Adiciona uma nova despesa ao sistema e atualiza a persistência.
     *
     * @param despesa a ser adicionada
     * @throws IllegalArgumentException se a despesa for inválida
     */
    public void adicionarDespesa(Despesa despesa) {

        if (despesa == null) {
            throw new IllegalArgumentException("Despesa inválida.");
        }

        despesas.add(despesa);
        persistencia.salvarDespesas(despesas);
    }

    /**
     * Calcula o saldo considerando apenas lançamentos até a data atual.
     *
     * @return saldo atual do sistema
     */
    public double consultarSaldoAtual() {

        LocalDate hoje = LocalDate.now();

        double receitasAtual = receitas.stream().filter(r -> !r.getData().isAfter(hoje)).mapToDouble(Receita::getValor).sum();
        double despesasAtual = despesas.stream().filter(d -> !d.getData().isAfter(hoje)).mapToDouble(Despesa::getValor).sum();

        return receitasAtual - despesasAtual;
    }

    /**
     * Calcula o saldo total considerando todos os lançamentos.
     *
     * @return saldo total
     */
    public double consultarSaldoTotal() {

        double totalReceitas = receitas.stream().mapToDouble(Receita::getValor).sum();
        double totalDespesas = despesas.stream().mapToDouble(Despesa::getValor).sum();

        return totalReceitas - totalDespesas;
    }

    /**
     * Lista todas as receitas cadastradas.
     *
     * @return lista de receitas
     */
    public List<Receita> listarReceitas() {
        return receitas;
    }

    /**
     * Lista todas as despesas cadastradas.
     *
     * @return lista de despesas
     */
    public List<Despesa> listarDespesas() {
        return despesas;
    }

    /**
     * Retorna os lançamentos ordenados por data.
     *
     * @return lista de lançamentos ordenados
     */
    public List<Lancamento> listarLancamentosOrdenados() {

        List<Lancamento> lancamentos = new ArrayList<>();

        lancamentos.addAll(receitas);
        lancamentos.addAll(despesas);

        lancamentos.sort(Comparator.comparing(Lancamento::getData));

        return lancamentos;
    }

    /**
     * Gera o extrato financeiro com saldo acumulado.
     *
     * @return lista de itens do extrato
     */
    public List<ItemExtrato> gerarExtrato() {

        List<Lancamento> lancamentos = listarLancamentosOrdenados();

        List<ItemExtrato> extrato = new ArrayList<>();

        double saldo = 0;

        for (Lancamento l : lancamentos) {

            if (l instanceof Receita) {
                saldo += l.getValor();
            } else {
                saldo -= l.getValor();
            }

            extrato.add(new ItemExtrato(l.getData(), l.getClass().getSimpleName(), l.getValor(), saldo));
        }

        return extrato;
    }

}