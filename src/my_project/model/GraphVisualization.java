package my_project.model;

import KAGO_framework.model.GraphicalObject;
import KAGO_framework.model.abitur.datenstrukturen.Graph;
import KAGO_framework.view.DrawTool;

public class GraphVisualization extends GraphicalObject {

	private final Graph graph;

	public GraphVisualization (Graph graph) {
		this.graph = graph;
	}

	@Override
	public void draw (DrawTool drawTool) {
		var l = graph.getVertices();

		int x = 1;
		int y = 1;

		l.toFirst();
		while (l.hasAccess()) {
			drawTool.drawCircle(x * 100, y * 100, 10);
			drawTool.drawText(x * 100, y * 100, l.getContent().getID());

			x++;
			if (x > 5) {
				y++;
				x = 1;
			}

			l.next();
		}
	}
}
