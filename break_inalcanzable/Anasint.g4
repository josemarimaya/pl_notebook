parser grammar Anasint;

options{
tokenVocab=Analex;
}

programa : instrucciones EOF ;
instrucciones : instruccion instrucciones
 | instruccion
 ;
instruccion : asignacion
 | iteracion
 | seleccion
 | ruptura
 ;

asignacion : IDENT ASIG expr PyC ;

iteracion : WHILE PA expr PC bloque ;

seleccion : IF PA expr PC bloque ;

ruptura : cent=BREAK PyC ;

bloque : instruccion
 | LLA instrucciones LLC
 ;

expr : expr_suma
 ((MENOR|MAYOR|MENORIGUAL|MAYORIGUAL|IGUAL|DISTINTO) expr_suma)?
 ;

expr_suma : expr_mult ((MAS|MENOS) expr_mult)*
 ;

expr_mult : expr_base ((POR|DIV) expr_base)*
 ;

expr_base : NUMERO
 | IDENT
 | PA expr PC
 ;