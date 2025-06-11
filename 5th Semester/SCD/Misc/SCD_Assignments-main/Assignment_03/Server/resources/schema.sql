CREATE TABLE Billing (
    cust_id VARCHAR(255),
    billing_month VARCHAR(2), -- MM format
    billing_year VARCHAR(4), -- YYYY format
    curr_meter_reading VARCHAR(255), -- Regular
    curr_meter_reading_peak VARCHAR(255), -- Peak
    reading_entry_date VARCHAR(10), -- Format: DD/MM/YYYY
    electricity_cost VARCHAR(255),
    sales_tax VARCHAR(255),
    fixed_charges VARCHAR(255),
    total_billing_amount VARCHAR(255),
    due_date VARCHAR(10), -- Format: DD/MM/YYYY
    bill_status VARCHAR(20), -- Paid or Unpaid
    bill_payment_date VARCHAR(10) -- Format: DD/MM/YYYY
);

CREATE TABLE Customer (
    Unique_ID VARCHAR(10), -- 4 digits
    CNIC VARCHAR(20), -- 13 digits
    Name VARCHAR(255),
    Address VARCHAR(255),
    Phone_No VARCHAR(255), -- 11 digits
    cust_type VARCHAR(255), -- Commercial / Domestic
    meter_type VARCHAR(255), -- Single Phase / Three Phase
    reg_units_consumed VARCHAR(255),
    peak_units_consumed VARCHAR(255) -- Blank for single phase
);

CREATE TABLE Employee (
    username VARCHAR(255),
    password VARCHAR(255)
);

CREATE TABLE NADRA (
    CNIC VARCHAR(13), -- 13 digits
    issue_date VARCHAR(10), -- Format: DD/MM/YYYY
    expiry_date VARCHAR(10) -- Format: DD/MM/YYYY
);

CREATE TABLE TariffTax (
    Meter_Type VARCHAR(50), -- Single Phase / Three Phase
    reg_unit_price VARCHAR(255),
    peak_unit_price VARCHAR(255), -- Not applicable in 1-phase customers
    percent_of_Tax VARCHAR(255),
    fixed_charges VARCHAR(255)
);

-- Insert into tarifftax
INSERT INTO TariffTax (Meter_Type, reg_unit_price, peak_unit_price, percent_of_Tax, fixed_charges) 
VALUES
('1 Phase Domestic', '5', '', '17', '150'),
('1 Phase Commercial', '15', '', '20', '250'),
('3 Phase Domestic', '8', '12', '17', '150'),
('3 Phase Commercial', '18', '25', '20', '250');

-- To Delete Tables
DROP TABLE IF EXISTS Billing;
DROP TABLE IF EXISTS Customer;
DROP TABLE IF EXISTS Employee;
DROP TABLE IF EXISTS NADRA;
DROP TABLE IF EXISTS TariffTax;
