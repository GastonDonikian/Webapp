package ar.edu.itba.paw.webapp.controllers;

import ar.edu.itba.paw.interfaces.services.*;
import ar.edu.itba.paw.models.*;
import ar.edu.itba.paw.models.chat.Chat;
import ar.edu.itba.paw.webapp.forms.ClassroomForm;
import ar.edu.itba.paw.webapp.forms.MessageForm;
import ar.edu.itba.paw.webapp.forms.UploadFileForm;
import ar.edu.itba.paw.webapp.forms.ReviewForm;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.context.Context;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@Controller
public class ClassroomController {
    @Autowired
    private LessonService lessonService;

    @Autowired
    private LoggedUserAdvice loggedUserAdvice;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private FileService fileService;

    @Autowired
    private ChatService chatService;

    private static final Logger LOGGER = LoggerFactory.getLogger(ClassroomController.class);

    @RequestMapping(path = "/myStudents")
    public ModelAndView myStudents(@RequestParam(value = "nPage",defaultValue = "1",required = false) Integer nPage,
                                   @RequestParam(value = "pPage",defaultValue = "1",required = false) Integer pPage,
                                   @RequestParam(value = "fPage",defaultValue = "1",required = false) Integer fPage,
                                   @RequestParam(value = "cPage",defaultValue = "1",required = false) Integer cPage){
        final ModelAndView mav = new ModelAndView("myStudents");

        Integer professorId = loggedUserAdvice.loggedUser().getUserId();

        final LessonFilter newFilter = new LessonFilter.Builder().professor(professorId).status(Lesson.LessonStatus.PENDING_APPROVAL).build();
        List<Lesson> newLessons = lessonService.getLessons(newFilter,nPage);
        mav.addObject("totalNew", lessonService.getLessonsCount(newFilter) );
        if(nPage <= 0)
            nPage = 1;
        mav.addObject("nPages", lessonService.getLessonsPages(newFilter) );
        mav.addObject("nPage",nPage);

        final LessonFilter progressFilter= new LessonFilter.Builder().professor(professorId).status(Lesson.LessonStatus.IN_PROCESS).build();
        List<Lesson> inProgressLessons = lessonService.getLessons(progressFilter,pPage);
        mav.addObject("totalProgress", lessonService.getLessonsCount(progressFilter) );
        if(pPage <= 0)
            pPage = 1;
        mav.addObject("pPages", lessonService.getLessonsPages(progressFilter) );
        mav.addObject("pPage",pPage);

        final LessonFilter finishedFilter = new LessonFilter.Builder().professor(professorId).status(Lesson.LessonStatus.FINISHED).build();
        List<Lesson> finishedLessons = lessonService.getLessons( finishedFilter,fPage);
        mav.addObject("totalFinished", lessonService.getLessonsCount(finishedFilter) );
        if(fPage <= 0)
            fPage = 1;
        mav.addObject("fPages", lessonService.getLessonsPages(finishedFilter) );
        mav.addObject("fPage",fPage);

        final LessonFilter cancelledFilter = new LessonFilter.Builder().professor(professorId).status(Lesson.LessonStatus.CANCELLED).build();
        List<Lesson> cancelledLessons = lessonService.getLessons(cancelledFilter,cPage);
        mav.addObject("totalCancelled", lessonService.getLessonsCount(cancelledFilter) );
        if(cPage <= 0)
            cPage = 1;
        mav.addObject("cPages", lessonService.getLessonsPages(cancelledFilter) );
        mav.addObject("cPage",cPage);


        mav.addObject("newLessons",newLessons);
        mav.addObject("inProgressLessons",inProgressLessons);
        mav.addObject("finishedLessons",finishedLessons);
        mav.addObject("cancelledLessons",cancelledLessons);

        LOGGER.debug("Showing students of professor {}",professorId);
        return mav;
    }

    @RequestMapping(path = "/myStudents/{lessonId}/{lessonStatus}",method = RequestMethod.POST)
    public ModelAndView updateMyStudentsAsTeacher(@PathVariable final Integer lessonId, @PathVariable final String lessonStatus){
        List<Lesson> lesson = lessonService.getLessons(new LessonFilter.Builder().professor(loggedUserAdvice.loggedUser().getUserId()).lesson(lessonId).build(),1);
        if(lesson.isEmpty())
            return new ModelAndView("redirect:/error403");
        Lesson.LessonStatus status = Lesson.LessonStatus.valueOf(lessonStatus);
        lessonService.changeLessonStatus(lessonId,true, status);

        LOGGER.debug("Changing lesson {} status to {} as teacher",lessonId,lessonStatus);
        return new ModelAndView("redirect:/myStudents");
    }

    @RequestMapping(path = "/myStudents/{lessonId}/0",method = RequestMethod.POST)
    public ModelAndView dropLessonAsTeacher(@PathVariable final Integer lessonId){
        List<Lesson> lesson = lessonService.getLessons(new LessonFilter.Builder().professor(loggedUserAdvice.loggedUser().getUserId()).lesson(lessonId).build(),1);
        if(lesson.isEmpty())
            return new ModelAndView("redirect:/error403");
        lessonService.dropLesson(lessonId);
        LOGGER.debug("Dropping lesson {} as teacher",lessonId);
        return new ModelAndView("redirect:/myStudents");
    }

    @RequestMapping(path = "/myLessons")
    public ModelAndView myLessons(@ModelAttribute("reviewForm") final ReviewForm reviewForm, @RequestParam(value = "nPage",defaultValue = "1",required = false) Integer nPage,
                                  @RequestParam(value = "pPage",defaultValue = "1",required = false) Integer pPage,
                                  @RequestParam(value = "fPage",defaultValue = "1",required = false) Integer fPage,
                                  @RequestParam(value = "cPage",defaultValue = "1",required = false) Integer cPage){
        final ModelAndView mav = new ModelAndView("myLessons");

        User user = loggedUserAdvice.loggedUser();
        if(user == null)
            return new ModelAndView("redirect:/error500");

        final LessonFilter newFilter = new LessonFilter.Builder().student(user.getUserId()).status(Lesson.LessonStatus.PENDING_APPROVAL).build();
        List<Lesson> requestedLessons = lessonService.getLessons(newFilter,nPage);
        mav.addObject("totalRequested", lessonService.getLessonsCount(newFilter) );
        if(nPage <= 0)
            nPage = 1;
        mav.addObject("nPages", lessonService.getLessonsPages(newFilter) );
        mav.addObject("nPage",nPage);

        final LessonFilter progressFilter= new LessonFilter.Builder().student(user.getUserId()).status(Lesson.LessonStatus.IN_PROCESS).build();
        List<Lesson> inProgressLessons = lessonService.getLessons(progressFilter,pPage);
        mav.addObject("totalProgress", lessonService.getLessonsCount(progressFilter) );
        if(pPage <= 0)
            pPage = 1;
        mav.addObject("pPages", lessonService.getLessonsPages(progressFilter) );
        mav.addObject("pPage",pPage);

        final LessonFilter finishedFilter = new LessonFilter.Builder().student(user.getUserId()).status(Lesson.LessonStatus.FINISHED).build();
        List<Lesson> finishedLessons = lessonService.getLessons(finishedFilter,fPage);
        mav.addObject("totalFinished", lessonService.getLessonsCount(finishedFilter) );
        if(fPage <= 0)
            fPage = 1;
        mav.addObject("fPages", lessonService.getLessonsPages(finishedFilter) );
        mav.addObject("fPage",fPage);

        final LessonFilter cancelledFilter = new LessonFilter.Builder().student(user.getUserId()).status(Lesson.LessonStatus.CANCELLED).build();
        List<Lesson> cancelledLessons = lessonService.getLessons(cancelledFilter,cPage);
        mav.addObject("totalCancelled", lessonService.getLessonsCount(cancelledFilter) );
        if(cPage <= 0)
            cPage = 1;
        mav.addObject("cPages", lessonService.getLessonsPages(cancelledFilter) );
        mav.addObject("cPage",cPage);

        mav.addObject("requestedLessons",requestedLessons);
        mav.addObject("inProgressLessons",inProgressLessons);
        mav.addObject("finishedLessons",finishedLessons);
        mav.addObject("cancelledLessons",cancelledLessons);
        mav.addObject("reviewForm",reviewForm);
        LOGGER.debug("Showing lessons as student {}",user.getUserId());
        return mav;
    }

    @RequestMapping(path = "/myLessons/{lessonId}/{lessonStatus}",method = RequestMethod.POST)
    public ModelAndView updateMyLessonAsStudent(@PathVariable final Integer lessonId,@PathVariable final String lessonStatus){
        List<Lesson> lesson = lessonService.getLessons(new LessonFilter.Builder().student(loggedUserAdvice.loggedUser().getUserId()).lesson(lessonId).build(),1);
        if(lesson.isEmpty())
            return new ModelAndView("redirect:/error403");
        Lesson.LessonStatus status = Lesson.LessonStatus.valueOf(lessonStatus);
        if(lesson.get(0).getLessonStatus() == Lesson.LessonStatus.FINISHED) {
            Lesson lesson1 = lesson.get(0);
            lessonService.newLesson(lesson1.getSubject().getId(),lesson1.getProfessor().getUserId(),lesson1.getStudent().getUserId(), lesson1.getPrice(),status,false,true);
        }
        else
            lessonService.changeLessonStatus(lessonId,true,status);
        LOGGER.debug("Changing lesson {} status to {} as student ",lessonId,lessonStatus);
        return new ModelAndView("redirect:/myLessons");
    }

    @RequestMapping(path = "/myLessons/{lessonId}/0",method = RequestMethod.POST)
    public ModelAndView dropLessonAsStudent(@PathVariable final Integer lessonId){
        List<Lesson> lesson = lessonService.getLessons(new LessonFilter.Builder().student(loggedUserAdvice.loggedUser().getUserId()).lesson(lessonId).build(),1);
        if(lesson.isEmpty())
            return new ModelAndView("redirect:/error403");
        lessonService.dropLesson(lessonId);
        LOGGER.debug("Dropping lesson {} status as Student ",lessonId);
        return new ModelAndView("redirect:/myLessons");
    }

    @RequestMapping(path = "/review/{lessonId}",method = RequestMethod.POST)
    public ModelAndView reviewLesson(@PathVariable final Integer lessonId,@ModelAttribute("reviewForm")@Valid final ReviewForm form,final BindingResult errors){
        List<Review> review = reviewService.getReviews(new ReviewFilter.Builder().lesson(lessonId).build(),1);
        if(errors.hasErrors())
            return new ModelAndView("redirect:/myLessons");

        if(review == null || review.isEmpty())
            reviewService.newReview(lessonId,loggedUserAdvice.loggedUser().getUserId(),form.getMessage(),form.getRating());
        else
            reviewService.changeReview(review.get(0).getReviewId(),form.getMessage(),form.getRating());
        LOGGER.debug("Reviewing lesson {} with {} stars ",lessonId,form.getRating());
        return new ModelAndView("redirect:/myLessons");
    }

    @RequestMapping(path = "/class/{lessonId}", method = RequestMethod.GET)
    public ModelAndView classBetweenStudentAndProfessor(@PathVariable final Integer lessonId,
                                                        @ModelAttribute("uploadFileForm") final UploadFileForm form,
                                                        @ModelAttribute("messageForm") final MessageForm form2,
                                                        @ModelAttribute("classroomForm")final ClassroomForm form3,
                                                        @RequestParam(value = "page",defaultValue = "1",required = false) Integer page){
        ModelAndView mav = new ModelAndView("aula");
        List<Lesson> lesson = lessonService.getLessons(new LessonFilter.Builder().lesson(lessonId).build(),1);
        if(lesson.isEmpty())
            return new ModelAndView("redirect:/error404");
        Lesson clase = lesson.get(0);
        Integer loggedUserId = loggedUserAdvice.loggedUser().getUserId();
        if(!loggedUserId.equals(clase.getStudent().getUserId()) && !loggedUserId.equals(clase.getProfessor().getUserId()))
            return new ModelAndView("redirect:/error403");

        if (page < 1)
            page = 1;
        mav.addObject("pages", fileService.getFilesCount(lessonId));
        mav.addObject("page",page);


        mav.addObject("files",fileService.getFiles(lessonId,page));
        //TODO: LU, CUANDO AGREGUES PAGINACION EN FILES PODES AGREGAR LA CANTIDAD CON ESTO:
        mav.addObject("filesCount",fileService.getFilesCount(lessonId));
        Chat chat = chatService.getChatByLesson(lessonId);
        chat.setMessageList(chatService.getMessagesByChat(chat.getChatId()));

        mav.addObject("classroomForm",form3);
        mav.addObject("uploadFileForm",form);
        mav.addObject("messageForm",form2);
        mav.addObject("chat",chat);
        mav.addObject("student",clase.getStudent());
        mav.addObject("professor",clase.getProfessor());
        mav.addObject("subject",clase.getSubject());
        mav.addObject("clase",clase);
        return mav;
    }

    @RequestMapping(path = "/class/{lessonId}/message",method = RequestMethod.POST)
    public ModelAndView uploadMessage(@PathVariable final Integer lessonId, @ModelAttribute("messageForm")@Valid final MessageForm form, final BindingResult errors)throws IOException {
        if(errors.hasErrors())
            return classBetweenStudentAndProfessor(lessonId,new UploadFileForm(),form,new ClassroomForm(), 1);
        Chat chat = chatService.getChatByLesson(lessonId);
        chatService.newMessage(chat.getChatId(),loggedUserAdvice.loggedUser().getUserId(), form.getMessage());

        return new ModelAndView("redirect:/class/" + lessonId);
    }

    @RequestMapping(path = "/class/{lessonId}/file", method = RequestMethod.POST)
    public ModelAndView uploadFile(@PathVariable final Integer lessonId, @ModelAttribute("uploadFileForm")@Valid final UploadFileForm form, final BindingResult errors)throws IOException {
        if(errors.hasErrors())
            return classBetweenStudentAndProfessor(lessonId,form,new MessageForm(),new ClassroomForm(),1);
        fileService.newFile(lessonId,form.getName(),form.getFile().getBytes());
        LOGGER.debug("File upload at {}",lessonId);
        return new ModelAndView("redirect:/class/" + lessonId);
    }

    @RequestMapping(path = "/class/{lessonId}/update", method = RequestMethod.POST)
    public ModelAndView uploadFile(@PathVariable final Integer lessonId, @ModelAttribute("classroomForm")@Valid final ClassroomForm form, final BindingResult errors)throws IOException {
        if(errors.hasErrors())
            return classBetweenStudentAndProfessor(lessonId,new UploadFileForm(),new MessageForm(),form, 1);
        lessonService.updateClassroom(lessonId,form.getSchedule(),form.getMeetingLink());
        LOGGER.debug("Class update at {}",lessonId);
        return new ModelAndView("redirect:/class/" + lessonId);
    }
    @RequestMapping(path = "/class/{lessonId}/message/{messageId}/delete")
    public ModelAndView deleteMessage(@PathVariable("lessonId") final Integer lessonId, @PathVariable("messageId") final Integer messageId){
        chatService.deleteMessage(messageId);
        return new ModelAndView("redirect:/class/" + lessonId);
    }

    @RequestMapping(value = "/class/{lessonId}/file/{fileId}/delete",method = RequestMethod.POST)
    public ModelAndView deleteClassFile(@PathVariable("lessonId") final Integer lessonId, @PathVariable("fileId") final Integer fileId){
        fileService.deleteFile(fileId);
        return new ModelAndView("redirect:/class/" + lessonId);
    }


        @RequestMapping("/class/{lessonId}/file/{fileId}")
    public void getClassFile(@PathVariable("lessonId") final Integer lessonId, @PathVariable("fileId") final Integer fileId, HttpServletRequest request, HttpServletResponse response){
        LOGGER.debug("Downloading class {} file: {}",lessonId,fileId);
        try {
            File file = fileService.getFile(fileId);
            //FixMe: hay un pequeño security breach aca :/
            response.setHeader("Content-Disposition", "attachment;filename=\"" + file.getName() + "\"");
            if (file.getFile() != null && file.getFile().length > 0) {
                OutputStream out = response.getOutputStream();
                response.setContentLength(file.getFile().length);
                IOUtils.copy(new ByteArrayInputStream(file.getFile()), out);
                out.flush();
                out.close();
            }
//            else {
//                response.sendRedirect(request.getContextPath() + "/resources/images/profilePhoto.jpeg");
//            }
        } catch(IOException e){
            throw new RuntimeException();
        }
    }

//    @RequestMapping(path = "/myRooms", method = RequestMethod.GET)
//    public ModelAndView roomsOfUser(){
//        ModelAndView mav = new ModelAndView("aulas");
//        User user = loggedUserAdvice.loggedUser();
//        List<Lesson> lessons = new ArrayList<>(lessonService.getLessons(new LessonFilter.Builder().status(Lesson.LessonStatus.IN_PROCESS).professor(user.getUserId()).build()));
//        lessons.addAll(lessonService.getLessons(new LessonFilter.Builder().status(Lesson.LessonStatus.IN_PROCESS).student(user.getUserId()).build()));
//        mav.addObject("user",user);
//        mav.addObject("lessons",lessons);
//        return mav;
//    }

}
