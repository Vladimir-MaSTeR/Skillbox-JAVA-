import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class HospitalTemperature {

    public static float[] generateTemperaturePatients(int count) {
        float[] temperaturePatients = new float[count];
        for (int i = 0; i < temperaturePatients.length; i++) {
            temperaturePatients[i] = 30 + (float) (Math.random() * ((40 - 32) + 1));
        }
        return temperaturePatients;
    }

    public static float averageTemperature(float[] temperaturePatients) {
        float x = 0;
        for (float temp : temperaturePatients) {
            x += temp;
        }
        float tempAverage = x / temperaturePatients.length;

        return tempAverage;
    }

    public static int patientHealthy(float[] temperaturePatients) {
        int healthyPatient = 0;
        for (int i = 0; i < temperaturePatients.length; i++) {
            if (temperaturePatients[i] >= 32.2 && temperaturePatients[i] <= 39.9) {
               healthyPatient++;
            }
        }
        return healthyPatient;
    }

    public static void print() {
         float[] temperaturePatients = generateTemperaturePatients(30);
         float tempAverage = averageTemperature(temperaturePatients);
         int healthyPatient = patientHealthy(temperaturePatients);

         System.out.println("----------------------------------------");
         System.out.println("Температура пациентов ");
         var count = 1;
        DecimalFormat formatter = new DecimalFormat("#0.00°C");
     for (float printArray : temperaturePatients) {
         System.out.print("| П-" + count + ". t:" + formatter.format(printArray) + " " );
         count++;
         if (count == 11 || count == 21) System.out.print("\n");
     }

         System.out.println("\n----------------------------------------");
         System.out.println("Средняя температура по больнице: " + formatter.format(tempAverage));
         System.out.println("----------------------------------------");

        System.out.println("Количество здоровых пациентов: " + healthyPatient);
        System.out.println("----------------------------------------");

    }

    public static void main(String[] args) {
     print();
    }
}
