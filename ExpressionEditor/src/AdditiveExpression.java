import java.util.ArrayList;

//Production Rules
//Terminals = [0-9], [a-z]

public class AdditiveExpression extends SimpleCompoundExpression {//implements CompoundExpression {
    //Variables
    final String EXPR = "+";
    final String FIELD = "AdditiveExpression";
    private CompoundExpression _parent;
    private ArrayList<Expression> _subexpression;
    private int _numSubExpr;

    //Constructors
    public AdditiveExpression() {
        _numSubExpr = 0;
    }

    //Methods
    @Override
    public void addSubexpression(Expression subexpression) {
        _subexpression.add(subexpression);
        _numSubExpr++;
        _subexpression.get(_subexpression.size() - 1).setParent(this);
    }

    @Override
    public CompoundExpression getParent() {
        return _parent;
    }

    @Override
    public void setParent(CompoundExpression parent) {
        _parent = parent;
    }

    @Override
    public Expression deepCopy() {

        return this;
    }

    @Override
    public void flatten() {
        for(int i = 0; i < _subexpression.size(); i++) {
            if(_subexpression.get(i) == _parent) {
                addSubexpression(_subexpression.get(i));
            }
        }
    }

    @Override
    public String convertToString(int indentLevel) {
        //use indent!!
        //Expression.indent(sb, indentLevel);
        //String str = "";
        StringBuilder sb = new StringBuilder();

        if(_subexpression == null) {

        }
        else {
            for(int i = 0; i < _subexpression.size(); i++) {
                sb.append(convertToString(indentLevel++));
            }
        }

        return null;
    }
}
