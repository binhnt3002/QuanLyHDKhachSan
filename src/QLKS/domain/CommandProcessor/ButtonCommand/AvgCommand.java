package QLKS.domain.CommandProcessor.ButtonCommand;

import QLKS.domain.CommandProcessor.Command.Command;
import QLKS.persistence.DAO;

public class AvgCommand implements Command{
    private DAO dao;


    public AvgCommand(DAO dao) {
        this.dao = dao;
    }

    @Override
    public void execute() {
        this.dao.avgByMonth();
    }
    

    
}
