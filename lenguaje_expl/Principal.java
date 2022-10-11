import org.antlr.v4.gui.TreeViewer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Arrays;


public class Principal {

	private static void mostrar_arbol_analisis(Anasint anasint,ParseTree arbol){
		JFrame frame = new JFrame("Antlr ParseTree (M)");
		TreeViewer v = new TreeViewer(Arrays.asList(anasint.getRuleNames()),arbol);
		JScrollPane panel = new JScrollPane(v);
		frame.add(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setSize(1000,300);
		frame.setVisible(true);

	}
	
	public static void main(String[] args) throws Exception {
		CharStream input = CharStreams.fromFileName(args[0]);
		Analex lexer = new Analex(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		Anasint parser = new Anasint(tokens);
		ParseTree tree = parser.sentencia();

		mostrar_arbol_analisis(parser,tree);  //mostrar árbol de derivación
	}
}
