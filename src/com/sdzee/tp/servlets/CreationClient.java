package com.sdzee.tp.servlets;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.sdzee.tp.beans.Client;
import com.sdzee.tp.forms.CreationClientForm;

public class CreationClient extends HttpServlet{
	
	public static final String ATT_CLIENT = "client";
	public static final String ATT_FORM = "form";
	public static final String VUE_SUCCES = "/WEBINF/afficherClient.jsp";
	public static final String VUE_FORM = "/WEB-INF/creerClient.jsp";
	
	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
	
		/* ? la r?ception d'une requ?te GET, simple affichage du
	formulaire */
		this.getServletContext().getRequestDispatcher( VUE_FORM ).forward( request, response );
	}
	
	public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
	
		/* Pr?paration de l'objet formulaire */
		CreationClientForm form = new CreationClientForm();
		/* Traitement de la requ?te et r?cup?ration du bean en r?sultant */
		Client client = form.creerClient( request );
		/* Ajout du bean et de l'objet m?tier ? l'objet requ?te */
		request.setAttribute( ATT_CLIENT, client ); request.setAttribute( ATT_FORM, form );
		if ( form.getErreurs().isEmpty() ) {
			/* Si aucune erreur, alors affichage de la fiche r?capitulative */
			this.getServletContext().getRequestDispatcher( VUE_SUCCES ).forward( request, response );
		} else {
			/* Sinon, r?-affichage du formulaire de cr?ation avec les erreurs */
		 this.getServletContext().getRequestDispatcher( VUE_FORM ).forward( request, response );
		}
	}
}