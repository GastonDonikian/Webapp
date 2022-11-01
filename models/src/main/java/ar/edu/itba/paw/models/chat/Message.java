package ar.edu.itba.paw.models.chat;

import ar.edu.itba.paw.models.User;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "message")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "message_message_id_seq")
    @SequenceGenerator(sequenceName = "message_message_id_seq", name = "message_message_id_seq",allocationSize = 1)
    @Column(name = "message_id")
    private Integer messageId;

    @ManyToOne
    @JoinColumn(name = "chat_id",nullable = false,foreignKey = @ForeignKey(name = "message_chat_id_fkey"))
    private Chat chat;

    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false,foreignKey = @ForeignKey(name = "message_user_id_fkey"))
    private User author;

    @Column(name = "date",nullable = false)
    private LocalDateTime date;



    @Column(name = "message",length = 255,nullable = false)
    private String message;


    public String getParseLocalDateTime(){
//        Todo: Sacar de aca, pesimo estilo que este aca"
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm MM/dd");
        String rta = date.format(formatter);
        return rta;
    }
    Message(){}

    public Message(Integer messageId, Chat chat, User author, LocalDateTime date,String message) {
        this.messageId = messageId;
        this.chat = chat;
        this.author = author;
        this.date = date;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
