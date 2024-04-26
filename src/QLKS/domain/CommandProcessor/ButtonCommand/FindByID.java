package QLKS.domain.CommandProcessor.ButtonCommand;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import QLKS.domain.CommandProcessor.Command.Command;
import QLKS.domain.model.QLKSModel;
import QLKS.persistence.DAO;

/**
 * FindByID
 */
public class FindByID implements Command {
    private DAO dao;
    private int idCUS;
    private DefaultTableModel tbModel;


    public FindByID(DAO dao, int idCUS, DefaultTableModel tbModel) {
        this.dao = dao;
        this.idCUS = idCUS;
        this.tbModel = tbModel;
    }


    @Override
    public void execute() {
        tbModel.setRowCount(0);
        QLKSModel model = dao.findbyID(idCUS);
        if (model != null) {
        //add data to table
        Object[] rowData = new Object[7];
        rowData[0] = idCUS;
        rowData[1] = model.getCusName();
        rowData[2] = model.getRoomID();
        rowData[3] = model.getReceiptID();
        rowData[4] = model.getDateReceipt();
        rowData[5] = model.getDayHours();
        rowData[6] = model.getTotal();
        tbModel.addRow(rowData);
            return;
        }else{

            JOptionPane.showMessageDialog(null, "Khách hàng này không tồn tại !", "Thông báo", JOptionPane.ERROR_MESSAGE);
        }



    }

    
}