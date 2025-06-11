import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.*;
import authentication.Customer;
import authentication.Employee;
import authentication.NADRA;
import billing.Billing;
import billing.TariffTax;
import schemas.Billing_Data;
import schemas.Customer_Data;
import schemas.NADRA_Data;
import schemas.Tariff_Data;

public class App {

    public String todaysDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate today = LocalDate.now();
        return today.format(formatter);
    }

    public String dateInSevenDays() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate today = LocalDate.now();
        LocalDate futureDate = today.plusDays(7);
        return futureDate.format(formatter);
    }

    public void clear() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (IOException | InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    public static int calculatePercentage(int percentage, int total) {
        return (int) ((percentage / 100.0) * total);
    }

    public static int checkCNICExpiry(String expiryDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate expirationDate = LocalDate.parse(expiryDate, formatter);
        LocalDate currentDate = LocalDate.now();

        if (currentDate.isAfter(expirationDate)) {
            return -1; // expired
        }

        int daysUntilExpiry = (int) ChronoUnit.DAYS.between(currentDate, expirationDate);

        if (daysUntilExpiry <= 30) {
            return 1; // close to expiry
        }

        return 0; // not close to expiry
    }

    public void Login_Menu() {
        clear();
        Scanner scn = null;
        String choice = "";
        System.out.println("Menu is as under:");
        System.out.println("1. Add meter (entry in Customer Info)");
        System.out.println("2. Update meter info (Customer Info file)");
        System.out.println("3. Create this month's bill");
        System.out.println("4. Pay Bill");
        System.out.println("5. Update Tax Info");
        System.out.println("6. View Bill");
        System.out.println("7. View Bill Report");
        System.out.println("8. View CNIC's (expired or close)");
        System.out.println("9. Logout");
        System.out.println("");

        try {
            System.out.println("Enter your choice: ");
            scn = new Scanner(System.in);
            choice = scn.nextLine();
            while (!choice.equals("1") && !choice.equals("2") && !choice.equals("3") && !choice.equals("4")
                    && !choice.equals("5") && !choice.equals("6") && !choice.equals("7") && !choice.equals("8")
                    && !choice.equals("9")) {
                System.out.println("Error! Please enter a valid choice: ");
                choice = scn.nextLine();
            }

            // scn.nextLine(); // remove the \n from buffer

            if (choice.equals("1")) {
                String CNIC;
                System.out.println("Enter CNIC (13 digits): ");
                CNIC = scn.nextLine();

                while (CNIC.length() != 13 || is_nums_only(CNIC) == false) {
                    System.out.println("Error! Enter valid CNIC (13 digits): ");
                    CNIC = scn.nextLine();
                }

                String issue_date = "";
                String expiry_date = "";

                NADRA nadra = new NADRA();

                if (nadra.exists(CNIC) == false) {

                    System.out.println("Enter issue date (DD/MM/YYYY): ");
                    issue_date = scn.nextLine();
                    while (issue_date.length() != 10 || isDate(issue_date) == false || isBeforeToday(issue_date)) {
                        System.out.println("Error! Enter valid issue date (DD/MM/YYYY): ");
                        issue_date = scn.nextLine();
                    }

                    System.out.println("Enter expiry date (DD/MM/YYYY): ");
                    expiry_date = scn.nextLine();
                    while (expiry_date.length() != 10 || isDate(expiry_date) == false || !isBeforeToday(expiry_date)) {
                        System.out.println("Error! Enter valid expiry date (DD/MM/YYYY): ");
                        expiry_date = scn.nextLine();
                    }
                }

                System.out.println("Enter your name: ");
                String name = scn.nextLine();
                while (name.length() < 7) {
                    System.out.println("Error: Enter name again: ");
                    name = scn.nextLine();
                }

                System.out.println("Enter your Address: ");
                String address = scn.nextLine();
                while (address.length() < 7 || address.contains(",")) {
                    System.out.println("Error: Enter address again: ");
                    address = scn.nextLine();
                }

                System.out.println("Enter your Phone no (11 digits): ");
                String phone_no = scn.nextLine();
                while (phone_no.length() != 11) {
                    System.out.println("Error: Enter phone-no again (11 digits): ");
                    phone_no = scn.nextLine();
                }

                System.out.println("Enter your Customer Type (Commercial/Domestic): ");
                String cust_type = scn.nextLine();
                while (cust_type.equals("Commercial") == false && cust_type.equals("Domestic") == false) {
                    System.out.println("Error: Enter Customer Type (Commercial/Domestic) again: ");
                    cust_type = scn.nextLine();
                }

                System.out.println("Enter Meter Type, Single Phase or Three Phase? (s/t): ");
                String meter_type = scn.nextLine();
                while (meter_type.equals("s") == false && meter_type.equals("t") == false) {
                    System.out.println("Error! Enter Meter Type, Single Phase or Three Phase? (s/t): ");
                    meter_type = scn.nextLine();
                }

                if (meter_type.equals("s")) {
                    meter_type = "Single Phase";
                } else if (meter_type.equals("t")) {
                    meter_type = "Three Phase";
                }

                String reg_units_consumed = "0";

                String peak_units_consumed = "";

                if (meter_type.equals("Single Phase") == false) {
                    peak_units_consumed = "0";
                }

                if (!nadra.exists(CNIC)) {
                    nadra.add(CNIC, issue_date, expiry_date); // saves and load after adding
                }

                Customer cust = new Customer();

                int count = cust.count_meters(CNIC);

                String unique_id = get_Rand();
                while (cust.isUnique(unique_id) == false) {
                    unique_id = get_Rand();
                }

                while (unique_id.length() < 4) {
                    unique_id = "0" + unique_id;
                }

                if (count < 3) // add the new one
                {
                    boolean bool = cust.add_meter(unique_id, CNIC, name, address, phone_no, cust_type, meter_type,
                            peak_units_consumed,
                            reg_units_consumed);
                    cust.save_data();
                    cust.load_data();

                    if (bool == true) {
                        System.out.println("Success! Added a new meter");
                    }
                }
            } else if (choice.equals("2")) {
                Customer customer = new Customer();
                if (customer.customer_size() < 1) {
                    System.out.println("Error: No data available for update!");
                } else {
                    System.out.println("");
                    customer.print_data();
                    System.out.println("Enter index to update: ");
                    String index = scn.nextLine();
                    while (Integer.valueOf(index) < 1 || Integer.valueOf(index) > customer.customer_size()
                            || !is_nums_only(index)) {
                        System.out.println("Error: Please enter valid index: ");
                        index = scn.nextLine();
                    }

                    System.out.println("Enter your name: ");
                    String name = scn.nextLine();
                    while (name.length() < 7) {
                        System.out.println("Error: Enter name again: ");
                        name = scn.nextLine();
                    }

                    System.out.println("Enter your Address: ");
                    String address = scn.nextLine();
                    while (address.length() < 7 || address.contains(",")) {
                        System.out.println("Error: Enter address again: ");
                        address = scn.nextLine();
                    }

                    System.out.println("Enter your Phone no (11 digits): ");
                    String phone_no = scn.nextLine();
                    while (phone_no.length() != 11) {
                        System.out.println("Error: Enter phone-no again (11 digits): ");
                        phone_no = scn.nextLine();
                    }

                    System.out.println("Enter your Customer Type (Commercial/Domestic): ");
                    String cust_type = scn.nextLine();
                    while (cust_type.equals("Commercial") == false && cust_type.equals("Domestic") == false) {
                        System.out.println("Error: Enter Customer Type (Commercial/Domestic) again: ");
                        cust_type = scn.nextLine();
                    }

                    System.out.println("Enter Meter Type, Single Phase or Three Phase? (s/t): ");
                    String meter_type = scn.nextLine();
                    while (meter_type.equals("s") == false && meter_type.equals("t") == false) {
                        System.out.println("Error! Enter Meter Type, Single Phase or Three Phase? (s/t): ");
                        meter_type = scn.nextLine();
                    }

                    if (meter_type.equals("s")) {
                        meter_type = "Single Phase";
                    } else if (meter_type.equals("t")) {
                        meter_type = "Three Phase";
                    }

                    String reg_units_consumed = "0";

                    String peak_units_consumed = "";

                    if (meter_type.equals("Single Phase") == false) {
                        peak_units_consumed = "0";
                    }
                    int indexVal = Integer.valueOf(index);
                    String unique_id = customer.customer_data.get(indexVal - 1).Unique_ID;
                    Customer_Data dt = customer.customer_data.get(indexVal - 1);
                    Customer_Data data = new Customer_Data(unique_id, dt.CNIC, name, address, phone_no, cust_type,
                            meter_type, peak_units_consumed, reg_units_consumed);

                    customer.customer_data.set(indexVal - 1, data);
                    customer.save_data();
                    customer.load_data();

                    System.out.println("Success! Updated Customer Info");
                }
            } else if (choice.equals("3")) {
                Billing billing = new Billing();
                String max_date = billing.getMaxDate();
                String todays_date = todaysDate();
                String today[] = todays_date.split("/"); // return 3 indexes DD/MM/YYYY
                String max[] = null;
                if (max_date != null) {
                    max = max_date.split("/"); // return 3 indexes DD/MM/YYYY
                }

                if (max_date != null) {
                    // if YYYY == YYYY and MM == MM
                    if (Integer.parseInt(max[1]) == Integer.parseInt(today[1])
                            && Integer.parseInt(max[2]) == Integer.parseInt(today[2])) {
                        System.out.println("Error: This month bills have already been generated");
                    } else {
                        Customer customer = new Customer();
                        int count = 0;
                        for (Customer_Data data : customer.customer_data) {
                            count++;
                            String cust_id = data.Unique_ID;
                            String billing_month = today[1];
                            String billing_year = today[2];
                            String curr_meter_reading = get_Rand();
                            String curr_meter_peak = "";
                            if (data.meter_type.equals("Three Phase") == true) {
                                curr_meter_peak = get_Rand();
                            }
                            String reading_entry_date = todays_date; // DD/MM/YYYY
                            String electricity_cost = "";
                            TariffTax tax = new TariffTax();
                            int regular_unit_price = -1;
                            int peak_unit_price = -1;
                            int percent_of_tax = -1;
                            int fixed_charges = -1;

                            if (data.meter_type.equals("Single Phase") == true) {
                                if (data.cust_type.equals("Domestic")) {
                                    regular_unit_price = Integer.parseInt(tax.domestic_1_phase.reg_unit_price);
                                    percent_of_tax = Integer.parseInt(tax.domestic_1_phase.percent_of_Tax);
                                    fixed_charges = Integer.parseInt(tax.domestic_1_phase.fixed_charges);
                                    peak_unit_price = -1;
                                } else if (data.cust_type.equals("Commercial")) {
                                    regular_unit_price = Integer.parseInt(tax.commercial_1_phase.reg_unit_price);
                                    percent_of_tax = Integer.parseInt(tax.commercial_1_phase.percent_of_Tax);
                                    fixed_charges = Integer.parseInt(tax.commercial_1_phase.fixed_charges);
                                    peak_unit_price = -1;
                                }
                            } else if (data.meter_type.equals("Three Phase") == true) {
                                if (data.cust_type.equals("Domestic")) {
                                    regular_unit_price = Integer.parseInt(tax.domestic_3_phase.reg_unit_price);
                                    peak_unit_price = Integer.parseInt(tax.domestic_3_phase.peak_unit_price);
                                    percent_of_tax = Integer.parseInt(tax.domestic_3_phase.percent_of_Tax);
                                    fixed_charges = Integer.parseInt(tax.domestic_3_phase.fixed_charges);
                                } else if (data.cust_type.equals("Commercial")) {
                                    regular_unit_price = Integer.parseInt(tax.commercial_3_phase.reg_unit_price);
                                    peak_unit_price = Integer.parseInt(tax.commercial_3_phase.peak_unit_price);
                                    percent_of_tax = Integer.parseInt(tax.commercial_3_phase.percent_of_Tax);
                                    fixed_charges = Integer.parseInt(tax.commercial_3_phase.fixed_charges);
                                }
                            }
                            int cost;
                            cost = (Integer.parseInt(curr_meter_reading) * regular_unit_price);

                            if (data.meter_type.equals("Three Phase") == true) {
                                cost = cost + (Integer.parseInt(curr_meter_peak) * peak_unit_price);
                            }
                            electricity_cost = String.valueOf(cost);
                            String sales_tax = String.valueOf(percent_of_tax);
                            String fix_charges = String.valueOf(fixed_charges);
                            cost = cost + calculatePercentage(percent_of_tax, cost);
                            cost = cost + fixed_charges;
                            String billing_amount = String.valueOf(cost);
                            String due_date = dateInSevenDays();
                            String bill_status = "Unpaid";
                            String bill_payment_date = ""; // fill in when paying

                            Billing_Data bill_data = new Billing_Data(cust_id, billing_month, billing_year,
                                    curr_meter_reading, curr_meter_peak, reading_entry_date, electricity_cost,
                                    sales_tax,
                                    fix_charges, billing_amount, due_date, bill_status, bill_payment_date);

                            Billing bill = new Billing();
                            bill.add(bill_data);
                            bill.save_data();
                            bill.load_data();
                        }
                        if (count > 0) {
                            System.out.println("Success: Bills added successfully");
                        } else {
                            System.out.println("No customers found");
                        }
                    }
                } else {
                    Customer customer = new Customer();
                    int count = 0;
                    for (Customer_Data data : customer.customer_data) {
                        count++;
                        String cust_id = data.Unique_ID;
                        String billing_month = today[1];
                        String billing_year = today[2];
                        String curr_meter_reading = get_Rand();
                        String curr_meter_peak = "";
                        if (data.meter_type.equals("Three Phase") == true) {
                            curr_meter_peak = get_Rand();
                        }
                        String reading_entry_date = todays_date; // DD/MM/YYYY
                        String electricity_cost = "";
                        TariffTax tax = new TariffTax();
                        int regular_unit_price = -1;
                        int peak_unit_price = -1;
                        int percent_of_tax = -1;
                        int fixed_charges = -1;

                        if (data.meter_type.equals("Single Phase") == true) {
                            if (data.cust_type.equals("Domestic")) {
                                regular_unit_price = Integer.parseInt(tax.domestic_1_phase.reg_unit_price);
                                percent_of_tax = Integer.parseInt(tax.domestic_1_phase.percent_of_Tax);
                                fixed_charges = Integer.parseInt(tax.domestic_1_phase.fixed_charges);
                                peak_unit_price = -1;
                            } else if (data.cust_type.equals("Commercial")) {
                                regular_unit_price = Integer.parseInt(tax.commercial_1_phase.reg_unit_price);
                                percent_of_tax = Integer.parseInt(tax.commercial_1_phase.percent_of_Tax);
                                fixed_charges = Integer.parseInt(tax.commercial_1_phase.fixed_charges);
                                peak_unit_price = -1;
                            }
                        } else if (data.meter_type.equals("Three Phase") == true) {
                            if (data.cust_type.equals("Domestic")) {
                                regular_unit_price = Integer.parseInt(tax.domestic_3_phase.reg_unit_price);
                                peak_unit_price = Integer.parseInt(tax.domestic_3_phase.peak_unit_price);
                                percent_of_tax = Integer.parseInt(tax.domestic_3_phase.percent_of_Tax);
                                fixed_charges = Integer.parseInt(tax.domestic_3_phase.fixed_charges);
                            } else if (data.cust_type.equals("Commercial")) {
                                regular_unit_price = Integer.parseInt(tax.commercial_3_phase.reg_unit_price);
                                peak_unit_price = Integer.parseInt(tax.commercial_3_phase.peak_unit_price);
                                percent_of_tax = Integer.parseInt(tax.commercial_3_phase.percent_of_Tax);
                                fixed_charges = Integer.parseInt(tax.commercial_3_phase.fixed_charges);
                            }
                        }
                        int cost;
                        cost = (Integer.parseInt(curr_meter_reading) * regular_unit_price);

                        if (data.meter_type.equals("Three Phase") == true) {
                            cost = cost + (Integer.parseInt(curr_meter_peak) * peak_unit_price);
                        }
                        electricity_cost = String.valueOf(cost);
                        String sales_tax = String.valueOf(percent_of_tax);
                        String fix_charges = String.valueOf(fixed_charges);
                        cost = cost + calculatePercentage(percent_of_tax, cost);
                        cost = cost + fixed_charges;
                        String billing_amount = String.valueOf(cost);
                        String due_date = dateInSevenDays();
                        String bill_status = "Unpaid";
                        String bill_payment_date = ""; // fill in when paying

                        Billing_Data bill_data = new Billing_Data(cust_id, billing_month, billing_year,
                                curr_meter_reading, curr_meter_peak, reading_entry_date, electricity_cost, sales_tax,
                                fix_charges, billing_amount, due_date, bill_status, bill_payment_date);

                        Billing bill = new Billing();
                        bill.add(bill_data);
                        bill.save_data();
                        bill.load_data();

                    }
                    if (count > 0) {
                        System.out.println("Success: Bills added successfully");
                    } else {
                        System.out.println("No customers found");
                    }
                }
            } else if (choice.equals("4")) {
                Billing billing = new Billing();
                if (billing.billing_data.size() > 0) {
                    billing.print();
                    String index;
                    System.out.println("Enter index of bill to pay: ");
                    index = scn.nextLine();
                    while (Integer.valueOf(index) < 1 || Integer.valueOf(index) > billing.billing_data.size()
                            || !is_nums_only(index)) {
                        System.out.println("Error: Please enter valid index: ");
                        index = scn.nextLine();
                    }
                    int i = Integer.parseInt(index);
                    Billing_Data data = billing.billing_data.get(i - 1);
                    if (data.bill_status.equals("Unpaid")) {
                        data.bill_status = "Paid";
                        data.bill_payment_date = todaysDate();
                        billing.billing_data.set(i - 1, data);
                        billing.save_data();
                        billing.load_data();

                        // add to the total units of customer
                        Customer cust = new Customer();
                        boolean bool = cust.add_to_units(data.cust_id, data.curr_meter_reading,
                                data.curr_meter_reading_peak);
                        cust.save_data();
                        cust.load_data();

                        if (bool == true) {
                            System.out.println("Bill payment made successfully");
                        } else {
                            System.out.println("Error: Bill not paid");
                        }

                    } else {
                        System.out.println("Error: Bill for this index has already been paid");
                    }
                } else {
                    System.out.println("Error: No bills found");
                }
            } else if (choice.equals("5")) {
                System.out.println("");
                System.out.println("1. Domestic 1 Phase");
                System.out.println("2. Commercial 1 Phase");
                System.out.println("3. Domestic 3 Phase");
                System.out.println("4. Commercial 3 Phase");
                System.out.println("");
                System.out.println("Please Enter a choice: ");
                int value = scn.nextInt();
                while (value < 1 && value > 4) {
                    System.out.println("Error! Enter a valid value: ");
                    value = scn.nextInt();
                }

                int reg;
                int peak = -1;
                int percent;
                int fixed_charges;

                String reg_;
                String peak_ = "";
                String percent_;
                String fixed_charges_;

                System.out.println("Enter Regular Unit Price: ");
                reg = scn.nextInt();
                while (reg < 1) {
                    System.out.println("Error! Enter valid value: ");
                    reg = scn.nextInt();
                }
                reg_ = String.valueOf(reg);

                if (value == 3 || value == 4) {
                    System.out.println("Enter Peak Unit Price: ");
                    peak = scn.nextInt();
                    while (peak < 1) {
                        System.out.println("Error! Enter valid value: ");
                        peak = scn.nextInt();
                    }
                    peak_ = String.valueOf(peak);
                }

                System.out.println("Enter Tax Percentage: ");
                percent = scn.nextInt();
                while (percent < 1 || percent > 100) {
                    System.out.println("Error! Enter valid value: ");
                    percent = scn.nextInt();
                }
                percent_ = String.valueOf(percent);

                System.out.println("Enter Fixed Charges: ");
                fixed_charges = scn.nextInt();
                while (fixed_charges < 1) {
                    System.out.println("Error! Enter valid value: ");
                    fixed_charges = scn.nextInt();
                }
                fixed_charges_ = String.valueOf(fixed_charges);

                TariffTax tax = new TariffTax();
                tax.update_tarrif(reg_, peak_, percent_, fixed_charges_, value);

                scn.nextLine(); // clear buffer

                System.out.println("Tariff Updated Successfully");
            } else if (choice.equals("6")) {
                String cust_id;
                System.out.println("Enter (4 digit) customer id: ");
                cust_id = scn.nextLine();

                while (cust_id.length() != 4 || is_nums_only(cust_id) == false) {
                    System.out.println("Error! Enter valid value: ");
                    cust_id = scn.nextLine();
                }

                Billing billing = new Billing();
                billing.view_bill(cust_id);
            } else if (choice.equals("7")) {
                Billing billing = new Billing();
                billing.view_reports();
            } else if (choice.equals("8")) {
                NADRA nadra = new NADRA();
                if (nadra.nadra_data.size() > 0) {
                    int index = 1;
                    for (NADRA_Data data : nadra.nadra_data) {
                        int res = checkCNICExpiry(data.expiry_date);
                        String status = "";
                        boolean flag = false;
                        if (res == -1) {
                            status = "Expired";
                            flag = true;
                        } else if (res == 1) {
                            status = "Close to Expiry";
                            flag = true;
                        }

                        if (flag == true) {
                            Customer customer = new Customer();
                            Customer_Data temp = customer.getCustomer(data.CNIC);
                            if (temp != null) {
                                String line = String.join(", ", temp.Name, data.CNIC, temp.Unique_ID, data.expiry_date);
                                line = String.valueOf(index) + ". " + line + "[" + status + "]";
                                System.out.println(line);
                                index++;
                            } else {
                                System.out.println("Error: Failed to fetch Customer Data");
                            }
                        }
                    }

                    if (index == 1) {
                        System.out.println("Error: No customers found that are close to expiry");
                    }
                } else {
                    System.out.println("No CNIC data found in storage");
                }
            } else if (choice.equals("9")) {
                System.out.println("Logging out...");
                return;
            }

        } catch (

        Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            System.out.println("Press any key to continue");
            scn.nextLine();
            if (scn != null) {
                // scn.close();
            }
        }

        Login_Menu();

    }

    public String get_Rand() {
        Random random = new Random();

        // from 0 to 9999
        int randomNumber = random.nextInt(10000);
        String randomString = Integer.toString(randomNumber);

        return randomString;
    }

    public boolean is_nums_only(String s) {
        if (s.matches("[0-9]+")) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isBeforeToday(String date) // DD/MM/YYYY
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        try {
            LocalDate inputDate = LocalDate.parse(date, formatter);
            LocalDate today = LocalDate.now();

            return !inputDate.isBefore(today);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format: " + e.getMessage());
            return false;
        }
    }

    public boolean isDate(String date) { // if it is a valid date or not
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);

        try {
            dateFormat.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    public void Auth_Menu() {
        Scanner scn = null;
        String choice = "";
        System.out.println("The Menu is as under:");
        System.out.println("1. Signup [Employee]");
        System.out.println("2. Login [Employee]");
        System.out.println("3. Update NADRA Info [Customer]");
        System.out.println("4. View bill info [Customer]");
        System.out.println("");
        System.out.println("Please enter your choice:");
        boolean auth_flag = false;

        try {
            scn = new Scanner(System.in);
            choice = scn.nextLine();

            while (choice.equals("1") == false && choice.equals("2") == false && choice.equals("3") == false
                    && choice.equals("4") == false) {
                System.out.println("Invalid value. Please enter your choice:");
                choice = scn.nextLine();
            }

            if (choice.equals("1")) {
                System.out.println("Enter Username (min 7 chars): ");
                String username = scn.nextLine();
                while (username.equals("") || username.length() < 7) {
                    System.out.println("Error! Please enter valid value (min 7 chars):");
                    username = scn.nextLine();
                }

                System.out.println("Enter Password (min 7 chars): ");
                String password = scn.nextLine();
                while (password.equals("") || password.length() < 7) {
                    System.out.println("Error! Please enter valid value (min 7 chars):");
                    password = scn.nextLine();
                }

                Employee employee = new Employee();
                if (employee.add_employee(username, password) == false) {
                    System.out.println("Error adding employee in the database!");
                } else {
                    System.out.println("Success!");
                }

            } else if (choice.equals("2")) // equals 2
            {
                System.out.println("Enter Username (min 7 chars): ");
                String username = scn.nextLine();
                while (username.equals("") || username.length() < 7) {
                    System.out.println("Error! Please enter valid value (min 7 chars):");
                    username = scn.nextLine();
                }

                System.out.println("Enter Password (min 7 chars): ");
                String password = scn.nextLine();
                while (password.equals("") || password.length() < 7) {
                    System.out.println("Error! Please enter valid value (min 7 chars):");
                    password = scn.nextLine();
                }

                Employee employee = new Employee();
                auth_flag = employee.authenticate(username, password);

                if (auth_flag == true) {
                    System.out.println("Success: Logged in");
                }

            } else if (choice.equals("3")) // equals 3
            {
                System.out.println("Enter you CNIC (13 digits): ");
                String CNIC;
                CNIC = scn.nextLine();

                while (CNIC.length() != 13 || is_nums_only(CNIC) == false) {
                    System.out.println("Invalid CNIC! Enter CNIC (13 digits): ");
                    CNIC = scn.nextLine();
                }

                NADRA nadra = new NADRA();
                if (nadra.exists(CNIC) == false) {
                    System.out.println("This CNIC doesn't exist.");
                } else {
                    System.out.println("Enter CNIC issue date (DD/MM/YYYY): ");
                    String issue = scn.nextLine();

                    while (isDate(issue) != true || issue.length() != 10 || isBeforeToday(issue)) {
                        System.out.println(" Error: Please re-enter date!: ");
                        issue = scn.nextLine();
                    }

                    System.out.println("Enter CNIC expiry date (DD/MM/YYYY): ");
                    String expiry = scn.nextLine();

                    while (isDate(expiry) != true || expiry.length() != 10 || !isBeforeToday(expiry)) {
                        System.out.println(" Error: Please re-enter date!: ");
                        expiry = scn.nextLine();
                    }

                    if (nadra.update(CNIC, issue, expiry) == true) {
                        System.out.println("Success: CNIC info updated!");
                    } else {
                        System.out.println("Error: Couldn't change CNIC info");
                    }
                }
            } else if (choice.equals("4")) {
                String cust_id = "";
                String CNIC = "";
                String meter_type = "";
                String curr_reading_reg = "";
                String curr_reading_peak = "";

                System.out.println("1. Check Bill against id");
                System.out.println("2. Check Bill against id & meter type (Advanced)");
                System.out.println("3. Check Bill estimate");
                System.out.println("Enter value: ");
                String ch = scn.nextLine();
                while (!ch.equals("1") && !ch.equals("2") && !ch.equals("3")) {
                    System.out.println("Enter valid value: ");
                    ch = scn.nextLine();
                }

                if (ch.equals("3")) {
                    String m_type = "";
                    String c_type = "";
                    String p_units = "";
                    String r_units = "";

                    System.out.println("Enter your meter type (Single Phase/3 Phase):");
                    m_type = scn.nextLine();

                    while (m_type.equals("Single Phase") == false && m_type.equals("3 Phase") == false) {
                        System.out.println("Error! Enter a valid meter type: ");
                        m_type = scn.nextLine();
                    }

                    System.out.println("Enter customer type (Domestic/Commercial): ");
                    c_type = scn.nextLine();

                    while (c_type.equals("Domestic") == false && c_type.equals("Commercial") == false) {
                        System.out.println("Error! Enter customer type (Domestic/Commercial): ");
                        c_type = scn.nextLine();
                    }

                    System.out.println("Enter Peak Units Consumed: ");
                    p_units = scn.nextLine();

                    while (is_nums_only(p_units) == false || Integer.parseInt(p_units) < 0) {
                        System.out.println("Error! Enter valid value: ");
                        p_units = scn.nextLine();
                    }

                    System.out.println("Enter regular units Consumed: ");
                    r_units = scn.nextLine();

                    while (is_nums_only(r_units) == false || Integer.parseInt(p_units) < 0) {
                        System.out.println("Error! Enter valid value: ");
                        r_units = scn.nextLine();
                    }

                    if (m_type.equals("Single Phase")) {
                        m_type = "1 Phase";
                    }

                    TariffTax tx = new TariffTax();
                    Tariff_Data td = tx.getTax(m_type, c_type);

                    int peak_price = 0;

                    if (m_type.equals("3 Phase")) {
                        peak_price = Integer.parseInt(td.peak_unit_price);
                    }

                    int total_electric = (Integer.parseInt(r_units) * Integer.parseInt(td.reg_unit_price))
                            + (Integer.parseInt(p_units) * peak_price);

                    int tax_percentage = Integer.parseInt(td.percent_of_Tax);

                    int taxed = total_electric
                            + calculatePercentage(tax_percentage, total_electric);

                    int tax = calculatePercentage(tax_percentage, total_electric);

                    int final_price = taxed + Integer.parseInt(td.fixed_charges);

                    System.out.println("");
                    System.out.println("Bill is as under:");
                    System.out.println("Meter: " + m_type);
                    System.out.println("Customer: " + c_type);
                    System.out.println("Electricity Cost: " + total_electric);
                    System.out.println("Tax (Percent): " + td.percent_of_Tax + "%");
                    System.out.println("Tax: " + tax);
                    System.out.println("Fixed Amount: " + td.fixed_charges);
                    System.out.println("Final Billing Amount: " + final_price);
                    System.out.println("");

                } else {

                    System.out.println("Enter your customer id (4 digits): ");
                    cust_id = scn.nextLine();

                    while (cust_id.length() != 4 || is_nums_only(cust_id) == false) {
                        System.out.println("Error: Please enter valid id: ");
                        scn.nextLine();
                    }

                    System.out.println("Enter CNIC (13 digits): ");
                    CNIC = scn.nextLine();

                    while (CNIC.length() != 13 || is_nums_only(CNIC) == false) {
                        CNIC = scn.nextLine();
                    }

                    if (ch.equals("2")) {
                        System.out.println("Enter your meter type (Single Phase or 3 Phase): ");
                        meter_type = scn.nextLine();
                        while (meter_type.equals("Single Phase") == false && meter_type.equals("3 Phase") == false) {
                            System.out.println("Error: Enter valid type: ");
                            meter_type = scn.nextLine();
                        }

                        System.out.println("Enter current reading (regular): ");
                        curr_reading_reg = scn.nextLine();

                        while (is_nums_only(curr_reading_reg) == false) {
                            System.out.println("Error: Enter valid reading (regular): ");
                            curr_reading_reg = scn.nextLine();
                        }

                        if (meter_type.equals("3 Phase") == true) {
                            System.out.println("Enter current reading (peak): ");
                            curr_reading_peak = scn.nextLine();

                            while (is_nums_only(curr_reading_peak) == false) {
                                System.out.println("Error: Enter valid reading: ");
                                curr_reading_peak = scn.nextLine();
                            }
                        }

                        Billing bill = new Billing();
                        bill.view_bill(cust_id, CNIC, meter_type, curr_reading_reg, curr_reading_peak);
                    } else {
                        Billing bill = new Billing();
                        bill.view_bill(cust_id, CNIC);
                    }
                }
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            System.out.println("Enter any key to continue!");
            scn.nextLine();

            if (scn != null) {
                // scn.close();
            }
        }

        if (choice.equals("1")) {
            clear();
            Auth_Menu();
        } else if (choice.equals("2")) {
            if (auth_flag == false) {
                clear();
                Auth_Menu();
            } else {
                Login_Menu();
                clear();
                Auth_Menu();
            }
        } else {
            clear();
            Auth_Menu();
        }
    }

    public static void main(String[] args) throws Exception {
        App app = new App();
        app.Auth_Menu();
    }
}
