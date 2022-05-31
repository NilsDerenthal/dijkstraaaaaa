package my_project.view;

import KAGO_framework.model.GraphicalObject;
import KAGO_framework.model.abitur.datenstrukturen.Edge;
import KAGO_framework.model.abitur.datenstrukturen.Graph;
import KAGO_framework.model.abitur.datenstrukturen.Vertex;
import KAGO_framework.view.DrawTool;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class GraphVisualization extends GraphicalObject {

	private final Graph graph;
	private final Map<Vertex, Point> coordinates;

	private final Random rand;

	private Color color;

	public GraphVisualization (Graph graph) {
		this.graph = graph;
		this.coordinates = new HashMap<>();
		this.rand = new Random();
		this.color = Color.RED;
	}

	public void loadCoordinates () {
		var l = graph.getVertices();

		int x = 1;
		int y = 1;

		l.toFirst();
		while (l.hasAccess()) {
			coordinates.put(
				l.getContent(),
				new Point((x - 1) * 250 + rand.nextInt(50) + 10, (y - 1) * 250 + rand.nextInt(50) + 10)
			);

			// x + 1 unless at the end, then y = 0
			y += (x >= 3) ? 1 : 0;
			x %= 3;
			x++;
			l.next();
		}
	}

	public void setMarkColor (Color color) {
		this.color = color;
	}

	@Override
	public void draw (DrawTool drawTool) {

		coordinates.forEach((v, coordinate) -> {
			drawTool.drawCircle(coordinate.getX(), coordinate.getY(), 10);
			drawTool.drawText(coordinate.getX(), coordinate.getY(), v.getID());
		});

		var edges = graph.getEdges();
		edges.toFirst();
		while (edges.hasAccess()) {
			var vertices = edges.getContent()
				.getVertices();

			var c1 = coordinates.get(vertices[0]);
			var c2 = coordinates.get(vertices[1]);

			double midX = (c1.getX() + c2.getX()) / 2d;
			double midY = (c1.getY() + c2.getY()) / 2d;

			drawTool.setCurrentColor(Color.GRAY);
			drawTool.setLineWidth(1);

			drawTool.drawText(midX, midY, String.valueOf(edges.getContent().getWeight()));

			if (edges.getContent().isMarked()) {
				drawTool.setCurrentColor(color);
				drawTool.setLineWidth(4);
			}

			drawTool.drawLine(
				c1.getX(), c1.getY(),
				c2.getX(), c2.getY()
			);
			edges.next();
		}
	}

}
