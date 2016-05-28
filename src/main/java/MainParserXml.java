import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.sax.SAXSource;
import java.io.File;

/**
 * Created by Khartonov Oleg on 21.05.2016.
 */
@Component
public class MainParserXml {
    public DBHelper dbHelper;

    @Autowired
    public MainParserXml (DBHelper dbHelper) {
        this.dbHelper=dbHelper;
    }

    public static void main(String[] args) throws Exception {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(AppConfig.class);
        ctx.refresh();
        ctx.getBean(MainParserXml.class).run();
    }

    @Bean
    public void run() throws Exception {
        JAXBContext context = JAXBContext.newInstance(TransactionInfo.class);
        SAXParserFactory saxParser = SAXParserFactory.newInstance();
        XMLReader xmlReader = saxParser.newSAXParser().getXMLReader();
        InputSource inputSource = new InputSource(new File("D:\\github\\parserXml\\src\\main\\resources\\7935.xml").toString());
        SAXSource source = new SAXSource(xmlReader, inputSource);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        TransactionInfo transactionInfo = (TransactionInfo) unmarshaller.unmarshal(source);
        transactionInfo.setRequestBlob(new File("D:\\github\\parserXml\\src\\main\\resources\\7935.xml").toString());
        dbHelper.addTxns(transactionInfo);
    }
}
