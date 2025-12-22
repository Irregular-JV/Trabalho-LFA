
import java.util.ArrayList;
import java.util.List;

public class Automato {

    private Estado inicial;
    private List<Estado> estadosFinais;
    private List<Transicao> listaTransicao;

    public Automato(List<Transicao> listaTransicao, List<Estado> listaEstados) {
        this.listaTransicao = listaTransicao;
        separaEstados(listaEstados);
    }

    public void separaEstados(List<Estado> e) {
        for (Estado estado : e) {
            if (estado.getEstadoInicial()) {
                this.inicial = estado;
            }
            if (estado.getEstadoFinal()) {
                estadosFinais.add(estado);
            }
        }
    }

    public void calculaFechoVazio() {
        for (Transicao transicao : this.listaTransicao) {
            if (!transicao.getEstadoOrigem().getListaFecho().contains(transicao.getEstadoOrigem())) {
                transicao.getEstadoOrigem().setAdicionaFecho(transicao.getEstadoOrigem());
            }
            if (!transicao.getEstadoDestino().getListaFecho().contains(transicao.getEstadoDestino())) {
                transicao.getEstadoDestino().setAdicionaFecho(transicao.getEstadoDestino());
            }
        }
        for (Transicao transicao : this.listaTransicao) {
            Estado estado = transicao.getEstadoOrigem();
            calculaFechoVazioRec(estado, estado);
        }
    }

    public void calculaFechoVazioRec(Estado estadoCalcular, Estado estadoAtual) {
        for (Transicao t : this.listaTransicao) {
            if (t.getEstadoOrigem().equals(estadoAtual) && t.getSimbolo() == 'E') {
                Estado proximo = t.getEstadoDestino();
                if (!estadoCalcular.getListaFecho().contains(proximo)) {
                    estadoCalcular.setAdicionaFecho(proximo);
                    calculaFechoVazioRec(estadoCalcular, proximo);
                }
            }
        }
    }

    public boolean aceitaPalavra (String palavra){
        List<Estado> estadosAtuais = new ArrayList<>();

        estadosAtuais.add(this.inicial);
        estadosAtuais.addAll(this.inicial.getListaFecho());

        for(int i = 0; i < palavra.length(); i++){
            Character letra = palavra.charAt(i);
            List<Estado> proximosEstados = new ArrayList<>();

            for(Estado e : estadosAtuais){
                for(Transicao t : listaTransicao){
                    if(t.getEstadoOrigem().equals(e) && t.getSimbolo() == letra){
                        Estado destino = t.getEstadoDestino();
                        proximosEstados.add(destino);
                        proximosEstados.addAll(destino.getListaFecho());
                    }
                }
            }
            estadosAtuais = proximosEstados;
            if(estadosAtuais.size() == 0) return false;
        }

        for(Estado estado : estadosAtuais){
            if(estado.getEstadoFinal() == true) return true;
        }

        return false;
    }

    public Estado getInicial () {
        return inicial;
    }

    public void setInicial (Estado inicial){
        this.inicial = inicial;
    }

    public List<Estado> getEstadosFinais () {
        return estadosFinais;
    }

    public void setEstadosFinais (List < Estado > estadosFinais) {
        this.estadosFinais = estadosFinais;
    }

    public List<Transicao> getListaTransicao () {
        return listaTransicao;
    }

    public void setListaTransicao (List < Transicao > listaTransicao) {
        this.listaTransicao = listaTransicao;
    }
}
