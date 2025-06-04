--Q1--

create procedure printsitesperorganization
as
begin
    set nocount on;

    declare @org_id int;
    declare @org_name nvarchar(100);
    declare @site_count int;
    declare @row int = 1;
    declare @total_rows int;

    declare @orgs table (
        row_num int identity(1,1),
        organization_id int,
        organization_name nvarchar(100)
    );

    insert into @orgs (organization_id, organization_name)
    select organization_id, organization_name
    from organization;

    select @total_rows = count(*) from @orgs;

    while @row <= @total_rows
    begin
        select @org_id = organization_id, @org_name = organization_name
        from @orgs
        where row_num = @row;

        select @site_count = count(*)
        from site
        where organization_id = @org_id;

        print 'organization: ' + @org_name + ' (id: ' + cast(@org_id as varchar) + ') has ' 
              + cast(@site_count as varchar) + ' site(s).';

        set @row = @row + 1;
    end
end;

--Q2--

create procedure defaultemissionfactorsetter @source_id int
as
begin
    declare @defaultemissionfactor decimal(8,4) = (select default_emission_factor from emission_source 
                                                   where source_id = @source_id)
    update emission_record
    set emission_factor = @defaultemissionfactor
    where source_id = @source_id 
end

--Q3--

create procedure totalemissionpersite @site_id int
as
begin
    set nocount on;

    declare @startdate date = dateadd(year, -1, cast(getdate() as date));
    declare @enddate date = cast(getdate() as date);
    declare @currentmonth date = datefromparts(year(@startdate), month(@startdate), 1);
    declare @nextmonth date;
    declare @monthlytotal float;

    while (@currentmonth < @enddate)
    begin
        set @nextmonth = dateadd(month, 1, @currentmonth);

        select @monthlytotal = sum(calculated_emission)
        from emission_record
        where site_id = @site_id
          and record_date >= @currentmonth
          and record_date < @nextmonth;

        print concat('month: ', format(@currentmonth, 'yyyy-mm'), 
                     ' | site id: ', @site_id,
                     ' | total emissions: ', isnull(@monthlytotal, 0));

        set @currentmonth = @nextmonth;
    end
end;

--Q4--

create table deleted_emission_log
(
record_id int,
site_id int, 
record_date date, 
calculated_emission decimal(8,6)
)

create trigger savedeleteemissiontrg
on emission_record
after delete
as 
begin
 insert into deleted_emission_log (record_id, site_id, record_date, calculated_emission)
    select 
        record_id,
        site_id,
        record_date,
        calculated_emission
    from deleted;
end

--Q5--

alter table emission_record
add last_updated datetime 

create trigger afterupdationtrg
on emission_record
after update
as 
begin
    set nocount on;

    update er
    set last_updated = getdate()
    from emission_record er
    join inserted i on er.record_id = i.record_id
    join deleted d on er.record_id = d.record_id
    where 
        isnull(i.quantity_used, 0) != isnull(d.quantity_used, 0)
        or isnull(i.emission_factor, 0) != isnull(d.emission_factor, 0);
end

--Q6--

create table schema_change_log (
    log_id int identity(1,1) primary key,
    table_name nvarchar(100),
    changed_by nvarchar(100),
    change_time datetime
);

create trigger preventtabledrop
on database
for drop_table
as
begin
    declare @event_data xml = eventdata();
    declare @table nvarchar(100) = @event_data.value('(/event_instance/objectname)[1]', 'nvarchar(100)');
    declare @user nvarchar(100) = system_user;

    insert into schema_change_log (table_name, changed_by, change_time)
    values (@table, @user, getdate());

    raiserror ('table drop is not allowed in superdogcarbondb.', 16, 1);
    rollback;
end;

--Q7--

create trigger logaltertable
on database
for alter_table
as
begin
    declare @event_data xml = eventdata();
    declare @table nvarchar(100) = @event_data.value('(/event_instance/objectname)[1]', 'nvarchar(100)');
    declare @user nvarchar(100) = system_user;

    insert into schema_change_log (table_name, changed_by, change_time)
    values (@table, @user, getdate());
end;
