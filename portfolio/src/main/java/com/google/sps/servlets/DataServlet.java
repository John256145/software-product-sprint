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
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
/** Servlet that returns some example content. TODO: modify this file to handle comments data */
@WebServlet("/data")
public class DataServlet extends HttpServlet {

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    // Get the input from the form.
    String text = request.getParameter("text-input");
    // commentsarray.add(text);
    response.sendRedirect("/");
  }

  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    ArrayList<Comment> jsonArray = new ArrayList<Comment>();
    Comment newComment1 = new Comment("I love this site.");
    Comment newComment2 = new Comment("This is so cool.");
    Comment newComment3 = new Comment("All the cool kids are coding nowadays.");
    jsonArray.add(newComment1);
    jsonArray.add(newComment2);
    jsonArray.add(newComment3);

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
