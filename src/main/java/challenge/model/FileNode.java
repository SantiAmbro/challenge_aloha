package challenge.model;

import challenge.exception.InvalidFileException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FileNode implements Serializable {
    private final String name;

    private final FileNode parent;
    private final List<FileNode> children;

    private final boolean isRoot;
    private final boolean isDocument;

    public FileNode(String name, FileNode parentNode, boolean isRoot, boolean isDocument) throws RuntimeException {
        if (isRoot && isDocument) {
            throw new InvalidFileException("Root cannot be a File");
        }

        this.parent = isRoot ? null : parentNode;
        this.name = name;
        this.children = new ArrayList<>();

        this.isRoot = isRoot;
        this.isDocument = isDocument;
    }

    public String getName() {
        return this.name;
    }

    public FileNode getParent() {
        return this.parent;
    }

    public boolean isRootFolder() {
        return this.isRoot;
    }

    public boolean isDocumentFile() {
        return this.isDocument;
    }

    public void addChild(FileNode child) throws InvalidFileException {
        if (isDocumentFile()) {
            throw new InvalidFileException("File is not a Folder");
        }
        if (contains(child.getName())) {
            throw new InvalidFileException("File or Folder already exists");
        }
        children.add(child);
    }

    public boolean contains(String name) {
        // Look for children when file node is not a document
        return !isDocumentFile() && hasChild(name);
    }

    public List<FileNode> getChildren() {
        return children;
    }

    public boolean hasChild(String childName) {
        return this.children.stream().anyMatch(child -> child.getName().equals(childName));
    }

    public Optional<FileNode> getChild(String childName) {
        if (!this.children.isEmpty()) {
            for (FileNode childNode : this.children) {
                if (childNode.getName().equals(childName))
                    return Optional.of(childNode);
            }
        }
        return Optional.empty();
    }
}