import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class Automato {

    private Estado inicial;
    private List<Estado> estadosFinais;
    private List<Transicao> listaTransicao;
    private List<Estado> listaEstados;

    public Automato(List<Transicao> listaTransicao, List<Estado> listaEstados) {
        this.listaTransicao = listaTransicao;
        this.listaEstados = listaEstados;
        this.estadosFinais = new ArrayList<>();
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
        for (Estado estado : listaEstados) {
            if (!estado.getListaFecho().contains(estado)) {
                estado.setAdicionaFecho(estado);
            }
        }

        for (Estado estado : listaEstados) {
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

    public boolean aceitaPalavra(String palavra) {
        Set<Estado> estadosAtuais = new LinkedHashSet<>();

        estadosAtuais.addAll(this.inicial.getListaFecho());

        for (int i = 0; i < palavra.length(); i++) {
            char letra = palavra.charAt(i);

            Set<Estado> proximosEstados = new LinkedHashSet<>();

            for (Estado e : estadosAtuais) {
                for (Transicao t : listaTransicao) {
                    if (t.getEstadoOrigem().equals(e) && t.getSimbolo() == letra) {
                        Estado destino = t.getEstadoDestino();
                        proximosEstados.addAll(destino.getListaFecho());
                    }
                }
            }

            estadosAtuais = proximosEstados;
            if (estadosAtuais.isEmpty()) return false;
        }

        for (Estado estado : estadosAtuais) {
            if (estado.getEstadoFinal()) return true;
        }

        return false;
    }

    public Estado getInicial() {
        return inicial;
    }

    public List<Estado> getEstadosFinais() {
        return estadosFinais;
    }

    public List<Transicao> getListaTransicao() {
        return listaTransicao;
    }

    public List<Estado> getListaEstados() {
        return listaEstados;
    }
}
