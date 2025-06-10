import java.util.InputMismatchException;
import java.util.Scanner;

public class InputHandler {
    private final Scanner scanner;
    
    public InputHandler() {
        this.scanner = new Scanner(System.in);
    }
    
    public Point getOrigin() throws InvalidInputException {
        try {
            System.out.print("Digite a origem do eixo x: ");
            double x = scanner.nextDouble();
            System.out.print("Digite a origem do eixo y: ");
            double y = scanner.nextDouble();
            return new Point(x, y);
        } catch (InputMismatchException e) {
            scanner.nextLine(); // Clear invalid input
            throw new InvalidInputException("Coordenadas da origem devem ser números válidos");
        }
    }
    
    public int getNumberOfPoints() throws InvalidInputException {
        try {
            System.out.print("Digite o número de pontos: ");
            int points = scanner.nextInt();
            if (points <= 0) {
                throw new InvalidInputException("Número de pontos deve ser maior que zero");
            }
            return points;
        } catch (InputMismatchException e) {
            scanner.nextLine(); // Clear invalid input
            throw new InvalidInputException("Número de pontos deve ser um número inteiro válido");
        }
    }
    
    public Point getPoint(int index) throws InvalidInputException {
        try {
            System.out.printf("Ponto %d:%n", index + 1);
            System.out.print("Digite o x do ponto: ");
            double x = scanner.nextDouble();
            System.out.print("Digite o y do ponto: ");
            double y = scanner.nextDouble();
            
            // Validate if coordinates are finite numbers
            if (!Double.isFinite(x) || !Double.isFinite(y)) {
                throw new InvalidInputException("Coordenadas devem ser números finitos");
            }
            
            return new Point(x, y);
        } catch (InputMismatchException e) {
            scanner.nextLine(); // Clear invalid input
            throw new InvalidInputException("Coordenadas do ponto devem ser números válidos");
        }
    }
    
    public void close() {
        scanner.close();
    }
}