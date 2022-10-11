// Analizador l�xico lenguaje R
lexer grammar Analex;

BLANCO: ' ' ->skip;
TABULADOR: '\t'->skip;
FIN_LINEA: '\r'?'\n' ->skip;

fragment DIGITO: [0-9];
fragment LETRA:[a-zA-Z];

VARIABLES: 'VARIABLES';
ASIGNACIONES: 'ASIGNACIONES';
HLIST: 'HList';
ILIST: 'IList';
BLIST: 'BList';
TRUE: 'true';
FALSE: 'false';
INT: 'Integer';
BOOL: 'Boolean';
TIMES: 'times';
MINUS: 'minus';
ADD: 'add';
AND: 'and';
OR: 'or';
NOT: 'not';
APPEND: 'append';

NUM : ('-')?DIGITO+;
IDENT : LETRA(LETRA|DIGITO)*;
PARENTESISABIERTO : '(';
PARENTESISCERRADO : ')';
CORCHETEABIERTO : '[';
CORCHETECERRADO : ']';
PyC : ';';
// Añadimos el lexema dos puntos que no viene en nuestra semántica
DosPuntos : ':';
COMA : ',';
ASIG: '=';

COMENTARIO_BLOQUE : '/*' .*? '*/' -> skip ;
COMENTARIO_LINEA : '//' .*? FIN_LINEA -> skip ;
