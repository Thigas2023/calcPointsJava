public class Main {
    public static void main(String[] args) {
        InputHandler inputHandler = new InputHandler();
        PointAnalyzer analyzer = null;
        
        try {
            // Block 1: Initialize system (origin and point count)
            Point origin = inputHandler.getOrigin();
            int numberOfPoints = inputHandler.getNumberOfPoints();
            
            analyzer = new PointAnalyzer(origin);
            System.out.println(); // Empty line for readability
            
            // Block 2: Process each point with individual error handling
            for (int i = 0; i < numberOfPoints; i++) {
                try {
                    Point point = inputHandler.getPoint(i);
                    String classification = analyzer.analyzeAndClassifyPoint(point);
                    System.out.println(classification);
                    
                } catch (InvalidInputException e) {
                    ResultsDisplay.displayPointError(i + 1, e);
                } catch (AnalysisException e) {
                    ResultsDisplay.displayPointError(i + 1, e);
                } catch (Exception e) {
                    ResultsDisplay.displayPointError(i + 1, new Exception("Erro inesperado", e));
                }
                
                System.out.println(); // Empty line between points
            }
            
        } catch (InvalidInputException e) {
            ResultsDisplay.displayError("inicialização", e);
            System.out.println("Programa encerrado devido a erro na entrada inicial.");
            return;
            
        } catch (Exception e) {
            ResultsDisplay.displayError("execução", new Exception("Erro inesperado durante execução", e));
            System.out.println("Programa encerrado devido a erro crítico.");
            return;
        }
        
        // Block 3: Display results (only if analyzer was created)
        try {
            if (analyzer != null) {
                ResultsDisplay.displayFinalResults(analyzer);
            }
        } catch (Exception e) {
            ResultsDisplay.displayError("exibição de resultados", e);
        }
        
        // Block 4: Cleanup resources
        try {
            inputHandler.close();
        } catch (Exception e) {
            ResultsDisplay.displayError("fechamento de recursos", e);
        }
    }
}