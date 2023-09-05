grammar MongoQuery;

/*
 * Parser rules
 */
 // Mongo aggregate : https://www.mongodb.com/docs/manual/reference/method/db.collection.aggregate/
query : collection=IDENTIFIER '.' AGGREGATE LEFT_PARENTHESES LEFT_BRACKET matchStage? COMMA? projectStage? COMMA? (lookupStage COMMA?)*? RIGHT_BRACKET RIGHT_PARENTHESES;

matchStage: LEFT_BRACES MATCH ':' LEFT_BRACES ((predicate | composedPredicate) COMMA?)* RIGHT_BRACES RIGHT_BRACES;
composedPredicate: OR ':' LEFT_BRACKET (LEFT_BRACES predicate RIGHT_BRACES COMMA?)* RIGHT_BRACKET; // Handle only $and for the moment not the $and
predicate: field ':' (value | LEFT_BRACES comparisonOperator ':' value RIGHT_BRACES);
value: (DQUOTE? | SQUOTE?) raw=IDENTIFIER (DQUOTE? | SQUOTE?) | raw=INTEGER_VALUE | raw=DECIMAL_VALUE;

projectStage: LEFT_BRACES PROJECT ':' LEFT_BRACES fieldsProjection RIGHT_BRACES RIGHT_BRACES;
fieldsProjection: (field ':' (BOOLEAN_TRUE | BOOLEAN_FALSE) COMMA?)*;

lookupStage: LEFT_BRACES LOOKUP ':' LEFT_BRACES from COMMA localField COMMA foreignField COMMA as RIGHT_BRACES RIGHT_BRACES;
from: 'from' ':' (DQUOTE? | SQUOTE?) collection=IDENTIFIER (DQUOTE? | SQUOTE?);
localField: 'localField' ':' (DQUOTE? | SQUOTE?) field (DQUOTE? | SQUOTE?);
foreignField: 'foreignField' ':' (DQUOTE? | SQUOTE?) field (DQUOTE? | SQUOTE?);
as: 'as' ':' (DQUOTE? | SQUOTE?) rename=IDENTIFIER (DQUOTE? | SQUOTE?);

field: IDENTIFIER | (IDENTIFIER DOT?)*;

comparisonOperator: EQ | NEQ | GT | GTE | LT | LTE | IN | NIN;
logicalOperator: AND | OR | NOR | NOT;
/*
 * Lexer rules
 */
AGGREGATE: 'aggregate';

MATCH: '$match';
PROJECT: '$project';
LOOKUP: '$lookup';


DOT: '.';
COLON: ':';
COMMA: ',';
ASTERISK: '*';
LEFT_BRACES: '{';
RIGHT_BRACES: '}';
LEFT_BRACKET: '[';
RIGHT_BRACKET: ']';
LEFT_PARENTHESES: '(';
RIGHT_PARENTHESES: ')';
DQUOTE: '"';
SQUOTE: '\'';

EQ: '$eq';// equal
NEQ : '$ne';// not equal
GT: '$gt';// greater than
GTE: '$gte';// greater than or equal
LT: '$lt';// lower than
LTE: '$lte';// lower than or equal
IN: '$in';// Matches any of the values specified in an array
NIN: '$nin';// Matches none of the values specified in an array

AND: '$and';
OR: '$or';
NOT: '$not';
NOR: '$nor';

BOOLEAN_TRUE : '1' | 'true' | 'TRUE';
BOOLEAN_FALSE : '0' | 'false' | 'FALSE';

INTEGER_VALUE: DIGIT+;

DECIMAL_VALUE: DECIMAL_DIGITS;

IDENTIFIER: (LETTER | DIGIT | '_')+;

WS : [ \r\n\t]+ -> skip ;

fragment DECIMAL_DIGITS : DIGIT+ '.' DIGIT* | '.' DIGIT+;
fragment DIGIT : [0-9];
fragment LETTER : [a-zA-Z];
