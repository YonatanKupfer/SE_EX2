import java.util.Scanner;
import java.util.ArrayList;
import java.util.Iterator;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;


public class Contacts{

    private static ArrayList<Contact> contactList;

    public Contacts(){
        Contacts.contactList = new ArrayList<Contact>();
    }

    public void setContactList(ArrayList<Contact> contactList){
        Contacts.contactList = contactList;
    }

    public static ArrayList<Contact> getContactList(){
        return contactList;
    }

    

    public void printContactsMenu(){

        System.out.println("Welcome to the menu");
        System.out.println("What would you like to do?");
        System.out.println("1. Add a new contact");
        System.out.println("2. Delete a contact (by name)");
        System.out.println("3. Display all contacts");
        System.out.println("4. Search for a contact (by name)");
        System.out.println("5. Sort contacts by name");
        System.out.println("6. Sort contacts by phone number");
        System.out.println("7. Remove duplicates");
        System.out.println("8. Reverse the contact list");
        System.out.println("9. Save to file");
        System.out.println("10. Load from file");
        System.out.println("11. Exit");

    }
    //add a contact
    public void addContact(Contact contact) {
        Contacts.contactList.add(contact);
    }

    //delete a contact
    public void deleteContact(String name) {
        //Scanner reader = new Scanner(System.in);
        //System.out.println("Enter name");
        //String name = reader.nextLine();
        //reader.close();
        Iterator<Contact> iter = Contacts.contactList.iterator();
        while (iter.hasNext()) {
            Contact contact = iter.next();
            if (contact.getName().equals(name)) {
                iter.remove();
                return;
            }
        }
    }

    //display all contacts
    public void displayContacts() {
        Iterator<Contact> iter = Contacts.contactList.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next());
        }
        System.out.println();
    }

    //search for a contact
    public void searchContact(String name) {
        //Scanner reader = new Scanner(System.in);
        //System.out.println("Enter name");
        //String name = reader.nextLine();
        //reader.close();
        boolean found = false;
        Iterator<Contact> iter = Contacts.contactList.iterator();
        while (iter.hasNext()) {
            Contact contact = iter.next();
            if (contact.getName().equals(name)) {
                System.out.println(contact);
                found = true;
            }
        }
        if (!found){
            System.out.println("Contact not found");
        }
    }

    public boolean searchContactBool(String name) {
        Iterator<Contact> iter = Contacts.contactList.iterator();
        while (iter.hasNext()) {
            Contact contact = iter.next();
            if (contact.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }
    
    //sort contacts by name
    public void sortByName() {
    	Collections.sort(Contacts.contactList, (a, b) -> a.getName().compareToIgnoreCase(b.getName()));
    	System.out.println("The new list is:");
    	displayContacts();
    }

    //sort contacts by phone number
    public void sortByNumber() {
    	Contacts.contactList.sort((a, b) -> b.getNumber().compareTo(a.getNumber()));
    	System.out.println("The new list is:");
    	displayContacts();
    }

    //remove duplicates
    public void removeDuplicates() {
        ArrayList<Contact> newList = new ArrayList<Contact>();
        Iterator<Contact> iter = Contacts.contactList.iterator();
        while (iter.hasNext()) {
            Contact contact = iter.next();
            Iterator<Contact> iter2 = newList.iterator();
            boolean found = false;
            while (iter2.hasNext()) {
                Contact contact2 = iter2.next();
                if (contact.getName().equals(contact2.getName()) && contact.getNumber().equals(contact2.getNumber())) {
                    found = true;
                }
            }
            if (!found) {
                newList.add(contact);
            }
        }
        Contacts.contactList = newList;
    }

    //reverse the contact list
    public void reverseList() {
        ArrayList<Contact> newList = new ArrayList<Contact>();
        Iterator<Contact> iter = Contacts.contactList.iterator();
        while (iter.hasNext()) {
            Contact contact = iter.next();
            newList.add(0, contact);
        }
        Contacts.contactList = newList;
    }
    //save to file
    public void saveToFile(String fileName) {
        //Scanner reader = new Scanner(System.in);
        //System.out.println("Enter file name");
        //String fileName = reader.nextLine();
        //reader.close();
        try{
            File file = new File(fileName + ".txt");
            file.createNewFile();
            try{
                FileWriter writer = new FileWriter(fileName + ".txt");
                Iterator<Contact> iter = Contacts.contactList.iterator();
                while (iter.hasNext()) {
                    Contact contact = iter.next();
                    writer.write(contact.getName() + "-" + contact.getNumber() + "\n");
                }
                writer.close();
                System.out.println("Successfully wrote to the file");
            } catch(IOException e){
                System.out.println("An error occurred");
                e.printStackTrace();
            }

        } catch(IOException e){
            System.out.println("An error occurred");
            e.printStackTrace();
        }

    }


    
    //load from file
    public void loadFromFile(String fileName){
        //Scanner reader = new Scanner(System.in);
        //System.out.println("Enter file name");
        //String fileName = reader.nextLine();
        //reader.close();
        try{
            File file = new File(fileName + ".txt");
            Scanner fileReader = new Scanner(file);
            while(fileReader.hasNextLine()){
                String data = fileReader.nextLine();
                String[] contactData = data.split("-");
                Contact contact = new Contact(contactData[0], contactData[1]);
                this.addContact(contact);
            }
            fileReader.close();
        } catch(FileNotFoundException e){
            System.out.println("An error occurred");
            e.printStackTrace();
        }

    }

    //get contact by name
    public Contact getContactByName(String name) throws CloneNotSupportedException{
        Iterator<Contact> iter = Contacts.contactList.iterator();
        while (iter.hasNext()) {
            Contact contact = iter.next();
            if (contact.getName().equals(name)) {
                return contact.clone();
            }
        }
        return null;
    }
//////////////////////////
    public void menuLoop(){
        boolean loop = true;
        
        Scanner reader = new Scanner(System.in);
        while(loop){
        	printContactsMenu();
            int choice = reader.nextInt();
            switch(choice){
                case 1:
                    reader.nextLine();
                    System.out.println("Enter name");
                    String name = reader.nextLine();
                    System.out.println("Enter number");
                    String number = reader.nextLine();
                    Contact contact = new Contact(name, number);
                    this.addContact(contact);
                    break;
                case 2:
                    reader.nextLine();
                    System.out.println("Enter name");
                    name = reader.nextLine();
                    this.deleteContact(name);
                    break;
                case 3:
                    displayContacts();
                    break;
                case 4:
                    reader.nextLine();
                    System.out.println("Enter name");
                    name = reader.nextLine();
                    this.searchContact(name);
                    break;
                case 5:
                    sortByName();
                    break;
                case 6:
                    sortByNumber();
                    break;
                case 7:
                    removeDuplicates();
                    break;
                case 8:
                    reverseList();
                    break;
                case 9:
                    reader.nextLine();
                    System.out.println("Enter file name");
                    String fileName = reader.nextLine();
                    saveToFile(fileName);
                    break;
                case 10:
                    reader.nextLine();
                    System.out.println("Enter file name");
                    fileName = reader.nextLine();
                    loadFromFile(fileName);
                    break;
                case 11:
                    System.out.println("Goodbye! exiting");
                    loop = false;
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }

        }
        reader.close();
    }
    //////////////////////////
}