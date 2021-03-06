package GUI.StatisticalGUI;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import BLL.OrderItemBLL;
import BLL.ProductBLL;
import Cores.ReadWriteFile;
import DTO.CustomerDTO;
import DTO.OrderItemDTO;
import DTO.ProductDTO;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

import javax.swing.JSeparator;
import javax.swing.JTable;
import java.awt.BorderLayout;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ProductDialog extends JDialog {
	private ReadWriteFile readWriteFile = new ReadWriteFile();
	private JDatePickerImpl datePickerStart, datePickerEnd;
	private ProductBLL productBLL = new ProductBLL();
	private OrderItemBLL orderItemBLL = new OrderItemBLL();
	private String path = String.valueOf(FileSystems.getDefault().getPath(new String()).toAbsolutePath());

	private Vector<ProductDTO> listProduct = productBLL.getProducts();
	private Vector<Vector<String>> dataBarCharForInventoryOption = new Vector<Vector<String>>();
	private Vector<Vector<String>> dataBarChartForSoldOption = new Vector<Vector<String>>();

	private JFreeChart chart;

	private JTable tblProduct;
	private JPanel pnChart;
	private JButton btnSold;
	private JButton btnInventory;
	private JButton btnBestSeller;
	private JTextField txtTotalMain;
	private JLabel lblTotalMain;
	private JButton btnExportChart;
	private JPanel panel;
	private JButton btnExportExcel;

	public ProductDialog() {
		initComponents();
		addEvents();
		loadInventoryTable();
		loadBarChartInventory();
	}

	public void initComponents() {
		setSize(1200, 450);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);

		panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(971, 0, 213, 411);
		getContentPane().add(panel);
		panel.setLayout(null);

		btnInventory = new JButton("Th???ng k?? t???n kho");
		btnInventory.setBorder(new LineBorder(new Color(46, 139, 87), 1, true));
		btnInventory.setBackground(new Color(255, 255, 255));
		btnInventory.setForeground(new Color(46, 139, 87));
		btnInventory.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnInventory.setIcon(new ImageIcon(ProductDialog.class.getResource("/images/statistical/analysis (2).png")));
		btnInventory.setBounds(10, 22, 193, 39);
		panel.add(btnInventory);

		btnSold = new JButton(" Th???ng k?? ???? b??n");
		btnSold.setIcon(new ImageIcon(ProductDialog.class.getResource("/images/statistical/analysis (2).png")));
		btnSold.setForeground(new Color(46, 139, 87));
		btnSold.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnSold.setBorder(new LineBorder(new Color(46, 139, 87), 1, true));
		btnSold.setBackground(Color.WHITE);
		btnSold.setBounds(10, 85, 193, 39);
		panel.add(btnSold);

		btnBestSeller = new JButton("S???n ph???m b??n ch???y");
		btnBestSeller.setIcon(new ImageIcon(ProductDialog.class.getResource("/images/statistical/pie-chart.png")));
		btnBestSeller.setForeground(new Color(46, 139, 87));
		btnBestSeller.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnBestSeller.setBorder(new LineBorder(new Color(46, 139, 87), 1, true));
		btnBestSeller.setBackground(Color.WHITE);
		btnBestSeller.setBounds(10, 146, 193, 39);
		panel.add(btnBestSeller);
		
		btnExportChart = new JButton("Xu???t bi???u ?????");
		btnExportChart.setIcon(new ImageIcon(ProductDialog.class.getResource("/images/statistical/pie-chart (1).png")));
		btnExportChart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnExportChart.setForeground(new Color(255, 255, 224));
		btnExportChart.setBackground(new Color(0, 128, 128));
		btnExportChart.setBounds(40, 369, 132, 30);
		panel.add(btnExportChart);
		
		btnExportExcel = new JButton("Xu???t excel");
		btnExportExcel.setIcon(new ImageIcon(ProductDialog.class.getResource("/images/excel.png")));
		btnExportExcel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnExportExcel.setForeground(new Color(255, 255, 224));
		btnExportExcel.setBackground(new Color(0, 128, 128));
		btnExportExcel.setBounds(40, 327, 132, 30);
		panel.add(btnExportExcel);

		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(603, 0, 1, 410);
		getContentPane().add(separator);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(610, 0, 358, 368);
		getContentPane().add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));

		tblProduct = new JTable();
		JScrollPane sc = new JScrollPane(tblProduct, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		panel_1.add(sc, BorderLayout.CENTER);

		pnChart = new JPanel();
		pnChart.setBounds(0, 0, 604, 411);
		getContentPane().add(pnChart);
		pnChart.setLayout(new BorderLayout(0, 0));
		
		lblTotalMain = new JLabel("");
		lblTotalMain.setForeground(new Color(0, 100, 0));
		lblTotalMain.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTotalMain.setBounds(622, 380, 197, 16);
		getContentPane().add(lblTotalMain);
		
		txtTotalMain = new JTextField();
		txtTotalMain.setText("4");
		txtTotalMain.setColumns(10);
		txtTotalMain.setBounds(829, 379, 138, 20);
		getContentPane().add(txtTotalMain);
	}

	public void addEvents() {
		btnInventory.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				loadInventoryTable();
				loadBarChartInventory();
			}
		});

		btnSold.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				loadSoldTable();
				loadBarChartSold();
			}
		});

		btnBestSeller.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				loadBestSellerTable();
				loadPieChartBestSeller();
			}
		});
		
		btnExportExcel.addMouseListener( new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				Vector<String> header = new Vector<String>();
				header.add("M?? s???n ph???m");
				header.add("T??n s???n ph???m");
				header.add("S??? l?????ng t???n kho");
				header.add("S??? l?????ng ???? b??n");
				
				Vector<Vector<String>> listObjectData = new Vector<Vector<String>>();
				for (ProductDTO productDTO : listProduct) {
					Vector<String> data = new Vector<String>();
					data.add(productDTO.getId_product());
					data.add(productDTO.getName());
					data.add(String.valueOf(productDTO.getQuantity()));
					data.add(String.valueOf(getQuantitySoldOfProduct(productDTO.getId_product())));
					listObjectData.add(data);
				}
				
				if (productBLL.writeExcel(listObjectData, header) == 1) {
					JOptionPane.showMessageDialog(null, "Xu???t file excel th??nh c??ng!");
					return;
				} else {
					JOptionPane.showMessageDialog(null, "Th???t b???i!");
					return;
				}
			}
		});

		btnExportChart.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int kq = readWriteFile.writeChart(chart);
				if (kq ==1 ) {
					JOptionPane.showMessageDialog(null, "Xu???t file bi???u ????? th??nh c??ng!");
				}else {
					JOptionPane.showMessageDialog(null, "Th???t b???i!");
					return;
				}
			}
		});
		
	}

	public void loadBarChartInventory() {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();  

		for (ProductDTO product : listProduct) {
			dataset.addValue(product.getQuantity(), "S???n ph???m t???n kho", product.getName());
		}


		chart = ChartFactory.createBarChart("Th???ng k?? t???n kho", "S???n ph???m", "S??? l?????ng (c??i)",
                                                           dataset, PlotOrientation.VERTICAL, true, true, false); 


														   
		pnChart.removeAll();
		ChartPanel chartPanel = new ChartPanel(chart);
		pnChart.add(chartPanel);
		reloadComponent();
    }

	public void loadPieChartBestSeller() {
		DefaultPieDataset dataset = new DefaultPieDataset();

		int totalSold = 0;
		for (ProductDTO product : listProduct) {
			totalSold = totalSold + getQuantitySoldOfProduct(product.getId_product());
		}

		for (ProductDTO product : listProduct) {
			float value = getQuantitySoldOfProduct(product.getId_product())* 100/totalSold;
			dataset.setValue(product.getName(), value);
		}

		chart = ChartFactory.createPieChart("Th???ng k?? s???n ph???m b??n ch???y",
                dataset, true, false, false);

		pnChart.removeAll();
		ChartPanel chartPanel = new ChartPanel(chart);
		pnChart.add(chartPanel);
		reloadComponent();
    }

	public void loadBarChartSold() {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();  

		for (ProductDTO product : listProduct) {
			dataset.addValue(getQuantitySoldOfProduct(product.getId_product()), "S???n ph???m ???? b??n", product.getName());
		}

		chart = ChartFactory.createBarChart("Th???ng k?? s??? l?????ng s???n ph???m ???? b??n", "S???n ph???m", "S??? l?????ng (c??i)",
                                                           dataset, PlotOrientation.VERTICAL, true, true, false); 

		pnChart.removeAll();
		ChartPanel chartPanel = new ChartPanel(chart);
		pnChart.add(chartPanel);
		reloadComponent();
	}


	public void loadInventoryTable() {
		DefaultTableModel dfm = new DefaultTableModel();
		String[] header = { "M?? SP", "T??n SP", "S??? l?????ng t???n kho" };
		dfm.setColumnIdentifiers(header);
		int total = 0;
		for (ProductDTO productDTO : listProduct) {
			Vector<String> rowData = new Vector<String>();
			rowData.add(productDTO.getId_product());
			rowData.add(productDTO.getName());
			rowData.add(String.valueOf(productDTO.getQuantity()));
			total = total + productDTO.getQuantity();
			dfm.addRow(rowData);
			dataBarCharForInventoryOption.add(rowData);
		}
		tblProduct.setModel(dfm);

		lblTotalMain.setText("T???ng s???n ph???m t??ng kho");
		txtTotalMain.setText(String.valueOf(total));
	}

	public void loadSoldTable() {
		DefaultTableModel dfm = new DefaultTableModel();
		String[] header = { "M?? SP", "T??n SP", "S??? l?????ng ???? b??n" };
		dfm.setColumnIdentifiers(header);
		int total = 0;
		for (ProductDTO productDTO : listProduct) {
			Vector<String> rowData = new Vector<String>();
			rowData.add(productDTO.getId_product());
			rowData.add(productDTO.getName());
			rowData.add(String.valueOf(getQuantitySoldOfProduct(productDTO.getId_product())));
			total = total + getQuantitySoldOfProduct(productDTO.getId_product());
			dfm.addRow(rowData);
			dataBarCharForInventoryOption.add(rowData);
		}
		tblProduct.setModel(dfm);

		lblTotalMain.setText("T???ng s???n ph???m ???? b??n");
		txtTotalMain.setText(String.valueOf(total));
	}

	public void loadBestSellerTable() {
		DefaultTableModel dfm = new DefaultTableModel();
		String[] header = { "M?? SP", "T??n SP", "S??? l?????ng t???n kho","S??? l?????ng ???? b??n"};
		dfm.setColumnIdentifiers(header);

		for (ProductDTO productDTO : listProduct) {
			Vector<String> rowData = new Vector<String>();
			rowData.add(productDTO.getId_product());
			rowData.add(productDTO.getName());
			rowData.add(String.valueOf(productDTO.getQuantity()));
			rowData.add(String.valueOf(getQuantitySoldOfProduct(productDTO.getId_product())));
			dfm.addRow(rowData);
			dataBarCharForInventoryOption.add(rowData);
		}
		tblProduct.setModel(dfm);
	}


	public int getQuantitySoldOfProduct(String id_product) {
		Vector<OrderItemDTO> listOrderItem = orderItemBLL.getOrderItemByProductId(id_product);
		int quantity = 0;
		for (OrderItemDTO orderItem : listOrderItem) {
			quantity = quantity + orderItem.getQuantity();
		}
		return quantity;
	}


	public void reloadComponent() {
		this.revalidate();
		this.repaint();
	}
}
