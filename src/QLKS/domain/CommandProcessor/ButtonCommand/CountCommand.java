package QLKS.domain.CommandProcessor.ButtonCommand;

import javax.swing.JOptionPane;

import QLKS.domain.CommandProcessor.Command.Command;
import QLKS.persistence.DAO;

public class CountCommand implements Command {
    private DAO dao;

    public CountCommand(DAO dao) {
        this.dao = dao;
    }

    @Override
    public void execute() {
        int dataCount = dao.countReceipt();
        JOptionPane.showMessageDialog(null, "Tổng số hóa đơn : " + dataCount , "Thông báo", JOptionPane.INFORMATION_MESSAGE);
    }
    
}
