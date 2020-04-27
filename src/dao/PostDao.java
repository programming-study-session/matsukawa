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
	// データベース接続に使用する情報
	private final String JDBC_URL = "jdbc:postgresql://localhost/javasample"; // ・・・
																				// jdbc:postgresql://[ホスト名]:[ポート番号]/[データベース名];
	private final String DB_USER = "postgres";
	private final String DB_PASS = "postgres";

	public List<Post> findAll() {
		List<Post> postList = new ArrayList<Post>();

		// データベース接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

			// SELECT文の準備
			String sql = "select id, name, text, created_at  from bbs_post_sample_dao order by id desc";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SELECTを実行
			ResultSet rs = pStmt.executeQuery();

			// SELECT文の結果をArrayListに格納
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
		// データベース接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

			// INSERT文の準備(idは自動連番なので指定しなくてよい）
			String sql = "insert into bbs_post_sample_dao (name, text) values(?, ?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			// INSERT文中の「?」に使用する値を設定しSQLを完成
			pStmt.setString(1, post.getName());
			pStmt.setString(2, post.getText());

			// INSERT文を実行
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
