package dataStructures;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import dataStructure.ListGraph;
import junit.framework.Assert;
class ListButterflyDataTest {
	private ListGraph<String,String ,Integer> lg;
	public void setupScenary1() {
		lg = new ListGraph<String,String ,Integer>();
		lg.createGraph();
		lg.addVertice("Colombia");
		
	}
	public void setupScenary2() {
		setupScenary1();
		lg.addVertice("Dubai");
		lg.addVertice("Portugal");
		lg.addVertice("Madagascar");
		lg.addEdge("Dubai", "Portugal",3000);
		lg.addEdge("Portugal", "Madagascar",2750);
		lg.addEdge("Madagascar", "Dubai",590);
		lg.addEdge("Colombia", "Dubai",1200);
		
	}

	@Test
	void test() {
		setupScenary2();
		assertEquals("Colombia", lg.bfs("Colombia").get(0).getValue());
		assertEquals("Colombia", lg.bfs("Dubai").get(1).getValue());
		assertEquals("Colombia", lg.bfs("Portugal").get(2).getValue());
	}

}
