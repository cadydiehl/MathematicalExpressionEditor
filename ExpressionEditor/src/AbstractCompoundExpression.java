import java.util.List;

public abstract class AbstractCompoundExpression implements CompoundExpression {
    //Variables
    //is this supposed to be a LinkedList ??
    private List<Expression> _subexpression;
    private CompoundExpression _expr;
    private String _field; //String that equals either "A", "M", or "P" (cannot be LiteralExpression)

    //Constructors
    public AbstractCompoundExpression(CompoundExpression expr, List<Expression> subexpression) {
        _expr = expr;
        _subexpression = subexpression;

        //Cannot be a LiteralExpression, can only be either Additive, Multiplicative, or Parenthetical
        //TODO make sure additive, multiplicative, parethetical can be an instance of CompoundExpression
        if(expr instanceof AdditiveExpression)
            _field = "A";
        else if(expr instanceof MultiplicativeExpression)
            _field = "M";
        else
            _field = "P";
    }

    public AbstractCompoundExpression() { //Default constructor

    }

    //Methods
    public Expression deepCopy() {
        //TODO implement or make abstract
        AbstractCompoundExpression copy;
        //List<Expression> copyChildren = new ArrayList<>();

        if(_field == "A")
            copy = new AdditiveExpression();
        else if(_field == "M")
            copy = new MultiplicativeExpression();
        else
            copy = new ParentheticalExpression();

        for(Expression subexpr : _subexpression) {
            copy._subexpression.add(subexpr.deepCopy());
        }

        /*
        for(int i = 0; i < _subexpression.size(); i++) {
            copy._subexpression.add(_subexpression.get(i).deepCopy());
            //use addSubexpression(Expression subexpression) ??
            //not sure if I need .deepCopy()
        }
        */

        return copy;
    }

    /*
    public void addSubexpression(Expression subexpression) {

    }
    */
}
