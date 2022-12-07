package g;

public class Singleton {
    private int filesBelowSize;
    private ElfDirectory smallestToDelete;

    public Singleton() {
        filesBelowSize = 0;
        smallestToDelete = new ElfDirectory("empty", this);
    }

    public int getFilesBelowSize() {
        return filesBelowSize;
    }

    public void setSmallestToDelete(ElfDirectory e)
    {
        System.out.println("Setting new smallest to delete directory to " + e);
        smallestToDelete = e;
    }

    public void addFilesBelowSize(int size) {
        filesBelowSize+=size;
    }

    public ElfDirectory getSmallestToDelete()
    {
        return smallestToDelete;
    }
}
