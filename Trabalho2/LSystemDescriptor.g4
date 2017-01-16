grammar LSystemDescriptor;

description : alphabet axiom rules;

  //****************
  //*** Alphabet ***
  //****************

  alphabet : OP_ALPHABET (symbolWithCommand (OP_SEPARATOR symbolWithCommand)*)?;

    symbolWithCommand : ALPHABET_SYMBOL (OP_COMMAND command)?;

      command	: OP_FORWARD parameter?			// Moves the turtle forward, drawing a line between last position and current position
      		| OP_FORWARD_NODRAW parameter?		// Moves the turtle forward without drawing
      		| OP_ROTATE_CCW parameter?		// Rotates the direction which the turtle is facing counter-clockwise (currentDirection += parameter)
      		| OP_ROTATE_CW parameter?		// Rotates the direction which the turtle is facing clockwise (currentDirection -= parameter)
      		| OP_SET_DIR parameter			// Sets the direction which the turtle is facing (currentDirection = parameter)
      		| OP_RESET				// Returns the turtle to the start facing the default angle
      		| OP_PUSH				// Anotates of the turtle's current position and direction
      		| OP_POP				// Goes back to the last position noted, facing the same direction in that note, then forget that note
      		| OP_FORGET				// Forgets the last position/direction noted without moving
      ;

        parameter : OP_OPEN_EXPRESSION arithmeticExpression OP_CLOSE_EXPRESSION;

          arithmeticExpression : term (OP_SUM term)*;

            term : factor (OP_MULT factor)*;

              factor : DIGIT | INTEGER | REAL | OP_OPEN_EXPRESSION arithmeticExpression OP_CLOSE_EXPRESSION;

  //*****************
  //***   Axiom   ***
  //*****************

  axiom : OP_AXIOM ALPHABET_SYMBOL*;

  //*****************
  //***   Rules   ***
  //*****************

  rules : OP_RULES (singleRule (OP_SEPARATOR singleRule)*)?;

    singleRule : ALPHABET_SYMBOL PERCENTAGE? OP_RULE resultExpression;

      resultExpression : (ALPHABET_SYMBOL | ALPHABET_SYMBOL symbolParameter | OTHER_CHAR)*;

        symbolParameter : OP_OPEN_EXPRESSION rulesArithmeticExpression OP_CLOSE_EXPRESSION;

          rulesArithmeticExpression : rulesTerm (OP_SUM rulesTerm)*;

            rulesTerm : rulesFactor (OP_MULT rulesFactor)*;

              rulesFactor : DIGIT | INTEGER | REAL | OP_OPEN_EXPRESSION rulesArithmeticExpression OP_CLOSE_EXPRESSION | OP_VALUE;

//*************
//*** LEXIC ***
//*************

COMMENT : '//' ~[\r\n]* -> skip;

WS : [ \t\r\n]+ -> skip;

ALPHABET_SYMBOL : OP_SYMBOL UPPER_CHAR OP_SYMBOL | OP_SYMBOL DIGIT OP_SYMBOL | SPECIAL_CHAR;

UPPER_CHAR : ('A'..'Z');

DIGIT : ('0'..'9');

SPECIAL_CHAR : (OP_SYMBOL '+' OP_SYMBOL | OP_SYMBOL '-' OP_SYMBOL | OP_SYMBOL '[' OP_SYMBOL | OP_SYMBOL ']' OP_SYMBOL);

INTEGER : OP_SIGNAL? DIGIT+;

REAL : INTEGER (OP_FLOAT_SEPARATOR DIGIT+)?;

PERCENTAGE : '0'* OP_FLOAT_SEPARATOR '0'* ('1'..'9') DIGIT* | '0'* ('100' | '0' ('1'..'9') | ('1'..'9') '0' | DIGIT? ('1'..'9')) OP_PERCENTAGE;

//*****************
//*** OPERATORS ***
//*****************

OP_ALPHABET : 'alphabet:';

OP_AXIOM : 'axiom:';

OP_RULES : 'rules:';

OP_SEPARATOR : ',';

OP_COMMAND : ':';

OP_RULE : '->';

OP_OPEN_EXPRESSION : '(';

OP_CLOSE_EXPRESSION : ')';

OP_VALUE : 'v';

OP_SIGNAL : '-';

OP_FLOAT_SEPARATOR : '.';

OP_SUM : '+' | '-';

OP_MULT : '*' | '/';

OP_PERCENTAGE : '%';

OP_SYMBOL: '\'';

//*********************
//*** CMD OPERATORS ***
//*********************

OP_FORWARD : 'MOVE' | 'FORWARD';

OP_FORWARD_NODRAW : 'move' | 'forward';

OP_ROTATE_CCW : 'ROTATE' | 'LEFT';

OP_ROTATE_CW : 'RIGHT';

OP_SET_DIR : 'SETDIR' | 'POINT';

OP_RESET : 'RESET' | 'HOME';

OP_PUSH : 'PUSH' | 'SAVE' | 'REMEMBER';

OP_POP : 'POP' | 'LOAD' | 'GOBACK' ;

OP_FORGET : 'FORGET' | 'POPNOMOVE';

//************
//*** ELSE ***
//************

OTHER_CHAR : .;