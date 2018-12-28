package com.github.kettoleon.jpatest;

import com.github.kettoleon.jpatest.model.Conversation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class ConversationRepository extends SimpleJpaRepository<Conversation, String> {

    public ConversationRepository(EntityManager em) {
        super(Conversation.class, em);
    }
}
