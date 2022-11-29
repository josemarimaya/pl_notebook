import java.util.HashMap;
import java.util.Map;

public class Interprete extends AnasintBaseVisitor<Integer>{

    Map<String, Integer> variables = new HashMap<>();
    Map<String, String> parametros = new HashMap<>();
    Map<String, Anasint.ExpresionContext> cuerpos = new HashMap<>();

    public void actualizar_variables(String variable, Integer valor){
        variables.put(variable, valor);
    }

    public void actualizar_funciones(String nombre, String parametro,
                                     Anasint.ExpresionContext cuerpo){
        parametros.put(nombre, parametro);
        cuerpos.put(nombre, cuerpo);
    }
    public Integer visitOrden(Anasint.OrdenContext ctx){
        return null;
    }

    public Integer visitAsignacion(Anasint.AsignacionContext ctx){
        return null;
    }

    public Integer visitFuncion(Anasint.FuncionContext ctx){
        return null;
    }

    public Integer visit(Anasint.ExpresionContext ctx, Boolean centinela,
                                  String parametro, Integer valor){
        Integer res = null;
        if(ctx instanceof Anasint.LlamadaExprContext)
            return visitLlamadaExpr((Anasint.LlamadaExprContext)ctx, centinela, parametro,valor);
        if(ctx instanceof Anasint.MasExprContext)
            return  visitMasExpr((Anasint.MasExprContext)ctx, centinela, parametro, valor);
        if(ctx instanceof Anasint.PorExprContext)
            return visitPorExpr((Anasint.PorExprContext)ctx, centinela, parametro,valor);
        if(ctx instanceof Anasint.MenosExprContext)
            return visitMenosExpr((Anasint.MenosExprContext)ctx, centinela, parametro,valor);
        if(ctx instanceof Anasint.NumExprContext)
            return visitNumExpr((Anasint.NumExprContext)ctx,centinela, parametro, valor);
        if(ctx instanceof Anasint.UnarioMenosExprContext)
            return visitUnarioMenosExpr((Anasint.UnarioMenosExprContext)ctx, centinela, parametro,valor);
        if(ctx instanceof Anasint.IdentExprContext)
            return visitIdentExpr((Anasint.IdentExprContext)ctx, centinela, parametro, valor);
        if(ctx instanceof Anasint.ParExprContext)
            return visitParExpr((Anasint.ParExprContext)ctx, centinela,parametro,valor);


        return r;
    }

    // Una función por tipo de expresión a visitar

    // ExprPar
    public Integer visitPar(Anasint.ParExprContext ctx, Boolean centinela,
                            String parametro, Integer valor){
        return null;
    }

    public Integer visitIdentExpr(Anasint.IdentExprContext ctx, Boolean centinela,
                                  String parametro, Integer valor) {
       if(centinela){
           if(ctx.IDENT().getText().equals(parametro)){
               return valor;
           }else{
               return variables.get(ctx.IDENT().getText());
           }
       }else{
           return variables.get(ctx.IDENT().getText());
       }

    }

    public Integer visitNumExpr(Anasint.NumExprContext ctx, Boolean centinela,
                                String parametro, Integer valor) {
        return Integer.valueOf(ctx.NUMERO().getText());
    }

    public Integer visitParExpr(Anasint.ParExprContext ctx, Boolean centinela,
                                String parametro, Integer valor) {
        return visit(ctx.expresion(), centinela, parametro, valor);
    }

    @Override
    public Integer visitMenosExpr(Anasint.MenosExprContext ctx, Boolean centinela,
                                  String parametro, Integer valor) {
        Integer i = visit(ctx.expresion(0), centinela, parametro, valor);
        Integer j = visit(ctx.expresion(1), centinela, parametro, valor);
        return i-j;
    }

    @Override
    public Integer visitPrograma(Anasint.ProgramaContext ctx, Boolean centinela,
                                 String parametro, Integer valor) {
        return super.visitPrograma(ctx);
    }

    @Override
    public Integer visitUnarioMenosExpr(Anasint.UnarioMenosExprContext ctx, Boolean centinela,
                                        String parametro, Integer valor) {
        Integer i = visit(ctx.expresion(0), centinela, parametro, valor);
        return -1*i;
    }

    @Override
    public Integer visitPorExpr(Anasint.PorExprContext ctx, Boolean centinela,
                                String parametro, Integer valor) {
        Integer i = visit(ctx.expresion(0), centinela, parametro, valor);
        Integer j = visit(ctx.expresion(1), centinela, parametro, valor);
        return i*j;
    }

    @Override
    public Integer visitLlamadaExpr(Anasint.LlamadaExprContext ctx, Boolean centinela,
                                    String parametro, Integer valor) {
        Integer i = visit(ctx.expresion(), centinela, parametro, valor);
        return visit(cuerpos.get(ctx.IDENT().getText()), true,
                parametros.get(ctx.IDENT().getText()),i);
    }

    @Override
    public Integer visitMasExpr(Anasint.MasExprContext ctx, Boolean centinela,
                                String parametro, Integer valor) {
        Integer i = visit(ctx.expresion(0), centinela, parametro, valor);
        Integer j = visit(ctx.expresion(1), centinela, parametro, valor);
        return i+j;;
    }
}
