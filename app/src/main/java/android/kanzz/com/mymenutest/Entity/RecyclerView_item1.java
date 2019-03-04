package android.kanzz.com.mymenutest.Entity;

public class RecyclerView_item1 extends RecyclerView_baseitem{
    private int touXiang;
    private String author;
    private String Time;
    private String abstractText;
    private int abstractImage;
    public RecyclerView_item1(int touXiang,String author,String Time,String abstractText,int abstractImage){
        this.touXiang=touXiang;
        this.author=author;
        this.Time=Time;
        this.abstractText=abstractText;
        this.abstractImage=abstractImage;
    }
    public int getTouXiang() {
        return touXiang;
    }

    public void setTouXiang(int touXiang) {
        this.touXiang = touXiang;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getAbstractText() {
        return abstractText;
    }

    public void setAbstractText(String abstractText) {
        this.abstractText = abstractText;
    }

    public int getAbstractImage() {
        return abstractImage;
    }

    public void setAbstractImage(int abstractImage) {
        this.abstractImage = abstractImage;
    }

}
