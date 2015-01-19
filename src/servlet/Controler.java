package servlet;

import model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.ServletSecurity.TransportGuarantee;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.EditContent;

@ServletSecurity( @HttpConstraint(rolesAllowed = {"user"}, transportGuarantee = TransportGuarantee.CONFIDENTIAL))
@WebServlet({ "/Controler", "/zapisky", "/upravit", "/pridat", "/smazat", "/ulozitupravy", "/logout" })
public class Controler extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public Controler() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user = request.getRemoteUser();
		String adresa = request.getServletPath();
		EditContent editContent = new EditContent();
		
		if (adresa.equals("/zapisky")) {
			try {
				List<Zapisek> zapisky = editContent.getZapisky(user);
				request.setAttribute("zapisky", zapisky);
				request.getRequestDispatcher("/WEB-INF/view/zapisky.jsp").forward(request, response);
			} catch (SQLException e) {
				Logger.getLogger(Controler.class.getName()).log(Level.SEVERE, null, e);
			}
		}
		else if (adresa.equals("/upravit")) {
			try {
				int id = Integer.parseInt(request.getParameter("id"));
				Zapisek zapisek = editContent.getZapisek(id, user);
				request.setAttribute("zapisek", zapisek);
				request.getRequestDispatcher("/WEB-INF/view/upravit.jsp").forward(request, response);
			} catch (SQLException e) {
				Logger.getLogger(Controler.class.getName()).log(Level.SEVERE, null, e);
			}
		}
//		Odhlasenie zo stranky pomocou zrusenia sedenia
		else if (adresa.equals("/logout")) {
			request.getSession().invalidate();
			presmeruj(request, response, "");
		} else {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user = request.getRemoteUser();
		String adresa = request.getServletPath();
		request.setCharacterEncoding("UTF-8");
		EditContent editContent = new EditContent();
		
		if (adresa.equals("/pridat")) {
			String nadpis = request.getParameter("nadpis");
			String obsah = request.getParameter("obsah");
			if (!nadpis.isEmpty() && !obsah.isEmpty()) {
				try {
					editContent.addZapisek(nadpis, obsah, user);
				} catch (SQLException e) {
					Logger.getLogger(EditContent.class.getName()).log(Level.SEVERE, null, e);
				}
				presmeruj(request, response, "");
			}
			else {
				presmeruj(request, response, "/?upozorneni=True");
			}
		}
		else if (adresa.equals("/ulozitupravy")) {
			int id = Integer.parseInt(request.getParameter("id"));
			String nadpis = request.getParameter("nadpis");
			String obsah = request.getParameter("obsah");
			
			if (!nadpis.isEmpty() && !obsah.isEmpty()) {
				try {
					editContent.setZapisek(id, nadpis, obsah, user);
				} catch (SQLException e) {
					Logger.getLogger(EditContent.class.getName()).log(Level.SEVERE, null, e);
				}
				presmeruj(request, response, "");
			}
			else {
				presmeruj(request, response, "/upravit?id=" + id + "&upozorneni=True");
			}
		}
		else if (adresa.equals("/smazat")) {
			int id = Integer.parseInt(request.getParameter("id"));
			try {
				editContent.removeZapisek(id, user);
			} catch (SQLException e) {
				Logger.getLogger(EditContent.class.getName()).log(Level.SEVERE, null, e);
			}
			presmeruj(request, response, "");
		}
		else {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
	}
	
	private void presmeruj(HttpServletRequest request, HttpServletResponse response, String url) {
		response.setStatus(HttpServletResponse.SC_SEE_OTHER);
		response.setHeader("Location", request.getContextPath() + url);
	}

}
