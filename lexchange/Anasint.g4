parser grammar Anasint;
options{
 tokenVocab=Analex;
}

programa: esquema_fuente datos_fuente esquema_destino restricciones EOF;

esquema_fuente: ESQUEMA FUENTE constructor;

esquema_destino: ESQUEMA DESTINO constructor;

constructor: IDENT PA contenido_constructor PC;

contenido_constructor: IDENT
                    | IDENT COMA contenido_constructor;

datos_fuente: DATOS FUENTE atributo;

atributo: IDENT PA contenido_atrib PC;

contenido_atrib : (IDENT | NUMERO)
                | (IDENT | NUMERO) COMA contenido_atrib;

restricciones: RESTRICCIONES decl_var implicacion;

decl_var: VAR vars PYC;

vars: IDENT
    | IDENT COMA vars;

implicacion: contenido_constructor IMPLICA contenido_constructor;
