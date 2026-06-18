# ❌ Jogo da Velha Moderno (Tic-Tac-Toe) ⭕

Um jogo da velha eletrônico desenvolvido em **Java** utilizando a biblioteca gráfica **Swing**. O projeto conta com um design moderno em *Dark Mode*, gerenciamento de nomes customizados, placar acumulativo, histórico de rodadas em tempo real e um layout inteligente que se adapta automaticamente a telas menores ou modo tela cheia.

---

## ✨ Funcionalidades

* **Menu Inicial:** Tela dedicada para configuração antes do início da partida.
* **Nomes Customizados:** Definição personalizada para o Jogador 1 (X) e Jogador 2 (O).
* **Design Fluido & Responsivo:** O jogo inicia em modo janela (1024x768), mas se adapta perfeitamente se for maximizado para **Tela Cheia**, redimensionando os botões e os tamanhos dos elementos automaticamente.
* **Controle de Turnos:** Exibição dinâmica na tela indicando de quem é a vez de jogar.
* **Histórico de Partidas:** Um painel lateral que registra o resultado de cada rodada disputada (Vitórias ou Empates).
* **Gerenciamento de Fluxo:** * *Jogar de Novo:* Reinicia o tabuleiro mantendo o placar e o histórico intactos.
    * *Zerar Tudo:* Reseta as estatísticas para começar um novo campeonato.
    * *Voltar ao Menu:* Retorna à tela inicial para alteração de jogadores.

---

## 🎨 Visual do Projeto

O jogo foi construído focando em alta legibilidade e estética gamer/moderna:
* **Fundo Escuro:** Tons de azul-escuro e cinza grafite para evitar fadiga visual.
* **Cores Sólidas:** Botões de controle com preenchimento forte (Verde para jogar, Azul para resetar, Vermelho para sair) e fontes brancas de alto contraste.
* **Cores dos Ícones:** O caractere **X** é renderizado em vermelho claro e o **O** em verde pastel, facilitando a rápida leitura do tabuleiro.

---

## 🚀 Como Executar o Jogo

### Pré-requisitos
Você precisa ter o **JDK (Java Development Kit)** instalado em sua máquina (versão 8 ou superior).

### Método 1: Pelo Terminal / Prompt de Comando
1. Baixe o arquivo `JogoDaVelha.java`.
2. Abra o terminal na pasta onde o arquivo foi salvo.
3. Compile o arquivo com o comando:
   
```bash
javac JogoDaVelha.java
```

### Execute o jogo com o comando:

```Bash
java JogoDaVelha

```
### Método 2: Por uma IDE (VS Code, IntelliJ ou Eclipse)

1. Abra o seu ambiente de desenvolvimento de preferência.

2. Crie um novo projeto Java ou importe o arquivo JogoDaVelha.java.

3. Certifique-se de que o arquivo está na pasta raiz de códigos (src).

4. Clique com o botão direito no código e selecione Run (Executar) a partir do método main.

## 🛠️ Tecnologias Utilizadas

- Linguagem: Java

-  Interface Gráfica: javax.swing e java.awt

- Arquitetura de Layout: CardLayout (para alternar telas), GridBagLayout (menu centralizado) e BorderLayout/GridLayout (tabuleiro e menus de jogo).
