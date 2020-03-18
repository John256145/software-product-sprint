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
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

@WebServlet("/loginData")
public class LoginServlet extends HttpServlet {

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    response.setContentType("text/html");
    UserService userService = UserServiceFactory.getUserService();
    
    if (userService.isUserLoggedIn()) {
      String userEmail = userService.getCurrentUser().getEmail();
      String urlToRedirectToAfterUserLogsOut = "/";
      String logoutUrl = userService.createLogoutURL(urlToRedirectToAfterUserLogsOut);

      response.getWriter().println("<p>Hello " + userEmail + "!</p>");
      response.getWriter().println("<form action=\"/data\" method=\"POST\"><p>Leave a comment here! (Your email address will be visible):</p><textarea name=\"comment-input\"></textarea><br/><br/><input type=\"submit\" /></form>");
      response.getWriter().println("<p>Click below to sign out.</p>");
      response.getWriter().println("<a href = \"" + logoutUrl + "\" target = \"_self\"> <img src = \"/images/signout.png\" alt = \"Logout\" height=\"46\" width=\"191\" border = \"0\"/> </a>");
    } else {
      String urlToRedirectToAfterUserLogsIn = "/";
      String loginUrl = userService.createLoginURL(urlToRedirectToAfterUserLogsIn);
      response.getWriter().println("<p>Log in below to add a comment!</p>");
      response.getWriter().println("<a href = \"" + loginUrl + "\" target = \"_self\"> <img src = \"/images/signinblue.png\" alt = \"Login\" height=\"46\" width=\"191\" border = \"0\"/> </a>");
    }

  }

//   @Override
//   public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

//   }

}

