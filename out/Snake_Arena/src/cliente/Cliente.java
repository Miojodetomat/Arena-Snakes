package cliente;

import Comunicados.ComunicadoDeEntradaDeJogador;
<<<<<<< HEAD
=======
import Comunicados.Parceiro;
>>>>>>> 37b1193e9d334ec691a42446f911c75b3a59cab0
import jogo.Game;

import java.net.*;
import java.io.*;


public class Cliente
{
    //definindo o local e a porta padrao caso o 
    //usuario não informe
    public static final String HOST_PADRAO = "localhost";
    public static final int PORTA_PADRAO = 8080;

    //declarando a classe executavel
    public static void main(String[] args)
    {
        //verificando se o tamanho do args(dados no vetor)
        if(args.length>2)
        {
            System.err.println("Dados incorretos\n");
            return;
        }
        try {

            //criando um socket
            Socket conexao = null;
            try {
                String host = Cliente.HOST_PADRAO;
                int porta = Cliente.PORTA_PADRAO;

                if (args.length > 0) {
                    host = args[0];
                    System.out.println(host);
                }

                if (args.length == 2) {
                    porta = Integer.parseInt(args[1]); //convetendo a string em int
                    System.out.println(porta);
                }

                conexao = new Socket(host, porta);
            } catch (Exception erro) {
                System.err.println("Indique o servidor e a porta corretos\n");
                return;
            }

            //criando um transmissor e receptor
            ObjectOutputStream transmissor = null;
            try {
                transmissor = new ObjectOutputStream(conexao.getOutputStream());
            } catch (Exception erro) {
                System.err.println("Indique o servidor e a porta corretos\n");
                return;
            }

            ObjectInputStream receptor = null;
            try {
                receptor = new ObjectInputStream(conexao.getInputStream());
            } catch (Exception erro) {
                System.err.println("Indique o servidor e a porta corretos\n");
                return;
            }

            //criando o servidor
            Parceiro servidor = null;
            try {
                servidor = new Parceiro(conexao, receptor, transmissor);
            } catch (Exception erro) {
                System.err.println("Indique o servidor e a porta corretos\n");
                return;
            }

            //criando o tratamento de desligamento
            TratamentoDeComunicadoDeDesligamento tratamentoDeComunicadoDeDesligamento = null;
            try {
                tratamentoDeComunicadoDeDesligamento = new TratamentoDeComunicadoDeDesligamento(servidor);
            } catch (Exception erro) {
            } //sei que não vai dar erro

            //iniciando essa classe
            tratamentoDeComunicadoDeDesligamento.start();

            //SalaDeEspera salaDeEspera = new SalaDeEspera(servidor);

            //for(;;) {
                /*if(servidor.espie() instanceof ComunicadoDeDesligamento)
                {
                    break;
                }*/


                    //salaDeEspera.setVisible(false);
            Game newGame = new Game(servidor);

            for(;;) {
                if(servidor.envie() instanceof ComunicadoDeEntradaDeJogador) {
                    newGame.start();
                    //criando o tratamento do segundo jogador
                    SupervisoraDePlayer1 supervisoraDoPlayer1 = null;
                    try {
                        supervisoraDoPlayer1 = new SupervisoraDePlayer1(servidor, newGame);
                    } catch (Exception erro) {
                    }
                    //iniciar player 1
                    supervisoraDoPlayer1.start();
                }
            }
                //}
            //}
        }
        catch (Exception err)
        {}
    }
}