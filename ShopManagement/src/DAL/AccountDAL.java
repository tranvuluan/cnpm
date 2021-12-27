package DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import DTO.AccountDTO;

public class AccountDAL {
	private Connection conn=null;
	private PreparedStatement pstm = null;
	private ResultSet rs = null;
	
	
	public Vector<AccountDTO> getAccounts() {
		Vector<AccountDTO> listAccount = new Vector<AccountDTO>();
		try {
			String sql = "SELECT * FROM tbl_account";
			conn = JDBCUtil.getConnection();
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			while (rs.next()) {
				AccountDTO accountDTO = new AccountDTO(
						rs.getString("id_account"), rs.getString("username"), rs.getString("password")
						);
				listAccount.add(accountDTO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				pstm.close();
				rs.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			
		}
		
		return listAccount;
	}
	
	
	public AccountDTO getAccountById(String id_account) {
		AccountDTO accountDTO = null;
		try {
			String sql = "SELECT * FROM tbl_account WHERE id_account = ?";
			conn = JDBCUtil.getConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, id_account);
			rs = pstm.executeQuery();
			if (rs.next()) {
				accountDTO = new AccountDTO(
					rs.getString("id_account"), rs.getString("username"), rs.getString("password"), rs.getInt("status")
				);
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
		return accountDTO;
	}
//	
	// Return id_account (String) if login=true else return null Login = false;
	public AccountDTO login(AccountDTO account) {
		AccountDTO accountDTO = null;
		try {
			conn = JDBCUtil.getConnection();
			String sql = "SELECT * FROM tbl_account WHERE `username` = ? AND `password` = md5(?)";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, account.getUsename());
			pstm.setString(2, account.getPassword());
			rs = pstm.executeQuery();
			if (rs.next()) {
				accountDTO = new AccountDTO(
					rs.getString("id_account"), rs.getString("username"), rs.getString("password"), rs.getInt("status")
				);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				pstm.close();
				rs.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			
		}
		return accountDTO;
	}
	
	public int insert(AccountDTO account) {
		int kq = 0;
		try {
			String sql = "INSERT INTO tbl_account(`id_account`, `username`, `password`, `status`) VALUES(?, ?, md5(?), ?)";
			conn = JDBCUtil.getConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, account.getId_user());
			pstm.setString(2, account.getUsename());
			pstm.setString(3, account.getPassword());
			pstm.setInt(4, account.getStatus());
			kq = pstm.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				pstm.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return kq;
	}
	
	public int suspend(AccountDTO account) {
		int kq = 0;
		try {
			String sql = "UPDATE tbl_account SET status = 0 WHERE id_account = ?";
			conn = JDBCUtil.getConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, account.getId_user());
			kq = pstm.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				pstm.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return kq;
	}
	
	public int active(AccountDTO account) {
		int kq = 0;
		try {
			String sql = "UPDATE tbl_account SET status = 1 WHERE id_account = ?";
			conn = JDBCUtil.getConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, account.getId_user());
			kq = pstm.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				pstm.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return kq;
	}
	
	
}
