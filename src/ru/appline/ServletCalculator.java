package ru.appline;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import ru.appline.logic.Calculator;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/calculator")
public class ServletCalculator extends HttpServlet {

    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
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

        double a = jObj.get("a").getAsDouble();
        double b = jObj.get("b").getAsDouble();
        String math = jObj.get("math").getAsString();

        Calculator calculator = new Calculator(a, b, math);

        pw.print(gson.toJson(calculator.toString()));
    }
}
