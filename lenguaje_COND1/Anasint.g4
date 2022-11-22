parser grammar Anasint;

options{
    tokenVocab = Analex;
}


programa: PROGRAMA variables instrucciones EOF;

variables: VARIABLES (lista_vars)? PyC;

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
asignacion : VAR ASIG expr PyC;
condicional : SI PA condicion PC ENTONCES
lista_instrs (alternativa)? FINSI;
alternativa : SINO lista_instrs ;
ruptura : RUPTURA PyC ;
impresion : MOSTRAR PA vars PC PyC ;
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
| PA expr PC #ParExp
;
