package dataStructure;

public class NodeK <V, U, H> {
	
	private NodeK <V, U, H> parent;
	private Vertice<V, U, H> vertice;
	
	public NodeK(Vertice<V, U, H> vertice){
		parent = null;
		this.vertice = vertice;
	}
	
	public NodeK <V, U, H> getParent() {
		return parent;
	}

	public void setParent(NodeK <V, U, H> parent) {
		this.parent = parent;
	}

	public Vertice<V, U, H> getVertice() {
		return vertice;
	}

	public void setVertice(Vertice<V, U, H> vertice) {
		this.vertice = vertice;
	}
}
