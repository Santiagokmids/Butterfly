package dataStructure;

import java.util.ArrayList;

public class ListGraph<U extends Comparable<ListVertice<V, U, H>>, V extends Comparable<ListVertice<V, U, H>>, H> implements IListGraph<U, V, H>{
	
	private ArrayList<ListVertice<V, U, H>> listVertice;
	private int distance[][] = new int[10][10];

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
	public ListVertice<V, U, H> getVertice() {
		return null;
	}

	@Override
	public Edge<U, V, H> getEdge() {
		return null;
	}

	public ArrayList<ListVertice<V, U, H>> getListVertice() {
		return listVertice;
	}

	public void setListVertice(ArrayList<ListVertice<V, U, H>> listVertice) {
		this.listVertice = listVertice;
	}

	@Override
	public ArrayList<ListVertice<V, U, H>> bfs() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<ListVertice<V, U, H>> dfs() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<H> dijkstra() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void floyd() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (j == i) {
					distance[i][j] = 0;
				}else if (checkWeight(i, j) != null) {
					distance[i][j] = (int) checkWeight(i, j);
				}else {
					distance[i][j] = Integer.MAX_VALUE;
				}
			}
		}
		
		for (int k = 0; k < 9; k++) {
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < distance.length; j++) {
					if (distance[i][j] > distance[i][k] + distance[k][j]) {
						distance[i][j] = distance[i][k] + distance[k][j];
					}
				}
			}
		}
	}
	
	public H checkWeight(int verticeOne, int verticeTwo) {
		ListVertice<V, U, H> verticeOneFind = listVertice.get(verticeOne);
		ListVertice<V, U, H> verticeTwoFind = listVertice.get(verticeTwo);
		
		for (int i = 0; i < verticeOneFind.getEdge().size()-1; i++) {
			if(verticeOneFind.getEdge().get(i).getInitVertice().compareTo(verticeOneFind) == 0 && verticeOneFind.getEdge().get(i).getFinalVertice().compareTo(verticeTwoFind) == 0) {
				return verticeOneFind.getEdge().get(i).getHeight();
			}
		}
		return null;
	}

	@Override
	public H prim() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public H kruskal() {
		// TODO Auto-generated method stub
		return null;
	}
}
