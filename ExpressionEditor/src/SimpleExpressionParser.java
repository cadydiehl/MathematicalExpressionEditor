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
	protected Expression parseExpression (String str) {
		Expression expression;
		
		// TODO implement me

		AdditiveExpressionParser ap = new AdditiveExpressionParser();
		expression = ap.parse(str, null);
		if (expression != null) { //E -> A
			return expression;
		}

		ParentheticalExpressionParser pp = new ParentheticalExpressionParser();
		expression = pp.parse(str, null);
		if (expression != null) { // E -> X
			return expression;
		}



		/*for (int i = 1; i < str.length() - 1; i++) {
			if (str.charAt(i) == '+' && parseExpression(str.substring(0, i)) *//* before + *//* &&
					parseExpression(str.substring(i+1))) *//* after + *//* )
		} */
		return null;
	}
}
