/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithmclarificationclone;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.KeyboardFocusManager;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;


/**
 *
 * @author Minh
 */
//public class AlgorithmClarificationClone {
//
//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String[] args) {
//        // TODO code application logic here
//    }
//    
//}

class MyMouseAdapter extends MouseAdapter
{
    private JFrame jFrame;

    MyMouseAdapter(JFrame jFrame)
    {
        this.jFrame=jFrame;
    }
}

public class AlgorithmClarificationClone{
    
    boolean checkExit = false;
    public JPanel contentPanel;
    private MyPanel panel1;
    private MyPanel2 panel2;
    private MyPanel1 panel3;
    private MyPanel4 panel4;//Home Panel for MyPanel2.
    private MyPanel5 panel5;//Home Panel for MyPanel1.
//    public MyPanel5 panel5;
//    public MyPanel6 panel6;
//    public MyPanel7 panel7;
//    public MyPanel8 panel8;
//    public MyPanel9 panel9;
//    public MyPanel10 panel10;
    
    
    private void setPanelHome(JPanel panel){
        
        //add components:
//        panel.add(homeLabel1);
//        panel.add(homeLabel2);
//        panel.add(homeLabel3);
//        panel.add(homeLabel4);
//        panel.add(homeLabel5);
    }
    
    private void displayGUI()
    {
        JFrame frame = new JFrame("Ứng dụng giúp tìm hiểu thuật toán SJF (Shortest Job First) - Điều phối tiến trình cho CPU và SSTF (Shortest Seek Time First) - Dịch chuyển đầu đọc ghi đĩa để tối ưu seek time.");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addMouseListener(new MyMouseAdapter(frame));
        JPanel contentPanel1 = new JPanel();
        contentPanel1.setBorder(
            BorderFactory.createEmptyBorder(0, 0, 0, 0));
        contentPanel1.setLayout(new CardLayout());
        panel1 = new MyPanel(contentPanel1);
        panel2 = new MyPanel2();
        panel3 = new MyPanel1();
        panel4 = new MyPanel4();
        panel5 = new MyPanel5();
        
        contentPanel1.add(panel1, "Panel 1"); 
        contentPanel1.add(panel2, "Panel 2");
        contentPanel1.add(panel3, "Panel 3");
        contentPanel1.add(panel4, "Panel 4");
        contentPanel1.add(panel5, "Panel 5");
        frame.setContentPane(contentPanel1);
        frame.pack();   
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
//        frame.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
    }
   
    class MyPanel extends JPanel {

        public JLabel jcomp2;
        public JLabel jcomp21;
        public JLabel jcomp0;
        public JLabel jcomp1;
        public JLabel jcomp01;
        public JLabel jcomp5;
        public JLabel jcomp6;
        public JButton jcomp4; 
        public JButton jcomp3;
        private JPanel contentPane;
        KeyEvent ke;
        public MyPanel(JPanel panel){

            contentPane = panel;
            super.setBackground(new Color(231, 221, 182));
            //construct components
            jcomp0 = new JLabel ("- Đây là ứng dụng giúp bạn tìm hiểu về thuật toán SJF");
            jcomp01 = new JLabel ("(Shortest Job First) và SSTF(Shortest Seek Time First).");
            jcomp2 = new JLabel ("- Bạn hãy chọn thuật toán mà bạn muốn tìm hiểu dưới ");
            jcomp21 = new JLabel (" đây bằng cách nhấn vào chữ bên dưới: ");
            jcomp3 = new JButton ("OS - CPU Scheduling: SJF (Shortest Job First)");
            jcomp4 = new JButton("OS - Disk Scheduling: SSTF (Shortest Seek Time First)");
//            jcomp1 = new JLabel("+ Nhấn \"1\" để chọn thuật toán lập lịch cho CPU: SJF.");
//            jcomp5 = new JLabel("+ Nhấn \"2\" để chọn thuật toán lập lịch cho đĩa: SSTF.");
//            jcomp6 = new JLabel("- Bạn có thể nhấn vào chữ bên dưới để chọn thuật toán:");
            //adjust size and set layout
            super.setPreferredSize(new Dimension (1200, 600));
            super.setLayout(null);
            
            //set component bounds (only needed by Absolute Positioning)
//            jcomp1.setBounds(20, 80, 1200, 225);
//            jcomp5.setBounds(20, 150, 1200, 225);
//            jcomp6.setBounds(10, 180, 1200, 225);
            jcomp2.setBounds(10, 80, 1200, 225);
            jcomp21.setBounds(10, 150, 1200, 225);
            jcomp0.setBounds (10, -60, 1200, 225);
            jcomp01.setBounds (10, 10, 1200, 225);
            jcomp3.setLocation(0, 325);
            jcomp3.setSize(1200, 100);
            jcomp4.setLocation(0, 465);
            jcomp4.setSize(1200, 100);


            //change the font size:
            jcomp3.setFont(new Font("Cambria", Font.PLAIN, 50));
            jcomp3.setBackground(new Color(62, 78, 83));
            jcomp3.setForeground(new Color(150, 198, 104));
            jcomp4.setFont(new Font("Cambria", Font.PLAIN, 50));
            jcomp4.setBackground(new Color(62, 78, 83));
            jcomp4.setForeground(new Color(150, 198, 104));
            
            jcomp0.setFont(jcomp0.getFont().deriveFont(45f));
//            jcomp0.setFont(new Font("C", Font.BOLD, 45));
//            jcomp01.setFont(new Font("Comic Sans MS", Font.BOLD, 45));
//            jcomp2.setFont(new Font("Comic Sans MS", Font.BOLD, 45));
//            jcomp21.setFont(new Font("Comic Sans MS", Font.BOLD, 45));
//            jcomp0.setFont(new Font("Comic Sans MS", Font.PLAIN,));
            jcomp0.setForeground(new Color(92, 62, 106));
            jcomp01.setFont(jcomp0.getFont().deriveFont(45f));
            jcomp01.setForeground(new Color(92, 62, 106));
            jcomp2.setFont(jcomp2.getFont().deriveFont(45f));
            jcomp2.setForeground(new Color(70, 96, 138));
            jcomp21.setFont(jcomp2.getFont().deriveFont(45f));
            jcomp21.setForeground(new Color(70, 96, 138));
//            jcomp1.setFont(jcomp0.getFont().deriveFont(40f));
//            jcomp1.setForeground(new Color(131, 99, 89));
//            jcomp5.setFont(jcomp2.getFont().deriveFont(40f));
//            jcomp5.setForeground(new Color(131, 99, 89));
//            jcomp6.setFont(jcomp2.getFont().deriveFont(45f));
//            jcomp6.setForeground(new Color(131, 99, 89));
            
            //add components
            super.add(jcomp0);
            super.add(jcomp01);
            super.add(jcomp2);
            super.add(jcomp21);
//            super.add(jcomp1);
//            super.add(jcomp5);
//            super.add(jcomp6);
            super.add(jcomp3);  
            super.add(jcomp4);
            
            //handle Robot class to initial Key Event when pressed a key in a panel:
//            Robot r = new Robot();
//            r.keyPress(KeyEvent.VK_F4);
//            r.keyRelease(KeyEvent.VK_F4);
//            

            //demo:
//            Action act;
//            act = new ActionImpl(driver);
//            act.sendkeys(Keys.ENTER).build().perform();

            //handle Key Event when Typing "1" and "2":
            
//            super.addKeyListener(new Keychecker());
            
//            panel.addKeyListener(new KeyListener() {
//                @Override
//                public void keyPressed(KeyEvent ke) {
//                    if(ke.getKeyCode() == KeyEvent.VK_F4){
//                        System.out.println("1");
//                        CardLayout cardLayout = (CardLayout) contentPane.getLayout();
//                        cardLayout.next(contentPane);
//                    } else if(ke.getKeyCode() == KeyEvent.VK_F5){
//                        System.out.println("2");
//                        CardLayout cardLayout = (CardLayout) contentPane.getLayout();
//                        cardLayout.next(contentPane);
//                        cardLayout.next(contentPane);
//                    }
//                }
//                @Override
//                public void keyReleased(KeyEvent ke) {}
//                @Override
//                public void keyTyped(KeyEvent ke) {}
//            });
            
            //handle 2nd:
//            if (IsKeyPressed.isAPressed()) {
//                // do your thing.
//            } else {
//            }

            //handle lately:
//            try{
//                int key = ke.getKeyCode();
//                
//            }catch(NullPointerException ex){
//                System.out.println("Error:"+ex.getMessage());
//            }
            //settle action Listener event Clicked:
            jcomp4.addActionListener((ActionEvent ae) -> {
                CardLayout cardLayout1 = (CardLayout)contentPane.getLayout();
                cardLayout1.next(contentPane);
                cardLayout1.next(contentPane);
                cardLayout1.next(contentPane);
                cardLayout1.next(contentPane);
            });
            jcomp3.addActionListener((ActionEvent ae) -> {
                CardLayout cardLayout = (CardLayout) contentPane.getLayout();
                cardLayout.next(contentPane);
                cardLayout.next(contentPane);
                cardLayout.next(contentPane);
            });
            
//            keyEventPressed(ke);
        }

        private MyPanel() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        
        //handle ket Event when clicking or typing 1 and 2:
        private void keyEventPressed(KeyEvent ke){
            if( ke.getKeyCode() == KeyEvent.VK_1){
                    CardLayout cardLayout = (CardLayout) contentPane.getLayout();
                    cardLayout.next(contentPane);
                }
                else if( ke.getKeyCode() == KeyEvent.VK_2){
                    CardLayout cardLayout = (CardLayout) contentPane.getLayout();
                    cardLayout.next(contentPane);
                    cardLayout.next(contentPane);
                }
        }

//        @Override
//        public void keyTyped(KeyEvent e) {
//            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//        }
//
//        @Override
//        public void keyPressed(KeyEvent e) {
//            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//        }
//
//        @Override
//        public void keyReleased(KeyEvent e) {
//            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//        }

        private class ActionImpl implements Action {

            public ActionImpl() {
            }

            @Override
            public Object getValue(String key) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void putValue(String key, Object value) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void setEnabled(boolean b) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public boolean isEnabled() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void addPropertyChangeListener(PropertyChangeListener listener) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void removePropertyChangeListener(PropertyChangeListener listener) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        }
    }
    
    class SubmitButton implements ActionListener, KeyListener {
        JButton nameInput;
        public SubmitButton(JButton jButton){
            nameInput = jButton;
        }
        @Override
        public void actionPerformed(ActionEvent submitClicked) {
//            JOptionPane.showMessageDialog(null , "You've Submitted the name " + nameInput.getText());
        }
        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode()==KeyEvent.VK_F4){
                System.out.println("Hello");
            }
//            JOptionPane.showMessageDialog(null , "You've Submitted the name " + nameInput.getText());
        }
        @Override
        public void keyReleased(KeyEvent arg0) {
            // TODO Auto-generated method stub
        }
        @Override
        public void keyTyped(KeyEvent arg0) {

        }
    }
    
    class Keychecker extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent event) {
            char ch = event.getKeyChar();
            System.out.println(event.getKeyChar());
        }
    }
    
    class IsKeyPressed {
        public volatile boolean aPressed = false;
        public boolean isAPressed() {
            synchronized (IsKeyPressed.class) {
                return aPressed;
            }
        }

        public void main(String[] args) {
            KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher((KeyEvent ke) -> {
                synchronized (IsKeyPressed.class) {
                    switch (ke.getID()) {
                        case KeyEvent.KEY_PRESSED:
                            if (ke.getKeyCode() == KeyEvent.VK_A) {
                                aPressed = true;
                            }
                            break;
                            
                        case KeyEvent.KEY_RELEASED:
                            if (ke.getKeyCode() == KeyEvent.VK_A) {
                                aPressed = false;
                            }
                            break;
                    }
                    return false;
                }
            });
    }
}
//    class KeyListener0 implements KeyListener {
//
//        @Override
//        public void keyTyped(KeyEvent e) {
//            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//        }
//
//        @Override
//        public void keyPressed(KeyEvent e) {
//            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//        }
//
//        @Override
//        public void keyReleased(KeyEvent e) {
//            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//        }
//        
//    }

    class MyPanel2 extends JPanel implements MouseListener{
        public Drawing d;
        public JLabel inforLabel;
        public JLabel inforLabel1;
        public JLabel inforLabel2;
        public JLabel inforLabel3;
        public JLabel inforLabel4;
        public JPanel jPanel1;
        public JPanel jPanel2;
        public JPanel jPanel3;
        public JPanel jPanel4;
        public JPanel jPanel5;
        public JPanel jPanel6;
        public JPanel jPanel7;
        public JPanel jPanel8;
        public JPanel jPanel9;
        public JPanel jPanel10;
        public JPanel jPanel11;
        public JPanel jPanel12;
        
        //method which use for handling color in mouse Clicked event
        public void setColor(JPanel panel){
            panel.setBackground(new Color(41, 57, 80));
        }
        public void resetColor(JPanel [] panel, JPanel [] indicators){
            for (JPanel panel6 : panel) {
                panel6.setBackground(new Color(23, 36, 51));
            }
            for (JPanel indicator : indicators) {
                indicator.setOpaque(false);
            }
        }
        public void setColor1(JPanel panel){
            panel.setBackground(new Color(135, 155, 203));
        }
        
        public void setFalseAndChangeToTrue(boolean a){
            a = true;
        }
   
        public MyPanel2(){
            
            //construct components
            inforLabel = new JLabel("Home");
            inforLabel1 = new JLabel("01");
            inforLabel2 = new JLabel("02");
            inforLabel3 = new JLabel("03");
            inforLabel4 = new JLabel("Exit");
            
            boolean checkHomeLabel = false;
            
            d = new Drawing("          Shortest Job First Algorithm", new Font("Cambria", Font.ITALIC, 50), new Color(181, 218, 181));
            jPanel1 = new JPanel();
            jPanel2 = new JPanel();
            jPanel3 = new JPanel();
            jPanel4 = new JPanel();
            jPanel5 = new JPanel();
            jPanel6 = new JPanel();
            jPanel7 = new JPanel();
            jPanel8 = new JPanel();
            jPanel9 = new JPanel();
            jPanel10 = new JPanel();
            jPanel11 = new JPanel();
            jPanel12 = new JPanel();
            
            //adjust size and set layout
            
            super.setPreferredSize (new Dimension (1200, 600));
            super.setLayout(null);
            
            //customize your own label:
            inforLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
            inforLabel.setForeground(Color.WHITE);
            inforLabel1.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
            inforLabel1.setForeground(Color.white);
            inforLabel2.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
            inforLabel2.setForeground(Color.white);
            inforLabel3.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
            inforLabel3.setForeground(Color.white);
            inforLabel4.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
            inforLabel4.setForeground(Color.WHITE);
            
            //Home Label:
            JPanel homePanel = new JPanel();
            JLabel homeLabel1 = new JLabel("- Danh sách các thành phần của ứng dụng:");
            JLabel homeLabel2 = new JLabel("+ Part 1: Các khái niệm và ưu điểm, nhược điểm của thuật toán.");
            JLabel homeLabel3 = new JLabel("+ Part 2: Tìm hiểu về SJF không ưu tiên trước (non preemptive).");
            JLabel homeLabel4 = new JLabel("+ Part 3: Tìm hiểu về SJF có ưu tiên trước (preemptive).");
            JLabel homeLabel5 = new JLabel("+ Exit: Trở về màn hình ban đầu của ứng dụng.");

            //customize ur own Labels' Home:
            homeLabel1.setForeground(Color.red);
            homeLabel1.setForeground(Color.red);
            homeLabel1.setForeground(Color.red);
            homeLabel1.setForeground(Color.red);
            homeLabel1.setForeground(Color.red);

            homeLabel1.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
            homeLabel2.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
            homeLabel3.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
            homeLabel4.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
            homeLabel5.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));

            homeLabel1.setBounds(120, 60, 980, 20);
            homeLabel1.setBounds(120, 80, 980, 20);
            homeLabel1.setBounds(120, 100, 980, 20);
            homeLabel1.setBounds(120, 120, 980, 20);
            homeLabel1.setBounds(120, 140, 980, 20);
        
            
            //default customize color for panel:
//            setColor(jPanel4);
//            jPanel3.setOpaque(true);
//            resetColor(new JPanel[]{jPanel6, jPanel8, jPanel10, jPanel12}, new JPanel[]{jPanel5, jPanel7, jPanel9, jPanel12});
            
            //handle mouseClicked by MouseListener
            jPanel4.addMouseListener(new MouseAdapter(){
//                public void actionPerformed(MouseEvent e) {
//                    CardLayout cardLayout2 = (CardLayout)contentPane.getLayout();
//                    cardLayout2.next(contentPane);
//                    cardLayout2.next(contentPane);
//                    cardLayout2.next(contentPane);
//                }
                @Override
                public void mouseClicked(MouseEvent e){
//                    setVisible(false);
//                    MyPanel mp = new MyPanel();
//                    mp.setVisible(false);
//                    setColor(jPanel4);
//                    jPanel3.setOpaque(true);
//                    resetColor(new JPanel[]{jPanel6, jPanel8, jPanel10, jPanel12}, new JPanel[]{jPanel5, jPanel7, jPanel9, jPanel12});
                    jPanel3.setBackground(new Color(135, 155, 203));
                    jPanel4.setBackground(new Color(41, 57, 80));
                    jPanel5.setBackground(new Color(23, 36, 51));
                    jPanel6.setBackground(new Color(23, 36, 51));
                    jPanel7.setBackground(new Color(23, 36, 51));
                    jPanel8.setBackground(new Color(23, 36, 51));
                    jPanel9.setBackground(new Color(23, 36, 51));
                    jPanel10.setBackground(new Color(23, 36, 51));
                    jPanel11.setBackground(new Color(23, 36, 51));
                    jPanel12.setBackground(new Color(23, 36, 51));
                    inforLabel1.setForeground(Color.white);
                    inforLabel1.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
                    inforLabel2.setForeground(Color.white);
                    inforLabel2.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
                    inforLabel3.setForeground(Color.white);
                    inforLabel3.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
                    inforLabel4.setForeground(Color.white);
                    inforLabel4.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
                    inforLabel.setForeground(Color.white);
                    inforLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
                    //method setPanelHome():
//                    setFalseAndChangeToTrue(checkHomeLabel);
//                    JPanel j = new MyPanel4();
//                    JInternalFrame newFrame = new JInternalFrame("Ứng dụng giúp tìm hiểu thuật toán SJF (Shortest Job First) - Lập lịch CPU và SSTF (Shortest Seek Time First) - Lập lịch đĩa");
//                    newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//                    newFrame.setContentPane(j);
//                    newFrame.pack();
//                    newFrame.setBounds(0, 0, 1100, 650);
//                    newFrame.setLocationByPlatform(true);
//                    newFrame.setVisible(true);
//                    mp.setVisible(false);
                    
                }
            });
//            
            jPanel6.addMouseListener(new MouseAdapter(){
                @Override
                public void mouseClicked(MouseEvent e){
//                    setColor(jPanel6);
//                    jPanel5.setOpaque(false);
//                    resetColor(new JPanel[]{jPanel4, jPanel8, jPanel10, jPanel12}, new JPanel[]{jPanel3, jPanel7, jPanel9, jPanel12});
                    jPanel5.setBackground(new Color(135, 155, 203));
                    jPanel6.setBackground(new Color(41, 57, 80));
                    jPanel3.setBackground(new Color(23, 36, 51));
                    jPanel4.setBackground(new Color(23, 36, 51));
                    jPanel7.setBackground(new Color(23, 36, 51));
                    jPanel8.setBackground(new Color(23, 36, 51));
                    jPanel9.setBackground(new Color(23, 36, 51));
                    jPanel10.setBackground(new Color(23, 36, 51));
                    jPanel11.setBackground(new Color(23, 36, 51));
                    jPanel12.setBackground(new Color(23, 36, 51));
                    inforLabel.setForeground(Color.white);
                    inforLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
                    inforLabel2.setForeground(Color.white);
                    inforLabel2.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
                    inforLabel3.setForeground(Color.white);
                    inforLabel3.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
                    inforLabel4.setForeground(Color.white);
                    inforLabel4.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
                    inforLabel1.setForeground(Color.white);
                    inforLabel1.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
                }
            });
//            
            jPanel8.addMouseListener(new MouseAdapter(){
                @Override
                public void mouseClicked(MouseEvent e){
//                    setColor(jPanel8);
//                    jPanel7.setOpaque(false);
//                    resetColor(new JPanel[]{jPanel6, jPanel4, jPanel10, jPanel12}, new JPanel[]{jPanel5, jPanel3, jPanel9, jPanel12});
                    jPanel7.setBackground(new Color(135, 155, 203));
                    jPanel8.setBackground(new Color(41, 57, 80));
                    jPanel3.setBackground(new Color(23, 36, 51));
                    jPanel4.setBackground(new Color(23, 36, 51));
                    jPanel5.setBackground(new Color(23, 36, 51));
                    jPanel6.setBackground(new Color(23, 36, 51));
                    jPanel9.setBackground(new Color(23, 36, 51));
                    jPanel10.setBackground(new Color(23, 36, 51));
                    jPanel11.setBackground(new Color(23, 36, 51));
                    jPanel12.setBackground(new Color(23, 36, 51));
                    inforLabel.setForeground(Color.white);
                    inforLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
                    inforLabel2.setForeground(Color.white);
                    inforLabel2.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
                    inforLabel3.setForeground(Color.white);
                    inforLabel3.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
                    inforLabel4.setForeground(Color.white);
                    inforLabel4.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
                    inforLabel1.setForeground(Color.white);
                    inforLabel1.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
                }
            });
//            
            jPanel10.addMouseListener(new MouseAdapter(){
                @Override
                public void mouseClicked(MouseEvent e){
//                    setColor(jPanel10);
//                    jPanel9.setOpaque(false);
//                    resetColor(new JPanel[]{jPanel6, jPanel8, jPanel4, jPanel12}, new JPanel[]{jPanel5, jPanel7, jPanel3, jPanel12});
                    jPanel9.setBackground(new Color(135, 155, 203));
                    jPanel10.setBackground(new Color(41, 57, 80));
                    jPanel3.setBackground(new Color(23, 36, 51));
                    jPanel4.setBackground(new Color(23, 36, 51));
                    jPanel7.setBackground(new Color(23, 36, 51));
                    jPanel8.setBackground(new Color(23, 36, 51));
                    jPanel5.setBackground(new Color(23, 36, 51));
                    jPanel6.setBackground(new Color(23, 36, 51));
                    jPanel11.setBackground(new Color(23, 36, 51));
                    jPanel12.setBackground(new Color(23, 36, 51));
                    inforLabel.setForeground(Color.white);
                    inforLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
                    inforLabel1.setForeground(Color.white);
                    inforLabel1.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
                    inforLabel2.setForeground(Color.white);
                    inforLabel2.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
                    inforLabel4.setForeground(Color.white);
                    inforLabel4.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
                    inforLabel3.setForeground(Color.white);
                    inforLabel3.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
                }
            });
//            
            jPanel12.addMouseListener(new MouseAdapter(){
                @Override
                public void mouseClicked(MouseEvent e){
//                    setColor(jPanel12);
//                    jPanel11.setOpaque(false);
//                    resetColor(new JPanel[]{jPanel6, jPanel8, jPanel10, jPanel4}, new JPanel[]{jPanel5, jPanel7, jPanel9, jPanel3});
                    jPanel11.setBackground(new Color(135, 155, 203));
                    jPanel12.setBackground(new Color(41, 57, 80));
                    jPanel3.setBackground(new Color(23, 36, 51));
                    jPanel4.setBackground(new Color(23, 36, 51));
                    jPanel7.setBackground(new Color(23, 36, 51));
                    jPanel8.setBackground(new Color(23, 36, 51));
                    jPanel9.setBackground(new Color(23, 36, 51));
                    jPanel10.setBackground(new Color(23, 36, 51));
                    jPanel5.setBackground(new Color(23, 36, 51));
                    jPanel6.setBackground(new Color(23, 36, 51));
                    inforLabel4.setForeground(Color.white);
                    inforLabel4.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
                    inforLabel.setForeground(Color.white);
                    inforLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
                    inforLabel1.setForeground(Color.white);
                    inforLabel1.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
                    inforLabel2.setForeground(Color.white);
                    inforLabel2.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
                    inforLabel3.setForeground(Color.white);
                    inforLabel3.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
                    int click = JOptionPane.showConfirmDialog(null, "Bạn muốn thoát chương trình hiện tại đúng không?");
                    if(click == JOptionPane.YES_OPTION)
                        setVisible(false);
                }
            });
            
            //add components for Panels' Home:
            if(checkHomeLabel == true){
                super.add(homeLabel1);
                super.add(homeLabel2);
                super.add(homeLabel3);
                super.add(homeLabel4);
                super.add(homeLabel5);
            }
            
            //set component bounds (only needed by Absolute Positioning)
            d.setSize(1200, 60);
            jPanel1.setBackground(Color.gray);
            jPanel1.setBounds(0, 0, 1200, 60);
            jPanel2.setBounds(0, 50, 120, 700);
            jPanel2.setBackground(new Color(23, 35, 51));
            jPanel3.setBackground(new Color(135, 155, 203));
            jPanel3.setBounds(0, 60, 10, 90);
            jPanel4.setBackground(new Color(41, 57, 80));
            jPanel4.setBounds(0, 60, 120, 90);
            jPanel5.setBounds(0, 150, 10, 90);
            jPanel5.setBackground(new Color(23, 36, 51));
            jPanel6.setBackground(new Color(23, 36, 51));
            jPanel6.setBounds(0, 150, 120, 90);
            jPanel7.setBounds(0, 240, 10, 90);
            jPanel7.setBackground(new Color(23, 36, 51));
            jPanel8.setBackground(new Color(23, 36, 51));
            jPanel8.setBounds(0, 240, 120, 90);
            jPanel9.setBounds(0, 330, 10, 90);
            jPanel9.setBackground(new Color(23, 36, 51));
            jPanel10.setBackground(new Color(23, 36, 51));
            jPanel10.setBounds(0, 330, 120, 90);
            jPanel11.setBounds(0, 420, 10, 90);
            jPanel11.setBackground(new Color(23, 36, 51));
            jPanel12.setBackground(new Color(23, 36, 51));
            jPanel12.setBounds(0, 420, 120, 90);
            
            inforLabel.setBounds(25, 60, 120, 90);
            inforLabel1.setBounds(45, 150, 120, 90);
            inforLabel2.setBounds(45, 240, 120, 90);
            inforLabel3.setBounds(45, 330, 120, 90);
            inforLabel4.setBounds(35, 420, 120, 90);
            
            
            //add components
            super.add(inforLabel);
            super.add(inforLabel1);
            super.add(inforLabel2);
            super.add(inforLabel3);
            super.add(inforLabel4);
            super.add(jPanel3);
            super.add(jPanel5);
            super.add(jPanel7);
            super.add(jPanel9);
            super.add(jPanel11);
            super.add(jPanel4);
            super.add(jPanel6);
            super.add(jPanel8);
            super.add(jPanel10);
            super.add(jPanel12);
            super.add(d);
            super.add(jPanel1);
            super.add(jPanel2);
            
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mousePressed(MouseEvent e) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mouseExited(MouseEvent e) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }
    
    class MyPanel1 extends JPanel{
        boolean checkExit = false;

        public Drawing d;
        public JPanel jPanel1;
        public JPanel jPanel2;
        public JButton jButton1;
        public JButton jButton2;
        public JLabel inforLabel;
        public JLabel inforLabel1;
        public JLabel inforLabel2;
        public JLabel inforLabel4;
        public JPanel jPanel3;
        public JPanel jPanel4;
        public JPanel jPanel5;
        public JPanel jPanel6;
        public JPanel jPanel7;
        public JPanel jPanel8;
        public JPanel jPanel11;
        public JPanel jPanel12;
        
        public MyPanel1(){
            
            //construct components:
            d = new Drawing("    Shortest Seek Time First Algorithm", new Font("Cambria", Font.ITALIC, 50), new Color(160, 242, 210));
            jButton1 = new JButton("What's up.");
            jButton2 = new JButton("Ey yo.");
            
            jPanel1 = new JPanel();
            jPanel2 = new JPanel();
            jPanel3 = new JPanel();
            jPanel4 = new JPanel();
            jPanel5 = new JPanel();
            jPanel6 = new JPanel();
            jPanel7 = new JPanel();
            jPanel8 = new JPanel();
            jPanel11 = new JPanel();
            jPanel12 = new JPanel();
            
            inforLabel = new JLabel("Home");
            inforLabel1 = new JLabel("01");
            inforLabel2 = new JLabel("02");
            inforLabel4 = new JLabel("Exit");
            
            //adjust size and sey layout:
            super.setPreferredSize(new Dimension(1200, 600));
            super.setLayout(null);
            
            //customize your own attributes:
            d.setSize(1200, 60);
            
            inforLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
            inforLabel.setForeground(Color.white);
            inforLabel1.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
            inforLabel1.setForeground(Color.white);
            inforLabel2.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
            inforLabel2.setForeground(Color.white);
            inforLabel4.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
            inforLabel4.setForeground(Color.WHITE);
            
            //handle Mouse Clicked Event:
            jPanel12.addMouseListener(new MouseAdapter(){
                @Override
                public void mouseClicked(MouseEvent e){
                    jPanel3.setBackground(new Color(23, 36, 51));
                    jPanel4.setBackground(new Color(23, 36, 51));
                    jPanel7.setBackground(new Color(23, 36, 51));
                    jPanel8.setBackground(new Color(23, 36, 51));
                    jPanel5.setBackground(new Color(23, 36, 51));
                    jPanel6.setBackground(new Color(23, 36, 51));
                    jPanel11.setBackground(new Color(135, 155, 203));
                    jPanel12.setBackground(new Color(41, 57, 80));
                    inforLabel.setForeground(Color.white);
                    inforLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
                    inforLabel1.setForeground(Color.white);
                    inforLabel1.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
                    inforLabel2.setForeground(Color.white);
                    inforLabel2.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
                    inforLabel4.setForeground(Color.WHITE);
                    inforLabel4.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
                    int click = JOptionPane.showConfirmDialog(null, "Bạn muốn thoát chương trình hiện tại đúng không?");
                    if(click == JOptionPane.YES_OPTION)
                        setVisible(false); 
                }
            });
            
            jPanel4.addMouseListener(new MouseAdapter(){
                @Override
                public void mouseClicked(MouseEvent e){
                    jPanel5.setBackground(new Color(23, 36, 51));
                    jPanel6.setBackground(new Color(23, 36, 51));
                    jPanel7.setBackground(new Color(23, 36, 51));
                    jPanel8.setBackground(new Color(23, 36, 51));
                    jPanel11.setBackground(new Color(23, 36, 51));
                    jPanel12.setBackground(new Color(23, 36, 51));
                    jPanel3.setBackground(new Color(135, 155, 203));
                    jPanel4.setBackground(new Color(41, 57, 80));
                    inforLabel1.setForeground(Color.white);
                    inforLabel1.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
                    inforLabel2.setForeground(Color.white);
                    inforLabel2.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
                    inforLabel4.setForeground(Color.white);
                    inforLabel4.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
                    inforLabel.setForeground(Color.white);
                    inforLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
                }
            });
            
            jPanel6.addMouseListener(new MouseAdapter(){
                @Override
                public void mouseClicked(MouseEvent e){
                    jPanel3.setBackground(new Color(23, 36, 51));
                    jPanel4.setBackground(new Color(23, 36, 51));
                    jPanel7.setBackground(new Color(23, 36, 51));
                    jPanel8.setBackground(new Color(23, 36, 51));
                    jPanel11.setBackground(new Color(23, 36, 51));
                    jPanel12.setBackground(new Color(23, 36, 51));
                    jPanel5.setBackground(new Color(135, 155, 203));
                    jPanel6.setBackground(new Color(41, 57, 80));
                    inforLabel2.setForeground(Color.white);
                    inforLabel2.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
                    inforLabel.setForeground(Color.white);
                    inforLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
                    inforLabel4.setForeground(Color.white);
                    inforLabel4.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
                    inforLabel1.setForeground(Color.white);
                    inforLabel1.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
                }
            });
            
            jPanel8.addMouseListener(new MouseAdapter(){
                @Override
                public void mouseClicked(MouseEvent e){
                    jPanel3.setBackground(new Color(23, 36, 51));
                    jPanel4.setBackground(new Color(23, 36, 51));
                    jPanel5.setBackground(new Color(23, 36, 51));
                    jPanel6.setBackground(new Color(23, 36, 51));
                    jPanel11.setBackground(new Color(23, 36, 51));
                    jPanel12.setBackground(new Color(23, 36, 51));
                    jPanel7.setBackground(new Color(135, 155, 203));
                    jPanel8.setBackground(new Color(41, 57, 80));
                    inforLabel.setForeground(Color.white);
                    inforLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
                    inforLabel1.setForeground(Color.white);
                    inforLabel1.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
                    inforLabel4.setForeground(Color.white);
                    inforLabel4.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
                    inforLabel2.setForeground(Color.white);
                    inforLabel2.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
                }
            });
            
            //beyond handling event, customize again:
            
            jPanel1.setBackground(Color.gray);
            jPanel1.setBounds(0, 0, 1200, 60);
            jPanel2.setBackground(new Color(23, 35, 51));
            jPanel2.setBounds(0, 50, 120, 700);
            jButton1.setBounds(20, 100, 200, 100);
            jButton2.setBounds(40, 200, 200, 100);
            
            jPanel3.setBackground(new Color(135, 155, 203));
            jPanel3.setBounds(0, 60, 10, 90);
            jPanel4.setBackground(new Color(41, 57, 80));
            jPanel4.setBounds(0, 60, 120, 90);
            jPanel5.setBounds(0, 150, 10, 90);
            jPanel5.setBackground(new Color(23, 36, 51));
            jPanel6.setBackground(new Color(23, 36, 51));
            jPanel6.setBounds(0, 150, 120, 90);
            jPanel7.setBounds(0, 240, 10, 90);
            jPanel7.setBackground(new Color(23, 36, 51));
            jPanel8.setBackground(new Color(23, 36, 51));
            jPanel8.setBounds(0, 240, 120, 90);
            jPanel11.setBounds(0, 330, 10, 90);
            jPanel11.setBackground(new Color(23, 36, 51));
            jPanel12.setBackground(new Color(23, 36, 51));
            jPanel12.setBounds(0, 330, 120, 90);
            
            inforLabel.setBounds(25, 60, 120, 90);
            inforLabel1.setBounds(45, 150, 120, 90);
            inforLabel2.setBounds(45, 240, 120, 90);
            inforLabel4.setBounds(35, 330, 120, 90);
            
            //add the components:
            super.add(d);
            super.add(inforLabel);
            super.add(inforLabel1);
            super.add(inforLabel2);
            super.add(inforLabel4);
            super.add(jPanel3);
            super.add(jPanel5);
            super.add(jPanel7);
            super.add(jPanel11);
            super.add(jPanel4);
            super.add(jPanel6);
            super.add(jPanel8);
            super.add(jPanel12);
//            add(jButton1);
//            add(jButton2);
            super.add(jPanel1);
            super.add(jPanel2);

            
        }
    }
    
    //Handle MouseListener and mouseClicked method
    abstract class HandleMouseListener implements MouseListener {
        
    }
    
    class Drawing extends JComponent {
        String text;
        Font myFont;
        Color myTextColor;

        public Drawing(String textArg, Font f, Color textColor) {
            myFont = f;
            myTextColor = textColor;
            text = textArg;
        }

        @Override
        public void paintComponent(Graphics g) {
            g.setFont(myFont);
            g.setColor(myTextColor);
            g.drawString(text, 200, 45);
        }
    }
    
    class MyPanel4 extends MyPanel2 {
        public MyPanel4(){
            //Home Label:
            JPanel homePanel = new JPanel();
            JLabel homeLabel1 = new JLabel("- Danh sách các thành phần của ứng dụng:");
            JLabel homeLabel2 = new JLabel("+ Part 1: Các khái niệm và ưu điểm, nhược điểm ");
            JLabel homeLabel3 = new JLabel(" của thuật toán SJF.");
            JLabel homeLabel4 = new JLabel("+ Part 2: Tìm hiểu về SJF không ưu tiên trước");
            JLabel homeLabel5 = new JLabel(" (non pre - emptive).");
            JLabel homeLabel6 = new JLabel("+ Part 3: Tìm hiểu về SJF có ưu tiên trước");
            JLabel homeLabel7 = new JLabel(" (pre - emptive).");
            JLabel homeLabel8 = new JLabel("+ Exit: Trở về màn hình ban đầu của ứng dụng.");

            //customize ur own Labels' Home:
            homeLabel1.setForeground(new Color(255, 60, 36));
            homeLabel2.setForeground(new Color(154, 16, 62));
            homeLabel3.setForeground(new Color(154, 16, 62));
            homeLabel4.setForeground(new Color(154, 16, 62));
            homeLabel5.setForeground(new Color(154, 16, 62));
            homeLabel6.setForeground(new Color(154, 16, 62));
            homeLabel7.setForeground(new Color(154, 16, 62));
            homeLabel8.setForeground(new Color(154, 16, 62));

            homeLabel1.setFont(new Font("Cambria", Font.PLAIN, 50));
            homeLabel2.setFont(new Font("Cambria", Font.PLAIN, 50));
            homeLabel3.setFont(new Font("Cambria", Font.PLAIN, 50));
            homeLabel4.setFont(new Font("Cambria", Font.PLAIN, 50));
            homeLabel5.setFont(new Font("Cambria", Font.PLAIN, 50));
            homeLabel6.setFont(new Font("Cambria", Font.PLAIN, 50));
            homeLabel7.setFont(new Font("Cambria", Font.PLAIN, 50));
            homeLabel8.setFont(new Font("Cambria", Font.PLAIN, 50));

            homeLabel1.setBounds(130, 70, 1100, 60);
            homeLabel2.setBounds(130, 130, 1100, 60);
            homeLabel3.setBounds(130, 190, 1100, 60);
            homeLabel4.setBounds(130, 250, 1100, 60);
            homeLabel5.setBounds(130, 310, 1100, 60);
            homeLabel6.setBounds(130, 370, 1100, 60);
            homeLabel7.setBounds(130, 430, 1100, 60);
            homeLabel8.setBounds(130, 490, 1100, 60);

            //handle Mouse Event:
            jPanel6.addMouseListener(new MouseAdapter(){
                @Override
                public void mouseClicked(MouseEvent e){
                    MyPanelInfor1 panel = new MyPanelInfor1();
                    JFrame newFrame = new JFrame("Ứng dụng giúp tìm hiểu thuật toán SJF (Shortest Job First) - Điều phối tiến trình cho CPU và SSTF (Shortest Seek Time First) - Dịch chuyển đầu đọc ghi của đĩa để tối ưu seek time.");
                    JScrollPane scrPane = new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                    
                    //customize scroll bar:
//                    scrPane.setLayout(new ScrollPaneLayout());
//                    scrPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
//                    scrPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                    
                    //DISPOSE_ON_CLOSE : means out of this frame, returning to main default frame.
                    newFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    
//                    newFrame.setContentPane(panel);
                    newFrame.pack();
                    newFrame.setSize(1200, 600);
//                    newFrame.add(scrPane);
                    newFrame.setLocationRelativeTo(null);
                    //Add scroll bar:
                    newFrame.getContentPane().add(scrPane);
                    newFrame.setVisible(true);
//                    newFrame.setBounds(0, 0, 1100, 650);
//                    getParent().add(panel);
                }
            });
            
            jPanel8.addMouseListener(new MouseAdapter(){
                @Override
                public void mouseClicked(MouseEvent e){
                    
                    try
                    {
                        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                    }
                    catch(ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException ex)
                    {
                    }
                    MyPanelAppDemo1 mpad1 = new MyPanelAppDemo1();
                    JFrame newFrame = new JFrame("Ứng dụng giúp tìm hiểu thuật toán SJF (Shortest Job First) - Điều phối tiến trình cho CPU và SSTF (Shortest Seek Time First) - Dịch chuyển đầu đọc ghi của đĩa để tối ưu seek time.");
                    newFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    newFrame.pack();
                    newFrame.setSize(1200, 670);
                    newFrame.setLocationRelativeTo(null);
                    newFrame.getContentPane().add(mpad1);
                    newFrame.setVisible(true);
                }
            });
            
            jPanel10.addMouseListener(new MouseAdapter(){
                @Override
                public void mouseClicked(MouseEvent e){
                    
                    try
                    {
                        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                    }
                    catch(ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException ex)
                    {
                    }
                    MyPanelAppDemo2 mpad2 = new MyPanelAppDemo2();
                    JFrame newFrame = new JFrame("Ứng dụng giúp tìm hiểu thuật toán SJF (Shortest Job First) - Điều phối tiến trình cho CPU và SSTF (Shortest Seek Time First) - Dịch chuyển đầu đọc ghi của đĩa để tối ưu seek time.");
                    newFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    newFrame.pack();
                    newFrame.setSize(1200, 580);
                    newFrame.setLocationRelativeTo(null);
                    newFrame.getContentPane().add(mpad2);
                    newFrame.setVisible(true);
                }
            });
            
            //add components for Home Panel of MyPanel2:
            super.add(homeLabel1);
            super.add(homeLabel2);
            super.add(homeLabel3);
            super.add(homeLabel4);
            super.add(homeLabel5);
            super.add(homeLabel6);
            super.add(homeLabel7);
            super.add(homeLabel8);
        }
    }
    
    class MyPanel5 extends MyPanel1 {
        
        public MyPanel5(){
            
            //Home Label:
            JPanel homePanel = new JPanel();
            JLabel homeLabel1 = new JLabel("- Danh sách các thành phần của ứng dụng:");
            JLabel homeLabel2 = new JLabel("+ Part 1: Các khái niệm và ưu điểm, nhược");
            JLabel homeLabel3 = new JLabel(" điểm của thuật toán SSTF.");
            JLabel homeLabel4 = new JLabel("+ Part 2: Tìm hiểu về SSTF thông qua chương");
            JLabel homeLabel5 = new JLabel("trình minh họa.");
            JLabel homeLabel6 = new JLabel("+ Exit: Trở về màn hình ban đầu của ứng ");
            JLabel homeLabel7 = new JLabel(" dụng.");

            //customize ur own Labels' Home:
            homeLabel1.setForeground(new Color(255, 60, 36));
            homeLabel2.setForeground(new Color(154, 16, 62));
            homeLabel3.setForeground(new Color(154, 16, 62));
            homeLabel4.setForeground(new Color(154, 16, 62));
            homeLabel5.setForeground(new Color(154, 16, 62));
            homeLabel6.setForeground(new Color(154, 16, 62));
            homeLabel7.setForeground(new Color(154, 16, 62));

            homeLabel1.setFont(new Font("Cambria", Font.PLAIN, 55));
            homeLabel2.setFont(new Font("Cambria", Font.PLAIN, 55));
            homeLabel3.setFont(new Font("Cambria", Font.PLAIN, 55));
            homeLabel4.setFont(new Font("Cambria", Font.PLAIN, 55));
            homeLabel5.setFont(new Font("Cambria", Font.PLAIN, 55));
            homeLabel6.setFont(new Font("Cambria", Font.PLAIN, 55));
            homeLabel7.setFont(new Font("Cambria", Font.PLAIN, 55));

            homeLabel1.setBounds(130, 60, 1150, 90);
            homeLabel2.setBounds(130, 130, 1150, 90);
            homeLabel3.setBounds(130, 200, 1150, 90);
            homeLabel4.setBounds(130, 270, 1150, 90);
            homeLabel5.setBounds(130, 340, 1150, 90);
            homeLabel6.setBounds(130, 410, 1150, 90);
            homeLabel7.setBounds(130, 480, 1150, 90);
            
            //add components for Home Panel of MyPanel2:
            super.add(homeLabel1);
            super.add(homeLabel2);
            super.add(homeLabel3);
            super.add(homeLabel4);
            super.add(homeLabel5);
            super.add(homeLabel6);
            super.add(homeLabel7);
            
            //handle the mouse click event:
            jPanel6.addMouseListener(new MouseAdapter(){
                @Override
                public void mouseClicked(MouseEvent e){
                    MyPanelInfor2 app = new MyPanelInfor2();
                    JFrame newFrame = new JFrame("Ứng dụng giúp tìm hiểu thuật toán SJF (Shortest Job First) - Điều phối tiến trình cho CPU và SSTF (Shortest Seek Time First) - Dịch chuyển đầu đọc ghi của đĩa để tối ưu seek time.");
                    JScrollPane scrPane = new JScrollPane(app, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                    newFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    newFrame.setVisible(true);
                    newFrame.pack();
                    newFrame.setSize(1250, 700);
                    newFrame.setLocationRelativeTo(null);
                    newFrame.getContentPane().add(scrPane);
                }
            });
            jPanel8.addMouseListener(new MouseAdapter(){
                @Override
                public void mouseClicked(MouseEvent e){
                    FinishAppDemo appFinishDemo = new FinishAppDemo();
                    MyPanelAppDemo3 app = new MyPanelAppDemo3();
                    JFrame newFrame = new JFrame("Ứng dụng giúp tìm hiểu thuật toán SJF (Shortest Job First) - Điều phối tiến trình cho CPU và SSTF (Shortest Seek Time First) - Dịch chuyển đầu đọc ghi của đĩa để tối ưu seek time.");
                    newFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    newFrame.addMouseListener(new MyMouseAdapter(newFrame));
                    JPanel contentPanel2 = new JPanel();
                    contentPanel2.setLayout(new CardLayout());
                    panel1 = new MyPanel(contentPanel2);
                    contentPanel2.add(app);
                    contentPanel2.add(appFinishDemo);
                    newFrame.setContentPane(contentPanel2);
                    newFrame.setVisible(true);
                    newFrame.pack();
                    newFrame.setSize(1200, 500);
                    newFrame.setLocationRelativeTo(null);
//                    newFrame.getContentPane().add(app);
                }
            });
        }
    }
    
    class MyPanelInfor1 extends JPanel {
        public Drawing d;
        public JPanel jPanel1;
        public JTextArea textArea = new JTextArea(50, 30);
        public JTextArea textArea1 = new JTextArea(50, 30);
        public JTextArea textArea2 = new JTextArea(50, 30);
        public JLabel jLabel1;
        public JLabel jLabel1_2;
        public JLabel jLabel2;
        public JLabel jLabel3;
        
        public MyPanelInfor1(){
//            String all = new Scanner(new File())
            super.setPreferredSize(new Dimension (1900, 1300));
            super.setLayout(null);
            String text = "Giải thuật SJF là giải thuật điều phối tiến trình của CPU mà gắn với mỗi tiến trình là thời gian sử dụng CPU tiếp sau của nó, thời gian này được sử dụng để lập lịch hay điều phối các tiến trình với thời gian ngắn nhất:\n";
            jLabel1 = new JLabel();
            jLabel1.setText("<html><p style=\"width:1500px\">"+text+"</p></html>");
//            jLabel1_2 = new JLabel("\n");
            jLabel2 = new JLabel("   A.SJF không ưu tiên trước (non pre-emptive):");
            jLabel3 = new JLabel("   B.SJF có ưu tiên trước (pre emptive):");
            textArea.setText(
            "- SJF còn được gọi là SJN (Shortest Job Next) hay SPN (Shortest Process Next).\n" +
            "- Đặc điểm: thuật toán lập lịch hay điều phối tiến trình này tối ưu vì nó cho thời gian chờ đợi trung bình của các tiến trình là nhỏ nhất.\n" +
            "- Điểm có lợi khi sử dụng thuật toán này để lập lịch cho các tiến trình là: giúp CPU lập lịch dài hạn, và ngược lại nó gặp bất lợi khi lập lịch ngắn hạn.\n" +
            "- Thông lượng (Throughput) hay số tiến trình được hình thành trong một đơn vị thời gian sẽ cao khi áp dụng điều phối các tiến trình CPU bằng thuật toán này.\n" +
            "- Nhược điểm hay điểm bất lợi khi sử dụng SJF để điều phối tiến trình cho CPU là nó có thể gây ra quá trình starvation * -> khi những tiến trình có độ ưu tiên thấp hay những tiến trình có thời gian sử dụng CPU (burst time) dài hơn có thể sẽ không bao giờ được thực hiện * nếu có số lượng lớn các tiến trình có độ ưu tiên cao hơn hay có thời gian sử dụng CPU (burst time) ngắn hơn liên tục được thực thi.\n" +
            "- Giải thuật này có 2 phương pháp hay 2 phiên bản:");
            textArea1.setText("+ Một tiến trình nếu đang sử dụng CPU hay sử dụng CPU trước thì sẽ không nhường cho tiến trình khác đến khi nó kết thúc.\n" +
            "+ Tiến trình có thời gian sử dụng CPU dài hơn có thể bị trì hoãn vô hạn định nếu các tiến trình có burst time ( thời gian sử dụng CPU) ngắn hơn liên tục vào.\n" +
            "+ Phiên bản hay phương pháp này không thích hợp cho môi trường time-sharing (chia sẻ thời gian thực).\n" +
            "+ Nếu tất cả các tiến trình có cùng chung một thời gian sử dụng CPU ( burst time) thì phương pháp hay phiên bản này hoạt động như thuật toán FCFS.\n" +
            "+ SJF sẽ khó dự đoán trước được thời gian sử dụng CPU ( burst time).\n" +
            "+ Nhược điểm: cài đặt thuật toán phức tạp, tốn nhiều xử lý cho quá trình quản lý, khó biết độ dài khi sử dụng CPU của tiến trình.");
            textArea2.setText("+ Phương pháp hay phiên bản này còn được gọi là Shortest Remaining Time First (SRTF).\n" +
            "+ Nếu một tiến trình đến có thời gian sử dụng CPU ngắn hơn thời gian còn lại của tiến trình đang thực hiện thì ưu tiên tiến trình mới đến trước.\n" +
            "+ Ngầm định : tiến trình nào có thời gian sử dụng CPU (burst time) ngắn hơn được ưu tiên hơn.\n" +
            "+ Tránh trường hợp tiến trình có thời gian sử dụng CPU dài hơn có thể độc chiếm CPU.\n" +
            "+ Lưu ý khi quản lý thời gian sử dụng CPU của các tiến trình còn lại.\n" +
            "+ Phương pháp này có thời gian quay vòng ( Turnaround Time ) tốt hơn so với SJF không ưu tiên, nó tối thiểu tổng lượng thời gian để thực hiện một tiến trình ( Turnaround Time).\n" +
            "+ Nếu tất cả các tiến trình có cùng Arrival Time : thời gian đến hay thời gian được đưa vào queue (hàng chờ) thì nó hoạt động như thuật toán SJF không ưu tiên (non pre-emptive).\n" +
            "+ Nhược điểm: việc cài đặt thuật toán khá phức tạp và quản lý thời gian đến của mỗi tiến trình khó khăn.");
            textArea.setLineWrap(true);
            textArea1.setLineWrap(true);
            textArea2.setLineWrap(true);
            textArea.setWrapStyleWord(true);
            textArea1.setWrapStyleWord(true);
            textArea2.setWrapStyleWord(true);
            textArea.setFont(new Font("Cambria", Font.PLAIN, 30));
            textArea1.setFont(new Font("Cambria", Font.PLAIN, 30));
            textArea2.setFont(new Font("Cambria", Font.PLAIN, 30));
            textArea.setForeground(new Color(92, 62, 106));
            textArea1.setForeground(new Color(92, 62, 106));
            textArea2.setForeground(new Color(92, 62, 106));
            d = new Drawing("                           Shortest Job First Algorithm", new Font("Cambria", Font.ITALIC, 50), new Color(181, 218, 181));
            jPanel1 = new JPanel();
            
            //customize ur own attributes:
//            d.setSize(1500, 60);
            d.setBounds(0, 0, 2200, 60);
            jPanel1.setBackground(Color.gray);
            jPanel1.setBounds(0, 0, 1900, 60);
            jLabel1.setForeground(new Color(168, 96, 74));
            jLabel1.setFont(new Font("Cambria", Font.BOLD, 35));
            jLabel1.setBounds(0, 60, 1900, 100);
//            jLabel1_2.setForeground(new Color(168, 96, 74));
//            jLabel1_2.setFont(new Font("Cambria", Font.BOLD, 35));
//            jLabel1_2.setBounds(0, 110, 1900, 50);
            textArea.setBounds(0, 160, 1900, 1300);
            jLabel2.setBounds(0, 485, 1900, 40);
            jLabel2.setForeground(new Color(67, 117, 119));
            jLabel2.setFont(new Font("Cambria", Font.BOLD, 35));
            textArea1.setBounds(0, 525, 1900, 1300);
            jLabel3.setForeground(new Color(119, 147, 66));
            jLabel3.setFont(new Font("Cambria", Font.BOLD, 35));
            jLabel3.setBounds(0, 815, 1900, 40);
            textArea2.setBounds(0, 860, 1900, 1300);

            //add components:
            super.add(jLabel3);
            super.add(jLabel2);
            super.add(jLabel1);
//            super.add(jLabel1_2);
            super.add(textArea2);
            super.add(textArea1);
            super.add(textArea);
            super.add(d);
            super.add(jPanel1);
        }
    }
    
    class MyPanelInfor2 extends JPanel {
        public Drawing d;
        public JLabel jLabel1;
        public JLabel jLabel1_2;
        public JLabel jLabel1_3;
        public JLabel jLabel1_4;
        public JTextArea jTextArea;
        public JPanel jPanel;
        public MyPanelInfor2(){
            super.setPreferredSize(new Dimension (1500, 800));
            super.setLayout(null);
            jPanel = new JPanel();
            d = new Drawing("              Shortest Seek Time First Algorithm", new Font("Cambria", Font.ITALIC, 50), new Color(160, 242, 210));
            jTextArea = new JTextArea(40, 30);
            String text = "- Giải thuật SSTF là giải thuật giúp dịch chuyển đầu đọc ghi sao cho thời gian truy cập đầu đĩa cứng là tối ưu nhất, cụ thể là di chuyển đầu đọc đến các khối cần thiết theo vị trí lần lượt gần với vị trí hiện tại của đầu đọc nhất có thể, hay tối ưu seek time(thời gian chuyển đầu từ đến cylinder chứa sector được yêu cầu) nhất có thể.";
            jLabel1 = new JLabel();
//            jLabel1.setUI(MultiLineLabelUI.labelUI);
            jLabel1.setText("<html><p style=\"width:1150px\">"+text+"</p></html>");
//            jLabel1_2 = new JLabel("đĩa cứng là tối ưu nhất, cụ thể là di chuyển đầu đọc đến các khối cần thiết theo vị trí lần");
//            jLabel1_3 = new JLabel("lượt gần với vị trí hiện tại của đầu đọc nhất có thể, hay tối ưu seek time(thời gian chuyển");        
//            jLabel1_4 = new JLabel("đầu từ đến cylinder chứa sector được yêu cầu) nhất có thể.");
            //customize ur own attributes:
            jTextArea.setText("  - Cách đọc:\n" + 
            "    + Track (vòng đĩa trên mỗi cylinder của đĩa) bất kỳ nào có thời gian dịch chuyển đầu đọc ghi ngắn nhất thì được ưu tiên đọc trước.\n" +
            "    + Nhu cầu đọc gần với vị trí hiện tại nhất.\n" +
            "    + Sau khi phục vụ một yêu cầu đọc đĩa, di chuyển đầu đọc tới vị trí cần đọc gần với vị trí hiện tại của đầu đọc nhất.\n" +
            "    + Chọn yêu cầu I/O mà đòi hỏi ít di chuyển nhất đầu đĩa từ vị trí hiện tại.\n\n" +
            "  - Ưu điểm:\n"
            + "    + Loại bỏ được nhược điểm của thuật toán đọc đĩa FCFS (First come first serve) là có quãng đường mà đầu đọc(disk arm) cần phải dịch chuyển khá là nhiều, thay vì điều đó, quãng đường mà đầu đọc (disk arm) dịch chuyển đã giảm đi.\n" +
            "    + Giảm seek time (thời gian dùng để định vị đầu đĩa (disk arm) đến cylinder mong muốn) so với FCFS. \n\n" +
            "  - Nhược điểm (vì là 1 dạng của thuật toán SJF nên có chung nhược điểm):\n "
            + "    + Vấn đề starvation(khi các đầu đĩa có độ ưu tiên thấp hơn có thể sẽ không bao giờ được đọc khi có liên tục những đầu đĩa có vị trí gần hơn được đọc).");
            d.setBounds(0, 0, 1800, 60);
            jTextArea.setLineWrap(true);
            jTextArea.setWrapStyleWord(true);
            jTextArea.setForeground(new Color(92, 62, 106));
            jTextArea.setFont(new Font("Cambria", Font.PLAIN, 30));
            jPanel.setBackground(Color.gray);
            jPanel.setBounds(0, 0, 1500, 60);
            jLabel1.setForeground(new Color(168, 96, 74));
//            jLabel1_2.setForeground(new Color(168, 96, 74));
//            jLabel1_3.setForeground(new Color(168, 96, 74));
//            jLabel1_4.setForeground(new Color(168, 96, 74));
            jLabel1.setFont(new Font("Cambria", Font.BOLD, 33));
//            jLabel1_2.setFont(new Font("Cambria", Font.BOLD, 33));
//            jLabel1_3.setFont(new Font("Cambria", Font.BOLD, 33));
//            jLabel1_4.setFont(new Font("Cambria", Font.BOLD, 33));
            jLabel1.setBounds(0, 60, 1500, 160);
//            jLabel1_2.setBounds(0, 100, 1500, 40);
//            jLabel1_3.setBounds(0, 140, 1500, 40);
//            jLabel1_4.setBounds(0, 180, 1500, 40);
            jTextArea.setBounds(0, 220, 1500, 800);
            
            //add components:
            super.add(d);
            super.add(jPanel);
            super.add(jTextArea);
            super.add(jLabel1);
//            super.add(jLabel1_2);
//            super.add(jLabel1_3);
//            super.add(jLabel1_4);
        }
    }
    class FinishAppDemo extends JPanel{
        public JLabel jLabel;
        public FinishAppDemo(){
            super.setPreferredSize(new Dimension(1300, 600));
            super.setLayout(null);
            jLabel = new JLabel("Hi, alo");
            //customize your own attributes:
            jLabel.setFont(new Font("Cambria", Font.BOLD, 40));
            jLabel.setBounds(0, 0, 1000, 70);
            jLabel.setForeground(new Color(235, 54, 15));
            //add the components:
            super.add(jLabel);
        }
    }
    
    class MyPanelAppDemo2 extends JPanel {
        
        public JLabel jvLabel1;
        public JLabel jvLabel2;
        public JLabel jvLabel3;
        public JLabel ganttChart;
        public JLabel ganttChart1;
        public JLabel ganttChart2;
        public JLabel ganttChart3;
        public JLabel ganttChart4;
        public JLabel ganttChart5;
        public JLabel ganttChart6;
        public JTextField jvTextField1;
        public JTextField jvTextField2;
        public JButton jvButton1;
        public JButton jvButton2;
        public DefaultTableModel model;
        public DefaultTableModel model1;
        public JTable jvTable;
        public Drawing d;
        public JPanel jvPanel1;
        public JPanel mainPanel;
//        public void main(String[] args){
//            super.setContentPane(new MyPanelAppDemo2().mainPanel);
//        }
        public MyPanelAppDemo2(){
            
            super.setPreferredSize(new Dimension (1200, 580));
            super.setLayout(null);
//            super.add(jvTable.getTableHeader(), BorderLayout.NORTH);
            d = new Drawing("        Shortest Job First Algorithm (Pre-emptive)", new Font("Cambria", Font.ITALIC, 50), new Color(181, 218, 181));
            jvPanel1 = new JPanel();
            jvLabel1 = new JLabel("- Thời gian chờ đợi trung bình (Average WT): ");
            jvLabel2 = new JLabel("- Thời gian quay vòng trung bình (Average TAT):");
            jvLabel3 = new JLabel("- Gantt Chart: ");
            ganttChart = new JLabel();
            ganttChart1 = new JLabel();
            ganttChart2 = new JLabel();
            ganttChart3 = new JLabel();
            ganttChart4 = new JLabel();
            ganttChart5 = new JLabel();
            ganttChart6 = new JLabel();
            jvTextField1 = new JTextField("...");
            jvTextField2 = new JTextField("...");
            String[] columnNames = {"Process ID", "Arrival Time", "Burst time"};
            Object[][] data = {{"Process ID", "Arrival Time", "Burst Time"}, {"01", "", ""}, {"02", "", ""}, {"03", "", ""}, {"04", "", ""}, {"05", "", ""},{"06", "", ""}, {"07", "", ""}, {"08", "", ""}};
            model = new DefaultTableModel(data, columnNames);
            jvTable = new JTable(model)
            {
            //  Returning the Class of each column will allow different
            //  renderers to be used based on Class
                @Override
                public Class getColumnClass(int column)
                {
                    return getValueAt(0, column).getClass();
                }
            };
            jvButton1 = new JButton("Submit");
            jvButton2 = new JButton("Reset");
            
            //set attributes:
            d.setBounds(-100, 0, 1200, 60);
            jvPanel1.setBackground(Color.gray);
            jvPanel1.setBounds(0, 0, 1200, 60);
            jvButton1.setBounds(930, 90, 220, 110);
            jvButton1.setBackground(new Color(131, 99, 89));
            jvButton1.setForeground(new Color(78, 90, 99));
            jvButton1.setFont(new Font("Cambria", Font.BOLD, 50));
            jvButton2.setBounds(930, 240, 220, 110);
            jvButton2.setBackground(new Color(131, 99, 89));
            jvButton2.setForeground(new Color(78, 90, 99));
            jvButton2.setFont(new Font("Cambria", Font.BOLD, 50));
            jvTextField1.setFont(new Font("Cambria", Font.PLAIN, 35));
            jvTextField1.setBackground(new Color(197, 223, 194));
            jvTextField1.setForeground(new Color(99, 99, 99));
            jvTextField1.setBounds(830, 390, 320, 50);
            jvLabel1.setForeground(new Color(57, 57, 57));
            jvLabel1.setFont(new Font("Cambria", Font.PLAIN, 38));
            jvLabel1.setBounds(0, 390, 880, 50);
            jvLabel2.setForeground(new Color(123, 71, 99));
            jvLabel2.setFont(new Font("Cambria", Font.PLAIN, 38));
            jvLabel2.setBounds(0, 440, 880, 50);
            jvTextField2.setFont(new Font("Cambria", Font.PLAIN, 35));
            jvTextField2.setBackground(new Color(164, 199, 203));
            jvTextField2.setForeground(new Color(202, 113, 80));
            jvTextField2.setBounds(830, 440, 320, 50);
//            jvLabel3.setForeground(new Color(145, 85, 190));
//            jvLabel3.setFont(new Font("Cambria", Font.PLAIN, 38));
//            jvLabel3.setBounds(0, 490, 880, 50);
            ganttChart.setForeground(new Color(57, 57, 57));
            ganttChart.setFont(new Font("Cambria", Font.PLAIN, 51));
            ganttChart.setBounds(14, 524, 1190, 30);
            ganttChart1.setForeground(new Color(44, 78, 79));
            ganttChart1.setFont(new Font("Cambria", Font.PLAIN, 65));
            ganttChart1.setBounds(4, 546, 1200, 40);
            ganttChart2.setForeground(new Color(44, 78, 79));
            ganttChart2.setFont(new Font("Cambria", Font.PLAIN, 51));
            ganttChart2.setBounds(14, 565, 1190, 30);
            ganttChart3.setForeground(new Color(44, 78, 79));
            ganttChart3.setFont(new Font("Cambria", Font.PLAIN, 65));
            ganttChart3.setBounds(4, 546, 1200, 40);
            ganttChart5.setForeground(new Color(252, 83, 65));
            ganttChart5.setFont(new Font("Cambria", Font.PLAIN, 40));
            ganttChart5.setBounds(54, 543, 1200, 45);
            ganttChart4.setForeground(new Color(5, 163, 114));
            ganttChart4.setFont(new Font("Cambria", Font.PLAIN, 40));
            ganttChart4.setBounds(10, 588, 1200, 40);
            ganttChart6.setForeground(new Color(15, 154, 107));
            ganttChart6.setFont(new Font("Cambria", Font.PLAIN, 40));
            ganttChart6.setBounds(10, 588, 1200, 40);
            jvTable.setBounds(0, 60, 900, 320);
            jvTable.setBackground(new Color(231, 221, 182));
            jvTable.setFont(new Font("Cambria", Font.PLAIN, 38));
            jvTable.setRowHeight(0, 40);
            for(int i=1;i<9;i++)
                jvTable.setRowHeight(i, 35);
            jvTable.setForeground(new Color(154, 16, 62));
            JTableHeader tableHeader = jvTable.getTableHeader();
//            jvTable.getTableHeader().setDefaultRenderer(new DefaultTableCellHeaderRenderer());
//            jvTable.getColumnModel().getColumn(1).setHeaderRenderer(headerRenderer);
            //handle data changes listening
//            jvTable.getModel().addTableModelListener(jvTable);
            int n=0;
            String[] atS = new String[8];
            String[] btS = new String[8];
            int[] atq = new int[8];
            int[] btq = new int[8];
            jvButton1.addMouseListener(new MouseAdapter(){
                @Override
                public void mouseClicked(MouseEvent e){
                    
                    //set default label:
                    jvTable.setValueAt("Process ID", 0, 0);
                    jvTable.setValueAt("01", 1, 0);
                    jvTable.setValueAt("02", 2, 0);
                    jvTable.setValueAt("03", 3, 0);
                    jvTable.setValueAt("04", 4, 0);
                    jvTable.setValueAt("05", 5, 0);
                    jvTable.setValueAt("06", 6, 0);
                    jvTable.setValueAt("07", 7, 0);
                    jvTable.setValueAt("08", 8, 0);
                    jvTable.setValueAt("Arrival Time", 0, 1);
                    jvTable.setValueAt("Burst Time", 0, 2);
                    
                    @SuppressWarnings("UnusedAssignment")
                    String[] atS = new String[8];
                    @SuppressWarnings("UnusedAssignment")
                    String[] btS = new String[8];
                    @SuppressWarnings("UnusedAssignment")
                    int[] atq = new int[8];
                    @SuppressWarnings("UnusedAssignment")
                    int[] btq = new int[8];
                    int countNullAndUnSpecify1 = 1;
                    int countNullAndUnSpecify2 = 1;
                    int n,j=0,k=0;
                    String[] checkStringNull = new String[9];
                    boolean checkLetterInput = false;
                    boolean checkNegativeNumberInput = false;
                    boolean checkSpecialCharacterInput = false;
                    boolean checkNullOrMissingInput = false;
                    Pattern regex = Pattern.compile("[^A-Za-z0-9]");
                    for(int d=0;d<9;d++){
                        checkStringNull[d] = (String)jvTable.getValueAt(d, 1);
                        Matcher matcher = regex.matcher(checkStringNull[d]);
                        if(checkStringNull[d] == null || "".equals(checkStringNull[d]))
                            countNullAndUnSpecify1++;
                        else if(checkStringNull[d].matches("[a-zA-Z]{1}")==true)
                            checkLetterInput = true;
                        else if(checkStringNull[d].matches("-[0-9]{1}")){
                            checkNegativeNumberInput = true;
                        }
//                        else if(checkStringNull[d].matches("[a-zA-Z]{1}")==false&&checkStringNull[d].matches("[0-9]{1}")==false && (checkStringNull[d] == null && "".equals(checkStringNull[d]))==true)
//                            checkSpecialCharacterInput=true;
                    }
                    for(int d=0;d<9;d++){
                        Matcher matcher = regex.matcher(checkStringNull[d]);
                        checkStringNull[d] = (String)jvTable.getValueAt(d, 2);
                        if(checkStringNull[d] == null || "".equals(checkStringNull[d]))
                            countNullAndUnSpecify2++;
                        else if(checkStringNull[d].matches("[a-zA-Z]{1}")==true)
                            checkLetterInput=true;
                        else if(checkStringNull[d].matches("-[0-9]{1}")){
                            checkNegativeNumberInput = true;
                        }
//                        else if(checkStringNull[d].matches("[a-zA-Z]{1}")==false&&checkStringNull[d].matches("[0-9]{1}")==false && (checkStringNull[d] == null && "".equals(checkStringNull[d]))==true)
//                            checkSpecialCharacterInput=true;
//                        else if(checkStringNull[d].matches("[0-9]{1}")==true)
//                            checkSpecialCharacterInput = false;
//                        else if(matcher.find()==false)
//                            checkSpecialCharacterInput=true;
                    }
                    if(countNullAndUnSpecify1!=countNullAndUnSpecify2){
                        checkNullOrMissingInput = true;
                        JOptionPane.showConfirmDialog(null, "Bạn phải điền đầy đủ các số liệu vào cả 2 cột số liệu ở trong bảng.");
                    } else if(countNullAndUnSpecify1==9||countNullAndUnSpecify2==9){
                        checkNullOrMissingInput = true;
                        JOptionPane.showConfirmDialog(null, "Bạn không được để trống các trường thông tin trong bảng.Xin mời nhập số liệu.");
                    }
                    if (checkNegativeNumberInput==true){
                        JOptionPane.showConfirmDialog(null, "Bạn đã điền số âm vào trường thông tin của bảng.Xin hãy nhập lại số liệu.");
                    } else if(checkLetterInput==true) 
                        JOptionPane.showConfirmDialog(null, "Bạn đã điền định dạng chữ cái vào trong trường thông tin của bảng. Xin mời nhập lại số liệu.");
                    else if(checkSpecialCharacterInput==true) 
                        JOptionPane.showConfirmDialog(null, "Bạn đã điền kí tự đặc biệt vào trong trường thông tin của bảng. Xin mời nhập lại số liệu.");
                    n=9-countNullAndUnSpecify1;
                    atS = new String[n];
                    btS = new String[n];
                    atq = new int[n];
                    btq = new int[n];
                    if(checkLetterInput==false && checkSpecialCharacterInput==false && checkNullOrMissingInput == false && checkNegativeNumberInput==false){
                        for(int i=1;i< atS.length+1;i++){
                            atS[j++]=(String)jvTable.getValueAt(i, 1);
    //                        System.out.println("OK 1");
                        }
                        for(int i=1;i< btS.length+1;i++){
                            btS[k++]=(String)jvTable.getValueAt(i, 2);
    //                        System.out.println("OK 2");
                        }
                        for(int i=0;i<atS.length;i++){
                            try {
                                atq[i] = Integer.parseInt(atS[i]);
    //                            System.out.println("OK 3");
                            } catch (NumberFormatException nfe){
                                    System.out.println("error:"+nfe.getMessage());
                            }
                        }
                        for(int i=0;i<btS.length;i++){
                            try {
                                btq[i] = Integer.parseInt(btS[i]);
    //                            System.out.println("OK 4");
                            } catch (NumberFormatException nfe){
                                    System.out.println("error:"+nfe.getMessage());
        //                            JOptionPane.showConfirmDialog(null, "Bạn phải điền số liệu vào các trường thông tin ở trong bảng.");
                                }
                        }
    //                    for(int i:atq)
    //                        System.out.println(i);
    //                    for(int i:btq)
    //                        System.out.println(i);
    //                    System.out.println("OK 5");
    //                    System.out.println("OK 6");

    //                    for(int i:btq){
    //                        System.out.println(i);
    //                    }

                        int[] atNew = new int[n];
                        @SuppressWarnings("UnusedAssignment")
                        int mid, minVal =0;
                        System.arraycopy(atq, 0, atNew, 0, n);
                        for(int i=0;i<n-1;i++)
                            for(int w=i+1;w<n;w++)
                                if(atNew[i]>atNew[w]){
                                    mid = atNew[i];
                                    atNew[i] = atNew[w];
                                    atNew[w] = mid;
                                }
                        minVal = atNew[0];

                        //set gantt chart via Jlabel setting:
                        String text = "";
                        String text1 = "";
                        String text2 = "";
                        String text4 = "";
                        String text5 = "";
                        String text6 = "";
    //                    for(int i:btq){
    //                        System.out.println(i);
    //                    }
                        for(int i=0;i<68;i++)
                            text+="-";
                        for(int i=0;i<83;i++){
                            if(i==0||i==82)
                                text1+="|";
                            else text1+=" ";
                        }
                        int sum2 = minVal;
                        for(int i:btq){
    //                        System.out.println(i);
                            sum2 += i;
                        }

    //                    System.out.println(sum2);
    //                    for(int i=0;i<83;i++){
    //                        switch (i) {
    //                            case 0:
    //                                text4+="0";
    //                                break;
    //                            default:
    //                                text4+=" ";
    //                                break;
    //                        }
    //                    }
                        boolean checkSuspend = false;
                        int count2=0, pointVal6 = 20, pointVal7 = 12;
                        for(int i=0;i<103;i++){
                            if(i==0) text4+=""+minVal+" "; 
                            else if(count2<n-1 && i==pointVal6){
                                text4+="  ";
                                count2++;
                                pointVal6+=pointVal7;
                                checkSuspend = true;
                            } else if(count2==n-1 && checkSuspend==true){ 
                                text4+=" "+sum2;
                                checkSuspend = false;
                            } else text4+=" ";
                        }
                        int count=0, pointVal = 8, pointVal1 = 8;
                        for(int i=0;i<83;i++){
                            if(count<n && i==pointVal){
                                text2+="|";
                                count++;
                                pointVal+=pointVal1;
                            } else text2+=" ";
                        }
                        int count1 = 0, pointVal2 = 0, pointVal3 = 8, u1=0;
                        int[] resultAl3 = new int[n];
    //                    System.out.println("OK 1");
                        int[] sjfPre3 = SJFNonPre3(n, atq, btq);
                        for(int i=0;i<atS.length;i++){
                            try {
                                atq[i] = Integer.parseInt(atS[i]);
    //                            System.out.println("OK 3");
                            } catch (NumberFormatException nfe){
                                    System.out.println("error:"+nfe.getMessage());
    //                                JOptionPane.showConfirmDialog(null, "Bạn phải điền số liệu vào các trường thông tin ở trong bảng.");
                            }
                        }
                        for(int i=0;i<btS.length;i++){
                            try {
                                btq[i] = Integer.parseInt(btS[i]);
    //                            System.out.println("OK 4");
                            } catch (NumberFormatException nfe){
                                    System.out.println("error:"+nfe.getMessage());
        //                            JOptionPane.showConfirmDialog(null, "Bạn phải điền số liệu vào các trường thông tin ở trong bảng.");
                                }
                        }
                        System.arraycopy(sjfPre3, 0, resultAl3, 0, n);
                        for(int i=0;i<83;i++){
                            if(count1<n && i==pointVal2){
                                text5+="P"+resultAl3[u1++];
                                text5+=" ";
                                count1++;
                                pointVal2+=pointVal3;
                            } else text5+=" ";
                        }
                        for(int i=0;i<atS.length;i++){
                            try {
                                atq[i] = Integer.parseInt(atS[i]);
    //                            System.out.println("OK 3");
                            } catch (NumberFormatException nfe){
                                    System.out.println("error:"+nfe.getMessage());
    //                                JOptionPane.showConfirmDialog(null, "Bạn phải điền số liệu vào các trường thông tin ở trong bảng.");
                            }
                        }
                        for(int i=0;i<btS.length;i++){
                            try {
                                btq[i] = Integer.parseInt(btS[i]);
    //                            System.out.println("OK 4");
                            } catch (NumberFormatException nfe){
                                    System.out.println("error:"+nfe.getMessage());
        //                            JOptionPane.showConfirmDialog(null, "Bạn phải điền số liệu vào các trường thông tin ở trong bảng.");
                                }
                        }
                        int count3=0, pointVal8 = 12, pointVal9 = 10;
                        if(sum2>100) {
                            pointVal8 = 10;
                            pointVal9 = 9;
                        }
                        else if(sum2<=100 && sum2>=20) {
                            pointVal8 = 9;
                            pointVal9 = 10;
                        } else if(sum2<=20){
                            pointVal8 = 10;
                            pointVal9 = 12;
                        }

                        int SJFi = 0;

                        //try to delete the last index of sjfNonPreArr:
                        int[] sjfPreArr = SJFPre4(n, atq, btq);
                        int[] sjfPreArr1 = new int[sjfPreArr.length-1];
                        System.arraycopy(sjfPreArr, 0, sjfPreArr1, 0, sjfPreArr1.length);
    //                    for(int i:sjfNonPreArr1)
    //                        System.out.println(i);
                        for(int i=0;i<93;i++){
                            if(count3<n-1 && i==pointVal8){
                                text6+=""+sjfPreArr1[SJFi++];
                                count3++;
                                pointVal8+=pointVal9;
                            } else text6+=" ";
                        }
                        for(int i=0;i<atS.length;i++){
                            try {
                                atq[i] = Integer.parseInt(atS[i]);
    //                            System.out.println("OK 3");
                            } catch (NumberFormatException nfe){
                                    System.out.println("error:"+nfe.getMessage());
    //                                JOptionPane.showConfirmDialog(null, "Bạn phải điền số liệu vào các trường thông tin ở trong bảng.");
                            }
                        }
                        for(int i=0;i<btS.length;i++){
                            try {
                                btq[i] = Integer.parseInt(btS[i]);
    //                            System.out.println("OK 4");
                            } catch (NumberFormatException nfe){
                                    System.out.println("error:"+nfe.getMessage());
        //                            JOptionPane.showConfirmDialog(null, "Bạn phải điền số liệu vào các trường thông tin ở trong bảng.");
                                }
                        }
                        ganttChart2.setText(text);
                        ganttChart.setText(text);
                        ganttChart1.setText(text1);
                        ganttChart3.setText(text2);
                        ganttChart4.setText(text4);
                        ganttChart5.setText(text5);
                        ganttChart6.setText(text6);
                        float result1 = SJFPre1(n, atq, btq);
                        jvTextField1.setText(""+result1);
                        for(int i=0;i<atS.length;i++){
                            try {
                                atq[i] = Integer.parseInt(atS[i]);
    //                            System.out.println("OK 3");
                            } catch (NumberFormatException nfe){
                                    System.out.println("error:"+nfe.getMessage());
    //                                JOptionPane.showConfirmDialog(null, "Bạn phải điền số liệu vào các trường thông tin ở trong bảng.");
                            }
                        }
                        for(int i=0;i<btS.length;i++){
                            try {
                                btq[i] = Integer.parseInt(btS[i]);
    //                            System.out.println("OK 4");
                            } catch (NumberFormatException nfe){
                                    System.out.println("error:"+nfe.getMessage());
        //                            JOptionPane.showConfirmDialog(null, "Bạn phải điền số liệu vào các trường thông tin ở trong bảng.");
                                }
                        }
                        float result2 = SJFPre2(n, atq, btq);
                        jvTextField2.setText(""+result2);
                    }
                }
            });
            
            jvButton2.addMouseListener(new MouseAdapter(){
                @Override
                public void mouseClicked(MouseEvent e){
                    
                    //set default label:
                    jvTable.setValueAt("Process ID", 0, 0);
                    jvTable.setValueAt("01", 1, 0);
                    jvTable.setValueAt("02", 2, 0);
                    jvTable.setValueAt("03", 3, 0);
                    jvTable.setValueAt("04", 4, 0);
                    jvTable.setValueAt("05", 5, 0);
                    jvTable.setValueAt("06", 6, 0);
                    jvTable.setValueAt("07", 7, 0);
                    jvTable.setValueAt("08", 8, 0);
                    jvTable.setValueAt("Arrival Time", 0, 1);
                    jvTable.setValueAt("Burst Time", 0, 2);
                    
                    String value = "";
                    for(int i=1;i<9;i++){
                        jvTable.setValueAt(value, i, 1);
                        jvTable.setValueAt(value, i, 2);
                    }
                    jvTextField1.setText("");
                    jvTextField2.setText("");
                    ganttChart.setText("");
                    ganttChart1.setText("");
                    ganttChart2.setText("");
                    ganttChart3.setText("");
                    ganttChart4.setText("");
                    ganttChart5.setText("");
                    ganttChart6.setText("");
                }
            });
            
            //set Table's column width: (error !! so sad)
//            jTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
//            jTable.getColumnModel().getColumn(0).setPreferredWidth(10);
//            jTable.getColumnModel().getColumn(1).setPreferredWidth(40);
//            jTable.getColumnModel().getColumn(2).setPreferredWidth(40);
            
            //set Table's alignment:
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(JLabel.CENTER);
            jvTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
            jvTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
            jvTable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);

            //add components:
            super.add(d);
            super.add(jvPanel1);
            super.add(jvTable);
            super.add(jvButton1);
            super.add(jvButton2);
            super.add(jvLabel1);
            super.add(jvLabel2);
//            super.add(jvLabel3);
//            super.add(ganttChart);
//            super.add(ganttChart1);
//            super.add(ganttChart2);
//            super.add(ganttChart3);
//            super.add(ganttChart4);
//            super.add(ganttChart5);
//            super.add(ganttChart6);
            super.add(jvTextField1);
            super.add(jvTextField2);

        //handle Key Event when typing "Enter" (Error)
        
//            super.getRootPane().setDefaultButton(jvButton1);
            jvButton1.requestFocus();
            
            AbstractAction action = new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(e.getSource() instanceof JButton){
                        JButton button = (JButton) e.getSource();
                        button.doClick();        
                    }
                }
            };
            jvButton1.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "DoClick");
            jvButton1.getActionMap().put("DoClick", action);
            
//            super.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,0),"clickButton");
//            super.getRootPane().getActionMap().put("clickButton",new AbstractAction(){
//                @Override
//                public void actionPerformed(ActionEvent ae){
//                    jvButton1.doClick();
//                    System.out.println("button clicked");
//                }
//            });
        }
    }
    
    class MyPanelAppDemo1 extends JPanel {
        public JLabel jLabel1;
        public JLabel jLabel2;
        public JLabel jLabel3;
        public JLabel ganttChart;
        public JLabel ganttChart1;
        public JLabel ganttChart2;
        public JLabel ganttChart3;
        public JLabel ganttChart4;
        public JLabel ganttChart5;
        public JLabel ganttChart6;
        public JTextField jTextField1;
        public JTextField jTextField2;
        public JButton jButton1;
        public JButton jButton2;
        public DefaultTableModel model;
        public JTable jTable;
        public Drawing d;
        public JPanel jPanel1;
        public Graphics g;
//        final int countNullAndUnspecify;
        public MyPanelAppDemo1(){
            
            super.setPreferredSize(new Dimension (1200, 700));
            super.setLayout(null);
            
            d = new Drawing("           Shortest Job First Algorithm (Non Pre-emptive)", new Font("Cambria", Font.ITALIC, 50), new Color(181, 218, 181));
            jPanel1 = new JPanel();
            jLabel1 = new JLabel("- Thời gian chờ đợi trung bình (Average WT): ");
            jLabel2 = new JLabel("- Thời gian quay vòng trung bình (Average TAT): ");
            jLabel3 = new JLabel("- Gantt Chart: ");
            ganttChart = new JLabel();
            ganttChart1 = new JLabel();
            ganttChart2 = new JLabel();
            ganttChart3 = new JLabel();
            ganttChart4 = new JLabel();
            ganttChart5 = new JLabel();
            ganttChart6 = new JLabel();
            jTextField1 = new JTextField("...");
            jTextField2 = new JTextField("...");
            String[] columnNames = {"Process ID", "Arrival Time", "Burst time"};
            Object[][] data = {{"Process ID", "Arrival Time", "Burst Time"}, {"01", "", ""}, {"02", "", ""}, {"03", "", ""}, {"04", "", ""}, {"05", "", ""},{"06", "", ""}, {"07", "", ""}, {"08", "", ""}};
            model = new DefaultTableModel(data, columnNames);
            jTable = new JTable(model)
            {
            //  Returning the Class of each column will allow different
            //  renderers to be used based on Class
                @Override
                public Class getColumnClass(int column)
                {
                    return getValueAt(0, column).getClass();
                }
            };
            jButton1 = new JButton("Submit");
            jButton2 = new JButton("Reset");
            //set attributes:
            d.setBounds(-200, 0, 1400, 60);
            jPanel1.setBackground(Color.gray);
            jPanel1.setBounds(0, 0, 1200, 60);
            jButton1.setBounds(930, 90, 220, 110);
            jButton1.setBackground(new Color(131, 99, 89));
            jButton1.setForeground(new Color(78, 90, 99));
            jButton1.setFont(new Font("Cambria", Font.BOLD, 50));
            jButton2.setBounds(930, 240, 220, 110);
            jButton2.setBackground(new Color(121, 97, 140));
            jButton2.setForeground(new Color(78, 90, 99));
            jButton2.setFont(new Font("Cambria", Font.BOLD, 50));
            jTextField1.setFont(new Font("Cambria", Font.PLAIN, 35));
            jTextField1.setBackground(new Color(197, 223, 194));
            jTextField1.setForeground(new Color(99, 99, 99));
            jTextField1.setBounds(860, 390, 300, 50);
            jLabel1.setForeground(new Color(57, 57, 57));
            jLabel1.setFont(new Font("Cambria", Font.PLAIN, 38));
            jLabel1.setBounds(0, 390, 880, 50);
            jLabel2.setForeground(new Color(123, 71, 99));
            jLabel2.setFont(new Font("Cambria", Font.PLAIN, 38));
            jLabel2.setBounds(0, 440, 880, 50);
            jTextField2.setFont(new Font("Cambria", Font.PLAIN, 35));
            jTextField2.setBackground(new Color(164, 199, 203));
            jTextField2.setForeground(new Color(202, 113, 80));
            jTextField2.setBounds(860, 440, 300, 50);
            jLabel3.setForeground(new Color(96, 56, 126));
            jLabel3.setFont(new Font("Cambria", Font.PLAIN, 38));
            jLabel3.setBounds(0, 490, 880, 50);
            ganttChart.setForeground(new Color(44, 78, 79));
            ganttChart.setFont(new Font("Cambria", Font.PLAIN, 51));
            ganttChart.setBounds(14, 524, 1190, 30);
            ganttChart1.setForeground(new Color(44, 78, 79));
            ganttChart1.setFont(new Font("Cambria", Font.PLAIN, 65));
            ganttChart1.setBounds(4, 546, 1200, 40);
            ganttChart2.setForeground(new Color(44, 78, 79));
            ganttChart2.setFont(new Font("Cambria", Font.PLAIN, 51));
            ganttChart2.setBounds(14, 565, 1190, 30);
            ganttChart3.setForeground(new Color(44, 78, 79));
            ganttChart3.setFont(new Font("Cambria", Font.PLAIN, 65));
            ganttChart3.setBounds(4, 546, 1200, 40);
            ganttChart5.setForeground(new Color(252, 83, 65));
            ganttChart5.setFont(new Font("Cambria", Font.PLAIN, 40));
            ganttChart5.setBounds(54, 543, 1200, 45);
            ganttChart4.setForeground(new Color(5, 163, 114));
            ganttChart4.setFont(new Font("Cambria", Font.PLAIN, 40));
            ganttChart4.setBounds(10, 588, 1200, 40);
            ganttChart6.setForeground(new Color(15, 154, 107));
            ganttChart6.setFont(new Font("Cambria", Font.PLAIN, 40));
            ganttChart6.setBounds(10, 588, 1200, 40);
            jTable.setBounds(0, 60, 900, 320);
            jTable.setBackground(new Color(231, 221, 182));
            jTable.setFont(new Font("Cambria", Font.PLAIN, 38));
            jTable.setRowHeight(0, 40);
            for(int i=1;i<9;i++)
                jTable.setRowHeight(i, 35);
            jTable.setForeground(new Color(154, 16, 62));
            JTableHeader tableHeader = jTable.getTableHeader();
            
            //handle data changes listening
            jTable.getModel().addTableModelListener(jTable);
            int n=0;
            String[] atS = new String[8];
            String[] btS = new String[8];
            int[] at = new int[8];
            int[] bt = new int[8];
            jButton1.addMouseListener(new MouseAdapter(){
                @Override
                public void mouseClicked(MouseEvent e){
                    
                    //set default label:
                    jTable.setValueAt("Process ID", 0, 0);
                    jTable.setValueAt("01", 1, 0);
                    jTable.setValueAt("02", 2, 0);
                    jTable.setValueAt("03", 3, 0);
                    jTable.setValueAt("04", 4, 0);
                    jTable.setValueAt("05", 5, 0);
                    jTable.setValueAt("06", 6, 0);
                    jTable.setValueAt("07", 7, 0);
                    jTable.setValueAt("08", 8, 0);
                    jTable.setValueAt("Arrival Time", 0, 1);
                    jTable.setValueAt("Burst Time", 0, 2);
                    
                    @SuppressWarnings("UnusedAssignment")
                    String[] atS = new String[8];
                    @SuppressWarnings("UnusedAssignment")
                    String[] btS = new String[8];
                    @SuppressWarnings("UnusedAssignment")
                    int[] at = new int[8];
                    @SuppressWarnings("UnusedAssignment")
                    int[] bt = new int[8];
                    int countNullAndUnSpecify1 = 1;
                    int countNullAndUnSpecify2 = 1;
                    int n,j=0,k=0;
                    String[] checkStringNull = new String[9];
                    boolean checkLetterInput = false;
                    boolean checkNegativeNumberInput = false;
                    boolean checkSpecialCharacterInput = false;
                    boolean checkNullOrMissingInput = false;
                    Pattern regex = Pattern.compile("[^A-Za-z0-9]");
                    for(int d=0;d<9;d++){
                        checkStringNull[d] = (String)jTable.getValueAt(d, 1);
                        Matcher matcher = regex.matcher(checkStringNull[d]);
                        if(checkStringNull[d] == null || "".equals(checkStringNull[d]))
                            countNullAndUnSpecify1++;
                        else if(checkStringNull[d].matches("[a-zA-Z]{1}")==true)
                            checkLetterInput = true;
                        else if(checkStringNull[d].matches("-[0-9]{1}")){
                            checkNegativeNumberInput = true;
                        }
//                        else if(checkStringNull[d].matches("[a-zA-Z]{1}")==false&&checkStringNull[d].matches("[0-9]{1}")==false && (checkStringNull[d] == null && "".equals(checkStringNull[d]))==true)
//                            checkSpecialCharacterInput=true;
                    }
                    for(int d=0;d<9;d++){
                        Matcher matcher = regex.matcher(checkStringNull[d]);
                        checkStringNull[d] = (String)jTable.getValueAt(d, 2);
                        if(checkStringNull[d] == null || "".equals(checkStringNull[d]))
                            countNullAndUnSpecify2++;
                        else if(checkStringNull[d].matches("[a-zA-Z]{1}")==true)
                            checkLetterInput=true;
                        else if(checkStringNull[d].matches("-[0-9]{1}")){
                            checkNegativeNumberInput = true;
                        }
//                        else if(checkStringNull[d].matches("[a-zA-Z]{1}")==false&&checkStringNull[d].matches("[0-9]{1}")==false && (checkStringNull[d] == null && "".equals(checkStringNull[d]))==true)
//                            checkSpecialCharacterInput=true;
//                        else if(checkStringNull[d].matches("[0-9]{1}")==true)
//                            checkSpecialCharacterInput = false;
//                        else if(matcher.find()==false)
//                            checkSpecialCharacterInput=true;
                    }
                    if(countNullAndUnSpecify1!=countNullAndUnSpecify2){
                        checkNullOrMissingInput = true;
                        JOptionPane.showConfirmDialog(null, "Bạn phải điền đầy đủ các số liệu vào cả 2 cột số liệu ở trong bảng.");
                    } else if(countNullAndUnSpecify1==9||countNullAndUnSpecify2==9){
                        checkNullOrMissingInput = true;
                        JOptionPane.showConfirmDialog(null, "Bạn không được để trống các trường thông tin trong bảng.Xin mời nhập số liệu.");
                    }
                    if (checkNegativeNumberInput==true){
                        JOptionPane.showConfirmDialog(null, "Bạn đã điền số âm vào trường thông tin của bảng.Xin hãy nhập lại số liệu.");
                    } else if(checkLetterInput==true) 
                        JOptionPane.showConfirmDialog(null, "Bạn đã điền định dạng chữ cái vào trong trường thông tin của bảng. Xin mời nhập lại số liệu.");
                    else if(checkSpecialCharacterInput==true) 
                        JOptionPane.showConfirmDialog(null, "Bạn đã điền kí tự đặc biệt vào trong trường thông tin của bảng. Xin mời nhập lại số liệu.");
                    n=9-countNullAndUnSpecify1;
                    atS = new String[n];
                    btS = new String[n];
                    at = new int[n];
                    bt = new int[n];
                    if(checkLetterInput==false && checkSpecialCharacterInput==false && checkNullOrMissingInput == false && checkNegativeNumberInput==false){
                        for(int i=1;i< atS.length+1;i++){
                            atS[j++]=(String)jTable.getValueAt(i, 1);
                        }
                        for(int i=1;i< btS.length+1;i++){
                            btS[k++]=(String)jTable.getValueAt(i, 2);
                        }
                        for(int i=0;i<atS.length;i++){
                            try {
                                at[i] = Integer.parseInt(atS[i]);
                            } catch (NumberFormatException nfe){
        //                            countNullAndUnSpecify++;
        //                            System.out.println("error:"+nfe.getMessage());
        //                            JOptionPane.showConfirmDialog(null, "Bạn phải điền số liệu vào các trường thông tin ở trong bảng.");
                            }
                        }
                        for(int i=0;i<btS.length;i++){
                            try {
                                bt[i] = Integer.parseInt(btS[i]);
                            } catch (NumberFormatException nfe){
        //                            System.out.println("error:"+nfe.getMessage());
        //                            JOptionPane.showConfirmDialog(null, "Bạn phải điền số liệu vào các trường thông tin ở trong bảng.");
                                }
                        }
    //                    for(int i: at){
    //                        System.out.println(i);
    //                    }
    //                    for(int f: bt){
    //                        System.out.println(f);
    //                    }
                        float result1 = SJFNonPre1(n, at, bt);
                        float result2 = SJFNonPre2(n, at, bt);
                        jTextField1.setText(""+result1);
                        jTextField2.setText(""+result2);

                        //drawing a rectangle:
    //                    paint(g);

                        int[] atNew = new int[n];
                        @SuppressWarnings("UnusedAssignment")
                        int mid, minVal =0;
                        System.arraycopy(at, 0, atNew, 0, n);
                        for(int i=0;i<n-1;i++)
                            for(int w=i+1;w<n;w++)
                                if(atNew[i]>atNew[w]){
                                    mid = atNew[i];
                                    atNew[i] = atNew[w];
                                    atNew[w] = mid;
                                }
                        minVal = atNew[0];

                        //set gantt chart via Jlabel setting:
                        String text = "";
                        String text1 = "";
                        String text2 = "";
                        String text4 = "";
                        String text5 = "";
                        String text6 = "";
                        for(int i=0;i<68;i++)
                            text+="-";
                        for(int i=0;i<83;i++){
                            if(i==0||i==82)
                                text1+="|";
                            else text1+=" ";
                        }
                        int sum1 =minVal;
                        for(int i=0;i<bt.length;i++)
                            sum1+=bt[i];
    //                    for(int i=0;i<83;i++){
    //                        switch (i) {
    //                            case 0:
    //                                text4+="0";
    //                                break;
    //                            default:
    //                                text4+=" ";
    //                                break;
    //                        }
    //                    }
                        boolean checkSuspend = false;
                        int count2=0, pointVal6 = 20, pointVal7 = 12;
                        for(int i=0;i<103;i++){
                            if(i==0) text4+=""+minVal+" "; 
                            else if(count2<n-1 && i==pointVal6){
                                text4+="  ";
                                count2++;
                                pointVal6+=pointVal7;
                                checkSuspend = true;
                            } else if(count2==n-1 && checkSuspend==true){ 
                                text4+=" "+sum1;
                                checkSuspend = false;
                            } else text4+=" ";
                        }
                        int count=0, pointVal = 8, pointVal1 = 8;
                        for(int i=0;i<83;i++){
                            if(count<n && i==pointVal){
                                text2+="|";
                                count++;
                                pointVal+=pointVal1;
                            } else text2+=" ";
                        }
                        int count1 = 0, pointVal2 = 0, pointVal3 = 8, u=0;
                        int[] resultAl4 = new int[n];
                        System.arraycopy(SJFNonPre4(n, at, bt), 0, resultAl4, 0, n);
                        for(int i=0;i<83;i++){
                            if(count1<n && i==pointVal2){
                                text5+="P"+resultAl4[u];
                                text5+=" ";
                                count1++;
                                pointVal2+=pointVal3;
                                u++;
                            } else text5+=" ";
                        }
                        int count3=0, pointVal8 = 12, pointVal9 = 10;
                        if(sum1>100) {
                            pointVal8 = 10;
                            pointVal9 = 9;
                        }
                        else if(sum1<=100 && sum1>=20) {
                            pointVal8 = 9;
                            pointVal9 = 10;
                        } else if(sum1<=20){
                            pointVal8 = 10;
                            pointVal9 = 12;
                        }

                        int SJFi = 0;

                        //try to delete the last index of sjfNonPreArr:
                        int[] sjfNonPreArr = SJFNonPre3(n, at, bt);
                        int[] sjfNonPreArr1 = new int[sjfNonPreArr.length-1];
                        System.arraycopy(sjfNonPreArr, 0, sjfNonPreArr1, 0, sjfNonPreArr1.length);
    //                    for(int i:sjfNonPreArr1)
    //                        System.out.println(i);
                        for(int i=0;i<93;i++){
                            if(count3<n-1 && i==pointVal8){
                                text6+=""+sjfNonPreArr1[SJFi++];
                                count3++;
                                pointVal8+=pointVal9;
                            } else text6+=" ";
                        }
                        ganttChart2.setText(text);
                        ganttChart.setText(text);
                        ganttChart1.setText(text1);
                        ganttChart3.setText(text2);
                        ganttChart4.setText(text4);
                        ganttChart5.setText(text5);
                        ganttChart6.setText(text6);
    //                    ganttChart4.setBackground(new Color(121, 97, 140));
                    }
                }
            });
            jButton2.addMouseListener(new MouseAdapter(){
                @Override
                public void mouseClicked(MouseEvent e){
                    
                    //set default label:
                    jTable.setValueAt("Process ID", 0, 0);
                    jTable.setValueAt("01", 1, 0);
                    jTable.setValueAt("02", 2, 0);
                    jTable.setValueAt("03", 3, 0);
                    jTable.setValueAt("04", 4, 0);
                    jTable.setValueAt("05", 5, 0);
                    jTable.setValueAt("06", 6, 0);
                    jTable.setValueAt("07", 7, 0);
                    jTable.setValueAt("08", 8, 0);
                    jTable.setValueAt("Arrival Time", 0, 1);
                    jTable.setValueAt("Burst Time", 0, 2);
                    
                    String value = "";
                    for(int i=1;i<9;i++){
                        jTable.setValueAt(value, i, 1);
                        jTable.setValueAt(value, i, 2);
                    }
                    jTextField1.setText("");
                    jTextField2.setText("");
                    ganttChart2.setText("");
                    ganttChart.setText("");
                    ganttChart1.setText("");
                    ganttChart3.setText("");
                    ganttChart4.setText("");
                    ganttChart5.setText("");
                    ganttChart6.setText("");
                }
            });
//            jButton1.addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent e) {
//                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//                }
//            }) -> {
//                for(int i=1;i< jTable.getRowCount();i++)
//                    for(int j=1;j<jTable.getColumnCount();j++)
//                        colValues.add((Integer)jTable.getValueAt(i, j));
//                JOptionPane.showMessageDialog(contentPanel, colValues);
//            });
            
            //set Table's column width: (error !! so sad)
            jTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            jTable.getColumnModel().getColumn(0).setPreferredWidth(10);
            jTable.getColumnModel().getColumn(1).setPreferredWidth(40);
            jTable.getColumnModel().getColumn(2).setPreferredWidth(40);
            
            //set Table's alignment:
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(JLabel.CENTER);
            jTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
            jTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
            jTable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);

            //add components:
            super.add(d);
            super.add(jPanel1);
            super.add(jTable);
            super.add(jButton1);
            super.add(jButton2);
            super.add(jLabel1);
            super.add(jLabel2);
            super.add(jLabel3);
            super.add(ganttChart);
            super.add(ganttChart1);
            super.add(ganttChart2);
            super.add(ganttChart3);
            super.add(ganttChart4);
            super.add(ganttChart5);
            super.add(ganttChart6);
            super.add(jTextField1);
            super.add(jTextField2);
            //draw a gantt chart by drawing an hollow asterisk rectangle:
//            drawHollowAstRect(3, 5);
        }
        //make a gantt chart via drawing a rectangle:
        private void paint(Graphics g, int n){
            Graphics2D g2 = (Graphics2D) g;
//                paintComponent(g);
//            g.setColor(new Color(105, 145, 197));
//            int i=10, sum=0;
//            while(sum<4){
//                g2.drawRect(i,550,1000/n,40);
//                i+=40;
//                sum++;
//            }
        }
        
        //make a gantt chart via drawing an hollow asterisk rectangle:
        private void drawHollowAstRect(int h, int w){
            for(int j=1; j<=h; j++){  
                for(int i=1; i<=w; i++){
                    if(j ==1 || j==h || i==1 || i==w){
                        System.out.print("* ");
                    } else {
                        System.out.print("  ");
                    }
                }
            System.out.println();
            }
        }
    }
    
    class MyPanelAppDemo3 extends JPanel{
        ActionListener l;
        ActionEvent e;
        private JPanel contentPanel;
        public Drawing d;
        public JPanel jPanel;
        public JLabel jLabel1;
        public JLabel jLabel2;
        public JLabel jLabel3;
        public JLabel jLabel4;
        public TextField textField1;
        public TextField textField2;
        public TextField textField3;
        public TextField textField4;
        public JButton jButton1;
        public JButton jButton2;
        public JButton jButton3;
        public MyPanelAppDemo3(){
            super.setPreferredSize(new Dimension(1200, 500));
            super.setLayout(null);
            jPanel = new JPanel();
            d = new Drawing("               Shortest Seek Time First Algorithm", new Font("Cambria", Font.ITALIC, 50), new Color(160, 242, 210));
            jLabel1 = new JLabel("- Dãy các cylinder được yêu cầu đọc ghi:");
            jLabel2 = new JLabel("- Đầu đọc đang nằm ở cylinder:");
            jLabel3 = new JLabel("- Tổng số bước dịch chuyển của đầu đọc ghi:");
            jLabel4 = new JLabel("- Thứ tự lần lượt các cylinder mà đầu đọc đã đọc ghi theo thuật toán:");
            textField1 = new TextField("...");
            textField2 = new TextField("...");
            textField3 = new TextField("...");
            textField4 = new TextField("...");
            jButton1 = new JButton("Submit");
            jButton2 = new JButton("Reset");
            jButton3 = new JButton("Exit");
            
            //customize ur own attributes:
            d.setBounds(-150, 0, 1450, 60);
            jPanel.setBackground(Color.gray);
            jPanel.setBounds(0, 0, 1200, 60);            
            jLabel1.setForeground(new Color(145, 85, 190));
            jLabel1.setFont(new Font("Cambria", Font.PLAIN, 34));
            jLabel1.setBounds(0, 60, 750, 50);
            textField1.setFont(new Font("Cambria", Font.BOLD, 32));
            textField1.setBackground(new Color(197, 223, 194));
            textField1.setForeground(new Color(99, 99, 99));
            textField1.setBounds(750, 60, 420, 50);
            jLabel2.setForeground(new Color(123, 71, 99));
            jLabel2.setFont(new Font("Cambria", Font.PLAIN, 34));
            jLabel2.setBounds(0, 120, 700, 50);
            textField2.setFont(new Font("Cambria", Font.BOLD, 32));
            textField2.setBackground(new Color(164, 199, 203));
            textField2.setForeground(new Color(202, 113, 80));
            textField2.setBounds(750, 120, 210, 50);
            jLabel3.setForeground(new Color(57, 57, 57));
            jLabel3.setFont(new Font("Cambria", Font.PLAIN, 34));
            jLabel3.setBounds(0, 180, 750, 50);
            textField3.setFont(new Font("Cambria", Font.BOLD, 32));
            textField3.setBackground(new Color(196, 185, 141));
            textField3.setForeground(new Color(78, 90, 99));
            textField3.setBounds(750, 180, 210, 50);
            jLabel4.setForeground(new Color(105, 145, 197));
            jLabel4.setFont(new Font("Cambria", Font.PLAIN, 34));
            jLabel4.setBounds(0, 240, 1500, 50);
            textField4.setFont(new Font("Cambria", Font.BOLD, 32));
            textField4.setBackground(new Color(179, 195, 145));
            textField4.setForeground(new Color(70, 96, 131));
            textField4.setBounds(20, 300, 1160, 60);
            jButton1.setBackground(Color.DARK_GRAY);
            jButton1.setForeground(Color.cyan);
            jButton1.setBounds(970, 130, 200, 90);
            jButton1.setFont(new Font("Cambria", Font.BOLD, 50));
            jButton2.setBackground(Color.DARK_GRAY);
            jButton2.setForeground(Color.cyan);
            jButton2.setBounds(500, 370, 200, 80);
            jButton2.setFont(new Font("Cambria", Font.BOLD, 60));
            jButton3.setBackground(Color.DARK_GRAY);
            jButton3.setForeground(Color.cyan);
            jButton3.setBounds(900, 370, 200, 80);
            jButton3.setFont(new Font("Cambria", Font.BOLD, 55));
            
            //add the components:
            super.add(d);
            super.add(jPanel);
            super.add(jLabel1);
            super.add(jLabel2);
            super.add(jLabel3);
            super.add(jLabel4);
            super.add(textField1);
            super.add(textField2);
            super.add(textField3);
            super.add(textField4);
            super.add(jButton1);
            super.add(jButton2);
//            super.add(jButton3);
            
            //handle mouse click events of button:
            try{
            jButton1.addMouseListener(new MouseAdapter(){
                @Override
                public void mouseClicked(MouseEvent e){
                    if("".equals(textField1.getText()) || "".equals(textField2.getText()) || textField2.getText() == null || textField1.getText() == null){
                        JOptionPane.showConfirmDialog(null, "Bạn phải điền thông tin vào 2 trường văn bản đầu tiên được yêu cầu.");
                    }
                    String infor1 = textField1.getText();
                    String infor2 = textField2.getText();    
                }
            });
            } catch(NumberFormatException nfe){
                JOptionPane.showConfirmDialog(null, "Bạn phải điền thông tin vào 2 trường văn bản đầu tiên được yêu cầu.");
            }
            try{
            jButton1.addActionListener((ActionEvent e1) -> {
                
                if("".equals(textField1.getText()) || "".equals(textField2.getText()) || textField2.getText() == null || textField1.getText() == null){
                    JOptionPane.showConfirmDialog(null, "Bạn phải điền thông tin vào 2 trường văn bản đầu tiên được yêu cầu.");
                } else{
                String infor1 = textField1.getText();
                String infor2 = textField2.getText();
                String[] c = infor1.split(", ");
                int[] arrIntInput = new int[c.length];
                for(int i=0;i<c.length;i++){
                    try {
                        arrIntInput[i] = Integer.parseInt(c[i]);
                    } catch (NumberFormatException nfe){
                        System.out.println("error:"+nfe.getMessage());
                        JOptionPane.showConfirmDialog(null, "Bạn phải điền thông tin vào 2 trường văn bản đầu tiên được yêu cầu.");
                    }
                }
                int numInput = Integer.parseInt(infor2);
                textField3.setText(shortestSeekTimeFirst(arrIntInput, numInput));
                textField4.setText(shortestSeekTimeFirst1(arrIntInput, numInput));
                }
            });
            } catch(NumberFormatException nfe){
                JOptionPane.showConfirmDialog(null, "Bạn phải điền thông tin vào 2 trường văn bản đầu tiên được yêu cầu.");
            }
//            actionPerformed(e);
//            if((JButton)e.getSource() == jButton1){
//            }
            jButton2.addMouseListener(new MouseAdapter(){
                @Override
                public void mouseClicked(MouseEvent e){
                    textField1.setText(null);
                    textField2.setText(null);
                    textField3.setText(null);
                    textField4.setText(null);
                }
            });
            jButton3.addMouseListener(new MouseAdapter(){
                @Override
                public void mouseClicked(MouseEvent e){
                    int click = JOptionPane.showConfirmDialog(null, "Bạn muốn thoát chương trình hiện tại đúng không?");
                    if(click == JOptionPane.YES_OPTION){
                        CardLayout cardLayout2 = (CardLayout)contentPanel.getLayout();
                        cardLayout2.next(contentPanel);
                        cardLayout2.next(contentPanel);
                        setVisible(false);
                    }
                }
            });
                //Error here:
//            jButton3.addActionListener((ActionEvent ae) -> {
//                CardLayout cardLayout2 = (CardLayout)contentPanel.getLayout();
//                cardLayout2.next(contentPanel);
//                cardLayout2.next(contentPanel);
//            });
        }
//        private void actionPerformed(ActionEvent e){
//            if((JButton)e.getSource() == jButton1){
//                String infor1 = textField1.getText();
//                String[] c = infor1.split(", ");
//                int[] arrIntInput = new int[c.length];
//                for(int i=0;i<c.length;i++){
//                    try {
//                        arrIntInput[i] = Integer.parseInt(c[i]);
//                    } catch (NumberFormatException nfe){
//                        System.out.println("error:"+nfe.getMessage());
//                    }
//                }
//                String infor2 = textField2.getText();
//                int numInput = Integer.parseInt(infor2);
//                textField3.setText(shortestSeekTimeFirst(arrIntInput, numInput));
//                textField4.setText(shortestSeekTimeFirst1(arrIntInput, numInput));
//            }
//        }
    }
    private static void calculateDifference(int queue[], int head, node diff[]) 
    { 
        for (int i = 0; i < diff.length; i++) 
            diff[i].distance = Math.abs(queue[i] - head); 
    } 
  
    // find unaccessed track  
    // which is at minimum distance from head 
    private static int findMin(node diff[]) 
    { 
        int index = -1, minimum = Integer.MAX_VALUE; 
  
        for (int i = 0; i < diff.length; i++) { 
            if (!diff[i].accessed && minimum > diff[i].distance) { 
                  
                minimum = diff[i].distance; 
                index = i; 
            } 
        } 
        return index; 
    } 
  
    public static String shortestSeekTimeFirst(int request[],  int head) 
                                                       
    { 
        if (request.length == 0) 
            return ""; 
              
        // create array of objects of class node     
        node diff[] = new node[request.length];  
          
        // initialize array 
        for (int i = 0; i < diff.length; i++)  
          
            diff[i] = new node(); 
          
        // count total number of seek operation     
        int seek_count = 0;  
          
        // stores sequence in which disk access is done 
        int[] seek_sequence = new int[request.length + 1];  
          
        for (int i = 0; i < request.length; i++) { 
              
            seek_sequence[i] = head; 
            calculateDifference(request, head, diff); 
              
            int index = findMin(diff); 
              
            diff[index].accessed = true; 
              
            // increase the total count 
            seek_count += diff[index].distance;  
              
            // accessed track is now new head 
            head = request[index];  
        } 
          
        // for last accessed track 
        seek_sequence[seek_sequence.length - 1] = head;  
          
//        System.out.println("Total number of seek operations = " 
//                                                     + seek_count); 
                                                       
//        System.out.println("Seek Sequence is"); 
          
        // print the sequence 
//        for (int i = 0; i < seek_sequence.length; i++)  
//            System.out.print(seek_sequence[i]); 
    return ""+seek_count;
    }
    
    public static String shortestSeekTimeFirst1(int request[],  int head) 
                                                       
    {
        String result="";
        if (request.length == 0) 
            return ""; 
              
        // create array of objects of class node     
        node diff[] = new node[request.length];  
          
        // initialize array 
        for (int i = 0; i < diff.length; i++)  
          
            diff[i] = new node(); 
          
        // count total number of seek operation     
        int seek_count = 0;  
          
        // stores sequence in which disk access is done 
        int[] seek_sequence = new int[request.length + 1];  
          
        for (int i = 0; i < request.length; i++) { 
              
            seek_sequence[i] = head; 
            calculateDifference(request, head, diff); 
              
            int index = findMin(diff); 
              
            diff[index].accessed = true; 
              
            // increase the total count 
            seek_count += diff[index].distance;  
              
            // accessed track is now new head 
            head = request[index];  
        } 
          
        // for last accessed track 
        seek_sequence[seek_sequence.length - 1] = head;  
          
//        System.out.println("Total number of seek operations = " 
//                                                     + seek_count); 
                                                       
//        System.out.println("Seek Sequence is"); 
          
        // print the sequence
        result += "";
        for (int i = 0; i < seek_sequence.length; i++)
            result+=seek_sequence[i]+" => ";
//            System.out.print(seek_sequence[i]);
        result = result.substring(0, result.length()-3);
    return result;
    }
    
    public static float SJFNonPre1(int n, int[] at, int[] bt){
        int pid[];
        pid = new int[n];
        int ct[] = new int[n]; // ct means complete time
        int ta[] = new int[n]; // ta means turn around time
        int wt[] = new int[n];  //wt means waiting time
        int f[] = new int[n];  // f means it is flag it checks process is completed or not
        int st=0, tot=0;
        double avgwt=0, avgta=0;
        for(int i=0;i<n;i++){
            pid[i] = i+1;
            f[i] = 0;
        }
        boolean a = true;
        while(true){
            int c=n, min=999;
            if (tot == n) // total no of process = completed process loop will be terminated
                break;
            for (int i=0; i<n; i++){
                /*
                * If i'th process arrival time <= system time and its flag=0 and burst<min
                * That process will be executed first
                */
                if ((at[i] <= st) && (f[i] == 0) && (bt[i]<min)){
                    min=bt[i];
                    c=i;
                }
            }
            /* If c==n means c value can not updated because no process arrival time< system time so we increase the system time */
            if (c==n) st++;
            else{
                ct[c]=st+bt[c];
                st+=bt[c];
                ta[c]=ct[c]-at[c];
                wt[c]=ta[c]-bt[c];
                f[c]=1;
                tot++;
            }
        }
        for(int i=0;i<n;i++){
            avgwt+= wt[i];
            avgta+= ta[i];
        }
        double result1 = avgwt/n;
        double result2 = avgta/n;            
        return (float)result1;
    }
    
    public static float SJFNonPre2(int n, int[] at, int[] bt){
        int pid[];
        pid = new int[n];
        int ct[] = new int[n]; // ct means complete time
        int ta[] = new int[n]; // ta means turn around time
        int wt[] = new int[n];  //wt means waiting time
        int f[] = new int[n];  // f means it is flag it checks process is completed or not
        int st=0, tot=0;
        double avgwt=0, avgta=0;
        for(int i=0;i<n;i++){
            pid[i] = i+1;
            f[i] = 0;
        }
        boolean a = true;
        while(a){
            int c=n, min=999;
            if (tot == n) // total no of process = completed process loop will be terminated
                break;
            for (int i=0; i<n; i++){
                /*
                * If i'th process arrival time <= system time and its flag=0 and burst<min
                * That process will be executed first
                */
                if ((at[i] <= st) && (f[i] == 0) && (bt[i]<min)){
                    min=bt[i];
                    c=i;
                }
            }
            /* If c==n means c value can not updated because no process arrival time< system time so we increase the system time */
            if (c==n) st++;
            else{
                ct[c]=st+bt[c];
                st+=bt[c];
                ta[c]=ct[c]-at[c];
                wt[c]=ta[c]-bt[c];
                f[c]=1;
                tot++;
            }
        }
        for(int i=0;i<n;i++){
            avgwt+= wt[i];
            avgta+= ta[i];
        }
        double result1 = avgwt/n;
        double result2 = avgta/n;            
        return (float)result2;
    }
    
    public static int[] SJFNonPre3(int n, int[] at, int[] bt){
        int pid[];
        pid = new int[n];
        int ct[] = new int[n]; // ct means complete time
        int ta[] = new int[n]; // ta means turn around time
        int wt[] = new int[n];  //wt means waiting time
        int f[] = new int[n];  // f means it is flag it checks process is completed or not
        int st=0, tot=0, st1i = 0;
        int st1[] = new int[n];
        double avgwt=0, avgta=0;
        for(int i=0;i<n;i++){
            pid[i] = i+1;
            f[i] = 0;
        }
        boolean a = true;
        while(a){
            int c=n, min=999;
            if (tot == n) // total no of process = completed process loop will be terminated
                break;
            for (int i=0; i<n; i++){
                /*
                * If i'th process arrival time <= system time and its flag=0 and burst<min
                * That process will be executed first
                */
                if ((at[i] <= st) && (f[i] == 0) && (bt[i]<min)){
                    min=bt[i];
                    c=i;
                }
            }
            /* If c==n means c value can not updated because no process arrival time< system time so we increase the system time */
            if (c==n){ 
                st++;
            }
            else{
                ct[c]=st+bt[c];
                st+=bt[c];
                ta[c]=ct[c]-at[c];
                wt[c]=ta[c]-bt[c];
                f[c]=1;
                tot++;
                st1[st1i++] =st;
            }
        }
        for(int i=0;i<n;i++){
            avgwt+= wt[i];
            avgta+= ta[i];
        }
        double result1 = avgwt/n;
        double result2 = avgta/n;            
        return st1;
    }
    
    public static int[] SJFNonPre4(int n, int[] at, int[] bt){
        int pid[];
        pid = new int[n];
        int ct[] = new int[n]; // ct means complete time
        int ta[] = new int[n]; // ta means turn around time
        int wt[] = new int[n];  //wt means waiting time
        int f[] = new int[n];  // f means it is flag it checks process is completed or not
        int st=0, tot=0, st1i = 0, f1i = 0;
//        int st1[] = new int[n];
        int f1[] = new int[n];
        double avgwt=0, avgta=0;
        for(int i=0;i<n;i++){
            pid[i] = i+1;
            f[i] = 0;
        }
        boolean a = true;
        while(a){
            int c=n, min=999;
            if (tot == n) // total no of process = completed process loop will be terminated
                break;
            for (int i=0; i<n; i++){
                /*
                * If i'th process arrival time <= system time and its flag=0 and burst<min
                * That process will be executed first
                */
                if ((at[i] <= st) && (f[i] == 0) && (bt[i]<min)){
                    min=bt[i];
                    c=i;
                }
            }
            /* If c==n means c value can not updated because no process arrival time< system time so we increase the system time */
            if (c==n){ 
                st++;
            }
            else{
                ct[c]=st+bt[c];
                st+=bt[c];
                ta[c]=ct[c]-at[c];
                wt[c]=ta[c]-bt[c];
                f1[f1i++]= c+1;
                f[c]=1;
                tot++;
//                st1[st1i++] =st;
            }
        }
        for(int i=0;i<n;i++){
            avgwt+= wt[i];
            avgta+= ta[i];
        }
        double result1 = avgwt/n;
        double result2 = avgta/n;            
        return f1;
    }
    
    public static float SJFPre1(int n, int[] at, int[] bt){
        int pid[]; // it takes pid of process
        pid = new int[n];
	int ct[] = new int[n]; // ct means complete time
	int ta[] = new int[n];// ta means turn around time
	int wt[] = new int[n];  // wt means waiting time
	int f[] = new int[n];  // f means it is flag it checks process is completed or not
	int k[]= new int[n];   // it is also stores brust time
	int i, st=0, tot=0;
	double avgwt=0, avgta=0;
        for (i=0;i<n;i++){
	    	pid[i]= i+1;
	    	k[i]= bt[i];
	    	f[i]= 0;
	}
        while(true){
            int min=99,c=n;
	    if (tot==n)
	    	break;
	    for ( i=0;i<n;i++){
	   	if ((at[i]<=st) && (f[i]==0) && (bt[i]<min)){	
                    min=bt[i];
                    c=i;
	    	}
	    }
	    if (c==n)
	    	st++;
	    else{
	    	bt[c]--;
	    	st++;
	    	if(bt[c]==0){
                    ct[c]= st;
                    f[c]=1;
                    tot++;
	    	}
	    }
	}
	for(i=0;i<n;i++){
	    ta[i] = ct[i] - at[i];
	    wt[i] = ta[i] - k[i];
	    avgwt+= wt[i];
	    avgta+= ta[i];
	}
//        System.out.println("OK a");
        double result1 = (float)avgwt/n;
        double result2 = (float)avgta/n;
        return (float)result1;
    }
    
    public static float SJFPre2(int n, int[] at, int[] bt){
        int pid[]; // it takes pid of process
        pid = new int[n];
        boolean q = true;
	int ct[] = new int[n]; // ct means complete time
	int ta[] = new int[n];// ta means turn around time
	int wt[] = new int[n];  // wt means waiting time
	int f[] = new int[n];  // f means it is flag it checks process is completed or not
	int k[]= new int[n];   // it is also stores brust time
	int i, st=0, tot=0;
	double avgwt=0, avgta=0;
//        System.out.println("OK a1");
        for (i=0;i<n;i++){
	    	pid[i]= i+1;
	    	k[i]= bt[i];
	    	f[i]= 0;
	    }
        while(q){
	    int min=99,c=n;
            if (tot==n)
	    	break;
            for ( i=0;i<n;i++){
	  	if ((at[i]<=st) && (f[i]==0) && (bt[i]<min)){	
                    min=bt[i];
                    c=i;
	    	}
	    }
	    if (c==n) st++;
	    else{
                bt[c]--;
	    	st++;
	    	if (bt[c]==0){
	    	ct[c]= st;
	  	f[c]=1;
	 	tot++;
	   	}
	    }
	}
	for(i=0;i<n;i++){
	    ta[i] = ct[i] - at[i];
	    wt[i] = ta[i] - k[i];
	    avgwt+= wt[i];
	    avgta+= ta[i];
//            System.out.println("aaa");
	}
//        System.out.println("OK a1");
        double result1 = (float)avgwt/n;
        double result2 = (float)avgta/n;
//        System.out.println("OK b");
        return (float)result2;
    }
    
    public static int[] SJFPre3(int n, int[] at, int[] bt){
        int pid[]; // it takes pid of process
        pid = new int[n];
	int ct[] = new int[n]; // ct means complete time
	int ta[] = new int[n];// ta means turn around time
	int wt[] = new int[n];  // wt means waiting time
	int f[] = new int[n];  // f means it is flag it checks process is completed or not
	int k[]= new int[n];   // it is also stores brust time
	int i, st=0, tot=0, f1i =0;
        int[] f1 = new int[n];
	double avgwt=0, avgta=0;
        for (i=0;i<n;i++){
	    	pid[i]= i+1;
	    	k[i]= bt[i];
	    	f[i]= 0;
	}
        while(true){
            int min=99,c=n;
	    if (tot==n)
	    	break;
	    for ( i=0;i<n;i++){
	   	if ((at[i]<=st) && (f[i]==0) && (bt[i]<min)){	
                    min=bt[i];
                    c=i;
	    	}
	    }
	    if (c==n)
	    	st++;
	    else{
	    	bt[c]--;
	    	st++;
	    	if(bt[c]==0){
                    ct[c]= st;
                    f1[f1i++]=c+1;
                    f[c]=1;
                    tot++;
	    	}
	    }
	}
	for(i=0;i<n;i++){
	    ta[i] = ct[i] - at[i];
	    wt[i] = ta[i] - k[i];
	    avgwt+= wt[i];
	    avgta+= ta[i];
	}
//        System.out.println("OK b");
//        double result1 = (float)avgwt/n;
//        double result2 = (float)avgta/n;
        return f1;
    }
    public static int[] SJFPre4(int n, int[] at, int[] bt){
        int pid[]; // it takes pid of process
        pid = new int[n];
	int ct[] = new int[n]; // ct means complete time
	int ta[] = new int[n];// ta means turn around time
	int wt[] = new int[n];  // wt means waiting time
	int f[] = new int[n];  // f means it is flag it checks process is completed or not
	int k[]= new int[n];   // it is also stores brust time
	int i, st=0, tot=0, sti =0;
        int st1[] = new int[n];
	double avgwt=0, avgta=0;
        for (i=0;i<n;i++){
	    	pid[i]= i+1;
	    	k[i]= bt[i];
	    	f[i]= 0;
	}
        while(true){
            int min=99,c=n;
	    if (tot==n)
	    	break;
	    for ( i=0;i<n;i++){
	   	if ((at[i]<=st) && (f[i]==0) && (bt[i]<min)){	
                    min=bt[i];
                    c=i;
	    	}
	    }
	    if (c==n){
	    	st++;
                
            }
	    else{
	    	bt[c]--;
	    	st++;
	    	if(bt[c]==0){
                    st1[sti++]=st;
                    ct[c]= st;
                    f[c]=1;
                    tot++;
	    	}
	    }
	}
	for(i=0;i<n;i++){
	    ta[i] = ct[i] - at[i];
	    wt[i] = ta[i] - k[i];
	    avgwt+= wt[i];
	    avgta+= ta[i];
	}
//        System.out.println("OK a");
//        double result1 = (float)avgwt/n;
//        double result2 = (float)avgta/n;
        return st1;
    }
    
    public static String SSTFAlgorithm(String a, String b){
        int be = Integer.parseInt(b);
        String[] c = a.split(", ");
        int[] d = new int[c.length];
        int[] e = new int[c.length];
        int u, sum;
        int[] f = new int[c.length];
        int[] g = new int[c.length];
        for(int i=0;i<c.length;i++){
            try {
                f[i] = Integer.parseInt(c[i]);
            } catch (NumberFormatException nfe){
                System.out.println("error:"+nfe.getMessage());
            }
        }
        for(int i=0;i<f.length;i++){
            d[i]=(int)Math.abs(be-f[i]);
        }
        System.arraycopy(d, 0, e, 0, c.length);
        for(int i=0;i<e.length-1;i++){
            for(int j=i+1;j<e.length;j++)
                if(e[i]>e[j]){
                    u = e[i];
                    e[i] = e[j];
                    e[j] = u;
                }
        }
        for(int i=0;i<e.length;i++)
            for(int k=0;k<d.length;k++)
                if(e[i]==d[k])
                    g[i]=f[k];
        sum = g[0]-53;
        for(int i=0;i<g.length-1;i++)
            sum+=Math.abs(g[i+1]-g[i]);
        return ""+sum;
    }
    public static String SSTFAlgorithm1(String a, String b){
        int be = Integer.parseInt(b);
        String[] c = a.split(", ");
        int[] d = new int[c.length];
        int[] e = new int[c.length];
        int u, sum;
        int[] f = new int[c.length];
        int[] g = new int[c.length];
        for(int i=0;i<c.length;i++){
            try {
                f[i] = Integer.parseInt(c[i]);
            } catch (NumberFormatException nfe){
                System.out.println("error:"+nfe.getMessage());
            }
        }
        for(int i=0;i<f.length;i++){
            d[i]=(int)Math.abs(be-f[i]);
        }
        System.arraycopy(d, 0, e, 0, c.length);
        for(int i=0;i<e.length-1;i++){
            for(int j=i+1;j<e.length;j++)
                if(e[i]>e[j]){
                    u = e[i];
                    e[i] = e[j];
                    e[j] = u;
                }
        }
        for(int i=0;i<e.length;i++)
            for(int k=0;k<d.length;k++)
                if(e[i]==d[k])
                    g[i]=f[k];
        sum = g[0]-53;
        for(int i=0;i<g.length-1;i++)
            sum+=Math.abs(g[i+1]-g[i]);
        String result = b+" ";
        for(int i=0;i<g.length;i++){
            result+=", "+g[i];
        }
        return result;
    }
    
    public static void main(String[] args) {
        //set Title:
//        JFrame frame = new JFrame("Ứng dụng tìm hiểu thuật toán Lập lịch CPU và Lập lịch đĩa");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //set Size:
//        frame.setSize(1000, 800);
//        frame.setVisible(true);
        
        SwingUtilities.invokeLater(() -> {
            new AlgorithmClarificationClone().displayGUI();
        });
    }
}
class node { 
      
    // represent difference between  
    // head position and track number 
    int distance = 0;  
      
    // true if track has been accessed 
    boolean accessed = false;  
} 
