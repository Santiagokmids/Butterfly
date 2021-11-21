package dataStructure;

public class NodeK <V, U, H> {
	
	private NodeK <V, U, H> parent;
	private ListVertice<V, U, H> vertice;
	
	public NodeK(ListVertice<V, U, H> vertice){
		parent = null;
		this.vertice = vertice;
	}
	
	public NodeK <V, U, H> getParent() {
		return parent;
	}

	public void setParent(NodeK <V, U, H> parent) {
		this.parent = parent;
	}

	public ListVertice<V, U, H> getVertice() {
		return vertice;
	}

	public void setVertice(ListVertice<V, U, H> vertice) {
		this.vertice = vertice;
	}
}
