package ar.edu.itba.paw.models;

import javax.persistence.*;

@Entity
@Table(name = "contract")
@SecondaryTable(name = "contract_rating")
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contract_contract_id_seq")
    @SequenceGenerator(sequenceName = "contract_contract_id_seq",name = "contract_contract_id_seq",allocationSize = 1)
    @Column(name="contract_id")
    private Integer id;

    @Column(name = "contract_description",length = 200,nullable = true)
    private String description;

    @ManyToOne(optional = false)
    @JoinColumn(name = "subject_id",foreignKey = @ForeignKey(name = "contract_subject_id_fkey"))
    private Subject subject;

    @ManyToOne(optional = false)
    @JoinColumn(name = "professor_id",foreignKey = @ForeignKey(name = "contract_professor_id_fkey"))
    private Professor professor;

    @Column
    private boolean local;
    @Column
    private boolean remote;
    @Column
    private float price;

    @Column(nullable = false,length = 10)
    @Enumerated(EnumType.STRING)
    private ContractStatus status;

    @Column(name = "contract_rating",table = "contract_rating")
    private Integer rating;

    @Column(name = "contract_rating_count", table = "contract_rating")
    private Integer ratingCount;

    public Integer getRatingCount() {
        return ratingCount;
    }

    public void setRatingCount(Integer ratingCount) {
        this.ratingCount = ratingCount;
    }

    public enum ContractStatus{
        ACTIVE,PAUSED
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public void setLocal(boolean local) {
        this.local = local;
    }

    public void setRemote(boolean remote) {
        this.remote = remote;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public ContractStatus getStatus() {
        return status;
    }

    public void setStatus(ContractStatus status) {
        this.status = status;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }


    public Integer getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public Subject getSubject() {
        return subject;
    }

    public Professor getProfessor() {
        return professor;
    }

    public boolean getLocal() {
        return local;
    }

    public boolean getRemote() {
        return remote;
    }

    public Integer getRating() {
        return rating;
    }

    public float getPrice() {
        return price;
    }



    public Contract(Integer id, Subject subject, Professor professor, String description, Boolean local, Boolean remote, Integer rating, Float price,ContractStatus status) {
        this.id = id;
        this.description = description;
        this.subject = subject;
        this.professor = professor;
        this.local = local;
        this.remote = remote;
        this.rating = rating;
        this.price = price;
        this.status = status;
    }

    Contract(){

    }



}