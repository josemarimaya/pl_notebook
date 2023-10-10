parser grammar Anasint;
options{
 tokenVocab=Analex;
}

programa: esquema_fuente datos_fuente esquema_destino restricciones EOF;
esquema_fuente: ESQUEMA FUENTE (constructor)*;
constructor: IDENT PA contenido_constructor PC;
contenido_constructor: IDENT
                    | IDENT COMA contenido_constructor;

datos_fuente: DATOS FUENTE (atributo)*;
atributo: IDENT PA contenido_atrib PC;
contenido_atrib : (IDENT | NUMERO) /*  distinto */
                | (IDENT | NUMERO) COMA contenido_atrib;

esquema_destino: ESQUEMA DESTINO (constructor)*;



restricciones: RESTRICCIONES decl_var implicacion; // distinto
decl_var: VAR vars PYC;
vars: IDENT
    | IDENT COMA vars;
implicacion: contenido_constructor IMPLICA contenido_constructor;