package day8;

public class Tree {
    private Boolean vis;
    private Integer size;
    private Integer scenicScore;

    public Tree(Integer size) {
        this.size = size;
        vis = false;
        scenicScore = 0;
    }

    public Boolean getVis() {
        return vis;
    }

    public void makeOutsideVis() {
        vis = true;
    }

    public Integer getSize() {
        return size;
    }

    public void setScene(Integer s) {
        scenicScore = s;
    }

    public Integer getScene() {
        return scenicScore;
    }

    @Override
    public String toString()
    {
        return "Size: " + size + " Scenic Score: " + scenicScore;
    }
}
