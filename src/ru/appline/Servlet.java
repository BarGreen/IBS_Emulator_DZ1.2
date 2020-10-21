package ru.appline;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;

import ru.appline.logic.Result;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Servlet implementation class Servlet
 */
@WebServlet("/calc")
public class Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    Result result = Result.getInstance();
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		JsonObject jobj = gson.fromJson(String.valueOf(jb), JsonObject.class);
		request.setCharacterEncoding("UTF-8");
		
		int a = jobj.get("a").getAsInt();
		int b = jobj.get("b").getAsInt();
		String math = jobj.get("math").getAsString();
		String text = "result";
		if (math.equals("+")) {
			int res = a + b;
			result.add(text, res);
		} else if (math.equals("-")) {
			int res = a - b;
			result.add(text, res);
		} else if (math.equals("*")) {
			int res = a * b;
			result.add(text, res);
		} else if (math.equals("/")) {
			int res = a / b;
			result.add(text, res);
		}
		
		
		response.setContentType("application/json;charset=utf-8");
		PrintWriter pw = response.getWriter();
		

		pw.print(gson.toJson(result.getFromList()));
		

	}
}