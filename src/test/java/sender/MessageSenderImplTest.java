package sender;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.i18n.LocalizationService;
import ru.netology.sender.MessageSenderImpl;

import static org.mockito.ArgumentMatchers.startsWith;

import java.util.HashMap;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
public class MessageSenderImplTest {

    @Mock
    private GeoService geoService;

    @Mock
    private LocalizationService localizationService;

    @Test
    @DisplayName("Тестирование отправки текста на русском языке, если ip относится к российскому сегменту адресов")
    void sendMessageRussia() {
        Location location = new Location(null, Country.RUSSIA, null, 0);

        Mockito.when(geoService.byIp(startsWith("172.")))
                .thenReturn(location);

        Mockito.when(localizationService.locale(Country.RUSSIA))
                .thenReturn("Добро пожаловать");

        MessageSenderImpl messageSender = new MessageSenderImpl(geoService, localizationService);
        Map<String, String> headers = new HashMap<>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "172.0.32.11");
        Assertions.assertEquals("Добро пожаловать", messageSender.send(headers));
    }

    @Test
    @DisplayName("Тестирование отправки текста на английском языке, если ip относится к американскому сегменту адресов")
    void sendMessageUsa() {
        Location location = new Location(null, Country.USA, null, 0);

        Mockito.when(geoService.byIp(startsWith("96.")))
                .thenReturn(location);

        Mockito.when(localizationService.locale(Country.USA))
                .thenReturn("Welcome");

        MessageSenderImpl messageSender = new MessageSenderImpl(geoService, localizationService);
        Map<String, String> headers = new HashMap<>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "96.44.183.149");
        Assertions.assertEquals("Welcome", messageSender.send(headers));
    }
}

