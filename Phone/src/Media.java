
public abstract class Media {
    private String title;
    private String length;


    public Media(String title, String length) {
        this.title = title;
        this.length = length;
    }

    public String getTitle() {
        return title;
    }

    public String getLength() {
        return length;
    }

    public abstract void turnOn();
}
