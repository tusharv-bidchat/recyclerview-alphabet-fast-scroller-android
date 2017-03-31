package viethoa.com.fastcroll.alphabet;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/**
 * Created by VietHoa on 07/10/15.
 */
public class DataHelper {

    public static List<String> getAlphabetData() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -1);
        cal.add(Calendar.DAY_OF_MONTH, -1);

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);

        List<String> days = new ArrayList<>();
        while (cal.before(Calendar.getInstance()))
        {
            days.add(dateFormat.format(cal.getTime()));
            cal.add(Calendar.DAY_OF_MONTH, 1);
        }
        return days;
    }
}
