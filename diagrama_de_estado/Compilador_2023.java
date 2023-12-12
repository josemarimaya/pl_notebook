import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Compilador extends AnasintBaseListener{

    Map<String, Map<String, Anasint.CondicionContext>> mem_transiciones = new HashMap<>();
    Map<String, List<Anasint.AsignacionContext>> mem_estados = new HashMap<>();

    String estado_inicial;



    // No necesitamos que sean asignacioncontext porque las vamos a inicializar a 0
    Set<String> declaraciones_iniciales;

    private String nombre_fichero;

    private FileWriter fichero;

    private int indent = 0;

    public void init(String n){
        nombre_fichero = new String(n);
    }
    private void abrir_fichero(String f){
        try{
            fichero = new FileWriter("src/codigo_optimizado.lc");
        }catch (IOException e){
        }
    }

    private void cerrar_fichero(){
        try {
            fichero.close();
        }catch (IOException e){

        }
    }

    private void escribir(String codigo){
        try {
            /* Según el número de identaciones escribimos espacios*/
            for (int i = 0; i < indent; i++) fichero.write(' ');
            fichero.write(codigo);
        }catch (IOException e){
        }
    }

    private void escribir_sin_indentar(String codigo){
        try {
            fichero.write(codigo);
        }catch (IOException e){
        }
    }

    @Override
    public void exitEstado(Anasint.EstadoContext ctx) {
        String estado = ctx.IDENT().getText();
        List<Anasint.AsignacionContext> lista_asignaciones = generar_lista_asignaciones(ctx.asignaciones());
        mem_estados.put(estado, lista_asignaciones);
        super.exitEstado(ctx);
    }

    private List<Anasint.AsignacionContext> generar_lista_asignaciones(Anasint.AsignacionesContext ctx){
        List<Anasint.AsignacionContext> ls = new ArrayList<>();
        if(ctx.getChildCount()>0){
            ls.add(ctx.asignacion());
            ls.addAll(generar_lista_asignaciones(ctx.asignaciones()));
        }
        return ls;
    }

    @Override
    public void exitTransicion(Anasint.TransicionContext ctx) {
        String estado_inicial = ctx.IDENT(0).getText();
        String estado_siguiente = ctx.IDENT(1).getText();
        Anasint.CondicionContext cond = ctx.condicion();

        Map<String, Anasint.CondicionContext> mc;
        if(!mem_transiciones.keySet().contains(estado_inicial)){
            mc = new HashMap<>();
        }else{
            mc = mem_transiciones.get(estado_inicial);
        }
        mc.put(estado_siguiente, cond);
        mem_transiciones.put(estado_inicial, mc);
        super.exitTransicion(ctx);
    }

    @Override
    public void exitVariables(Anasint.VariablesContext ctx) {
        List<String> vars = genera_lista_vars(ctx.vars());
        declaraciones_iniciales.addAll(vars);
    }

    /* Cuando estamos haciendo una llamada recursiva a una función que está más abajo
    *
    * Pasamos como parámetro de contexto la función que está encima de ella*/
    private List<String> genera_lista_vars(Anasint.VarsContext ctx){
        List<String> ls = new ArrayList<>();
        if(ctx.getChildCount()>0){
            ls.add(ctx.IDENT().getText());
            ls.addAll(genera_lista_vars(ctx.vars()));
        }
        return ls;
    }

    @Override
    public void exitDiagrama(Anasint.DiagramaContext ctx) {
        abrir_fichero(nombre_fichero);
        // Generamos el código de inicio
        escribir("public class _Diagrama {"+ "\n");
        indent+=3;
        escribir("public static void main(String[] args) {\n");
        indent+=3;
        // Empezamos a generar las funcionalidades
        escribir("int ");
        List<String> ls = new ArrayList<>(declaraciones_iniciales);
        Integer tam_ls = ls.size();
        for(int i = 0; ls.size()>i; i++){

            escribir(ls.get(i)+"="+ "0");
            if(i == tam_ls-1){
                escribir(";");
            }else {
                escribir(",");
            }
        }
        escribir("boolean fin;");


        // Generamos el código de fin
        indent-=3;
        escribir("}\n");
        indent-=3;
        super.exitDiagrama(ctx);
    }
}
