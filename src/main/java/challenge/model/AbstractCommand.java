package challenge.model;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Optional;

public abstract class AbstractCommand {
    protected final String PATH_SEPARATOR = "/";
    protected final String COMMAND_SEPARATOR = " ";

    private String name;

    public AbstractCommand(String inputText) {
        this.name = inputText;
    }

    public ExecutionContext execute(ExecutionContext context) {
        return context;
    }

    public boolean exit() {
        return false;
    }

    protected Optional<FileNode> getNodeForPath(String path, FileNode current) {
        Iterator<String> pathElements = Arrays
                .stream(path.split(PATH_SEPARATOR))
                .iterator();

        Optional<FileNode> tmp = Optional.of(current);
        while (pathElements.hasNext()) {
            String dirName = pathElements.next();
            if (!dirName.isEmpty()) {
                tmp = tmp.flatMap(node -> node.getChild(dirName));
            }

        }

        return tmp;
    }
}