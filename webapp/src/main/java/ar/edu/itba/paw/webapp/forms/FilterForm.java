package ar.edu.itba.paw.webapp.forms;

import ar.edu.itba.paw.models.Subject;

import java.util.List;

public class FilterForm {
    private List<Subject.Levels> levels;
    private List<Subject.Categories> categories;
    private String search;

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
}
