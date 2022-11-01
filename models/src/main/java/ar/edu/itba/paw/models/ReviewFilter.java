package ar.edu.itba.paw.models;

public class ReviewFilter {
    private final Integer reviewId;
    private final Integer lessonId;
    private final Integer professorId;
    private final Integer studentId;


    private final Integer subjectId;

    public Integer getReviewId() {
        return reviewId;
    }

    public Integer getLessonId() {
        return lessonId;
    }

    public Integer getProfessorId() {
        return professorId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public Integer getSubjectId() {return subjectId;}

    public static class Builder {
        private Integer reviewId = -1;
        private Integer lessonId = -1;
        private Integer professorId = -1;
        private Integer studentId = -1;
        private Integer subjectId = -1;

        public Builder subject(Integer subjectId) {
            if (subjectId == null)
                return this;
            this.subjectId = subjectId;
            return this;
        }

        public Builder review(Integer reviewId) {
            if (reviewId == null)
                return this;
            this.reviewId = reviewId;
            return this;
        }

        public Builder lesson(Integer lessonId) {
            if (lessonId == null)
                return this;
            this.lessonId = lessonId;
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

        public ReviewFilter build() {
            return new ReviewFilter(this);
        }
    }

    private ReviewFilter(Builder builder) {
        reviewId = builder.reviewId;
        lessonId = builder.lessonId;
        professorId = builder.professorId;
        studentId = builder.studentId;
        subjectId = builder.subjectId;
    }
}
