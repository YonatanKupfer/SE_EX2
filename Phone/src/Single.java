
import java.util.Date;

public class Single extends Event {
	
	private String description;
	
	public Single(String title, Date date, int duration, String description) throws CloneNotSupportedException{
        super(title, date, duration);
        this.description = description;
    }
	
	public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    @Override
    public String toString() {
        String str = "Event:\n";
        str += super.toString();
        str += "Description: " + this.description + "\n";
        return str;
    }
    
    public boolean compareContact(Contact other){
        return false;
    }
}
