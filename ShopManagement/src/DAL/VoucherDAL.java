package DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Vector;

import DTO.VoucherDTO;

public class VoucherDAL {
	private Connection conn = null;
	private PreparedStatement pstm = null;
	private ResultSet rs = null;
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	public Vector<VoucherDTO> getVouchers(){
		Vector<VoucherDTO> listVoucher = new Vector<VoucherDTO>();
		try {
			String sql = "SELECT * FROM `tbl_voucher`";
			conn = JDBCUtil.getConnection();
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			while(rs.next()) {
				VoucherDTO voucherDTO = new VoucherDTO(rs.getString("id_voucher"), 
				rs.getString("code"), 
				rs.getFloat("discountpercent"),
				 rs.getDate("startdate"), 
				 rs.getDate("enddate"));
				listVoucher.add(voucherDTO);
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
		return listVoucher;
	}
	
	public VoucherDTO getVoucherById(String id_voucher) {
		VoucherDTO voucherDTO = null;
		try {
			String sql = "SELECT * FROM tbl_voucher WHERE `id_voucher` = ?";
			conn = JDBCUtil.getConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, id_voucher);
			rs = pstm.executeQuery();
			if(rs.next()) {
				voucherDTO = new VoucherDTO(
							rs.getString("id_voucher"), rs.getString("code"), rs.getFloat("discountpercent"), rs.getDate("startdate"), rs.getDate("enddate")
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
		return voucherDTO;
	}
	
	public int insert(VoucherDTO voucherDTO) {
		int kq = 0;
		try {
			String sql = "INSERT INTO tbl_voucher(id_voucher, code, discountpercent, startdate, enddate) VALUES(?, ?, ?, ?, ?)";
			conn = JDBCUtil.getConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, voucherDTO.getId_voucher());
			pstm.setString(2, voucherDTO.getCode());
			pstm.setFloat(3, voucherDTO.getDiscountpercent());
			pstm.setString(4, sdf.format(voucherDTO.getStartdate()));
			pstm.setString(5,sdf.format(voucherDTO.getEnddate()));
			
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
	
	public int update(VoucherDTO voucherDTO) {
		int kq = 0;
		try {
			String sql = "UPDATE tbl_voucher SET code = ?, discountpercent = ?, startdate = ?, enddate = ? WHERE id_voucher = ?";
			conn = JDBCUtil.getConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, voucherDTO.getCode());
			pstm.setFloat(2, voucherDTO.getDiscountpercent());
			pstm.setString(3, sdf.format(voucherDTO.getStartdate()));
			pstm.setString(4,sdf.format(voucherDTO.getEnddate()));
			pstm.setString(5, voucherDTO.getId_voucher());
			
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
	
	public int delete(String id_voucher) {
		int kq = 0;
		try {
			String sql = "DELETE FROM tbl_voucher WHERE `id_voucher` = ?";
			conn = JDBCUtil.getConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, id_voucher);
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
}
