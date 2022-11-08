import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.Semaphore;

public class Parceiro{
    //criando uma conexao, transmissor e receptor
    private Socket conexao;
    private ObjectInputStream receptor;
    private ObjectOutputStream transmissor;

    //criando um comunicado
    private Comunicado proximoComunicado=null;

    //criando um semaforo -> cordenar dados
    private Semaphore mutEx = new Semaphore(1, true);

    //construtor de parceiro
    public Parceiro(Socket conexao, ObjectInputStream receptor, ObjectOutputStream transmissor)
    {
        if(conexao==null)
            throw new Exception("Conexao ausente");

        if(receptor==null)
            throw new Exception("Conexao ausente");

        if(transmissor==null)
            throw new Exception("Conexao ausente");

        this.conexao    =conexao;
        this.transmissor=transmissor;
        this.receptor   =receptor;
    }

    //metodos essenciais(recaba, espie, envie, adeus)
    public void receba(Comunicado x) throws Exception
    {
        try{
            this.transmissor.writeObject(x);
            this.transmissor.flush      ();
        }
        catch(IOException erro)
        {
            throw new Exception("Erro de transmissão");
        }
    }

    public Comunicado espie() throws Exception
    {
        try{
            this.mutEx.acquireUninterruptibly();
            if(this.proximoComunicado==null)
                this.proximoComunicado=(Comunicado)this.receptor.readObject();
            this.mutEx.release();
            return this.proximoComunicado;
        }
        catch(Exception erro)
        {
            throw new Exception("Erro de recepção");
        }
    }

    public Comunicado envie() throws Exception
    {
        try
        {
            if(this.proximoComunicado==null)
                this.proximoComunicado=(Comunicado)this.receptor.readObject();
            Comunicado ret = this.proximoComunicado;
            this.proximoComunicado=null;
            return ret;
        }
        catch(Exception erro)
        {
            throw new Exception("Erro de recepção");
        }
    }

    public void adeus () throws Exception
    {
        try{
            this.transmissor.close();
            this.receptor.close();
            this.conexao.close();
        }
        catch(Exception erro)
        {
            throw new Exception("Erro na desconexão");
        }
    }
}