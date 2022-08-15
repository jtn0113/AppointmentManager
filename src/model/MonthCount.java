package model;

import java.time.Month;

/**
 * MonthCount class with constructor, setters, and getters
 */
public class MonthCount {
    private Month month;
    private int count;

    /**
     * Constructor for MonthCount class
     * @param month
     * @param count
     */
    public MonthCount(Month month, int count) {
        this.month = month;
        this.count = count;
    }

    /**
     *
     * @return month
     */
    public Month getMonth() {
        return month;
    }

    /**
     * Sets month
     * @param month
     */
    public void setMonth(Month month) {
        this.month = month;
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
}
