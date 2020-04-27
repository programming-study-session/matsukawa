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

		// ���N�G�X�g�p�����[�^�̎擾
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");

		// UserName�C���X�^���X�̎擾
		UserName userName = new UserName(name);

		// ���O���Z�b�V�����X�R�[�v�ɕۑ�
		HttpSession session = request.getSession();
		session.setAttribute("userName", userName);

		// Main�T�[�u���b�g�Ƀt�H���[�h
		RequestDispatcher dispatcher = request.getRequestDispatcher("/Main");
		dispatcher.forward(request, response);
	}
}
