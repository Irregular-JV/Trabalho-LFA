public class Transicao{

    private Estado origem;
    private Estado destino;
    private String simbolo;


    public  Transicao(Estado origem, Estado destino, String simbolo) {
        this.origem = origem;
        this.destino = destino;
        this.simbolo = simbolo;
    }

    public Estado getEstadoOrigem () {
        return origem;
    }

    public Estado getEstadoDestino() {
        return destino;
    }

    public String getSimbolo() {
        return simbolo;
    }

    public void setEstadoOrigem(Estado novo){
        origem = novo;
    }

    public void setEstadoDestino (Estado novo) {
        destino = novo;
    }

    public void setSimbolo(String smb) {
        simbolo = smb;
    }
}