package utils;

public class QueryHandler {

    public static String mapSizeKeyword (String keyword) {

        return switch (keyword.toLowerCase()) {

            case "small", "s" -> "S";
            case "medium", "m" -> "M";
            case "large", "l" -> "L";
            case "extra large", "xl" -> "XL";

            default -> keyword.matches("\\d+") ? keyword : null; // Accept number sizes (e.g., 40, 41) directly
        };
    }

    public static String mapGenderKeyword (String keyword) {

        return switch (keyword.toLowerCase()) {

            case "male", "m" -> "M";
            case "female", "f" -> "F";

            default -> null; // Accept number sizes (e.g., 40, 41) directly
        };
    }

    public static String capitaliseEachWord (String input) {

        String[] words;
        StringBuilder result;

        if (input == null || input.isEmpty()) {
            return input;
        }

        words = input.trim().split("\\s+");
        result = new StringBuilder();

        for (String word : words) {

            if (word.length() > 0) {

                result.append(Character.toUpperCase(word.charAt(0)));
                result.append(word.substring(1).toLowerCase());
                result.append(" ");
            }
        }

        // Remove the trailing space
        return result.toString().trim();
    }
}