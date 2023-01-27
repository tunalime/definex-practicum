package model;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

public class BuildingList {
    private List<House> houseList = new ArrayList<>();
    private List<Villa> villaList = new ArrayList<>();
    private List<SummerHouse> summerHouseList = new ArrayList<>();
    private List<Building> buildingList = new ArrayList<>();

    public BuildingList(){
        createBuilding(House.class,this.houseList);
        createBuilding(Villa.class,this.villaList);
        createBuilding(SummerHouse.class,this.summerHouseList);
    }

    public List<House> getHouseList() {
        return this.houseList;
    }
    public List<Villa> getVillaList() {
        return this.villaList;
    }
    public List<SummerHouse> getSummerHouseList() {
        return this.summerHouseList;
    }
    public List<Building> getBuildingList() {
        return this.buildingList;
    }

    // Farklı türdeki binaları ayrı metotlardan üretmek yerine
    // ortak bir metot üzerinden üretilmesi sağlandı
    private <T> void createBuilding(Class<T> tClass, List<T> buildingList){
        int buildingCount = 3 + (int)(Math.random()*5); // olusturulacak bina sayısı
        for (int i = 0; i < buildingCount; i++){
            double value = (int)(10000000 * Math.random());
            double squareMeter = (int)(1000 * Math.random());
            int roomCount = 2 + (int)(6 * Math.random());
            int saloonCount = (int)(3 * Math.random());
            T building = createInstance(tClass,value,squareMeter,roomCount,saloonCount);
            buildingList.add(building);
        }
        System.out.println(tClass.getName() + " tipinde " + buildingList.size() + " adet yapi olusturuldu.");
        this.buildingList.addAll((List<? extends Building>) buildingList);
    }

    //Generic olarak obje üreten metot
    public static <T> T createInstance(Class<T> tClass, Object... args){
        T instance = null;
        try{
            Class<?>[] argTypes = new Class<?>[args.length];
            for (int i = 0; i < args.length; i++) {
                argTypes[i] = args[i].getClass();
            }
            Constructor<?>[] a = tClass.getConstructors();
            Constructor<T> constructor = tClass.getConstructor(argTypes);
            instance = constructor.newInstance(args);
        }catch (Exception e){
            new Exception("There is an error occured while new instance was creating");
        }
        return instance;
    }
}