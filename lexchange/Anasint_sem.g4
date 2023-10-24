parser grammar Anasint;

options{
tokenVocab=Analex;
}
entrada : esquema_fuente datos_fuente esquema_destino restricciones EOF;
esquema_fuente : ESQUEMA FUENTE (signatura)+ ;

signatura: IDENT PA atributos PC ;
atributos: IDENT COMA atributos
 | IDENT
 ;

datos_fuente: DATOS FUENTE (tupla)+ ;

esquema_destino: ESQUEMA DESTINO (signatura)+ ;
restricciones: RESTRICCIONES (restriccion)+ ;

restriccion: variables implicacion ;

variables: VAR vars PyC ;

vars: IDENT COMA vars
 | IDENT
 ;

implicacion: tupla IMPLICA tupla ;

tupla: IDENT PA terminos PC ;

terminos: termino COMA terminos
 | termino
 ;

termino: IDENT
 | NUMERO
 ;