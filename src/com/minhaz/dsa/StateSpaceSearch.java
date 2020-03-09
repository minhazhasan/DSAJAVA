package com.minhaz.dsa;

import java.util.*;

public class StateSpaceSearch {

    public Board board;
    public StateSpaceSearch(int[][] board){
        this.board = new Board(board);
    }
    public class Board{
        private int[][] maze;
        private int row = 0;
        private int column = 0;
        public Board parent = null;
        public int[][] goalBoard = getGoalBoard();
        private Map<Integer, Tuple<Integer, Integer>> goalBoardIndexMap;


        public Board(int[][] blocks){
            this.row = blocks.length;
            this.column = blocks[0].length;
            this.maze = new int[row][column];
            this.maze = Arrays.copyOf(blocks, this.row);
        }


        private void makeCopy(int[][] src, int[][] dest) {
            for (int i = 0; i < src.length; i++) {
                for (int j = 0; j < src[0].length; j++) {
                    dest[i][j] = src[i][j];
                }
            }
        }

        private int[][] getGoalBoard(){
            goalBoardIndexMap = new HashMap<>();
            int[][] goalBoard = new int[row][column];
            int counter = 1;
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < column; j++) {
                    if(i == row - 1 && j == column - 1) {
                        goalBoard[i][j] = 0;
                        goalBoardIndexMap.putIfAbsent(0, new Tuple<>(i, j));
                    }
                    else {
                        goalBoard[i][j] = counter;
                        goalBoardIndexMap.putIfAbsent(counter, new Tuple<>(i, j));
                        counter++;
                    }
                }
            }

            return goalBoard;
        }

        public Tuple<Integer, Integer> getBlankPosition(){
            Tuple<Integer, Integer> pair = new Tuple<>(row - 1, column - 1);
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < column; j++) {
                    if(this.maze[i][j] == 0){
                        pair.x = i;
                        pair.y = j;
                    }
                }
            }
            return pair;
        }

        public List<Board> successors(){
            Tuple<Integer, Integer> blankPos = getBlankPosition();
            List<Board> successors = new ArrayList<>();

            // UP
            if(validPair(blankPos.x - 1, blankPos.y)){
                Board newBoard = new Board(swap(createTuple(blankPos.x, blankPos.y), createTuple(blankPos.x - 1, blankPos.y)));
                successors.add(newBoard);
            }

            // Down
            if(validPair(blankPos.x + 1, blankPos.y)){
                Board newBoard = new Board(swap(createTuple(blankPos.x, blankPos.y), createTuple(blankPos.x + 1, blankPos.y)));
                successors.add(newBoard);
            }

            // Left
            if(validPair(blankPos.x, blankPos.y - 1)){
                Board newBoard = new Board(swap(createTuple(blankPos.x, blankPos.y), createTuple(blankPos.x, blankPos.y - 1)));
                successors.add(newBoard);
            }

            // Right
            if(validPair(blankPos.x, blankPos.y + 1)){
                Board newBoard = new Board(swap(createTuple(blankPos.x, blankPos.y), createTuple(blankPos.x, blankPos.y + 1)));
                successors.add(newBoard);
            }

            return successors;

        }

        public int getManhattanDistance(){
            int[][] blocks = getGoalBoard();
            int distance = 0;
            for (int i = 0; i < this.row; i++) {
                for (int j = 0; j < this.column; j++) {
                    if(blocks[i][j] != this.maze[i][j]) {
                        Tuple<Integer, Integer> actualPos = goalBoardIndexMap.get(this.maze[i][j]);
                        distance += Math.abs(i - actualPos.x) + Math.abs(j - actualPos.y);
                    }
                }
            }
            return distance;
        }

        public int getHammingDistance(){
          int[][] goalBlocks = getGoalBoard();
          int misplacedTilesCounter = 0;
          for(int i = 0; i < this.row; i++){
              for(int j = 0; j < this.column; j++){
                  if(this.maze[i][j] != goalBlocks[i][j])
                      misplacedTilesCounter++;
              }
          }
          return misplacedTilesCounter;
        }

        private Tuple<Integer, Integer> createTuple(Integer x, Integer y){
            return new Tuple<>(x, y);
        }

        private int[][] swap(Tuple<Integer, Integer> oldPos, Tuple<Integer, Integer> newPos){
            int[][] newBoard = new int[this.row][this.column];
            makeCopy(this.maze, newBoard);
            int temp = newBoard[oldPos.x][oldPos.y];
            newBoard[oldPos.x][oldPos.y] = newBoard[newPos.x][newPos.y];
            newBoard[newPos.x][newPos.y] = temp;
            return newBoard;

        }

        private boolean validPair(int x, int y){

            boolean flag = true;
            if((x <= -1) || (x >= this.row))
                flag = false;
            else if((y <= -1) || (y >= this.column))
                flag = false;
            else
                flag = true;

            return flag;
        }

        @Override
        public int hashCode() {
            //return super.hashCode();
            return Arrays.deepHashCode(this.maze);
        }

        @Override
        public boolean equals(Object obj) {
            if(obj == null) return false;
            if(!(obj instanceof Board)) return false;
            if(this == obj) return true;
            Board board = (Board) obj;
            return Arrays.deepHashCode(this.maze) == Arrays.deepHashCode(board.maze);
        }

        @Override
        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("{");
            for (int i = 0; i < this.row; i++) {
                for (int j = 0; j < this.column; j++) {
                    stringBuilder.append(this.maze[i][j]);
                    if(i == this.row - 1 && j == this.column - 1)
                        continue;
                    stringBuilder.append(",");
                }
            }
            stringBuilder.append("}");
            return stringBuilder.toString();
        }
    }

    public class Tuple<X, Y>{
        public X x;
        public Y y;
        public Tuple(X x, Y y){
            this.x = x;
            this.y = y;
        }
    }

    public int getMoves(Board obj){
        int count = 0;
        while (!(obj.parent == null)){
            obj = obj.parent;
            count++;
        }

        return count;
    }

    public int bfsSlidingPuzzle(Board initialBoard){

        int expanded = 0;
        if(initialBoard == null) return 0;

        Board goalBoard = new Board(initialBoard.getGoalBoard());

        if(Arrays.deepEquals(initialBoard.maze, initialBoard.getGoalBoard()))
            return getMoves(initialBoard);

        Queue<Board> frontier = new LinkedList<>();
        HashSet<Board> visited = new HashSet<>();
        frontier.add(initialBoard);
        while (!frontier.isEmpty()){
            Board currBoard = frontier.remove();
            visited.add(currBoard);
            expanded++;
            for(Board nextAction : currBoard.successors()){
                if(!visited.contains(nextAction)){
                    nextAction.parent = currBoard;
                    if(nextAction.equals(goalBoard))
                        return getMoves(nextAction);
                    frontier.add(nextAction);
                }

            }
        }
        System.out.println("Expanded Nodes: " + expanded);
        return -1;
    }
}
