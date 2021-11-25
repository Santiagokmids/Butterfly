package dataStructure;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class ListGraph<U extends Comparable<U>, V extends Comparable<V>, H extends Comparable<H>>
implements IListGraph<U, V, H> {

	private ArrayList<ListVertice<V, U, H>> listVertice;
	private ArrayList<NodeK<V, U, H>> ensembleArrayList;
	private int distance[][];

	@Override
	public void createGraph() {
		listVertice = new ArrayList<ListVertice<V, U, H>>();
		ensembleArrayList = new ArrayList<NodeK<V, U, H>>();
	}

	@Override
	public boolean addVertice(V value) {
		boolean noFound = true;
		
		for (int i = 0; i < listVertice.size() && noFound; i++) {
			if (listVertice.get(i).getValue().compareTo(value) == 0) {
				noFound = false;
			}
		}
		if (noFound) {
			ListVertice<V, U, H> newVertice = new ListVertice<V, U, H>(value);
			listVertice.add(newVertice);
			return true;
		}
		return false;
	}

	@Override
	public boolean addEdge(V valueIni, V valueEnd, H height) {
		int positionA = 0;
		int positionB = 0;
		boolean foundA = false;
		boolean foundB = false;

		for (int i = 0; i < listVertice.size(); i++) {
			if (listVertice.get(i).getValue().compareTo(valueIni) == 0) {
				foundA = true;
				positionA = i;
			}
			if (listVertice.get(i).getValue().compareTo(valueEnd) == 0) {
				foundB = true;
				positionB = i;
			}
			if (foundA == true && foundB == true) {
				i = listVertice.size();
			}
		}
		if (foundA == true && foundB == true && (int)height >= 0) {
			listVertice.get(positionA).getAdjacency().add(listVertice.get(positionB).getValue());
			ListEdge<U, V, H> list = new ListEdge<U,V,H>(listVertice.get(positionA),listVertice.get(positionB),height);
			return listVertice.get(positionA).addEdge(list);
		}
		return false;
	}
	
	@Override
	public H searchEdge(V verticeInit, V verticeEnd) {

		boolean stop = false;
		H weight = null; 

		for (int i = 0; i < listVertice.size() && !stop; i++) {
			if(verticeInit.compareTo(listVertice.get(i).getValue()) == 0) {

				for (int k = 0; k < listVertice.get(i).getEdge().size() && !stop; k++) {
					if(verticeEnd.compareTo(listVertice.get(i).getEdge().get(k).getFinalVertice().getValue()) == 0) {
						stop = true;
						weight = listVertice.get(i).getEdge().get(k).getHeight();
					}
				}
			}
		}
		return weight;
	}
	
	@Override
	public V searchVertice(V vertice) {

		boolean stop = false;
		V ver = null;
		
		for (int i = 0; i < listVertice.size() && !stop; i++) {
			if(vertice.compareTo(listVertice.get(i).getValue()) == 0) {
				stop = true;
				ver = vertice;
			}
		}
		return ver;
	}

	@Override
	public boolean deleteVertice(V verticeToDelete) {
		boolean verify = false;

		for (int i = 0; i < listVertice.size(); i++) {
			if(listVertice.get(i).getValue().compareTo(verticeToDelete) == 0) {
				listVertice.remove(i);
				
				if(deleteAdjacency(verticeToDelete) && deleteEdge(verticeToDelete)) {
					verify = true;
				}
			}
		}
		return verify;
	}
	
	@Override
	public boolean setEdge(V verticeInit, V verticeEnd, H weight) {
		
		boolean stop = false, verify = false;
		int index = -1;
		
		for (int i = 0; i < listVertice.size() && !stop; i++) {
			if(verticeInit.compareTo(listVertice.get(i).getValue()) == 0) {
				stop = true;
				index = i;
			}
		}
		
		stop = false;
		
		if (index >= 0) {
			for (int i = 0; i < listVertice.get(index).getEdge().size() && !stop; i++) {
				if(listVertice.get(index).getEdge().get(i).getFinalVertice().getValue().compareTo(verticeEnd) == 0) {
					listVertice.get(index).getEdge().get(i).setHeight(weight);
					stop = true;
					verify = true;
				}
			}
		}
		return verify;
	}

	public boolean deleteAdjacency(V verticeToDelete) {

		boolean stop = false;

		for (int i = 0; i < listVertice.size(); i++) {

			for (int j = 0; j < listVertice.get(i).getAdjacency().size(); j++) {
				if(verticeToDelete.compareTo(listVertice.get(i).getAdjacency().get(j)) == 0) {
					listVertice.get(i).getAdjacency().remove(j);
					stop = true;
				}
			}
		}
		return stop;
	}

	public boolean deleteEdge(V verticeToDelete) {

		boolean stop = false;

		for (int i = 0; i < listVertice.size(); i++) {

			for (int j = 0; j < listVertice.get(i).getEdge().size(); j++) {
				if(verticeToDelete.compareTo(listVertice.get(i).getEdge().get(j).getInitVertice().getValue()) == 0 || 
						verticeToDelete.compareTo(listVertice.get(i).getEdge().get(j).getFinalVertice().getValue()) == 0) {
					
					listVertice.get(i).getEdge().remove(j);
					stop = true;
				}
			}
		}
		return stop;
	}

	@Override
	public boolean deleteEdge(V verticeInit, V verticeEnd, H weight) {
		boolean stop = false;

		for (int i = 0; i < listVertice.size() && !stop; i++) {

			for (int j = 0; j < listVertice.get(i).getEdge().size() && !stop; j++) {
				if(verticeInit.compareTo(listVertice.get(i).getEdge().get(j).getInitVertice().getValue()) == 0 && verticeEnd.compareTo(listVertice.get(i).getEdge().get(j).getFinalVertice().getValue()) == 0 
						&& weight.compareTo(listVertice.get(i).getEdge().get(j).getHeight()) == 0) {
					
					listVertice.get(i).getEdge().remove(j);
					stop = true;
				}
			}
		}
		return stop;
	}

	@Override
	public ArrayList<ListVertice<V, U, H>> getVertice() {
		return listVertice;
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
	public ArrayList<ListVertice<V, U, H>> bfs(V v) {
		boolean found = false;
		ArrayList<ListVertice<V, U, H>> vertic = new ArrayList<ListVertice<V, U, H>>();
		int position =0;
		for(int i =0;i<listVertice.size()&& found == false;i++) {
			if(listVertice.get(i).getValue().compareTo(v)==0) {
				position = i;
				found = true;
			}
		}
		for(int i = 0; i < listVertice.size();i++) {
			listVertice.get(i).setColor(0);
		}
		if(found ==true) {
			bfs(vertic,listVertice.get(position));
			return listVertice;
		}
		return null;
	}

	public void bfs(ArrayList<ListVertice<V, U, H>>vertic,ListVertice<V, U, H> e) {	
		ArrayList<ListVertice<V, U, H>>verticeO = new ArrayList<ListVertice<V, U, H>>();
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
			if(verticeO.get(i).getColor()!=2)
				bfs(vertic, verticeO.get(i));
		}
	}

	@Override
	public ArrayList<ListVertice<V, U, H>> dfs(V v) {
		boolean found = false;
		int position = 0;
		ArrayList<ListVertice<V, U, H>>vertic = new ArrayList<ListVertice<V, U, H>>();
		for(int i=0;i< listVertice.size() ;i++) {
			listVertice.get(i).setVisited(false);
		}
		for(int i=0;i< listVertice.size() && found ==false;i++) {
			if(listVertice.get(i).getValue().compareTo(v)==0) {
				position = i;
				found = true;
			}
		}
		vertic.add(listVertice.get(position));
		dfs(vertic, listVertice.get(position));
		return vertic;
	}

	public void dfs(ArrayList<ListVertice<V, U, H>> vertic,ListVertice<V, U, H> e) {
		ArrayList<ListEdge<U, V, H>> verticeO = e.getEdge();
		for(int i =0;i<verticeO.size();i++) {
			if(verticeO.get(i).getFinalVertice().isVisited() == false) {			
				vertic.add(verticeO.get(i).getFinalVertice());
				verticeO.get(i).getFinalVertice().setVisited(true);
				dfs(vertic, verticeO.get(i).getFinalVertice());
			}
		}

	}

	public int makeDijkstra(ListVertice<V, U, H> start, ListVertice<V, U, H> end) {
		ArrayList<H> dijkstra = dijkstra(start);
		ArrayList<ListVertice<V, U, H>> reference = assignRef();
		int weight = 0;

		for (int i = 0; i < reference.size(); i++) {
			if (reference.get(i).getValue().compareTo(end.getValue()) == 0) {
				weight = (int) dijkstra.get(i);
			}
		}
		return weight;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<H> dijkstra(ListVertice<V, U, H> start) {

		ArrayList<Integer> dist = new ArrayList<>();
		ArrayList<Integer> distances = new ArrayList<>();
		ArrayList<ListVertice<V, U, H>> prev = new ArrayList<>();
		Queue<ListVertice<V, U, H>> queue = new LinkedList<>();
		ArrayList<ListVertice<V, U, H>> reference = new ArrayList<>();
		
		dist = assignSource(start, dist);
		distances = assignSource(start, distances);
		prev = assignPrev(prev);
		queue = assignQueue(queue);
		reference = assignRef();
		int cont = 0;
		
		while (!queue.isEmpty()) {

			int weight = 0;

			queue = extractMin(queue, (ArrayList<Integer>)dist.clone(), (ArrayList<ListVertice<V, U, H>>)reference.clone(),cont);
			
			ListVertice<V, U, H> value = queue.peek();
			
			int index = searchReference(value, reference);
			
			if(listVertice.get(index).getEdge().size() > 0) {
				
				for (int i = 0; i < listVertice.get(index).getEdge().size(); i++) {
					
					int finalVertice = searchReference(value.getEdge().get(i).getFinalVertice(), reference);
					
					if(finalVertice != -1 && dist.get(index) != Integer.MAX_VALUE) {

						weight += dist.get(index) + (Integer) value.getEdge().get(i).getHeight();

						if (weight < dist.get(finalVertice)) {
							distances.set(finalVertice, weight);
							dist.set(finalVertice, weight);
							prev.set(finalVertice, value);
							queue.poll();
						}
					}else {
						queue.poll();
					}
				}
				cont++;
			}else {
				queue.clear();
			}
		}

		return (ArrayList<H>) dist;
	}

	private ArrayList<Integer> assignSource(ListVertice<V, U, H> start, ArrayList<Integer> dist) {

		for (int i = 0; i < listVertice.size(); i++) {

			if (start.getValue().compareTo(listVertice.get(i).getValue()) == 0) {
				dist.add(i, 0);
				
			} else {
				dist.add(Integer.MAX_VALUE);
			}
		}
		return dist;
	}

	private ArrayList<ListVertice<V, U, H>> assignPrev(ArrayList<ListVertice<V, U, H>> prev) {

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

	private Queue<ListVertice<V, U, H>> extractMin(Queue<ListVertice<V, U, H>> queue, ArrayList<Integer> dist, ArrayList<ListVertice<V, U, H>> reference ,int cont) {

		for (int i = 0; i < dist.size(); i++) {
			int min = dist.get(i);
			ListVertice<V, U, H> minim = reference.get(i);

			for (int j = i + 1; j < dist.size(); j++) {

				if (dist.get(j) < min) {
					int temp = dist.get(j);
					ListVertice<V, U, H> temporal = reference.get(j);
					dist.set(j, min);
					reference.set(j, minim);
					min = temp;
					minim = temporal;
				}
			}
			dist.set(i, min);
			reference.set(i, minim);
		}
		
		queue.clear();

		for (int i = cont; i < reference.size(); i++) {
			queue.add(reference.get(i));
		}
		return queue;
	}

	private ArrayList<ListVertice<V, U, H>> assignRef() {

		ArrayList<ListVertice<V, U, H>> ref = new ArrayList<ListVertice<V, U, H>>();

		for (int i = 0; i < listVertice.size(); i++) {
			ref.add(listVertice.get(i));
		}
		return ref;
	}

	private int searchReference(ListVertice<V, U, H> vert, ArrayList<ListVertice<V, U, H>> reference) {
		int index = 0;

		for (int i = 0; i < reference.size(); i++) {
			if (reference.get(i) == vert) {
				index = i;
			}
		}
		return index;
	}

	@Override
	public void floyd() {

		if (!listVertice.isEmpty()) {
			
			distance = new int[listVertice.size()][listVertice.size()];
			
			distance = toInfinte(distance);
			
			distance = toZero(distance);
			
			distance = toWeight(distance);
			
			for (int k = 0; k < listVertice.size(); k++) {
				for (int i = 0; i < listVertice.size(); i++) {
					for (int j = 0; j < listVertice.size(); j++) {
						if (distance[i][k] != Integer.MAX_VALUE && distance[k][j] != Integer.MAX_VALUE && distance[i][j] > (distance[i][k] + distance[k][j])) {
							distance[i][j] = distance[i][k] + distance[k][j];
						}
					}
				}
			}
		}
	}

	private int[][] toWeight(int[][] distance) {
		
		int[][] newMatrix = distance;	
		
		for (int i = 0; i < newMatrix.length; i++) {
			for (int j = 0; j < newMatrix.length; j++) {
				if (checkWeight(i, j) != null) {
					newMatrix[i][j] = (int) checkWeight(i, j);
				}
			}
		}
		
		return newMatrix;
	}

	private int[][] toZero(int[][] distance) {
		
		int[][] newMatrix = distance;	
		
		for (int i = 0; i < newMatrix.length; i++) {
			for (int j = 0; j < newMatrix.length; j++) {
				if (i == j) {
					newMatrix[i][j] = 0;
				}
			}
		}
		return newMatrix;
	}

	private int[][] toInfinte(int[][] distance) {
		
		int[][] newMatrix = distance;	
		
		for (int i = 0; i < newMatrix.length; i++) {
			for (int j = 0; j < newMatrix.length; j++) {
				newMatrix[i][j] = Integer.MAX_VALUE;
			}
		}
		return newMatrix;
	}

	public H checkWeight(int verticeOne, int verticeTwo) {
		V verticeOneFind = listVertice.get(verticeOne).getValue();
		V verticeTwoFind = listVertice.get(verticeTwo).getValue();

		if (listVertice.get(verticeOne).getAdjacency() != null && listVertice.get(verticeOne).getEdge() != null) {
			for (int i = 0; i < listVertice.get(verticeOne).getEdge().size(); i++) {
				if (listVertice.get(verticeOne).getEdge().get(i).getInitVertice().getValue().compareTo(verticeOneFind) == 0
						&& listVertice.get(verticeOne).getEdge().get(i).getFinalVertice().getValue()
						.compareTo(verticeTwoFind) == 0) {
					return listVertice.get(verticeOne).getEdge().get(i).getHeight();
				}
			}
		}

		return null;
	}

	@Override
	public int prim(ListVertice<V, U, H> start) {

		ArrayList<Integer> dist = new ArrayList<>();
		ArrayList<ListVertice<V, U, H>> prev = new ArrayList<>();
		Queue<ListVertice<V, U, H>> queue = new LinkedList<>();

		dist = assignSource(start, dist);
		prev = assignPrev(prev);
		queue = assignQueue(queue);

		ArrayList<Boolean> colors = assignColors();
		ArrayList<ListVertice<V, U, H>> reference = assignRef();

		while (!queue.isEmpty()) {

			int weight = 0;
			queue = extractMin(queue, dist);
			ListVertice<V, U, H> value = queue.peek();
			int index = searchReference(value, reference);

			for (int i = 0; i < value.getEdge().size(); i++) {
				int finalVertice = searchReference(value.getEdge().get(i).getFinalVertice(), reference);
				weight += dist.get(index) + (Integer) value.getEdge().get(index).getHeight();

				if (weight < dist.get(finalVertice) && !colors.get(i)) {
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

		for (int i = 0; i < listVertice.size(); i++) {
			colors.add(false);
		}

		return colors;
	}

	@Override
	public int kruskal() {

		int cont = 0;

		if (!listVertice.isEmpty()) {

			Queue<ListEdge<U, V, H>> priorityQueue = priority();

			for (int i = 0; i < listVertice.size(); i++) {
				makeset(listVertice.get(i));
			}
			cont = kruskalOperation(priorityQueue, 0, 0);
		}

		return cont;
	}

	private int kruskalOperation(Queue<ListEdge<U, V, H>> priorityQueue, int contVertice, int contT) {
		
		if(!priorityQueue.isEmpty() && contVertice < listVertice.size()-1) {

			ListEdge<U, V, H> priority = priorityQueue.poll();
			
			NodeK<V, U, H> nodeOne = findNode(priority.getInitVertice().getValue());
			NodeK<V, U, H> nodeTwo = findNode(priority.getFinalVertice().getValue());
			
			if (findSet(nodeOne) != findSet(nodeTwo)) {
				union(nodeOne, nodeTwo);
				priorityQueue = secondPriority(priorityQueue);
				return kruskalOperation(priorityQueue, contVertice+1, contT+(Integer) priority.getHeight());
			}else {
				priorityQueue = secondPriority(priorityQueue);
				return kruskalOperation(priorityQueue, contVertice, contT);
			}
		}
		return contT;
	}

	private Queue<ListEdge<U, V, H>> secondPriority(Queue<ListEdge<U, V, H>> priorityQueue) {

		Queue<ListEdge<U, V, H>> newQueue = new LinkedList<ListEdge<U, V, H>>();
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

		for (int i = 0; i < newArrayList.size(); i++) {
			newQueue.add(newArrayList.get(i));
		}

		return newQueue;
	}

	private void union(NodeK<V, U, H> nodeOne, NodeK<V, U, H> nodeTwo) {
		nodeTwo.setParent(nodeOne);
	}

	private NodeK<V, U, H> findNode(V value) {

		boolean verify = false;

		for (int i = 0; i < ensembleArrayList.size() && !verify; i++) {
			if (ensembleArrayList.get(i).getVertice().getValue().compareTo(value) == 0) {
				verify = true;
				return ensembleArrayList.get(i);
			}
		}
		return null;
	}

	public NodeK<V, U, H> findSet(NodeK<V, U, H> toFind) {

		NodeK<V, U, H> newNode = toFind;

		if (toFind != null && toFind.getParent() != null) {
			return newNode = findSet(toFind.getParent());
		}

		return newNode;
	}

	public Queue<ListEdge<U, V, H>> priority() {

		ArrayList<ListVertice<V, U, H>> newArrayList = listVertice;
		ArrayList<ListEdge<U, V, H>> toOrganize = new ArrayList<>();
		Queue<ListEdge<U, V, H>> priorityQueue = new LinkedList<>();

		for (int i = 0; i < newArrayList.size(); i++) {
			for (int j = 0; j < newArrayList.get(i).getEdge().size(); j++) {
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
			for (int j = i; j > 0
					&& newArrayList.get(j - 1).getHeight().compareTo(newArrayList.get(j).getHeight()) > 0; j--) {
				ListEdge<U, V, H> newEdge = newArrayList.get(j);

				newArrayList.set(j, newArrayList.get(j - 1));
				newArrayList.set(j - 1, newEdge);
			}
		}

		return newArrayList;
	}

	public void makeset(ListVertice<V, U, H> vertice) {
		ensembleArrayList.add(new NodeK<V, U, H>(vertice));
	}

	public int[][] getDistance() {
		return distance;
	}

}
