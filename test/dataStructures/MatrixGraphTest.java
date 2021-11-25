package dataStructures;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import dataStructure.MatrixGraph;

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
		matrixGraph.addVertice("Espa�a");
		matrixGraph.addVertice("Japon");
		matrixGraph.addVertice("EEUU");
		matrixGraph.addVertice("Australia");
		matrixGraph.addVertice("Nigeria");
		matrixGraph.addVertice("Rusia");
		matrixGraph.addVertice("Portugal");
		matrixGraph.addVertice("Dubai");
		matrixGraph.addVertice("Madagascar");
		
		matrixGraph.addEdge("Colombia", "Espa�a", 3000);
		matrixGraph.addEdge("Colombia", "EEUU", 700);
		matrixGraph.addEdge("Espa�a", "Japon", 5000);	
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
	public void bfsTest() {
		setupScenary2();
		assertEquals("Colombia", matrixGraph.bfs("Colombia").get(0).getValue());
		assertEquals("Dubai", matrixGraph.bfs("Colombia").get(1).getValue());
		assertEquals("Portugal", matrixGraph.bfs("Colombia").get(2).getValue());
		assertEquals("Madagascar", matrixGraph.bfs("Colombia").get(3).getValue());
	}
	@Test
	void bfsTestScenary3() {
		setupScenary3();
		assertEquals("Colombia", matrixGraph.bfs("Colombia").get(0).getValue());
		assertEquals("Espa�a", matrixGraph.bfs("Colombia").get(1).getValue());
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
	void dfsTestScenary4() {
		setupScenary3();
		for(int i =0;i<=9;i++) {
			//System.out.println(matrixGraph.dfs("Colombia").get(i).getValue());
		}
		assertEquals("Colombia", matrixGraph.dfs("Colombia").get(0).getValue());
		assertEquals("Espa�a", matrixGraph.dfs("Colombia").get(1).getValue());
		assertEquals("Japon", matrixGraph.dfs("Colombia").get(2).getValue());
		assertEquals("EEUU", matrixGraph.dfs("Colombia").get(3).getValue());
		assertEquals("Australia", matrixGraph.dfs("Colombia").get(4).getValue());
		assertEquals("Nigeria", matrixGraph.dfs("Colombia").get(5).getValue());
		assertEquals("Portugal", matrixGraph.dfs("Colombia").get(6).getValue());
		assertEquals("Rusia", matrixGraph.dfs("Colombia").get(7).getValue());
		assertEquals("Dubai", matrixGraph.dfs("Colombia").get(8).getValue());
		assertEquals("Madagascar", matrixGraph.dfs("Colombia").get(9).getValue());
	}

	@Test
	void test() {
		setupScenary1();
		assertEquals("Colombia", matrixGraph.getVertice().get(0).getValue());
	}

}
