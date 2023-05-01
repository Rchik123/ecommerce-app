package main.commands;

public class InvalidCommand implements EcommerceCommand {

    @Override
    public void execute() {
        System.out.println("invalid command");
    }
}
