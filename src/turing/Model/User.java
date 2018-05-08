package turing.Model;

import java.util.ArrayList;
import java.util.List;

public class User {

    private String username;
    private String password;
    private List<String> liked = new ArrayList<String>();
    private List<String> disliked = new ArrayList<String>();


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLiked(List<String> picNum) {
        this.liked = picNum;
    }

    public List<String> getLiked() {
        return liked;
    }


    public List<String> getDisliked() {
        return disliked;
    }

    public void setDisliked(List<String> disliked) {
        this.disliked = disliked;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", liked=" + liked +
                '}';
    }
}
