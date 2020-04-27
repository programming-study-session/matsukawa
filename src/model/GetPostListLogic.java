package model;

import java.util.List;
import dao.PostDao;

public class GetPostListLogic {
	public List<Post> execute() {
		PostDao dao = new PostDao();
		List<Post> postList = dao.findAll();
		return postList;
	}

}
