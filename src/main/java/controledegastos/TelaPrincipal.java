package controledegastos;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TelaPrincipal extends JFrame {

    private ControleFinanceiro controle;

    private JTextArea txtResultado;

    private JButton btnReceita;
    private JButton btnDespesa;
    private JButton btnSaldoAtual;
    private JButton btnSaldoTotal;
    private JButton btnReceitas;
    private JButton btnDespesas;
    private JButton btnExtrato;
    
//  private JButton btnLimpar;

    public TelaPrincipal() {

        controle = new ControleFinanceiro();

        configurarJanela();
        criarComponentes();
        configurarEventos();
    }

    private void configurarJanela() {

        setTitle("Controle Financeiro");
        setSize(700, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void criarComponentes() {

        JPanel painelBotoes = new JPanel();
        painelBotoes.setLayout(new GridLayout(7, 1, 5, 5));

        btnReceita = new JButton("Adicionar Receita");
        btnDespesa = new JButton("Adicionar Despesa");
        btnSaldoAtual = new JButton("Saldo Atual");
        btnSaldoTotal = new JButton("Saldo Total");
        btnReceitas = new JButton("Listar Receitas");
        btnDespesas = new JButton("Listar Despesas");
        btnExtrato = new JButton("Extrato");
        
//      btnLimpar = new JButton("Limpar tudo");

        painelBotoes.add(btnReceita);
        painelBotoes.add(btnDespesa);
        painelBotoes.add(btnSaldoAtual);
        painelBotoes.add(btnSaldoTotal);
        painelBotoes.add(btnReceitas);
        painelBotoes.add(btnDespesas);
        painelBotoes.add(btnExtrato);
        
//      painelBotoes.add(Box.createVerticalStrut(5));
//      painelBotoes.add(btnLimpar);

        txtResultado = new JTextArea();
        txtResultado.setEditable(false);

        add(painelBotoes, BorderLayout.WEST);
        add(new JScrollPane(txtResultado), BorderLayout.CENTER);
    }

    private void configurarEventos() {

        btnReceita.addActionListener(e -> adicionarReceita());

        btnDespesa.addActionListener(e -> adicionarDespesa());

        btnSaldoAtual.addActionListener(e -> mostrarSaldoAtual());

        btnSaldoTotal.addActionListener(e -> mostrarSaldoTotal());

        btnReceitas.addActionListener(e -> listarReceitas());

        btnDespesas.addActionListener(e -> listarDespesas());

        btnExtrato.addActionListener(e -> mostrarExtrato());
    }

    private void adicionarReceita() {

        try {

            CategoriaReceita categoria = (CategoriaReceita) JOptionPane.showInputDialog(
                            this,
                            "Categoria",
                            "Receita",
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            CategoriaReceita.values(),
                            CategoriaReceita.SALARIO);

            if (categoria == null) {
                return;
            }

            double valor = Double.parseDouble(JOptionPane.showInputDialog("Valor:"));

            String dataTexto = JOptionPane.showInputDialog("Data (dd/MM/yyyy)");

            LocalDate data = LocalDate.parse(dataTexto, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

            controle.adicionarReceita(new Receita(valor, data, categoria));

            JOptionPane.showMessageDialog(this, "Receita cadastrada com sucesso.");

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
        }
    }

    private void adicionarDespesa() {

        try {

            CategoriaDespesa categoria = (CategoriaDespesa) JOptionPane.showInputDialog(
                            this,
                            "Categoria",
                            "Despesa",
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            CategoriaDespesa.values(),
                            CategoriaDespesa.ALIMENTACAO);

            if (categoria == null) {
                return;
            }

            double valor = Double.parseDouble(JOptionPane.showInputDialog("Valor:"));

            String dataTexto = JOptionPane.showInputDialog("Data (dd/MM/yyyy)");

            LocalDate data = LocalDate.parse(dataTexto, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

            controle.adicionarDespesa(new Despesa(valor, data, categoria));

            JOptionPane.showMessageDialog(this, "Despesa cadastrada com sucesso.");

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
        }
    }

    private void mostrarSaldoAtual() {

        txtResultado.setText("Saldo atual: R$ " + String.format("%.2f", controle.consultarSaldoAtual()));
    }

    private void mostrarSaldoTotal() {txtResultado.setText("Saldo total: R$ " + String.format("%.2f", controle.consultarSaldoTotal()));
    }

    private void listarReceitas() {

        txtResultado.setText("");

        for (Receita r : controle.listarReceitas()) {

            txtResultado.append( r.getData()
                            + " - "
                            + r.getCategoria()
                            + " - R$ "
                            + String.format("%.2f", r.getValor())
                            + "\n");
        }
    }

    private void listarDespesas() {

        txtResultado.setText("");

        for (Despesa d : controle.listarDespesas()) {

            txtResultado.append(d.getData()
                            + " - "
                            + d.getCategoria()
                            + " - R$ "
                            + String.format("%.2f", d.getValor())
                            + "\n");
        }
    }

    private void mostrarExtrato() {

        txtResultado.setText("");

        for (ItemExtrato item : controle.gerarExtrato()) {

            txtResultado.append(item.getData()
                            + " | "
                            + item.getDescricao()
                            + " | R$ "
                            + String.format("%.2f", item.getValor())
                            + " | Saldo: R$ "
                            + String.format("%.2f", item.getSaldo())
                            + "\n");
        }
        
//      btnLimpar.addActionListener(e -> {
//
//          txtResultado.setText("");
//
//          controle.listarReceitas().clear();
//          controle.listarDespesas().clear();
//
//          JOptionPane.showMessageDialog(this, "Dados e tela limpos!");
//      });
    }
}