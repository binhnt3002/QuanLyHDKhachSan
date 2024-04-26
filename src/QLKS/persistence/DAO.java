package QLKS.persistence;

import java.util.List;

import QLKS.domain.model.QLKSModel;

/**
 * DAO
 */
public interface DAO {
    List<QLKSModel> getAllData();
    void saveData(QLKSModel model);
    void deleteData(QLKSModel model);
    void updateData(QLKSModel model);
    QLKSModel findbyID(int id);
    int countReceipt();
    // List<QLKSModel> getDataByMonthAndYear(String month, String year);
    void avgByMonth();
    //Oberser method
    void addObserver(DaoObserver observer);
    void deleteObserver(DaoObserver observer);
    void notifyObservers();
    
}