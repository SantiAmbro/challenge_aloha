package challenge;

import challenge.model.*;

import java.util.Scanner;

class CommandExecution {
    private final String COMMAND_SEPARATOR = " ";
    private ExecutionContext context;

    CommandExecution(ExecutionContext context) {
        this.context = context;
    }

    AbstractCommand resolve(String input) throws RuntimeException {
        String[] parts = input.split(COMMAND_SEPARATOR);
        String name = parts[0];
        switch (name) {
            case "quit":
                return new QuitCommand(input);
            case "pwd":
                return new CurrentDirectoryCommand(input);
            case "ls":
                return new ListContentsCommand(input);
            case "mkdir":
                return new MakeDirectoryCommand(input);
            case "cd":
                return new ChangeDirectoryCommand(input);
            case "touch":
                return new CreateFileCommand(input);
            default:
                return new UnrecognizedCommand(input);
        }
    }

    void loop() {
        final Scanner scanner = new Scanner(System.in);
        AbstractCommand command = read(scanner);
        while (!command.exit()) {
            updateContext(command.execute(context));
            command = read(scanner);
        }
    }

    private AbstractCommand read(Scanner scanner) {
        String text = scanner.nextLine();
        return resolve(text);
    }

    private void updateContext(ExecutionContext context) {
        this.context = context;
    }
}