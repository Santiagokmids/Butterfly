package dataStructure;

import java.util.ArrayList;

public interface IMatrixGraph<U, V, H> {
	public void createGraph();
	public boolean addVertice();
	public boolean addEdge();
	public boolean deleteVertice();
	public boolean deleteEdge();
	public Vertice<V, U, H> getVertice();
	public Edge<U, V, H> getEdges();
	public ArrayList<Vertice<V, U, H>> bfs();
	public ArrayList<Vertice<V, U, H>> dfs();
	public ArrayList<H> dijkstra(Vertice<V, U, H> start, Vertice<V, U, H> Final);
	public void floyd();
	public H prim();
	public H kruskal();
}
