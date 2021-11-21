import java.util.Scanner;

public class Main {
import java.util.Scanner;

    public class Main {
        public static void main(String[] args) {
            Scanner input = new Scanner(System.in);
            System.out.print("Введите номер объекта (Садовая 1 - 1; Шапошникова 7 - 2): ");
            int object = input.nextInt();
            System.out.print("Введите предыдущие показания за газ: ");
            int lastInducationGas = input.nextInt();
            System.out.print("Введите текущие показания за газ: ");
            int newInducationGas = input.nextInt();
            System.out.print("Введите предыдущие показания за воду: ");
            int lastInducationWater = input.nextInt();
            System.out.print("Введите текущие показания за воду: ");
            int newInducationWater = input.nextInt();
            System.out.print("Введите предыдущие показания за свет: ");
            int lastInducatioElectricity = input.nextInt();
            System.out.print("Введите текущие показания за свет: ");
            int newInducationElectricity = input.nextInt();
            System.out.print("Введите предыдущие показания за свет: ");

            if (object == 1 || object == 2) {
                int consumptionOfcubesGas = newInducationGas - lastInducationGas;
                double toPayForGas = consumptionOfcubesGas * 6.55900;
                System.out.println("Газа израсходованно - " + consumptionOfcubesGas + " куб.," + " к оплате за газ - " + toPayForGas + " руб.");
            }
            if (object == 1) {
                int consumptionOfcubesForWater = newInducationWater - lastInducationWater;
                double coldWater = (consumptionOfcubesForWater + 0.1146) * 67.48;
                double wasteWaterTransp = (consumptionOfcubesForWater + 0.1146) * 17.95;
                double wasteWaterTreatment = (consumptionOfcubesForWater + 0.1146) * 27.19;
                double garbage = 103.89;
                double majorRepairs = 360.10;
                double toPayForCommunService = coldWater + wasteWaterTreatment + wasteWaterTransp + garbage + majorRepairs;
                System.out.println("Воды израсходованно - " + consumptionOfcubesForWater + " куб.," + " к оплате за коммунальные услуги - " + toPayForCommunService + " руб.");
            }
            if (object == 2) {
                int consumptionOfcubesForWater = newInducationWater - lastInducationWater;
                double coldWater = (consumptionOfcubesForWater + 0.1146) * 67.48;
                double garbage = 105.62;
                double toPayForCommunService = coldWater + garbage;
                System.out.println("Воды израсходованно - " + consumptionOfcubesForWater + " куб.," + " к оплате за коммунальные услуги - " + toPayForCommunService + " руб.");
            }
            if (object == 1) {
                double electrСonsumption = newInducationElectricity - lastInducatioElectricity;
                if (electrСonsumption <= 96) {
                    double toPayForelEctricity = electrСonsumption * 4.25;
                    System.out.println("Электроэнергии израсходованно - " + electrСonsumption + " кВт.," + " к оплате за электроэнергию - " + toPayForelEctricity + " руб.");
                } else {
                    double excessСonsumEctricity = electrСonsumption - 96;
                    double toPayForelEctricity = (excessСonsumEctricity * 5.95) + (96 * 4.25);
                    System.out.println("Электроэнергии израсходованно - " + electrСonsumption + " кВт.," + " к оплате за электроэнергию - " + toPayForelEctricity + " руб.");
                }
            }
            if (object == 2) {
                double electrСonsumption = newInducationElectricity - lastInducatioElectricity;
                if (electrСonsumption <= 236) {
                    double toPayForelEctricity = electrСonsumption * 4.25;
                    System.out.println("Электроэнергии израсходованно - " + electrСonsumption + " кВт.," + " к оплате за электроэнергию - " + toPayForelEctricity + " руб.");
                }
                else {
                    double excessСonsumptionEctricity = electrСonsumption - 236;
                    double toPayForelEctricity = (excessСonsumptionEctricity * 5.95) + (236 * 4.25);
                    System.out.println("Электроэнергии израсходованно - " + electrСonsumption + " кВт.," + " к оплате за электроэнергию - " + toPayForelEctricity + " руб.");
                }
            }
        }
    }

}
