
----------
Readme.txt
----------

The examples are arranged by chapter.

You can use most of the examples without any additional configuration. Just open
the application folder as a website in Visual Studio. Or, open the corresponding
solution (.sln) file. All the websites include solution files, except Chapter03
and Chapter05.

The following notes explain special considerations for some examples, such as
database setup instructions.



---------
Chapter 8
---------

This website includes examples that write event log entries. Writing to an event
log requires elevated privileges. In Windows Vista, you must explicitly run
Visual Studio as an administrator (right-click the Visual Studio icon and
choose Run As Administrator). Otherwise, the event log code will fail with a
security error.



----------
Chapter 12
----------

You can download the font  used in the GDI+ drawing example (it's optional) at
http://www.fonttrader.com/detailed~name~Alba_Super~font~648.htm.



------------------
Chapter 15, 16, 17
------------------

Most of the code in these websites requires SQL Server 7 or later. You must also
install the sample databases and configure the connection string. The following
sections explain how.


* Installing the Sample Databases
---------------------------------
These examples use two sample databases: Northwind and Pubs. If you are using
SQL Server 7 or SQL Server 2000, these sample databases may be installed already.
If you are using a later version of SQL Server, they won't be installed.

To install the sample databases, you can use the following SQL scripts:

- Use InstNwnd.sql to install the Northwind database.
- Use InstPubs.sql to install the Pubs database.

If you are using a full version of SQL Server, you can open these scripts and run
them using a graphical tool like SQL Server Management Studio.

If you are using SQL Server 2005 Express Edition, you'll need to use the sqlcmd.exe
command-line tool. If you are using SQL Server 2005 Express Edition, you need a
command like this to install the Northwind database:

  sqlcmd -S localhost\SQLEXPRESS -i InstNwnd.sql

If you are using a full version of SQL Server 2005 on the local computer, you need
a command like this:

  sqlcmd -i InstNwnd.sql


* Connection Strings
--------------------
You may need to modify the connection strings depending on how what version of
SQL Server you're using and where it's installed. The connection strings are in
the web.config file of each application, in the <connectionStrings> section.

By default, the connection strings assume you are using SQL Server 2005
Express Edition. Here's an example:

  <connectionStrings>   
    <add name="Pubs" connectionString=
"Data Source=localhost\SQLEXPRESS;Initial Catalog=Pubs;Integrated Security=SSPI"/>
  </connectionStrings>

If you are using a full version of SQL Server, you'll need to remove the instance
name and use a connection string like this:

  <connectionStrings>   
    <add name="Pubs" connectionString=
"Data Source=localhost;Initial Catalog=Pubs;Integrated Security=SSPI"/>
  </connectionStrings>

If you are using a SQL Server instance on another computer, you'll need to use
the server name instead of localhost. Read Chapter 15 for more information.

Finally, the ConnectionTester.aspx page in Chapter 15 includes a hard-coded
connection string. You need to modify the source code of that page to change it.



-----------------
Chapter 21 and 22
-----------------

These examples in these chapters use the automatic file-creation option in
SQL Server 2005 to create the membership and profiles databases. As long as
you have SQL Server 2005 installed, the required database will be generated
automatically in the App_Data subdirectory.

If you are using SQL Server 2005 Express Edition, no changes are needed.
If you are using the full edition of SQL Server, you need to manually
create a database for storing the membership and profile information using the
aspnet_regsql.exe command-line tool. You must then modify the connection string
to use this database. Chapter 21 explains this setup process.



----------
Chapter 23
----------

The two websites provided for this chapter use custom components.
The easiest way to use these examples is to open the solution (.sln) file, which
opens both the website and the class library project in one Visual Studio window. There are two solution files:

- ComponentTest.sln 		(the simple component)
- DatabaseComponentTest.sln     (the AdBoard database component)

Before using the DatabaseComponentTest website, you must create the AdBoard
database. You can use the InstAdboard.sql script with a graphical management tool
or the sqlcmd.exe command-line tool, as described in the "Chapter 15, 16, 17"
section. If you are using the full version of SQL Server (not SQL Server Express),
you also need to modify the connection string in the web.config file.



----------
Chapter 24
----------

The database dependency examples require the Northwind database. The section
"Chapter 15, 16, 17" describes how to install this database. If you are using
the full version of SQL Server (not SQL Server Express), you also need to modify
the connection string in the web.config file.



----------
Chapter 25
----------

The autocomplete text box example requires the Northwind database. The section
"Chapter 15, 16, 17" describes how to install this database. If you are using
the full version of SQL Server (not SQL Server Express), you also need to modify
the connection string in the web.config file.

