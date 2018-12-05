import java.util.ArrayList;

public class AdditiveExpressionParser implements ExpressionParser {
    // A := A+M | M
    @Override
    public Expression parse(String str, boolean withJavaFXControls) throws ExpressionParseException {
        AdditiveExpression expression = new AdditiveExpression();

        AdditiveExpressionParser ap = new AdditiveExpressionParser();
        MultiplicativeExpressionParser mp = new MultiplicativeExpressionParser();


        for (int i = 1; i < str.length() - 1; i++) {
            if (str.charAt(i) == '+') {
                AdditiveExpression ae = (AdditiveExpression) ap.parse(str.substring(0, i), false); // TODO: 04/12/2018 rememnber to do the same in the other parsers: AdditiveExpression expression; for the returning data type and also the same I did in this line
                MultiplicativeExpression me = (MultiplicativeExpression) mp.parse(str.substring(i + 1), false);
                if (ae != null && me != null) {
                    // We set the parent of A, * and M to be expression.
                    ae.setParent(expression);
                    //LiteralExpression m = new LiteralExpression("*");
                    //m.setParent(expression);
                    me.setParent(expression);

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

        MultiplicativeExpression me = (MultiplicativeExpression) mp.parse(str, false);

        if (me != null) {
            me.setParent(expression);
            return expression;
        }
        return null;
    }
}
