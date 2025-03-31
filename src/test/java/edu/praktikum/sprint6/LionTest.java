package edu.praktikum.sprint6;

import com.example.Feline;
import com.example.Lion;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class LionTest {

    @Mock
    private Feline feline;

    private Lion lion;

    @Before
    public void setUp() throws Exception {
        lion = new Lion("Самец") {
            @Override
            public int getKittens() {
                return feline.getKittens();
            }

            @Override
            public List<String> getFood() throws Exception {
                return feline.getFood("Хищник");
            }
        };
    }

    @Test
    public void getKittensTest() {
        Mockito.when(feline.getKittens()).thenReturn(5);
        assertEquals(5, lion.getKittens());
    }

    @Test
    public void getKittensFelineCalledTest() {
        lion.getKittens();
        Mockito.verify(feline, Mockito.times(1)).getKittens();
    }


    @Test
    public void getFoodTest() throws Exception {
        Mockito.when(feline.getFood("Хищник")).thenReturn(List.of("Животные", "Птицы", "Рыба"));
        List<String> actual = lion.getFood();
        assertEquals("Список еды не совпадает", List.of("Животные", "Птицы", "Рыба"), actual);
    }

    @Test
    public void getFoodFelineCalledTest() throws Exception {
        lion.getFood();
        Mockito.verify(feline, Mockito.times(1)).getFood("Хищник");
    }

    @Test(expected = Exception.class)
    public void getFoodThrowsExceptionTest() throws Exception {
        Mockito.when(feline.getFood("Хищник")).thenThrow(new Exception("Ошибка получения пищи"));
        lion.getFood();
    }

    @Test
    public void lionConstructorInvalidSexThrowsExceptionTest() {
        try {
            new Lion("Неизвестный");
        }catch (Exception e) {
            assertEquals("Сообщение об исключении отличается","Используйте допустимые значения пола животного - самец или самка", e.getMessage());
        }
    }
}
