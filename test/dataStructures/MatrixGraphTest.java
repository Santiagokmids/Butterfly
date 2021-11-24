package dataStructures;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import dataStructure.ListGraph;
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
		matrixGraph.addVertice("Colombia");
		matrixGraph.addVertice("Dubai");
		matrixGraph.addVertice("Portugal");
		matrixGraph.addVertice("Madagascar");
		matrixGraph.addVertice("EEUU");
		matrixGraph.addVertice("Japon");
		matrixGraph.addVertice("España");
		matrixGraph.addVertice("Australia");
		matrixGraph.addVertice("Nigeria");
		matrixGraph.addVertice("Rusia");
		matrixGraph.addEdge("España", "Japon", 5000);
		matrixGraph.addEdge("Colombia", "España", 3000);
		matrixGraph.addEdge("Colombia", "EEUU", 700);
		matrixGraph.addEdge("EEUU", "Australia", 7000);
		matrixGraph.addEdge("Australia", "Rusia", 2500);
		matrixGraph.addEdge("Australia", "Dubai", 1250);
		matrixGraph.addEdge("Australia", "Nigeria", 3000);
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
	void test() {
		fail("Not yet implemented");
	}

}
