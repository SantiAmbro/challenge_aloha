package challenge.model;

public class QuitCommand extends AbstractCommand {
    public QuitCommand(String inputText) {
        super(inputText);
    }

    @Override
    public ExecutionContext execute(ExecutionContext context) {
        return super.execute(context);
    }

    @Override
    public boolean exit() {
        return true;
    }
}