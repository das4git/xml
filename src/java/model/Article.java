package model;

public class Article {


    private int id_art;
    private int code;
    private String name;
    private String userName;
    private String guid;


    public int getId_art() {
        return id_art;
    }

    public void setId_art(int id_art) {
        this.id_art = id_art;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id_art=" + id_art +
                ", code=" + code +
                ", name='" + name + '\'' +
                ", userName='" + userName + '\'' +
                ", guid='" + guid + '\'' +
                '}';
    }

}
