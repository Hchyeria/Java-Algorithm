package OOD.InMemoryFileSystem;

import java.util.ArrayList;
import java.util.List;

public class Directory extends Entry {
    protected List<Entry> content;
    public Directory(String n, Directory p) {
        super(n, p);
        content = new ArrayList<>();
    }

    protected List<Entry> getContent() {
        return content;
    }

    public int size() {
        int size = 0;
        for (Entry e : content) {
            size += e.size();
        }
        return size;
    }

    public int numberOfFiles() {
        int count = 0;
        for (Entry e : content) {
            if (e instanceof Directory) {
                Directory d = (Directory) e;
                count += d.numberOfFiles() + 1;
            } else if (e instanceof File) {
                count++;
            }
        }
        return count;
    }

    public boolean deleteEntry(Entry entry) {
        return content.remove(entry);
    }

    public void addEntry(Entry entry) {
        content.add(entry);
    }

    public Entry getChild(String entryName) {
        for (Entry e : content) {
            if (e.getName().equals(entryName)) {
                return e;
            }
        }
        return null;
    }
}
