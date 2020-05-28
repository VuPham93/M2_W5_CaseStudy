package Library;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class UserManagement {
    private Path filePath;
    private Document document;

    public UserManagement() {
        filePath = Paths.get(System.getProperty("user.dir"), "files", "users.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try {
            builder = dbFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        this.document = builder.newDocument();
    }

    public boolean isFileExist(){
        if(Files.exists(filePath)){
            return true;
        }else{
            return false;
        }

    }

    public void createFile(String name,String username,String pass){
        try {

            File myFile= new File(String.valueOf(filePath));
            myFile.setWritable(true);
            myFile.setReadable(true);
            myFile.createNewFile();


            Element rootElement=document.createElement("information");
            document.appendChild(rootElement);

            appendUserInfo(document,rootElement,name,username,pass);


        }   catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void appendDocument(String name,String user,String pass){

        try {
            DocumentBuilderFactory dbFactory= DocumentBuilderFactory.newInstance();
            DocumentBuilder builder= dbFactory.newDocumentBuilder();
            document = builder.parse(String.valueOf(filePath));
            Element rootElement= document.getDocumentElement();
            System.out.println(document.getNodeName());
            System.out.println(rootElement.getTagName());

            appendUserInfo(document,rootElement,name,user,pass);


        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    public void appendUserInfo(Document document,Element rootElement,String name,String username,String pass) throws TransformerException {

        Element user= document.createElement("user");

        Element fullName=document.createElement("fullname");
        fullName.setTextContent(name);

        Element userName= document.createElement("username");
        userName.setTextContent(username);

        Element passwordElement= document.createElement("password");
        passwordElement.setTextContent(pass);

        user.appendChild(fullName);
        user.appendChild(userName);
        user.appendChild(passwordElement);
        rootElement.appendChild(user);

        TransformerFactory tfFactory=TransformerFactory.newInstance();
        Transformer transformer =tfFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        DOMSource source=new DOMSource(document);
        StreamResult result=new StreamResult(new File(String.valueOf(filePath)));
        transformer.transform(source,result);


    }

    public boolean isUserExist(String userName) {

        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = dbFactory.newDocumentBuilder();
            this.document = builder.parse(String.valueOf(filePath));
            XPath xPath = XPathFactory.newInstance().newXPath();
            String url = "/information/user/username";
            NodeList nList = (NodeList) xPath.compile(url).evaluate(document, XPathConstants.NODESET);
            boolean userExist = false;
            for (int i = 0; i < nList.getLength(); i++) {
                Element element = (Element) nList.item(i);
                System.out.println(element.getTextContent());
                if (element.getTextContent().equals(userName)) {
                    userExist = true;
                    break;
                }
            }
            return userExist;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
