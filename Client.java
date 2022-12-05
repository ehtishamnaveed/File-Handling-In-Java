package ACP.Client;
import ACP.Employee.Employee;
import javax.swing.*;
import java.time.*;

//##########   Client   ###########################################
class Client
{
    public static void main(String[] ss) 
    {
        String intsearch; int search; 
        int records; 
        final int limit = 50;
        Employee obj[] = new Employee[1];
        obj[0] = new Employee();

        menu:
        while(true)
        {
            try
            {
                String choice = JOptionPane.showInputDialog(null," ******  MAIN MENU   ****** "+
                                                                 "\n 1. Add New Employee record"+
                                                                 "\n 2. Update Employee Information"+
                                                                 "\n 3. Delete Employee Record"+
                                                                 "\n 4. Search & view Employee"+
                                                                 "\n 0. Total Records"+
                                                                 "\n 00. View All Records"+
                                                                 "\n E. Exit");
                switch(choice)
                {
                // *********************
                // Add Record
                case "1":
                    records = obj[0].read_All_Records();
                    if(records >= limit)
                        JOptionPane.showMessageDialog(null,"Reached Maximun Records Limit \"50\"");
                    else
                    {
                        setInfo:
                        while(true)
                        {
                            String insertoption = obj[0].set_Employee_Information();
                            switch(insertoption)
                            {
                            case "Y": case "y":
                                obj[0].write_Record("EmpDB.dat");
                                break;

                            case "N": case "n":
                                continue setInfo;
                            }
                            break;
                        }
                    }
                    break;

                // *********************
                // Update Information
                case "2":
                    update:
                    while(true)
                    {
                        try
                        {
                            intsearch = JOptionPane.showInputDialog("Enter ID Number:");
                            search = Integer.parseInt(intsearch);
                            obj[0].update_Record_Information(search);
                            break;
                        }
                        catch(NullPointerException e){continue menu;}
                        catch(NumberFormatException e) 
                        {
                            JOptionPane.showMessageDialog(null,"Record not Found");
                            continue menu;
                        }
                    }
                    break;

                // *********************
                // Delete Record
                case "3":
                    delete:
                    while(true)
                    {
                        try
                        {
                            intsearch = JOptionPane.showInputDialog("Enter ID Number:");
                            search = Integer.parseInt(intsearch);
                            if(intsearch.equals(""))
                                throw new NumberFormatException();
                            obj[0].delete_Record(search);
                            break;
                        }
                        catch(NumberFormatException e) 
                        {
                            JOptionPane.showMessageDialog(null,"Record not Found");
                            continue menu;
                        }
                    }
                    break;

                // *********************
                // Search Record
                case "4":
                    search:
                    while(true)
                    {
                        try
                        {
                            String seachoption = JOptionPane.showInputDialog(null," ******  Search Menu   ****** "+
                                                                         "\n 1. BY Emp ID"+
                                                                         "\n 2. By Employee Name"+
                                                                         "\n 3. By Age"+
                                                                         "\n 4. By Job Catagory"+
                                                                           "\n 00. Back");

                            switch(seachoption)
                            {
                            // *********************
                            // Search by ID
                            case "1":
                                obj[0].read_Records_By("ID");
                                break;

                            // *********************
                            // Search by Name
                            case "2":
                                obj[0].read_Records_By("Name");
                                break;

                            // *********************
                            // Search by Age
                            case "3":
                                obj[0].read_Records_By("Age");
                                break;

                            // *********************
                            // Search by Job Category
                            case "4":
                                obj[0].read_Records_By("Job");
                                break;

                            // *********************
                            // Back to Main Menu
                            case "00":
                                continue menu;

                            default:
                                JOptionPane.showMessageDialog(null,"ERROR: Invalid","ERROR",JOptionPane.ERROR_MESSAGE);
                                continue search;
                            }
                            break;
                        }
                        catch(NullPointerException e) {continue menu;}
                    }   
                    break;

                // *********************
                // View Amount Total Records
                case "0":
                    records = obj[0].read_All_Records();
                    JOptionPane.showMessageDialog(null,"Total Records: \"" + records + "\'");
                    break;

                // *********************
                // View All Records
                case "00":
                    obj[0].show_All_Records();
                    break;

                // *********************
                // Exit
                case "E": case "e":
                    System.exit(0);
                    break;

                default:
                    JOptionPane.showMessageDialog(null,"ERROR: Invalid", "ERROR" ,JOptionPane.ERROR_MESSAGE);
                    continue menu;
                }
            }catch(NullPointerException e){System.exit(0);}
        }
    }
}
