package i18n;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.i18n.LocalizationService;

public class LocalizationServiceImplTest {

    @Test
    @DisplayName("Тестирование возвращаемого текста")
    void testLocale() {
        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        Mockito.when(localizationService.locale(Country.RUSSIA))
                .thenReturn("Добро пожаловать");
        Mockito.when(localizationService.locale(Country.USA))
                .thenReturn("Welcome");
        Mockito.when(localizationService.locale(Country.GERMANY))
                .thenReturn("Welcome");
        Mockito.when(localizationService.locale(Country.BRAZIL))
                .thenReturn("Welcome");

        Assertions.assertEquals("Добро пожаловать", localizationService.locale(Country.RUSSIA));
        Assertions.assertEquals("Welcome", localizationService.locale(Country.USA));
        Assertions.assertEquals("Welcome", localizationService.locale(Country.GERMANY));
        Assertions.assertEquals("Welcome", localizationService.locale(Country.BRAZIL));
    }
}
