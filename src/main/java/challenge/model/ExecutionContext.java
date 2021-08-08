package challenge.model;

public class ExecutionContext {

    private final FileNode root;
    private FileNode current;

    public ExecutionContext(FileNode fileSystem) {
        this.root = fileSystem;
        this.current = fileSystem;
    }

    public FileNode getRoot() {
        return root;
    }

    public FileNode getCurrent() {
        return current;
    }

    public void setCurrent(FileNode current) {
        this.current = current;
    }
}