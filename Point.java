public class Point {
    private final double x;
    private final double y;
    
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
    
    public double getX() {
        return x;
    }
    
    public double getY() {
        return y;
    }
    
    public double distanceTo(Point other) {
        return Math.sqrt(Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2));
    }
    
    public boolean isAtOrigin() {
        return x == 0 && y == 0;
    }
    
    public boolean isOnAxis(Point origin) {
        return x == origin.x || y == origin.y;
    }
    
    public int getQuadrant(Point origin) {
        if (isOnAxis(origin)) {
            return 0;
        }
        
        if (x > origin.x) {
            return (y > origin.y) ? 1 : 4;
        } else {
            return (y > origin.y) ? 2 : 3;
        }
    }
    
    @Override
    public String toString() {
        return String.format("(%.1f, %.1f)", x, y);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Point point = (Point) obj;
        return Double.compare(point.x, x) == 0 && Double.compare(point.y, y) == 0;
    }
}