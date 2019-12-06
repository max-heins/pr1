import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarTest {

    @org.junit.jupiter.api.Test
    void getOwner() {
        String want = "Person";
        Car testcar = new Car(want, Maker.Audi, 1980);
        String have = testcar.getOwner();
        assertEquals(want, have);
    }

    @org.junit.jupiter.api.Test
    void setOwner() {
        String want = "Larson";
        Car testcar = new Car("Person", Maker.BMW, 1990);
        testcar.setOwner("Larson");
        String have = testcar.getOwner();
        assertEquals("Larson", want, have);
    }

    @org.junit.jupiter.api.Test
    void getMaker() {
        Maker want = Maker.Fiat;
        Car testcar = new Car("Person", Maker.Fiat, 2000);
        Maker have = testcar.getMaker();
        assertEquals(want, have);
    }

    @org.junit.jupiter.api.Test
    void getBuildingYear() {
        int want = 1960;
        Car testcar = new Car("Person", Maker.Audi, want);
        int have = testcar.getBuildingYear();
        assertEquals(1960, want, have);
    }

    @org.junit.jupiter.api.Test
    void requireValidBuildingYear() {
        int want = 1970;
        int have = Car.requireValidBuildingYear(want);
        assertEquals(1970, want, have);
    }

    @org.junit.jupiter.api.Test
    void requireValidBuildingYearFail() {
        assertThrows(IllegalArgumentException.class, () -> {Car.requireValidBuildingYear(1900);});
    }

    @org.junit.jupiter.api.Test
    void requireValidOwner() {
        String want = "Johannson";
        String have = Car.requireValidOwner(want);
        assertEquals("Johannson", want, have);
    }

    @org.junit.jupiter.api.Test
    void requireValidOwnerFail() {
        assertThrows(IllegalArgumentException.class, () -> {Car.requireValidOwner(null);});
    }

    @org.junit.jupiter.api.Test
    void copyCar()
    {
        Car want = new Car("Person", Maker.Audi, 1980);
        Car have = new Car(want);
        assertEquals(want, have);
    }

    @org.junit.jupiter.api.Test
    void contructorMakerFail() {
        assertThrows(IllegalArgumentException.class, () -> {new Car("Person", null, 1980);});
    }
}