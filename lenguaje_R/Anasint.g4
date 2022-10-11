// Analizador sint�ctico lenguaje R
parser grammar Anasint;
options{
   tokenVocab=Analex;
}

programa : variables asignaciones EOF  ;

variables : VARIABLES (decl_vars)*   ;

// Añadimos la declaración de variables con dos puntos Ejemplo-> j : Boolean;
decl_vars : tipo vars PyC
            | vars DosPuntos tipo PyC
            ;

tipo: HLIST | ILIST | BLIST | INT | BOOL   ;

vars : IDENT (COMA vars)?  ;


asignaciones : ASIGNACIONES (asignacion)*
           // | PARENTESISABIERTO ASIGNACIONES (asignacion )* PARENTESISCERRADO
            ;

asignacion: asignacion_simple
            |asignacion_multiple
            ;

// Añadimos asigancion_multiple y editamos asignacion para que haya dos tipos de asignaciones en vez de solo la simple
asignacion_simple: IDENT ASIG expr PyC ;

asignacion_multiple: PARENTESISABIERTO vars PARENTESISCERRADO ASIG PARENTESISABIERTO expr (COMA expr)? PARENTESISCERRADO PyC;

/*
Con esta expresión podemos sustituir expr (COMA expr)? para sacarla igual
exprs: expr COMA expr
      | expr;
*/
expr: expr_lista
    | expr_booleana
    | expr_entera
    ;

expr_lista: lista_por_extension
    | lista_append
    | variable
    ;

lista_por_extension:
     CORCHETEABIERTO  CORCHETECERRADO           #ListaExtVacia
   | CORCHETEABIERTO seq_elems CORCHETECERRADO  #ListaExtNoVacia
   ;

lista_append: APPEND PARENTESISABIERTO
                        expr_lista COMA expr_lista
                     PARENTESISCERRADO   ;

variable: IDENT ;

funcion_booleana_binaria: AND | OR   ;

expr_booleana: FALSE                                        #ExprBoolFALSE
    | TRUE                                                  #ExprBoolTRUE
    | funcion_booleana_binaria
          PARENTESISABIERTO
             expr_booleana COMA expr_booleana
          PARENTESISCERRADO                                 #ExprBoolFunc
    | NOT PARENTESISABIERTO expr_booleana PARENTESISCERRADO #ExprBoolNot
    | variable                                              #ExprBoolId
    ;

funcion_entera: ADD | MINUS | TIMES    ;

expr_entera: NUM                                    #ExprEntNUM
 | funcion_entera PARENTESISABIERTO
                     expr_entera COMA expr_entera
                  PARENTESISCERRADO                 #ExprEntFunc
 | variable                                         #ExprEntId
 ;

seq_elems: expr_entera (COMA seq_elems)?     #SeqElemsEnt
         | expr_booleana (COMA seq_elems)?   #SeqElemsBool
         | variable (COMA seq_elems)?        #SeqElemsId  ;
