package util;

import org.supercsv.io.CsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CSVManager extends CsvBeanWriter {

    public CSVManager(HttpServletResponse response, CsvPreference csvPreference) throws IOException {
        super(response.getWriter(), csvPreference);
        response.setContentType("text/csv");
    }
}