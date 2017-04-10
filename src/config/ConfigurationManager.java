package config;

import model.mail.Person;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by Arnold von Bauer Gauss (GaussianBlurs) on 08.04.2017.
 */
public class ConfigurationManager implements IConfigurationManager {
    private String smtpServerAddress;
    private int smtpServerPort;
    private final List<Person> victims;
    private final List<String> messages;
    private int numberOfGroups;
    private List<Person> witnessesToCC;

    public ConfigurationManager() throws IOException {
        victims = loadAddressesFromFile("./config/victims.utf8");
        messages = loadMessagesFromfile("./config/messages.utf8");
        loadProperties("./config/config.properties");
    }

    private void loadProperties(String fileName) throws IOException {
        FileInputStream fis = new FileInputStream(fileName);
        Properties properties = new Properties();
        properties.load(fis);
        smtpServerAddress = properties.getProperty("smtpServerAddress");
        smtpServerPort = Integer.parseInt(properties.getProperty("smtpServerPort"));
        numberOfGroups = Integer.parseInt(properties.getProperty("numberOfGroups"));

        witnessesToCC = new ArrayList<>();
        String witnesses = properties.getProperty("witnessesToCC");
        String[] witnessesAddresses = witnesses.split(",");
        for(String address : witnessesAddresses) {
            witnessesToCC.add(new Person(address));
        }
    }

    private List<Person> loadAddressesFromFile(String fileName) throws IOException {
        List<Person> result;
        FileInputStream fis = new FileInputStream(fileName);
        InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
        BufferedReader reader = new BufferedReader(isr);
        result = new ArrayList<>();
        String address =  reader.readLine();
        while(address != null) {
            result.add(new Person(address));
            address = reader.readLine();
        }
        return result;
    }

    private List<String> loadMessagesFromfile(String fileName) throws IOException {
        List<String> result;
        FileInputStream fis = new FileInputStream(fileName);
        InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
        BufferedReader reader = new BufferedReader(isr);
        result = new ArrayList<>();
        String line = reader.readLine();
        while(line != null) {
            StringBuilder body = new StringBuilder();
            while((line != null) && (!line.equals("=="))) {
                body.append(line);
                body.append("\r\n");
                line = reader.readLine();
            }
            result.add(body.toString());
            line = reader.readLine();
        }
        return result;
    }

    @Override
    public int getNumberOfGroups() {
        return numberOfGroups;
    }

    @Override
    public List<Person> getVictims() {
        return victims;
    }

    @Override
    public List<Person> getWitnessesToCC() {
        return witnessesToCC;
    }

    @Override
    public List<String> getMessages() {
        return messages;
    }
 }
