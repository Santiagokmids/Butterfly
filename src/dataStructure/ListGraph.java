package dataStructure;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;


public class ListGraph<U extends Comparable<ListVertice<V, U, H>>, V extends Comparable<V>, H extends Comparable<H>> implements IListGraph<U, V, H>{
	
	private ArrayList<ListVertice<V, U, H>> listVertice;
	private ArrayList<NodeK<V, U, H>> ensembleArrayList = new ArrayList<NodeK<V, U, H>>();
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
		V verticeOneFind = listVertice.get(verticeOne).getValue();
		V verticeTwoFind = listVertice.get(verticeTwo).getValue();
		
		for (int i = 0; i < listVertice.get(verticeOne).getAdjacency().size()-1; i++) {
			if(listVertice.get(verticeOne).getEdge().get(i).getInitVertice().getValue().compareTo(verticeOneFind) == 0 && listVertice.get(verticeOne).getEdge().get(i).getFinalVertice().getValue().compareTo(verticeTwoFind) == 0) {
				return listVertice.get(verticeOne).getEdge().get(i).getHeight();
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
	public int kruskal() {
		
		int cont = 0;
		Queue<ListEdge<U, V, H>> priorityQueue = priority();
		
		for (int i = 0; i < 9; i++) {
			makeset(listVertice.get(i));
		}
		
		for (int i = 0; i < priorityQueue.size()-1; i++) {
			
			ListEdge<U, V, H> priority = priorityQueue.poll();
			
			NodeK<V, U, H> nodeOne = findNode(priority.getInitVertice().getValue());
			NodeK<V, U, H> nodeTwo = findNode(priority.getFinalVertice().getValue());
			
			if(findSet(nodeOne) == findSet(nodeTwo)) {
				cont += (Integer) priority.getHeight();
				union(nodeOne, nodeTwo);
			}
			
			priorityQueue = secondPriority(priorityQueue);
		}
		
		return cont;
	}
	
	private Queue<ListEdge<U, V, H>> secondPriority(Queue<ListEdge<U, V, H>> priorityQueue) {
		
		Queue<ListEdge<U, V, H>> newQueue = new LinkedList<ListEdge<U,V,H>>();
		ArrayList<ListEdge<U, V, H>> newArrayList = new ArrayList<>();
		
		for (ListEdge<U, V, H> listEdge : priorityQueue) {
			newArrayList.add(listEdge);
		}
		
		newArrayList = organize(newArrayList);
		newQueue = intoQueue(newArrayList);
		
		return newQueue;
	}

	private Queue<ListEdge<U, V, H>> intoQueue(ArrayList<ListEdge<U, V, H>> arrayList) {

		ArrayList<ListEdge<U, V, H>> newArrayList = arrayList;
		Queue<ListEdge<U, V, H>> newQueue = new LinkedList<>();
		
		for (int i = 0; i < newArrayList.size()-1; i++) {
			newQueue.add(newArrayList.get(i));
		}
		
		return newQueue;
	}

	private void union(NodeK<V, U, H> nodeOne, NodeK<V, U, H> nodeTwo) {
		if (nodeOne.getVertice().getValue().compareTo(nodeTwo.getVertice().getValue()) < 0) {
			nodeTwo.setParent(nodeOne);
		}
	}

	private NodeK<V, U, H> findNode(V value) {
		
		boolean verify = false;
		
		for (int i = 0; i < ensembleArrayList.size()-1 && !verify; i++) {
			if(ensembleArrayList.get(i).getVertice().getValue().compareTo(value) == 0) {
				verify = true;
				return ensembleArrayList.get(i);
			}
		}
		return null;
	}

	public NodeK<V, U, H> findSet(NodeK<V, U, H> toFind){
		
		NodeK<V, U, H> newNode = toFind;
		
		if(toFind.getParent() != null) {
			return newNode = findSet(toFind.getParent());
		}
		
		return newNode;
	}
	
	public Queue<ListEdge<U, V, H>> priority() {
		
		ArrayList<ListVertice<V, U, H>> newArrayList = listVertice;
		ArrayList<ListEdge<U, V, H>> toOrganize = new ArrayList<>();
		Queue<ListEdge<U, V, H>> priorityQueue = new LinkedList<>();
		
		for (int i = 0; i < newArrayList.size()-1; i++) {
			for (int j = 0; j < newArrayList.get(i).getEdge().size()-1; j++) {
				toOrganize.add(newArrayList.get(i).getEdge().get(j));
			}
		}
		
		toOrganize = organize(toOrganize);
		priorityQueue = intoQueue(toOrganize);
		
		return priorityQueue;
	}

	private ArrayList<ListEdge<U, V, H>> organize(ArrayList<ListEdge<U, V, H>> toOrganize) {
		
		ArrayList<ListEdge<U, V, H>> newArrayList = toOrganize;
		
		for (int i = 1; i < newArrayList.size(); i++) {
			for (int j = i; j > 0 && newArrayList.get(j-1).getHeight().compareTo(newArrayList.get(j).getHeight()) > 0; j--) {
				ListEdge<U, V, H> newEdge = newArrayList.get(j);
				
				newArrayList.set(j, newArrayList.get(j-1));
				newArrayList.set(j-1, newEdge);
			}
		}
		
		return newArrayList;
	}
	
	public void makeset(ListVertice<V, U, H> vertice) {
		ensembleArrayList.add(new NodeK<V, U, H>(vertice));
	}
}
