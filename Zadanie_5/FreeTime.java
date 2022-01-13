import java.io.Serial;
import java.io.Serializable;

public class FreeTime implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    public boolean isFree;

    @Override
    public String toString() {
        return "FreeTime{" +
                "isFree=" + isFree +
                '}';
    }
}
