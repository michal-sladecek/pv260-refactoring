/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practicalrefactorings.floodfill;

import java.awt.Color;
import java.util.ArrayList;
import static java.util.Arrays.asList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;

public class Floodfill {
	private boolean isValidIndex(int x, int y, Grid<Color> grid) {
		return !(x < 0 || x >= grid.width() || y < 0 || y >= grid.height());
	}
	public Grid<Color> fillAt(Grid<Color> original, int startX, int startY, Color color) {
		if (!isValidIndex(startX, startY, original)) {
			throw new IndexOutOfBoundsException("Got " + new Position(startX, startY) + " but grid is only " + original.width() + "x" + original.height());
		}
		Grid<Color> gridCopy = getColorGridCopy(original);

		Queue<Position> left = new LinkedList<>();
		left.add(new Position(startX, startY));
		Color replacingColor = original.get(startX, startY);
		if (replacingColor.equals(color)) {
			return gridCopy;
		}
		while (!left.isEmpty()) {
			Position at = left.poll();
			if (isValidIndex(at.x(),at.y(),original)) {
				gridCopy.set(color, at.x(), at.y());
				Collection<Position> neighbors = asList(
						new Position(at.x() + 1, at.y()),
						new Position(at.x(), at.y() + 1),
						new Position(at.x() - 1, at.y()),
						new Position(at.x(), at.y() - 1)
				);
				Collection<Position> uncoloredNeighbors = new ArrayList<>();
				for (Position position : neighbors) {
					if (position.x() >= 0 && position.x() < gridCopy.width() && position.y() >= 0 && position.y() < gridCopy.height()) {
						Color colorAtPosition = gridCopy.get(position.x(), position.y());
						if (colorAtPosition.equals(replacingColor)) {
							uncoloredNeighbors.add(position);
						}
					}
				}

				left.addAll(uncoloredNeighbors);
			}
		}
		return gridCopy;
	}

	private Grid<Color> getColorGridCopy(Grid<Color> original) {
		Grid<Color> copy = new ArrayBackedGrid<>(original.width(), original.height());
		for (int x = 0; x < original.width(); x++) {
			for (int y = 0; y < original.height(); y++) {
				copy.set(original.get(x, y), x, y);
			}
		}
		return copy;
	}
}
