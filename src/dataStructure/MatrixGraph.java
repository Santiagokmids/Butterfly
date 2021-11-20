package dataStructure;

import java.util.ArrayList;

public class MatrixGraph<U, V, H, E> implements IMatrixGraph<U, V, H, E>{
	
	public ArrayList<Vertice<V, E>> vertice;
	
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
	public Vertice<V, E> getVertice() {
		return null;
	}

	@Override
	public Edge<U, V, H> getEdges() {
		return null;
	}

	@Override
	public ArrayList<Vertice<V, E>> bfs() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Vertice<V, E>> dfs() {
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
