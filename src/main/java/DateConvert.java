import javax.xml.bind.ValidationEventHandler;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Khartonov Oleg on 21.05.2016.
 */
public class DateConvert extends XmlAdapter<String, Timestamp> {
    private final SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
    /**
     * Convert a value type to a bound type.
     *
     * @param v The value to be converted. Can be null.
     * @throws Exception if there's an error during the conversion. The caller is responsible for
     *                   reporting the error to the user through {@link ValidationEventHandler}.
     */
    @Override
    public Timestamp unmarshal(String v) throws Exception {
        return new Timestamp(format.parse(v).getTime());
    }

    /**
     * Convert a bound type to a value type.
     *
     * @param v The value to be convereted. Can be null.
     * @throws Exception if there's an error during the conversion. The caller is responsible for
     *                   reporting the error to the user through {@link ValidationEventHandler}.
     */
    @Override
    public String marshal(Timestamp v) throws Exception {
        return format.format(new Date(v.getTime()));
    }
}
