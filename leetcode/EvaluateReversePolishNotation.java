
/*
 * Evaluate the value of an arithmetic expression in Reverse Polish Notation.
 *
 * Valid operators are +, -, *, /. Each operand may be an integer or another expression.
 *
 * Some examples:
 * ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
 * ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6
 */
import java.util.Stack;


public class EvaluateReversePolishNotation {
	public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for (String token : tokens) {
        	if (token.length() > 1) {
        		stack.push(Integer.parseInt(token));
        	} else if (token.compareTo("+-*/") < 5) {
        		int operand2 = stack.pop();
        		int operand1 = stack.pop();
        		stack.push(eval(operand1, token, operand2));
        	} else {
        		stack.push(Integer.parseInt(token));
        	}
        }
        
        return stack.pop();
    }
	
	public int eval(int operand1, String operator, int operand2) {
		switch(operator) {
		case "+": return operand1 + operand2;
		case "-": return operand1 - operand2;
		case "*": return operand1 * operand2;
		default: return operand1 / operand2;
		}
	}
	
	public static void main(String[] args) {
		EvaluateReversePolishNotation solution = new EvaluateReversePolishNotation();
		String[] test1 = {"2", "1", "+", "3", "*"};
		assert solution.evalRPN(test1) == 9 : "Not match";
		String[] test2 = {"4", "13", "5", "/", "+"};
		assert solution.evalRPN(test2) == 6 : "Not match";
	}
}
