package my_project.model;

import KAGO_framework.model.abitur.datenstrukturen.Vertex;

public class VertexData {

	private final Vertex src;
	private VertexData prev;

	private double dist;


	public VertexData (Vertex src, VertexData prev, double dist) {
		this.src = src;
		this.prev = prev;
		this.dist = dist;
	}

	public void setDist (double dist) {
		this.dist = dist;
	}

	public Vertex getSrc () {
		return src;
	}

	public double getDist () {
		return dist;
	}

	public VertexData getPrev () {
		return prev;
	}

	public void setPrev (VertexData prev) {
		this.prev = prev;
	}
}
