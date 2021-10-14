package co.edu.unbosque.ciclo3demo;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.edu.unbosque.jsoncontroller.UsuariosJSON;
import co.edu.unbosque.model.Usuarios;

/**
 * Servlet implementation class DemoServlet
 */
@WebServlet("/DemoServlet")
public class DemoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DemoServlet() {
		super();

	}

	public void validarUsuarios(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			ArrayList<Usuarios> lista = UsuariosJSON.getJSON();
			request.setAttribute("lista", lista);
			String usua = request.getParameter("txtusuario");
			String pass = request.getParameter("txtpassword");
			int respuesta = 0;
			for (Usuarios usuario : lista) {
				if (usuario.getUsuario().equals(usua) && usuario.getPassword().equals(pass)) {
					request.setAttribute("usuario", usuario);
					request.getRequestDispatcher("/principal.jsp").forward(request, response);
					respuesta = 1;
				}
			}

			if (respuesta == 0) {
				request.getRequestDispatcher("/login.jsp").forward(request, response);
//				System.out.println("Usuario o Contraseņa invalidos");
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String accion = request.getParameter("accion");
		System.out.println(accion);
		if (accion.equals("Ingresar")) {
			this.validarUsuarios(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);

	}

}
