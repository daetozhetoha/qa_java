package edu.praktikum.sprint6;

import com.example.Cat;
import com.example.Feline;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class CatTest {

    @Mock
    private Feline feline;

    private Cat cat;

    @Before
    public void setUp() {
        cat = new Cat(feline);
    }

    @Test
    public void getSoundTest() {
        assertEquals("Кошки произносят другой звук","Мяу", cat.getSound());
    }

    @Test
    public void getFoodTest() throws Exception {
        Mockito.when(feline.eatMeat()).thenReturn(List.of("Животные", "Птицы", "Рыба"));
        List<String> actual = cat.getFood();
        assertEquals("Список еды не совпадает", List.of("Животные", "Птицы", "Рыба"), actual);

    }

    @Test(expected = Exception.class)
    public void getFoodThrowsExceptionTest() throws Exception {
        Mockito.when(feline.eatMeat()).thenThrow(new Exception("Ошибка получения пищи"));
        cat.getFood();
    }
}
