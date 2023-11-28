programa : PROGRAMA variables instrucciones EOF ;

variables : VARIABLES lista_vars PUNTOYCOMA;

lista_vars : IDENT COMA lista_vars #Vars
 | IDENT #Var
 ;
instrucciones : INSTRUCCIONES (lista_instrs)? ;

lista_instrs : instruccion (lista_instrs)? ;

instruccion : asignacion
 | condicional
 | leer
 ;
asignacion : IDENT ASIG expr PUNTOYCOMA;

condicional : SI PARENTESISABIERTO condicion PARENTESISCERRADO ENTONCES
 lista_instrs (alternativa)?
 FINSI;

alternativa : SINO lista_instrs ;

leer : LEER PARENTESISABIERTO IDENT PARENTESISCERRADO PUNTOYCOMA ;

condicion : expr MAYOR expr
 | expr MENOR expr
 | expr IGUAL expr
 | expr DISTINTO expr
 | expr MAYORIGUAL expr
 | expr MENORIGUAL expr;
expr : expr MAS expr
 | expr MENOS expr
 | expr POR expr
 | IDENT
 | NUMERO
 | PARENTESISABIERTO expr PARENTESISCERRADO;