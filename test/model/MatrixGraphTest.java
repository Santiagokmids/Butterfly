package model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import dataStructure.MatrixGraph;

class MatrixGraphTest {

	private MatrixGraph<String, String, Integer> matrixGraph;

	public void setupScenary1() {
		matrixGraph = new MatrixGraph<String, String, Integer>();
		matrixGraph.createGraph();
		matrixGraph.addVertice("Colombia");
	}

	public void setupScenary2() {
		setupScenary1();
		matrixGraph.addVertice("Dubai");
		matrixGraph.addVertice("Portugal");
		matrixGraph.addVertice("Madagascar");
		matrixGraph.addEdge("Dubai", "Portugal", 3000);
		matrixGraph.addEdge("Portugal", "Madagascar", 2750);
		matrixGraph.addEdge("Madagascar", "Dubai", 590);
		matrixGraph.addEdge("Colombia", "Dubai", 1200);
	}

	public void setupScenary3() {
		setupScenary1();
		matrixGraph.addVertice("España");
		matrixGraph.addVertice("Japon");
		matrixGraph.addVertice("EEUU");
		matrixGraph.addVertice("Australia");
		matrixGraph.addVertice("Nigeria");
		matrixGraph.addVertice("Rusia");
		matrixGraph.addVertice("Portugal");
		matrixGraph.addVertice("Dubai");
		matrixGraph.addVertice("Madagascar");		
		matrixGraph.addEdge("Colombia", "España", 3000);
		matrixGraph.addEdge("Colombia", "EEUU", 700);
		matrixGraph.addEdge("España", "Japon", 5000);	
		matrixGraph.addEdge("EEUU", "Australia", 7000);
		matrixGraph.addEdge("Australia", "Nigeria", 3000);
		matrixGraph.addEdge("Australia", "Rusia", 2500);	
		matrixGraph.addEdge("Australia", "Dubai", 1250);
		matrixGraph.addEdge("Nigeria", "EEUU", 4000);
		matrixGraph.addEdge("Nigeria", "Portugal", 920);
		matrixGraph.addEdge("Portugal", "Australia", 2100);
		matrixGraph.addEdge("Dubai", "Madagascar", 5000);
		matrixGraph.addEdge("Madagascar", "Japon", 1930);
		matrixGraph.addEdge("Nigeria", "Japon", 1000);
	}
	
	@Test
	public void addVerticeTest() {
		setupScenary1();
		matrixGraph.addVertice("Japon");
		matrixGraph.addVertice("Portugal");
		assertEquals(3, matrixGraph.getVertice().size());
	}
	
	@Test
	public void addEdgeTest() {
		setupScenary2();

		matrixGraph.addEdge("Colombia", "Portugal", 6450);
		assertEquals(6450, matrixGraph.searchEdge("Colombia", "Portugal"));
		assertEquals(false, matrixGraph.addEdge("Madagascar", "Colombia", -200));

	}
	
	@Test
	public void deleteVerticeTest() {
		setupScenary2();
		assertEquals(true, matrixGraph.deleteVertice("Dubai"));
	}
	
	@Test
	public void deleteVerticeTest2() {
		setupScenary1();
		matrixGraph.deleteVertice("Colombia");
		assertEquals(matrixGraph.getVertice().isEmpty(), true);
	}
	
	@Test
	public void deleteEdgeTest() {
		setupScenary2();
		assertEquals(true, matrixGraph.deleteEdge("Colombia", "Dubai", 1200));
		assertEquals(true, matrixGraph.deleteEdge("Dubai", "Portugal", 3000));
	}
	
	@Test
	public void modifyEdgeTest() {
		setupScenary2();
		matrixGraph.modifyEdge("Dubai", "Portugal", 1320);
		assertEquals(1320, matrixGraph.searchEdge("Dubai", "Portugal"));
		matrixGraph.modifyEdge("Colombia", "Dubai", 6200);
		assertEquals(6200, matrixGraph.searchEdge("Colombia", "Dubai"));
	}

	@Test
	public void bfsTest() {
		setupScenary2();
		assertEquals("Colombia", matrixGraph.bfs("Colombia").get(0).getValue());
		assertEquals("Dubai", matrixGraph.bfs("Colombia").get(1).getValue());
		assertEquals("Portugal", matrixGraph.bfs("Colombia").get(2).getValue());
		assertEquals("Madagascar", matrixGraph.bfs("Colombia").get(3).getValue());

	}
	
	@Test
	public void bfsTestScenary2() {
		setupScenary3();
		assertEquals("Colombia", matrixGraph.bfs("Colombia").get(0).getValue());
		assertEquals("España", matrixGraph.bfs("Colombia").get(1).getValue());
		assertEquals("EEUU", matrixGraph.bfs("Colombia").get(2).getValue());
		assertEquals("Japon", matrixGraph.bfs("Colombia").get(3).getValue());
		assertEquals("Australia", matrixGraph.bfs("Colombia").get(4).getValue());
		assertEquals("Nigeria", matrixGraph.bfs("Colombia").get(5).getValue());	
		assertEquals("Rusia", matrixGraph.bfs("Colombia").get(6).getValue());
		assertEquals("Dubai", matrixGraph.bfs("Colombia").get(7).getValue());
		assertEquals("Portugal", matrixGraph.bfs("Colombia").get(8).getValue());
		assertEquals("Madagascar", matrixGraph.bfs("Colombia").get(9).getValue());

	}

	
	@Test
	public void dijkstraTest() {
		setupScenary3();
		int value = 0;
		value = matrixGraph.makeDijkstra(matrixGraph.getVertice().get(7), matrixGraph.getVertice().get(2));
		assertEquals(value, 6100);
	}
	
	@Test
	public void dijkstraTest2() {
		setupScenary3();
		ArrayList<Integer> dijkstra = matrixGraph.dijkstra(matrixGraph.getVertice().get(7));
		
		ArrayList<Integer> values = new ArrayList<>();
		values.add(Integer.MAX_VALUE);
		values.add(Integer.MAX_VALUE);
		values.add(6100);
		values.add(9100);
		values.add(2100);
		values.add(5100);
		values.add(4600);
		values.add(0);
		values.add(3350);
		values.add(8350);
		
		for (int i = 0; i < dijkstra.size(); i++) {
			assertEquals(values.get(i), dijkstra.get(i));
		}
	}

}
