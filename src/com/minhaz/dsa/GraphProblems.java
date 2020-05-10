    package com.minhaz.dsa;

import java.util.*;

public class GraphProblems {

//    Stack<Integer> stack = new Stack<>();
//    HashSet<Integer> visited = new HashSet<>();

    public static List<Integer> DFS(int[][] graph){
        Set<Integer> visited = new HashSet<>();
        Stack<Integer> stack = new Stack<>();
        List<Integer> list = new LinkedList<>();

        for(int row = 0; row < graph.length; row++){
            stack.add(row);
            while (!stack.isEmpty()){

                int currentNode = stack.pop();

                if(!visited.contains(currentNode)) {
                    visited.add(currentNode);
                    list.add(currentNode);
                    for (int col = 0; col < graph[0].length; col++) {
                        if (graph[currentNode][col] == 1)
                            stack.add(col);
                    }
                }
            }
        }
        return list;
    }

    public static List<Integer> DFSRec(int[][] graph){
        Set<Integer> visited = new HashSet<>();
        List<Integer> list = new LinkedList<>();
        int start = 0;
        return DFSRecHelper(graph, start, visited, list);
    }

    private static List<Integer> DFSRecHelper(int[][] graph, int start, Set<Integer> visited, List<Integer> list) {
        if(!visited.contains(start) && start < graph.length) {
            visited.add(start);
            list.add(start);
            for (int i = 0; i < graph[0].length; i++) {
                if (graph[start][i] == 1)
                    DFSRecHelper(graph, i, visited, list);
            }
        }

        return list;
    }

    public static List<List<Integer>> CC(int[][] graph){
        List<List<Integer>> comps = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();


        for(int i = 0; i < graph.length; i++){
            if(!visited.contains(i)){
                List<Integer> comp = new LinkedList<>();
                comps.add(DFSRecHelper(graph, i, visited, comp));
            }
        }

        return comps;
    }

    static Stack<Integer> stack = new Stack<>();
    static HashSet<Integer> visited = new HashSet<>();
    static List<Integer> gList;

    public static List<List<Integer>> SCC(int[][] graph){

        //1. DFS on graph
        DFSHelper(graph, 0);
        int[][] reversedGraph = getTranspose(graph);

        List<List<Integer>> components = new ArrayList<>();
        visited.clear();

        while (!stack.isEmpty()){
            int vertex = stack.pop();
            if(!visited.contains(vertex)){
                gList = new LinkedList<>();
                //visited.add(vertex);
                components.add(DFSRecHelper(reversedGraph, vertex, visited, gList));

            }
        }
        return components;
    }

//    public static List<Integer> ArticulationPoints(int[][] graph){
//
//    }

    private static void DFSHelper(int[][] graph, int source){

        visited.add(source);

        for(int i = 0; i < graph[0].length; i++){
            if(graph[source][i] == 1 && !visited.contains(i))
                DFSHelper(graph, i);
        }

        stack.push(source);
    }

    private static int[][] getTranspose(int[][] graph){
        int[][] revGraph = new int[graph.length][graph[0].length];
        for(int row = 0; row < graph.length; row++){
            for(int col = 0; col < graph[0].length; col++){
                if(graph[row][col] == 1){
                    revGraph[col][row] = 1;
                }
            }
        }
        return revGraph;
    }
}
