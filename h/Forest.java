package h;

public class Forest {
    Tree[][] forest;

    public Tree[][] getForest() {
        return forest;
    }

    public Forest(Integer size) {
        forest = new Tree[size][size];
    }

    public void setTree(Tree t, Integer i, Integer j) {
        forest[i][j] = t;
    }

    public Tree getTree(int i, int j) {
        return forest[i][j];
    }

    public void printForest2X(int i1, int j1, int i2, int j2) {
        for (int i = 0; i < 99; i++) {
            for (int j = 0; j < 99; j++) {
                if (i == i1 && j == j1) {
                    System.out.print("X");
                } else if (i == i2 && j == j2) {
                    System.out.print("Y");
                } else {
                    System.out.print(forest[i][j].getSize());
                }
            }
            System.out.println();
        }
        System.out.println();
    }
}
