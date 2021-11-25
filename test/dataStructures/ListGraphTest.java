package dataStructures;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import dataStructure.ListGraph;

class ListGraphTest {

	private ListGraph<String,String ,Integer> lg;
	
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
		setupScenary1();
		lg.addVertice("España");
		lg.addVertice("Japon");
		lg.addVertice("EEUU");
		lg.addVertice("Rusia");
		lg.addVertice("Australia");
		lg.addVertice("Nigeria");
		lg.addVertice("Dubai");
		lg.addVertice("Portugal");
		lg.addVertice("Madagascar");
		lg.addEdge("Colombia", "España",3000);
		lg.addEdge("Colombia", "EEUU",700);
		lg.addEdge("España", "Japon",5000);
		lg.addEdge("EEUU", "Australia",7000);
		lg.addEdge("Australia", "Rusia",2500);
		lg.addEdge("Australia", "Dubai",1250);
		lg.addEdge("Australia", "Nigeria",3000);
		lg.addEdge("Nigeria", "EEUU",4000);
		lg.addEdge("Nigeria", "Portugal",920);
		lg.addEdge("Portugal", "Australia",2100);
		lg.addEdge("Dubai", "Madagascar",5000);
		lg.addEdge("Madagascar", "Japon",1930);
		lg.addEdge("Nigeria", "Japon",1000);
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
		assertEquals(null, lg.searchEdge("Madagascar", "Colombia"));
	}
	
	@Test
	public void deleteVerticeTest() {
		setupScenary2();
		lg.deleteVertice("Dubai");
		assertEquals(null, lg.searchVertice("Dubai"));
		setupScenary1();
		lg.deleteVertice("Colombia");
		assertEquals(null, lg.searchVertice("Colombia"));
	}
	
	@Test
	public void deleteEdgeTest() {
		setupScenary2();
		lg.deleteEdge("Colombia","Dubai",1200);
		lg.deleteEdge("Dubai","Portugal",3000);
		assertEquals(null, lg.searchEdge("Colombia","Dubai"));
		assertEquals(null, lg.searchEdge("Dubai", "Portugal"));
	}
	
	@Test
	public void modifyEdgeTest() {
		setupScenary2();
		lg.setEdge("Dubai","Portugal",2130);
		lg.setEdge("Colombia","Dubai",3000);
		assertEquals(2130, lg.searchEdge("Dubai","Portugal"));
		assertEquals(3000, lg.searchEdge("Colombia", "Dubai"));
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
	public void floydWarshallTest1() {
		setupScenary3();
		lg.floyd();
		Integer infinite = Integer.MAX_VALUE;
		
		int matrix[][] = {
				{0,3000,8000,700,10200,7700,10700,8950,11620,13950},
				{infinite,0,5000,infinite,infinite,infinite,infinite,infinite,infinite,infinite},
				{infinite,infinite,0,infinite,infinite,infinite,infinite,infinite,infinite,infinite},
				{infinite,infinite,11000,0,9500,7000,10000,8250,10920,13250},
				{infinite,infinite,infinite,infinite,0,infinite,infinite,infinite,infinite,infinite},
				{infinite,infinite,4000,7000,2500,0,3000,1250,3920,6250},
				{infinite,infinite,1000,4000,5520,3020,0,4270,920,9270},
				{infinite,infinite,6930,infinite,infinite,infinite,infinite,0,infinite,5000},
				{infinite,infinite,6100,9100,4600,2100,5100,3350,0,8350},
				{infinite,infinite,1930,infinite,infinite,infinite,infinite,infinite,infinite,0}};
		
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {
				assertEquals(matrix[i][j], lg.getDistance()[i][j]);
			}
		}
	}
	
	
	@Test
	public void floydWarshallTest2() {
		setupScenary1();
		lg.deleteVertice("Colombia");
		assertEquals(true, lg.getDistance() == null);
	}
	
	@Test
	public void floydWarshallTest3() {
		setupScenary2();
		lg.floyd();
		Integer infinite = Integer.MAX_VALUE;
		
		int[][] matrix = {
				{0,1200,4200,6950},
				{infinite,0,3000,5750},
				{infinite,3340,0,2750},
				{infinite,590,3590,0}};
		
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {
				assertEquals(matrix[i][j], lg.getDistance()[i][j]);
			}
		}
	}
	
	@Test
	public void kruskalTest1() {
		setupScenary3();
		int kruskal = lg.kruskal();
		assertEquals(17400, kruskal);
	}
	
	@Test
	public void kruskalTest2() {
		setupScenary1();
		lg.deleteVertice("Colombia");
		int kruskal = lg.kruskal();
		assertEquals(0, kruskal);
	}
	
	@Test
	public void kruskalTest3() {
		setupScenary2();
		int kruskal = lg.kruskal();
		assertEquals(4540, kruskal);
	}
}
