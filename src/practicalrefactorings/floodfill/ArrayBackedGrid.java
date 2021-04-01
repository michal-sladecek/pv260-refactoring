/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practicalrefactorings.floodfill;

/**
 * Implementation of the Grid using 2D array
 */
public class ArrayBackedGrid<T> implements Grid<T> {

	private Object[][] backingArray;

	@Override
	public int width() {
		return backingArray.length;
	}

	@Override
	public int height() {
		return backingArray[0].length;
	}

	/**
	 * @throws IllegalArgumentException either of the width and height are <= 0
	 */
	public ArrayBackedGrid(int width, int height) {
		validateSize(width, height);
		backingArray = new Object[width][height];
	}

	@Override
	public T get(int x, int y) {
		validateIndex(x, y);
		// The cast is always safe as the set (only way to add items )
		// quarantees that the item is of correct type
		return (T) backingArray[x][y];
	}

	@Override
	public void set(T item, int x, int y) {
		validateIndex(x, y);
		backingArray[x][y] = item;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof ArrayBackedGrid)) {
			return false;
		}

		ArrayBackedGrid<T> other = (ArrayBackedGrid) obj;
		if (other.width() != width() || other.height() != height()) {
			return false;
		}
		for (int x = 0; x < width(); x++) {
			for (int y = 0; y < height(); y++) {
				if (!other.get(x, y).equals(get(x, y))) {
					return false;
				}
			}
		}
		return true;
	}

	@Override
	public int hashCode() {
		int hash = 1;
		for (int x = 0; x < width(); x++) {
			for (int y = 0; y < width(); y++) {
				hash *= 17 + get(x, y).hashCode();
			}
		}
		return hash;
	}

	private void validateSize(int width, int height) {
		if (width <= 0 || height <= 0) {
			throw new IllegalArgumentException("Got size " + width + "x" + height + ", size is required to be at least 1x1");
		}
	}

	private void validateIndex(int x, int y) {
		if (x < 0 || x >= width()
				|| y < 0 || y >= height()) {
			throw new IndexOutOfBoundsException("Got index [" + x + "," + y + "] but the grid is only " + width() + "x" + height());
		}
	}
}
