public class ParentheticalExpressionParser implements ExpressionParser {
    @Override
    public Expression parse(String str, boolean withJavaFXControls) throws ExpressionParseException {
        //X := (E) | L
        ParentheticalExpression expression = new ParentheticalExpression();

        SimpleExpressionParser ep = new SimpleExpressionParser();
        Expression e = ep.parse(str.substring(1, str.length() - 1), false); //beginIndex - the beginning index, inclusive ; endIndex - the ending index, exclusive.
        if (str.charAt(0) == '('
                &&  str.charAt(str.length() - 1) == ')'
                && e != null) {
            e.setParent(expression);
            return expression;
        }

        LiteralExpressionParser lp = new LiteralExpressionParser();
        LiteralExpression le = (LiteralExpression) lp.parse(str, false);
        if (le != null) {
            le.setParent(expression);
            return expression;
        }

        return null;
    }
}
