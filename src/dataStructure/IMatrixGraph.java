package dataStructure;

import java.util.ArrayList;

public interface IMatrixGraph<U, V, H, E> {
	public void createGraph();
	public boolean addVertice();
	public boolean addEdge();
	public boolean deleteVertice();
	public boolean deleteEdge();
	public Vertice<V, E> getVertice();
	public Edge<U, V, H> getEdges();
	public ArrayList<Vertice<V, E>> bfs();
	public ArrayList<Vertice<V, E>> dfs();
	public ArrayList<H> dijkstra();
	public void floyd();
	public H prim();
	public H kruskal();
}
