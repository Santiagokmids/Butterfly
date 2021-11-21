package dataStructure;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class MatrixGraph<V extends Comparable <V>, U, H extends Comparable<H>> implements IMatrixGraph<U, V, H>{
	
	public ArrayList<Vertice<V, U, H>> vertice;
	private ArrayList<NodeM<V, U, H>> ensembleArrayList = new ArrayList<NodeM<V, U, H>>();
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
	public Vertice<V, U, H> getVertice() {
		return null;
	}

	@Override
	public Edge<U, V, H> getEdges() {
		return null;
	}

	@Override
	public ArrayList<Vertice<V, U, H>> bfs(V v) {
		boolean found = false;
		int position =0;
		for(int i =0;i<vertice.size()&& found == false;i++) {
			if(vertice.get(i).getValue().compareTo(v)==0) {
				position = i;
				found = true;
			}
		}
		for(int i = 0; i < vertice.size();i++) {
			vertice.get(i).setColor(0);
		}
		if(found ==true) {
			
		}
		return null;
	}
	
	public void bfs(ArrayList<Vertice<V, U, H>> vertic,Vertice<V, U, H> e) {	
		ArrayList<Vertice<V, U, H>> verticeO = new ArrayList<Vertice<V,U,H>>();
		for(int i = 0 ; i <e.getEdge().size(); i++) {
			verticeO.add(e.getEdge().get(i).getFinalVertice() );
		}
		
		for(int i =0; i< e.getEdge().size();i++) {
			if(verticeO.get(i).getColor() == 0) {
				vertic.add(verticeO.get(i));
				verticeO.get(i).setColor(1);
			}
		
		}
		e.setColor(2);
		for(int i = 0; i<verticeO.size();i++) {
			bfs(vertic, verticeO.get(i));
		}
	}
	
	@Override
	public ArrayList<Vertice<V, U, H>>dfs(V v ) {
		boolean found = false;
		int position = 0;
		
		for(int i=0;i< vertice.size()-1 ;i++) {
			vertice.get(i).setVisited(false);
		}
		for(int i=0;i< vertice.size()-1 && found ==false;i++) {
			if(vertice.get(i).getValue().compareTo(v)==0) {
				position = i;
				found = true;
			}
		}
		
		return null;
	}
	
	public void dfs(ArrayList<Vertice<V, U, H>> vertic,Vertice e) {
		ArrayList<Vertice<V, U, H>> verticeO = e.getEdge();
		for(int i =0;i<verticeO.size();i++) {
			if(verticeO.get(i).isVisited() == false) {
				verticeO.get(i).setVisited(true);
				vertic.add(verticeO.get(i));
				dfs(vertic, verticeO.get(i));
			}
		}
			
	}
	
	public int makeDijkstra(Vertice<V, U, H> start, Vertice<V, U, H> end) {
		ArrayList<H> dijkstra = dijkstra(start);
		ArrayList<Vertice<V, U, H>> reference = assignRef();
		int weight = 0;
		
		for (int i = 0; i < reference.size(); i++) {
			if(reference.get(i).getValue().compareTo(end.getValue()) == 0) {
				weight = (int) dijkstra.get(i);
			}
		}
		return weight;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<H> dijkstra(Vertice<V, U, H> start) {
		
		ArrayList<Integer> dist = new ArrayList<>();
		ArrayList<Vertice<V, U, H>> prev = new ArrayList<>();
		Queue<Vertice<V, U, H>> queue = new LinkedList<>();

		dist = assignSource(start, dist);
		prev = assignPrev(prev);
		queue = assignQueue(queue);
		ArrayList<Vertice<V, U, H>> reference = assignRef();

		while(!queue.isEmpty()) {
			
			int weight = 0;
			queue = extractMin(queue, dist);
			Vertice<V, U, H> value = queue.peek();
			int index = searchReference(value, reference);
			
			for (int i = 0; i < value.getEdge().size(); i++) {
				int finalVertice = searchReference(value.getEdge().get(i).getFinalVertice(), reference);
				weight += dist.get(index) + (Integer)value.getEdge().get(index).getHeight();

				if(weight < dist.get(finalVertice)) {
					dist.set(finalVertice, weight);
					prev.set(finalVertice, value);
					queue.poll();
				}
			}
		}
		
		return (ArrayList<H>) dist;
	}

	private ArrayList<Integer> assignSource(Vertice<V, U, H> start, ArrayList<Integer> dist){

		for (int i = 0; i < vertice.size(); i++) {

			if(start.getValue().compareTo(vertice.get(i).getValue()) == 0) {
				dist.add(i, 0);

			}else {
				dist.add(Integer.MAX_VALUE);
			}
		}
		return dist;
	}

	private ArrayList<Vertice<V, U, H>> assignPrev(ArrayList<Vertice<V, U, H>> prev){

		for (int i = 0; i < vertice.size(); i++) {
			prev.add(null);
		}

		return prev;
	}

	private Queue<Vertice<V, U, H>> assignQueue(Queue<Vertice<V, U, H>> queue) {

		for (int i = 0; i < vertice.size(); i++) {
			queue.add(vertice.get(i));
		}

		return queue;
	}

	private Queue<Vertice<V, U, H>> extractMin(Queue<Vertice<V, U, H>> queue, ArrayList<Integer> dist) {
		
		ArrayList<Vertice<V, U, H>> values = new ArrayList<>();
		
		for (int i = 0; i < dist.size(); i++) {
			values.add(queue.poll());
		}
		
		for(int i = 0;i < dist.size();i++) {
			int min = dist.get(i);
			Vertice<V, U, H> minim = values.get(i);
			
			for(int j = i+1;j < dist.size();j++) {
				
				if(dist.get(j) < min) {
					int temp = dist.get(j);
					Vertice<V, U, H> temporal = values.get(j);
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
	
	private ArrayList<Vertice<V, U, H>> assignRef(){
		
		ArrayList<Vertice<V, U, H>> ref = new ArrayList<Vertice<V, U, H>>();
		
		for (int i = 0; i < vertice.size(); i++) {
			ref.add(vertice.get(i));
		}
		return ref;
	}
	
	private int searchReference(Vertice<V, U, H> vert, ArrayList<Vertice<V, U, H>> reference) {
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
		V verticeOneFind = vertice.get(verticeOne).getValue();
		V verticeTwoFind = vertice.get(verticeTwo).getValue();
		
		for (int i = 0; i < vertice.get(verticeOne).getEdge().size()-1; i++) {
			if(vertice.get(verticeOne).getEdge().get(i).getInitVertice().getValue().compareTo(verticeOneFind) == 0 && vertice.get(verticeOne).getEdge().get(i).getFinalVertice().getValue().compareTo(verticeTwoFind) == 0) {
				return vertice.get(verticeOne).getEdge().get(i).getHeight();
			}
		}
		return null;
	}

	@Override
	public int prim(Vertice<V, U, H> start) {
		
		ArrayList<Integer> dist = new ArrayList<>();
		ArrayList<Vertice<V, U, H>> prev = new ArrayList<>();
		Queue<Vertice<V, U, H>> queue = new LinkedList<>();

		dist = assignSource(start, dist);
		prev = assignPrev(prev);
		queue = assignQueue(queue);
		
		ArrayList<Boolean> colors = assignColors();
		ArrayList<Vertice<V, U, H>> reference = assignRef();

		while(!queue.isEmpty()) {
			
			int weight = 0;
			queue = extractMin(queue, dist);
			Vertice<V, U, H> value = queue.peek();
			int index = searchReference(value, reference);
			
			for (int i = 0; i < value.getEdge().size(); i++) {
				int finalVertice = searchReference(value.getEdge().get(i).getFinalVertice(), reference);
				weight += dist.get(index) + (Integer)value.getEdge().get(index).getHeight();

				if(weight < dist.get(finalVertice) && !colors.get(i)) {
					dist.set(finalVertice, weight);
					prev.set(finalVertice, value);
					queue.poll();
				}
			}
			colors.set(index, true);
		}
		
		int amount = 0;
		
		for (int i = 0; i < dist.size(); i++) {
			amount += dist.get(i);
		} 
		
		return amount;
	}
	
	private ArrayList<Boolean> assignColors() {
		
		ArrayList<Boolean> colors = new ArrayList<>();
		
		for (int i = 0; i < vertice.size(); i++) {
			colors.add(false);
		}
		
		return colors;
	}


	@Override
	public int kruskal() {
		
		int cont = 0;
		Queue<Edge<U, V, H>> priorityQueue = priority();
		
		for (int i = 0; i < 9; i++) {
			makeset(vertice.get(i));
		}
		
		for (int i = 0; i < priorityQueue.size()-1; i++) {
			
			Edge<U, V, H> priority = priorityQueue.poll();
			
			NodeM<V, U, H> nodeOne = findNode(priority.getInitVertice().getValue());
			NodeM<V, U, H> nodeTwo = findNode(priority.getFinalVertice().getValue());
			
			if(findSet(nodeOne) == findSet(nodeTwo)) {
				cont += (Integer) priority.getHeight();
				union(nodeOne, nodeTwo);
			}
			
			priorityQueue = secondPriority(priorityQueue);
		}
		
		return cont;
	}

	public Queue<Edge<U, V, H>> priority() {
		
		ArrayList<Vertice<V, U, H>> newArrayList = vertice;
		ArrayList<Edge<U, V, H>> toOrganize = new ArrayList<>();
		Queue<Edge<U, V, H>> priorityQueue = new LinkedList<>();
		
		for (int i = 0; i < newArrayList.size()-1; i++) {
			for (int j = 0; j < newArrayList.get(i).getEdge().size()-1; j++) {
				toOrganize.add(newArrayList.get(i).getEdge().get(j));
			}
		}
		
		toOrganize = organize(toOrganize);
		priorityQueue = intoQueue(toOrganize);
		
		return priorityQueue;
	}
	
	private ArrayList<Edge<U, V, H>> organize(ArrayList<Edge<U, V, H>> toOrganize) {
		
		ArrayList<Edge<U, V, H>> newArrayList = toOrganize;
		
		for (int i = 1; i < newArrayList.size(); i++) {
			for (int j = i; j > 0 && newArrayList.get(j-1).getHeight().compareTo(newArrayList.get(j).getHeight()) > 0; j--) {
				Edge<U, V, H> newEdge = newArrayList.get(j);
				
				newArrayList.set(j, newArrayList.get(j-1));
				newArrayList.set(j-1, newEdge);
			}
		}
		
		return newArrayList;
	}

	private Queue<Edge<U, V, H>> intoQueue(ArrayList<Edge<U, V, H>> arrayList) {

		ArrayList<Edge<U, V, H>> newArrayList = arrayList;
		Queue<Edge<U, V, H>> newQueue = new LinkedList<>();
		
		for (int i = 0; i < newArrayList.size()-1; i++) {
			newQueue.add(newArrayList.get(i));
		}
		
		return newQueue;
	}
	
	public void makeset(Vertice<V, U, H> vertice) {
		ensembleArrayList.add(new NodeM<V, U, H>(vertice));
	}
	
	private NodeM<V, U, H> findNode(V value) {
		
		boolean verify = false;
		
		for (int i = 0; i < ensembleArrayList.size()-1 && !verify; i++) {
			if(ensembleArrayList.get(i).getVertice().getValue().compareTo(value) == 0) {
				verify = true;
				return ensembleArrayList.get(i);
			}
		}
		return null;
	}
	
	public NodeM<V, U, H> findSet(NodeM<V, U, H> toFind){
		
		NodeM<V, U, H> newNode = toFind;
		
		if(toFind.getParent() != null) {
			return newNode = findSet(toFind.getParent());
		}
		
		return newNode;
	}
	
	private void union(NodeM<V, U, H> nodeOne, NodeM<V, U, H> nodeTwo) {
		if (nodeOne.getVertice().getValue().compareTo(nodeTwo.getVertice().getValue()) < 0) {
			nodeTwo.setParent(nodeOne);
		}
	}
	
	private Queue<Edge<U, V, H>> secondPriority(Queue<Edge<U, V, H>> priorityQueue) {
		
		Queue<Edge<U, V, H>> newQueue = new LinkedList<>();
		ArrayList<Edge<U, V, H>> newArrayList = new ArrayList<>();
		
		for (Edge<U, V, H> listEdge : priorityQueue) {
			newArrayList.add(listEdge);
		}
		
		newArrayList = organize(newArrayList);
		newQueue = intoQueue(newArrayList);
		
		return newQueue;
	}
}
