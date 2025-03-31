package edu.praktikum.sprint6;

import com.example.Feline;
import com.example.Lion;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class LionParamsTest {
    private String sex;
    private boolean expectedManePresence;
    private Lion lion;
    @Mock
    private Feline feline;

    public LionParamsTest(String sex, boolean expectedManePresence) {
        this.sex = sex;
        this.expectedManePresence = expectedManePresence;
    }

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        lion = new Lion(sex, feline);
    }

    @Parameterized.Parameters
    public static Object[][] data() {
        return new Object[][] {
                {"Самец", true},
                {"Самка", false}
        };
    }

    @Test
    public void lionDoesHaveManeTest() {
        assertEquals(expectedManePresence, lion.doesHaveMane());
    }

}
