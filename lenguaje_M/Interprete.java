public class Interprete extends AnasintBaseVisitor<String>{

    public static Object visitExpresion2(Anasint.Expresion2Context ctx, String t){

        Object e = null;

        // Podemos implementar etiquetas en la gramatica atribuida para únicamente implementar cada visit en el nodo
        if (ctx.getChild().size() == 3) {

            e = visitExpresion(ctx.expresion(), t);

        }else if (ctx.getChild()==2) { // CASO NEGACION

            if(t.equals("bool")){
                Object aux = visitExpresion(ctx.expresion(), t);
                e= !aux;
            }else{
                e = "indefinido";
            }
            String aux = visitExpresion(ctx.expresion(), t);


        }else if(ctx.FALSO() != null){
            if(t.equals("bool")){
                e = false;
            }else if(t.equals("nat")){
                e = 0;
            }else{
                e = "indefinido";
            }
        }else if(ctx.CIERTO() != null){

            if(t.equals("bool")){
                e = true;
            }else if(t.equals("nat")){
                e = 1;
            }else{
                e = "indefinido";
            }

        } else if (ctx.NUMERO() != null) {

            if(t.equals("nat")){
                Integer i = Integer.parseInt(ctx.NUMERO().getText());
                e = i;
            }

        }

        return e;

    }
}
