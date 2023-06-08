

import java.util.Date;

public abstract class Event implements Cloneable, Comparable<Event> {
    private String title;
    private Date date;
    private int duration;
    
    public Event(String title, Date date, int duration) {
        this.title = title;
        this.date = (Date) date.clone();
        this.duration = duration;
    }

    ////////////////////
    public String getTitle() {
        return title;
    }

    public Date getDate() {
        return (Date) date.clone();
    }

    public int getDuration() {
        return duration;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDate(Date date) {
        this.date = (Date) date.clone();
    }


    public void setDuration(int duration) {
        this.duration = duration;
    }
    ////////////////////

    @Override
    public String toString() {
        String str = "";
        str += "Title: " + this.title + "\n";
        str += "Date: " + this.date.toString() + "\n";
        str += "Duration: " + this.duration + "\n";
        return str;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Event) {
            Event other = (Event) obj;
            return this.title.equals(other.title) && this.date.equals(other.date) && this.duration == other.duration;
        }
        return false;
    }

    @Override
    public int compareTo(Event other) {
        return this.date.compareTo(other.getDate());
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Event cloned = (Event) super.clone();
        cloned.date = (Date) this.date.clone();
        return cloned;
    }

    public abstract boolean compareContact(Contact other);

    //check overlap events. if overlap delete the one that start later
    //return 1 if this start first, 2 if other start first, 0 if no overlap
    public int overlap(Event other){
        int oldStart = this.date.getHours() * 60 + this.date.getMinutes();
        int oldEnd = oldStart + this.duration;
        int newStart = other.date.getHours() * 60 + other.date.getMinutes();
        int newEnd = newStart + other.duration;
        if(oldStart <= newStart && newStart < oldEnd){//remove the new event
            return 1;
        }
        if(newStart <= oldStart && oldStart < newEnd){//remove the old event
            return 2;
        }
        return 0;//no overlap
    }





}
