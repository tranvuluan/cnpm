package GUI.WarehouseReceiptGUI;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.ImageIcon;

import java.awt.ComponentOrientation;
import java.awt.Cursor;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import BLL.BrandBLL;
import BLL.CategoryChildBLL;
import BLL.ProductBLL;
import BLL.SupplierBLL;
import BLL.WarehouseReceiptBLL;
import BLL.WarehouseReceiptDetailBLL;
import Cores.CopyImage;
import DTO.BrandDTO;
import DTO.CategoryChildDTO;
import DTO.CustomerDTO;
import DTO.EmployeeDTO;
import DTO.OrderDTO;
import DTO.OrderItemDTO;
import DTO.ProductDTO;
import DTO.SupplierDTO;
import DTO.WarehouseReceiptDTO;
import DTO.WarehouseReceiptDetailDTO;

import javax.swing.border.LineBorder;

import java.awt.event.MouseEvent;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import java.awt.event.MouseAdapter;
import javax.swing.JSeparator;

public class WarehouseReceiptForm extends JPanel{
	private WarehouseReceiptBLL warehousereceiptBLL = new WarehouseReceiptBLL();
	private WarehouseReceiptDetailBLL warehousereceiptdetailBLL = new WarehouseReceiptDetailBLL();
	private SupplierBLL supplierBLL = new SupplierBLL();
	private ProductBLL productBLL = new ProductBLL();
	private BrandBLL brandBLL = new BrandBLL();
	private CategoryChildBLL categoryChildBLL = new CategoryChildBLL();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	private Date date = new Date();


	// Get data: list warehouse receipt,...
	private Vector<WarehouseReceiptDTO> warehousereceiptList = warehousereceiptBLL.getWarehouseReceipts();
	private Vector<SupplierDTO> supplierList = supplierBLL.getSuppliers();

	private JTextField txtWareId;
	private JTextField txtTotalPrice;
	private JTextField txtEmployeeId;
	private JTextField txtDate;
	private JTable tblWarehouseReceipt;
	private JTextField txtFilterId;
	private JTable tblDetail;
	private JTextField txtProductId;
	private JTextField txtQuantity;
	private JTextField txtProductName;
	private JTextField txtPrice;
	private JTextField txtBrandName;
	private JButton btnAdd;
	private EmployeeDTO employeeDTO;
	private JComboBox<SupplierDTO> cboSupplier;
	private JTextField txtCategoryChild;
	private JButton btnExportExcel;
	private JButton btnReload;
	private JLabel image;
	
	public WarehouseReceiptForm(EmployeeDTO employeeDTO) {
		this.employeeDTO = employeeDTO;
		initComponents();
		addEvents();
		loadWarehouseReceiptTable();
	}
	

	public void initComponents() {
		setLayout(null);
		setBounds(0, 0, 830, 490);
		
		JPanel pnWarehouse = new JPanel();
		pnWarehouse.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Phi\u1EBFu nh\u1EADp", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 128, 128)));
		pnWarehouse.setBounds(380, 40, 450, 450);
		add(pnWarehouse);
		pnWarehouse.setLayout(null);
		
		JPanel pnWareInfos = new JPanel();
		pnWareInfos.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 139, 139)));
		pnWareInfos.setBounds(8, 20, 428, 114);
		pnWarehouse.add(pnWareInfos);
		pnWareInfos.setLayout(null);
		
		txtWareId = new JTextField();
		txtWareId.setEditable(false);
		txtWareId.setColumns(10);
		txtWareId.setBounds(99, 8, 140, 20);
		pnWareInfos.add(txtWareId);
		
		JLabel lblMPhiuNhp = new JLabel("Mã phiếu nhập");
		lblMPhiuNhp.setForeground(new Color(0, 100, 0));
		lblMPhiuNhp.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblMPhiuNhp.setBounds(12, 10, 88, 17);
		pnWareInfos.add(lblMPhiuNhp);
		
		JLabel lblTngTin = new JLabel("Tổng (triệu đ)");
		lblTngTin.setForeground(new Color(0, 100, 0));
		lblTngTin.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTngTin.setBounds(245, 10, 89, 17);
		pnWareInfos.add(lblTngTin);
		
		txtTotalPrice = new JTextField();
		txtTotalPrice.setEditable(false);
		txtTotalPrice.setColumns(10);
		txtTotalPrice.setBounds(338, 10, 81, 20);
		pnWareInfos.add(txtTotalPrice);
		
		JLabel lblNhCc = new JLabel("Nhà CC");
		lblNhCc.setForeground(new Color(0, 100, 0));
		lblNhCc.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNhCc.setBounds(12, 47, 72, 17);
		pnWareInfos.add(lblNhCc);
		
		cboSupplier = new JComboBox<SupplierDTO>(supplierList);
		cboSupplier.setBounds(75, 44, 125, 25);
		pnWareInfos.add(cboSupplier);
		
		JLabel lblMNhnVin = new JLabel("Mã nhân viên");
		lblMNhnVin.setForeground(new Color(0, 100, 0));
		lblMNhnVin.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblMNhnVin.setBounds(219, 47, 81, 17);
		pnWareInfos.add(lblMNhnVin);
		
		txtEmployeeId = new JTextField();
		txtEmployeeId.setEditable(false);
		txtEmployeeId.setColumns(10);
		txtEmployeeId.setBounds(306, 46, 113, 20);
		pnWareInfos.add(txtEmployeeId);
		
		JLabel lblNgyNhp = new JLabel("Ngày nhập");
		lblNgyNhp.setForeground(new Color(0, 100, 0));
		lblNgyNhp.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNgyNhp.setBounds(12, 82, 95, 17);
		pnWareInfos.add(lblNgyNhp);
		
		txtDate = new JTextField();
		txtDate.setEditable(false);
		txtDate.setColumns(10);
		txtDate.setBounds(85, 81, 125, 20);
		pnWareInfos.add(txtDate);
		
		JPanel pnWareTable = new JPanel();
		pnWareTable.setBounds(8, 146, 430, 203);
		pnWarehouse.add(pnWareTable);
		pnWareTable.setLayout(new BorderLayout(0, 0));
		
		tblWarehouseReceipt = new JTable();
		JScrollPane scrollPane = new JScrollPane(tblWarehouseReceipt, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		pnWareTable.add(scrollPane);
		
		JPanel pnWareAction = new JPanel();
		pnWareAction.setBorder(new MatteBorder(2, 0, 0, 0, (Color) new Color(0, 128, 0)));
		pnWareAction.setBounds(0, 361, 450, 89);
		pnWarehouse.add(pnWareAction);
		pnWareAction.setLayout(null);
		
		btnAdd = new JButton("Thêm phiếu nhập");
		btnAdd.setIcon(new ImageIcon(WarehouseReceiptForm.class.getResource("/images/homeicon/invoice.png")));
		btnAdd.setBounds(12, 12, 182, 26);
		btnAdd.setForeground(new Color(255, 255, 224));
		btnAdd.setBackground(new Color(0, 128, 128));
		pnWareAction.add(btnAdd);
		
		btnExportExcel = new JButton("Xuất Excel");
		btnExportExcel.setIcon(new ImageIcon(WarehouseReceiptForm.class.getResource("/images/excel.png")));
		btnExportExcel.setForeground(new Color(255, 255, 224));
		btnExportExcel.setBackground(new Color(0, 128, 128));
		btnExportExcel.setBounds(12, 51, 182, 26);
		pnWareAction.add(btnExportExcel);
		
		btnReload = new JButton("Làm mới danh sách");
		btnReload.setIcon(new ImageIcon(WarehouseReceiptForm.class.getResource("/images/reloading.png")));
		btnReload.setHorizontalAlignment(SwingConstants.LEADING);
		btnReload.setForeground(new Color(255, 255, 224));
		btnReload.setFont(new Font("Dialog", Font.BOLD, 12));
		btnReload.setBackground(new Color(0, 128, 128));
		btnReload.setBounds(227, 12, 182, 26);
		pnWareAction.add(btnReload);
		
		
		JPanel pnDetails = new JPanel();
		pnDetails.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Chi ti\u1EBFt phi\u1EBFu nh\u1EADp", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 128, 128)));
		pnDetails.setInheritsPopupMenu(true);
		pnDetails.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		pnDetails.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		pnDetails.setDoubleBuffered(false);
		pnDetails.setForeground(new Color(255, 255, 255));
		pnDetails.setBounds(0, 40, 374, 450);
		add(pnDetails);
		pnDetails.setLayout(null);
		
		JPanel pnProductTable = new JPanel();
		pnProductTable.setBorder(null);
		pnProductTable.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		pnProductTable.setBounds(7, 45, 359, 151);
		pnDetails.add(pnProductTable);
		pnProductTable.setLayout(new BorderLayout(0, 0));
		
		tblDetail = new JTable();
		JScrollPane scrollPane_1 = new JScrollPane(tblDetail, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		pnProductTable.add(scrollPane_1, BorderLayout.CENTER);
		
		
		JLabel lblTmKim = new JLabel("Tìm kiếm");
		lblTmKim.setForeground(new Color(0, 100, 0));
		lblTmKim.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTmKim.setBounds(28, 19, 68, 17);
		pnDetails.add(lblTmKim);
		
		txtFilterId = new JTextField();
		txtFilterId.setEditable(false);
		txtFilterId.setColumns(10);
		txtFilterId.setBounds(102, 18, 159, 20);
		pnDetails.add(txtFilterId);
		
		JButton btnFilter = new JButton("");
		btnFilter.setOpaque(false);
		btnFilter.setBorderPainted(false);
		btnFilter.setBorder(null);
		btnFilter.setBackground(new Color(255, 255, 255));
		btnFilter.setForeground(Color.WHITE);
		btnFilter.setIcon(new ImageIcon(WarehouseReceiptForm.class.getResource("/images/search-icon.png")));
		btnFilter.setBounds(270, 13, 28, 26);
		pnDetails.add(btnFilter);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Th\u00F4ng tin s\u1EA3n ph\u1EA9m", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 128, 128)));
		panel.setBounds(0, 205, 380, 245);
		pnDetails.add(panel);
		panel.setLayout(null);
		
		image = new JLabel("");
		image.setHorizontalAlignment(SwingConstants.CENTER);
		image.setIcon(new ImageIcon(WarehouseReceiptForm.class.getResource("/images/Nike-Shirt-17-icon.png")));
		image.setBounds(12, 36, 128, 145);
		panel.add(image);
		
		txtProductId = new JTextField();
		txtProductId.setOpaque(false);
		txtProductId.setBorder(new TitledBorder(null, "M\u00E3 s\u1EA3n ph\u1EA9m", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 139, 139)));
		txtProductId.setBounds(158, 12, 211, 35);
		panel.add(txtProductId);
		txtProductId.setColumns(10);
		
		txtQuantity = new JTextField();
		txtQuantity.setOpaque(false);
		txtQuantity.setColumns(10);
		txtQuantity.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "S\u1ED1 l\u01B0\u1EE3ng", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 139, 139)));
		txtQuantity.setBounds(297, 186, 72, 35);
		panel.add(txtQuantity);
		
		txtProductName = new JTextField();
		txtProductName.setOpaque(false);
		txtProductName.setColumns(10);
		txtProductName.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "T\u00EAn s\u1EA3n ph\u1EA9m", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 139, 139)));
		txtProductName.setBounds(159, 59, 210, 35);
		panel.add(txtProductName);
		
		txtPrice = new JTextField();
		txtPrice.setOpaque(false);
		txtPrice.setColumns(10);
		txtPrice.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Gi\u00E1 nh\u1EADp (\u0111)", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 139, 139)));
		txtPrice.setBounds(158, 186, 136, 35);
		panel.add(txtPrice);
		
		txtBrandName = new JTextField();
		txtBrandName.setOpaque(false);
		txtBrandName.setColumns(10);
		txtBrandName.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Th\u01B0\u01A1ng hi\u1EC7u", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 139, 139)));
		txtBrandName.setBounds(159, 103, 210, 35);
		panel.add(txtBrandName);
		
		txtCategoryChild = new JTextField();
		txtCategoryChild.setOpaque(false);
		txtCategoryChild.setColumns(10);
		txtCategoryChild.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Danh m\u1EE5c", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 139, 139)));
		txtCategoryChild.setBounds(159, 146, 210, 35);
		panel.add(txtCategoryChild);
		
		JPanel pnHeader = new JPanel();
		pnHeader.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 128, 128)));
		pnHeader.setBounds(0, 0, 830, 32);
		add(pnHeader);
		
		JLabel lblNewLabel = new JLabel("PHIẾU NHẬP");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(0, 128, 128));
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 15));
		pnHeader.add(lblNewLabel);
		
		JSeparator separator = new JSeparator();
		separator.setOpaque(true);
		separator.setBackground(new Color(0, 100, 0));
		separator.setBounds(377, 30, 3, 460);
		add(separator);
	}

	public void addEvents() {
		btnAdd.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				AddWarehouseReceipDialog addWarehouseDialog = new AddWarehouseReceipDialog(employeeDTO);
				addWarehouseDialog.setVisible(true);
			}
		});

		btnReload.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				warehousereceiptList = warehousereceiptBLL.getWarehouseReceipts();
				loadWarehouseReceiptTable();
			}
		});

		tblWarehouseReceipt.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int row = tblWarehouseReceipt.getSelectedRow();
				if (row > -1) {
					String id_warehousereceipt = String.valueOf(tblWarehouseReceipt.getValueAt(row, 0));
					WarehouseReceiptDTO warehouseReceiptDTO = warehousereceiptBLL.getWarehouseReceiptById(id_warehousereceipt);
					Vector<WarehouseReceiptDetailDTO> warehousereceiptdetailList = warehousereceiptdetailBLL.getWarehouseReceiptDetailByWarehouseReceiptId(id_warehousereceipt);
					txtWareId.setText(warehouseReceiptDTO.getId_warehousereceipt());
					txtTotalPrice.setText(String.valueOf(warehouseReceiptDTO.getTotalPrice()*1.0/1000000));
					txtDate.setText(String.valueOf(warehouseReceiptDTO.getDate()));
					txtEmployeeId.setText(warehouseReceiptDTO.getEmployee().getId());
					SupplierDTO sup = supplierBLL.getSupplierById(warehouseReceiptDTO.getSupplier().getId_supplier());
					cboSupplier.getModel().setSelectedItem(sup);
					loadReceiptDetailTable(warehousereceiptdetailList);
				}
				
			}
		});

		tblDetail.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int row = tblDetail.getSelectedRow();
				if (row > -1) {
					String id_product = String.valueOf(tblDetail.getValueAt(row, 1));
					ProductDTO productDTO = productBLL.getProductById(id_product);
					txtProductId.setText(productDTO.getId_product());
					txtProductName.setText(productDTO.getName());
					BrandDTO brand = brandBLL.getBrandById(productDTO.getBrand().getId_brand());
					CategoryChildDTO category = categoryChildBLL.getCategoryChildById(productDTO.getCategorychild().getId_categorychild());
					txtBrandName.setText(brand.getName());
					txtCategoryChild.setText(category.getName());
					txtQuantity.setText(String.valueOf(productDTO.getQuantity()));
					txtPrice.setText(String.valueOf(tblDetail.getValueAt(row, 3)));
					image.setIcon(new ImageIcon(CopyImage.resizeImage(".\\src\\images\\product\\" + productDTO.getImage(), image)));
				}
			}
		});
		btnExportExcel.addMouseListener( new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				Vector<String> header = new Vector<String>();
				header.add("Mã phiếu nhập");
				header.add("Mã nhà cung cấp");
				header.add("Mã nhân viên");
				header.add("Ngày nhập");
				header.add("Tổng tiền(triệu đ)");
				
				Vector<Vector<String>> listObjectData = new Vector<Vector<String>>();
				for (WarehouseReceiptDTO warehouseReceiptDTO : warehousereceiptBLL.getWarehouseReceipts()) {
					Vector<String> data = new Vector<String>();
					data.add(warehouseReceiptDTO.getId_warehousereceipt());
					data.add(warehouseReceiptDTO.getSupplier().getId_supplier());
					data.add(warehouseReceiptDTO.getEmployee().getId());
					data.add(sdf.format(warehouseReceiptDTO.getDate()));
					data.add(String.valueOf(warehouseReceiptDTO.getTotalPrice()));
					listObjectData.add(data);
				}
				
				File file = warehousereceiptBLL.writeExcel(listObjectData, header, 0);
				if (file != null) {
					for (WarehouseReceiptDTO warehouseReceiptDTO : warehousereceiptBLL.getWarehouseReceipts()) {
						Vector<WarehouseReceiptDetailDTO> listWarehouseReceiptDetailDTOs = warehousereceiptdetailBLL.getWarehouseReceiptDetailByWarehouseReceiptId(warehouseReceiptDTO.getId_warehousereceipt());
						exportDetailWarehouseReceipt(listWarehouseReceiptDetailDTOs, file);
					}
					JOptionPane.showMessageDialog(null, "Xuất file excel thành công!");
					return;
				} else {
					JOptionPane.showMessageDialog(null, "Thất bại!");
					return;
				}
			}
		});

	}
	
	// Export detail order
	public void exportDetailWarehouseReceipt(Vector<WarehouseReceiptDetailDTO> listWarehouseReceiptDetailDTOs, File file) {
		Vector<String> header = new Vector<String>();
		header.add("Mã phiếu nhập");	
		header.add("Mã sản phẩm");	
		header.add("Giá nhập (đ)");
		header.add("Số lượng");
		
		Vector<Vector<String>> listObjectData = new Vector<Vector<String>>();
		for (WarehouseReceiptDetailDTO warehouseReceiptDetailDTO : listWarehouseReceiptDetailDTOs) {
			Vector<String> data = new Vector<String>();
			data.add(warehouseReceiptDetailDTO.getWarehouseReceipt().getId_warehousereceipt());
			data.add(warehouseReceiptDetailDTO.getProduct().getId_product());
			data.add(String.valueOf(warehouseReceiptDetailDTO.getPrice()));
			data.add(String.valueOf(warehouseReceiptDetailDTO.getAmount()));
			listObjectData.add(data);
		}

		if (warehousereceiptBLL.writeExcelForDetail(listObjectData, header, file) == 0) {
			System.out.println("Loi");
		}
	}

	public void loadWarehouseReceiptTable() {
		DefaultTableModel dfm = new DefaultTableModel();
		String[] header = { "Mã PN", "Mã NCC", "Mã NV", "Ngày nhập", "Tổng (triệu đ)" };
		dfm.setColumnIdentifiers(header);
		for (WarehouseReceiptDTO warehouseReceiptDTO : warehousereceiptList) {
			String[] row = {
				warehouseReceiptDTO.getId_warehousereceipt(),
				warehouseReceiptDTO.getSupplier().getId_supplier(),
				warehouseReceiptDTO.getEmployee().getId(),
				String.valueOf(warehouseReceiptDTO.getDate()),
				String.valueOf(warehouseReceiptDTO.getTotalPrice()*1.0/1000000)
			};
			dfm.addRow(row);
		}
		tblWarehouseReceipt.setModel(dfm);
	}

	public void loadReceiptDetailTable(Vector<WarehouseReceiptDetailDTO> listWarehouseReceiptDetail) {
		DefaultTableModel dfm = new DefaultTableModel();
		String[] header = { "Mã phiếu nhập", "Mã sản phẩm", "Số lượng", "Giá nhập(đ)" };
		dfm.setColumnIdentifiers(header);
		for (WarehouseReceiptDetailDTO warehouseReceiptDetailDTO : listWarehouseReceiptDetail) {
			String[] row = {
				warehouseReceiptDetailDTO.getWarehouseReceipt().getId_warehousereceipt(),
				warehouseReceiptDetailDTO.getProduct().getId_product(),
				String.valueOf(warehouseReceiptDetailDTO.getAmount()),
				String.valueOf(warehouseReceiptDetailDTO.getPrice())
			};
			dfm.addRow(row);
		}
		tblDetail.setModel(dfm);
	}
}
