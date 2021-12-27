package GUI.SupplierAndBrandGUI;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;

import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import BLL.BrandBLL;
import BLL.SupplierBLL;
import DTO.BrandDTO;
import DTO.SupplierDTO;
import GUI.CategoryGUI.CategoryForm;

import java.awt.BorderLayout;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import java.awt.event.*;
import javax.swing.event.CaretListener;
import javax.swing.event.CaretEvent;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;

public class SupplierAndBrandForm extends JPanel {

	private JPanel pnOrder;
	private JLabel lblMNcc;
	private JButton btnAddSupplier;
	private JButton btnUpdateSupplier;
	private JButton btnDeleteSupplier;
	private JButton btnConfirmSupplier;
	private JButton btnConfirmBrand;
	private JTextField txtIdSupplier;
	private JTextField txtSupplierName;
	private JLabel lblTnNcc;
	private JPanel pnDetails;
	private JPanel panel;
	private JScrollPane scrollPane;
	private JPanel panel_2;
	private JTextField txtAddress;
	private JTable tblSupplier;
	
	private SupplierBLL supplierBLL = new SupplierBLL();
	private BrandBLL brandBLL = new BrandBLL();
	private Date date = new Date();
	

	public SupplierAndBrandForm() {
		initComponents();
		addEvents();
		loadTableSupplier();
		loadTableBrand();
	}

	public void initComponents() {
		setLayout(null);
		setBounds(0, 0, 830, 490);

		pnOrder = new JPanel();
		pnOrder.setBorder(new MatteBorder(0, 0, 0, 2, (Color) new Color(0, 139, 139)));
		pnOrder.setBounds(0, 40, 832, 450);
		add(pnOrder);
		pnOrder.setLayout(null);

		pnDetails = new JPanel();
		pnDetails.setBorder(new MatteBorder(2, 0, 0, 0, (Color) new Color(0, 128, 128)));
		pnDetails.setBounds(0, 399, 832, 51);
		pnOrder.add(pnDetails);
		pnDetails.setLayout(null);

		btnAddSupplier = new JButton("Thêm NCC");
		btnAddSupplier.setIcon(new ImageIcon(SupplierAndBrandForm.class.getResource("/images/manufacture.png")));
		btnAddSupplier.setForeground(new Color(255, 255, 224));
		btnAddSupplier.setBackground(new Color(0, 128, 128));
		btnAddSupplier.setBounds(25, 11, 153, 30);
		pnDetails.add(btnAddSupplier);

		btnUpdateSupplier = new JButton("Sửa NCC");
		btnUpdateSupplier.setIcon(new ImageIcon(SupplierAndBrandForm.class.getResource("/images/edit-icon.png")));

		btnUpdateSupplier.setForeground(new Color(255, 255, 224));
		btnUpdateSupplier.setBackground(new Color(0, 128, 128));
		btnUpdateSupplier.setBounds(420, 11, 165, 30);
		pnDetails.add(btnUpdateSupplier);

		btnDeleteSupplier = new JButton("Xóa NCC");
		btnDeleteSupplier.setIcon(new ImageIcon(SupplierAndBrandForm.class.getResource("/images/delete-icon.png")));
		btnDeleteSupplier.setForeground(new Color(255, 255, 224));
		btnDeleteSupplier.setBackground(new Color(0, 128, 128));
		btnDeleteSupplier.setBounds(225, 11, 153, 30);
		pnDetails.add(btnDeleteSupplier);
		

		JPanel pnAction = new JPanel();
		pnAction.setBounds(0, 0, 832, 387);
		pnOrder.add(pnAction);
		pnAction.setLayout(null);

		lblMNcc = new JLabel("Mã NCC");
		lblMNcc.setForeground(new Color(0, 100, 0));
		lblMNcc.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMNcc.setBounds(47, 13, 78, 27);
		pnAction.add(lblMNcc);

		txtIdSupplier = new JTextField();
		txtIdSupplier.setEditable(false);
		txtIdSupplier.setColumns(10);
		txtIdSupplier.setBounds(156, 13, 238, 27);
		pnAction.add(txtIdSupplier);

		txtSupplierName = new JTextField();
		txtSupplierName.setEditable(false);
		txtSupplierName.setColumns(10);
		txtSupplierName.setBounds(156, 49, 238, 27);
		pnAction.add(txtSupplierName);

		lblTnNcc = new JLabel("Tên NCC");
		lblTnNcc.setForeground(new Color(0, 100, 0));
		lblTnNcc.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTnNcc.setBounds(47, 50, 78, 27);
		pnAction.add(lblTnNcc);
		
		panel = new JPanel();
		panel.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Danh s\u00E1ch NCC", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 128, 0)));
		panel.setBounds(32, 135, 767, 252);
		pnAction.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		tblSupplier = new JTable();
		
		scrollPane = new JScrollPane(tblSupplier, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		panel.add(scrollPane);
		
		JLabel lblDiaChi = new JLabel("Địa chỉ");
		lblDiaChi.setForeground(new Color(0, 100, 0));
		lblDiaChi.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDiaChi.setBounds(47, 86, 78, 27);
		pnAction.add(lblDiaChi);
		
		txtAddress = new JTextField();
		txtAddress.setEditable(false);
		txtAddress.setColumns(10);
		txtAddress.setBounds(156, 86, 238, 27);
		pnAction.add(txtAddress);

		DefaultTableModel dtmHD = new DefaultTableModel();
		String[] headerHD = { "Mã NV", "Tên NV", "SĐT", "Chức vụ" };
		dtmHD.setColumnIdentifiers(headerHD);
		
		panel_2 = new JPanel();
		panel_2.setBorder(new MatteBorder(0, 0, 3, 0, (Color) new Color(0, 128, 128)));
		panel_2.setBounds(0, 0, 832, 40);
		add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("QUẢN LÝ NHÀ CUNG CẤP");
		lblNewLabel.setForeground(new Color(0, 100, 0));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 17));
		lblNewLabel.setBounds(313, 10, 206, 20);
		panel_2.add(lblNewLabel);


	}
	
	public void addEvents() {
		tblSupplier.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int row = tblSupplier.getSelectedRow();
				
//				txtIdSupplier.setText(String.valueOf(tblSupplier.getValueAt(row, 0)));
//				txtSupplierName.setText(String.valueOf(tblSupplier.getValueAt(row, 1)));
//				txtAddress.setText(String.valueOf(tblSupplier.getValueAt(row, 2)));
				
				SupplierDTO supplierDTO = supplierBLL.getSupplierById(String.valueOf(tblSupplier.getValueAt(row, 0)));
				txtIdSupplier.setText(supplierDTO.getId_supplier());
				txtSupplierName.setText(supplierDTO.getName());
				txtAddress.setText(supplierDTO.getAddress());
			}
		});
		
		// Xử lý tbl Supplier
		btnAddSupplier.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				date = new Date();
				txtSupplierName.setEditable(true);
				txtAddress.setEditable(true);
				
				txtIdSupplier.setText("SU" + date.getTime());
				txtSupplierName.setText("");
				txtAddress.setText("");
				
				disableButtoninSupplier();
				
				btnConfirmSupplier = new JButton("Xác nhận");
				btnConfirmSupplier.setIcon(new ImageIcon(CategoryForm.class.getResource("/images/checkmark.png")));
				btnConfirmSupplier.setForeground(new Color(255, 255, 224));
				btnConfirmSupplier.setBackground(new Color(0, 128, 128));
				btnConfirmSupplier.setBounds(630, 11, 165, 30);
				pnDetails.add(btnConfirmSupplier);
				refreshComponents();
				btnConfirmSupplier.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						try {
							SupplierDTO supplierDTO = new SupplierDTO(
									txtIdSupplier.getText(), txtSupplierName.getText(), txtAddress.getText()
									);
							int kq = supplierBLL.insert(supplierDTO);
							if(kq == 1) {
								JOptionPane.showMessageDialog(null, "Thêm thành công!");
								txtSupplierName.setEditable(false);
								pnDetails.remove(btnConfirmSupplier);
								loadTableSupplier();
								refreshComponents();
								enableButtoninSupplier();
							}else if(kq == 2){
								JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin!");
							} else {
								JOptionPane.showMessageDialog(null, "Thêm thất bại!");
							}
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
							JOptionPane.showMessageDialog(null,"Thất bại");
						}
					}
				});
				
			}
		});
		
		btnUpdateSupplier.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				
				int row = tblSupplier.getSelectedRow();
				if(row < 0) {
					return;
				}
				JOptionPane.showMessageDialog(null, "Vui lòng nhập thông tin cần sửa!");
				String id_supplier = String.valueOf(tblSupplier.getValueAt(row, 0));
				txtSupplierName.setEditable(true);
				txtAddress.setEditable(true);
				
				txtSupplierName.setText("");
				txtAddress.setText("");
	
				disableButtoninSupplier();
				
				btnConfirmSupplier = new JButton("Xác nhận");
				btnConfirmSupplier.setIcon(new ImageIcon(CategoryForm.class.getResource("/images/checkmark.png")));
				btnConfirmSupplier.setForeground(new Color(255, 255, 224));
				btnConfirmSupplier.setBackground(new Color(0, 128, 128));
				btnConfirmSupplier.setBounds(630, 11, 165, 30);
				pnDetails.add(btnConfirmSupplier);
				refreshComponents();
				
				btnConfirmSupplier.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						try {
							SupplierDTO supplierDTO = new SupplierDTO(
									txtIdSupplier.getText(), txtSupplierName.getText(), txtAddress.getText()
									);
							int kq = supplierBLL.update(supplierDTO);
							if(kq == 1) {
								JOptionPane.showMessageDialog(null, "Sửa thành công!");
								txtSupplierName.setEditable(false);
								pnDetails.remove(btnConfirmSupplier);
								loadTableSupplier();
								refreshComponents();
								enableButtoninSupplier();
							}else if(kq == 2){
								JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin!");
							} else {
								JOptionPane.showMessageDialog(null, "Sửa thất bại!");
							}
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
							JOptionPane.showMessageDialog(null,"Thất bại");
						}
					}
				});
			}
		});
		
		btnDeleteSupplier.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int row = tblSupplier.getSelectedRow();
				String id_supplier = String.valueOf(tblSupplier.getValueAt(row, 0));
				int kq = supplierBLL.delete(id_supplier);
				if(kq == 1) {
					JOptionPane.showMessageDialog(null, "Xóa thành công!");
					loadTableSupplier();
				}else {
					JOptionPane.showMessageDialog(null, "Thất bại!");
				}
			}
		});
		
	}
	
	public void refreshComponents() {
		this.repaint();
		this.revalidate();
	}
	public void disableButtoninSupplier() {
		btnAddSupplier.setEnabled(false);
		btnUpdateSupplier.setEnabled(false);
		btnDeleteSupplier.setEnabled(false);
	}
	public void enableButtoninSupplier() {
		btnAddSupplier.setEnabled(true);
		btnUpdateSupplier.setEnabled(true);
		btnDeleteSupplier.setEnabled(true);
	}

	
// Load Table Supplier
	public void loadTableSupplier() {
		DefaultTableModel dfm = new DefaultTableModel();
		String[] header = {"Mã nhà cung cấp", "Tên nhà cung cấp", "Địa chỉ"};
		dfm.setColumnIdentifiers(header);
			
		Vector<SupplierDTO> listSupplier = supplierBLL.getSuppliers();
		for(SupplierDTO supplierDTO : listSupplier) {
			String[] row = {
					supplierDTO.getId_supplier(), supplierDTO.getName(), supplierDTO.getAddress()
			};
			dfm.addRow(row);

		}
		tblSupplier.setModel(dfm);
	}
// Load Table Supplier
		public void loadTableBrand() {
			DefaultTableModel dfm = new DefaultTableModel();
			String[] header = {"Mã thương hiệu", "Tên thương hiệu"};
			dfm.setColumnIdentifiers(header);
					
			Vector<BrandDTO> listBrand = brandBLL.getBrands();
			for(BrandDTO brandDTO : listBrand) {
				String[] row = {
						brandDTO.getId_brand(), brandDTO.getName()
				};
				dfm.addRow(row);

			}
		}	
}