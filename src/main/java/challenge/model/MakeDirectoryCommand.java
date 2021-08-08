package challenge.model;

import java.util.Arrays;

public class MakeDirectoryCommand extends AbstractCommand {

    private String dirName;

    public MakeDirectoryCommand(String inputText) {
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
        if (dirName.isEmpty()) {
            System.out.println("Invalid Command");
        } else if (dirName.length() > 100) {
            System.out.println("Invalid File or Folder Name");
        } else {
            FileNode current = context.getCurrent();

            if (current.contains(dirName)) {
                System.out.println("Directory already exists");
            } else {
                FileNode directory = new FileNode(dirName, current, false, false);
                current.addChild(directory);
            }
        }

        return super.execute(context);
    }
}