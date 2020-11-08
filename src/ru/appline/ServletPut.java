package ru.appline;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import ru.appline.logic.Model;
import ru.appline.logic.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/put")
public class ServletPut extends HttpServlet {

    Model model = Model.getInstance();
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {

        StringBuffer jb = new StringBuffer();
        String line;

        try {
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null) {
                jb.append(line);
            }
        } catch (Exception e) {
            System.out.println("Error");
        }

        JsonObject jObj = gson.fromJson(String.valueOf(jb), JsonObject.class);
        response.setContentType("application/json;charset=utf-8");
        request.setCharacterEncoding("UTF-8");

        PrintWriter pw = response.getWriter();

        int id = jObj.get("id").getAsInt();
        String name = jObj.get("name").getAsString();
        String surname = jObj.get("surname").getAsString();
        double salary = jObj.get("salary").getAsDouble();

        User user = new User(name, surname, salary);
        model.add(user, id);

        pw.print(gson.toJson(model.getFromList()));
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter pw = response.getWriter();

        try {

            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            String surname = request.getParameter("surname");
            double salary = Double.parseDouble(request.getParameter("salary"));

            User user = new User(name, surname, salary);
            model.add(user, id);

            if (id <= 0 || id > model.getFromList().size()) {
                pw.print("<html> + " +
                        "<h3>Такого пользователя нет!</h3>" +
                        "<a href=\"index.jsp\">Домой</a>" +
                        "</html>");
            } else {
                pw.print("<html> " +
                        "<h3>Данные пользователя с id: " + id + " успешно изменены! </h3>" +
                        "<a href=\"index.jsp\">Домой</a>" +
                        "</html>");
            }

        } catch (NumberFormatException e) {

            pw.print("<html> " +
                    "<h3>Введен некорректный формат данных, попробуйте еще раз! </h3>" +
                    "<a href=\"changeUser.html\">Вернуться назад</a>" +
                    "</html>");
        }
    }
}
