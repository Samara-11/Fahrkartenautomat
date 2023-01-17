import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

class Fahrkartenautomat {

    public static double fahrkartenbestellungErfassen(HashMap<String, Double> Fahrkarten ) {
        int[] anzahlTickets = new int[Fahrkarten.size()];

        double gesamtPreis = 0.0;
        int auswahl;
        Scanner tastatur = new Scanner(System.in);

        System.out.println(" Fahrkartenbestellvorgang:");
        System.out.println(" =========================");
        do {
            auswahl = fahrkartenMenue(Fahrkarten);

            if (auswahl == 0)
            {int i = 0;
                for(Map.Entry<String,Double> e :Fahrkarten.entrySet()){
                    gesamtPreis += e.getValue() * anzahlTickets[i];
                    i++;
                }}
            else
                erfasseTicketAnzahl(anzahlTickets, auswahl);

        } while (auswahl != 0);

        return gesamtPreis;

    }

    public static void erfasseTicketAnzahl(int[] anzahlTickets, int auswahl) {
        Scanner tastatur = new Scanner(System.in);
        System.out.print("\n  Anzahl der Tickets: ");
        anzahlTickets[auswahl - 1] += tastatur.nextInt();
    }

    public static int fahrkartenMenue(HashMap<String, Double> Fahrkarten ) {
        int auswahl;
        boolean istEingabeKorrekt = false;
        Scanner tastatur = new Scanner(System.in);

        do {
            System.out.println("\n  W�hlen Sie :");
            for(String key : Fahrkarten.keySet())
                System.out.println("    " + key);
            System.out.println("    Bezahlen (0)\n");
            System.out.print("  Ihre Wahl: ");
            auswahl = tastatur.nextInt();
            if (auswahl < Fahrkarten.size() && auswahl >= 0)
                istEingabeKorrekt = true;
            else
                System.out.println("  >>falsche Eingabe<<");

        } while (!istEingabeKorrekt);
        return auswahl;
    }

    public static double fahrkartenBezahlen(double zuZahlenderBetrag) {
        double eingeworfeneMuenze;
        double eingezahlterGesamtbetrag = 0.0;
        Scanner tastatur = new Scanner(System.in);

        while (eingezahlterGesamtbetrag < zuZahlenderBetrag) {
            System.out.format("  Noch zu zahlen: %4.2f � %n", (zuZahlenderBetrag - eingezahlterGesamtbetrag));
            System.out.print("  Eingabe (mind. 5Ct, h�chstens 10 Euro): ");
            eingeworfeneMuenze = tastatur.nextDouble();
            eingezahlterGesamtbetrag += eingeworfeneMuenze;
        }
        return eingezahlterGesamtbetrag - zuZahlenderBetrag;
    }

    public static void fahrkartenAusgeben() {
        System.out.println("\n  Fahrschein wird ausgegeben");
        System.out.print("  ");
        for (int i = 0; i < 8; i++) {
            System.out.print("=");
            warte(255);
        }
        System.out.println("\n");
    }

    public static void warte(int milisekunde) {
        try {
            Thread.sleep(milisekunde);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void rueckgeldAusgeben(double rueckgabebetrag) {

        if (rueckgabebetrag > 0.0) {
            System.out.format("  Der R�ckgabebetrag in H�he von %4.2f � %n", rueckgabebetrag);
            System.out.println("  wird in folgenden M�nzen ausgezahlt:");

            while (rueckgabebetrag >= 2.0) {// 2 EURO-M�nzen
                muenzeAusgeben(2, "EURO");
                rueckgabebetrag -= 2.0;
            }
            while (rueckgabebetrag >= 1.0) {// 1 EURO-M�nzen
                muenzeAusgeben(1, "EURO");
                rueckgabebetrag -= 1.0;
            }
            while (rueckgabebetrag >= 0.5) // 50 CENT-M�nzen
            {
                muenzeAusgeben(50, "CENT");
                rueckgabebetrag -= 0.5;
            }
            while (rueckgabebetrag >= 0.2) // 20 CENT-M�nzen
            {
                muenzeAusgeben(20, "CENT");
                rueckgabebetrag -= 0.2;
            }
            while (rueckgabebetrag >= 0.1) // 10 CENT-M�zen
            {
                muenzeAusgeben(10, "CENT");
                rueckgabebetrag -= 0.1;
            }
            while (rueckgabebetrag >= 0.05)// 5 CENT-M�nzen
            {
                muenzeAusgeben(5, "CENT");
                rueckgabebetrag -= 0.05;
            }
        }
    }

    public static void muenzeAusgeben(int betrag, String einheit) {

        System.out.println("                 * * *        ");
        System.out.println("               *       *      ");
        System.out.format("              *    %-2s   *     %n", betrag);
        System.out.format("              *   %4s  *     %n", einheit);
        System.out.println("               *       *      ");
        System.out.println("                 * * *        ");

    }

    public static void main(String[] args) {
        HashMap<String, Double> Fahrkarten = new LinkedHashMap<String, Double>();
        Fahrkarten.put("Einzelfahrschein Berlin AB (1)", 2.90);
        Fahrkarten.put("Einzelfahrschein Berlin BC (2)", 3.30);
        Fahrkarten.put("Einzelfahrschein Berlin ABC (3)", 3.60);
        Fahrkarten.put("Kurzstrecke (4)", 1.90);
        Fahrkarten.put("Tageskarte Berlin AB (5)", 8.60);
        Fahrkarten.put("Tageskarte Berlin BC (6)", 9.00);
        Fahrkarten.put("Tageskarte Berlin ABC (7)", 9.60);
        Fahrkarten.put("Kleingruppen-Tageskarte Berlin AB (8)", 23.50);
        Fahrkarten.put("Kleingruppen-Tageskarte Berlin BC (9)", 24.30);
        Fahrkarten.put("Kleingruppen-Tageskarte Berlin ABC (10)", 24.90);

        double zuZahlenderBetrag;
        double rueckgabebetrag;
        do {

            zuZahlenderBetrag = fahrkartenbestellungErfassen(Fahrkarten);
            rueckgabebetrag = fahrkartenBezahlen(zuZahlenderBetrag);
            fahrkartenAusgeben();
            rueckgeldAusgeben(rueckgabebetrag);

            System.out.println("\n  Vergessen Sie nicht, den Fahrschein\n" + "  vor Fahrtantritt entwerten zu lassen!\n"
                    + "  Wir w�nschen Ihnen eine gute Fahrt.\n\n");
        } while (true);

    }
}