package dataStructure;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class ListGraph<U extends Comparable<ListVertice<V, U, H>>, V extends Comparable<V>, H> implements IListGraph<U, V, H>{
	
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

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<H> dijkstra(ListVertice<V, U, H> start, ListVertice<V, U, H> Final) {
		
		ArrayList<Integer> dist = new ArrayList<>();
		ArrayList<ListVertice<V, U, H>> prev = new ArrayList<>();
		Queue<ListVertice<V, U, H>> queue = new LinkedList<>();
		ArrayList<ListVertice<V, U, H>> reference = new ArrayList<>();

		dist = assignSource(start, dist);
		prev = assignPrev(prev);
		queue = assignQueue(queue);
		reference = assignRef();

		while(!queue.isEmpty()) {
			
			int weight = 0;
			queue = extractMin(queue, dist);
			ListVertice<V, U, H> value = queue.poll();
			int index = searchReference(value, reference);
			
			for (int i = 0; i < value.getEdge().size(); i++) {
				int finalVertice = searchReference(value.getEdge().get(i).getFinalVertice(), reference);
				weight += dist.get(index) + (Integer)value.getEdge().get(index).getHeight();

				if(weight < dist.get(finalVertice)) {
					dist.set(finalVertice, weight);
					prev.set(finalVertice, value);
				}
			}
		}

		return (ArrayList<H>) dist;
	}

	private ArrayList<Integer> assignSource(ListVertice<V, U, H> start, ArrayList<Integer> dist){

		for (int i = 0; i < listVertice.size(); i++) {

			if(start.getValue().compareTo(listVertice.get(i).getValue()) == 0) {
				dist.add(i, 0);

			}else {
				dist.add(Integer.MAX_VALUE);
			}
		}
		return dist;
	}

	private ArrayList<ListVertice<V, U, H>> assignPrev(ArrayList<ListVertice<V, U, H>> prev){

		for (int i = 0; i < listVertice.size(); i++) {
			prev.add(null);
		}

		return prev;
	}

	private Queue<ListVertice<V, U, H>> assignQueue(Queue<ListVertice<V, U, H>> queue) {

		for (int i = 0; i < listVertice.size(); i++) {
			queue.add(listVertice.get(i));
		}

		return queue;
	}

	private Queue<ListVertice<V, U, H>> extractMin(Queue<ListVertice<V, U, H>> queue, ArrayList<Integer> dist) {
		
		ArrayList<ListVertice<V, U, H>> values = new ArrayList<>();
		
		for (int i = 0; i < dist.size(); i++) {
			values.add(queue.poll());
		}
		
		for(int i = 0;i < dist.size();i++) {
			int min = dist.get(i);
			ListVertice<V, U, H> minim = values.get(i);
			
			for(int j = i+1;j < dist.size();j++) {
				
				if(dist.get(j) < min) {
					int temp = dist.get(j);
					ListVertice<V, U, H> temporal = values.get(j);
					dist.set(j, min);
					values.set(j, minim);
					min = temp;
					minim = temporal;
				}
			}
			dist.set(i, min);
			values.set(i, minim);
		}
		
		queue.clear();
		
		for (int i = 0; i < values.size(); i++) {
			queue.add(values.get(i));
		}
		return queue;
	}
	
	private ArrayList<ListVertice<V, U, H>> assignRef(){
		
		ArrayList<ListVertice<V, U, H>> ref = new ArrayList<ListVertice<V, U, H>>();
		
		for (int i = 0; i < listVertice.size(); i++) {
			ref.add(listVertice.get(i));
		}
		return ref;
	}
	
	private int searchReference(ListVertice<V, U, H> vert, ArrayList<ListVertice<V, U, H>> reference) {
		int index = 0;
		
		for (int i = 0; i < reference.size(); i++) {
			if(reference.get(i) == vert) {
				index = i;
			}
		}
		return index;
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
