package co.lucjay.board;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BoardSeviceImpl extends BoardService {

	@Override
	public void insertBoard(BoardDTO board) {
		String sql = "insert into board values((select nvl(max(board_no),0)+ 1 from board),?, ?, ?, sysdate, null)";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, board.getTitle());
			psmt.setString(2, board.getContent());
			psmt.setString(3, board.getWriter());
			int r = psmt.executeUpdate();
			System.out.println(r + "건 입력되었습니다");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public void updateBoard(BoardDTO board) {
	}

	@Override
	public BoardDTO getBoard(int boardNo) {
		String sql = "select * from board where board_no = ?";
		BoardDTO b = new BoardDTO();
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, boardNo);
			rs = psmt.executeQuery();
			if (rs.next()) {
				b.setBoardNo(rs.getInt("board_no"));
				b.setContent(rs.getString("content"));
				b.setCreationDate(rs.getString("creation_Date"));
				b.setTitle(rs.getString("title"));
				b.setWriter(rs.getString("writer"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return b;
	}

	public List<BoardDTO> getReplyList(int boardNo) {
		String sql = "select * from board where parent_no = ? order by 1 desc";
		List<BoardDTO> list = new ArrayList<>();
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, boardNo);
			rs = psmt.executeQuery();
			while (rs.next()) {
				BoardDTO b = new BoardDTO();
				b.setBoardNo(rs.getInt("board_no"));
				b.setContent(rs.getString("content"));
				b.setCreationDate(rs.getString("creation_Date"));
				b.setTitle(rs.getString("title"));
				b.setWriter(rs.getString("writer"));
				b.setParentNo(rs.getInt("parent_no"));
				list.add(b);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return list;
	}

	@Override
	public List<BoardDTO> getBoardList(BoardDTO board) {
		String sql = "select * from board where parent_no is null order by 1 desc";
		List<BoardDTO> list = new ArrayList<>();
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) {
				BoardDTO b = new BoardDTO();
				b.setBoardNo(rs.getInt("board_no"));
				b.setContent(rs.getString("content"));
				b.setCreationDate(rs.getString("creation_Date"));
				b.setTitle(rs.getString("title"));
				b.setWriter(rs.getString("writer"));
				list.add(b);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return list;
	}

}
