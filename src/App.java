

import QLKS.persistence.DAO;
import QLKS.persistence.DaoImpl;
import QLKS.presentation.Controller.Controller;
import QLKS.presentation.GUI.GUI;
// import newlogin.LGform;


public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        // new LGform();
            DAO dao = new DaoImpl();
            Controller controller = new Controller(dao);
            GUI gui = new GUI(controller);
            controller.setGui(gui);

    }
}
 