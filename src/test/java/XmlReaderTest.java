import com.offbytwo.jenkins.JenkinsServer;
import hello.utils.SgJenkinsServer;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.*;
import java.io.*;

/**
 * @创建人 sgwang
 * @name XmlReaderTest
 * @user Anti
 * @创建时间 2020/3/4
 * @描述
 */
public class XmlReaderTest {
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        String classpath = Thread.currentThread().getContextClassLoader().getResource("").getPath();

        File xmlFile = new File(classpath + "normal-config.xml");
        System.out.println("xmlFile: " + xmlFile.getPath());

        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(xmlFile)));
        String line = null;
        StringBuffer strBuffer = new StringBuffer();
        while ((line = reader.readLine()) != null) {
            strBuffer.append(line);
        }
        System.out.println(strBuffer.toString());

        JenkinsServer jenkinsServer = SgJenkinsServer.getInstance();
        jenkinsServer.createJob("pipeline-demo-02", strBuffer.toString());


    }
}
