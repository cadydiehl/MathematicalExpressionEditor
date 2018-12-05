public class MultiplicativeExpressionParser implements ExpressionParser {
    @Override
    public Expression parse(String str, boolean withJavaFXControls) throws ExpressionParseException {
        //M := M*M | X

        MultiplicativeExpression expression = new MultiplicativeExpression();

        MultiplicativeExpressionParser mp = new MultiplicativeExpressionParser();



        for (int i = 1; i < str.length() - 1; i++) {
            if (str.charAt(i) == '*') {

                MultiplicativeExpression me1 = (MultiplicativeExpression) mp.parse(str.substring(0,i), false);
                MultiplicativeExpression me2 = (MultiplicativeExpression) mp.parse(str.substring(i + 1), false);
                if (me1 != null && me2 != null) {
                    // We set the parent of A, * and M to be expression.
                    me1.setParent(expression);
                    //LiteralExpression m = new LiteralExpression("*");
                    //m.setParent(expression);
                    me2.setParent(expression);

                    // We create a arraylist of the children/subexpressions of the additiveExpression.
                    /*ArrayList<Expression> subExpressions = new ArrayList<>();
                    subExpressions.add(ae);
                    subExpressions.add(m);
                    subExpressions.add(me);*/ // TODO: 04/12/2018 not sure if it works without initializing the array _subExrpressions in the abstract class AcstractCompoundExpressions.
//                    expression._subExpressions.add(ae);
//                    expression._subExpressions.add(m);
//                    expression._subExpressions.add(me);

                    return expression;
                }
            }

        }

        ParentheticalExpressionParser pp = new ParentheticalExpressionParser();
        MultiplicativeExpression pe = (MultiplicativeExpression) pp.parse(str, false);

        if (pe != null) {
            pe.setParent(expression);
            return expression;
        }
        return null;
    }
}
