
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    private static int paymentAddress;

//============================== Gas data ==============================

    private static int lastInductGas;
    private static int newInductGas;
    private static final double gasPricePerCub = 8.050;
    private static double toPayForGas;

//============================= Water data =============================

    private static int lastInductWater;
    private static int newInductWater;
    private static int consumptionOfCubForWater;
    private static double toPayForCommunService;
    private static double coldWater;
    private static final double amountOfUtilityRes = 0.1146;
    private static final double communalTariffPerColdWater = 61.33;
    private static final double communalTariffPerWasteWaterTransp = 20.61;
    private static final double communalTariffWasteWaterTreatment = 31.22;
    private static final double garbageSadovaya = 114.83;
    private static final double garbageShaposhnikova = 116.74;
    private static final double majorRepairs = 457.74;

//========================== Electricity data ==========================
//TODO При оплате за электро энергию учесть показатель "socialNormForElectricitySadovaya"
// т.к. при смене времени года меняется норма расхода в пределах социальной нормы

    private static int lastInductElectricity;
    private static int newInductElectricity;
    private static double electricConsumption;
    private static double toPayForElectric;
    private static final int socialNormForElectricitySadovaya = 156;
    private static final int socialNormForElectricityShaposhnikova = 236;
    private static final double electricityTariffWithinNormalLimits = 5.24;
    private static final double electricityTariffIsHigherThanNormal = 7.32;

//========================== Internet ==========================

    private static final int internet = 530;
    private static final int phone = 250;

    public static void main(String[] args) {
        while (true) {
            System.out.print("\nВведите номер объекта (Садовая 1 - 1; Шапошникова 7 - 2): ");
            paymentAddress = (new Scanner(System.in)).nextInt();

            if (paymentAddress == 1 || paymentAddress == 2) {
                System.out.println("\n======Вводите показания целым числом (без дополнительных знаков)======\n");

                try {
                    System.out.print("Введите предыдущие показания за газ: ");
                    lastInductGas = (new Scanner(System.in)).nextInt();
                    System.out.print("Введите текущие показания за газ: ");
                    newInductGas = (new Scanner(System.in)).nextInt();

                    System.out.print("Введите предыдущие показания за воду: ");
                    lastInductWater = (new Scanner(System.in)).nextInt();
                    System.out.print("Введите текущие показания за воду: ");
                    newInductWater = (new Scanner(System.in)).nextInt();

                    System.out.print("Введите предыдущие показания за свет: ");
                    lastInductElectricity = (new Scanner(System.in)).nextInt();
                    System.out.print("Введите текущие показания за свет: ");
                    newInductElectricity = (new Scanner(System.in)).nextInt();
                } catch (InputMismatchException inputMismatchException) {
                    System.out.println("\n======Введите показания корректно======");
                    continue;
                }
            } else {
                System.out.println("Недопустимое значение. Введите корректный номер объекта.");
            }

            gas();
            water();
            electricity();
            totalAmountAndMessage();
        }
    }

    private static void gas() {
        if (paymentAddress == 1 || paymentAddress == 2) {
            int consumptionOfCubGas = newInductGas - lastInductGas;
            toPayForGas = consumptionOfCubGas * gasPricePerCub;
            System.out.println("Газа израсходовано - " + consumptionOfCubGas + " куб.," + " к оплате за газ - "
                    + NumberFormat.getNumberFormat(toPayForGas) + " руб.");
        }
    }

    private static void printWater() {

        System.out.println("Воды израсходовано - " + consumptionOfCubForWater + " куб.," + " к оплате за коммунальные услуги - "
                + NumberFormat.getNumberFormat(toPayForCommunService) + " руб.");
    }

    private static void waterPrice() {
        consumptionOfCubForWater = newInductWater - lastInductWater;
        coldWater = (consumptionOfCubForWater + amountOfUtilityRes) * communalTariffPerColdWater;
    }

    private static void water() {

        if (paymentAddress == 1) {
            waterPrice();
            double wasteWaterTransp = (consumptionOfCubForWater + amountOfUtilityRes) * communalTariffPerWasteWaterTransp;
            double wasteWaterTreatment = (consumptionOfCubForWater + amountOfUtilityRes) * communalTariffWasteWaterTreatment;

            toPayForCommunService = coldWater + wasteWaterTreatment + wasteWaterTransp + garbageSadovaya + majorRepairs;
            System.out.println("Стоимость Хол.Вода: " + NumberFormat.getNumberFormat(coldWater)
                    + "\nСтоимость ТрансСтВод: " + NumberFormat.getNumberFormat(wasteWaterTransp)
                    + "\nСтоимость ОчистСтХВ: " + NumberFormat.getNumberFormat(wasteWaterTreatment)
                    + "\nСтоимость Обращение с ТКО: " + NumberFormat.getNumberFormat(garbageSadovaya)
                    + "\nЕжемес.взнос кап.рем.: " + majorRepairs);
            printWater();
        }
        if (paymentAddress == 2) {
            waterPrice();
            toPayForCommunService = coldWater + garbageShaposhnikova;
            System.out.println("Стоимость Хол.Вода: " + NumberFormat.getNumberFormat(coldWater) + '\n' + "Стоимость Обращение с ТКО: "
                    + NumberFormat.getNumberFormat(garbageShaposhnikova));
            printWater();
        }
    }

    public static void electricityPrint() {
        System.out.println("Электроэнергии израсходовано - " + electricConsumption + " кВт.," + " к оплате за электроэнергию - "
                + NumberFormat.getNumberFormat(toPayForElectric) + " руб.");
    }

    private static void electricity() {
        if (paymentAddress == 1) {
            electricConsumption = newInductElectricity - lastInductElectricity;
            if (electricConsumption <= socialNormForElectricitySadovaya) {
                toPayForElectric = electricConsumption * electricityTariffWithinNormalLimits;
                electricityPrint();
            } else {
                double excessConsumptionElectric = electricConsumption - socialNormForElectricitySadovaya;
                toPayForElectric = (excessConsumptionElectric * electricityTariffIsHigherThanNormal) + (socialNormForElectricitySadovaya
                        * electricityTariffWithinNormalLimits);
                electricityPrint();
            }
        }

        if (paymentAddress == 2) {
            electricConsumption = newInductElectricity - lastInductElectricity;
            if (electricConsumption <= socialNormForElectricityShaposhnikova) {
                toPayForElectric = electricConsumption * electricityTariffWithinNormalLimits;
                electricityPrint();
            } else {
                double excessConsumptionElectric = electricConsumption - socialNormForElectricityShaposhnikova;
                toPayForElectric = (excessConsumptionElectric * electricityTariffIsHigherThanNormal) + (socialNormForElectricityShaposhnikova
                        * electricityTariffWithinNormalLimits);
                electricityPrint();
            }
        }
    }

    private static void totalAmountAndMessage() {
        if (paymentAddress == 1){
            double total = NumberFormat.getNumberFormat(toPayForElectric + toPayForCommunService + toPayForGas - majorRepairs)
                                                        + internet;
            System.out.println("\nПривет.\n" +
                               "Расчет коммуналки:\n" +
                               "Газ: " + NumberFormat.getNumberFormat(toPayForGas) + "\n" +
                               "Комун.услуги (с вычетом за кап.ремонт): " + NumberFormat.getNumberFormat((toPayForCommunService - majorRepairs)) + "\n" +
                               "Электроэнергия: " + NumberFormat.getNumberFormat(toPayForElectric) + "\n" +
                               "Интернет: " + internet + "\n" +
                               "Итого: " + total);
        }

        if (paymentAddress == 2){
            double total = NumberFormat.getNumberFormat(toPayForElectric + toPayForCommunService + toPayForGas + phone);
            System.out.println("Привет. За коммуналку получилось: " + total);
        }
    }
}
