/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package basepostgresluaxls;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

/**
 *
 * @author admin
 */
public class Main_JPanel extends javax.swing.JPanel {

    BasePostgresLuaXLS workbase = new BasePostgresLuaXLS(); // подключаем нашу базу
    CreateFileIO_AILUA createF = new CreateFileIO_AILUA();
    LuaRun startScript = new LuaRun(); // Тут Луа 
    CreateTGPAAI gpaai = new CreateTGPAAI(); // создание файла и тут есть переменные с UUID
    ArrayList<String> listDropT = new ArrayList();


    /**
     * Creates new form Main_JPanel
     */
    public Main_JPanel() {
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

        jButton1 = new javax.swing.JButton();
        jTextField2 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox();

        setAutoscrolls(true);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jButton1.setText("out_dataDB");
        jButton1.setToolTipText("Выгрузка данных из базы для файла LUA");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTextField2.setText("jTextField2");
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jButton2.setText("run_lua");
        jButton2.setToolTipText("Запустить скрипт");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Download XLS in DB");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Create_type_AI");
        jButton4.setToolTipText(" create file Type_GPA_AI_from_java  Type_List_GPA_AI_from_java");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("ListTableDB");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("DropTable");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setText("Global_Var_in XML_file");
        jButton7.setToolTipText("(не реализованно)вносим данные в Algorithm файла ControlProgram.int и ControlProgram.iec_st \\n доступоно после создание кнопки Create_type_AI");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jComboBox1.setModel(getComboBoxModel());
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTextField2)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addGap(188, 188, 188)
                                .addComponent(jButton5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton6))
                            .addComponent(jButton3)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 291, Short.MAX_VALUE)
                        .addComponent(jButton2)
                        .addGap(48, 48, 48))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton5)
                    .addComponent(jButton6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4)
                    .addComponent(jButton7)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        //jButton1.setToolTipText(" данные из Базы"); // не работает тут, нужно через Свойства
        JFileChooser fileopen = new JFileChooser("C:\\Users\\Nazarov\\Desktop\\Info_script_file_work\\_actual_config\\Config\\Design\\IO_XLS\\GPA");
        int ret = fileopen.showDialog(null, "Открыть файл");                
        if (ret == JFileChooser.APPROVE_OPTION) {
        File file = fileopen.getSelectedFile();
        //System.out.print(file.getPath());
        jTextField2.setText(file.getPath()); //вносим данные в поле путь до файла
        // select Database test and write to file from Lua
        
        ArrayList<String[]> dataFromDb = new ArrayList<>();
        workbase.connectionToBase();
        dataFromDb = workbase.selectData("ai1");
            try {
                createF.writeData(dataFromDb, file.getPath()); // write file
            } catch (IOException ex) {
                Logger.getLogger(Main_JPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        javax.swing.JOptionPane.showMessageDialog(null,"Данные из базы в файл " + file.getPath() + " загружены"); //диалоговое окно


          
   
}
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        JFileChooser fileopen = new JFileChooser("C:\\Users\\Nazarov\\Desktop\\Info_script_file_work\\_actual_config\\Config\\Design\\LUA_FILE\\Template\\AI");
        int ret = fileopen.showDialog(null, "Запустить Lua файл");                
        if (ret == JFileChooser.APPROVE_OPTION) {
        File file = fileopen.getSelectedFile();
        //System.out.print(file.getPath());
        jTextField2.setText(file.getPath()); //вносим данные в поле путь до файла
        // select Database test and write to file from Lua
        
        startScript.runLua(file.getPath());
    }//GEN-LAST:event_jButton2ActionPerformed
    }
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        JFileChooser fileopen = new JFileChooser("C:\\Users\\Nazarov\\Desktop\\Info_script_file_work\\_actual_config\\Config\\Design\\IO_XLS\\GPA");
        int ret = fileopen.showDialog(null, "Загрузка Exel в базу");                
        if (ret == JFileChooser.APPROVE_OPTION) {
        File file = fileopen.getSelectedFile();
        
        try {
            // TODO add your handling code here:
            //загрузка в базу изх файла
            Main.fillDB(file.getPath());
        }
       catch(IOException e){
            System.out.println(e);
        }
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        workbase.connectionToBase();
        ArrayList<String[]> dataFromDbGPAI = new ArrayList<>();
        dataFromDbGPAI = workbase.selectDataGPAAI("ai1");
        try {
            gpaai.writeAI_();
            //gpaai.writeListData(15); // write file 
            gpaai.writeAI_PLC();
            gpaai.writeAI_HMI();
            gpaai.T_GPA_AI_PLC(dataFromDbGPAI, "T_GPA_AI_PLC"); // должны быть после создания выше
            gpaai.T_GPA_AI_HMI(dataFromDbGPAI, "T_GPA_AI_HMI");
            gpaai.T_GPA_AI_DRV(dataFromDbGPAI, "T_GPA_AI_DRV");
            
        }
        catch(IOException e){
            System.out.println(e);
        }


    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        // выводим список таблиц из базы test08_DB
        String db = "test08_DB";
        workbase.connectionToBase();
        listDropT = workbase.getviewTable(db);
        Iterator<String> iter_list_table = listDropT.iterator();
        String listTable = "";
       
        while (iter_list_table.hasNext()) {
         
         listTable += iter_list_table.next() + " \n";
        }
         
         
               
        jTextField2.setText( listTable);
        
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        workbase.connectionToBase();
        if (!listDropT.isEmpty()){  // если есть что удалять передаем лист в обработчик баз
        workbase.dropTable(listDropT);
        }
        else ;
        
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
                // TODO add your handling code here:
        workbase.connectionToBase();
        ArrayList<String[]> dataFromDbGPAI = new ArrayList<>();
        dataFromDbGPAI = workbase.selectDataGPAAI("ai1");
        try {
            gpaai.writeAI_();
            //gpaai.writeListData(15); // Это было первое нужно или удалить или забыть
            gpaai.writeAI_PLC();
            gpaai.writeAI_HMI();
            gpaai.T_GPA_AI_PLC(dataFromDbGPAI, "T_GPA_AI_PLC"); // должны быть после создания выше
            gpaai.T_GPA_AI_HMI(dataFromDbGPAI, "T_GPA_AI_HMI");
            gpaai.T_GPA_AI_DRV(dataFromDbGPAI, "T_GPA_AI_DRV");
            
        }
        catch(IOException e){
            System.out.println(e);
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
        jTextField2.setText((String)jComboBox1.getSelectedItem());// выводим что выбрали
        ArrayList<String[]> dataFromDb = new ArrayList<>();
        String[] columns = {"uuid_plc","colum_18"};
        workbase.connectionToBase();
        workbase.selectData((String)jComboBox1.getSelectedItem(), columns); //внесли данные в сущность 
        dataFromDb = workbase.getcurrentSelectTable();
        //javax.swing.JOptionPane.showMessageDialog(null,"Выборка по базе " + columns + " загружены"); //диалоговое окно
        
        // тут при выборе открываем новое Диалоговое окно с таблицой выборки
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();  //размеры экрана
        int sizeWidth = 800;
        int sizeHeight = 600;
        int locationX = (screenSize.width - sizeWidth) / 2;
        int locationY = (screenSize.height - sizeHeight) / 2;
        FrameTable frameTable = new FrameTable();
        JFrame frame = new JFrame();
        frame.setBounds(locationX, locationY, sizeWidth, sizeHeight); // Размеры и позиция
        frame.setContentPane(frameTable); // Передаем нашу форму
        frame.setVisible(true);
    }//GEN-LAST:event_jComboBox1ActionPerformed
    

private ComboBoxModel getComboBoxModel()  // функция для создания списка из таблиц базы
{ 
        String db = "test08_DB";
        workbase.connectionToBase();
        listDropT = workbase.getviewTable(db);
        Iterator<String> iter_list_table = listDropT.iterator();
        
         String listTable = "";
       int l =0;
        while (iter_list_table.hasNext()) {
         iter_list_table.next();
         //System.out.print(l);
         ++l;
        }
        String[] listarrayTable = new String[l];
        l=0;
        
        iter_list_table = listDropT.iterator();
        while (iter_list_table.hasNext()) {
         String res =iter_list_table.next();
         listarrayTable[l] = res;
         ++l;
        }
        return new DefaultComboBoxModel(listarrayTable); 
} 
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
}