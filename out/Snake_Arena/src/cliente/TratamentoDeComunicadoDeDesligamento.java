package cliente;


import Comunicados.ComunicadoDeDesligamento;

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
                        System.out.println("\nO servidor vai ser desligado agora");
                        System.out.println("Jogue mais tarde\n");
                }
                System.exit(0);
            }
            catch(Exception erro)
            {}
        }
    }
}