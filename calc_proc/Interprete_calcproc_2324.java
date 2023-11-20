import java.util.HashMap;
import java.util.Map;

public class interprete_calcproc extends AnasintBaseVisitor<Integer>{

    // DECISIÓN 1

    Map<String, Integer> mem_var = new HashMap<>();
    Map<String, Map<String, String>> mem_func = new HashMap<>();

    Boolean cent = null;

    @Override
    public Integer visitAsignacion(Anasint.AsignacionContext ctx) {
        String var = ctx.ASIG().getText();
        Integer valor = visitExpr(ctx.expresion(), cent, var, mem_var.get(var));
        return super.visitAsignacion(ctx);
    }

    @Override
    public Integer visitFuncion(Anasint.FuncionContext ctx) {
        return super.visitFuncion(ctx);
    }

    public Integer visitExpr(Anasint.ExpresionContext ctx, Boolean cent,
                             String parametro, Integer valor){

        Integer res = 0;

        if (ctx instanceof Anasint.LlamadaExprContext){
            visitLlamadaExpr((Anasint.LlamadaExprContext) ctx, cent, parametro, valor);
            // Igual para las demás etiquetas que hay en expr
        } else if () {

        }

        return  res;

    }


    public Integer visitParExpr(Anasint.ParExprContext ctx, Boolean cent,
                                String parametro, Integer valor) {
        return super.visitParExpr(ctx);
    }

    @Override
    public Integer visitUnarioMenosExpr(Anasint.UnarioMenosExprContext ctx, Boolean cent,
                                        String parametro, Integer valor) {
        return super.visitUnarioMenosExpr(ctx);
    }


    public Integer visitOrdenExpr(Anasint.OrdenExprContext ctx, Boolean cent,
                                  String parametro, Integer valor) {
        return super.visitOrdenExpr(ctx);
    }



    public Integer visitIdentExpr(Anasint.IdentExprContext ctx, Boolean cent, String parametro, Integer valor) {
        return super.visitIdentExpr(ctx);
    }

    public Integer visitNumExpr(Anasint.NumExprContext ctx, Boolean cent,
                                String parametro, Integer valor) {
        return super.visitNumExpr(ctx);
    }


    public Integer visitMenosExpr(Anasint.MenosExprContext ctx, Boolean cent,
                                  String parametro, Integer valor) {
        return super.visitMenosExpr(ctx);
    }


    public Integer visitPorExpr(Anasint.PorExprContext ctx, Boolean cent,
                                String parametro, Integer valor) {
        return super.visitPorExpr(ctx);
    }

    public Integer visitLlamadaExpr(Anasint.LlamadaExprContext ctx, Boolean cent,
                                    String parametro, Integer valor) {
        return super.visitLlamadaExpr(ctx);
    }

    public Integer visitMasExpr(Anasint.MasExprContext ctx, Boolean cent,
                                String parametro, Integer valor) {
        return super.visitMasExpr(ctx);
    }
}
