grammar SqlQuery;

/*
 * Parser rules
 */
//query : (fromClause|selectClause)+;
query: SELECT selectClause FROM fromClause | SELECT selectClause FROM fromClause WHERE condition;

selectClause: selectItem (COMMA selectItem)* | ASTERISK;
selectItem: dotNotation;

fromClause: fromItem (COMMA fromItem)*;
fromItem: IDENTIFIER;

condition: (join | predicate | subCondition) (AND (join | predicate | subCondition))*;

subCondition: LEFT_PARENTHESIS predicate OR predicate RIGHT_PARENTHESIS;
predicate: dotNotation comparisonOperator value=(INTEGER_VALUE | DECIMAL_VALUE | IDENTIFIER);
join: leftCondition=dotNotation EQ rightCondition=dotNotation;

comparisonOperator: GT | GE | LT | LE | EQ | NEQ;

dotNotation: table=IDENTIFIER DOT column=IDENTIFIER; // table.column

/*
 * Lexer rules
 */
SELECT: 'SELECT' | 'select';
FROM: 'FROM' | 'from';
WHERE : 'WHERE' | 'where';

DOT: '.';
COMMA: ',';
ASTERISK: '*';
LEFT_PARENTHESIS: '(';
RIGHT_PARENTHESIS: ')';
EQ: '=';
NOT : '!';
MINUS : '-';
PLUS: '+';
GT: '>';
GE: '>=';
LT: '<';
LE: '<=';
NEQ: '!=';

AND: 'AND' | 'and' | '&&';
OR: 'OR' | 'or' | '||';

INTEGER_VALUE
    : DIGIT+
    ;

DECIMAL_VALUE
    : DECIMAL_DIGITS
    ;

IDENTIFIER
    : (LETTER | DIGIT)+
    ;

WS : [ \r\n\t]+ -> skip ;

fragment DECIMAL_DIGITS : DIGIT+ '.' DIGIT* | '.' DIGIT+;
fragment DIGIT : [0-9];
fragment LETTER : [a-zA-Z] | '_';