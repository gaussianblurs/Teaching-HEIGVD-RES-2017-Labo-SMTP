package model.prank;

import model.mail.Message;
import model.mail.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Arnold von Bauer Gauss (GaussianBlurs) on 08.04.2017.
 */
public class Prank {
    private Person victimSender;
    private final List<Person> victimRecipients = new ArrayList<>();
    private final List<Person> witnessRecipients = new ArrayList<>();
    private String message;

    public Person getVictimSender() {
        return victimSender;
    }

    public void setVictimSender(Person victimSender) {
        this.victimSender = victimSender;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Person> getVictimRecipients() {
        return new ArrayList<>(victimRecipients);
    }

    public void addVictimRecipients(List<Person> victims) {
        victimRecipients.addAll(victims);
    }

    public List<Person> getWitnessRecipients() {
        return new ArrayList<>(witnessRecipients);
    }

    public void addWitnessRecipients(List<Person> witnesses) {
        witnessRecipients.addAll(witnesses);
    }

    public Message generateMailMessage() {
        Message msg = new Message();
        msg.setBody(this.message + "\r\n" + victimSender.getFirstName());

        String[] to = victimRecipients
                .stream()
                .map(p -> p.getAddress())
                .collect(Collectors.toList())
                .toArray(new String[]{});
        msg.setTo(to);

        String[] cc = witnessRecipients
                .stream()
                .map(p -> p.getAddress())
                .collect(Collectors.toList())
                .toArray(new String[]{});
        msg.setCc(cc);

        msg.setFrom(victimSender.getAddress());

        return msg;
    }
}
