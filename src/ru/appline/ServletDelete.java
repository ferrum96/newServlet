package ru.appline;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import ru.appline.logic.Model;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/delete")
public class ServletDelete extends HttpServlet {

    Model model = Model.getInstance();
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        StringBuffer jb = new StringBuffer();
        String line;
        PrintWriter pw = response.getWriter();
        response.setContentType("application/json;charset=utf-8");
        request.setCharacterEncoding("UTF-8");

        try {
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null) {
                jb.append(line);
            }
        } catch (Exception e) {
            System.out.println("Error");
        }

        JsonObject jObj = gson.fromJson(String.valueOf(jb), JsonObject.class);
        int id = jObj.get("id").getAsInt();
        model.getFromList().remove(id);
        pw.print(gson.toJson(model.getFromList()));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter pw = response.getWriter();
        int id = Integer.parseInt(request.getParameter("id"));

        if (id <= 0 || id > model.getFromList().size()) {
            pw.print("<html> + " +
                    "<h3>Такого пользователя нет!</h3>" +
                    "<a href=\"index.jsp\">Домой</a>" +
                    "</html>");
        } else {
            pw.print("<html> + " +
                    "<h3>Пользователь " + model.getFromList().get(id).getName() + " " + model.getFromList().get(id).getSurname() + " с id: " + id + " удален! </h3>" +
                    "<a href=\"index.jsp\">Домой</a>" +
                    "</html>");
            model.getFromList().remove(id);
        }
    }
}
