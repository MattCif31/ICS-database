public class VehicleInfo {
    /*VehicleInfo: Keeps track of the details of the vehicle owned by the customer, i.e., model, year, type
      of vehicle, and whether it was new, certified pre-owned or used.*/

    private String model;
    private String year;
    private String type;
    private String method;

    VehicleInfo(String mdl, String yr, String typ, String mthd){
        model = mdl;
        year = yr;
        type = typ;
        method = mthd;
    }

    String getModel(){
        return model;
    }
    String getYear(){
        return year;
    }
    String getType(){
        return type;
    }
    String getMethod(){
        return method;
    }

    void updateModel(String newModel){
        model = newModel;
    }
    void updateYear(String newYear){
        year = newYear;
    }
    void updateType(String newType){
        type = newType;
    }
    void updateMethod(String newMethod){
        method = newMethod;
    }
}
