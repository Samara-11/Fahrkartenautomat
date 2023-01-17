import java.util.Scanner;

class Fahrkartenautomat {

    public static double fahrkartenbestellungErfassen() {
        int anzahlTickets;
        double ticketPreis1 = 0;
        double ticketPreis2 = 0;
        double ticketPreis3 = 0;
        double zwischensumme1 = 0;
        double zwischensumme2 = 0;
        double zwischensumme3 = 0;
        double totalsumme = 0;
        int auswahl;
        int bezahlen;



        Scanner tastatur = new Scanner(System.in);


        do {
            System.out.println("Wählen Sie Ihre Wunsch Fahrkarte für Berlin AB aus:\n");
            System.out.println("1. Einzelfahrschein Regeltarif AB [2,90 EUR] ");
            System.out.println("2. Tagesticket Regeltarif AB [8,60 EUR]");
            System.out.println("3. Kleingruppen-Tageskarte Regeltarif AB [23,50 EUR]");
            System.out.println("9. Bezahlen ");
            System.out.print("Ihre Wahl: ");
            auswahl = tastatur.nextInt();


            switch(auswahl) {
                case 1:
                    ticketPreis1 = 2.90;
                    System.out.print("Anzahl der Tickets: ");
                    anzahlTickets = tastatur.nextInt();
                    if( anzahlTickets < 1 || anzahlTickets > 10) {
                        System.out.println("Das Automat bearbeitet nur der Anzahl bis 10 Tickets. Sie werden ein Ticket bekommen");
                        anzahlTickets = 1;
                    }
                    zwischensumme1 = ticketPreis1 * anzahlTickets;
                    break;
                case 2:
                    ticketPreis2 = 8.60;
                    System.out.print("Anzahl der Tickets: ");
                    anzahlTickets = tastatur.nextInt();
                    if( anzahlTickets < 1 || anzahlTickets > 10) {
                        System.out.println("Das Automat bearbeitet nur der Anzahl bis 10 Tickets. Sie werden ein Ticket bekommen");
                        anzahlTickets = 1;
                    }
                    zwischensumme2 = ticketPreis2 * anzahlTickets;
                    break;
                case 3:
                    ticketPreis3 = 23.50;
                    System.out.print("Anzahl der Tickets: ");
                    anzahlTickets = tastatur.nextInt();
                    if( anzahlTickets < 1 || anzahlTickets > 10) {
                        System.out.println("Das Automat bearbeitet nur der Anzahl bis 10 Tickets. Sie werden ein Ticket bekommen");
                        anzahlTickets = 1;
                    }
                    zwischensumme3 = ticketPreis3 * anzahlTickets;
                    break;
                case 4:

                    break;
                default:
                    System.out.println("\n>> Falsche Eingabe <<\n>>Versuchen Sie es nochmal<<\n");
            }
        } while(auswahl !=4 );

        totalsumme = zwischensumme1 + zwischensumme2 + zwischensumme3;
        return totalsumme;
    }

    public static double fahrkartenBezahlen(double zuZahlenderBetrag) {
        double eingeworfenemuenze;
        double eingezahlterGesamtbetrag = 0.0;
        Scanner tastatur = new Scanner(System.in);

        while (eingezahlterGesamtbetrag < zuZahlenderBetrag) {
            System.out.format("Noch zu zahlen: %4.2f € %n", (zuZahlenderBetrag - eingezahlterGesamtbetrag));
            System.out.print("Eingabe (mind. 5Ct, höchstens 2 Euro): ");
            eingeworfenemuenze = tastatur.nextDouble();
            eingezahlterGesamtbetrag += eingeworfenemuenze;
        }
        return eingezahlterGesamtbetrag - zuZahlenderBetrag;
    }

    public static void fahrkartenAusgeben() {
        System.out.println("\nFahrschein wird ausgegeben");
        for (int i = 0; i < 8; i++) {
            System.out.print("=");
            warte(255);
        }
        System.out.println("\n\n");
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
            System.out.format("Der rueckgabebetrag in Höhe von %4.2f € %n", rueckgabebetrag);
            System.out.println("wird in folgenden muenzen ausgezahlt:");

            while (rueckgabebetrag >= 2.0) {// 2 EURO-muenzen
                muenzeAusgeben(2, "EURO");
                rueckgabebetrag -= 2.0;
            }
            while (rueckgabebetrag >= 1.0) {// 1 EURO-muenzen
                muenzeAusgeben(1, "EURO");
                rueckgabebetrag -= 1.0;
            }
            while (rueckgabebetrag >= 0.5) // 50 CENT-muenzen
            {
                muenzeAusgeben(50, "CENT");
                rueckgabebetrag -= 0.5;
            }
            while (rueckgabebetrag >= 0.2) // 20 CENT-muenzen
            {
                muenzeAusgeben(20, "CENT");
                rueckgabebetrag -= 0.2;
            }
            while (rueckgabebetrag >= 0.1) // 10 CENT-Müzen
            {
                muenzeAusgeben(10, "CENT");
                rueckgabebetrag -= 0.1;
            }
            while (rueckgabebetrag >= 0.05)// 5 CENT-muenzen
            {
                muenzeAusgeben(5, "CENT");
                rueckgabebetrag -= 0.05;
            }
        }
    }

    public static void muenzeAusgeben(int betrag, String einheit) {
        System.out.println("" + betrag + " " + einheit);
    }

    public static void main (String[] args) {
        /* code */

        double zuZahlenderBetrag;
        double rueckgabebetrag;
        boolean einkaufsbereit = true;



        do {
            zuZahlenderBetrag = fahrkartenbestellungErfassen();
            rueckgabebetrag = fahrkartenBezahlen(zuZahlenderBetrag);
            fahrkartenAusgeben();
            rueckgeldAusgeben(rueckgabebetrag);

            System.out.println("\nVergessen Sie nicht, den Fahrschein\n" + "vor Fahrtantritt entwerten zu lassen!\n"
                    + "Wir wünschen Ihnen eine gute Fahrt.");
            warte(255);
            System.out.println("\nDer Vorgang wird in Kürze beendet.");
            warte(255);
            for(int i = 0 ; i < 5 ; i++ ) {
                System.out.println("\n");
            }
            System.out.println("Willkommen :)\n\n");
        } while(einkaufsbereit);

    }
}