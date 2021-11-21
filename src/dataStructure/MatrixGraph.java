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
	public ArrayList<Vertice<V, U, H>> bfs(V v) {
		boolean found = false;
		ArrayList<Vertice<V, U, H>> vertic = new ArrayList<Vertice<V, U, H>>();
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
			bfs(vertic,vertice.get(position));
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
			if(verticeO.get(i).getColor()!=2)
			bfs(vertic, verticeO.get(i));
		}
	}
	
	@Override
	public ArrayList<Vertice<V, U, H>>dfs(V v ) {
		boolean found = false;
		int position = 0;
		ArrayList<Vertice<V, U, H>> vertic = new ArrayList<Vertice<V, U, H>>();
		for(int i=0;i< vertice.size()-1 ;i++) {
			vertice.get(i).setVisited(false);
		}
		for(int i=0;i< vertice.size()-1 && found ==false;i++) {
			if(vertice.get(i).getValue().compareTo(v)==0) {
				position = i;
				found = true;
			}
		}
		dfs(vertic, vertice.get(position));
		return vertic;
	}
	
	public void dfs(ArrayList<Vertice<V, U, H>> vertic,Vertice<V, U, H> e) {
		ArrayList<Edge<U, V, H>> verticeO = e.getEdge();
		for(int i =0;i<verticeO.size();i++) {
			if(verticeO.get(i).getFinalVertice().isVisited() == false) {
				verticeO.get(i).getFinalVertice().setVisited(true);
				vertic.add(verticeO.get(i).getFinalVertice());
				dfs(vertic, verticeO.get(i).getFinalVertice());
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
		// TODO Auto-generated method stub

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
	public H kruskal() {
		// TODO Auto-generated method stub
		return null;
	}



}
