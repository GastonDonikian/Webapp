package ar.edu.itba.paw.models;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

public class ContractFilter {

    private final Boolean local;
    private final Boolean remote;
    private final OrderBy orderBy;
    private final List<Professor.Location> localidades;
    private final Integer subjectId;
    private final Integer professorId;
    private final Float maxPrice;
    private final List<Subject.Categories> category;
    private final List<Subject.Levels> level;
    private final String search;

    public List<Subject.Categories> getCategory() {
        return category;
    }

    public List<Subject.Levels> getLevel() {
        return level;
    }

    public String getSearch() {
        return search;
    }

    public Contract.ContractStatus getStatus() {
        return status;
    }


    private final Contract.ContractStatus status;
    public enum OrderBy{
        price, rating;

        @Override
        public String toString(){
            switch (this){
                case price: return "Precio";
                case rating: return "Rating";
                default: return "Error404";
            }
        }
    }



    public Integer getSubjectId() {
        return subjectId;
    }

    public Integer getProfessorId() {
        return professorId;
    }



    public Boolean getLocal() {
        return local;
    }

    public Boolean getRemote() {
        return remote;
    }

    public Float getMaxPrice() {
        return maxPrice;
    }

    public OrderBy getOrderBy() {
        return orderBy;
    }

    public List<Professor.Location> getLocalidades() {
        return localidades;
    }

    public static class Builder{
        private Boolean local = false;
        private Boolean remote = false;
        private Float maxPrice = (float) 0;
        private Integer subjectId = -1;
        private Integer professorId = -1;
        private Contract.ContractStatus status = Contract.ContractStatus.ACTIVE;
        private ContractFilter.OrderBy orderBy = OrderBy.rating;
        private final List<Subject.Categories> categories = new ArrayList<>();
        private final List<Subject.Levels> levels = new ArrayList<>();
        private final List<Professor.Location> localidades = new ArrayList<>();
        private String search= "";

        public Builder(){
        }

        public Builder categories(List<Subject.Categories> categories){
            if(categories == null)
                return this;
            this.categories.addAll(categories);
            return this;
        }

        public Builder levels(List<Subject.Levels> levels){
            if(levels == null)
                return this;
            this.levels.addAll(levels);
            return this;
        }
        public Builder localidades(List<Professor.Location> localidades) {
            if(localidades == null || localidades.isEmpty())
                return this;
            this.localidades.addAll(localidades);
            return this;
        }

        public Builder search(String search){
            if(search == null)
                return this;
            this.search = Normalizer.normalize(search,Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "").toLowerCase();
            return this;
        }


        public Builder local(Boolean local){
            if(local == null)
                return this;
            this.local = local;
            return this;
        }

        public Builder remote(Boolean remote){
            if(remote == null)
                return this;
            this.remote = remote;
            return this;
        }

        public Builder maxPrice(Float maxPrice){
            if(maxPrice == null)
                return this;
            this.maxPrice = maxPrice;
            return this;
        }

        public Builder subjectId(Integer subjectId){
            if(subjectId == null)
                return this;
            this.subjectId = subjectId;
            return this;
        }

        public Builder professorId(Integer professorId){
            if(professorId == null)
                return this;
            this.professorId = professorId;
            return this;
        }

        public Builder orderBy(ContractFilter.OrderBy orderBy){
            if(orderBy == null)
                return this;
            this.orderBy = orderBy;
            return this;
        }



        public Builder status(Contract.ContractStatus status){
            if(status == null)
                return this;
            this.status = status;
            return this;
        }

        public ContractFilter build(){return new ContractFilter(this);}
    }

    private ContractFilter(Builder builder){
        local = builder.local;
        subjectId = builder.subjectId;
        professorId = builder.professorId;
        remote = builder.remote;
        maxPrice = builder.maxPrice;
        orderBy =  builder.orderBy;
        localidades = builder.localidades;
        status = builder.status;
        category = builder.categories;
        level = builder.levels;
        search = builder.search;
    }


}
