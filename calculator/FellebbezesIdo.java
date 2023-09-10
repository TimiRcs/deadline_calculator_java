package calculator;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;

public class FellebbezesIdo {
    LocalDate vegDatum;
    LocalDate mydatum;
    int ev;
    LocalDate husvetVasarnap;

    public LocalDate fellebbezesVege(LocalDate datum) {
        try {
            vegDatum = datum.plusDays(15);

        } catch (Exception e) {
            System.out.println("Rossz a dátum formátuma, írja be újra. Példa: 2023-01-01");
        }
        return vegDatum;
    }

    public LocalDate getdatum(LocalDate datum) {
        mydatum = datum;
        return mydatum;
    }

    public int melyikEv() {
        ev = mydatum.getYear();
        return ev;
    }

    public LocalDate hetVege() {
        DayOfWeek hetNapja = vegDatum.getDayOfWeek();
        if (hetNapja.equals(hetNapja.SATURDAY)) {
            vegDatum = vegDatum.plusDays(2);
            munkaSzunet();
            return vegDatum;
        } else if (hetNapja.equals(hetNapja.SUNDAY)) {
            vegDatum = vegDatum.plusDays(1);
            munkaSzunet();
            return vegDatum;
        } else {
            return vegDatum;
        }

    }

    public LocalDate husvet() {
        melyikEv();
        int y = ev;
        int a = y % 19;
        int b = y / 100;
        int c = y % 100;
        int d = b / 4;
        int e = b % 4;
        int g = (8 * b + 13) / 25;
        int h = (19 * a + b - d - g + 15) % 30;
        int j = c / 4;
        int k = c % 4;
        int m = (a + 11 * h) / 319;
        int r = (2 * e + 2 * j - k - h + m + 32) % 7;
        int n = (h - m + r + 90) / 25;
        int p = (h - m + r + n + 19) % 32;

        husvetVasarnap = LocalDate.of(y, n, p);
        return husvetVasarnap;
    }

    public LocalDate munkaSzunet() {
        melyikEv();
        husvet();
        if (vegDatum.isEqual(LocalDate.of(ev, 3, 15))) { // március 15.
            vegDatum = vegDatum.plusDays(1);
            return vegDatum;
        } else if (vegDatum.isEqual(LocalDate.of(ev, 5, 1))) { // munka ünnepe
            vegDatum = vegDatum.plusDays(1);
            return vegDatum;
        } else if (vegDatum.isEqual(LocalDate.of(ev, 10, 23))) { // október 23.
            vegDatum = vegDatum.plusDays(1);
            return vegDatum;
        } else if (vegDatum.isEqual(LocalDate.of(ev, 11, 1))) { // mindenszentek
            vegDatum = vegDatum.plusDays(1);
            return vegDatum;
        } else if (vegDatum.isEqual(husvetVasarnap.plusDays(1))
                || vegDatum.isEqual(husvetVasarnap)
                || vegDatum.isEqual(husvetVasarnap.minusDays(2))
                || vegDatum.isEqual(husvetVasarnap.minusDays(1))) { // húsvét
            vegDatum = husvetVasarnap.plusDays(2);
            return vegDatum;
        } else if (vegDatum.isEqual(husvetVasarnap.plusDays(50))) { // pünkösd hétfő
            vegDatum = vegDatum.plusDays(1);
            return vegDatum;
        } else {
            return vegDatum;
        }
    }

    public LocalDate itelkezesiSzunet(LocalDate datum) {
        melyikEv();
        LocalDate nyarSzunetEleje = LocalDate.of(ev, 7, 15);
        LocalDate nyarSzunetVege = LocalDate.of(ev, 8, 20);

        LocalDate telSzunetEleje = LocalDate.of(ev, 12, 24);
        LocalDate telSzunetVege = LocalDate.of(ev + 1, 1, 1);

        Month honap = datum.getMonth();

        if (honap.equals(honap.JULY)) {
            int nap1 = datum.getDayOfMonth();
            int nap2 = nyarSzunetEleje.minusDays(1).getDayOfMonth();
            int nap3 = nap2 - nap1;
            int nap4 = 15 - nap3;
            if (nap4 > 0) {
                vegDatum = nyarSzunetVege.plusDays(nap4);
                return vegDatum;
            } else {
                return vegDatum;
            }
        } else if (honap.equals(honap.DECEMBER)) {
            int nap1 = datum.getDayOfMonth();
            int nap2 = telSzunetEleje.minusDays(1).getDayOfMonth();
            int nap3 = nap2 - nap1;
            int nap4 = 15 - nap3;
            if (nap4 > 0) {
                vegDatum = telSzunetVege.plusDays(nap4);
                return vegDatum;
            } else {
                return vegDatum;
            }

        }
        return vegDatum;
    }

    public String kiir() {
        String eredmeny = "A fellebbezési határidő vége: " + vegDatum;
        return eredmeny;
    }

}
