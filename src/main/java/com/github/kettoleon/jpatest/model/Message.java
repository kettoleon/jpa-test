package com.github.kettoleon.jpatest.model;

import javax.persistence.*;

import java.util.Date;

import static com.github.kettoleon.jpatest.UUIDUtils.randomUUIDBytes;

@Entity
@Table(name ="MESSAGES")
public class Message {

    @Id
    private byte[] id;

    @Lob
    private String text;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "conversation_id", nullable = false)
    private Conversation conversation;


    public Message(){}

    public Message(String text){
        this.text = text;
        this.id = randomUUIDBytes();
    }

    public byte[] getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Conversation getConversation() {
        return conversation;
    }

    public void setConversation(Conversation conversation) {
        this.conversation = conversation;
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
}
