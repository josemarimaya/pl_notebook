parser grammar Anasint;
options{
   tokenVocab=Analex;
}

procedimiento : perfil variableslocales instrucciones EOF;

perfil: IDENT PA parametros PC DEV PA tipos PC;

parametros: parametro COMA parametros
            |parametro
            |
            ;
parametro : tipo IDENT;

tipos: tipo COMA tipos
        | tipo
        |
        ;

tipo: VECTOR PA tipo PC CA indice RANGO indice CC
       | ENTERO
       | BOOLEANO
       ;

indice : NUMERO | IDENT ;

variableslocales: VARIABLES LOCALES DP variables;

variables : decl_vars variables
            |
            ;

decl_vars: tipo vars PyC;

vars: IDENT COMA vars
    | IDENT
    ;

instrucciones: INSTRUCCIONES DP (instruccion)* FIN ;

instruccion: asignacion
            | iteracion
            | seleccion
            | retorno
             ;

asignacion: asignacionsimple
            |asignacionmultiple
            ;

asignacionsimple: IDENT ASIG expr PyC;
asignacionmultiple: PA vars PC ASIG PA exprs PC PyC;
iteracion: MIENTRAS PA expr PC HACER (instruccion)* FIN;
seleccion: SI PA expr PC ENTONCES (instruccion)* (SINO (instruccion)*)? FINSI;
retorno: DEV PA exprs PC PyC;

exprs:  expr COMA exprs
        | expr
        |
        ;

expr: expr_booleana | expr_entera ;

expr_booleana : CIERTO
                | FALSO
                |expr_booleana (DOBLEASIG | DISTINTO) expr_booleana
                | expr_entera (DOBLEASIG | MENORIGUAL | MAYORIGUAL | DISTINTO | MAYOR | MENOR) expr_entera
                | expr_booleana (Y | O) expr_booleana
                | NO expr_booleana
                | IDENT CA expr_entera CC
                | IDENT PA exprs PC
                | IDENT
                ;

expr_entera: expr_entera (MAS | MENOS | POR | DIV) expr_entera
            |IDENT CA expr_entera CC
            | IDENT PA expr_entera PC
            | IDENT
            |NUMERO
            ;