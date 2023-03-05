import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.MessageFormat;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

public class POS_System {

	private JFrame frame;
	private JTable table;
	private JTextField textField_barcode;
	private JTextField tf_subTotal;
	private JTextField tf_tax;
	private JTextField tf_discount;
	private JTextField tf_total;
	private JTextField tf_cash;
	private JTextField tf_change;
	JPanel category_panel = new JPanel();
	JPanel snacks_panel = new JPanel();
	JPanel clothes_panel = new JPanel();
	JComboBox jcboPayment = new JComboBox();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					POS_System window = new POS_System();
//					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public POS_System() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void itemCost() {
		double sum = 0;
		double tax = 3.9;
		double discount = 2;

		for (int i = 0; i < table.getRowCount(); i++) {
			sum = sum + (Double.parseDouble(table.getValueAt(i, 3).toString()) * Double.parseDouble(table.getValueAt(i, 2).toString()));
		}
		tf_subTotal.setText(Double.toString(sum));
		double cTotal = Double.parseDouble(tf_subTotal.getText());

		double cTax = (cTotal * tax) / 100;
		String iTaxTotal = String.format("$ %.2f", cTax);
		tf_tax.setText(iTaxTotal);

		String iSubTotal = String.format("$ %.2f", cTotal);
		tf_subTotal.setText(iSubTotal);

		double cDiscount = (cTotal * discount) / 100;
		String iDiscount = String.format("$ %.2f", cDiscount);
		tf_discount.setText(iDiscount);

		String iTotal = String.format("$ %.2f", cTotal + cTax - cDiscount);
		tf_total.setText(iTotal);
		textField_barcode.setText(iSubTotal + iTaxTotal + iDiscount + iTotal);
	}

//	function change

	public void Change() {
		double sum = 0;
		double tax = 3.9;
		double cash = Double.parseDouble(tf_cash.getText());
		double discount = 2;

		for (int i = 0; i < table.getRowCount(); i++) {
			sum = sum + (Double.parseDouble(table.getValueAt(i, 3).toString()) * Double.parseDouble(table.getValueAt(i, 2).toString()));
		}

		double cTax = (tax * sum) / 100;
		double cDiscount = (discount * sum) / 100;
		double cTotal = sum + cTax - cDiscount;
		double cChange = (cash - (sum + cTax - cDiscount));
		String ChangeGiven = String.format("$ %.2f", cChange);
		tf_change.setText(ChangeGiven);
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.addRow(new Object[] { "Sub Total: " , String.format("$ %.2f", sum), "Total: " , String.format("$ %.2f", cTotal)});
		model.addRow(new Object[] { "Discount: " , String.format("$ %.2f", cDiscount), "Tax: " , String.format("$ %.2f", cTax)});
		model.addRow(new Object[] { "Cash: " , String.format("$ %.2f", cash), "Change: " , String.format("$ %.2f", cChange)});
	}
	
	

	private void initialize(){
		frame = new JFrame();
		frame.setBounds(0, 0, 1450, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JPanel beverages_panel = new JPanel();
		beverages_panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		beverages_panel.setBounds(373, 106, 900, 521);
		frame.getContentPane().add(beverages_panel);

		JButton b1 = new JButton("");
		b1.setIcon(new ImageIcon("C:\\Users\\SO KIMLANG\\eclipse-workspace\\POS_System\\src\\POS\\b1.jpg"));
		b1.setBounds(10, 11, 137, 90);
		
		b1.addActionListener(new ActionListener() {
			
			int qty = 1;
			public void actionPerformed(ActionEvent e) {
				double PriceOfItem = 1.25;
				String id = "001";
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				// Qty 
				if(table.getRowCount() == 0) {
					qty =1;
				}
				else {
					for(int i=0; i< table.getRowCount();i++) {
						if(table.getValueAt(i, 0) == id) {
							int Oqty = (int) table.getModel().getValueAt(i, 2);
							Oqty ++;
							model.removeRow(i);
							qty = Oqty;
						}
					};
				}
				
				model.addRow(new Object[] { id, "Sprite White", qty , PriceOfItem });
				itemCost();
			}
		});
		beverages_panel.setLayout(null);
		beverages_panel.add(b1);

		JButton b13 = new JButton("");
		b13.setIcon(new ImageIcon("C:\\Users\\SO KIMLANG\\eclipse-workspace\\POS_System\\src\\POS\\b13.jpg"));
		b13.setBounds(10, 212, 137, 90);
		b13.addActionListener(new ActionListener() {
			int qty =1;
			public void actionPerformed(ActionEvent e) {
				double PriceOfItem = 2.75;
				String id = "013";
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				// Qty 
				if(table.getRowCount() == 0) {
					qty =1;
				}
				else {
					for(int i=0; i< table.getRowCount();i++) {
						if(table.getValueAt(i, 0) == id) {
							int Oqty = (int) table.getModel().getValueAt(i, 2);
							Oqty ++;
							model.removeRow(i);
							qty = Oqty;
						}
					};
				}
				model.addRow(new Object[] {id, "Milk Orange Flavor", qty, PriceOfItem });
				itemCost();
			}
		});
		beverages_panel.setLayout(null);
		beverages_panel.add(b13);

		JButton b7 = new JButton("");
		b7.setIcon(new ImageIcon("C:\\Users\\SO KIMLANG\\eclipse-workspace\\POS_System\\src\\POS\\b7.jpg"));
		b7.setBounds(10, 111, 137, 90);
		b7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double PriceOfItem = 0.75;
				String id = "007";
				int qty = 1;
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				// Qty 
				if(table.getRowCount() == 0) {
					qty =1;
				}
				else {
					for(int i=0; i< table.getRowCount();i++) {
						if(table.getValueAt(i, 0) == id) {
							int Oqty = (int) table.getModel().getValueAt(i, 2);
							Oqty ++;
							model.removeRow(i);
							qty = Oqty;
						}
					};
				}
				model.addRow(new Object[] {id, "Pepsi Cola", qty, PriceOfItem });
				itemCost();
			}
		});
		beverages_panel.setLayout(null);
		beverages_panel.add(b7);

		JButton b19 = new JButton("");
		b19.setIcon(new ImageIcon("C:\\Users\\SO KIMLANG\\eclipse-workspace\\POS_System\\src\\POS\\b19.jpg"));
		b19.setBounds(10, 313, 137, 90);
		b19.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double PriceOfItem = 0.80;
				String id = "019";
				int qty = 1;
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				// Qty 
				if(table.getRowCount() == 0) {
					qty =1;
				}
				else {
					for(int i=0; i< table.getRowCount();i++) {
						if(table.getValueAt(i, 0) == id) {
							int Oqty = (int) table.getModel().getValueAt(i, 2);
							Oqty ++;
							model.removeRow(i);
							qty = Oqty;
						}
					};
				}
				model.addRow(new Object[] {id, "Soda", qty, PriceOfItem });
				itemCost();
			}
		});
		beverages_panel.setLayout(null);
		beverages_panel.add(b19);

		JButton b25 = new JButton("");
		b25.setIcon(new ImageIcon("C:\\Users\\SO KIMLANG\\eclipse-workspace\\POS_System\\src\\POS\\b25.jpg"));
		b25.setBounds(10, 414, 137, 90);
		b25.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double PriceOfItem = 5.75;
				int qty = 1;
				String id = "025";
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				if(table.getRowCount() == 0) {
					qty =1;
				}
				else {
					for(int i=0; i< table.getRowCount();i++) {
						if(table.getValueAt(i, 0) == id) {
							int Oqty = (int) table.getModel().getValueAt(i, 2);
							Oqty ++;
							model.removeRow(i);
							qty = Oqty;
						}
					};
				}
				model.addRow(new Object[] { id,"Grapes Wine", qty, PriceOfItem });
				itemCost();
			}
		});
		beverages_panel.setLayout(null);
		beverages_panel.add(b25);

		JButton b2 = new JButton("");
		b2.setIcon(new ImageIcon("C:\\Users\\SO KIMLANG\\eclipse-workspace\\POS_System\\src\\POS\\b2.jpg"));
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double PriceOfItem = 1.25;
				int qty = 1;
				String id = "002";
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				if(table.getRowCount() == 0) {
					qty =1;
				}
				else {
					for(int i=0; i< table.getRowCount();i++) {
						if(table.getValueAt(i, 0) == id) {
							int Oqty = (int) table.getModel().getValueAt(i, 2);
							Oqty ++;
							model.removeRow(i);
							qty = Oqty;
						}
					};
				}
				model.addRow(new Object[] {id, "Sprite Green", qty, PriceOfItem });
				itemCost();
			}
		});
		beverages_panel.setLayout(null);
		b2.setBounds(157, 11, 137, 90);
		beverages_panel.add(b2);

		JButton b8 = new JButton("");
		b8.setIcon(new ImageIcon("C:\\Users\\SO KIMLANG\\eclipse-workspace\\POS_System\\src\\POS\\b8.jpg"));
		b8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double PriceOfItem = 2.75;
				int qty = 1;
				String id = "008";
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				if(table.getRowCount() == 0) {
					qty =1;
				}
				else {
					for(int i=0; i< table.getRowCount();i++) {
						if(table.getValueAt(i, 0) == id) {
							int Oqty = (int) table.getModel().getValueAt(i, 2);
							Oqty ++;
							model.removeRow(i);
							qty = Oqty;
						}
					};
				}
				model.addRow(new Object[] {id, "Milk Green Apple Flavor", qty, PriceOfItem });
				itemCost();
			}
		});
		beverages_panel.setLayout(null);
		b8.setBounds(157, 111, 137, 90);
		beverages_panel.add(b8);

		JButton b14 = new JButton("");
		b14.setIcon(new ImageIcon("C:\\Users\\SO KIMLANG\\eclipse-workspace\\POS_System\\src\\POS\\b14.jpg"));
		b14.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double PriceOfItem = 2.55;
				int qty = 1;
				String id = "014";
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				if(table.getRowCount() == 0) {
					qty =1;
				}
				else {
					for(int i=0; i< table.getRowCount();i++) {
						if(table.getValueAt(i, 0) == id) {
							int Oqty = (int) table.getModel().getValueAt(i, 2);
							Oqty ++;
							model.removeRow(i);
							qty = Oqty;
						}
					};
				}
				model.addRow(new Object[] { id,"Almond Fresh Milk", qty, PriceOfItem });
				itemCost();
			}
		});
		beverages_panel.setLayout(null);
		b14.setBounds(157, 212, 137, 90);
		beverages_panel.add(b14);

		JButton b20 = new JButton("");
		b20.setIcon(new ImageIcon("C:\\Users\\SO KIMLANG\\eclipse-workspace\\POS_System\\src\\POS\\b20.jpg"));
		b20.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double PriceOfItem = 1.75;
				String id = "020";
				int qty = 1;
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				if(table.getRowCount() == 0) {
					qty =1;
				}
				else {
					for(int i=0; i< table.getRowCount();i++) {
						if(table.getValueAt(i, 0) == id) {
							int Oqty = (int) table.getModel().getValueAt(i, 2);
							Oqty ++;
							model.removeRow(i);
							qty = Oqty;
						}
					};
				}
				model.addRow(new Object[] {id, "Korean Milk", qty, PriceOfItem });
				itemCost();
			}
		});
		beverages_panel.setLayout(null);
		b20.setBounds(157, 313, 137, 90);
		beverages_panel.add(b20);

		JButton b26 = new JButton("");
		b26.setIcon(new ImageIcon("C:\\Users\\SO KIMLANG\\eclipse-workspace\\POS_System\\src\\POS\\b26.jpg"));
		b26.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double PriceOfItem = 1.85;
				int qty = 1;
				String id = "026";
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				if(table.getRowCount() == 0) {
					qty =1;
				}
				else {
					for(int i=0; i< table.getRowCount();i++) {
						if(table.getValueAt(i, 0) == id) {
							int Oqty = (int) table.getModel().getValueAt(i, 2);
							Oqty ++;
							model.removeRow(i);
							qty = Oqty;
						}
					};
				}
				model.addRow(new Object[] {id, "Heineken", qty, PriceOfItem });
				itemCost();
			}
		});
		beverages_panel.setLayout(null);
		b26.setBounds(157, 414, 137, 90);
		beverages_panel.add(b26);

		JButton b3 = new JButton("");
		b3.setIcon(new ImageIcon("C:\\Users\\SO KIMLANG\\eclipse-workspace\\POS_System\\src\\POS\\b3.jpg"));
		b3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double PriceOfItem = 0.60;
				int qty = 1;
				String id = "003";
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				if(table.getRowCount() == 0) {
					qty =1;
				}
				else {
					for(int i=0; i< table.getRowCount();i++) {
						if(table.getValueAt(i, 0) == id) {
							int Oqty = (int) table.getModel().getValueAt(i, 2);
							Oqty ++;
							model.removeRow(i);
							qty = Oqty;
						}
					};
				}
				model.addRow(new Object[] {id, "Coca Cola", qty, PriceOfItem });
				itemCost();
			}
		});
		beverages_panel.setLayout(null);
		b3.setBounds(304, 11, 137, 90);
		beverages_panel.add(b3);

		JButton b9 = new JButton("");
		b9.setIcon(new ImageIcon("C:\\Users\\SO KIMLANG\\eclipse-workspace\\POS_System\\src\\POS\\b9.jpg"));
		b9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double PriceOfItem = 1.85;
				int qty = 1;
				String id = "009";
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				if(table.getRowCount() == 0) {
					qty =1;
				}
				else {
					for(int i=0; i< table.getRowCount();i++) {
						if(table.getValueAt(i, 0) == id) {
							int Oqty = (int) table.getModel().getValueAt(i, 2);
							Oqty ++;
							model.removeRow(i);
							qty = Oqty;
						}
					};
				}
				model.addRow(new Object[] {id, "Palm Drinks", qty, PriceOfItem });
				itemCost();
			}
		});
		beverages_panel.setLayout(null);
		b9.setBounds(304, 111, 137, 90);
		beverages_panel.add(b9);

		JButton b15 = new JButton("");
		b15.setIcon(new ImageIcon("C:\\Users\\SO KIMLANG\\eclipse-workspace\\POS_System\\src\\POS\\b15.jpg"));
		b15.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double PriceOfItem = 1.85;
				int qty = 1;
				String id = "015";
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				if(table.getRowCount() == 0) {
					qty =1;
				}
				else {
					for(int i=0; i< table.getRowCount();i++) {
						if(table.getValueAt(i, 0) == id) {
							int Oqty = (int) table.getModel().getValueAt(i, 2);
							Oqty ++;
							model.removeRow(i);
							qty = Oqty;
						}
					};
				}
				model.addRow(new Object[] {id, "Silk Almond Milk", qty, PriceOfItem });
				itemCost();
			}
		});
		beverages_panel.setLayout(null);
		b15.setBounds(304, 212, 137, 90);
		beverages_panel.add(b15);

		JButton b21 = new JButton("");
		b21.setIcon(new ImageIcon("C:\\Users\\SO KIMLANG\\eclipse-workspace\\POS_System\\src\\POS\\b21.jpg"));
		b21.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double PriceOfItem = 1.95;
				String id = "021";
				int qty = 1;
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				if(table.getRowCount() == 0) {
					qty =1;
				}
				else {
					for(int i=0; i< table.getRowCount();i++) {
						if(table.getValueAt(i, 0) == id) {
							int Oqty = (int) table.getModel().getValueAt(i, 2);
							Oqty ++;
							model.removeRow(i);
							qty = Oqty;
						}
					};
				}
				model.addRow(new Object[] { id,"Strawberry Jinro Soju", qty, PriceOfItem });
				itemCost();
			}
		});
		beverages_panel.setLayout(null);
		b21.setBounds(304, 313, 137, 90);
		beverages_panel.add(b21);

		JButton b27 = new JButton("");
		b27.setIcon(new ImageIcon("C:\\Users\\SO KIMLANG\\eclipse-workspace\\POS_System\\src\\POS\\b27.jpg"));
		b27.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double PriceOfItem = 2.05;
				String id = "027";
				int qty = 1;
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				if(table.getRowCount() == 0) {
					qty =1;
				}
				else {
					for(int i=0; i< table.getRowCount();i++) {
						if(table.getValueAt(i, 0) == id) {
							int Oqty = (int) table.getModel().getValueAt(i, 2);
							Oqty ++;
							model.removeRow(i);
							qty = Oqty;
						}
					};
				}
				model.addRow(new Object[] {id, "Hoegaarden Original Flavor", qty, PriceOfItem });
				itemCost();
			}
		});
		beverages_panel.setLayout(null);
		b27.setBounds(304, 414, 137, 90);
		beverages_panel.add(b27);

		JButton b4 = new JButton("");
		b4.setIcon(new ImageIcon("C:\\Users\\SO KIMLANG\\eclipse-workspace\\POS_System\\src\\POS\\b4.jpg"));
		b4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double PriceOfItem = 0.85;
				String id = "004";
				int qty = 1;
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				if(table.getRowCount() == 0) {
					qty =1;
				}
				else {
					for(int i=0; i< table.getRowCount();i++) {
						if(table.getValueAt(i, 0) == id) {
							int Oqty = (int) table.getModel().getValueAt(i, 2);
							Oqty ++;
							model.removeRow(i);
							qty = Oqty;
						}
					};
				}
				model.addRow(new Object[] {id, "Black Pepsi Cola", qty, PriceOfItem });
				itemCost();
			}
		});
		beverages_panel.setLayout(null);
		b4.setBounds(451, 11, 137, 90);
		beverages_panel.add(b4);

		JButton b10 = new JButton("");
		b10.setIcon(new ImageIcon("C:\\Users\\SO KIMLANG\\eclipse-workspace\\POS_System\\src\\POS\\b10.jpg"));
		b10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double PriceOfItem = 2.35;
				String id = "010";
				int qty = 1;
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				if(table.getRowCount() == 0) {
					qty =1;
				}
				else {
					for(int i=0; i< table.getRowCount();i++) {
						if(table.getValueAt(i, 0) == id) {
							int Oqty = (int) table.getModel().getValueAt(i, 2);
							Oqty ++;
							model.removeRow(i);
							qty = Oqty;
						}
					};
				}
				model.addRow(new Object[] {id, "Orange Juice", qty, PriceOfItem });
				itemCost();
			}
		});
		beverages_panel.setLayout(null);
		b10.setBounds(451, 111, 137, 90);
		beverages_panel.add(b10);

		JButton b16 = new JButton("");
		b16.setIcon(new ImageIcon("C:\\Users\\SO KIMLANG\\eclipse-workspace\\POS_System\\src\\POS\\b16.jpg"));
		b16.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double PriceOfItem = 1.85;
				String id = "016";
				int qty = 1;
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				if(table.getRowCount() == 0) {
					qty =1;
				}
				else {
					for(int i=0; i< table.getRowCount();i++) {
						if(table.getValueAt(i, 0) == id) {
							int Oqty = (int) table.getModel().getValueAt(i, 2);
							Oqty ++;
							model.removeRow(i);
							qty = Oqty;
						}
					};
				}
				model.addRow(new Object[] { id,"CowHead Fresh Milk", qty, PriceOfItem });
				itemCost();
			}
		});
		beverages_panel.setLayout(null);
		b16.setBounds(451, 212, 137, 90);
		beverages_panel.add(b16);

		JButton b22 = new JButton("");
		b22.setIcon(new ImageIcon("C:\\Users\\SO KIMLANG\\eclipse-workspace\\POS_System\\src\\POS\\b22.jpg"));
		b22.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double PriceOfItem = 1.85;
				String id = "022";
				int qty = 1;
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				if(table.getRowCount() == 0) {
					qty =1;
				}
				else {
					for(int i=0; i< table.getRowCount();i++) {
						if(table.getValueAt(i, 0) == id) {
							int Oqty = (int) table.getModel().getValueAt(i, 2);
							Oqty ++;
							model.removeRow(i);
							qty = Oqty;
						}
					};
				}
				model.addRow(new Object[] {id, "Jinro Soju Original Flavor", qty, PriceOfItem });
				itemCost();
			}
		});
		beverages_panel.setLayout(null);
		b22.setBounds(451, 313, 137, 90);
		beverages_panel.add(b22);

		JButton b28 = new JButton("");
		b28.setIcon(new ImageIcon("C:\\Users\\SO KIMLANG\\eclipse-workspace\\POS_System\\src\\POS\\b28.jpg"));
		b28.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double PriceOfItem = 2.85;
				String id = "028";
				int qty = 1;
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				if(table.getRowCount() == 0) {
					qty =1;
				}
				else {
					for(int i=0; i< table.getRowCount();i++) {
						if(table.getValueAt(i, 0) == id) {
							int Oqty = (int) table.getModel().getValueAt(i, 2);
							Oqty ++;
							model.removeRow(i);
							qty = Oqty;
						}
					};
				}
				model.addRow(new Object[] {id, "Hoegaarden Strawberry Flavor", qty, PriceOfItem });
				itemCost();
			}
		});
		beverages_panel.setLayout(null);
		b28.setBounds(451, 414, 137, 90);
		beverages_panel.add(b28);

		JButton b5 = new JButton("");
		b5.setIcon(new ImageIcon("C:\\Users\\SO KIMLANG\\eclipse-workspace\\POS_System\\src\\POS\\b5.jpg"));
		b5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double PriceOfItem = 1.85;
				int qty = 1;
				String id = "005";
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				if(table.getRowCount() == 0) {
					qty =1;
				}
				else {
					for(int i=0; i< table.getRowCount();i++) {
						if(table.getValueAt(i, 0) == id) {
							int Oqty = (int) table.getModel().getValueAt(i, 2);
							Oqty ++;
							model.removeRow(i);
							qty = Oqty;
						}
					};
				}
				model.addRow(new Object[] {id, "Apple juice", qty, PriceOfItem });
				itemCost();
			}
		});
		beverages_panel.setLayout(null);
		b5.setBounds(600, 11, 137, 90);
		beverages_panel.add(b5);

		JButton b11 = new JButton("");
		b11.setIcon(new ImageIcon("C:\\Users\\SO KIMLANG\\eclipse-workspace\\POS_System\\src\\POS\\b11.jpg"));
		b11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double PriceOfItem = 1.55;
				int qty = 1;
				String id = "011";
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				if(table.getRowCount() == 0) {
					qty =1;
				}
				else {
					for(int i=0; i< table.getRowCount();i++) {
						if(table.getValueAt(i, 0) == id) {
							int Oqty = (int) table.getModel().getValueAt(i, 2);
							Oqty ++;
							model.removeRow(i);
							qty = Oqty;
						}
					};
				}
				model.addRow(new Object[] {id, "Almond Fresh Milk No Sugar", qty, PriceOfItem });
				itemCost();
			}
		});
		beverages_panel.setLayout(null);
		b11.setBounds(600, 111, 137, 90);
		beverages_panel.add(b11);

		JButton b17 = new JButton("");
		b17.setIcon(new ImageIcon("C:\\Users\\SO KIMLANG\\eclipse-workspace\\POS_System\\src\\POS\\b17.jpg"));
		b17.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double PriceOfItem = 1.85;
				int qty = 1;
				String id = "017";
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				if(table.getRowCount() == 0) {
					qty =1;
				}
				else {
					for(int i=0; i< table.getRowCount();i++) {
						if(table.getValueAt(i, 0) == id) {
							int Oqty = (int) table.getModel().getValueAt(i, 2);
							Oqty ++;
							model.removeRow(i);
							qty = Oqty;
						}
					};
				}
				model.addRow(new Object[] {id, "Meiji Fresh Milk", qty, PriceOfItem });
				itemCost();
			}
		});
		beverages_panel.setLayout(null);
		b17.setBounds(600, 212, 137, 90);
		beverages_panel.add(b17);

		JButton b23 = new JButton("");
		b23.setIcon(new ImageIcon("C:\\Users\\SO KIMLANG\\eclipse-workspace\\POS_System\\src\\POS\\b23.jpg"));
		b23.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double PriceOfItem = 1.25;
				int qty = 1;
				String id = "023";
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				if(table.getRowCount() == 0) {
					qty =1;
				}
				else {
					for(int i=0; i< table.getRowCount();i++) {
						if(table.getValueAt(i, 0) == id) {
							int Oqty = (int) table.getModel().getValueAt(i, 2);
							Oqty ++;
							model.removeRow(i);
							qty = Oqty;
						}
					};
				}
				model.addRow(new Object[] {id, "Banana Milk Korean", qty, PriceOfItem });
				itemCost();
			}
		});
		beverages_panel.setLayout(null);
		b23.setBounds(600, 313, 137, 90);
		beverages_panel.add(b23);

		JButton b29 = new JButton("");
		b29.setIcon(new ImageIcon("C:\\Users\\SO KIMLANG\\eclipse-workspace\\POS_System\\src\\POS\\b29.jpg"));
		b29.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double PriceOfItem = 1.85;
				int qty = 1;
				String id = "029";
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				if(table.getRowCount() == 0) {
					qty =1;
				}
				else {
					for(int i=0; i< table.getRowCount();i++) {
						if(table.getValueAt(i, 0) == id) {
							int Oqty = (int) table.getModel().getValueAt(i, 2);
							Oqty ++;
							model.removeRow(i);
							qty = Oqty;
						}
					};
				}
				model.addRow(new Object[] {id, "Budwiser's Original Flavor", qty, PriceOfItem });
				itemCost();
			}
		});
		beverages_panel.setLayout(null);
		b29.setBounds(600, 414, 137, 90);
		beverages_panel.add(b29);

		JButton b6 = new JButton("");
		b6.setIcon(new ImageIcon("C:\\Users\\SO KIMLANG\\eclipse-workspace\\POS_System\\src\\POS\\b6.jpg"));
		b6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double PriceOfItem = 1.85;
				int qty = 1;
				String id = "006";
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				if(table.getRowCount() == 0) {
					qty =1;
				}
				else {
					for(int i=0; i< table.getRowCount();i++) {
						if(table.getValueAt(i, 0) == id) {
							int Oqty = (int) table.getModel().getValueAt(i, 2);
							Oqty ++;
							model.removeRow(i);
							qty = Oqty;
						}
					};
				}
				model.addRow(new Object[] {id, "Apple Milky Juice", qty, PriceOfItem });
				itemCost();
			}
		});
		beverages_panel.setLayout(null);
		b6.setBounds(747, 11, 137, 90);
		beverages_panel.add(b6);

		JButton b12 = new JButton("");
		b12.setIcon(new ImageIcon("C:\\Users\\SO KIMLANG\\eclipse-workspace\\POS_System\\src\\POS\\b12.jpg"));
		b12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double PriceOfItem = 1.85;
				int qty = 1;
				String id = "012";
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				if(table.getRowCount() == 0) {
					qty =1;
				}
				else {
					for(int i=0; i< table.getRowCount();i++) {
						if(table.getValueAt(i, 0) == id) {
							int Oqty = (int) table.getModel().getValueAt(i, 2);
							Oqty ++;
							model.removeRow(i);
							qty = Oqty;
						}
					};
				}
				model.addRow(new Object[] {id, "Mango Milk", qty, PriceOfItem });
				itemCost();
			}
		});
		beverages_panel.setLayout(null);
		b12.setBounds(747, 111, 137, 90);
		beverages_panel.add(b12);

		JButton b18 = new JButton("");
		b18.setIcon(new ImageIcon("C:\\Users\\SO KIMLANG\\eclipse-workspace\\POS_System\\src\\POS\\b18.jpg"));
		b18.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double PriceOfItem = 3.85;
				int qty = 1;
				String id = "018";
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				if(table.getRowCount() == 0) {
					qty =1;
				}
				else {
					for(int i=0; i< table.getRowCount();i++) {
						if(table.getValueAt(i, 0) == id) {
							int Oqty = (int) table.getModel().getValueAt(i, 2);
							Oqty ++;
							model.removeRow(i);
							qty = Oqty;
						}
					};
				}
				model.addRow(new Object[] {id, "Meiji Original Milk Flavor", qty, PriceOfItem });
				itemCost();
			}
		});
		beverages_panel.setLayout(null);
		b18.setBounds(747, 212, 137, 90);
		beverages_panel.add(b18);

		JButton b24 = new JButton("");
		b24.setIcon(new ImageIcon("C:\\Users\\SO KIMLANG\\eclipse-workspace\\POS_System\\src\\POS\\b24.jpg"));
		b24.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double PriceOfItem = 6.85;
				int qty = 1;
				String id = "024";
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				if(table.getRowCount() == 0) {
					qty =1;
				}
				else {
					for(int i=0; i< table.getRowCount();i++) {
						if(table.getValueAt(i, 0) == id) {
							int Oqty = (int) table.getModel().getValueAt(i, 2);
							Oqty ++;
							model.removeRow(i);
							qty = Oqty;
						}
					};
				}
				model.addRow(new Object[] {id, "Rondonal Wine", qty, PriceOfItem });
				itemCost();
			}
		});
		beverages_panel.setLayout(null);
		b24.setBounds(747, 313, 137, 90);
		beverages_panel.add(b24);

		JButton b30 = new JButton("");
		b30.setIcon(new ImageIcon("C:\\Users\\SO KIMLANG\\eclipse-workspace\\POS_System\\src\\POS\\b30.jpg"));
		b30.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double PriceOfItem = 2.85;
				int qty = 1;
				String id = "030";
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				if(table.getRowCount() == 0) {
					qty =1;
				}
				else {
					for(int i=0; i< table.getRowCount();i++) {
						if(table.getValueAt(i, 0) == id) {
							int Oqty = (int) table.getModel().getValueAt(i, 2);
							Oqty ++;
							model.removeRow(i);
							qty = Oqty;
						}
					};
				}
				model.addRow(new Object[] {id, "Hoegaarden Orange Flavor", qty, PriceOfItem });
				itemCost();
			}
		});
		beverages_panel.setLayout(null);
		b30.setBounds(747, 414, 137, 90);
		beverages_panel.add(b30);
		beverages_panel.setVisible(true);// beverages
		beverages_panel.setVisible(true);// beverages

		// clothes

		clothes_panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		clothes_panel.setBounds(373, 106, 900, 521);
		frame.getContentPane().add(clothes_panel);

		JButton c1 = new JButton("");
		c1.setIcon(new ImageIcon("C:\\Users\\SO KIMLANG\\eclipse-workspace\\POS_System\\src\\POS\\c1.jpg"));
		// Tshirt.setIcon(new ImageIcon(""));
		c1.setBounds(10, 11, 137, 90);
		c1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double PriceOfItem = 5.57;
				int qty = 1;
				String id = "c001";
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				if(table.getRowCount() == 0) {
					qty =1;
				}
				else {
					for(int i=0; i< table.getRowCount();i++) {
						if(table.getValueAt(i, 0) == id) {
							int Oqty = (int) table.getModel().getValueAt(i, 2);
							Oqty ++;
							model.removeRow(i);
							qty = Oqty;
						}
					};
				}
				model.addRow(new Object[] {id, "White Baby Clothes", qty, PriceOfItem });
				itemCost();
			}
		});
		clothes_panel.setLayout(null);
		c1.setFont(new Font("Tahoma", Font.BOLD, 48));
		clothes_panel.add(c1);

		JButton c7 = new JButton("");
		c7.setIcon(new ImageIcon("C:\\Users\\SO KIMLANG\\eclipse-workspace\\POS_System\\src\\POS\\c7.jpg"));
		c7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double PriceOfItem = 5.57;
				int qty = 1;
				String id = "c007";
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				if(table.getRowCount() == 0) {
					qty =1;
				}
				else {
					for(int i=0; i< table.getRowCount();i++) {
						if(table.getValueAt(i, 0) == id) {
							int Oqty = (int) table.getModel().getValueAt(i, 2);
							Oqty ++;
							model.removeRow(i);
							qty = Oqty;
						}
					};
				}
				model.addRow(new Object[] {id, "Orange Baby Clothes", qty, PriceOfItem });
				itemCost();
			}
		});
		clothes_panel.setLayout(null);
		c7.setBounds(10, 112, 137, 90);
		clothes_panel.add(c7);

		JButton c13 = new JButton("");
		c13.setIcon(new ImageIcon("C:\\Users\\SO KIMLANG\\eclipse-workspace\\POS_System\\src\\POS\\c13.jpg"));
		c13.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double PriceOfItem = 5.57;
				int qty = 1;
				String id = "c013";
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				if(table.getRowCount() == 0) {
					qty =1;
				}
				else {
					for(int i=0; i< table.getRowCount();i++) {
						if(table.getValueAt(i, 0) == id) {
							int Oqty = (int) table.getModel().getValueAt(i, 2);
							Oqty ++;
							model.removeRow(i);
							qty = Oqty;
						}
					};
				}
				model.addRow(new Object[] {id, "White Baby Clothes", qty, PriceOfItem });
				itemCost();
			}
		});
		clothes_panel.setLayout(null);
		c13.setBounds(10, 213, 137, 90);
		clothes_panel.add(c13);

		JButton c19 = new JButton("");
		c19.setIcon(new ImageIcon("C:\\Users\\SO KIMLANG\\eclipse-workspace\\POS_System\\src\\POS\\c19.jpg"));
		c19.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double PriceOfItem = 5.57;
				int qty = 1;
				String id = "c019";
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				if(table.getRowCount() == 0) {
					qty =1;
				}
				else {
					for(int i=0; i< table.getRowCount();i++) {
						if(table.getValueAt(i, 0) == id) {
							int Oqty = (int) table.getModel().getValueAt(i, 2);
							Oqty ++;
							model.removeRow(i);
							qty = Oqty;
						}
					};
				}
				model.addRow(new Object[] {id, "Cute Colthes for baby", qty, PriceOfItem });
				itemCost();
			}
		});
		clothes_panel.setLayout(null);
		c19.setBounds(10, 314, 137, 90);
		clothes_panel.add(c19);

		JButton c25 = new JButton("");
		c25.setIcon(new ImageIcon("C:\\Users\\SO KIMLANG\\eclipse-workspace\\POS_System\\src\\POS\\c25.jpg"));
		c25.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double PriceOfItem = 8.57;
				String id = "c025";
				int qty = 1;
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				if(table.getRowCount() == 0) {
					qty =1;
				}
				else {
					for(int i=0; i< table.getRowCount();i++) {
						if(table.getValueAt(i, 0) == id) {
							int Oqty = (int) table.getModel().getValueAt(i, 2);
							Oqty ++;
							model.removeRow(i);
							qty = Oqty;
						}
					};
				}
				model.addRow(new Object[] {id, "white T-shirt and Jeans", qty, PriceOfItem });
				itemCost();
			}
		});
		clothes_panel.setLayout(null);
		c25.setBounds(10, 415, 137, 90);
		clothes_panel.add(c25);

		JButton c2 = new JButton("");
		c2.setIcon(new ImageIcon("C:\\Users\\SO KIMLANG\\eclipse-workspace\\POS_System\\src\\POS\\c2.jpg"));
		c2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double PriceOfItem = 5.57;
				String id = "c002";
				int qty = 1;
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				if(table.getRowCount() == 0) {
					qty =1;
				}
				else {
					for(int i=0; i< table.getRowCount();i++) {
						if(table.getValueAt(i, 0) == id) {
							int Oqty = (int) table.getModel().getValueAt(i, 2);
							Oqty ++;
							model.removeRow(i);
							qty = Oqty;
						}
					};
				}
				model.addRow(new Object[] {id, "Blue baby Clothes", qty, PriceOfItem });
				itemCost();
			}
		});
		clothes_panel.setLayout(null);
		c2.setBounds(157, 11, 137, 90);
		clothes_panel.add(c2);

		JButton c8 = new JButton("");
		c8.setIcon(new ImageIcon("C:\\Users\\SO KIMLANG\\eclipse-workspace\\POS_System\\src\\POS\\c8.jpg"));
		c8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double PriceOfItem = 5.57;
				String id = "c008";
				int qty = 1;
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				if(table.getRowCount() == 0) {
					qty =1;
				}
				else {
					for(int i=0; i< table.getRowCount();i++) {
						if(table.getValueAt(i, 0) == id) {
							int Oqty = (int) table.getModel().getValueAt(i, 2);
							Oqty ++;
							model.removeRow(i);
							qty = Oqty;
						}
					};
				}
				model.addRow(new Object[] {id, "Pink baby Clothes", qty, PriceOfItem });
				itemCost();
			}
		});
		clothes_panel.setLayout(null);
		c8.setBounds(157, 112, 137, 90);
		clothes_panel.add(c8);

		JButton c14 = new JButton("");
		c14.setIcon(new ImageIcon("C:\\Users\\SO KIMLANG\\eclipse-workspace\\POS_System\\src\\POS\\c14.jpg"));
		c14.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double PriceOfItem = 5.57;
				String id = "c014";
				int qty = 1;
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				if(table.getRowCount() == 0) {
					qty =1;
				}
				else {
					for(int i=0; i< table.getRowCount();i++) {
						if(table.getValueAt(i, 0) == id) {
							int Oqty = (int) table.getModel().getValueAt(i, 2);
							Oqty ++;
							model.removeRow(i);
							qty = Oqty;
						}
					};
				}
				model.addRow(new Object[] {id, "White baby Clothes", qty, PriceOfItem });
				itemCost();
			}
		});
		clothes_panel.setLayout(null);
		c14.setBounds(157, 213, 137, 90);
		clothes_panel.add(c14);

		JButton c20 = new JButton("");
		c20.setIcon(new ImageIcon("C:\\Users\\SO KIMLANG\\eclipse-workspace\\POS_System\\src\\POS\\c20.jpg"));
		c20.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double PriceOfItem = 5.57;
				String id = "c020";
				int qty = 1;
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				if(table.getRowCount() == 0) {
					qty =1;
				}
				else {
					for(int i=0; i< table.getRowCount();i++) {
						if(table.getValueAt(i, 0) == id) {
							int Oqty = (int) table.getModel().getValueAt(i, 2);
							Oqty ++;
							model.removeRow(i);
							qty = Oqty;
						}
					};
				}
				model.addRow(new Object[] {id, "White baby Clothes", qty, PriceOfItem });
				itemCost();
			}
		});
		clothes_panel.setLayout(null);
		c20.setBounds(157, 314, 137, 90);
		clothes_panel.add(c20);

		JButton c26 = new JButton("");
		c26.setIcon(new ImageIcon("C:\\Users\\SO KIMLANG\\eclipse-workspace\\POS_System\\src\\POS\\c26.jpg"));
		c26.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double PriceOfItem = 5.57;
				int qty = 1;
				String id = "c026";
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				if(table.getRowCount() == 0) {
					qty =1;
				}
				else {
					for(int i=0; i< table.getRowCount();i++) {
						if(table.getValueAt(i, 0) == id) {
							int Oqty = (int) table.getModel().getValueAt(i, 2);
							Oqty ++;
							model.removeRow(i);
							qty = Oqty;
						}
					};
				}
				model.addRow(new Object[] {id ,"White baby Clothes", qty, PriceOfItem });
				itemCost();
			}
		});
		clothes_panel.setLayout(null);
		c26.setBounds(157, 415, 137, 90);
		clothes_panel.add(c26);

		JButton c3 = new JButton("");
		c3.setIcon(new ImageIcon("C:\\Users\\SO KIMLANG\\eclipse-workspace\\POS_System\\src\\POS\\c3.jpg"));
		c3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double PriceOfItem = 5.57;
				int qty = 1;
				String id = "c003";
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				if(table.getRowCount() == 0) {
					qty =1;
				}
				else {
					for(int i=0; i< table.getRowCount();i++) {
						if(table.getValueAt(i, 0) == id) {
							int Oqty = (int) table.getModel().getValueAt(i, 2);
							Oqty ++;
							model.removeRow(i);
							qty = Oqty;
						}
					};
				}
				model.addRow(new Object[] {id, "Light Blue Baby Clothes", qty, PriceOfItem });
				itemCost();
			}
		});
		clothes_panel.setLayout(null);
		c3.setBounds(304, 11, 137, 90);
		clothes_panel.add(c3);

		JButton c9 = new JButton("");
		c9.setIcon(new ImageIcon("C:\\Users\\SO KIMLANG\\eclipse-workspace\\POS_System\\src\\POS\\c9.jpg"));
		c9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double PriceOfItem = 5.57;
				int qty = 1;
				String id = "c009";
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				if(table.getRowCount() == 0) {
					qty =1;
				}
				else {
					for(int i=0; i< table.getRowCount();i++) {
						if(table.getValueAt(i, 0) == id) {
							int Oqty = (int) table.getModel().getValueAt(i, 2);
							Oqty ++;
							model.removeRow(i);
							qty = Oqty;
						}
					};
				}
				model.addRow(new Object[] {id, "White Clothes for baby", qty, PriceOfItem });
				itemCost();
			}
		});
		clothes_panel.setLayout(null);
		c9.setBounds(304, 112, 137, 90);
		clothes_panel.add(c9);

		JButton c15 = new JButton("");
		c15.setIcon(new ImageIcon("C:\\Users\\SO KIMLANG\\eclipse-workspace\\POS_System\\src\\POS\\c15.jpg"));
		c15.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double PriceOfItem = 15.57;
				int qty = 1;
				String id = "c015";
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				if(table.getRowCount() == 0) {
					qty =1;
				}
				else {
					for(int i=0; i< table.getRowCount();i++) {
						if(table.getValueAt(i, 0) == id) {
							int Oqty = (int) table.getModel().getValueAt(i, 2);
							Oqty ++;
							model.removeRow(i);
							qty = Oqty;
						}
					};
				}
				model.addRow(new Object[] {id, "Blue teddybear clothes", qty, PriceOfItem });
				itemCost();
			}
		});
		clothes_panel.setLayout(null);
		c15.setBounds(304, 213, 137, 90);
		clothes_panel.add(c15);

		JButton c21 = new JButton("");
		c21.setIcon(new ImageIcon("C:\\Users\\SO KIMLANG\\eclipse-workspace\\POS_System\\src\\POS\\c21.jpg"));
		c21.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double PriceOfItem = 15.57;
				int qty = 1;
				String id = "c021";
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				if(table.getRowCount() == 0) {
					qty =1;
				}
				else {
					for(int i=0; i< table.getRowCount();i++) {
						if(table.getValueAt(i, 0) == id) {
							int Oqty = (int) table.getModel().getValueAt(i, 2);
							Oqty ++;
							model.removeRow(i);
							qty = Oqty;
						}
					};
				}
				model.addRow(new Object[] {id, "Cute Clothes", qty, PriceOfItem });
				itemCost();
			}
		});
		clothes_panel.setLayout(null);
		c21.setBounds(304, 314, 137, 90);
		clothes_panel.add(c21);

		JButton c27 = new JButton("");
		c27.setIcon(new ImageIcon("C:\\Users\\SO KIMLANG\\eclipse-workspace\\POS_System\\src\\POS\\c27.jpg"));
		c27.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double PriceOfItem = 15.57;
				int qty = 1;
				String id = "c027";
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				if(table.getRowCount() == 0) {
					qty =1;
				}
				else {
					for(int i=0; i< table.getRowCount();i++) {
						if(table.getValueAt(i, 0) == id) {
							int Oqty = (int) table.getModel().getValueAt(i, 2);
							Oqty ++;
							model.removeRow(i);
							qty = Oqty;
						}
					};
				}
				model.addRow(new Object[] {id, "Bear Clothes for baby", qty, PriceOfItem });
				itemCost();
			}
		});
		clothes_panel.setLayout(null);
		c27.setBounds(304, 415, 137, 90);
		clothes_panel.add(c27);

		JButton c4 = new JButton("");
		c4.setIcon(new ImageIcon("C:\\Users\\SO KIMLANG\\eclipse-workspace\\POS_System\\src\\POS\\c4.jpg"));
		c4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double PriceOfItem = 5.57;
				int qty = 1;
				String id = "c004";
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				if(table.getRowCount() == 0) {
					qty =1;
				}
				else {
					for(int i=0; i< table.getRowCount();i++) {
						if(table.getValueAt(i, 0) == id) {
							int Oqty = (int) table.getModel().getValueAt(i, 2);
							Oqty ++;
							model.removeRow(i);
							qty = Oqty;
						}
					};
				}
				model.addRow(new Object[] {id, "Light Blue Baby Clothes", qty, PriceOfItem });
				itemCost();
			}
		});
		clothes_panel.setLayout(null);
		c4.setBounds(451, 11, 137, 90);
		clothes_panel.add(c4);

		JButton c10 = new JButton("");
		c10.setIcon(new ImageIcon("C:\\Users\\SO KIMLANG\\eclipse-workspace\\POS_System\\src\\POS\\c10.jpg"));
		c10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double PriceOfItem = 5.57;
				int qty = 1;
				String id = "c010";
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				if(table.getRowCount() == 0) {
					qty =1;
				}
				else {
					for(int i=0; i< table.getRowCount();i++) {
						if(table.getValueAt(i, 0) == id) {
							int Oqty = (int) table.getModel().getValueAt(i, 2);
							Oqty ++;
							model.removeRow(i);
							qty = Oqty;
						}
					};
				}
				model.addRow(new Object[] {id, "Chinese baby clothes", qty, PriceOfItem });
				itemCost();
			}
		});
		clothes_panel.setLayout(null);
		c10.setBounds(451, 112, 137, 90);
		clothes_panel.add(c10);

		JButton c16 = new JButton("");
		c16.setIcon(new ImageIcon("C:\\Users\\SO KIMLANG\\eclipse-workspace\\POS_System\\src\\POS\\c16.jpg"));
		c16.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double PriceOfItem = 8.57;
				int qty = 1;
				String id = "c016";
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				if(table.getRowCount() == 0) {
					qty =1;
				}
				else {
					for(int i=0; i< table.getRowCount();i++) {
						if(table.getValueAt(i, 0) == id) {
							int Oqty = (int) table.getModel().getValueAt(i, 2);
							Oqty ++;
							model.removeRow(i);
							qty = Oqty;
						}
					};
				}
				model.addRow(new Object[] {id, "Gray Baby Clothes", qty, PriceOfItem });
				itemCost();
			}
		});
		clothes_panel.setLayout(null);
		c16.setBounds(451, 213, 137, 90);
		clothes_panel.add(c16);

		JButton c22 = new JButton("");
		c22.setIcon(new ImageIcon("C:\\Users\\SO KIMLANG\\eclipse-workspace\\POS_System\\src\\POS\\c22.jpg"));
		c22.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double PriceOfItem = 7.57;
				int qty = 1;
				String id = "c022";
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				if(table.getRowCount() == 0) {
					qty =1;
				}
				else {
					for(int i=0; i< table.getRowCount();i++) {
						if(table.getValueAt(i, 0) == id) {
							int Oqty = (int) table.getModel().getValueAt(i, 2);
							Oqty ++;
							model.removeRow(i);
							qty = Oqty;
						}
					};
				}
				model.addRow(new Object[] {id, "Animal baby Clothes", qty, PriceOfItem });
				itemCost();
			}
		});
		clothes_panel.setLayout(null);
		c22.setBounds(451, 314, 137, 90);
		clothes_panel.add(c22);

		JButton c28 = new JButton("");
		c28.setIcon(new ImageIcon("C:\\Users\\SO KIMLANG\\eclipse-workspace\\POS_System\\src\\POS\\c28.jpg"));
		c28.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double PriceOfItem = 7.57;
				int qty = 1;
				String id = "c028";
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				if(table.getRowCount() == 0) {
					qty =1;
				}
				else {
					for(int i=0; i< table.getRowCount();i++) {
						if(table.getValueAt(i, 0) == id) {
							int Oqty = (int) table.getModel().getValueAt(i, 2);
							Oqty ++;
							model.removeRow(i);
							qty = Oqty;
						}
					};
				}
				model.addRow(new Object[] {id, "Animal baby Clothes", qty, PriceOfItem });
				itemCost();
			}
		});
		clothes_panel.setLayout(null);
		c28.setBounds(451, 415, 137, 90);
		clothes_panel.add(c28);

		JButton c5 = new JButton("");
		c5.setIcon(new ImageIcon("C:\\Users\\SO KIMLANG\\eclipse-workspace\\POS_System\\src\\POS\\c5.jpg"));
		c5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double PriceOfItem = 5.57;
				int qty = 1;
				String id = "c005";
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				if(table.getRowCount() == 0) {
					qty =1;
				}
				else {
					for(int i=0; i< table.getRowCount();i++) {
						if(table.getValueAt(i, 0) == id) {
							int Oqty = (int) table.getModel().getValueAt(i, 2);
							Oqty ++;
							model.removeRow(i);
							qty = Oqty;
						}
					};
				}
				model.addRow(new Object[] {id, "White baby Clothes", qty, PriceOfItem });
				itemCost();
			}
		});
		clothes_panel.setLayout(null);
		c5.setBounds(598, 11, 137, 90);
		clothes_panel.add(c5);

		JButton c11 = new JButton("");
		c11.setIcon(new ImageIcon("C:\\Users\\SO KIMLANG\\eclipse-workspace\\POS_System\\src\\POS\\c11.jpg"));
		c11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double PriceOfItem = 5.57;
				int qty = 1;
				String id = "c011";
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				if(table.getRowCount() == 0) {
					qty =1;
				}
				else {
					for(int i=0; i< table.getRowCount();i++) {
						if(table.getValueAt(i, 0) == id) {
							int Oqty = (int) table.getModel().getValueAt(i, 2);
							Oqty ++;
							model.removeRow(i);
							qty = Oqty;
						}
					};
				}
				model.addRow(new Object[] {id, "White baby Clothes", qty, PriceOfItem });
				itemCost();
			}
		});
		clothes_panel.setLayout(null);
		c11.setBounds(598, 112, 137, 90);
		clothes_panel.add(c11);

		JButton c17 = new JButton("");
		c17.setIcon(new ImageIcon("C:\\Users\\SO KIMLANG\\eclipse-workspace\\POS_System\\src\\POS\\c17.jpg"));
		c17.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double PriceOfItem = 9.57;
				int qty = 1;
				String id = "c017";
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				if(table.getRowCount() == 0) {
					qty =1;
				}
				else {
					for(int i=0; i< table.getRowCount();i++) {
						if(table.getValueAt(i, 0) == id) {
							int Oqty = (int) table.getModel().getValueAt(i, 2);
							Oqty ++;
							model.removeRow(i);
							qty = Oqty;
						}
					};
				}
				model.addRow(new Object[] {id, "Teddybear baby Clothes", qty, PriceOfItem });
				itemCost();
			}
		});
		clothes_panel.setLayout(null);
		c17.setBounds(598, 213, 137, 90);
		clothes_panel.add(c17);

		JButton c23 = new JButton("");
		c23.setIcon(new ImageIcon("C:\\Users\\SO KIMLANG\\eclipse-workspace\\POS_System\\src\\POS\\c23.jpg"));
		c23.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double PriceOfItem = 10.57;
				int qty = 1;
				String id = "c023";
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				if(table.getRowCount() == 0) {
					qty =1;
				}
				else {
					for(int i=0; i< table.getRowCount();i++) {
						if(table.getValueAt(i, 0) == id) {
							int Oqty = (int) table.getModel().getValueAt(i, 2);
							Oqty ++;
							model.removeRow(i);
							qty = Oqty;
						}
					};
				}
				model.addRow(new Object[] {id, "Brown teddybear baby clothes", qty, PriceOfItem });
				itemCost();
			}
		});
		clothes_panel.setLayout(null);
		c23.setBounds(598, 314, 137, 90);
		clothes_panel.add(c23);

		JButton c29 = new JButton("");
		c29.setIcon(new ImageIcon("C:\\Users\\SO KIMLANG\\eclipse-workspace\\POS_System\\src\\POS\\c29.jpg"));
		c29.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double PriceOfItem = 5.57;
				int qty = 1;
				String id = "c029";
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				if(table.getRowCount() == 0) {
					qty =1;
				}
				else {
					for(int i=0; i< table.getRowCount();i++) {
						if(table.getValueAt(i, 0) == id) {
							int Oqty = (int) table.getModel().getValueAt(i, 2);
							Oqty ++;
							model.removeRow(i);
							qty = Oqty;
						}
					};
				}
				model.addRow(new Object[] {id, "pink clothes for baby", qty, PriceOfItem });
				itemCost();
			}
		});
		clothes_panel.setLayout(null);
		c29.setBounds(598, 415, 137, 90);
		clothes_panel.add(c29);

		JButton c6 = new JButton("");
		c6.setIcon(new ImageIcon("C:\\Users\\SO KIMLANG\\eclipse-workspace\\POS_System\\src\\POS\\c6.jpg"));
		c6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double PriceOfItem = 5.57;
				int qty = 1;
				String id = "c006";
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				if(table.getRowCount() == 0) {
					qty =1;
				}
				else {
					for(int i=0; i< table.getRowCount();i++) {
						if(table.getValueAt(i, 0) == id) {
							int Oqty = (int) table.getModel().getValueAt(i, 2);
							Oqty ++;
							model.removeRow(i);
							qty = Oqty;
						}
					};
				}
				model.addRow(new Object[] {id, "Pink baby clothes", qty, PriceOfItem });
				itemCost();
			}
		});
		clothes_panel.setLayout(null);
		c6.setBounds(745, 11, 137, 90);
		clothes_panel.add(c6);

		JButton c12 = new JButton("");
		c12.setIcon(new ImageIcon("C:\\Users\\SO KIMLANG\\eclipse-workspace\\POS_System\\src\\POS\\c12.jpg"));
		c12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double PriceOfItem = 5.57;
				int qty = 1;
				String id = "c012";
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				if(table.getRowCount() == 0) {
					qty =1;
				}
				else {
					for(int i=0; i< table.getRowCount();i++) {
						if(table.getValueAt(i, 0) == id) {
							int Oqty = (int) table.getModel().getValueAt(i, 2);
							Oqty ++;
							model.removeRow(i);
							qty = Oqty;
						}
					};
				}
				model.addRow(new Object[] {id, "Chinese Bay Clothes", qty, PriceOfItem });
				itemCost();
			}
		});
		clothes_panel.setLayout(null);
		c12.setBounds(745, 112, 137, 90);
		clothes_panel.add(c12);

		JButton c18 = new JButton("");
		c18.setIcon(new ImageIcon("C:\\Users\\SO KIMLANG\\eclipse-workspace\\POS_System\\src\\POS\\c18.jpg"));
		c18.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double PriceOfItem = 8.57;
				int qty = 1;
				String id = "c018";
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				if(table.getRowCount() == 0) {
					qty =1;
				}
				else {
					for(int i=0; i< table.getRowCount();i++) {
						if(table.getValueAt(i, 0) == id) {
							int Oqty = (int) table.getModel().getValueAt(i, 2);
							Oqty ++;
							model.removeRow(i);
							qty = Oqty;
						}
					};
				}
				model.addRow(new Object[] {id, "Teddybear clothes", qty, PriceOfItem });
				itemCost();
			}
		});
		clothes_panel.setLayout(null);
		c18.setBounds(745, 213, 137, 90);
		clothes_panel.add(c18);

		JButton c24 = new JButton("");
		c24.setIcon(new ImageIcon("C:\\Users\\SO KIMLANG\\eclipse-workspace\\POS_System\\src\\POS\\c24.jpg"));
		c24.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double PriceOfItem = 8.57;
				String id = "c024";
				int qty = 1;
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				if(table.getRowCount() == 0) {
					qty =1;
				}
				else {
					for(int i=0; i< table.getRowCount();i++) {
						if(table.getValueAt(i, 0) == id) {
							int Oqty = (int) table.getModel().getValueAt(i, 2);
							Oqty ++;
							model.removeRow(i);
							qty = Oqty;
						}
					};
				}
				model.addRow(new Object[] {id, "Teddybear clothes", qty, PriceOfItem });
				itemCost();
			}
		});
		clothes_panel.setLayout(null);
		c24.setBounds(745, 314, 137, 90);
		clothes_panel.add(c24);

		JButton c30 = new JButton("");
		c30.setIcon(new ImageIcon("C:\\Users\\SO KIMLANG\\eclipse-workspace\\POS_System\\src\\POS\\c30.jpg"));
		c30.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double PriceOfItem = 9.57;
				String id = "c030";
				int qty = 1;
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				if(table.getRowCount() == 0) {
					qty =1;
				}
				else {
					for(int i=0; i< table.getRowCount();i++) {
						if(table.getValueAt(i, 0) == id) {
							int Oqty = (int) table.getModel().getValueAt(i, 2);
							Oqty ++;
							model.removeRow(i);
							qty = Oqty;
						}
					};
				}
				model.addRow(new Object[] {id, "Owl baby Clothes", qty, PriceOfItem });
				itemCost();
			}
		});
		clothes_panel.setLayout(null);
		c30.setBounds(745, 415, 137, 90);
		clothes_panel.add(c30);
		clothes_panel.setVisible(false);// clothes

		// snacks

		snacks_panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		snacks_panel.setBounds(373, 106, 900, 521);
		frame.getContentPane().add(snacks_panel);
		snacks_panel.setLayout(null);
		snacks_panel.setLayout(null);
		snacks_panel.setLayout(null);
		snacks_panel.setLayout(null);
		snacks_panel.setLayout(null);
		snacks_panel.setLayout(null);
		snacks_panel.setLayout(null);
		snacks_panel.setLayout(null);
		snacks_panel.setLayout(null);
		snacks_panel.setLayout(null);
		snacks_panel.setLayout(null);
		snacks_panel.setLayout(null);
		snacks_panel.setLayout(null);
		snacks_panel.setLayout(null);
		snacks_panel.setLayout(null);

		JButton s1 = new JButton("");
		s1.setIcon(new ImageIcon("C:\\Users\\SO KIMLANG\\eclipse-workspace\\POS_System\\src\\POS\\s1.jpg"));
		s1.setFont(new Font("Tahoma", Font.BOLD, 48));
		s1.setBounds(10, 11, 137, 90);
		snacks_panel.add(s1);
		s1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double PriceOfItem = 2.57;
				int qty = 1;
				String id = "s001";
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				if(table.getRowCount() == 0) {
					qty =1;
				}
				else {
					for(int i=0; i< table.getRowCount();i++) {
						if(table.getValueAt(i, 0) == id) {
							int Oqty = (int) table.getModel().getValueAt(i, 2);
							Oqty ++;
							model.removeRow(i);
							qty = Oqty;
						}
					};
				}
				model.addRow(new Object[] {id, "PeaTos Purple", qty, PriceOfItem });
				itemCost();
			}
		});
		snacks_panel.setLayout(null);
		JButton s13 = new JButton("");
		s13.setIcon(new ImageIcon("C:\\Users\\SO KIMLANG\\eclipse-workspace\\POS_System\\src\\POS\\s13.jpg"));
		s13.setFont(new Font("Tahoma", Font.BOLD, 48));
		s13.setBounds(10, 212, 137, 90);
		snacks_panel.add(s13);
		s13.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double PriceOfItem = 2.57;
				int qty = 1;
				String id = "s013";
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				if(table.getRowCount() == 0) {
					qty =1;
				}
				else {
					for(int i=0; i< table.getRowCount();i++) {
						if(table.getValueAt(i, 0) == id) {
							int Oqty = (int) table.getModel().getValueAt(i, 2);
							Oqty ++;
							model.removeRow(i);
							qty = Oqty;
						}
					};
				}
				model.addRow(new Object[] {id, "CheeTos BBQ", qty, PriceOfItem });
				itemCost();
			}
		});
		snacks_panel.setLayout(null);

		JButton s7 = new JButton("");
		s7.setIcon(new ImageIcon("C:\\Users\\SO KIMLANG\\eclipse-workspace\\POS_System\\src\\POS\\s7.jpg"));
		s7.setFont(new Font("Tahoma", Font.BOLD, 48));
		s7.setBounds(10, 111, 137, 90);
		snacks_panel.add(s7);
		s7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double PriceOfItem = 2.57;
				int qty = 1;
				String id = "s007";
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				if(table.getRowCount() == 0) {
					qty =1;
				}
				else {
					for(int i=0; i< table.getRowCount();i++) {
						if(table.getValueAt(i, 0) == id) {
							int Oqty = (int) table.getModel().getValueAt(i, 2);
							Oqty ++;
							model.removeRow(i);
							qty = Oqty;
						}
					};
				}
				model.addRow(new Object[] {id, "PeaTos Spicy", qty, PriceOfItem });
				itemCost();
			}
		});
		snacks_panel.setLayout(null);

		JButton s19 = new JButton("");
		s19.setIcon(new ImageIcon("C:\\Users\\SO KIMLANG\\eclipse-workspace\\POS_System\\src\\POS\\s19.jpg"));
		s19.setFont(new Font("Tahoma", Font.BOLD, 48));
		s19.setBounds(10, 313, 137, 90);
		snacks_panel.add(s19);
		s19.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double PriceOfItem = 2.57;
				int qty = 1;
				String id = "s019";
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				if(table.getRowCount() == 0) {
					qty =1;
				}
				else {
					for(int i=0; i< table.getRowCount();i++) {
						if(table.getValueAt(i, 0) == id) {
							int Oqty = (int) table.getModel().getValueAt(i, 2);
							Oqty ++;
							model.removeRow(i);
							qty = Oqty;
						}
					};
				}
				model.addRow(new Object[] {id, "Lays Cheese Flavor", qty, PriceOfItem });
				itemCost();
			}
		});
		snacks_panel.setLayout(null);

		JButton s25 = new JButton("");
		s25.setIcon(new ImageIcon("C:\\Users\\SO KIMLANG\\eclipse-workspace\\POS_System\\src\\POS\\s25.jpg"));
		s25.setFont(new Font("Tahoma", Font.BOLD, 48));
		s25.setBounds(10, 414, 137, 90);
		snacks_panel.add(s25);
		s25.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double PriceOfItem = 2.57;
				int qty = 1;
				String id = "s025";
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				if(table.getRowCount() == 0) {
					qty =1;
				}
				else {
					for(int i=0; i< table.getRowCount();i++) {
						if(table.getValueAt(i, 0) == id) {
							int Oqty = (int) table.getModel().getValueAt(i, 2);
							Oqty ++;
							model.removeRow(i);
							qty = Oqty;
						}
					};
				}
				model.addRow(new Object[] {id, "Lays Cool Flavor", qty, PriceOfItem });
				itemCost();
			}
		});
		snacks_panel.setLayout(null);

		JButton s2 = new JButton("");
		s2.setIcon(new ImageIcon("C:\\Users\\SO KIMLANG\\eclipse-workspace\\POS_System\\src\\POS\\s2.jpg"));
		s2.setFont(new Font("Tahoma", Font.BOLD, 48));
		s2.setBounds(157, 11, 137, 90);
		snacks_panel.add(s2);
		s2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double PriceOfItem = 2.57;
				int qty = 1;
				String id = "s002";
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				if(table.getRowCount() == 0) {
					qty =1;
				}
				else {
					for(int i=0; i< table.getRowCount();i++) {
						if(table.getValueAt(i, 0) == id) {
							int Oqty = (int) table.getModel().getValueAt(i, 2);
							Oqty ++;
							model.removeRow(i);
							qty = Oqty;
						}
					};
				}
				model.addRow(new Object[] {id, "PeaTos Onion", qty, PriceOfItem });
				itemCost();
			}
		});
		snacks_panel.setLayout(null);

		JButton s8 = new JButton("");
		s8.setIcon(new ImageIcon("C:\\Users\\SO KIMLANG\\eclipse-workspace\\POS_System\\src\\POS\\s8.jpg"));
		s8.setFont(new Font("Tahoma", Font.BOLD, 48));
		s8.setBounds(157, 111, 137, 90);
		snacks_panel.add(s8);
		s8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double PriceOfItem = 2.57;
				int qty = 1;
				String id = "s008";
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				if(table.getRowCount() == 0) {
					qty =1;
				}
				else {
					for(int i=0; i< table.getRowCount();i++) {
						if(table.getValueAt(i, 0) == id) {
							int Oqty = (int) table.getModel().getValueAt(i, 2);
							Oqty ++;
							model.removeRow(i);
							qty = Oqty;
						}
					};
				}
				model.addRow(new Object[] {id, "PeaTos Milk Flavor", qty, PriceOfItem });
				itemCost();
			}
		});
		snacks_panel.setLayout(null);

		JButton s14 = new JButton("");
		s14.setIcon(new ImageIcon("C:\\Users\\SO KIMLANG\\eclipse-workspace\\POS_System\\src\\POS\\s14.jpg"));
		s14.setFont(new Font("Tahoma", Font.BOLD, 48));
		s14.setBounds(157, 212, 137, 90);
		snacks_panel.add(s14);
		s14.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double PriceOfItem = 2.20;
				int qty = 1;
				String id = "s014";
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				if(table.getRowCount() == 0) {
					qty =1;
				}
				else {
					for(int i=0; i< table.getRowCount();i++) {
						if(table.getValueAt(i, 0) == id) {
							int Oqty = (int) table.getModel().getValueAt(i, 2);
							Oqty ++;
							model.removeRow(i);
							qty = Oqty;
						}
					};
				}
				model.addRow(new Object[] {id, "CheeTos Cheese Flavor", qty, PriceOfItem });
				itemCost();
			}
		});
		snacks_panel.setLayout(null);

		JButton s20 = new JButton("");
		s20.setIcon(new ImageIcon("C:\\Users\\SO KIMLANG\\eclipse-workspace\\POS_System\\src\\POS\\s20.jpg"));
		s20.setFont(new Font("Tahoma", Font.BOLD, 48));
		s20.setBounds(157, 313, 137, 90);
		snacks_panel.add(s20);
		s20.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double PriceOfItem = 1.50;
				int qty = 1;
				String id = "s020";
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				if(table.getRowCount() == 0) {
					qty =1;
				}
				else {
					for(int i=0; i< table.getRowCount();i++) {
						if(table.getValueAt(i, 0) == id) {
							int Oqty = (int) table.getModel().getValueAt(i, 2);
							Oqty ++;
							model.removeRow(i);
							qty = Oqty;
						}
					};
				}
				model.addRow(new Object[] {id, "Lays Lemon Flavor", qty, PriceOfItem });
				itemCost();
			}
		});
		snacks_panel.setLayout(null);

		JButton s26 = new JButton("");
		s26.setIcon(new ImageIcon("C:\\Users\\SO KIMLANG\\eclipse-workspace\\POS_System\\src\\POS\\s26.jpg"));
		s26.setFont(new Font("Tahoma", Font.BOLD, 48));
		s26.setBounds(157, 414, 137, 90);
		snacks_panel.add(s26);
		s26.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double PriceOfItem = 3.78;
				int qty = 1;
				String id = "s026";
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				if(table.getRowCount() == 0) {
					qty =1;
				}
				else {
					for(int i=0; i< table.getRowCount();i++) {
						if(table.getValueAt(i, 0) == id) {
							int Oqty = (int) table.getModel().getValueAt(i, 2);
							Oqty ++;
							model.removeRow(i);
							qty = Oqty;
						}
					};
				}
				model.addRow(new Object[] {id, "Oreo", qty, PriceOfItem });
				itemCost();
			}
		});
		snacks_panel.setLayout(null);

		JButton s3 = new JButton("");
		s3.setIcon(new ImageIcon("C:\\Users\\SO KIMLANG\\eclipse-workspace\\POS_System\\src\\POS\\s3.jpg"));
		s3.setFont(new Font("Tahoma", Font.BOLD, 48));
		s3.setBounds(304, 11, 137, 90);
		snacks_panel.add(s3);
		s3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double PriceOfItem = 1.57;
				int qty = 1;
				String id = "s003";
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				if(table.getRowCount() == 0) {
					qty =1;
				}
				else {
					for(int i=0; i< table.getRowCount();i++) {
						if(table.getValueAt(i, 0) == id) {
							int Oqty = (int) table.getModel().getValueAt(i, 2);
							Oqty ++;
							model.removeRow(i);
							qty = Oqty;
						}
					};
				}
				model.addRow(new Object[] {id, "PeaTos Alien", qty, PriceOfItem });
				itemCost();
			}
		});
		snacks_panel.setLayout(null);

		JButton s9 = new JButton("");
		s9.setIcon(new ImageIcon("C:\\Users\\SO KIMLANG\\eclipse-workspace\\POS_System\\src\\POS\\s9.jpg"));
		s9.setFont(new Font("Tahoma", Font.BOLD, 48));
		s9.setBounds(304, 111, 137, 90);
		snacks_panel.add(s9);
		s9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double PriceOfItem = 2.17;
				int qty = 1;
				String id = "s009";
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				if(table.getRowCount() == 0) {
					qty =1;
				}
				else {
					for(int i=0; i< table.getRowCount();i++) {
						if(table.getValueAt(i, 0) == id) {
							int Oqty = (int) table.getModel().getValueAt(i, 2);
							Oqty ++;
							model.removeRow(i);
							qty = Oqty;
						}
					};
				}
				model.addRow(new Object[] {id, "PeaTos Spicy Cheese", qty, PriceOfItem });
				itemCost();
			}
		});
		snacks_panel.setLayout(null);

		JButton s15 = new JButton("");
		s15.setIcon(new ImageIcon("C:\\Users\\SO KIMLANG\\eclipse-workspace\\POS_System\\src\\POS\\s15.jpg"));
		s15.setFont(new Font("Tahoma", Font.BOLD, 48));
		s15.setBounds(304, 212, 137, 90);
		snacks_panel.add(s15);
		s15.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double PriceOfItem = 1.57;
				int qty = 1;
				String id = "s015";
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				if(table.getRowCount() == 0) {
					qty =1;
				}
				else {
					for(int i=0; i< table.getRowCount();i++) {
						if(table.getValueAt(i, 0) == id) {
							int Oqty = (int) table.getModel().getValueAt(i, 2);
							Oqty ++;
							model.removeRow(i);
							qty = Oqty;
						}
					};
				}
				model.addRow(new Object[] {id, "CheeTos Baked Tomato", qty, PriceOfItem });
				itemCost();
			}
		});
		snacks_panel.setLayout(null);

		JButton s21 = new JButton("");
		s21.setIcon(new ImageIcon("C:\\Users\\SO KIMLANG\\eclipse-workspace\\POS_System\\src\\POS\\s21.jpg"));
		s21.setFont(new Font("Tahoma", Font.BOLD, 48));
		s21.setBounds(304, 313, 137, 90);
		snacks_panel.add(s21);
		s21.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double PriceOfItem = 3.57;
				int qty = 1;
				String id = "s021";
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				if(table.getRowCount() == 0) {
					qty =1;
				}
				else {
					for(int i=0; i< table.getRowCount();i++) {
						if(table.getValueAt(i, 0) == id) {
							int Oqty = (int) table.getModel().getValueAt(i, 2);
							Oqty ++;
							model.removeRow(i);
							qty = Oqty;
						}
					};
				}
				model.addRow(new Object[] {id, "Lays Spicy Chicken", qty, PriceOfItem });
				itemCost();
			}
		});
		snacks_panel.setLayout(null);

		JButton s27 = new JButton("");
		s27.setIcon(new ImageIcon("C:\\Users\\SO KIMLANG\\eclipse-workspace\\POS_System\\src\\POS\\s27.jpg"));
		s27.setFont(new Font("Tahoma", Font.BOLD, 48));
		s27.setBounds(304, 414, 137, 90);
		snacks_panel.add(s27);
		s27.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double PriceOfItem = 2.27;
				int qty = 1;
				String id = "s027";
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				if(table.getRowCount() == 0) {
					qty =1;
				}
				else {
					for(int i=0; i< table.getRowCount();i++) {
						if(table.getValueAt(i, 0) == id) {
							int Oqty = (int) table.getModel().getValueAt(i, 2);
							Oqty ++;
							model.removeRow(i);
							qty = Oqty;
						}
					};
				}
				model.addRow(new Object[] {id, "Lays Tomato Sauce", qty, PriceOfItem });
				itemCost();
			}
		});
		snacks_panel.setLayout(null);

		JButton s4 = new JButton("");
		s4.setIcon(new ImageIcon("C:\\Users\\SO KIMLANG\\eclipse-workspace\\POS_System\\src\\POS\\s4.jpg"));
		s4.setFont(new Font("Tahoma", Font.BOLD, 48));
		s4.setBounds(451, 11, 137, 90);
		snacks_panel.add(s4);
		s4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double PriceOfItem = 2.11;
				int qty = 1;
				String id = "s004";
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				if(table.getRowCount() == 0) {
					qty =1;
				}
				else {
					for(int i=0; i< table.getRowCount();i++) {
						if(table.getValueAt(i, 0) == id) {
							int Oqty = (int) table.getModel().getValueAt(i, 2);
							Oqty ++;
							model.removeRow(i);
							qty = Oqty;
						}
					};
				}
				model.addRow(new Object[] {id, "PeaTos Yellow", qty, PriceOfItem });
				itemCost();
			}
		});
		snacks_panel.setLayout(null);

		JButton s10 = new JButton("");
		s10.setIcon(new ImageIcon("C:\\Users\\SO KIMLANG\\eclipse-workspace\\POS_System\\src\\POS\\s10.jpg"));
		s10.setFont(new Font("Tahoma", Font.BOLD, 48));
		s10.setBounds(451, 111, 137, 90);
		snacks_panel.add(s10);
		s10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double PriceOfItem = 2.59;
				int qty = 1;
				String id = "s010";
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				if(table.getRowCount() == 0) {
					qty =1;
				}
				else {
					for(int i=0; i< table.getRowCount();i++) {
						if(table.getValueAt(i, 0) == id) {
							int Oqty = (int) table.getModel().getValueAt(i, 2);
							Oqty ++;
							model.removeRow(i);
							qty = Oqty;
						}
					};
				}
				model.addRow(new Object[] {id, "CheeTos Hot and Spicy", qty, PriceOfItem });
				itemCost();
			}
		});
		snacks_panel.setLayout(null);

		JButton s16 = new JButton("");
		s16.setIcon(new ImageIcon("C:\\Users\\SO KIMLANG\\eclipse-workspace\\POS_System\\src\\POS\\s16.jpg"));
		s16.setFont(new Font("Tahoma", Font.BOLD, 48));
		s16.setBounds(451, 212, 137, 90);
		snacks_panel.add(s16);
		s16.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double PriceOfItem = 1.16;
				int qty = 1;
				String id = "s016";
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				if(table.getRowCount() == 0) {
					qty =1;
				}
				else {
					for(int i=0; i< table.getRowCount();i++) {
						if(table.getValueAt(i, 0) == id) {
							int Oqty = (int) table.getModel().getValueAt(i, 2);
							Oqty ++;
							model.removeRow(i);
							qty = Oqty;
						}
					};
				}
				model.addRow(new Object[] {id, "CheeTos Baked Spicy", qty, PriceOfItem });
				itemCost();
			}
		});
		snacks_panel.setLayout(null);

		JButton s22 = new JButton("");
		s22.setIcon(new ImageIcon("C:\\Users\\SO KIMLANG\\eclipse-workspace\\POS_System\\src\\POS\\s22.jpg"));
		s22.setFont(new Font("Tahoma", Font.BOLD, 48));
		s22.setBounds(451, 313, 137, 90);
		snacks_panel.add(s22);
		s22.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double PriceOfItem = 2.87;
				int qty = 1;
				String id = "s022";
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				if(table.getRowCount() == 0) {
					qty =1;
				}
				else {
					for(int i=0; i< table.getRowCount();i++) {
						if(table.getValueAt(i, 0) == id) {
							int Oqty = (int) table.getModel().getValueAt(i, 2);
							Oqty ++;
							model.removeRow(i);
							qty = Oqty;
						}
					};
				}
				model.addRow(new Object[] {id, "Lays Garlic Lime", qty, PriceOfItem });
				itemCost();
			}
		});
		snacks_panel.setLayout(null);

		JButton s28 = new JButton("");
		s28.setIcon(new ImageIcon("C:\\Users\\SO KIMLANG\\eclipse-workspace\\POS_System\\src\\POS\\s28.jpg"));
		s28.setFont(new Font("Tahoma", Font.BOLD, 48));
		s28.setBounds(451, 414, 137, 90);
		snacks_panel.add(s28);
		s28.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double PriceOfItem = 2.77;
				int qty = 1;
				String id = "s028";
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				if(table.getRowCount() == 0) {
					qty =1;
				}
				else {
					for(int i=0; i< table.getRowCount();i++) {
						if(table.getValueAt(i, 0) == id) {
							int Oqty = (int) table.getModel().getValueAt(i, 2);
							Oqty ++;
							model.removeRow(i);
							qty = Oqty;
						}
					};
				}
				model.addRow(new Object[] {id, "KitKat Red", qty, PriceOfItem });
				itemCost();
			}
		});
		snacks_panel.setLayout(null);

		JButton s5 = new JButton("");
		s5.setIcon(new ImageIcon("C:\\Users\\SO KIMLANG\\eclipse-workspace\\POS_System\\src\\POS\\s5.jpg"));
		s5.setFont(new Font("Tahoma", Font.BOLD, 48));
		s5.setBounds(600, 11, 137, 90);
		snacks_panel.add(s5);
		s5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double PriceOfItem = 2.77;
				int qty = 1;
				String id = "s005";
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				if(table.getRowCount() == 0) {
					qty =1;
				}
				else {
					for(int i=0; i< table.getRowCount();i++) {
						if(table.getValueAt(i, 0) == id) {
							int Oqty = (int) table.getModel().getValueAt(i, 2);
							Oqty ++;
							model.removeRow(i);
							qty = Oqty;
						}
					};
				}
				model.addRow(new Object[] {id, "PeaTos Spicy Cheese", qty, PriceOfItem });
				itemCost();
			}
		});
		snacks_panel.setLayout(null);

		JButton s11 = new JButton("");
		s11.setIcon(new ImageIcon("C:\\Users\\SO KIMLANG\\eclipse-workspace\\POS_System\\src\\POS\\s11.jpg"));
		s11.setFont(new Font("Tahoma", Font.BOLD, 48));
		s11.setBounds(600, 111, 137, 90);
		snacks_panel.add(s11);
		s11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double PriceOfItem = 2.97;
				int qty = 1;
				String id = "s011";
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				if(table.getRowCount() == 0) {
					qty =1;
				}
				else {
					for(int i=0; i< table.getRowCount();i++) {
						if(table.getValueAt(i, 0) == id) {
							int Oqty = (int) table.getModel().getValueAt(i, 2);
							Oqty ++;
							model.removeRow(i);
							qty = Oqty;
						}
					};
				}
				model.addRow(new Object[] {id, "CheeTos Cheese Yellow", qty, PriceOfItem });
				itemCost();
			}
		});
		snacks_panel.setLayout(null);

		JButton s17 = new JButton("");
		s17.setIcon(new ImageIcon("C:\\Users\\SO KIMLANG\\eclipse-workspace\\POS_System\\src\\POS\\s17.jpg"));
		s17.setFont(new Font("Tahoma", Font.BOLD, 48));
		s17.setBounds(600, 212, 137, 90);
		snacks_panel.add(s17);
		s17.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double PriceOfItem = 2.57;
				int qty = 1;
				String id = "s017";
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				if(table.getRowCount() == 0) {
					qty =1;
				}
				else {
					for(int i=0; i< table.getRowCount();i++) {
						if(table.getValueAt(i, 0) == id) {
							int Oqty = (int) table.getModel().getValueAt(i, 2);
							Oqty ++;
							model.removeRow(i);
							qty = Oqty;
						}
					};
				}
				model.addRow(new Object[] {id, "CheeTos Baked Cheese", qty, PriceOfItem });
				itemCost();
			}
		});
		snacks_panel.setLayout(null);

		JButton s23 = new JButton("");
		s23.setIcon(new ImageIcon("C:\\Users\\SO KIMLANG\\eclipse-workspace\\POS_System\\src\\POS\\s23.jpg"));
		s23.setFont(new Font("Tahoma", Font.BOLD, 48));
		s23.setBounds(600, 313, 137, 90);
		snacks_panel.add(s23);
		s23.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double PriceOfItem = 2.23;
				int qty = 1;
				String id = "s023";
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				if(table.getRowCount() == 0) {
					qty =1;
				}
				else {
					for(int i=0; i< table.getRowCount();i++) {
						if(table.getValueAt(i, 0) == id) {
							int Oqty = (int) table.getModel().getValueAt(i, 2);
							Oqty ++;
							model.removeRow(i);
							qty = Oqty;
						}
					};
				}
				model.addRow(new Object[] {id, "Lays Original Taste", qty, PriceOfItem });
				itemCost();
			}
		});
		snacks_panel.setLayout(null);

		JButton s29 = new JButton("");
		s29.setIcon(new ImageIcon("C:\\Users\\SO KIMLANG\\eclipse-workspace\\POS_System\\src\\POS\\s29.jpg"));
		s29.setFont(new Font("Tahoma", Font.BOLD, 48));
		s29.setBounds(600, 414, 137, 90);
		snacks_panel.add(s29);
		s29.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double PriceOfItem = 2.77;
				int qty = 1;
				String id = "s029";
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				if(table.getRowCount() == 0) {
					qty =1;
				}
				else {
					for(int i=0; i< table.getRowCount();i++) {
						if(table.getValueAt(i, 0) == id) {
							int Oqty = (int) table.getModel().getValueAt(i, 2);
							Oqty ++;
							model.removeRow(i);
							qty = Oqty;
						}
					};
				}
				model.addRow(new Object[] {id, "KitKat Green", qty, PriceOfItem });
				itemCost();
			}
		});
		snacks_panel.setLayout(null);

		JButton s6 = new JButton("");
		s6.setIcon(new ImageIcon("C:\\Users\\SO KIMLANG\\eclipse-workspace\\POS_System\\src\\POS\\s6.jpg"));
		s6.setFont(new Font("Tahoma", Font.BOLD, 48));
		s6.setBounds(747, 11, 137, 90);
		snacks_panel.add(s6);
		s6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double PriceOfItem = 2.57;
				int qty = 1;
				String id = "s006";
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				if(table.getRowCount() == 0) {
					qty =1;
				}
				else {
					for(int i=0; i< table.getRowCount();i++) {
						if(table.getValueAt(i, 0) == id) {
							int Oqty = (int) table.getModel().getValueAt(i, 2);
							Oqty ++;
							model.removeRow(i);
							qty = Oqty;
						}
					};
				}
				model.addRow(new Object[] {id, "PeaTos Double Cheese", qty, PriceOfItem });
				itemCost();
			}
		});
		snacks_panel.setLayout(null);

		JButton s12 = new JButton("");
		s12.setIcon(new ImageIcon("C:\\Users\\SO KIMLANG\\eclipse-workspace\\POS_System\\src\\POS\\s12.jpg"));
		s12.setFont(new Font("Tahoma", Font.BOLD, 48));
		s12.setBounds(747, 111, 137, 90);
		snacks_panel.add(s12);
		s12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double PriceOfItem = 2.88;
				int qty = 1;
				String id = "s012";
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				if(table.getRowCount() == 0) {
					qty =1;
				}
				else {
					for(int i=0; i< table.getRowCount();i++) {
						if(table.getValueAt(i, 0) == id) {
							int Oqty = (int) table.getModel().getValueAt(i, 2);
							Oqty ++;
							model.removeRow(i);
							qty = Oqty;
						}
					};
				}
				model.addRow(new Object[] {id, "CheeTos Roasted Potato", qty, PriceOfItem });
				itemCost();
			}
		});
		snacks_panel.setLayout(null);

		JButton s18 = new JButton("");
		s18.setIcon(new ImageIcon("C:\\Users\\SO KIMLANG\\eclipse-workspace\\POS_System\\src\\POS\\s18.jpg"));
		s18.setFont(new Font("Tahoma", Font.BOLD, 48));
		s18.setBounds(747, 212, 137, 90);
		snacks_panel.add(s18);
		s18.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double PriceOfItem = 3.57;
				int qty = 1;
				String id = "s018";
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				if(table.getRowCount() == 0) {
					qty =1;
				}
				else {
					for(int i=0; i< table.getRowCount();i++) {
						if(table.getValueAt(i, 0) == id) {
							int Oqty = (int) table.getModel().getValueAt(i, 2);
							Oqty ++;
							model.removeRow(i);
							qty = Oqty;
						}
					};
				}
				model.addRow(new Object[] {id, "CheeTos Baked Honey", qty, PriceOfItem });
				itemCost();
			}
		});
		snacks_panel.setLayout(null);

		JButton s24 = new JButton("");
		s24.setIcon(new ImageIcon("C:\\Users\\SO KIMLANG\\eclipse-workspace\\POS_System\\src\\POS\\s24.jpg"));
		s24.setFont(new Font("Tahoma", Font.BOLD, 48));
		s24.setBounds(747, 313, 137, 90);
		snacks_panel.add(s24);
		s24.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double PriceOfItem = 2.24;
				int qty = 1;
				String id = "s024";
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				if(table.getRowCount() == 0) {
					qty =1;
				}
				else {
					for(int i=0; i< table.getRowCount();i++) {
						if(table.getValueAt(i, 0) == id) {
							int Oqty = (int) table.getModel().getValueAt(i, 2);
							Oqty ++;
							model.removeRow(i);
							qty = Oqty;
						}
					};
				}
				model.addRow(new Object[] {id, "Lays Summer Taste", qty, PriceOfItem });
				itemCost();
			}
		});
		snacks_panel.setLayout(null);

		JButton s30 = new JButton("");
		s30.setIcon(new ImageIcon("C:\\Users\\SO KIMLANG\\eclipse-workspace\\POS_System\\src\\POS\\s30.jpg"));
		s30.setFont(new Font("Tahoma", Font.BOLD, 48));
		s30.setBounds(747, 414, 137, 90);
		snacks_panel.add(s30);
		s30.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double PriceOfItem = 3.37;
				int qty = 1;
				String id = "s030";
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				if(table.getRowCount() == 0) {
					qty =1;
				}
				else {
					for(int i=0; i< table.getRowCount();i++) {
						if(table.getValueAt(i, 0) == id) {
							int Oqty = (int) table.getModel().getValueAt(i, 2);
							Oqty ++;
							model.removeRow(i);
							qty = Oqty;
						}
					};
				}
				model.addRow(new Object[] {id, "Hanami Original Flavor", qty, PriceOfItem });
				itemCost();
			}
		});
		snacks_panel.setLayout(null);

		JPanel payment_panel = new JPanel();
		payment_panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		payment_panel.setBounds(10, 270, 353, 362);
		frame.getContentPane().add(payment_panel);
		payment_panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Sub Total");
		lblNewLabel.setBounds(10, 12, 122, 25);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		payment_panel.add(lblNewLabel);

		tf_subTotal = new JTextField();
		tf_subTotal.setBounds(130, 9, 213, 31);
		tf_subTotal.setFont(new Font("Tahoma", Font.BOLD, 20));
		payment_panel.add(tf_subTotal);
		tf_subTotal.setColumns(10);

		JLabel lblTax = new JLabel("Tax");
		lblTax.setBounds(10, 51, 122, 25);
		lblTax.setFont(new Font("Tahoma", Font.BOLD, 20));
		payment_panel.add(lblTax);

		tf_tax = new JTextField();
		tf_tax.setBounds(130, 48, 213, 31);
		tf_tax.setFont(new Font("Tahoma", Font.BOLD, 20));
		tf_tax.setColumns(10);
		payment_panel.add(tf_tax);

		JLabel lblDiscount = new JLabel("Discount");
		lblDiscount.setBounds(10, 90, 122, 25);
		lblDiscount.setFont(new Font("Tahoma", Font.BOLD, 20));
		payment_panel.add(lblDiscount);

		tf_discount = new JTextField();
		tf_discount.setBounds(130, 87, 213, 31);
		tf_discount.setFont(new Font("Tahoma", Font.BOLD, 20));
		tf_discount.setColumns(10);
		payment_panel.add(tf_discount);

		JLabel lblTotal = new JLabel("Total");
		lblTotal.setBounds(10, 129, 122, 25);
		lblTotal.setFont(new Font("Tahoma", Font.BOLD, 20));
		payment_panel.add(lblTotal);

		tf_total = new JTextField();
		tf_total.setBounds(130, 126, 213, 31);
		tf_total.setFont(new Font("Tahoma", Font.BOLD, 20));
		tf_total.setColumns(10);
		payment_panel.add(tf_total);

		JLabel lblPaymentMethod = new JLabel("Payment ");
		lblPaymentMethod.setBounds(10, 166, 122, 25);
		lblPaymentMethod.setFont(new Font("Tahoma", Font.BOLD, 20));
		payment_panel.add(lblPaymentMethod);

		JLabel lblCash = new JLabel("Cash");
		lblCash.setBounds(10, 204, 122, 25);
		lblCash.setFont(new Font("Tahoma", Font.BOLD, 20));
		payment_panel.add(lblCash);

		tf_cash = new JTextField();
		tf_cash.setBounds(130, 201, 213, 31);
		tf_cash.setFont(new Font("Tahoma", Font.BOLD, 20));
		tf_cash.setColumns(10);
		payment_panel.add(tf_cash);

		JLabel lblChange = new JLabel("Change");
		lblChange.setBounds(10, 243, 122, 25);
		lblChange.setFont(new Font("Tahoma", Font.BOLD, 20));
		payment_panel.add(lblChange);

		tf_change = new JTextField();
		tf_change.setBounds(130, 240, 213, 31);
		tf_change.setFont(new Font("Tahoma", Font.BOLD, 20));
		tf_change.setColumns(10);
		payment_panel.add(tf_change);

		JButton btnPay = new JButton("Pay");
		btnPay.setBounds(10, 279, 122, 35);
		btnPay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (jcboPayment.getSelectedItem().equals("Cash")) {
					Change();
				} else {
					tf_change.setText("");
					tf_cash.setText("");
				}
			
			}
		});
		btnPay.setFont(new Font("Tahoma", Font.BOLD, 17));
		payment_panel.add(btnPay);

		JButton btn_reset = new JButton("Reset");
		btn_reset.setBounds(140, 279, 106, 35);
		btn_reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tf_subTotal.setText(null);
				tf_tax.setText(null);
				tf_discount.setText(null);
				tf_total.setText(null);
				tf_change.setText(null);
				tf_cash.setText(null);
				textField_barcode.setText(null);
				DefaultTableModel RecordTable = (DefaultTableModel) table.getModel();
				RecordTable.setRowCount(0);
//				table.setModel(new DefaultTableModel(null,new String[] {"ID","Item","Qty","Price"}));
			}
		});
		btn_reset.setFont(new Font("Tahoma", Font.BOLD, 17));
		payment_panel.add(btn_reset);
		

		JButton btn_print = new JButton("Print");
		btn_print.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MessageFormat header = new MessageFormat("Invoice");
				MessageFormat footer = new MessageFormat("Page {0, number, integer}");
				try {
					table.print(JTable.PrintMode.NORMAL, header, footer);

				} catch (java.awt.print.PrinterException ex) {
					System.err.format("No printer found", ex.getMessage());
				}
			}
		});
		btn_print.setBounds(256, 282, 87, 71);
		btn_print.setFont(new Font("Tahoma", Font.BOLD, 17));
		payment_panel.add(btn_print);

		JButton btn_remove = new JButton("Remove Item");
		btn_remove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel) table.getModel();

				int RemoveItem = table.getSelectedRow();
				if (RemoveItem >= 0) {
//					if (.getValueAt(RemoveItem, 2) > 1);
					int qty = (int) table.getModel().getValueAt(RemoveItem, 2);
					if(qty > 1) {
						qty = qty -1;
						model.setValueAt(qty, RemoveItem, 2);
					}else {
						model.removeRow(RemoveItem);
					}
				}
				itemCost();
				if (jcboPayment.getSelectedItem().equals("Cash")) {
					Change();
				} else {
					tf_change.setText("");
					tf_cash.setText("");
				}
			}
		});
		btn_remove.setBounds(10, 321, 122, 32);
		payment_panel.add(btn_remove);
		btn_remove.setFont(new Font("Tahoma", Font.BOLD, 17));

		JButton btn_exit = new JButton("Exit");
		btn_exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame = new JFrame("Exit");
				if (JOptionPane.showConfirmDialog(frame, "Confirm if you want to exit", "Point of Sale",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
					System.exit(0);
				}
			}
		});
		btn_exit.setFont(new Font("Tahoma", Font.BOLD, 17));
		btn_exit.setBounds(140, 321, 106, 35);
		payment_panel.add(btn_exit);

		jcboPayment.setFont(new Font("Tahoma", Font.PLAIN, 17));
		jcboPayment.setModel(new DefaultComboBoxModel(new String[] { "", "Cash", "Visa Card", "Bank Account" }));
		jcboPayment.setBounds(130, 165, 213, 28);
		payment_panel.add(jcboPayment);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 353, 180);
		frame.getContentPane().add(scrollPane);

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {
			
		}, new String[] { "ID", "Items", "Qty", "Price" }));
		table.getColumnModel().getColumn(0).setPreferredWidth(25);
		table.getColumnModel().getColumn(1).setPreferredWidth(90);
		table.getColumnModel().getColumn(2).setPreferredWidth(25);
		table.getColumnModel().getColumn(3).setPreferredWidth(50);
		scrollPane.setViewportView(table);
		
		
		textField_barcode = new JTextField();
		textField_barcode.setFont(new Font("C39HrP24DhTt", Font.BOLD, 36));
		textField_barcode.setBounds(10, 202, 353, 57);
		frame.getContentPane().add(textField_barcode);
		textField_barcode.setColumns(10);

		category_panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		category_panel.setBounds(373, 11, 900, 90);
		frame.getContentPane().add(category_panel);

		JButton beverages = new JButton("Beverages");
//		beverages.setIcon(new ImageIcon("C:\\Users\\SO KIMLANG\\OneDrive\\Pictures\\POS\\coffee.jpg"));
		beverages.setBounds(10, 11, 274, 80);
		beverages.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				beverages_panel.setVisible(true);
				snacks_panel.setVisible(false);
				clothes_panel.setVisible(false);
			}
		});
		category_panel.setLayout(null);
		beverages.setFont(new Font("Tahoma", Font.BOLD, 35));
		category_panel.add(beverages);
//		
		JButton snacks = new JButton("Snacks");
//		snacks.setIcon(new ImageIcon(""));
		snacks.setBounds(304, 11, 274, 80);
		snacks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				snacks_panel.setVisible(true);
				beverages_panel.setVisible(false);
				clothes_panel.setVisible(false);
			}
		});
		category_panel.setLayout(null);
		snacks.setFont(new Font("Tahoma", Font.BOLD, 35));
		category_panel.add(snacks);

		JButton clothes = new JButton("Clothes");
//		clothes.setIcon(new ImageIcon(""));
		clothes.setBounds(598, 11, 274, 80);
		clothes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clothes_panel.setVisible(true);
				beverages_panel.setVisible(false);
				snacks_panel.setVisible(false);
			}
		});
		category_panel.setLayout(null);
		clothes.setFont(new Font("Tahoma", Font.BOLD, 35));
		category_panel.add(clothes);

		frame.setVisible(true);
		payment_panel.setVisible(true);// total
		snacks_panel.setVisible(false);// snacks
		category_panel.setVisible(true);// categories
	}
}
