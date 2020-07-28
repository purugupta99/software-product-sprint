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

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.SortDirection;
import com.google.gson.Gson;

import com.google.sps.data.UserDetail;

import java.util.ArrayList;
import java.util.List;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** Servlet that returns some example content. TODO: modify this file to handle comments data */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
  
  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    UserService userService = UserServiceFactory.getUserService();
    if (userService.isUserLoggedIn()) {
      String userEmail = userService.getCurrentUser().getEmail();
      String urlToRedirectToAfterUserLogsOut = "/web.html";
      String logoutUrl = userService.createLogoutURL(urlToRedirectToAfterUserLogsOut);
      String state = "logged in";

      UserDetail logObj = new UserDetail(userEmail, state, logoutUrl);
      
      Gson gson = new Gson();

      // System.out.println(gson.toJson(logObj)); 
  
      response.setContentType("application/json;");
      response.getWriter().println(gson.toJson(logObj));

    } else {
      String urlToRedirectToAfterUserLogsIn = "/web.html";
      String loginUrl = userService.createLoginURL(urlToRedirectToAfterUserLogsIn);
      String state = "logged out";

      UserDetail logObj = new UserDetail("", state, loginUrl);
      
      Gson gson = new Gson();

      // System.out.println(gson.toJson(logObj)); 
  
      response.setContentType("application/json;");
      response.getWriter().println(gson.toJson(logObj));
      

    }
  }
  
  
  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    response.getWriter().println("<p>Hello stranger.</p>");
  }
}
