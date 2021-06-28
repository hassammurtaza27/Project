import java.io.Serializable;

public class Person implements Serializable
{
    private String ID, name, gender;

    public Person(String id, String name, String gender)
    {
        ID = id;
        this.name = name;
        this.gender = gender;
    }

    public Person(Person person)
    {
        this.ID = person.ID;
        this.name = person.name;
        this.gender = person.gender;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String toString()
    {
        return "Name: " + name +
                "\nGender: " + gender +
                "\nID: " + ID;
    }
}
