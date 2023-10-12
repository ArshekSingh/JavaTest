package enums;

public enum Category {

    GEN("General"), OBC("OBC"), ST("Schedule Tribe"),
    SC("Schedule Caste"), OT("Other");

    public final String key;

    private Category(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public static String findByName(String name) {
        for (Category category : values()) {
            if (category.name().equals(name)) {
                return category.getKey();
            }
        }
        return "";
    }
}
