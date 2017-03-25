DROP TABLE  BATCH_STEP_EXECUTION_CONTEXT IF EXISTS;
DROP TABLE  BATCH_JOB_EXECUTION_CONTEXT IF EXISTS;
DROP TABLE  BATCH_STEP_EXECUTION IF EXISTS;
DROP TABLE  BATCH_JOB_EXECUTION_PARAMS IF EXISTS;
DROP TABLE  BATCH_JOB_EXECUTION IF EXISTS;
DROP TABLE  BATCH_JOB_INSTANCE IF EXISTS;

DROP SEQUENCE  BATCH_STEP_EXECUTION_SEQ IF EXISTS;
DROP SEQUENCE  BATCH_JOB_EXECUTION_SEQ IF EXISTS;
DROP SEQUENCE  BATCH_JOB_SEQ IF EXISTS;

INSERT INTO MEMBER (ID, MEMBER_ID, PASSWORD, NAME, EMAIL) VALUES (1, 'zumgu', 'test', '줌구', 'wckhg89@gmail.com');
INSERT INTO MEMBER (ID, MEMBER_ID, PASSWORD, NAME, EMAIL) VALUES (2, 'zumgu2', 'test', '줌구2', 'wckhg89@gmail.com');
INSERT INTO MEMBER (ID, MEMBER_ID, PASSWORD, NAME, EMAIL) VALUES (3, 'zumgu3', 'test', '줌구3', 'wckhg89@gmail.com');
INSERT INTO MEMBER (ID, MEMBER_ID, PASSWORD, NAME, EMAIL) VALUES (4, 'zumgu4', 'test', '줌구4', 'wckhg89@gmail.com');
INSERT INTO MEMBER (ID, MEMBER_ID, PASSWORD, NAME, EMAIL) VALUES (5, 'zumgu5', 'test', '줌구5', 'wckhg89@gmail.com');

INSERT INTO CONTENT (ID, CONTENT, CREATED_AT, MEMBER_FK_ID) VALUES (1, '테스트1글 입니다.', CURRENT_TIMESTAMP(), 1);
INSERT INTO CONTENT (ID, CONTENT, CREATED_AT, MEMBER_FK_ID) VALUES (2, '테스트2글 입니다.', CURRENT_TIMESTAMP(), 1);
INSERT INTO CONTENT (ID, CONTENT, CREATED_AT, MEMBER_FK_ID) VALUES (3, '테스트3글 입니다.', CURRENT_TIMESTAMP(), 1);
INSERT INTO CONTENT (ID, CONTENT, CREATED_AT, MEMBER_FK_ID) VALUES (4, '테스트4글 입니다.', CURRENT_TIMESTAMP(), 1);
INSERT INTO CONTENT (ID, CONTENT, CREATED_AT, MEMBER_FK_ID) VALUES (5, '테스트5글 입니다.', CURRENT_TIMESTAMP(), 1);

