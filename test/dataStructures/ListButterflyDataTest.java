package dataStructures;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import dataStructure.ListGraph;
import dataStructure.ListVertice;


class ListButterflyDataTest<U extends Comparable<ListVertice<V, U, H>>, V extends Comparable<V>, H extends Comparable<H>> {
	private ListGraph<U,V,H> lg;
	public void setupScenary1() {

		lg = new ListGraph<U, V, H>();
		lg.createGraph();
	}
	public void setupScenary2() {
		setupScenary2();
		//lg.addVertice();
	}

	@Test
	void test() {
		fail("Not yet implemented");
	}

}
