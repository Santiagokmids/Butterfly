package dataStructure;

public class ListGraph<U, V, E, H> implements IListGraph<U, V, E, H>{
	
	private ListVertice<V, E> listVertice;

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

	public ListVertice<V, E> getListVertice() {
		return listVertice;
	}

	public void setListVertice(ListVertice<V, E> listVertice) {
		this.listVertice = listVertice;
	}
}
