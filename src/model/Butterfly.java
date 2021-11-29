package model;

import java.util.ArrayList;

import dataStructure.ListGraph;
import dataStructure.ListVertice;
import dataStructure.MatrixGraph;
import dataStructure.Vertice;

public class Butterfly {
	
	public MatrixGraph<String, String, Integer> matrixGraph;
	public ListGraph<String, String, Integer> listGraph;
	
	public Butterfly() {
		matrixGraph = new MatrixGraph<>();
		listGraph = new ListGraph<>();
		addVerticeInList();
		addVerticeInMatrix();
	}
	
	public void addVerticeInMatrix() {
		matrixGraph.addVertice("Colombia");
		matrixGraph.addVertice("España");
		matrixGraph.addVertice("Japón");
		matrixGraph.addVertice("Estados Unidos");
		matrixGraph.addVertice("Australia");
		matrixGraph.addVertice("Nigeria");
		matrixGraph.addVertice("Rusia");
		matrixGraph.addVertice("Portugal");
		matrixGraph.addVertice("Dubái");
		matrixGraph.addVertice("Madagascar");
	}
	
	public void addVerticeInList() {
		listGraph.addVertice("Colombia");
		listGraph.addVertice("España");
		listGraph.addVertice("Japón");
		listGraph.addVertice("Estados Unidos");
		listGraph.addVertice("Australia");
		listGraph.addVertice("Nigeria");
		listGraph.addVertice("Rusia");
		listGraph.addVertice("Portugal");
		listGraph.addVertice("Dubái");
		listGraph.addVertice("Madagascar");
	}
	
	public boolean addEdgeMatrix(String valueIni, String valueEnd, int weight) {
		return matrixGraph.addEdge(valueIni, valueEnd, weight);
	}
	
	public boolean addEdgeList(String valueIni, String valueEnd, int weight) {
		return listGraph.addEdge(valueIni, valueEnd, weight);
	}
	
	public boolean modifyEdgeMatrix(String valueIni, String valueEnd, int newWeight) {
		return matrixGraph.modifyEdge(valueIni, valueEnd, newWeight);
	}
	
	public boolean modifyEdgeList(String valueIni, String valueEnd, int newWeight) {
		return listGraph.setEdge(valueIni, valueEnd, newWeight);
	}
	
	public boolean deleteVerticeInMatrix(String value) {
		return matrixGraph.deleteVertice(value);
	}
	
	public boolean deleteVerticeInList(String value) {
		return listGraph.deleteVertice(value);
	}
	
	public boolean deleteEdgeInMatrix(String valueInit, String valueEnd, int weight) {
		return matrixGraph.deleteEdge(valueInit, valueEnd, weight);
	}
	
	public boolean deleteEdgeInList(String valueInit, String valueEnd, int weight) {
		return listGraph.deleteEdge(valueInit, valueEnd, weight);
	}
	
	public ArrayList<Vertice<String, String, Integer>> bfsInMatrix(String value){
		ArrayList<Vertice<String, String, Integer>> array = matrixGraph.bfs(value);
		return array;
	}
	
	public ArrayList<ListVertice<String, String, Integer>> bfsInList(String value){
		return listGraph.dfs(value);
	}
	
	public ArrayList<Integer> dijkstraInMatrix(String start){
		return matrixGraph.dijkstra(new Vertice<String, String, Integer>(start));
	}
	
	public int dijkstraInMatrix(String start, String end){
		return matrixGraph.makeDijkstra(new Vertice<String, String, Integer>(start), new Vertice<String, String, Integer>(end));
	}
	
	public ArrayList<Integer> dijkstraInList(String start){
		return listGraph.dijkstra(new ListVertice<String, String, Integer>(start));
	}
	
	public int dijkstraInList(String start, String end){
		return listGraph.makeDijkstra(new ListVertice<String, String, Integer>(start), new ListVertice<String, String, Integer>(end));
	}

	public boolean searchFlight(String origin, String destination, Integer weight, boolean typeGraph) {
		
		Integer weightInteger = 0;
		boolean verify = false;
		
		if (!typeGraph) {
			if (listGraph.searchEdge(origin, destination) != null) {
				weightInteger = listGraph.searchEdge(origin, destination);
			}
		}else {
			if (matrixGraph.searchEdge(origin, destination) != null) {
				weightInteger = matrixGraph.searchEdge(origin, destination);
			}
		}
		if (weightInteger.equals(weight)) {
			verify = true;
		}
		return verify;
	}
	
	public boolean searchFlight(String origin, String destination, boolean typeGraph) {
		
		Integer weightInteger = 0;
		boolean verify = false;
		
		if (!typeGraph) {
			if (listGraph.searchEdge(origin, destination) != null) {
				weightInteger = listGraph.searchEdge(origin, destination);
			}
		}else {
			if (matrixGraph.searchEdge(origin, destination) != null) {
				weightInteger = matrixGraph.searchEdge(origin, destination);
			}
		}
		if (!weightInteger.equals(0)) {
			verify = true;
		}
		return verify;
	}
	
	public boolean add(String origin, String destinatio, Integer weight) {
		boolean verify = false;
		verify = addEdgeMatrix(origin, destinatio, weight);
		verify = addEdgeList(origin, destinatio, weight);
		
		return verify;
	}

	public boolean delete(String origin, String destinatio, Integer weight) {
		boolean verify = false;
		verify = deleteEdgeInList(origin, destinatio, weight);
		verify = deleteEdgeInMatrix(origin, destinatio, weight);
		return verify;
	}

	public boolean modify(String origin, String destinatio, Integer weight) {
		boolean verify = false;
		verify = modifyEdgeList(origin, destinatio, weight);
		verify = modifyEdgeMatrix(origin, destinatio, weight);
		return verify;
	}
}
