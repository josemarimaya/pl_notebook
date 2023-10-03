parser grammar Anasint;

options{
    tokenVocab= Analex;
}

sentencia: formula EOF;

formula: predicado |
        NO formula | // negación
        formula Y formula | //conjunción
        formula O formula | //disyunción
        formula IMPLICA formula |  // implicación
        (PARATODO|EXISTE)  variables DOSPUNTOS PA formula PC |  // cuantificadores
        PA formula PC;

variables: variable | variable COMA variables;

variable: IDENT;

predicado: predicadoPrefijo | predicadoInfijo;

predicadoPrefijo: IDENT PA terminos PC;

predicadoInfijo: termino operadorInfijo termino;

operadorInfijo: IGUAL | DISTINTO |MAYOR | MENOR | MAYORQUE | MENORQUE;

terminos: termino | termino COMA terminos;

// f(x) +                           2          -    3*5
// termino1 MAS                    termino
// termino2                        termino1 MENOS termino
// IDENT PA terminos PC            termino2        termino1

// Gramática no ambigua

termino: termino1 (MAS|MENOS) termino
    | termino1;

termino1: termino2 (POR| DIV) termino1
    | termino2;

termino2: variable
| NUMERO
| IDENT PA terminos PC
| PA termino PC;


