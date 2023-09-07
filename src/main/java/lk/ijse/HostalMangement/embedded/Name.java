package lk.ijse.HostalMangement.embedded;

import javax.persistence.Embeddable;

@Embeddable
public class Name {

    private String FirstName;
    private String LastName;
    private String MiddleName;

    public Name() {
    }

    public Name(String firstName, String lastName, String middleName) {
        FirstName = firstName;
        LastName = lastName;
        MiddleName = middleName;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getMiddleName() {
        return MiddleName;
    }

    public void setMiddleName(String middleName) {
        MiddleName = middleName;
    }

    @Override
    public String toString() {
        return "Name{" +
                "FirstName='" + FirstName + '\'' +
                ", LastName='" + LastName + '\'' +
                ", MiddleName='" + MiddleName + '\'' +
                '}';
    }
}
