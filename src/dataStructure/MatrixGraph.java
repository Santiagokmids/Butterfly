package dataStructure;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class MatrixGraph<V extends Comparable <V>, U, H> implements IMatrixGraph<U, V, H>{
	
	public ArrayList<Vertice<V, U, H>> vertice;
	
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
	public ArrayList<Vertice<V, U, H>> bfs() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Vertice<V, U, H>> dfs() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<H> dijkstra(Vertice<V, U, H> start, Vertice<V, U, H> Final) {
		
		ArrayList<Integer> dist = new ArrayList<>();
		ArrayList<V> prev = new ArrayList<>();
		Queue<Vertice<V, U, H>> queue = new LinkedList<>();
		ArrayList<Vertice<V, U, H>> reference = new ArrayList<>();

		dist = assignSource(start, dist);
		prev = assignPrev(prev);
		queue = assignQueue(queue);
		reference = assignRef();

		while(!queue.isEmpty()) {
			int height = 0;
			queue = extractMin(queue, dist);
			Vertice<V, U, H> value = queue.poll();
			int index = searchReference(value, reference);
			
			for (int i = 0; i < value.getEdge().size(); i++) {
				int finalVertice = searchReference(value.getEdge().get(index).getFinalVertice(), reference);
				height +=  + (Integer)value.getEdge().get(index).getHeight();
				
			}
		}

		return null;
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

	private ArrayList<V> assignPrev(ArrayList<V> prev){

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
