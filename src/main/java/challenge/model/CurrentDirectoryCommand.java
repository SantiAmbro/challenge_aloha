package challenge.model;

public class CurrentDirectoryCommand extends AbstractCommand {
    public CurrentDirectoryCommand(String inputText) {
        super(inputText);
    }

    @Override
    public ExecutionContext execute(ExecutionContext context) {
        FileNode current = context.getCurrent();
        printCurrentDirectory(current);
        return super.execute(context);
    }

    private void printCurrentDirectory(FileNode current) {
        StringBuilder builder = new StringBuilder();
        builder.append(current.getName());
        FileNode tmp = current.getParent();
        while (tmp != null) {
            prepend(builder, tmp);
            tmp = tmp.getParent();
        }
        // Add root origin
        builder.insert(0, "/");

        System.out.println(builder);
    }

    private void prepend(StringBuilder builder, FileNode node) {
        if (node.isDocumentFile()) {
            builder.insert(0, node.getName());
        } else {
            builder.insert(0, node.getName() + "/");
        }
    }
}