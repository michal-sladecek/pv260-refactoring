/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practicalrefactorings.equationsolver;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class EquationSolverTest {

	@Test
	public void testSinglePlus() {
		RPNEquationBuilder builder = new OnTheFlyRPNEquationBuilder();
		Evaluable equation = builder.push("3").push("4").push("+").build();
		assertEquals(7, equation.evaluate());
		assertEquals("(3 + 4)", equation.representation());
	}

	@Test
	public void testPlusMinus() {
		RPNEquationBuilder builder = new OnTheFlyRPNEquationBuilder();
		Evaluable equation = builder.push("3").push("4").push("+").push("5").push("-").build();
		assertEquals(2, equation.evaluate());
		assertEquals("((3 + 4) - 5)", equation.representation());
	}

	@Test
	public void testSingleNumber() {
		RPNEquationBuilder builder = new OnTheFlyRPNEquationBuilder();
		Evaluable equation = builder.push("3").build();
		assertEquals(3, equation.evaluate());
		assertEquals("3", equation.representation());
	}

	@Test(expected = IllegalStateException.class)
	public void testUnbalancedInputNoOperator() {
		RPNEquationBuilder builder = new OnTheFlyRPNEquationBuilder();
		Evaluable equation = builder.push("3").push("4").build();
	}

	@Test(expected = IllegalStateException.class)
	public void testUnbalancedInputTooManyOperands() {
		RPNEquationBuilder builder = new OnTheFlyRPNEquationBuilder();
		Evaluable equation = builder.push("3").push("4").push("+").push("-").build();
	}

	@Test
	public void testLongInput() {
		RPNEquationBuilder builder = new OnTheFlyRPNEquationBuilder();
		Evaluable equation = builder
				.push("5").push("1").push("2")
				.push("+").push("4").push("*")
				.push("+").push("3").push("-").build();
		assertEquals(14, equation.evaluate());
		assertEquals("((5 + ((1 + 2) * 4)) - 3)", equation.representation());
	}

}
