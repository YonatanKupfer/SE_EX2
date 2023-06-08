
public class Video extends Media{
    public Video(String title, String length) {
        super(title, length);
    }

    public void turnOn() {
        System.out.println("Playing " + getTitle() + " for " + getLength());
    }
    
}
