fragment DIGITO: [0-9];
fragment LETRA:[a-zA-Z];

VARIABLES: 'VARIABLES';
ASIGNACIONES: 'ASIGNACIONES';
CIERTO: 'cierto';
FALSO: 'falso';
INT: 'Integer';
BOOL: 'Boolean';
AND: 'and';
OR: 'or';
NOT: 'not';

NUM : ('-')?DIGITO+;
IDENT : LETRA(LETRA|DIGITO)*;
PA : '(';
PC : ')';
CA : '[';
CC : ']';
PyC : ';';
// Añadimos los lexemas necesarios al nuevo lenguaje
DP : ':';
COMA : ',';
ASIG: '=';
DOBLEASIG: '==';
MENORIGUAL: '<=';
MAYORIGUAL: '>=';
NO: 'no';
MAYOR: '>';
MENOR: '<';
DISTINTO: '!=';
DEV : 'devuelve';

RANGO: '..';
VECTOR: 'vector';

INSTRUCCIONES: 'instrucciones';
FINSI: 'finsi';
FIN: 'finmientras';
MIENTRAS: 'mientras';

Y: 'Y';
O: 'O';

MAS : '+';
MENOS: '-';
POR: '*';
DIV: '/';