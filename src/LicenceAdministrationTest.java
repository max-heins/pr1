import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class LicenceAdministrationTest {

    @Test
    void register() {
        LicenceAdministration licenceAdmin = new LicenceAdministration();
        Map<String, Car> want = new HashMap<>();
        want.put("ED-XY-99", new Car("Person", Maker.Audi, 1990));
        want.put("ED-YX-99", new Car("Larson", Maker.BMW, 2000));
        want.put("ED-ZZ-99", new Car("Johannson", Maker.Fiat, 2010));
        licenceAdmin.register(want);
        Map<String, Car> have = licenceAdmin.getPlatesToCar();
        assertEquals(want, have);

    }

    @Test
    void testRegister() {
        LicenceAdministration licenceAdmin = new LicenceAdministration();
        Map<String, Car> want = new HashMap<>();
        want.put("ED-XY-99", new Car("Person", Maker.Audi, 1990));
        licenceAdmin.register("ED-XY-99", new Car("Person", Maker.Audi, 1990));
        Map<String, Car> have = licenceAdmin.getPlatesToCar();
        assertEquals(want, have);
    }

    @Test
    void size() {
        LicenceAdministration licenceAdmin = new LicenceAdministration();
        licenceAdmin.register("ED-XY-99", new Car("Person", Maker.Audi, 1990));
        int want = 1;
        int have = licenceAdmin.size();
        assertEquals(want, have);

    }

    @Test
    void getPlatesToCar() {
    }

    @Test
    void getLicencePlates() {
        LicenceAdministration licenceAdmin = new LicenceAdministration();
        Map<String, Car> mp = new HashMap<>();
        mp.put("ED-XY-99", new Car("Person", Maker.Audi, 1990));
        mp.put("ED-YX-99", new Car("Larson", Maker.BMW, 2000));
        mp.put("ED-ZZ-99", new Car("Johannson", Maker.Fiat, 2010));
        licenceAdmin.register(mp);
        Set<String> want = new HashSet<String>();
        want.add("ED-XY-99");
        want.add("ED-YX-99");
        want.add("ED-ZZ-99");
        Set<String> have = licenceAdmin.getLicencePlates();
        assertEquals(want, have);
    }

    @Test
    void getLicencesOfOwner() {
        LicenceAdministration licenceAdmin = new LicenceAdministration();
        Map<String, Car> mp = new HashMap<>();
        mp.put("ED-XY-99", new Car("Person", Maker.Audi, 1990));
        mp.put("ED-YX-99", new Car("Larson", Maker.BMW, 2000));
        mp.put("ED-ZZ-99", new Car("Person", Maker.Fiat, 2010));
        licenceAdmin.register(mp);
        List<String> want = new ArrayList();
        want.add("ED-XY-99");
        want.add("ED-ZZ-99");
        List<String> have = licenceAdmin.getLicencesOfOwner("Person");
        assertEquals(want, have);
    }

    @Test
    void getLicencesOlderThan() {
        LicenceAdministration licenceAdmin = new LicenceAdministration();
        Map<String, Car> mp = new HashMap<>();
        mp.put("ED-XY-99", new Car("Person", Maker.Audi, 1990));
        mp.put("ED-YX-99", new Car("Larson", Maker.BMW, 2000));
        mp.put("ED-ZZ-99", new Car("Person", Maker.Fiat, 2010));
        licenceAdmin.register(mp);
        List<String> want = new ArrayList();
        want.add("ED-YX-99");
        want.add("ED-XY-99");
        List<String> have = licenceAdmin.getLicencesOlderThan(2005);
        assertEquals(want, have);
    }

    @Test
    void keepLicenceNewerThan() {
        LicenceAdministration licenceAdmin = new LicenceAdministration();
        Map<String, Car> mp = new HashMap<>();
        mp.put("ED-XY-99", new Car("Person", Maker.Audi, 1990));
        mp.put("ED-YX-99", new Car("Larson", Maker.BMW, 2000));
        mp.put("ED-ZZ-99", new Car("Person", Maker.Fiat, 2010));
        licenceAdmin.register(mp);
        licenceAdmin.keepLicenceNewerThan(1995);
        Map<String, Car> want = new HashMap<>();
        want.put("ED-YX-99", new Car("Larson", Maker.BMW, 2000));
        want.put("ED-ZZ-99", new Car("Person", Maker.Fiat, 2010));
        Map<String, Car> have = licenceAdmin.getPlatesToCar();
        assertEquals(want, have);
    }

    @Test
    void requireValidLicencePlate() {
        String want = "ED-AB-12";
        String have = LicenceAdministration.requireValidLicencePlate(want);
    }
}