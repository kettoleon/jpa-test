package com.github.kettoleon.jpatest.model;

import com.github.kettoleon.jpatest.UUIDUtils;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "events")
public class Event {

    @Id
    private byte[] id;
    private String code;


    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "conversation_id", nullable = false)
    private Conversation conversation;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "message_id")
    private Message message;


    public Event(){}

    public Event(String code){
        this.id = UUIDUtils.randomUUIDBytes();
        this.code = code;
    }

    public String getCode() {
        return code;
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

    public void setMessage(Message message) {
        this.message = message;
    }
}
