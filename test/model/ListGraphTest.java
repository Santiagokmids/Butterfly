package model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import dataStructure.ListGraph;

class ListGraphTest {

private ListGraph<String,String ,Integer> listGraph;
	
	public void setupScenary1() {
		listGraph = new ListGraph<String, String, Integer>();
		listGraph.createGraph();
		listGraph.addVertice("Colombia");
	}

	public void setupScenary2() {
		setupScenary1();
		listGraph.addVertice("Dubai");
		listGraph.addVertice("Portugal");
		listGraph.addVertice("Madagascar");
		listGraph.addEdge("Dubai", "Portugal", 3000);
		listGraph.addEdge("Portugal", "Madagascar", 2750);
		listGraph.addEdge("Madagascar", "Dubai", 590);
		listGraph.addEdge("Colombia", "Dubai", 1200);
	}

	public void setupScenary3() {
		setupScenary1();
		listGraph.addVertice("España");
		listGraph.addVertice("Japon");
		listGraph.addVertice("EEUU");
		listGraph.addVertice("Rusia");
		listGraph.addVertice("Australia");
		listGraph.addVertice("Nigeria");
		listGraph.addVertice("Dubai");
		listGraph.addVertice("Portugal");
		listGraph.addVertice("Madagascar");
		listGraph.addEdge("Colombia", "España",3000);
		listGraph.addEdge("Colombia", "EEUU",700);
		listGraph.addEdge("España", "Japon",5000);
		listGraph.addEdge("EEUU", "Australia",7000);
		listGraph.addEdge("Australia", "Rusia",2500);
		listGraph.addEdge("Australia", "Dubai",1250);
		listGraph.addEdge("Australia", "Nigeria",3000);
		listGraph.addEdge("Nigeria", "EEUU",4000);
		listGraph.addEdge("Nigeria", "Portugal",920);
		listGraph.addEdge("Portugal", "Australia",2100);
		listGraph.addEdge("Dubai", "Madagascar",5000);
		listGraph.addEdge("Madagascar", "Japon",1930);
		listGraph.addEdge("Nigeria", "Japon",1000);
	}
	
	@Test
	public void addVerticeTest() {
		setupScenary1();
		listGraph.addVertice("Nigeria");
		listGraph.addVertice("Madagascar");
		assertEquals("Nigeria", listGraph.getListVertice().get(1).getValue());
		assertEquals("Madagascar", listGraph.getListVertice().get(2).getValue());
	}
	
	@Test
	public void addEdgeTest() {
		setupScenary2();
		listGraph.addEdge("Colombia", "Portugal",2000);
		listGraph.addEdge("Dubai", "Colombia",-780);
		assertEquals(2000, listGraph.searchEdge("Colombia", "Portugal"));
		assertEquals(null, listGraph.searchEdge("Dubai", "Colombia"));
	}
	
	@Test
	public void deleteVerticeTest() {
		setupScenary2();
		listGraph.deleteVertice("Colombia");
		assertEquals(null, listGraph.searchVertice("Colombia"));
	}
	
	@Test
	public void deleteEdgeTest() {
		setupScenary2();
		listGraph.deleteEdge("Dubai","Portugal",3000);
		assertEquals(null, listGraph.searchEdge("Dubai", "Portugal"));
	}
	
	@Test
	public void modifyEdgeTest() {
		setupScenary2();
		listGraph.setEdge("Portugal","Madagascar",6000);
		listGraph.setEdge("Madagascar","Dubai",3900);
		assertEquals(6000, listGraph.searchEdge("Portugal","Madagascar"));
		assertEquals(3900, listGraph.searchEdge("Madagascar", "Dubai"));
	}

	@Test
	public void bfsTest() {
		setupScenary2();
		assertEquals("Colombia", listGraph.bfs("Colombia").get(0).getValue());
		assertEquals("Madagascar", listGraph.bfs("Colombia").get(3).getValue());
	}
	
	@Test
	public void dijkstraTest() {
		setupScenary3();
		int value = 0;
		value = listGraph.makeDijkstra(listGraph.getVertice().get(8), listGraph.getVertice().get(2));
		assertEquals(value, 6100);
	}
	
	
	@Test
	public void dijkstraTest2() {
		setupScenary3();
		ArrayList<Integer> dijkstra = listGraph.dijkstra(listGraph.getVertice().get(8));
		
		ArrayList<Integer> values = new ArrayList<>();
		values.add(Integer.MAX_VALUE);
		values.add(Integer.MAX_VALUE);
		values.add(6100);
		values.add(9100);
		values.add(4600);
		values.add(2100);
		values.add(5100);
		values.add(3350);
		values.add(0);
		values.add(8350);
		
		for (int i = 0; i < dijkstra.size(); i++) {
			assertEquals(values.get(i), dijkstra.get(i));
		}
	}
	
}
