package ar.edu.itba.paw.webapp.forms;

import ar.edu.itba.paw.models.Contract;
import ar.edu.itba.paw.models.ContractFilter;
import ar.edu.itba.paw.models.Professor;
import ar.edu.itba.paw.models.Subject;

import java.util.List;

public class FilterContractForm
{
    private Float rating;
    private Boolean local;
    private Boolean remote;
    private ContractFilter.OrderBy orderBy;
    private Float maxPrice;
    private List<Subject.Levels> levels;
    private List<Subject.Categories> categories;
    private List<Professor.Location> localidades;
    private String search;
    private Integer page;

    public String getSearch(){return search;}
    public List<Subject.Levels> getLevels() {
        return levels;
    }

    public void setSearch(String search){this.search= search;}

    public void setLevels(List<Subject.Levels> levels) {
        this.levels = levels;
    }

    public List<Subject.Categories> getCategories() {
        return categories;
    }
    public void setCategories(List<Subject.Categories> categories) {
        this.categories = categories;
    }
    public List<Professor.Location> getLocalidades() {
        return localidades;
    }

    public void setLocalidades(List<Professor.Location> localidades) {
        this.localidades = localidades;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Boolean getLocal() {
        return local;
    }

    public void setLocal(Boolean local) {
        this.local = local;
    }

    public Boolean getRemote() {
        return remote;
    }

    public void setRemote(Boolean remote) {
        this.remote = remote;
    }

    public ContractFilter.OrderBy getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(ContractFilter.OrderBy orderBy) {
        this.orderBy = orderBy;
    }

    public Float getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Float maxPrice) {
        this.maxPrice = maxPrice;
    }
}
