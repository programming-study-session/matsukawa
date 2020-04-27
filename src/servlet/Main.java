package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.GetPostListLogic;
import model.Post;
import model.PostLogic;
import model.UserName;

/**
 * Servlet implementation class Main
 */
@WebServlet("/Main")
public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ���e���X�g���擾���āA���N�G�X�g�X�R�[�v�ɕۑ�
		GetPostListLogic getPostListLogic = new GetPostListLogic();
		List<Post> postList = getPostListLogic.execute();
		request.setAttribute("postList", postList);

		// �t�H���[�h
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// ���N�G�X�g�p�����[�^�̎擾
		request.setCharacterEncoding("UTF-8");
		String text = request.getParameter("text");

		// ���͒l�`�F�b�N
		if (text != null && text.length() != 0) {

			// �Z�b�V�����X�R�[�v�ɕۑ��������O���擾
			HttpSession session = request.getSession();
			UserName userName = (UserName) session.getAttribute("userName");

			// ���e�𓊍e���X�g�ɒǉ�
			Post post = new Post(getId(), userName.getName(), text);
			PostLogic postLogic = new PostLogic();
			postLogic.execute(post);
		} else {
			// �G���[���b�Z�[�W�����N�G�X�g�X�R�[�v�ɕۑ�
			request.setAttribute("errorMsg", "���e�����͂���Ă��܂���");
		}

		// ���e���X�g���擾���āB���N�G�X�g�X�R�[�v�ɕۑ�
		GetPostListLogic getPostListLogic = new GetPostListLogic();
		List<Post> postList = getPostListLogic.execute();
		request.setAttribute("postList", postList);

		// �t�H���[�h
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
		dispatcher.forward(request, response);

	}

	private String getId() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		return null;
	}
}