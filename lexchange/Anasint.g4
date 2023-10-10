parser grammar Anasint;

options{
tokenVocab=Analex;
}
programa: esquema_fuente datos_fuente esquema_destino restricciones EOF;
esquema_fuente: ESQUEMA FUENTE (signatura)*;
signatura: IDENT PA atributos PC;
atributos: IDENT COMA atributos | IDENT;

datos_fuente: DATOS FUENTE (atomo)*;
atomo: IDENT PA terminos PC;
terminos: termino COMA terminos | termino;
termino: IDENT | NUMERO;

esquema_destino: ESQUEMA DESTINO (signatura)*;

restricciones: RESTRICCIONES (restriccion)*;
restriccion: decl_vars implicacion;
decl_vars: VAR variables PyC;
variables: IDENT COMA variables | IDENT;
implicacion: datos IMPLICA datos;
datos: IDENT PA variables PC;
