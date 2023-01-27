package service;

import model.Building;
import model.BuildingList;
import java.util.List;
import java.util.stream.Collectors;

public class BuildingService {

    BuildingList buildingList;

    public BuildingService() {
        this.buildingList = new BuildingList();
    }

    // totalValue hesaplayan generic metot
    // kod tekrarina dusmemek adina yazildi
    private double calculateTotalValueOfBuildings(List<? extends Building> buildingList){
        double totalValue = buildingList.stream().mapToDouble(Building::getValue).sum();
        return totalValue;
    }

    public double totalValueOfHouses(){
        return calculateTotalValueOfBuildings(buildingList.getHouseList());
    }

    public double totalValueOfVillas(){
        return calculateTotalValueOfBuildings(buildingList.getVillaList());
    }

    public double totalValueOfSummerHouses(){
        return calculateTotalValueOfBuildings(buildingList.getSummerHouseList());
    }

    public double totalValueOfBuildings(){
        return calculateTotalValueOfBuildings(buildingList.getBuildingList());
    }

    // averageSquareMeters hesaplayan generic metot
    // kod tekrarina dusmemek adina yazildi
    private double calculateAverageSquareMetersOfBuildings(List<? extends Building> buildingList){
        double totalSquareMeters = buildingList.stream().mapToDouble(Building::getSquareMeter).sum();
        return totalSquareMeters / buildingList.size();
    }

    public double averageSquareMetersOfHouses(){
        return calculateAverageSquareMetersOfBuildings(buildingList.getHouseList());
    }

    public double averageSquareMetersOfVillas(){
        return calculateAverageSquareMetersOfBuildings(buildingList.getVillaList());
    }

    public double averageSquareMetersOfSummerHouses(){
        return calculateAverageSquareMetersOfBuildings(buildingList.getSummerHouseList());
    }

    public double averageSquareMetersOfBuildings(){
        return calculateAverageSquareMetersOfBuildings(buildingList.getBuildingList());
    }

    // oda ve salon sayisina gore building filtreleyen metot
    public String getBuildingsByRoomAndSaloonCount(int roomCount, int saloonCount){
        List<Building> filteredBuildings = this.buildingList.getBuildingList()
                .stream()
                .filter(building -> building.getRoomCount() == roomCount
                        && building.getSaloonCount() == saloonCount)
                .collect(Collectors.toList());
        return filteredBuildings.isEmpty() ? "Filtreye uygun yapi bulunamadi!" : filteredBuildings.toString();
    }
}
