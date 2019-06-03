package suffixtree;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class MainClass {		

	public static void main(String[] args) throws Exception {
		JLabel jlabel1 = new JLabel("  Introduceti text ");
		jlabel1.setFont(new Font("Verdana",1,20));
		jlabel1.setHorizontalAlignment(SwingConstants.CENTER);  
		JTextField textField = new JTextField(40);
		
		JLabel jlabel2 = new JLabel("Introduceti sufix ");
		jlabel2.setFont(new Font("Verdana",1,20));
		jlabel2.setHorizontalAlignment(SwingConstants.CENTER); 		
		JTextField searchTextField = new JTextField(40);
		
	    JButton addButton = new JButton("Cauta");
	    
	    addButton.addActionListener(new ActionListener(){
	    	   public void actionPerformed(ActionEvent ae){
	    		   if(ae.getSource() == addButton){
	    			   String text = textField.getText();
	    			   String searchText = searchTextField.getText(); 
	    			   if(text == null || text.equals("") || searchText == null || searchText.equals("")){
	    				   drawErrorGraphic();
	    			   }else{
	    				   getSearchResult(text, searchText);
	    			   }
	    		   }	    	      
	    	   }
	    });
		
		JFrame frame= new JFrame("Introduceti datele");	
				
		frame.setSize(700, 500);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.getContentPane().setLayout(new FlowLayout());		
		frame.getContentPane().add(jlabel1);
		frame.getContentPane().add(textField);
		frame.getContentPane().add(jlabel2);
		frame.getContentPane().add(searchTextField);
		frame.getContentPane().add(addButton);				
	}
	
	private static void drawErrorGraphic() {
		JFrame frame= new JFrame("Nu ati introdus datele");	
		
		frame.setSize(500, 400);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel jlabel = new JLabel("Trebuie sa introduceti toate datele");
		jlabel.setFont(new Font("Verdana",1,20));
		frame.setLayout(new GridBagLayout());
		frame.getContentPane().add(jlabel);
	}
	
	private static void getSearchResult(String text, String searchText) {
		SuffixTree st = new SuffixTree();
        st.T = text.toCharArray();
        st.N = st.T.length - 1;  
 
        for (int i = 0 ; i <= st.N ; i++ )
            st.AddPrefix( st.active, i );
 
        String result = st.dump_edges( st.N );
        
        JFrame frame= new JFrame("Rezultat");			
		frame.setSize(700, 700);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		if(st.checkIfSubtextExists(searchText, st.N)) {
        	result += " Sufixul \"" + searchText + "\" exista in textul dat!</html>";
        }else {
        	result += " Sufixul \"" + searchText + "\" nu a fost gasit in textul dat!</html>";
        }
		
		JLabel jlabel = new JLabel(result);
		jlabel.setFont(new Font("Verdana",1,20));
		jlabel.setHorizontalAlignment(SwingConstants.CENTER);  
		
		frame.setLayout(new GridLayout(0,1));
		frame.getContentPane().add(jlabel);
	}
}
