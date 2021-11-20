package dataStructure;

import java.util.ArrayList;

public interface IListGraph<U, V, E, H> {
	public void createGraph();
	public boolean addVertice();
	public boolean addEdge();
	public boolean deleteVertice();
	public boolean deleteEdge();
	public ListVertice<V, E> getVertice();
	public Edge<U, V, H> getEdge();
	public ArrayList<ListVertice<V, E>> bfs();
	public ArrayList<ListVertice<V, E>> dfs();
	public ArrayList<H> dijkstra();
	public void floyd();
	public H prim();
	public H kruskal();
}
