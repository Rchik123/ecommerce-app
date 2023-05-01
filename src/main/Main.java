package main;

import main.commands.EcommerceCommand;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Ecommerce ecommerce = new Ecommerce();
        EcommerceCommandParser parser = new EcommerceCommandParser(ecommerce);
        EcommerceCommandProcessor processor = new EcommerceCommandProcessor();

        Scanner scanner = new Scanner(System.in);
        while(true) {
            String commandStr = scanner.nextLine();
            if (commandStr.equals("exit")) break;
            EcommerceCommand command = parser.parseCommandStr(commandStr);
            processor.processCommand(command);
        }
    }
}
