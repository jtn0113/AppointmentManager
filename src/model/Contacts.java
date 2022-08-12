package model;

/**
 * Contacts class with constructor, setters, and getters
 */
public class Contacts {
    private int contactsID;
    private String contactsName;
    private String contactsEmail;

    /**
     * Constructor for contacts class
     * @param contactsID
     * @param contactsName
     * @param contactsEmail
     */
    public Contacts(int contactsID, String contactsName, String contactsEmail) {
        this.contactsID = contactsID;
        this.contactsName = contactsName;
        this.contactsEmail = contactsEmail;
    }

    /**
     *
     * @return contactsID
     */
    public int getContactsID() {
        return contactsID;
    }

    /**
     * Sets contactsID
     * @param contactsID
     */
    public void setContactsID(int contactsID) {
        this.contactsID = contactsID;
    }

    /**
     *
     * @return contactsName
     */
    public String getContactsName() {
        return contactsName;
    }

    /**
     * Sets contactsName
     * @param contactsName
     */
    public void setContactsName(String contactsName) {
        this.contactsName = contactsName;
    }

    /**
     *
     * @return contactsEmail
     */
    public String getContactsEmail() {
        return contactsEmail;
    }

    /**
     * Sets contactsEmail
     * @param contactsEmail
     */
    public void setContactsEmail(String contactsEmail) {
        this.contactsEmail = contactsEmail;
    }
}
