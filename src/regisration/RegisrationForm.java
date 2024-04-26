package regisration;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import newlogin.LGform;
import regisration.checkaccount.Check;

public class RegisrationForm implements ActionListener {
    Check checkRemotCheck = new Check();
    
    JFrame frame = new JFrame("Đăng ký");
    private JLabel userNamJLabel , emaiLabel, phonLabel, addressJLabel, passJLabel,conFormPassJLabel,regisJLabel,emJLabel;
    private JTextField userNamText, emailText, phoneText,addressText;
    private JPasswordField passText, conFormPassText;
    private JButton btnDK,btnCancel;

    public RegisrationForm() {
        
        
        Font fontText = new Font(null,0,17);  //setFont Text field
        
        //head line
        regisJLabel = new JLabel("Đăng ký");
        regisJLabel.setBounds(155, 0, 200, 70);
        regisJLabel.setFont(new Font(null, 1, 48));

        //user label
        userNamJLabel = new JLabel("Tên đăng nhập : ");
        userNamJLabel.setBounds(20, 50, 160, 70);
        userNamJLabel.setFont(new Font(null,1,20));
        userNamText = new JTextField();
        userNamText.setBounds(225, 78 , 230 , 23);
        userNamText.setFont(fontText);

        //email label
        emaiLabel = new JLabel("Email : ");
        emaiLabel.setBounds(20, 100, 70, 70);
        emaiLabel.setFont(new Font(null, 1, 20));
        emailText = new JTextField();
        emailText.setBounds(225, 125, 230, 23);
        emailText.setFont(fontText);

        //phone label
        phonLabel = new JLabel("Số điện thoại : ");
        phonLabel.setBounds(20, 150, 200, 70);
        phonLabel.setFont(new Font(null, 1, 20));
        phoneText = new JTextField();
        phoneText.setBounds(225, 178, 230, 23);
        phoneText.setFont(fontText);

        //address label
        addressJLabel = new JLabel("Địa chỉ : ");
        addressJLabel.setBounds(20, 200, 150, 70);
        addressJLabel.setFont(new Font(null, 1, 20));
        addressText = new JTextField();
        addressText.setBounds(225 , 228, 230, 23);
        addressText.setFont(fontText);

        //pass & confirm pass
        passJLabel = new JLabel("Mật khẩu : ");
        passJLabel.setBounds(20, 250, 150, 70);
        passJLabel.setFont(new Font(null, 1, 20));
        passText = new JPasswordField();
        passText.setBounds(225, 278 , 230, 23);
        passText.setFont(fontText);

        conFormPassJLabel = new JLabel("Xác nhận mật khẩu : ");
        conFormPassJLabel.setBounds(20, 300, 250, 70);
        conFormPassJLabel.setFont(new Font(null, 1, 20));
        conFormPassText = new JPasswordField();
        conFormPassText.setBounds(225, 328, 230, 23);
        conFormPassText.setFont(fontText);
    
        //button login & registration
        btnDK = new JButton("Đăng ký");
        btnDK.addActionListener(this);
        btnDK.setBounds(280, 400, 95, 20);
        btnCancel= new JButton("Hủy bỏ");
        btnCancel.addActionListener(this);
        btnCancel.setBounds(380, 400, 95, 20);

        //empty label
        emJLabel = new JLabel();

        //add to frame
        frame.add(regisJLabel);
        frame.add(userNamJLabel);
        frame.add(userNamText);
        frame.add(emaiLabel);
        frame.add(emailText);
        frame.add(phonLabel);
        frame.add(phoneText);
        frame.add(addressJLabel);
        frame.add(addressText);
        frame.add(passJLabel);
        frame.add(passText);
        frame.add(conFormPassJLabel);
        frame.add(conFormPassText);
        frame.add(btnDK);
        frame.add(btnCancel);
        frame.add(emJLabel);

        //set GUI registration form
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        

    }

    //check the require for account
    

    //add user to db
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if (e.getSource() == btnCancel) {
            frame.dispose();
            new LGform();
            return;
        }
        if (e.getSource() == btnDK ) {
            String userName = userNamText.getText();
            String email  = emailText.getText();
            String phone = phoneText.getText();
            String address = addressText.getText();
            String password = new String(passText.getPassword());
            String confirmPass = new String(conFormPassText.getPassword());


            //check condition for create an account
            if (userName.isEmpty() || email.isEmpty() || phone.isEmpty() || address.isEmpty() || password.isEmpty() ||confirmPass.isEmpty()) {
                JOptionPane.showMessageDialog(
                    this.frame,
                    "Điền đầy đủ các ô trống !",
                    "Thông báo",
                    JOptionPane.ERROR_MESSAGE
            );
                return;
            }
            if (!checkRemotCheck.checkAcc(userName)) {
                JOptionPane.showMessageDialog(frame, "Tên tài khoản phải dài từ 3 - 16 ký tự", "Thông báo ", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (!checkRemotCheck.checkEmail(email)) {
                JOptionPane.showMessageDialog(frame, "Email không đúng định dạng @gmail.com ! ", "Thông báo", JOptionPane.ERROR_MESSAGE);
                return;
            }if (!checkRemotCheck.checkPhone(phone)) {
                JOptionPane.showMessageDialog(frame, "Vui lòng điền số điện thoại !", "Thông báo", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!confirmPass.equals(password)) {
                JOptionPane.showMessageDialog(this.frame, "Mật khẩu không trùng khớp !", "Thông báo", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            //adding useser process
            else {

                    final String URL_DATABASE = "jdbc:mysql://127.0.0.1:3306/logindb";
                    final String USER = "root";
                    final String PASSWORD = "123456";

                try {
                    Connection conn = DriverManager.getConnection(URL_DATABASE,USER,PASSWORD);
                    Statement stmt = conn.createStatement();
                    String sql = "INSERT INTO user(username,email,phone,address,password) VALUES(?,?,?,?,?)";
                    
                    PreparedStatement preState = conn.prepareStatement(sql);
                    preState.setString(1, userName);
                    preState.setString(2, email);
                    preState.setString(3, phone);
                    preState.setString(4, address);
                    preState.setString(5, password);

                    preState.executeUpdate();

                    stmt.close();
                    conn.close();

                    JOptionPane.showMessageDialog(frame, "Đăng ký thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    frame.dispose();
                    new LGform();

                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        }
    }
    
}
