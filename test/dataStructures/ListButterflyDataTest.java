package dataStructures;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import dataStructure.ListGraph;
import dataStructure.ListVertice;


class ListButterflyDataTest {
	private ListGraph<String,String ,Integer> lg;
	public void setupScenary1() {

		lg = new ListGraph<String,String ,Integer>();
		lg.createGraph();
	}
	public void setupScenary2() {
		setupScenary1();
		ListVertice<String, String , Integer> listV = new ListVertice<String, String, Integer>("Colombia");  
		lg.addVertice("Colombia");
	}

	@Test
	void test() {
		fail("Not yet implemented");
	}

}
