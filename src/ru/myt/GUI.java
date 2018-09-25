package ru.myt;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.net.URL;
import java.util.ArrayList;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ru.myt.interfaces.ShowTrains;
import ru.myt.objects.Train;
import ru.myt.settings.Settings;

public class GUI extends JFrame implements ShowTrains {

    private static final Logger LOGGER = LogManager.getLogger(GUI.class);
    private DefaultListModel availableTrainsListModel = new DefaultListModel();

    private static final String APPLICATION_NAME = "MyYandexTrains v 1.2 (final 09.11.2016)";
    public static final String TRAIN_LOGO_IMAGE = "images/train.png";

    private Controller controller;

    private TrayIcon trayIcon;
    protected JDialog hiddenDialog;

    private JPopupMenu trayMenu = new JPopupMenu();

    public GUI(Controller controller) {

        this.controller = controller;

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        initComponents();

        setTrayIcon();
        setWindowIcon();
        this.setVisible(false);

        controller.addNewTrainsToShowListener(this);
        availableTrainsJList.addMouseListener(controller);
    }

    private void setWindowIcon() {
        URL imageURL = GUI.class.getResource(TRAIN_LOGO_IMAGE);
        Image image = Toolkit.getDefaultToolkit().getImage(imageURL);
        this.setIconImage(image);
    }

    private void setTrayIcon() {

        if (!SystemTray.isSupported()) {
            return;
        }

        /*http://stackoverflow.com/questions/19868209/cannot-hide-systemtray-jpopupmenu-when-it-loses-focus*/
        /* Initialize the hidden dialog as a headless, titleless dialog window */
        hiddenDialog = new JDialog();
        hiddenDialog.setSize(10, 10);
        /* Add the window focus listener to the hidden dialog */
        hiddenDialog.addWindowFocusListener(new WindowFocusListener() {
            @Override
            public void windowLostFocus(WindowEvent we) {
                hiddenDialog.setVisible(false);
            }

            @Override
            public void windowGainedFocus(WindowEvent we) {
            }
        });

        URL imageURL = GUI.class.getResource(TRAIN_LOGO_IMAGE);

        Image image = Toolkit.getDefaultToolkit().getImage(imageURL);

        trayIcon = new TrayIcon(image, "MyYandexTrains");

        trayIcon.setImageAutoSize(true);
        trayIcon.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    trayMenu.setLocation(e.getX(), e.getY());
                    hiddenDialog.setLocation(e.getX(), e.getY());
                    trayMenu.setInvoker(hiddenDialog);
                    hiddenDialog.setVisible(true);
                    trayMenu.setVisible(true);
                }
            }
        });
        SystemTray tray = SystemTray.getSystemTray();
        try {
            tray.add(trayIcon);
        } catch (AWTException e) {
            if (Settings.DEBUG_MODE) {
                LOGGER.error("TrayIcon could not be added");
            }
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new JScrollPane();
        availableTrainsJList = new JList();

        setTitle(APPLICATION_NAME);
        setLocationByPlatform(true);
        setMinimumSize(new Dimension(525, 300));
        setName("mainFrame"); // NOI18N
        setPreferredSize(new Dimension(525, 300));
        setResizable(false);

        jScrollPane1.setViewportView(availableTrainsJList);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JList availableTrainsJList;
    private JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

    public void showAvailableTrains(ArrayList<Train> availableTrains) {
        availableTrainsListModel.clear();

        if (!availableTrains.isEmpty()) {

            for (Train currentTrain : availableTrains) {
                availableTrainsListModel.addElement(currentTrain);
            }
            availableTrainsJList.setModel(availableTrainsListModel);

        } else {
            Train train = new Train();
            train.setName("Нет доступных электричек");
            train.setUniqueID("fakeUniqueId");
            availableTrainsListModel.addElement(train);
            availableTrainsJList.setModel(availableTrainsListModel);
        }
        repaint();
        pack();
    }

    @Override
    public void showNearestTrains(ArrayList<Train> nearestTrains) {

        trayMenu.setVisible(false);
        trayMenu.removeAll();

        availableTrainsJList.setCellRenderer(new TrainListCellRenderer(nearestTrains));

        if (!nearestTrains.isEmpty()) {

            trayMenu.addSeparator();

            int availableCount;
            if (nearestTrains.size() < Settings.COUNT_NEAREST_TRAINS_TO_SHOW) {
                availableCount = nearestTrains.size();
            } else {
                availableCount = Settings.COUNT_NEAREST_TRAINS_TO_SHOW;
            }

            for (int currentTrain = 0; currentTrain < availableCount; currentTrain++) {

                JCheckBoxMenuItem trainItem = new JCheckBoxMenuItem(nearestTrains.get(currentTrain).toString());
                trainItem.setName(nearestTrains.get(currentTrain).getUniqueID());
                if (!nearestTrains.get(currentTrain).isEnoughTimeToCatchTrain()) {
                    trainItem.setEnabled(false);
                }
                if (nearestTrains.get(currentTrain).isSelected()) {
                    trainItem.setSelected(true);
                } else {
                    trainItem.setSelected(false);
                }

                trainItem.addActionListener(controller);

                ActionListener listener = new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ae) {
                        hiddenDialog.setVisible(false);
                    }
                };

                trainItem.addActionListener(listener);
                trayMenu.add(trainItem);
            }
            trayMenu.addSeparator();
            JMenuItem taxiItem = new JMenuItem("Может сразу на такси?");
            taxiItem.setName("COMMAND-showTaxiInfo");
            taxiItem.addActionListener(controller);
            trayMenu.add(taxiItem);

        } else {
            JMenuItem taxiItem = new JMenuItem("Похоже, пора вызывать такси");
            taxiItem.setName("COMMAND-showTaxiInfo");
            taxiItem.addActionListener(controller);
            trayMenu.add(taxiItem);
        }

        JMenuItem showAllTrainsItem = new JMenuItem("Показать все поезда");
        showAllTrainsItem.setName("COMMAND-showAvailableTrains");
        showAllTrainsItem.addActionListener(controller);
        trayMenu.add(showAllTrainsItem);
        trayMenu.addSeparator();

        JMenuItem exitItem = new JMenuItem("Выход");
        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LOGGER.info("Application is terminating...");
                System.exit(0);
            }
        });
        trayMenu.add(exitItem);
        repaint();
        pack();
    }

    @Override
    public void showNotificationAboutSelectedTrain(int timeBeforeSelectedTrain) {

        if (timeBeforeSelectedTrain > 0) {
            trayIcon.displayMessage("На выбранный поезд пора выходить через:", timeBeforeSelectedTrain + "''", TrayIcon.MessageType.INFO);
        } else {
            trayIcon.displayMessage("Кстати...", "...пора всё бросать и выходить", TrayIcon.MessageType.WARNING);
        }
    }

    @Override
    public void showAvailableTrainsWindow() {
        actualizeSelectedTrainPositionInAvailableTrainsWindow();
        this.setVisible(true);

    }

    private void actualizeSelectedTrainPositionInAvailableTrainsWindow() {
        int selectedTrainIndex = -1;
        int firstEnabledInJlistTrainIndex = -1;
        for (int currentIndex = 0; currentIndex < availableTrainsListModel.getSize(); currentIndex++) {
            Train currentTrain = (Train) availableTrainsListModel.get(currentIndex);

            if (currentTrain.isEnoughTimeToCatchTrain() && firstEnabledInJlistTrainIndex == -1) {
                firstEnabledInJlistTrainIndex = currentIndex;
            }
            if (currentTrain.isSelected() && selectedTrainIndex == -1) {
                selectedTrainIndex = currentIndex;
            }
            if (selectedTrainIndex > -1 && firstEnabledInJlistTrainIndex > -1) {
                break;
            }

        }
        //brainFuck
        if (firstEnabledInJlistTrainIndex == -1) {
            availableTrainsJList.ensureIndexIsVisible(0);
            availableTrainsJList.setSelectedIndex(0);
        } else {

            if (selectedTrainIndex < firstEnabledInJlistTrainIndex) {
                availableTrainsJList.ensureIndexIsVisible(availableTrainsListModel.getSize() - 1);
                availableTrainsJList.ensureIndexIsVisible(firstEnabledInJlistTrainIndex);
                availableTrainsJList.setSelectedIndex(firstEnabledInJlistTrainIndex);
            } else {
                if (selectedTrainIndex == -1) {
                    selectedTrainIndex = 0;
                };
                availableTrainsJList.ensureIndexIsVisible(availableTrainsListModel.getSize() - 1);
                availableTrainsJList.ensureIndexIsVisible(selectedTrainIndex);
                availableTrainsJList.setSelectedIndex(selectedTrainIndex);
            }
        }
        pack();
    }

    @Override
    public void showNotification(String title, String text) {
        trayIcon.displayMessage(title, text, TrayIcon.MessageType.INFO);
    }

    @Override
    public void showTaxiInfoWindow() {
        JOptionPane.showMessageDialog(null, Settings.TAXI_INFO, "Про корпоративное такси", JOptionPane.INFORMATION_MESSAGE);

    }

    private static class TrainListCellRenderer extends DefaultListCellRenderer {

        ArrayList<Train> nearestTrains = new ArrayList<>();

        TrainListCellRenderer(ArrayList<Train> nearestTrains) {

            this.nearestTrains = nearestTrains;
        }

        ;

            @Override
        public Component getListCellRendererComponent(JList list, Object value,
                int index, boolean isSelected, boolean cellHasFocus
        ) {

            Color bgColor = null;
            boolean enabledListTrainItem = false;
            boolean isSelectedTrain = false;

            if (value instanceof Train) {
                Train train = (Train) value;
                bgColor = train.getColor();
                value = train.toStringWithFinalDestination();
                if (train.isSelected()) {
                    value = ">> " + value;
                    isSelectedTrain = true;
                }
                
                if (!nearestTrains.isEmpty()) {
                    for (Train currentNearestTrain : nearestTrains) {

                        if (train.getUniqueID().equals(currentNearestTrain.getUniqueID()) && currentNearestTrain.isEnoughTimeToCatchTrain()) {
                            enabledListTrainItem = true;

                            break;
                        }
                    }
                }

            }

            Component renderComponent = super.getListCellRendererComponent(
                    list, value, index, isSelected, cellHasFocus);

            if (bgColor != null) {
                renderComponent.setBackground(bgColor);

            }
            if (enabledListTrainItem) {
                renderComponent.setEnabled(true);
            } else {
                renderComponent.setEnabled(false);
            }

            if (isSelectedTrain) {
                Font boldFont = renderComponent.getFont().deriveFont(1);
                renderComponent.setFont(boldFont);
            }

            return renderComponent;
        }

    }
}
