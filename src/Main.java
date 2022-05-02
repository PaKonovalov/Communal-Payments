import java.util.Scanner;

    public class Main {
        private static int object;

//============================== Gas data ==============================

        private static int lastInducatGas;
        private static int newInducatGas;
        private static double gasPricePerCub = 6.55900;

//============================= Water data =============================

        private static int lastInducatWater;
        private static int newInducatWater;
        private static int consumptionOfCubForWater;
        private static double toPayForCommunService;
        private static double coldWater;
        private static double amountOfUtilityRes = 0.1146;
        private static double communalTariffPerColdWater = 61.33;
        private static double communalTariffPerWasteWaterTransp = 17.95;
        private static double communalTariffWasteWaterTreatment = 27.19;
        private static double garbageSadovaya = 103.89;
        private static double garbageShaposhnikova = 105.62;
        private static double majorRepairs = 457.74;

//========================== Electricity data ==========================
//TODO При оплате за электро энергию учитесть показатель "socialNormForElectricitySadovaya"
// т.к. при смене времени года меняется норма расхода в пределах социальной нормы

        private static int lastInducatioElectricity;
        private static int newInducationElectricity;
        private static double electricConsumption;
        private static double toPayForElectric;
        private static int socialNormForElectricitySadovaya = 86;
        private static int socialNormForElectricityShaposhnikova = 236;
        private static double electricityTariffWithinNormalLimits = 4.25;
        private static double electricityTariffIsHigherThanNormal = 5.95;

//=======================================================================

        public static void main(String[] args) {
            System.out.print("Введите номер объекта (Садовая 1 - 1; Шапошникова 7 - 2): ");
            object = (new Scanner(System.in)).nextInt();

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

            gas();
            water();
            electricity();
        }

        private static void gas() {
            if (object == 1 || object == 2) {
                int consumptionOfCubGas = newInducatGas - lastInducatGas;
                double toPayForGas = consumptionOfCubGas * gasPricePerCub;
                System.out.println("Газа израсходовано - " + consumptionOfCubGas + " куб.," + " к оплате за газ - " + toPayForGas + " руб.");
            }
        }

        private static void printWater() {
            System.out.println("Воды израсходовано - " + consumptionOfCubForWater + " куб.," + " к оплате за коммунальные услуги - " + toPayForCommunService + " руб.");
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
                System.out.println("Стоимость Хол.Вода: " + coldWater + '\n' + "Стоимость ТрансСтВод: " + wasteWaterTransp + '\n' +
                        "Стоимость ОчистСтХВ: " + wasteWaterTreatment + '\n' + "Стоимость Обращение с ТКО: " + garbageSadovaya + '\n' +
                        "Ежемес.взнос кап.рем.: " + majorRepairs);
                printWater();
            }
            if (object == 2) {
                waterPrice();
                toPayForCommunService = coldWater + garbageShaposhnikova;
                System.out.println("Стоимость Хол.Вода: " + coldWater + '\n' + "Стоимость Обращение с ТКО: " + garbageShaposhnikova);
                printWater();
            }
        }

        public static void electricityPrint() {
            System.out.println("Электроэнергии израсходовано - " + electricConsumption + " кВт.," + " к оплате за электроэнергию - " + toPayForElectric + " руб.");
        }

        private static void electricity() {
            if (object == 1) {
                electricConsumption = newInducationElectricity - lastInducatioElectricity;
                if (electricConsumption <= socialNormForElectricitySadovaya) {
                    toPayForElectric = electricConsumption * electricityTariffWithinNormalLimits;
                    electricityPrint();
                } else {
                    double excessConsumptionElectric = electricConsumption - socialNormForElectricitySadovaya;
                    toPayForElectric = (excessConsumptionElectric * electricityTariffIsHigherThanNormal) + (socialNormForElectricitySadovaya * electricityTariffWithinNormalLimits);
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
                    toPayForElectric = (excessConsumptionElectric * electricityTariffIsHigherThanNormal) + (socialNormForElectricityShaposhnikova * electricityTariffWithinNormalLimits);
                    electricityPrint();
                }
            }
        }
    }
