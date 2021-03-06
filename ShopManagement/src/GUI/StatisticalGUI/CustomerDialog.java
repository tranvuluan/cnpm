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
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import BLL.CustomerBLL;
import BLL.OrderBLL;
import BLL.OrderItemBLL;
import BLL.ProductBLL;
import Cores.ReadWriteFile;
import DTO.CustomerDTO;
import DTO.OrderDTO;
import DTO.OrderItemDTO;
import DTO.ProductDTO;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

import javax.swing.JSeparator;
import javax.swing.JTable;
import java.awt.BorderLayout;
import java.util.Date;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.text.SimpleDateFormat;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CustomerDialog extends JDialog {
	private ReadWriteFile readWriteFile = new ReadWriteFile();
	private Date date = new Date();
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	private CustomerBLL customerBLL = new CustomerBLL();

	private Vector<CustomerDTO> listCustomer = customerBLL.getCustomers();

	private JFreeChart chart;

	private JTable tblCustomer;
	private JPanel pnChart;
	private JButton btnStatistic;
	private JTextField txtTotalMain;
	private JLabel lblTotalMain;
	private JButton btnPoint;
	private JPanel panel;
	private JButton btnExportExcel;
	private JButton btnExportChart;

	public CustomerDialog() {
		initComponents();
		addEvents();
		loadCustomerTable(listCustomer);
		loadLineChart();
		loadTotalMain(listCustomer);
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

		btnStatistic = new JButton("L??u l?????ng kh??ch h??ng");
		btnStatistic.setBorder(new LineBorder(new Color(46, 139, 87), 1, true));
		btnStatistic.setBackground(new Color(255, 255, 255));
		btnStatistic.setForeground(new Color(46, 139, 87));
		btnStatistic.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnStatistic.setIcon(new ImageIcon(ProductDialog.class.getResource("/images/statistical/analysis (2).png")));
		btnStatistic.setBounds(10, 22, 193, 39);
		panel.add(btnStatistic);

		btnPoint = new JButton("B???c ??i???m t??ch l??y");
		btnPoint.setIcon(new ImageIcon(CustomerDialog.class.getResource("/images/statistical/analysis (2).png")));
		btnPoint.setForeground(new Color(46, 139, 87));
		btnPoint.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnPoint.setBorder(new LineBorder(new Color(46, 139, 87), 1, true));
		btnPoint.setBackground(Color.WHITE);
		btnPoint.setBounds(10, 91, 193, 39);
		panel.add(btnPoint);

		btnExportExcel = new JButton("Xu???t excel");
		btnExportExcel.setIcon(new ImageIcon(CustomerDialog.class.getResource("/images/excel.png")));
		btnExportExcel.setForeground(new Color(255, 255, 224));
		btnExportExcel.setBackground(new Color(0, 128, 128));
		btnExportExcel.setBounds(40, 327, 132, 30);
		panel.add(btnExportExcel);

		btnExportChart = new JButton("Xu???t bi???u ?????");
		btnExportChart
				.setIcon(new ImageIcon(CustomerDialog.class.getResource("/images/statistical/pie-chart (1).png")));
		btnExportChart.setForeground(new Color(255, 255, 224));
		btnExportChart.setBackground(new Color(0, 128, 128));
		btnExportChart.setBounds(40, 369, 132, 30);
		panel.add(btnExportChart);

		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(603, 0, 1, 410);
		getContentPane().add(separator);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(610, 0, 358, 355);
		getContentPane().add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));

		tblCustomer = new JTable();
		JScrollPane sc = new JScrollPane(tblCustomer, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		panel_1.add(sc, BorderLayout.CENTER);

		pnChart = new JPanel();
		pnChart.setBounds(0, 0, 604, 411);
		getContentPane().add(pnChart);
		pnChart.setLayout(new BorderLayout(0, 0));

		lblTotalMain = new JLabel("T???ng s??? kh??ch h??ng");
		lblTotalMain.setForeground(new Color(0, 100, 0));
		lblTotalMain.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTotalMain.setBounds(676, 371, 137, 16);
		getContentPane().add(lblTotalMain);

		txtTotalMain = new JTextField();
		txtTotalMain.setBounds(823, 370, 138, 20);
		getContentPane().add(txtTotalMain);
		txtTotalMain.setColumns(10);

	}

	public void addEvents() {

		btnStatistic.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				loadCustomerTable(listCustomer);
				loadLineChart();
				loadTotalMain(listCustomer);
			}
		});

		btnPoint.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				loadCustomerTable(listCustomer);
				loadBarChart();
				loadTotalMain(listCustomer);
			}
		});

		btnExportExcel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				Vector<String> header = new Vector<String>();
				header.add("M?? kh??ch h??ng");
				header.add("T??n kh??ch h??ng");
				header.add("Ng??y l???p");
				header.add("??i???m");

				Vector<Vector<String>> listObjectData = new Vector<Vector<String>>();
				for (CustomerDTO customer : listCustomer) {
					Vector<String> data = new Vector<String>();
					data.add(customer.getId());
					data.add(customer.getFullname());
					data.add(sdf.format(customer.getCreatedate()));
					data.add(String.valueOf(customer.getPoint()));
					listObjectData.add(data);
				}

				if (customerBLL.writeExcel(listObjectData, header) == 1) {
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
				if (kq == 1) {
					JOptionPane.showMessageDialog(null, "Xu???t file bi???u ????? th??nh c??ng!");
				} else {
					JOptionPane.showMessageDialog(null, "Th???t b???i!");
					return;
				}
			}
		});
	}

	public void loadCustomerTable(Vector<CustomerDTO> listCustomer) {
		DefaultTableModel dfm = new DefaultTableModel();
		String[] header = { "M?? KH", "T??n KH", "Ng??y l???p", "??i???m" };
		dfm.setColumnIdentifiers(header);

		for (CustomerDTO customer : listCustomer) {
			String[] row = {
					customer.getId(),
					customer.getFullname(),
					sdf.format(customer.getCreatedate()),
					String.valueOf(customer.getPoint())
			};
			dfm.addRow(row);
		}
		tblCustomer.setModel(dfm);
	}

	public void loadBarChart() {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		int qCus0to500 = 0;
		int qCus500to1500 = 0;
		int qCus1500to3000 = 0;
		int qCus3000to4500 = 0;
		int qCus4500toMore = 0;

		for (CustomerDTO customer : listCustomer) {
			if (customer.getPoint() < 500)
				qCus0to500++;
			else if (customer.getPoint() >= 500 && customer.getPoint() < 1500)
				qCus500to1500++;
			else if (customer.getPoint() >= 1500 && customer.getPoint() < 3000)
				qCus1500to3000++;
			else if (customer.getPoint() >= 3000 && customer.getPoint() < 4500)
				qCus3000to4500++;
			else
				qCus4500toMore++;
		}
		dataset.addValue(qCus0to500, "S??? l?????ng", "Ch??a c?? danh hi???u");
		dataset.addValue(qCus500to1500, "S??? l?????ng", "B???c");
		dataset.addValue(qCus1500to3000, "S??? l?????ng", "V??ng");
		dataset.addValue(qCus3000to4500, "S??? l?????ng", "B???ch kim");
		dataset.addValue(qCus4500toMore, "S??? l?????ng", "Kim c????ng");

		chart = ChartFactory.createBarChart("Th???ng k?? ??i???m t??ch l??y", "Danh hi???u", "S??? l?????ng",
				dataset, PlotOrientation.VERTICAL, true, true, false);

		pnChart.removeAll();
		ChartPanel chartPanel = new ChartPanel(chart);
		pnChart.add(chartPanel);
		reloadComponent();
	}

	public void loadLineChart() {
		XYDataset dataset = createDataset();

		chart = ChartFactory.createXYLineChart(
				"Th???ng k?? l?????ng kh??ch h??ng n??m " + (date.getYear() + 1900),
				"Th??ng",
				"S??? l?????ng (ng?????i)",
				dataset,
				PlotOrientation.VERTICAL,
				true,
				true,
				false);
		XYPlot xyPlot = (XYPlot) chart.getPlot();
		xyPlot.setDomainCrosshairVisible(true);
		xyPlot.setRangeCrosshairVisible(true);
		XYItemRenderer renderer = xyPlot.getRenderer();
		NumberAxis domain = (NumberAxis) xyPlot.getDomainAxis();
		// domain.setRange(0.00, 1.00);
		domain.setTickUnit(new NumberTickUnit(1));
		// domain.setVerticalTickLabels(true);
		NumberAxis range = (NumberAxis) xyPlot.getRangeAxis();
		// range.setRange(0.0, 1.0);
		range.setTickUnit(new NumberTickUnit(1));

		pnChart.removeAll();
		ChartPanel chartPanel = new ChartPanel(chart);
		pnChart.add(chartPanel, BorderLayout.CENTER);
		reloadComponent();
	}

	public XYDataset createDataset() {

		var series = new XYSeries(date.getYear() + 1900);

		for (int i = 1; i <= 12; i++) {
			series.add(i, getQuantityCustomerOfMonth(i));
		}

		var dataset = new XYSeriesCollection();
		dataset.addSeries(series);

		return dataset;
	}

	public float getQuantityCustomerOfMonth(int MONTH) {
		int quantity = customerBLL.getCustomerOfMonth(MONTH).size();
		return quantity;
	}

	public void loadTotalMain(Vector<CustomerDTO> listCustomer) {
		int total = listCustomer.size();
		txtTotalMain.setText(String.valueOf(total));
	}

	public void reloadComponent() {
		this.revalidate();
		this.repaint();
	}
}
