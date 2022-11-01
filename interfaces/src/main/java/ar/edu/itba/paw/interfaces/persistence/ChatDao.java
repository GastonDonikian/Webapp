package ar.edu.itba.paw.interfaces.persistence;

import ar.edu.itba.paw.models.chat.Chat;
import ar.edu.itba.paw.models.chat.Message;

import java.util.List;

public interface ChatDao {
    Chat getChat(Integer chatId);
    Chat getChatByLesson(Integer lessonId);
    List<Message> getMessagesByChat(Integer chatId);
    Message getMessage(Integer messageId);
    void newChat(Integer lessonId,String name);
    void newMessage(Integer chatId,Integer userId,String message);
    void saveChat(Chat chat);
    void saveMessage(Message message);
    void deleteChat(Integer chatId);
    void deleteMessage(Integer messageId);
}
