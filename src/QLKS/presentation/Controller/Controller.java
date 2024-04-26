package QLKS.presentation.Controller;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import QLKS.domain.CommandProcessor.ButtonCommand.AvgCommand;
import QLKS.domain.CommandProcessor.ButtonCommand.CountCommand;
import QLKS.domain.CommandProcessor.ButtonCommand.DeleteCommand;
import QLKS.domain.CommandProcessor.ButtonCommand.SaveCommand;
import QLKS.domain.CommandProcessor.ButtonCommand.UpdateCommand;
import QLKS.domain.CommandProcessor.Command.Command;
import QLKS.domain.model.QLKSModel;
import QLKS.persistence.DAO;
import QLKS.persistence.DaoObserver;
import QLKS.presentation.GUI.GUI;


public class Controller {
    private DAO dao;
    private GUI gui;
    private Queue<Command> commandQueue;



    public Controller(DAO dao){
        this.dao = dao;
        this.commandQueue = new LinkedList<>();
    }


    //METHOD
    public void setGui(GUI gui) {
        this.gui = gui;
    }

    public void countInvoice() {
        Command countCommand = new CountCommand(dao);
        countCommand.execute();
    }

    public void saveNewData(QLKSModel model) {
        Command saveCommand = new SaveCommand(dao, model);
        saveCommand.execute();
    }

    public List<QLKSModel> getAllData() {
        return dao.getAllData();
    }

    public void dataDelete(QLKSModel model) {
        Command deleteCommand = new DeleteCommand(dao, model);
        deleteCommand.execute();
    }

    public void editData(QLKSModel model) {
        Command updateCommand = new UpdateCommand(dao, model);
        updateCommand.execute();
    }

    public void averageCommand(){
        Command avgCommand = new AvgCommand(dao);
        avgCommand.execute();
    }

    public void addCommand(Command command) {
        commandQueue.offer(command);
    }



    public void processCommands() {
        while (!commandQueue.isEmpty()) {
            Command command = commandQueue.poll();
            command.execute();
        }
    }

    public void registrationObserver(DaoObserver observer){
        dao.addObserver(observer);
    }

    public void unRegistrationObserver(DaoObserver observer){
        dao.deleteObserver(observer);
    }


}
