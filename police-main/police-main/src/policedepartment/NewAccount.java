/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package policedepartment;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

class MarqueeLabell extends JLabel {

    public static int LEFT_TO_RIGHT = 1;
    public static int RIGHT_TO_LEFT = 2;

    String Text;
    int Option;
    int Speed;

    public MarqueeLabell(String Text, int Option, int Speed) {

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

/**
 *
 * @author Gokul
 */
public class NewAccount extends javax.swing.JFrame {

    /**
     * Creates new form NewAccount
     */
    public NewAccount() {
        initComponents();

        ButtonGroup bg = new ButtonGroup();
        bg.add(male);
        bg.add(female);

        jLabel1.setIcon(img(jLabel1, "/images/ab.png"));
        l4.setIcon(img(l4, "/images/i20.png"));
        srno();
        n();
    }

    void srno() {
        String s;

        s = this.srno("select id from useraccount order by id DESC Limit 1;", "id");
        idno.setText(s);
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

    ImageIcon ResizeImage(String imagepath) {
        ImageIcon myimage;
        myimage = new ImageIcon(imagepath);
        Image img1;
        img1 = myimage.getImage();
        Image img2 = img1.getScaledInstance(img.getWidth(), img.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon iw = new ImageIcon(img2);
        return iw;

    }

    void n() {
        String pp = null;
        try {
            Statement ps = loginconnection.connection().createStatement();
            ResultSet rs = ps.executeQuery("select name from policestaff;");
            while (rs.next()) {

                nname.addItem(rs.getString(1));
            }
        } catch (Exception e) {
        }
    }

    public boolean checkUsername1(String username) {

        PreparedStatement st;
        ResultSet rs;
        boolean username_exist = false;

        String query = "SELECT * FROM `useraccount` WHERE `username` = ?";

        try {

            st = loginconnection.connection().prepareStatement(query);
            st.setString(1, username);
            rs = st.executeQuery();

            if (rs.next()) {
                username_exist = true;
                // JOptionPane.showMessageDialog(null, "This Username is Already Taken, Choose Another One", "Username Failed", 2);
            }

        } catch (SQLException ex) {
            // Logger.getLogger(NewAccount_Page.class.getName()).log(Level.SEVERE, null, ex);
        }

        return username_exist;
    }

    public boolean checkUsername(String username) {

        PreparedStatement st;
        ResultSet rs;
        boolean username_exist = false;

        String query = "SELECT * FROM `useraccount` WHERE `username` = ?";

        try {

            st = loginconnection.connection().prepareStatement(query);
            st.setString(1, username);
            rs = st.executeQuery();

            if (rs.next()) {
                username_exist = true;
                JOptionPane.showMessageDialog(null, "This Username is Already Taken, Choose Another One", "Username Failed", 2);
            }

        } catch (SQLException ex) {
            //   Logger.getLogger(NewAccount_Page.class.getName()).log(Level.SEVERE, null, ex);
        }

        return username_exist;
    }

    public boolean verifyFields() {
        String idn = idno.getText();
        // String uname = name.getText();
        String phone = contactno.getText();
        String user = username.getText();
        String pass1 = String.valueOf(password.getPassword());
        String pass2 = String.valueOf(confirmpassword.getPassword());

        // check empty fields
        if (idn.trim().equals("") || phone.trim().equals("")
                || pass1.trim().equals("") || pass2.trim().equals("") || user.trim().equals("")) {
            JOptionPane.showMessageDialog(null, "One Or More Fields Are Empty", "Empty Fields", 2);
            return false;
        } // check if the two password are equals or not
        else if (!pass1.equals(pass2)) {
            JOptionPane.showMessageDialog(null, "Password Doesn't Match", "Confirm Password", 2);
            return false;
        } // if everything is ok
        else {
            return true;
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        contactno = new javax.swing.JTextField();
        username = new javax.swing.JTextField();
        idno = new javax.swing.JTextField();
        female = new javax.swing.JRadioButton();
        male = new javax.swing.JRadioButton();
        confirmpassword = new javax.swing.JPasswordField();
        password = new javax.swing.JPasswordField();
        jButton1 = new javax.swing.JButton();
        imagepath = new javax.swing.JLabel();
        loginbutton = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        img = new javax.swing.JLabel();
        currentpost = new javax.swing.JComboBox<>();
        nname = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        idno1 = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        loginbutton5 = new javax.swing.JButton();
        user = new javax.swing.JTextField();
        password1 = new javax.swing.JPasswordField();
        newpassword = new javax.swing.JPasswordField();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        loginbutton4 = new javax.swing.JButton();
        username4 = new javax.swing.JTextField();
        password4 = new javax.swing.JPasswordField();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel2 = new MarqueeLabell("Warning !!! Don't create the new account ,Whose account is already exist..           Don't share account password .....          Don't misuse the someone details...               ",MarqueeLabel.RIGHT_TO_LEFT,10);
        loginbutton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        l4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setLayout(null);

        jTabbedPane2.setBackground(new java.awt.Color(0, 102, 204));
        jTabbedPane2.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N

        jPanel2.setBackground(new java.awt.Color(0, 102, 204));
        jPanel2.setLayout(null);

        jPanel6.setBackground(new java.awt.Color(0, 102, 204));
        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        jPanel6.setName("pa"); // NOI18N
        jPanel6.setLayout(null);

        jLabel7.setFont(new java.awt.Font("Adobe Caslon Pro Bold", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Username:");
        jPanel6.add(jLabel7);
        jLabel7.setBounds(70, 240, 90, 26);

        jLabel8.setFont(new java.awt.Font("Adobe Caslon Pro Bold", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Confirm Password:");
        jPanel6.add(jLabel8);
        jLabel8.setBounds(440, 240, 150, 35);

        jLabel10.setFont(new java.awt.Font("Adobe Caslon Pro Bold", 0, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Gender:");
        jPanel6.add(jLabel10);
        jLabel10.setBounds(502, 140, 70, 29);

        jLabel13.setBackground(new java.awt.Color(255, 255, 255));
        jLabel13.setFont(new java.awt.Font("Adobe Caslon Pro Bold", 0, 48)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Create New Account");
        jLabel13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 3));
        jPanel6.add(jLabel13);
        jLabel13.setBounds(230, 0, 440, 64);

        contactno.setFont(new java.awt.Font("Verdana", 3, 14)); // NOI18N
        contactno.setHorizontalAlignment(JTextField.CENTER);
        contactno.setToolTipText("Enter Contact Number");
        contactno.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
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
        contactno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                contactnoKeyTyped(evt);
            }
        });
        jPanel6.add(contactno);
        contactno.setBounds(180, 190, 246, 20);

        username.setFont(new java.awt.Font("Verdana", 3, 14)); // NOI18N
        username.setHorizontalAlignment(JTextField.CENTER);
        username.setToolTipText("Enter Username");
        username.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        username.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                usernameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                usernameFocusLost(evt);
            }
        });
        username.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                usernameMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                usernameMouseExited(evt);
            }
        });
        username.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usernameActionPerformed(evt);
            }
        });
        jPanel6.add(username);
        username.setBounds(180, 240, 246, 20);

        idno.setFont(new java.awt.Font("Verdana", 3, 14)); // NOI18N
        idno.setHorizontalAlignment(JTextField.CENTER);
        idno.setToolTipText("Enter ID no");
        idno.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        idno.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        idno.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                idnoFocusLost(evt);
            }
            public void focusGained(java.awt.event.FocusEvent evt) {
                idnoFocusGained(evt);
            }
        });
        idno.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                idnoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                idnoMouseExited(evt);
            }
        });
        idno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idnoActionPerformed(evt);
            }
        });
        idno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                idnoKeyTyped(evt);
            }
        });
        jPanel6.add(idno);
        idno.setBounds(180, 90, 246, 20);

        female.setFont(new java.awt.Font("Adobe Caslon Pro Bold", 0, 18)); // NOI18N
        female.setForeground(new java.awt.Color(255, 255, 255));
        female.setText("Female");
        female.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                femaleMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                femaleMouseExited(evt);
            }
        });
        female.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                femaleActionPerformed(evt);
            }
        });
        jPanel6.add(female);
        female.setBounds(740, 140, 95, 29);

        male.setFont(new java.awt.Font("Adobe Caslon Pro Bold", 0, 18)); // NOI18N
        male.setForeground(new java.awt.Color(255, 255, 255));
        male.setText("Male");
        male.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                maleMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                maleMouseExited(evt);
            }
        });
        jPanel6.add(male);
        male.setBounds(590, 140, 99, 29);

        confirmpassword.setHorizontalAlignment(JTextField.CENTER);
        confirmpassword.setToolTipText("Confirm Your Password");
        confirmpassword.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        confirmpassword.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                confirmpasswordFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                confirmpasswordFocusLost(evt);
            }
        });
        confirmpassword.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                confirmpasswordMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                confirmpasswordMouseExited(evt);
            }
        });
        confirmpassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmpasswordActionPerformed(evt);
            }
        });
        jPanel6.add(confirmpassword);
        confirmpassword.setBounds(590, 240, 253, 22);

        password.setHorizontalAlignment(JTextField.CENTER);
        password.setToolTipText("Enter Password");
        password.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        password.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                passwordFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                passwordFocusLost(evt);
            }
        });
        password.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                passwordMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                passwordMouseExited(evt);
            }
        });
        jPanel6.add(password);
        password.setBounds(590, 190, 253, 22);

        jButton1.setBackground(new java.awt.Color(0, 255, 0));
        jButton1.setText("Upload Image");
        jButton1.setToolTipText("Upload Your Image");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton1);
        jButton1.setBounds(640, 410, 143, 29);

        imagepath.setText("image path");
        jPanel6.add(imagepath);
        imagepath.setBounds(660, 460, 80, 21);

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
        jPanel6.add(loginbutton);
        loginbutton.setBounds(400, 400, 140, 33);

        jLabel16.setFont(new java.awt.Font("Adobe Caslon Pro Bold", 0, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Password:");
        jPanel6.add(jLabel16);
        jLabel16.setBounds(500, 190, 80, 19);

        img.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jPanel6.add(img);
        img.setBounds(650, 270, 110, 130);

        currentpost.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        currentpost.setMaximumRowCount(20);
        currentpost.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select the Post......", "Director General of Police", "Additional Director General of Police", "Inspector General of Police", "Deputy Inspector General of Police", "Superintendent of Police", "Inspector of Police", "Sub Inspector of Police", "Assistant Sub Inspector of Police", "Head Constable", "Constable" }));
        currentpost.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        currentpost.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                currentpostActionPerformed(evt);
            }
        });
        jPanel6.add(currentpost);
        currentpost.setBounds(180, 290, 246, 23);

        nname.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        nname.setMaximumRowCount(20);
        nname.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select the Name......", " " }));
        nname.setToolTipText("Enter the name");
        nname.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        nname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nnameActionPerformed(evt);
            }
        });
        jPanel6.add(nname);
        nname.setBounds(180, 140, 246, 23);

        jLabel9.setBackground(new java.awt.Color(255, 255, 255));
        jLabel9.setFont(new java.awt.Font("Adobe Caslon Pro Bold", 0, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Officer Name:");
        jPanel6.add(jLabel9);
        jLabel9.setBounds(50, 130, 110, 26);

        jLabel11.setFont(new java.awt.Font("Adobe Caslon Pro Bold", 0, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Contact No:");
        jPanel6.add(jLabel11);
        jLabel11.setBounds(60, 190, 110, 26);

        jLabel12.setFont(new java.awt.Font("Adobe Caslon Pro Bold", 0, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("DOB:");
        jPanel6.add(jLabel12);
        jLabel12.setBounds(520, 90, 54, 26);

        jLabel14.setFont(new java.awt.Font("Adobe Caslon Pro Bold", 0, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Choose Post:");
        jPanel6.add(jLabel14);
        jLabel14.setBounds(50, 290, 110, 26);

        jLabel17.setFont(new java.awt.Font("Adobe Caslon Pro Bold", 0, 18)); // NOI18N
        jLabel17.setText("Account No:");
        jPanel6.add(jLabel17);
        jLabel17.setBounds(60, 90, 120, 26);

        idno1.setFont(new java.awt.Font("Verdana", 3, 14)); // NOI18N
        idno1.setHorizontalAlignment(JTextField.CENTER);
        idno1.setToolTipText("Enter ID no");
        idno1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        idno1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        idno1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                idno1FocusLost(evt);
            }
            public void focusGained(java.awt.event.FocusEvent evt) {
                idno1FocusGained(evt);
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
        jPanel6.add(idno1);
        idno1.setBounds(590, 90, 246, 20);

        jPanel2.add(jPanel6);
        jPanel6.setBounds(200, 40, 870, 480);

        jTabbedPane2.addTab("Create New Account", jPanel2);

        jPanel3.setBackground(new java.awt.Color(0, 102, 204));
        jPanel3.setLayout(null);

        jPanel8.setBackground(new java.awt.Color(0, 102, 204));
        jPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        jPanel8.setLayout(null);

        jLabel21.setBackground(new java.awt.Color(255, 255, 255));
        jLabel21.setFont(new java.awt.Font("Adobe Caslon Pro Bold", 0, 48)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("Change Password");
        jLabel21.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        jPanel8.add(jLabel21);
        jLabel21.setBounds(170, 0, 400, 64);

        loginbutton5.setBackground(new java.awt.Color(0, 153, 0));
        loginbutton5.setFont(new java.awt.Font("Chaparral Pro", 3, 24)); // NOI18N
        loginbutton5.setText("Change");
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
        jPanel8.add(loginbutton5);
        loginbutton5.setBounds(290, 340, 180, 33);

        user.setFont(new java.awt.Font("Verdana", 3, 14)); // NOI18N
        user.setHorizontalAlignment(JTextField.CENTER);
        user.setToolTipText("Enter the Username");
        user.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        user.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                userFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                userFocusLost(evt);
            }
        });
        user.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                userMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                userMouseExited(evt);
            }
        });
        user.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userActionPerformed(evt);
            }
        });
        jPanel8.add(user);
        user.setBounds(330, 100, 262, 30);

        password1.setHorizontalAlignment(JTextField.CENTER);
        password1.setToolTipText("Enter old Password");
        password1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        password1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                password1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                password1FocusLost(evt);
            }
        });
        password1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                password1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                password1MouseExited(evt);
            }
        });
        password1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                password1ActionPerformed(evt);
            }
        });
        jPanel8.add(password1);
        password1.setBounds(330, 180, 260, 30);

        newpassword.setHorizontalAlignment(JTextField.CENTER);
        newpassword.setToolTipText("Enter  New Password");
        newpassword.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        newpassword.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                newpasswordFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                newpasswordFocusLost(evt);
            }
        });
        newpassword.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                newpasswordMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                newpasswordMouseExited(evt);
            }
        });
        newpassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newpasswordActionPerformed(evt);
            }
        });
        jPanel8.add(newpassword);
        newpassword.setBounds(330, 260, 260, 30);

        jLabel18.setFont(new java.awt.Font("Adobe Caslon Pro Bold", 0, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Enter New Password:");
        jPanel8.add(jLabel18);
        jLabel18.setBounds(120, 260, 170, 26);

        jLabel19.setFont(new java.awt.Font("Adobe Caslon Pro Bold", 0, 18)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Enter Old Password:");
        jPanel8.add(jLabel19);
        jLabel19.setBounds(130, 180, 160, 26);

        jLabel20.setFont(new java.awt.Font("Adobe Caslon Pro Bold", 0, 18)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("Enter Username:");
        jPanel8.add(jLabel20);
        jLabel20.setBounds(150, 100, 140, 26);

        jPanel3.add(jPanel8);
        jPanel8.setBounds(240, 30, 773, 440);

        jTabbedPane2.addTab("Change Account Password", jPanel3);

        jPanel7.setBackground(new java.awt.Color(0, 102, 204));
        jPanel7.setLayout(null);

        jPanel9.setBackground(new java.awt.Color(0, 102, 204));
        jPanel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        jPanel9.setLayout(null);

        loginbutton4.setBackground(new java.awt.Color(0, 153, 0));
        loginbutton4.setFont(new java.awt.Font("Chaparral Pro", 3, 24)); // NOI18N
        loginbutton4.setText("Delete");
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
        jPanel9.add(loginbutton4);
        loginbutton4.setBounds(290, 310, 150, 40);

        username4.setFont(new java.awt.Font("Verdana", 3, 14)); // NOI18N
        username4.setHorizontalAlignment(JTextField.CENTER);
        username4.setToolTipText("Enter the Username");
        username4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        username4.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                username4FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                username4FocusLost(evt);
            }
        });
        username4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                username4MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                username4MouseExited(evt);
            }
        });
        username4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                username4ActionPerformed(evt);
            }
        });
        jPanel9.add(username4);
        username4.setBounds(330, 112, 290, 30);

        password4.setHorizontalAlignment(JTextField.CENTER);
        password4.setToolTipText("Enter the Password");
        password4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        password4.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                password4FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                password4FocusLost(evt);
            }
        });
        password4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                password4MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                password4MouseExited(evt);
            }
        });
        password4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                password4ActionPerformed(evt);
            }
        });
        jPanel9.add(password4);
        password4.setBounds(330, 202, 290, 30);

        jLabel22.setBackground(new java.awt.Color(255, 255, 255));
        jLabel22.setFont(new java.awt.Font("Adobe Caslon Pro Bold", 0, 48)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("Delete Account");
        jLabel22.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        jPanel9.add(jLabel22);
        jLabel22.setBounds(220, 0, 340, 64);

        jLabel23.setFont(new java.awt.Font("Adobe Caslon Pro Bold", 0, 18)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("Enter Password:");
        jPanel9.add(jLabel23);
        jLabel23.setBounds(180, 200, 140, 26);

        jLabel24.setFont(new java.awt.Font("Adobe Caslon Pro Bold", 0, 18)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("Enter Username:");
        jPanel9.add(jLabel24);
        jLabel24.setBounds(180, 120, 140, 26);

        jPanel7.add(jPanel9);
        jPanel9.setBounds(240, 30, 773, 440);

        jTabbedPane2.addTab("Delete Account", jPanel7);

        jPanel1.add(jTabbedPane2);
        jTabbedPane2.setBounds(0, 130, 1270, 560);

        jLabel15.setBackground(new java.awt.Color(255, 255, 255));
        jLabel15.setFont(new java.awt.Font("Calisto MT", 1, 48)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText(" Account  Details");
        jLabel15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 3));
        jPanel1.add(jLabel15);
        jLabel15.setBounds(440, 20, 400, 64);

        jLabel2.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 51));
        jPanel1.add(jLabel2);
        jLabel2.setBounds(0, 100, 1260, 30);

        loginbutton1.setBackground(new java.awt.Color(0, 153, 0));
        loginbutton1.setFont(new java.awt.Font("Chaparral Pro", 3, 24)); // NOI18N
        loginbutton1.setText("Back");
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
        jPanel1.add(loginbutton1);
        loginbutton1.setBounds(1040, 40, 120, 33);

        jLabel1.setText("jLabel1");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(500, 270, 280, 340);

        l4.setText("jLabel1");
        jPanel1.add(l4);
        l4.setBounds(0, 0, 1260, 690);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1266, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 696, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void contactnoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_contactnoFocusGained
        // TODO add your handling code here:


    }//GEN-LAST:event_contactnoFocusGained

    private void contactnoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_contactnoFocusLost
        // TODO add your handling code here:

    }//GEN-LAST:event_contactnoFocusLost

    private void contactnoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_contactnoMouseEntered
        // TODO add your handling code here:
        //  contactno.setBackground(new Color(0,255,0));

    }//GEN-LAST:event_contactnoMouseEntered

    private void contactnoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_contactnoMouseExited
        // TODO add your handling code here:

    }//GEN-LAST:event_contactnoMouseExited

    private void contactnoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_contactnoKeyTyped
        if (!Character.isDigit(evt.getKeyChar())) {
            JOptionPane.showMessageDialog(null, "Enter Only Digit", "Empty Password", 2);
            evt.consume();
            // TODO add your handling code here:
    }//GEN-LAST:event_contactnoKeyTyped
    }
    private void usernameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_usernameFocusGained
        // TODO add your handling code here:


    }//GEN-LAST:event_usernameFocusGained

    private void usernameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_usernameFocusLost
        // TODO add your handling code here:

    }//GEN-LAST:event_usernameFocusLost

    private void usernameMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_usernameMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_usernameMouseEntered

    private void usernameMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_usernameMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_usernameMouseExited

    private void usernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usernameActionPerformed

    private void idnoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_idnoFocusLost
        // TODO add your handling code here:


    }//GEN-LAST:event_idnoFocusLost

    private void idnoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_idnoFocusGained
        // TODO add your handling code here:


    }//GEN-LAST:event_idnoFocusGained

    private void idnoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_idnoMouseEntered
        // TODO add your handling code here:

    }//GEN-LAST:event_idnoMouseEntered

    private void idnoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_idnoMouseExited
        // TODO add your handling code here:

    }//GEN-LAST:event_idnoMouseExited

    private void idnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idnoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idnoActionPerformed

    private void idnoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_idnoKeyTyped
        // TODO add your handling code here:
        if (!Character.isDigit(evt.getKeyChar())) {
            JOptionPane.showMessageDialog(null, "Enter Only Digit", "Empty Password", 2);
            evt.consume();
        }
    }//GEN-LAST:event_idnoKeyTyped

    private void femaleMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_femaleMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_femaleMouseEntered

    private void femaleMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_femaleMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_femaleMouseExited

    private void femaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_femaleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_femaleActionPerformed

    private void maleMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_maleMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_maleMouseEntered

    private void maleMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_maleMouseExited
        // ODO add your handling code here:
    }//GEN-LAST:event_maleMouseExited

    private void confirmpasswordFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_confirmpasswordFocusGained
        // TODO add your handling code here:

    }//GEN-LAST:event_confirmpasswordFocusGained

    private void confirmpasswordFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_confirmpasswordFocusLost
        // TODO add your handling code here:

    }//GEN-LAST:event_confirmpasswordFocusLost

    private void confirmpasswordMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_confirmpasswordMouseEntered

    }//GEN-LAST:event_confirmpasswordMouseEntered

    private void confirmpasswordMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_confirmpasswordMouseExited
        //DO add your handling code here:
    }//GEN-LAST:event_confirmpasswordMouseExited

    private void confirmpasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmpasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_confirmpasswordActionPerformed

    private void passwordFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_passwordFocusGained
        // TODO add your handling code here:

    }//GEN-LAST:event_passwordFocusGained

    private void passwordFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_passwordFocusLost
        // TODO add your handling code here:

    }//GEN-LAST:event_passwordFocusLost

    private void passwordMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_passwordMouseEntered

        // TODO add your handling code here:
    }//GEN-LAST:event_passwordMouseEntered

    private void passwordMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_passwordMouseExited

        // TODO add your handling code here:
    }//GEN-LAST:event_passwordMouseExited

    private void dobMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dobMouseEntered
        // TODO add your handling code here:

    }//GEN-LAST:event_dobMouseEntered

    private void dobMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dobMouseExited
        // TODO add your handling code here:

    }//GEN-LAST:event_dobMouseExited

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String path = null;
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        FileNameExtensionFilter extension = new FileNameExtensionFilter("*.Images", "jpg", "png", "jpeg", ".gif");
        chooser.addChoosableFileFilter(extension);
        int filestate = chooser.showSaveDialog(null);
        if (filestate == JFileChooser.APPROVE_OPTION) {
            File selectedImage = chooser.getSelectedFile();
            path = selectedImage.getAbsolutePath();
            imagepath.setText(path);
            img.setIcon(ResizeImage(path));
        } else if (filestate == JFileChooser.CANCEL_OPTION) {
            ImageIcon iw = new ImageIcon();
            img.setIcon(iw);
        }
    }

    /*

    }//GEN-LAST:event_jButton1ActionPerformed
*/
    private void loginbuttonFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_loginbuttonFocusGained
        // TODO add your handling code here:

    }//GEN-LAST:event_loginbuttonFocusGained

    private void loginbuttonFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_loginbuttonFocusLost
        // TODO add your handling code here:

    }//GEN-LAST:event_loginbuttonFocusLost

    private void loginbuttonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loginbuttonMouseEntered
        // TODO add your handling code here:

    }//GEN-LAST:event_loginbuttonMouseEntered

    private void loginbuttonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loginbuttonMouseExited
        // TODO add your handling code here:

    }//GEN-LAST:event_loginbuttonMouseExited

    private void loginbuttonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loginbuttonMouseReleased
        // TODO add your handling code here:

        imagepath.setText("");

        username.setText("");
        password.setText("");
        confirmpassword.setText("");
        idno1.setText("");
        contactno.setText("");
        //  this.reassign();
    }//GEN-LAST:event_loginbuttonMouseReleased

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
    private void loginbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginbuttonActionPerformed
        // TODO add your handling code here:

        String ipath = imagepath.getText();
        String cp = (String) currentpost.getSelectedItem();
        String id = idno.getText();
        String fname = (String) nname.getSelectedItem();
        String user = username.getText();
        String pass1 = String.valueOf(password.getPassword());
        String pass2 = String.valueOf(confirmpassword.getPassword());
        String phone = contactno.getText();
        String gender = "Male";

        // Date date = dob.getDate();
        String strDate = idno1.getText();

        if (female.isSelected()) {
            gender = "Female";
        }

        if (verifyFields()) {
            // JOptionPane.showMessageDialog(null, "verified the value");
            int id1 = Integer.parseInt(id);
            if (!checkUsername(user)) {
                //JOptionPane.showMessageDialog(null, "checked the user name");
                PreparedStatement ps;
                ResultSet rs;
                //     String registerUserQuery = "INSERT INTO `users`(`full_name`, `username`, `password`, `phone`, `gender`, `picture`) VALUES (?,?,?,?,?,?)";
                String registerUserQuery1 = "INSERT INTO `useraccount` VALUES(?,?,?,?,?,?,?,?,?)";
                try {

                    ps = loginconnection.connection().prepareStatement(registerUserQuery1);
                    ps.setInt(1, id1);
                    ps.setString(2, fname);
                    ps.setString(3, strDate);
                    ps.setString(4, gender);
                    ps.setString(5, phone);
                    ps.setString(6, cp);
                    ps.setString(7, user);
                    ps.setString(8, pass1);
                    // ps.setString(5, cp);
                    //        JOptionPane.showMessageDialog(null, "value is set in field");

                    try {

                        // save the image as blob in the database
                        if (ipath != null) {

                            InputStream image = new FileInputStream(new File(ipath));
                            ps.setString(9, ipath);
                            //    JOptionPane.showMessageDialog(null, "image value set");

                        } else {
                            ps.setNull(9, java.sql.Types.NULL);
                        }

                        if (ps.executeUpdate() != 0) {
                            JOptionPane.showMessageDialog(null, "Your Account Has Been Created");
                        } else {
                            JOptionPane.showMessageDialog(null, "Error: Check Your Information");
                        }

                    } catch (Exception ex) {
                        //       Logger.getLogger(NewAccount_Page.class.getName()).log(Level.SEVERE, null, ex);
                    }

                } catch (SQLException ex) {
                    //       Logger.getLogger(NewAccount_Page.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }
        srno();
    }//GEN-LAST:event_loginbuttonActionPerformed

    private void currentpostActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_currentpostActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_currentpostActionPerformed

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

    private void loginbutton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginbutton1ActionPerformed
        // TODO add your handling code here:
        try {

            DashBoard1 d = new DashBoard1();

            d.setVisible(true);
            d.pack();
            d.setLocationRelativeTo(null);
            this.dispose();
            d.show();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_loginbutton1ActionPerformed

    private void nnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nnameActionPerformed

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
        user.setText("");
        password1.setText("");
        newpassword.setText("");

    }//GEN-LAST:event_loginbutton5MouseReleased

    private void loginbutton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginbutton5ActionPerformed
        // TODO add your handling code here:
        String user1 = user.getText();
        String pass1 = String.valueOf(password1.getPassword());
        String pass2 = String.valueOf(newpassword.getPassword());
        //String sql="update useraccount set password="+pass2+" where username="+user1+";";
        String sql = "update useraccount set password='" + pass2 + "' where username='" + user1 + "'";
        PreparedStatement ps, psi;
        ResultSet rs;
        if (checkUsername1(user1, pass1)) {
            try {
                ps = loginconnection.connection().prepareStatement(sql);
                ps.executeUpdate(sql);
                JOptionPane.showMessageDialog(null, "Password changed Successfully....", "Confirm it", 2);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Check Username OR Password it Doesn't Match", "Confirm it", 2);
        }
    }//GEN-LAST:event_loginbutton5ActionPerformed
    boolean checkUsername1(String username, String password) {

        PreparedStatement st, pt;
        ResultSet rs, rp;
        boolean username_exist = false;

        String query = "SELECT * FROM `useraccount` WHERE `username` = ?";
        String query1 = "SELECT * FROM `useraccount` WHERE `password` = ?";

        try {

            st = loginconnection.connection().prepareStatement(query);
            st.setString(1, username);
            rs = st.executeQuery();
            pt = loginconnection.connection().prepareStatement(query1);

            pt.setString(1, password);
            rp = pt.executeQuery();

            if (rs.next() && rp.next()) {
                username_exist = true;
                //JOptionPane.showMessageDialog(null, "Your Old Username or Password Doesn't Matched !!!!!!", "Username Failed", 2);
            }

        } catch (SQLException ex) {
            //Logger.getLogger(NewAccount_Page.class.getName()).log(Level.SEVERE, null, ex);
        }

        return username_exist;
    }
    private void userFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_userFocusGained
        // TODO add your handling code here:

    }//GEN-LAST:event_userFocusGained

    private void userFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_userFocusLost


    }//GEN-LAST:event_userFocusLost

    private void userMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_userMouseEntered
        // TODO add your handling code here:

    }//GEN-LAST:event_userMouseEntered

    private void userMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_userMouseExited
        // TODO add your handling code here:

    }//GEN-LAST:event_userMouseExited

    private void userActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_userActionPerformed

    private void password1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_password1FocusGained
        // TODO add your handling code here:

    }//GEN-LAST:event_password1FocusGained

    private void password1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_password1FocusLost


    }//GEN-LAST:event_password1FocusLost

    private void password1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_password1MouseEntered
        // TODO add your handling code here:

    }//GEN-LAST:event_password1MouseEntered

    private void password1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_password1MouseExited
        // TODO add your handling code here:

    }//GEN-LAST:event_password1MouseExited

    private void password1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_password1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_password1ActionPerformed

    private void newpasswordFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_newpasswordFocusGained
        // TODO add your handling code here:


    }//GEN-LAST:event_newpasswordFocusGained

    private void newpasswordFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_newpasswordFocusLost
        // TODO add your handling code here:

    }//GEN-LAST:event_newpasswordFocusLost

    private void newpasswordMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_newpasswordMouseEntered
        // TODO add your handling code here:

    }//GEN-LAST:event_newpasswordMouseEntered

    private void newpasswordMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_newpasswordMouseExited
        // TODO add your handling code here:

    }//GEN-LAST:event_newpasswordMouseExited

    private void newpasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newpasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_newpasswordActionPerformed

    private void loginbutton4FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_loginbutton4FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_loginbutton4FocusGained

    private void loginbutton4FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_loginbutton4FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_loginbutton4FocusLost

    private void loginbutton4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loginbutton4MouseEntered
        // TODO add your handling code here:

    }//GEN-LAST:event_loginbutton4MouseEntered

    private void loginbutton4MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loginbutton4MouseExited
        // TODO add your handling code here:

    }//GEN-LAST:event_loginbutton4MouseExited

    private void loginbutton4MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loginbutton4MouseReleased

        // TODO add your handling code here:
    }//GEN-LAST:event_loginbutton4MouseReleased

    private void loginbutton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginbutton4ActionPerformed
        // TODO add your handling code here:
        String user = username4.getText();
        String pass1 = String.valueOf(password4.getPassword());
        String sql = "delete from useraccount where username='" + user + "' and password='" + pass1 + "'";

        //  String q="Select * from employees where empid='"+eid+"'";
        PreparedStatement ps;
        ResultSet rs;

        if (checkUsername1(user, pass1)) {
            try {

                ps = loginconnection.connection().prepareStatement(sql);

                ps.executeUpdate(sql);
                JOptionPane.showMessageDialog(null, "Account Deleted Successfully....", "Confirm it", 2);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Check Username OR Password it Doesn't Match", "Confirm it", 2);
        }
    }//GEN-LAST:event_loginbutton4ActionPerformed

    private void username4FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_username4FocusGained
        // TODO add your handling code here:
        // TODO add your handling code here:

    }//GEN-LAST:event_username4FocusGained

    private void username4FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_username4FocusLost
        // TODO add your handling code here:

    }//GEN-LAST:event_username4FocusLost

    private void username4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_username4MouseEntered
        // TODO add your handling code here:

    }//GEN-LAST:event_username4MouseEntered

    private void username4MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_username4MouseExited
        // TODO add your handling code here:

    }//GEN-LAST:event_username4MouseExited

    private void username4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_username4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_username4ActionPerformed

    private void password4FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_password4FocusGained
        // TODO add your handling code here:

    }//GEN-LAST:event_password4FocusGained

    private void password4FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_password4FocusLost
        // TODO add your handling code here:

    }//GEN-LAST:event_password4FocusLost

    private void password4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_password4MouseEntered
        // TODO add your handling code here:

    }//GEN-LAST:event_password4MouseEntered

    private void password4MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_password4MouseExited
        // TODO add your handling code here:

    }//GEN-LAST:event_password4MouseExited

    private void password4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_password4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_password4ActionPerformed

    private void idno1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_idno1FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_idno1FocusLost

    private void idno1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_idno1FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_idno1FocusGained

    private void idno1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_idno1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_idno1MouseEntered

    private void idno1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_idno1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_idno1MouseExited

    private void idno1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idno1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idno1ActionPerformed

    private void idno1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_idno1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_idno1KeyTyped

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
            java.util.logging.Logger.getLogger(NewAccount.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewAccount.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewAccount.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewAccount.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NewAccount().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPasswordField confirmpassword;
    private javax.swing.JTextField contactno;
    private javax.swing.JComboBox<String> currentpost;
    private javax.swing.JRadioButton female;
    private javax.swing.JTextField idno;
    private javax.swing.JTextField idno1;
    private javax.swing.JLabel imagepath;
    private javax.swing.JLabel img;
    private javax.swing.JButton jButton1;
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
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JLabel l4;
    private javax.swing.JButton loginbutton;
    private javax.swing.JButton loginbutton1;
    private javax.swing.JButton loginbutton4;
    private javax.swing.JButton loginbutton5;
    private javax.swing.JRadioButton male;
    private javax.swing.JPasswordField newpassword;
    private javax.swing.JComboBox<String> nname;
    private javax.swing.JPasswordField password;
    private javax.swing.JPasswordField password1;
    private javax.swing.JPasswordField password4;
    private javax.swing.JTextField user;
    private javax.swing.JTextField username;
    private javax.swing.JTextField username4;
    // End of variables declaration//GEN-END:variables
}
