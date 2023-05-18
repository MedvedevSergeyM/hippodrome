import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class HorseTest {

    @Test
    void nameIsNullExceptionType() {
        assertThrows(IllegalArgumentException.class, () -> new Horse(null, 35, 123));
    }

    @Test
    void nameIsNullExceptionMessage() {
        try {
            new Horse(null, 35, 123);
        } catch (IllegalArgumentException e) {
            assertEquals("Name cannot be null.", e.getMessage());
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "   ", "\t", "\n", "\r", " \n  \n  \t   \n   \r "})
    void nameIsEmptyExceptionType(String name) {
        assertThrows(IllegalArgumentException.class, () -> new Horse(name, 0, 0));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "   ", "\t", "\n", "\r", " \n  \n  \t   \n   \r "})
    void nameIsEmptyExceptionMessage(String name) {
        try {
            new Horse(name, 35, 123);
        } catch (IllegalArgumentException e) {
            assertEquals("Name cannot be blank.", e.getMessage());
        }
    }

    @Test
    void speedIsNegativeExceptionType() {
        assertThrows(IllegalArgumentException.class, () -> new Horse("Вишня", -1, 123));
    }

    @Test
    void speedIsNegativeExceptionMessage() {
        try {
            new Horse("Вишня", -1, 123);
        } catch (IllegalArgumentException e) {
            assertEquals("Speed cannot be negative.", e.getMessage());
        }
    }

    @Test
    void distanceIsNegativeExceptionType() {
        assertThrows(IllegalArgumentException.class, () -> new Horse("Вишня", 35, -1));
    }

    @Test
    void distanceIsNegativeExceptionMessage() {
        try {
            new Horse("Вишня", 35, -1);
        } catch (IllegalArgumentException e) {
            assertEquals("Distance cannot be negative.", e.getMessage());
        }
    }

    @Test
    void getName() {
        Horse horse = new Horse("Вишня", 35, 123);
        assertEquals("Вишня", horse.getName());
    }

    @Test
    void getSpeed() {
        Horse horse = new Horse("Вишня", 35, 123);
        assertEquals(35, horse.getSpeed());
    }

    @Test
    void getDistance() {
        Horse horse = new Horse("Вишня", 35, 123);
        assertEquals(123, horse.getDistance());
    }

    @Test
    void moveIsCallGetRandomDouble() {
        try (MockedStatic<Horse> horseStatic =  Mockito.mockStatic(Horse.class)) {
            Horse horse = new Horse("Вишня", 35, 123);
            horse.move();
            horseStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

    @Test
    void moveAssignsDistanceValue() {
        double speed = 35;
        double distance = 123;
        double getRandomDoubleResult = 0.5;

        try (MockedStatic<Horse> horseStatic =  Mockito.mockStatic(Horse.class)) {
            horseStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(getRandomDoubleResult);
            Horse horse = new Horse("Вишня", speed, distance);
            horse.move();
            assertEquals(distance + speed * getRandomDoubleResult, horse.getDistance());
        }
    }
}