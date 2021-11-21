package dataStructure;

public class ListEdge<U, V, H> {
	
	private ListVertice<V, U, H> initVertice;
	private ListVertice<V, U, H> finalVertice;
	private H height;
	
	public ListEdge(ListVertice<V, U, H> initVertice, ListVertice<V, U, H>finalVertice, H height) {
		this.initVertice = initVertice;
		this.finalVertice = finalVertice;
		this.height = height;
	}

	public ListVertice<V, U, H> getInitVertice() {
		return initVertice;
	}

	public void setInitVertice(ListVertice<V, U, H> initVertice) {
		this.initVertice = initVertice;
	}

	public ListVertice<V, U, H> getFinalVertice() {
		return finalVertice;
	}

	public void setFinalVertice(ListVertice<V, U, H> finalVertice) {
		this.finalVertice = finalVertice;
	}

	public H getHeight() {
		return height;
	}

	public void setHeight(H height) {
		this.height = height;
	}
}
