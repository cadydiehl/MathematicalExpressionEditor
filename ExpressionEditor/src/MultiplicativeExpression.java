import java.util.List;

public class MultiplicativeExpression extends SimpleCompoundExpression {
    //Variables
    private CompoundExpression _parent;
    protected List<Expression> _subexpression;
    private int _numSubExpr;

    //Constructors
    public MultiplicativeExpression() {
        _numSubExpr = 0;
    }

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

        //not sure if this is needed
        for(Expression subexpr : _subexpression) {
            _parent.addSubexpression(subexpr);
        }
    }

    @Override
    public Expression deepCopy() {
        final MultiplicativeExpression copy = new MultiplicativeExpression();
        //List<Expression> copyChildren = new ArrayList<>();

        for(Expression subexpr : _subexpression) {
            copy._subexpression.add(subexpr.deepCopy());
        }

        /*
        for(int i = 0; i < _subexpression.size(); i++) {
            copy._subexpression.add(_subexpression.get(i).deepCopy()); //not sure if I need .deepCopy()
        }
        */

        return copy;
    }

    @Override
    public void flatten() {
        if(getParent() != null && getParent() instanceof MultiplicativeExpression) {//.getClass() == getClass()) {
            for(Expression subexpr : _subexpression) {
                getParent().addSubexpression(subexpr);
            }

            //remove this from parent subexpression
            setParent(null); //is this right??
            ((MultiplicativeExpression)getParent()).removeSubexpression(this);
        }
    }

    @Override
    public String convertToString(int indentLevel) {
        StringBuffer sb = new StringBuffer();

        //sb.append(this.indent(sb, indentLevel));

        for(Expression subexpr : _subexpression) {
            sb.append(subexpr.convertToString(indentLevel++));
        }

        return sb.toString();
    }

    public void removeSubexpression(MultiplicativeExpression subexpr) {
        _subexpression.remove(subexpr);
    }
}
