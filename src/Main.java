
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    private static int object;

//============================== Gas data ==============================

    private static int lastInducatGas;
    private static int newInducatGas;
    private static final double gasPricePerCub = 7.328;

//============================= Water data =============================

    private static int lastInducatWater;
    private static int newInducatWater;
    private static int consumptionOfCubForWater;
    private static double toPayForCommunService;
    private static double coldWater;
    private static final double amountOfUtilityRes = 0.1146;
    private static final double communalTariffPerColdWater = 61.33;
    private static final double communalTariffPerWasteWaterTransp = 18.91;
    private static final double communalTariffWasteWaterTreatment = 28.65;
    private static final double garbageSadovaya = 105.35;
    private static final double garbageShaposhnikova = 105.62;
    private static final double majorRepairs = 457.74;

//========================== Electricity data ==========================
//TODO При оплате за электро энергию учитесть показатель "socialNormForElectricitySadovaya"
// т.к. при смене времени года меняется норма расхода в пределах социальной нормы

    private static int lastInducatioElectricity;
    private static int newInducationElectricity;
    private static double electricConsumption;
    private static double toPayForElectric;
    private static final int socialNormForElectricitySadovaya = 106;
    private static final int socialNormForElectricityShaposhnikova = 236;
    private static final double electricityTariffWithinNormalLimits = 4.42;
    private static final double electricityTariffIsHigherThanNormal = 6.18;

//=======================================================================

    public static void main(String[] args) {
        while (true) {
            System.out.print("\nВведите номер объекта (Садовая 1 - 1; Шапошникова 7 - 2): ");
            object = (new Scanner(System.in)).nextInt();

            if (object == 1 || object == 2) {
                System.out.println("\n======Вводите показания целым числом (без дополнительных знаков)======\n");

                try {
                    System.out.print("Введите предыдущие показания за газ: ");
                    lastInducatGas = (new Scanner(System.in)).nextInt();
                    System.out.print("Введите текущие показания за газ: ");
                    newInducatGas = (new Scanner(System.in)).nextInt();

                    System.out.print("Введите предыдущие показания за воду: ");
                    lastInducatWater = (new Scanner(System.in)).nextInt();
                    System.out.print("Введите текущие показания за воду: ");
                    newInducatWater = (new Scanner(System.in)).nextInt();

                    System.out.print("Введите предыдущие показания за свет: ");
                    lastInducatioElectricity = (new Scanner(System.in)).nextInt();
                    System.out.print("Введите текущие показания за свет: ");
                    newInducationElectricity = (new Scanner(System.in)).nextInt();
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
        }
    }


    private static void gas() {
        if (object == 1 || object == 2) {
            int consumptionOfCubGas = newInducatGas - lastInducatGas;
            double toPayForGas = consumptionOfCubGas * gasPricePerCub;
            System.out.println("Газа израсходовано - " + consumptionOfCubGas + " куб.," + " к оплате за газ - "
                    + NumberFormat.getNumberFormat(toPayForGas) + " руб.");
        }
    }

    private static void printWater() {

        System.out.println("Воды израсходовано - " + consumptionOfCubForWater + " куб.," + " к оплате за коммунальные услуги - "
                + NumberFormat.getNumberFormat(toPayForCommunService) + " руб.");
    }

    private static void waterPrice() {
        consumptionOfCubForWater = newInducatWater - lastInducatWater;
        coldWater = (consumptionOfCubForWater + amountOfUtilityRes) * communalTariffPerColdWater;
    }

    private static void water() {

        if (object == 1) {
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
        if (object == 2) {
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
        if (object == 1) {
            electricConsumption = newInducationElectricity - lastInducatioElectricity;
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

        if (object == 2) {
            electricConsumption = newInducationElectricity - lastInducatioElectricity;
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
}