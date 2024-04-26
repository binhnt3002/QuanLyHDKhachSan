package QLKS.persistence;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;


import QLKS.domain.model.QLKSModel;

public class DaoImpl implements DAO {

    private List<DaoObserver> observers = new ArrayList<>();
    private Connection conn;

    //Constructor 
    public DaoImpl() {
        String url = "jdbc:mysql://127.0.0.1:3306/qlks";
        String username = "root";
        String password = "123456";

        try {
            conn = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            // TODO: handle exception
            throw new RuntimeException(e);
        }
    }

//118.71.92.79
    //METHOD 

    //3 METHOD của mẫu Observer
    @Override
    public void addObserver(DaoObserver observer){
       observers.add(observer);
    }
    @Override
    public void deleteObserver(DaoObserver observer){
        observers.remove(observer);
    }
    @Override
    public void notifyObservers() {
        for (DaoObserver daoObserver : observers) {
            daoObserver.update();
        }
    }



    //Lấy dữ liệu
    @Override
    public List<QLKSModel> getAllData() {
        List<QLKSModel> modelsDataList = new ArrayList<>();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM info2");

            while (rs.next()) {
                QLKSModel model = new QLKSModel();
                model.setId(rs.getInt("id"));
                model.setCusName(rs.getString("cusName"));
                model.setRoomID(rs.getString("roomID"));
                model.setReceiptID(rs.getString("receiptID"));
                model.setDateReceipt(rs.getString("dateReceipt"));
                model.setDayHours(rs.getInt("dayHours"));
                model.setTotal(rs.getDouble("total"));
                modelsDataList.add(model);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return modelsDataList;
    }


    //Lưu dữ liệu
    @Override
    public void saveData(QLKSModel model) {
        try {
            PreparedStatement prestmt = conn.prepareStatement("INSERT INTO info2 (cusName,roomID,receiptID,dateReceipt,dayHours,total) VALUES(?,?,?,?,?,?)");
            prestmt.setString(1, model.getCusName());
            prestmt.setString(2, model.getRoomID());
            prestmt.setString(3, model.getReceiptID());
            prestmt.setString(4, model.getDateReceipt());
            prestmt.setInt(5, model.getDayHours());
            prestmt.setDouble(6, model.getTotal());

            prestmt.executeUpdate();
            notifyObservers();
        
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }


    //Xóa dữ liệu
    @Override
    public void deleteData(QLKSModel model) {
        try {
            PreparedStatement prestmt = conn.prepareStatement("DELETE FROM info2 WHERE id = ?");
            prestmt.setInt(1, model.getId());

            prestmt.executeUpdate();
            notifyObservers();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }


    //Cập nhật (edit) #CHƯA HOẠT ĐỘNG
    @Override
    public void updateData(QLKSModel model) {
        try {
            PreparedStatement prestmt = conn.prepareStatement("UPDATE info2 SET cusName = ? , roomID = ? , dateReceipt = ? , dayHours = ?, total = ? WHERE receiptID = ?");
            prestmt.setString(1, model.getCusName());
            prestmt.setString(2, model.getRoomID());
            prestmt.setString(3, model.getDateReceipt());
            prestmt.setInt(4, model.getDayHours());
            prestmt.setDouble(5, model.getTotal());
            
            prestmt.setString(6, model.getReceiptID());

            prestmt.executeUpdate();
            notifyObservers();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }


    //tìm đơn hàng bằng ID
    @Override
    public QLKSModel findbyID(int id) {
        QLKSModel model = null;
        try {
            PreparedStatement prestmt = conn.prepareStatement("SELECT * FROM info2 WHERE id = ? ");
            prestmt.setInt(1, id);

            try (ResultSet rs = prestmt.executeQuery()){
                if (rs.next()) {
                    int idcustomer = rs.getInt("id");
                    String name = rs.getString("cusName");
                    String roomID = rs.getString("roomID");
                    String receiptID = rs.getString("receiptID");
                    String dateRe = rs.getString("dateReceipt");
                    int dayhour = rs.getInt("dayHours");
                    double toTal = rs.getDouble("total");
                    model = new QLKSModel(idcustomer, name, roomID, receiptID, dateRe, dayhour, toTal);


                }
            } 
        }catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
        }
      return model;
    }


    //Đếm số hóa đơn hiện hiện có
    @Override
    public int countReceipt() {
        try {
            PreparedStatement prestmt = conn.prepareStatement("SELECT COUNT(*) FROM info2");
            ResultSet rs = prestmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return 0;
    }


    //Tách tháng và năm trong dateRecept để tính trung bình
    // @Override
    // public List<QLKSModel> getDataByMonthAndYear(String month, String year) {
    //     List<QLKSModel> modelDate = getAllData();
    //     return modelDate.stream()
    //                 .filter(model -> {
    //                     int modelMonth = SeperateDate.getMonth(month);
    //                     int modelYear = SeperateDate.getyear(year);
    //                     return modelMonth == Integer.parseInt(month) && modelYear == Integer.parseInt(year);
    //                 })
    //                 .collect(Collectors.toList());
    // }

    @Override
    public void avgByMonth() {
;   String month = JOptionPane.showInputDialog("Điền tháng bạn muốn tính trung bình : ");
        try {
            PreparedStatement prestmt = conn.prepareStatement("SELECT  AVG(total) FROM info2 WHERE month(dateReceipt) = ?");
            prestmt.setInt(1, Integer.parseInt(month));

            ResultSet rs = prestmt.executeQuery();

            if (rs.next()) {
                double avg = rs.getDouble(1);
                JOptionPane.showMessageDialog(null, "Trung bình số tiền trong tháng "+month+" là : "+avg+" !", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }



  
    }


   
    
}
