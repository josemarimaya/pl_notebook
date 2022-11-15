parser grammar Anasint;

options{
    tokenVocab = Analex;
}


programa: PROGRAMA variables instrucciones EOF;

variables: VARIABLES (lista_vars)? PUNTOYCOMA;

lista_vars: VAR COMA lista_vars
            |VARS
            ;

instrucciones: INSTRUCCIONES (lista_instrs)? ;

lista_instrs: instruccion(lista_instrs);

instruccion : asignacion
| condicional
| ruptura
| impresion
;
asignacion : VAR ASIG expr PUNTOYCOMA;
condicional : SI PARENTESISABIERTO condicion PARENTESISCERRADO ENTONCES
lista_instrs (alternativa)? FINSI;
alternativa : SINO lista_instrs ;
ruptura : RUPTURA PUNTOYCOMA ;
impresion : MOSTRAR PARENTESISABIERTO vars PARENTESISCERRADO PUNTOYCOMA ;
vars : VAR COMA vars
| VAR
;
condicion : expr MAYOR expr #Mayor
| expr MENOR expr #Menor
| expr IGUAL expr #Igual
| expr DISTINTO expr #Distinto
| expr MAYORIGUAL expr #MayorIgual
| expr MENORIGUAL expr #MenorIgual
;
expr : expr MAS expr #Mas
| expr MENOS expr #Menos
| expr POR expr #Por
| VAR #Var
| NUM #Num
| PARENTESISABIERTO expr PARENTESISCERRADO #ParExp
;