package enums;

public enum PromptCategory {

    WELCOME("welcome"), MENU("menu"), THANKS("thanks"), NO_INPUT("noinput"), WRONG_INPUT("wronginput");

    public final String value;

    PromptCategory(String value) {
        this.value = value;
    }

    public static String findByName(String name) {
        for (PromptCategory promptCategory : values()) {
            if (promptCategory.name().equals(name)) {
                return promptCategory.name();
            }
        }
        return "";
    }

    public static String findValueByName(String key) {
        for (PromptCategory promptCategory : values()) {
            if (promptCategory.name().equals(key)) {
                return promptCategory.getValue();
            }
        }
        return "";
    }

    public String getValue() {
        return value;
    }
}
