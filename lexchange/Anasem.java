import java.util.HashMap;
import java.util.Map;

public class Anasem extends AnasintBaseVisitor<Integer>{

    ////decision 1 (global) nombre_tabla: valor_tabla
    //entrada : esquema_fuente datos_fuente esquema_destino restricciones EOF;
    //esquema_fuente : ESQUEMA FUENTE (signatura)+ ;

    public Map<String, Integer> memoria = new HashMap<>();

    ////decisión 2
    //signatura: IDENT PA num=atributos PC; {Almacenar en memoria Ident como clave y guardar el numero de atributos}

    @Override
    public Integer visitSignatura(Anasint.SignaturaContext ctx) {
        String nombre_tabla = ctx.IDENT().getText();
        Integer num_argumentos = visitAtributos(ctx.atributos());
        memoria.put(nombre_tabla,num_argumentos);
        return null;
    }

    //atributos dev num: IDENT COMA num2=atributos {num=1+num2} | IDENT {num=1};
    @Override
    public Integer visitAtributos(Anasint.AtributosContext ctx) {
       if(ctx.getChildCount()>1){ // caso recursivo
           return 1 + visit(ctx.atributos());
       }else{
           return 1; // caso base
       }
    }

    @Override
    public Integer visitTupla(Anasint.TuplaContext ctx) {
        String nombre_tabla = ctx.IDENT().getText();
        Integer num1 = memoria.get(nombre_tabla);
        Integer num2 = visit(ctx.terminos());

        if(num1==null){
            System.out.println("Error tabla no declarada"
                    + nombre_tabla + "Linea: " + ctx.IDENT().getSymbol().getLine());
        }else if (num1!=num2){
            System.out.println("Numero de atributos incorrecto");
        } else {
            System.out.println("Declaración correcta");
        }
        return null;
    }

    @Override
    public Integer visitTerminos(Anasint.TerminosContext ctx) {
        if(ctx.getChildCount()>1){ // caso recursivo
            return 1 + visit(ctx.terminos());
        }else{
            return 1; // caso base
        }
    }
}
