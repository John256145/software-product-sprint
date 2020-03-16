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
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.SortDirection;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/data")
public class DataServlet extends HttpServlet {

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    String text = request.getParameter("comment-input");
    String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date());
    String user = "Anonymous";

    if (text.isEmpty() == false) {
        Entity commentEntity = new Entity("commentsData");
        commentEntity.setProperty("Comment", text);
        commentEntity.setProperty("Timestamp", timeStamp);
        commentEntity.setProperty("User", user);
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        datastore.put(commentEntity);
    }

    //redirects to main page
    response.sendRedirect("/index.html");
  }

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    ArrayList<Comment> jsonArray = new ArrayList<Comment>();
    Query query = new Query("commentsData");
    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
    PreparedQuery results = datastore.prepare(query);

    for (Entity entity : results.asIterable()) {
        String comment = (String) entity.getProperty("Comment");
        String timeStamp = (String) entity.getProperty("Timestamp");
        String user = (String) entity.getProperty("User");
        Comment newComment = new Comment(comment, user, timeStamp);
        jsonArray.add(newComment);
    }
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

