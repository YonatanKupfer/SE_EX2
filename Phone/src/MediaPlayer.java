import java.util.LinkedList;
import java.util.ListIterator;

public class MediaPlayer {
    private LinkedList<Media> mediaList;

    public MediaPlayer() {
        mediaList = new LinkedList<Media>();
    }
    //1 and 2 are for adding music and video
    public void addMedia(Media media) {
        mediaList.add(media);
    }

    //turn on media by name, if there are multiple media with the same name, 
    //turn on the first one
    public void turnOnMedia(String title) {
        boolean found = false;
        ListIterator<Media> iterator = mediaList.listIterator();
        while (iterator.hasNext()) {
            Media media = iterator.next();
            if (media.getTitle().equals(title)) {
                media.turnOn();
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Media not found with title " + title);
        }
    }

    public void turnOnAllMedia() {
        if(mediaList.isEmpty()){
            System.out.println("No media to play");
        }
        ListIterator<Media> iterator = mediaList.listIterator();
        while (iterator.hasNext()) {
            Media media = iterator.next();
            media.turnOn();
        }
    }

    public void printMediaPlayerMenu() {
        System.out.println("1. Add Music");
        System.out.println("2. Add Video");
        System.out.println("3. Play Media by Title");
        System.out.println("4. Play All Media");
        System.out.println("5. Exit");
    }
}
