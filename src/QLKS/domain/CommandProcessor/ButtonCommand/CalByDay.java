package QLKS.domain.CommandProcessor.ButtonCommand;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import QLKS.domain.CommandProcessor.Command.Command;
import QLKS.domain.model.QLKSModel;

public class CalByDay implements Command {
    private JTextField dayhourField,toTalField;

    

    public CalByDay(JTextField dayhourField, JTextField toTalField) {
        this.dayhourField = dayhourField;
        this.toTalField = toTalField;
    }



    @Override
    public void execute() {
        try {
            int dayhour = Integer.parseInt(dayhourField.getText());
            if (dayhour > 7) {
                QLKSModel modelCalCulate = new QLKSModel(dayhour);
                double toTal = modelCalCulate.calByDay() - ((dayhour - 7) * 0.8);
                toTalField.setText(String.format("%.2f", toTal));
            }
        } catch (NumberFormatException e) {
            // TODO: handle exception
            JOptionPane.showMessageDialog(null, "Ngày/Giờ phải ở định dạng số !", "Thông báo", JOptionPane.ERROR_MESSAGE);
        }
    }
    
}
