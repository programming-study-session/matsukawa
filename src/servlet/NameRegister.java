package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.UserName;

/**
 * Servlet implementation class NameRegister
 */
@WebServlet("/NameRegister")
public class NameRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");

		// UserNameインスタンスの取得
		UserName userName = new UserName(name);

		// 名前をセッションスコープに保存
		HttpSession session = request.getSession();
		session.setAttribute("userName", userName);

		// Mainサーブレットにフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/Main");
		dispatcher.forward(request, response);
	}
}
