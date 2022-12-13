parser grammar Anasint;
options{
	tokenVocab=Analex;
}

diagrama: DIAGRAMA IDENT variables estados inicial transiciones EOF;

variables: VARIABLES vars ;

vars: IDENT vars
    |
    ;

estados: ESTADOS (estado)* ;

estado: IDENT asignaciones ;

asignaciones: asignacion asignaciones
            |
            ;

asignacion: IDENT ASIG term PUNTOYCOMA ;

inicial: INICIAL IDENT ;

transiciones: TRANSICIONES (transicion)* ;

transicion: IDENT IDENT condicion PUNTOYCOMA ;

condicion: condicion Y condicion                            #condY
         | condicion O condicion                            #condO
         | NO condicion                                     #condNo
         | PARENTESISABIERTO condicion PARENTESISCERRADO    #condPar
         | term MAYOR term                                  #condMayor
         | term MENOR term                                  #condMenor
         | term IGUAL term                                  #condIgual
         | term DISTINTO term                               #condDistinto
         | term MAYORIGUAL term                             #condMayorIgual
         | term MENORIGUAL term                             #condMenorIgual
         ;

term: NUMERO | IDENT ;

