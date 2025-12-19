public class Transicao{

    private Estado origem;
    private Estado destino;
    private char simbolo;


    public  Transicao(Estado origem, Estado destino, char simbolo) {
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

    public char getSimbolo() {
        return simbolo;
    }

    public void setEstadoOrigem(Estado novo){
        origem = novo;
    }

    public void setEstadoDestino (Estado novo) {
        destino = novo;
    }

    public void setSimbolo(char smb) {
        simbolo = smb;
    }
}