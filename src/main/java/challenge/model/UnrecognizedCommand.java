package challenge.model;

public class UnrecognizedCommand extends AbstractCommand {
    public UnrecognizedCommand(String inputText) {
        super(inputText);
    }

    @Override
    public ExecutionContext execute(ExecutionContext context) {
        System.out.println("Unrecognized command");
        return super.execute(context);
    }
}