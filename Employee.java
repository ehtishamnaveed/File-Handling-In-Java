package ACP.Employee;
import javax.swing.*;
import java.io.*;
import java.time.*;
import java.time.temporal.ChronoField;

public class Employee implements Serializable
{
    String Employee_Name, Father_Name , Job_Category , Education , NIC;
    int Emp_ID = 8999;
    int Pay_Scale;
    LocalDate Date_of_birth;


    // #######   >>>>>>     GUI FUNCTIONS     <<<<<<   #######
    // ********************   Save Employee Information function
    public String set_Employee_Information()
    {
        set_Name();
        set_Father_Name();
        set_ID();
        set_Job_Category();
        set_Birthday();
        set_Education();
        set_Pay_Scale();
        set_NIC();
        String option = confirm_Record();
        return option;
    }

    // ********************   Set Employee Name function
    public void set_Name()
    {
        reTry:
        while(true)
        {
            try
            {
                Employee_Name = JOptionPane.showInputDialog("Enter Name");
                // Check is left Empty
                if(Employee_Name.equals(""))
                    throw new Exception();
                break;
            }
            catch(Exception e) 
            { 
                JOptionPane.showMessageDialog(null,"ERROR: Field can not be Empty","ERROR",JOptionPane.ERROR_MESSAGE); 
                continue reTry; 
            }
        }
    }

    // ********************   Set Father Name function
    public void set_Father_Name()
    {
        reTry:
        while(true)
        {
            try
            {
                Father_Name = JOptionPane.showInputDialog("Enter Father Name");
                // Check is left Empty
                if(Father_Name.equals(""))
                    throw new Exception();
                break;
            }
            catch(Exception e) 
            { 
                JOptionPane.showMessageDialog(null,"ERROR: Field can not be Empty","ERROR",JOptionPane.ERROR_MESSAGE); 
                continue reTry; 
            }
        }
    }

    // ********************   Set Employee ID function
    public void set_ID()
    {
        Employee obj = null;
        //boolean count = true;
        try
        {
            FileInputStream emp_info = new FileInputStream("EmpDB.dat");
            while(true) 
            {
                try
                {
                    ObjectInputStream read_data = new ObjectInputStream(emp_info);
                    obj = (Employee) read_data.readObject(); // Reading the object for the Last Employee ID
                    if (read_data != null) 
                        Emp_ID = obj.Emp_ID;
                }
                catch(NullPointerException e)
                {
                    emp_info.close();
                    break;
                }
            }
        }
        catch(Exception e){}
        Emp_ID++;
    }

    // ********************   Set Job Category function
    public void set_Job_Category()
    {
        //boolean choice = false;
        setJob:
        while(true)
        {
            Job_Category = JOptionPane.showInputDialog("Select the Job:\n"+ 
                                                          " > Teacher\n"+
                                                          " > Officer\n"+
                                                          " > Staff\n"+
                                                          " > Labour\n");
            switch(Job_Category)
            {
                case("Teacher"): case("teacher"):
                    //JOptionPane.showMessageDialog(null, "Job_Category is selected as: 'Teacher'");
                    break;

                case("Officer"): case("officer"):
                    //JOptionPane.showMessageDialog(null, "Job_Category is selected as: 'Officer'");
                    break;

                case("Staff"): case("staff"):
                    //JOptionPane.showMessageDialog(null, "Job_Category is selected as: 'Staff'");
                    break;

                case("Labour"): case("labour"):
                    //JOptionPane.showMessageDialog(null, "Job_Category is selected as: 'Labour'");
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "ERROR: Invalid","ERROR",JOptionPane.ERROR_MESSAGE);
                    continue setJob;
            }
            break;
        }
    }

    // ********************   Set Empolyee Birthday function
    public void set_Birthday()
    {
        int birth_year = 0 , birth_day = 0 , birth_month = 0;
        String bir_year , bir_day , bir_month;

        // Getting input for Employee's Birthday

        // ****************** Input for Year of Birthday
        setYear:
        while(true)
        {
            // Check if not Integer Value
            try
            {
                bir_year = JOptionPane.showInputDialog("Enter the Year of Birth\n (1975 - 2010)"); 
                birth_year = Integer.parseInt(bir_year);
            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(null,"ERROR: Faulty Input","ERROR",JOptionPane.ERROR_MESSAGE);
                continue setYear;
            }

            // Check for Year Condition
            if(birth_year >= 1975 && birth_year <= 2010)
            {
                break;                
            }
            else 
            {
                JOptionPane.showMessageDialog(null,"ERROR: Ileligible choice","ERROR",JOptionPane.ERROR_MESSAGE);
                continue;
            }
        }

        //  ****************** Input for Day of Birthday
        setDay:
        while(true)
        {
            // Check if not Integer
            try
            {
                bir_day = JOptionPane.showInputDialog("Enter the Day of Birth\n (1 - 31)"); 
                birth_day = Integer.parseInt(bir_day);
            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(null,"ERROR: Faulty Input","ERROR",JOptionPane.ERROR_MESSAGE);
                continue setDay;
            }

            // Check for number of Days
            if(birth_day >= 1 && birth_day <= 31)
            {
                break;
            }
            else
            {
                JOptionPane.showMessageDialog(null,"ERROR: Invalid 'Enter between (1 - 31)'","ERROR",JOptionPane.ERROR_MESSAGE);
                continue;
            }

        }

       //  ****************** Input for Month of Birthday
       setMonth:
       while(true)
       {
            // Check if not Integer
            try
            {
                bir_month = JOptionPane.showInputDialog("Enter the Month of Birth\n (1 - 12)"); 
                birth_month = Integer.parseInt(bir_month);
            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(null,"ERROR: Faulty Input","ERROR",JOptionPane.ERROR_MESSAGE);
                continue setMonth;
            }

            // Check for Months
            if(birth_month >= 1 && birth_month <= 12)
            {
                break;
            }
            else
            {
                JOptionPane.showMessageDialog(null,"ERROR: Invalid 'Enter between (1 - 12)'","ERROR",JOptionPane.ERROR_MESSAGE);
                continue;
            }
        }

        //  Check if for Days in Months
        try
        {
            Date_of_birth = LocalDate.of(birth_year , birth_month , birth_day);
        }
        catch(Exception e)
        {
            Month monthName = Month.of(birth_month);
            JOptionPane.showMessageDialog(null,"ERROR: \"" + monthName + "\" can not have \"" + birth_day + "\" days","ERROR",JOptionPane.ERROR_MESSAGE); 
            set_Birthday();
        }
        // System.out.println("Birthday is on : "+ Date_of_birth);
        //JOptionPane.showMessageDialog(null, "Birthday is on : "+ Date_of_birth);
    }

    // ********************   Set Education function
    public void set_Education()
    {
        getEducation:
        while(true)
        {
                // Input for Employee's Education
                Education = JOptionPane.showInputDialog("Select the Education:\n"+ 
                                                            " > Matric\n"+
                                                            " > FSc\n"+
                                                            " > BS\n"+
                                                            " > MS\n"+
                                                            " > PHD\n");
            switch(Job_Category)
            {
                // *******************************
                // Check for Teacher's Education
                case("Teacher"): case("teacher"):  
                    switch(Education)
                    {
                        case("MS"): case("ms"):  // For MS
                            //JOptionPane.showMessageDialog(null, "Education is selected as 'MS'");
                            break;

                        case("PHD"): case("phd"):  // For PHD
                            //JOptionPane.showMessageDialog(null, "Education is selected as 'PHD'");
                            break;

                        default:
                            JOptionPane.showMessageDialog(null, "ERROR: Teacher's Education can not be lower than \"MS\"","ERROR",JOptionPane.ERROR_MESSAGE);
                            continue getEducation;
                    }
                    break;

                // *******************************
                // Check for Officer's Education
                case("Officer"): case("officer"): 
                    switch(Education)
                    {
                        case("BS"): case("bs"):  // For BS
                            //JOptionPane.showMessageDialog(null, "Education is selected as 'BS'");
                            break;

                        case("MS"): case("ms"):  // For MS
                            //JOptionPane.showMessageDialog(null, "Education is selected as: 'MS'");
                            break;

                        case("PHD"): case("phd"):  // For PHD
                            //JOptionPane.showMessageDialog(null, "Education is selected as: 'PHD'");
                            break;

                        default:
                            JOptionPane.showMessageDialog(null, "ERROR: Officer's Education can not be lower than \"BS\"","ERROR",JOptionPane.ERROR_MESSAGE);
                            continue getEducation;
                    }
                    break;

                // *******************************
                // Check for Staff's Education
                case("Staff"): case("staff"):  
                    switch(Education)
                    {
                        case("FSc"): case("fsc"):  // For Fsc
                            //JOptionPane.showMessageDialog(null, "Education is selected as: 'FSc'");
                            break;

                        case("BS"): case("bs"):  // For BS
                            //JOptionPane.showMessageDialog(null, "Education is selected as: 'BS'");
                            break;

                        case("MS"): case("ms"):  // For MS
                            //JOptionPane.showMessageDialog(null, "Education is selected as: 'MS'");
                            break;

                        case("PHD"): case("phd"):  // For PHD
                            //JOptionPane.showMessageDialog(null, "Education is selected as: 'PHD'");
                            break;

                        default:
                            JOptionPane.showMessageDialog(null, "ERROR: Staff's Education can not be lower than \"FSc\"","ERROR",JOptionPane.ERROR_MESSAGE);
                            continue getEducation;
                    }
                    break;

                // *******************************
                // Check for Labour's Education
                case("Labour"): case("labour"):  
                    switch(Education)
                    {
                        case("Matric"): case("matric"):  // For Matric
                            //JOptionPane.showMessageDialog(null, "Education is selected as: 'Matric'");
                            break;

                        case("FSc"): case("fsc"):  // For Fsc
                            //JOptionPane.showMessageDialog(null, "Education is selected as: 'FSc'");
                            break;

                        case("BS"): case("bs"):  // For BS
                            //JOptionPane.showMessageDialog(null, "Education is selected as: 'BS'");
                            break;

                        case("MS"): case("ms"):  // For MS
                            //JOptionPane.showMessageDialog(null, "Education is selected as: 'MS'");
                            break;

                        case("PHD"): case("phd"):  // For PHD
                            //JOptionPane.showMessageDialog(null, "Education is selected as: 'PHD'");
                            break;

                        default:
                            JOptionPane.showMessageDialog(null, "ERROR: Labour's Education can not be lower than \"Matric\"","ERROR",JOptionPane.ERROR_MESSAGE);
                            continue getEducation;
                    }
                    break;
            }
            break;
        }
    }

    // ********************   Set Pay Scale function
    public void set_Pay_Scale()
    {
        //boolean choice = false;
        setPayScale:
        while(true)
        {
            // Check for Integer Input
            try
            {
                String pay = JOptionPane.showInputDialog("Enter Pay Scale");
                Pay_Scale = Integer.parseInt(pay);
                if(Pay_Scale == 0)
                    throw new Exception();
                //break;
            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(null,"ERROR: Faulty Input","ERROR",JOptionPane.ERROR_MESSAGE);
                continue setPayScale;
            }

            // Check for Pay Scale with respect to Job Category
            switch(Job_Category)
            {
                // *******************************
                // Check for Teacher's Pay Scale
                case("Teacher"): case("teacher"):
                    if(Pay_Scale >= 18)
                        break;
                    else
                    {
                        JOptionPane.showMessageDialog(null, "ERROR: Teacher Pay scale should not be lesser than \"18\"","ERROR",JOptionPane.ERROR_MESSAGE);
                        continue;
                    }

                // *******************************
                // Check for Officer's Pay Scale
                case("Officer"): case("officer"):
                    if(Pay_Scale >= 17)
                        break;
                    else
                    {
                        JOptionPane.showMessageDialog(null, "ERROR: Officer Pay scale should not be lesser than \"17\"","ERROR",JOptionPane.ERROR_MESSAGE);
                        continue;
                    }

                // *******************************
                // Check for Staff's Pay Scale
                case("Staff"): case("staff"):
                    if(Pay_Scale >= 11 && Pay_Scale <= 16)
                        break;
                    else
                    {
                        JOptionPane.showMessageDialog(null, "ERROR: Staff Pay scale should be in between \"11 - 16\"","ERROR",JOptionPane.ERROR_MESSAGE);
                        continue;
                    }

                // *******************************
                // Check for Labour's Pay Scale
                case("Labour"): case("labour"):
                    if(Pay_Scale >= 1 && Pay_Scale <= 10)
                        break;
                    else
                    {
                        JOptionPane.showMessageDialog(null, "ERROR: Labour Pay scale should be in between \"1 - 10\"","ERROR",JOptionPane.ERROR_MESSAGE);
                        continue;
                    }
            }
            break;
        }
    }

    // ********************   Set NIC function
    public void set_NIC()
    {
        setNIC:
        while(true)
        {
            try
            {
                NIC = JOptionPane.showInputDialog("Enter NIC");
                if(NIC.equals(""))
                    throw new Exception();
                break;

            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(null, "ERROR: Field can not be Empty","ERROR",JOptionPane.ERROR_MESSAGE);
                continue setNIC;
            }
        }
    }

    // ********************   Empolyee Age Calulator function
    public int Age()
    {
        LocalDate curr = LocalDate.now();
        int age = Period.between(Date_of_birth , curr).getYears();
        // System.out.println("Age is : "+ Age);
        //JOptionPane.showMessageDialog(null, "Age is : "+ Age);
        return age;
    }

    // ********************   Display Information for confirmation of record function
    public String confirm_Record() 
    {
        String choice = ""; 
        //boolean input = false;
        re_enter:
        while(true)
        {
                // Confirmation of Record
                choice = JOptionPane.showInputDialog(" ****   Empolyee Information   ****"+
                                                            //" _-_-_-_-_-_-_-_-_-_-_-_ "+"\n"+
                                                            "\n Name : " + Employee_Name +
                                                            "\n Father Name : " + Father_Name +
                                                            "\n ID Number : " + Emp_ID +
                                                            "\n Designation : " + Job_Category +
                                                            "\n Birthday : " + Date_of_birth +
                                                            "\n Education : " + Education +
                                                            "\n Pay : " + Pay_Scale +
                                                            "\n NIC : " + NIC +
                                                            //"\n _-_-_-_-_-_-_-_-_-_-_-_ "+
                                                            "\n - - - - - - - - - - - - - - - - "+
                                                            "\n \"Confirm the record:\"   (Y / N)");
                switch(choice)
                {
                    case "Y": case "y":
                        return choice;

                    case "N": case "n":
                        return choice;

                    default:
                        JOptionPane.showMessageDialog(null,"ERROR: Invalid Option","ERROR",JOptionPane.ERROR_MESSAGE);
                        continue re_enter;
                }
        }
    }

    // ********************   Display Employee's Information function
    public void display_Employee_Information()
    {
        JOptionPane.showMessageDialog(null," ****   Empolyee Information   ****"+
                                            //" _-_-_-_-_-_-_-_-_-_-_-_ "+"\n"+
                                            "\n Name : " + Employee_Name +
                                            "\n Father Name : " + Father_Name +
                                            "\n ID Number : " + Emp_ID +
                                            "\n Job Designation : " + Job_Category +
                                            "\n Birthday : " + Date_of_birth +
                                            "\n Education : " + Education +
                                            "\n Pay : " + Pay_Scale +
                                            "\n NIC : " + NIC ); //+
                                            //"\n _-_-_-_-_-_-_-_-_-_-_-_ "+
                                            //"\n - - - - - - - - - - - - - - - - ");
    }
    // **********************************************************************************************************


    // #######   >>>>>>     SERIALIZATION FUNCTIONS     <<<<<<   #######
    // ********************   Writing  Data into File function
    public void write_Record(final String file_name)
    {
        switch(file_name)
        {
            // *******************************
            // Main file for adding Employee Records
            case "EmpDB.dat":
                 // Write Employee Data
                try
                {
                    FileOutputStream emp_info = new FileOutputStream(file_name,true); // Creating and Opening the file in Append Mode
                    ObjectOutputStream write_info = new ObjectOutputStream(emp_info); // Creating Output Steam
                    write_info.writeObject(this); // Writing the Employee Information
                    JOptionPane.showMessageDialog(null,"Successfully saved the data.");
                    emp_info.close();
                    write_info.close();
                } 
                catch (IOException e)
                {
                    e.printStackTrace();
                }
                break;

            // *******************************
            // Tempory file for Delection & Updation of Records
             case "temp.dat":
                 // Write Employee Data
                try
                {
                    FileOutputStream emp_info = new FileOutputStream(file_name,true); // Creating and Opening the file in Append Mode
                    ObjectOutputStream write_info = new ObjectOutputStream(emp_info); // Creating Output Steam
                    write_info.writeObject(this); // Writing the Employee Information
                    emp_info.close();
                    write_info.close();
                } 
                catch (IOException e)
                {
                    e.printStackTrace();
                }
                break;
        }
    }

    // ********************   Reading Data from File function
    public void show_All_Records()
    {
        Employee emp = null;
        int records = read_All_Records();
        JOptionPane.showMessageDialog(null,"Total Records: \"" + records + "\'");
        //boolean count = true;
        try
        {
            FileInputStream emp_info = new FileInputStream("EmpDB.dat"); // Opening the file
            // Read all Records
            while(true) 
            {
                try{
                        ObjectInputStream read_info = new ObjectInputStream(emp_info); // Creating an Input Stream
                        emp = (Employee) read_info.readObject();
                        if (emp != null)
                        { 
                            emp.display_Employee_Information(); // Displaying Employee's Information
                        }
                    }
                catch(Exception e) {emp_info.close(); break;}
            }
        }
        catch(Exception e){}
    }

    // ********************   Reading Data from File function
    public void read_Records_By(final String condition)
    {
        // Read Employee File
        int records = 0;
        Employee emp = null;
        //boolean count = true;
            //FileInputStream emp_info = new FileInputStream("EmpDB.dat"); // Opening the file
        switch(condition)
        {
            // *******************************
            // Search by ID Number
            case "ID": case "id":
                //boolean input_id = false;
                int temp_id = 0;
                // check for Integer Input
                re_enter:
                while(true)
                {
                    try
                    {
                        String id = JOptionPane.showInputDialog("Enter ID number:");
                        temp_id = Integer.parseInt(id);
                        break;
                    }
                    catch(Exception e)
                    {
                        JOptionPane.showMessageDialog(null,"ERROR: Faulty Input","ERROR",JOptionPane.ERROR_MESSAGE);
                        continue re_enter;
                    }
                }

                // Check for how many Records are there
                try
                {
                    FileInputStream fis = new FileInputStream("EmpDB.dat");   
                    //boolean cont = true;
                    while(true) 
                    {
                        try
                        {
                            ObjectInputStream input = new ObjectInputStream(fis);
                            emp = (Employee) input.readObject();
                            if (temp_id == emp.Emp_ID) // f != null &&
                            {
                              records++;
                            }
                        }
                        catch(Exception e) { fis.close(); break; }
                    }
                }
                catch(Exception e) {}

                JOptionPane.showMessageDialog(null,"Total Records: \"" + records + "\'");
                if(records != 0)
                    emp.show_Records_via_Emp_ID(temp_id); // Displaying if the record is founded
                break;

            // *******************************
            // Search by Name
            case "Name": case "name":
                String name = JOptionPane.showInputDialog("Enter Name:");
                // Read all Records of ID given 
                 try
                    { 
                        FileInputStream fis = new FileInputStream("EmpDB.dat"); // Creating an Input Stream 
                        //boolean cont = true;
                        while(true) 
                        {
                            try
                            {
                                ObjectInputStream input = new ObjectInputStream(fis);
                                emp = (Employee) input.readObject();
                                if (name.equals(emp.Employee_Name) || name.equals(emp.Employee_Name.toLowerCase())) // f != null &&
                                {
                                  records++;
                                }
                            }
                            catch(Exception e) { fis.close(); break; }
                        }
                    }
                    catch (Exception e){}
                    
                    JOptionPane.showMessageDialog(null,"Total Records: \"" + records + "\'");
                    if(records != 0)
                        emp.show_Records_via_Emp_Name(name); // Displaying if the record is founded
                break;

            // *******************************
            // Search by Age
            case "Age": case "age":
                //boolean input_age = false;
                int required_age = 0;
                // Check for Integer input
                re_enter:
                while(true)
                {
                    try
                    {
                        String temp_age = JOptionPane.showInputDialog("Enter Age:");
                        required_age = Integer.parseInt(temp_age);
                        break;
                    }
                    catch(Exception e)
                    {
                        JOptionPane.showMessageDialog(null,"ERROR: Faulty Input","ERROR",JOptionPane.ERROR_MESSAGE);
                        continue re_enter;
                    }
                }

                // Check for how many Record are there
                try
                {
                    FileInputStream fis = new FileInputStream("EmpDB.dat");   
                    //boolean cont = true;
                    while (true) 
                    {
                        try
                        {
                            ObjectInputStream input = new ObjectInputStream(fis);
                            emp = (Employee) input.readObject();
                            int age = emp.Age();
                            if (required_age == age) // f != null &&
                            {
                              records++;
                            }
                        }catch(Exception e) { fis.close(); break;}
                    }
                }
                catch(Exception e) {}

                JOptionPane.showMessageDialog(null,"Total Records: \"" + records + "\'");
                if(records != 0)
                    emp.show_Records_via_Emp_Age(required_age); // Displaying if the record is founded
                break;

            // *******************************
            // Search by Job
            case "Job": case "job":
                String job = JOptionPane.showInputDialog("Enter Job Category:");
                // Read all Records of ID given 
                 try
                    { 
                        FileInputStream fis = new FileInputStream("EmpDB.dat"); // Creating an Input Stream 
                        //boolean cont = true;
                        while (true) 
                        {
                            try
                            {
                                ObjectInputStream input = new ObjectInputStream(fis);
                                emp = (Employee) input.readObject();
                                if (job.equals(emp.Job_Category) || job.equals(emp.Job_Category.toLowerCase())) // f != null &&
                                {
                                  records++;
                                }
                            }catch(Exception e) { fis.close(); break;}
                        }
                    }
                    catch (Exception e){}
                    finally{JOptionPane.showMessageDialog(null,"Total Records: \"" + records + "\'");
                            if(records != 0)
                                emp.show_Records_via_Emp_Job(job);} // Displaying if the record is founded
                break;
        }   
    }

    // ********************   Reading all Records from File function
    public int read_All_Records()
    {
        int records = 0;
        Employee emp =null;
        try
        {
            FileInputStream fis = new FileInputStream("EmpDB.dat");   
            //boolean count = true;
            while (true) 
            {
                try
                {
                    ObjectInputStream input = new ObjectInputStream(fis);
                    emp = (Employee) input.readObject();
                    if (emp != null) // f != null &&
                    {
                      records++;
                    }
                }catch(Exception e) { fis.close(); break;}
            }
        }catch (Exception e) {}
        return records;
    }

    // ********************   Reading Records via Employee ID function
    public void show_Records_via_Emp_ID(final int required_id)
    {
        // Read Employee File
        Employee emp = null;
        //boolean count = true;
        try
        {
            FileInputStream emp_info = new FileInputStream("EmpDB.dat"); // Opening the file
            while(true) 
            {
                try
                {
                    ObjectInputStream read_info = new ObjectInputStream(emp_info); // Creating an Input Stream
                    emp = (Employee) read_info.readObject();
                    if (required_id == emp.Emp_ID) // f != null &&
                    {
                        emp.display_Employee_Information();
                    }
                }catch(Exception e) { emp_info.close(); break;}
            }
        }
        catch(Exception e){}
    }

    // ********************   Reading Records via Employee Name function
    public void show_Records_via_Emp_Name(final String required_name)
    {
        // Read Employee File
        Employee emp = null;
        //boolean count = true;
        try
        { 
            FileInputStream fis = new FileInputStream("EmpDB.dat"); // Creating an Input Stream 
            //boolean cont = true;
            while(true) 
            {
                try
                {
                    ObjectInputStream input = new ObjectInputStream(fis);
                    emp = (Employee) input.readObject();
                    if (required_name.equals(emp.Employee_Name) || required_name.equals(emp.Employee_Name.toLowerCase())) // Comparing the Name in File
                    {
                      emp.display_Employee_Information();
                    }
                }catch(Exception e) { fis.close(); break;}
            }
        }
        catch (Exception e){}
    }

    // ********************   Reading Records via Employee Age function
    public void show_Records_via_Emp_Age(final int required_age)
    {
        // Read Employee File
        Employee emp = null;
        //boolean count = true;
        try
        {
            FileInputStream emp_info = new FileInputStream("EmpDB.dat"); // Opening the file
            while(true) 
            {
                try
                {
                    ObjectInputStream read_info = new ObjectInputStream(emp_info); // Creating an Input Stream
                    emp = (Employee) read_info.readObject();
                    int age = emp.Age();
                    if (required_age == age) // f != null 
                    {
                        emp.display_Employee_Information();
                    }
                }catch(Exception e) { emp_info.close(); break;}  
            }
        }
        catch(Exception e){}
    }

    // ********************   Reading Records via Employee Job function
    public void show_Records_via_Emp_Job(final String required_Job)
    {
        // Read Employee File
        Employee emp = null;
        try
        { 
            FileInputStream fis = new FileInputStream("EmpDB.dat"); // Creating an Input Stream 
            //boolean cont = true;
            while(true) 
            {
                try
                {
                    ObjectInputStream input = new ObjectInputStream(fis);
                    emp = (Employee) input.readObject();
                    if (required_Job.equals(emp.Job_Category) || required_Job.equals(emp.Job_Category.toLowerCase())) // Comparing Job with Records in File
                    {
                      emp.display_Employee_Information();
                    }
                }catch(Exception e) { fis.close(); break;}   
            }
        }
        catch (Exception e){}
    }

    // ********************   Delete Record by Employee ID function
    public void delete_Record(final int required_id)
    {
        // Read Employee File
        Employee read=null;
        boolean found = false;
        String choice = "";

        try
        {
            FileInputStream main = new FileInputStream("EmpDB.dat"); 
            //boolean cont = true;
            continue_Writing:
            while(true) 
            { 
                try
                {
                    ObjectInputStream main_file = new ObjectInputStream(main); 
                    read = (Employee) main_file.readObject();
                    if (required_id == read.Emp_ID)
                    {
                        boolean input = false;
                        getChoice:
                        while(!input) 
                        {
                            choice = read.confirm_Record();
                            found = true;
                            switch(choice)
                            {
                                case "Y": case "y":
                                    input = true;
                                    continue continue_Writing;

                                case "N": case "n":
                                    throw new Exception();

                                default:
                                    JOptionPane.showMessageDialog(null,"ERROR: Invalid Option","ERROR",JOptionPane.ERROR_MESSAGE);
                                    continue getChoice;
                            }
                        }
                    } 
                    read.write_Record("temp.dat");
                }catch(Exception e) { main.close(); read.update_File(choice); break;} 
            }
        }
        catch (Exception e){}

        if(!found)
        {
            // File temp_file = new File("temp.dat"); 
            // temp_file.delete();
            JOptionPane.showMessageDialog(null,"No Record Found");
        }
    }

    // ********************   File Updation function
    public void update_File(final String option)
    {
        File main_file = new File("EmpDB.dat");
        File temp_file = new File("temp.dat"); 
        switch(option)
        {
         case "Y": case "y":
            main_file.delete();
            temp_file.renameTo(main_file);
            break; 

        case "N": case "n":
            temp_file.delete();
            break;
        }
    }

    // ********************   Update Education function
    public void update_Record_Information(final int required_id)
    {
        // Read Employee File
        Employee read=null;
        String choice = "";
        boolean found = false;

        try
        {
            FileInputStream main = new FileInputStream("EmpDB.dat"); 
            while(true) 
            { 
                try
                {
                    ObjectInputStream main_file = new ObjectInputStream(main);
                    read = (Employee) main_file.readObject();
                    if (required_id == read.Emp_ID)
                    {
                        getChoice:
                        while(true)
                        {
                            found = true;
                            choice = read.confirm_Record();
                            switch(choice)
                            {
                                case "Y": case "y":
                                    choose:
                                    while(true)
                                    {
                                        try
                                        {
                                            String option = JOptionPane.showInputDialog("Select from following"+
                                                                                        "\n 1. Job Category"+
                                                                                        "\n 2. Education"+
                                                                                        "\n 3. Pay Scale"+
                                                                                        "\n 00. Cancel");
                                            switch(option)
                                            {
                                            case "1":
                                                read.set_Job_Category();
                                                read.set_Pay_Scale();
                                                break;

                                            case "2":
                                                read.set_Education();
                                                break;

                                            case "3":
                                                read.set_Pay_Scale();
                                                break;

                                            case "00":
                                                JOptionPane.showMessageDialog(null,"Updation Request Reverted");
                                                choice = "N";
                                                break;

                                            default:
                                                JOptionPane.showMessageDialog(null,"Error: Invalid","ERROR",JOptionPane.ERROR_MESSAGE);
                                                continue choose;
                                            }
                                            break;
                                        }
                                        catch(Exception e) {break;}
                                    }
                                    break;

                                case "N": case "n":
                                    main.close();
                                    break;

                                default:
                                    JOptionPane.showMessageDialog(null,"ERROR: Invalid Option","ERROR",JOptionPane.ERROR_MESSAGE);
                                    continue getChoice;
                            }
                            break;
                        }
                    }
                read.write_Record("temp.dat");   
                }catch(Exception e) { main.close(); read.update_File(choice); break;} 
            }

        }
        catch (Exception e){}

        if(!found)
        {
            // File temp_file = new File("temp.dat"); 
            // temp_file.delete();
            JOptionPane.showMessageDialog(null,"No Record Found");
        }
     }
    // **********************************************************************************************************
}
