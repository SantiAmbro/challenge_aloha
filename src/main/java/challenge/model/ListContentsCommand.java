package challenge.model;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ListContentsCommand extends AbstractCommand {
    private final String RECURSIVE_OPTION = "-r";

    private final boolean recursive;
    private final String path;

    public ListContentsCommand(String inputText) {
        super(inputText);

        List<String> parts = Arrays.asList(inputText.split(COMMAND_SEPARATOR));

        this.recursive = parts.contains(RECURSIVE_OPTION);

        this.path = parts.subList(1, parts.size())
                .stream()
                .filter(argument -> !argument.equals(RECURSIVE_OPTION))
                .findFirst()
                .orElse("");

    }

    @Override
    public ExecutionContext execute(ExecutionContext context) {
        FileNode current = context.getCurrent();
        FileNode pathNode = getNodeForPath(path, current).orElse(null);

        if (pathNode == null) {
            System.out.println("Invalid Path");
        } else {
            printContents(pathNode, "");
        }

        return super.execute(context);
    }


    private void printContents(FileNode current, String container) {
        final String updated = updateContainer(container, current);

        if (!container.isEmpty() && !current.getChildren().isEmpty()) {
            System.out.println(updated);
        }

        List<FileNode> children = current.getChildren();
        for (FileNode child : children) {
            System.out.println(child.getName());
        }

        if (recursive) {
            List<FileNode> directoryChildren = children.stream()
                    .filter(child -> !child.isDocumentFile())
                    .collect(Collectors.toList());

            directoryChildren.forEach(directory -> printContents(directory, updated));
        }


    }

    private String updateContainer(String container, FileNode node) {
        if (container.isEmpty() && node.isRootFolder()) {
            return "./";
        } else if (container.isEmpty()) {
            return "./" + node.getName() + "/";
        } else {
            return container + node.getName() + "/";
        }
    }

}