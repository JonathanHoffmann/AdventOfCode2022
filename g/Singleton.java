package g;

import java.text.DecimalFormat;

// This Class shall be used in a single object that is passed to every directory
// to avoid having to send results down the recursive methods, and instead have a
// central storing location
public class Singleton {
    private int filesBelowSize;
    private ElfDirectory smallestToDelete;
    private DecimalFormat decimalFormat;


    public Singleton() {
        filesBelowSize = 0;
        smallestToDelete = new ElfDirectory("empty", this);
        decimalFormat = new DecimalFormat("#.##");
        decimalFormat.setGroupingUsed(true);
        decimalFormat.setGroupingSize(3);
    }

    public DecimalFormat getFormat()
    {
        return decimalFormat;
    }

    // Add new folder to total dirs below given size -Task 1
    public void addFileBelowSize(int size) {
        filesBelowSize += size;
    }

    // Return total size of dirs below given size -Task 1
    public int getFilesBelowSize() {
        return filesBelowSize;
    }

    // Change to new smallest Directory -Task 2
    public void setSmallestToDelete(ElfDirectory e) {
        smallestToDelete = e;
    }

    // Return smallest Directory -Task 2
    public ElfDirectory getSmallestToDelete() {
        return smallestToDelete;
    }
}
