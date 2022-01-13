import java.io.Serial;
import java.io.Serializable;

public class MedicalCard implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    String cardId;
    public MedicalCard(){ }

    public MedicalCard(String cardId){
        this.cardId = cardId;
    }

    @Override
    public String toString() {
        return "мед карта с номером: " + cardId;
    }
}
