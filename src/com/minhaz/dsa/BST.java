package com.minhaz.dsa;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class BST {
	
	private BinaryNode root;
	
	public BST() {
		root = null;
	}
	
	
	public void insert(int val) {
		
		BinaryNode node = root;
		
		if(root == null) {
			root = new BinaryNode(val);
		} else {
			 while(true) {
				 if(node.getVal() > val) {
					 if(node.left_child == null) {
						 node.left_child = new BinaryNode(val);
						 break;
					 } else {
						 node = node.left_child;
					 }
				 } else {
					 if(node.right_child == null) {
						 node.right_child = new BinaryNode(val);
						 break;
					 } else {
						 node = node.right_child;
					 }
				 }
			 }
			 
			 //node = new BinaryNode(val);
		}
	}
	
	public boolean find(int val) {
		if(isEmpty())
			return false;
		
		return findHelper(this.root, val);
		
	}
	
	// T(n) = O(n)
	public boolean findHelper(BinaryNode node, int val) {
		
		if(node == null)
			return false;
		
		if(node.left_child == null && node.right_child == null) {
			if(node.getVal() == val)
				return true;
			else
				return false;
		}
		
		boolean left = findHelper(node.left_child, val);
		boolean root = node.getVal() == val ? true : false;
		boolean right = findHelper(node.right_child, val);
		
		return (left ? true : false) || (root ? true : false) || (right ? true : false);
	}
	
	// T(n) = O(lgN)
	public boolean findFast(int val) {
		
		if(isEmpty())
			return false;
		
		BinaryNode current = this.root;
		
		while(current != null) {
			if(val < current.getVal())
				current = current.left_child;
			else if(val > current.getVal())
				current = current.right_child;
			else
				return true;
		}
		
		return false;
		
	}
	
	public boolean isEmpty() {
		return this.root == null;
	}
	
	public List<Integer> leverOrder(){
		
		if(isEmpty())
			return null;
		
		List<Integer> nodes = new ArrayList<Integer>();
		Queue<BinaryNode> nodeQueue = new LinkedList<BinaryNode>();
		
		nodeQueue.add(this.root);
		
		while(!nodeQueue.isEmpty()) {
			
			BinaryNode current = nodeQueue.poll();
			nodes.add(current.getVal());
			
			if(current.left_child != null)
				nodeQueue.add(current.left_child);
			
			if(current.right_child != null)
				nodeQueue.add(current.right_child);
			
			
		}
		
		return nodes;
	}
	
	public List<Integer> inorder(){
		if(isEmpty())
			return null;
		
		List<Integer> nodes = new ArrayList<Integer>();
		Stack<BinaryNode> stack = new Stack<BinaryNode>();
		
		BinaryNode current = this.root;
		
		//stack.add(current);
		
		while(!stack.isEmpty() || current != null) {
			if(current != null) {
				stack.add(current);
				current = current.left_child;
			} else {
				current = stack.pop();
				nodes.add(current.getVal());
				current = current.right_child;
			}
		}
		
		return nodes;
		
	}
	
	public List<Integer> preorder(){
		
		if(isEmpty())
			return null;
		
		List<Integer> nodes = new ArrayList<Integer>();
		Stack<BinaryNode> stack = new Stack<BinaryNode>();
		
		BinaryNode current = this.root;
		
		stack.add(current);
		
		while(!stack.isEmpty()) {
			
			current = stack.pop();
			nodes.add(current.getVal());
			
			if(current.right_child != null)
				stack.add(current.right_child);
			
			if(current.left_child != null)
				stack.add(current.left_child);
		}
		
		return nodes;
		
	}
	
	public List<Integer> postorder(){
		if(isEmpty())
			return null;
		
		List<Integer> nodes = new ArrayList<Integer>();
		Stack<BinaryNode> stack = new Stack<BinaryNode>();
		
		BinaryNode current = this.root;
		BinaryNode peekNode = null;
		BinaryNode lastVisitedNode = null;
		
		while(!stack.isEmpty() || current != null) {
			
			
			if(current != null) {
				stack.push(current);
				current = current.left_child;
			} else {
				
				peekNode = stack.peek();
				
				if(peekNode.right_child != null && peekNode.right_child != lastVisitedNode) {
					current = peekNode.right_child;
				} else {
					nodes.add(peekNode.getVal());
					lastVisitedNode = stack.pop();
				}
			}
		}
		
		return nodes;
	}
	
	public boolean isLeaf(BinaryNode node) {
		return node.left_child == null && node.right_child == null;
	}
	
}
