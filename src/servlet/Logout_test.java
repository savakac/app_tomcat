package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//Zabezpeci pristup k servletu len autentifikuvanemu uzivatelovy s autorizaciou "user"
@ServletSecurity( @HttpConstraint(rolesAllowed = {"user"}))
//Adresa servletu
@WebServlet("/Logout_test")
public class Logout_test extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Logout_test() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().invalidate();
		
		response.setStatus(HttpServletResponse.SC_SEE_OTHER);
		response.setHeader("Location", request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
