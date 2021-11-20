package dataStructure;

public interface IMatrixGraph<U, V, H, E> {
	public void createGraph();
	public boolean addVertice();
	public boolean addEdge();
	public boolean deleteVertice();
	public boolean deleteEdge();
	public Vertice<V, E> getVertice();
	public Edge<U, V, H> getEdges();
}
