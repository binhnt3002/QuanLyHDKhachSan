package QLKS.domain.CommandProcessor.ButtonCommand;

import javax.swing.JOptionPane;

import QLKS.domain.CommandProcessor.Command.Command;
import QLKS.domain.model.QLKSModel;
import QLKS.persistence.DAO;

public class SaveCommand implements Command {
    private DAO dao;
    private QLKSModel modelRemote;

    
    public SaveCommand(DAO dao, QLKSModel modelRemote) {
        this.dao = dao;
        this.modelRemote = modelRemote;
    }


    @Override
    public void execute() {
        this.dao.saveData(modelRemote);
        JOptionPane.showMessageDialog(null, "Lưu hóa đơn thành công !", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
    }
    
}
