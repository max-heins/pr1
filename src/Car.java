import java.time.LocalDate;
import java.util.Objects;

/** Ein einfaches Auto.
 * @author Gudrun Schiedermeier, gschied@haw-landshut.de
 */
public class Car {
    /** Fruehestes gueltiges Zulassungsjahr.     */
    private static final int EARLIEST_BUILDING_YEAR = 1950;

    /** Eigentuemer eines Autos, nicht null, nicht leer.     */
    private String owner;

    /** Hersteller eines Autos, nicht null.  */
    private final Maker maker;

    /** Baujahr des Autos, nicht vor EARLIEST_BUILDING_YEAR, nicht nach aktuellem Jahr.  */
    private final int buildingYear;

    /** Erzeugt ein neues Auto.
     * @param owner der Eigentuermer, nicht null.
     * @param maker der Herstelle, nicht null.
     * @param buildingYear ein gueltiges Baujahr.
     */
    public Car(String owner, Maker maker, int buildingYear) {
        Car.requireValidOwner(owner);
        this.owner = owner;
        if (maker == null)
        {
            throw new IllegalArgumentException();
        }
        this.maker = maker;
        this.buildingYear = requireValidBuildingYear(buildingYear);
    }

    /** Kopiert ein anderes Auto.
     * @param other ein anderes Auto.
     */
    public Car(Car other) {
        this.owner = other.getOwner();
        this.maker = other.getMaker();
        this.buildingYear = other.getBuildingYear();
    }

    public String getOwner() {
        return this.owner;
    }

    /** Setzt den Eigentuemer des Autos.
     * @param owner der Eigentuemer des Autos, nicht null, nicht leer.
     */
    public void setOwner(String owner) {
        this.owner = requireValidOwner(owner);
    }

    public Maker getMaker() {
        return this.maker;
    }

    public int getBuildingYear() {
        return this.buildingYear;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + Objects.hashCode(this.owner);
        hash = 47 * hash + Objects.hashCode(this.maker);
        hash = 47 * hash + this.buildingYear;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final Car other = (Car) obj;
        if (buildingYear != other.buildingYear)
            return false;
        if (!owner.equals(other.owner))
        {
            return false;
        }
        if (!maker.equals(other.maker))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Car [owner=" + owner + ", maker=" + maker + ", buildingYear=" + buildingYear + "]";
    }

    /** Ueberprueft das Herstellungsjahr auf gueltige Werte.
     * @param year das Herstellungsjahr, nicht vor EARLIEST_BUILDING_YEAR, nicht nach aktuellem Jahr.
     * @return ein gueltiges Herstellungsjahr.
     */
    public static int requireValidBuildingYear(int year) {
        if ((year < EARLIEST_BUILDING_YEAR) || (year > java.time.LocalDate.now().getYear()))
        {
            throw new IllegalArgumentException();
        }
        return year;
    }

    /** Ueberprueft den Eigner des Autos auf einen gueltigen Wert.
     * @param string der Eigner des Autos, nicht null, nicht leer.
     * @return der gueltige Name des Eigners.
     */
    public static String requireValidOwner(String string) {
        if ((string == null) || (string.length() == 0))
        {
            throw new IllegalArgumentException();
        }
        return string;
    }

}