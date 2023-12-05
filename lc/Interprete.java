import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Interprete {

    private Set<Map<String, Integer>> estado;

    private String codigo;

    public Interprete(){
        estado = new HashSet<>();
        Map<String, Integer> aux = new HashMap<>();
        estado.add(aux);
        codigo= "";
    }

    public String getCodigo(){ return codigo;}

    public void setCodigo(String codigo){ this.codigo = codigo;}

    public Set<Map<String, Integer>> getEstado(){ return estado;}

    public void setEstado(Set<Map<String, Integer>> estado){
        this.estado = estado;
    }
}
