package cliente;


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
                    System.err.println("Jogue mais tarde\n");
                }
            }
            catch(Exception erro)
            {}
        }
    }
}