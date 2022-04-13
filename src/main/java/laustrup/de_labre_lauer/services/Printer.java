package laustrup.de_labre_lauer.services;

public class Printer {

    private static final String border = "\n\n----------------------------------------------------------\n\n";

    public static void printException(String description,Exception e) {
        System.err.println(description + border + e + border);
        e.printStackTrace();
    }

    public static void printLn(String content) {System.out.println("\n"+content+"\n\n");}
    public static void printErr(String content) {System.err.println("\n"+content+"\n\n");}
}
