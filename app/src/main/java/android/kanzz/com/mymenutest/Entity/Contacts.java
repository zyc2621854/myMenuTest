package android.kanzz.com.mymenutest.Entity;

public class Contacts {
    private String name;
    private String message;
    private int imageId;

    public Contacts(String name,String message,int imageId){
        this.name=name;
        this.message=message;
        this.imageId=imageId;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}
