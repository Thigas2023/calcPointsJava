public class PointAnalyzer {
    private Point closestPoint;
    private Point farthestPoint;
    private double minDistance;
    private double maxDistance;
    private final int[] quadrantCounts;
    private final Point origin;
    private int validPointsCount;
    
    public PointAnalyzer(Point origin) {
        this.origin = origin;
        this.minDistance = Double.POSITIVE_INFINITY;
        this.maxDistance = 0;
        this.quadrantCounts = new int[4]; // Quadrantes 1-4
        this.validPointsCount = 0;
    }
    
    public String analyzeAndClassifyPoint(Point point) throws AnalysisException {
        if (point == null) {
            throw new AnalysisException("Ponto não pode ser nulo");
        }
        
        try {
            double distance = point.distanceTo(origin);
            
            if (!Double.isFinite(distance)) {
                throw new AnalysisException("Distância calculada é inválida para o ponto " + point);
            }
            
            updateDistanceExtremes(point, distance);
            updateQuadrantCount(point);
            validPointsCount++;
            
            return classifyPoint(point);
        } catch (Exception e) {
            throw new AnalysisException("Erro ao analisar ponto " + point + ": " + e.getMessage(), e);
        }
    }
    
    private void updateDistanceExtremes(Point point, double distance) {
        if (distance < minDistance) {
            minDistance = distance;
            closestPoint = point;
        }
        
        if (distance > maxDistance) {
            maxDistance = distance;
            farthestPoint = point;
        }
    }
    
    private void updateQuadrantCount(Point point) {
        int quadrant = point.getQuadrant(origin);
        if (quadrant > 0) {
            quadrantCounts[quadrant - 1]++;
        }
    }
    
    private String classifyPoint(Point point) {
        if (point.isAtOrigin()) {
            return String.format("O ponto %s está na origem", point);
        }
        
        if (point.isOnAxis(origin)) {
            return String.format("O ponto %s está sobre o eixo das coordenadas", point);
        }
        
        int quadrant = point.getQuadrant(origin);
        return String.format("O ponto %s está no %d° quadrante", point, quadrant);
    }
    
    // Getters
    public Point getClosestPoint() { return closestPoint; }
    public Point getFarthestPoint() { return farthestPoint; }
    public double getMinDistance() { return minDistance; }
    public double getMaxDistance() { return maxDistance; }
    public int[] getQuadrantCounts() { return quadrantCounts.clone(); }
    public int getValidPointsCount() { return validPointsCount; }
    
    public int getQuadrantCount(int quadrant) {
        if (quadrant < 1 || quadrant > 4) {
            throw new IllegalArgumentException("O Quadrante deve estar entre 1 e 4");
        }
        return quadrantCounts[quadrant - 1];
    }
    
    public boolean hasValidPoints() {
        return validPointsCount > 0;
    }
}