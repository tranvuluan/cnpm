
package GUI.SellProductGUI;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JDialog;
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
import BLL.ProductSaleBLL;
import Cores.CopyImage;
import DTO.BrandDTO;
import DTO.CategoryChildDTO;
import DTO.OrderDTO;
import DTO.OrderItemDTO;
import DTO.ProductDTO;
import DTO.ProductSaleDTO;

import javax.swing.border.LineBorder;
import javax.swing.JRadioButton;

public class ProductDialog extends JDialog {
	private ProductBLL productBLL = new ProductBLL();
	private ProductSaleBLL productSaleBLL = new ProductSaleBLL();
	private CategoryChildBLL categoryChildBLL = new CategoryChildBLL();
	private BrandBLL brandBLL = new BrandBLL();

	private Vector<ProductDTO> listProduct = productBLL.getProducts();
	private Vector<ProductSaleDTO> listProductSale = productSaleBLL.getProductSales();

	private JTextField txtId;
	private JTextField txtQuantity;
	private JTextField txtName;
	private JTextField txtCategory;
	private JTextField txtBrand;
	private JTable tblProduct;
	private JTextField txtSellPrice;
	private JRadioButton radioSale;
	private JButton btnAddToCart;
	private JLabel image;

	public ProductDialog() {
		initComponents();
		addEvents();
		loadProductTable();
	}

	public ProductDialog(Vector<String> list_id_product_filter) {
		Vector<ProductDTO> listTemp  = new Vector<ProductDTO>();
		for (ProductDTO productDTO : listProduct) {
			for(String id_filer : list_id_product_filter) {
				if (productDTO.getId_product().equals(id_filer)) {
					listTemp.add(productDTO);
				}
			}
		}
		listProduct = listTemp;
		initComponents();
		addEvents();
		loadProductTable();
	}

	public void initComponents() {
		setSize(830, 400);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		JPanel pnDetails = new JPanel();
		pnDetails.setBorder(new MatteBorder(0, 0, 0, 2, (Color) new Color(0, 128, 0)));
		pnDetails.setInheritsPopupMenu(true);
		pnDetails.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		pnDetails.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		pnDetails.setDoubleBuffered(false);
		pnDetails.setForeground(new Color(255, 255, 255));
		pnDetails.setBounds(0, 0, 386, 372);
		getContentPane().add(pnDetails);
		pnDetails.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Th\u00F4ng tin s\u1EA3n ph\u1EA9m",
				TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 128, 128)));
		panel.setBounds(2, 12, 380, 290);
		pnDetails.add(panel);
		panel.setLayout(null);

		image = new JLabel("");
		image.setHorizontalAlignment(SwingConstants.CENTER);
		image.setIcon(new ImageIcon(ProductDialog.class.getResource("/images/Nike-Shirt-17-icon.png")));
		image.setBounds(12, 36, 128, 145);
		panel.add(image);

		txtId = new JTextField();
		txtId.setOpaque(false);
		txtId.setBorder(new TitledBorder(null, "M\u00E3 s\u1EA3n ph\u1EA9m", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(0, 139, 139)));
		txtId.setBounds(157, 20, 211, 35);
		panel.add(txtId);
		txtId.setColumns(10);

		txtQuantity = new JTextField();
		txtQuantity.setOpaque(false);
		txtQuantity.setColumns(10);
		txtQuantity.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "S\u1ED1 l\u01B0\u1EE3ng",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 139, 139)));
		txtQuantity.setBounds(269, 235, 99, 35);
		panel.add(txtQuantity);

		txtName = new JTextField();
		txtName.setOpaque(false);
		txtName.setColumns(10);
		txtName.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "T\u00EAn s\u1EA3n ph\u1EA9m",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 139, 139)));
		txtName.setBounds(158, 60, 210, 35);
		panel.add(txtName);

		txtCategory = new JTextField();
		txtCategory.setOpaque(false);
		txtCategory.setColumns(10);
		txtCategory.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Danh m\u1EE5c",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 139, 139)));
		txtCategory.setBounds(158, 147, 210, 35);
		panel.add(txtCategory);

		txtBrand = new JTextField();
		txtBrand.setOpaque(false);
		txtBrand.setColumns(10);
		txtBrand.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Th\u01B0\u01A1ng hi\u1EC7u",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 139, 139)));
		txtBrand.setBounds(158, 100, 210, 35);
		panel.add(txtBrand);

		radioSale = new JRadioButton("Khuyến mãi");
		radioSale.setBounds(154, 239, 107, 24);
		panel.add(radioSale);
		radioSale.setFont(new Font("Dialog", Font.BOLD, 13));
		radioSale.setForeground(new Color(178, 34, 34));

		txtSellPrice = new JTextField();
		txtSellPrice.setOpaque(false);
		txtSellPrice.setColumns(10);
		txtSellPrice.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Gi\u00E1 b\u00E1n (\u0111)",

				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 139, 139)));
		txtSellPrice.setBounds(158, 188, 210, 35);
		panel.add(txtSellPrice);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new MatteBorder(3, 0, 0, 0, (Color) new Color(0, 128, 0)));
		panel_2.setBounds(2, 307, 380, 53);
		pnDetails.add(panel_2);
		panel_2.setLayout(null);

		btnAddToCart = new JButton("Thêm vào giỏ hàng");
		btnAddToCart.setIcon(new ImageIcon(ProductDialog.class.getResource("/images/cart.png")));
		btnAddToCart.setForeground(new Color(255, 255, 224));
		btnAddToCart.setBackground(new Color(0, 128, 128));
		btnAddToCart.setBounds(176, 12, 192, 33);
		panel_2.add(btnAddToCart);

		JPanel pnHeader = new JPanel();
		pnHeader.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 128, 128)));
		pnHeader.setBounds(0, 12, 814, 360);
		getContentPane().add(pnHeader);
		pnHeader.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(
				new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Danh s\u00E1ch s\u1EA3n ph\u1EA9m",
						TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 139, 139)));
		panel_1.setBounds(393, 0, 409, 322);
		pnHeader.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));

		tblProduct = new JTable();
		JScrollPane sc = new JScrollPane(tblProduct, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		panel_1.add(sc);
	}

	public void addEvents() {
		tblProduct.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int row = tblProduct.getSelectedRow();
				if (row > -1) {
					String id_product = String.valueOf(tblProduct.getValueAt(row, 0));
					loadProductDetail(id_product);
				}
			}
		});

		btnAddToCart.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (txtQuantity.getText().isBlank()) {
					JOptionPane.showMessageDialog(null, "Vui lòng nhập số lượng !");
					return;
				}
				float quantity = Float.valueOf(txtQuantity.getText());
				if (quantity < 1) {
					JOptionPane.showMessageDialog(null, "Số lượng phải > 0");
					return;
				} else if (quantity > Integer.valueOf((String) tblProduct.getValueAt(tblProduct.getSelectedRow(), 2))) {
					JOptionPane.showMessageDialog(null, "Số lượng vượt quá, vui lòng nhập lại!");
					return;
				}

				ProductDTO productDTO = new ProductDTO(
						txtId.getText(),
						null,
						null,
						txtName.getText(),
						Integer.valueOf(txtQuantity.getText()),
						Float.valueOf(txtSellPrice.getText()),
						"abc",
						1);

				OrderItemDTO orderItemDTO = new OrderItemDTO(
						new OrderDTO(SellProductForm.getId_order()),
						productDTO,
						Integer.valueOf(txtQuantity.getText()),
						Float.valueOf(txtSellPrice.getText()));

				int index = 0;
				boolean orderItemExist = false;
				for (OrderItemDTO orderItem : SellProductForm.getListOrderItem()) {
					if (orderItem.getProduct().getId_product().equals(orderItemDTO.getProduct().getId_product())) {
						int quantity_orderItem = orderItem.getQuantity() + orderItemDTO.getQuantity();
						if (quantity_orderItem > Integer
								.valueOf((String) tblProduct.getValueAt(tblProduct.getSelectedRow(), 2))) {
							JOptionPane.showMessageDialog(null, "Số lượng vượt quá, vui lòng nhập lại!");
							return;
						}
						orderItem.setQuantity(quantity_orderItem);
						SellProductForm.getListOrderItem().set(index, orderItem);
						orderItemExist = true;
					}
					index++;
				}
				if (orderItemExist == false) {
					SellProductForm.getListOrderItem().add(orderItemDTO);
				}
				SellProductForm.loadItemOrderTable();
			}
		});
	}

	public void loadProductTable() {
		DefaultTableModel dfm = new DefaultTableModel();
		String[] header = { "Mã SP", "Tên SP", "Số lượng", "Giá bán(đ)" };
		dfm.setColumnIdentifiers(header);
		for (ProductDTO product : listProduct) {
			if (product.getStatus() == 1 && product.getQuantity() > 0) {
				Vector<String> row = new Vector<String>();
				row.add(product.getId_product());
				row.add(product.getName());
				row.add(String.valueOf(product.getQuantity()));
				if (productSaleBLL.checkSale(product.getId_product()) == 1) {
					ProductSaleDTO productSale = productSaleBLL.getProductSaleByProductId(product.getId_product());
					float sellprice = Float.valueOf(product.getPrice())
							* (1 - Float.valueOf(productSale.getDiscountPercent()));
					row.add(String.valueOf(sellprice));
				} else {
					row.add(String.valueOf(product.getPrice()));
				}
				dfm.addRow(row);
			}
		}
		tblProduct.setModel(dfm);
	}

	public void loadProductDetail(String id_product) {
		ProductDTO productDTO = productBLL.getProductById(id_product);
		CategoryChildDTO categorychildDTO = categoryChildBLL
				.getCategoryChildById(productDTO.getCategorychild().getId_categorychild());
		BrandDTO brandDTO = brandBLL.getBrandById(productDTO.getBrand().getId_brand());
		txtId.setText(productDTO.getId_product());
		txtName.setText(productDTO.getName());
		txtBrand.setText(brandDTO.getName());
		txtCategory.setText(categorychildDTO.getName());
		image.setIcon(new ImageIcon(CopyImage.resizeImage(".\\src\\images\\product\\" + productDTO.getImage(), image)));
		if (productSaleBLL.checkSale(id_product) == 1) {
			ProductSaleDTO productSale = productSaleBLL.getProductSaleByProductId(productDTO.getId_product());
			float sellprice = Float.valueOf(productDTO.getPrice())
					* (1 - Float.valueOf(productSale.getDiscountPercent()));
			txtSellPrice.setText(String.valueOf(sellprice));
			radioSale.setSelected(true);
		} else {
			txtSellPrice.setText(String.valueOf(productDTO.getPrice()));
			radioSale.setSelected(false);
		}
	}

	public void closeDialog() {
		this.dispose();
	}
}
