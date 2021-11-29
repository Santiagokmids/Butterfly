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
	ArrayList<Vertice<V, U, H>> verticeArray = new ArrayList<Vertice<V,U,H>>();

	
	public MatrixGraph() {
		createGraph();
	}

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

		if ((int) height > 0) {
			edges.add(new Edge<U, V, H>(vertice.get(positionA), vertice.get(positionB), height));

			verify = addEdge(positionA, positionB, height);
		}

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
			
			if (!newVertice.getEdge().isEmpty()) {
				weight = newVertice.getEdge().get(0).getHeight();
			}
		}

		return weight;
	}

	@Override
	public boolean deleteVertice(V value) {

		boolean verify = false;

		for (int i = 0; i < vertice.size() && !verify; i++) {
			if (vertice.get(i).getValue().compareTo(value) == 0) {
				vertice.remove(i);
				removeEdgesFromVertice(value);
				verify = true;
				
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

					if (e.getEdge().get(0).getFinalVertice().getColor() == 0) {
						if (e.getEdge().get(0).getInitVertice().getColor() == 0) {
							vertic.add(e.getEdge().get(0).getInitVertice());
							e.getEdge().get(0).getInitVertice().setColor(2);
						}

						vertic.add(e.getEdge().get(0).getFinalVertice());
						bfs(vertic, e.getNext(), firts, position);
					} else if (e.getNext() != null) {

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
			bfs(vertic, e.getDown(), firts, position);
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

			dfs(vertic, first, first, position);
			return vertic;
		}
		return vertic;
	}

	public void dfs2() {
		
		Vertice<V, U, H> current = first;
		
		for (int i = 0; i < vertice.size(); i++) {
			vertice.get(i).setColor(0);
		}
		
		for (int i = 0; i < vertice.size(); i++) {
			
			dfsVisited(current);
			if (current.getDown() != null) {
				current = current.getDown();
			}
		}
	}
	
	private void dfsVisited(Vertice<V, U, H> vertice2) {
		vertice2.setColor(1);
		ArrayList<Vertice<V,U,H>> ad = findAdj(vertice2);
		for (int i = 0; i < ad.size(); i++) {
			if (ad.get(i).getColor() == 0) {
				dfsVisited(ad.get(i));
			}
		}
		vertice2.setColor(2);
		if(vertice2.getValue() != null) {
		verticeArray.add(vertice2);
		}
	}

	private ArrayList<Vertice<V, U, H>> findAdj(Vertice<V, U, H> vertice2) {

		Vertice<V, U, H> current = vertice2;
		ArrayList<Vertice<V, U, H>> arrayAd = new ArrayList<Vertice<V,U,H>>();
		
		for (int i = 0; i < vertice.size(); i++) {
			
			if(current== first) {
				
				arrayAd.add(current);
			}
			if (!current.getEdge().isEmpty()) {
				arrayAd.add(current.getEdge().get(0).getFinalVertice());
			}
			if (current.getNext() != null) {
				current = current.getNext();
			}
		}
		return arrayAd;
	}

	public void dfs(ArrayList<Vertice<V, U, H>> vertic, Vertice<V, U, H> e, Vertice<V, U, H> firt, int position) {
		if (position == 0) {
			if (e != null) {
				if (e.getEdge().isEmpty()) {
					dfs(vertic, e.getNext(), firt, position);
				} else {
					if (e.getEdge().get(0).getFinalVertice().getColor() == 0) {
						if (e.getEdge().get(0).getInitVertice().getColor() == 0) {
							vertic.add(e.getEdge().get(0).getInitVertice());
							e.getEdge().get(0).getInitVertice().setColor(1);
						}
						e.getEdge().get(0).getFinalVertice().setColor(1);
						vertic.add(e.getEdge().get(0).getFinalVertice());
						position = validPosition(e.getEdge().get(0).getFinalVertice().getValue());
						firt = e.getEdge().get(0).getInitVertice();
						dfs(vertic, first, first, position);
					} else {
						dfs(vertic, e.getNext(), first, position);
					}
				}
			} else {
				for (int i = vertic.size(); i >= 0; i--) {
					if (i >= 1) {
						if (vertic.get(i - 1).getColor() == 1) {
							 vertic.get(i - 1).setColor(2);
							position = validPosition(vertic.get(i - 1).getValue());
							dfs(vertic, first, first, position);
						}
					} else {
						if (vertic.get(i).getColor() == 1) {
							vertic.get(i).setColor(2);
							position = validPosition(vertic.get(i).getValue());
							int out = 3;
							while(out >0) {
							dfs(vertic, first, first, position);
							out--;
							}

						}
					}
				}
			}
		} else {
			position--;
			dfs(vertic, e.getDown(), e.getDown(), position);
		}

	}

	public int makeDijkstra(Vertice<V, U, H> start, Vertice<V, U, H> end) {
		ArrayList<H> dijkstra = dijkstra(start);
		int weight = -1;

		for (int i = 0; i < vertice.size(); i++) {
			if (vertice.get(i).getValue().compareTo(end.getValue()) == 0) {
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
		ArrayList<Vertice<V, U, H>> reference = new ArrayList<>();

		dist = assignSource(start, dist);
		prev = assignPrev(prev);
		queue = assignQueue(queue, start);
		reference = assignRef();

		while (!queue.isEmpty()) {

			queue = extractMin(queue);

			Vertice<V, U, H> value = queue.peek();

			int index = searchReference(value, reference);

			Vertice<V, U, H> current = first;

			for (int i = 0; i < index; i++) {
				if (current.getDown() != null) {
					current = current.getDown();
				}
			}
			int position = 0, weight = 0;

			if (!current.getEdge().isEmpty()) {
				position = searchReference(current.getEdge().get(0).getFinalVertice(), reference);
				if (dist.get(index) != Integer.MAX_VALUE && position != -1) {
					weight = dist.get(index) + (Integer) current.getEdge().get(0).getHeight();

					if (weight < dist.get(position)) {
						dist.set(position, weight);
						queue = setDistances(queue, position, weight);
						prev.set(position, value);
					}
				} else {
					queue.poll();
				}
			}

			for (int j = 0; j < vertice.size(); j++) {
				if (current.getNext() != null) {
					current = current.getNext();
				}
				if (!current.getEdge().isEmpty()) {
					position = searchReference(current.getEdge().get(0).getFinalVertice(), reference);
					if (dist.get(index) != Integer.MAX_VALUE && position != -1) {
						weight = dist.get(index) + (Integer) current.getEdge().get(0).getHeight();

						if (weight < dist.get(position)) {
							dist.set(position, weight);
							queue = setDistances(queue, position, weight);
							prev.set(position, value);
						}
					} else {
						queue.poll();
					}
				}
			}
			queue.poll();
		}
		return (ArrayList<H>) dist;
	}

	private ArrayList<Integer> assignSource(Vertice<V, U, H> start, ArrayList<Integer> dist) {

		for (int i = 0; i < vertice.size(); i++) {

			if (start.getValue().compareTo(vertice.get(i).getValue()) == 0) {
				dist.add(i, 0);

			} else {
				dist.add(Integer.MAX_VALUE);
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

	private Queue<Vertice<V, U, H>> assignQueue(Queue<Vertice<V, U, H>> queue, Vertice<V, U, H> start) {

		for (int i = 0; i < vertice.size(); i++) {

			if (vertice.get(i).getValue().compareTo(start.getValue()) == 0) {
				vertice.get(i).setDistance(0);
			}
			queue.add(vertice.get(i));
		}

		return queue;
	}

	private Queue<Vertice<V, U, H>> extractMin(Queue<Vertice<V, U, H>> queue) {

		ArrayList<Vertice<V, U, H>> vertix = new ArrayList<>();

		for (int i = 0; i < vertice.size(); i++) {
			if (!queue.isEmpty()) {
				vertix.add(queue.poll());
			}
		}

		for (int i = 0; i < vertix.size(); i++) {
			Vertice<V, U, H> min = vertix.get(i);

			for (int j = i + 1; j < vertix.size(); j++) {

				if (vertix.get(j).getDistance() < min.getDistance()) {
					Vertice<V, U, H> temp = vertix.get(j);
					vertix.set(j, min);
					min = temp;
				}
			}
			vertix.set(i, min);
		}

		queue.clear();
		for (int i = 0; i < vertix.size(); i++) {
			queue.add(vertix.get(i));
		}
		return queue;
	}

	private Queue<Vertice<V, U, H>> setDistances(Queue<Vertice<V, U, H>> queue, int index, int distance) {

		ArrayList<Vertice<V, U, H>> current = new ArrayList<Vertice<V, U, H>>();
		boolean verify = false;

		while (!verify) {

			if (queue.isEmpty()) {
				verify = true;

			} else {
				if (queue.peek().getValue().compareTo(vertice.get(index).getValue()) == 0) {
					vertice.get(index).setDistance(distance);
					queue.poll();
					current.add(vertice.get(index));

				} else {
					current.add(queue.poll());
				}
			}
		}

		for (int i = 0; i < current.size(); i++) {
			queue.add(current.get(i));
		}

		return queue;
	}

	private ArrayList<Vertice<V, U, H>> assignRef() {

		ArrayList<Vertice<V, U, H>> ref = new ArrayList<Vertice<V, U, H>>();

		for (int i = 0; i < vertice.size(); i++) {
			ref.add(vertice.get(i));
		}
		return ref;
	}

	private int searchReference(Vertice<V, U, H> vert, ArrayList<Vertice<V, U, H>> reference) {
		int index = -1;

		for (int i = 0; i < reference.size(); i++) {
			if (reference.get(i).getValue().compareTo(vert.getValue()) == 0) {
				index = i;
			}
		}
		return index;
	}

	@Override
	public void floyd() {

		if (!vertice.isEmpty()) {

			distance = new int[vertice.size()][vertice.size()];

			distance = toInfinte(distance);

			distance = toZero(distance);

			distance = toWeight(distance);

			for (int k = 0; k < vertice.size(); k++) {
				for (int i = 0; i < vertice.size(); i++) {
					for (int j = 0; j < vertice.size(); j++) {
						if (distance[i][k] != Integer.MAX_VALUE && distance[k][j] != Integer.MAX_VALUE
								&& distance[i][j] > (distance[i][k] + distance[k][j])) {
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
		V verticeOneFind = vertice.get(verticeOne).getValue();
		V verticeTwoFind = vertice.get(verticeTwo).getValue();
		Vertice<V, U, H> currentVertice = first;
		H weightH = null;

		for (int i = 0; i < verticeOne; i++) {
			if (currentVertice.getDown() != null) {
				currentVertice = currentVertice.getDown();
			}
		}

		for (int i = 0; i < verticeTwo; i++) {
			if (currentVertice.getNext() != null) {
				currentVertice = currentVertice.getNext();
			}
		}

		if (!currentVertice.getEdge().isEmpty()
				&& currentVertice.getEdge().get(0).getInitVertice().getValue().compareTo(verticeOneFind) == 0
				&& currentVertice.getEdge().get(0).getFinalVertice().getValue().compareTo(verticeTwoFind) == 0) {
			weightH = currentVertice.getEdge().get(0).getHeight();
		}

		return weightH;
	}

	@Override
	public int prim(Vertice<V, U, H> start) {

		ArrayList<Integer> dist = new ArrayList<>();
		ArrayList<Vertice<V, U, H>> prev = new ArrayList<>();
		Queue<Vertice<V, U, H>> queue = new LinkedList<>();
		ArrayList<Vertice<V, U, H>> reference = new ArrayList<>();

		dist = assignSource(start, dist);
		prev = assignPrev(prev);
		queue = assignQueue(queue, start);
		reference = assignRef();

		ArrayList<Boolean> colors = assignColors();

		while (!queue.isEmpty()) {

			queue = extractMin(queue);

			Vertice<V, U, H> value = queue.peek();

			int index = searchReference(value, reference);

			Vertice<V, U, H> current = first;

			for (int i = 0; i < index; i++) {
				if (current.getDown() != null) {
					current = current.getDown();
				}
			}
			int position = 0, weight = 0;

			if (!current.getEdge().isEmpty()) {
				position = searchReference(current.getEdge().get(0).getFinalVertice(), reference);

				if (dist.get(index) != Integer.MAX_VALUE && position != -1) {
					weight = (Integer) current.getEdge().get(0).getHeight();

					if (weight < dist.get(position) && !colors.get(position)) {
						dist.set(position, weight);
						queue = setDistances(queue, position, weight);
						prev.set(position, value);
					}
				} else {
					queue.poll();
				}
			}

			for (int j = 0; j < vertice.size(); j++) {
				if (current.getNext() != null) {
					current = current.getNext();
				}
				if (!current.getEdge().isEmpty()) {
					position = searchReference(current.getEdge().get(0).getFinalVertice(), reference);
					if (dist.get(index) != Integer.MAX_VALUE && position != -1) {
						weight = (Integer) current.getEdge().get(0).getHeight();

						if (weight < dist.get(position) && !colors.get(position)) {
							dist.set(position, weight);
							queue = setDistances(queue, position, weight);
							prev.set(position, value);
						}
					} else {
						queue.poll();
					}
				}
			}
			queue.poll();
			colors.set(index, true);
		}

		int amount = 0;

		for (int i = 0; i < dist.size(); i++) {
			if (dist.get(i) != Integer.MAX_VALUE) {
				amount += dist.get(i);
			}
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

			for (int i = 0; i < vertice.size(); i++) {
				makeset(vertice.get(i));
			}
			cont = kruskalOperation(priorityQueue, 0, 0);
		}

		return cont;
	}

	private int kruskalOperation(Queue<Edge<U, V, H>> priorityQueue, int contVertice, int contT) {

		if(!priorityQueue.isEmpty() && contVertice < vertice.size()-1) {

			Edge<U, V, H> priority = priorityQueue.poll();

			NodeM<V, U, H> nodeOne = findNode(priority.getInitVertice().getValue());
			NodeM<V, U, H> nodeTwo = findNode(priority.getFinalVertice().getValue());
			if (findSet(nodeOne) != findSet(nodeTwo)) {
				union(nodeOne, nodeTwo);
				priorityQueue = secondPriority(priorityQueue);
				return kruskalOperation(priorityQueue, ++contVertice, contT+(Integer) priority.getHeight());
			}else {
				priorityQueue = secondPriority(priorityQueue);
				return kruskalOperation(priorityQueue, contVertice, contT);
			}
		}
		return contT;
	}

	public Queue<Edge<U, V, H>> priority() {

		ArrayList<Edge<U, V, H>> newArrayList = findEdges(first);
		ArrayList<Edge<U, V, H>> toOrganize = new ArrayList<>();
		Queue<Edge<U, V, H>> priorityQueue = new LinkedList<>();

		for (int i = 0; i < newArrayList.size(); i++) {
			toOrganize.add(newArrayList.get(i));
		}
		toOrganize = organize(toOrganize);
		priorityQueue = intoQueue(toOrganize);

		return priorityQueue;
	}

	private ArrayList<Edge<U, V, H>> findEdges(Vertice<V, U, H> first) {
		
		Vertice<V, U, H> currentVertice = first;
		Vertice<V, U, H> newFirst = first;
		ArrayList<Edge<U, V, H>> listEdge = new ArrayList<>();
		
		for (int i = 0; i < vertice.size(); i++) {
			for (int j = 0; j < vertice.size(); j++) {
				if(currentVertice != null && !currentVertice.getEdge().isEmpty()) {
					listEdge.add(currentVertice.getEdge().get(0));
				}
				if (currentVertice.getNext() != null) {
					currentVertice = currentVertice.getNext();
				}
			}
			if (newFirst.getDown() != null) {
				currentVertice = newFirst.getDown();
				newFirst = newFirst.getDown();
			}
		}
		
		return listEdge;
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

		for (int i = 0; i < newArrayList.size(); i++) {
			newQueue.add(newArrayList.get(i));
		}

		return newQueue;
	}

	public void makeset(Vertice<V, U, H> vertice) {
		ensembleArrayList.add(new NodeM<V, U, H>(vertice));
	}

	private NodeM<V, U, H> findNode(V value) {

		boolean verify = false;

		for (int i = 0; i < ensembleArrayList.size() && !verify; i++) {
			if (ensembleArrayList.get(i).getVertice().getValue().compareTo(value) == 0) {
				verify = true;
				return ensembleArrayList.get(i);
			}
		}
		return null;
	}

	public NodeM<V, U, H> findSet(NodeM<V, U, H> toFind) {

		NodeM<V, U, H> newNode = toFind;

		if (toFind != null && toFind.getParent() != null) {
			return newNode = findSet(toFind.getParent());
		}

		return newNode;
	}

	private void union(NodeM<V, U, H> nodeOne, NodeM<V, U, H> nodeTwo) {
		nodeTwo.setParent(nodeOne);
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
					&& edges.get(i).getHeight().compareTo(weight) == 0 && (int)newWeight > 0) {
				verify = true;
				edges.get(i).setHeight(newWeight);
				modifyEdgeInVertice(initial, end, weight, newWeight);
				modifyEdgeInMatrix(initial, end, weight, newWeight);
			}
		}

		return verify;
	}

	private void modifyEdgeInVertice(V initial, V end, H weight, H newWeight) {

		for (int i = 0; i < vertice.size(); i++) {
			if (vertice.get(i).getValue().compareTo(initial) == 0) {

				for (int j = 0; j < vertice.get(i).getEdge().size(); j++) {

					if (vertice.get(i).getEdge().get(j).getFinalVertice().getValue().compareTo(end) == 0
							&& weight.compareTo(vertice.get(i).getEdge().get(j).getHeight()) == 0) {
						vertice.get(i).getEdge().get(j).setHeight(newWeight);
					}
				}
			}
		}
	}

	private void modifyEdgeInMatrix(V initial, V end, H weight, H newWeight) {

		int indexA = -1, indexB = -1;

		for (int i = 0; i < vertice.size(); i++) {
			if (vertice.get(i).getValue().compareTo(initial) == 0) {
				indexA = i;
			}
		}

		for (int i = 0; i < vertice.size(); i++) {
			if (vertice.get(i).getValue().compareTo(end) == 0) {
				indexB = i;
			}
		}

		Vertice<V, U, H> current = first;

		for (int i = 0; i < indexA; i++) {
			if (current.getDown() != null) {
				current = current.getDown();
			}
		}

		for (int i = 0; i < indexB; i++) {
			if (current.getNext() != null) {
				current = current.getNext();
			}
		}
		Edge<U, V, H> edge = new Edge<U, V, H>(vertice.get(indexA), vertice.get(indexB), newWeight);

		current.getEdge().set(0, edge);
	}

	public int[][] getDistance() {
		return distance;
	}

	public ArrayList<Vertice<V, U, H>> getVerticeArray() {
		return verticeArray;
	}
}
