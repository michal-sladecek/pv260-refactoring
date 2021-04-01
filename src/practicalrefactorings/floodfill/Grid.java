/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practicalrefactorings.floodfill;

/**
 * Data structure which holds elements in a 2D matrix.
 * The indexing is following:
 * <pre>
 * +------+------+
 * |[0][0]|[1][0]|
 * +-------------+
 * |[0][1]|[1][1]|
 * +------+------+
 * </pre>
 * The object is mutable.
 * Both width and height of the grid must always be at least 1.
 */
public interface Grid<T> {

	int width();

	int height();

	/**
	 * @throws IndexOutOfBoundsException if either the x or y are <0
	 * or >= than the respective dimension of the grid
	 * */
	T get(int x, int y);

	/**
	 * {@inheritDoc }
	 * @throws IndexOutOfBoundsException if either the x or y are <0
	 * or >= than the respective dimension of the grid
	 * */
	void set(T item, int x, int y);

}
