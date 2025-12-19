
import java.util.List;

public class Automato{

    private Estado inicial;
    private List<Estado> estadosFinais;
    private List<Transicao> listaTransicao;

    






    public void separaEstados(List<Estado> e) {
        for(Estado estado: e) {
            if(estado.getEstadoInicial()) {
                this.inicial = estado;
            }

            if (estado.getEstadoFinal()) {
                estadosFinais.add(estado);
            }
        }
    }

}