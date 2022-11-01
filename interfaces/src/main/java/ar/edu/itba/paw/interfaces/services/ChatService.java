package ar.edu.itba.paw.interfaces.services;

import ar.edu.itba.paw.models.chat.Chat;
import ar.edu.itba.paw.models.chat.Message;

import java.util.List;

public interface ChatService {
    Chat getChat(Integer chatId);
    Chat getChatByLesson(Integer lessonId);
    List<Message> getMessagesByChat(Integer chatId);
    void editChat(Integer chatId, String name);
    void deleteChat(Integer chatId);
    void editMessage(Integer messageId, Integer userId, String message);
    void deleteMessage(Integer messageId);
    void newChat(Integer lessonId,String name);
    void newMessage(Integer chatId,Integer userId,String message);
}
