package config;

import model.mail.Person;

import java.util.List;

/**
 * Created by Arnold von Bauer Gauss (GaussianBlurs) on 08.04.2017.
 */
public interface IConfigurationManager {
    public int getNumberOfGroups();
    public List<Person> getVictims();
    public List<Person> getWitnessesToCC();
    public List<String> getMessages();
}
