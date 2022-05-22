package my_project.model;

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

	private Edge highlight;

	public GraphVisualization (Graph graph) {
		this.graph = graph;
		this.coordinates = new HashMap<>();
		this.rand = new Random();
	}

	public void load () {
		var l = graph.getVertices();

		int x = 1;
		int y = 1;

		l.toFirst();
		while (l.hasAccess()) {
			coordinates.put(
				l.getContent(),
				new Point(x * 100 + rand.nextInt(50), y * 100 + rand.nextInt(50))
			);

			// x + 1 unless at the end, then y = 0
			y += (x >= 3) ? 1 : 0;
			x %= 3;
			x++;
			l.next();
		}
	}

	public void setHighlight (Edge edge) {
		this.highlight = edge;
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

			drawTool.setCurrentColor(edges.getContent() == highlight ? Color.GREEN : Color.BLACK);

			drawTool.drawLine(
				c1.getX(), c1.getY(),
				c2.getX(), c2.getY()
			);
			edges.next();
		}
	}

}
