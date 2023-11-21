import java.util.HashMap;
import java.util.Map;

public class interprete_calcproc extends AnasintBaseVisitor<Integer>{

    // DECISIÓN 1
    Map<String, Integer> mem_var = new HashMap<>();
    Map<String, Map<String, Integer>> mem_func = new HashMap<>();
    Boolean cent = null;

    @Override
    public Integer visitAsignacion(Anasint.AsignacionContext ctx) {
        String var = ctx.IDENT().getText();
        Integer valor = visitExpr(ctx.expresion(), cent, var);
        mem_var.put(var, valor);
        return null;
    }

    @Override
    public Integer visitFuncion(Anasint.FuncionContext ctx) {
        String var = ctx.IDENT(0).getText();
        String param = ctx.IDENT(1).getText();
        Integer exp = visitExpr(ctx.expresion(), cent, param, valor);
        Map<String, Integer> aux = new HashMap<>();
        aux.put(param, exp);
        mem_func.put(var, aux);
        return null;
    }

    public Integer visitExpr(Anasint.ExpresionContext ctx, Boolean cent,
                             String parametro, Integer valor){

        Integer res = 0;

        if (ctx instanceof Anasint.LlamadaExprContext){
            visitLlamadaExpr((Anasint.LlamadaExprContext) ctx, cent, parametro, valor);
            // Igual para las demás etiquetas que hay en expr
        } else if (ctx instanceof Anasint.ParExprContext) {
            visitParExpr((Anasint.ParExprContext) ctx, cent, parametro, valor);
        } else if (ctx instanceof Anasint.NumExprContext) {
            visitParExpr((Anasint.NumExprContext) ctx, cent, parametro, valor);
        }else if (ctx instanceof Anasint.IdentExprContext) {
            visitParExpr((Anasint.IdentExprContext) ctx, cent, parametro, valor);
        }else if (ctx instanceof Anasint.ParExprContext) {
            visitParExpr((Anasint.ParExprContext) ctx, cent, parametro, valor);
        }else if (ctx instanceof Anasint.ParExprContext) {
            visitParExpr((Anasint.ParExprContext) ctx, cent, parametro, valor);
        }

        return  res;

    }


    public Integer visitParExpr(Anasint.ParExprContext ctx, Boolean cent,
                                String parametro) {
        return super.visitParExpr(ctx);
    }

    @Override
    public Integer visitUnarioMenosExpr(Anasint.UnarioMenosExprContext ctx, Boolean cent,
                                        String parametro) {
        return - Integer.parseInt(ctx.expresion().getText());
    }


    public Integer visitOrdenExpr(Anasint.OrdenExprContext ctx, Boolean cent,
                                  String parametro) {
        return super.visitOrdenExpr(ctx);
    }



    public Integer visitIdentExpr(Anasint.IdentExprContext ctx, Boolean cent, String parametro, Integer valor) {
        return super.visitIdentExpr(ctx);
    }

    public Integer visitNumExpr(Anasint.NumExprContext ctx, Boolean cent,
                                String parametro) {
        String num = ctx.NUMERO().getText();
        Integer res = Integer.parseInt(num);
        return res;
    }


    public Integer visitMasExpr(Anasint.MasExprContext ctx, Boolean cent,
                                String parametro) {
        String v1 = ctx.expresion(0).getText();
        String v2 = ctx.expresion(1).getText();
        Integer v1_n = Integer.parseInt(v1);
        Integer v2_n = Integer.parseInt(v2);
        return v1_n+v2_n;
    }


    public Integer visitMenosExpr(Anasint.MenosExprContext ctx, Boolean cent,
                                  String parametro) {
        String v1 = ctx.expresion(0).getText();
        String v2 = ctx.expresion(1).getText();
        Integer v1_n = Integer.parseInt(v1);
        Integer v2_n = Integer.parseInt(v2);
        return v1_n-v2_n;
    }


    public Integer visitPorExpr(Anasint.PorExprContext ctx, Boolean cent,
                                String parametro, Integer valor) {
        String v1 = ctx.expresion(0).getText();
        String v2 = ctx.expresion(1).getText();
        Integer v1_n = Integer.parseInt(v1);
        Integer v2_n = Integer.parseInt(v2);
        return v1_n*v2_n;
    }

    public Integer visitLlamadaExpr(Anasint.LlamadaExprContext ctx, Boolean cent,
                                    String parametro, Integer valor) {
        return visitExpr(ctx.expresion(), cent, parametro, valor);
    }

}
