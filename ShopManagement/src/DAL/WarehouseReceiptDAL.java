package DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Vector;


import DTO.WarehouseReceiptDTO;
import DTO.EmployeeDTO;
import DTO.SupplierDTO;

public class WarehouseReceiptDAL {
	private Connection conn = null;
	private PreparedStatement pstm = null;
	private ResultSet rs = null;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	public Vector<WarehouseReceiptDTO> getWarehouseReceipts() {
		 Vector<WarehouseReceiptDTO> warehouseRecriptList = new Vector<WarehouseReceiptDTO>();
		 try {
			String sql = "SELECT * FROM tbl_warehousereceipt";
			conn = JDBCUtil.getConnection();
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			while (rs.next()) {
				WarehouseReceiptDTO  warehouseReceiptDTO = new WarehouseReceiptDTO(
						rs.getString("id_warehousereceipt"),
						new SupplierDTO(rs.getString("id_supplier")),
						new EmployeeDTO(rs.getString("id_employee")),
						rs.getDate("date"),
						rs.getFloat("totalprice")
						);
				warehouseRecriptList.add(warehouseReceiptDTO);
				
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
		 return warehouseRecriptList;
	}

	public WarehouseReceiptDTO getWarehouseReceiptById(String  id_warehousereceipt) {
		WarehouseReceiptDTO warehouseReceiptDTO = null;
		try {
			String sql = "SELECT * FROM tbl_warehousereceipt where id_warehousereceipt = ?";
			conn = JDBCUtil.getConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, id_warehousereceipt);
			rs = pstm.executeQuery();
			if (rs.next()) {
				warehouseReceiptDTO = new WarehouseReceiptDTO(
						rs.getString("id_warehousereceipt"),
						new SupplierDTO(rs.getString("id_supplier")),
						new EmployeeDTO(rs.getString("id_employee")),
						rs.getDate("date"),
						rs.getFloat("totalprice")
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
		return warehouseReceiptDTO;
    }
	
	public int insert(WarehouseReceiptDTO warehouseReceiptDTO) {
		int kq = 0;
		try {
			String sql = "INSERT INTO `tbl_warehousereceipt`(`id_warehousereceipt`, `id_supplier`, `id_employee`, `date`, `totalprice`) VALUES (?,?,?,?,?)";
			conn = JDBCUtil.getConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, warehouseReceiptDTO.getId_warehousereceipt());
			pstm.setString(2, warehouseReceiptDTO.getSupplier().getId_supplier());
			pstm.setString(3, warehouseReceiptDTO.getEmployee().getId());
			pstm.setString(4, sdf.format(warehouseReceiptDTO.getDate()));
			pstm.setFloat(5, warehouseReceiptDTO.getTotalPrice());
			kq = pstm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pstm.close();
				conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return kq;
	}

	public int update (WarehouseReceiptDTO warehouseReceiptDTO) {
		int kq = 0;
		try {
			String sql = "UPDATE `tbl_warehousereceipt` SET `id_supplier`= ? ,`id_employee`= ?,`date`= ? ,`totalprice`= ? WHERE `id_warehousereceipt` = ?";
			conn = JDBCUtil.getConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, warehouseReceiptDTO.getId_warehousereceipt());
			pstm.setString(2, warehouseReceiptDTO.getSupplier().getId_supplier());
			pstm.setString(3, warehouseReceiptDTO.getEmployee().getId());
			pstm.setString(4, sdf.format(warehouseReceiptDTO.getDate()));
			pstm.setFloat(5, warehouseReceiptDTO.getTotalPrice());
			pstm.setString(5, warehouseReceiptDTO.getId_warehousereceipt());
			kq = pstm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pstm.close();
				conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return kq;
	}


	public int delete (String id_warehousereceipt) {
		int kq = 0;
		try {
			String sql = "DELETE FROM `tbl_warehousereceipt` where `id_warehousereceipt` = ?";
			conn = JDBCUtil.getConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, id_warehousereceipt);
			
			kq = pstm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
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
