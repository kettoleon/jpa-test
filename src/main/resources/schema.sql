CREATE TABLE CONVERSATIONS
(
  ID varchar(40) NOT NULL,
  SOURCE varchar(40) NOT NULL,
  DESTINATION varchar(40) NOT NULL,
  SUBJECT varchar(40) NOT NULL,
  CREATED timestamp NOT NULL,
  CREATEDBY varchar(40) NOT NULL,
  CONSTRAINT CONVERSATION_ID PRIMARY KEY (ID)
);

CREATE TABLE MESSAGES
(
  ID RAW(16) NOT NULL,
  CONVERSATION_ID varchar(40) NOT NULL,
  TEXT CLOB NOT NULL,
  CREATED timestamp NOT NULL,
  CREATEDBY varchar(40) NOT NULL,
  CONSTRAINT MESSAGE_ID PRIMARY KEY (ID)
);

ALTER TABLE MESSAGES ADD CONSTRAINT FK_MESSAGES_CONVERSATION
  FOREIGN KEY (CONVERSATION_ID) REFERENCES CONVERSATIONS (ID);

CREATE TABLE EVENTS
(
  ID RAW(16) NOT NULL,
  CONVERSATION_ID varchar(40) NOT NULL,
  MESSAGE_ID RAW(16) NULL,
  CODE varchar(40) NOT NULL,
  CREATED timestamp NOT NULL,
  CREATEDBY varchar(40) NOT NULL,
  CONSTRAINT EVENT_ID PRIMARY KEY (ID)
);

ALTER TABLE EVENTS ADD CONSTRAINT FK_EVENTS_CONVERSATION
  FOREIGN KEY (CONVERSATION_ID) REFERENCES CONVERSATIONS (ID);
ALTER TABLE EVENTS ADD CONSTRAINT FK_EVENTS_MESSAGE
  FOREIGN KEY (MESSAGE_ID) REFERENCES MESSAGES (ID);