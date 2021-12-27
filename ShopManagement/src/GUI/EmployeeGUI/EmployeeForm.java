package GUI.EmployeeGUI;

import javax.swing.JPanel;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.Date;
import java.util.Vector;

import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import BLL.AccountBLL;
import BLL.EmployeeBLL;
import BLL.PositionBLL;
import DTO.AccountDTO;
import DTO.CustomerDTO;
import DTO.EmployeeDTO;
import DTO.PositionDTO;

import GUI.CategoryGUI.CategoryForm;
import javax.swing.border.LineBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.BorderLayout;

import javax.swing.JComboBox;

import javax.swing.JPasswordField;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JSeparator;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;

public class EmployeeForm extends JPanel {
	private AccountBLL accountBLL = new AccountBLL();

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	private JTextField txtIdEmployee;
	private JTextField txtEmployeeName;
	private JTextField txtBirthday;
	private JTextField txtEmail;
	private JTextField txtPhone;
	private JTextField txtAddress;
	private JTextField txtCMND;
	private JTextField txtUsername;
	private JPasswordField txtPassword;
	private JTextField txtFilter;
	private JTable tblEmployee;
	private JComboBox cboGender;
	private PositionBLL positionBLL = new PositionBLL();
	private EmployeeBLL employeeBLL = new EmployeeBLL();
	private JComboBox cboPosition;
	Vector<PositionDTO> listPosition = positionBLL.getPositions();
	private JButton btnAdd;
	private JButton btnUpdate;
	private Date date = new Date();
	private JButton btnConfirm;
	private JPanel pnOrderAction;
	private JButton btnExportExcel;
	private JButton btnFilter;
	private JRadioButton radioActive;
	private JRadioButton radioBlock;

	public EmployeeForm() {
		initComponents();
		addEvents();
		loadTable();
		loadPosition();
	}

	public void initComponents() {
		setLayout(null);
		setBounds(0, 0, 830, 463);

		JPanel pnHeader = new JPanel();
		pnHeader.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 128, 128)));
		pnHeader.setBounds(0, 0, 830, 32);
		add(pnHeader);

		JLabel lblNhnVin = new JLabel("QUẢN LÝ NHÂN VIÊN");
		lblNhnVin.setHorizontalAlignment(SwingConstants.CENTER);
		lblNhnVin.setForeground(new Color(0, 128, 128));
		lblNhnVin.setFont(new Font("Dialog", Font.BOLD, 15));
		pnHeader.add(lblNhnVin);

		JPanel pnOrder = new JPanel();
		pnOrder.setLayout(null);
		pnOrder.setBorder(
				new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Danh s\u00E1ch nh\u00E2n vi\u00EAn",
						TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 128, 128)));
		pnOrder.setBounds(400, 40, 429, 423);
		add(pnOrder);

		JPanel pnEmployeeTable = new JPanel();
		pnEmployeeTable.setBounds(12, 57, 402, 296);
		pnOrder.add(pnEmployeeTable);
		pnEmployeeTable.setLayout(new BorderLayout(0, 0));

		tblEmployee = new JTable();
		JScrollPane scrollPane = new JScrollPane(tblEmployee, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		pnEmployeeTable.add(scrollPane, BorderLayout.CENTER);

		pnOrderAction = new JPanel();
		pnOrderAction.setLayout(null);
		pnOrderAction.setBorder(new MatteBorder(2, 0, 0, 0, (Color) new Color(0, 128, 0)));
		pnOrderAction.setBounds(0, 360, 414, 63);
		pnOrder.add(pnOrderAction);

		btnUpdate = new JButton("Sửa");
		btnUpdate.setIcon(new ImageIcon(EmployeeForm.class.getResource("/images/edit-icon.png")));
		btnUpdate.setForeground(Color.WHITE);
		btnUpdate.setFont(new Font("Dialog", Font.BOLD, 13));
		btnUpdate.setBackground(new Color(0, 128, 128));
		btnUpdate.setBounds(124, 18, 96, 33);
		pnOrderAction.add(btnUpdate);

		btnAdd = new JButton("Thêm");
		btnAdd.setIcon(new ImageIcon(EmployeeForm.class.getResource("/images/add-user 24.png")));
		btnAdd.setForeground(Color.WHITE);
		btnAdd.setFont(new Font("Dialog", Font.BOLD, 13));
		btnAdd.setBackground(new Color(0, 128, 128));
		btnAdd.setBounds(12, 18, 103, 33);
		pnOrderAction.add(btnAdd);

		btnExportExcel = new JButton("Xuất Excel");
		btnExportExcel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnExportExcel.setIcon(new ImageIcon(EmployeeForm.class.getResource("/images/excel.png")));
		btnExportExcel.setForeground(Color.WHITE);
		btnExportExcel.setFont(new Font("Dialog", Font.BOLD, 12));
		btnExportExcel.setBackground(new Color(0, 128, 128));
		btnExportExcel.setBounds(263, 18, 139, 33);
		pnOrderAction.add(btnExportExcel);

		JButton btnFilterWarehouse = new JButton("");
		btnFilterWarehouse.setOpaque(false);
		btnFilterWarehouse.setForeground(Color.WHITE);
		btnFilterWarehouse.setBorderPainted(false);
		btnFilterWarehouse.setBorder(null);
		btnFilterWarehouse.setBackground(Color.WHITE);
		btnFilterWarehouse.setBounds(199, 142, 28, 26);
		pnOrder.add(btnFilterWarehouse);

		JLabel lblNewLabel_2 = new JLabel("Tìm kiếm");
		lblNewLabel_2.setForeground(new Color(0, 128, 0));
		lblNewLabel_2.setFont(new Font("Dialog", Font.BOLD, 13));
		lblNewLabel_2.setBounds(12, 25, 64, 16);
		pnOrder.add(lblNewLabel_2);

		txtFilter = new JTextField();
		txtFilter.setBounds(80, 25, 147, 20);
		pnOrder.add(txtFilter);
		txtFilter.setColumns(10);

		btnFilter = new JButton("");
		btnFilter.setIcon(new ImageIcon(EmployeeForm.class.getResource("/images/search-icon.png")));
		btnFilter.setForeground(Color.WHITE);
		btnFilter.setFont(new Font("Dialog", Font.BOLD, 13));
		btnFilter.setBackground(new Color(0, 128, 128));
		btnFilter.setBounds(234, 25, 35, 24);
		pnOrder.add(btnFilter);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Th\u00F4ng tin nh\u00E2n vi\u00EAn",
				TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 128, 0)));
		panel.setBounds(10, 40, 378, 423);
		add(panel);
		panel.setLayout(null);

		txtIdEmployee = new JTextField();
		txtIdEmployee.setOpaque(false);
		txtIdEmployee.setColumns(10);
		txtIdEmployee.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)),
				"M\u00E3 nh\u00E2n vi\u00EAn", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 139, 139)));
		txtIdEmployee.setBounds(12, 24, 188, 35);
		panel.add(txtIdEmployee);

		txtEmployeeName = new JTextField();
		txtEmployeeName.setOpaque(false);
		txtEmployeeName.setColumns(10);
		txtEmployeeName.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)),
				"T\u00EAn nh\u00E2n vi\u00EAn", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 139, 139)));
		txtEmployeeName.setBounds(12, 80, 188, 35);
		panel.add(txtEmployeeName);

		txtBirthday = new JTextField();
		txtBirthday.setOpaque(false);
		txtBirthday.setColumns(10);
		txtBirthday.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Ng\u00E0y sinh",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 139, 139)));
		txtBirthday.setBounds(222, 80, 148, 35);
		panel.add(txtBirthday);

		txtEmail = new JTextField();
		txtEmail.setOpaque(false);
		txtEmail.setColumns(10);
		txtEmail.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Email", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(0, 139, 139)));
		txtEmail.setBounds(12, 136, 188, 35);
		panel.add(txtEmail);

		txtPhone = new JTextField();
		txtPhone.setOpaque(false);
		txtPhone.setColumns(10);
		txtPhone.setBorder(
				new TitledBorder(new LineBorder(new Color(184, 207, 229)), "S\u1ED1 \u0111i\u1EC7n tho\u1EA1i",
						TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 139, 139)));
		txtPhone.setBounds(222, 136, 148, 35);
		panel.add(txtPhone);

		txtAddress = new JTextField();
		txtAddress.setOpaque(false);
		txtAddress.setColumns(10);
		txtAddress.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "\u0110\u1ECBa ch\u1EC9",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 139, 139)));
		txtAddress.setBounds(12, 190, 188, 35);
		panel.add(txtAddress);

		txtCMND = new JTextField();
		txtCMND.setOpaque(false);
		txtCMND.setColumns(10);
		txtCMND.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "CCCD / CMND",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 139, 139)));
		txtCMND.setBounds(222, 190, 148, 35);
		panel.add(txtCMND);

		cboPosition = new JComboBox();
		cboPosition.setBounds(71, 244, 124, 25);
		panel.add(cboPosition);

		JLabel lblNewLabel = new JLabel("Chức vụ");
		lblNewLabel.setForeground(new Color(0, 128, 0));
		lblNewLabel.setBounds(12, 244, 58, 25);
		panel.add(lblNewLabel);

		txtUsername = new JTextField();
		txtUsername.setOpaque(false);
		txtUsername.setColumns(10);
		txtUsername.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "T\u00E0i kho\u1EA3n",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 139, 139)));
		txtUsername.setBounds(222, 264, 148, 35);
		panel.add(txtUsername);

		//
		String[] gender = { "Nam", "Nữ" };
		cboGender = new JComboBox(gender);
		cboGender.setBounds(277, 29, 93, 25);
		panel.add(cboGender);

		JLabel lblGiiTnh = new JLabel("Giới tính");
		lblGiiTnh.setForeground(new Color(0, 128, 0));
		lblGiiTnh.setBounds(212, 30, 53, 25);
		panel.add(lblGiiTnh);

		txtPassword = new JPasswordField();
		txtPassword.setOpaque(false);
		txtPassword.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "M\u1EADt kh\u1EA9u",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 128, 128)));
		txtPassword.setBounds(222, 311, 148, 35);
		panel.add(txtPassword);

		JLabel lblImage = new JLabel("");
		lblImage.setIcon(new ImageIcon(EmployeeForm.class.getResource("/images/employee 128.png")));
		lblImage.setBounds(0, 281, 124, 130);
		panel.add(lblImage);

		JLabel lblNewLabel_1 = new JLabel("Trạng thái");
		lblNewLabel_1.setForeground(new Color(0, 128, 128));
		lblNewLabel_1.setBounds(140, 365, 69, 25);
		panel.add(lblNewLabel_1);

		radioActive = new JRadioButton("Hoạt động");
		radioActive.setForeground(new Color(0, 128, 128));
		radioActive.setBounds(221, 354, 121, 24);
		panel.add(radioActive);

		radioBlock = new JRadioButton("Khóa");
		radioBlock.setForeground(new Color(0, 128, 128));
		radioBlock.setBounds(221, 382, 121, 24);
		panel.add(radioBlock);

		JSeparator separator = new JSeparator();
		separator.setOpaque(true);
		separator.setForeground(new Color(0, 128, 0));
		separator.setBackground(new Color(0, 128, 0));
		separator.setBounds(395, 30, 2, 433);
		add(separator);

		ButtonGroup bg = new ButtonGroup();
		bg.add(radioActive);
		bg.add(radioBlock);
		radioActive.setSelected(true);
	}

	public void loadPosition() {
		for (PositionDTO positionDTO : listPosition) {
			cboPosition.addItem(positionDTO.getName());
		}
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
				Vector<EmployeeDTO> listEmployeeByFilter = employeeBLL.getEmployeesByFilter(filter);
				loadTable(listEmployeeByFilter);
			}
		});

		tblEmployee.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int row = tblEmployee.getSelectedRow();
				EmployeeDTO employeeDTO = employeeBLL.getEmployeeById(String.valueOf(tblEmployee.getValueAt(row, 0)));
				AccountDTO accountDTO = accountBLL.getAccountById(employeeDTO.getId());
				txtIdEmployee.setText(employeeDTO.getId());
				txtEmployeeName.setText(employeeDTO.getFullname());
				txtEmail.setText(employeeDTO.getEmail());
				cboGender.setSelectedItem(employeeDTO.getGender());
				txtAddress.setText(employeeDTO.getAddress());
				cboPosition.setSelectedItem(employeeDTO.getPositionDTO());
				txtBirthday.setText(String.valueOf(employeeDTO.getBirthday()));
				txtPhone.setText(employeeDTO.getPhone());
				txtCMND.setText(employeeDTO.getCmnd());
				txtUsername.setText(accountDTO.getUsename());
				if (accountDTO.getStatus() == 1) {
					radioActive.setSelected(true);
				} else {
					radioBlock.setSelected(true);
				}
			}
		});

		btnAdd.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				date = new Date();
				txtEmployeeName.setEditable(true);
				txtEmail.setEditable(true);
				txtAddress.setEditable(true);
				txtBirthday.setEditable(true);
				txtPhone.setEditable(true);
				txtCMND.setEditable(true);

				txtIdEmployee.setText("EM" + date.getTime());
				txtEmployeeName.setText("");
				txtEmail.setText("");
				txtAddress.setText("");
				txtBirthday.setText("");
				txtPhone.setText("");
				txtCMND.setText("");
				txtUsername.setText("");
				txtPassword.setText("");
				disableButtoninEmployee();
				pnOrderAction.remove(btnUpdate);

				btnConfirm = new JButton("Xác nhận");
				btnConfirm.setIcon(new ImageIcon(CategoryForm.class.getResource("/images/checkmark.png")));
				btnConfirm.setForeground(Color.WHITE);
				btnConfirm.setFont(new Font("Dialog", Font.BOLD, 12));
				btnConfirm.setBackground(new Color(0, 128, 128));
				btnConfirm.setBounds(124, 18, 120, 33);
				pnOrderAction.add(btnConfirm);
				refreshComponents();
				btnConfirm.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						try {
							String name_position = (String) cboPosition.getSelectedItem();
							String id_position = "";
							for (PositionDTO positionDTO : listPosition) {
								if (name_position.equals(positionDTO.getName())) {
									id_position = positionDTO.getId_positions();
								}
							}
							PositionDTO positionDTO = new PositionDTO(
									id_position, name_position);
							EmployeeDTO employeeDTO = new EmployeeDTO(
									txtIdEmployee.getText(),
									positionDTO,
									txtEmployeeName.getText(),
									String.valueOf(cboGender.getSelectedItem()),
									sdf.parse(txtBirthday.getText()),
									txtAddress.getText(),
									txtPhone.getText(),
									txtEmail.getText(),
									"abc",
									txtCMND.getText());
							if (employeeDTO.getFullname().isBlank() ||
									employeeDTO.getEmail().isBlank() ||
									employeeDTO.getAddress().isBlank() ||
									employeeDTO.getPositionDTO().getId_positions().isBlank() ||
									employeeDTO.getGender().isBlank() ||
									String.valueOf(employeeDTO.getBirthday()).isBlank() ||
									employeeDTO.getPhone().isBlank() ||
									employeeDTO.getCmnd().isBlank()) {
								JOptionPane.showMessageDialog(null, "Vui lòng điền đẩy đủ thông tin!");
								return;
							}

							int status = 0;
							if (radioActive.isSelected()) {
								status = 1;
							}
							AccountDTO accountDTO = new AccountDTO(
									txtIdEmployee.getText(),
									txtUsername.getText(),
									String.valueOf(txtPassword.getPassword()),
									status);
							if (accountDTO.getUsename().isBlank() || String.valueOf(accountDTO.getPassword()).isBlank()) {
								JOptionPane.showMessageDialog(null, "Vui lòng điền đẩy đủ thông tin!");
								return;
							}
							int kq = accountBLL.insert(accountDTO);
							if (kq == 1) {
								int kq1 = employeeBLL.insert(employeeDTO);
								if (kq1 == 1) {
									JOptionPane.showMessageDialog(null, "Thêm thành công!");
									txtEmployeeName.setEditable(false);
									pnOrderAction.remove(btnConfirm);
									pnOrderAction.add(btnUpdate);
									loadTable();
									refreshComponents();
									enableButtoninEmployee();
								} else if (kq1 == 2) {
									JOptionPane.showMessageDialog(null, "Vui lòng điền đẩy đủ thông tin!");
								} else {
									JOptionPane.showMessageDialog(null, "Thêm thất bại!");
								}
							}
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
							JOptionPane.showMessageDialog(null, "Ngày sinh không hợp lệ");
						}
					}
				});
			}
		});
		btnUpdate.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				String name_position = (String) cboPosition.getSelectedItem();
				String id_position = "";
				for (PositionDTO positionDTO : listPosition) {
					if (name_position.equals(positionDTO.getName())) {
						id_position = positionDTO.getId_positions();
					}
				}
				PositionDTO positionDTO = new PositionDTO(
						id_position, name_position);
				int row = tblEmployee.getSelectedRow();
				if (row < 0) {
					JOptionPane.showMessageDialog(null, "Chọn nhân viên cần thay đổi!");
					return;
				}
				String id_employee = String.valueOf(tblEmployee.getValueAt(row, 0));
				txtEmployeeName.setEditable(true);
				txtEmail.setEditable(true);
				txtAddress.setEditable(true);
				txtBirthday.setEditable(true);
				txtPhone.setEditable(true);
				txtCMND.setEditable(true);
				disableButtoninEmployee();
				pnOrderAction.remove(btnUpdate);

				btnConfirm = new JButton("Xác nhận");
				btnConfirm.setIcon(new ImageIcon(CategoryForm.class.getResource("/images/checkmark.png")));
				btnConfirm.setForeground(Color.WHITE);
				btnConfirm.setFont(new Font("Dialog", Font.BOLD, 12));
				btnConfirm.setBackground(new Color(0, 128, 128));
				btnConfirm.setBounds(124, 18, 96, 33);
				pnOrderAction.add(btnConfirm);
				refreshComponents();
				btnConfirm.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						try {
							EmployeeDTO employeeDTO = new EmployeeDTO(
									txtIdEmployee.getText(),
									positionDTO,
									txtEmployeeName.getText(),
									String.valueOf(cboGender.getSelectedItem()),
									sdf.parse(txtBirthday.getText()),
									txtAddress.getText(),
									txtPhone.getText(),
									txtEmail.getText(),
									"abc",
									txtCMND.getText());
							if (employeeDTO.getFullname().isBlank() ||
									employeeDTO.getEmail().isBlank() ||
									employeeDTO.getAddress().isBlank() ||
									employeeDTO.getPositionDTO().getId_positions().isBlank() ||
									employeeDTO.getGender().isBlank() ||
									String.valueOf(employeeDTO.getBirthday()).isBlank() ||
									employeeDTO.getPhone().isBlank() ||
									employeeDTO.getCmnd().isBlank()) {
								JOptionPane.showMessageDialog(null, "Vui lòng điền đẩy đủ thông tin!");
								return;
							}
							int kq = employeeBLL.update(employeeDTO);
							if (kq == 1) {
								AccountDTO accountDTO = new AccountDTO(txtIdEmployee.getText(), txtUsername.getText(), String.valueOf(txtPassword.getText()));
								if (accountDTO.getUsename().isBlank() || String.valueOf(accountDTO.getPassword()).isBlank()) {
									JOptionPane.showMessageDialog(null, "Vui lòng điền đẩy đủ thông tin!");
									return;
								}
								if (radioActive.isSelected()) {
									int activeAccount = accountBLL.active(accountDTO);
								} else {
									int blockAccount = accountBLL.suspend(accountDTO);
								}
								JOptionPane.showMessageDialog(null, "Sửa thành công!");
								txtEmployeeName.setEditable(false);
								pnOrderAction.remove(btnConfirm);
								pnOrderAction.add(btnUpdate);
								loadTable();
								refreshComponents();
								enableButtoninEmployee();
							} else if (kq == 2) {
								JOptionPane.showMessageDialog(null, "Vui lòng điền đẩy đủ thông tin!");
							} else if (kq == 3) {
								JOptionPane.showMessageDialog(null, "Thông tin không hợp lệ!");
							} else if (kq == 4) {
								JOptionPane.showMessageDialog(null, "Thông tin không hợp lệ!");
							} else {
								JOptionPane.showMessageDialog(null, "Sửa thất bại!");
							}
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
							JOptionPane.showMessageDialog(null, "Ngày sinh không hợp lệ");
						}
					}
				});
			}
		});

		btnExportExcel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				Vector<String> header = new Vector<String>();
				header.add("Mã nhân viên");
				header.add("Tên nhân viên");
				header.add("Email");
				header.add("Địa chỉ");
				header.add("Chức vụ");
				header.add("Giới tính");
				header.add("Ngày sinh");
				header.add("Số điện thoại");
				header.add("CMND");

				Vector<Vector<String>> listObjectData = new Vector<Vector<String>>();
				for (EmployeeDTO employeeDTO : employeeBLL.getEmployees()) {
					Vector<String> data = new Vector<String>();
					data.add(employeeDTO.getId());
					data.add(employeeDTO.getFullname());
					data.add(employeeDTO.getEmail());
					data.add(employeeDTO.getAddress());
					data.add(employeeDTO.getPositionDTO().getId_positions());
					data.add(employeeDTO.getGender());
					data.add(sdf.format(employeeDTO.getBirthday()));
					data.add(employeeDTO.getPhone());
					data.add(employeeDTO.getCmnd());
					listObjectData.add(data);
				}

				if (employeeBLL.writeExcel(listObjectData, header) == 1) {
					JOptionPane.showMessageDialog(null, "Xuất file excel thành công!");
					return;
				} else {
					JOptionPane.showMessageDialog(null, "Thất bại!");
					return;
				}
			}
		});
	}
	// loadTable

	public void loadTable() {
		DefaultTableModel dfm = new DefaultTableModel();
		String[] header = { "IDNV", "Name", "Email", "Address", "Position" };
		dfm.setColumnIdentifiers(header);
		Vector<EmployeeDTO> listEmployee = employeeBLL.getEmployees();
		for (EmployeeDTO employeeDTO : listEmployee) {
			String[] row = {
					employeeDTO.getId(), employeeDTO.getFullname(), employeeDTO.getEmail(), employeeDTO.getAddress(),
					employeeDTO.getPositionDTO().getId_positions(),
					employeeDTO.getGender(), String.valueOf(employeeDTO.getBirthday()), employeeDTO.getPhone(),
					employeeDTO.getCmnd(),
			};
			dfm.addRow(row);
		}

		tblEmployee.setModel(dfm);
	}

	public void loadTable(Vector<EmployeeDTO> listEmployeeByFilter) {
		DefaultTableModel dfm = new DefaultTableModel();
		String[] header = { "IDNV", "Name", "Email", "Address", "Position" };
		dfm.setColumnIdentifiers(header);
		for (EmployeeDTO employeeDTO : listEmployeeByFilter) {
			String[] row = {
					employeeDTO.getId(), employeeDTO.getFullname(), employeeDTO.getEmail(), employeeDTO.getAddress(),
					employeeDTO.getPositionDTO().getId_positions(),
					employeeDTO.getGender(), String.valueOf(employeeDTO.getBirthday()), employeeDTO.getPhone(),
					employeeDTO.getCmnd(),
			};
			dfm.addRow(row);
		}
		tblEmployee.setModel(dfm);
	}

	public void refreshComponents() {
		this.repaint();
		this.revalidate();
	}

	public void disableButtoninEmployee() {
		btnAdd.setEnabled(false);
		btnUpdate.setEnabled(false);
		// btnDelete.setEnabled(false);
	}

	public void enableButtoninEmployee() {
		btnAdd.setEnabled(true);
		btnUpdate.setEnabled(true);
		// btnDelete.setEnabled(true);
	}
}
