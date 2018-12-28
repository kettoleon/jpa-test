package com.github.kettoleon.jpatest;

import com.github.kettoleon.jpatest.model.Conversation;
import com.github.kettoleon.jpatest.model.Event;
import com.github.kettoleon.jpatest.model.Message;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.SQLException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JpaTestApplicationTests {

    @Autowired
    private ConversationRepository conversationRepository;

    private DataSource h2DataSource = Config.h2DataSource();


    @Before
    public void clear() {
        JdbcTemplate jdbc = new JdbcTemplate(h2DataSource);
        jdbc.update("DELETE FROM EVENTS");
        jdbc.update("DELETE FROM MESSAGES");
        jdbc.update("DELETE FROM CONVERSATIONS");
    }

    @Test
    public void weCanSaveConversations() {

        Conversation oconv = new Conversation("src", "dst", "sbj");
        conversationRepository.save(oconv);

        printResults();
    }

    @Test
    public void weCanSaveConversationsWithMessages() {

        Conversation oconv = new Conversation("src", "dst", "sbj");
        Message message = new Message("Hello");
        oconv.addMessage(message);
        conversationRepository.save(oconv);

        printResults();
    }

    @Test
    public void weCanSaveConversationsWithMessagesAndEvents() {

        Conversation oconv = new Conversation("src", "dst", "sbj");
        Message message = new Message("Hello");
        oconv.addMessage(message);

        Event event = new Event("CREATED");
//		event.setMessage(message);
        oconv.addEvent(event);
        conversationRepository.save(oconv);
        printResults();
    }

    @Test
    public void weCanSaveConversationsWithMessagesAndEventsLinkedToMessagesToo() {

        Conversation oconv = new Conversation("src", "dst", "sbj");
        Message message = new Message("Hello");
        oconv.addMessage(message);

        Event event = new Event("CREATED");
        event.setMessage(message);
        oconv.addEvent(event);
        conversationRepository.save(oconv);
        printResults();
    }


    private void printResults() {
        try {
            DBTablePrinter.printTable(h2DataSource.getConnection(), "CONVERSATIONS");
            DBTablePrinter.printTable(h2DataSource.getConnection(), "MESSAGES");
            DBTablePrinter.printTable(h2DataSource.getConnection(), "EVENTS");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

