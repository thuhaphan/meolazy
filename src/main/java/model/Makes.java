package model;

public class Makes {

    private int makeId;

    private String make;

    public int getMakeId() {
        return makeId;
    }

    public void setMakeId(int makeId) {
        this.makeId = makeId;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    @Override
    public String toString() {
        return "Makes{" +
                "makeId=" + makeId +
                ", make='" + make + '\'' +
                '}';
    }
}
