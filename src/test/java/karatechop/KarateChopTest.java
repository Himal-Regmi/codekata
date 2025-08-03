package karatechop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static karatechop.KarateChop.karateChop;

public class KarateChopTest {
    @Test
    public void testKarateChop() {
        Assertions.assertEquals(-1, karateChop(3, new int[]{}));
        Assertions.assertEquals(-1, karateChop(3, new int[]{1}));
        Assertions.assertEquals(0, karateChop(1, new int[]{1}));

        Assertions.assertEquals(0, karateChop(1, new int[]{1, 3, 5}));
        Assertions.assertEquals(1, karateChop(3, new int[]{1, 3, 5}));
        Assertions.assertEquals(2, karateChop(5, new int[]{1, 3, 5}));
        Assertions.assertEquals(-1, karateChop(0, new int[]{1, 3, 5}));
        Assertions.assertEquals(-1, karateChop(2, new int[]{1, 3, 5}));
        Assertions.assertEquals(-1, karateChop(4, new int[]{1, 3, 5}));
        Assertions.assertEquals(-1, karateChop(6, new int[]{1, 3, 5}));


        Assertions.assertEquals(0, karateChop(1, new int[]{1, 3, 5, 7}));
        Assertions.assertEquals(1, karateChop(3, new int[]{1, 3, 5, 7}));
        Assertions.assertEquals(2, karateChop(5, new int[]{1, 3, 5, 7}));
        Assertions.assertEquals(3, karateChop(7, new int[]{1, 3, 5, 7}));
        Assertions.assertEquals(-1, karateChop(0, new int[]{1, 3, 5, 7}));
        Assertions.assertEquals(-1, karateChop(2, new int[]{1, 3, 5, 7}));
        Assertions.assertEquals(-1, karateChop(4, new int[]{1, 3, 5, 7}));
        Assertions.assertEquals(-1, karateChop(6, new int[]{1, 3, 5, 7}));
        Assertions.assertEquals(-1, karateChop(8, new int[]{1, 3, 5, 7}));
    }
}
