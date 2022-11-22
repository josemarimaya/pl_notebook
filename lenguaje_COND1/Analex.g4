lexer grammar Analex;

PROGRAMA: 'PROGRAMA';
VARIABLES: 'VARIABLES';
INSTRUCCIONES: 'INSTRUCCIONES';
ENTONCES: 'entonces';
SINO:'sino';
FINSI:'finsi';
RUPTURA:'ruptura';
MOSTRAR:'mostrar';

BLANCO: ' ' -> skip;
TAB: '\t' -> skip;
NL: '\r'?'\n'-> skip;
CONLINEA: '//' (.)*? NL -> skip;
CON: '/*' (.)*? '*/' -> skip;

fragment DIGITO: [0-9];
fragment LETRA:[a-zA-Z];
VAR: LETRA(LETRA|DIGITO)*;
PA : '(';
PC : ')';
CA : '[';
CC : ']';
PyC : ';';
// Añadimos los lexemas necesarios al nuevo lenguaje
DP : ':';
COMA : ',';
IGUAL: '=';

DOBLEASIG: '==';
MENORIGUAL: '<=';
MAYORIGUAL: '>=';
NO: 'no';
SI: 'si';
MAYOR: '>';
MENOR: '<';
DISTINTO: '!=';
DEV : 'devuelve';

MAS : '+';
MENOS: '-';
POR: '*';
DIV: '/';
