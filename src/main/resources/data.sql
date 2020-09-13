
drop table if exists chat CASCADE;
drop table if exists chat_messages CASCADE;
drop table if exists message CASCADE;
create table chat (id binary not null, when_created timestamp, when_modified timestamp, participants varchar(255), primary key (id));
create table chat_messages (chat_id binary not null, messages_id binary not null);
create table message (id binary not null, when_created timestamp, when_modified timestamp, value_from varchar(255), message_payload clob, value_to varchar(255), primary key (id));
alter table chat add constraint participants unique (participants);
alter table chat_messages add constraint UK_mrq0rmc439okhdws2rxsjjhdn unique (messages_id);
alter table chat_messages add constraint FKjtlh6un2reea4nsgktq7qtao0 foreign key (messages_id) references message;
alter table chat_messages add constraint FKb27mi3082eolv7k6tavhgq3wc foreign key (chat_id) references chat;
