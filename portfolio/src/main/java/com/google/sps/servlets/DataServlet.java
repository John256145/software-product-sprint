// Copyright 2019 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.sps.servlets;
import com.google.gson.Gson;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

@WebServlet("/data")
public class DataServlet extends HttpServlet {
  ArrayList<Comment> jsonArray = new ArrayList<Comment>();


  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    String text = request.getParameter("comment-input");
    //As of right now, comments are stored in jsonArray and datastore

    if (text.isEmpty() == false) {
        //When retrieval from datastore is implemented, this section of code will change
        Comment newComment = new Comment(text);
        jsonArray.add(newComment);

        //sending comment to datastore
        Entity commentEntity = new Entity("Comments");
        commentEntity.setProperty("Comment", text);
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        datastore.put(commentEntity);
    }

    //redirects to main page
    response.sendRedirect("/index.html"); 
  }

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    //retrieval from datastore not yet implemented
    String json = convertToJson(jsonArray);
    response.setContentType("application/json;");
    response.getWriter().println(json);

  }

  private String convertToJson(ArrayList<Comment> commentsList) {

	Gson gson = new Gson();
    String json = gson.toJson(commentsList);
    return json;
  }
}