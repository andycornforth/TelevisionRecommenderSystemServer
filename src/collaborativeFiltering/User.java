/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package collaborativeFiltering;

/**
 *
 * @author Andy
 */
public class User {

    private String userName;
    private int age;
    // gender can either be 'm' or 'f'
    private char gender;

    public User(String name, int a, char gender) {
        userName = name;
        age = a;
        // set gender via method to validate it
        setGender(gender);
    }
    
    // empty constructor
    public User(){  
    }

    /*
     * ************ Getters and Setters ****************************************
     */
    public String getName() {
        return userName;
    }

    public int getAge() {
        return age;
    }

    public char getGender() {
        return gender;
    }

    public void setName(String name) {
        this.userName = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setGender(char gender) {
        // check gender is valid
        if (gender == 'm' || gender == 'f') {
            this.gender = gender;
        } // if its not valid, default gender is male
        else {
            this.gender = 'm';
        }
    }

    @Override
    public String toString() {
        return "User{" + "userName=" + userName + ", age=" + age + ", gender=" + gender + '}';
    }
}
