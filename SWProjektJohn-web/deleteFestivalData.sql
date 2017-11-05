delete from LineupDates where buehneId in (select id from Buehnen where festivalId > 151);
delete from Buehnen where festivalId >151 ;
delete from CampingVarianten where festivalId > 151;
delete from FestivalsZusatzeigenschaften where festivalId >151;
delete from TicketArten where festivalId >151;
delete from Festivals where id > 151;

delete from Locations where id not in (select locationId from Festivals where id <= 151);

commit;