package foxminded.ua.app;

import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class InputProcessor {

    private final Scanner scanner;

    public InputProcessor() {
        this.scanner = new Scanner(System.in);
    }

    public Integer getIntInput() {
        System.out.print("here->");
        return Integer.parseInt(scanner.nextLine());
    }

    public String getStringInput() {
        System.out.print("Please, enter value here->");
        return scanner.nextLine();
    }
    
    public Long getLongInput() {
    	System.out.print("here->");
        return Long.parseLong(scanner.nextLine());
    }
}
