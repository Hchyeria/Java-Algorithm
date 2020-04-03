package OOD.InMemoryFileSystem;

import java.util.ArrayList;
import java.util.List;

public class FileSystem {
    private final Directory root;

    public FileSystem() {
        root = new Directory("/", null);
    }

    public List<Entry> resolve(String path) {
        assert path.startsWith("/");
        String[] components = path.substring(1).split("/");
        List<Entry> entries = new ArrayList<>(components.length + 1);
        entries.add(root);
        Entry entry = root;
        for (String component : components) {
            if (entry == null || !(entry instanceof Directory)) {
                throw new IllegalArgumentException("invalid path: " + path);
            }
            if (!component.isEmpty()) {
                entry = ((Directory) entry).getChild(component);
                entries.add(entry);
            }
        }
        return entries;
    }

    public void mkdir(String path) {
        List<Entry> entries = resolve(path);
        if (entries.get(entries.size() - 1) != null) {
            throw new IllegalArgumentException("Director already exits: " + path);
        }
        String[] components = path.split("/");
        final String dirName = components[components.length - 1];
        final Directory parent = (Directory) entries.get(entries.size() - 2);
        Directory newDir = new Directory(dirName, parent);
        parent.addEntry(newDir);
    }

    public void createFile(String path, int size) {
        assert !path.endsWith("/");
        List<Entry> entries = resolve(path);
        if (entries.get(entries.size() - 1) != null) {
            throw new IllegalArgumentException("File already exits: " + path);
        }
        final String fileName = path.substring(path.lastIndexOf("/" ) + 1);
        final Directory parent = (Directory) entries.get(entries.size() - 2);
        File file = new File(fileName, parent, size);
        parent.addEntry(file);
    }

    public void delete(String path) {
        List<Entry> entries = resolve(path);
        if (entries.get(entries.size() - 1) == null) {
            throw new IllegalArgumentException("The target Entry doesn't exists: " + path);

        }
        Entry targetEntry = entries.get(entries.size() - 1);
        targetEntry.delete();
    }

    public List<Entry> list(String path) {
        List<Entry> children = new ArrayList<>();
        List<Entry> entries = resolve(path);
        Entry targetEntry = entries.get(entries.size() - 1);
        if (targetEntry instanceof File) {
            throw new IllegalArgumentException("the target Direction doesn't exists: " + path);
        }
        for (Entry e : ((Directory)targetEntry).getContent()) {
            if (e instanceof Directory) {
                children.add(e);
            }
        }
        return children;
    }

    public int count() {
        int count = 1;
        for (Entry e : root.getContent()) {
            if (e instanceof Directory) {
                count += ((Directory)e).numberOfFiles() + 1;
            } else {
                count++;
            }
        }
        return count;
    }
}
