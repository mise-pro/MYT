package ru.myt;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class Start {

    private static final Logger LOGGER = LogManager.getLogger(Start.class);

    public static void main(String args[]) {

        LOGGER.info("Application START");
        Controller controller = new Controller();
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                GUI gui1 = new GUI(controller);
                //GUI gui2 = new GUI(controller);
            }
        });

    }

}
