package dataStructure;

import java.util.ArrayList;

public class Vertice<V extends Comparable<V>, U, H> {
	
	private V value;
	private ArrayList<Edge<U, V, H>> edge;
	private boolean visited;
	private int color;
	public Vertice<V, U, H> next;
	public Vertice<V, U, H> down;
	private int distance;
	
	public Vertice(V value) {
		this.value = value;
		edge = new ArrayList<Edge<U, V, H>>();
		setVisited(false);
		color = 0;
		setDistance(Integer.MAX_VALUE);
	}
	
	public boolean addEdge(Vertice<V, U, H> initial, Vertice<V, U, H> end, H weight) {
		edge.add(new Edge<U, V, H>(initial,end,weight));
		return true;
	}
	
	public boolean deleteEdge(Edge<U, V, H> edge) {
		return true;
	}
	
	public V getValue() {
		return value;
	}
	public void setValue(V value) {
		this.value = value;
	}
	public ArrayList<Edge<U, V, H>> getEdge() {
		return edge;
	}
	public void setEdge(ArrayList<Edge<U, V, H>> edge) {
		this.edge = edge;
	}

	public boolean isVisited() {
		return visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}
	
	public Vertice<V, U, H> getNext() {
		return next;
	}

	public void setNext(Vertice<V, U, H> next) {
		this.next = next;
	}

	public Vertice<V, U, H> getDown() {
		return down;
	}

	public void setDown(Vertice<V, U, H> down) {
		this.down = down;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}
}
