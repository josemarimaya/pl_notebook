public class Anasem extends AnasintBaseVisitor<Boolean>{

    /* Practicamente usaremos literalmente todo lo que hay en la gramatica atribuida
    *  usando los visits en los puntos que así nos lo indican*/

    @Override
    public Boolean visitPrograma(Anasint.ProgramaContext ctx) {
        return visitInstrucciones(ctx.instrucciones(), false);
    }

    public Boolean visitInstrucciones(Anasint.InstruccionesContext ctx, Boolean cent) {
        Boolean r = null;
        if(ctx.getChildCount() < 2){
            r = visitInstruccion(ctx.instruccion(), cent);
        } else {
            Boolean cent_aux = visitInstruccion(ctx.instruccion(), cent);
            r = visitInstrucciones(ctx.instrucciones(), cent_aux);
        }
        return null;
    }


    public Boolean visitInstruccion(Anasint.InstruccionContext ctx, Boolean cent) {
        Boolean r = null;
        /* Visitamos cada tipo de instruccion que se puede dar viendo su tipo
        *  en el contexto*/
        switch(ctx.start.getType()){
            case Anasint.ASIG -> r = visitAsignacion(ctx.asignacion(), cent);
            case Anasint.WHILE -> r = visitIteracion(ctx.iteracion(), cent);
            case Anasint.IF -> r = visitSeleccion(ctx.seleccion(), cent);
            case Anasint.BREAK -> r = visitRuptura(ctx.ruptura(),cent);
        }

        return r;
    }

    public Boolean visitAsignacion(Anasint.AsignacionContext ctx, Boolean cent)  {
        return cent;
    }


    public Boolean visitIteracion(Anasint.IteracionContext ctx, Boolean cent_ext) {
        Boolean cent_int = cent_ext;
        visitBloque(ctx.bloque(), cent_int);
        Boolean r = cent_ext;
        return r;
    }

    public Boolean visitSeleccion(Anasint.SeleccionContext ctx, Boolean cent_ext) {
        Boolean cent_int = cent_ext;
        Boolean r = cent_ext;
        return r;
    }


    public Boolean visitRuptura(Anasint.RupturaContext ctx, Boolean cent) {
        Boolean r = true;
        if(cent == true){
            System.out.println("Bucle inalcanzable en la línea: " +
                    ctx.start.getLine());
        }
        return r;
    }

    
    public Boolean visitBloque(Anasint.BloqueContext ctx, Boolean cent) {
        Boolean r = null;
        // Es decir, que al no tener 3 hijos no es la segunda opcion de bloque
        if(ctx.getChildCount() < 3){
            r = visitInstruccion(ctx.instruccion(),cent);
        }else{
            visitInstrucciones(ctx.instrucciones(),cent);
        }
        return super.visitBloque(ctx);
    }
}
