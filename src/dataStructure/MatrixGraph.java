package dataStructure;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class MatrixGraph<U, V extends Comparable <V>, H, E> implements IMatrixGraph<U, V, H, E>{

	public ArrayList<Vertice<V, E>> vertice;

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
	public Vertice<V, E> getVertice() {
		return null;
	}

	@Override
	public Edge<U, V, H> getEdges() {
		return null;
	}

	@Override
	public ArrayList<Vertice<V, E>> bfs() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Vertice<V, E>> dfs() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<H> dijkstra(Vertice<V, E> start, Vertice<V, E> Final) {
		ArrayList<Integer> dist = new ArrayList<>();
		ArrayList<V> nameDist = new ArrayList<>();
		ArrayList<V> prev = new ArrayList<>();
		Queue<V> queue = new LinkedList<>();

		dist = assignSource(start, dist);
		prev = assignPrev(prev);
		queue = assignQueue(queue);
		nameDist = assignNames(start, nameDist);

		while(!queue.isEmpty()) {

		}

		return null;
	}

	private ArrayList<Integer> assignSource(Vertice<V, E> start, ArrayList<Integer> dist){

		for (int i = 0; i < vertice.size(); i++) {

			if(start.getValue().compareTo(vertice.get(i).getValue()) == 0) {
				dist.add(i, 0);

			}else {
				dist.add(Integer.MAX_VALUE);
			}
		}
		return dist;
	}

	private ArrayList<V> assignPrev(ArrayList<V> prev){

		for (int i = 0; i < vertice.size(); i++) {
			prev.add(null);
		}

		return prev;
	}

	private Queue<V> assignQueue(Queue<V> queue) {

		for (int i = 0; i < vertice.size(); i++) {
			queue.add(vertice.get(i).getValue());
		}

		return queue;
	}

	private ArrayList<V> assignNames(Vertice<V, E> start, ArrayList<V> names) {

		for (int i = 0; i < vertice.size(); i++) {

			if(start.getValue().compareTo(vertice.get(i).getValue()) == 0) {
				names.add(0, vertice.get(i).getValue());

			}else {
				names.add(vertice.get(i).getValue());
			}
		}

		return names;
	}

	private ArrayList<V> sortDist(ArrayList<V> names, ArrayList<Integer> dist) {

		for(int i = 0;i < dist.size();i++) {
			int min = dist.get(i);
			
			for(int j = i+1;j < dist.size();j++) {
				
				if(dist.get(j) < min) {
					int temp = dist.get(j);
					array[j] = min;
					min = temp;
				}
			}
			array[i] = min;
		}
		return names;
	}

	private V extractMin(ArrayList<V> names,Queue<V> queue) {

		return null;
	}

	@Override
	public void floyd() {
		// TODO Auto-generated method stub

	}

	@Override
	public H prim() {

		return null;
	}

	@Override
	public H kruskal() {
		// TODO Auto-generated method stub
		return null;
	}



}
