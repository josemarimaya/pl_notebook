parser grammar Anasint;
options{
   tokenVocab=Analex;
}

programa : declaracion_variables instrucciones EOF ;
declaracion_variables: VARIABLES variables PUNTOYCOMA
 ;
variables: IDENT COMA variables #Ids
 | IDENT #Id
 ;
instrucciones: INSTRUCCIONES (asignacion)* ;
asignacion : IDENT ASIG expresion PUNTOYCOMA ;
expresion : expresion (POR|DIV|MAS|MENOS) expresion #OpBin
 | NUMERO #Num
 | IDENT PARENTESISABIERTO PARENTESISCERRADO #VarNoAmb
 | IDENT #Var
 | PARENTESISABIERTO expresion PARENTESISCERRADO #Par
 ;