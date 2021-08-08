package challenge.model;

import java.util.Arrays;

public class ChangeDirectoryCommand extends AbstractCommand {
    private final String ROOT_REFERENCE = "/";
    private final String PARENT_REFERENCE = "..";
    private final String dirName;

    public ChangeDirectoryCommand(String inputText) {
        super(inputText);
        String[] parts = inputText.split(COMMAND_SEPARATOR);
        this.dirName = Arrays
                .asList(parts)
                .subList(1, parts.length)
                .stream()
                .findFirst()
                .orElse("");
    }

    @Override
    public ExecutionContext execute(ExecutionContext context) {
        FileNode current = context.getCurrent();

        if (dirName.isEmpty()) {
            System.out.println("Invalid Command");
        } else {
            switch (dirName) {
                case ROOT_REFERENCE:
                    updateContext(context, context.getRoot());
                    break;

                case PARENT_REFERENCE:
                    if (!current.isRootFolder()) {
                        updateContext(context, current.getParent());
                    }
                    break;

                default:
                    if (dirName.contains("/")) {
                        FileNode destination = getNodeForPath(dirName, current).orElse(null);
                        if (destination == null) {
                            System.out.println("Invalid Path");
                        } else {
                            updateContext(context, destination);
                        }
                    } else if (!current.hasChild(dirName)) {
                        System.out.println("Directory not found");
                    } else {
                        current.getChild(dirName).ifPresent(newCurrent -> updateContext(context, newCurrent));
                    }
                    break;
            }
        }
        return super.execute(context);
    }

    private void updateContext(ExecutionContext context, FileNode newCurrent) {
        context.setCurrent(newCurrent);
    }
}