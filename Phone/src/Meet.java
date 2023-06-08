
import java.util.Date;

public class Meet extends Event{
	
	private Contact contact;
	
	public Meet(String title, Date date, int duration, Contact contact) throws CloneNotSupportedException{
        super(title, date, duration);
        this.contact = (Contact) contact.clone();
    }
	
	public Contact getContact() throws CloneNotSupportedException {
        return (Contact) contact.clone();
    }
	
	@Override
    public String toString() {
        String str = "Meeting:\n";
        str += super.toString();
        str += "Contact: " + this.contact.toString() + "\n";
        return str;
    }
	
	public boolean compareContact(Contact other){
        return this.contact.getName().equals(other.getName());
    }
}