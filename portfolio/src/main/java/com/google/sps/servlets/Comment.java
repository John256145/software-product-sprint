package com.google.sps.servlets;
public class Comment {

    String user = "";
	String comment = "";
    String timestamp = "";



	public Comment(String comment) {
		this.comment = comment;
	}

    public Comment(String comment, String user, String timestamp){
        this.comment = comment;
        this.timestamp = timestamp;
        this.user = user;
    }


	public String getComment() {
		return comment;
	}


    public String getTimestamp() {
        return timestamp;
    }

    public String getUser() {
        return user;
    }

    public void setComment(String comment) {
		this.comment = comment;
	}

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public void setUser(String user) {
        this.user = user;
    }

}
