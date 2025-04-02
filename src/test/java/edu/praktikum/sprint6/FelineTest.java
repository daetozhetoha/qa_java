package edu.praktikum.sprint6;


import com.example.Feline;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.List;
import static org.junit.Assert.assertEquals;


@RunWith(MockitoJUnitRunner.class)
public class FelineTest {

    @Spy
    private Feline feline;

    @Test
    public void getFamilyTest() {
        assertEquals("Название семейства не совпадает","Кошачьи", feline.getFamily());
    }

    @Test
    public void getKittensWithParameterTest() {
        assertEquals("Переданное значение не равно возвращаемому", 5, feline.getKittens(5));
    }

    @Test
    public void getKittensNoParamaterCallTest() {
        feline.getKittens();
        Mockito.verify(feline, Mockito.times(1)).getKittens(1);
    }

    @Test
    public void getKittensNoParameterReturns1Test() {
        assertEquals("Возвращаемое значение не совпадает",1, feline.getKittens());
    }

    @Test
    public void eatMeatTest() throws Exception {
        Mockito.when(feline.getFood("Хищник")).thenReturn(List.of("Животные", "Птицы", "Рыба"));
        List<String> actual = feline.eatMeat();
        assertEquals("Список еды не совпадает", List.of("Животные", "Птицы", "Рыба"), actual);
    }

    @Test(expected = Exception.class)
    public void getFoodUnknownAnimalKindThrowsExceptionTest() throws Exception {
        feline.getFood("Неизвестный вид");
    }
}
