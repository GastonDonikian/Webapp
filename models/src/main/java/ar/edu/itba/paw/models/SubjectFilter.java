package ar.edu.itba.paw.models;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;


public class SubjectFilter {
    //Aca me pasan todos los filtros, me buildean y se lo pasan al subjectService
    private final List<Subject.Categories> category;
    private final String search;

    public List<Subject.Categories> getCategory() {
        return category;
    }

    public List<Subject.Levels> getLevel() {
        return level;
    }

    public String getSearch(){return search;}

    private final List<Subject.Levels> level;

    public static class Builder{
        //Required parameters


        //Optional parameters - initialized to default values | en mi caso son null porque tambien uso isEmpty()
        private final List<Subject.Categories> categories = new ArrayList<>();
        private final List<Subject.Levels> levels = new ArrayList<>();
        private String search= "";

        public Builder(){
        }

        public Builder categories(List<Subject.Categories> category) {
            if(category == null)
                return this;
            this.categories.addAll(category);
            return this;
        }

        public Builder levels(List<Subject.Levels> level){
            if(level == null)
                return this;
            this.levels.addAll(level);
            return this;
        }

        public Builder search(String search){
            if (search == null)
                return this;
            this.search = Normalizer.normalize(search, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "").toLowerCase();
            return this;
        }

        public List<Subject.Levels> getLevels() {
            return levels;
        }

        public List<Subject.Categories> getCategories() {
            return categories;
        }

        public String getSearch(){return search;}

        public SubjectFilter build(){
            return new SubjectFilter(this);
        }
    }

    private SubjectFilter(Builder builder){
        category = builder.getCategories();
        level = builder.getLevels();
        search = builder.getSearch();
    }
}
