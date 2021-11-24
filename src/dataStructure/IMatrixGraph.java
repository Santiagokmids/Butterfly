package dataStructure;

import java.util.ArrayList;

public interface IMatrixGraph<U, V, H> {
	public void createGraph();
	public boolean addVertice(V value);
	public boolean addEdge(V valueIni, V valueEnd, H height);
	public boolean deleteVertice();
	public boolean deleteEdge();
	public boolean setEdge();
	public Vertice<V, U, H> getVertice();
	public Edge<U, V, H> getEdges();
	public ArrayList<Vertice<V, U, H>> bfs(V v);
	public ArrayList<Vertice<V, U, H>> dfs(V v);
	public ArrayList<H> dijkstra(Vertice<V, U, H> start);
	public void floyd();
	public int prim(Vertice<V, U, H> start);
	public int kruskal();
}
