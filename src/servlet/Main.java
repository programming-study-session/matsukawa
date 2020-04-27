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
		// 投稿リストを取得して、リクエストスコープに保存
		GetPostListLogic getPostListLogic = new GetPostListLogic();
		List<Post> postList = getPostListLogic.execute();
		request.setAttribute("postList", postList);

		// フォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		String text = request.getParameter("text");

		// 入力値チェック
		if (text != null && text.length() != 0) {

			// セッションスコープに保存した名前を取得
			HttpSession session = request.getSession();
			UserName userName = (UserName) session.getAttribute("userName");

			// 投稿を投稿リストに追加
			Post post = new Post(getId(), userName.getName(), text);
			PostLogic postLogic = new PostLogic();
			postLogic.execute(post);
		} else {
			// エラーメッセージをリクエストスコープに保存
			request.setAttribute("errorMsg", "投稿が入力されていません");
		}

		// 投稿リストを取得して。リクエストスコープに保存
		GetPostListLogic getPostListLogic = new GetPostListLogic();
		List<Post> postList = getPostListLogic.execute();
		request.setAttribute("postList", postList);

		// フォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
		dispatcher.forward(request, response);

	}

	private String getId() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}
}