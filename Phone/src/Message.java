
public class Message {
    private String text;
    private String contactName;

    public Message(String text, String contactName) {
        this.text = text;
        this.contactName = contactName;
    }

    public String getText() {
        return this.text;
    }

    public String getContactName() {
        return this.contactName;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public void addText(String text) {
        this.text = this.text + " " + text;
    }
}
