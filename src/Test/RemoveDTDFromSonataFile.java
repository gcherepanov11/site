/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Test;

import static Test.RemoveDTDFromSonataFile.positionDTD;
import basepostgresluaxls.UUID;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 *
 * @author nazarov
 * Вырезанные куски лишнего кода для лучшего понимания что я тут сделал
 */

// удаляем не верный <!DOCTYPE SubAppType v. 1.3 >
public  class RemoveDTDFromSonataFile {
    
    static String patchD = "";

    private  RemoveDTDFromSonataFile() {
        this.patchD = "C:\\Users\\Nazarov\\Desktop\\Info_script_file_work\\Project_from_Lev\\FirstGen\\Design\\";
    }
    public RemoveDTDFromSonataFile(String patchD) {
        this.patchD = patchD;
    }
    
    static String doctype = "";
    static int positionDTD;
    
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, Exception{
    
    // --- Ниже реализация этого функционала ----
        
    RemoveDTDFromSonataFile testStart = new RemoveDTDFromSonataFile("C:\\Users\\Nazarov\\Desktop\\Info_script_file_work\\Project_from_Lev\\FirstGen\\Design\\ControlProgram.int");
    String patchF = patchD ;
    String documenWithoutDoctype = testStart.methodRead(patchF);// Так читаем и получаем преобразованные данные, 
      // так парсим что получили
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    factory.setNamespaceAware(true);
      // так преобразовываем строку в поток и скармливаем билдеру XML
    InputStream stream = new ByteArrayInputStream(documenWithoutDoctype.getBytes(StandardCharsets.UTF_8)); 
      // или так
      //InputStream in = org.apache.commons.io.IOUtils.toInputStream(source, "UTF-8");
    Document doc = factory.newDocumentBuilder().parse(stream);
    // Так передаем на переработку
    viewAllXML(doc);
    writeDocument(doc, patchF);
    testStart.returnToFileDtd(patchF);
    //System.out.println(testStart.methodRead(patchF)); 
    //testStart.writeWithoutDTD(patchF, methodRead(patchF)); // Так записываем без DTD
    }
    
    // --- Запись книги в файл ----
    static void writeDocument(Document document, String patchF) throws TransformerFactoryConfigurationError, TransformerConfigurationException, TransformerException {
        try {
            File file = new File(patchF);
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(new DOMSource(document), new StreamResult(file));
            
        } catch (TransformerException e) {
            e.printStackTrace(System.out);
        }
    }
    
    static void viewAllXML (Document document){
      // Получаем корневой элемент
            Node root = document.getDocumentElement();            
            System.out.println("List of books:");
            System.out.println();
            // Просматриваем все подэлементы корневого - т.е. книги
            NodeList books = root.getChildNodes();
            for (int i = 0; i < books.getLength(); i++) {
                Node book = books.item(i);
                // Если нода не текст, то это книга - заходим внутрь
                if (book.getNodeType() != Node.TEXT_NODE) {
                    NodeList bookProps = book.getChildNodes();
                                System.out.println(books.getLength());
                    for(int j = 0; j < bookProps.getLength(); j++) {
                        Node bookProp = bookProps.item(j);
                        // Если нода не текст, то это один из параметров книги - печатаем
                        if (bookProp.getNodeType() != Node.TEXT_NODE) {
                           // System.out.println(bookProp.getNodeName() + ":" + bookProp.getChildNodes().item(0).getTextContent());
                            for(int i1=0; i1 < bookProp.getChildNodes().getLength(); i1++){
                            System.out.println(bookProp.getNodeName() + ":" + bookProp.getChildNodes().item(i1).getTextContent());
                            }  
                        }
                    }
                    System.out.println("===========>>>>");
                }
            }
    }
    
    // Метод Парсера строк поиск Доктипа
    static String paternDOCTYPE(String st1, int pos){
     Pattern pattern2 = Pattern.compile("^(<!DOCTYPE.*)$"); // Патерн нашего ДокТипа
     Matcher matcher2 = pattern2.matcher(st1);
     if (matcher2.matches()){ 
         positionDTD = pos; // Так же вносим позицию от куда это взяли
         doctype = matcher2.group(1); // в глобальные переменную что собираемся затереть
         System.out.println(doctype);
         return "";  // Возвращаем пустую строку если нашли DOCTYPE 
     }else return st1;

    }
    
     //метод чтения файла
   static  public String methodRead(String path) throws InterruptedException{    
        String result_data = "";
        try {
        BufferedReader in = new BufferedReader(new FileReader(path));
        String str;
        int pos_str = 0;
        while ((str = in.readLine()) != null){
            result_data += paternDOCTYPE(str, pos_str) + "\n"; // Передаем строки в парсер обработчик
            ++pos_str;
        }
        in.close();
    } catch (IOException e) {
    }
    // тут сразу и записываем для тестов видимо было
    return result_data; // возвращаем преобразованную строку 
    }
   
   //метод записи файла и изначальных данных и DTD
    static public void  methodWrite(String path, String data)throws InterruptedException{   
        try {
               
        File resultName = new File(path + "_newfile"); //Файл с новой записью
        File tmpName = new File(path + "_original"); // это просто Имя
        File realName = new File(path); // Оригинальное имя
        
        BufferedWriter writer = new BufferedWriter(new FileWriter(resultName, true));
        int tmpPos =0;
        String resultTofile = "";
        for (String tmpStr : data.split("\n")){ // бъем строку что бы записать в нужное место что вырезали выше
          //writer.append(tmpStr);
          
          if (tmpPos == positionDTD){ // если позиция верная то внсим обратно доктипДТД
              resultTofile += doctype + "\n";
            } else resultTofile += tmpStr + "\n"; // таким способоб убираем пустую и вставляем нужную
          ++tmpPos;
        }        
        //writer.write(data); // это походу переписать
        writer.write(resultTofile); // Добавляем вновь сформированную строку в файл
        writer.close();
         // Удаляем  и переименовываем в удаленный файл
         if (tmpName.exists()){ // проверяем на существование бекапного файла
             if(tmpName.delete()){ // если он есть удаляем его
                System.out.println(tmpName.getName() + " is deleted!");
                realName.renameTo(tmpName); 
            }else{
                System.out.println("Delete operation is failed.");
            }
         } else realName.renameTo(tmpName); 
         resultName.renameTo(realName);
        //tmpName.renameTo(resultName);
    } catch (IOException e) {
    }
     }
    
     //метод записи файла без DTD
    static public void  writeWithoutDTD(String path, String data)throws InterruptedException{   
        try {
               
        File resultName = new File(path + "_newfile"); //Файл с новой записью
        File tmpName = new File(path + "_original"); // это просто Имя
        File realName = new File(path); // Оригинальное имя
        
        BufferedWriter writer = new BufferedWriter(new FileWriter(resultName, true));        
        writer.write(data); // это походу переписать
        writer.close();

         // Удаляем  и переименовываем в удаленный файл
         if (tmpName.exists()){ // проверяем на существование бекапного файла
             if(tmpName.delete()){ // если он есть удаляем его
                System.out.println(tmpName.getName() + " is deleted!");
                realName.renameTo(tmpName); 
            }else{
                System.out.println("Delete operation is failed.");
            }
         } else realName.renameTo(tmpName); 
         resultName.renameTo(realName);
        //tmpName.renameTo(resultName);
    } catch (IOException e) {
    }
     }
    
    //метод записи файла и изначальных данных и DTD(не доделал просто чуть подчищен выше метод)
    static public void  writeOnlyDtd(String path, String data)throws InterruptedException{   
        try {
               
        File resultName = new File(path + "_newfile"); //Файл с новой записью
        File tmpName = new File(path + "_original"); // это просто Имя
        File realName = new File(path); // Оригинальное имя

        BufferedWriter writer = new BufferedWriter(new FileWriter(resultName, true));
        int tmpPos =0;
        String resultTofile = "";
        for (String tmpStr : data.split("\n")){ // бъем строку что бы записать в нужное место что вырезали выше
          if (tmpPos == positionDTD){ // если позиция верная то внсим обратно доктипДТД
              resultTofile += doctype + "\n";
            } else resultTofile += tmpStr + "\n"; // таким способоб убираем пустую и вставляем нужную
          ++tmpPos;
        }        
        writer.write(resultTofile); // Добавляем вновь сформированную строку в файл
        writer.close();
         // Удаляем  и переименовываем в удаленный файл
         if (tmpName.exists()){ // проверяем на существование бекапного файла
             if(tmpName.delete()){ // если он есть удаляем его
                System.out.println(tmpName.getName() + " is deleted!");
                realName.renameTo(tmpName); 
            }else{
                System.out.println("Delete operation is failed.");
            }
         } else realName.renameTo(tmpName); 
         resultName.renameTo(realName);
        //tmpName.renameTo(resultName);
       
             

    } catch (IOException e) {
    }
     }
    
    //метод записи файла DOCTYPE который мы забрали
    public void  returnToFileDtd(String path)throws InterruptedException{  
         try {
                BufferedReader read = new BufferedReader(new FileReader(path));
                String str;
                int pos_str = 0;
                String resultTofile = "";
                while ((str = read.readLine()) != null){
                    if (pos_str == positionDTD){ // если позиция верная то внсим обратно доктипДТД
                    resultTofile += doctype + "\n";
                    resultTofile += str + "\n";
                    } else resultTofile += str + "\n"; // таким способоб убираем пустую и вставляем нужную
                    ++pos_str;
                }
                read.close();
                // прочитали сформировали и теперь его перепишем строкой
                BufferedWriter writer = new BufferedWriter(new FileWriter(new File(path), false)); // true если надо добавлять в конец файла
                writer.write(resultTofile); // Добавляем вновь сформированную строку в файл
                writer.close();
            } catch (IOException e) {
                }
     }
}
