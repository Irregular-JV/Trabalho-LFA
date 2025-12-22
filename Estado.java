import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Estado {

    private int estado;
    private boolean estadoFinal;
    private boolean estadoInicial;
    private List<Estado> fechoVazio;

    public Estado(int estado, boolean estadoInicial, boolean estadoFinal) {
        this.estado = estado;
        this.estadoFinal = estadoFinal;
        this.estadoInicial = estadoInicial;
        this.fechoVazio = new ArrayList<>();
    }

    public int getEstado() {
        return estado;
    }

    public boolean getEstadoFinal() {
        return estadoFinal;
    }

    public boolean getEstadoInicial() {
        return estadoInicial;
    }

    public void setEstado(int novoEstado) {
        this.estado = novoEstado;
    }

    public void setEstadoFinal(boolean flag) {
        this.estadoFinal = flag;
    }

    public void setEstadoInicial(boolean flag) {
        this.estadoInicial = flag;
    }

    public void setAdicionaFecho(Estado e) {
        fechoVazio.add(e);
    }

    public List<Estado> getListaFecho() {
        return fechoVazio;
    }

    @Override
    public String toString() {
        return String.valueOf(estado);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Estado)) return false;
        Estado estado1 = (Estado) o;
        return estado == estado1.estado;
    }

    @Override
    public int hashCode() {
        return Objects.hash(estado);
    }
}
