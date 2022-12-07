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
        size = 0; // reset size when adding new content
    }

    public String getName() {
        return name;
    }

    // Recursive function to index the size of folders in the filesystem.
    // Without change, indexing does not have to be repeated.
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
        }
        return size;
    }

    // Finding a nested folder. Used when CDing into a subfolder
    public ElfDirectory findFolder(String n) {
        for (Object o : content) {
            if (o instanceof ElfDirectory) {
                ElfDirectory e = (ElfDirectory) o;
                if (e.getName().equals(n)) {
                    return e;
                }
            }
        }
        System.out.println("No folder called " + n + " in " + name + "!");
        return this;
    }

    // Recursive function to find the answer to task 1
    // Finding all folders with a size of less than a given amount
    public void findDirectoriesUnderSize(int maxSize) {
        // Recur this function for subfolders.
        for (Object o : content) {
            if (o instanceof ElfDirectory) {
                ElfDirectory ed = (ElfDirectory) o;
                ed.findDirectoriesUnderSize(maxSize);
            }
        }
        // Index size if not indexed
        if (this.getSize() <= maxSize) {
            single.addFileBelowSize(size);
        }
    }

    // Recursive function to find the answer to task 2
    // Finding the smallest folder that gives enough space
    public void findSmallestDirToDelete(int minSize) {
        // Recur this function for subfolders.
        for (Object o : content) {
            if (o instanceof ElfDirectory) {
                ElfDirectory ed = (ElfDirectory) o;
                ed.findSmallestDirToDelete(minSize);
            }
        }
        // Index size if not indexed
        if (this.getSize() < single.getSmallestToDelete().size && size >= minSize) {
            single.setSmallestToDelete(this);
        }
    }

    @Override
    public String toString() {
        if (size == 0) {
            this.getSize();
        }
        return "Folder: " + name + " Size: " + single.getFormat().format(size);
    }
}
