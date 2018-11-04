Drop table if exists apt.plan_details;

create table apt.plan_details(planid int primary key,plan_name varchar(20), date_of_introduction date, base_price double,
gst double, validity int, no_of_reports int);

insert into apt.plan_details values(0,'Plan_0',curdate(), 0,0,2,1);
insert into apt.plan_details values(1,'Plan_1',curdate(), 1000,140,7,1);
insert into apt.plan_details values(2,'Plan_2',curdate(), 2400,336,30,3);
insert into apt.plan_details values(3,'Plan_3',curdate(), 3000,21.5,60,5);
insert into apt.plan_details values(4,'Plan_4',curdate(), 5000,21.5,180,10);
insert into apt.plan_details values(5,'Plan_5',curdate(), 8000,21.5,365,20);