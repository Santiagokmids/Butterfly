package dataStructure;

import java.util.ArrayList;

public class ListGraph<U, V, E, H> implements IListGraph<U, V, E, H>{
	
	private ArrayList<ListVertice<V, E>> listVertice;

	@Override
	public void createGraph() {
	}

	@Override
	public boolean addVertice() {
		return false;
	}

	@Override
	public boolean addEdge() {
		return false;
	}

	@Override
	public boolean deleteVertice() {
		return false;
	}

	@Override
	public boolean deleteEdge() {
		return false;
	}

	@Override
	public ListVertice<V, E> getVertice() {
		return null;
	}

	@Override
	public Edge<U, V, H> getEdge() {
		return null;
	}

	public ArrayList<ListVertice<V,E>> getListVertice() {
		return listVertice;
	}

	public void setListVertice(ArrayList<ListVertice<V, E>> listVertice) {
		this.listVertice = listVertice;
	}

	@Override
	public ArrayList<ListVertice<V, E>> bfs() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<ListVertice<V, E>> dfs() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<H> dijkstra() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void floyd() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public H prim() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public H kruskal() {
		// TODO Auto-generated method stub
		return null;
	}
}
