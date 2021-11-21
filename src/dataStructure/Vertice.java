package dataStructure;

import java.util.ArrayList;

public class Vertice<V, U, H>{
	
	private V value;
	private ArrayList<Edge<U, V, H>> edge;
	
	public Vertice(V value, ArrayList<Edge<U, V, H>> edge) {
		this.value = value;
		this.edge = edge;
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
	public ArrayList<Edge<U, V, H>> getEdge() {
		return edge;
	}
	public void setEdge(ArrayList<Edge<U, V, H>> edge) {
		this.edge = edge;
	}
}
