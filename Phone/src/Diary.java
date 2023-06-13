

//import java.util.ArrayList;
import java.util.Date;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Iterator;
import java.util.Calendar;


public class Diary {

    //private ArrayList<Event> events;
    private LinkedList<Event>[] daysOfDiary;

    public Diary() {
        this.daysOfDiary = new LinkedList[30];
        for (int i = 0; i < 30; i++) {
            this.daysOfDiary[i] = new LinkedList<Event>();
        }
    }


    // 1. Add an event
    public void addEvent(Event event) throws CloneNotSupportedException{
        if(isOverlap(event)){
            return;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(event.getDate());
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        System.out.println(day);
        this.daysOfDiary[day-1].add((Event) event.clone());
        Collections.sort(this.daysOfDiary[day-1]);
        System.out.println("Event added successfully");
        
    }

    // 2. Remove an event
    public void removeEvent(int event, Date date) {
        if(event > this.daysOfDiary[date.getDay()-1].size()){
            System.out.println("Event not found");
            return;
        }
        this.daysOfDiary[date.getDay()-1].remove(event-1);
        System.out.println("Event removed successfully");
    }

    // 3. Print all events of a date by order
    public void printEventsByDate(Date date) {
        boolean flag = false;
        int num = 1;
        Iterator<Event> it = this.daysOfDiary[date.getDay()-1].iterator();
        while(it.hasNext()){
            Event e = it.next();
            System.out.println("Event number " + num + ":");
            System.out.println(e);
            num++;
            flag = true;
        }
        if(!flag){
            System.out.println("No events in this date");
        }
    }

    // 4. Print all events of a contact by date order
    public void printEventsByContact(String name) {
        boolean flag = false;
        for(LinkedList<Event> list : this.daysOfDiary){
            Iterator<Event> it = list.iterator();
            while(it.hasNext()){
                Event e = it.next();
                if(e.compareContact(new Contact(name,"0"))){
                    System.out.println(e);
                    flag = true;
                }
            }
        }
        if(!flag){
            System.out.println("No events for this contact");
        }
    }

    // 5. Print all events
    public void printAllEvents() {
        boolean flag = false;
        for(int i = 0; i < 30; i++){
            Iterator<Event> it = this.daysOfDiary[i].iterator();
            while(it.hasNext()){
                Event e = it.next();
                System.out.println(e);
                flag = true;
            }
        }
        if(!flag){
            System.out.println("No events in this diary");
        }
    }

    public boolean isValidDate(int date, int hour, int minute){
        if(date < 1 || date > 30){
            System.out.println("Invalid date");
            return false;
        }
        if(hour < 0 || hour > 23){
            System.out.println("Invalid hour");
            return false;
        }
        if(minute < 0 || minute > 59){
            System.out.println("Invalid minute");
            return false;
        }
        return true;
    }
    public void removeAllEventsByName(String name) {
        for(int i = 0; i < 30; i++){
            Iterator<Event> it = this.daysOfDiary[i].iterator();
            while(it.hasNext()){
                Event e = it.next();
                if(e.compareContact(new Contact(name,"0"))){
                    it.remove();
                }
            }
        }
    }

    public boolean isOverlap(Event event){
    	Calendar calendar = Calendar.getInstance();
        calendar.setTime(event.getDate());
        int day = calendar.get(Calendar.DAY_OF_MONTH); 
        Iterator<Event> it = this.daysOfDiary[day-1].iterator();
        while(it.hasNext()){
            Event e = it.next();
            int choose = e.overlap(event);
            if(choose == 1){
                System.out.println("Can't add this event, overlap with another event");
                return true;
            }
            if(choose == 2){
                it.remove();
                System.out.println("Remove the old event");
                return false;
            }
        }
        return false;
    }

    public void printDiaryMenu(){
        System.out.println("Welcome to your diary!");
        System.out.println("Choose an option:");
        System.out.println("1. Add an event");
        System.out.println("2. Remove an event");
        System.out.println("3. Print all events of a date by order");
        System.out.println("4. Print all events of a contact by date order");
        System.out.println("5. Print all events");
        System.out.println("6. Exit");


    }

    
}
