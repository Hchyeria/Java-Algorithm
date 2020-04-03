package OOD.InMemoryFileSystem;

public class File extends Entry {
    private byte[] content;
    private int size;

    public File(String n, Directory p, int size) {
        super(n, p);
        this.size = size;
    }

    public int size() {
        return size;
    }

    public String getContent() {
        return new String(content);
    }

    public void setContent(byte[] content) {
        this.content = content;
    }
}
