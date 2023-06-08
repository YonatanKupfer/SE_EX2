import java.util.ArrayList;
import java.util.Iterator;


public class Sms {
    private ArrayList<Message> messages;

    public Sms() {
        this.messages = new ArrayList<Message>();
    }

    // add a new message to a contact even if it's the first message
    //need to check if the contact exists in the contact list under contacts
    public void addMessage(String name, String text) {
        //check if the contact exists in the contact list
        //if it does, add the message to the contact
        //if it doesn't, print an error message
        if(!contactExists(name)){
            System.out.println("Contact does not exist");
            return;
        }
        if(haveMessages(name)){
            Message m = getTextByContact(name);
            m.addText(text);
        }
        else{
            Message m = new Message(text, name);
            this.messages.add(m);
        }
    }

    // delete a conversation with a contact
    public void deleteConversation(String name){
        if (!haveMessages(name)){
            System.out.println("No conversation with this contact");
            return;
        }
        Message m = getTextByContact(name);
        messages.remove(m);   
    }

    // print a conversation with a contact
    public void printConversation(String name){
        if (!haveMessages(name)){
            System.out.println("No conversation with this contact");
            return;
        }
        Message m = getTextByContact(name);
        System.out.println(m.getText());
    }

    // search for a sentence in all messages
    // print all names of contacts that have the sentence
    public void searchSentence(String sentence){
        boolean found = false;
        Iterator<Message> iter = this.messages.iterator();
        while(iter.hasNext()){
            Message m = iter.next();
            if(m.getText().contains(sentence)){
                System.out.println(m.getContactName());
                found = true;
            }
        }
        if(!found){
            System.out.println("No contact has this sentence");
        }
    }
    
    // print all messages
    public void printAllMessages(){
        if(this.messages.isEmpty()){
            System.out.println("No messages");
            return;
        }
        Iterator<Message> iter = this.messages.iterator();
        while(iter.hasNext()){
            Message m = iter.next();
            System.out.println(m.getContactName() + ": " + m.getText());
        }
    }




    // get a message by contact name
    public Message getTextByContact(String name){
        Iterator<Message> iter = this.messages.iterator();
        while(iter.hasNext()){
            Message m = iter.next();
            if(m.getContactName().equals(name)){
                return m;
            }
        }
        return null;
    }

    public boolean haveMessages(String name){
        Iterator<Message> iter = this.messages.iterator();
        while(iter.hasNext()){
            Message m = iter.next();
            if(m.getContactName().equals(name)){
                return true;
            }
        }
        return false;
    }

    public boolean contactExists(String name){
        Iterator<Contact> iter = Contacts.getContactList().iterator();
        while(iter.hasNext()){
            Contact c = iter.next();
            if(c.getName().equals(name)){
                return true;
            }
        }
        return false;
    }

    public void printSmsMenu(){
        System.out.println("Welcome to your SMS app!");
        System.out.println("Choose an option:");
        System.out.println("1. Send a new message");
        System.out.println("2. Delete Conversation with a contact");
        System.out.println("3. Print a conversation with a contact");
        System.out.println("4. Search for a santance in all messages");
        System.out.println("5. Print all conversations");
        System.out.println("6. Exit");
}
}
