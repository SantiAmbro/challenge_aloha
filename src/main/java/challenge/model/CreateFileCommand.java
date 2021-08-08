package challenge.model;

import java.util.Arrays;

public class CreateFileCommand extends AbstractCommand {

    private final String fileName;

    public CreateFileCommand(String inputText) {
        super(inputText);


        String[] parts = inputText.split(COMMAND_SEPARATOR);

        this.fileName = Arrays
                .asList(parts)
                .subList(1, parts.length)
                .stream()
                .findFirst()
                .orElse("");
    }

    @Override
    public ExecutionContext execute(ExecutionContext context) {
        if (fileName.isEmpty()) {
            System.out.println("Invalid Command");
        } else if (fileName.length() > 100) {
            System.out.println("Invalid File or Folder Name");
        } else {
            FileNode current = context.getCurrent();
            if (current.contains(fileName)) {
                System.out.println("Directory already exists");
            } else {
                FileNode directory = new FileNode(fileName, current, false, true);
                current.addChild(directory);
            }

        }
        return super.execute(context);
    }
}