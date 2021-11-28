package dataStructures;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import dataStructure.MatrixGraph;
import dataStructure.Vertice;

class MatrixGraphTest<V extends Comparable <V>, U, H extends Comparable<H>>{
	
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

		matrixGraph.addEdge("Colombia", "Portugal", 1600);
		assertEquals(1600, matrixGraph.searchEdge("Colombia", "Portugal"));
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
		matrixGraph.modifyEdge("Dubai", "Portugal", 3000, 2130);
		assertEquals(2130, matrixGraph.searchEdge("Dubai", "Portugal"));
		matrixGraph.modifyEdge("Colombia", "Dubai", 1200, 3000);
		assertEquals(3000, matrixGraph.searchEdge("Colombia", "Dubai"));
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
	public void bfsTestScenary3() {
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
	public void dfsTestScenary4() {
		/*
		setupScenary3();
		assertEquals("Colombia", matrixGraph.dfs("Colombia").get(0).getValue());
		assertEquals("España", matrixGraph.dfs("Colombia").get(1).getValue());
		assertEquals("Japon", matrixGraph.dfs("Colombia").get(2).getValue());
		assertEquals("EEUU", matrixGraph.dfs("Colombia").get(3).getValue());
		assertEquals("Australia", matrixGraph.dfs("Colombia").get(4).getValue());
		assertEquals("Nigeria", matrixGraph.dfs("Colombia").get(5).getValue());
		assertEquals("Portugal", matrixGraph.dfs("Colombia").get(6).getValue());
		assertEquals("Rusia", matrixGraph.dfs("Colombia").get(7).getValue());
		assertEquals("Dubai", matrixGraph.dfs("Colombia").get(8).getValue());
		assertEquals("Madagascar", matrixGraph.dfs("Colombia").get(9).getValue());
		*/
	}

	@Test
	public void test() {
		setupScenary1();
		assertEquals("Colombia", matrixGraph.getVertice().get(0).getValue());
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
	
	@Test
	public void dijkstraTest3() {
		setupScenary3();
		int value = 0;
		value = matrixGraph.makeDijkstra(matrixGraph.getVertice().get(9), matrixGraph.getVertice().get(8));
		assertEquals(value, Integer.MAX_VALUE);
	}
	
	@Test
	public void dijkstraTest4() {
		setupScenary3();
		int value = 0;
		Vertice<String, String, Integer> vertix = new Vertice<String, String, Integer>("Andorra");
		value = matrixGraph.makeDijkstra(matrixGraph.getVertice().get(9), vertix);
		assertEquals(value, -1);
	}
	
	@Test
	public void floydWarshallTest1() {
		setupScenary3();
		matrixGraph.floyd();
		Integer infinite = Integer.MAX_VALUE;
		
		int matrix[][] = {
				{0,3000,8000,700,7700,10700,10200,11620,8950,13950},
				{infinite,0,5000,infinite,infinite,infinite,infinite,infinite,infinite,infinite},
				{infinite,infinite,0,infinite,infinite,infinite,infinite,infinite,infinite,infinite},
				{infinite,infinite,11000,0,7000,10000,9500,10920,8250,13250},
				{infinite,infinite,4000,7000,0,3000,2500,3920,1250,6250},
				{infinite,infinite,1000,4000,3020,0,5520,920,4270,9270},
				{infinite,infinite,infinite,infinite,infinite,infinite,0,infinite,infinite,infinite},
				{infinite,infinite,6100,9100,2100,5100,4600,0,3350,8350},
				{infinite,infinite,6930,infinite,infinite,infinite,infinite,infinite, 0, 5000},
				{infinite,infinite,1930,infinite,infinite,infinite,infinite,infinite,infinite,0}};
		
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {
				assertEquals(matrix[i][j], matrixGraph.getDistance()[i][j]);
			}
		}
	}
	
	@Test
	public void floydWarshallTest2() {
		setupScenary1();
		matrixGraph.deleteVertice("Colombia");
		assertEquals(true, matrixGraph.getDistance() == null);
	}
	
	@Test
	public void floydWarshallTest3() {
		setupScenary2();
		matrixGraph.floyd();
		Integer infinite = Integer.MAX_VALUE;
		
		int[][] matrix = {
				{0,1200,4200,6950},
				{infinite,0,3000,5750},
				{infinite,3340,0,2750},
				{infinite,590,3590,0}};
		
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {
				assertEquals(matrix[i][j], matrixGraph.getDistance()[i][j]);
			}
		}
	}
	
	@Test
	public void kruskalTest1() {
		setupScenary3();
		int kruskal = matrixGraph.kruskal();
		assertEquals(17400, kruskal);
	}/*
	
	@Test
	public void kruskalTest2() {
		setupScenary1();
		matrixGraph.deleteVertice("Colombia");
		int kruskal = matrixGraph.kruskal();
		assertEquals(0, kruskal);
	}
	
	@Test
	public void kruskalTest3() {
		setupScenary2();
		int kruskal = matrixGraph.kruskal();
		assertEquals(4540, kruskal);
	}*/
	
	@Test
	public void prim() {
		setupScenary3();
		int value = 0;
		value = matrixGraph.prim(matrixGraph.getVertice().get(0));
		assertEquals(value, 28370);
	}
	
	@Test
	public void prim2() {
		setupScenary2();
		int value = matrixGraph.prim(matrixGraph.getVertice().get(0));
		assertEquals(value, 6950);
	}
	
	@Test
	public void prim3() {
		setupScenary3();
		int value = 0;
		value = matrixGraph.prim(matrixGraph.getVertice().get(9));
		assertEquals(value, 1930);
	}
	
	@Test
	public void prim4() {
		setupScenary3();
		int value = 0;
		value = matrixGraph.prim(matrixGraph.getVertice().get(6));
		assertEquals(value, 0);
	}
}

