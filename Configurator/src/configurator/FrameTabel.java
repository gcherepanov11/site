/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package configurator;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.xml.parsers.ParserConfigurationException;

/**
 *
 * @author cherepanov
 */
public class FrameTabel extends javax.swing.JPanel {
    Main_JPanel mj=new Main_JPanel();
    
    int filepath;
    String filepatch;
   // String signal=mj.signal;
    //Main_JPanel signals;

    XMLSAX createXMLSax = new XMLSAX();

    DataBase workbase = DataBase.getInstance();

    public FrameTabel() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(999, 530));

        jTable1.setModel(getTableData());
        jScrollPane1.setViewportView(jTable1);

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 964, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jButton1)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jButton1)
                .addGap(17, 17, 17)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 451, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("tab1", jPanel2);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1000, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 530, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents


    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       

////        JFileChooser fileload = new JFileChooser();
////
////        fileload.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);//эта строка отвечает за путь файла
////        filepath = fileload.showOpenDialog(this);//эта строка отвечает за само открытие
////        if (filepath == JFileChooser.APPROVE_OPTION) {
////            String filename = fileload.getSelectedFile().getName();//записывает в переменную путь,так что теперь надо обЪяснить методу как его юзать 
////            try {
////                filepatch = fileload.getSelectedFile().getCanonicalPath();
////            } catch (IOException ex) {
////                Logger.getLogger(Main_JPanel.class.getName()).log(Level.SEVERE, null, ex);
////
////            }
////           
////
////            switch (signal) {
////                case "ai1":
////                    try {
////
////                        createXMLSax.runBaseRuncreateTypeT(filepatch);
////                    } catch (ParserConfigurationException ex) {
////                        Logger.getLogger(Main_JPanel.class.getName()).log(Level.SEVERE, null, ex);
////                    }
////                    break;
////                case "ao1":
////                    try {
////
////                        createXMLSax.runBaseRuncreateType(filepatch);
////                    } catch (ParserConfigurationException ex) {
////                        Logger.getLogger(Main_JPanel.class.getName()).log(Level.SEVERE, null, ex);
////                    }
////                    break;
////                case "do1":
////                    try {
////                        createXMLSax.runBaseRuncreateTypeDSig(filepatch);
////                    } catch (ParserConfigurationException ex) {
////                        Logger.getLogger(Main_JPanel.class.getName()).log(Level.SEVERE, null, ex);//доволен не дововлен 
////                    }
////                    break;
////                case "di1":
////                    try {
////                        createXMLSax.runBaseRuncreateTypeDSign(filepatch);
////                    } catch (ParserConfigurationException ex) {
////                        Logger.getLogger(Main_JPanel.class.getName()).log(Level.SEVERE, null, ex);
////                    }
////            }
////        }
    }//GEN-LAST:event_jButton1ActionPerformed
    TableModel getTableData() { // функция для создания списка из талиц базы так же возращаем объект для конструкции таблицы при запуске
        // Можно так сложно не соединять, аппендицит от предыдущего что бы не запутаться
        String[] columnDop = {"Выбор"};// до поля для галок или еще чего
        String[] columnNames = StructSelectData.getColumns();
        String[] resultColumn = Stream.concat(Arrays.stream(columnDop), Arrays.stream(columnNames))
                .toArray(String[]::new); // соединяем два массива
        Object[][] data = StructSelectData.getcurrentSelectTable(); // От куда беру данные
        Object[] streamArray;
        Object[] streamNull = new Object[1];
        streamNull[0] = null;
        Object[][] tmp2 = new Object[data.length][];
        for (int i = 0; i < data.length; i++) {
            streamArray = new Object[data[i].length + 1];
            // преобразовываем массив
            Object[] testStream = Stream.concat(Arrays.stream(streamNull), Arrays.stream(data[i])).toArray(Object[]::new);
            tmp2[i] = testStream;
        }
        return new DefaultTableModel(tmp2, resultColumn) {
            @Override
            public Class<?> getColumnClass(int columnIndex) { // структура для отображения таблицы с галками
                Class clazz = String.class;
                switch (columnIndex) {
                    case 0:
                        clazz = Boolean.class;
                        break;
                }
                return clazz;
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return column == column;
            }

            @Override
            public void setValueAt(Object aValue, int row, int column) {
                // Условие проверки галочки скрывать легенду
                if (aValue instanceof Boolean && column == 0) {
                    System.out.println("Posution - > " + row + " " + aValue);
                    Vector rowData = (Vector) getDataVector().get(row); // не помню для чего но без этого только скрывает =(
                    rowData.set(0, (boolean) aValue);
                    fireTableCellUpdated(row, column);

                    try {
                        // Само действие не реализованно
                        if ((boolean) aValue == true) {
                            System.out.println("true");
                        }
                        if ((boolean) aValue == false) {
                            System.out.println("false");
                        }
                    } catch (NullPointerException e) {
                        JOptionPane.showMessageDialog(null, "Трудности с добавлением");
                    }
                }

            }
        };
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
