import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.util.Objects;
import java.util.Scanner;


public class CustomerProfDB {
    List<CustomerProf> _customerList = new ArrayList<>();
    private int _numCustomer;
    private int _currentCustomerIndex;
    private String _fileName;

    private File database; //added this because otherwise it always wrote to null.txt which is bad

    public int customers() {
        _numCustomer = _customerList.size();
        return _numCustomer;
    }                                           //returns number of customer profiles in list


    /*public CustomerProfDB(String Filename) {
        _fileName = Filename;
        initializeDatabase(_fileName);
    }*/                                               //used on startup, creates file if file doesn't exist

    public CustomerProfDB(File f) throws FileNotFoundException {
        database = f;
        newInitializeDatabase(f);
    }

    public void insertNewProfile(CustomerProf CustomerProf) {
        _customerList.add(CustomerProf);
        //System.out.println(_customerList);
    }                                                   //adds profile to list

    /*public CustomerProf findProfile(String adminID, String lastName) {
        int i;
        int n = _customerList.size();
        if (n == 0) {
            System.out.println("No profiles found, database size is zero.");
            return null;
        }                                                   //finds profile on list
        for (i = 0; i < n; i++) {
            System.out.println("Compared " + _customerList.get(i).getadminID() + " and " + adminID);
            System.out.println("Compared " + _customerList.get(i).getLastName() + " and " + lastName);
            if (_customerList.get(i).getadminID().equals(adminID) && _customerList.get(i).getLastName().equals(lastName)) {
                return _customerList.get(i);
            }                                            //finds if arguments are equal to any profile attributes of adminID and lastName
        }
        _currentCustomerIndex = i;
        System.out.println("No profiles found");
        return null;
    }*/

    public CustomerProf findProfile(String adminID, String lastName){
        return findProfileHelper(adminID, lastName, findFirstProfile());
    }

    public CustomerProf findProfileHelper(String adminID, String lastName, CustomerProf profile){
        if(profile == null){
            System.out.println("No matches.");
            return null;
        }
        System.out.println("Target: " + lastName + " with adminID " + adminID);// + " with adminID " + adminID);
        String tempName = profile.getLastName().substring(0, profile.getLastName().length()-1);
        String tempID = profile.getadminID().substring(0, profile.getadminID().length()-1);
        System.out.println("Checking: " + tempName + " with admin ID " + tempID);// + " with adminID " + profile.getadminID());
        if(tempName.equals(lastName) && tempID.equals(adminID)) {
            System.out.println("Match!");
            return profile;
        }
        else{
            return findProfileHelper(adminID, lastName, findNextProfile());
        }
    }

    public boolean deleteProfile(String adminID, String lastName) {
        boolean found = false;
        CustomerProf p = findProfile(adminID, lastName);
        if (p == null) {
            System.out.println("No profile found");
        }
        else {
            found = true;
        }
        int n = _customerList.size();
        /*int i = 0;
        int n = _customerList.size();
        if (n == 0) {
            System.out.print("No profiles to delete");
        }

        boolean found = false;

        for (i = 0; i < n; i++) {
            if (Objects.equals(_customerList.get(i).getadminID(), adminID) && Objects.equals(_customerList.get(i).getLastName(), lastName)) {
                found = true;
                break;
            }                                           //finds if arguments are equal to any profile attributes of adminID and lastName
        }
        //System.out.print(_customerList);

        */System.out.println("Profile found: " + found);
        if(found){
            _customerList.remove(_customerList.indexOf(p));                        //removes profile
            _currentCustomerIndex = _customerList.indexOf(p);
            int z = _customerList.size();
            if (z+1 == n) {
                System.out.println("Profile deleted");      //ensures profile got deleted
                return true;
            }
        }
        return false;
    }

    public CustomerProf findFirstProfile() {
        _currentCustomerIndex = 0;
        if (_customerList.get(0) == null) {
            System.out.print("No profiles in list");
            return null;
        }                                           //returns first profile
        return _customerList.get(0);
    }

    public CustomerProf findNextProfile() {
        int n = _customerList.size();
        _currentCustomerIndex += 1;
        if (_currentCustomerIndex < n) {
            return _customerList.get(_currentCustomerIndex);        //returns next profile on list
        }
        return null;
    }


    public void writeAllCustomerProf() {
        try {
            //String txt = _fileName + ".txt";
            FileWriter myWriter = new FileWriter(database);
            int i;
            for (i = 0; i < _customerList.size(); i++) {
                myWriter.write(_customerList.get(i).getadminID()+"\n");
                myWriter.write("Customer Information:\n");
                myWriter.write(_customerList.get(i).getFirstName()+"\n");
                myWriter.write(_customerList.get(i).getLastName()+"\n");
                myWriter.write(_customerList.get(i).getAddress()+"\n");
                myWriter.write(_customerList.get(i).getPhone()+"\n");
                float flo = _customerList.get(i).getIncome();
                String fl = String.valueOf(flo);
                myWriter.write(fl+"\n");
                myWriter.write(_customerList.get(i).getStatus()+"\n");
                myWriter.write(_customerList.get(i).getUse()+"\n");
                myWriter.write("Vehicle Info:\n");
                myWriter.write(_customerList.get(i).getVehicleInfo().getModel()+"\n");
                myWriter.write(_customerList.get(i).getVehicleInfo().getYear()+"\n");
                myWriter.write(_customerList.get(i).getVehicleInfo().getType()+"\n");
                myWriter.write(_customerList.get(i).getVehicleInfo().getMethod()+"\n");
                myWriter.write('\n');
            }                                                   //writes to list specific order of profiles
            myWriter.close();
            System.out.println("Successfully wrote to file");
        }   catch (IOException e) {
            System.out.println("Error writing");
            e.printStackTrace();
        }
    }

    public void newInitializeDatabase(File newDB) throws FileNotFoundException {
        try {
            String txt = newDB.getPath(); //change made here
            String line = "";
            int i;
            File f = newDB;
            System.out.print("Reading...\n");
            Scanner scan = new Scanner(new File(txt)).useDelimiter(",\\s*");
            List templist = new ArrayList();
            int n;
            while (scan.hasNext()) {
                line = scan.next();
                templist.add(line);
            }
            templist = List.of(((String) templist.get(0)).split("\n"));     //splits string of lines into array arguments

            n = templist.size();
            scan.close();
            for (i = 0; i < n; i++) {
                String fo = String.valueOf(templist.get(i+6));
                float flo = Float.parseFloat(fo);
                VehicleInfo car = new VehicleInfo(((String) templist.get(i+10)),
                        ((String) templist.get(i+11)),
                        ((String) templist.get(i+12)),
                        ((String) templist.get(i+13)));
                CustomerProf c = new CustomerProf(
                        (String) templist.get(i),
                        (String) templist.get(i+2),
                        (String) templist.get(i+3),
                        (String) templist.get(i+4),
                        (String) templist.get(i+5),
                        flo,
                        (String) templist.get(i+7),
                        (String) templist.get(i+8),
                        car);

                _customerList.add(c);
                //System.out.print(i);
                i += 14;
                //takes array and sorts arguments into respective classes, then adds profile to list
            }
        } catch (IOException e) {
            System.out.println("Error reading");
            e.printStackTrace();
        }
        _currentCustomerIndex = 0;
    }

    //probably obsolete by now but holding on to it just in case, maybe it'll help sometime down the road
    public void initializeDatabase(String filename) {
        try {
            String txt = filename + ".txt";
            String line = "";
            int i;
            File f = new File(txt);
            if (!f.exists()) {
                f.createNewFile();                                      //creates file if it doesn't exist
                System.out.println("New file " + txt + " created");
            }
            else {
                System.out.print("Reading...");
                Scanner scan = new Scanner(new File(txt)).useDelimiter(",\\s*");
                List templist = new ArrayList();
                int n;
                while (scan.hasNext()) {
                    line = scan.next();
                    templist.add(line);
                }
                templist = List.of(((String) templist.get(0)).split("\n"));     //splits string of lines into array arguments

                System.out.println("Printing from Templist:");
                System.out.println("First line: " + templist.get(0));
                System.out.println("2nd line: " + templist.get(1));
                System.out.println("3rd line: " + templist.get(2));
                System.out.println("4th line: " + templist.get(3));

                n = templist.size();
                scan.close();
                for (i = 0; i < n; i++) {
                    String fo = String.valueOf(templist.get(i+6));
                    float flo = Float.parseFloat(fo);
                    VehicleInfo car = new VehicleInfo(((String) templist.get(i+10)),
                            ((String) templist.get(i+11)),
                            ((String) templist.get(i+12)),
                            ((String) templist.get(i+13)));
                    CustomerProf c = new CustomerProf(
                            (String) templist.get(i),
                            (String) templist.get(i+2),
                            (String) templist.get(i+3),
                            (String) templist.get(i+4),
                            (String) templist.get(i+5),
                            flo,
                            (String) templist.get(i+7),
                            (String) templist.get(i+8),
                            car);

                    _customerList.add(c);
                    //System.out.print(i);
                    i += 14;
                    //takes array and sorts arguments into respective classes, then adds profile to list

                }
            }
        } catch (IOException e) {
            System.out.println("Error reading");
            e.printStackTrace();
        }
        _currentCustomerIndex = 0;
    }
/*
    public static void main(String args[]) {
        CustomerProfDB test = new CustomerProfDB("CSE2102 DemoTestCase");
        System.out.println(test._customerList);
        System.out.println(test._customerList.get(0).getFirstName());
        System.out.println(test._customerList.get(1).getFirstName());
        System.out.println(test._customerList.get(2).getFirstName());
        System.out.println(test._customerList.get(3).getFirstName());
        System.out.println(test._customerList.get(4).getFirstName());
        System.out.println(test._customerList.get(5).getFirstName());     //testing
    }*/
}