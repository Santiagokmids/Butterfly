package dataStructure;

import java.util.ArrayList;
import java.util.Iterator;
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

	@Override
	public ArrayList<H> dijkstra(Vertice<V, U, H> start, Vertice<V, U, H> Final) {
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

	private Queue<V> assignQueue(Queue<V> queue) {

		for (int i = 0; i < vertice.size(); i++) {
			queue.add(vertice.get(i).getValue());
		}

		return queue;
	}

	private ArrayList<V> assignNames(Vertice<V, U, H> start, ArrayList<V> names) {

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
					dist.set(j, min);
					min = temp;
				}
			}
			dist.set(i, min);
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
