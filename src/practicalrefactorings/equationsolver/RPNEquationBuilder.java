/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practicalrefactorings.equationsolver;

/**
 * Takes tokens in reverse polish notation order and constructs the equation tree
 */
public interface RPNEquationBuilder {

	RPNEquationBuilder push(String token);

	Evaluable build();
}
