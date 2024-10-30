package service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ConversionHistory {
    private List<String> history;

    public ConversionHistory() {
        history = new ArrayList<>();
    }

    public void addConversion(String record) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        history.add(timestamp + " - " + record);
    }

    public void showHistory() {
        System.out.println("----- Historial de Conversiones -----");
        if (history.isEmpty()) {
            System.out.println("No hay conversiones registradas.");
        } else {
            for (String record : history) {
                System.out.println(record);
            }
        }
    }
}
