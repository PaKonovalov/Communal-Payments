import java.util.Scanner;

    public class Main {
        public static void main(String[] args) {
            double garbageSadovaya = 103.89;
            double garbageShaposhnikova = 105.62;
            int socialNormForElectricitySadovaya = 96;
            int socialNormForElectricityShaposhnikova = 236;
            double majorRepairs = 360.10;
            double gasPricePerCubM = 6.55900;
            double amountOfUtilityRes = 0.1146;
            double communalTariffPerColdWater = 67.48;
            double communalTariffPerWasteWaterTransp = 17.95;
            double communalTariffWasteWaterTreatment = 27.19;
            double electricityTariffWithinNormalLimits = 4.25;
            double electricityTariffIsHigherThanNormal = 5.95;
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

            if (object == 1 || object == 2) {
                int consumptionOfcubesGas = newInducationGas - lastInducationGas;
                double toPayForGas = consumptionOfcubesGas * gasPricePerCubM;
                System.out.println("Газа израсходованно - " + consumptionOfcubesGas + " куб.," + " к оплате за газ - " + toPayForGas + " руб.");
            }
            if (object == 1) {
                int consumptionOfcubesForWater = newInducationWater - lastInducationWater;
                double coldWater = (consumptionOfcubesForWater + amountOfUtilityRes) * communalTariffPerColdWater;
                double wasteWaterTransp = (consumptionOfcubesForWater + amountOfUtilityRes) * communalTariffPerWasteWaterTransp;
                double wasteWaterTreatment = (consumptionOfcubesForWater + amountOfUtilityRes) * communalTariffWasteWaterTreatment;

                double toPayForCommunService = coldWater + wasteWaterTreatment + wasteWaterTransp + garbageSadovaya + majorRepairs;
                System.out.println("Стоимость Хол.Вода: " + coldWater + '\n' + "Стоимость ТрансСтВод: " + wasteWaterTransp + '\n' +
                                   "Стоимость ОчистСтХВ: " +  wasteWaterTreatment + '\n' + "Стоимость Обращение с ТКО: " + garbageSadovaya + '\n' +
                                   "Ежемес.взнос кап.рем.: " + majorRepairs);
                System.out.println("Воды израсходованно - " + consumptionOfcubesForWater + " куб.," + " к оплате за коммунальные услуги - " + toPayForCommunService + " руб.");
            }
            if (object == 2) {
                int consumptionOfcubesForWater = newInducationWater - lastInducationWater;
                double coldWater = (consumptionOfcubesForWater + amountOfUtilityRes) * communalTariffPerColdWater;
                double toPayForCommunService = coldWater + garbageShaposhnikova;
                System.out.println("Стоимость Хол.Вода: " + coldWater + '\n' +  "Стоимость Обращение с ТКО: " + garbageShaposhnikova);
                System.out.println("Воды израсходованно - " + consumptionOfcubesForWater + " куб.," + " к оплате за коммунальные услуги - " + toPayForCommunService + " руб.");
            }
            if (object == 1) {
                double electrСonsumption = newInducationElectricity - lastInducatioElectricity;
                if (electrСonsumption <= socialNormForElectricitySadovaya) {
                    double toPayForelEctricity = electrСonsumption * electricityTariffWithinNormalLimits;
                    System.out.println("Электроэнергии израсходованно - " + electrСonsumption + " кВт.," + " к оплате за электроэнергию - " + toPayForelEctricity + " руб.");
                } else {
                    double excessСonsumEctricity = electrСonsumption - socialNormForElectricitySadovaya;
                    double toPayForelEctricity = (excessСonsumEctricity * electricityTariffIsHigherThanNormal) + (socialNormForElectricitySadovaya * electricityTariffWithinNormalLimits);
                    System.out.println("Электроэнергии израсходованно - " + electrСonsumption + " кВт.," + " к оплате за электроэнергию - " + toPayForelEctricity + " руб.");
                }
            }
            if (object == 2) {
                double electrСonsumption = newInducationElectricity - lastInducatioElectricity;
                if (electrСonsumption <= socialNormForElectricityShaposhnikova) {
                    double toPayForelEctricity = electrСonsumption * electricityTariffWithinNormalLimits;
                    System.out.println("Электроэнергии израсходованно - " + electrСonsumption + " кВт.," + " к оплате за электроэнергию - " + toPayForelEctricity + " руб.");
                }
                else {
                    double excessСonsumptionEctricity = electrСonsumption - socialNormForElectricityShaposhnikova;
                    double toPayForelEctricity = (excessСonsumptionEctricity * electricityTariffIsHigherThanNormal) + (socialNormForElectricityShaposhnikova * electricityTariffWithinNormalLimits);
                    System.out.println("Электроэнергии израсходованно - " + electrСonsumption + " кВт.," + " к оплате за электроэнергию - " + toPayForelEctricity + " руб.");
                }
            }
        }
    }
