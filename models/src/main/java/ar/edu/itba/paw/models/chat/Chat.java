package ar.edu.itba.paw.models.chat;

import ar.edu.itba.paw.models.Lesson;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "chat")
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "chat_chat_id_seq")
    @SequenceGenerator(sequenceName = "chat_chat_id_seq", name = "chat_chat_id_seq",allocationSize = 1)
    @Column(name = "chat_id")
    private Integer chatId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lesson_id",foreignKey = @ForeignKey(name = "chat_lesson_id_fkey"))
    private Lesson lesson;

    @Column(name ="chat_name",length = 30)
    private String name;

    @OneToMany(mappedBy = "chat",fetch = FetchType.LAZY)
    private List<Message> messageList;

    Chat() {
    }

    public Chat(Integer chatId, Lesson lesson, String name, List<Message> messageList) {
        this.chatId = chatId;
        this.lesson = lesson;
        this.name = name;
        this.messageList = messageList;
    }

    public Integer getChatId() {
        return chatId;
    }

    public void setChatId(Integer chatId) {
        this.chatId = chatId;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Message> getMessageList() {
        return messageList;
    }

    public void setMessageList(List<Message> messageList) {
        this.messageList = messageList;
    }
}
