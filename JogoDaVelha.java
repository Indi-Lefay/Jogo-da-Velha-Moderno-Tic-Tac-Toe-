import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.Objects;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class JogoDaVelha extends JFrame {
   private CardLayout cardLayout;
   private JPanel mainPanel;
   private String nomeJogador1 = "Jogador 1";
   private String nomeJogador2 = "Jogador 2";
   private boolean vezDoX = true;
   private int vitoriasX = 0;
   private int vitoriasO = 0;
   private int numeroDaPartida = 1;
   private JLabel labelTitulo;
   private JLabel lblJ1;
   private JLabel lblJ2;
   private JTextField txtJ1;
   private JTextField txtJ2;
   private JButton btnIniciarJogo;
   private JLabel labelStatus;
   private JLabel labelPlacar;
   private JLabel labelTituloHistorico;
   private JTextArea areaHistorico;
   private JButton[] botoesTabuleiro = new JButton[9];
   private JButton btnJogarDeNovo;
   private JButton btnLimparTudo;
   private JButton btnVoltarMenu;
   private int jogadas = 0;
   private boolean jogoAtivo = true;

   public JogoDaVelha() {
      this.setTitle("Jogo da Velha Moderno Pro");
      this.setDefaultCloseOperation(3);
      this.setSize(1024, 768);
      this.setMinimumSize(new Dimension(800, 600));
      this.setLocationRelativeTo((Component)null);
      this.cardLayout = new CardLayout();
      this.mainPanel = new JPanel(this.cardLayout);
      JPanel var1 = this.criarTelaMenu();
      JPanel var2 = this.criarTelaJogo();
      this.mainPanel.add(var1, "Menu");
      this.mainPanel.add(var2, "Jogo");
      this.add(this.mainPanel);
      this.cardLayout.show(this.mainPanel, "Menu");
      this.addComponentListener(new ComponentAdapter() {
         {
            Objects.requireNonNull(JogoDaVelha.this);
         }

         public void componentResized(ComponentEvent var1) {
            JogoDaVelha.this.ajustarFontesDinamicas();
         }
      });
      this.setVisible(true);
   }

   private JPanel criarTelaMenu() {
      JPanel var1 = new JPanel(new GridBagLayout());
      var1.setBackground(new Color(24, 24, 38));
      GridBagConstraints var2 = new GridBagConstraints();
      var2.insets = new Insets(15, 15, 15, 15);
      var2.fill = 2;
      this.labelTitulo = new JLabel("JOGO DA VELHA", 0);
      this.labelTitulo.setForeground(new Color(81, 150, 255));
      var2.gridx = 0;
      var2.gridy = 0;
      var2.gridwidth = 2;
      var1.add(this.labelTitulo, var2);
      this.lblJ1 = new JLabel("Jogador 1 (X): ");
      this.lblJ1.setForeground(Color.WHITE);
      var2.gridx = 0;
      var2.gridy = 1;
      var2.gridwidth = 1;
      var1.add(this.lblJ1, var2);
      this.txtJ1 = new JTextField("Jogador 1", 15);
      this.txtJ1.setBackground(new Color(43, 43, 66));
      this.txtJ1.setForeground(Color.WHITE);
      this.txtJ1.setCaretColor(Color.WHITE);
      this.txtJ1.setBorder(BorderFactory.createLineBorder(new Color(81, 150, 255), 1));
      var2.gridx = 1;
      var2.gridy = 1;
      var1.add(this.txtJ1, var2);
      this.lblJ2 = new JLabel("Jogador 2 (O): ");
      this.lblJ2.setForeground(Color.WHITE);
      var2.gridx = 0;
      var2.gridy = 2;
      var1.add(this.lblJ2, var2);
      this.txtJ2 = new JTextField("Jogador 2", 15);
      this.txtJ2.setBackground(new Color(43, 43, 66));
      this.txtJ2.setForeground(Color.WHITE);
      this.txtJ2.setCaretColor(Color.WHITE);
      this.txtJ2.setBorder(BorderFactory.createLineBorder(new Color(81, 150, 255), 1));
      var2.gridx = 1;
      var2.gridy = 2;
      var1.add(this.txtJ2, var2);
      this.btnIniciarJogo = new JButton("INICIAR JOGO");
      this.btnIniciarJogo.setBackground(new Color(34, 139, 34));
      this.btnIniciarJogo.setForeground(Color.WHITE);
      this.btnIniciarJogo.setFocusPainted(false);
      this.btnIniciarJogo.setBorderPainted(false);
      this.btnIniciarJogo.setContentAreaFilled(false);
      this.btnIniciarJogo.setOpaque(true);
      this.btnIniciarJogo.setBorder(BorderFactory.createEmptyBorder(15, 30, 15, 30));
      var2.gridx = 0;
      var2.gridy = 3;
      var2.gridwidth = 2;
      var2.insets = new Insets(30, 15, 15, 15);
      this.btnIniciarJogo.addActionListener((var1x) -> {
         if (!this.txtJ1.getText().trim().isEmpty()) {
            this.nomeJogador1 = this.txtJ1.getText().trim();
         }

         if (!this.txtJ2.getText().trim().isEmpty()) {
            this.nomeJogador2 = this.txtJ2.getText().trim();
         }

         this.atualizarStatus();
         this.atualizarPlacar();
         this.cardLayout.show(this.mainPanel, "Jogo");
      });
      var1.add(this.btnIniciarJogo, var2);
      return var1;
   }

   private JPanel criarTelaJogo() {
      JPanel var1 = new JPanel(new BorderLayout());
      var1.setBackground(new Color(24, 24, 38));
      JPanel var2 = new JPanel(new GridLayout(2, 1));
      var2.setBackground(new Color(33, 33, 52));
      var2.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
      this.labelStatus = new JLabel("", 0);
      this.labelStatus.setForeground(Color.WHITE);
      this.labelPlacar = new JLabel("", 0);
      this.labelPlacar.setForeground(new Color(180, 180, 200));
      var2.add(this.labelStatus);
      var2.add(this.labelPlacar);
      var1.add(var2, "North");
      JPanel var3 = new JPanel(new GridLayout(3, 3, 10, 10));
      var3.setBackground(new Color(24, 24, 38));
      var3.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

      for(int var4 = 0; var4 < 9; ++var4) {
         this.botoesTabuleiro[var4] = new JButton("");
         this.botoesTabuleiro[var4].setBackground(new Color(43, 43, 66));
         this.botoesTabuleiro[var4].setFocusPainted(false);
         this.botoesTabuleiro[var4].setBorderPainted(false);
         this.botoesTabuleiro[var4].setContentAreaFilled(false);
         this.botoesTabuleiro[var4].setOpaque(true);
         this.botoesTabuleiro[var4].addActionListener((var2x) -> this.realizarJogada(var4));
         var3.add(this.botoesTabuleiro[var4]);
      }

      var1.add(var3, "Center");
      JPanel var7 = new JPanel(new BorderLayout());
      var7.setBackground(new Color(33, 33, 52));
      var7.setPreferredSize(new Dimension(280, 0));
      var7.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
      this.labelTituloHistorico = new JLabel("Histórico", 0);
      this.labelTituloHistorico.setForeground(Color.WHITE);
      var7.add(this.labelTituloHistorico, "North");
      this.areaHistorico = new JTextArea();
      this.areaHistorico.setEditable(false);
      this.areaHistorico.setBackground(new Color(24, 24, 38));
      this.areaHistorico.setForeground(new Color(220, 220, 240));
      this.areaHistorico.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
      JScrollPane var5 = new JScrollPane(this.areaHistorico);
      var5.setBorder(BorderFactory.createLineBorder(new Color(60, 60, 90)));
      var7.add(var5, "Center");
      var1.add(var7, "East");
      JPanel var6 = new JPanel(new FlowLayout(1, 15, 15));
      var6.setBackground(new Color(24, 24, 38));
      this.btnJogarDeNovo = new JButton("Jogar de Novo");
      this.btnJogarDeNovo.setBackground(new Color(34, 139, 34));
      this.btnJogarDeNovo.setForeground(Color.WHITE);
      this.btnJogarDeNovo.setEnabled(false);
      this.btnJogarDeNovo.setFocusPainted(false);
      this.btnJogarDeNovo.setBorderPainted(false);
      this.btnJogarDeNovo.setContentAreaFilled(false);
      this.btnJogarDeNovo.setOpaque(true);
      this.btnJogarDeNovo.setBorder(BorderFactory.createEmptyBorder(12, 25, 12, 25));
      this.btnJogarDeNovo.addActionListener((var1x) -> this.reiniciarTabuleiro(false));
      this.btnLimparTudo = new JButton("Zerar Placar/Histórico");
      this.btnLimparTudo.setBackground(new Color(25, 100, 210));
      this.btnLimparTudo.setForeground(Color.WHITE);
      this.btnLimparTudo.setFocusPainted(false);
      this.btnLimparTudo.setBorderPainted(false);
      this.btnLimparTudo.setContentAreaFilled(false);
      this.btnLimparTudo.setOpaque(true);
      this.btnLimparTudo.setBorder(BorderFactory.createEmptyBorder(12, 25, 12, 25));
      this.btnLimparTudo.addActionListener((var1x) -> this.reiniciarTabuleiro(true));
      this.btnVoltarMenu = new JButton("Voltar ao Menu");
      this.btnVoltarMenu.setBackground(new Color(180, 40, 40));
      this.btnVoltarMenu.setForeground(Color.WHITE);
      this.btnVoltarMenu.setFocusPainted(false);
      this.btnVoltarMenu.setBorderPainted(false);
      this.btnVoltarMenu.setContentAreaFilled(false);
      this.btnVoltarMenu.setOpaque(true);
      this.btnVoltarMenu.setBorder(BorderFactory.createEmptyBorder(12, 25, 12, 25));
      this.btnVoltarMenu.addActionListener((var1x) -> {
         this.reiniciarTabuleiro(true);
         this.cardLayout.show(this.mainPanel, "Menu");
      });
      var6.add(this.btnJogarDeNovo);
      var6.add(this.btnLimparTudo);
      var6.add(this.btnVoltarMenu);
      var1.add(var6, "South");
      return var1;
   }

   private void ajustarFontesDinamicas() {
      int var1 = this.getWidth();
      double var2 = (double)var1 / (double)1024.0F;
      if (var2 < 0.8) {
         var2 = 0.8;
      }

      this.labelTitulo.setFont(new Font("Segoe UI", 1, (int)((double)45.0F * var2)));
      this.lblJ1.setFont(new Font("Segoe UI", 0, (int)((double)18.0F * this.factorAjustado(var2))));
      this.lblJ2.setFont(new Font("Segoe UI", 0, (int)((double)18.0F * this.factorAjustado(var2))));
      this.txtJ1.setFont(new Font("Segoe UI", 0, (int)((double)16.0F * this.factorAjustado(var2))));
      this.txtJ2.setFont(new Font("Segoe UI", 0, (int)((double)16.0F * this.factorAjustado(var2))));
      this.btnIniciarJogo.setFont(new Font("Segoe UI", 1, (int)((double)20.0F * this.factorAjustado(var2))));
      this.labelStatus.setFont(new Font("Segoe UI", 1, (int)((double)24.0F * this.factorAjustado(var2))));
      this.labelPlacar.setFont(new Font("Segoe UI", 0, (int)((double)18.0F * this.factorAjustado(var2))));
      this.labelTituloHistorico.setFont(new Font("Segoe UI", 1, (int)((double)18.0F * this.factorAjustado(var2))));
      this.areaHistorico.setFont(new Font("Monospaced", 0, (int)((double)13.0F * this.factorAjustado(var2))));
      this.btnJogarDeNovo.setFont(new Font("Segoe UI", 1, (int)((double)15.0F * this.factorAjustado(var2))));
      this.btnLimparTudo.setFont(new Font("Segoe UI", 1, (int)((double)15.0F * this.factorAjustado(var2))));
      this.btnVoltarMenu.setFont(new Font("Segoe UI", 1, (int)((double)15.0F * this.factorAjustado(var2))));

      for(JButton var7 : this.botoesTabuleiro) {
         var7.setFont(new Font("Segoe UI", 1, (int)((double)65.0F * var2)));
      }

   }

   private double factorAjustado(double var1) {
      return var1 > 1.4 ? 1.4 : var1;
   }

   private void realizarJogada(int var1) {
      if (this.jogoAtivo && this.botoesTabuleiro[var1].getText().equals("")) {
         if (this.vezDoX) {
            this.botoesTabuleiro[var1].setText("X");
            this.botoesTabuleiro[var1].setForeground(new Color(255, 100, 100));
         } else {
            this.botoesTabuleiro[var1].setText("O");
            this.botoesTabuleiro[var1].setForeground(new Color(100, 255, 150));
         }

         ++this.jogadas;
         if (this.verificarVitoria()) {
            this.jogoAtivo = false;
            String var2 = this.vezDoX ? this.nomeJogador1 + " (X)" : this.nomeJogador2 + " (O)";
            if (this.vezDoX) {
               ++this.vitoriasX;
            } else {
               ++this.vitoriasO;
            }

            this.labelStatus.setText("Vencedor: " + var2);
            this.areaHistorico.append("Partida #" + this.numeroDaPartida + ": " + (this.vezDoX ? "X" : "O") + " venceu\n");
            this.finalizarRodada();
         } else if (this.jogadas == 9) {
            this.jogoAtivo = false;
            this.labelStatus.setText("Empate! Deu Velha!");
            this.areaHistorico.append("Partida #" + this.numeroDaPartida + ": Empate\n");
            this.finalizarRodada();
         } else {
            this.vezDoX = !this.vezDoX;
            this.atualizarStatus();
         }

      }
   }

   private void finalizarRodada() {
      this.atualizarPlacar();
      this.btnJogarDeNovo.setEnabled(true);
      ++this.numeroDaPartida;
   }

   private boolean verificarVitoria() {
      String[][] var1 = new String[3][3];
      int var2 = 0;

      for(int var3 = 0; var3 < 3; ++var3) {
         for(int var4 = 0; var4 < 3; ++var4) {
            var1[var3][var4] = this.botoesTabuleiro[var2++].getText();
         }
      }

      for(int var5 = 0; var5 < 3; ++var5) {
         if (!var1[var5][0].equals("") && var1[var5][0].equals(var1[var5][1]) && var1[var5][0].equals(var1[var5][2])) {
            return true;
         }

         if (!var1[0][var5].equals("") && var1[0][var5].equals(var1[1][var5]) && var1[0][var5].equals(var1[2][var5])) {
            return true;
         }
      }

      if (!var1[0][0].equals("") && var1[0][0].equals(var1[1][1]) && var1[0][0].equals(var1[2][2])) {
         return true;
      } else if (!var1[0][2].equals("") && var1[0][2].equals(var1[1][1]) && var1[0][2].equals(var1[2][0])) {
         return true;
      } else {
         return false;
      }
   }

   private void atualizarStatus() {
      this.labelStatus.setText(this.vezDoX ? "Vez de: " + this.nomeJogador1 + " (X)" : "Vez de: " + this.nomeJogador2 + " (O)");
   }

   private void atualizarPlacar() {
      this.labelPlacar.setText(this.nomeJogador1 + " (" + this.vitoriasX + ")  vs  (" + this.vitoriasO + ") " + this.nomeJogador2);
   }

   private void reiniciarTabuleiro(boolean var1) {
      for(int var2 = 0; var2 < 9; ++var2) {
         this.botoesTabuleiro[var2].setText("");
      }

      this.jogadas = 0;
      this.vezDoX = true;
      this.jogoAtivo = true;
      this.btnJogarDeNovo.setEnabled(false);
      if (var1) {
         this.vitoriasX = 0;
         this.vitoriasO = 0;
         this.numeroDaPartida = 1;
         this.areaHistorico.setText("");
      }

      this.atualizarStatus();
      this.atualizarPlacar();
   }

   public static void main(String[] var0) {
      SwingUtilities.invokeLater(JogoDaVelha::new);
   }
}
