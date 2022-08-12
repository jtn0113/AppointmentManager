package model;

import java.time.Month;

public class MonthCount {
    private Month month;
    private int count;

    public MonthCount(Month month, int count) {
        this.month = month;
        this.count = count;
    }

    public Month getMonth() {
        return month;
    }

    public void setMonth(Month month) {
        this.month = month;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
