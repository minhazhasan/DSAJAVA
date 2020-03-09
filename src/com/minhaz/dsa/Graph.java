package com.minhaz.dsa;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {
	private class Node{
		private String label;
		
		public Node(String label) {
			this.label = label;
		}
	}
	
	private Map<String, Node> nodes = new HashMap<>();
	private Map<Node, List<Node>> adjacentLists = new HashMap<>();
	
	public void addNode(String label) {
		Node node = new Node(label);
		nodes.putIfAbsent(label, node);
	}
	
	public void addEdges(String fromNode, String toNode) {
		if(!nodes.containsKey(fromNode) || !nodes.containsKey(toNode))
			throw new IllegalStateException();
		
		//adjacentLists.
		
		
	}

}
