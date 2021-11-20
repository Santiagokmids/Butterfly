package dataStructure;

public class Edge<U, V, H> {
	
	private U initVertice;
	private V finalVertice;
	private H height;
	
	public Edge(U initVertice, V finalVertice, H height) {
		this.initVertice = initVertice;
		this.finalVertice = finalVertice;
		this.height = height;
	}

	public U getInitVertice() {
		return initVertice;
	}

	public void setInitVertice(U initVertice) {
		this.initVertice = initVertice;
	}

	public V getFinalVertice() {
		return finalVertice;
	}

	public void setFinalVertice(V finalVertice) {
		this.finalVertice = finalVertice;
	}

	public H getHeight() {
		return height;
	}

	public void setHeight(H height) {
		this.height = height;
	}
}
