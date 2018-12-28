package com.github.kettoleon.jpatest.model;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "CONVERSATIONS")
public class Conversation{

    @Id
    private String id;

    private String source;

    private String destination;

    private String subject;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "conversation")
    private Set<Message> messages = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "conversation")
    private Set<Event> events = new HashSet<>();


    public Conversation(){}

    public Conversation(String source, String destination, String subject) {
        this.source = source;
        this.destination = destination;
        this.subject = subject;
        id = "CNV-" + UUID.randomUUID();
    }


    public String getId() {
        return id;
    }


    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }

    public Set<Message> getMessages() {
        return messages;
    }

    public String getSubject() {
        return subject;
    }

    public void addMessage(Message m) {
        m.setConversation(this);
        messages.add(m);
    }

    private Date created = new Date();

    @Column(name="CREATEDBY")
    private String createdBy = "me";

    public Date getCreated() {
        return created;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void addEvent(Event event) {
        event.setConversation(this);
        events.add(event);
    }
}
