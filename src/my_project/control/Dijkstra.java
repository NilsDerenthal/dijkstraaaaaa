package my_project.control;

import KAGO_framework.model.abitur.datenstrukturen.*;
import my_project.model.VertexData;

import java.util.function.Function;

public class Dijkstra {

	private final Graph graph;

	private final Vertex start;
	private final Vertex end;

	private final Queue<Edge> queueOrder;

	public Dijkstra (Graph graph, Vertex start, Vertex end) {
		this.graph = graph;
		this.start = start;
		this.end = end;
		this.queueOrder = new Queue<>();
	}

	public VertexData execute () {
		List<VertexData> unvisited = map(graph.getVertices(),
			v -> new VertexData(v, null, v == start ? 0 : Double.MAX_VALUE));

		List<VertexData> all = new List<>();
		unvisited.toFirst();
		while (unvisited.hasAccess()) {
			all.append(unvisited.getContent());
			unvisited.next();
		}
		return evalDistances(end, unvisited, all);
	}

	private VertexData evalDistances (Vertex end, List<VertexData> unvisited, List<VertexData> all) {
		VertexData tmp;
		while ((tmp = getMinAndRemove(unvisited)).getSrc() != end) {

			var neighbours = graph.getNeighbours(tmp.getSrc());

			neighbours.toFirst();
			while (neighbours.hasAccess()) {
				var nbData = find(all, neighbours.getContent());

				var edge = graph.getEdge(neighbours.getContent(), tmp.getSrc());
				queueOrder.enqueue(edge);

				double sum = tmp.getDist() + edge.getWeight();

				if (sum < nbData.getDist()) {
					nbData.setDist(sum);
					nbData.setPrev(tmp);
				}
				neighbours.next();
			}

		}
		return tmp;
	}

	private VertexData find (List<VertexData> data, Vertex content) {
		data.toFirst();
		while (data.hasAccess()) {
			if (data.getContent().getSrc() == content) {
				return data.getContent();
			}
			data.next();
		}
		return null;
	}

	private VertexData getMinAndRemove (List<VertexData> src) {
		src.toFirst();
		VertexData res = src.getContent();
		while (src.hasAccess()) {
			if (src.getContent().getDist() < res.getDist()) {
				res = src.getContent();
			}
			src.next();
		}

		src.toFirst();
		while (src.hasAccess()) {
			if (src.getContent() == res) {
				src.remove();
				return res;
			}
			src.next();
		}

		return res;
	}

	private <A, B> List<B> map (List<A> src, Function<A, B> mapper) {
		List<B> res = new List<>();
		src.toFirst();
		while (src.hasAccess()) {
			res.append(mapper.apply(src.getContent()));
			src.next();
		}
		return res;
	}

	public Queue<Edge> getEdges () {
		return queueOrder;
	}
}
