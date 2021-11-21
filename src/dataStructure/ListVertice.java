package dataStructure;

import java.util.ArrayList;

public class ListVertice<V, U, H> extends Vertice<V, U, H>{

	private ArrayList<V> adjacency;
	
	public ListVertice(V value, ArrayList<Edge<U, V, H>> edge, ArrayList<V> adjacency) {
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
