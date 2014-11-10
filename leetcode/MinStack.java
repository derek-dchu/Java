
/**
 *Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
 * push(x) -- Push element x onto stack.
 * pop() -- Removes the element on top of the stack.
 * top() -- Get the top element.
 * getMin() -- Retrieve the minimum element in the stack.
 */

import java.util.Stack;


public class MinStack {
	private Stack<Integer> data;
	private Stack<Integer> min;
	
	public MinStack() {
		this.data = new Stack<>();
		this.min = new Stack<>();
	}
	
	public void push(int x) {
        if (this.data.isEmpty() || x <= this.min.peek()) {
        	this.min.push(x);
        } 
        
        this.data.push(x);
    }

    public void pop() {
        if (this.data.isEmpty()) {
        	return;
        }
        
        if (this.data.peek().equals(this.min.peek())) {
        	this.min.pop();
        };
        this.data.pop();
    }

    public int top() {
        return this.data.peek();
    }

    public int getMin() {
        return this.min.peek();
    }
    
    public static void main(String[] args) {
    	MinStack minStack = new MinStack();
    	
	    minStack.push(2147483646);
	    minStack.push(2147483646);
	    minStack.push(2147483647);
	    assert minStack.top() == 2147483647 : "Not Match";
	    assert minStack.getMin() == 2147483646 : "Not Match";
	    minStack.pop();
	    minStack.getMin();
	    minStack.pop();
	    minStack.push(2147483647);
	    minStack.top();
	    minStack.getMin();
	    minStack.push(-2147483648);
	    minStack.top();
	    assert minStack.getMin() == -2147483648 : "Not Match";
	    minStack.pop();
	    assert minStack.getMin() == 2147483646 : "Not Match";
	    System.out.println("Finished");
    }
}
