import java.util.List;

public class ParentheticalExpression extends AbstractCompoundExpression {
    //Variables
    private CompoundExpression _parent;
    protected List<Expression> _subexpression;
    private int _numSubExpr;

    //Constructors
    public ParentheticalExpression() {
        _numSubExpr = 0;
    }

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

        //not sure if this is needed
        for(Expression subexpr : _subexpression) {
            _parent.addSubexpression(subexpr);
        }
    }

    @Override
    public Expression deepCopy() {
        final ParentheticalExpression copy = new ParentheticalExpression();
        //List<Expression> copyChildren = new ArrayList<>();

        for(Expression subexpr : _subexpression) {
            copy._subexpression.add(subexpr.deepCopy()); //not sure if i need .deepCopy()
        }

        return copy;
    }

    @Override
    public void flatten() {
        if(getParent() != null && getParent() instanceof ParentheticalExpression) { // .getClass() == getClass()) { //or do i use instanceof
            for(Expression subexpr : _subexpression) {
                getParent().addSubexpression(subexpr);
            }

            //remove this from parent subexpression
            setParent(null); //is this right??
            ((ParentheticalExpression)getParent()).removeSubexpression(this);
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

    public void removeSubexpression(ParentheticalExpression subexpr) {
        _subexpression.remove(subexpr);
    }
}
