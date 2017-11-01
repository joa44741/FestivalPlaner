delete from LineupDates where buehneId in (select id from Buehnen where festivalId > 47);
delete from Buehnen where festivalId >47 ;
delete from CampingVarianten where festivalId > 47;
delete from FestivalsZusatzeigenschaften where festivalId >47;
delete from TicketArten where festivalId >47;
delete from Festivals where id > 47;

delete from Locations where id not in (select locationId from Festivals where id <= 47);

commit;