package ar.edu.itba.paw.models;

import javax.persistence.*;
import java.util.List;

@Entity
@PrimaryKeyJoinColumn(name = "professor_id", referencedColumnName = "user_id",foreignKey = @ForeignKey(name = "professors_professor_id_fkey"))
@Table(name = "professors")
@SecondaryTable(name = "professor_rating")
public class Professor extends User {
    @Column(nullable = true, length = 200)
    private String description;
    @Column(nullable = true, length = 30)
    private String studies;

    @Column(nullable = true,length = 30)
    private String schedule;



    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    @Column(nullable = true, length = 25)
    @Enumerated(EnumType.STRING)
    private Location location;

    @Column(name = "professor_rating",table = "professor_rating")
    private Integer rating;

    @Column(name = "professor_rating_count",table = "professor_rating")
    private Integer ratingCount;

    public Integer getRating() {
        return rating;
    }

    public Integer getRatingCount() {
        return ratingCount;
    }

    public void setRatingCount(Integer ratingCount) {
        this.ratingCount = ratingCount;
    }

    public enum Location {
        All, Retiro, SanNicolas, PuertoMadero, SanTelmo, Montserrat, Constitucion, Recoleta, Balvanera, SanCristobal,
        LaBoca, Barracas, ParquePatricios, NuevaPompeya, Almagro, Boedo, Caballito, Flores, ParqueChacabuco, VillaSoldati,
        VillaRiachuelo, VillaLugano, Liniers, Mataderos, ParqueAvellaneda, VillaReal, MonteCastro, Versalles, Floresta,
        VelezSarsfield, VillaLuro, VillaGeneralMitre, VillaDevoto, VillaDelParque, VillaSantaRita, Coghlan, Saavedra,
        VillaUrquiza, VillaPueyrredon, Nunez, Belgrano, Colegiales, Palermo, Chacarita, VillaCrespo, LaPaternal, VillaOrtuzar,
        Agronomia, ParqueChas;

        @Override
        public String toString() {
            switch (this) {
                case All:
                    return "Todas";
                case Agronomia:
                    return "Agronomia";
                case Almagro:
                    return "Almagro";
                case Balvanera:
                    return "Balvanera";
                case Retiro:
                    return "Retiro";
                case SanNicolas:
                    return "SanNicolas";
                case PuertoMadero:
                    return "PuertoMadero";
                case SanTelmo:
                    return "San Telmo";
                case Montserrat:
                    return "Montserrat";
                case Constitucion:
                    return "Constitucion";
                case Recoleta:
                    return "Recoleta";
                case SanCristobal:
                    return "SanCristobal";
                case LaBoca:
                    return "LaBoca";
                case Barracas:
                    return "Barracas";
                case ParquePatricios:
                    return "ParquePatricios";
                case NuevaPompeya:
                    return "NuevaPompeya";
                case Boedo:
                    return "Boedo";
                case Caballito:
                    return "Caballito";
                case Flores:
                    return "Flores";
                case ParqueChacabuco:
                    return "ParqueChacabuco";
                case VillaSoldati:
                    return "Villa Soldati";
                case VillaRiachuelo:
                    return "VillaRiachuelo";
                case VillaLugano:
                    return "VillaLugano";
                case Liniers:
                    return "Liniers";
                case Mataderos:
                    return "Mataderos";
                case ParqueAvellaneda:
                    return "ParqueAvellaneda";
                case VillaReal:
                    return "VillaReal";
                case MonteCastro:
                    return "MonteCastro";
                case Versalles:
                    return "Versalles";
                case Floresta:
                    return "Floresta";
                case VelezSarsfield:
                    return "VelezSarsfield";
                case VillaLuro:
                    return "VillaLuro";
                case VillaGeneralMitre:
                    return "VillaGeneralMitre";
                case VillaDevoto:
                    return "VillaDevoto";
                case VillaDelParque:
                    return "VillaDelParque";
                case VillaSantaRita:
                    return "VillaSantaRita";
                case Coghlan:
                    return "Coghlan";
                case Saavedra:
                    return "Saavedra";
                case VillaUrquiza:
                    return "VillaUrquiza";
                case VillaPueyrredon:
                    return "VillaPueyrredon";
                case Nunez:
                    return "Nunez";
                case Belgrano:
                    return "Belgrano";
                case Colegiales:
                    return "Colegiales";
                case Palermo:
                    return "Palermo";
                case Chacarita:
                    return "Chacarita";
                case VillaCrespo:
                    return "VillaCrespo";
                case LaPaternal:
                    return "LaPaternal";
                case VillaOrtuzar:
                    return "VillaOrtuzar";
                case ParqueChas:
                    return "ParqueChas";
                default:
                    return "Error404";
            }
        }
    }


    Professor() {
        //Just for Hibernate
    }


    public Professor(Integer userId, String name, String surname, String email, String password,String schedule, String phoneNumber, String studies, String description, Professor.Location location, Integer rating) {
        super(userId, name, surname, email, password, phoneNumber, true);
        this.studies = studies;
        this.description = description;
        this.schedule = schedule;
        this.location = location;
        this.rating = rating;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStudies() {
        return studies;
    }

    public void setStudies(String studies) {
        this.studies = studies;
    }

}