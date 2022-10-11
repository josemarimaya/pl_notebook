// Analizador sint�ctico lenguaje expr
parser grammar Anasint;
options{
	tokenVocab=Analex;
}

sentencia : exprLogica EOF ;

//Añadimos la expresión lógica que agrega la equivalencia
exprLogica: CIERTO
          | FALSO
          | predicado
          | exprLogica Y exprLogica
          | exprLogica O exprLogica
          | exprLogica IMPLICA exprLogica
          | exprLogica EQUIVALENCIA exprLogica
          | NO exprLogica
          | PA exprLogica PC
          | cuantificador variables DP PA exprLogica PC
          ;

// predicado : predicadoInfijo | predicadoPrefijo ;

predicado : predicadoPrefijo;

/*predicadoInfijo :
  term
  (IGUAL|MAYOR|MENOR|MAYORIGUAL|MENORIGUAL|DISTINTO)
  term ;
*/

// Eliminamos el predicadoInfijo y lo unificamos todos en el Prefijo
predicadoPrefijo : IDENT PA terms PC
                | (IGUAL|MAYOR|MENOR|MAYORIGUAL|MENORIGUAL|DISTINTO) PA terms PC;

terms : term | term COMA terms ;

term: term1 (MAS|MENOS) term
    | term1
    ;

term1: term2 (POR|DIVISION) term1
     | term2
     ;

term2: NUMERO
    | IDENT
    | IDENT PA terms PC
    | PA term PC
    | MENOS term
    ;

//Añadimos el EXISTEUNO
cuantificador : PARATODO | EXISTE | EXISTEUNO;

variables : IDENT | IDENT COMA variables ;