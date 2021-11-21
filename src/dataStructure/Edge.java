package dataStructure;

public class Edge<U, V, H> {
	
	private Vertice<V, U, H> initVertice;
	private Vertice<V, U, H> finalVertice;
	private H height;
	
	public Edge(Vertice<V, U, H> initVertice, Vertice<V, U, H>finalVertice, H height) {
		this.initVertice = initVertice;
		this.finalVertice = finalVertice;
		this.height = height;
	}

	public  Vertice<V, U, H> getInitVertice() {
		return initVertice;
	}

	public void setInitVertice( Vertice<V, U, H> initVertice) {
		this.initVertice = initVertice;
	}

	public  Vertice<V, U, H> getFinalVertice() {
		return finalVertice;
	}

	public void setFinalVertice( Vertice<V, U, H> finalVertice) {
		this.finalVertice = finalVertice;
	}

	public H getHeight() {
		return height;
	}

	public void setHeight(H height) {
		this.height = height;
	}
}
