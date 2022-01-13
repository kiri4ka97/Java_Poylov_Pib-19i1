import java.io.Serial;
import java.io.Serializable;

public class Reception implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    public DateReception date;
    public TimeReception time;
    public int month;
    public int year;

    public Reception(DateReception date, TimeReception time, int month, int year) {
        this.date = date;
        this.time = time;
        this.month = month;
        this.year = year;
    }
}
