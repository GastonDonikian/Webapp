package ar.edu.itba.paw.services;

import ar.edu.itba.paw.interfaces.persistence.ChatDao;
import ar.edu.itba.paw.interfaces.services.ChatService;
import ar.edu.itba.paw.interfaces.services.EmailService;
import ar.edu.itba.paw.models.chat.Chat;
import ar.edu.itba.paw.models.chat.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
public class ChatServiceImpl implements ChatService {
    @Autowired
    private ChatDao chatDao;

    @Autowired
    private EmailService emailService;

    @Override
    @Transactional(readOnly = true)
    public Chat getChat(Integer chatId) {
        return chatDao.getChat(chatId);
    }

    @Override
    @Transactional(readOnly = true)
    public Chat getChatByLesson(Integer lessonId) {
        return chatDao.getChatByLesson(lessonId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Message> getMessagesByChat(Integer chatId) {
        List<Message> messageList = chatDao.getMessagesByChat(chatId);
        if(messageList == null)
            return Collections.emptyList();
        return messageList;
    }

    @Override
    @Transactional
    public void editChat(Integer chatId, String name) {
        Chat chat = chatDao.getChat(chatId);
        if(chat == null)
            return;
        chat.setName(name);
        chatDao.saveChat(chat);
    }

    @Override
    @Transactional
    public void deleteChat(Integer chatId) {
        chatDao.deleteChat(chatId);
    }

    @Override
    @Transactional
    public void editMessage(Integer messageId, Integer userId, String message) {
        Message chatMessage = chatDao.getMessage(messageId);
        chatMessage.setMessage(message);
        chatDao.saveMessage(chatMessage);
    }

    @Override
    @Transactional
    public void deleteMessage(Integer messageId) {
        chatDao.deleteMessage(messageId);
    }

    @Override
    @Transactional
    public void newChat(Integer lessonId, String name) {
        chatDao.newChat(lessonId, name);
    }

    @Override
    @Transactional
    public void newMessage(Integer chatId, Integer userId, String message) {
        chatDao.newMessage(chatId, userId, message);
        Chat chat = chatDao.getChat(chatId);
        emailService.sendNewMessage(chat,userId);
    }
}
