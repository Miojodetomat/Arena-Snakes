public class ComunicadoDeMovimento extends Comunicado {
    private String movimentos = "";

    public ComunicadoDeMovimento(String movimento) throws Exception {
        if (movimento == null)
            throw new Exception("Movimento inválido");

        this.movimentos = movimento;
    }

    public String getMovimento() {
        return this.movimentos;
    }
}