import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Compilador extends AnasintBaseListener {

    GeneradorCodigoCondicion g = new GeneradorCodigoCondicion(); //Generador código condición
    boolean debug = true; //centinela para generar código de depuración

    //(1)	Inicializar memorias
    // cada estado se asocia a sus asignaciones
    Map<String, List<Anasint.AsignacionContext>> memoria_estados = new HashMap<>();

    // cada estado se asocia a sus transiciones. La transición contiene el estado destino y la condición de transición.
    Map<String, Map<String, Anasint.CondicionContext>> memoria_transiciones = new HashMap<>();

    // estado inicial
    String inicial;
    // variables usadas en el diagrama
    Set<String> variables = new HashSet<String>();

    //(2)	Almacenar estado en la memoria de estados
    // Función auxiliar para extraer cada asignación del árbol asignaciones
    List<Anasint.AsignacionContext> lista_asignaciones(Anasint.AsignacionesContext a) {
        /*AsignacionContext nos da un contexto de uso para las asignaciones
        sin él no podríamos hacer las asignaciones*/
        List<Anasint.AsignacionContext> r = new LinkedList<>();
        // Comprobar si los hijos no son null los vamos añadiendo a su asignación
        if (a.children!=null) {
            r.add(a.asignacion());
            r.addAll(lista_asignaciones(a.asignaciones()));
        }
        return r;
    }


    void almacenar_estado_memoria_estados(String estado,
                                          Anasint.AsignacionesContext asignaciones) {
        List<Anasint.AsignacionContext> r = lista_asignaciones(asignaciones);
        memoria_estados.put(estado, r);
    }

    //(3)	Almacenar transición en la memoria de transiciones
    // Método para almacenar cada transición en la memoria de transiciones
    void almacenar_transicion_memoria_transiciones(String estado_inicial, // Origen
                                                   String estado_final, // Destino
                                                   Anasint.CondicionContext condicion) {
        Map<String, Anasint.CondicionContext> trans;
        if (!memoria_transiciones.keySet().contains(estado_inicial))
            trans = new HashMap<String, Anasint.CondicionContext>();
        else
            /* Si no existe en la memoria la transicion se mete en la memoria*/
            trans = memoria_transiciones.get(estado_inicial);
        trans.put(estado_final, condicion);
        memoria_transiciones.put(estado_inicial, trans);
    }

    //(4)	Almacenar variables
    // Función auxiliar para extraer cada variable del árbol vars
    List<String> lista_variables(Anasint.VarsContext v) {
        List<String> r = new LinkedList<>();
        if (v.children == null)
            return r;
        else{
            r.add(v.IDENT().getText());
            r.addAll(lista_variables(v.vars()));
        }
        return r;
    }

    void almacenar_variables(Anasint.VarsContext vars) {

        variables.addAll(lista_variables(vars));
    }

    //(5)	Almacenar estado inicial
    void almacenar_estado_inicial(String estado) {

        inicial = estado;
    }

    FileWriter fichero;  //Fichero de salida
    int indent = 0;      //Indentación (número de espacios)

    /* Son necesarios tanto el método de apertura como el de cierre
    * porque sino da error el código*/
    void abrir_fichero() {
        try {
            fichero = new FileWriter("src//_Diagrama.java");
        } catch (IOException e) {
        }
    }

    void cerrar_fichero() {
        try {
            fichero.close();
        } catch (IOException e) {
        }
    }

    //Escribir código
    void escribir(String codigo) {
        try {
            for (int i = 0; i < indent; i++) fichero.write(' ');
            fichero.write(codigo);
        } catch (IOException e) {
        }
    }

    void escribir_sin_indentar(String codigo) {
        try {
            fichero.write(codigo);
        } catch (IOException e) {
        }
    }

    //(6)	Generar código comienzo clase y main

    // Método para crear la clase de "GeneradorCodigoCondicion"
    void generar_codigo_comienzo_clase_y_main() {
        // Línea en la que se escribe
        indent = 0;
        // La cabecera
        escribir("public class _Diagrama {\n");
        // La siguiente línea que queremos escribir es la 3
        indent += 3;
        escribir("public static void main(String[] args) {\n");
        // Y tras meter el main vamos 3 líneas más abajo
        indent += 3;
    }

    //(7)	Generar código declaración de variables en el diagrama de estados
    //      y código del centinela.
    void generar_codigo_declaracion_variables() {
        if (variables.size() > 0) {
            int cont = 0;
            escribir("int ");
            for (String v : variables) {
                cont++;
                // Inicializamos las variables directamente
                escribir_sin_indentar(v + "=0");
                if (variables.size() > cont) escribir_sin_indentar(", ");
            }
            escribir_sin_indentar(";\n");
        }
        escribir("boolean fin=false;\n");
    }


//    (8)	Generar código diagrama (incluidos generar códigos estado y condición)

    //    generar_codigo_diagrama_desde(estado){
//        escribir_codigo_estado(estado)
//        si no hay ninguna transición desde(estado) entonces
//           escribir(fin=true;)
//        sino
//           escribir(fin=false;)
//           para cada transición a un estado_destino desde estado hacer
//              escribir(if (fin==false && condición de la transición){)
//              generar_codigo_diagrama_desde(estado_destino)
//              escribir(})
//           finpara
//        finsi
//    }
    void generar_codigo_diagrama_desde(String estado) {
        escribir_estado(estado);
        if (!memoria_transiciones.keySet().contains(estado))
            escribir("fin=true;\n");
        else  {
            escribir("fin=false;\n");
            for(String estado_destino:memoria_transiciones.get(estado).keySet()){
                escribir("if (fin==false && ");
                escribir_sin_indentar(generar_codigo_condicion(memoria_transiciones.get(estado).get(estado_destino)));
                escribir_sin_indentar("){\n"); indent+=3;
                generar_codigo_diagrama_desde(estado_destino); indent-=3;
                escribir("}\n");
            }
        }
    }


    //Generar código para estado
    void escribir_estado(String estado) {
        escribir("//Estado: " + estado + "\n");
        List<Anasint.AsignacionContext> asignaciones = memoria_estados.get(estado);
        for (Anasint.AsignacionContext asignacion : asignaciones)
            generar_codigo_asignacion(asignacion);
        if (debug) generar_codigo_mostrar_estado(estado);
    }

    //Generar código para cada asignación en un estado
    void generar_codigo_asignacion(Anasint.AsignacionContext asignacion) {
        escribir(asignacion.IDENT().getText());
        escribir_sin_indentar(asignacion.ASIG().getText());
        escribir_sin_indentar(asignacion.term().getText());
        escribir_sin_indentar(";\n");
    }

    //Generar código de depuración para mostrar el estado
    void generar_codigo_mostrar_estado(String estado) {
        escribir("System.out.println(\"Estado: " + estado + "\");\n");
        generar_codigo_evaluar_variables();
    }

    //Generar código de depuración para mostrar el estado
    void generar_codigo_evaluar_variables() {
        // Bucle para escribir un syso para cada una de las variables para que se impriman por pantalla
        for (String v : variables)
            escribir("System.out.println(" + "\"   " + v + "\"+" + "\"" + " = " + "\"+" + v + ");\n");
    }

    String generar_codigo_condicion(Anasint.CondicionContext condicion) {
        return g.visit(condicion);
    }

    //(10)	Generar código finalización main y clase
    void generar_codigo_finalizacion_main_y_clase() {
        indent -= 3;
        escribir("}\n");
        indent -= 3;
        escribir("}");
    }

    /////////////////////
    // REGLAS
    ////////////////////
    public void exitVariables(Anasint.VariablesContext ctx) {

        almacenar_variables(ctx.vars());
    }

    public void exitEstado(Anasint.EstadoContext ctx) {
        almacenar_estado_memoria_estados(ctx.IDENT().getText(),
                ctx.asignaciones());
    }

    public void exitTransicion(Anasint.TransicionContext ctx) {
        almacenar_transicion_memoria_transiciones(ctx.IDENT(0).getText(),
                ctx.IDENT(1).getText(), ctx.condicion());
    }

    public void exitInicial(Anasint.InicialContext ctx){
        almacenar_estado_inicial(ctx.IDENT().getText());
    }

    public void exitDiagrama(Anasint.DiagramaContext ctx){
        abrir_fichero();
        generar_codigo_comienzo_clase_y_main();
        generar_codigo_declaracion_variables();
        generar_codigo_diagrama_desde(inicial);
        generar_codigo_finalizacion_main_y_clase();
        cerrar_fichero();
    }
}