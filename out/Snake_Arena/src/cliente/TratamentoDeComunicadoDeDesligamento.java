package cliente;
import servidor.Parceiro;

public class TratamentoDeComunicadoDeDesligamento extends Thread
{
    private servidor.Parceiro servidor;

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
                if (this.servidor.espie() instanceof servidor.ComunicadoDeDesligamento)
                {
                    System.out.println("\nO servidor vai ser desligado agora");
                    System.err.println("Jogue mais tarde\n");
                }
            }
            catch(Exception erro)
            {}
        }
    }
}