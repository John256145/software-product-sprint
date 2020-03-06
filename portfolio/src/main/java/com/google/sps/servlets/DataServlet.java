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

    
    


  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    ArrayList<String> jsonarray = new ArrayList<String>();
    jsonarray.add("I love this site.");
    jsonarray.add("This is so cool.");
    jsonarray.add("All the cool kids are coding nowadays.");
    // response.setContentType("text/html;");
    // response.getWriter().println("Hello Juan!");

    String json = convertToJson(jsonarray);
    response.setContentType("application/json;");
    response.getWriter().println(json);

  }


  private String convertToJson(ArrayList<String> alist) {
		String json = "{";
		
		for (int i=0; i<alist.size() - 1; i++) {
			json += "\"comment" + i + "\": " + "\"" + alist.get(i) + "\"";
			json += ", ";
		}
		
		int lastidx = alist.size() - 1;
		json += "\"comment" + lastidx + "\": " + alist.get(lastidx) + "\"";
		json += "}";
		return json;
	}



}
