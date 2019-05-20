package com.hm707.queue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Island {

    public static void main(String[] args) {
        //Island la = new Island();
        //char[][] grid = {
        //    {'1','1','1','1','0'},
        //    {'1','1','0','1','0'},
        //    {'1','1','0','0','0'},
        //    {'0','0','0','0','0'}
        //};
        //int result = la.numIslands(grid);
        //System.out.println(result);

        System.out.println((int)('9') - 48);
    }
    class Point{
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        int x, y;
    }
   
    boolean[][] flagArray;
    Queue<Point> queue = new LinkedList<>();

     public int numIslands(char[][] grid) {
        if(grid.length == 0){
            return 0;
        }

        int landsCount = 0;
        flagArray = new boolean[grid.length][grid[0].length];

        int startX = 0;
        int startY = 0;

        for (Point rootPoint = findRootNode(grid, new Point(startX, startY)); rootPoint != null; startX = rootPoint.x, startY = rootPoint.y, rootPoint = findRootNode(grid, new Point(startX, startY))) {

            landsCount++;

            queue.offer(rootPoint);
            markVisit(rootPoint);

            while (!queue.isEmpty()) {
                Point parentPoint = queue.poll();
                List<Point> leafPoint = findLeafPoint(grid, parentPoint);
                if (leafPoint.isEmpty()) {
                    continue;
                }
                for (Point p : leafPoint) {
                    queue.offer(p);
                    markVisit(p);
                }
            }
        }

        return landsCount;
    }

    private boolean hasLand(char[][] grid) {
        //判断是否存在 未访问过并且是值是1的位置
        for(char x = 0; x < grid.length; x++){
            for(char y = 0; y < grid[x].length; y++){

                if(grid[x][y] == '1' && !flagArray[x][y]){
                   return true;
                }
            }
        }

        return false;
    }

    private List<Point> findLeafPoint(char[][] grid, Point parentPoint) {
        List<Point> points = new ArrayList<>();
        //up
        int upY = parentPoint.y - 1;
        if(upY >= 0 && grid[parentPoint.x][upY] == '1' && !flagArray[parentPoint.x][upY]){
            points.add(new Point(parentPoint.x, upY));
        }

        //down
        int downY = parentPoint.y + 1;
        if(downY < grid[parentPoint.x].length && grid[parentPoint.x][downY] == '1' && !flagArray[parentPoint.x][downY]){
            points.add(new Point(parentPoint.x, downY));
        }
        
        //left
        int leftX = parentPoint.x - 1;
        if(leftX >= 0 && grid[leftX][parentPoint.y] == '1' && !flagArray[leftX][parentPoint.y]){
            points.add(new Point(leftX, parentPoint.y));
        }
        //right
        int rightX = parentPoint.x + 1;
        if(rightX < grid.length && grid[rightX][parentPoint.y] == '1' && !flagArray[rightX][parentPoint.y]){
            points.add(new Point(rightX, parentPoint.y));
        }
        return points;
    }

    private Point findRootNode(char[][] grid, Point startPoint) {
        for(int x = startPoint.x; x < grid.length; x++){
            int y = 0;
            if(x == startPoint.x){
                y = startPoint.y;
            }
            for(; y < grid[x].length; ){

                if(grid[x][y] == '0' || flagArray[x][y]){
                    y++;
                    continue;
                }

                return new Point(x, y);
            }
        }

        return null;
    }


    private void markVisit(Point visitPoint){

        flagArray[visitPoint.x][visitPoint.y] = true;

    }


    private void printArr(boolean[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                System.out.print(grid[i][j] + "  ");
            }
            System.out.println();
        }
    }
   
}