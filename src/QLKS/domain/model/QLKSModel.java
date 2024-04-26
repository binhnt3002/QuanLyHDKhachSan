package QLKS.domain.model;

public class QLKSModel {
    private int id ;
    private String cusName;
    private String roomID;
    private String receiptID;
    private String dateReceipt;
    private int dayHours;
    private double total;
    
    public QLKSModel(){}


    public QLKSModel(int dayHours) {
        this.dayHours = dayHours;
    }

    public QLKSModel(String dateReceipt){
        this.dateReceipt = dateReceipt;
    }    

    public QLKSModel(String cusName, String roomID, String receiptID, String dateReceipt, int dayHours,
            double total) {
        this.cusName = cusName;
        this.roomID = roomID;
        this.receiptID = receiptID;
        this.dateReceipt = dateReceipt;
        this.dayHours = dayHours;
        this.total = total;
            }

    public QLKSModel(int id, String cusName, String roomID, String receiptID, String dateReceipt, int dayHours,
            double total) {
        this.id = id;
        this.cusName = cusName;
        this.roomID = roomID;
        this.receiptID = receiptID;
        this.dateReceipt = dateReceipt;
        this.dayHours = dayHours;
        this.total = total;
    }


    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public String getCusName() {
        return cusName;
    }


    public void setCusName(String cusName) {
        this.cusName = cusName;
    }


    public String getRoomID() {
        return roomID;
    }


    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }


    public String getReceiptID() {
        return receiptID;
    }


    public void setReceiptID(String receiptID) {
        this.receiptID = receiptID;
    }


    public String getDateReceipt() {
        return dateReceipt;
    }


    public void setDateReceipt(String dateReceipt) {
        this.dateReceipt = dateReceipt;
    }


    public int getDayHours() {
        return dayHours;
    }


    public void setDayHours(int dayHours) {
        this.dayHours = dayHours;
    }


    public double getTotal() {
        return total;
    }


    public void setTotal(double total) {
        this.total = total;
    }


    public double calByDay() {
        return dayHours * 250000;
    }

    public double calByHour() {
        return dayHours * 80000;
    }

}
