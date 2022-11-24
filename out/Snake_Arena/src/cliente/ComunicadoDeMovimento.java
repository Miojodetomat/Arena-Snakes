package cliente;

import java.util.Locale;

public class ComunicadoDeMovimento extends Comunicado{

    private String movimento;

    public ComunicadoDeMovimento(String movimento)
    {
        this.movimento = movimento.toUpperCase().trim();
    }

    public String getMovimento()
    {
        return this.movimento;
    }
}
