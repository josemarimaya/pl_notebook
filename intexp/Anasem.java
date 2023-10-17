public class Anasem extends AnasintBaseListener{

    // DECISION 2

    // Detectar asignaciones ambiguas. Cada variable declarada inicialmente tiene que tener un centinela indicando
    //que es ambigua

    int contador_instrucciones = 0;
    VerificarExpresionesAmbiguas verificador = new VerificarExpresionesAmbiguas();

    //IDENT COMA variables #Ids {almacenar IDENT en memoria con ambigua}
    @Override
    public void exitIds(Anasint.IdsContext ctx) {
        verificador.memoria.put(ctx.IDENT().getText(), true);
    }

    // | IDENT #Id {almacenar IDENT en memoria con ambigua}

    @Override
    public void exitId(Anasint.IdContext ctx) {
        verificador.memoria.put(ctx.IDENT().getText(), true);
    }

    //- Una vez calculada la ambiguedad de la expresion (decision 1) si la expresion no es ambigua entonces actualizar
    //la memoria con el centinela asociada a la expresion. En el caso que la asociacion si sea ambigua, escribir mensaje por pantalla


    @Override
    public void exitAsignacion(Anasint.AsignacionContext ctx) {
        contador_instrucciones++;
        String instr = ctx.IDENT().getText() + " " + ctx.ASIG().getText() + " " + ctx.expresion().getText();
        Boolean cent = verificador.visit(ctx.expresion());
        verificador.memoria.put(ctx.IDENT().getText(), cent);

        if (cent){
            System.out.println("Asignacion " + instr + " es ambigua (instrución: " + contador_instrucciones);
        }
    }
}
