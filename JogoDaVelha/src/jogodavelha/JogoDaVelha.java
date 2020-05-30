
package jogodavelha;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author alexandre
 */
public class JogoDaVelha extends JFrame {
    //importação das imagens usadas para o X e o Círculo
    ImageIcon circuloJogo = new ImageIcon(getClass().getResource("circulo.png"));
    ImageIcon xJogo = new ImageIcon(getClass().getResource("x.png"));
    
    //painel criado com as definições
    JPanel PainelDeJogo = new JPanel(new GridLayout(3, 3, 8, 8)); 
    
    //o vetor que representa os 9 blocos do jogo da velha
    Botao[] botoes = new Botao[9];
    
    //variaveis que representam os jogadores 1 e 2 
    final int JOGADOR1 = 1;
    final int JOGADOR2 = 2;
    
    //variavel que indica de quem é a vez no jogo
    int vezJogador = JOGADOR1;
    
    //número de jogadas para definir se o jogo deu velha
    int jogadas = 0;
    
    //JLabel que mostra na tela a vez do jogador
    JLabel LabelJogador = new JLabel("Jogador " +JOGADOR1);
    
   //objeto jogo da velha
    public JogoDaVelha(){
        JanelaDeJogo();
        ConfigTelaDeJogo();
    }
    
    //definições da tela do jogo
    public void ConfigTelaDeJogo(){
        add(BorderLayout.CENTER, PainelDeJogo);
        add(BorderLayout.NORTH, LabelJogador);
        PainelDeJogo.setBackground(Color.BLACK);
        LabelJogador.setFont(new Font("Cambria", Font.BOLD, 40));
        LabelJogador.setForeground(Color.GREEN);
        LabelJogador.setHorizontalAlignment(SwingConstants.CENTER);
        
        //for que cria os 9 blocos do jogo na tela
        for(int i = 0; i < 9; i++){
            Botao botao = new Botao();
            botoes[i] = botao;
            PainelDeJogo.add(botao);
        }

    }
    
    //método que muda a vez dos jogadores, alterando o jogador no JLabel também
    public void proximoJogador(){
        if(vezJogador == 1){
            vezJogador = 2;
            LabelJogador.setText("Jogador "+JOGADOR2);
            LabelJogador.setForeground(Color.RED);
        } else{
            vezJogador = 1;
            LabelJogador.setText("Jogador "+JOGADOR1);
            LabelJogador.setForeground(Color.GREEN);
        }
    }
    
    
    //teste de todas as possibilidades de vitória do jogo
    public boolean vitoria(int jogador){
       if((botoes[0].donoDaJogada == jogador) && (botoes[1].donoDaJogada == jogador) &&
               (botoes[2].donoDaJogada == jogador)){
               return true;           
       }
       if((botoes[3].donoDaJogada == jogador) && (botoes[4].donoDaJogada == jogador) &&
               (botoes[5].donoDaJogada == jogador)){
               return true;           
       }
       if((botoes[6].donoDaJogada == jogador) && (botoes[7].donoDaJogada == jogador) &&
               (botoes[8].donoDaJogada == jogador)){
               return true;           
       }
       if((botoes[0].donoDaJogada == jogador) && (botoes[3].donoDaJogada == jogador) &&
               (botoes[6].donoDaJogada == jogador)){
               return true;           
       }
       if((botoes[1].donoDaJogada == jogador) && (botoes[4].donoDaJogada == jogador) &&
               (botoes[7].donoDaJogada == jogador)){
               return true;           
       }
       if((botoes[2].donoDaJogada == jogador) && (botoes[5].donoDaJogada == jogador) &&
               (botoes[8].donoDaJogada == jogador)){
               return true;           
       }
       if((botoes[0].donoDaJogada == jogador) && (botoes[4].donoDaJogada == jogador) &&
               (botoes[8].donoDaJogada == jogador)){
               return true;           
       }
       if((botoes[2].donoDaJogada == jogador) && (botoes[4].donoDaJogada == jogador) &&
               (botoes[6].donoDaJogada == jogador)){
               return true;           
       }
       return false;
    }

    // Configurações da Janela do jogo
    public void JanelaDeJogo(){
        setTitle("Jogo da Velha");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 600);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }
    
    // configurações do botao do jogo da velha
    public class Botao extends JButton{
        //essa variavel indica que o botao não foi preenchido por nenhum jogador
        int donoDaJogada = 0;        
       
        public Botao(){
            setBackground(Color.WHITE);
            addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent evento){
                    if(donoDaJogada == 0){
                        if(vezJogador == JOGADOR1){
                            //preenche o botao vazio com o icone do jogador correspondente
                            setIcon(circuloJogo);
                            donoDaJogada = JOGADOR1;
                           
                        }else{
                            setIcon(xJogo);
                            donoDaJogada = JOGADOR2;
                            
                        }
                        if (vitoria(donoDaJogada)){
                            JOptionPane.showMessageDialog(null, "Jogador "+ donoDaJogada+ " Venceu");
                            System.exit(0);
                        }
                        //incrementa o número de jogadas
                        jogadas++;
                        
                        /*se o números de jogadas chegar a 9 sem nenhuma condição de vitoria
                        atendida, significa que o número de jgadas se esgotou e deu velha
                        */
                        if (jogadas == 9){
                            JOptionPane.showMessageDialog(null, "Deu Velha");
                            System.exit(0);
                        }
                        proximoJogador();
                    }
                }
                
            });
            
        }
        
    }
    
    
}
