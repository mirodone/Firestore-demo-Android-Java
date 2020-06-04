package ro.mirodone.firestoredemo;

public class List {

    private String title;
    private String details;


    //Firestore needs an empty constructor.
    public List() {

    }

    public List(String title, String details) {
        this.title = title;
        this.details = details;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
