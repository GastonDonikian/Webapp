package ar.edu.itba.paw.models;

import java.util.Optional;

public class LessonFilter {
    private final Integer lessonId;
    private final Integer subjectId;
    private final Integer professorId;
    private final Integer studentId;
    private final Lesson.LessonStatus status;
    private final Boolean confirmedByStudent;
    private final Boolean confirmedByProfessor;
    private final OrderBy orderBy;

    public enum OrderBy{
        RECENT, OLDEST;

        @Override
        public String toString(){
            switch (this){
                case RECENT: return "Reciente";
                case OLDEST: return "Ultimos";
                default: return "Error404";
            }
        }
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public Boolean getConfirmedByStudent() {
        return confirmedByStudent;
    }

    public Boolean getConfirmedByProfessor() {
        return confirmedByProfessor;
    }

    public Integer getProfessorId() {
        return professorId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public OrderBy getOrderBy() {
        return orderBy;
    }

    public Lesson.LessonStatus getStatus() {
        return status;
    }

    public Integer getLessonId() {
        return lessonId;
    }


    public static class Builder {
        private Integer lessonId = -1;
        private Integer subjectId = -1;
        private Integer professorId = -1;
        private Integer studentId = -1;
        private Lesson.LessonStatus status = null;
        private Boolean confirmedByStudent = null;
        private Boolean confirmedByProfessor = null;
        private OrderBy orderBy = OrderBy.OLDEST;

        public Builder lesson(Integer lessonId){
            if(lessonId == null)
                return this;
            this.lessonId = lessonId;
            return this;
        }

        public Builder subject(Integer subjectId) {
            if(subjectId == null)
                return this;
            this.subjectId = subjectId;
            return this;
        }

        public Builder professor(Integer professorId) {
            if (professorId == null)
                return this;
            this.professorId = professorId;
            return this;
        }

        public Builder student(Integer studentId) {
            if (studentId == null)
                return this;
            this.studentId = studentId;
            return this;
        }

        public Builder status(Lesson.LessonStatus status) {
            if (status == null)
                return this;
            if(status == Lesson.LessonStatus.PENDING_APPROVAL || status == Lesson.LessonStatus.IN_PROCESS)
                this.orderBy = OrderBy.RECENT;

            this.status = status;
            return this;
        }

        public Builder confirmedByStudent(Boolean confirmedByStudent) {
            if (confirmedByStudent == null)
                return this;
            this.confirmedByStudent = confirmedByStudent;
            return this;
        }

        public Builder confirmedByProfessor(Boolean confirmedByProfessor) {
            if (confirmedByProfessor == null)
                return this;
            this.confirmedByProfessor = confirmedByProfessor;
            return this;
        }

        public LessonFilter build() {
            return new LessonFilter(this);
        }
    }

    private LessonFilter(Builder builder) {
        lessonId = builder.lessonId;
        subjectId = builder.subjectId;
        professorId = builder.professorId;
        studentId = builder.studentId;
        status = builder.status;
        confirmedByProfessor = builder.confirmedByProfessor;
        confirmedByStudent = builder.confirmedByStudent;
        orderBy = builder.orderBy;
    }
}
