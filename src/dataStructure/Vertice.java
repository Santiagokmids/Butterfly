package dataStructure;

import java.util.ArrayList;

public class Vertice<V, E>{
	
	private V value;
	private ArrayList<E> edge;
	
	public Vertice(V value, ArrayList<E> edge) {
		this.value = value;
		this.edge = edge;
	}
	
	public boolean addEdge(E edge) {
		return true;
	}
	
	public boolean deleteEdge(E edge) {
		return true;
	}
	
	public V getValue() {
		return value;
	}
	public void setValue(V value) {
		this.value = value;
	}
	public ArrayList<E> getEdge() {
		return edge;
	}
	public void setEdge(ArrayList<E> edge) {
		this.edge = edge;
	}
}
