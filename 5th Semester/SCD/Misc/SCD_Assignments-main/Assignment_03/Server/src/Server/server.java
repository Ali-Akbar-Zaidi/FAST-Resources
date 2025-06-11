package Server;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.concurrent.*;
import Controller.authentication.Customer;
import Controller.authentication.Employee;
import Controller.authentication.NADRA;
import Controller.billing.Billing;
import Controller.billing.TariffTax;
import Controller.schemas.Billing_Data;
import Controller.schemas.Customer_Data;
import Controller.schemas.NADRA_Data;
import Controller.schemas.Tariff_Data;

public class server {
    private static final int PORT = 1234;
    private static ExecutorService pool = Executors.newFixedThreadPool(10);

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(PORT);

        while (true) {
            System.out.println("Waiting for clients...");
            Socket client = serverSocket.accept();
            System.out.println("Connected to client");
            ClientHandler clientThread = new ClientHandler(client);
            pool.execute(clientThread);
        }
    }
}

class ClientHandler implements Runnable {
    private Socket clientSocket;
    private BufferedReader in;
    private PrintWriter out;

    public ClientHandler(Socket socket) throws IOException {
        this.clientSocket = socket;
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        out = new PrintWriter(clientSocket.getOutputStream(), true);
    }

    @Override
    public void run() {
        try (ObjectOutputStream objectOut = new ObjectOutputStream(clientSocket.getOutputStream());
                ObjectInputStream objectIn = new ObjectInputStream(clientSocket.getInputStream())) {

            String request = (String) objectIn.readObject();
            Object response = processRequest(request);
            objectOut.writeObject(response);

        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private synchronized Object processRequest(String request) {
        Object response = null;
        String parts[] = request.split(",");

        if (parts[0].equals("billing.get_bill") && parts.length == 2) {

            return new Billing().get_bill(parts[1]);
        } else if (parts[0].equals("billing.getMaxDate") && (parts.length == 1)) {

            return new Billing().getMaxDate();
        } else if (parts[0].equals("customer.customer_data")) {
            Customer cust = new Customer();
            return cust.customer_data;
        } else if (parts[0].equals("billing.add")) {

            for (int i = 1; i < parts.length; i++) {
                if (parts[i].equals("NULL")) {
                    parts[i] = "";
                }
            }

            for (int i = 1; i < parts.length; i++) {
                if (parts[i] == null) {
                    parts[i] = "";
                }
            }

            Billing_Data data = new Billing_Data(parts[1], parts[2], parts[3], parts[4], parts[5], parts[6], parts[7],
                    parts[8], parts[9], parts[10], parts[11], parts[12], parts[13]);
            Billing bill = new Billing();
            boolean flag = bill.add(data);
            bill.save_data();
            bill.load_data();
            return flag;
        } else if (parts[0].equals("billing.billing_data")) {
            Billing b = new Billing();
            return b.billing_data;
        } else if (parts[0].equals("customer.getMeterType")) {
            Customer c = new Customer();
            return c.getMeterType(parts[1]);
        } else if (parts[0].equals("billing.update_edit_billing_data")) {
            Billing_Data b_d = new Billing_Data(parts[1], parts[2], parts[3], parts[4], parts[5], parts[6], parts[7],
                    parts[8],
                    parts[9], parts[10], parts[11], parts[12], parts[13]);

            Billing billing = new Billing();
            boolean success = billing.update_edit_billing_data(b_d);
            billing.save_data();
            billing.load_data();
            return success;
        } else if (parts[0].equals("billing.delete_bill")) {
            Billing b = new Billing();
            return b.delete_bill(parts[1], parts[2], parts[3], parts[4]);
        } else if (parts[0].equals("nadra.exists")) {
            NADRA nadra = new NADRA();
            return nadra.exists(parts[1]);
        } else if (parts[0].equals("customer.count_meters")) {
            Customer cust = new Customer();
            return cust.count_meters(parts[1]);
        } else if (parts[0].equals("customer.replace_customer_data_by_unique_id")) {
            Customer_Data temp_data = new Customer_Data(parts[1], parts[2], parts[3], parts[4], parts[5], parts[6],
                    parts[7], parts[8], parts[9]);
            Customer customer = new Customer();
            boolean success = customer.replace_customer_data_by_unique_id(temp_data);
            customer.save_data();
            customer.load_data();
            return success;
        } else if (parts[0].equals("customer.delete_from_unique_id")) {
            Customer customer = new Customer();
            boolean success = customer.delete_from_unique_id(parts[1]);
            customer.save_data();
            customer.load_data();
            return success;
        } else if (parts[0].equals("nadra.nadra_data")) {
            NADRA nadra = new NADRA();
            return nadra.nadra_data;
        } else if (parts[0].equals("nadra.cnic_exits_except_index")) {
            NADRA nadra = new NADRA();
            return nadra.cnic_exits_except_index(parts[1], Integer.parseInt(parts[2]));
        } else if (parts[0].equals("nadra.nadra_data.set")) {
            NADRA nadra = new NADRA();
            NADRA_Data TEMP = new NADRA_Data(parts[2], parts[3], parts[4]);
            nadra.nadra_data.set(Integer.parseInt(parts[1]), TEMP);
            nadra.save_data();
            nadra.load_data();
        } else if (parts[0].equals("tarriftax.tarrif_data")) {
            TariffTax tax = new TariffTax();
            ArrayList<Tariff_Data> tarif_data = new ArrayList<>();
            Tariff_Data ad1 = new Tariff_Data();
            Tariff_Data ad2 = new Tariff_Data();
            Tariff_Data ad3 = new Tariff_Data();
            Tariff_Data ad4 = new Tariff_Data();
            tarif_data.add(ad1);
            tarif_data.add(ad2);
            tarif_data.add(ad3);
            tarif_data.add(ad4);

            Tariff_Data d1 = tax.domestic_1_phase;
            Tariff_Data c1 = tax.commercial_1_phase;
            Tariff_Data d3 = tax.domestic_3_phase;
            Tariff_Data c3 = tax.commercial_3_phase;

            tarif_data.add(0, d1);
            tarif_data.add(1, c1);
            tarif_data.add(2, d3);
            tarif_data.add(3, c3);

            System.out.println(d1.getString());
            System.out.println(c1.getString());
            System.out.println(d3.getString());
            System.out.println(c3.getString());

            return tarif_data;
        } else if (parts[0].equals("tarriftax.update_tarrif") && parts.length == 21) {
            // if any parts 1 onwards equals "NULL" replace with ""
            for (int i = 1; i < parts.length; i++) {
                if (parts[i].equals("NULL")) {
                    parts[i] = "";
                }
            }

            Tariff_Data d1 = new Tariff_Data(parts[1], parts[2], parts[3], parts[4], parts[5]);
            Tariff_Data c1 = new Tariff_Data(parts[6], parts[7], parts[8], parts[9], parts[10]);
            Tariff_Data d3 = new Tariff_Data(parts[11], parts[12], parts[13], parts[14], parts[15]);
            Tariff_Data c3 = new Tariff_Data(parts[16], parts[17], parts[18], parts[19], parts[20]);

            TariffTax tax = new TariffTax();
            tax.domestic_1_phase = d1;
            tax.commercial_1_phase = c1;
            tax.domestic_3_phase = d3;
            tax.commercial_3_phase = c3;

            tax.save_data();
            tax.load_data();

            return true;
        } else if (parts[0].equals("employee.authenticate")) {
            Employee employee = new Employee();
            return employee.authenticate(parts[1], parts[2]);
        } else if (parts[0].equals("employee.add_employee")) {
            Employee employee = new Employee();
            return employee.add_employee(parts[1], parts[2]);
        } else if (parts[0].equals("nadra.add")) {
            NADRA nadra = new NADRA();
            return nadra.add(parts[1], parts[2], parts[3]);
        } else if (parts[0].equals("cust.count_meters")) {
            Customer cust = new Customer();
            return cust.count_meters(parts[1]);
        } else if (parts[0].equals("customer.isUnique")) {
            Customer customer = new Customer();
            return customer.isUnique(parts[1]);
        } else if (parts[0].equals("cust.add_meter")) {
            Customer cust = new Customer();
            boolean flag = cust.add_meter(parts[1], parts[2], parts[3], parts[4], parts[5], parts[6], parts[7],
                    parts[8], parts[9]);
            cust.save_data();
            cust.load_data();
            return flag;
        } else if (parts[0].equals("billing.billing_data.set")) {
            // if any parts is "NULL" replace with ""
            for (int i = 1; i < parts.length; i++) {
                if (parts[i].equals("NULL")) {
                    parts[i] = "";
                }
            }

            Billing_Data data = new Billing_Data(parts[1], parts[2], parts[3], parts[4], parts[5], parts[6], parts[7],
                    parts[8], parts[9], parts[10], parts[11], parts[13], parts[12]);

            int row = Integer.parseInt(parts[14]);

            Billing billing = new Billing();
            billing.billing_data.set(row, data);
            billing.save_data();
            billing.load_data();
            return true;
        } else if (parts[0].equals("customer.add_to_units")) {
            Customer cust = new Customer();
            return cust.add_to_units(parts[1], parts[2], parts[3]);
        } else if (parts[0].equals("customer.getCustomer")) {
            Customer cust = new Customer();
            return cust.getCustomer(parts[1]);
        } else if (parts[0].equals("nadra.update")) {
            NADRA nadra = new NADRA();
            return nadra.update(parts[1], parts[2], parts[3]);
        } else if (parts[0].equals("customer.customer_data.set")) {
            Customer cust = new Customer();
            for (int i = 1; i < parts.length; i++) {
                if (parts[i].equals("NULL")) {
                    parts[i] = "";
                }
            }

            int row = Integer.parseInt(parts[1]);

            System.out.println("row: " + row);
            System.out.println("length: " + parts.length);
            for (int i = 0; i < parts.length; i++) {
                System.out.println(parts[i]);
            }

            Customer_Data data = new Customer_Data(parts[2], parts[3], parts[4], parts[5], parts[6], parts[7], parts[8],
                    parts[9], parts[10]);

            cust.customer_data.set(row, data);
            cust.save_data();
            cust.load_data();
            return true;
        } else if (parts[0].equals("tax.update_tarrif")) {
            TariffTax tax = new TariffTax();
            int part5 = Integer.parseInt(parts[5]);
            tax.update_tarrif(parts[1], parts[2], parts[3], parts[4], part5);
            return true;
        } else if (parts[0].equals("billings.get_bill")) {
            Billing b = new Billing();
            return b.get_bill(parts[1]);
        } else if (parts[0].equals("billing.get_reports_paid")) {
            Billing billing = new Billing();
            return billing.get_reports_paid();
        } else if (parts[0].equals("billing.get_reports_unpaid")) {
            Billing billing = new Billing();
            return billing.get_reports_unpaid();
        } else if (parts[0].equals("tariff.getTax")) {
            TariffTax tax = new TariffTax();
            return tax.getTax(parts[1], parts[2]);
        } else if (parts[0].equals("Billing.get_latest_date")) {
            Billing bill = new Billing();
            return bill.get_latest_date();
        }

        return response;
    }
}