create table organization (
    organization_id int primary key,
    organization_name varchar(255) not null,
    industry_type varchar(100)
);

create table site (
    site_id int primary key,
    organization_id int not null,
    site_name varchar(255),
    location varchar(255),
    foreign key (organization_id) references organization(organization_id)
);

create table emission_scope (
    scope_id int primary key,
    scope_type varchar(100),
    description varchar(255)
);

create table emission_target (
    target_id int primary key,
    organization_id int not null,
    scope_id int not null,
    year int,
    emission_limit decimal(18, 4),
    foreign key (organization_id) references organization(organization_id),
    foreign key (scope_id) references emission_scope(scope_id)
);

create table emission_source (
    source_id int primary key,
    source_type varchar(100),
    unit_of_measure varchar(50),
    default_emission_factor decimal(18, 6)
);

create table supplier (
    supplier_id int primary key,
    supplier_name varchar(255),
    region varchar(100)
);

create table emission_record (
    record_id int primary key,
    site_id int not null,
    supplier_id int not null,
    scope_id int not null,
    source_id int not null,
    record_date date,
    quantity_used decimal(18, 4),
    emission_factor decimal(18, 6),
    calculated_emission decimal(18, 6),
    foreign key (site_id) references site(site_id),
    foreign key (supplier_id) references supplier(supplier_id),
    foreign key (scope_id) references emission_scope(scope_id),
    foreign key (source_id) references emission_source(source_id)
);

insert into organization values (1, 'ecocorp', 'manufacturing');
insert into organization values (2, 'greentech', 'technology');

insert into site values (1, 1, 'eco plant 1', 'california');
insert into site values (2, 2, 'greentech hq', 'new york');

insert into emission_scope values (1, 'scope 1', 'direct emissions');
insert into emission_scope values (2, 'scope 2', 'indirect emissions from energy');

insert into emission_target values (1, 1, 1, 2025, 5000.0000);
insert into emission_target values (2, 2, 2, 2025, 3000.0000);

insert into emission_source values (1, 'natural gas', 'cubic meters', 2.204);
insert into emission_source values (2, 'electricity', 'kwh', 0.453);

insert into supplier values (1, 'global supplies inc.', 'west coast');
insert into supplier values (2, 'energyplus', 'east coast');

insert into emission_record values 
(1, 1, 1, 1, 1, '2025-01-15', 1000.0000, 2.200000, 2200.0000),
(2, 2, 2, 2, 2, '2025-02-20', 500.0000, 0.450000, 225.0000);

create trigger trg_preventduplicateemissionrecord
on emission_record
instead of insert
as
begin
    if exists 
	(
        select 1
        from emission_record er
        join inserted i on 
            er.site_id = i.site_id and 
            er.source_id = i.source_id and 
            er.record_date = i.record_date
    )
    begin
        print 'duplicate entry for site_id, source_id, and record_date is not allowed.';
        return;
    end

    insert into emission_record (
        record_id, site_id, supplier_id, scope_id, source_id, record_date, 
        quantity_used, emission_factor, calculated_emission
    )
    select 
        record_id, site_id, supplier_id, scope_id, source_id, record_date, 
        quantity_used, emission_factor, calculated_emission
    from inserted;
end;

create function fn_getemissioncategory(@emission int)
returns varchar(10)
as
begin
	declare @emission_count int
	set @emission_count=@emission
	if(@emission_count<500)
		begin
		return 'low'
		end
	else if(@emission_count<5000)
		begin
		return 'moderate'
		end
	return'high'
end

select record_id, dbo.fn_getemissioncategory(calculated_emission)as 'category' from emission_record

alter table emission_record
add category as dbo.fn_getemissioncategory(calculated_emission)

create function fn_computeriskscore(@calculated_emission decimal(18,6),@quanitity_used decimal(18, 4),@scope_id int)
returns decimal(18,6)
as
begin
	declare @scope_type varchar(20)
	set @scope_type=(select scope_type from emission_scope
					where scope_id=@scope_id)
	declare @risk decimal(18,6)
	set @risk=((@calculated_emission)*1.2)+(@quanitity_used *0.5)+(case when @scope_type='scope 1' then 100 else 50 end)
	return @risk
end

alter table emission_record
add risk as dbo.fn_computeriskscore(calculated_emission,quantity_used,scope_id)

create trigger trg_emergencyburnalert
on emission_record
after insert, update
as
begin
    set nocount on;

    insert into emergency_alerts (record_id, alert_message, alert_date)
    select 
        er.record_id,
        concat('emergency fuel burn detected: ', es.source_type, ' used > 20000 liters'),
        getdate()
    from 
        inserted er
    join emission_source es on er.source_id = es.source_id
    where 
        er.quantity_used > 20000 and
        es.source_type = 'diesel';

    update es
    set source_type = 
        case 
            when right(source_type, 17) != ' - emergency burn' then
                left(source_type + ' - emergency burn', 100)
            else
                source_type
        end
    from emission_source es
    join inserted er on er.source_id = es.source_id
    where 
        er.quantity_used > 20000 and
        es.source_type = 'diesel';
end;

create database snakeladdergame;
end
go

use snakeladdergame;
go

if object_id('dbo.trg_updateposition', 'tr') is not null drop trigger dbo.trg_updateposition;
if object_id('dbo.simulateturn', 'p') is not null drop procedure dbo.simulateturn;
if object_id('dbo.calculatenewposition', 'fn') is not null drop function dbo.calculatenewposition;
if object_id('dbo.getsnakesandladders', 'fn') is not null drop function dbo.getsnakesandladders;
if object_id('dbo.ludogameplay', 'u') is not null drop table dbo.ludogameplay;
go

create table dbo.ludogameplay (
    turn_id int identity(1,1) primary key,
    player_id int not null,
    dice_roll int not null,
    old_position int not null,
    new_position int not null,
    special_event varchar(50) null,
    turn_timestamp datetime default getdate()
);
go

create function dbo.getsnakesandladders(@position int)
returns int
as
begin
    declare @new_position int = @position;
    if @position = 99 return 80;
    if @position = 95 return 75;
    if @position = 92 return 73;
    if @position = 89 return 68;
    if @position = 74 return 55;
    if @position = 64 return 36;
    if @position = 62 return 19;
    if @position = 49 return 11;
    if @position = 16 return 6;
    if @position = 2 return 38;
    if @position = 7 return 14;
    if @position = 8 return 31;
    if @position = 15 return 26;
    if @position = 21 return 42;
    if @position = 28 return 84;
    if @position = 36 return 44;
    if @position = 51 return 67;
    if @position = 71 return 91;
    if @position = 78 return 98;
    return @position;
end;
go

create function dbo.calculatenewposition(@current_position int, @dice_roll int)
returns int
as
begin
    declare @new_position int;
    set @new_position = @current_position + @dice_roll;
    if @new_position > 100 
        set @new_position = @current_position;
    if @new_position <= 100
        set @new_position = dbo.getsnakesandladders(@new_position);
    return @new_position;
end;
go

create trigger trg_updateposition
on dbo.ludogameplay
instead of insert
as
begin
    set nocount on;
    
    declare @player_id int;
    declare @dice_roll int;
    declare @old_position int;
    declare @new_position int;
    declare @special_event varchar(50) = null;
    
    select @player_id = player_id, @dice_roll = dice_roll, @old_position = old_position
    from inserted;
    
    set @new_position = dbo.calculatenewposition(@old_position, @dice_roll);
    
    if @new_position > @old_position + @dice_roll
        set @special_event = 'climbed ladder from ' + cast(@old_position + @dice_roll as varchar) + ' to ' + cast(@new_position as varchar);
    else if @new_position < @old_position + @dice_roll and @old_position + @dice_roll <= 100
        set @special_event = 'slid down snake from ' + cast(@old_position + @dice_roll as varchar) + ' to ' + cast(@new_position as varchar);
    else if @old_position + @dice_roll > 100
        set @special_event = 'exceeded 100, stayed at ' + cast(@old_position as varchar);
    
    insert into dbo.ludogameplay (player_id, dice_roll, old_position, new_position, special_event)
    values (@player_id, @dice_roll, @old_position, @new_position, @special_event);
    
    print 'player ' + cast(@player_id as varchar) + 
          ' rolled a ' + cast(@dice_roll as varchar) + 
          ', moved from ' + cast(@old_position as varchar) + 
          ' to ' + cast(@new_position as varchar) + 
          case when @special_event is not null then ' (' + @special_event + ')' else '' end;
end;
go

create procedure dbo.simulateturn
as
begin
    declare @player_id int;
    declare @current_position int = 0;
    select top 1 @player_id = 
        case 
            when player_id = 2 then 1 
            when player_id = 1 then 2
            else 1
        end
    from dbo.ludogameplay
    order by turn_id desc;
    
    if @player_id = 1
    begin
        select top 1 @current_position = new_position 
        from dbo.ludogameplay 
        where player_id = 1 
        order by turn_id desc;
        if @current_position is null 
            set @current_position = 0;
    end
    else
    begin
        select top 1 @current_position = new_position 
        from dbo.ludogameplay 
        where player_id = 2 
        order by turn_id desc;
        if @current_position is null 
            set @current_position = 0;
    end
    
    if @player_id is null 
    begin
        set @player_id = 1;
        set @current_position = 0;
    end
    
    declare @dice_roll int = cast(rand() * 6 + 1 as int);
    
    insert into dbo.ludogameplay (player_id, dice_roll, old_position, new_position)
    values (@player_id, @dice_roll, @current_position, 0);
end;
go

delete from dbo.ludogameplay;
go

declare @game_ongoing bit = 1;

while @game_ongoing = 1
begin
    exec simulateturn;
    
    select top 1 * from ludogameplay order by turn_id desc;
    
    if exists (select 1 from ludogameplay where player_id = 1 and new_position = 100)
    begin
        print 'player 1 has won!';
        set @game_ongoing = 0;
    end
    
    if exists (select 1 from ludogameplay where player_id = 2 and new_position = 100)
    begin
        print 'player 2 has won!';
        set @game_ongoing = 0;
    end
end;
go

select 
    turn_id,
    player_id,
    dice_roll,
    old_position,
    new_position,
    special_event,
    turn_timestamp
from 
    dbo.ludogameplay
order by 
    turn_id;
go

select 
    'total turns' as statistic, 
    count(*) as value 
from 
    dbo.ludogameplay
union all
select 
    'player ' + cast(player_id as varchar) + ' turns',
    count(*) 
from 
    dbo.ludogameplay 
group by 
    player_id
union all
select 
    'ladders climbed',
    count(*) 
from 
    dbo.ludogameplay 
where 
    special_event like 'climbed ladder%'
union all
select 
    'snakes encountered',
    count(*) 
from 
    dbo.ludogameplay 
where 
    special_event like 'slid down snake%'
union all
select 
    'winner',
    player_id 
from 
    dbo.ludogameplay 
where 
    new_position = 100;
go
