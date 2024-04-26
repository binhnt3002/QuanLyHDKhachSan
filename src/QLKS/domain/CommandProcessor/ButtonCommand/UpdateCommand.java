package QLKS.domain.CommandProcessor.ButtonCommand;

import javax.swing.JOptionPane;

import QLKS.domain.CommandProcessor.Command.Command;
import QLKS.domain.model.QLKSModel;
import QLKS.persistence.DAO;

public class UpdateCommand implements Command{
    private DAO dao;
    private QLKSModel modelRemote;
    
    
    
    public UpdateCommand(DAO dao, QLKSModel modelRemote) {
        this.dao = dao;
        this.modelRemote = modelRemote;
    }



    @Override
    public void execute() {
        this.dao.updateData(modelRemote);
        JOptionPane.showMessageDialog(null, "Chỉnh sửa thành công !", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
    }
    
}
