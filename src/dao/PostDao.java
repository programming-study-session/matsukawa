package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Post;

public class PostDao {
	// �f�[�^�x�[�X�ڑ��Ɏg�p������
	private final String JDBC_URL = "jdbc:postgresql://localhost/javasample"; // �E�E�E
																				// jdbc:postgresql://[�z�X�g��]:[�|�[�g�ԍ�]/[�f�[�^�x�[�X��];
	private final String DB_USER = "postgres";
	private final String DB_PASS = "postgres";

	public List<Post> findAll() {
		List<Post> postList = new ArrayList<Post>();

		// �f�[�^�x�[�X�ڑ�
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

			// SELECT���̏���
			String sql = "select id, name, text, created_at  from bbs_post_sample_dao order by id desc";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SELECT�����s
			ResultSet rs = pStmt.executeQuery();

			// SELECT���̌��ʂ�ArrayList�Ɋi�[
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String text = rs.getString("text");
				String created_at = rs.getString("created_at");
				Post post = new Post(id, name, text, created_at);
				postList.add(post);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return postList;
	}

	public boolean create(Post post) {
		// �f�[�^�x�[�X�ڑ�
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

			// INSERT���̏���(id�͎����A�ԂȂ̂Ŏw�肵�Ȃ��Ă悢�j
			String sql = "insert into bbs_post_sample_dao (name, text) values(?, ?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			// INSERT�����́u?�v�Ɏg�p����l��ݒ肵SQL������
			pStmt.setString(1, post.getName());
			pStmt.setString(2, post.getText());

			// INSERT�������s
			int result = pStmt.executeUpdate();

			if (result != 1) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
