package enums;

public enum Occupation {

    AG("Agriculture" ),AH("Animal Husbandry"),HA("Handicraft" ),HM("Home Maker"),LA("Labour"),OT("Others"),UE("Unemployed"),
    RE("Retired"),SA("Salaried"),SE("Self Employed"),ST("Student"),SV("Service"),TR("Trade"),RA("Rural Artisans");

    public final String key;

    private Occupation(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public static String findByName(String name) {
        for (Occupation occupation : values()) {
            if (occupation.name().equals(name)) {
                return occupation.getKey();
            }
        }
        return "";
    }
}
