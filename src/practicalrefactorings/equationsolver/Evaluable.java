/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practicalrefactorings.equationsolver;

public interface Evaluable {

	/** Calculates value of the whole expression subtree under this node */
	int evaluate();

	/** Graphically represents the subtree under this node */
	String representation();

}
