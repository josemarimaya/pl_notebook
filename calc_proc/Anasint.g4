parser grammar Anasint;

options{
    tokenVocab = Analex;
}

programa : (orden PUNTOYCOMA)* EOF ;
orden: expresion #OrdenExpr
 | asignacion #OrdenAsig
 | funcion #OrdenFunc
 ;
asignacion : IDENT ASIG expresion ;
funcion : IDENT PARENTESISABIERTO IDENT PARENTESISCERRADO ASIG expresion ;
expresion : PARENTESISABIERTO expresion PARENTESISCERRADO #ParExpr
 | MENOS expresion
#UnarioMenosExpr
 | expresion POR expresion #PorExpr
 | expresion MAS expresion #MasExpr
 | expresion MENOS expresion #MenosExpr
 | NUMERO #NumExpr
 | IDENT #IdentExpr
 | IDENT PARENTESISABIERTO expresion PARENTESISCERRADO #LlamadaExpr
 ;
