import java.util.HashMap;
import java.util.Map;

public class VerificarExpresionesAmbiguas extends AnasintBaseVisitor<Boolean>{

    // Code > Overwrite Methods > Seleccionar metodos

    // Memoria

    public Map<String, Boolean> memoria = new HashMap<>();

    // Decisión 1: Analizar expresiones ambiguas

    // | NUMERO {centinela = no ambigua}#Num
    @Override
    public Boolean visitNum(Anasint.NumContext ctx) {
       return false; // Asociamos false a no ambiguo
    }

    //  | IDENT {consultar en memoria si la variable es ambigua o no} #Var
    @Override
    public Boolean visitVar(Anasint.VarContext ctx) {
        return memoria.get(ctx.IDENT().getText());
    }
    // | PARENTESISABIERTO expresion PARENTESISCERRADO #Par
    @Override
    public Boolean visitPar(Anasint.ParContext ctx) {
        return visit(ctx.expresion());
    }
    // | IDENT {centinela = no ambigua, ya que según el problema es una variable preasignada con valor 0}
    //    PARENTESISABIERTO PARENTESISCERRADO #VarNoAmb
    @Override
    public Boolean visitVarNoAmb(Anasint.VarNoAmbContext ctx) {
        return false;
    }

    //|  cent1=expresion (POR|DIV|MAS|MENOS) cent2=expresion #OpBin
    // {si cent1 o cent2 es ambigua entonces centinela es ambigua sino centinela es no ambigua}

    @Override
    public Boolean visitOpBin(Anasint.OpBinContext ctx) {
        Boolean cent1 = visit(ctx.expresion(0));
        Boolean cent2 = visit(ctx.expresion(1));
        return cent1 || cent2; // Si alguno es ambiguo todo será ambiguo
    }
}
