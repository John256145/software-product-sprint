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

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
/** Servlet that returns some example content. TODO: modify this file to handle comments data */
@WebServlet("/data")
public class DataServlet extends HttpServlet {
  ArrayList<String> commentsarray = new ArrayList<String>();

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    // Get the input from the form.
    String text = request.getParameter("text-input");
    commentsarray.add(text);
    response.sendRedirect("/");
  }


  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    String comments = convert(commentsarray);
    response.setContentType("application/text;");
    response.getWriter().println(comments);

  }

  private String convertToJson(ArrayList<String> alist) {
		String json = "{";
		
        if (alist.size() <= 0) {
            return "";
        }

		for (int i=0; i<alist.size() - 1; i++) {
			json += "\"comment" + i + "\": " + "\"" + alist.get(i) + "\"";
			json += ", ";
		}
		
		int lastidx = alist.size() - 1;
		json += "\"comment" + lastidx + "\": " + alist.get(lastidx) + "\"";
		json += "}";
		return json;
	}



  public static String convert(ArrayList<String> alist) {
		String s = "";
		for(int i = 0; i<alist.size(); i++) {
			s += alist.get(i);
			s += "\n";
		}
		return s;
	}



}
