package ir.at.scream.model;

import com.google.gson.annotations.SerializedName;

public class Response {

    @SerializedName("Response")
    private String response;

    @SerializedName("Score")
    private String score;

    @SerializedName("Name")
    private String name;

    @SerializedName("ImgProf")
    private String imgProf;

    public String getResponse() {
        return response;
    }

    public String getScore() {
        return score;
    }

    public String getName() {
        return name;
    }

    public String getImgProf() {
        return imgProf;
    }
}
