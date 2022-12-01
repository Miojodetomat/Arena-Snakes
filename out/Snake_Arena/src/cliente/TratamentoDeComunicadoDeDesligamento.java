package cliente;


import Comunicados.ComunicadoDeDesligamento;
<<<<<<< HEAD
=======
import Comunicados.Parceiro;
>>>>>>> 37b1193e9d334ec691a42446f911c75b3a59cab0

public class TratamentoDeComunicadoDeDesligamento extends Thread
{
    private Parceiro servidor;

    //construtor
    public TratamentoDeComunicadoDeDesligamento(Parceiro servidor)  throws Exception
    {
        if(servidor==null)
            throw new Exception("Porta invalida");

        this.servidor=servidor;
    }

    //run
    public void run()
    {
        for(;;) //for infinito
        {
            try
            {
                if (this.servidor.espie() instanceof ComunicadoDeDesligamento)
                {
<<<<<<< HEAD
                        System.out.println("\nO servidor vai ser desligado agora");
                        System.out.println("Jogue mais tarde\n");
=======
                    System.out.println("\nO servidor vai ser desligado agora");
                    System.err.println("Jogue mais tarde\n");
                    this.servidor.envie();
>>>>>>> 37b1193e9d334ec691a42446f911c75b3a59cab0
                }
                System.exit(0);
            }
            catch(Exception erro)
            {
                System.out.println("deu ruim");
            }
        }
    }
}