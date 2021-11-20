package dataStructure;

import java.util.ArrayList;

public class ListVertice<V, E> extends Vertice<V, E>{

	private ArrayList<V> adjacency;
	
	public ListVertice(V value, ArrayList<E> edge, ArrayList<V> adjacency) {
		super(value, edge);
		this.adjacency = adjacency;
	}
	
	public boolean addAdjacency(V vertice) {
		return true;
	}

	public ArrayList<V> getAdjacency() {
		return adjacency;
	}

	public void setAdjacency(ArrayList<V> adjacency) {
		this.adjacency = adjacency;
	}
}
