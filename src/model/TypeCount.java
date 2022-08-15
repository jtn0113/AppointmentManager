package model;

/**
 * TypeCount class with constructor, setters, and getters
 */
public class TypeCount {
    private String type;
    private int count;

    /**
     * Constructor for TypeCount class
     * @param type
     * @param count
     */
    public TypeCount(String type, int count) {
        this.type = type;
        this.count = count;
    }

    /**
     *
     * @return count
     */
    public int getCount() {
        return count;
    }

    /**
     * Sets count
     * @param count
     */
    public void setCount(int count) {
        this.count = count;
    }

    /**
     *
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     * Sets type
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }
}
