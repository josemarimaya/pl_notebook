public class GeneradorCodigoCondicion extends AnasintBaseVisitor {
    @Override
    public Object visitDiagrama(Anasint.DiagramaContext ctx) {
        return super.visitDiagrama(ctx);
    }

    @Override
    public Object visitCondPar(Anasint.CondParContext ctx) {
        return "(" + visit(ctx.condicion())+")";
    }

    @Override
    public Object visitCondMayor(Anasint.CondMayorContext ctx) {
        return visit(ctx.term(0))+ ">" +visit(ctx.term(1));
        // Como hay dos términos distintos usando la indexión siendo i:0 term1 e i:1 term2
    }

    @Override
    public Object visitCondIgual(Anasint.CondIgualContext ctx) {
        return visit(ctx.term(0))+ "==" +visit(ctx.term(1));
    }

    @Override
    public Object visitCondY(Anasint.CondYContext ctx) {
        return super.visitCondY(ctx);
    }

    @Override
    public Object visitCondMenorIgual(Anasint.CondMenorIgualContext ctx) {
        return visit(ctx.term(0))+ "<=" +visit(ctx.term(1));
    }

    @Override
    public Object visitCondMayorIgual(Anasint.CondMayorIgualContext ctx) {
        return super.visitCondMayorIgual(ctx);
    }

    @Override
    public Object visitCondO(Anasint.CondOContext ctx) {
        return super.visitCondO(ctx);
    }

    @Override
    public Object visitCondNo(Anasint.CondNoContext ctx) {
        return super.visitCondNo(ctx);
    }

    @Override
    public Object visitCondDistinto(Anasint.CondDistintoContext ctx) {
        return super.visitCondDistinto(ctx);
    }

    @Override
    public Object visitCondMenor(Anasint.CondMenorContext ctx) {
        return super.visitCondMenor(ctx);
    }
}
