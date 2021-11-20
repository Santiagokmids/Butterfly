package dataStructure;

public interface IListGraph<U, V, E, H> {
	public void createGraph();
	public boolean addVertice();
	public boolean addEdge();
	public boolean deleteVertice();
	public boolean deleteEdge();
	public ListVertice<V, E> getVertice();
	public Edge<U, V, H> getEdge();
}
