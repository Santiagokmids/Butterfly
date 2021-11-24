package dataStructure;

import java.util.ArrayList;

public class ListVertice<V extends Comparable<V>, U, H>{

	private ArrayList<V> adjacency;
	
	private V value;
	private ArrayList<ListEdge<U, V, H>> edge;
	private boolean visited;
	private int color;
	
	public ListVertice(V value) {
		adjacency = new ArrayList<>();
		this.value = value;
		edge = new ArrayList<ListEdge<U,V,H>>();
		setVisited(false);
		color = 0;
	}
	
	public boolean addEdge(ListEdge<U, V, H> ed) {
		boolean noFound = true; 
		for(int i =0; i< edge.size() && noFound;i++) {
			if(edge.get(i).getFinalVertice().getValue().compareTo(ed.getFinalVertice().getValue()) == 0) {
				noFound= false;
			}
		}if(noFound) {
			edge.add(ed);
			return true;
			
		}
		return false;
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
