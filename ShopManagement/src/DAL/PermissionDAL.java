package DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import DTO.PermissionDTO;


public class PermissionDAL {
	private Connection conn = null;
	private PreparedStatement pstm = null;
	private ResultSet rs = null;
	
	
	public Vector<PermissionDTO> getPermissions() {
		Vector<PermissionDTO> listPermission = new Vector<PermissionDTO>();
		try {
			String sql = "SELECT * FROM tbl_permission";
			conn = JDBCUtil.getConnection();
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			while (rs.next()) {
				PermissionDTO PermissionDTO = new PermissionDTO(rs.getString("id_permission"), rs.getString("name"));
				listPermission.add(PermissionDTO);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstm.close();
				conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			
		}
		return listPermission;
	}
	
	
	public PermissionDTO getPermissionById(String id_permission) {
		PermissionDTO permissionDTO = null;
		try {
			String sql = "SELECT * FROM tbl_permission WHERE `id_permission` = ?";
			conn = JDBCUtil.getConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, id_permission);
			rs = pstm.executeQuery();
			if (rs.next()) {
				permissionDTO = new PermissionDTO(
						rs.getString("id_permission"), rs.getString("name")
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
		
		return permissionDTO;
	}
	
	
	public int insert(PermissionDTO permissionDTO) {
		int kq = 0;
		try {
			String sql = "INSERT INTO tbl_permission(id_permission, name) VALUES(?, ?)";
			conn = JDBCUtil.getConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, permissionDTO.getId_permission());
			pstm.setString(2, permissionDTO.getName());
			
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
	
	public int delete(PermissionDTO permissionDTO) {
		int kq1 = 0;
		try {
			String sql = "DELETE FROM tbl_permission WHERE `id_permission` = ?";
			conn = JDBCUtil.getConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, permissionDTO.getId_permission());
			
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
