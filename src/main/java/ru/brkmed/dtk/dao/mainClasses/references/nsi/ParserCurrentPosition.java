package ru.brkmed.dtk.dao.mainClasses.references.nsi;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import ru.brkmed.dtk.dao.mainClasses.entityes.CurrentPositionFRMR;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ParserCurrentPosition {
    public List<CurrentPositionFRMR> parseXML(File file) {
        List<CurrentPositionFRMR> listCP = new ArrayList<>( );
       /* SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance( );
            DocumentBuilder builder = factory.newDocumentBuilder( );
            Document document = builder.parse(file);
            NodeList nodeList = document.getChildNodes( );
            for (int i = 0; i < nodeList.getLength( ); i++) {
                // updateMessage("чтение файла");
                Node node = nodeList.item(i);
                if (node.getNodeType( ) != Node.TEXT_NODE) {
                    NodeList firstNode = node.getChildNodes( );
                    for (int j = 0; j < firstNode.getLength( ); j++) {
                        Node fNode = firstNode.item(j);
                        if (fNode.getNodeType( ) != Node.TEXT_NODE) {
                            NodeList secondNode = fNode.getChildNodes( );
                            for (int k = 0; k < secondNode.getLength( ); k++) {
                                Node sNode = secondNode.item(k);
                                if (sNode.getNodeType() == Node.ELEMENT_NODE) {
                                   NodeList treeNode = sNode.getChildNodes();
                                for(int l = 0; l < treeNode.getLength(); l++) {
                                    Node tNode = treeNode.item(l);
                                    Element elem = (Element) tNode;
                                    String s4 = elem.getTagName( );
                                    String s1 = elem.getNodeName( );
                                    String s3 = elem.getNodeValue();
                                    if(s3 != null ) {

                                        String s2 = null;

                                        Long idNSI = Long.parseLong(elem.getElementsByTagName("id")
                                                .item(k).getChildNodes( ).item(k).getNodeValue( ));

                                        String s = elem.getElementsByTagName("parent_id").item(0).getNodeValue( );

                                        Integer parentId = Integer.parseInt(elem.getElementsByTagName("parent_id")
                                                .item(0).getChildNodes( ).item(0).getNodeValue( ));
                                        String workPosition = elem.getElementsByTagName("work_position").item(0)
                                                .getChildNodes( ).item(0).getNodeValue( );
                                        Integer med = Integer.parseInt(elem.getElementsByTagName("med")
                                                .item(0).getChildNodes( ).item(0).getNodeValue( ));
                                        Date dateEnd = dateFormat.parse(elem.getElementsByTagName("date_end").item(k)
                                                .getChildNodes( ).item(k).getNodeValue( ));
                                        Integer form = Integer.parseInt(elem.getElementsByTagName("form_30")
                                                .item(0).getChildNodes( ).item(0).getNodeValue( ));
                                        Integer needCert = Integer.parseInt(elem.getElementsByTagName("need_cert")
                                                .item(0).getChildNodes( ).item(0).getNodeValue( ));
                                        Integer iducat = Integer.parseInt(elem.getElementsByTagName("educ")
                                                .item(0).getChildNodes( ).item(0).getNodeValue( ));
                                        Integer zemDoc = Integer.parseInt(elem.getElementsByTagName("zs")
                                                .item(0).getChildNodes( ).item(0).getNodeValue( ));
                                        Integer showList = Integer.parseInt(elem.getElementsByTagName("show_staff_list")
                                                .item(0).getChildNodes( ).item(0).getNodeValue( ));
                                        Integer fedCode = Integer.parseInt(elem.getElementsByTagName("federal_code")
                                                .item(0).getChildNodes( ).item(0).getNodeValue( ));
                                        listCP.add(new CurrentPositionFRMR(idNSI, parentId, workPosition, med, dateEnd, form, needCert,
                                                iducat, zemDoc, showList, fedCode));
                                    }
                                }

                                }
                            }
                        }
                    }

                }
            }
        } catch (ParserConfigurationException | SAXException | ParseException | IOException e) {
            e.printStackTrace( );
        }*/

        try {
            // Создается построитель документа
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            // Создается дерево DOM документа из файла
            Document document = documentBuilder.parse(file);

            // Получаем корневой элемент
            Node root = document.getDocumentElement();
            SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");

            // Просматриваем все подэлементы корневого - т.е. книги
            NodeList books = root.getChildNodes();
            for (int i = 0; i < books.getLength(); i++) {
                Node book = books.item(i);
                // Если нода не текст, то это книга - заходим внутрь
                if (book.getNodeType() != Node.TEXT_NODE) {
                    NodeList bookProps = book.getChildNodes();
                    for (int j = 0; j < bookProps.getLength(); j++) {


                        Node bookProp = bookProps.item(j);
                        // Если нода не текст, то это один из параметров книги - печатаем
                        if (bookProp.getNodeType() != Node.TEXT_NODE) {
                            CurrentPositionFRMR current = new CurrentPositionFRMR(  );
                            // System.out.println(bookProp.getNodeName() + ":" + bookProp.getChildNodes().item(0).getTextContent());
                            NodeList inside = bookProp.getChildNodes();
                            for (int k = 0; k < inside.getLength(); k++) {
                                Node nodeInside = inside.item(k);

                                switch (nodeInside.getNodeName()) {
                                    case ("ID"):
                                        current.setIdNsi(Integer.parseInt(nodeInside.getChildNodes().item(0).getTextContent()));
                                        break;
                                    case ("PID_1002"):
                                        current.setParentId(Integer.parseInt(nodeInside.getChildNodes().item(0).getTextContent()));
                                        break;
                                    case ("NAME_1002"):
                                        current.setWorkPosition(nodeInside.getChildNodes().item(0).getTextContent());
                                        break;
                                    case ("ID_181"):
                                        current.setMed(Integer.parseInt(nodeInside.getChildNodes().item(0).getTextContent()));
                                        break;


                                }

                            }
                            listCP.add(current);
                        }
                    }

                }

            }

        } catch (ParserConfigurationException ex) {
            ex.printStackTrace(System.out);
        } catch (SAXException ex) {
            ex.printStackTrace(System.out);
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }


        //updateMessage("файл прочитан");
        return listCP;
    }

//    @Override
//    protected Void call() throws Exception {
//        List<CurrentPositionFRMR> current = parseXML(ControlerLoadPositionFRMR.getFile());
//        ControlerPositionDaoFRMR position = new ControlerPositionDaoFRMR();
//        updateMessage("загрузка файла");
//        position.loadNsi(current);
//        updateMessage("загрузка файла выполнена");
//        return null;
//    }
}
