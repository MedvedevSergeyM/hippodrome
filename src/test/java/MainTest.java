import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

class MainTest {

    @Test
    @Timeout(value = 22)
    @Disabled
    @DisplayName("Выполняется не дольше 22 секунд")
    void testMain () throws Exception {
        Main.main(null);
    }

}