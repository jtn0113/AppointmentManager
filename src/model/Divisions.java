package model;

/**
 * Divisions class with constructor, setters, and getters
 */
public class Divisions {
    private int divisionID;
    private String divisionName;
    private int Country_ID;

    /**
     * Constructor for divisions class
     * @param divisionID
     * @param divisionName
     * @param country_ID
     */
    public Divisions(int divisionID, String divisionName, int country_ID) {
        this.divisionID = divisionID;
        this.divisionName = divisionName;
        Country_ID = country_ID;
    }

    /**
     *
     * @return divisionID
     */
    public int getDivisionID() {
        return divisionID;
    }

    /**
     * Sets divisionID
     * @param divisionID
     */
    public void setDivisionID(int divisionID) {
        this.divisionID = divisionID;
    }

    /**
     *
     * @return divisionName
     */
    public String getDivisionName() {
        return divisionName;
    }

    /**
     * Sets divisionName
     * @param divisionName
     */
    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
    }

    /**
     *
     * @return Country_ID
     */
    public int getCountry_ID() {
        return Country_ID;
    }

    /**
     * Sets Country_ID
     * @param country_ID
     */
    public void setCountry_ID(int country_ID) {
        Country_ID = country_ID;
    }
}
