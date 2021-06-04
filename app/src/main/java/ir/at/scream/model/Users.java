package ir.at.scream.model;

import com.google.gson.annotations.SerializedName;

public class Users {

    @SerializedName("id")
    private String id;

    @SerializedName("name")
    private String name;

    @SerializedName("img")
    private String img;

    @SerializedName("username")
    private String username;

    @SerializedName("score")
    private String score;

    @SerializedName("dateScore")
    private String dateScore;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getDateScore() {
        return dateScore;
    }

    public void setDateScore(String dateScore) {
        this.dateScore = dateScore;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
