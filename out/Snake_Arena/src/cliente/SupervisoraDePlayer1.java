package cliente;

import jogo.*;

public class SupervisoraDePlayer1 extends Thread{
    private Parceiro servidor;
    private Game g;

    public SupervisoraDePlayer1(Parceiro servidor, Game g) throws Exception
    {
        if(servidor == null)
            throw new Exception("Porta inválida");
        if(g == null)
            throw new Exception("Jogo não está rodando");

        this.servidor = servidor;
        this.g = g;
    }

    public void run()
    {
        for(;;)
        {
            try{
                if(servidor.espie() instanceof ComunicadoDeMovimento)
                {
                    ComunicadoDeMovimento comunicadoDeMovimento = (ComunicadoDeMovimento) servidor.envie();
                    String move = comunicadoDeMovimento.getMovimento();
                    if(move.equals("CIMA")){
                        g.getPlayer1().up();
                    } else
                    if(move.equals("BAIXO")){
                        g.getPlayer1().down();
                    } else
                    if(move.equals("DIREITA")){
                        g.getPlayer1().right();
                    } else
                    if(move.equals("ESQUERDA")){
                        g.getPlayer1().left();
                    }
                }
                else
                    if (servidor.espie() instanceof ComunicadoDeCrescimento)
                {
                   ComunicadoDeCrescimento comunicadoDeCrescimento = (ComunicadoDeCrescimento) servidor.envie();
                   g.getPlayer1().grow();
                   g.getFood().setX(comunicadoDeCrescimento.getFoodX());
                   g.getFood().setY(comunicadoDeCrescimento.getFoodY());
                }
                else
                    if (servidor.espie() instanceof ComunicadoDeMorte)
                {
                    g.getGraphics().state = "END";
                }
            }
            catch (Exception erro)
            {}
        }
    }
}
