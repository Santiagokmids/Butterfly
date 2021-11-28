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
		matrixGraph.addVertice("Japon");
		matrixGraph.addVertice("EEUU");
		matrixGraph.addVertice("Australia");
		matrixGraph.addVertice("Nigeria");
		matrixGraph.addVertice("Rusia");
		matrixGraph.addVertice("Portugal");
		matrixGraph.addVertice("Dubai");
		matrixGraph.addVertice("Madagascar");
	}
	
	public void addVerticeInList() {
		listGraph.addVertice("Colombia");
		listGraph.addVertice("España");
		listGraph.addVertice("Japon");
		listGraph.addVertice("EEUU");
		listGraph.addVertice("Australia");
		listGraph.addVertice("Nigeria");
		listGraph.addVertice("Rusia");
		listGraph.addVertice("Portugal");
		listGraph.addVertice("Dubai");
		listGraph.addVertice("Madagascar");
	}
	
	public boolean addEdgeMatrix(String valueIni, String valueEnd, int weight) {
		return matrixGraph.addEdge(valueIni, valueEnd, weight);
	}
	
	public boolean addEdgeList(String valueIni, String valueEnd, int weight) {
		return listGraph.addEdge(valueIni, valueEnd, weight);
	}
	
	public boolean modifyEdgeMatrix(String valueIni, String valueEnd, int weight, int newWeight) {
		return matrixGraph.modifyEdge(valueIni, valueEnd, weight, newWeight);
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
	
	public ArrayList<Vertice<String, String, Integer>> dfsInMatrix(String value){
		ArrayList<Vertice<String, String, Integer>> array = matrixGraph.dfs(value);
		return array;
	}
	
	public ArrayList<ListVertice<String, String, Integer>> dfsInList(String value){
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
}
