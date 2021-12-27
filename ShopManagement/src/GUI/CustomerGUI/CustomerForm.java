package GUI.CustomerGUI;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JTable;

import javax.swing.table.DefaultTableModel;

import BLL.CustomerBLL;
import DTO.CustomerDTO;
import GUI.CategoryGUI.CategoryForm;

import javax.swing.JScrollPane;

import javax.swing.border.TitledBorder;

public class CustomerForm extends JPanel {
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	private CustomerBLL customerBLL = new CustomerBLL();
	private Date date = new Date();

	private JPanel pnOptions;
	private JPanel pnOrder;
	private JTable tblDsKhachHang;
	private JLabel lblMNv;
	private JLabel lblSinThoi;
	private JLabel lblaCh;
	private JLabel lblNgyLm;
	private JLabel lblChcV;
	private JButton btnAddCus;
	private JButton btnUpdateCus;
	private JTextField txtMaKH;
	private JTextField txtSDT;
	private JTextField txtDiaChi;
	private JTextField txtNgaytao;
	private JTextField txtEmail;
	private JTextField txtTenKH;
	private JLabel lblTnNv;
	private JPanel pnDetails;
	private JButton btnExportExcel;
	private JButton btnFilter;
	private JTextField txtFilter;
	private JTextField txtPoint;
	private JButton btnConfirm;

	public CustomerForm() {
		initComponents();
		addEvents();
		loadTable();
	}

	public void initComponents() {
		setLayout(null);
		setBounds(0, 0, 830, 490);

		pnOrder = new JPanel();
		pnOrder.setBorder(new MatteBorder(0, 0, 0, 2, (Color) new Color(0, 139, 139)));
		pnOrder.setBounds(0, 38, 332, 452);
		add(pnOrder);
		pnOrder.setLayout(null);

		pnDetails = new JPanel();
		pnDetails.setBorder(new MatteBorder(2, 0, 0, 0, (Color) new Color(0, 128, 128)));
		pnDetails.setBounds(0, 374, 329, 78);
		pnOrder.add(pnDetails);
		pnDetails.setLayout(null);

		btnAddCus = new JButton("Thên KH");
		btnAddCus.setIcon(new ImageIcon(CustomerForm.class.getResource("/images/follow.png")));
		btnAddCus.setForeground(new Color(255, 255, 224));
		btnAddCus.setBackground(new Color(0, 128, 128));
		btnAddCus.setBounds(26, 7, 124, 30);
		pnDetails.add(btnAddCus);

		btnUpdateCus = new JButton("Sửa KH");
		btnUpdateCus.setIcon(new ImageIcon(CustomerForm.class.getResource("/images/edit.png")));

		btnUpdateCus.setForeground(new Color(255, 255, 224));
		btnUpdateCus.setBackground(new Color(0, 128, 128));
		btnUpdateCus.setBounds(181, 7, 124, 30);
		pnDetails.add(btnUpdateCus);

		JPanel pnAction = new JPanel();
		pnAction.setBorder(new TitledBorder(null, "Th\u00F4ng tin kh\u00E1ch h\u00E0ng", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(0, 128, 0)));
		pnAction.setBounds(0, 12, 329, 358);
		pnOrder.add(pnAction);
		pnAction.setLayout(null);

		lblMNv = new JLabel("Mã KH");
		lblMNv.setForeground(new Color(0, 100, 0));
		lblMNv.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblMNv.setBounds(25, 35, 45, 27);
		pnAction.add(lblMNv);

		txtMaKH = new JTextField();
		txtMaKH.setEditable(false);
		txtMaKH.setColumns(10);
		txtMaKH.setBounds(100, 35, 217, 27);
		pnAction.add(txtMaKH);

		lblSinThoi = new JLabel("SĐT");
		lblSinThoi.setForeground(new Color(0, 100, 0));
		lblSinThoi.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblSinThoi.setBounds(25, 215, 53, 27);
		pnAction.add(lblSinThoi);

		txtSDT = new JTextField();
		txtSDT.setEditable(false);
		txtSDT.setColumns(10);
		txtSDT.setBounds(100, 215, 217, 29);
		pnAction.add(txtSDT);

		lblaCh = new JLabel("Địa chỉ");
		lblaCh.setForeground(new Color(0, 100, 0));
		lblaCh.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblaCh.setBounds(25, 170, 63, 27);
		pnAction.add(lblaCh);

		txtDiaChi = new JTextField();
		txtDiaChi.setEditable(false);
		txtDiaChi.setColumns(10);
		txtDiaChi.setBounds(100, 170, 217, 26);
		pnAction.add(txtDiaChi);

		lblNgyLm = new JLabel("Ngày tạo");
		lblNgyLm.setForeground(new Color(0, 100, 0));
		lblNgyLm.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNgyLm.setBounds(25, 260, 63, 27);
		pnAction.add(lblNgyLm);

		txtNgaytao = new JTextField();
		txtNgaytao.setEditable(false);
		txtNgaytao.setColumns(10);
		txtNgaytao.setBounds(100, 260, 217, 26);
		pnAction.add(txtNgaytao);

		lblChcV = new JLabel("Email");
		lblChcV.setForeground(new Color(0, 100, 0));
		lblChcV.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblChcV.setBounds(25, 125, 53, 27);
		pnAction.add(lblChcV);

		txtEmail = new JTextField();
		txtEmail.setEditable(false);
		txtEmail.setColumns(10);
		txtEmail.setBounds(100, 125, 217, 26);
		pnAction.add(txtEmail);

		txtTenKH = new JTextField();
		txtTenKH.setEditable(false);
		txtTenKH.setColumns(10);
		txtTenKH.setBounds(100, 80, 217, 27);
		pnAction.add(txtTenKH);

		lblTnNv = new JLabel("Tên KH");
		lblTnNv.setForeground(new Color(0, 100, 0));
		lblTnNv.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTnNv.setBounds(25, 80, 53, 27);
		pnAction.add(lblTnNv);

		JLabel lblim = new JLabel("Điểm");
		lblim.setForeground(new Color(0, 100, 0));
		lblim.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblim.setBounds(25, 305, 53, 27);
		pnAction.add(lblim);

		txtPoint = new JTextField();
		txtPoint.setEditable(false);
		txtPoint.setColumns(10);
		txtPoint.setBounds(100, 305, 217, 26);
		pnAction.add(txtPoint);

		pnOptions = new JPanel();
		pnOptions.setBorder(new TitledBorder(null, "Danh s\u00E1ch kh\u00E1ch h\u00E0ng", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(0, 128, 0)));
		pnOptions.setBounds(335, 48, 495, 442);
		add(pnOptions);

		DefaultTableModel dtmHD = new DefaultTableModel();
		String[] headerHD = { "Mã NV", "Tên NV", "SĐT", "Chức vụ" };
		dtmHD.setColumnIdentifiers(headerHD);
		pnOptions.setLayout(null);
		tblDsKhachHang = new JTable();

		tblDsKhachHang.setBounds(0, 0, 486, 300);
		tblDsKhachHang.setModel(dtmHD);
		JScrollPane sc1 = new JScrollPane(tblDsKhachHang, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		sc1.setBounds(12, 30, 478, 300);
		pnOptions.add(sc1);

		JPanel panel = new JPanel();
		panel.setBorder(new MatteBorder(2, 0, 0, 0, (Color) new Color(0, 128, 0)));
		panel.setBounds(0, 365, 495, 77);
		pnOptions.add(panel);
		panel.setLayout(null);

		btnFilter = new JButton("LỌC");
		btnFilter.setIcon(new ImageIcon(CustomerForm.class.getResource("/images/search-icon.png")));
		btnFilter.setBounds(220, 23, 102, 30);
		btnFilter.setForeground(new Color(255, 255, 224));
		btnFilter.setBackground(new Color(0, 128, 128));
		panel.add(btnFilter);

		txtFilter = new JTextField();
		txtFilter.setBounds(10, 25, 198, 26);
		panel.add(txtFilter);
		txtFilter.setColumns(10);

		btnExportExcel = new JButton("Xuất Excel");
		btnExportExcel.setIcon(new ImageIcon(CustomerForm.class.getResource("/images/excel.png")));
		btnExportExcel.setBounds(348, 23, 122, 30);
		panel.add(btnExportExcel);

		btnExportExcel.setForeground(new Color(255, 255, 224));
		btnExportExcel.setBackground(new Color(0, 128, 128));

		JPanel pnHeader = new JPanel();
		pnHeader.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 128, 128)));
		pnHeader.setBounds(0, 0, 830, 38);
		add(pnHeader);

		JLabel lblQunLKhch = new JLabel("QUẢN LÝ KHÁCH HÀNG");
		lblQunLKhch.setHorizontalAlignment(SwingConstants.CENTER);
		lblQunLKhch.setForeground(new Color(0, 128, 128));
		lblQunLKhch.setFont(new Font("Dialog", Font.BOLD, 15));
		pnHeader.add(lblQunLKhch);

	}

	public void addEvents() {
		btnFilter.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (txtFilter.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Vui lòng nhập điều kiện lọc!");
					return;
				}
				String filter = txtFilter.getText();
				filter = filter.replaceAll("'", "");
				Vector<CustomerDTO> listCustomerByFilter = customerBLL.getCustomersByFilter(filter);
				loadTable(listCustomerByFilter);
			}
		});

		tblDsKhachHang.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int row = tblDsKhachHang.getSelectedRow();
				CustomerDTO customerDTO = customerBLL
						.getCustomerById(String.valueOf(tblDsKhachHang.getValueAt(row, 0)));
				txtMaKH.setText(customerDTO.getId());
				txtTenKH.setText(customerDTO.getFullname());
				txtEmail.setText(customerDTO.getEmail());
				txtSDT.setText(customerDTO.getPhone());
				txtDiaChi.setText(customerDTO.getAddress());
				txtNgaytao.setText(String.valueOf(customerDTO.getCreatedate()));
				txtPoint.setText(String.valueOf(customerDTO.getPoint()));
			}
		});

		btnAddCus.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				date = new Date();
				txtTenKH.setEditable(true);
				// txtNgaySinh.setEditable(true);
				txtSDT.setEditable(true);
				txtDiaChi.setEditable(true);
				txtNgaytao.setEditable(true);
				txtEmail.setEditable(true);
				txtPoint.setEditable(false);

				txtMaKH.setText("CU" + date.getTime());
				txtTenKH.setText("");
				// txtNgaySinh.setText("");
				txtSDT.setText("");
				txtDiaChi.setText("");
				txtNgaytao.setText(sdf.format(date));
				txtEmail.setText("");
				txtPoint.setText("0");

				disableButtoninCustomer();

				btnConfirm = new JButton("Xác nhận");
				btnConfirm.setIcon(new ImageIcon(CategoryForm.class.getResource("/images/checkmark.png")));
				btnConfirm.setForeground(new Color(255, 255, 224));
				btnConfirm.setBackground(new Color(0, 128, 128));
				btnConfirm.setBounds(181, 42, 124, 30);
				pnDetails.add(btnConfirm);
				refreshComponents();
				btnConfirm.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						try {
							CustomerDTO customerDTO = new CustomerDTO(
									txtMaKH.getText(), txtTenKH.getText(), txtEmail.getText(), txtDiaChi.getText(),
									txtSDT.getText(), sdf.parse(txtNgaytao.getText()),
									Integer.valueOf(txtPoint.getText()));
							int kq = customerBLL.insert(customerDTO);
							if (kq == 1) {
								JOptionPane.showMessageDialog(null, "Thêm thành công!");
								txtTenKH.setEditable(false);
								pnDetails.remove(btnConfirm);
								loadTable();
								refreshComponents();
								enableButtoninCustomer();
							} else if (kq == 2) {
								JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin!");
							} else if (kq == 4) {
								JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin!");
							} else {
								JOptionPane.showMessageDialog(null, "Thêm thất bại!");
							}
						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
							JOptionPane.showMessageDialog(null, "Ngày tạo không hợp lệ!");
						}
					}
				});

			}
		});

		btnUpdateCus.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int row = tblDsKhachHang.getSelectedRow();
				if (row < 0) {
					JOptionPane.showMessageDialog(null, "Chọn khách hàng cần thay đổi");
					return;
				}
				String id_voucher = String.valueOf(tblDsKhachHang.getValueAt(row, 0));
				txtTenKH.setEditable(true);
				txtSDT.setEditable(true);
				txtDiaChi.setEditable(true);
				txtNgaytao.setEditable(true);
				txtEmail.setEditable(true);
				txtPoint.setEditable(false);

				txtTenKH.setText("");
				// txtNgaySinh.setText("");
				txtSDT.setText("");
				txtDiaChi.setText("");
				txtNgaytao.setText("");
				txtEmail.setText("");
				// txtPoint.setText("");

				disableButtoninCustomer();

				btnConfirm = new JButton("Xác nhận");
				btnConfirm.setIcon(new ImageIcon(CategoryForm.class.getResource("/images/checkmark.png")));
				btnConfirm.setForeground(new Color(255, 255, 224));
				btnConfirm.setBackground(new Color(0, 128, 128));
				btnConfirm.setBounds(181, 42, 124, 30);
				pnDetails.add(btnConfirm);
				refreshComponents();

				btnConfirm.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						try {
							CustomerDTO customerDTO = new CustomerDTO(
									txtMaKH.getText(),
									txtTenKH.getText(),
									txtEmail.getText(),
									txtDiaChi.getText(),
									txtSDT.getText(),
									sdf.parse(txtNgaytao.getText()),
									Integer.valueOf(txtPoint.getText()));
							int kq = customerBLL.update(customerDTO);
							if (kq == 1) {
								JOptionPane.showMessageDialog(null, "Sửa thành công!");
								txtTenKH.setEditable(false);
								pnDetails.remove(btnConfirm);
								loadTable();
								refreshComponents();
								enableButtoninCustomer();
							} else if (kq == 2) {
								JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin!");
							} else if (kq == 4) {
								JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin!");
							} else {
								JOptionPane.showMessageDialog(null, "Sửa thất bại!");
							}
						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
							JOptionPane.showMessageDialog(null, "Ngày tạo không hợp lệ!");
						}
					}
				});
			}
		});

		// btnDeleteCus.addMouseListener(new MouseAdapter() {
		// public void mouseClicked(MouseEvent e) {
		// int row = tblDsKhachHang.getSelectedRow();
		// String id_voucher = String.valueOf(tblDsKhachHang.getValueAt(row, 0));
		// int kq = customerBLL.delete(id_voucher);
		// if(kq == 1) {
		// JOptionPane.showMessageDialog(null, "Xóa thành công!");
		// loadTable();
		// }else {
		// JOptionPane.showMessageDialog(null, "Thất bại!");
		// }
		// }
		// });

		btnExportExcel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				Vector<String> header = new Vector<String>();
				header.add("Mã khách hàng");
				header.add("Tên khách hàng");
				header.add("Ngày tạo");
				header.add("Điểm");

				Vector<Vector<String>> listObjectData = new Vector<Vector<String>>();
				for (CustomerDTO customer : customerBLL.getCustomers()) {
					Vector<String> data = new Vector<String>();
					data.add(customer.getId());
					data.add(customer.getFullname());
					data.add(sdf.format(customer.getCreatedate()));
					data.add(String.valueOf(customer.getPoint()));
					listObjectData.add(data);
				}

				if (customerBLL.writeExcel(listObjectData, header) == 1) {
					JOptionPane.showMessageDialog(null, "Xuất file excel thành công!");
					return;
				} else {
					JOptionPane.showMessageDialog(null, "Thất bại!");
					return;
				}
			}
		});

	}

	public void refreshComponents() {
		this.repaint();
		this.revalidate();
	}

	public void disableButtoninCustomer() {
		btnAddCus.setEnabled(false);
		btnUpdateCus.setEnabled(false);
		// btnDeleteCus.setEnabled(false);
	}

	public void enableButtoninCustomer() {
		btnAddCus.setEnabled(true);
		btnUpdateCus.setEnabled(true);
		// btnDeleteCus.setEnabled(true);
	}

	// Load Table
	public void loadTable() {
		DefaultTableModel dfm = new DefaultTableModel();
		String[] header = { "Mã KH", "Tên KH", "Email", "Điểm" };
		dfm.setColumnIdentifiers(header);
		Vector<CustomerDTO> listCustomer = customerBLL.getCustomers();
		for (CustomerDTO customerDTO : listCustomer) {
			String[] row = {
					customerDTO.getId(),
					customerDTO.getFullname(),
					customerDTO.getEmail(),
					String.valueOf(customerDTO.getPoint())
			};
			dfm.addRow(row);

		}
		tblDsKhachHang.setModel(dfm);
	}

	public void loadTable(Vector<CustomerDTO> listCustomerByFilter) {
		DefaultTableModel dfm = new DefaultTableModel();
		String[] header = { "Mã KH", "Tên KH", "Email", "Điểm" };
		dfm.setColumnIdentifiers(header);
		for (CustomerDTO customerDTO : listCustomerByFilter) {
			String[] row = {
					customerDTO.getId(),
					customerDTO.getFullname(),
					customerDTO.getEmail(),
					String.valueOf(customerDTO.getPoint())
			};
			dfm.addRow(row);

		}
		tblDsKhachHang.setModel(dfm);
	}

}
