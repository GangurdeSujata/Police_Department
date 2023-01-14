/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package policedepartment;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import static java.lang.Thread.sleep;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

class jPanelGradient extends JPanel {

    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        int width = getWidth();
        int height = getHeight();
        Color color1 = new Color(255, 153, 80);
        Color color2 = new Color(86, 180, 253);
        GradientPaint gp = new GradientPaint(0, 0, color1, 180, height, color2);
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, width, height);

    }
}

class jPanelGradient1 extends JPanel {

    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        int width = getWidth();
        int height = getHeight();
        Color color1 = new Color(0, 204, 204);
        Color color2 = new Color(255, 153, 255);
        GradientPaint gp = new GradientPaint(0, 0, color1, 180, height, color2);
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, width, height);

    }
}

class EvenOddRenderer111 implements TableCellRenderer {

    public static final DefaultTableCellRenderer DEFAULT_RENDERER = new DefaultTableCellRenderer();

    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {

        Component renderer = DEFAULT_RENDERER.getTableCellRendererComponent(
                table, value, isSelected, hasFocus, row, column);

        ((JLabel) renderer).setOpaque(true);

        Color foreground, background;
        if (isSelected) {

            foreground = Color.BLUE;

            background = new Color(255, 204, 255);

        } else {

            if (row % 2 == 0) {

                foreground = Color.black;

                background = new Color(255, 255, 204);

            } else {

                foreground = Color.black;

                background = new Color(204, 255, 204);

            }

        }

        renderer.setForeground(foreground);

        renderer.setBackground(background);

        ((JLabel) renderer).setHorizontalAlignment(SwingConstants.CENTER);

        return renderer;

    }

}

class imgsliderr implements ActionListener {

    Timer tm;
    int x = 0;
    String[] list = {"i1.jpg", "i2.jpg", "i3.jpg", "i4.jpg", "i6.jpg", "i7.jpg", "i8.jpg"};

    imgsliderr() {
        tm = new javax.swing.Timer(10000, this);
        tm.start();
    }

    public void actionPerformed(ActionEvent e) {
        SetImageSize(x);
        x += 1;
        if (x >= list.length) {
            x = 0;
        }
    }

    void SetImageSize(int i) {
        ImageIcon myimage;
        myimage = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource(list[i])));
        Image img1;
        img1 = myimage.getImage();
        Image img2 = img1.getScaledInstance(DashBoard1.pic.getWidth(), DashBoard1.pic.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon ii = new ImageIcon(img2);
        DashBoard1.pic.setIcon(ii);
    }
}

class MarqueeLabel extends JLabel {

    public static int LEFT_TO_RIGHT = 1;
    public static int RIGHT_TO_LEFT = 2;

    String Text;
    int Option;
    int Speed;

    public MarqueeLabel(String Text, int Option, int Speed) {

        this.Option = Option;
        this.Speed = Speed;
        this.setText(Text);

    }

    @Override
    protected void paintComponent(Graphics g) {
        if (Option == LEFT_TO_RIGHT) {
            g.translate((int) ((System.currentTimeMillis() / Speed) % (getWidth() * 2) - getWidth()), 0);
        } else if (Option == RIGHT_TO_LEFT) {
            g.translate((int) (getWidth() - (System.currentTimeMillis() / Speed) % (getWidth() * 2)), 0);

        }
        super.paintComponent(g);
        repaint(5);
    }
}

public class DashBoard1 extends javax.swing.JFrame {

    int table_height = 0, cell_spacing = 0;

    /**
     * Creates new form DashBoard1
     */
    public DashBoard1() {
        initComponents();
       // jLabel81 = new MarqueeLabel("Police force",MarqueeLabel.RIGHT_TO_LEFT,20) ;
        temp();
        jLabel1.setIcon(img(jLabel1, "/images/sky.jpg"));
        n();
     
        Calender();
        rno();
        iimg();
        ButtonGroup bg = new ButtonGroup();
        bg.add(male);
        bg.add(female);
        ButtonGroup bg1 = new ButtonGroup();
   
        ButtonGroup bg2 = new ButtonGroup();
    ;

        ButtonGroup bg3 = new ButtonGroup();
        bg3.add(male1);
        bg3.add(female1);

        ButtonGroup bg4 = new ButtonGroup();
        bg4.add(male2);
        bg4.add(female2);
    }
    
 public void Calender() {

        Thread clock = new Thread() {
            public void run() {
                for (;;) {
                    Calendar cal = new GregorianCalendar();
                    int month = cal.get(Calendar.MONTH);
                    int year = cal.get(Calendar.YEAR);
                    int day = cal.get(Calendar.DAY_OF_MONTH);
                    date.setText(+day + "-" + (month + 1) + "-" + year);
                    time.setText(java.time.LocalTime.now().toString());
                    try {
                        sleep(1000);

                    } catch (Exception e) {
                    }
                }
            }
        };
        clock.start();
    }
    void iimg() {
        pic.setIcon(img(pic, "/images/CREATE.jpg"));
        jLabel1.setIcon(img(jLabel1, "/images/MH.png"));
        jLabel6.setIcon(img(jLabel6, "/images/ab.png"));
        //img.setIcon(img(img, "/images/ab.png"));
   
        img4.setIcon(img(img4, "/images/ab.png"));

    }

    public ImageIcon img(JLabel l, String image) {
        ImageIcon myimage;
        myimage = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource(image)));
        Image img1;
        img1 = myimage.getImage();
        Image img2 = img1.getScaledInstance(l.getWidth(), l.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon i = new ImageIcon(img2);
        return i;
    }

    void temp() {
        String s1;

        s1 = this.srno("select FIR_ID from fir order by FIR_ID DESC Limit 1;", "FIR_ID");
        f_id.setText(s1);
     
        s1 = this.srno("select ID_NUMBER from commoncrime order by ID_NUMBER DESC Limit 1;", "ID_NUMBER");
        common_crime_id.setText(s1);
    }

    public String srno(String query, String field) {
        PreparedStatement st;
        ResultSet rs;
        String f = null;
        // String query = ;
        int maxid = 0;
        //  JOptionPane.showMessageDialog(null, "inside method");
        try {

            st = loginconnection.connection().prepareStatement(query);
            rs = st.executeQuery();

            if (rs.next()) {
                maxid = rs.getInt(field);

            }
            int t = maxid + 1;
            f = Integer.toString(t);

            //      JOptionPane.showMessageDialog(null, "inside method");
        } catch (SQLException ex) {
            //  Logger.getLogger(NewAccount_Page.class.getName()).log(Level.SEVERE, null, ex);
        }
        return f;
    }

    /*    void three()
          {  PreparedStatement ps,ps1,ps11,s1,s2;
          String f1,vid1,cid1;
          f1=firid1.getText();
          vid1=vid.getText();
          cid1=cid.getText();
                 int f11=Integer.parseInt(f1);
        int v11=Integer.parseInt(vid1);
        int c11=Integer.parseInt(cid1);
                  String q1="insert into fv values(?,?);";
        String q2="insert into vc values(?,?);";
try{
               s1 = loginconnection.connection().prepareStatement(q1);
            s1.setInt(1,f11);
            s1.setInt(2,v11);
            s1.execute();

            s2 = loginconnection.connection().prepareStatement(q2);
            s2.setInt(1,v11);
            s2.setInt(2,c11);
            s2.execute();
          }catch(Exception e)
          {
              
          }
          } */

    void n() {
        String pp = null;
        try {
            Statement ps = loginconnection.connection().createStatement();
            ResultSet rs = ps.executeQuery("select name from policestaff;");
            while (rs.next()) {
                mostwanted_officer.addItem(rs.getString(1));
                common_crime_dutyofficer.addItem(rs.getString(1));
                investigation_officer.addItem(rs.getString(1));
            }
        } catch (Exception e) {
        }
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        pic = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel11 = new javax.swing.JPanel();
        FirDetails = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        complainer_name = new javax.swing.JTextField();
        f_id = new javax.swing.JTextField();
        crime_time = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        investigation_officer = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        criminal_details = new javax.swing.JTextArea();
        crime_date = new com.toedter.calendar.JDateChooser();
        crime_address = new javax.swing.JTextField();
        criminal_name = new javax.swing.JTextField();
        jLabel84 = new javax.swing.JLabel();
        jLabel85 = new javax.swing.JLabel();
        victim_name = new javax.swing.JTextField();
        jScrollPane12 = new javax.swing.JScrollPane();
        complianer_details = new javax.swing.JTextArea();
        jScrollPane13 = new javax.swing.JScrollPane();
        compliant = new javax.swing.JTextArea();
        jLabel86 = new javax.swing.JLabel();
        jScrollPane14 = new javax.swing.JScrollPane();
        victim_details = new javax.swing.JTextArea();
        jLabel88 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jPanel27 = new jPanelGradient();
        firl = new javax.swing.JTextField();
        jButton25 = new javax.swing.JButton();
        jButton26 = new javax.swing.JButton();
        jButton27 = new javax.swing.JButton();
        jButton29 = new javax.swing.JButton();
        jButton40 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        common_crime_time = new javax.swing.JTextField();
        common_crime_id = new javax.swing.JTextField();
        loginbutton = new javax.swing.JButton();
        female = new javax.swing.JRadioButton();
        male = new javax.swing.JRadioButton();
        common_crime_dutyofficer = new javax.swing.JComboBox<>();
        common_crime_age = new javax.swing.JComboBox<>();
        jScrollPane7 = new javax.swing.JScrollPane();
        common_crime = new javax.swing.JTextArea();
        jLabel39 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        common_crime_date = new com.toedter.calendar.JDateChooser();
        jLabel89 = new javax.swing.JLabel();
        jLabel90 = new javax.swing.JLabel();
        common_crime_name = new javax.swing.JTextField();
        loginbutton8 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        j13 = new javax.swing.JLabel();
        mostwanted_name = new javax.swing.JTextField();
        mostwanted_id = new javax.swing.JTextField();
        loginbutton1 = new javax.swing.JButton();
        female1 = new javax.swing.JRadioButton();
        male1 = new javax.swing.JRadioButton();
        mostwanted_officer = new javax.swing.JComboBox<>();
        mostwanted_section = new javax.swing.JComboBox<>();
        mostwanted_age = new javax.swing.JComboBox<>();
        jScrollPane9 = new javax.swing.JScrollPane();
        mostwanted_criminal_detail = new javax.swing.JTextArea();
        jLabel51 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        jScrollPane15 = new javax.swing.JScrollPane();
        mostwanted_crime_discription = new javax.swing.JTextArea();
        jLabel91 = new javax.swing.JLabel();
        loginbutton2 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        court_id = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        order = new javax.swing.JTextArea();
        loginbutton3 = new javax.swing.JButton();
        court_date = new com.toedter.calendar.JDateChooser();
        loginbutton7 = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel17 = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        contactno = new javax.swing.JTextField();
        qulification = new javax.swing.JTextField();
        idno1 = new javax.swing.JTextField();
        female2 = new javax.swing.JRadioButton();
        male2 = new javax.swing.JRadioButton();
        jButton8 = new javax.swing.JButton();
        imagepath = new javax.swing.JLabel();
        name = new javax.swing.JTextField();
        loginbutton4 = new javax.swing.JButton();
        img4 = new javax.swing.JLabel();
        currentpost = new javax.swing.JComboBox<>();
        jLabel65 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        staff_image = new javax.swing.JLabel();
        staff_date = new com.toedter.calendar.JDateChooser();
        jPanel22 = new javax.swing.JPanel();
        jPanel23 = new javax.swing.JPanel();
        ccl = new javax.swing.JTextField();
        jButton14 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        k = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        jPanel24 = new javax.swing.JPanel();
        jTabbedPane4 = new javax.swing.JTabbedPane();
        jPanel26 = new jPanelGradient();
        mwl = new javax.swing.JTextField();
        jButton21 = new javax.swing.JButton();
        jButton22 = new javax.swing.JButton();
        jButton23 = new javax.swing.JButton();
        jPanel25 = new jPanelGradient();
        ccl1 = new javax.swing.JTextField();
        jButton17 = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        jButton19 = new javax.swing.JButton();
        jPanel28 = new jPanelGradient();
        col = new javax.swing.JTextField();
        jButton30 = new javax.swing.JButton();
        jButton31 = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        jPanel34 = new jPanelGradient();
        col1 = new javax.swing.JTextField();
        jButton32 = new javax.swing.JButton();
        jButton36 = new javax.swing.JButton();
        jScrollPane10 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel72 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jPanel29 = new javax.swing.JPanel();
        jLabel73 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel74 = new javax.swing.JLabel();
        event_id = new javax.swing.JTextField();
        jScrollPane11 = new javax.swing.JScrollPane();
        event = new javax.swing.JTextArea();
        loginbutton5 = new javax.swing.JButton();
        event_date = new com.toedter.calendar.JDateChooser();
        jPanel10 = new javax.swing.JPanel();
        jTabbedPane5 = new javax.swing.JTabbedPane();
        jPanel32 = new javax.swing.JPanel();
        jPanel33 = new jPanelGradient();
        ccl2 = new javax.swing.JTextField();
        jButton33 = new javax.swing.JButton();
        jButton34 = new javax.swing.JButton();
        jButton35 = new javax.swing.JButton();
        k1 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jPanel30 = new javax.swing.JPanel();
        jPanel31 = new javax.swing.JPanel();
        G1 = new javax.swing.JTextField();
        G4 = new javax.swing.JTextField();
        G3 = new javax.swing.JTextField();
        G5 = new javax.swing.JTextField();
        jLabel75 = new javax.swing.JLabel();
        jLabel76 = new javax.swing.JLabel();
        jLabel77 = new javax.swing.JLabel();
        jLabel78 = new javax.swing.JLabel();
        G2 = new javax.swing.JComboBox<>();
        jLabel79 = new javax.swing.JLabel();
        jLabel80 = new javax.swing.JLabel();
        loginbutton6 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel81 = new MarqueeLabel("सद्ररक्षणाय खलनिग्रहणाय                                                                            सद्ररक्षणाय खलनिग्रहणाय                                                                                         सद्ररक्षणाय खलनिग्रहणाय                                                                              सद्ररक्षणाय खलनिग्रहणाय                                                                                     सद्ररक्षणाय खलनिग्रहणाय                                                          सद्ररक्षणाय खलनिग्रहणाय                                                                          सद्ररक्षणाय खलनिग्रहणाय                                                                  सद्ररक्षणाय खलनिग्रहणाय",MarqueeLabel.LEFT_TO_RIGHT,30) ;
        jLabel82 = new javax.swing.JLabel();
        jLabel83 = new javax.swing.JLabel();
        time = new javax.swing.JLabel();
        date = new javax.swing.JLabel();
        jLabel87 = new javax.swing.JLabel();
        jButton38 = new javax.swing.JButton();
        jButton39 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 204, 153));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setLayout(null);

        jTabbedPane1.setBackground(new java.awt.Color(255, 204, 204));
        jTabbedPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jTabbedPane1.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 14)); // NOI18N

        jPanel2.setBackground(new java.awt.Color(153, 153, 255));
        jPanel2.setLayout(null);

        pic.setText("jLabel1");
        jPanel2.add(pic);
        pic.setBounds(0, 0, 1260, 500);

        jTabbedPane1.addTab("Home", jPanel2);

        jPanel3.setBackground(new java.awt.Color(255, 153, 153));
        jPanel3.setLayout(null);

        jTabbedPane2.setBackground(new java.awt.Color(0, 102, 255));
        jTabbedPane2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        FirDetails.setBackground(new java.awt.Color(255, 204, 204));
        FirDetails.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 102, 255), 5));
        FirDetails.setForeground(new java.awt.Color(0, 102, 255));
        FirDetails.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        FirDetails.setLayout(null);

        jLabel12.setFont(new java.awt.Font("Old English Text MT", 3, 36)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 102, 255));
        jLabel12.setText("     FIR");
        jLabel12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 255), 5));
        FirDetails.add(jLabel12);
        jLabel12.setBounds(410, 10, 231, 46);

        complainer_name.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        complainer_name.setHorizontalAlignment(JTextField.CENTER);
        complainer_name.setToolTipText("Enter Complianer Name");
        complainer_name.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 255)));
        complainer_name.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                complainer_nameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                complainer_nameFocusLost(evt);
            }
        });
        FirDetails.add(complainer_name);
        complainer_name.setBounds(210, 260, 270, 30);

        f_id.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        f_id.setHorizontalAlignment(JTextField.CENTER);
        f_id.setToolTipText("Enter FIr ID");
        f_id.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 255)));
        f_id.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                f_idFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                f_idFocusLost(evt);
            }
        });
        FirDetails.add(f_id);
        f_id.setBounds(210, 80, 270, 30);

        crime_time.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        crime_time.setHorizontalAlignment(JTextField.CENTER);
        crime_time.setToolTipText("Enter the Name");
        crime_time.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 255)));
        crime_time.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                crime_timeFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                crime_timeFocusLost(evt);
            }
        });
        FirDetails.add(crime_time);
        crime_time.setBounds(210, 120, 270, 30);

        jButton4.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jButton4.setText("Save");
        jButton4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 255)));
        jButton4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jButton4MouseReleased(evt);
            }
        });
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        FirDetails.add(jButton4);
        jButton4.setBounds(510, 370, 120, 30);

        investigation_officer.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        investigation_officer.setMaximumRowCount(20);
        investigation_officer.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select the Investigation Officer Name......", " " }));
        investigation_officer.setToolTipText("Enter the name");
        investigation_officer.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 204)));
        investigation_officer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                investigation_officerActionPerformed(evt);
            }
        });
        FirDetails.add(investigation_officer);
        investigation_officer.setBounds(770, 330, 270, 30);

        jLabel11.setFont(new java.awt.Font("Adobe Caslon Pro Bold", 0, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 255));
        jLabel11.setText("Complianer Name:");
        FirDetails.add(jLabel11);
        jLabel11.setBounds(40, 260, 150, 26);

        jLabel13.setFont(new java.awt.Font("Adobe Caslon Pro Bold", 0, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 0, 255));
        jLabel13.setText("Enter The Compliant:");
        FirDetails.add(jLabel13);
        jLabel13.setBounds(580, 280, 180, 26);

        jLabel14.setFont(new java.awt.Font("Adobe Caslon Pro Bold", 0, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 0, 255));
        jLabel14.setText("Crime Address:");
        FirDetails.add(jLabel14);
        jLabel14.setBounds(70, 210, 140, 26);

        jLabel16.setFont(new java.awt.Font("Adobe Caslon Pro Bold", 0, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 0, 255));
        jLabel16.setText("Crime Time:");
        FirDetails.add(jLabel16);
        jLabel16.setBounds(90, 120, 110, 26);

        jLabel17.setFont(new java.awt.Font("Adobe Caslon Pro Bold", 0, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 0, 255));
        jLabel17.setText("Criminal Details:");
        FirDetails.add(jLabel17);
        jLabel17.setBounds(620, 220, 160, 26);

        jLabel18.setFont(new java.awt.Font("Adobe Caslon Pro Bold", 0, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(0, 0, 255));
        jLabel18.setText("Investigation Officer:");
        FirDetails.add(jLabel18);
        jLabel18.setBounds(590, 330, 180, 26);

        jLabel20.setFont(new java.awt.Font("Adobe Caslon Pro Bold", 0, 18)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(0, 0, 255));
        jLabel20.setText("Crime Date:");
        FirDetails.add(jLabel20);
        jLabel20.setBounds(90, 170, 110, 26);

        jLabel23.setFont(new java.awt.Font("Adobe Caslon Pro Bold", 0, 18)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(0, 0, 255));
        jLabel23.setText("FIR ID:");
        FirDetails.add(jLabel23);
        jLabel23.setBounds(120, 90, 70, 20);

        criminal_details.setColumns(20);
        criminal_details.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        criminal_details.setRows(5);
        criminal_details.setToolTipText("Enter the Evidence");
        criminal_details.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 255)));
        jScrollPane4.setViewportView(criminal_details);

        FirDetails.add(jScrollPane4);
        jScrollPane4.setBounds(770, 210, 270, 50);

        crime_date.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        crime_date.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        FirDetails.add(crime_date);
        crime_date.setBounds(210, 170, 270, 28);

        crime_address.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        crime_address.setHorizontalAlignment(JTextField.CENTER);
        crime_address.setToolTipText("Enter the Name");
        crime_address.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 255)));
        crime_address.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                crime_addressFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                crime_addressFocusLost(evt);
            }
        });
        FirDetails.add(crime_address);
        crime_address.setBounds(210, 210, 270, 30);

        criminal_name.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        criminal_name.setHorizontalAlignment(JTextField.CENTER);
        criminal_name.setToolTipText("Enter Complianer Details");
        criminal_name.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 255)));
        criminal_name.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                criminal_nameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                criminal_nameFocusLost(evt);
            }
        });
        criminal_name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                criminal_nameActionPerformed(evt);
            }
        });
        FirDetails.add(criminal_name);
        criminal_name.setBounds(770, 170, 270, 30);

        jLabel84.setFont(new java.awt.Font("Adobe Caslon Pro Bold", 0, 18)); // NOI18N
        jLabel84.setForeground(new java.awt.Color(0, 0, 255));
        jLabel84.setText("Criminal Name:");
        FirDetails.add(jLabel84);
        jLabel84.setBounds(630, 180, 160, 26);

        jLabel85.setFont(new java.awt.Font("Adobe Caslon Pro Bold", 0, 18)); // NOI18N
        jLabel85.setForeground(new java.awt.Color(0, 0, 255));
        jLabel85.setText("Victim Details:");
        FirDetails.add(jLabel85);
        jLabel85.setBounds(640, 120, 160, 26);

        victim_name.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        victim_name.setHorizontalAlignment(JTextField.CENTER);
        victim_name.setToolTipText("Enter Complianer Details");
        victim_name.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 255)));
        victim_name.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                victim_nameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                victim_nameFocusLost(evt);
            }
        });
        victim_name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                victim_nameActionPerformed(evt);
            }
        });
        FirDetails.add(victim_name);
        victim_name.setBounds(770, 60, 270, 30);

        complianer_details.setColumns(20);
        complianer_details.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        complianer_details.setRows(5);
        complianer_details.setToolTipText("Enter the Evidence");
        complianer_details.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 255)));
        jScrollPane12.setViewportView(complianer_details);

        FirDetails.add(jScrollPane12);
        jScrollPane12.setBounds(210, 310, 270, 50);

        compliant.setColumns(20);
        compliant.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        compliant.setRows(5);
        compliant.setToolTipText("Enter the Evidence");
        compliant.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 255)));
        jScrollPane13.setViewportView(compliant);

        FirDetails.add(jScrollPane13);
        jScrollPane13.setBounds(770, 270, 270, 50);

        jLabel86.setFont(new java.awt.Font("Adobe Caslon Pro Bold", 0, 18)); // NOI18N
        jLabel86.setForeground(new java.awt.Color(0, 0, 255));
        jLabel86.setText("Victim Name:");
        FirDetails.add(jLabel86);
        jLabel86.setBounds(650, 60, 160, 26);

        jScrollPane14.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N

        victim_details.setColumns(20);
        victim_details.setRows(5);
        victim_details.setToolTipText("Enter the Evidence");
        victim_details.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 255)));
        jScrollPane14.setViewportView(victim_details);

        FirDetails.add(jScrollPane14);
        jScrollPane14.setBounds(770, 100, 270, 50);

        jLabel88.setFont(new java.awt.Font("Adobe Caslon Pro Bold", 0, 18)); // NOI18N
        jLabel88.setForeground(new java.awt.Color(0, 0, 255));
        jLabel88.setText("Complianer Details:");
        FirDetails.add(jLabel88);
        jLabel88.setBounds(40, 310, 170, 26);

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addComponent(FirDetails, javax.swing.GroupLayout.DEFAULT_SIZE, 1240, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addComponent(FirDetails, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 16, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Register FIR", jPanel11);

        jPanel27.setBackground(new java.awt.Color(153, 153, 153));
        jPanel27.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 255), 2), "Search FIR Record", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 18), new java.awt.Color(51, 0, 255))); // NOI18N
        jPanel27.setLayout(null);

        firl.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        firl.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 255)));
        jPanel27.add(firl);
        firl.setBounds(10, 20, 170, 32);

        jButton25.setBackground(new java.awt.Color(255, 153, 153));
        jButton25.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        jButton25.setText("Search Record by FIR ID");
        jButton25.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 255)));
        jButton25.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton25.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton25MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton25MouseExited(evt);
            }
        });
        jButton25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton25ActionPerformed(evt);
            }
        });
        jPanel27.add(jButton25);
        jButton25.setBounds(10, 70, 240, 25);

        jButton26.setBackground(new java.awt.Color(255, 153, 153));
        jButton26.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        jButton26.setText("Search Record by Criminal name");
        jButton26.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 255)));
        jButton26.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton26.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton26MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton26MouseExited(evt);
            }
        });
        jButton26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton26ActionPerformed(evt);
            }
        });
        jPanel27.add(jButton26);
        jButton26.setBounds(10, 150, 310, 25);

        jButton27.setBackground(new java.awt.Color(255, 153, 153));
        jButton27.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        jButton27.setText("Search Record by Victim Name");
        jButton27.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 255)));
        jButton27.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton27.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton27MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton27MouseExited(evt);
            }
        });
        jButton27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton27ActionPerformed(evt);
            }
        });
        jPanel27.add(jButton27);
        jButton27.setBounds(10, 110, 280, 25);

        jButton29.setBackground(new java.awt.Color(255, 153, 153));
        jButton29.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        jButton29.setText("All Records ");
        jButton29.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 255)));
        jButton29.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton29.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton29MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton29MouseExited(evt);
            }
        });
        jButton29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton29ActionPerformed(evt);
            }
        });
        jPanel27.add(jButton29);
        jButton29.setBounds(10, 190, 270, 30);

        jButton40.setBackground(new java.awt.Color(255, 153, 153));
        jButton40.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        jButton40.setText("Print all FIR");
        jButton40.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 255)));
        jButton40.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton40.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton40MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton40MouseExited(evt);
            }
        });
        jButton40.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton40ActionPerformed(evt);
            }
        });
        jPanel27.add(jButton40);
        jButton40.setBounds(20, 250, 270, 140);

        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable4);

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 915, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 416, Short.MAX_VALUE))
                .addGap(0, 10, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Search FIR Records", jPanel13);

        jPanel3.add(jTabbedPane2);
        jTabbedPane2.setBounds(0, 0, 1260, 460);

        jTabbedPane1.addTab("Register FIR", jPanel3);

        jPanel4.setLayout(null);

        jPanel14.setBackground(new java.awt.Color(255, 204, 204));
        jPanel14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 204)));
        jPanel14.setLayout(null);

        jLabel3.setFont(new java.awt.Font("Algerian", 3, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 102, 255));
        jLabel3.setText(" Common Crime");
        jLabel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 255), 2));
        jPanel14.add(jLabel3);
        jLabel3.setBounds(420, 20, 310, 60);

        common_crime_time.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        common_crime_time.setHorizontalAlignment(JTextField.CENTER);
        common_crime_time.setToolTipText("Enter full name");
        common_crime_time.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 255)));
        common_crime_time.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                common_crime_timeFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                common_crime_timeFocusLost(evt);
            }
        });
        common_crime_time.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                common_crime_timeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                common_crime_timeMouseExited(evt);
            }
        });
        common_crime_time.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                common_crime_timeActionPerformed(evt);
            }
        });
        jPanel14.add(common_crime_time);
        common_crime_time.setBounds(740, 130, 270, 30);

        common_crime_id.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        common_crime_id.setHorizontalAlignment(JTextField.CENTER);
        common_crime_id.setToolTipText("Enter ID Number");
        common_crime_id.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 255)));
        common_crime_id.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                common_crime_idFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                common_crime_idFocusLost(evt);
            }
        });
        common_crime_id.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                common_crime_idMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                common_crime_idMouseExited(evt);
            }
        });
        common_crime_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                common_crime_idActionPerformed(evt);
            }
        });
        common_crime_id.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                common_crime_idKeyTyped(evt);
            }
        });
        jPanel14.add(common_crime_id);
        common_crime_id.setBounds(290, 130, 270, 30);

        loginbutton.setBackground(new java.awt.Color(0, 153, 0));
        loginbutton.setFont(new java.awt.Font("Chaparral Pro", 3, 24)); // NOI18N
        loginbutton.setText("Save");
        loginbutton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        loginbutton.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                loginbuttonFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                loginbuttonFocusLost(evt);
            }
        });
        loginbutton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                loginbuttonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                loginbuttonMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                loginbuttonMouseReleased(evt);
            }
        });
        loginbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginbuttonActionPerformed(evt);
            }
        });
        jPanel14.add(loginbutton);
        loginbutton.setBounds(470, 350, 190, 30);

        female.setText("Female");
        female.setToolTipText("Choose Your Gender");
        female.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                femaleActionPerformed(evt);
            }
        });
        jPanel14.add(female);
        female.setBounds(470, 230, 83, 29);

        male.setText("male");
        male.setToolTipText("Choose Your Gender");
        jPanel14.add(male);
        male.setBounds(290, 230, 67, 29);

        common_crime_dutyofficer.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        common_crime_dutyofficer.setMaximumRowCount(20);
        common_crime_dutyofficer.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Officer Name......", " " }));
        common_crime_dutyofficer.setToolTipText("Enter the name");
        common_crime_dutyofficer.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 204)));
        common_crime_dutyofficer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                common_crime_dutyofficerActionPerformed(evt);
            }
        });
        jPanel14.add(common_crime_dutyofficer);
        common_crime_dutyofficer.setBounds(740, 290, 270, 30);

        common_crime_age.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        common_crime_age.setMaximumRowCount(20);
        common_crime_age.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Age....", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59", "60", "61", "62", "63", "64", "65", "66", "67", "68", "69", "70", "71", "72", "73", "74", "75", "76", "77", "78", "79", "80", "81", "82", "83", "84", "85", "86", "87", "88", "89", "90", "91", "92", "93", "94", "95", "96", "97", "98", "99", "100" }));
        common_crime_age.setToolTipText("Enter the name");
        common_crime_age.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 204)));
        common_crime_age.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                common_crime_ageActionPerformed(evt);
            }
        });
        jPanel14.add(common_crime_age);
        common_crime_age.setBounds(280, 290, 270, 30);

        common_crime.setColumns(20);
        common_crime.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        common_crime.setRows(5);
        common_crime.setToolTipText("Enter the Victim Biography");
        common_crime.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 255)));
        jScrollPane7.setViewportView(common_crime);

        jPanel14.add(jScrollPane7);
        jScrollPane7.setBounds(740, 220, 270, 60);

        jLabel39.setFont(new java.awt.Font("Adobe Caslon Pro Bold", 0, 18)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(0, 0, 255));
        jLabel39.setText("Enter Criminal Name:");
        jPanel14.add(jLabel39);
        jLabel39.setBounds(90, 170, 190, 40);

        jLabel41.setFont(new java.awt.Font("Adobe Caslon Pro Bold", 0, 18)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(0, 0, 255));
        jLabel41.setText("Gender:");
        jPanel14.add(jLabel41);
        jLabel41.setBounds(200, 230, 80, 40);

        jLabel42.setFont(new java.awt.Font("Adobe Caslon Pro Bold", 0, 18)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(0, 0, 255));
        jLabel42.setText("Age:");
        jPanel14.add(jLabel42);
        jLabel42.setBounds(220, 290, 50, 40);

        jLabel49.setFont(new java.awt.Font("Adobe Caslon Pro Bold", 0, 18)); // NOI18N
        jLabel49.setForeground(new java.awt.Color(0, 0, 255));
        jLabel49.setText("Duty Officer name:");
        jPanel14.add(jLabel49);
        jLabel49.setBounds(580, 290, 160, 40);

        jLabel50.setFont(new java.awt.Font("Adobe Caslon Pro Bold", 0, 18)); // NOI18N
        jLabel50.setForeground(new java.awt.Color(0, 0, 255));
        jLabel50.setText("Time:");
        jPanel14.add(jLabel50);
        jLabel50.setBounds(680, 120, 100, 40);

        jLabel52.setFont(new java.awt.Font("Adobe Caslon Pro Bold", 0, 18)); // NOI18N
        jLabel52.setForeground(new java.awt.Color(0, 0, 255));
        jLabel52.setText("ID_NO:");
        jPanel14.add(jLabel52);
        jLabel52.setBounds(190, 130, 80, 40);
        jPanel14.add(common_crime_date);
        common_crime_date.setBounds(740, 180, 270, 30);

        jLabel89.setFont(new java.awt.Font("Adobe Caslon Pro Bold", 0, 18)); // NOI18N
        jLabel89.setForeground(new java.awt.Color(0, 0, 255));
        jLabel89.setText("Date:");
        jPanel14.add(jLabel89);
        jLabel89.setBounds(680, 180, 100, 40);

        jLabel90.setFont(new java.awt.Font("Adobe Caslon Pro Bold", 0, 18)); // NOI18N
        jLabel90.setForeground(new java.awt.Color(0, 0, 255));
        jLabel90.setText("Crime:");
        jPanel14.add(jLabel90);
        jLabel90.setBounds(670, 230, 100, 40);

        common_crime_name.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        common_crime_name.setHorizontalAlignment(JTextField.CENTER);
        common_crime_name.setToolTipText("Enter full name");
        common_crime_name.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 255)));
        common_crime_name.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                common_crime_nameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                common_crime_nameFocusLost(evt);
            }
        });
        common_crime_name.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                common_crime_nameMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                common_crime_nameMouseExited(evt);
            }
        });
        common_crime_name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                common_crime_nameActionPerformed(evt);
            }
        });
        jPanel14.add(common_crime_name);
        common_crime_name.setBounds(290, 180, 270, 30);

        loginbutton8.setBackground(new java.awt.Color(0, 153, 0));
        loginbutton8.setFont(new java.awt.Font("Chaparral Pro", 3, 24)); // NOI18N
        loginbutton8.setText("Print All Common Crime Record");
        loginbutton8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        loginbutton8.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                loginbutton8FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                loginbutton8FocusLost(evt);
            }
        });
        loginbutton8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                loginbutton8MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                loginbutton8MouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                loginbutton8MouseReleased(evt);
            }
        });
        loginbutton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginbutton8ActionPerformed(evt);
            }
        });
        jPanel14.add(loginbutton8);
        loginbutton8.setBounds(350, 390, 490, 30);

        jPanel4.add(jPanel14);
        jPanel14.setBounds(0, 0, 1280, 460);

        jTabbedPane1.addTab("Common Crime", jPanel4);

        jPanel5.setLayout(null);

        jPanel15.setBackground(new java.awt.Color(204, 255, 204));
        jPanel15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 204)));
        jPanel15.setLayout(null);

        jLabel4.setFont(new java.awt.Font("Algerian", 3, 36)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 102, 255));
        jLabel4.setText(" Mostwanted Criminal");
        jLabel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 255), 2));
        jPanel15.add(jLabel4);
        jLabel4.setBounds(360, 20, 460, 60);

        j13.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        j13.setText("Image");
        jPanel15.add(j13);
        j13.setBounds(860, 500, 42, 17);

        mostwanted_name.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        mostwanted_name.setHorizontalAlignment(JTextField.CENTER);
        mostwanted_name.setToolTipText("Enter full name");
        mostwanted_name.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 255)));
        mostwanted_name.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                mostwanted_nameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                mostwanted_nameFocusLost(evt);
            }
        });
        mostwanted_name.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                mostwanted_nameMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                mostwanted_nameMouseExited(evt);
            }
        });
        mostwanted_name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mostwanted_nameActionPerformed(evt);
            }
        });
        jPanel15.add(mostwanted_name);
        mostwanted_name.setBounds(250, 150, 270, 30);

        mostwanted_id.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        mostwanted_id.setHorizontalAlignment(JTextField.CENTER);
        mostwanted_id.setToolTipText("Enter ID Number");
        mostwanted_id.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 255)));
        mostwanted_id.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                mostwanted_idFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                mostwanted_idFocusLost(evt);
            }
        });
        mostwanted_id.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                mostwanted_idMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                mostwanted_idMouseExited(evt);
            }
        });
        mostwanted_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mostwanted_idActionPerformed(evt);
            }
        });
        mostwanted_id.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                mostwanted_idKeyTyped(evt);
            }
        });
        jPanel15.add(mostwanted_id);
        mostwanted_id.setBounds(250, 99, 270, 30);

        loginbutton1.setBackground(new java.awt.Color(0, 153, 0));
        loginbutton1.setFont(new java.awt.Font("Chaparral Pro", 3, 24)); // NOI18N
        loginbutton1.setText("Save");
        loginbutton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        loginbutton1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                loginbutton1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                loginbutton1FocusLost(evt);
            }
        });
        loginbutton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                loginbutton1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                loginbutton1MouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                loginbutton1MouseReleased(evt);
            }
        });
        loginbutton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginbutton1ActionPerformed(evt);
            }
        });
        jPanel15.add(loginbutton1);
        loginbutton1.setBounds(490, 350, 130, 30);

        female1.setText("Female");
        female1.setToolTipText("Choose Your Gender");
        female1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                female1ActionPerformed(evt);
            }
        });
        jPanel15.add(female1);
        female1.setBounds(440, 210, 83, 29);

        male1.setText("male");
        male1.setToolTipText("Choose Your Gender");
        jPanel15.add(male1);
        male1.setBounds(290, 210, 67, 29);

        mostwanted_officer.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        mostwanted_officer.setMaximumRowCount(20);
        mostwanted_officer.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Officer Name......", " " }));
        mostwanted_officer.setToolTipText("Enter the name");
        mostwanted_officer.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 204)));
        mostwanted_officer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mostwanted_officerActionPerformed(evt);
            }
        });
        jPanel15.add(mostwanted_officer);
        mostwanted_officer.setBounds(810, 310, 270, 30);

        mostwanted_section.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        mostwanted_section.setMaximumRowCount(20);
        mostwanted_section.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select the Section......", "204A(C)", "103(B)", "302B(A)", "102C", "301D", "202A" }));
        mostwanted_section.setToolTipText("Enter the name");
        mostwanted_section.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 204)));
        mostwanted_section.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mostwanted_sectionActionPerformed(evt);
            }
        });
        jPanel15.add(mostwanted_section);
        mostwanted_section.setBounds(810, 250, 270, 30);

        mostwanted_age.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        mostwanted_age.setMaximumRowCount(20);
        mostwanted_age.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Age....", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59", "60", "61", "62", "63", "64", "65", "66", "67", "68", "69", "70", "71", "72", "73", "74", "75", "76", "77", "78", "79", "80", "81", "82", "83", "84", "85", "86", "87", "88", "89", "90", "91", "92", "93", "94", "95", "96", "97", "98", "99", "100" }));
        mostwanted_age.setToolTipText("Enter the name");
        mostwanted_age.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 204)));
        mostwanted_age.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mostwanted_ageActionPerformed(evt);
            }
        });
        jPanel15.add(mostwanted_age);
        mostwanted_age.setBounds(250, 270, 270, 30);

        mostwanted_criminal_detail.setColumns(20);
        mostwanted_criminal_detail.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        mostwanted_criminal_detail.setRows(5);
        mostwanted_criminal_detail.setToolTipText("Enter the Victim Biography");
        mostwanted_criminal_detail.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 255)));
        jScrollPane9.setViewportView(mostwanted_criminal_detail);

        jPanel15.add(jScrollPane9);
        jScrollPane9.setBounds(810, 90, 270, 50);

        jLabel51.setFont(new java.awt.Font("Adobe Caslon Pro Bold", 0, 18)); // NOI18N
        jLabel51.setForeground(new java.awt.Color(0, 0, 255));
        jLabel51.setText("Enter Criminal Name:");
        jPanel15.add(jLabel51);
        jLabel51.setBounds(70, 150, 190, 40);

        jLabel53.setFont(new java.awt.Font("Adobe Caslon Pro Bold", 0, 18)); // NOI18N
        jLabel53.setForeground(new java.awt.Color(0, 0, 255));
        jLabel53.setText("Gender:");
        jPanel15.add(jLabel53);
        jLabel53.setBounds(170, 210, 80, 40);

        jLabel54.setFont(new java.awt.Font("Adobe Caslon Pro Bold", 0, 18)); // NOI18N
        jLabel54.setForeground(new java.awt.Color(0, 0, 255));
        jLabel54.setText("Age:");
        jPanel15.add(jLabel54);
        jLabel54.setBounds(200, 260, 50, 40);

        jLabel60.setFont(new java.awt.Font("Adobe Caslon Pro Bold", 0, 18)); // NOI18N
        jLabel60.setForeground(new java.awt.Color(0, 0, 255));
        jLabel60.setText("Criminal Details:");
        jPanel15.add(jLabel60);
        jLabel60.setBounds(670, 110, 140, 40);

        jLabel61.setFont(new java.awt.Font("Adobe Caslon Pro Bold", 0, 18)); // NOI18N
        jLabel61.setForeground(new java.awt.Color(0, 0, 255));
        jLabel61.setText("Appointed Officer name:");
        jPanel15.add(jLabel61);
        jLabel61.setBounds(610, 310, 220, 40);

        jLabel62.setFont(new java.awt.Font("Adobe Caslon Pro Bold", 0, 18)); // NOI18N
        jLabel62.setForeground(new java.awt.Color(0, 0, 255));
        jLabel62.setText("Crime:");
        jPanel15.add(jLabel62);
        jLabel62.setBounds(740, 180, 60, 40);

        jLabel63.setFont(new java.awt.Font("Adobe Caslon Pro Bold", 0, 18)); // NOI18N
        jLabel63.setForeground(new java.awt.Color(0, 0, 255));
        jLabel63.setText("ID_NO:");
        jPanel15.add(jLabel63);
        jLabel63.setBounds(170, 100, 80, 40);

        mostwanted_crime_discription.setColumns(20);
        mostwanted_crime_discription.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        mostwanted_crime_discription.setRows(5);
        mostwanted_crime_discription.setToolTipText("Enter the Victim Biography");
        mostwanted_crime_discription.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 255)));
        jScrollPane15.setViewportView(mostwanted_crime_discription);

        jPanel15.add(jScrollPane15);
        jScrollPane15.setBounds(810, 170, 270, 50);

        jLabel91.setFont(new java.awt.Font("Adobe Caslon Pro Bold", 0, 18)); // NOI18N
        jLabel91.setForeground(new java.awt.Color(0, 0, 255));
        jLabel91.setText("Section:");
        jPanel15.add(jLabel91);
        jLabel91.setBounds(730, 250, 70, 40);

        loginbutton2.setBackground(new java.awt.Color(0, 153, 0));
        loginbutton2.setFont(new java.awt.Font("Chaparral Pro", 3, 24)); // NOI18N
        loginbutton2.setText("Print All Mostwanted criminal Record");
        loginbutton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        loginbutton2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                loginbutton2FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                loginbutton2FocusLost(evt);
            }
        });
        loginbutton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                loginbutton2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                loginbutton2MouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                loginbutton2MouseReleased(evt);
            }
        });
        loginbutton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginbutton2ActionPerformed(evt);
            }
        });
        jPanel15.add(loginbutton2);
        loginbutton2.setBounds(340, 390, 460, 30);

        jPanel5.add(jPanel15);
        jPanel15.setBounds(0, 0, 1270, 450);

        jTabbedPane1.addTab("Mostwanted Criminal", jPanel5);

        jPanel6.setLayout(null);

        jPanel16.setBackground(new java.awt.Color(255, 204, 204));
        jPanel16.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 255), 2));
        jPanel16.setLayout(null);

        jLabel15.setFont(new java.awt.Font("Old English Text MT", 3, 48)); // NOI18N
        jLabel15.setText(" Court Order");
        jLabel15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jPanel16.add(jLabel15);
        jLabel15.setBounds(350, 20, 349, 88);

        jLabel2.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel2.setText("Order:");
        jPanel16.add(jLabel2);
        jLabel2.setBounds(360, 280, 82, 37);

        jLabel5.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel5.setText("ID NO:");
        jPanel16.add(jLabel5);
        jLabel5.setBounds(350, 160, 67, 37);

        jLabel8.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel8.setText("Date:");
        jPanel16.add(jLabel8);
        jLabel8.setBounds(360, 210, 82, 37);

        court_id.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        court_id.setHorizontalAlignment(JTextField.CENTER);
        court_id.setToolTipText("Enter the CourtOrder Number");
        court_id.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 255)));
        jPanel16.add(court_id);
        court_id.setBounds(510, 140, 330, 40);

        order.setColumns(20);
        order.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        order.setRows(5);
        order.setToolTipText("Write the Court Order");
        order.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(0, 255, 51), new java.awt.Color(102, 102, 255), new java.awt.Color(0, 0, 255), new java.awt.Color(255, 0, 153)));
        jScrollPane1.setViewportView(order);

        jPanel16.add(jScrollPane1);
        jScrollPane1.setBounds(510, 270, 330, 110);

        loginbutton3.setBackground(new java.awt.Color(0, 153, 0));
        loginbutton3.setFont(new java.awt.Font("Chaparral Pro", 3, 24)); // NOI18N
        loginbutton3.setText("Save");
        loginbutton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        loginbutton3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                loginbutton3FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                loginbutton3FocusLost(evt);
            }
        });
        loginbutton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                loginbutton3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                loginbutton3MouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                loginbutton3MouseReleased(evt);
            }
        });
        loginbutton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginbutton3ActionPerformed(evt);
            }
        });
        jPanel16.add(loginbutton3);
        loginbutton3.setBounds(270, 390, 239, 40);
        jPanel16.add(court_date);
        court_date.setBounds(510, 210, 330, 40);

        loginbutton7.setBackground(new java.awt.Color(0, 153, 0));
        loginbutton7.setFont(new java.awt.Font("Chaparral Pro", 3, 24)); // NOI18N
        loginbutton7.setText("Print All courtOrders");
        loginbutton7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        loginbutton7.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                loginbutton7FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                loginbutton7FocusLost(evt);
            }
        });
        loginbutton7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                loginbutton7MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                loginbutton7MouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                loginbutton7MouseReleased(evt);
            }
        });
        loginbutton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginbutton7ActionPerformed(evt);
            }
        });
        jPanel16.add(loginbutton7);
        loginbutton7.setBounds(950, 320, 260, 90);

        jPanel6.add(jPanel16);
        jPanel16.setBounds(0, 0, 1260, 450);

        jTabbedPane1.addTab("Court Order", jPanel6);

        jPanel7.setLayout(null);

        jTabbedPane3.setBackground(new java.awt.Color(153, 255, 153));
        jTabbedPane3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 255), 3));

        jPanel18.setBackground(new java.awt.Color(255, 204, 204));
        jPanel18.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 255), 2));
        jPanel18.setName("pa"); // NOI18N
        jPanel18.setLayout(null);

        jLabel22.setBackground(new java.awt.Color(255, 255, 255));
        jLabel22.setFont(new java.awt.Font("Garamond", 3, 36)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(0, 102, 255));
        jLabel22.setText("    Register Staff Information");
        jLabel22.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 255), 3));
        jPanel18.add(jLabel22);
        jLabel22.setBounds(330, 20, 485, 58);

        contactno.setFont(new java.awt.Font("Verdana", 3, 14)); // NOI18N
        contactno.setHorizontalAlignment(JTextField.CENTER);
        contactno.setToolTipText("Enter Contact Number");
        contactno.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 255)));
        contactno.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                contactnoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                contactnoFocusLost(evt);
            }
        });
        contactno.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                contactnoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                contactnoMouseExited(evt);
            }
        });
        contactno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                contactnoActionPerformed(evt);
            }
        });
        contactno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                contactnoKeyTyped(evt);
            }
        });
        jPanel18.add(contactno);
        contactno.setBounds(320, 320, 200, 28);

        qulification.setFont(new java.awt.Font("Verdana", 3, 14)); // NOI18N
        qulification.setHorizontalAlignment(JTextField.CENTER);
        qulification.setToolTipText("Enter Qulification");
        qulification.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 255)));
        qulification.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                qulificationFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                qulificationFocusLost(evt);
            }
        });
        qulification.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                qulificationMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                qulificationMouseExited(evt);
            }
        });
        qulification.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                qulificationActionPerformed(evt);
            }
        });
        jPanel18.add(qulification);
        qulification.setBounds(720, 110, 230, 30);

        idno1.setFont(new java.awt.Font("Verdana", 3, 14)); // NOI18N
        idno1.setHorizontalAlignment(JTextField.CENTER);
        idno1.setToolTipText("Enter ID no");
        idno1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 255)));
        idno1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        idno1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                idno1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                idno1FocusLost(evt);
            }
        });
        idno1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                idno1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                idno1MouseExited(evt);
            }
        });
        idno1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idno1ActionPerformed(evt);
            }
        });
        idno1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                idno1KeyTyped(evt);
            }
        });
        jPanel18.add(idno1);
        idno1.setBounds(320, 120, 200, 26);

        female2.setText("Female");
        female2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                female2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                female2MouseExited(evt);
            }
        });
        female2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                female2ActionPerformed(evt);
            }
        });
        jPanel18.add(female2);
        female2.setBounds(430, 220, 90, 29);

        male2.setText("Male");
        male2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                male2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                male2MouseExited(evt);
            }
        });
        jPanel18.add(male2);
        male2.setBounds(320, 220, 110, 29);

        jButton8.setBackground(new java.awt.Color(0, 255, 0));
        jButton8.setText("Upload Image");
        jButton8.setToolTipText("Upload Your Image");
        jButton8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jPanel18.add(jButton8);
        jButton8.setBounds(590, 250, 143, 29);
        jPanel18.add(imagepath);
        imagepath.setBounds(900, 350, 160, 40);

        name.setFont(new java.awt.Font("Verdana", 3, 14)); // NOI18N
        name.setHorizontalAlignment(JTextField.CENTER);
        name.setToolTipText("Enter Name");
        name.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 255)));
        name.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                nameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                nameFocusLost(evt);
            }
        });
        name.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                nameMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                nameMouseExited(evt);
            }
        });
        name.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                nameKeyTyped(evt);
            }
        });
        jPanel18.add(name);
        name.setBounds(320, 170, 200, 28);

        loginbutton4.setBackground(new java.awt.Color(0, 153, 0));
        loginbutton4.setFont(new java.awt.Font("Chaparral Pro", 3, 24)); // NOI18N
        loginbutton4.setText("Submit");
        loginbutton4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        loginbutton4.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                loginbutton4FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                loginbutton4FocusLost(evt);
            }
        });
        loginbutton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                loginbutton4MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                loginbutton4MouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                loginbutton4MouseReleased(evt);
            }
        });
        loginbutton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginbutton4ActionPerformed(evt);
            }
        });
        jPanel18.add(loginbutton4);
        loginbutton4.setBounds(530, 370, 270, 30);

        img4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(0, 255, 51), new java.awt.Color(255, 51, 102), new java.awt.Color(255, 0, 0), new java.awt.Color(0, 0, 255)));
        jPanel18.add(img4);
        img4.setBounds(790, 220, 100, 110);

        currentpost.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        currentpost.setMaximumRowCount(20);
        currentpost.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select the Post......", "Director General of Police", "Additional Director General of Police", "Inspector General of Police", "Deputy Inspector General of Police", "Superintendent of Police", "Inspector of Police", "Sub Inspector of Police", "Assistant Sub Inspector of Police", "Head Constable", "Constable" }));
        currentpost.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 204)));
        jPanel18.add(currentpost);
        currentpost.setBounds(720, 170, 230, 30);

        jLabel65.setFont(new java.awt.Font("Adobe Caslon Pro Bold", 0, 18)); // NOI18N
        jLabel65.setForeground(new java.awt.Color(0, 0, 255));
        jLabel65.setText("Name:");
        jPanel18.add(jLabel65);
        jLabel65.setBounds(220, 160, 80, 40);

        jLabel66.setFont(new java.awt.Font("Adobe Caslon Pro Bold", 0, 18)); // NOI18N
        jLabel66.setForeground(new java.awt.Color(0, 0, 255));
        jLabel66.setText("Gender:");
        jPanel18.add(jLabel66);
        jLabel66.setBounds(220, 210, 80, 40);

        jLabel67.setFont(new java.awt.Font("Adobe Caslon Pro Bold", 0, 18)); // NOI18N
        jLabel67.setForeground(new java.awt.Color(0, 0, 255));
        jLabel67.setText("DOB:");
        jPanel18.add(jLabel67);
        jLabel67.setBounds(220, 260, 80, 40);

        jLabel68.setFont(new java.awt.Font("Adobe Caslon Pro Bold", 0, 18)); // NOI18N
        jLabel68.setForeground(new java.awt.Color(0, 0, 255));
        jLabel68.setText("Contact No:");
        jPanel18.add(jLabel68);
        jLabel68.setBounds(180, 310, 110, 40);

        jLabel69.setFont(new java.awt.Font("Adobe Caslon Pro Bold", 0, 18)); // NOI18N
        jLabel69.setForeground(new java.awt.Color(0, 0, 255));
        jLabel69.setText("Post:");
        jPanel18.add(jLabel69);
        jLabel69.setBounds(640, 160, 80, 40);

        jLabel70.setFont(new java.awt.Font("Adobe Caslon Pro Bold", 0, 18)); // NOI18N
        jLabel70.setForeground(new java.awt.Color(0, 0, 255));
        jLabel70.setText("Enter Qulification:");
        jPanel18.add(jLabel70);
        jLabel70.setBounds(550, 110, 150, 40);

        jLabel71.setFont(new java.awt.Font("Adobe Caslon Pro Bold", 0, 18)); // NOI18N
        jLabel71.setForeground(new java.awt.Color(0, 0, 255));
        jLabel71.setText("ID No:");
        jPanel18.add(jLabel71);
        jLabel71.setBounds(220, 110, 80, 40);
        jPanel18.add(staff_image);
        staff_image.setBounds(840, 350, 150, 0);
        jPanel18.add(staff_date);
        staff_date.setBounds(320, 270, 200, 30);

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, 411, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 69, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("Staff Information", jPanel17);

        jPanel22.setBackground(new java.awt.Color(255, 204, 204));
        jPanel22.setLayout(null);

        jPanel23.setBackground(new java.awt.Color(255, 204, 204));
        jPanel23.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 255), 2), "Staff  Record", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 24), new java.awt.Color(51, 51, 255))); // NOI18N
        jPanel23.setLayout(null);

        ccl.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        ccl.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 255)));
        ccl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cclActionPerformed(evt);
            }
        });
        jPanel23.add(ccl);
        ccl.setBounds(370, 40, 317, 31);

        jButton14.setBackground(new java.awt.Color(255, 153, 153));
        jButton14.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        jButton14.setText("Search Record by ID");
        jButton14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 255)));
        jButton14.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton14MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton14MouseExited(evt);
            }
        });
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });
        jPanel23.add(jButton14);
        jButton14.setBounds(370, 90, 317, 25);

        jButton15.setBackground(new java.awt.Color(255, 153, 153));
        jButton15.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        jButton15.setText("Search Record by Staff Name");
        jButton15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 255)));
        jButton15.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton15MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton15MouseExited(evt);
            }
        });
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });
        jPanel23.add(jButton15);
        jButton15.setBounds(370, 130, 317, 25);

        jButton16.setBackground(new java.awt.Color(255, 153, 153));
        jButton16.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        jButton16.setText("ALL RECORDS");
        jButton16.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 255)));
        jButton16.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton16MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton16MouseExited(evt);
            }
        });
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });
        jPanel23.add(jButton16);
        jButton16.setBounds(370, 170, 317, 25);

        jPanel22.add(jPanel23);
        jPanel23.setBounds(80, 0, 1080, 210);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        k.setViewportView(jTable1);

        jPanel22.add(k);
        k.setBounds(80, 250, 1080, 190);

        jTabbedPane3.addTab("Search Staff Information ", jPanel22);

        jPanel7.add(jTabbedPane3);
        jTabbedPane3.setBounds(0, 0, 1260, 520);

        jTabbedPane1.addTab("Police Staff", jPanel7);

        jPanel8.setLayout(null);

        jPanel24.setBackground(new java.awt.Color(255, 204, 204));
        jPanel24.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 255), 4));
        jPanel24.setLayout(null);

        jTabbedPane4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 255), 3));

        jPanel26.setBackground(new java.awt.Color(255, 204, 204));
        jPanel26.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 255), 2), "Search Most Wanted Criminal Record", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 18), new java.awt.Color(0, 51, 255))); // NOI18N
        jPanel26.setLayout(null);

        mwl.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        mwl.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 255)));
        mwl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mwlActionPerformed(evt);
            }
        });
        jPanel26.add(mwl);
        mwl.setBounds(190, 30, 350, 31);

        jButton21.setBackground(new java.awt.Color(255, 153, 153));
        jButton21.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        jButton21.setText("Search Record by ID");
        jButton21.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 255)));
        jButton21.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton21.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton21MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton21MouseExited(evt);
            }
        });
        jButton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton21ActionPerformed(evt);
            }
        });
        jPanel26.add(jButton21);
        jButton21.setBounds(190, 70, 350, 25);

        jButton22.setBackground(new java.awt.Color(255, 153, 153));
        jButton22.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        jButton22.setText("Search Record by Criminal Name");
        jButton22.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 255)));
        jButton22.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton22.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton22MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton22MouseExited(evt);
            }
        });
        jButton22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton22ActionPerformed(evt);
            }
        });
        jPanel26.add(jButton22);
        jButton22.setBounds(190, 110, 350, 25);

        jButton23.setBackground(new java.awt.Color(255, 153, 153));
        jButton23.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        jButton23.setText("ALL RECORDS");
        jButton23.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 255)));
        jButton23.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton23.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton23MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton23MouseExited(evt);
            }
        });
        jButton23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton23ActionPerformed(evt);
            }
        });
        jPanel26.add(jButton23);
        jButton23.setBounds(190, 140, 350, 25);

        jTabbedPane4.addTab("Search Most Wanted Criminal", jPanel26);

        jPanel25.setBackground(new java.awt.Color(255, 204, 204));
        jPanel25.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 255), 2), "Common crime Record", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 18), new java.awt.Color(51, 51, 255))); // NOI18N
        jPanel25.setLayout(null);

        ccl1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        ccl1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 255)));
        ccl1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ccl1ActionPerformed(evt);
            }
        });
        jPanel25.add(ccl1);
        ccl1.setBounds(210, 30, 317, 31);

        jButton17.setBackground(new java.awt.Color(255, 153, 153));
        jButton17.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        jButton17.setText("Search Record by ID");
        jButton17.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 255)));
        jButton17.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton17MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton17MouseExited(evt);
            }
        });
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });
        jPanel25.add(jButton17);
        jButton17.setBounds(210, 70, 317, 25);

        jButton18.setBackground(new java.awt.Color(255, 153, 153));
        jButton18.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        jButton18.setText("Search Record by Criminal Name");
        jButton18.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 255)));
        jButton18.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton18.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton18MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton18MouseExited(evt);
            }
        });
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });
        jPanel25.add(jButton18);
        jButton18.setBounds(210, 110, 317, 25);

        jButton19.setBackground(new java.awt.Color(255, 153, 153));
        jButton19.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        jButton19.setText("ALL RECORDS");
        jButton19.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 255)));
        jButton19.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton19.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton19MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton19MouseExited(evt);
            }
        });
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });
        jPanel25.add(jButton19);
        jButton19.setBounds(220, 140, 317, 25);

        jTabbedPane4.addTab("Search Common Crime Record", jPanel25);

        jPanel28.setBackground(new java.awt.Color(255, 204, 204));
        jPanel28.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 255), 2), "Search Court Order Record", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 18), new java.awt.Color(51, 51, 255))); // NOI18N
        jPanel28.setLayout(null);

        col.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        col.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 255)));
        col.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                colActionPerformed(evt);
            }
        });
        jPanel28.add(col);
        col.setBounds(240, 30, 311, 31);

        jButton30.setBackground(new java.awt.Color(255, 153, 153));
        jButton30.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        jButton30.setText("Search Record By  Court ID");
        jButton30.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 255)));
        jButton30.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton30.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton30MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton30MouseExited(evt);
            }
        });
        jButton30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton30ActionPerformed(evt);
            }
        });
        jPanel28.add(jButton30);
        jButton30.setBounds(240, 70, 311, 25);

        jButton31.setBackground(new java.awt.Color(255, 153, 153));
        jButton31.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        jButton31.setText("ALL RECORDS");
        jButton31.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 255)));
        jButton31.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton31.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton31MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton31MouseExited(evt);
            }
        });
        jButton31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton31ActionPerformed(evt);
            }
        });
        jPanel28.add(jButton31);
        jButton31.setBounds(240, 110, 311, 25);

        jTabbedPane4.addTab("Search Court Order", jPanel28);

        jPanel34.setBackground(new java.awt.Color(255, 204, 204));
        jPanel34.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 255), 2), "Search Court Order Record", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 18), new java.awt.Color(51, 51, 255))); // NOI18N
        jPanel34.setLayout(null);

        col1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        col1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 255)));
        col1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                col1ActionPerformed(evt);
            }
        });
        jPanel34.add(col1);
        col1.setBounds(240, 30, 311, 31);

        jButton32.setBackground(new java.awt.Color(255, 153, 153));
        jButton32.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        jButton32.setText("Search Record By  Court ID");
        jButton32.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 255)));
        jButton32.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton32.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton32MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton32MouseExited(evt);
            }
        });
        jButton32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton32ActionPerformed(evt);
            }
        });
        jPanel34.add(jButton32);
        jButton32.setBounds(240, 70, 311, 25);

        jButton36.setBackground(new java.awt.Color(255, 153, 153));
        jButton36.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        jButton36.setText("ALL RECORDS");
        jButton36.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 255)));
        jButton36.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton36.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton36MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton36MouseExited(evt);
            }
        });
        jButton36.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton36ActionPerformed(evt);
            }
        });
        jPanel34.add(jButton36);
        jButton36.setBounds(240, 110, 311, 25);

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 765, Short.MAX_VALUE)
            .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel12Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel34, javax.swing.GroupLayout.PREFERRED_SIZE, 765, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 200, Short.MAX_VALUE)
            .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel12Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel34, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jTabbedPane4.addTab("villege Record", jPanel12);

        jPanel24.add(jTabbedPane4);
        jTabbedPane4.setBounds(240, 60, 776, 240);

        jScrollPane10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 255)));

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane10.setViewportView(jTable2);

        jPanel24.add(jScrollPane10);
        jScrollPane10.setBounds(70, 300, 1090, 150);

        jLabel72.setBackground(new java.awt.Color(0, 204, 0));
        jLabel72.setFont(new java.awt.Font("Garamond", 3, 48)); // NOI18N
        jLabel72.setForeground(new java.awt.Color(0, 51, 255));
        jLabel72.setText(" Search Record");
        jLabel72.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 255), 2));
        jPanel24.add(jLabel72);
        jLabel72.setBounds(420, 10, 365, 50);

        jPanel8.add(jPanel24);
        jPanel24.setBounds(0, 0, 1280, 450);

        jTabbedPane1.addTab("Search Record", jPanel8);

        jPanel9.setLayout(null);

        jPanel29.setBackground(new java.awt.Color(255, 204, 204));
        jPanel29.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 255), 2));
        jPanel29.setLayout(null);

        jLabel73.setFont(new java.awt.Font("Old English Text MT", 3, 48)); // NOI18N
        jLabel73.setText("Event");
        jLabel73.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jPanel29.add(jLabel73);
        jLabel73.setBounds(490, 20, 190, 88);

        jLabel9.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel9.setText("Event");
        jPanel29.add(jLabel9);
        jLabel9.setBounds(360, 250, 82, 37);

        jLabel10.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel10.setText("ID NO:");
        jPanel29.add(jLabel10);
        jLabel10.setBounds(370, 130, 67, 37);

        jLabel74.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel74.setText("Date:");
        jPanel29.add(jLabel74);
        jLabel74.setBounds(360, 190, 82, 37);

        event_id.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        event_id.setHorizontalAlignment(JTextField.CENTER);
        event_id.setToolTipText("Enter the CourtOrder Number");
        event_id.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 255)));
        jPanel29.add(event_id);
        event_id.setBounds(480, 120, 330, 40);

        event.setColumns(20);
        event.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        event.setRows(5);
        event.setToolTipText("Write the Court Order");
        event.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(0, 255, 51), new java.awt.Color(102, 102, 255), new java.awt.Color(0, 0, 255), new java.awt.Color(255, 0, 153)));
        jScrollPane11.setViewportView(event);

        jPanel29.add(jScrollPane11);
        jScrollPane11.setBounds(480, 240, 330, 110);

        loginbutton5.setBackground(new java.awt.Color(0, 153, 0));
        loginbutton5.setFont(new java.awt.Font("Chaparral Pro", 3, 24)); // NOI18N
        loginbutton5.setText("Save");
        loginbutton5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        loginbutton5.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                loginbutton5FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                loginbutton5FocusLost(evt);
            }
        });
        loginbutton5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                loginbutton5MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                loginbutton5MouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                loginbutton5MouseReleased(evt);
            }
        });
        loginbutton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginbutton5ActionPerformed(evt);
            }
        });
        jPanel29.add(loginbutton5);
        loginbutton5.setBounds(400, 370, 239, 33);
        jPanel29.add(event_date);
        event_date.setBounds(480, 180, 330, 40);

        jPanel9.add(jPanel29);
        jPanel29.setBounds(0, 0, 1260, 500);

        jTabbedPane1.addTab("Department Event ", jPanel9);

        jPanel10.setLayout(null);

        jTabbedPane5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 0, 51), 2));

        jPanel32.setBackground(new java.awt.Color(255, 204, 204));
        jPanel32.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 0, 51), 2));
        jPanel32.setLayout(null);

        jPanel33.setBackground(new java.awt.Color(255, 204, 204));
        jPanel33.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 255), 2), "Search Villege Record", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 24), new java.awt.Color(51, 51, 255))); // NOI18N
        jPanel33.setLayout(null);

        ccl2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        ccl2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 255)));
        ccl2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ccl2ActionPerformed(evt);
            }
        });
        jPanel33.add(ccl2);
        ccl2.setBounds(390, 40, 317, 31);

        jButton33.setBackground(new java.awt.Color(255, 153, 153));
        jButton33.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        jButton33.setText("Search Record by ID");
        jButton33.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 255)));
        jButton33.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton33.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton33MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton33MouseExited(evt);
            }
        });
        jButton33.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton33ActionPerformed(evt);
            }
        });
        jPanel33.add(jButton33);
        jButton33.setBounds(400, 80, 317, 25);

        jButton34.setBackground(new java.awt.Color(255, 153, 153));
        jButton34.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        jButton34.setText("Search Record by Villege Name");
        jButton34.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 255)));
        jButton34.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton34.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton34MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton34MouseExited(evt);
            }
        });
        jButton34.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton34ActionPerformed(evt);
            }
        });
        jPanel33.add(jButton34);
        jButton34.setBounds(400, 120, 317, 25);

        jButton35.setBackground(new java.awt.Color(255, 153, 153));
        jButton35.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        jButton35.setText("ALL RECORDS");
        jButton35.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 255)));
        jButton35.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton35.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton35MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton35MouseExited(evt);
            }
        });
        jButton35.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton35ActionPerformed(evt);
            }
        });
        jPanel33.add(jButton35);
        jButton35.setBounds(400, 160, 317, 25);

        jPanel32.add(jPanel33);
        jPanel33.setBounds(0, 0, 1240, 240);

        k1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 255), 3));

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        k1.setViewportView(jTable3);

        jPanel32.add(k1);
        k1.setBounds(0, 240, 1240, 170);

        jTabbedPane5.addTab("Search Villeges Information", jPanel32);

        jPanel30.setBackground(new java.awt.Color(255, 204, 204));
        jPanel30.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 0, 51), 2));
        jPanel30.setLayout(null);

        jPanel31.setBackground(new java.awt.Color(255, 204, 204));
        jPanel31.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 255)));
        jPanel31.setToolTipText("Enter the Village Headman contact number");
        jPanel31.setLayout(null);

        G1.setFont(new java.awt.Font("Verdana", 3, 14)); // NOI18N
        G1.setHorizontalAlignment(JTextField.CENTER);
        G1.setToolTipText("Enter ID no");
        G1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 255)));
        G1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        G1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                G1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                G1FocusLost(evt);
            }
        });
        G1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                G1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                G1MouseExited(evt);
            }
        });
        G1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                G1ActionPerformed(evt);
            }
        });
        G1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                G1KeyTyped(evt);
            }
        });
        jPanel31.add(G1);
        G1.setBounds(330, 90, 230, 30);

        G4.setFont(new java.awt.Font("Verdana", 3, 14)); // NOI18N
        G4.setHorizontalAlignment(JTextField.CENTER);
        G4.setToolTipText("Enter Village Headman Name");
        G4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 255)));
        G4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        G4.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                G4FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                G4FocusLost(evt);
            }
        });
        G4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                G4MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                G4MouseExited(evt);
            }
        });
        G4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                G4ActionPerformed(evt);
            }
        });
        G4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                G4KeyTyped(evt);
            }
        });
        jPanel31.add(G4);
        G4.setBounds(330, 250, 230, 30);

        G3.setFont(new java.awt.Font("Verdana", 3, 14)); // NOI18N
        G3.setHorizontalAlignment(JTextField.CENTER);
        G3.setToolTipText("Enter Village Papulation");
        G3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 255)));
        G3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        G3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                G3FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                G3FocusLost(evt);
            }
        });
        G3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                G3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                G3MouseExited(evt);
            }
        });
        G3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                G3ActionPerformed(evt);
            }
        });
        G3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                G3KeyTyped(evt);
            }
        });
        jPanel31.add(G3);
        G3.setBounds(330, 200, 230, 30);

        G5.setFont(new java.awt.Font("Verdana", 3, 14)); // NOI18N
        G5.setHorizontalAlignment(JTextField.CENTER);
        G5.setToolTipText("Enter ID no");
        G5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 255)));
        G5.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        G5.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                G5FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                G5FocusLost(evt);
            }
        });
        G5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                G5MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                G5MouseExited(evt);
            }
        });
        G5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                G5ActionPerformed(evt);
            }
        });
        G5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                G5KeyTyped(evt);
            }
        });
        jPanel31.add(G5);
        G5.setBounds(330, 300, 230, 30);

        jLabel75.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel75.setText("Village ID:");
        jPanel31.add(jLabel75);
        jLabel75.setBounds(220, 90, 99, 37);

        jLabel76.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel76.setText("Headman Contact no:");
        jPanel31.add(jLabel76);
        jLabel76.setBounds(130, 300, 189, 37);

        jLabel77.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel77.setText("Village Headman:");
        jPanel31.add(jLabel77);
        jLabel77.setBounds(160, 250, 173, 37);

        jLabel78.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel78.setText("Village Papulation:");
        jPanel31.add(jLabel78);
        jLabel78.setBounds(150, 200, 173, 37);

        G2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        G2.setMaximumRowCount(20);
        G2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select The Village Name..", "Lasalgaon", "Takali", "Vinchur", "Malegaon", "Kotamgaon", "Vaki", "Pimplad", " " }));
        G2.setToolTipText("Enter Village Name");
        G2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 204)));
        jPanel31.add(G2);
        G2.setBounds(330, 140, 230, 34);

        jLabel79.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel79.setText("Village Name:");
        jPanel31.add(jLabel79);
        jLabel79.setBounds(190, 140, 123, 37);

        jLabel80.setBackground(new java.awt.Color(255, 255, 255));
        jLabel80.setFont(new java.awt.Font("Garamond", 3, 36)); // NOI18N
        jLabel80.setForeground(new java.awt.Color(0, 102, 255));
        jLabel80.setText("    Villages Information");
        jLabel80.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 255), 3));
        jPanel31.add(jLabel80);
        jLabel80.setBounds(130, 0, 401, 58);

        loginbutton6.setBackground(new java.awt.Color(0, 153, 0));
        loginbutton6.setFont(new java.awt.Font("Chaparral Pro", 3, 24)); // NOI18N
        loginbutton6.setText("SAVE");
        loginbutton6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        loginbutton6.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                loginbutton6FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                loginbutton6FocusLost(evt);
            }
        });
        loginbutton6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                loginbutton6MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                loginbutton6MouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                loginbutton6MouseReleased(evt);
            }
        });
        loginbutton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginbutton6ActionPerformed(evt);
            }
        });
        jPanel31.add(loginbutton6);
        loginbutton6.setBounds(300, 360, 100, 33);

        jPanel30.add(jPanel31);
        jPanel31.setBounds(230, 10, 620, 470);

        jTabbedPane5.addTab("Enter Villages Information", jPanel30);

        jPanel10.add(jTabbedPane5);
        jTabbedPane5.setBounds(0, 0, 1270, 520);

        jTabbedPane1.addTab("Village information", jPanel10);

        jPanel1.add(jTabbedPane1);
        jTabbedPane1.setBounds(0, 160, 1290, 480);

        jLabel1.setText("jLabel1");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(780, 10, 130, 110);

        jLabel81.setFont(jLabel81.getFont().deriveFont(jLabel81.getFont().getStyle() | java.awt.Font.BOLD, jLabel81.getFont().getSize()+24));
        jPanel1.add(jLabel81);
        jLabel81.setBounds(150, 120, 1080, 40);

        jLabel82.setFont(new java.awt.Font("Calisto MT", 1, 48)); // NOI18N
        jLabel82.setForeground(new java.awt.Color(0, 0, 204));
        jLabel82.setText("POLICE DEPARTMENT");
        jPanel1.add(jLabel82);
        jLabel82.setBounds(200, 20, 570, 90);

        jLabel83.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel83.setText("Date:");
        jPanel1.add(jLabel83);
        jLabel83.setBounds(980, 50, 70, 30);

        time.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jPanel1.add(time);
        time.setBounds(1050, 10, 180, 30);

        date.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jPanel1.add(date);
        date.setBounds(1050, 50, 200, 30);

        jLabel87.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel87.setText("Time:");
        jPanel1.add(jLabel87);
        jLabel87.setBounds(980, 10, 70, 30);

        jButton38.setFont(new java.awt.Font("Californian FB", 1, 24)); // NOI18N
        jButton38.setForeground(new java.awt.Color(255, 0, 0));
        jButton38.setText("Account Section");
        jButton38.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 51, 51), 2));
        jButton38.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton38ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton38);
        jButton38.setBounds(940, 80, 170, 33);

        jButton39.setFont(new java.awt.Font("Californian FB", 1, 24)); // NOI18N
        jButton39.setForeground(new java.awt.Color(255, 0, 0));
        jButton39.setText("Logout");
        jButton39.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 51, 51), 2));
        jButton39.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton39ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton39);
        jButton39.setBounds(1120, 80, 170, 33);

        jLabel6.setText("jLabel6");
        jPanel1.add(jLabel6);
        jLabel6.setBounds(0, 0, 140, 160);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1303, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 645, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void common_crime_timeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_common_crime_timeFocusGained
        // TODO add your handling code here:
        // setgot(f2,"enter full name");
    }//GEN-LAST:event_common_crime_timeFocusGained

    private void common_crime_timeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_common_crime_timeFocusLost
        // TODO add your handling code here:
        //   setlost(f2,"enter full name");

    }//GEN-LAST:event_common_crime_timeFocusLost

    private void common_crime_timeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_common_crime_timeMouseEntered
        // TODO add your handling code here:
        //    Men(f2);

    }//GEN-LAST:event_common_crime_timeMouseEntered

    private void common_crime_timeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_common_crime_timeMouseExited
        // TODO add your handling code here:
        //    Mex(f2);
    }//GEN-LAST:event_common_crime_timeMouseExited

    private void common_crime_timeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_common_crime_timeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_common_crime_timeActionPerformed

    private void common_crime_idFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_common_crime_idFocusGained
        // TODO add your handling code here:
        //   setgot(f1,"enter id number");
    }//GEN-LAST:event_common_crime_idFocusGained

    private void common_crime_idFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_common_crime_idFocusLost
        // TODO add your handling code here:
        // setlost(f1,"enter id number");

    }//GEN-LAST:event_common_crime_idFocusLost

    private void common_crime_idMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_common_crime_idMouseEntered
        // TODO add your handling code here:
        //   Men(f1);

    }//GEN-LAST:event_common_crime_idMouseEntered

    private void common_crime_idMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_common_crime_idMouseExited
        // TODO add your handling code here:
        //  Mex(f1);
    }//GEN-LAST:event_common_crime_idMouseExited

    private void common_crime_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_common_crime_idActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_common_crime_idActionPerformed

    private void common_crime_idKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_common_crime_idKeyTyped
        if (!Character.isDigit(evt.getKeyChar())) {
            JOptionPane.showMessageDialog(null, "Enter Only Digit", "Empty Password", 2);
            evt.consume();        // TODO add your handling code here:
        }
    }//GEN-LAST:event_common_crime_idKeyTyped

    private void loginbuttonFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_loginbuttonFocusGained
        // TODO add your handling code here:
        //   loginbutton.setBackground(new Color(255,0,0));
    }//GEN-LAST:event_loginbuttonFocusGained

    private void loginbuttonFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_loginbuttonFocusLost
        // TODO add your handling code here:
        //    loginbutton.setBackground(new Color(0,153,0));
    }//GEN-LAST:event_loginbuttonFocusLost

    private void loginbuttonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loginbuttonMouseEntered
        // TODO add your handling code here:
        //       loginbutton.setBackground(new Color(255,0,0));
    }//GEN-LAST:event_loginbuttonMouseEntered

    private void loginbuttonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loginbuttonMouseExited
        // TODO add your handling code here:
        //     loginbutton.setBackground(new Color(0,153,0));
    }//GEN-LAST:event_loginbuttonMouseExited

    private void loginbuttonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loginbuttonMouseReleased
   
    }//GEN-LAST:event_loginbuttonMouseReleased
   boolean common_value_check() {
       String common_crime_id1=common_crime_id.getText();
      
        String common_crime_name1 = common_crime_name.getText();
         String common_crime_gender = "Male";
        if (female.isSelected()) {
            common_crime_gender = "Female";
        }
         String common_crime_age1 = (String)common_crime_age.getSelectedItem();
         String common_crime_time1= common_crime_time.getText();
         String common_crime_date1 =((JTextField)common_crime_date.getDateEditor().getUiComponent()).getText();
          String common_crime1= common_crime.getText();
         String common_crime_dutyofficer1 = (String)common_crime_dutyofficer.getSelectedItem();
       
 

        // check empty fields
        if (common_crime_id1.trim().equals("") || common_crime_name1.trim().equals("")
           || common_crime_gender.trim().equals("") || common_crime_age1.trim().equals("")
           || common_crime_time1.trim().equals("")|| common_crime_date1.trim().equals("") || common_crime1.trim().equals("")
                || common_crime_dutyofficer1.trim().equals(""))
        {
            JOptionPane.showMessageDialog(null, "One Or More Fields Are Empty", "Empty Fields", 2);
            return false;
        } // check if the two password are equals or not
    
        else {
            return true;
        }
    }
   void common_crime_clearfield(){
       common_crime_name.setText("");
       common_crime_time.setText("");
       common_crime.setText("");
   }
    private void loginbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginbuttonActionPerformed
        // TODO add your handling code here:
        int common_crime_id1 = Integer.parseInt( common_crime_id.getText());
        String common_crime_name1 = common_crime_name.getText();
         String common_crime_gender = "Male";
        if (female.isSelected()) {
            common_crime_gender = "Female";
        }
         String common_crime_age1 = (String)common_crime_age.getSelectedItem();
         String common_crime_time1= common_crime_time.getText();
         String common_crime_date1 =((JTextField)common_crime_date.getDateEditor().getUiComponent()).getText();
          String common_crime1= common_crime.getText();
         String common_crime_dutyofficer1 = (String)common_crime_dutyofficer.getSelectedItem();
       
 if (common_value_check()){
       
        String Query= "INSERT INTO commoncrime VALUES(?,?,?,?,?,?,?,?);";

       

        PreparedStatement ps;

        try {
            boolean flag = false;
            ps = loginconnection.connection().prepareStatement(Query);
            ps.setInt(1, common_crime_id1);
            ps.setString(2,common_crime_name1);
            ps.setString(3,common_crime_gender);
            ps.setString(4,common_crime_age1);
            ps.setString(5,common_crime_time1);
            ps.setString(6,common_crime_date1);
            ps.setString(7,common_crime1);
            ps.setString(8,common_crime_dutyofficer1);
            ps.execute();

            flag = true;
            if (flag == true) {
                JOptionPane.showMessageDialog(null, "Information saved");
                common_crime_clearfield();
            } else {
                JOptionPane.showMessageDialog(null, "Error: Check Your Information");
            }

        } catch (Exception ex) {
        }
        rno();
 }
    }//GEN-LAST:event_loginbuttonActionPerformed
    void rno() {
        String s;
        n();
   
        s = this.srno("select ID_NUMBER from commoncrime order by ID_NUMBER DESC Limit 1;", "ID_NUMBER");
        common_crime_id.setText(s);
//String s;

        s = this.srno("select ID from village order by ID DESC Limit 1;", "ID");
        G1.setText(s);
        s = this.srno("select ID from EVENT order by ID DESC Limit 1;", "ID");
        event_id.setText(s);
        s = this.srno("select ID_NUMBER from staff order by ID_NUMBER DESC Limit 1;", "ID_NUMBER");
        idno1.setText(s);
        s = this.srno("select ID from courtorder order by ID DESC Limit 1;", "ID");
        court_id.setText(s);
        s = this.srno("select ID_NUMBER from mostwanted order by ID_NUMBER DESC Limit 1;", "ID_NUMBER");
        mostwanted_id.setText(s);
    }
    private void femaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_femaleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_femaleActionPerformed

    private void common_crime_dutyofficerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_common_crime_dutyofficerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_common_crime_dutyofficerActionPerformed

    private void common_crime_ageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_common_crime_ageActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_common_crime_ageActionPerformed

    private void mostwanted_nameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_mostwanted_nameFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_mostwanted_nameFocusGained

    private void mostwanted_nameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_mostwanted_nameFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_mostwanted_nameFocusLost

    private void mostwanted_nameMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mostwanted_nameMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_mostwanted_nameMouseEntered

    private void mostwanted_nameMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mostwanted_nameMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_mostwanted_nameMouseExited

    private void mostwanted_nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mostwanted_nameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mostwanted_nameActionPerformed

    private void mostwanted_idFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_mostwanted_idFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_mostwanted_idFocusGained

    private void mostwanted_idFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_mostwanted_idFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_mostwanted_idFocusLost

    private void mostwanted_idMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mostwanted_idMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_mostwanted_idMouseEntered

    private void mostwanted_idMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mostwanted_idMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_mostwanted_idMouseExited

    private void mostwanted_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mostwanted_idActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mostwanted_idActionPerformed

    private void mostwanted_idKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_mostwanted_idKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_mostwanted_idKeyTyped

    private void loginbutton1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_loginbutton1FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_loginbutton1FocusGained

    private void loginbutton1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_loginbutton1FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_loginbutton1FocusLost

    private void loginbutton1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loginbutton1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_loginbutton1MouseEntered

    private void loginbutton1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loginbutton1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_loginbutton1MouseExited

    private void loginbutton1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loginbutton1MouseReleased
        // TODO add your handling code here:
  
    }//GEN-LAST:event_loginbutton1MouseReleased
 boolean mostwanted_value_check() {
       String mostwanted_id1=mostwanted_id.getText();
  
        String mostwanted_name1 = mostwanted_name.getText();
  String mostwanted_gender = "Male";
        if (female.isSelected()) {
            mostwanted_gender = "Female";
        }
        String mostwanted_age1 = (String) mostwanted_age.getSelectedItem();
     String mostwanted_criminal_detail1 = mostwanted_criminal_detail.getText();
     String mostwanted_crime_discription1 = mostwanted_crime_discription.getText();
        String mostwanted_section1 = (String) mostwanted_section.getSelectedItem();
        String mostwanted_officer1 = (String) mostwanted_officer.getSelectedItem();
        

        // check empty fields
        if (mostwanted_id1.trim().equals("") || mostwanted_name1.trim().equals("")
           || mostwanted_gender.trim().equals("") || mostwanted_age1.trim().equals("")
           || mostwanted_criminal_detail1.trim().equals("")|| mostwanted_crime_discription1.trim().equals("") || mostwanted_section1.trim().equals("")
                || mostwanted_officer1.trim().equals(""))
        {
            JOptionPane.showMessageDialog(null, "One Or More Fields Are Empty", "Empty Fields", 2);
            return false;
        } // check if the two password are equals or not
    
        else {
            return true;
        }
    }
 void mostwanted_clearfield(){
     mostwanted_name.setText("");
             
    mostwanted_criminal_detail.setText("");
             
    mostwanted_crime_discription.setText("");
 }
    private void loginbutton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginbutton1ActionPerformed
     
          int mostwanted_id1 = Integer.parseInt(mostwanted_id.getText());
        String mostwanted_name1 = mostwanted_name.getText();
  String mostwanted_gender = "Male";
        if (female.isSelected()) {
            mostwanted_gender = "Female";
        }
        String mostwanted_age1 = (String) mostwanted_age.getSelectedItem();
     String mostwanted_criminal_detail1 = mostwanted_criminal_detail.getText();
     String mostwanted_crime_discription1 = mostwanted_crime_discription.getText();
        String mostwanted_section1 = (String) mostwanted_section.getSelectedItem();
        String mostwanted_officer1 = (String) mostwanted_officer.getSelectedItem();
        
       
if (mostwanted_value_check()){
        
        String Query= "INSERT INTO mostwanted VALUES(?,?,?,?,?,?,?,?);";

     

        PreparedStatement ps;

        try {
            boolean flag = false;
            ps = loginconnection.connection().prepareStatement(Query);
            ps.setInt(1, mostwanted_id1);
            ps.setString(2, mostwanted_name1);
            ps.setString(3, mostwanted_gender);
            ps.setString(4, mostwanted_age1);
            ps.setString(5, mostwanted_criminal_detail1);
            ps.setString(6, mostwanted_crime_discription1);
            ps.setString(7, mostwanted_section1);
            ps.setString(8, mostwanted_officer1);
           
            ps.execute();

            flag = true;
            if (flag == true) {
                JOptionPane.showMessageDialog(null, "Information saved");
                mostwanted_clearfield();
            } else {
                JOptionPane.showMessageDialog(null, "Error: Check Your Information");
            }

        } catch (Exception ex) {
        }
        rno();
}

    }//GEN-LAST:event_loginbutton1ActionPerformed

    private void female1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_female1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_female1ActionPerformed

    private void mostwanted_officerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mostwanted_officerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mostwanted_officerActionPerformed

    private void mostwanted_sectionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mostwanted_sectionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mostwanted_sectionActionPerformed

    private void mostwanted_ageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mostwanted_ageActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mostwanted_ageActionPerformed

    private void loginbutton3FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_loginbutton3FocusGained
        // TODO add your handling code here:
//        loginbutton.setBackground(new Color(255,0,0));
    }//GEN-LAST:event_loginbutton3FocusGained

    private void loginbutton3FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_loginbutton3FocusLost
        // TODO add your handling code here:
        //  loginbutton.setBackground(new Color(0,153,0));
    }//GEN-LAST:event_loginbutton3FocusLost

    private void loginbutton3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loginbutton3MouseEntered
        // TODO add your handling code here:
        //   loginbutton.setBackground(new Color(255,0,0));
    }//GEN-LAST:event_loginbutton3MouseEntered

    private void loginbutton3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loginbutton3MouseExited
        // TODO add your handling code here:
        //    loginbutton.setBackground(new Color(0,153,0));
    }//GEN-LAST:event_loginbutton3MouseExited

    private void loginbutton3MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loginbutton3MouseReleased
        // TODO add your handling code here:

       
    }//GEN-LAST:event_loginbutton3MouseReleased
void court_clear(){
     court_id.setText("");
        order.setText("");
       
}
boolean court_value_check() {

   String id=court_id.getText();
         String order1 = order.getText();
       String strDate  =((JTextField)court_date.getDateEditor().getUiComponent()).getText();
        // check empty fields
        if (order1.trim().equals("") || strDate.trim().equals("")
           || id.trim().equals("") )
        {
            JOptionPane.showMessageDialog(null, "One Or More Fields Are Empty", "Empty Fields", 2);
            return false;
        } // check if the two password are equals or not
    
        else {
            return true;
        }
    }
    private void loginbutton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginbutton3ActionPerformed
      int id = Integer.parseInt(court_id.getText());
         String order1 = order.getText();
        boolean flag = false;
       String strDate  =((JTextField)court_date.getDateEditor().getUiComponent()).getText();

        if (court_value_check()){
        PreparedStatement ps;
        ResultSet rs;
      String registerUserQuery1 = "INSERT INTO  courtorder VALUES(?,?,?);";
      try {

            ps = loginconnection.connection().prepareStatement(registerUserQuery1);
            ps.setInt(1, id);
            ps.setString(2, strDate);
            ps.setString(3, order1);
            ps.execute();

            flag = true;

            if (flag == true) {
                JOptionPane.showMessageDialog(null, "INFORMATION SAVED SUCCESSFULLY");
                court_clear();
            } else {
                JOptionPane.showMessageDialog(null, "Error: Check Your Information");
            }

        } catch (Exception ex) {
        }
        }
     rno();
    }//GEN-LAST:event_loginbutton3ActionPerformed

    private void contactnoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_contactnoFocusGained
        // TODO add your handling code here:
        if (contactno.getText().trim().toLowerCase().equals("enter contact no")) {
            contactno.setText("");
            contactno.setForeground(Color.black);
        }
    }//GEN-LAST:event_contactnoFocusGained

    private void contactnoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_contactnoFocusLost
        // TODO add your handling code here:
        if (contactno.getText().trim().equals("") || contactno.getText().trim().toLowerCase().equals("enter contact no")) {
            contactno.setText("enter contact no");
            contactno.setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_contactnoFocusLost

    private void contactnoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_contactnoMouseEntered
        // TODO add your handling code here:
        contactno.setBackground(new Color(0, 255, 0));
    }//GEN-LAST:event_contactnoMouseEntered

    private void contactnoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_contactnoMouseExited
        // TODO add your handling code here:
        contactno.setBackground(new Color(255, 255, 255));
    }//GEN-LAST:event_contactnoMouseExited

    private void contactnoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_contactnoKeyTyped
        if (!Character.isDigit(evt.getKeyChar())) {
            JOptionPane.showMessageDialog(null, "Enter Only Digit", "Empty Password", 2);
            evt.consume();
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_contactnoKeyTyped

    private void qulificationFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_qulificationFocusGained
        // TODO add your handling code here:
        if (qulification.getText().trim().toLowerCase().equals("enter qulification")) {
            qulification.setText("");
            qulification.setForeground(Color.black);
        }
    }//GEN-LAST:event_qulificationFocusGained

    private void qulificationFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_qulificationFocusLost
        // TODO add your handling code here:
        if (qulification.getText().trim().equals("") || qulification.getText().trim().toLowerCase().equals("enter qulification")) {
            qulification.setText("enter qulification");
            qulification.setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_qulificationFocusLost

    private void qulificationMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_qulificationMouseEntered
        qulification.setBackground(new Color(0, 255, 0));
        // TODO add your handling code here:
    }//GEN-LAST:event_qulificationMouseEntered

    private void qulificationMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_qulificationMouseExited
        qulification.setBackground(new Color(255, 255, 255));
        // TODO add your handling code here:
    }//GEN-LAST:event_qulificationMouseExited

    private void qulificationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_qulificationActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_qulificationActionPerformed

    private void idno1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_idno1FocusGained
        // TODO add your handling code here:
        if (court_id.getText().trim().toLowerCase().equals("enter id")) {
            court_id.setText("");
            court_id.setForeground(Color.black);
        }
    }//GEN-LAST:event_idno1FocusGained

    private void idno1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_idno1FocusLost
        // TODO add your handling code here:

        if (court_id.getText().trim().equals("") || court_id.getText().trim().toLowerCase().equals("enter id")) {
            court_id.setText("enter id");
            court_id.setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_idno1FocusLost

    private void idno1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_idno1MouseEntered
        // TODO add your handling code here:
        court_id.setBackground(new Color(0, 255, 0));
    }//GEN-LAST:event_idno1MouseEntered

    private void idno1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_idno1MouseExited
        // TODO add your handling code here:
        court_id.setBackground(new Color(255, 255, 255));
    }//GEN-LAST:event_idno1MouseExited

    private void idno1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idno1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idno1ActionPerformed

    private void idno1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_idno1KeyTyped
        // TODO add your handling code here:
        if (!Character.isDigit(evt.getKeyChar())) {
            JOptionPane.showMessageDialog(null, "Enter Only Digit", "Empty Password", 2);
            evt.consume();
        }
    }//GEN-LAST:event_idno1KeyTyped

    private void female2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_female2MouseEntered
        //   female.setBackground(new Color(0,255,0));
        // TODO add your handling code here:
    }//GEN-LAST:event_female2MouseEntered

    private void female2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_female2MouseExited
        female.setBackground(new Color(255, 255, 255));
        // TODO add your handling code here:
    }//GEN-LAST:event_female2MouseExited

    private void female2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_female2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_female2ActionPerformed

    private void male2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_male2MouseEntered
        male.setBackground(new Color(0, 255, 0));
        // TODO add your handling code here:
    }//GEN-LAST:event_male2MouseEntered

    private void male2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_male2MouseExited
        male.setBackground(new Color(255, 255, 255));
        // TODO add your handling code here:
    }//GEN-LAST:event_male2MouseExited

    private void dobMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dobMouseEntered
        // TODO add your handling code here:
        // dob.setBackground(new Color(0,255,0));
    }//GEN-LAST:event_dobMouseEntered

    private void dobMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dobMouseExited
        // TODO add your handling code here:
        //  dob.setBackground(new Color(255,255,255));
    }//GEN-LAST:event_dobMouseExited

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        String path = null;
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        FileNameExtensionFilter extension = new FileNameExtensionFilter("*.Images", "jpg", "png", "jpeg", "gif");
        chooser.addChoosableFileFilter(extension);
        int filestate = chooser.showSaveDialog(null);
        if (filestate == JFileChooser.APPROVE_OPTION) {
            File selectedImage = chooser.getSelectedFile();
            path = selectedImage.getAbsolutePath();
           imagepath.setText(path);
            img4.setIcon(ResizeImage(path, img4));
           
        } else if (filestate == JFileChooser.CANCEL_OPTION) {
            ImageIcon iw = new ImageIcon();
          //  img.setIcon(iw);
        }
    }

    ImageIcon ResizeImage(String imagepath, JLabel l) {
        ImageIcon myimage;
        myimage = new ImageIcon(imagepath);
        Image img1;
        img1 = myimage.getImage();
        Image img2 = img1.getScaledInstance(l.getWidth(), l.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon iw = new ImageIcon(img2);
        return iw;
    }//GEN-LAST:event_jButton8ActionPerformed

    private void nameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_nameFocusGained
        // TODO add your handling code here:
        if (name.getText().trim().toLowerCase().equals("enter name")) {
            name.setText("");
            name.setForeground(Color.black);
        }
    }//GEN-LAST:event_nameFocusGained

    private void nameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_nameFocusLost
        // TODO add your handling code here:
        if (name.getText().trim().equals("") || name.getText().trim().toLowerCase().equals("enter name")) {
            name.setText("enter name");
            name.setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_nameFocusLost

    private void nameMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nameMouseEntered
        // TODO add your handling code here:
        name.setBackground(new Color(0, 255, 0));
    }//GEN-LAST:event_nameMouseEntered

    private void nameMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nameMouseExited
        // TODO add your handling code here:
        name.setBackground(new Color(255, 255, 255));
    }//GEN-LAST:event_nameMouseExited

    private void nameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nameKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_nameKeyTyped

    private void loginbutton4FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_loginbutton4FocusGained
        // TODO add your handling code here:
        loginbutton.setBackground(new Color(255, 0, 0));
    }//GEN-LAST:event_loginbutton4FocusGained

    private void loginbutton4FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_loginbutton4FocusLost
        // TODO add your handling code here:
        loginbutton.setBackground(new Color(0, 153, 0));
    }//GEN-LAST:event_loginbutton4FocusLost

    private void loginbutton4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loginbutton4MouseEntered
        // TODO add your handling code here:
        loginbutton.setBackground(new Color(255, 0, 0));
    }//GEN-LAST:event_loginbutton4MouseEntered

    private void loginbutton4MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loginbutton4MouseExited
        // TODO add your handling code here:
        loginbutton.setBackground(new Color(0, 153, 0));
    }//GEN-LAST:event_loginbutton4MouseExited

    private void loginbutton4MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loginbutton4MouseReleased
        // TODO add your handling code here:

    }//GEN-LAST:event_loginbutton4MouseReleased
 boolean staff_value_check() {
       String mostwanted_id1=court_id.getText();

        String fname = name.getText();
        String phone = contactno.getText();
        String user = qulification.getText();
        String cp = (String) currentpost.getSelectedItem();

    

      
        String ipath = imagepath.getText();


        if (mostwanted_id1.trim().equals("") || fname.trim().equals("") || phone.trim().equals("") || user.trim().equals("")
           || cp.trim().equals("") || ipath.trim().equals("")
             )
        {
            JOptionPane.showMessageDialog(null, "One Or More Fields Are Empty");
            return false;
        } 
    
        else {
            return true;
        }
    }
    private void loginbutton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginbutton4ActionPerformed
  

             boolean flag = false;

       
         int id = Integer.parseInt(idno1.getText());
        String name1 = name.getText();
         String gender = "Male";
         if (female2.isSelected()) {
            gender = "Female";
        }
         String staff_date1  =((JTextField)staff_date.getDateEditor().getUiComponent()).getText();
        String contactno1 = contactno.getText();
        String qulification1 = qulification.getText();
        String currentpost1 = (String) currentpost.getSelectedItem();
       String imagepath1 = imagepath.getText();
       
        
       if (staff_value_check()){

        PreparedStatement ps;
   
       String Query = "INSERT INTO `staff` VALUES(?,?,?,?,?,?,?,?);";
        try {

              ps = loginconnection.connection().prepareStatement(Query);
            ps.setInt(1, id);
            ps.setString(2, name1);
            ps.setString(3, gender);
            ps.setString(4, staff_date1);
            ps.setString(5, contactno1);
            ps.setString(6, qulification1);
            ps.setString(7, currentpost1);
            ps.setString(8, imagepath1);
            ps.execute();
            flag = true;

            if (flag == true) {
                JOptionPane.showMessageDialog(null, "Your Information Saved!!!");
                staff_clear();
                 rno();
        iimg();
            } else {
                JOptionPane.showMessageDialog(null, "Error: Check Your Information");
            }

        } catch (Exception e) {
        }
       }
       

    }//GEN-LAST:event_loginbutton4ActionPerformed
   void staff_clear(){
      
       name.setText("");
       contactno.setText("");
      qulification.setText("");
    
 
       imagepath.setText("");
   
   }
   

    private void cclActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cclActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cclActionPerformed

    private void jButton14MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton14MouseEntered
        // TODO add your handling code here:

    }//GEN-LAST:event_jButton14MouseEntered

    private void jButton14MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton14MouseExited
        // TODO add your handling code here:
 
    }//GEN-LAST:event_jButton14MouseExited

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        int key = Integer.parseInt(ccl.getText());
        setVisible(true);
        //String key="anjali";
        setLayout(null);

        Connection con = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/police", "root", "");

            PreparedStatement s = con.prepareStatement("SELECT * FROM staff WHERE ID_NUMBER=?");
            s.setInt(1, key);
            ResultSet rs = s.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();

            getContentPane().setBackground(Color.white);

            JTable jt = new JTable();

            DefaultTableModel dtm = new DefaultTableModel();

            Object col[] = new Object[rsmd.getColumnCount()];

            Object det[] = new Object[rsmd.getColumnCount()];
            for (int p = 1; p <= rsmd.getColumnCount(); p++) {
                col[p - 1] = rsmd.getColumnName(p);
            }

            jTable1.setModel(dtm);

            dtm.setColumnIdentifiers(col);

            while (rs.next()) {

                for (int j = 1; j <= rsmd.getColumnCount(); j++) {

                    if (rsmd.getColumnTypeName(j).equals("int")) {
                        det[j - 1] = rs.getInt(j);
                    } else {
                        det[j - 1] = rs.getString(j);
                    }

                }
                dtm.addRow(det);
            }

            jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            this.resizeColumnWidth(jTable1);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Sorry ! Check Your Input", "Confirm Password", 2);
            System.out.println(e);

        }         // TODO add your handling code here:
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton15MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton15MouseEntered
        // TODO add your handling code here:
     //   jButton7.setBackground(new Color(255, 51, 102));
    }//GEN-LAST:event_jButton15MouseEntered

    private void jButton15MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton15MouseExited
        // TODO add your handling code here:
    ///////////////////    jButton7.setBackground(new Color(255, 153, 153));
    }//GEN-LAST:event_jButton15MouseExited

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        String key = ccl.getText();
        setVisible(true);
         setLayout(null);

        Connection con = null;
      
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/police", "root", "");

            PreparedStatement s = con.prepareStatement("SELECT * FROM staff WHERE NAME =?");
            s.setString(1, key);
            ResultSet rs = s.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();

            getContentPane().setBackground(Color.white);

            JTable jt = new JTable();

            DefaultTableModel dtm = new DefaultTableModel();

            Object col[] = new Object[rsmd.getColumnCount()];

            Object det[] = new Object[rsmd.getColumnCount()];
            for (int p = 1; p <= rsmd.getColumnCount(); p++) {
                col[p - 1] = rsmd.getColumnName(p);
            }

            jTable1.setModel(dtm);

            dtm.setColumnIdentifiers(col);

            while (rs.next()) {

                for (int j = 1; j <= rsmd.getColumnCount(); j++) {

                    if (rsmd.getColumnTypeName(j).equals("int")) {
                        det[j - 1] = rs.getInt(j);
                    } else {
                        det[j - 1] = rs.getString(j);
                    }

                }
                dtm.addRow(det);
            }

            jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            this.resizeColumnWidth(jTable1);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Sorry ! Check Your Input", "Confirm Password", 2);
            System.out.println(e);

        }         // TODO add your handling code here:
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jButton16MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton16MouseEntered
        // TODO add your handling code here:
    
    }//GEN-LAST:event_jButton16MouseEntered

    private void jButton16MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton16MouseExited
        // TODO add your handling code here:
     
    }//GEN-LAST:event_jButton16MouseExited

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        String key = ccl.getText();
        setVisible(true);
        //String key="anjali";
        setLayout(null);

        Connection con = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/police", "root", "");

            PreparedStatement s = con.prepareStatement("SELECT * FROM staff");
            //s.setString(1,key);
            ResultSet rs = s.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();

            getContentPane().setBackground(Color.white);

            JTable jt = new JTable();

            DefaultTableModel dtm = new DefaultTableModel();

            Object col[] = new Object[rsmd.getColumnCount()];

            Object det[] = new Object[rsmd.getColumnCount()];
            for (int p = 1; p <= rsmd.getColumnCount(); p++) {
                col[p - 1] = rsmd.getColumnName(p);
            }

            jTable1.setModel(dtm);

            dtm.setColumnIdentifiers(col);

            while (rs.next()) {

                for (int j = 1; j <= rsmd.getColumnCount(); j++) {

                    if (rsmd.getColumnTypeName(j).equals("int")) {
                        det[j - 1] = rs.getInt(j);
                    } else {
                        det[j - 1] = rs.getString(j);
                    }

                }
                dtm.addRow(det);
            }

            jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            this.resizeColumnWidth(jTable1);

            //jTable1.setRowHeight(jTable1.getRowHeight() + 40);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Sorry ! Check Your Input", "Confirm Password", 2);
            System.out.println(e);

        }         // TODO add your handling code here:
    }//GEN-LAST:event_jButton16ActionPerformed
    public void resizeColumnWidth(JTable table) {

//table.setRowHeight(table.getRowHeight() + 40);
        table.setColumnSelectionAllowed(false);

        JTableHeader header = table.getTableHeader();

        header.setFont(new Font("arial", Font.BOLD, 14));

        header.setBackground(Color.yellow);

        header.setForeground(Color.blue);

        TableCellRenderer renderer = new EvenOddRenderer111();

        table.setDefaultRenderer(Object.class, renderer);

        table.setShowGrid(false);

        table.setIntercellSpacing(new Dimension(5, 15));

        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        table.setFont(new Font("ARIAL", Font.PLAIN, 12));

        for (int column = 0; column < table.getColumnCount(); column++) {
            TableColumn tableColumn = table.getColumnModel().getColumn(column);

            int preferredWidth = tableColumn.getMinWidth();

            int maxWidth = tableColumn.getMaxWidth();

            header = table.getTableHeader();

            TableColumnModel tcm = header.getColumnModel();

//header.setPreferredSize(new Dimension(100,50));
            for (int row = 0; row < table.getRowCount(); row++) {

                TableCellRenderer cellRenderer = table.getCellRenderer(row, column);

                Component c = table.prepareRenderer(cellRenderer, row, column);

                int width = c.getPreferredSize().width + table.getIntercellSpacing().width;

                preferredWidth = Math.max(preferredWidth, width);

                if (preferredWidth >= maxWidth) {

                    preferredWidth = maxWidth;

                    break;

                }

            }

            cell_spacing += 12;
            table.setRowHeight(50);
            TableColumn tc = tcm.getColumn(column);

            int w = 80;

            w += tc.getWidth();

            if (preferredWidth < tc.getWidth()) {
                tableColumn.setPreferredWidth(w);
            } else {
                tableColumn.setPreferredWidth(preferredWidth + 20);
            }

        }
        table_height += ((table.getRowHeight()) * (table.getRowCount()) + (cell_spacing));

    }


    private void contactnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_contactnoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_contactnoActionPerformed

    private void ccl1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ccl1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ccl1ActionPerformed

    private void jButton17MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton17MouseEntered
        // TODO add your handling code here:

    }//GEN-LAST:event_jButton17MouseEntered

    private void jButton17MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton17MouseExited
   
    }//GEN-LAST:event_jButton17MouseExited

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        int key = Integer.parseInt(ccl1.getText());
        setVisible(true);
        //String key="anjali";
        setLayout(null);

        Connection con = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/police", "root", "");

            PreparedStatement s = con.prepareStatement("SELECT * FROM commoncrime WHERE ID_NUMBER=?");
            s.setInt(1, key);
            ResultSet rs = s.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();

            getContentPane().setBackground(Color.white);

            JTable jt = new JTable();

            DefaultTableModel dtm = new DefaultTableModel();

            Object col[] = new Object[rsmd.getColumnCount()];

            Object det[] = new Object[rsmd.getColumnCount()];
            for (int p = 1; p <= rsmd.getColumnCount(); p++) {
                col[p - 1] = rsmd.getColumnName(p);
            }

            jTable2.setModel(dtm);

            dtm.setColumnIdentifiers(col);

            while (rs.next()) {

                for (int j = 1; j <= rsmd.getColumnCount(); j++) {

                    if (rsmd.getColumnTypeName(j).equals("int")) {
                        det[j - 1] = rs.getInt(j);
                    } else {
                        det[j - 1] = rs.getString(j);
                    }

                }
                dtm.addRow(det);
            }

            jTable2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            this.resizeColumnWidth(jTable2);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Sorry ! Check Your Input", "Confirm Password", 2);
            System.out.println(e);

        }         // TODO add your handling code here:
    }//GEN-LAST:event_jButton17ActionPerformed

    private void jButton18MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton18MouseEntered
        // TODO add your handling code here:
  //      jButton7.setBackground(new Color(255, 51, 102));
    }//GEN-LAST:event_jButton18MouseEntered

    private void jButton18MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton18MouseExited
        // TODO add your handling code here:
  ///      jButton7.setBackground(new Color(255, 153, 153));
    }//GEN-LAST:event_jButton18MouseExited

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        String key = ccl1.getText();
        setVisible(true);
         
        setLayout(null);

        Connection con = null;
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/police", "root", "");

            PreparedStatement s = con.prepareStatement("SELECT * FROM commoncrime WHERE  CRIMINAL_NAME =?");
            s.setString(1, key);
            ResultSet rs = s.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();

            getContentPane().setBackground(Color.white);

            JTable jt = new JTable();

            DefaultTableModel dtm = new DefaultTableModel();

            Object col[] = new Object[rsmd.getColumnCount()];

            Object det[] = new Object[rsmd.getColumnCount()];
            for (int p = 1; p <= rsmd.getColumnCount(); p++) {
                col[p - 1] = rsmd.getColumnName(p);
            }

            jTable2.setModel(dtm);

            dtm.setColumnIdentifiers(col);

            while (rs.next()) {

                for (int j = 1; j <= rsmd.getColumnCount(); j++) {

                    if (rsmd.getColumnTypeName(j).equals("int")) {
                        det[j - 1] = rs.getInt(j);
                    } else {
                        det[j - 1] = rs.getString(j);
                    }

                }
                dtm.addRow(det);
            }

            jTable2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            this.resizeColumnWidth(jTable2);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Sorry ! Check Your Input", "Confirm Password", 2);
            System.out.println(e);

        }         // TODO add your handling code here:
    }//GEN-LAST:event_jButton18ActionPerformed

    private void jButton19MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton19MouseEntered
        // TODO add your handling code here:

    }//GEN-LAST:event_jButton19MouseEntered

    private void jButton19MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton19MouseExited
        // TODO add your handling code here:
    
    }//GEN-LAST:event_jButton19MouseExited

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
     
        setVisible(true);
      
        setLayout(null);

        Connection con = null;
      

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/police", "root", "");

            PreparedStatement s = con.prepareStatement("SELECT * FROM commoncrime");
            //s.setString(1,key);
            ResultSet rs = s.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();

            getContentPane().setBackground(Color.white);

            JTable jt = new JTable();

            DefaultTableModel dtm = new DefaultTableModel();

            Object col[] = new Object[rsmd.getColumnCount()];

            Object det[] = new Object[rsmd.getColumnCount()];
            for (int p = 1; p <= rsmd.getColumnCount(); p++) {
                col[p - 1] = rsmd.getColumnName(p);
            }

            jTable2.setModel(dtm);

            dtm.setColumnIdentifiers(col);

            while (rs.next()) {

                for (int j = 1; j <= rsmd.getColumnCount(); j++) {

                    if (rsmd.getColumnTypeName(j).equals("int")) {
                        det[j - 1] = rs.getInt(j);
                    } else {
                        det[j - 1] = rs.getString(j);
                    }

                }
                dtm.addRow(det);
            }

            jTable2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            this.resizeColumnWidth(jTable2);

            //jTable1.setRowHeight(jTable1.getRowHeight() + 40);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Sorry ! Check Your Input", "Confirm Password", 2);
            System.out.println(e);

        }         // TODO add your handling code here:
    }//GEN-LAST:event_jButton19ActionPerformed

    private void mwlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mwlActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mwlActionPerformed

    private void jButton21MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton21MouseEntered
        // TODO add your handling code here:
        jButton4.setBackground(new Color(255, 51, 102));
    }//GEN-LAST:event_jButton21MouseEntered

    private void jButton21MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton21MouseExited
        // TODO add your handling code here:.
        jButton4.setBackground(new Color(255, 153, 153));
    }//GEN-LAST:event_jButton21MouseExited

    private void jButton21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton21ActionPerformed
        int key = Integer.parseInt(mwl.getText());
        setVisible(true);
      
        setLayout(null);

        Connection con = null;
       
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/police", "root", "");

            PreparedStatement s = con.prepareStatement("SELECT * FROM mostwanted WHERE ID_NUMBER=?");
            s.setInt(1, key);
            ResultSet rs = s.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();

            getContentPane().setBackground(Color.white);

            JTable jt = new JTable();

            DefaultTableModel dtm = new DefaultTableModel();

            Object col[] = new Object[rsmd.getColumnCount()];

            Object det[] = new Object[rsmd.getColumnCount()];
            for (int p = 1; p <= rsmd.getColumnCount(); p++) {
                col[p - 1] = rsmd.getColumnName(p);
            }

            jTable2.setModel(dtm);

            dtm.setColumnIdentifiers(col);

            while (rs.next()) {

                for (int j = 1; j <= rsmd.getColumnCount(); j++) {

                    if (rsmd.getColumnTypeName(j).equals("int")) {
                        det[j - 1] = rs.getInt(j);
                    } else {
                        det[j - 1] = rs.getString(j);
                    }

                }
                dtm.addRow(det);
            }

            jTable2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            this.resizeColumnWidth(jTable2);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Sorry ! Check Your Input", "Confirm Password", 2);
            System.out.println(e);

        }         // TODO add your handling code here:
    }//GEN-LAST:event_jButton21ActionPerformed

    private void jButton22MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton22MouseEntered
        // TODO add your handling code here:
        jButton8.setBackground(new Color(255, 51, 102));
    }//GEN-LAST:event_jButton22MouseEntered

    private void jButton22MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton22MouseExited
        // TODO add your handling code here:
        jButton8.setBackground(new Color(255, 153, 153));
    }//GEN-LAST:event_jButton22MouseExited

    private void jButton22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton22ActionPerformed
        String key = mwl.getText();
        setVisible(true);
       
        setLayout(null);

        Connection con = null;
      
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/police", "root", "");

            PreparedStatement s = con.prepareStatement("SELECT * FROM mostwanted where CRIMINAL_NAME=?");
            s.setString(1, key);
            ResultSet rs = s.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();

            getContentPane().setBackground(Color.white);

            JTable jt = new JTable();

            DefaultTableModel dtm = new DefaultTableModel();

            Object col[] = new Object[rsmd.getColumnCount()];

            Object det[] = new Object[rsmd.getColumnCount()];
            for (int p = 1; p <= rsmd.getColumnCount(); p++) {
                col[p - 1] = rsmd.getColumnName(p);
            }

            jTable2.setModel(dtm);

            dtm.setColumnIdentifiers(col);

            while (rs.next()) {

                for (int j = 1; j <= rsmd.getColumnCount(); j++) {

                    if (rsmd.getColumnTypeName(j).equals("int")) {
                        det[j - 1] = rs.getInt(j);
                    } else {
                        det[j - 1] = rs.getString(j);
                    }

                }
                dtm.addRow(det);
            }

            jTable2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            this.resizeColumnWidth(jTable2);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Sorry ! Check Your Input", "Confirm Password", 2);
            System.out.println(e);

        }         // TODO add your handling code here:
    }//GEN-LAST:event_jButton22ActionPerformed

    private void jButton23MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton23MouseEntered
  
    }//GEN-LAST:event_jButton23MouseEntered

    private void jButton23MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton23MouseExited
    
    }//GEN-LAST:event_jButton23MouseExited

    private void jButton23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton23ActionPerformed
        String key = firl.getText();
        setVisible(true);
        //String key="anjali";
        setLayout(null);

        Connection con = null;
  

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/police", "root", "");

            PreparedStatement s = con.prepareStatement("SELECT * FROM MOSTWANTED");
            //s.setString(1,key);
            ResultSet rs = s.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();

            getContentPane().setBackground(Color.white);

            JTable jt = new JTable();

            DefaultTableModel dtm = new DefaultTableModel();

            Object col[] = new Object[rsmd.getColumnCount()];

            Object det[] = new Object[rsmd.getColumnCount()];
            for (int p = 1; p <= rsmd.getColumnCount(); p++) {
                col[p - 1] = rsmd.getColumnName(p);
            }

            jTable2.setModel(dtm);

            dtm.setColumnIdentifiers(col);

            while (rs.next()) {

                for (int j = 1; j <= rsmd.getColumnCount(); j++) {

                    if (rsmd.getColumnTypeName(j).equals("int")) {
                        det[j - 1] = rs.getInt(j);
                    } else {
                        det[j - 1] = rs.getString(j);
                    }

                }
                dtm.addRow(det);
            }

            jTable2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            this.resizeColumnWidth(jTable2);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Sorry ! Check Your Input", "Confirm Password", 2);
            System.out.println(e);

        }         // TODO add your handling code here:
    }//GEN-LAST:event_jButton23ActionPerformed

    private void jButton25MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton25MouseEntered

    }//GEN-LAST:event_jButton25MouseEntered

    private void jButton25MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton25MouseExited
        // TODO add your handling code here:

    
    }//GEN-LAST:event_jButton25MouseExited

    private void jButton25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton25ActionPerformed

        int key = Integer.parseInt(firl.getText());
        
        setVisible(true);
     
        setLayout(null);

        Connection con = null;
      

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/police", "root", "");

            PreparedStatement s = con.prepareStatement("SELECT * FROM fir where FIR_ID =?");
            s.setInt(1, key);
            ResultSet rs = s.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();

            getContentPane().setBackground(Color.white);

            JTable jt = new JTable();

            DefaultTableModel dtm = new DefaultTableModel();

            Object col[] = new Object[rsmd.getColumnCount()];

            Object det[] = new Object[rsmd.getColumnCount()];
            for (int p = 1; p <= rsmd.getColumnCount(); p++) {
                col[p - 1] = rsmd.getColumnName(p);
            }

            jTable4.setModel(dtm);

            dtm.setColumnIdentifiers(col);

            while (rs.next()) {

                for (int j = 1; j <= rsmd.getColumnCount(); j++) {

                    if (rsmd.getColumnTypeName(j).equals("int")) {
                        det[j - 1] = rs.getInt(j);
                    } else {
                        det[j - 1] = rs.getString(j);
                    }

                }
                dtm.addRow(det);
            }

            jTable4.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            this.resizeColumnWidth(jTable4);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Sorry ! Check Your Input", "Confirm Password", 2);
            System.out.println(e);

        }
    }                                         

    private void jButton28MouseEntered(java.awt.event.MouseEvent evt) {                                       
 
    }                                      

    private void jButton28MouseExited(java.awt.event.MouseEvent evt) {                                      

    }                                     

    private void jButton28ActionPerformed(java.awt.event.ActionEvent evt) {                                          

        setVisible(true);
        setLayout(null);

        Connection con = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/police", "root", "");

            PreparedStatement s = con.prepareStatement("SELECT * FROM FIR;");
            //s.setString(1,key);
            ResultSet rs = s.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();

            getContentPane().setBackground(Color.orange);

            JTable jt = new JTable();

            DefaultTableModel dtm = new DefaultTableModel();

            Object col[] = new Object[rsmd.getColumnCount()];

            Object det[] = new Object[rsmd.getColumnCount()];
            for (int p = 1; p <= rsmd.getColumnCount(); p++) {
                col[p - 1] = rsmd.getColumnName(p);
            }

            jTable4.setModel(dtm);

            dtm.setColumnIdentifiers(col);

            while (rs.next()) {

                for (int j = 1; j <= rsmd.getColumnCount(); j++) {

                    if (rsmd.getColumnTypeName(j).equals("int")) {
                        det[j - 1] = rs.getInt(j);
                    } else {
                        det[j - 1] = rs.getString(j);
                    }

                }
                dtm.addRow(det);
            }

            jTable4.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            this.resizeColumnWidth(jTable4);

            //jTable1.setRowHeight(jTable1.getRowHeight() + 40);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Sorry ! Check Your Input", "Confirm Password", 2);
            System.out.println(e);
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_jButton25ActionPerformed

    private void jButton26MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton26MouseEntered
        // TODO add your handling code here:
       
    }//GEN-LAST:event_jButton26MouseEntered

    private void jButton26MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton26MouseExited
        // TODO add your handling code here:
   
    }//GEN-LAST:event_jButton26MouseExited

    private void jButton26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton26ActionPerformed
        String key = firl.getText();
        setVisible(true);
        //String key="anjali";
        setLayout(null);

        Connection con = null;
      

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/police", "root", "");

            PreparedStatement s = con.prepareStatement("SELECT * FROM fir where CRIMINAL_NAME =?");
            s.setString(1, key);
            ResultSet rs = s.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();

            getContentPane().setBackground(Color.white);

            JTable jt = new JTable();

            DefaultTableModel dtm = new DefaultTableModel();

            Object col[] = new Object[rsmd.getColumnCount()];

            Object det[] = new Object[rsmd.getColumnCount()];
            for (int p = 1; p <= rsmd.getColumnCount(); p++) {
                col[p - 1] = rsmd.getColumnName(p);
            }

            jTable4.setModel(dtm);

            dtm.setColumnIdentifiers(col);

            while (rs.next()) {

                for (int j = 1; j <= rsmd.getColumnCount(); j++) {

                    if (rsmd.getColumnTypeName(j).equals("int")) {
                        det[j - 1] = rs.getInt(j);
                    } else {
                        det[j - 1] = rs.getString(j);
                    }

                }
                dtm.addRow(det);
            }

            jTable4.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            this.resizeColumnWidth(jTable4);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Sorry ! Check Your Input", "Confirm Password", 2);
            System.out.println(e);

        }         // TODO add your handling code here:
    }//GEN-LAST:event_jButton26ActionPerformed

    private void jButton27MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton27MouseEntered
        // TODO add your handling code here:

    }//GEN-LAST:event_jButton27MouseEntered

    private void jButton27MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton27MouseExited
        // TODO add your handling code here:

    }//GEN-LAST:event_jButton27MouseExited

    private void jButton27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton27ActionPerformed
        // TODO add your handling code here:
        String key = firl.getText();
        setVisible(true);
     
        setLayout(null);

        Connection con = null;
      

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/police", "root", "");

            PreparedStatement s = con.prepareStatement("SELECT * FROM fir where VICTIM_NAME =?");
            s.setString(1, key);
            ResultSet rs = s.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();

            getContentPane().setBackground(Color.white);

            JTable jt = new JTable();

            DefaultTableModel dtm = new DefaultTableModel();

            Object col[] = new Object[rsmd.getColumnCount()];

            Object det[] = new Object[rsmd.getColumnCount()];
            for (int p = 1; p <= rsmd.getColumnCount(); p++) {
                col[p - 1] = rsmd.getColumnName(p);
            }

            jTable4.setModel(dtm);

            dtm.setColumnIdentifiers(col);

            while (rs.next()) {

                for (int j = 1; j <= rsmd.getColumnCount(); j++) {

                    if (rsmd.getColumnTypeName(j).equals("int")) {
                        det[j - 1] = rs.getInt(j);
                    } else {
                        det[j - 1] = rs.getString(j);
                    }

                }
                dtm.addRow(det);
            }

            jTable4.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            this.resizeColumnWidth(jTable4);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Sorry ! Check Your Input", "Confirm Password", 2);
            System.out.println(e);

        }
    }//GEN-LAST:event_jButton27ActionPerformed

    private void jButton29MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton29MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton29MouseEntered

    private void jButton29MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton29MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton29MouseExited

    private void jButton29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton29ActionPerformed
        // TODO add your handling code here:
    
        setVisible(true);
        setLayout(null);

        Connection con = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/police", "root", "");

            PreparedStatement s = con.prepareStatement("SELECT * FROM FIR;");
            //s.setString(1,key);
            ResultSet rs = s.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();

            getContentPane().setBackground(Color.orange);

            JTable jt = new JTable();

            DefaultTableModel dtm = new DefaultTableModel();

            Object col[] = new Object[rsmd.getColumnCount()];

            Object det[] = new Object[rsmd.getColumnCount()];
            for (int p = 1; p <= rsmd.getColumnCount(); p++) {
                col[p - 1] = rsmd.getColumnName(p);
            }

            jTable4.setModel(dtm);

            dtm.setColumnIdentifiers(col);

            while (rs.next()) {

                for (int j = 1; j <= rsmd.getColumnCount(); j++) {

                    if (rsmd.getColumnTypeName(j).equals("int")) {
                        det[j - 1] = rs.getInt(j);
                    } else {
                        det[j - 1] = rs.getString(j);
                    }

                }
                dtm.addRow(det);
            }

            jTable4.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            this.resizeColumnWidth(jTable4);

            //jTable1.setRowHeight(jTable1.getRowHeight() + 40);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Sorry ! Check Your Input", "Confirm Password", 2);
            System.out.println(e);
        }
    }//GEN-LAST:event_jButton29ActionPerformed

    private void colActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_colActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_colActionPerformed

    private void jButton30MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton30MouseEntered
        // TODO add your handling code here:
        //jButton3.setBackground(new Color(255, 51, 102));
    }//GEN-LAST:event_jButton30MouseEntered

    private void jButton30MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton30MouseExited
        // TODO add your handling code here:
      //  jButton3.setBackground(new Color(255, 153, 153));
    }//GEN-LAST:event_jButton30MouseExited

    private void jButton30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton30ActionPerformed
        int key = Integer.parseInt(col.getText());
        setVisible(true);
        //String key="anjali";
        setLayout(null);

        Connection con = null;
        /*
        PreparedStatement st;
        ResultSet rs;
        boolean username_exist = false;

        String query = "SELECT * FROM `useraccount` WHERE `username` = ?";

        try {

            st = loginconnection.connection().prepareStatement(query);
            st.setString(7, username);
            rs = st.executeQuery();

            if(rs.next())
            {
                username_exist = true;
                JOptionPane.showMessageDialog(null, "This Username is Already Taken, Choose Another One", "Username Failed", 2);
            }

        } catch (SQLException ex) {
            Logger.getLogger(CreateAccountPage1.class.getName()).log(Level.SEVERE, null, ex);
        }*/

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/police", "root", "");

            PreparedStatement s = con.prepareStatement("SELECT * FROM courtorder WHERE ID=?");
            s.setInt(1, key);
            ResultSet rs = s.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();

            getContentPane().setBackground(Color.white);

            JTable jt = new JTable();

            DefaultTableModel dtm = new DefaultTableModel();

            Object col[] = new Object[rsmd.getColumnCount()];

            Object det[] = new Object[rsmd.getColumnCount()];
            for (int p = 1; p <= rsmd.getColumnCount(); p++) {
                col[p - 1] = rsmd.getColumnName(p);
            }

            jTable2.setModel(dtm);

            dtm.setColumnIdentifiers(col);

            while (rs.next()) {

                for (int j = 1; j <= rsmd.getColumnCount(); j++) {

                    if (rsmd.getColumnTypeName(j).equals("int")) {
                        det[j - 1] = rs.getInt(j);
                    } else {
                        det[j - 1] = rs.getString(j);
                    }

                }
                dtm.addRow(det);
            }

            jTable2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            this.resizeColumnWidth(jTable2);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Sorry ! Check Your Input", "Confirm Password", 2);
            System.out.println(e);

        }         // TODO add your handling code here:
    }//GEN-LAST:event_jButton30ActionPerformed

    private void jButton31MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton31MouseEntered
        // TODO add your handling code here:
  
    }//GEN-LAST:event_jButton31MouseEntered

    private void jButton31MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton31MouseExited
      
    }//GEN-LAST:event_jButton31MouseExited

    private void jButton31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton31ActionPerformed
        String key = col.getText();
        setVisible(true);
        //String key="anjali";
        setLayout(null);

        Connection con = null;
        /*
        PreparedStatement st;
        ResultSet rs;
        boolean username_exist = false;

        String query = "SELECT * FROM `useraccount` WHERE `username` = ?";

        try {

            st = loginconnection.connection().prepareStatement(query);
            st.setString(7, username);
            rs = st.executeQuery();

            if(rs.next())
            {
                username_exist = true;
                JOptionPane.showMessageDialog(null, "This Username is Already Taken, Choose Another One", "Username Failed", 2);
            }

        } catch (SQLException ex) {
            Logger.getLogger(CreateAccountPage1.class.getName()).log(Level.SEVERE, null, ex);
        }*/

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/police", "root", "");

            PreparedStatement s = con.prepareStatement("SELECT * FROM COURTORDER");
            //s.setString(1,key);
            ResultSet rs = s.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();

            getContentPane().setBackground(Color.white);

            JTable jt = new JTable();

            DefaultTableModel dtm = new DefaultTableModel();

            Object col[] = new Object[rsmd.getColumnCount()];

            Object det[] = new Object[rsmd.getColumnCount()];
            for (int p = 1; p <= rsmd.getColumnCount(); p++) {
                col[p - 1] = rsmd.getColumnName(p);
            }

            jTable2.setModel(dtm);

            dtm.setColumnIdentifiers(col);

            while (rs.next()) {

                for (int j = 1; j <= rsmd.getColumnCount(); j++) {

                    if (rsmd.getColumnTypeName(j).equals("int")) {
                        det[j - 1] = rs.getInt(j);
                    } else {
                        det[j - 1] = rs.getString(j);
                    }

                }
                dtm.addRow(det);
            }

            jTable2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            this.resizeColumnWidth(jTable2);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Sorry ! Check Your Input", "Confirm Password", 2);
            System.out.println(e);

        }         // TODO add your handling code here:
    }//GEN-LAST:event_jButton31ActionPerformed

    private void loginbutton5FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_loginbutton5FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_loginbutton5FocusGained

    private void loginbutton5FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_loginbutton5FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_loginbutton5FocusLost

    private void loginbutton5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loginbutton5MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_loginbutton5MouseEntered

    private void loginbutton5MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loginbutton5MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_loginbutton5MouseExited

    private void loginbutton5MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loginbutton5MouseReleased
        // TODO add your handling code here:
  
    }//GEN-LAST:event_loginbutton5MouseReleased
boolean event_value_check(){
      String id=event_id.getText();
         String order1 = event.getText();
       String strDate  =((JTextField)event_date.getDateEditor().getUiComponent()).getText();
        if (order1.trim().equals("") || strDate.trim().equals("")
           || id.trim().equals("") )
        {
            JOptionPane.showMessageDialog(null, "One Or More Fields Are Empty", "Empty Fields", 2);
            return false;
        } // check if the two password are equals or not
    
        else {
            return true;
        }
}
    private void loginbutton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginbutton5ActionPerformed
         int id = Integer.parseInt(event_id.getText());
         String order1 = event.getText();
        boolean flag = false;
       String strDate  =((JTextField)event_date.getDateEditor().getUiComponent()).getText();

        if (event_value_check()){
        PreparedStatement ps;
        ResultSet rs;
      String registerUserQuery1 = "INSERT INTO  event VALUES(?,?,?);";
      try {

            ps = loginconnection.connection().prepareStatement(registerUserQuery1);
            ps.setInt(1, id);
            ps.setString(2, strDate);
            ps.setString(3, order1);
            ps.execute();

            flag = true;

            if (flag == true) {
                JOptionPane.showMessageDialog(null, "INFORMATION SAVED SUCCESSFULLY");
                     event_id.setText("");
        event.setText("");
            } else {
                JOptionPane.showMessageDialog(null, "Error: Check Your Information");
            }

        } catch (Exception ex) {
        }
        }
     rno();
    }//GEN-LAST:event_loginbutton5ActionPerformed

    private void G1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_G1FocusGained

    }//GEN-LAST:event_G1FocusGained

    private void G1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_G1FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_G1FocusLost

    private void G1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_G1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_G1MouseEntered

    private void G1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_G1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_G1MouseExited

    private void G1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_G1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_G1ActionPerformed

    private void G1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_G1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_G1KeyTyped

    private void G4FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_G4FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_G4FocusGained

    private void G4FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_G4FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_G4FocusLost

    private void G4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_G4MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_G4MouseEntered

    private void G4MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_G4MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_G4MouseExited

    private void G4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_G4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_G4ActionPerformed

    private void G4KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_G4KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_G4KeyTyped

    private void G3FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_G3FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_G3FocusGained

    private void G3FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_G3FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_G3FocusLost

    private void G3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_G3MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_G3MouseEntered

    private void G3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_G3MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_G3MouseExited

    private void G3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_G3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_G3ActionPerformed

    private void G3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_G3KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_G3KeyTyped

    private void G5FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_G5FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_G5FocusGained

    private void G5FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_G5FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_G5FocusLost

    private void G5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_G5MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_G5MouseEntered

    private void G5MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_G5MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_G5MouseExited

    private void G5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_G5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_G5ActionPerformed

    private void G5KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_G5KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_G5KeyTyped

    private void loginbutton6FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_loginbutton6FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_loginbutton6FocusGained

    private void loginbutton6FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_loginbutton6FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_loginbutton6FocusLost

    private void loginbutton6MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loginbutton6MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_loginbutton6MouseEntered

    private void loginbutton6MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loginbutton6MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_loginbutton6MouseExited

    private void loginbutton6MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loginbutton6MouseReleased
        // TODO add your handling code here:
        //   G1.setText("");
        G3.setText("");
        G4.setText("");
        G5.setText("");
        String s;

        s = this.srno("select ID_NUMBER from village order by ID_NUMBER DESC Limit 1;", "ID_NUMBER");
        G1.setText(s);
    }//GEN-LAST:event_loginbutton6MouseReleased

    private void loginbutton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginbutton6ActionPerformed
        // TODO add your handling code here:

        // TODO add your handling code here:
        String f1 = G1.getText();
        String f2 = (String) G2.getSelectedItem();
        String f3 = G3.getText();
        String f4 = G4.getText();

        String f6 = G5.getText();

        String registerUserQuery11 = "INSERT INTO villege VALUES(?,?,?,?,?);";

        int f11 = Integer.parseInt(f1);

        PreparedStatement ps;

        try {
            boolean flag = false;
            ps = loginconnection.connection().prepareStatement(registerUserQuery11);
            ps.setInt(1, f11);
            ps.setString(2, f2);
            ps.setString(3, f3);
            ps.setString(4, f4);
            ps.setString(5, f6);

            ps.execute();

            flag = true;
            if (flag == true) {
                JOptionPane.showMessageDialog(null, "Information saved");
            } else {
                JOptionPane.showMessageDialog(null, "Error: Check Your Information");
            }

        } catch (Exception ex) {
        }
        rno();
    }//GEN-LAST:event_loginbutton6ActionPerformed

    private void ccl2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ccl2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ccl2ActionPerformed

    private void jButton33MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton33MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton33MouseEntered

    private void jButton33MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton33MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton33MouseExited

    private void jButton33ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton33ActionPerformed
        int key = Integer.parseInt(ccl2.getText());
        setVisible(true);
        //String key="anjali";
        setLayout(null);

        Connection con = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/police", "root", "");

            PreparedStatement s = con.prepareStatement("SELECT * FROM villege WHERE ID=?");
            s.setInt(1, key);
            ResultSet rs = s.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();

            getContentPane().setBackground(Color.white);

            JTable jt = new JTable();

            DefaultTableModel dtm = new DefaultTableModel();

            Object col[] = new Object[rsmd.getColumnCount()];

            Object det[] = new Object[rsmd.getColumnCount()];
            for (int p = 1; p <= rsmd.getColumnCount(); p++) {
                col[p - 1] = rsmd.getColumnName(p);
            }

            jTable3.setModel(dtm);

            dtm.setColumnIdentifiers(col);

            while (rs.next()) {

                for (int j = 1; j <= rsmd.getColumnCount(); j++) {

                    if (rsmd.getColumnTypeName(j).equals("int")) {
                        det[j - 1] = rs.getInt(j);
                    } else {
                        det[j - 1] = rs.getString(j);
                    }

                }
                dtm.addRow(det);
            }

            jTable3.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            this.resizeColumnWidth(jTable3);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Sorry ! Check Your Input", "Confirm Password", 2);
            System.out.println(e);

        }         // TODO add your handling code here:
    }//GEN-LAST:event_jButton33ActionPerformed

    private void jButton34MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton34MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton34MouseEntered

    private void jButton34MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton34MouseExited
        // TODO add your handling code here:
        ;
    }//GEN-LAST:event_jButton34MouseExited

    private void jButton34ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton34ActionPerformed
        String key = ccl2.getText();
        setVisible(true);
        //String key="anjali";
        setLayout(null);

        Connection con = null;
     

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/police", "root", "");

            PreparedStatement s = con.prepareStatement("SELECT * FROM villege WHERE VILLAGE_NAME =?");
            s.setString(1, key);
            ResultSet rs = s.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();

            getContentPane().setBackground(Color.white);

            JTable jt = new JTable();

            DefaultTableModel dtm = new DefaultTableModel();

            Object col[] = new Object[rsmd.getColumnCount()];

            Object det[] = new Object[rsmd.getColumnCount()];
            for (int p = 1; p <= rsmd.getColumnCount(); p++) {
                col[p - 1] = rsmd.getColumnName(p);
            }

            jTable3.setModel(dtm);

            dtm.setColumnIdentifiers(col);

            while (rs.next()) {

                for (int j = 1; j <= rsmd.getColumnCount(); j++) {

                    if (rsmd.getColumnTypeName(j).equals("int")) {
                        det[j - 1] = rs.getInt(j);
                    } else {
                        det[j - 1] = rs.getString(j);
                    }

                }
                dtm.addRow(det);
            }

            jTable3.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            this.resizeColumnWidth(jTable3);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Sorry ! Check Your Input", "Confirm Password", 2);
            System.out.println(e);

        }         // TODO add your handling code here:
    }//GEN-LAST:event_jButton34ActionPerformed

    private void jButton35MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton35MouseEntered

    }//GEN-LAST:event_jButton35MouseEntered

    private void jButton35MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton35MouseExited
  
    }//GEN-LAST:event_jButton35MouseExited

    private void jButton35ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton35ActionPerformed
        String key = ccl2.getText();
        setVisible(true);
        //String key="anjali";
        setLayout(null);

        Connection con = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/police", "root", "");

            PreparedStatement s = con.prepareStatement("SELECT * FROM villege");
            //s.setString(1,key);
            ResultSet rs = s.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();

            getContentPane().setBackground(Color.white);

            JTable jt = new JTable();

            DefaultTableModel dtm = new DefaultTableModel();

            Object col[] = new Object[rsmd.getColumnCount()];

            Object det[] = new Object[rsmd.getColumnCount()];
            for (int p = 1; p <= rsmd.getColumnCount(); p++) {
                col[p - 1] = rsmd.getColumnName(p);
            }

            jTable3.setModel(dtm);

            dtm.setColumnIdentifiers(col);

            while (rs.next()) {

                for (int j = 1; j <= rsmd.getColumnCount(); j++) {

                    if (rsmd.getColumnTypeName(j).equals("int")) {
                        det[j - 1] = rs.getInt(j);
                    } else {
                        det[j - 1] = rs.getString(j);
                    }

                }
                dtm.addRow(det);
            }

            jTable3.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            this.resizeColumnWidth(jTable3);

            //jTable1.setRowHeight(jTable1.getRowHeight() + 40);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Sorry ! Check Your Input", "Confirm Password", 2);
            System.out.println(e);

        }         // TODO add your handling code here:
    }//GEN-LAST:event_jButton35ActionPerformed

    private void jButton38ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton38ActionPerformed
        // TODO add your handling code here:
           NewAccount d=new NewAccount();
           
                    d.setVisible(true);
                    d.pack();
                      d.setLocationRelativeTo(null);
                   this.dispose();
                    d.show();
    }//GEN-LAST:event_jButton38ActionPerformed

    private void jButton39ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton39ActionPerformed
        // TODO add your handling code here:
         LoginScreen d=new LoginScreen();
                    d.setVisible(true);
                   this.dispose();
                    d.show();
    }//GEN-LAST:event_jButton39ActionPerformed

    private void victim_nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_victim_nameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_victim_nameActionPerformed

    private void victim_nameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_victim_nameFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_victim_nameFocusLost

    private void victim_nameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_victim_nameFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_victim_nameFocusGained

    private void criminal_nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_criminal_nameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_criminal_nameActionPerformed

    private void criminal_nameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_criminal_nameFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_criminal_nameFocusLost

    private void criminal_nameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_criminal_nameFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_criminal_nameFocusGained

    private void crime_addressFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_crime_addressFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_crime_addressFocusLost

    private void crime_addressFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_crime_addressFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_crime_addressFocusGained

    private void investigation_officerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_investigation_officerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_investigation_officerActionPerformed
  void fir_refresh(){
    crime_time.setText("");
    crime_address.setText("");
    complainer_name.setText("");
    complianer_details.setText("");
    victim_name.setText("");
    victim_details.setText("");
    criminal_name.setText("");
    criminal_details.setText("");
    compliant.setText("");
    
            }

   boolean fir_value_check() {
       String f_id1=f_id.getText();
     String crime_time1 = crime_time.getText();
        String crime_date1 =((JTextField)crime_date.getDateEditor().getUiComponent()).getText();
        String crime_address1 = crime_address.getText();
         String complainer_name1 = complainer_name.getText();
         String complianer_details1 = complianer_details.getText();
         String victim_name1 = victim_name.getText();
         String victim_details1 = victim_details.getText();
         String criminal_name1 = criminal_name.getText();
         String criminal_details1 = criminal_details.getText();
         String compliant1=compliant.getText();
         String investigation_officer1 = (String) investigation_officer.getSelectedItem();

        // check empty fields
        if (f_id1.trim().equals("") || crime_time1.trim().equals("")
           || crime_date1.trim().equals("") || crime_address1.trim().equals("")
           || complainer_name1.trim().equals("")|| complianer_details1.trim().equals("") || victim_name1.trim().equals("")
                || victim_details1.trim().equals("") || criminal_name1.trim().equals("")
                || criminal_details1.trim().equals("") || compliant1.trim().equals("")
                || investigation_officer1.trim().equals(""))
        {
            JOptionPane.showMessageDialog(null, "One Or More Fields Are Empty", "Empty Fields", 2);
            return false;
        } // check if the two password are equals or not
    
        else {
            return true;
        }
    }
   void temp_value_table(){
           int f_id1 = Integer.parseInt(f_id.getText());
        String crime_time1 = crime_time.getText();
        String crime_date1 =((JTextField)crime_date.getDateEditor().getUiComponent()).getText();
        String crime_address1 = crime_address.getText();
         String complainer_name1 = complainer_name.getText();
         String complianer_details1 = complianer_details.getText();
         String victim_name1 = victim_name.getText();
         String victim_details1 = victim_details.getText();
         String criminal_name1 = criminal_name.getText();
         String criminal_details1 = criminal_details.getText();
         String compliant1=compliant.getText();
         String investigation_officer1 = (String) investigation_officer.getSelectedItem();
 
        PreparedStatement ps;

        String Query = "INSERT INTO current_fir VALUES(?,?,?,?,?,?,?,?,?,?,?,?);";
        try {
            
            ps = loginconnection.connection().prepareStatement(Query);
            ps.setInt(1, f_id1);
            ps.setString(2, crime_time1);
            ps.setString(3, crime_date1);
            ps.setString(4, crime_address1);
            ps.setString(5, complainer_name1);
            ps.setString(6, complianer_details1);
            ps.setString(7, victim_name1);
            ps.setString(8, victim_details1);
            ps.setString(9, criminal_name1);
            ps.setString(10, criminal_details1);
            ps.setString(11, compliant1);
            ps.setString(12, investigation_officer1);
            ps.execute();

           
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
     try{
        InputStream in=new FileInputStream(new File("F:\\JAVA\\Java Project\\PoliceDepartment\\src\\Reports\\Current_fir.jrxml"));
        JasperDesign jd=JRXmlLoader.load(in);
       Connection con=null;
         
           Class.forName("com.mysql.cj.jdbc.Driver"); 
         con=DriverManager.getConnection("jdbc:mysql://localhost:3306/police","root","");
            
           JasperReport  jr = JasperCompileManager.compileReport(jd);
           HashMap para=new HashMap();
          JasperPrint j=JasperFillManager.fillReport(jr, para,con);
                JasperViewer.viewReport(j);
 }catch(Exception e){System.out.println(e);}
        
                  PreparedStatement ps1;

        String Query1 = "delete from current_fir;";
        try {
            
            ps = loginconnection.connection().prepareStatement(Query1);
  
            ps.execute();
        }catch(Exception e){
          
        }   
       
   }
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
    
        int f_id1 = Integer.parseInt(f_id.getText());
        String crime_time1 = crime_time.getText();
        String crime_date1 =((JTextField)crime_date.getDateEditor().getUiComponent()).getText();
        String crime_address1 = crime_address.getText();
         String complainer_name1 = complainer_name.getText();
         String complianer_details1 = complianer_details.getText();
         String victim_name1 = victim_name.getText();
         String victim_details1 = victim_details.getText();
         String criminal_name1 = criminal_name.getText();
         String criminal_details1 = criminal_details.getText();
         String compliant1=compliant.getText();
         String investigation_officer1 = (String) investigation_officer.getSelectedItem();
        if (fir_value_check()){
        boolean flag = false;
        PreparedStatement ps;

        String Query = "INSERT INTO fir VALUES(?,?,?,?,?,?,?,?,?,?,?,?);";
        try {

            ps = loginconnection.connection().prepareStatement(Query);
            ps.setInt(1, f_id1);
            ps.setString(2, crime_time1);
            ps.setString(3, crime_date1);
            ps.setString(4, crime_address1);
            ps.setString(5, complainer_name1);
            ps.setString(6, complianer_details1);
            ps.setString(7, victim_name1);
            ps.setString(8, victim_details1);
            ps.setString(9, criminal_name1);
            ps.setString(10, criminal_details1);
            ps.setString(11, compliant1);
            ps.setString(12, investigation_officer1);
            ps.execute();

            flag = true;
            if (flag == true) {
                
                JOptionPane.showMessageDialog(null, "Information saved");
                temp_value_table();
                fir_refresh();
            } else {
                JOptionPane.showMessageDialog(null, "Error: Check Your Information");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        temp();
        }
        
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton4MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4MouseReleased

        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4MouseReleased

    private void crime_timeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_crime_timeFocusLost
        // TODO add your handling code here:
        //setlost(time1,"enter the time");
    }//GEN-LAST:event_crime_timeFocusLost

    private void crime_timeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_crime_timeFocusGained
        // TODO add your handling code here:
        //setgot(time1,"enter the time");
    }//GEN-LAST:event_crime_timeFocusGained

    private void f_idFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_f_idFocusLost
        // TODO add your handling code here:
        //setlost(firid1,"enter fir number");
    }//GEN-LAST:event_f_idFocusLost

    private void f_idFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_f_idFocusGained
        // TODO add your handling code here:
        //setgot(firid1,"enter fir number");
    }//GEN-LAST:event_f_idFocusGained

    private void complainer_nameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_complainer_nameFocusLost
        // TODO add your handling code here:
        //setlost( complainername1,"enter complainer name");
    }//GEN-LAST:event_complainer_nameFocusLost

    private void complainer_nameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_complainer_nameFocusGained
        // TODO add your handling code here:
        //setgot( complainername1,"enter complainer name");
    }//GEN-LAST:event_complainer_nameFocusGained

    private void jButton40MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton40MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton40MouseEntered

    private void jButton40MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton40MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton40MouseExited

    private void jButton40ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton40ActionPerformed
        // TODO add your handling code here:
           // TODO add your handling code here:
        try{
        InputStream in=new FileInputStream(new File("F:\\JAVA\\Java Project\\PoliceDepartment\\src\\Reports\\All_Fir_Records.jrxml"));
        JasperDesign jd=JRXmlLoader.load(in);
       Connection con=null;
         
           Class.forName("com.mysql.cj.jdbc.Driver"); 
         con=DriverManager.getConnection("jdbc:mysql://localhost:3306/police","root","");
            
           JasperReport  jr = JasperCompileManager.compileReport(jd);
           HashMap para=new HashMap();
          JasperPrint j=JasperFillManager.fillReport(jr, para,con);
                JasperViewer.viewReport(j);
 }catch(Exception e){System.out.println(e);}
    }//GEN-LAST:event_jButton40ActionPerformed

    private void common_crime_nameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_common_crime_nameFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_common_crime_nameFocusGained

    private void common_crime_nameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_common_crime_nameFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_common_crime_nameFocusLost

    private void common_crime_nameMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_common_crime_nameMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_common_crime_nameMouseEntered

    private void common_crime_nameMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_common_crime_nameMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_common_crime_nameMouseExited

    private void common_crime_nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_common_crime_nameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_common_crime_nameActionPerformed

    private void loginbutton2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_loginbutton2FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_loginbutton2FocusGained

    private void loginbutton2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_loginbutton2FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_loginbutton2FocusLost

    private void loginbutton2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loginbutton2MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_loginbutton2MouseEntered

    private void loginbutton2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loginbutton2MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_loginbutton2MouseExited

    private void loginbutton2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loginbutton2MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_loginbutton2MouseReleased

    private void loginbutton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginbutton2ActionPerformed
        // TODO add your handling code here:
              try{
        InputStream in=new FileInputStream(new File("F:\\JAVA\\Java Project\\PoliceDepartment\\src\\Reports\\Mostwanted_Crimial.jrxml"));
        JasperDesign jd=JRXmlLoader.load(in);
       Connection con=null;
         
           Class.forName("com.mysql.cj.jdbc.Driver"); 
         con=DriverManager.getConnection("jdbc:mysql://localhost:3306/police","root","");
            
           JasperReport  jr = JasperCompileManager.compileReport(jd);
           HashMap para=new HashMap();
          JasperPrint j=JasperFillManager.fillReport(jr, para,con);
                JasperViewer.viewReport(j);
 }catch(Exception e){System.out.println(e);}
    }//GEN-LAST:event_loginbutton2ActionPerformed

    private void loginbutton7FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_loginbutton7FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_loginbutton7FocusGained

    private void loginbutton7FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_loginbutton7FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_loginbutton7FocusLost

    private void loginbutton7MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loginbutton7MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_loginbutton7MouseEntered

    private void loginbutton7MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loginbutton7MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_loginbutton7MouseExited

    private void loginbutton7MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loginbutton7MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_loginbutton7MouseReleased

    private void loginbutton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginbutton7ActionPerformed
        // TODO add your handling code here:
                    try{
        InputStream in=new FileInputStream(new File("F:\\JAVA\\Java Project\\PoliceDepartment\\src\\Reports\\CourtOrder.jrxml"));
        JasperDesign jd=JRXmlLoader.load(in);
       Connection con=null;
         
           Class.forName("com.mysql.cj.jdbc.Driver"); 
         con=DriverManager.getConnection("jdbc:mysql://localhost:3306/police","root","");
            
           JasperReport  jr = JasperCompileManager.compileReport(jd);
           HashMap para=new HashMap();
          JasperPrint j=JasperFillManager.fillReport(jr, para,con);
                JasperViewer.viewReport(j);
 }catch(Exception e){System.out.println(e);}
    }//GEN-LAST:event_loginbutton7ActionPerformed

    private void loginbutton8FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_loginbutton8FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_loginbutton8FocusGained

    private void loginbutton8FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_loginbutton8FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_loginbutton8FocusLost

    private void loginbutton8MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loginbutton8MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_loginbutton8MouseEntered

    private void loginbutton8MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loginbutton8MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_loginbutton8MouseExited

    private void loginbutton8MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loginbutton8MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_loginbutton8MouseReleased

    private void loginbutton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginbutton8ActionPerformed
          try{
        InputStream in=new FileInputStream(new File("F:\\JAVA\\Java Project\\PoliceDepartment\\src\\Reports\\CommonCrime.jrxml"));
        JasperDesign jd=JRXmlLoader.load(in);
       Connection con=null;
         
           Class.forName("com.mysql.cj.jdbc.Driver"); 
         con=DriverManager.getConnection("jdbc:mysql://localhost:3306/police","root","");
            
           JasperReport  jr = JasperCompileManager.compileReport(jd);
           HashMap para=new HashMap();
          JasperPrint j=JasperFillManager.fillReport(jr, para,con);
                JasperViewer.viewReport(j);
 }catch(Exception e){System.out.println(e);}
    }//GEN-LAST:event_loginbutton8ActionPerformed

    private void col1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_col1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_col1ActionPerformed

    private void jButton32MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton32MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton32MouseEntered

    private void jButton32MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton32MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton32MouseExited

    private void jButton32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton32ActionPerformed
        // TODO add your handling code here:
         int key = Integer.parseInt(col1.getText());
        setVisible(true);
        //String key="anjali";
        setLayout(null);

        Connection con = null;
        /*
        PreparedStatement st;
        ResultSet rs;
        boolean username_exist = false;

        String query = "SELECT * FROM `useraccount` WHERE `username` = ?";

        try {

            st = loginconnection.connection().prepareStatement(query);
            st.setString(7, username);
            rs = st.executeQuery();

            if(rs.next())
            {
                username_exist = true;
                JOptionPane.showMessageDialog(null, "This Username is Already Taken, Choose Another One", "Username Failed", 2);
            }

        } catch (SQLException ex) {
            Logger.getLogger(CreateAccountPage1.class.getName()).log(Level.SEVERE, null, ex);
        }*/

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/police", "root", "");

            PreparedStatement s = con.prepareStatement("SELECT * FROM event WHERE ID=?");
            s.setInt(1, key);
            ResultSet rs = s.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();

            getContentPane().setBackground(Color.white);

            JTable jt = new JTable();

            DefaultTableModel dtm = new DefaultTableModel();

            Object col[] = new Object[rsmd.getColumnCount()];

            Object det[] = new Object[rsmd.getColumnCount()];
            for (int p = 1; p <= rsmd.getColumnCount(); p++) {
                col[p - 1] = rsmd.getColumnName(p);
            }

            jTable2.setModel(dtm);

            dtm.setColumnIdentifiers(col);

            while (rs.next()) {

                for (int j = 1; j <= rsmd.getColumnCount(); j++) {

                    if (rsmd.getColumnTypeName(j).equals("int")) {
                        det[j - 1] = rs.getInt(j);
                    } else {
                        det[j - 1] = rs.getString(j);
                    }

                }
                dtm.addRow(det);
            }

            jTable2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            this.resizeColumnWidth(jTable2);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Sorry ! Check Your Input", "Confirm Password", 2);
            System.out.println(e);

        }   
    }//GEN-LAST:event_jButton32ActionPerformed

    private void jButton36MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton36MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton36MouseEntered

    private void jButton36MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton36MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton36MouseExited

    private void jButton36ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton36ActionPerformed
        // TODO add your handling code here:
          String key = col.getText();
        setVisible(true);
        //String key="anjali";
        setLayout(null);

        Connection con = null;
        /*
        PreparedStatement st;
        ResultSet rs;
        boolean username_exist = false;

        String query = "SELECT * FROM `useraccount` WHERE `username` = ?";

        try {

            st = loginconnection.connection().prepareStatement(query);
            st.setString(7, username);
            rs = st.executeQuery();

            if(rs.next())
            {
                username_exist = true;
                JOptionPane.showMessageDialog(null, "This Username is Already Taken, Choose Another One", "Username Failed", 2);
            }

        } catch (SQLException ex) {
            Logger.getLogger(CreateAccountPage1.class.getName()).log(Level.SEVERE, null, ex);
        }*/

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/police", "root", "");

            PreparedStatement s = con.prepareStatement("SELECT * FROM event");
            //s.setString(1,key);
            ResultSet rs = s.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();

            getContentPane().setBackground(Color.white);

            JTable jt = new JTable();

            DefaultTableModel dtm = new DefaultTableModel();

            Object col[] = new Object[rsmd.getColumnCount()];

            Object det[] = new Object[rsmd.getColumnCount()];
            for (int p = 1; p <= rsmd.getColumnCount(); p++) {
                col[p - 1] = rsmd.getColumnName(p);
            }

            jTable2.setModel(dtm);

            dtm.setColumnIdentifiers(col);

            while (rs.next()) {

                for (int j = 1; j <= rsmd.getColumnCount(); j++) {

                    if (rsmd.getColumnTypeName(j).equals("int")) {
                        det[j - 1] = rs.getInt(j);
                    } else {
                        det[j - 1] = rs.getString(j);
                    }

                }
                dtm.addRow(det);
            }

            jTable2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            this.resizeColumnWidth(jTable2);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Sorry ! Check Your Input", "Confirm Password", 2);
            System.out.println(e);

        }
    }//GEN-LAST:event_jButton36ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DashBoard1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DashBoard1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DashBoard1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DashBoard1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DashBoard1().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel FirDetails;
    private javax.swing.JTextField G1;
    private javax.swing.JComboBox<String> G2;
    private javax.swing.JTextField G3;
    private javax.swing.JTextField G4;
    private javax.swing.JTextField G5;
    private javax.swing.JTextField ccl;
    private javax.swing.JTextField ccl1;
    private javax.swing.JTextField ccl2;
    private javax.swing.JTextField col;
    private javax.swing.JTextField col1;
    private javax.swing.JTextArea common_crime;
    private javax.swing.JComboBox<String> common_crime_age;
    private com.toedter.calendar.JDateChooser common_crime_date;
    private javax.swing.JComboBox<String> common_crime_dutyofficer;
    private javax.swing.JTextField common_crime_id;
    private javax.swing.JTextField common_crime_name;
    private javax.swing.JTextField common_crime_time;
    private javax.swing.JTextField complainer_name;
    private javax.swing.JTextArea complianer_details;
    private javax.swing.JTextArea compliant;
    private javax.swing.JTextField contactno;
    private com.toedter.calendar.JDateChooser court_date;
    private javax.swing.JTextField court_id;
    private javax.swing.JTextField crime_address;
    private com.toedter.calendar.JDateChooser crime_date;
    private javax.swing.JTextField crime_time;
    private javax.swing.JTextArea criminal_details;
    private javax.swing.JTextField criminal_name;
    private javax.swing.JComboBox<String> currentpost;
    private javax.swing.JLabel date;
    private javax.swing.JTextArea event;
    private com.toedter.calendar.JDateChooser event_date;
    private javax.swing.JTextField event_id;
    private javax.swing.JTextField f_id;
    private javax.swing.JRadioButton female;
    private javax.swing.JRadioButton female1;
    private javax.swing.JRadioButton female2;
    private javax.swing.JTextField firl;
    private javax.swing.JTextField idno1;
    private javax.swing.JLabel imagepath;
    private javax.swing.JLabel img4;
    private javax.swing.JComboBox<String> investigation_officer;
    private javax.swing.JLabel j13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton25;
    private javax.swing.JButton jButton26;
    private javax.swing.JButton jButton27;
    private javax.swing.JButton jButton29;
    private javax.swing.JButton jButton30;
    private javax.swing.JButton jButton31;
    private javax.swing.JButton jButton32;
    private javax.swing.JButton jButton33;
    private javax.swing.JButton jButton34;
    private javax.swing.JButton jButton35;
    private javax.swing.JButton jButton36;
    private javax.swing.JButton jButton38;
    private javax.swing.JButton jButton39;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton40;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel34;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JTabbedPane jTabbedPane4;
    private javax.swing.JTabbedPane jTabbedPane5;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    private javax.swing.JScrollPane k;
    private javax.swing.JScrollPane k1;
    private javax.swing.JButton loginbutton;
    private javax.swing.JButton loginbutton1;
    private javax.swing.JButton loginbutton2;
    private javax.swing.JButton loginbutton3;
    private javax.swing.JButton loginbutton4;
    private javax.swing.JButton loginbutton5;
    private javax.swing.JButton loginbutton6;
    private javax.swing.JButton loginbutton7;
    private javax.swing.JButton loginbutton8;
    private javax.swing.JRadioButton male;
    private javax.swing.JRadioButton male1;
    private javax.swing.JRadioButton male2;
    private javax.swing.JComboBox<String> mostwanted_age;
    private javax.swing.JTextArea mostwanted_crime_discription;
    private javax.swing.JTextArea mostwanted_criminal_detail;
    private javax.swing.JTextField mostwanted_id;
    private javax.swing.JTextField mostwanted_name;
    private javax.swing.JComboBox<String> mostwanted_officer;
    private javax.swing.JComboBox<String> mostwanted_section;
    private javax.swing.JTextField mwl;
    private javax.swing.JTextField name;
    private javax.swing.JTextArea order;
    public static javax.swing.JLabel pic;
    private javax.swing.JTextField qulification;
    private com.toedter.calendar.JDateChooser staff_date;
    private javax.swing.JLabel staff_image;
    private javax.swing.JLabel time;
    private javax.swing.JTextArea victim_details;
    private javax.swing.JTextField victim_name;
    // End of variables declaration//GEN-END:variables

    // private Icon ResizeImage(String path, JLabel img1) {
    // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    // }
}
