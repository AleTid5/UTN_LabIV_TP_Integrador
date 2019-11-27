package Utils;

public class Message {
    private String className;
    private String title;
    private String body;

    public Message(Boolean isError, String body) {
        this.className = isError ? "rose" : "success";
        this.title = isError ? "Error" : "Operación exitosa";
        this.body = body;
    }

    public String getClassName() {
        return className;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }
}
