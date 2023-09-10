package calculator;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

class FelmondasiIdo {
    LocalDate felmondasVege;

    public LocalDate fellebbezesVege(LocalDate datum) {
        try {
            felmondasVege = datum.plusDays(30);

        } catch (Exception e) {
            System.out.println("Rossz a dátum formátuma, írja be újra. Példa: 2023-01-01");
        }
        return felmondasVege;
    }

    public LocalDate hosszabbFelmondas(LocalDate kezdet, LocalDate datum) {
        LocalDate ma = LocalDate.now();
        long mvHossza = ChronoUnit.YEARS.between(kezdet, ma);
        if (mvHossza < 3) {
            return felmondasVege;
        } else if (mvHossza < 5) {
            felmondasVege = datum.plusDays(35);
        } else if (mvHossza < 8) {
            felmondasVege = datum.plusDays(45);
        } else if (mvHossza < 10) {
            felmondasVege = datum.plusDays(50);
        } else if (mvHossza < 15) {
            felmondasVege = datum.plusDays(55);
        } else if (mvHossza < 18) {
            felmondasVege = datum.plusDays(60);
        } else if (mvHossza < 20) {
            felmondasVege = datum.plusDays(70);
        } else {
            felmondasVege = datum.plusDays(90);
        }
        return felmondasVege;
    }

    public String kiiras() {
        String eredmeny = "A felmondási idő vége: " + felmondasVege;
        return eredmeny;
    }
}
