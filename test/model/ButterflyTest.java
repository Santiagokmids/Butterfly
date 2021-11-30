package model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class ButterflyTest {

	private Butterfly butterfly = new Butterfly();

	public void setupScenary1() {
		butterfly.matrixGraph.addVertice("Colombia");
	}

	public void setupScenary2() {
		setupScenary1();
		butterfly.matrixGraph.addVertice("Dubai");
		butterfly.matrixGraph.addVertice("Portugal");
		butterfly.matrixGraph.addVertice("Madagascar");
		butterfly.addEdgeMatrix("Dubai", "Portugal", 3000);
		butterfly.addEdgeMatrix("Portugal", "Madagascar", 2750);
		butterfly.addEdgeMatrix("Madagascar", "Dubai", 590);
		butterfly.addEdgeMatrix("Colombia", "Dubai", 1200);
	}

	public void setupScenary3() {
		setupScenary1();
		butterfly.matrixGraph.addVertice("España");
		butterfly.matrixGraph.addVertice("Japon");
		butterfly.matrixGraph.addVertice("EEUU");
		butterfly.matrixGraph.addVertice("Australia");
		butterfly.matrixGraph.addVertice("Nigeria");
		butterfly.matrixGraph.addVertice("Rusia");
		butterfly.matrixGraph.addVertice("Portugal");
		butterfly.matrixGraph.addVertice("Dubai");
		butterfly.matrixGraph.addVertice("Madagascar");		
		butterfly.addEdgeMatrix("Colombia", "España", 3000);
		butterfly.addEdgeMatrix("Colombia", "EEUU", 700);
		butterfly.addEdgeMatrix("España", "Japon", 5000);	
		butterfly.addEdgeMatrix("EEUU", "Australia", 7000);
		butterfly.addEdgeMatrix("Australia", "Nigeria", 3000);
		butterfly.addEdgeMatrix("Australia", "Rusia", 2500);	
		butterfly.addEdgeMatrix("Australia", "Dubai", 1250);
		butterfly.addEdgeMatrix("Nigeria", "EEUU", 4000);
		butterfly.addEdgeMatrix("Nigeria", "Portugal", 920);
		butterfly.addEdgeMatrix("Portugal", "Australia", 2100);
		butterfly.addEdgeMatrix("Dubai", "Madagascar", 5000);
		butterfly.addEdgeMatrix("Madagascar", "Japon", 1930);
		butterfly.addEdgeMatrix("Nigeria", "Japon", 1000);
	}
	
	@Test
	public void addVerticeTest() {
		setupScenary1();
		butterfly.matrixGraph.addVertice("Japon");
		butterfly.matrixGraph.addVertice("Portugal");
		assertEquals(3, butterfly.matrixGraph.getVertice().size());
	}
	
	@Test
	public void addEdgeTest() {
		setupScenary2();
		butterfly.addEdgeMatrix("Colombia", "Portugal", 6450);
		assertEquals(6450, butterfly.matrixGraph.searchEdge("Colombia", "Portugal"));
		assertEquals(false, butterfly.matrixGraph.addEdge("Madagascar", "Colombia", -200));

	}
	
	@Test
	public void deleteVerticeTest() {
		setupScenary2();
		assertEquals(true, butterfly.deleteVerticeInMatrix("Dubai"));
	}
	
	@Test
	public void deleteVerticeTest2() {
		setupScenary1();
		butterfly.deleteVerticeInMatrix("Colombia");
		assertEquals(butterfly.matrixGraph.getVertice().isEmpty(), true);
	}
	
	@Test
	public void deleteEdgeTest() {
		setupScenary2();
		assertEquals(true, butterfly.deleteEdgeInMatrix("Colombia", "Dubai", 1200));
		assertEquals(true, butterfly.deleteEdgeInMatrix("Dubai", "Portugal", 3000));
	}
	
	@Test
	public void modifyEdgeTest() {
		setupScenary2();
		butterfly.modifyEdgeMatrix("Dubai", "Portugal", 1320);
		assertEquals(1320, butterfly.matrixGraph.searchEdge("Dubai", "Portugal"));
		butterfly.modifyEdgeMatrix("Colombia", "Dubai", 6200);
		assertEquals(6200, butterfly.matrixGraph.searchEdge("Colombia", "Dubai"));
	}

	@Test
	public void bfsTest() {
		setupScenary2();
		assertEquals("Colombia", butterfly.bfsInMatrix("Colombia").get(0).getValue());
		assertEquals("Dubai", butterfly.bfsInMatrix("Colombia").get(1).getValue());
		assertEquals("Portugal", butterfly.bfsInMatrix("Colombia").get(2).getValue());
		assertEquals("Madagascar", butterfly.bfsInMatrix("Colombia").get(3).getValue());

	}
	
	@Test
	public void bfsTestScenary2() {
		setupScenary3();
		assertEquals("Colombia", butterfly.bfsInMatrix("Colombia").get(0).getValue());
		assertEquals("España",butterfly.bfsInMatrix("Colombia").get(1).getValue());
		assertEquals("EEUU", butterfly.bfsInMatrix("Colombia").get(2).getValue());
		assertEquals("Japon", butterfly.bfsInMatrix("Colombia").get(3).getValue());
		assertEquals("Australia", butterfly.bfsInMatrix("Colombia").get(4).getValue());
		assertEquals("Nigeria", butterfly.bfsInMatrix("Colombia").get(5).getValue());	
		assertEquals("Rusia", butterfly.bfsInMatrix("Colombia").get(6).getValue());
		assertEquals("Dubai", butterfly.bfsInMatrix("Colombia").get(7).getValue());
		assertEquals("Portugal", butterfly.bfsInMatrix("Colombia").get(8).getValue());
		assertEquals("Madagascar", butterfly.bfsInMatrix("Colombia").get(9).getValue());

	}

	@Test
	public void dijkstraTest() {
		setupScenary3();
		int value = 0;
		value = butterfly.dijkstraInMatrix(butterfly.matrixGraph.getVertice().get(7).getValue(), butterfly.matrixGraph.getVertice().get(2).getValue());
		assertEquals(value, 6100);
	}
	
	@Test
	public void dijkstraTest2() {
		setupScenary3();
		ArrayList<Integer> dijkstra = butterfly.dijkstraInList(butterfly.matrixGraph.getVertice().get(7).getValue());
		
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
	
	//Tests for listGraph
	
	public void setupScenary1List() {
		butterfly.listGraph.addVertice("Colombia");
	}

	public void setupScenary2List() {
		setupScenary1List();
		butterfly.listGraph.addVertice("Dubai");
		butterfly.listGraph.addVertice("Portugal");
		butterfly.listGraph.addVertice("Madagascar");
		butterfly.addEdgeList("Dubai", "Portugal", 3000);
		butterfly.addEdgeList("Portugal", "Madagascar", 2750);
		butterfly.addEdgeList("Madagascar", "Dubai", 590);
		butterfly.addEdgeList("Colombia", "Dubai", 1200);
	}

	public void setupScenary3List() {
		setupScenary1List();
		butterfly.listGraph.addVertice("España");
		butterfly.listGraph.addVertice("Japon");
		butterfly.listGraph.addVertice("EEUU");
		butterfly.listGraph.addVertice("Rusia");
		butterfly.listGraph.addVertice("Australia");
		butterfly.listGraph.addVertice("Nigeria");
		butterfly.listGraph.addVertice("Dubai");
		butterfly.listGraph.addVertice("Portugal");
		butterfly.listGraph.addVertice("Madagascar");
		butterfly.addEdgeList("Colombia", "España",3000);
		butterfly.addEdgeList("Colombia", "EEUU",700);
		butterfly.addEdgeList("España", "Japon",5000);
		butterfly.addEdgeList("EEUU", "Australia",7000);
		butterfly.addEdgeList("Australia", "Rusia",2500);
		butterfly.addEdgeList("Australia", "Dubai",1250);
		butterfly.addEdgeList("Australia", "Nigeria",3000);
		butterfly.addEdgeList("Nigeria", "EEUU",4000);
		butterfly.addEdgeList("Nigeria", "Portugal",920);
		butterfly.addEdgeList("Portugal", "Australia",2100);
		butterfly.addEdgeList("Dubai", "Madagascar",5000);
		butterfly.addEdgeList("Madagascar", "Japon",1930);
		butterfly.addEdgeList("Nigeria", "Japon",1000);
	}
	
	@Test
	public void addVerticeTestList() {
		setupScenary1List();
		butterfly.listGraph.addVertice("Nigeria");
		butterfly.listGraph.addVertice("Madagascar");
		assertEquals("Nigeria", butterfly.listGraph.getListVertice().get(1).getValue());
		assertEquals("Madagascar", butterfly.listGraph.getListVertice().get(2).getValue());
	}
	
	@Test
	public void addEdgeTestList() {
		setupScenary2List();
		butterfly.addEdgeList("Colombia", "Portugal",2000);
		butterfly.addEdgeList("Dubai", "Colombia",-780);
		assertEquals(2000, butterfly.listGraph.searchEdge("Colombia", "Portugal"));
		assertEquals(null, butterfly.listGraph.searchEdge("Dubai", "Colombia"));
	}
	
	@Test
	public void deleteVerticeTestList() {
		setupScenary2List();
		butterfly.deleteVerticeInList("Colombia");
		assertEquals(null, butterfly.listGraph.searchVertice("Colombia"));
	}
	
	@Test
	public void deleteEdgeTestList() {
		setupScenary2List();
		butterfly.deleteEdgeInList("Dubai","Portugal",3000);
		assertEquals(null, butterfly.listGraph.searchEdge("Dubai", "Portugal"));
	}
	
	@Test
	public void modifyEdgeTestList() {
		setupScenary2List();
		butterfly.modifyEdgeList("Portugal","Madagascar",6000);
		butterfly.modifyEdgeList("Madagascar","Dubai",3900);
		assertEquals(6000, butterfly.listGraph.searchEdge("Portugal","Madagascar"));
		assertEquals(3900, butterfly.listGraph.searchEdge("Madagascar", "Dubai"));
	}

	@Test
	public void bfsTestList() {
		setupScenary2List();
		assertEquals("Colombia", butterfly.bfsInList("Colombia").get(0).getValue());
		assertEquals("Madagascar", butterfly.bfsInList("Colombia").get(3).getValue());
	}
	
	@Test
	public void dijkstraTestList() {
		setupScenary3List();
		int value = 0;
		value = butterfly.dijkstraInList(butterfly.listGraph.getVertice().get(8).getValue(), butterfly.listGraph.getVertice().get(2).getValue());
		assertEquals(value, 6100);
	}
	
	
	@Test
	public void dijkstraTest2List() {
		setupScenary3List();
		ArrayList<Integer> dijkstra = butterfly.dijkstraInList(butterfly.listGraph.getVertice().get(8).getValue());
		
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
