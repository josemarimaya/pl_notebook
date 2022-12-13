lexer grammar Analex;

DIAGRAMA:'DIAGRAMA';
VARIABLES:'VARIABLES';
ESTADOS:'ESTADOS';
INICIAL:'INICIAL';
TRANSICIONES:'TRANSICIONES';

fragment NUEVALINEA: '\r'?'\n';
fragment DIGITO: [0-9];
fragment LETRA: [a-zA-Z];

SEPARADORES: (' '|'\t'|NUEVALINEA) -> skip ;
COM: '//'(.)*? NUEVALINEA -> skip;

IDENT: LETRA(LETRA|DIGITO)*;
NUMERO:(DIGITO)+;
Y:'&&';
O:'||';
MAYOR:'>';
MENOR:'<';
MAYORIGUAL:'>=';
MENORIGUAL:'<=';
NO:'!';
IGUAL:'==';
ASIG:'=';
DISTINTO:'!=';
PARENTESISABIERTO:'(';
PARENTESISCERRADO:')';
PUNTOYCOMA:';';