package dataStructure;

import java.util.ArrayList;

public interface IListGraph<U, V, H> {
	public void createGraph();
	public boolean addVertice();
	public boolean addEdge();
	public boolean deleteVertice();
	public boolean deleteEdge();
	public ListVertice<V, U, H> getVertice();
	public Edge<U, V, H> getEdge();
	public ArrayList<ListVertice<V, U, H>> bfs();
	public ArrayList<ListVertice<V, U, H>> dfs();
	public ArrayList<H> dijkstra(ListVertice<V, U, H> start);
	public void floyd();
	public int prim(ListVertice<V, U, H> start);
	public int kruskal();
}
