
import java.util.List;

public class Estado{

    private int estado;
    private boolean estadoFinal;
    private boolean estadoInicial;
    private List<Estado> fechoVazio;

    public Estado(int estado, boolean estadoInicial, boolean estadoFinal) {
        this.estado = estado;
        this.estadoFinal = estadoFinal;
        this.estadoInicial = estadoInicial;
    }

    public int getEstado() {
        return  estado;
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

    public void setEstadoFinal (boolean flag) {
        this.estadoFinal = flag;
    }

    public void setEstadoInicial(boolean b) {
        this.estadoInicial = b;
    }

    public void setAdicionaFecho(Estado e) {
        fechoVazio.add(e);
    }

    public List getListaFecho() {
        return fechoVazio;
    }
}