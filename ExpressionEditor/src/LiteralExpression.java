import java.lang.reflect.Array;
import java.util.ArrayList;

public class LiteralExpression implements Expression {
    //Variables
    private ArrayList<Expression> _subexpressions;
    private String _str;
    private CompoundExpression _parent;
    private int _numSubExpr;

    //Constructors
    public LiteralExpression(String str, ArrayList<LiteralExpression>) {
        _str = str;
        _numSubExpr = 0;
    }

    //Methods
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
        //TODO implement me
        Expression copy = new LiteralExpression(new String(_str));

        return copy;
    }

    @Override
    public void flatten() {

    }

    @Override
    public String convertToString(int indentLevel) {
        return null;
    }

}

