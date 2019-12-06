import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/** Verwaltungssoftware zur Zuordnung von Autokennzeichen auf einen Besitzer.
 * @author Gudrun Schiedermeier, gschied@haw-landshut.de
 */
public class LicenceAdministration {
    /** Bildet Autokennzeichen auf das zugehoerige Fahrzeug ab. */
    private Map<String, Car> platesToCar = new HashMap<>();

    /** Kopiert eine gesamte andere Autokennzeichenverwaltung, alle Zulassungen.
     * @param entries Abbildung von Autokennzeichen auf Autos.
     * @throw NullpointerException, falls entries null ist.
     */
    public void register(Map<String, Car> entries) {

        if (entries == null)
        {
            throw new NullPointerException();
        }

        this.platesToCar.putAll(entries);

    }

    /** Fuegt einen neuen Eintrag in die Autokennzeichenverwaltung ein.
     *
     * @param licence ein gueltiges Autokennzeichen.
     * @param car ein Auto.
     * @throw IllegalArgumentException, falls das Auto bereits registriert ist.
     */
    public void register(String licence, Car car) {
        if (this.platesToCar.putIfAbsent(licence, car) != null)
        {
            throw new IllegalArgumentException();
        }
    }

    /** Liefert die Anzahl der Zulassungen.
     *
     * @return Anzahl der Zulassungen.
     */
    public int size() {
        return this.platesToCar.size();
    }


    /** Liefert eine unveraenderliche Sicht auf die Abbildung der Zulassungen.
     * @return unveraenderliche Sicht auf die Zulassungen.
     */
    Map<String, Car> getPlatesToCar() {
        return Collections.unmodifiableMap(platesToCar);
    }

    /** Liefert die unveraenderliche Menge aller Autokennzeichen.
     * @return die unveraenderliche Menge aller Autokennzeichen.
     */
    public Set<String> getLicencePlates() {

        return Collections.unmodifiableSet(platesToCar.keySet());

    }

    /** Liefert fuer einen Besitzer (owner) eine Liste aller Autokennzeichen.
     * @param owner der Besitzer eines Autos, nicht null, nicht leer.
     * @return Liste aller autokennzeichen eines Besitzer.
     */
    public List<String> getLicencesOfOwner(String owner) {
        if ((owner == null) || (owner.length() == 0))
        {
            throw new IllegalArgumentException();
        }
        List<String> list = this.getPlatesToCar().entrySet().stream().filter(pair->owner.equals(pair.getValue().getOwner())).map(x->x.getKey()).collect(Collectors.toList());
        /**List<String> list = new ArrayList<String> ();
        for (Map.Entry<String,Car> pair : this.getPlatesToCar().entrySet()){
            if (owner.equals(pair.getValue().getOwner()))
            {
                list.add(pair.getKey());
            }
        }*/
        return list;
    }

    /** Liefert eine sortierte Liste der Nummernschilder fuer Autos aelter als year.
     * @param beforeYear Jahresangabe.
     * @return sortierte Liste von Nummernschildern.
     */
    public List<String> getLicencesOlderThan(int beforeYear) {

        List<String> list = this.getPlatesToCar().entrySet().stream().filter(pair->beforeYear > pair.getValue().getBuildingYear()).map(x->x.getKey()).collect(Collectors.toList());

        /**
         *  List<String> list = new ArrayList<String> ();
         *  for (Map.Entry<String,Car> pair : this.getPlatesToCar().entrySet()){
            if (beforeYear > pair.getValue().getBuildingYear())
            {
                list.add(pair.getKey());
            }
        }*/
        return list;
    }

    /** Verschrottet ein Auto, dessen Erstellungsjahr vor einem bestimmten Jahr liegt.
     * @param year ein Herstellungsjahr.
     */
    void keepLicenceNewerThan(int year) {
        List<String> list = this.getLicencesOlderThan(year);
        if(list.size()<1)
        {
            throw new NoSuchElementException();
        }
        this.platesToCar.remove(list.get(0));
        //this.platesToCar = (Map<String, Car>) list.stream().skip(1).collect(Collectors.toList());
    }

    /** Ueberprueft den String fuer ein Nummernschild auf einen korrekten Aufbau.
     * @param string ein Nummernschild.
     * @return ein korrektes Nummernschild
     * @throw IllegalArgumentException, falls das Nummernschild nicht den Regeln entspricht.
     */
    public static String requireValidLicencePlate(String string) {
        if(!string.matches("[A-Z]{1,3}-[A-Z]{1,2}-\\d{1,2}"))
        {
            throw new IllegalArgumentException();
        }
        return string;
    }



}