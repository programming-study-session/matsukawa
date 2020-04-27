package model;

import dao.PostDao;

public class PostLogic {
	public void execute(Post post) {
		PostDao dao = new PostDao();
		dao.create(post);
	}

}
