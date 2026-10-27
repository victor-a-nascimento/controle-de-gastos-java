package controledegastos;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Responsável por salvar e carregar dados financeiros em arquivos CSV.
 *
 * Persistência de receitas e despesas.
 */
public class PersistenciaCSV {

    private static final String ARQUIVO_RECEITAS = "receitas.csv";
    private static final String ARQUIVO_DESPESAS = "despesas.csv";

    /**
     * Salva receitas no arquivo CSV.
     *
     * @param lista de receitas
     */
    public void salvarReceitas(List<Receita> receitas) {

        List<String> linhas = new ArrayList<>();

        for (Receita r : receitas) {
            linhas.add(r.getValor() + ";" + r.getData() + ";" + r.getCategoria());
        }

        salvarArquivo(ARQUIVO_RECEITAS, linhas);
    }

    /**
     * Salva despesas no arquivo CSV.
     *
     * @param lista de despesas
     */
    public void salvarDespesas(List<Despesa> despesas) {

        List<String> linhas = new ArrayList<>();

        for (Despesa d : despesas) {
            linhas.add(d.getValor() + ";" + d.getData() + ";" + d.getCategoria());
        }

        salvarArquivo(ARQUIVO_DESPESAS, linhas);
    }

    private void salvarArquivo(String nomeArquivo, List<String> linhas) {

        try (PrintWriter writer = new PrintWriter(new FileWriter(nomeArquivo))) {

            for (String linha : linhas) {
                writer.println(linha);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Carrega receitas do arquivo CSV.
     *
     * @return lista de receitas
     */
    public List<Receita> carregarReceitas() {

        List<Receita> receitas = new ArrayList<>();

        for (String linha : carregarArquivo(ARQUIVO_RECEITAS)) {

            String[] dados = linha.split(";");

            receitas.add(new Receita(Double.parseDouble(dados[0]), LocalDate.parse(dados[1]), CategoriaReceita.valueOf(dados[2])));
        }

        return receitas;
    }

    /**
     * Carrega despesas do arquivo CSV.
     *
     * @return lista de despesas
     */
    public List<Despesa> carregarDespesas() {

        List<Despesa> despesas = new ArrayList<>();

        for (String linha : carregarArquivo(ARQUIVO_DESPESAS)) {

            String[] dados = linha.split(";");

            despesas.add(new Despesa(Double.parseDouble(dados[0]), LocalDate.parse(dados[1]), CategoriaDespesa.valueOf(dados[2])));
        }

        return despesas;
    }

    private List<String> carregarArquivo(String nomeArquivo) {

        List<String> linhas = new ArrayList<>();

        File arquivo = new File(nomeArquivo);

        if (!arquivo.exists()) {
            return linhas;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {

            String linha;

            while ((linha = reader.readLine()) != null) {
                linhas.add(linha);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return linhas;
    }
}