package dataStructure;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class MatrixGraph<V extends Comparable<V>, U, H extends Comparable<H>> implements IMatrixGraph<U, V, H> {

	public ArrayList<Vertice<V, U, H>> vertice;
	public ArrayList<Edge<U, V, H>> edges;
	public Vertice<V, U, H> first;
	private ArrayList<NodeM<V, U, H>> ensembleArrayList = new ArrayList<NodeM<V, U, H>>();
	private int distance[][];

	@Override
	public void createGraph() {
		vertice = new ArrayList<Vertice<V, U, H>>();
		edges = new ArrayList<Edge<U, V, H>>();
	}

	@Override
	public boolean addVertice(V value) {
		Vertice<V, U, H> vertic = new Vertice<V, U, H>(value);
		vertice.add(vertic);

		Vertice<V, U, H> vertix = new Vertice<V, U, H>(null);
		createMatrix(vertix);
		reloadEdges();
		return true;
	}

	private void reloadEdges() {
		if (!edges.isEmpty()) {
			for (int i = 0; i < edges.size(); i++) {
				addEdge(findPosition(edges.get(i).getInitVertice()), findPosition(edges.get(i).getFinalVertice()),
						edges.get(i).getHeight());
			}
		}
	}

	public int findPosition(Vertice<V, U, H> value) {
		int position = 0;

		for (int i = 0; i < vertice.size(); i++) {
			if (vertice.get(i).getValue().compareTo(value.getValue()) == 0) {
				position = i;
			}
		}

		return position;
	}

	public Vertice<V, U, H> findVertice() {
		return null;
	}

	private void createMatrix(Vertice<V, U, H> current) {

		if (first == null && !vertice.isEmpty()) {
			first = vertice.get(0);
		} else {
			createMatrix(first, first, current, 0, 0);
		}
	}

	private void createMatrix(Vertice<V, U, H> dynamicV, Vertice<V, U, H> dynamic, Vertice<V, U, H> current, int cont,
			int contV) {

		if (dynamic.getNext() == null) {

			if (cont < vertice.size()) {
				Vertice<V, U, H> newVertice = new Vertice<V, U, H>(null);
				dynamic.setNext(newVertice);
				createMatrix(dynamicV, dynamic.getNext(), newVertice, ++cont, contV);

			} else if (dynamicV.getDown() == null) {
				if (contV < vertice.size()) {
					Vertice<V, U, H> newVertice = new Vertice<V, U, H>(null);
					dynamicV.setDown(newVertice);
					createMatrix(dynamicV.getDown(), dynamicV.getDown(), newVertice, 0, ++contV);
				}
			} else {
				createMatrix(dynamicV.getDown(), dynamicV.getDown(), current, 0, ++contV);
			}
		} else {
			createMatrix(dynamicV, dynamic.getNext(), current, ++cont, contV);
		}
	}

	@Override
	public boolean addEdge(V valueIni, V valueEnd, H height) {
		int positionA = 0;
		int positionB = 0;
		boolean foundA = false;
		boolean foundB = false;
		boolean verify = false;

		for (int i = 0; i < vertice.size() && !foundA; i++) {
			if (vertice.get(i).getValue().compareTo(valueIni) == 0) {
				foundA = true;
				positionA = i;
			}
		}

		for (int i = 0; i < vertice.size() && !foundB; i++) {
			if (vertice.get(i).getValue().compareTo(valueEnd) == 0) {
				foundB = true;
				positionB = i;
			}
		}

		edges.add(new Edge<U, V, H>(vertice.get(positionA), vertice.get(positionB), height));

		verify = addEdge(positionA, positionB, height);

		return verify;
	}

	private boolean addEdge(int positionA, int positionB, H height) {

		boolean verify = false;

		if (first != null) {

			Vertice<V, U, H> newVertice = first;

			for (int i = 0; i < positionA; i++) {
				if (newVertice.getDown() != null) {
					newVertice = newVertice.getDown();
				}
			}

			for (int i = 0; i < positionB; i++) {
				if (newVertice.getNext() != null) {
					newVertice = newVertice.getNext();
				}
			}
			verify = newVertice.addEdge(vertice.get(positionA), vertice.get(positionB), height);
		}

		return verify;
	}

	@Override
	public H searchEdge(V verticeInit, V verticeEnd) {

		int positionA = 0;
		int positionB = 0;
		boolean foundA = false;
		boolean foundB = false;
		H weight = null;

		if (first != null) {

			Vertice<V, U, H> newVertice = first;

			Vertice<V, U, H> current = newVertice;

			for (int i = 0; i < vertice.size() && !foundA; i++) {
				if (vertice.get(i).getValue().compareTo(verticeInit) == 0) {
					foundA = true;
					positionA = i;
				}
			}

			for (int i = 0; i < vertice.size() && !foundB; i++) {
				if (vertice.get(i).getValue().compareTo(verticeEnd) == 0) {
					foundB = true;
					positionB = i;
				}
			}

			for (int i = 0; i < positionA; i++) {
				if (newVertice.getDown() != null) {
					newVertice = newVertice.getDown();
				}
			}

			for (int i = 0; i < positionB; i++) {
				if (newVertice.getNext() != null) {
					newVertice = newVertice.getNext();
				}
			}

			weight = newVertice.getEdge().get(0).getHeight();
		}

		return weight;
	}

	@Override
	public boolean deleteVertice(V value) {

		boolean verify = false;

		for (int i = 0; i < vertice.size() && !verify; i++) {
			if (vertice.get(i).getValue().compareTo(value) == 0) {
				verify = true;

				vertice.remove(i);

				removeEdgesFromVertice(value);

				Vertice<V, U, H> vertix = new Vertice<V, U, H>(null);
				createMatrix(vertix);
			}
		}
		return verify;
	}

	private void removeEdgesFromVertice(V value) {
		for (int i = 0; i < edges.size(); i++) {
			if (edges.get(i).getInitVertice().getValue().compareTo(value) == 0
					|| edges.get(i).getFinalVertice().getValue().compareTo(value) == 0) {
				edges.remove(i);
			}
		}
		reloadEdges();
	}

	@Override
	public boolean deleteEdge(V initial, V end, H weight) {

		boolean verify = false;

		for (int i = 0; i < edges.size() && !verify; i++) {
			if (edges.get(i).getInitVertice().getValue().compareTo(initial) == 0
					&& edges.get(i).getFinalVertice().getValue().compareTo(end) == 0
					&& edges.get(i).getHeight().compareTo(weight) == 0) {
				verify = true;
				edges.remove(i);
			}
		}

		if (verify) {
			Vertice<V, U, H> vertix = new Vertice<V, U, H>(null);
			createMatrix(vertix);
			reloadEdges();
		}

		return verify;
	}

	@Override
	public ArrayList<Vertice<V, U, H>> getVertice() {
		return vertice;
	}

	@Override
	public ArrayList<Edge<U, V, H>> getEdges() {
		return edges;
	}

	@Override
	public ArrayList<Vertice<V, U, H>> bfs(V v) {
		boolean found = false;
		ArrayList<Vertice<V, U, H>> vertic = new ArrayList<Vertice<V, U, H>>();
		int position = 0;
		for (int i = 0; i < vertice.size() && found == false; i++) {
			if (vertice.get(i).getValue().compareTo(v) == 0) {
				position = i;
				found = true;
			}
		}
		for (int i = 0; i < vertice.size(); i++) {
			vertice.get(i).setColor(0);
		}
		if (found == true) {
			// vertice.get(position).getValue());

			bfs(vertic, first, first, position);
			return vertic;
		}
		return vertic;
	}

	public void bfs(ArrayList<Vertice<V, U, H>> vertic, Vertice<V, U, H> e, Vertice<V, U, H> firts, int position) {
		
		if (position == 0) {
		
			if (e != null) {
			
				if (e.getEdge().isEmpty()) {
					
					
					bfs(vertic, e.getNext(), firts, position);
					
				} else {
					// primer");

					if (e.getEdge().get(0).getFinalVertice().getColor() == 0) {
						if (e.getEdge().get(0).getInitVertice().getColor() == 0) {
							vertic.add(e.getEdge().get(0).getInitVertice());
							e.getEdge().get(0).getInitVertice().setColor(2);
						}

						vertic.add(e.getEdge().get(0).getFinalVertice());
						bfs(vertic, e.getNext(), firts, position);
					} else if(e.getNext() != null){
						
						bfs(vertic, e.getNext(), firts, position);
					}
				}
			} else {
				for (int i = 0; i < vertic.size(); i++) {
					if (vertic.get(i).getColor() != 2) {
						int pos = validPosition(vertic.get(i).getValue());
						vertic.get(i).setColor(2);
						
						bfs(vertic, first, first, pos);
					}
				}
				
			}
			
			

		} else {
			position--;
			bfs(vertic, e.getDown(), e.getDown(), position);
		}

	}

	private int validPosition(V v) {
		boolean found = false;

		int position = 0;
		for (int i = 0; i < vertice.size() && found == false; i++) {
			if (vertice.get(i).getValue().compareTo(v) == 0) {
				position = i;
				found = true;
			}
		}
		return position;
	}

	@Override
	public ArrayList<Vertice<V, U, H>> dfs(V v) {
		boolean found = false;
		int position = 0;
		ArrayList<Vertice<V, U, H>> vertic = new ArrayList<Vertice<V, U, H>>();
		for (int i = 0; i < vertice.size(); i++) {
			vertice.get(i).setVisited(false);
		}
		for (int i = 0; i < vertice.size() && found == false; i++) {
			if (vertice.get(i).getValue().compareTo(v) == 0) {
				position = i;
				found = true;
			}
		}
		vertic.add(vertice.get(position));
		dfs(vertic, vertice.get(position));

		return vertic;
	}

	public void dfs(ArrayList<Vertice<V, U, H>> vertic, Vertice<V, U, H> e) {
		ArrayList<Edge<U, V, H>> verticeO = e.getEdge();
		for (int i = 0; i < verticeO.size(); i++) {
			if (verticeO.get(i).getFinalVertice().isVisited() == false) {
				vertic.add(verticeO.get(i).getFinalVertice());
				verticeO.get(i).getFinalVertice().setVisited(true);
				dfs(vertic, verticeO.get(i).getFinalVertice());
			}
		}

	}

	public int makeDijkstra(Vertice<V, U, H> start, Vertice<V, U, H> end) {
		ArrayList<H> dijkstra = dijkstra(start);
		ArrayList<Vertice<V, U, H>> reference = assignRef();
		int weight = -1;

		for (int i = 0; i < reference.size(); i++) {
			if (reference.get(i).getValue().compareTo(end.getValue()) == 0) {
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

		while (!queue.isEmpty()) {

			int weight = 0;
			queue = extractMin(queue, dist);
			Vertice<V, U, H> value = queue.peek();
			int index = searchReference(value, reference);

			for (int i = 0; i < value.getEdge().size(); i++) {
				int finalVertice = searchReference(value.getEdge().get(i).getFinalVertice(), reference);

				if (finalVertice != -1) {
					weight += dist.get(index) + (Integer) value.getEdge().get(index).getHeight();

					if (weight < dist.get(finalVertice)) {
						dist.set(finalVertice, weight);
						prev.set(finalVertice, value);
						queue.poll();
					}
				}
			}
		}

		return (ArrayList<H>) dist;
	}

	private ArrayList<Integer> assignSource(Vertice<V, U, H> start, ArrayList<Integer> dist) {

		Vertice<V, U, H> current = first;

		for (int i = 0; i < vertice.size(); i++) {

			if (start.getValue().compareTo(current.getValue()) == 0) {
				dist.add(i, 0);

			} else {
				dist.add(Integer.MAX_VALUE);
				current.getDown();
			}
		}
		return dist;
	}

	private ArrayList<Vertice<V, U, H>> assignPrev(ArrayList<Vertice<V, U, H>> prev) {

		for (int i = 0; i < vertice.size(); i++) {
			prev.add(null);
		}
		return prev;
	}

	private Queue<Vertice<V, U, H>> assignQueue(Queue<Vertice<V, U, H>> queue) {

		Vertice<V, U, H> current = first;

		for (int i = 0; i < vertice.size(); i++) {
			queue.add(current);
			current = current.getDown();
		}

		return queue;
	}

	private Queue<Vertice<V, U, H>> extractMin(Queue<Vertice<V, U, H>> queue, ArrayList<Integer> dist) {

		ArrayList<Vertice<V, U, H>> values = new ArrayList<>();

		for (int i = 0; i < dist.size(); i++) {
			values.add(queue.poll());
		}

		for (int i = 0; i < dist.size(); i++) {
			int min = dist.get(i);
			Vertice<V, U, H> minim = values.get(i);

			for (int j = i + 1; j < dist.size(); j++) {

				if (dist.get(j) < min) {
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

	private ArrayList<Vertice<V, U, H>> assignRef() {

		ArrayList<Vertice<V, U, H>> ref = new ArrayList<Vertice<V, U, H>>();
		Vertice<V, U, H> current = first;

		for (int i = 0; i < vertice.size(); i++) {
			ref.add(current);
			current = current.getNext();
		}
		return ref;
	}

	private int searchReference(Vertice<V, U, H> vert, ArrayList<Vertice<V, U, H>> reference) {
		int index = -1;

		for (int i = 0; i < reference.size(); i++) {
			if (reference.get(i) == vert) {
				index = i;
			}
		}
		return index;
	}

	@Override
	public void floyd() {

		if (!vertice.isEmpty()) {

			distance = new int[vertice.size()][vertice.size()];

			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					if (j == i) {
						distance[i][j] = 0;
					} else if (checkWeight(i, j) != null) {
						distance[i][j] = (int) checkWeight(i, j);
					} else {
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
	}

	public H checkWeight(int verticeOne, int verticeTwo) {
		V verticeOneFind = vertice.get(verticeOne).getValue();
		V verticeTwoFind = vertice.get(verticeTwo).getValue();

		for (int i = 0; i < vertice.get(verticeOne).getEdge().size() - 1; i++) {
			if (vertice.get(verticeOne).getEdge().get(i).getInitVertice().getValue().compareTo(verticeOneFind) == 0
					&& vertice.get(verticeOne).getEdge().get(i).getFinalVertice().getValue()
							.compareTo(verticeTwoFind) == 0) {
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

		while (!queue.isEmpty()) {

			int weight = 0;
			queue = extractMin(queue, dist);
			Vertice<V, U, H> value = queue.peek();
			int index = searchReference(value, reference);

			for (int i = 0; i < value.getEdge().size(); i++) {
				int finalVertice = searchReference(value.getEdge().get(i).getFinalVertice(), reference);

				if (finalVertice != -1) {
					weight += dist.get(index) + (Integer) value.getEdge().get(index).getHeight();

					if (weight < dist.get(finalVertice) && !colors.get(i)) {
						dist.set(finalVertice, weight);
						prev.set(finalVertice, value);
						queue.poll();
					}
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

		if (!vertice.isEmpty()) {
			Queue<Edge<U, V, H>> priorityQueue = priority();

			for (int i = 0; i < 9; i++) {
				makeset(vertice.get(i));
			}

			for (int i = 0; i < priorityQueue.size() - 1; i++) {

				Edge<U, V, H> priority = priorityQueue.poll();

				NodeM<V, U, H> nodeOne = findNode(priority.getInitVertice().getValue());
				NodeM<V, U, H> nodeTwo = findNode(priority.getFinalVertice().getValue());

				if (findSet(nodeOne) == findSet(nodeTwo)) {
					cont += (Integer) priority.getHeight();
					union(nodeOne, nodeTwo);
				}

				priorityQueue = secondPriority(priorityQueue);
			}
		}

		return cont;
	}

	public Queue<Edge<U, V, H>> priority() {

		ArrayList<Vertice<V, U, H>> newArrayList = vertice;
		ArrayList<Edge<U, V, H>> toOrganize = new ArrayList<>();
		Queue<Edge<U, V, H>> priorityQueue = new LinkedList<>();

		for (int i = 0; i < newArrayList.size() - 1; i++) {
			for (int j = 0; j < newArrayList.get(i).getEdge().size() - 1; j++) {
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
			for (int j = i; j > 0
					&& newArrayList.get(j - 1).getHeight().compareTo(newArrayList.get(j).getHeight()) > 0; j--) {
				Edge<U, V, H> newEdge = newArrayList.get(j);

				newArrayList.set(j, newArrayList.get(j - 1));
				newArrayList.set(j - 1, newEdge);
			}
		}

		return newArrayList;
	}

	private Queue<Edge<U, V, H>> intoQueue(ArrayList<Edge<U, V, H>> arrayList) {

		ArrayList<Edge<U, V, H>> newArrayList = arrayList;
		Queue<Edge<U, V, H>> newQueue = new LinkedList<>();

		for (int i = 0; i < newArrayList.size() - 1; i++) {
			newQueue.add(newArrayList.get(i));
		}

		return newQueue;
	}

	public void makeset(Vertice<V, U, H> vertice) {
		ensembleArrayList.add(new NodeM<V, U, H>(vertice));
	}

	private NodeM<V, U, H> findNode(V value) {

		boolean verify = false;

		for (int i = 0; i < ensembleArrayList.size() - 1 && !verify; i++) {
			if (ensembleArrayList.get(i).getVertice().getValue().compareTo(value) == 0) {
				verify = true;
				return ensembleArrayList.get(i);
			}
		}
		return null;
	}

	public NodeM<V, U, H> findSet(NodeM<V, U, H> toFind) {

		NodeM<V, U, H> newNode = toFind;

		if (toFind.getParent() != null) {
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

	@Override
	public boolean modifyEdge(V initial, V end, H weight, H newWeight) {

		boolean verify = false;

		for (int i = 0; i < edges.size() && !verify; i++) {
			if (edges.get(i).getInitVertice().getValue().compareTo(initial) == 0
					&& edges.get(i).getFinalVertice().getValue().compareTo(end) == 0
					&& edges.get(i).getHeight().compareTo(weight) == 0) {
				verify = true;
				edges.get(i).setHeight(newWeight);
				reloadEdges();
			}
		}

		return verify;
	}
}
