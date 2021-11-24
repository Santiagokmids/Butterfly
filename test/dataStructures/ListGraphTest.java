package dataStructures;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import dataStructure.ListGraph;

class ListGraphTest {

	private ListGraph<String, String, Integer> lg;

	public void setupScenary1() {
		lg = new ListGraph<String, String, Integer>();
		lg.createGraph();
		lg.addVertice("Colombia");
	}

	public void setupScenary2() {
		setupScenary1();
		lg.addVertice("Dubai");
		lg.addVertice("Portugal");
		lg.addVertice("Madagascar");
		lg.addEdge("Dubai", "Portugal", 3000);
		lg.addEdge("Portugal", "Madagascar", 2750);
		lg.addEdge("Madagascar", "Dubai", 590);
		lg.addEdge("Colombia", "Dubai", 1200);

	}

	public void setupScenary3() {
		lg.addVertice("Dubai");
		lg.addVertice("Portugal");
		lg.addVertice("Madagascar");
		lg.addVertice("EEUU");
		lg.addVertice("Japon");
		lg.addVertice("España");
		lg.addVertice("Australia");
		lg.addVertice("Nigueria");
		lg.addEdge("España", "Japon", 5000);
		lg.addEdge("Colombia", "España", 3000);
		lg.addEdge("Colombia", "EEUU", 700);
		lg.addEdge("EEUU", "Australia", 7000);
		lg.addEdge("Australia", "Rusia", 2500);
		lg.addEdge("Australia", "Dubai", 1250);
		lg.addEdge("Australia", "Nigeria", 3000);
		lg.addEdge("Nigeria", "EEUU", 4000);
		lg.addEdge("Nigeria", "Portugal", 920);
		lg.addEdge("Portugal", "Australia", 2100);
		lg.addEdge("Dubai", "Madagascar", 5000);
		lg.addEdge("Madagascar", "Japon", 1930);
		lg.addEdge("Nigeria", "Japon", 1000);
	}
	
	@Test
	public void addVerticeTest() {
		setupScenary1();
		lg.addVertice("Japon");
		lg.addVertice("Portugal");
		assertEquals("Japon", lg.getListVertice().get(1).getValue());
		assertEquals("Portugal", lg.getListVertice().get(2).getValue());
	}
	
	@Test
	public void addEdgeTest() {
		setupScenary2();
		lg.addEdge("Portugal", "Colombia",1600);
		lg.addEdge("Madagascar", "Colombia",-200);
		assertEquals(1600, lg.searchEdge("Portugal", "Colombia"));
		assertEquals(-1, lg.searchEdge("Madagascar", "Colombia"));
	}
	
	@Test
	public void deleteEdgeTest() {
		setupScenary2();
		lg.addEdge("Portugal", "Colombia",1600);
		lg.addEdge("Madagascar", "Colombia",-200);
		assertEquals(1600, lg.searchEdge("Portugal", "Colombia"));
		assertEquals(-1, lg.searchEdge("Madagascar", "Colombia"));
	}

	@Test
	public void bfsTest() {
		setupScenary2();
		assertEquals("Colombia", lg.bfs("Colombia").get(0).getValue());
		assertEquals("Dubai", lg.bfs("Colombia").get(1).getValue());
		assertEquals("Portugal", lg.bfs("Colombia").get(2).getValue());
		assertEquals("Madagascar", lg.bfs("Colombia").get(3).getValue());
	}

	@Test
	public void floydWarshallTest() {

	}
}
