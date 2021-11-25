package dataStructure;

public class NodeM <V extends Comparable<V> , U, H> {
	
	private NodeM <V, U, H> parent;
	private Vertice<V, U, H> vertice;
	
	public NodeM (Vertice<V, U, H> vertice){
		parent = null;
		this.vertice = vertice;
	}
	
	public NodeM <V, U, H> getParent() {
		return parent;
	}

	public void setParent(NodeM <V, U, H> parent) {
		this.parent = parent;
	}

	public Vertice<V, U, H> getVertice() {
		return vertice;
	}

	public void setVertice(Vertice<V, U, H> vertice) {
		this.vertice = vertice;
	}
}
