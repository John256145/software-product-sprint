package com.google.sps.servlets;
public class Comment {

    String userEmail = "";
	String comment = "";
    String timestamp = "";

	public Comment(String comment) {
		this.comment = comment;
	}

    public Comment(String comment, String userEmail, String timestamp){
        this.comment = comment;
        this.timestamp = timestamp;
        this.userEmail = userEmail;
    }

	public String getComment() {
		return comment;
	}


    public String getTimestamp() {
        return timestamp;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setComment(String comment) {
		this.comment = comment;
	}

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

}
