import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class MainMenu extends JFrame{

    //the option currently selected, used when the select button is pressed as well as for some fancy font stuff
    int selectedOption = 0;
    //to prevent user from selecting multiple options at once
    boolean inOptionWindow = false;
    //the CustomerProfDB we're working with
    CustomerProfDB database;
    String dbName = "Database in use: No Database Selected";
    private JLabel dbChooser = new JLabel(dbName);

    //make JLabels out here so, they can be reset with the resetButtonFonts method
    private JLabel createProfile;
    private JLabel deleteProfile;
    private JLabel updateProfile;
    private JLabel fdProfile;
    private JLabel dispAllProfiles;

    public MainMenu(){
        //basic frame info like title and size
        setTitle("Integrated Customer System");
        setSize(600, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setBackground(Color.gray);
        setLayout(null);
        setResizable(false);

        //Add Title to the Screen
        JLabel ttl = new JLabel("Integrated Customer System");
        ttl.setFont(new Font("Courier New", Font.BOLD, 30));
        ttl.setForeground(Color.BLACK);
        add(ttl);
        ttl.setBounds(30, 0, 500, 100);

        //Create Profile Button
        createProfile = new JLabel("Create Profile");
        createProfile.setFont(new Font("Courier New", Font.PLAIN, 30));
        createProfile.setForeground(Color.BLACK);
        add(createProfile);
        createProfile.setBounds(40, 100, 400, 50);
        createProfile.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                if(selectedOption != 1){
                    resetButtonFonts();
                    highlightButton(createProfile);
                    selectedOption = 1;
                }
                else{
                    resetButtonFonts();
                    selectedOption = 0;
                }
            }
            public void mouseEntered(MouseEvent e){
                if(selectedOption != 1)
                    createProfile.setForeground(Color.GRAY);
            }
            public void mouseExited(MouseEvent e){
                if(selectedOption != 1)
                    createProfile.setForeground(Color.BLACK);
            }
        });

        //Delete Profile Button
        deleteProfile = new JLabel("Delete Profile");
        deleteProfile.setFont(new Font("Courier New", Font.PLAIN, 30));
        deleteProfile.setForeground(Color.BLACK);
        add(deleteProfile);
        deleteProfile.setBounds(40, 160, 400, 50);
        deleteProfile.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                if(selectedOption != 2){
                    resetButtonFonts();
                    highlightButton(deleteProfile);
                    selectedOption = 2;
                }
                else{
                    resetButtonFonts();
                    selectedOption = 0;
                }
            }
            public void mouseEntered(MouseEvent e){
                if(selectedOption != 2)
                    deleteProfile.setForeground(Color.GRAY);
            }
            public void mouseExited(MouseEvent e){
                if(selectedOption != 2)
                    deleteProfile.setForeground(Color.BLACK);
            }
        });

        //Update Profile Button
        updateProfile = new JLabel("Update Profile");
        updateProfile.setFont(new Font("Courier New", Font.PLAIN, 30));
        updateProfile.setForeground(Color.BLACK);
        add(updateProfile);
        updateProfile.setBounds(40, 220, 400, 50);
        updateProfile.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                if(selectedOption != 3){
                    resetButtonFonts();
                    highlightButton(updateProfile);
                    selectedOption = 3;
                }
                else{
                    resetButtonFonts();
                    selectedOption = 0;
                }
            }
            public void mouseEntered(MouseEvent e){
                if(selectedOption != 3)
                    updateProfile.setForeground(Color.GRAY);
            }
            public void mouseExited(MouseEvent e){
                if(selectedOption != 3)
                    updateProfile.setForeground(Color.BLACK);
            }
        });

        //Find/Display Profile Button
        fdProfile = new JLabel("Find/Display Profile");
        fdProfile.setFont(new Font("Courier New", Font.PLAIN, 30));
        fdProfile.setForeground(Color.BLACK);
        add(fdProfile);
        fdProfile.setBounds(40, 280, 400, 50);
        fdProfile.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                if(selectedOption != 4){
                    resetButtonFonts();
                    highlightButton(fdProfile);
                    selectedOption = 4;
                }
                else{
                    resetButtonFonts();
                    selectedOption = 0;
                }
            }
            public void mouseEntered(MouseEvent e){
                if(selectedOption != 4)
                    fdProfile.setForeground(Color.GRAY);
            }
            public void mouseExited(MouseEvent e){
                if(selectedOption != 4)
                    fdProfile.setForeground(Color.BLACK);
            }
        });

        //Display All Profile Button
        dispAllProfiles = new JLabel("Display All Profiles");
        dispAllProfiles.setFont(new Font("Courier New", Font.PLAIN, 30));
        dispAllProfiles.setForeground(Color.BLACK);
        add(dispAllProfiles);
        dispAllProfiles.setBounds(40, 340, 400, 50);
        dispAllProfiles.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                if(selectedOption != 5){
                    resetButtonFonts();
                    highlightButton(dispAllProfiles);
                    selectedOption = 5;
                }
                else{
                    resetButtonFonts();
                    selectedOption = 0;
                }
            }
            public void mouseEntered(MouseEvent e){
                if(selectedOption != 5)
                    dispAllProfiles.setForeground(Color.GRAY);
            }
            public void mouseExited(MouseEvent e){
                if(selectedOption != 5)
                    dispAllProfiles.setForeground(Color.BLACK);
            }
        });

        //Create the Select Button (a real button this time, not a label pretending to be one)
        JButton selButt = new JButton("Select");
        selButt.setFont(new Font("Courier New", Font.PLAIN, 20));
        add(selButt);
        selButt.setBounds(200, 430, 200, 50);
        selButt.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                selectOption(selectedOption);
            }
        });

        //Small button allowing user to change Database, displaying current Database
        initDB(); //init the database
        dbChooser.setFont(new Font("Courier New", Font.PLAIN, 15));
        dbChooser.setForeground(Color.BLACK);
        add(dbChooser);
        dbChooser.setBounds(20, 530, 600, 20);
        dbChooser.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                try {
                    changeDatabase();
                } catch (FileNotFoundException ex) {
                    JOptionPane.showMessageDialog(null, "ERROR: Database Change Failed.", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
            public void mouseEntered(MouseEvent e){
                dbChooser.setForeground(Color.GRAY);
            }
            public void mouseExited(MouseEvent e){
                dbChooser.setForeground(Color.BLACK);
            }
        });

        //Force the screen to refresh
        setSize(200, 200);
        setSize(600, 600);
    }

    public static void main(String[] args){
        new MainMenu();
    }

    private void refreshDBButton(){
        //Small button allowing user to change Database, displaying current Database
        dbChooser.setText("Database in use: " + dbName);
        //Force the screen to refresh
        setSize(200, 200);
        setSize(600, 600);
    }

    private void resetButtonFonts(){
        //set all button fonts back to their defaults
        //reset createProfile
        createProfile.setFont(new Font("Courier New", Font.PLAIN, 30));
        createProfile.setForeground(Color.BLACK);
        //reset deleteProfile
        deleteProfile.setFont(new Font("Courier New", Font.PLAIN, 30));
        deleteProfile.setForeground(Color.BLACK);
        //reset updateProfile
        updateProfile.setFont(new Font("Courier New", Font.PLAIN, 30));
        updateProfile.setForeground(Color.BLACK);
        //reset fdProfile
        fdProfile.setFont(new Font("Courier New", Font.PLAIN, 30));
        fdProfile.setForeground(Color.BLACK);
        //reset dispAllProfiles
        dispAllProfiles.setFont(new Font("Courier New", Font.PLAIN, 30));
        dispAllProfiles.setForeground(Color.BLACK);
    }

    private void highlightButton(JLabel button){ //made into a method as to enable more easy and uniform modification (as in, don't need to change each label if I want the highlight to be red now, just change this)
        button.setFont(new Font("Courier New", Font.BOLD, 30));
        button.setForeground(Color.GREEN);
    }

    private void selectOption(int option){ //take the selected menu item and execute the appropriate action
        //1 = Create Profile, 2 = Delete Profile, 3 = Update Profile, 4 = Find/Display Profile, 5 = Display all Profiles
        if(!inOptionWindow){
            switch(option){
                case 1:
                    openCreateProfile();
                    break;
                case 2:
                    openDeleteProfile();
                    break;
                case 3:
                    openUpdateProfile();
                    break;
                case 4:
                    openFindDisplayProfile();
                    break;
                case 5:
                    openDisplayAllProfiles();
                    break;
                default:
                    // No option selected, display error message via dialogue box
                    JOptionPane.showMessageDialog(null, "ERROR: No option selected. Please select an option.", "ERROR", JOptionPane.ERROR_MESSAGE);
                    break;
            }
        }
        else
            JOptionPane.showMessageDialog(null, "ERROR: Cannot do multiple tasks at once. Please finish current task.", "ERROR", JOptionPane.ERROR_MESSAGE);
    }

    private void changeDatabase() throws FileNotFoundException {
        //in here let the user change the current Database (text file) being used
        //trigger dialogue box asking for file & save result
        JFileChooser fc = new JFileChooser(); //create file chooser object
        int returnVal = fc.showOpenDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION){ //got a file
            File newDB = fc.getSelectedFile(); //we got it yay woo-hoo
            database = new CustomerProfDB(newDB);
            dbName = newDB.getName();
            refreshDBButton();
        }
        else{
            JOptionPane.showMessageDialog(null, "ERROR: Must select a file to continue.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        //change database
    }

    private void initDB(){
        File f = new File("defaultDatabase.txt");
        try {
            database = new CustomerProfDB(f);
            dbName = f.getName();
            refreshDBButton();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "ERROR: Default Database not found. Please manually select a database before continuing.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }

    }


    boolean verifyUserInformation(String adminID, String lname){
        //Have this attempt to find a profile, it will return true if success and false if fail
        //return true; //uncomment to skip verification
        if(database.findProfile(adminID, lname) == null)
            return false;
        return true;
    }

    void openCreateProfile(){
        // allow the user to create a new profile

        // basic layout stuff. very boring.
        JFrame createFrame = new JFrame();
        createFrame.setTitle("Create New Profile");
        createFrame.setSize(500, 600);
        createFrame.setLocationRelativeTo(null);
        createFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        createFrame.setVisible(true);
        createFrame.setBackground(Color.gray);
        createFrame.setLayout(null);
        createFrame.setResizable(false);

        //title, labels, & input boxes

        //title
        JLabel ttl = new JLabel("Create Profile");
        ttl.setFont(new Font("Courier New", Font.BOLD, 30));
        ttl.setForeground(Color.BLACK);
        ttl.setBounds(30, 0, 500, 100);
        createFrame.add(ttl);

        //admin ID, in via textField
        JLabel aidLabel = new JLabel("AdminID:");
        aidLabel.setFont(new Font("Courier New", Font.PLAIN, 20));
        aidLabel.setForeground(Color.BLACK);
        aidLabel.setBounds(50, 100, 300, 20);
        createFrame.add(aidLabel);
        JTextField aidField = new JTextField(20);
        aidField.setBounds(220, 100, 200, 20);
        createFrame.add(aidField);

        //first name, in via textField
        JLabel fnameLabel = new JLabel("First Name:");
        fnameLabel.setFont(new Font("Courier New", Font.PLAIN, 20));
        fnameLabel.setForeground(Color.BLACK);
        fnameLabel.setBounds(50, 130, 300, 20);
        createFrame.add(fnameLabel);
        JTextField fnameField = new JTextField(20);
        fnameField.setBounds(220, 130, 200, 20);
        createFrame.add(fnameField);

        //last name, in via textField
        JLabel lnameLabel = new JLabel("Last Name:");
        lnameLabel.setFont(new Font("Courier New", Font.PLAIN, 20));
        lnameLabel.setForeground(Color.BLACK);
        lnameLabel.setBounds(50, 160, 300, 20);
        createFrame.add(lnameLabel);
        JTextField lnameField = new JTextField(20);
        lnameField.setBounds(220, 160, 200, 20);
        createFrame.add(lnameField);

        //address, in via textField
        JLabel addrLabel = new JLabel("Address:");
        addrLabel.setFont(new Font("Courier New", Font.PLAIN, 20));
        addrLabel.setForeground(Color.BLACK);
        addrLabel.setBounds(50, 190, 300, 20);
        createFrame.add(addrLabel);
        JTextField addrField = new JTextField(20);
        addrField.setBounds(220, 190, 200, 20);
        createFrame.add(addrField);

        //phone num, in via textField
        JLabel phoneLabel = new JLabel("Phone:");
        phoneLabel.setFont(new Font("Courier New", Font.PLAIN, 20));
        phoneLabel.setForeground(Color.BLACK);
        phoneLabel.setBounds(50, 220, 300, 20);
        createFrame.add(phoneLabel);
        JTextField phoneField = new JTextField(20);
        phoneField.setBounds(220, 220, 200, 20);
        createFrame.add(phoneField);

        //income, in via textField
        JLabel incomeLabel = new JLabel("Income:");
        incomeLabel.setFont(new Font("Courier New", Font.PLAIN, 20));
        incomeLabel.setForeground(Color.BLACK);
        incomeLabel.setBounds(50, 250, 300, 20);
        createFrame.add(incomeLabel);
        JTextField incomeField = new JTextField(20);
        incomeField.setBounds(220, 250, 200, 20);
        createFrame.add(incomeField);

        //use, in via comboBox
        JLabel useLabel = new JLabel("Use:");
        useLabel.setFont(new Font("Courier New", Font.PLAIN, 20));
        useLabel.setForeground(Color.BLACK);
        useLabel.setBounds(50, 280, 300, 20);
        createFrame.add(useLabel);
        String[] useFieldOptions = {"Select an Option:", "Personal", "Business", "Other"};
        JComboBox useField = new JComboBox(useFieldOptions);
        useField.setBounds(220, 280, 200, 20);
        createFrame.add(useField);

        //status, in via comboBox
        JLabel statusLabel = new JLabel("Status:");
        statusLabel.setFont(new Font("Courier New", Font.PLAIN, 20));
        statusLabel.setForeground(Color.BLACK);
        statusLabel.setBounds(50, 310, 300, 20);
        createFrame.add(statusLabel);
        String[] statusFieldOptions = {"Select an Option:", "Active", "Inactive"};
        JComboBox statusField = new JComboBox(statusFieldOptions);
        statusField.setBounds(220, 310, 200, 20);
        createFrame.add(statusField);

        //model, in via textField
        JLabel modelLabel = new JLabel("Model:");
        modelLabel.setFont(new Font("Courier New", Font.PLAIN, 20));
        modelLabel.setForeground(Color.BLACK);
        modelLabel.setBounds(50, 340, 300, 20);
        createFrame.add(modelLabel);
        JTextField modelField = new JTextField(20);
        modelField.setBounds(220, 340, 200, 20);
        createFrame.add(modelField);

        //year, in via textField
        JLabel yearLabel = new JLabel("Year:");
        yearLabel.setFont(new Font("Courier New", Font.PLAIN, 20));
        yearLabel.setForeground(Color.BLACK);
        yearLabel.setBounds(50, 370, 300, 20);
        createFrame.add(yearLabel);
        JTextField yearField = new JTextField(20);
        yearField.setBounds(220, 370, 200, 20);
        createFrame.add(yearField);

        //type, in via comboBox
        JLabel typeLabel = new JLabel("Type:");
        typeLabel.setFont(new Font("Courier New", Font.PLAIN, 20));
        typeLabel.setForeground(Color.BLACK);
        typeLabel.setBounds(50, 400, 300, 20);
        createFrame.add(typeLabel);
        String[] typeFieldOptions = {"Select an Option:", "Sedan", "Coupe", "Hatchback", "Sports Car", "Station Wagon", "Convertible", "SUV", "Other"};
        JComboBox typeField = new JComboBox(typeFieldOptions);
        typeField.setBounds(220, 400, 200, 20);
        createFrame.add(typeField);

        //method, in via comboBox
        JLabel methodLabel = new JLabel("Method:");
        methodLabel.setFont(new Font("Courier New", Font.PLAIN, 20));
        methodLabel.setForeground(Color.BLACK);
        methodLabel.setBounds(50, 430, 300, 20);
        createFrame.add(methodLabel);
        String[] methodFieldOptions = {"Select an Option:", "Used", "New", "Refurbished", "Other"};
        JComboBox methodField = new JComboBox(methodFieldOptions);
        methodField.setBounds(220, 430, 200, 20);
        createFrame.add(methodField);

        //submit button
        JButton submitNewProfile = new JButton("Submit");
        submitNewProfile.setFont(new Font("Courier New", Font.PLAIN, 15));
        submitNewProfile.setBounds(175, 490, 150, 30);
        submitNewProfile.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){


                try {
                    Float.parseFloat(incomeField.getText());
                } catch(NumberFormatException ex){
                    JOptionPane.showMessageDialog(null, "Invalid input- please try again.", "Error", JOptionPane.ERROR_MESSAGE);

                }
                if (typeField.getSelectedIndex() == 0 || methodField.getSelectedIndex() == 0 || statusField.getSelectedIndex() == 0 || useField.getSelectedIndex() == 0) {
                    JOptionPane.showMessageDialog(null, "Please make sure all inputs are valid", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else {
                    VehicleInfo newVeh = new VehicleInfo(modelField.getText(), yearField.getText(), typeFieldOptions[typeField.getSelectedIndex()], methodFieldOptions[methodField.getSelectedIndex()]);
                    CustomerProf newProf = new CustomerProf(aidField.getText(), fnameField.getText(), lnameField.getText(), addrField.getText(), phoneField.getText(), Float.parseFloat(incomeField.getText()), statusFieldOptions[statusField.getSelectedIndex()], useFieldOptions[useField.getSelectedIndex()], newVeh);
                    database.insertNewProfile(newProf);
                    database.writeAllCustomerProf();
                    JOptionPane.showMessageDialog(null, fnameField.getText() + " " + lnameField.getText() + "'s profile has been created successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                    //displayProfile(newProf); //uncomment if you want to test teh display option but can't because the database access stuff won't work from CustomerProfDB
                    createFrame.dispose();
                }

            }
        });
        createFrame.add(submitNewProfile);

        createFrame.setSize(100, 100);
        createFrame.setSize(500, 600);
    }

    void openDeleteProfile(){
        // allow the user to delete a profile

        // basic layout stuff. still very boring.
        JFrame deleteFrame = new JFrame();
        deleteFrame.setTitle("Delete Profile");
        deleteFrame.setSize(500, 300);
        deleteFrame.setLocationRelativeTo(null);
        deleteFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        deleteFrame.setVisible(true);
        deleteFrame.setBackground(Color.gray);
        deleteFrame.setLayout(null);
        deleteFrame.setResizable(false);

        //title, labels, & input boxes

        //title
        JLabel ttl = new JLabel("Delete Profile");
        ttl.setFont(new Font("Courier New", Font.BOLD, 30));
        ttl.setForeground(Color.BLACK);
        ttl.setBounds(30, 0, 500, 100);
        deleteFrame.add(ttl);

        //admin ID, in via textField
        JLabel aidLabel = new JLabel("AdminID:");
        aidLabel.setFont(new Font("Courier New", Font.PLAIN, 20));
        aidLabel.setForeground(Color.BLACK);
        aidLabel.setBounds(50, 100, 300, 20);
        deleteFrame.add(aidLabel);
        JTextField aidField = new JTextField(20);
        aidField.setBounds(220, 100, 200, 20);
        deleteFrame.add(aidField);

        //last name, in via textField
        JLabel lnameLabel = new JLabel("Last Name:");
        lnameLabel.setFont(new Font("Courier New", Font.PLAIN, 20));
        lnameLabel.setForeground(Color.BLACK);
        lnameLabel.setBounds(50, 130, 300, 20);
        deleteFrame.add(lnameLabel);
        JTextField lnameField = new JTextField(20);
        lnameField.setBounds(220, 130, 200, 20);
        deleteFrame.add(lnameField);

        //delete button
        JButton deleteProfile = new JButton("Delete");
        deleteProfile.setFont(new Font("Courier New", Font.PLAIN, 15));
        deleteProfile.setBounds(175, 190, 150, 30);
        deleteProfile.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                // open success message via dialogue box when complete
                if(verifyUserInformation(aidField.getText(), lnameField.getText())) {

                    database.deleteProfile(aidField.getText(), lnameField.getText());
                    JOptionPane.showMessageDialog(null, "Profile with last name " + lnameField.getText() + " deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                    database.writeAllCustomerProf(); //save profile deletion
                    deleteFrame.dispose();
                }
                else
                    JOptionPane.showMessageDialog(null, "Specified profile not found. Please verify your information is accurate.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        deleteFrame.add(deleteProfile);

        deleteFrame.setSize(50, 30);
        deleteFrame.setSize(500, 300);
    }

    void openUpdateProfile(){
        // allow the user to update a profile

        // basic layout stuff. still, still very boring.
        JFrame updateFrame = new JFrame();
        updateFrame.setTitle("Update Profile");
        updateFrame.setSize(500, 300);
        updateFrame.setLocationRelativeTo(null);
        updateFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        updateFrame.setVisible(true);
        updateFrame.setBackground(Color.gray);
        updateFrame.setLayout(null);
        updateFrame.setResizable(false);

        //title, labels, & input boxes

        //title
        JLabel ttl = new JLabel("Update Profile");
        ttl.setFont(new Font("Courier New", Font.BOLD, 30));
        ttl.setForeground(Color.BLACK);
        ttl.setBounds(30, 0, 500, 100);
        updateFrame.add(ttl);

        //admin ID, in via textField
        JLabel aidLabel = new JLabel("AdminID:");
        aidLabel.setFont(new Font("Courier New", Font.PLAIN, 20));
        aidLabel.setForeground(Color.BLACK);
        aidLabel.setBounds(50, 90, 300, 20);
        updateFrame.add(aidLabel);
        JTextField aidField = new JTextField(20);
        aidField.setBounds(220, 90, 200, 20);
        updateFrame.add(aidField);

        //last name, in via textField
        JLabel lnameLabel = new JLabel("Last Name:");
        lnameLabel.setFont(new Font("Courier New", Font.PLAIN, 20));
        lnameLabel.setForeground(Color.BLACK);
        lnameLabel.setBounds(50, 120, 300, 20);
        updateFrame.add(lnameLabel);
        JTextField lnameField = new JTextField(20);
        lnameField.setBounds(220, 120, 200, 20);
        updateFrame.add(lnameField);

        //field to update, in via comboBox
        JLabel updateLabel = new JLabel("Update Field:");
        updateLabel.setFont(new Font("Courier New", Font.PLAIN, 20));
        updateLabel.setForeground(Color.BLACK);
        updateLabel.setBounds(50, 150, 300, 20);
        updateFrame.add(updateLabel);
        String[] updateFieldOptions = {"Select an Option:", "Admin ID", "First Name", "Last Name", "Address", "Phone", "Income", "Status", "Use", "Model", "Year", "Type", "Method"};
        JComboBox updateField = new JComboBox(updateFieldOptions);
        updateField.setBounds(220, 150, 200, 20);
        updateFrame.add(updateField);

        //find button
        JButton findProfile = new JButton("Find");
        findProfile.setFont(new Font("Courier New", Font.PLAIN, 15));
        findProfile.setBounds(175, 200, 150, 30);
        findProfile.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                // open success message via dialogue box when complete
                if(verifyUserInformation(aidField.getText(), lnameField.getText()) && updateField.getSelectedIndex() != 0) { //because index 0 is "Select an Option:"
                    //Update top two text fields to include user inputs
                    aidLabel.setText(aidLabel.getText() + " " + aidField.getText());
                    updateFrame.remove(aidField);
                    lnameLabel.setText(lnameLabel.getText() + " " + lnameField.getText());
                    updateFrame.remove(lnameField);
                    updateLabel.setText(updateFieldOptions[updateField.getSelectedIndex()] + ":");

                    //Update the lowest text field to match the chosen option
                    JTextField newUpdateField = new JTextField(20);
                    newUpdateField.setBounds(220, 150, 200, 20);
                    updateFrame.add(newUpdateField);
                    updateFrame.remove(updateField);

                    //Update button
                    JButton submitUpdate = new JButton("Submit");
                    submitUpdate.setFont(new Font("Courier New", Font.PLAIN, 15));
                    submitUpdate.setBounds(175, 200, 150, 30);
                    updateFrame.add(submitUpdate);
                    updateFrame.remove(findProfile);

                    //add function to new button
                    submitUpdate.addMouseListener(new MouseAdapter() {
                        public void mouseClicked(MouseEvent e) {
                            //update profile using the inputted information
                            //now just need to actually update it. Probably with a shitty solution like a really long if statement for each possible thing to be updated.
                                    //Note from future self: yup. exactly that.
                            if(updateFieldOptions[updateField.getSelectedIndex()] == "Admin ID")
                                database.findProfile(aidField.getText(), lnameField.getText()).updateadminID(newUpdateField.getText());
                            else if(updateFieldOptions[updateField.getSelectedIndex()] == "First Name")
                                database.findProfile(aidField.getText(), lnameField.getText()).updateFirstName(newUpdateField.getText());
                            else if(updateFieldOptions[updateField.getSelectedIndex()] == "Last Name")
                                database.findProfile(aidField.getText(), lnameField.getText()).updateLastName(newUpdateField.getText());
                            else if(updateFieldOptions[updateField.getSelectedIndex()] == "Address")
                                database.findProfile(aidField.getText(), lnameField.getText()).updateAddress(newUpdateField.getText());
                            else if(updateFieldOptions[updateField.getSelectedIndex()] == "Phone")
                                database.findProfile(aidField.getText(), lnameField.getText()).updatePhone(newUpdateField.getText());
                            else if(updateFieldOptions[updateField.getSelectedIndex()] == "Income")
                                database.findProfile(aidField.getText(), lnameField.getText()).updateIncome(Float.parseFloat(newUpdateField.getText()));
                            else if(updateFieldOptions[updateField.getSelectedIndex()] == "Status")
                                database.findProfile(aidField.getText(), lnameField.getText()).updateStatus(newUpdateField.getText());
                            else if(updateFieldOptions[updateField.getSelectedIndex()] == "Use")
                                database.findProfile(aidField.getText(), lnameField.getText()).updateUse(newUpdateField.getText());
                            else if(updateFieldOptions[updateField.getSelectedIndex()] == "Model")
                                database.findProfile(aidField.getText(), lnameField.getText()).getVehicleInfo().updateModel(newUpdateField.getText());
                            else if(updateFieldOptions[updateField.getSelectedIndex()] == "Year")
                                database.findProfile(aidField.getText(), lnameField.getText()).getVehicleInfo().updateYear(newUpdateField.getText());
                            else if(updateFieldOptions[updateField.getSelectedIndex()] == "Type")
                                database.findProfile(aidField.getText(), lnameField.getText()).getVehicleInfo().updateType(newUpdateField.getText());
                            else if(updateFieldOptions[updateField.getSelectedIndex()] == "Method")
                                database.findProfile(aidField.getText(), lnameField.getText()).getVehicleInfo().updateMethod(newUpdateField.getText());
                            else
                                System.out.println("Don't Know how we got here, but you've snuck your way into an invalid field. Try again later.");
                                //Not sure what to put here since it shouldn't be possible getting here but it feels wrong leaving it out, so here's an empty else

                            database.writeAllCustomerProf(); //save changes made
                            JOptionPane.showMessageDialog(null, "Profile with last name " + lnameField.getText() + " updated successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                            updateFrame.dispose();
                        }
                    });

                    updateFrame.setSize(50, 30);
                    updateFrame.setSize(500, 300);
                }
                else if(updateField.getSelectedIndex() == 0)
                    JOptionPane.showMessageDialog(null, "Please select a valid field.", "Error", JOptionPane.ERROR_MESSAGE);
                else //Error handling for profile not found
                    JOptionPane.showMessageDialog(null, "Specified profile not found. Please verify your information is accurate.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        updateFrame.add(findProfile);

        updateFrame.setSize(50, 30);
        updateFrame.setSize(500, 300);
    }

    void openFindDisplayProfile(){
        // allow the user to create a new profile
        // basic layout stuff. still very boring.
        JFrame fdFrame = new JFrame();
        fdFrame.setTitle("Display Profile");
        fdFrame.setSize(500, 300);
        fdFrame.setLocationRelativeTo(null);
        fdFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        fdFrame.setVisible(true);
        fdFrame.setBackground(Color.gray);
        fdFrame.setLayout(null);
        fdFrame.setResizable(false);

        //title, labels, & input boxes

        //title
        JLabel ttl = new JLabel("Display Profile");
        ttl.setFont(new Font("Courier New", Font.BOLD, 30));
        ttl.setForeground(Color.BLACK);
        ttl.setBounds(30, 0, 500, 100);
        fdFrame.add(ttl);

        //admin ID, in via textField
        JLabel aidLabel = new JLabel("AdminID:");
        aidLabel.setFont(new Font("Courier New", Font.PLAIN, 20));
        aidLabel.setForeground(Color.BLACK);
        aidLabel.setBounds(50, 100, 300, 20);
        fdFrame.add(aidLabel);
        JTextField aidField = new JTextField(20);
        aidField.setBounds(220, 100, 200, 20);
        fdFrame.add(aidField);

        //last name, in via textField
        JLabel lnameLabel = new JLabel("Last Name:");
        lnameLabel.setFont(new Font("Courier New", Font.PLAIN, 20));
        lnameLabel.setForeground(Color.BLACK);
        lnameLabel.setBounds(50, 130, 300, 20);
        fdFrame.add(lnameLabel);
        JTextField lnameField = new JTextField(20);
        lnameField.setBounds(220, 130, 200, 20);
        fdFrame.add(lnameField);

        //delete button
        JButton deleteProfile = new JButton("Find");
        deleteProfile.setFont(new Font("Courier New", Font.PLAIN, 15));
        deleteProfile.setBounds(175, 190, 150, 30);
        deleteProfile.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                // open success message via dialogue box when complete
                if(verifyUserInformation(aidField.getText(), lnameField.getText())) {
                    //Still doesn't really work right since CustomerProfDB doesn't work right...
                    displayProfile(database.findProfile(aidField.getText(), lnameField.getText()));
                    fdFrame.dispose(); //close now that we're done
                }
                else
                    JOptionPane.showMessageDialog(null, "Specified profile not found. Please verify your information is accurate.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        fdFrame.add(deleteProfile);

        fdFrame.setSize(50, 30);
        fdFrame.setSize(500, 300);
    }

    private void displayProfile(CustomerProf profToDisplay){
        //only for use by openFindDisplayProfile, will not work for openDisplayAllProfiles
        // basic layout stuff. very boring.
        JFrame createFrame = new JFrame();
        createFrame.setTitle("Display Profile");
        createFrame.setSize(500, 600);
        createFrame.setLocationRelativeTo(null);
        createFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        createFrame.setVisible(true);
        createFrame.setBackground(Color.gray);
        createFrame.setLayout(null);
        createFrame.setResizable(false);

        //title, labels, & input boxes

        //title
        JLabel ttl = new JLabel("Display Profile");
        ttl.setFont(new Font("Courier New", Font.BOLD, 30));
        ttl.setForeground(Color.BLACK);
        ttl.setBounds(30, 0, 500, 100);
        createFrame.add(ttl);

        //admin ID, in via textField
        JLabel aidLabel = new JLabel("AdminID:");
        aidLabel.setFont(new Font("Courier New", Font.PLAIN, 20));
        aidLabel.setForeground(Color.BLACK);
        aidLabel.setBounds(50, 100, 300, 20);
        createFrame.add(aidLabel);
        JLabel aidField = new JLabel(profToDisplay.getadminID());
        aidField.setBounds(220, 100, 200, 20);
        createFrame.add(aidField);

        //first name, in via textField
        JLabel fnameLabel = new JLabel("First Name:");
        fnameLabel.setFont(new Font("Courier New", Font.PLAIN, 20));
        fnameLabel.setForeground(Color.BLACK);
        fnameLabel.setBounds(50, 130, 300, 20);
        createFrame.add(fnameLabel);
        JLabel fnameField = new JLabel(profToDisplay.getFirstName());
        fnameField.setBounds(220, 130, 200, 20);
        createFrame.add(fnameField);

        //last name, in via textField
        JLabel lnameLabel = new JLabel("Last Name:");
        lnameLabel.setFont(new Font("Courier New", Font.PLAIN, 20));
        lnameLabel.setForeground(Color.BLACK);
        lnameLabel.setBounds(50, 160, 300, 20);
        createFrame.add(lnameLabel);
        JLabel lnameField = new JLabel(profToDisplay.getLastName());
        lnameField.setBounds(220, 160, 200, 20);
        createFrame.add(lnameField);

        //address, in via textField
        JLabel addrLabel = new JLabel("Address:");
        addrLabel.setFont(new Font("Courier New", Font.PLAIN, 20));
        addrLabel.setForeground(Color.BLACK);
        addrLabel.setBounds(50, 190, 300, 20);
        createFrame.add(addrLabel);
        JLabel addrField = new JLabel(profToDisplay.getAddress());
        addrField.setBounds(220, 190, 200, 20);
        createFrame.add(addrField);

        //phone num, in via textField
        JLabel phoneLabel = new JLabel("Phone:");
        phoneLabel.setFont(new Font("Courier New", Font.PLAIN, 20));
        phoneLabel.setForeground(Color.BLACK);
        phoneLabel.setBounds(50, 220, 300, 20);
        createFrame.add(phoneLabel);
        JLabel phoneField = new JLabel(profToDisplay.getPhone());
        phoneField.setBounds(220, 220, 200, 20);
        createFrame.add(phoneField);

        //income, in via textField
        JLabel incomeLabel = new JLabel("Income:");
        incomeLabel.setFont(new Font("Courier New", Font.PLAIN, 20));
        incomeLabel.setForeground(Color.BLACK);
        incomeLabel.setBounds(50, 250, 300, 20);
        createFrame.add(incomeLabel);
        JLabel incomeField = new JLabel(String.valueOf(profToDisplay.getIncome()));
        incomeField.setBounds(220, 250, 200, 20);
        createFrame.add(incomeField);

        //use, in via comboBox
        JLabel useLabel = new JLabel("Use:");
        useLabel.setFont(new Font("Courier New", Font.PLAIN, 20));
        useLabel.setForeground(Color.BLACK);
        useLabel.setBounds(50, 280, 300, 20);
        createFrame.add(useLabel);
        JLabel useField = new JLabel(profToDisplay.getUse());
        useField.setBounds(220, 280, 200, 20);
        createFrame.add(useField);

        //status, in via comboBox
        JLabel statusLabel = new JLabel("Status:");
        statusLabel.setFont(new Font("Courier New", Font.PLAIN, 20));
        statusLabel.setForeground(Color.BLACK);
        statusLabel.setBounds(50, 310, 300, 20);
        createFrame.add(statusLabel);
        JLabel statusField = new JLabel(profToDisplay.getStatus());
        statusField.setBounds(220, 310, 200, 20);
        createFrame.add(statusField);

        //model, in via textField
        JLabel modelLabel = new JLabel("Model:");
        modelLabel.setFont(new Font("Courier New", Font.PLAIN, 20));
        modelLabel.setForeground(Color.BLACK);
        modelLabel.setBounds(50, 340, 300, 20);
        createFrame.add(modelLabel);
        JLabel modelField = new JLabel(profToDisplay.getVehicleInfo().getModel());
        modelField.setBounds(220, 340, 200, 20);
        createFrame.add(modelField);

        //year, in via textField
        JLabel yearLabel = new JLabel("Year:");
        yearLabel.setFont(new Font("Courier New", Font.PLAIN, 20));
        yearLabel.setForeground(Color.BLACK);
        yearLabel.setBounds(50, 370, 300, 20);
        createFrame.add(yearLabel);
        JLabel yearField = new JLabel(profToDisplay.getVehicleInfo().getYear());
        yearField.setBounds(220, 370, 200, 20);
        createFrame.add(yearField);

        //type, in via comboBox
        JLabel typeLabel = new JLabel("Type:");
        typeLabel.setFont(new Font("Courier New", Font.PLAIN, 20));
        typeLabel.setForeground(Color.BLACK);
        typeLabel.setBounds(50, 400, 300, 20);
        createFrame.add(typeLabel);
        JLabel typeField = new JLabel(profToDisplay.getVehicleInfo().getType());
        typeField.setBounds(220, 400, 200, 20);
        createFrame.add(typeField);

        //method, in via comboBox
        JLabel methodLabel = new JLabel("Method:");
        methodLabel.setFont(new Font("Courier New", Font.PLAIN, 20));
        methodLabel.setForeground(Color.BLACK);
        methodLabel.setBounds(50, 430, 300, 20);
        createFrame.add(methodLabel);
        JLabel methodField = new JLabel(profToDisplay.getVehicleInfo().getMethod());
        methodField.setBounds(220, 430, 200, 20);
        createFrame.add(methodField);


        createFrame.setSize(50, 60);
        createFrame.setSize(380, 540);
    }

    void openDisplayAllProfiles(){
        // allow the user to display ALL profiles
        //start by initializing it, then call the helper
        CustomerProf profToDisplay = database.findFirstProfile();

        JFrame createFrame = new JFrame();
        createFrame.setTitle("Display Profile");
        createFrame.setSize(500, 600);
        createFrame.setLocationRelativeTo(null);
        createFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        createFrame.setVisible(true);
        createFrame.setBackground(Color.gray);
        createFrame.setLayout(null);
        createFrame.setResizable(false);

        //title, labels, & input boxes

        //title
        JLabel ttl = new JLabel("Display Profile");
        ttl.setFont(new Font("Courier New", Font.BOLD, 30));
        ttl.setForeground(Color.BLACK);
        ttl.setBounds(30, 0, 500, 100);
        createFrame.add(ttl);

        //admin ID, in via textField
        JLabel aidLabel = new JLabel("AdminID:");
        aidLabel.setFont(new Font("Courier New", Font.PLAIN, 20));
        aidLabel.setForeground(Color.BLACK);
        aidLabel.setBounds(50, 100, 300, 20);
        createFrame.add(aidLabel);
        JLabel aidField = new JLabel(profToDisplay.getadminID());
        aidField.setBounds(220, 100, 200, 20);
        createFrame.add(aidField);

        //first name, in via textField
        JLabel fnameLabel = new JLabel("First Name:");
        fnameLabel.setFont(new Font("Courier New", Font.PLAIN, 20));
        fnameLabel.setForeground(Color.BLACK);
        fnameLabel.setBounds(50, 130, 300, 20);
        createFrame.add(fnameLabel);
        JLabel fnameField = new JLabel(profToDisplay.getFirstName());
        fnameField.setBounds(220, 130, 200, 20);
        createFrame.add(fnameField);

        //last name, in via textField
        JLabel lnameLabel = new JLabel("Last Name:");
        lnameLabel.setFont(new Font("Courier New", Font.PLAIN, 20));
        lnameLabel.setForeground(Color.BLACK);
        lnameLabel.setBounds(50, 160, 300, 20);
        createFrame.add(lnameLabel);
        JLabel lnameField = new JLabel(profToDisplay.getLastName());
        lnameField.setBounds(220, 160, 200, 20);
        createFrame.add(lnameField);

        //address, in via textField
        JLabel addrLabel = new JLabel("Address:");
        addrLabel.setFont(new Font("Courier New", Font.PLAIN, 20));
        addrLabel.setForeground(Color.BLACK);
        addrLabel.setBounds(50, 190, 300, 20);
        createFrame.add(addrLabel);
        JLabel addrField = new JLabel(profToDisplay.getAddress());
        addrField.setBounds(220, 190, 200, 20);
        createFrame.add(addrField);

        //phone num, in via textField
        JLabel phoneLabel = new JLabel("Phone:");
        phoneLabel.setFont(new Font("Courier New", Font.PLAIN, 20));
        phoneLabel.setForeground(Color.BLACK);
        phoneLabel.setBounds(50, 220, 300, 20);
        createFrame.add(phoneLabel);
        JLabel phoneField = new JLabel(profToDisplay.getPhone());
        phoneField.setBounds(220, 220, 200, 20);
        createFrame.add(phoneField);

        //income, in via textField
        JLabel incomeLabel = new JLabel("Income:");
        incomeLabel.setFont(new Font("Courier New", Font.PLAIN, 20));
        incomeLabel.setForeground(Color.BLACK);
        incomeLabel.setBounds(50, 250, 300, 20);
        createFrame.add(incomeLabel);
        JLabel incomeField = new JLabel(String.valueOf(profToDisplay.getIncome()));
        incomeField.setBounds(220, 250, 200, 20);
        createFrame.add(incomeField);

        //use, in via comboBox
        JLabel useLabel = new JLabel("Use:");
        useLabel.setFont(new Font("Courier New", Font.PLAIN, 20));
        useLabel.setForeground(Color.BLACK);
        useLabel.setBounds(50, 280, 300, 20);
        createFrame.add(useLabel);
        JLabel useField = new JLabel(profToDisplay.getUse());
        useField.setBounds(220, 280, 200, 20);
        createFrame.add(useField);

        //status, in via comboBox
        JLabel statusLabel = new JLabel("Status:");
        statusLabel.setFont(new Font("Courier New", Font.PLAIN, 20));
        statusLabel.setForeground(Color.BLACK);
        statusLabel.setBounds(50, 310, 300, 20);
        createFrame.add(statusLabel);
        JLabel statusField = new JLabel(profToDisplay.getStatus());
        statusField.setBounds(220, 310, 200, 20);
        createFrame.add(statusField);

        //model, in via textField
        JLabel modelLabel = new JLabel("Model:");
        modelLabel.setFont(new Font("Courier New", Font.PLAIN, 20));
        modelLabel.setForeground(Color.BLACK);
        modelLabel.setBounds(50, 340, 300, 20);
        createFrame.add(modelLabel);
        JLabel modelField = new JLabel(profToDisplay.getVehicleInfo().getModel());
        modelField.setBounds(220, 340, 200, 20);
        createFrame.add(modelField);

        //year, in via textField
        JLabel yearLabel = new JLabel("Year:");
        yearLabel.setFont(new Font("Courier New", Font.PLAIN, 20));
        yearLabel.setForeground(Color.BLACK);
        yearLabel.setBounds(50, 370, 300, 20);
        createFrame.add(yearLabel);
        JLabel yearField = new JLabel(profToDisplay.getVehicleInfo().getYear());
        yearField.setBounds(220, 370, 200, 20);
        createFrame.add(yearField);

        //type, in via comboBox
        JLabel typeLabel = new JLabel("Type:");
        typeLabel.setFont(new Font("Courier New", Font.PLAIN, 20));
        typeLabel.setForeground(Color.BLACK);
        typeLabel.setBounds(50, 400, 300, 20);
        createFrame.add(typeLabel);
        JLabel typeField = new JLabel(profToDisplay.getVehicleInfo().getType());
        typeField.setBounds(220, 400, 200, 20);
        createFrame.add(typeField);

        //method, in via comboBox
        JLabel methodLabel = new JLabel("Method:");
        methodLabel.setFont(new Font("Courier New", Font.PLAIN, 20));
        methodLabel.setForeground(Color.BLACK);
        methodLabel.setBounds(50, 430, 300, 20);
        createFrame.add(methodLabel);
        JLabel methodField = new JLabel(profToDisplay.getVehicleInfo().getMethod());
        methodField.setBounds(220, 430, 200, 20);
        createFrame.add(methodField);

        JButton nextProf = new JButton("Next");
        nextProf.setFont(new Font("Courier New", Font.PLAIN, 15));
        createFrame.add(nextProf);
        nextProf.setBounds(155, 490, 70, 30);
        nextProf.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                CustomerProf nextProf = database.findNextProfile();
                if(nextProf!=null) {
                    aidField.setText(nextProf.getadminID());
                    fnameField.setText(nextProf.getFirstName());
                    lnameField.setText(nextProf.getLastName());
                    addrField.setText(nextProf.getAddress());
                    phoneField.setText(nextProf.getPhone());
                    incomeField.setText(String.valueOf(nextProf.getIncome()));
                    statusField.setText(nextProf.getStatus());
                    useField.setText(nextProf.getUse());
                    modelField.setText(nextProf.getVehicleInfo().getModel());
                    yearField.setText(nextProf.getVehicleInfo().getYear());
                    typeField.setText(nextProf.getVehicleInfo().getType());
                    methodField.setText(nextProf.getVehicleInfo().getMethod());
                }
                else{
                    JOptionPane.showMessageDialog(null, "Last profile reached. Closing.", "End", JOptionPane.INFORMATION_MESSAGE);
                    createFrame.dispose();
                }
            }
        });

        createFrame.setSize(50, 60);
        createFrame.setSize(380, 600);

    }

}
