package DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import DTO.PositionDTO;


public class PositionDAL {
	private Connection conn = null;
	private PreparedStatement pstm = null;
	private ResultSet rs = null;
	
	public Vector<PositionDTO> getPositions(){
		Vector<PositionDTO> listPosition = new Vector<PositionDTO>();
		try {
			String sql = "SELECT * FROM tbl_position";
			conn = JDBCUtil.getConnection();
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			while(rs.next()) {
				PositionDTO positionDTO = new PositionDTO(rs.getString("id_position"), rs.getString("name"));
				listPosition.add(positionDTO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstm.close();
				conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return listPosition;
	}
	
	public PositionDTO getPositionById(String id_position) {
		PositionDTO positionDTO = null;
		try {
			String sql = "SELECT * FROM tbl_position WHERE `id_position` = ?";
			conn = JDBCUtil.getConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, id_position);
			rs = pstm.executeQuery();
			if (rs.next()) {
				positionDTO = new PositionDTO(
						rs.getString("id_position"), rs.getString("name")
						);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstm.close();
				conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			
		}
		
		return positionDTO;
	}
	
	public int insert(PositionDTO positionDTO) {
		int kq = 0;
		try {
			String sql = "INSERT INTO tbl_position(id_position, name) VALUES(?, ?)";
			conn = JDBCUtil.getConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, positionDTO.getId_positions());
			pstm.setString(2, positionDTO.getName());
			
			kq = pstm.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				pstm.close();
				conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			
		}
		return kq;
	}
	
	public int delete(String id_position) {
		int kq1 = 0;
		try {
			String sql = "DELETE FROM tbl_position WHERE `id_position` = ?";
			conn = JDBCUtil.getConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, id_position);
			
			kq1 = pstm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				pstm.close();
				conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return kq1;
	}
}
