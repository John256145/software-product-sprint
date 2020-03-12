package com.google.sps.servlets;
public class Comment {

    String user = "Anonymous";
	String comment = "";


	public Comment(String comment) {
		this.comment = comment;
	}

	public String getComment() {
		return comment;
	}

    public void setComment(String comment) {
		this.comment = comment;
	}


}
