package proto;


import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.StringTokenizer; 
import java.util.ArrayList;

public class Output extends JFrame implements ActionListener {
	
	private JScrollBar jscbHort = new JScrollBar(JScrollBar.HORIZONTAL);
	private JScrollBar jscbVert = new JScrollBar(JScrollBar.VERTICAL);
	private Transmission trs = null;
	public JLabel keyList = new JLabel();
	JButton reset = new JButton("Back");
	
	
	public Output(String words) throws Exception {
		
		
		ArrayList<String> keywords = new ArrayList<String>();
		StringTokenizer s = new StringTokenizer(words);  
		while(s.hasMoreTokens()) {             	
			keywords.add(s.nextToken(", "));            	
		}  
		
		trs = new Transmission(keywords);
        String []index = {"Date", "Site", "Headline", "Url"};
        String [][]cont = new String[100][100];
       
        for(int i=0 ; i < trs.getArt().getHowManyData() ; i++) {
        	for (int j=0 ; j < 4 ; j++) {
        		cont[i][0] = Integer.toString(trs.getArt().getDate(i));
        		cont[i][1] = trs.getArt().getSite(i);
        		cont[i][2] = trs.getArt().getHeadline(i);
        		cont[i][3] = trs.getArt().getUrl(i);
        	}
        }
        
      // test: System.out.println(art1.getDate(0));
		setTitle("Search result");
		setSize(1200,800);
		
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		DefaultTableModel model = new DefaultTableModel(cont, index);
		model.setColumnCount(4);
		
		setLayout(new BorderLayout());
		JTable table = new JTable(model);

        
        // 컬럼의 속성 셋팅 
        table.getColumnModel().getColumn(0).setMaxWidth(200); 
        table.getColumnModel().getColumn(0).setMinWidth(100); 
        table.getColumnModel().getColumn(0).setWidth(80); 

        table.getColumnModel().getColumn(1).setMaxWidth(200); 
        table.getColumnModel().getColumn(1).setMinWidth(100); 
        table.getColumnModel().getColumn(1).setWidth(80); 
        
        table.getColumnModel().getColumn(2).setMaxWidth(600); 
        table.getColumnModel().getColumn(2).setMinWidth(200); 
        table.getColumnModel().getColumn(2).setWidth(200); 
        
        table.getColumnModel().getColumn(3).setMaxWidth(400); 
        table.getColumnModel().getColumn(3).setMinWidth(100); 
        table.getColumnModel().getColumn(3).setWidth(100); 
         // 행의 높이 정하기 
        table.setRowHeight(50); 
        
        DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
        dtcr.setHorizontalAlignment(SwingConstants.CENTER);
        TableColumnModel tcm = table.getColumnModel();
        tcm.getColumn(0).setCellRenderer(dtcr);
        tcm.getColumn(1).setCellRenderer(dtcr);
        
		JScrollPane sc = new JScrollPane(table);
		
		JPanel p0 = new JPanel();
		p0.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 15));
		JPanel p1 = new JPanel();
		p1.setLayout(new GridLayout(1, 2));
	
		
		p0.add(new JLabel("Keyword ->"));
		
		p0.add(new JTextField(words, 25));

		p0.add(reset);
		reset.addActionListener(this);
		
		add(p0, BorderLayout.NORTH);
		p1.add(sc);

	
		add(p1);
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(reset)) {
			dispose();
		}
	}
}

