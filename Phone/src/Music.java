
public class Music extends Media{
    public Music(String title, String length) {
        super(title, length);
    }

    public void turnOn() {
        System.out.println("Playing " + getTitle() + " for " + getLength());
    }
    
}
