package model.mail;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Arnold von Bauer Gauss (GaussianBlurs) on 08.04.2017.
 */
public class Person {
    private String firstName;
    private String lastName;
    private final String address;

    public Person(String firstName, String lastName, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
    }

    public Person(String address) {
        this.address = address;
        Pattern pattern = Pattern.compile("(.*)\\.(.*)@");
        Matcher matcher = pattern.matcher(address);
        boolean found = matcher.find();
        if(found) {
            firstName = matcher.group(1);
            firstName = firstName.substring(0, 1).toUpperCase() + firstName.substring(1);
            lastName = matcher.group(2);
            lastName = lastName.substring(0, 1).toUpperCase() + lastName.substring(1);
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }
}
