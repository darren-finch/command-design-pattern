package com.all.sandboxjava.designpatterns.command;

public class Canvas {
    public static class Point {
        private final int x;
        private final int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }

    public static void paint(Point location, int radius) {
        System.out.println("Canvas: Painting at " + location.toString() + " with a brush radius of " + radius);
    }

    public static void erase(Point location, int radius) {
        System.out.println("Canvas: Erasing at " + location.toString() + " with a eraser radius of " + radius);
    }

    public static void pickColor(Point location) {
        System.out.println("Canvas: Picking color from " + location);
    }
}
