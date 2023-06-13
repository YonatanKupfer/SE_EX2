
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class TestPhone {
    public TestPhone(){

    }

    public void phoneManager_test() throws CloneNotSupportedException{
        Contacts contacts = new Contacts();
        Sms sms = new Sms();
        Diary diary = new Diary();
        MediaPlayer mediaPlayer = new MediaPlayer();
        boolean[] apps = {true, true, true, true};
        boolean exit = false;
        
		contacts.addContact(new Contact("itay", "123"));
		contacts.addContact(new Contact("noam", "456"));
		contacts.addContact(new Contact("adina", "789"));
		contacts.addContact(new Contact("dvir", "1011"));
		contacts.addContact(new Contact("Dvir", "1011"));
		
		
		sms.addMessage("itay", "message_1");		
		sms.addMessage("itay","message_1");
		
		Date date_1 = new Date();
		
		diary.addEvent(new Meet("meet_1", date_1, 40, contacts.getContactByName("adina")));
        diary.addEvent(new Single("single_1", date_1, 10, "description"));

        
        mediaPlayer.addMedia(new Music("Unicorn - Noa Kirel", "3:00"));
        mediaPlayer.addMedia(new Music("It's been a long time you got here", "0:05"));
        mediaPlayer.addMedia(new Video("Noams 'Bar-Mitzva'", "120:00"));

        
        Scanner reader = new Scanner(System.in);
        String choice = "";
        String name = "";
        String number = "";
        while(!exit){
            printPhoneMenu();
            choice = reader.nextLine();
            switch(choice){
                //contacts
                case "1":
                    while(apps[0]){
                        contacts.printContactsMenu();
                        choice = reader.nextLine();
                        switch(choice){
                            case "1":
                                System.out.println("Enter name: ");
                                name = reader.nextLine();
                                if(!contacts.searchContactBool(name)) {
                                	System.out.println("Enter number: ");
                                	number = reader.nextLine();
                                	contacts.addContact(new Contact(name, number));
                                }
                                else {
                                	System.out.println("Contact already exists.");
                                }
                                break;
                            case "2":
                            System.out.println("Enter name: ");
                            name = reader.nextLine();
                            if(contacts.searchContactBool(name)){
                                diary.removeAllEventsByName(name);
                                if(sms.haveMessages(name)){
                                    sms.deleteConversation(name);
                                }
                                contacts.deleteContact(name);
                            }
                            else{
                                System.out.println("Contact not found");
                            }
                                break;
                            case "3":
                                contacts.displayContacts();
                                break;
                            case "4":
                                System.out.println("Enter name: ");
                                name = reader.nextLine();
                                contacts.searchContact(name);
                                break;
                            case "5":
                                contacts.sortByName();
                                break; 
                            case "6":
                                contacts.sortByNumber();
                                break;
                            case "7":
                                contacts.removeDuplicates();
                                break;
                            case "8":
                                contacts.reverseList();
                                break;
                            case "9":
                                System.out.println("Enter file name: ");
                                String fileName = reader.nextLine();
                                contacts.saveToFile(fileName);
                                break;
                            case "10":
                                System.out.println("Enter file name: ");
                                fileName = reader.nextLine();
                                contacts.loadFromFile(fileName);
                                break;
                            case "11":
                                System.out.println("Returning to phone menu");
                                apps[0] = false;
                                break;
                            default:
                                System.out.println("Invalid input");
                                break;
                        }
                    }
                    break;
                //SMS app
                case "2":
                    while(apps[1]){
                        sms.printSmsMenu();
                        choice = reader.nextLine();
                        switch(choice){
                            case "1":
                                System.out.println("Enter name: ");
                                name = reader.nextLine();
                                if(contacts.searchContactBool(name)){
                                    System.out.println("Enter message: ");
                                    String message = reader.nextLine();
                                    sms.addMessage(name, message);
                                }
                                else{
                                    System.out.println("Contact not found");
                                }
                                break;
                            case "2":
                                System.out.println("Enter name: ");
                                name = reader.nextLine();
                                if(contacts.searchContactBool(name)){
                                    sms.deleteConversation(name);
                                }
                                else{
                                    System.out.println("Contact not found");
                                }
                                break;
                            case "3":
                                System.out.println("Enter name: ");
                                name = reader.nextLine();
                                if(contacts.searchContactBool(name)){
                                    sms.printConversation(name);
                                }
                                else{
                                    System.out.println("Contact not found");
                                }
                                break;
                            case "4":
                                System.out.println("Enter a sentence to search for: ");
                                String sentence = reader.nextLine();
                                sms.searchSentence(sentence);
                                break;
                            case "5":
                                sms.printAllMessages();
                                break;
                            case "6":
                                System.out.println("Returning to phone menu");
                                apps[1] = false;
                                break;
                            default:
                                System.out.println("Invalid input");
                                break;
                            }
                    }
                    break;
                //Diary app
                case "3":
                    while(apps[2]){
                        diary.printDiaryMenu();
                        choice = reader.nextLine();
                        switch(choice){
                            case "1":
                                System.out.println("Enter date: (1-30)");
                                int date = reader.nextInt();
                                reader.nextLine();
                                System.out.println("Enter hour: (0-23)");
                                int hour = reader.nextInt();
                                reader.nextLine();
                                System.out.println("Enter minute: (0-59)");
                                int minute = reader.nextInt();
                                reader.nextLine();
                                if(!diary.isValidDate(date, hour, minute)){
                                    System.out.println("Invalid date");
                                    break;
                                }
                                Calendar cal = Calendar.getInstance();
                                cal.set(2023,6,date,hour,minute);
                                Date date1 = cal.getTime();
                                System.out.println("Enter duration: (1-60)");
                                int duration = reader.nextInt();
                                reader.nextLine();
                                if(duration < 1 || duration > 60){
                                    System.out.println("Invalid duration");
                                    break;
                                }
                                System.out.println("Enter title: ");
                                String title = reader.nextLine();
                            
                                boolean flag = true;
                                while(flag){
                                    System.out.println("Enter type: 1 for meeting, 2 for single event");
                                    int type = reader.nextInt();
                                    reader.nextLine();
                                    if(type==1){
                                        System.out.println("Enter name: ");
                                        name = reader.nextLine();
                                        try{
                                            if(contacts.searchContactBool(name)){
                                                diary.addEvent(new Meet(title, date1, duration, contacts.getContactByName(name)));
                                                flag = false;
                                            }
                                            else{
                                                System.out.println("Contact not found");
                                            }
                                        }
                                        catch(Exception e){
                                            System.out.println("Invalid input");
                                        }
                                    }
                                    else if(type==2){
                                        System.out.println("Enter description: ");
                                        String description = reader.nextLine();
                                        try{
                                            diary.addEvent(new Single(title, date1, duration, description));
                                            flag = false;
                                        }
                                        catch(Exception e){
                                            System.out.println("Invalid input");
                                        }
                                    }
                                    else{
                                    System.out.println("Invalid input");
                                    }
                                }
                                break;
                            case "2":
                                System.out.println("Enter date: (1-30)");
                                date = reader.nextInt();
                                reader.nextLine();
                                if(!diary.isValidDate(date, 0, 0)){
                                    System.out.println("Invalid date");
                                    break;
                                }
                                cal = Calendar.getInstance();
                                cal.set(2023,6,date,0,0);
                                date1 = cal.getTime();
                                System.out.println("Choose an event to delete: ");
                                diary.printEventsByDate(date1);
                                int event = reader.nextInt();
                                reader.nextLine();
                                diary.removeEvent(event, date1);
                                break;
                            case "3":
                                System.out.println("Enter date: (1-30)");
                                date = reader.nextInt();
                                reader.nextLine();
                                if(!diary.isValidDate(date, 0, 0)){
                                    System.out.println("Invalid date");
                                    break;
                                }
                                cal = Calendar.getInstance();
                                cal.set(2023,6,date,0,0);
                                date1 = cal.getTime();
                                diary.printEventsByDate(date1);
                                break;
                            case "4":
                                System.out.println("Enter name: ");
                                name = reader.nextLine();
                                if(contacts.searchContactBool(name)){
                                    diary.printEventsByContact(name);
                                }
                                else{
                                    System.out.println("Contact not found");
                                }
                                break;
                            case "5":
                                diary.printAllEvents();
                                break;
                            case "6":
                                System.out.println("Returning to phone menu");
                                apps[2] = false;
                                break;
                            default:
                                System.out.println("Invalid input");
                                break;
                            }
                    }
                    break;
                //Media player app
                case "4":
                    while(apps[3]){
                        mediaPlayer.printMediaPlayerMenu();
                        choice = reader.nextLine();
                        switch(choice){
                            case "1":
                                System.out.println("Enter song title: ");
                                String title = reader.nextLine();
                                System.out.println("Enter song minutes: ");
                                String minutes = reader.nextLine();
                                System.out.println("Enter song seconds: ");
                                String seconds = reader.nextLine();
                                String length = minutes + ":" + seconds;
                                //check if length is valid: both minutes and seconds must be integers
                                //and seconds must be between 0 and 59
                                try{
                                    Integer.parseInt(minutes);
                                    int sec = Integer.parseInt(seconds);
                                    if(sec < 0 || sec > 59){
                                        System.out.println("Invalid length");
                                        break;
                                    }
                                    
                                }
                                catch(NumberFormatException e){
                                    System.out.println("Invalid length");
                                    break;
                                }
                                mediaPlayer.addMedia(new Music(title, length));
                                break;
                            case "2":
                                System.out.println("Enter video title: ");
                                title = reader.nextLine();
                                System.out.println("Enter video minutes: ");
                                minutes = reader.nextLine();
                                System.out.println("Enter video seconds: ");
                                seconds = reader.nextLine();
                                length = minutes + ":" + seconds;
                                //check if length is valid: both minutes and seconds must be integers
                                //and seconds must be between 0 and 59
                                try{
                                    Integer.parseInt(minutes);
                                    int sec = Integer.parseInt(seconds);
                                    if(sec < 0 || sec > 59){
                                        System.out.println("Invalid length");
                                        break;
                                    }
                                }
                                catch(NumberFormatException e){
                                    System.out.println("Invalid length");
                                    break;
                                }
                                mediaPlayer.addMedia(new Video(title, length));
                                break;
                            case "3":
                                System.out.println("Enter Media title: ");
                                title = reader.nextLine();
                                mediaPlayer.turnOnMedia(title);
                                break;
                            case "4":
                                mediaPlayer.turnOnAllMedia();
                                break;
                            case "5":
                                System.out.println("Returning to phone menu");
                                apps[3] = false;
                                break;
                            default:
                                System.out.println("Invalid input");
                                break;
                            }   
                        }
                    break;
                case "5":
                    System.out.println("Printing all apps functions");
                    System.out.println("Contacts: ");
                    contacts.printContactsMenu();
                    System.out.println("SMS: ");
                    sms.printSmsMenu();
                    System.out.println("Diary: ");
                    diary.printDiaryMenu();
                    System.out.println("Media Player: ");
                    mediaPlayer.printMediaPlayerMenu();
                    break;

                case "6":
                    System.out.println("Exiting phone");
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid input");
                    break;                        
                                
            }
            apps[0] = true;
            apps[1] = true;
            apps[2] = true;
            apps[3] = true;
            System.out.println();
        }
        reader.close();
    }



    public void printPhoneMenu(){
        System.out.println("Welcome to your phone");
        System.out.println("Which app would you like to open?");
        System.out.println("1. Contacts");
        System.out.println("2. SMS");
        System.out.println("3. Diary");
        System.out.println("4. Media Player");
        System.out.println("5. Prtint all apps functions");
        System.out.println("6. Exit");

    }
}
