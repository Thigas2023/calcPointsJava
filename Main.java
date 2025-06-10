public class Main {
    public static void main(String[] args) {
        InputHandler inputHandler = new InputHandler();
        PointAnalyzer analyzer = null;
        
        try {
            // Block 1: Inicializa o sistema
            Point origin = inputHandler.getOrigin();
            int numberOfPoints = inputHandler.getNumberOfPoints();
            
            analyzer = new PointAnalyzer(origin);
            System.out.println(); 
            
            // Block 2: Processa cada ponto
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
                
                System.out.println();
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
        
        // Block 3: Mostra os resultados
        try {
            if (analyzer != null) {
                ResultsDisplay.displayFinalResults(analyzer);
            }
        } catch (Exception e) {
            ResultsDisplay.displayError("exibição de resultados", e);
        }
        
        try {
            inputHandler.close();
        } catch (Exception e) {
            ResultsDisplay.displayError("fechamento de recursos", e);
        }
    }
}
