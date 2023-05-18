import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class HippodromeTest {

    @Test
    void horsesIsNullExceptionType() {
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
    }

    @Test
    void horsesIsNullExceptionMessage() {
        try {
            new Hippodrome(null);
        } catch (IllegalArgumentException e) {
            assertEquals("Horses cannot be null.", e.getMessage());
        }
    }

    @Test
    void horsesIsEmptyExceptionType() {
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<>()));
    }

    @Test
    void horsesIsEmptyExceptionMessage() {
        try {
            new Hippodrome(new ArrayList<>());
        } catch (IllegalArgumentException e) {
            assertEquals("Horses cannot be empty.", e.getMessage());
        }
    }

    @Test
    @DisplayName("Возвращает список, который был передан в конструктор")
    void getHorses() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            horses.add(new Horse("Horse_" + i, Math.random() * 10, Math.random() * 100));
        }

        Hippodrome hippodrome = new Hippodrome(horses);

        for (int i = 0; i < 30; i++) {
            assertEquals(horses.get(i), hippodrome.getHorses().get(i));
        }
    }

    @Test
    @DisplayName("Вызывает метод move у всех лошадей")
    void move() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            horses.add(Mockito.mock(Horse.class));
        }

        Hippodrome hippodrome = new Hippodrome(horses);
        hippodrome.move();

        for (int i = 0; i < 50; i++) {
            Mockito.verify(horses.get(i)).move();
        }
    }

    @Test
    @DisplayName("Возвращает лошадь с самым большим значением distance")
    void getWinner() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            horses.add(new Horse("Horse_" + i, Math.random() * 10, Math.random() * 100));
        }

        Hippodrome hippodrome = new Hippodrome(horses);

        for (int i = 0; i < 20; i++) {
            hippodrome.move();
        }

        Horse winnerHorse = horses.stream()
                .max(Comparator.comparing(Horse::getDistance))
                .get();

        assertEquals(winnerHorse, hippodrome.getWinner());
    }

}