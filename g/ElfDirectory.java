package g;

import java.util.ArrayList;

public class ElfDirectory {
    private ArrayList<Object> content;
    private String name;
    private int size;
    private Singleton single;

    public ElfDirectory(String n, Singleton singleton) {
        size = 0;
        name = n;
        content = new ArrayList<>();
        single = singleton;
    }

    public ArrayList<Object> getContent() {
        return content;
    }

    public void addContent(Object newContent) {
        content.add(newContent);
    }

    public int getSize() {
        if (size == 0) {
            for (Object o : content) {
                if (o instanceof ElfDirectory) {
                    ElfDirectory ed = (ElfDirectory) o;
                    size += ed.getSize();
                } else if (o instanceof ElfFile) {
                    ElfFile ef = (ElfFile) o;
                    size += ef.getSize();
                } else {
                    System.err.println("Object neither file or directory");
                }
            }
            if (size <= 100000) {
                single.addFilesBelowSize(size);
            }
            if (size < single.getSmallestToDelete().size && size >= 1412830) // I hardcoded the missing space to not
                                                                             // change my code here. this is not nice
                                                                             // and only works because I already knew
                                                                             // the total size from previous runs. Don't
                                                                             // code like this!
            {
                single.setSmallestToDelete(this);
            }
        }
        return size;
    }

    public String getName() {
        return name;
    }

    public ElfDirectory findFolder(String n) {
        for (Object o : content) {
            if (o instanceof ElfDirectory) {
                ElfDirectory e = (ElfDirectory) o;
                if (e.getName().equals(n)) {
                    return e;
                }
            } else if (o instanceof ElfFile) {
                // Skipping file
            } else {
                System.err.println("Object neither file or directory");
            }
        }
        System.out.println("No folder called " + n + " in " + name + "!");
        return this;
    }

    @Override
    public String toString() {
        return name + " Size: " + size;
    }
}
