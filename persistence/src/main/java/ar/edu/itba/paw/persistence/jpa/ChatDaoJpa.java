package ar.edu.itba.paw.persistence.jpa;

import ar.edu.itba.paw.interfaces.persistence.ChatDao;
import ar.edu.itba.paw.models.chat.Chat;
import ar.edu.itba.paw.models.chat.Message;
import ar.edu.itba.paw.models.Lesson;
import ar.edu.itba.paw.models.User;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@Primary
@Repository
public class ChatDaoJpa implements ChatDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Message> getMessagesByChat(Integer chatId) {
        final Query nativeQuery = em.createNativeQuery("SELECT message_id FROM message WHERE chat_id = :chatId ORDER BY date DESC LIMIT 20");
        nativeQuery.setParameter("chatId",chatId);


        final List<Integer> messageIdList = ((List<Integer>) nativeQuery.getResultList());
        if(messageIdList.isEmpty())
            return Collections.emptyList();
        final TypedQuery<Message> query = em.createQuery("from Message where messageId IN :messageIdList",Message.class);
        query.setParameter("messageIdList",messageIdList);
        return query.getResultList();
    }

    @Override
    public void newChat(Integer lessonId,String name){
        Lesson lesson = em.getReference(Lesson.class,lessonId);
        em.persist(new Chat(null,lesson,name,null));
    }

    @Override
    public void newMessage(Integer chatId,Integer userId,String message){
        Chat chat = em.getReference(Chat.class,chatId);
        User author = em.getReference(User.class,userId);
        em.persist(new Message(null,chat,author, LocalDateTime.now(),message));
    }
    @Override
    public Chat getChat(Integer chatId) {
        return em.find(Chat.class,chatId);
    }

     @Override
     public Chat getChatByLesson(Integer lessonId){
        Lesson lesson = em.getReference(Lesson.class,lessonId);
         final TypedQuery<Chat> query =  em.createQuery("from Chat where lesson = :lesson",
                 Chat.class);
         query.setParameter("lesson",lesson);
         return query.getResultList().stream().findFirst().get();
     }

    @Override
    public Message getMessage(Integer messageId) {
        return em.find(Message.class,messageId);
    }

    @Override
    public void saveChat(Chat chat) {
        em.persist(chat);
    }

    @Override
    public void saveMessage(Message message) {
        em.persist(message);
    }

    @Override
    public void deleteChat(Integer chatId) {
        em.remove(em.getReference(Chat.class,chatId));
    }

    @Override
    public void deleteMessage(Integer messageId) {
        em.remove(em.getReference(Message.class,messageId));
    }
}
