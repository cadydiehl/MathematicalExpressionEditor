/**
 * Starter code to implement an ExpressionParser. Your code should parse a context-free grammar
 * for mathematical expressions for addition, multiplication, and parentheses over single-letter
 * and integer operands. Suggested (though not required) grammar:
 * E := AdditiveExpression | ParentheticalExpression
 * AdditiveExpression := AdditiveExpression+MultiplicativeExpression | MultiplicativeExpression
 * MultiplicativeExpression := MultiplicativeExpression*MultiplicativeExpression | ParentheticalExpression
 * ParentheticalExpression := (E) | LiteralExpression
 * LiteralExpression := [0-9]+ | [a-z]
 *
 * E := A | X
 * A := A+M | M
 * M := M*M | X
 * X := (E) | L
 * L := [0-9]+ | [a-z]
 */


import java.lang.String;

public class SimpleExpressionParser implements ExpressionParser {
	/**
	 * Attempts to create an expression tree -- flattened as much as possible -- from the specified String.
	 * Throws a ExpressionParseException if the specified string cannot be parsed.
	 * @param str the string to parse into an expression tree
	 * @param withJavaFXControls you can just ignore this variable for R1
	 * @return the Expression object representing the parsed expression tree
	 */
	public Expression parse (String str, boolean withJavaFXControls) throws ExpressionParseException {
		// Remove spaces -- this simplifies the parsing logic
		str = str.replaceAll(" ", "");
		Expression expression = parseExpression(str);
		if (expression == null) {
			// If we couldn't parse the string, then raise an error
			throw new ExpressionParseException("Cannot parse expression: " + str);
		}

		// Flatten the expression before returning
		expression.flatten();
		return expression;
	}

	/**
	 *
	 * @param str
	 * @return
	 */
	protected Expression parseExpression (String str) throws ExpressionParseException {
		Expression expression;
		
		// TODO implement me
		// TODO: 04/12/2018 I am not sure how to declare the parents/children here! Is E an additive node or is A a children of E 
		AdditiveExpressionParser ap = new AdditiveExpressionParser();
		AdditiveExpression ae = (AdditiveExpression) ap.parse(str, false);
		if (ae != null) { //E -> A
			expression = new SimpleCompoundExpression();
			ae.setParent((CompoundExpression) expression); //todo not sure if it is safe to do this casting
			return expression;
		}

		ParentheticalExpressionParser pp = new ParentheticalExpressionParser();
		ParentheticalExpression pe = (ParentheticalExpression) pp.parse(str, false);
		if (pe != null) { // E -> X
			expression = new SimpleCompoundExpression();
			pe.setParent((CompoundExpression) expression); //todo not sure if it is safe to do this casting
			return expression;
		}
		return null;
	}
}
