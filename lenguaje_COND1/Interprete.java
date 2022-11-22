import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.Collections;
import java.util.HashMap;

public class Interprete extends AnasintBaseVisitor<Integer>{

    Map<String, Integer> estado = new HashMap<String, Integer>();

    @Override
    public Integer visitPrograma(Anasint.ProgramaContext ctx) {
        return super.visitPrograma(ctx);
    }

    @Override
    public Integer visitVariables(Anasint.VariablesContext ctx) {
        return super.visitVariables(ctx);
    }

    public Integer visitLista_vars(Anasint.Lista_varsContext ctx){
        if(ctx.getChildCOunt()>i){
            String var = ctx.VAR().getText();
            estado.put(var,0);
            visitLista_vars(ctx.lista_vars());
        }else{
            String var = ctx.VAR().getText();
            estado.put(var,0);
        }
        return null;
    }

    @Override
    public Integer visitInstrucciones(Anasint.InstruccionesContext ctx) {
        if(ctx.getChildCount()>1){
            Integer cent = 1; // Si hay que interpretar y 0 si no
            visitLista_instrs(ctx.lista_instrs(), cent);
        }
        return null;
    }

    @Override
    public Integer visitLista_instrs(Anasint.Lista_instrsContext ctx, Integer cent) {
        if(ctx.getChildCount()>1){
            Integer cent2 = visitInstruccion(ctx.instruccion(), cent);
            visitLista_instrs(ctx.lista_instrs(), cent2);
        }else{
            Integer cent2 = visitInstruccion(ctx.instruccion(), cent);
        }
        return null;
    }

    @Override
    public Integer visitInstruccion(Anasint.InstruccionContext ctx, Integer cent) {
        Integer cent2 = 0;
        if(ctx.asignacion()!= null){
            visitAsignacion(ctx.asignacion(), cent);
            cent2 = cent;
        }else if(ctx.condicional() != null){
            visitCondicional(ctx.condicional(), cent);
            cent2 = cent;
        }else if(ctx.ruptura()!=null){
            cent = visitRuptura(ctx.ruptura(), cent);
        }else{
            visitImpresion(ctx.impresion(), cent);
            cent2 = cent;
        }
        return cent2;
    }

    @Override
    public Integer visitAsignacion(Anasint.AsignacionContext ctx, Integer cent) {
        return super.visitAsignacion(ctx);
    }

    @Override
    public Integer visitCondicional(Anasint.CondicionalContext ctx) {
        return super.visitCondicional(ctx);
    }

    @Override
    public Integer visitAlternativa(Anasint.AlternativaContext ctx) {
        return super.visitAlternativa(ctx);
    }

    @Override
    public Integer visitRuptura(Anasint.RupturaContext ctx, Integer cent) {
        return super.visitRuptura(ctx);
    }

    @Override
    public Integer visitImpresion(Anasint.ImpresionContext ctx) {
        return super.visitImpresion(ctx);
    }

    @Override
    public Integer visitVars(Anasint.VarsContext ctx) {
        return super.visitVars(ctx);
    }

    @Override
    public Integer visitMayor(Anasint.MayorContext ctx) {
        return super.visitMayor(ctx);
    }

    @Override
    public Integer visitMenor(Anasint.MenorContext ctx) {
        return super.visitMenor(ctx);
    }

    @Override
    public Integer visitIgual(Anasint.IgualContext ctx) {
        return super.visitIgual(ctx);
    }

    @Override
    public Integer visitDistinto(Anasint.DistintoContext ctx) {
        return super.visitDistinto(ctx);
    }

    @Override
    public Integer visitMayorIgual(Anasint.MayorIgualContext ctx) {
        return super.visitMayorIgual(ctx);
    }

    @Override
    public Integer visitMenorIgual(Anasint.MenorIgualContext ctx) {
        return super.visitMenorIgual(ctx);
    }

    @Override
    public Integer visitPor(Anasint.PorContext ctx) {
        return super.visitPor(ctx);
    }

    @Override
    public Integer visitParExp(Anasint.ParExpContext ctx) {
        return super.visitParExp(ctx);
    }

    @Override
    public Integer visitVar(Anasint.VarContext ctx) {
        return super.visitVar(ctx);
    }

    @Override
    public Integer visitMenos(Anasint.MenosContext ctx) {
        return super.visitMenos(ctx);
    }

    @Override
    public Integer visitNum(Anasint.NumContext ctx) {
        return super.visitNum(ctx);
    }

    @Override
    public Integer visitMas(Anasint.MasContext ctx) {
        return super.visitMas(ctx);
    }

    @Override
    public Integer visit(ParseTree tree) {
        return super.visit(tree);
    }

    @Override
    public Integer visitChildren(RuleNode node) {
        return super.visitChildren(node);
    }

    @Override
    public Integer visitTerminal(TerminalNode node) {
        return super.visitTerminal(node);
    }

    @Override
    public Integer visitErrorNode(ErrorNode node) {
        return super.visitErrorNode(node);
    }
}
