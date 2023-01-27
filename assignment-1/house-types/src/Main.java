import service.BuildingService;

public class Main {
    public static void main(String[] args) {

        // buildingService olusturuldugunda arkada buildingList classi olusturulur
        // ve bu class tum ev tiplerinden nesneler olusturur
        BuildingService buildingService = new BuildingService();

        System.out.printf("\nEvlerin toplam degeri: %.2f",buildingService.totalValueOfHouses());
        System.out.printf("\nVillalarin toplam degeri: %.2f",buildingService.totalValueOfVillas());
        System.out.printf("\nYazliklarin toplam degeri: %.2f",buildingService.totalValueOfSummerHouses());
        System.out.printf("\nTum yapilarin toplam degeri: %.2f",buildingService.totalValueOfBuildings());
        System.out.println();
        System.out.printf("\nEvlerin ortalama metrekaresi: %.2f",buildingService.averageSquareMetersOfHouses());
        System.out.printf("\nVillalarin ortalama metrekaresi: %.2f",buildingService.averageSquareMetersOfVillas());
        System.out.printf("\nYazliklarin ortalama metrekaresi: %.2f",buildingService.averageSquareMetersOfSummerHouses());
        System.out.printf("\nTum yapilarin ortalama metrekaresi: %.2f",buildingService.averageSquareMetersOfBuildings());
        System.out.println();
        System.out.println("\nFiltrelenen yapilar: \n" + buildingService.getBuildingsByRoomAndSaloonCount(3,1).toString());

    }
}