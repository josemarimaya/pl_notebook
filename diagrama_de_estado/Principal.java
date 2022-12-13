import org.antlr.v4.gui.TreeViewer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import javax.swing.*;
import java.util.Arrays;

public class Principal {
    private static void mostrar_arbol_analisis(Anasint anasint, ParseTree arbol){
        JFrame frame = new JFrame("Antlr ParseTree (diagrama de estados)");
        TreeViewer v = new TreeViewer(Arrays.asList(anasint.getRuleNames()),arbol);
        JScrollPane panel = new JScrollPane(v);
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(1000,300);
        frame.setVisible(true);

    }

    public static void main(String[] args) throws Exception{
        CharStream f = CharStreams.fromFileName("diagrama1.txt");
        Analex analex = new Analex(f);
        CommonTokenStream ts = new CommonTokenStream(analex);
        Anasint anasint = new Anasint(ts);
        ParseTree arbol = anasint.diagrama();

        mostrar_arbol_analisis(anasint,arbol);

        ParseTreeWalker walker = new ParseTreeWalker();

        //Compilador compilador = new Compilador();
        //walker.walk(compilador,arbol);
    }
}



