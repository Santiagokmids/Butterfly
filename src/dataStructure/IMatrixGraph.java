package dataStructure;

import java.util.ArrayList;

public interface IMatrixGraph<U, V extends Comparable<V>, H> {
	public void createGraph();
	public boolean addVertice(V value);
	public boolean addEdge(V valueIni, V valueEnd, H height);
	public H searchEdge(V verticeInit, V verticeEnd);
	public boolean deleteVertice(V value);
	public boolean deleteEdge(V initial, V end, H ewight);
	public boolean modifyEdge(V initial, V end, H ewight, H newWeight);
	public ArrayList<Vertice<V, U, H>> getVertice();
	public ArrayList<Edge<U, V, H>> getEdges();
	public ArrayList<Vertice<V, U, H>> bfs(V v);
	public ArrayList<Vertice<V, U, H>> dfs(V v);
	public ArrayList<H> dijkstra(Vertice<V, U, H> start);
	public void floyd();
	public int prim(Vertice<V, U, H> start);
	public int kruskal();
}
