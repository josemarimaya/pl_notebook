public class Anasem extends AnasintBaseVisitor<Boolean>{

    Boolean cent = false;

    @Override
    public Boolean visitRuptura(Anasint.RupturaContext ctx) {
        if(cent){
            System.out.println("Bucle inalcanzable");
        }else{
            cent = true;
        }
        return cent;
    }

    @Override
    public Boolean visitBloque(Anasint.BloqueContext ctx) {

        if (cent) {
            cent = false;
        }
        return cent;
    }
}
