package geo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoServiceImpl;

public class GeoServiceImplTest {

    @Test
    @DisplayName("Тестирование определения локации по ip")
    void testLocationByIp() {
        GeoServiceImpl geoService = new GeoServiceImpl();

        Location location1 = new Location(null, null, null, 0);
        Location location2 = new Location("Moscow", Country.RUSSIA, "Lenina", 15);
        Location location3 = new Location("New York", Country.USA, " 10th Avenue", 32);
        Location location4 = new Location("Moscow", Country.RUSSIA, null, 0);
        Location location5 = new Location("New York", Country.USA, null,  0);

        Assertions.assertEquals(location1.getCity(), geoService.byIp("127.0.0.1").getCity());
        Assertions.assertEquals(location2.getCity(), geoService.byIp("172.0.32.11").getCity());
        Assertions.assertEquals(location3.getCity(), geoService.byIp("96.44.183.149").getCity());
        Assertions.assertEquals(location4.getCity(), geoService.byIp("172.0.0.1").getCity());
        Assertions.assertEquals(location5.getCity(), geoService.byIp("96.0.0.1").getCity());
    }
}
