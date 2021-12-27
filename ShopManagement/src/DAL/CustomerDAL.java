package DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import DTO.CustomerDTO;

public class CustomerDAL {
	private Date date = new Date();
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	private Connection conn=null;
	private PreparedStatement pstm = null;
	private ResultSet rs = null;
	
	public Vector<CustomerDTO> getCustomers() {
		Vector<CustomerDTO> listCustomer = new Vector<CustomerDTO>();
		try {
			String sql = "SELECT * FROM tbl_customer";
			conn = JDBCUtil.getConnection();
			pstm = conn.prepareStatement(sql);
			rs= pstm.executeQuery();
			while (rs.next()) {
				CustomerDTO customerDTO = new CustomerDTO(
						rs.getString("id_customer"), rs.getString("fullname"), rs.getString("email"), rs.getString("address"), rs.getString("phone"),
						rs.getDate("createdate"), rs.getInt("point")
						);
				listCustomer.add(customerDTO);
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
		return listCustomer;
	}


	public Vector<CustomerDTO> getCustomersByFilter(String filter) {
		Vector<CustomerDTO> listCustomer = new Vector<CustomerDTO>();
		try {
			String sql = "SELECT * FROM tbl_customer WHERE `id_customer` LIKE '%" + filter + "%' OR `fullname` LIKE '%" + filter + "%' OR `address` LIKE '%" + filter + "%' OR `phone` LIKE '%" + filter + "%' OR `email` LIKE '%" + filter + "%'";
			conn = JDBCUtil.getConnection();
			pstm = conn.prepareStatement(sql);
			rs= pstm.executeQuery();
			while (rs.next()) {
				CustomerDTO customerDTO = new CustomerDTO(
						rs.getString("id_customer"), rs.getString("fullname"), rs.getString("email"), rs.getString("address"), rs.getString("phone"),
						rs.getDate("createdate"), rs.getInt("point")
						);
				listCustomer.add(customerDTO);
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
		return listCustomer;
	}

	public Vector<CustomerDTO> getCustomerOfMonth(int MONTH) {
		Vector<CustomerDTO> listCustomer = new Vector<CustomerDTO>();
		try {
			String startdate = "" + (date.getYear() + 1900 ) + "-" + MONTH + "-" + "1";
            String endate = "" + (date.getYear() + 1900) + "-" + MONTH + "-" + "31";
			String sql = "SELECT * FROM tbl_customer  WHERE  `createdate` BETWEEN ? AND ? ";
			conn = JDBCUtil.getConnection();
			pstm = conn.prepareStatement(sql);
            pstm.setString(1, startdate);
            pstm.setString(2, endate);
			rs= pstm.executeQuery();
			while (rs.next()) {
				CustomerDTO customerDTO = new CustomerDTO(
						rs.getString("id_customer"), rs.getString("fullname"), rs.getString("email"), rs.getString("address"), rs.getString("phone"),
						rs.getDate("createdate"), rs.getInt("point")
						);
				listCustomer.add(customerDTO);
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
		return listCustomer;
	}
	
	public CustomerDTO getCustomerById(String id_customer) {
		CustomerDTO customerDTO = null;
		try {
			String sql = "SELECT * FROM tbl_customer WHERE  `id_customer`= ?";
			conn = JDBCUtil.getConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, id_customer);
			rs = pstm.executeQuery();
			if (rs.next()) {
				customerDTO = new CustomerDTO(
						rs.getString("id_customer"), rs.getString("fullname"), rs.getString("email"), rs.getString("address"), rs.getString("phone"),
						rs.getDate("createdate"), rs.getInt("point")
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
		return customerDTO;
	}
	
	public int insert(CustomerDTO customerDTO) {
		int kq = 0;
		try {
			String sql = "INSERT INTO tbl_customer(`id_customer`,`fullname`,`address`,`phone`,`email`, `createdate`, `point`)"
					+ " VALUES(?,?,?,?,?,?, ?)";
			conn = JDBCUtil.getConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, customerDTO.getId());
			pstm.setString(2, customerDTO.getFullname());
			pstm.setString(3, customerDTO.getAddress());
			pstm.setString(4, customerDTO.getPhone());
			pstm.setString(5, customerDTO.getEmail());
			pstm.setString(6, sdf.format(customerDTO.getCreatedate()));
			pstm.setInt(7, customerDTO.getPoint());
			kq = pstm.executeUpdate();
			
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
		return  kq;
	}
	

	public int update(CustomerDTO customerDTO) {
		int kq = 0;
		try {
			String sql = "UPDATE tbl_customer SET fullname = ?,email = ?, address = ?, phone = ?, createdate = ?,  point = ? WHERE id_customer = ?";
			conn = JDBCUtil.getConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, customerDTO.getFullname());
			pstm.setString(2, customerDTO.getEmail());
			pstm.setString(3, customerDTO.getAddress());
			pstm.setString(4, customerDTO.getPhone());
			pstm.setString(5, sdf.format(customerDTO.getCreatedate()));
			pstm.setInt(6, customerDTO.getPoint());
			pstm.setString(7, customerDTO.getId());
			
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

	public int updatePoint(CustomerDTO customerDTO) {
		int kq = 0;
		try {
			String sql = "UPDATE tbl_customer SET point = ? WHERE id_customer = ?";
			conn = JDBCUtil.getConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, customerDTO.getPoint());
			pstm.setString(2, customerDTO.getId());
			
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

	// public int delete(String id_customer) {
	// 	int kq = 0;
	// 	try {
	// 		String sql = "DELETE FROM tbl_customer WHERE `id_customer` = ?";
	// 		conn = JDBCUtil.getConnection();
	// 		pstm = conn.prepareStatement(sql);
	// 		pstm.setString(1, id_customer);
	// 		kq = pstm.executeUpdate();
	// 	} catch (Exception e) {
	// 		e.printStackTrace();
	// 	}finally {
	// 		try {
	// 			pstm.close();
	// 			conn.close();
	// 		} catch (Exception e2) {
	// 			e2.printStackTrace();
	// 		}
	// 	}
	// 	return kq;
	// }
}
