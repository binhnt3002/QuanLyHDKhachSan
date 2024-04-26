package QLKS.presentation.GUI;

import java.awt.*;
// import java.sql.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.util.List;

// import QLKS.domain.CommandProcessor.ButtonCommand.AverageCommand;
import QLKS.domain.CommandProcessor.ButtonCommand.CalByDay;
import QLKS.domain.CommandProcessor.ButtonCommand.CalByHours;
import QLKS.domain.CommandProcessor.ButtonCommand.FindByID;
import QLKS.domain.CommandProcessor.Command.Command;
import QLKS.domain.model.QLKSModel;
import QLKS.persistence.DAO;
import QLKS.persistence.DaoImpl;
import QLKS.persistence.DaoObserver;
import QLKS.presentation.Controller.Controller;
import QLKS.presentation.Memento.MementoJTexField;


public class GUI extends JFrame implements DaoObserver{

    private Controller controller;
    private DAO dao;
    private MementoJTexField txt1,txt2,txt3,txt4,txt5;

    private DefaultTableModel tbModel;
    private JLabel quanLyLabel,hoTen,maHD,maPhong,ngayHD,dayHours,total,days,hours;
    private JTextField hoTentxt, maHDtxt,maPhongtxt,ngayHDtxt,daHourstxt,totaltxt;
    private JButton btnNgay,btnGio,btnTim,btnThem,btnSua,btnXoa,btnNhapMoi,btnCount,btnAverage,btnUndo,btnRedo;
    private JTable table;
    // private JScrollBar scrollBar;
    
    public GUI(Controller controller) {
        this.controller = controller;
        controller.registrationObserver(this);
        DAO dao = new DaoImpl();
        

        Font fontLabel = new Font(null,0,22);
        Font fontTxt = new Font(null,0,20);
        Dimension dimension = new Dimension(100,20);
        
    
        quanLyLabel = new JLabel("Quản Lý khách sạn",SwingConstants.CENTER);
        quanLyLabel.setBounds(0, 0, 800, 70);
        quanLyLabel.setFont(new Font(null, 0, 32));
        quanLyLabel.setBackground(new Color(148, 176, 218));
        quanLyLabel.setForeground(new Color(255,255,255));
        quanLyLabel.setOpaque(true);

        //label và text field
        hoTen = new JLabel("Họ và tên : ");
        hoTen.setBounds(30, 70, 150, 50);
        hoTen.setFont(fontLabel);
        hoTentxt = new JTextField();
        hoTentxt.setBounds(140, 85, 600, 25);
        hoTentxt.setFont(fontTxt);

        maPhong = new JLabel("Mã phòng : ");
        maPhong.setBounds(30, 120, 150, 50);
        maPhong.setFont(fontLabel);
        maPhongtxt = new JTextField();
        maPhongtxt.setBounds(190, 135, 200, 25);
        maPhongtxt.setFont(fontTxt);
        
        maHD = new JLabel("Mã hóa đơn : ");
        maHD.setBounds(400, 120, 200, 50);
        maHD.setFont(fontLabel);
        maHDtxt = new JTextField();
        maHDtxt.setBounds(540, 136, 200, 25);
        maHDtxt.setFont(fontTxt);

        ngayHD = new JLabel("Ngày hóa đơn : ");
        ngayHD.setBounds(30, 170, 200 , 50);
        ngayHD.setFont(fontLabel);
        ngayHDtxt = new JTextField();
        ngayHDtxt.setBounds(190, 185, 200, 25);
        ngayHDtxt.setFont(fontTxt);

        dayHours = new JLabel("Ngày/Giờ : ");
        dayHours.setBounds(30, 220 , 200, 50);
        dayHours.setFont(fontLabel);
        daHourstxt = new JTextField();
        daHourstxt.setBounds(190, 235, 200, 25);
        daHourstxt.setFont(fontTxt);

        total = new JLabel("Tổng tiền : ");
        total.setBounds(400, 220 , 200, 50);
        total.setFont(fontLabel);
        totaltxt = new JTextField();
        totaltxt.setBounds(540, 236, 200, 25);
        totaltxt.setEnabled(false);
        totaltxt.setFont(fontTxt);


        days = new JLabel("Giá 1 ngày : 250,000 đ");
        days.setBounds(400, 270, 120, 20);
        hours = new JLabel("Giá 1 giờ : 80,000 đ");
        hours.setBounds(550, 270, 120, 20);


        //button 
        btnNgay = new JButton("Ngày");
        btnNgay.setPreferredSize(new Dimension(80, 20));
        btnNgay.addActionListener(e ->{
            Command calByDayCommand = new CalByDay(daHourstxt, totaltxt);
            controller.addCommand(calByDayCommand);
            controller.processCommands();
        });
        


        //btnGio
        btnGio = new JButton("Giờ ");
        btnGio.setPreferredSize(new Dimension(80, 20));
        btnGio.addActionListener(e -> {
            Command calByHours = new CalByHours(daHourstxt, totaltxt);
            controller.addCommand(calByHours);
            controller.processCommands();
        });


        //btnTim
        btnTim = new JButton("tìm");
        btnTim.setPreferredSize(dimension);
        btnTim.setBackground(Color.CYAN);
        btnTim.addActionListener(e -> {
            int idFind = Integer.parseInt(JOptionPane.showInputDialog("Điền ID cần tìm : "));
            Command findById = new FindByID(dao, idFind, tbModel);
            controller.addCommand(findById);
            controller.processCommands();
        });
        

        //btnThem
        btnThem = new JButton("Thêm");
        btnThem.setPreferredSize(dimension);
        btnThem.setBackground(Color.green);
        btnThem.addActionListener(e -> {
            addInfo();
        });


        //btnSua
        btnSua = new JButton("Sửa");
        btnSua.setPreferredSize(dimension);
        btnSua.setBackground(Color.ORANGE);
        btnSua.addActionListener(e -> {
            editInfo();
        });



        //btnXoa
        btnXoa = new JButton("Xóa");
        btnXoa.setPreferredSize(dimension);
        btnXoa.setBackground(Color.YELLOW);
        btnXoa.addActionListener(e -> {
            deleteInfo();
        });


        //btnNhapMoi
        btnNhapMoi = new JButton("Nhập mới");
        btnNhapMoi.setPreferredSize(dimension);
        btnNhapMoi.setBackground(Color.RED);
        btnNhapMoi.addActionListener(e -> {
            clearText();
            table.clearSelection();
            maHDtxt.setEnabled(true);
            refreshTable();
        });

        //btnCount
        btnCount = new JButton("Đếm");
        btnCount.setPreferredSize(dimension);
        btnCount.addActionListener(e -> {
            controller.countInvoice();
        });

        /*câu lệnh thay thế nếu JOptionPane không hiện
         *  int userCount = (Integer) controller.countUsers();
            JOptionPane.showMessageDialog(frame, "Number of users: " + userCount);
         */


        //btnAverage 
        btnAverage = new JButton("Trung bình");
        btnAverage.setPreferredSize(dimension);
        btnAverage.addActionListener(e -> {
            controller.averageCommand();
        
        });
    
        

        //thêm thực thể vào cho Undo/Redo
        txt1 = new MementoJTexField(hoTentxt);
        txt2 = new MementoJTexField(maPhongtxt);
        txt3 = new MementoJTexField(maHDtxt);
        txt4 = new MementoJTexField(ngayHDtxt);
        txt5 = new MementoJTexField(daHourstxt);


        //btn Undo/Redo
        btnUndo = new JButton("Undo");
        btnUndo.setPreferredSize(new Dimension(65, 20));
        btnUndo.addActionListener(e -> {
            undoMethod();
        });

        btnRedo = new JButton("Redo");
        btnRedo.setPreferredSize(new Dimension(65, 20));
        btnRedo.addActionListener(e -> {
            redoMethod();
        });

        //dùng panel để làm Group Box
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder(""));
        panel.setBounds(500, 185, 200, 36);

        JPanel panel2 = new JPanel();
        panel2.setBorder(BorderFactory.createTitledBorder(""));
        panel2.setBounds(15, 310, 750, 36);

/////////////////////////////////////
        JPanel panel3 = new JPanel();
        panel3.setBorder(BorderFactory.createTitledBorder(""));
        panel3.setBounds(350, 270, 310, 30);
/////////////////////////////////////

       
        
        //tao columns
        String[] colNames = {"ID","Name","RoomID","ReceiptID","DateReceipt","Day/Hours","Total"};
        //tao table
        tbModel = new DefaultTableModel(colNames,0);
        table = new JTable(tbModel);
        table.setBounds(0, 350, 800, 250);
        table.getSelectionModel().addListSelectionListener(e -> {
            fillFormFields();
        });

        JScrollBar scrollBar = new JScrollBar();
        scrollBar.add(table,BorderLayout.CENTER);
        table.setFillsViewportHeight(true);


        //lấy dữ liệu từ DB
        DaoImpl daoImpl = new DaoImpl();
        List<QLKSModel> modelDataList = daoImpl.getAllData();
        for (QLKSModel qlksModel : modelDataList) {
            tbModel.addRow(new Object[]{
                qlksModel.getId(),
                qlksModel.getCusName(),
                qlksModel.getRoomID(),
                qlksModel.getReceiptID(),
                qlksModel.getDateReceipt(),
                qlksModel.getDayHours(),
                qlksModel.getTotal()
            });
        }



        //tạo thanh trượt
  
 
        //database
        // String URL = "jdbc:mysql://127.0.0.1:3306/studentms";
        // String USER = "root";
        // String PASSWORD = "123456";
        
        // String[] columnName = {"id","email","first_name","last_name"};
        // DefaultTableModel model = new DefaultTableModel(columnName,0);
        
        // table = new JTable(model);

        // String sql = "SELECT * FROM students ";
        // try {
        //     Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        //     java.sql.Statement stmt = conn.createStatement();

        //     ResultSet rs = stmt.executeQuery(sql);

        //     while (rs.next()) {
        //         String d1 = rs.getString("id");
        //         String d2 = rs.getString("email");
        //         String d3 = rs.getString("first_name");
        //         String d4 = rs.getString("last_name");

        //         model.addRow(new Object[]{d1,d2,d3,d4});
        //     }
        
        // } catch (SQLException sqlEx) {
        //     // TODO: handle exception
        //     sqlEx.printStackTrace();
        // }

        add(panel);
        add(panel2);
        // add(panel3);

        add(quanLyLabel);
        
        add(hoTen);
        add(hoTentxt);
        
        add(maPhong);
        add(maPhongtxt);
        
        add(maHD);
        add(maHDtxt);
        
        add(ngayHD);
        add(ngayHDtxt);
        
        add(dayHours);
        add(daHourstxt);
        
        add(total);
        add(totaltxt);

        add(days);
        add(hours);
        
        add(table);

        panel.add(btnNgay);
        panel.add(btnGio);
        
        panel2.add(btnTim);
        panel2.add(btnThem);
        panel2.add(btnSua);
        panel2.add(btnXoa);
        panel2.add(btnNhapMoi);
        panel2.add(btnCount);
        panel2.add(btnAverage);

        // panel3.add(days);
        // panel3.add(hours);



        setLayout(null);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
       
    }



    //làm rỗng text field
    private void clearText() {
        hoTentxt.setText("");
        maHDtxt.setText("");
        maPhongtxt.setText("");
        ngayHDtxt.setText("");
        daHourstxt.setText("");
        totaltxt.setText("");
    }


    //load lại bảng
    private void refreshTable() {
        tbModel.setRowCount(0);

        List<QLKSModel> model = controller.getAllData();
        for (QLKSModel qlksModel : model) {
            Object [] row = new Object[]{
                qlksModel.getId(),
                qlksModel.getCusName(),
                qlksModel.getRoomID(),
                qlksModel.getReceiptID(),
                qlksModel.getDateReceipt(),
                qlksModel.getDayHours(),
                qlksModel.getTotal()
            };
            tbModel.addRow(row);
        }
    }


    //điền thông tin khi chọn
    private void fillFormFields() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0 ) {
            String name = (String) tbModel.getValueAt(selectedRow, 1);
            String roomID = (String) tbModel.getValueAt(selectedRow, 2);
            String receipt = (String) tbModel.getValueAt(selectedRow, 3);
            String dateReceipt = (String) tbModel.getValueAt(selectedRow, 4);
            int dayhour = (Integer) tbModel.getValueAt(selectedRow, 5);
            double toTal = (Double) tbModel.getValueAt(selectedRow, 6);

            hoTentxt.setText(name);
            maPhongtxt.setText(roomID);
            maHDtxt.setText(receipt);
            maHDtxt.setEnabled(false);
            ngayHDtxt.setText(dateReceipt);
            daHourstxt.setText(String.valueOf(dayhour));
            totaltxt.setText(String.valueOf(toTal));
        }
    }


    //thêm
    private void addInfo() {
          String name = hoTentxt.getText();
           String roomid = maPhongtxt.getText();
           String receipt = maHDtxt.getText();
           String dateReceipt = ngayHDtxt.getText();
           int dayhours = Integer.parseInt(daHourstxt.getText());
           double toTal = Double.parseDouble(totaltxt.getText());
           QLKSModel dataModel = new QLKSModel(name, roomid, receipt, dateReceipt, dayhours, toTal);
           controller.saveNewData(dataModel);
        //    refreshTable();
    }


    //sửa
    private void editInfo() {
        String name = hoTentxt.getText();
            String roomid = maPhongtxt.getText();
            String receipt = maHDtxt.getText();
            String dateReceipt = ngayHDtxt.getText();
            int dayhours = Integer.parseInt(daHourstxt.getText());
            double toTal = Double.parseDouble(totaltxt.getText());
            QLKSModel dataModel = new QLKSModel(name, roomid, receipt, dateReceipt, dayhours, toTal);
            controller.editData(dataModel);
            // refreshTable();
    }


    //xóa
    private void deleteInfo(){
        int selectRow = table.getSelectedRow();
            int response =  JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa thông tin khách hàng này không ?", "Thông báo", JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.WARNING_MESSAGE);
            if (selectRow >= 0 ) {
                int id = (Integer) tbModel.getValueAt(selectRow, 0);
                String name = (String) tbModel.getValueAt(selectRow, 1);
                String roomID = (String) tbModel.getValueAt(selectRow, 2);
                String receipt = (String) tbModel.getValueAt(selectRow, 3);
                String dateReceipt = (String) tbModel.getValueAt(selectRow, 4);
                int dayhour = (Integer) tbModel.getValueAt(selectRow, 5);
                double toTal = (Double) tbModel.getValueAt(selectRow, 6);

                QLKSModel dataModel = new QLKSModel(id, name, roomID, receipt, dateReceipt, dayhour, toTal);
                if (response == JOptionPane.YES_OPTION) {
                    controller.dataDelete(dataModel);
                }
                // refreshTable();                
            }
    }


    public void undoMethod() {
        txt1.undo();
        txt2.undo();
        txt3.undo();
        txt4.undo();
        txt5.undo();
    }

    public void redoMethod() {
        txt1.redo();
        txt2.redo();
        txt3.redo();
        txt4.redo();
        txt5.redo();
    }


    @Override
    public void update() {
        dao = new DaoImpl();
        //Dọn dẹp bảng 
        tbModel.setRowCount(0);

        //tải lại dữ liệu bảng 
        for (QLKSModel dataListModel : dao.getAllData()) {
        Object[] rowData = new Object[7];
        rowData[0] = dataListModel.getId();
        rowData[1] = dataListModel.getCusName();
        rowData[2] = dataListModel.getRoomID();
        rowData[3] = dataListModel.getReceiptID();
        rowData[4] = dataListModel.getDateReceipt();
        rowData[5] = dataListModel.getDayHours();
        rowData[6] = dataListModel.getTotal();
        tbModel.addRow(rowData);
        }

    }






}

