package org.pack1;

public class Player {
    private int id;
    private String firstName;
    private String lastName;
    private int jerseyNumber;

    public Player(int id, String firstName, String lastName, int jerseyNumber) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.jerseyNumber = jerseyNumber;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public int getJerseyNumber() { return jerseyNumber; }
    public void setJerseyNumber(int jerseyNumber) { this.jerseyNumber = jerseyNumber; }

    @Override
    public String toString() {
        return "Player{" +
               "id=" + id +
               ", firstName='" + firstName + '\'' +
               ", lastName='" + lastName + '\'' +
               ", jerseyNumber=" + jerseyNumber +
               '}';
    }
}
