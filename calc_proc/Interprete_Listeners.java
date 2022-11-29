public class Interprete_Listeners extends AnasintBaseListener{

    Interprete evaluador = new Interprete();

    public void exitOrdenExpr(Anasint.OrdenExprContext ctx){
        System.out.println("Expresion: " + ctx.expresion().getText()
            + "vale " + evaluador.visit(ctx.expresion(), false,
                null, null));
    }

    public void exitAsignacion(Anasint.AsignacionContext ctx){
        Integer r = evaluador.visit(ctx.expresion(), false
        , null, null);

        evaluador.actualizar_variables(ctx.IDENT().getText(), r);

        System.out.println("Asinación: " + ctx.IDENT().getText() + " vale "
            + r);
    }

    public void exitFuncion(Anasint.FuncionContext ctx){
        evaluador.actualizar_funciones(ctx.IDENT(0).getText(), ctx.IDENT(1).getText(), ctx.expresion());
        System.out.println("Función: " + ctx.IDENT(0) + ctx.PARENTESISABIERTO().getText()
        + ctx.IDENT(1) + ctx.PARENTESISCERRADO() + " vale " + ctx.expresion().getText());
    }


}
