import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class OptimizadorClase extends  AnasintBaseVisitor<Interprete>{


    private String nombre_fichero;

    private FileWriter fichero;

    private int indent=0; // Para indentar el codigo

    // Para ponerle nombre al fichero
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

    //programa : {abrir_fichero(nombre_fichero); escribir("PROGRAMA\n");}
    //           PROGRAMA _,estado=variables instrucciones[estado,positivo] EOF ;
    //           {cerrar_fichero();};

    @Override
    public Interprete visitPrograma(Anasint.ProgramaContext ctx) {
        abrir_fichero(nombre_fichero);
        escribir("PROGRAMA\n");
        indent+=3; // Dado el ejemplo sabemos que tenemos que tabular, meter 3 espacios
        Interprete i = visitVariables(ctx.variables());
        // Visitamos instrucciones pasandole por parametro un estado de la clase interprete y el centinela a true
        visitInstrucciones(ctx.instrucciones(), i.getEstado(), true);
        cerrar_fichero();
        return null;
    }

    public Interprete visitVariables(Anasint.VariablesContext ctx) {
        //{escribir("VARIABLES\n");}
        escribir("VARIABLES\n"); indent+=3;
        //codigo,estado=lista_vars PUNTOYCOMA;
        // Hacemos la llamada  a i que nos devolverá el codigo ya que es una de las propiedades que hemos creado
        Interprete i = visitLista_vars(ctx.lista_vars());
        //{escribir(codigo); escribir(";\n")}
        escribir(i.getCodigo()+"\n");
        return super.visitVariables(ctx);
    }

    public Interprete visitLista_vars(Anasint.Lista_varsContext ctx) {
        Interprete info = new Interprete();
        if (ctx.getChildCount() == 3){
            //  { almacenar_variable_con_valor(estado,IDENT,0);
            //             codigo=concatenar IDENT COMA codigo2}
            // codigo2,estado=lista_vars
            // La tupla del diseño nos da el objeto interprete
            info = visitLista_vars(ctx.lista_vars());

            // Hacemos lo mismo que en el caso recursivo
            Set<Map<String, Integer>> estado = info.getEstado();

            for(Map<String, Integer> mapa: estado){
                mapa.put(ctx.IDENT().getText(), 0);
            }
            // Añadimos el codigo de la variable junto a la coma a las variables que ya están
            info.setCodigo(ctx.IDENT().getText() + ctx.COMA() + info.getCodigo());

        }else { // Caso base para el caso de IDENT

            Set<Map<String, Integer>> estado = info.getEstado();
            // Caso base que nos crea el nuevo constructor almacenando los mapas que hay en estado
            // inicializandolo a 0 cada uno
            for(Map<String, Integer> mapa: estado){
                mapa.put(ctx.IDENT().getText(), 0);
            }
            // String codigo ='IDENT'
            info.setCodigo(ctx.IDENT().getText());

        }


        return super.visitLista_vars(ctx);
    }


    public Interprete visitInstrucciones(Anasint.InstruccionesContext ctx,
                                         Set<Map<Integer, String>> estado, Boolean cent) {

        escribir("INSTRUCCIONES\n"); indent +=3;
        // Dado que el visit es opcional
        if(ctx.getChildCount() == 2){
            Interprete info = visitLista_instrs(ctx.lista_instrs(), estado,cent);
        }
        return null;
    }

    public Interprete visitLista_instrs(Anasint.Lista_instrsContext ctx,
                                        Set<Map<Integer, String>> estado, Boolean cent) {
        Interprete i = new Interprete();
        if(ctx.getChildCount() == 1){
            i = visitInstruccion(ctx.instruccion(), estado, cent);
        } else if (ctx.getChildCount() > 1) {
            Interprete intermedio = visitInstruccion(ctx.instruccion(), estado, cent);
            i = visitLista_instrs(ctx.lista_instrs(), intermedio.getEstado(), cent);
        }
        return i;
    }


    public Interprete visitInstruccion(Anasint.InstruccionContext ctx,
                                       Set<Map<Integer, String>> estado, Boolean cent) {
        Interprete i = new Interprete();

        if(ctx.asignacion() != null){ // Cuando nuestro contexto es asignacion
            i = visitAsignacion(ctx.asignacion(), estado, cent);
        } else if (ctx.condicional() != null) {
            i = visitCondicional(ctx.condicional(), estado, cent);
        } else if (ctx.leer() != null) {
            i = visitLeer(ctx.leer(), estado, cent);
        }

        return i;
    }


    public Interprete visitAsignacion(Anasint.AsignacionContext ctx,
                                      Set<Map<Integer, String>> estado, Boolean cent) {

        Interprete i = new Interprete();
        i.setEstado(estado);
        if(cent == true){
            Integer v = evaluadorExpresion.visitExpr(ctx.expr());

            Set<Map<String, Integer>> estado = i.getEstado();
            for(Map<String,Integer> dic: estado){
                dic.put(ctx.IDENT().getText(),v);
            }

            escribir(ctx.IDENT().getText());
            escribir_sin_indentar(" = ");
            escribir_sin_indentar(ctx.expr().getText());
            escribir_sin_indentar(";\n");
        }
        return super.visitAsignacion(ctx);
    }
}
