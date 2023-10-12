package enums;

public enum MeetingType {
    SUPRIZE_CENTER_VISIT("SCV"), COLLECTION("COLL"), CGT("C"), CGT2("C2"), HOUSE_VISIT("HV"), GRT("G");

    public final String value;

    private MeetingType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static String findByName(String name) {
        for (MeetingType meetingType : values()) {
            if (meetingType.name().equals(name)) {
                return meetingType.getValue();
            }
        }
        return "";
    }
}
