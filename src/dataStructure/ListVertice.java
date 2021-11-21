package dataStructure;

import java.util.ArrayList;

public class ListVertice<V, U, H>{

	private ArrayList<V> adjacency;
	
	private V value;
	private ArrayList<ListEdge<U, V, H>> edge;
	private boolean visited;
	private int color;
	
	public ListVertice(V value, ArrayList<ListEdge<U, V, H>> edge) {
		this.value = value;
		this.edge = edge;
		setVisited(false);
		color = 0;
	}
	
	public boolean addEdge(Edge<U, V, H> edge) {
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
	public ArrayList<ListEdge<U, V, H>> getEdge() {
		return edge;
	}
	public void setEdge(ArrayList<ListEdge<U, V, H>> edge) {
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

	public ArrayList<V> getAdjacency() {
		return adjacency;
	}

	public void setAdjacency(ArrayList<V> adjacency) {
		this.adjacency = adjacency;
	}
	
}
