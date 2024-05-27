CREATE TABLE events(
    events_id SERIAL PRIMARY KEY,
    event_name VARCHAR(200),
    event_Date date,
    venue_id int,
    constraint fk_venue_id foreign key(venue_id) references venues(venue_id)
);
CREATE TABLE event_attendee(
    id serial primary key ,
   event_id int,
   attendee_id int,
   constraint fk_event_id foreign key (event_id) references events(events_id) on delete cascade on update cascade,
   constraint fk_attendee_id foreign key (attendee_id) references attendees(attendee_id) on delete cascade on update cascade
);