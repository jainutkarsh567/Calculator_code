import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class calculator1 extends JFrame {
	double lastNumber,currentNumber,memory;
	JPanel ptop,pbtm,pmid;
	JLabel lb1h,lb2r;
	JButton b1[]=new JButton[5],b2[]=new JButton[24];
	String s1[]= {"MC","MR","M+","M-","MS"};
	String s2[]= {"%","sqrt","sqre"," 1/x","CE","C","\u232b","/","7","8","9","*","4","5","6","-","1","2","3","+","\u2213","0",".","="};
	boolean flagconcat=true;
	char currentOperator;
	 calculator1()
	{
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		ptop=new JPanel();
		ptop.setLayout(new GridLayout(3,1));
		lb1h=new JLabel("",SwingConstants.RIGHT);
		lb1h.setFont(new Font(Font.SANS_SERIF,Font.BOLD,30));
		lb2r=new JLabel("0",SwingConstants.RIGHT);
		lb2r.setFont(new Font(Font.SANS_SERIF,Font.BOLD,30));
		pmid=new JPanel();
		pmid.setLayout(new GridLayout(1,5));
		for(int i=0;i<s1.length;i++)
		{
			b1[i]=new JButton(s1[i]);
			b1[i].setBackground(new Color(245,245,239));
			b1[i].addMouseListener(new MouseAdapter() {
				public void mouseEntered(MouseEvent me)
				{
					JButton btn=(JButton)me.getSource();
					btn.setBackground(new Color(100,115,155));
				}
				public void mouseExited(MouseEvent me) {
					JButton btn=(JButton)me.getSource();
					btn.setBackground(new Color(245,245,239));
				}
			});
			b1[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) {
					JButton btn=(JButton)ae.getSource();
					String str=btn.getText();
					if(str.equalsIgnoreCase("mc"))
					{
						memory=0;
					}
					else if(str.equalsIgnoreCase("mr"))
					{
						lb2r.setText(formatCurrentValue(memory+""));
						flagconcat=false;
					}
					if(str.equalsIgnoreCase("m+"))
					{
						memory+=Double.parseDouble(lb2r.getText());
						flagconcat=false;
					}
					if(str.equalsIgnoreCase("m-"))
					{
						memory-=Double.parseDouble(lb2r.getText());
						flagconcat=false;
					}
					if(str.equalsIgnoreCase("ms"))
					{
						memory=Double.parseDouble(lb2r.getText());
						flagconcat=false;
					}
					}
			});
			pmid.add(b1[i]);
			
		}
		ptop.add(lb1h);
		ptop.add(lb2r);
		ptop.add(pmid);
		add(ptop,"North");
		pbtm=new JPanel();
		pbtm.setLayout(new GridLayout(6,4));
		for(int i=0;i<s2.length;i++)
		{
			b2[i]=new JButton(s2[i]);
			b2[i].setFocusable(false);
			if(Character.isDigit(s2[i].charAt(0)))
			{
				b2[i].setBackground(new Color(255,255,255));
			    b2[i].addMouseListener(new MouseAdapter() {
				public void mouseEntered(MouseEvent me) {
					JButton btn=(JButton)me.getSource();
					btn.setBackground(new Color(100,140,128));
				}
				public void mouseExited(MouseEvent me)
				{
					JButton btn=(JButton)me.getSource();
					btn.setBackground(new Color(255,255,255));
					}
				});
			b2[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae)
				{
					JButton btn=(JButton)ae.getSource();
					String str=btn.getText();
					//char ch=btn.getText().charAt(0);
					String str2=lb2r.getText();
					if(flagconcat==true)
						{//str2+=ch;
						str2+=str;
						lb2r.setText(formatCurrentValue(str2));}
					else
						{//str2=ch+"";
						str2=str;
						lb2r.setText(formatCurrentValue(str2));
						flagconcat=true;}
					
				}
			});
			}
			else
			{
				b2[i].setBackground(new Color(245,245,239));
				b2[i].addMouseListener(new MouseAdapter() {
					public void mouseEntered(MouseEvent me) {
						JButton btn=(JButton)me.getSource();
						btn.setBackground(new Color(100,140,128));
					}
					public void mouseExited(MouseEvent me)
					{
						JButton btn=(JButton)me.getSource();
						btn.setBackground(new Color(245,245,239));
					}
				});
				b2[i].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent ae)
					{
						JButton btn=(JButton)ae.getSource();
						String str=btn.getText();
						//for operators
						if(isOperator(str.charAt(0)))
						{
							flagconcat=false;
							currentNumber=Double.parseDouble(lb2r.getText());
							//lastNumber=currentNumber;
							if(currentOperator!='\u0000')
							{
								switch(currentOperator) {
								case '+':
									lastNumber=lastNumber+currentNumber;
									break;
								case '-':
									lastNumber=lastNumber-currentNumber;
									break;
								case '*':
									lastNumber=lastNumber*currentNumber;
									break;
								case '/':
									lastNumber=lastNumber/currentNumber;
									break;
								default:
									break;
								}
								lb2r.setText(formatCurrentValue(lastNumber+""));
							}
							currentOperator=str.charAt(0);
							lastNumber=Double.parseDouble(lb2r.getText());
							lb1h.setText(lb1h.getText()+ formatCurrentValue(currentNumber+"")+currentOperator);
						}
						else if(str.equalsIgnoreCase("%")) {
							currentNumber=Double.parseDouble(lb2r.getText());
							currentNumber=currentNumber/100;
							lb2r.setText(formatCurrentValue(currentNumber+""));
						}
						else if(str.equalsIgnoreCase("sqrt")) {
							currentNumber=Double.parseDouble(lb2r.getText());
							currentNumber=Math.sqrt(currentNumber);
							lb2r.setText(formatCurrentValue(currentNumber+""));
						}
						else if(str.equalsIgnoreCase("sqr")) {
							currentNumber=Double.parseDouble(lb2r.getText());
							currentNumber=currentNumber*currentNumber;
							lb2r.setText(formatCurrentValue(currentNumber+""));
							flagconcat=false;
						}
						else if(str.equalsIgnoreCase(" 1/x")) {
							currentNumber=Double.parseDouble(lb2r.getText());
							currentNumber=1/currentNumber;
							lb2r.setText(formatCurrentValue(currentNumber+""));
							flagconcat=false;
						}
						else if(str.equalsIgnoreCase("ce")) {
							lb2r.setText("0");
							flagconcat=false;
						}
						else if(str.equalsIgnoreCase("c")) {
							lb2r.setText("0");
							currentOperator='\u0000';
							lastNumber=0;
							currentNumber=0;
							flagconcat=false;
							lb1h.setText("");
						}
						else if(str.equalsIgnoreCase("\u232b")) {
							String s1=lb2r.getText();
							lb2r.setText(formatCurrentValue(s1.substring(0, s1.length()-1)));
							flagconcat=false;
						}
						else if(str.equalsIgnoreCase("\u2213")) {
							currentNumber=Double.parseDouble(lb2r.getText());
							currentNumber=-currentNumber;
							lb2r.setText(formatCurrentValue(currentNumber+""));
							flagconcat=false;
						}
						else if(str.equalsIgnoreCase(".")) {
							String s1=lb2r.getText();
							int pos=s1.indexOf(".");
							if(pos==-1)//if not found
								lb2r.setText(s1+".");
						}
						else if(str.equalsIgnoreCase("=")) {
							currentNumber=Double.parseDouble(lb2r.getText());
							switch(currentOperator) {
								case '+':
									lastNumber=lastNumber+currentNumber;
									break;
								case '-':
									lastNumber=lastNumber-currentNumber;
									break;
								case '*':
									lastNumber=lastNumber*currentNumber;
									break;
								case '/':
									lastNumber=lastNumber/currentNumber;
									break;
								default:
									break;
							}
							lb2r.setText(formatCurrentValue(lastNumber+""));
							lb1h.setText("");
							currentOperator='\u0000';
							lastNumber=0.0;
							flagconcat=false;
						}
						
				
			}
				});
		
			}
			
			pbtm.add(b2[i]);
		}
		add(pbtm);
		setFocusable(true);
		addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent ke)
			{
				char ch=ke.getKeyChar();
				if(ch==10)
				{
					b2[23].doClick();
				}
				else if(ch==27)
				{
					b2[5].doClick();
				}
				else
				{
					for(int i=0;i<s2.length;i++) {
						if(s2[i].equals(ch+ ""))
						{
							b2[i].doClick();
						}
						
						
					}
				}
			}
		});
		setSize(350,500);
		setResizable(false);
		
		setVisible(true);
		setTitle("Calculator 2.0");
	}
	 boolean isOperator(char ch) {
			if(ch=='+' || ch== '-' || ch== '*' || ch== '/')
				return true;
			return false;
		}
	 String formatCurrentValue(String val) {
			if(val.isEmpty())
				return "0";
			double d=Double.parseDouble(val);
			if(d==(int)d)
				return (int)d+"";
			else
				return d+"";
		}	
	public static void main(String args[])
	{		new calculator1();
}
}