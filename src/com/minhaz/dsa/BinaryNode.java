package com.minhaz.dsa;

public class BinaryNode {
	
	private int val;
	public BinaryNode left_child, right_child;
	
	public BinaryNode(int val) {
		this.val = val;
		this.left_child = null;
		this.right_child = null;
	}
	
	public void setLeftChild(BinaryNode node) {
		this.left_child = node;
	}
	
	public void setRightChild(BinaryNode node) {
		this.right_child = node;
	}
	
	public BinaryNode getLeftChild() {
		return this.left_child;
	}
	
	public BinaryNode getRightChild() {
		return this.right_child;
	}
	
	public int getVal() {
		return this.val;
	}

}
