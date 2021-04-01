/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practicalrefactorings.equationsolver;

import java.util.Stack;

public class OnTheFlyRPNEquationBuilder implements RPNEquationBuilder {

	private Stack<Evaluable> stack = new Stack<>();

	private boolean isNumeric(String token){
		try {
			int value = Integer.parseInt(token);
		} catch(NumberFormatException e){
			return false;
		}
		return true;
	}

	@Override
	public RPNEquationBuilder push(String token) {
		if (isNumeric(token)) {
			handleNumber(token);
		} else {
			handleSymbol(token);
		}
		return this;
	}

	private void handleNumber(String token) {
		int value = Integer.parseInt(token);
		NumberNode number = new NumberNode(value);
		stack.push(number);
	}

	private void handleSymbol(String token) {
		if (token.length() == 1) {
			SymbolNode operator = new SymbolNode(token.charAt(0));
			if (stack.isEmpty()) {
				throw new IllegalStateException("Nothing left on the stack for operand");
			}
			Evaluable right = stack.pop();
			if (stack.isEmpty()) {
				throw new IllegalStateException("Nothing left on the stack for operand");
			}
			Evaluable left = stack.pop();
			operator.setLeft(left);
			operator.setRight(right);
			stack.push(operator);
		} else {
			throw new IllegalArgumentException("Dont understand token: " + token);
		}
	}

	@Override
	public Evaluable build() {
		if (stack.size() != 1) {
			throw new IllegalStateException("More than one token left on the stack, unbalanced input.");
		}
		return stack.pop();
	}

}
