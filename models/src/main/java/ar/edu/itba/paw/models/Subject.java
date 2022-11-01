package ar.edu.itba.paw.models;

import org.intellij.lang.annotations.Language;

import javax.persistence.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "subjects")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "subjects_subject_id_seq")
    @SequenceGenerator( sequenceName = "subjects_subject_id_seq", name = "subjects_subject_id_seq",allocationSize = 1)
    @Column(name = "subject_id")
    private Integer subjectId;

    @Column(nullable = false, length = 40)
    private String name;

    @Column(nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private Levels level;

    @Column(nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private Categories category;

    public enum Categories {
        All, Language, Science, Social, Arts;

        @Override
        public String toString() {
            switch (this) {
                case All:
                    return "Todos";
                case Language:
                    return "Lenguaje";
                case Science:
                    return "Ciencia";
                case Social:
                    return "Sociales";
                case Arts:
                    return "Artes";
                default:
                    return "Error404";
            }
        }
    }

    public enum Levels {
        All, School, HighSchool, College, Others;

        @Override
        public String toString() {
            switch (this) {
                case All:
                    return "Todos";
                case School:
                    return "Primaria";
                case HighSchool:
                    return "Secundaria";
                case College:
                    return "Universidad";
                case Others:
                    return "Otros";
                default:
                    return "Error404";
            }
        }
    }

    Subject() {}

    public Subject(Integer subjectId, String name, Levels level, Categories category) {
        this.subjectId = subjectId;
        this.name = name;
        this.level = level;
        this.category = category;
    }


    public Integer getId() {
        return subjectId;
    }

    public String getName() {
        return name;
    }

    public Levels getLevel() {
        return level;
    }

    public Categories getCategory() {
        return category;
    }
}
