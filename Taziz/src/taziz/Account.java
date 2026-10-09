/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package taziz;

/**
 *
 * @author rimas
 */

public class Account {
    private final String id;
    private final String password;
    private final char role; // A = Admin, S = Student
    private final String name;
    private final String universityId;

    public Account(String id, String password, char role, String name, String universityId) {
        this.id = id;
        this.password = password;
        this.role = Character.toUpperCase(role);
        this.name = name;
        this.universityId = universityId;
    }

    public String getId() { return id; }
    public String getPassword() { return password; }
    public char getRole() { return role; }
    public String getName() { return name; }
    public String getUniversityId() { return universityId; }
    public boolean isAdmin() { return role == 'A'; }
}
