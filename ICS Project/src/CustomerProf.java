public class CustomerProf {
    private String _adminID;
    private String _firstName;
    private String _lastName;
    private String _address;
    private String _phone;
    private float _income;
    private String _status;
    private String _use;
    private VehicleInfo _VehicleInfo;

    public CustomerProf(String adminID, String firstName, String lastName, String address, String phone, float income, String status, String use, VehicleInfo VehicleInfo) {
        _adminID = adminID;
        _firstName = firstName;
        _lastName = lastName;
        _address = address;
        _phone = phone;
        _income = income;
        _status = status;
        _use = use;
        _VehicleInfo = VehicleInfo;
    }                                       //Constructor

    public String getadminID() {
        return _adminID;
    }

    public String getFirstName() {
        return _firstName;
    }

    public String getLastName() {
        return _lastName;
    }

    public String getAddress() {
        return _address;
    }

    public String getPhone() {
        return _phone;
    }

    public float getIncome() {
        return _income;
    }

    public String getStatus() {
        return _status;
    }

    public String getUse() {
        return _use;
    }

    public VehicleInfo getVehicleInfo() {
        return _VehicleInfo;
    }                                           //basic gets

    public void updateadminID(String adminID) { _adminID = adminID;}
    public void updateFirstName(String firstName) { _firstName = firstName;}
    public void updateLastName(String lastName) { _lastName = lastName;}
    public void updateAddress(String address) { _address = address;}
    public void updatePhone(String phone) { _phone = phone;}
    public void updateIncome(float income) { _income = income;}
    public void updateStatus(String status) { _status = status;}
    public void updateUse(String use) { _use = use;}
    public void updateVehicleInfo(VehicleInfo VehicleInfo) { _VehicleInfo = VehicleInfo;} //basic sets

    public static void main(String[] args) {
        VehicleInfo car = new VehicleInfo("Ford", "2021", "sport", "new");
        CustomerProf Matt = new CustomerProf("mrc18013", "Matt", "Cif", "1 pickle road", "1234567890", 100, "active", "business", car);
        System.out.println(Matt.getadminID());
        System.out.println(Matt.getFirstName());
        System.out.println(Matt.getLastName());
        System.out.println(Matt.getAddress());
        Matt.updatePhone("1112223333");
        System.out.println(Matt.getPhone());
        Matt.updateIncome(5);
        System.out.println(Matt.getIncome());
        System.out.println(Matt.getStatus());
        car.updateYear("2000");
        System.out.println(car.getYear());
        System.out.println(car.getType());
        //CustomerProfDB test = new CustomerProfDB("sendingaprayer");
        //test.initializeDatabase("sendingaprayer");                        //testing

    }


}
