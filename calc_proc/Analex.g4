lexer grammar Analex;
fragment DIGITO: [0-9];
fragment LETRA:[a-zA-Z];

// Operaciones
POR: '*';
MAS: '+';
MENOS: '-';

// Caracteres especiales
PARENTESISABIERTO: '(';
PARENTESISCERRADO: ')';
PUNTOYCOMA: ';';
ASIG : '=';

// Básicos
NUMERO: DIGITO(DIGITO)+;
IDENT: LETRA(DIGITO|LETRA)*;