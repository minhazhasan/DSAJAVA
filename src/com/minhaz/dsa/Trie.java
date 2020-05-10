package com.minhaz.dsa;

import com.sun.jdi.event.StepEvent;

import java.util.*;

public class Trie {

    private TreeNode root = new TreeNode('#');

    class TreeNode {
        char val; // value of the node
        Map<Character, TreeNode> children; // children of the node
        boolean endOfTheWord; // end character of a word
        boolean isVisited;
        int depth;
        TreeNode parent;


        public TreeNode(char v) {
            this.val = v;
            children = new HashMap<>();
            endOfTheWord = false;
            isVisited = false;
            depth = 0;
            parent = null;
        }

        public char getNodeVal() {
            return this.val;
        }

        public void setEndOfTheWord(boolean flag) {
            this.endOfTheWord = flag;
        }

        public boolean hasChild(char c) {
            return children.containsKey(c);
        }

        public void addChild(char c) {
            children.put(c, new TreeNode(c));
        }

        public TreeNode getChild(char c) {
            return children.get(c);
        }

        public TreeNode[] getChildren() {
            return this.children.values().toArray(new TreeNode[0]);
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append(getNodeVal() + " -> ");

            TreeNode[] nodes = getChildren();
            for(int i = 0; i < nodes.length; i++){
                if(i == nodes.length - 1) {
                    builder.append(nodes[i].getNodeVal());
                } else {
                    builder.append(nodes[i].getNodeVal() + " -> ");
                }
            }

            return builder.toString();
        }
    }

    public void createTrie(String[] words) {
        for (String word : words) {
            insertIntoTrie(word);
        }
    }

    public void insertIntoTrie(String word) {

        // set current to root
        // extract each character of the word and check if it is a child of the current node
        // if it's not add it as a child of the current node
        // set current node to one its child node if it's not empty.
        TreeNode current = root;
        char[] charArr = word.toCharArray();
        for (char c : charArr) {
            if (!current.hasChild(c))
                current.addChild(c);

            // DS Augmentation -> node parent & depth
            TreeNode temp = current;
            current = current.getChild(c);
            current.parent = temp;
            current.depth = current.parent.depth + 1;
        }
        current.setEndOfTheWord(true);
    }

    public boolean lookup(String word) {

        if (word == null)
            return false;

        TreeNode current = root;
        for (char c : word.toCharArray()) {
            if (!current.hasChild(c))
                return false;
            current = current.getChild(c);
        }

        return current.endOfTheWord;
    }

    public void preOrderTraverse() {

        // dfs traversal, start with root left right

        List<String> words = new ArrayList<>(){
            @Override
            public String toString() {
                StringBuilder builder = new StringBuilder();
                builder.append("{");
                for(int i = 0; i < this.size(); i++) {
                    if(i == this.size() - 1) {
                        builder.append(this.get(i));
                    }
                    builder.append(this.get(i) + ",");

                }
                return builder.toString();
            }
        };

        if (root.children.isEmpty())
            System.out.println("Don't have any words");

        StringBuilder builder = null;
        TreeNode current = root;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(current);

        while (!stack.isEmpty()) {

            current = stack.pop();

            if(current.depth == 1)
                builder = new StringBuilder();

            if (!current.isVisited) {
                current.isVisited = true;

                if (current.depth > 0 && builder != null)
                    builder.append(current.val);


                if (current.endOfTheWord == true) {
                    words.add(builder.toString());
                }

                for (TreeNode child : current.getChildren()) stack.push(child);
            }
        }
    }
}
