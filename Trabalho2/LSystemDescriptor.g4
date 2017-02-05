grammar LSystemDescriptor;

description : alphabet axiom rules settings;

  //****************
  //*** Alphabet ***
  //****************

  alphabet : OP_ALPHABET (symbolWithCommand (OP_SEPARATOR symbolWithCommand)*)?;

    symbolWithCommand : ALPHABET_SYMBOL (OP_COMMAND command)?;

      command	: OP_FORWARD		// Moves the turtle forward, drawing a line between last position and current position
      		| OP_FORWARD_NODRAW	// Moves the turtle forward without drawing
      		| OP_ROTATE_CCW		// Rotates the direction which the turtle is facing counter-clockwise (currentDirection += parameter)
      		| OP_ROTATE_CW		// Rotates the direction which the turtle is facing clockwise (currentDirection -= parameter)
// ***		| OP_SET_DIR		// Sets the direction which the turtle is facing (currentDirection = parameter)
      		| OP_RESET		// Returns the turtle to the start facing the default angle
      		| OP_PUSH		// Anotates of the turtle's current position and direction
      		| OP_POP		// Goes back to the last position noted, facing the same direction in that note, then forget that note
      		| OP_FORGET		// Forgets the last position/direction noted without moving
      ;

  //*****************
  //***   Axiom   ***
  //*****************

  axiom : OP_AXIOM ALPHABET_SYMBOL*;

  //*****************
  //***   Rules   ***
  //*****************

  rules : OP_RULES (singleRule (OP_SEPARATOR singleRule)*)?;

    singleRule : ALPHABET_SYMBOL OP_RULE ALPHABET_SYMBOL*;

  //********************
  //***   Settings   ***
  //********************

  settings : (angle)? (size)?;
    angle : OP_ANGLE (INTEGER | REAL);
    size : OP_FORWARD_SIZE (INTEGER | REAL);

//*************
//*** LEXIC ***
//*************

COMMENT : '//' ~[\r\n]* -> skip;

WS : [ \t\r\n]+ -> skip;

ALPHABET_SYMBOL : OP_SYMBOL UPPER_CHAR OP_SYMBOL | OP_SYMBOL DIGIT OP_SYMBOL | SPECIAL_CHAR;

UPPER_CHAR : ('A'..'Z');

DIGIT : ('0'..'9');

INTEGER : OP_SIGNAL? DIGIT+;

REAL : INTEGER (OP_FLOAT_SEPARATOR DIGIT+)?;

SPECIAL_CHAR : (OP_SYMBOL '+' OP_SYMBOL | OP_SYMBOL '-' OP_SYMBOL | OP_SYMBOL '[' OP_SYMBOL | OP_SYMBOL ']' OP_SYMBOL);

//*****************
//*** OPERATORS ***
//*****************

OP_ALPHABET : 'alphabet:';

OP_AXIOM : 'axiom:';

OP_RULES : 'rules:';

OP_ANGLE : 'angle:';

OP_FORWARD_SIZE : 'size:';

OP_SEPARATOR : ',';

OP_COMMAND : ':';

OP_RULE : '->';

OP_SIGNAL : '-';

OP_FLOAT_SEPARATOR : '.';

OP_SYMBOL: '\'';

//*********************
//*** CMD OPERATORS ***
//*********************

OP_FORWARD : 'MOVE' | 'FORWARD';

OP_FORWARD_NODRAW : 'move' | 'forward';

OP_ROTATE_CCW : 'ROTATE' | 'LEFT';

OP_ROTATE_CW : 'RIGHT';

//OP_SET_DIR : 'SETDIR' | 'POINT';

OP_RESET : 'RESET' | 'HOME';

OP_PUSH : 'PUSH' | 'SAVE' | 'REMEMBER';

OP_POP : 'POP' | 'LOAD' | 'GOBACK' ;

OP_FORGET : 'FORGET' | 'POPNOMOVE';

//************
//*** ELSE ***
//************

OTHER_CHAR : .+?;
