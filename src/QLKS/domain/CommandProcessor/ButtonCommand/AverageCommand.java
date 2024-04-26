// package QLKS.domain.CommandProcessor.ButtonCommand;

// import java.util.List;

// import javax.swing.JOptionPane;

// import QLKS.domain.CommandProcessor.Command.Command;
// import QLKS.domain.model.QLKSModel;
// import QLKS.persistence.DAO;

// public class AverageCommand implements Command {
//     private DAO dao;
//     private String month;
//     private String year; 


//     public AverageCommand(DAO dao, String month, String year) {
//         this.dao = dao;
//         this.month = month;
//         this.year = year;
//     }


//     @Override
//     public void execute() {
//         List<QLKSModel> model = dao.getDataByMonthAndYear(month, year);
//         if (model.isEmpty()) {
//             JOptionPane.showMessageDialog(null, "không có dữ liệu của tháng "+ month+ " !", "Thông báo", JOptionPane.ERROR_MESSAGE);
//             return;
//         }

//         double sum = model.stream().mapToDouble(QLKSModel::getTotal).sum();
//         double average = sum / model.size();
//         JOptionPane.showMessageDialog(null, "Tổng số tiền trong tháng "+month+" năm "+year+" là : "+average+ " !", "Thông báo", JOptionPane.INFORMATION_MESSAGE);

//     }
    
// }
