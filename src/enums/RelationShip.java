package enums;

public enum RelationShip {
    H( "Husband"), F("Father"), S("Son"), D("Daughter"),B("Brother"),Z("Mother-In-Law"),
    M("Mother"),O("Other"),T("Brother IN Law"),U("Son In Law"),V("Daughter-In-Law"),W("Wife"),
    X("Sister-In Law"),Y("Father In Law"),C("Sister");

    public final String key;

    private RelationShip(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public static String findByKey(String name) {
        for (RelationShip gender : values()) {
            if (gender.name().equals(name)) {
                return gender.getKey();
            }
        }
        return "";
    }
}
