public class ResultsDisplay {
    
    public static void displayError(String operation, Exception e) {
        System.err.printf("ERRO em %s: %s%n", operation, e.getMessage());
    }
    
    public static void displayPointError(int pointNumber, Exception e) {
        System.err.printf("ERRO ao processar ponto %d: %s%n", pointNumber, e.getMessage());
        System.out.println("Ponto será ignorado na análise.");
    }
    
    public static void displayNoValidPointsWarning() {
        System.out.println("\n⚠️  AVISO: Nenhum ponto válido foi processado. Não há resultados para exibir.");
    }
    
    public static void displayFinalResults(PointAnalyzer analyzer) {
        if (!analyzer.hasValidPoints()) {
            displayNoValidPointsWarning();
            return;
        }
        
        System.out.println("\n=== RESULTADOS FINAIS ===");
        System.out.printf("Total de pontos válidos processados: %d%n", analyzer.getValidPointsCount());
        
        if (analyzer.getClosestPoint() != null) {
            System.out.printf("Ponto mais próximo: %s (distância: %.2f)%n", 
                analyzer.getClosestPoint(), analyzer.getMinDistance());
        }
        
        if (analyzer.getFarthestPoint() != null) {
            System.out.printf("Ponto mais distante: %s (distância: %.2f)%n", 
                analyzer.getFarthestPoint(), analyzer.getMaxDistance());
        }
        
        displayQuadrantDistribution(analyzer);
    }
    
    private static void displayQuadrantDistribution(PointAnalyzer analyzer) {
        System.out.println("\nDistribuição por quadrantes:");
        for (int i = 1; i <= 4; i++) {
            int count = analyzer.getQuadrantCount(i);
            System.out.printf("  %d° quadrante: %d ponto(s)%n", i, count);
        }
    }
}