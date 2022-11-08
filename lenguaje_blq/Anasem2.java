import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class Anasem2 extends AnasintBaseVisitor<Set<String>>{

    //(global) relacion_de_inclusion_entre_bloques = {} // Usamos una pila para hacer la relación de los bloques
    private Stack<Set<String>> relacion_inclusion_entre_bloques;

    /*    bloque dev ins, b: BLOQUE memoria = variables
        {apilar memoria en relacion_de_inclusion_entre_bloques}
        instrucciones = ins FBLOQUE ;
        {desapilar de relacion_de_inclusion_entre_bloques}
     */
    public Set<String> visitBloque(Anasint.BloqueContext ctx){

        Set<String> memoria = visitVariables(ctx.variables);
        relacion_inclusion_entre_bloques.push(memoria);
        visitInstrucciones(ctx.instrucciones());
        relacion_inclusion_entre_bloques.pop();
        return null;

    }
    public Set<String> visitPrograma(Anasint.ProgramaContext ctx){
        relacion_inclusion_entre_bloques = new Stack<Set<String>>();
        visitBloque(ctx.bloque());
        return null;
    }

    public Set<String> visitVariables(Anasint.VariablesContext ctx){
        Set<String> memoria = new HashSet<>();
        if(!relacion_inclusion_entre_bloques.empty()){
            memoria.addAll(relacion_inclusion_entre_bloques.peek());
        }

    }

    public Set<String> visitVars(Anasint.VarsContext ctx){
        Set<String> memoria = new HashSet<>();
        if(ctx.getChildCount() == 1){
            memoria.add(ctx.VAR().getText());
        }else{
            Set<String>
        }
    }

    public Set<String> visitAsignacion(Anasint.AsignacionContext ctx){
        Set<String> variables_no_declaradas = visitExpr(ctx.expr());

    }
}
