/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practicalrefactorings.floodfill;

import java.awt.Color;
import static java.awt.Color.BLACK;
import static java.awt.Color.WHITE;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class FloodfillTest {

	private static final Color W = WHITE;
	private static final Color B = BLACK;

	@Test
	public void testFillSinglePixel() {
		Grid<Color> input = fromArray(new Color[][]{
			{B, W, B},
			{W, W, B},
			{B, B, B}});
		Floodfill floodfill = new Floodfill();
		Grid<Color> filled = floodfill.fillAt(input, 0, 0, W);
		assertEquals(
				fromArray(new Color[][]{
					{W, W, B},
					{W, W, B},
					{B, B, B}}),
				filled);
	}

	@Test
	public void testFillWhole() {
		Grid<Color> input = fromArray(new Color[][]{
			{B, B, B},
			{B, B, B},
			{B, B, B}});
		Floodfill floodfill = new Floodfill();
		Grid<Color> filled = floodfill.fillAt(input, 0, 0, W);
		assertEquals(
				fromArray(new Color[][]{
					{W, W, W},
					{W, W, W},
					{W, W, W}}),
				filled);
	}

	@Test
	public void testFillShape() {
		Grid<Color> input = fromArray(new Color[][]{
			{W, B, W, W, W},
			{W, W, W, B, W},
			{B, B, B, W, W},
			{W, W, B, W, B}});
		Floodfill floodfill = new Floodfill();
		Grid<Color> filled = floodfill.fillAt(input, 0, 0, B);
		assertEquals(
				fromArray(new Color[][]{
					{B, B, B, B, B},
					{B, B, B, B, B},
					{B, B, B, B, B},
					{W, W, B, B, B}}),
				filled);
	}

	@Test
	public void testFillSameColor() {
		Grid<Color> input = fromArray(new Color[][]{
			{W}});
		Floodfill floodfill = new Floodfill();
		Grid<Color> filled = floodfill.fillAt(input, 0, 0, W);
		assertEquals(
				fromArray(new Color[][]{
					{W}}),
				filled);
	}

	@Test
	public void testFromArray() {
		Color[][] array = new Color[][]{
			{W, W},
			{B, B}};
		Grid<Color> grid = fromArray(array);
		assertEquals(WHITE, grid.get(0, 0));
		assertEquals(WHITE, grid.get(1, 0));
		assertEquals(BLACK, grid.get(0, 1));
		assertEquals(BLACK, grid.get(1, 1));
	}

	public Grid<Color> fromArray(Color[][] input) {
		int width = input[0].length;
		int height = input.length;
		Grid<Color> result = new ArrayBackedGrid<>(width, height);
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				result.set(input[y][x], x, y);
			}
		}
		return result;
	}

	private void print(Grid<Color> grid) {
		for (int x = 0; x < grid.width(); x++) {
			for (int y = 0; y < grid.height(); y++) {
				System.out.print(grid.get(x, y).equals(WHITE) ? "W" : "B");
			}
			System.out.println();
		}
	}

}
