-- 1. Populate Venues first
INSERT INTO venues (venue_name, location) VALUES
                                              ('Grand Tech Convention Center', 'New York, NY'),
                                              ('Innovation Hub', 'San Francisco, CA'),
                                              ('The Learning Annex', 'Chicago, IL');

-- 2. Populate Attendees
INSERT INTO attendees (attendee_name, email) VALUES
                                                 ('Alice Smith', 'alice.smith@example.com'),
                                                 ('Bob Jones', 'bob.jones@example.com'),
                                                 ('Charlie Brown', 'charlie.b@example.com'),
                                                 ('Diana Prince', 'diana.prince@example.com');

-- 3. Populate Events
-- (Referencing the venue_ids 1, 2, and 3 generated above)
INSERT INTO events (event_name, event_date, venue_id) VALUES
                                                          ('PostgreSQL Mastery Workshop', '2026-05-15 09:00:00', 1),
                                                          ('Cloud Architecture Summit', '2026-06-20 10:00:00', 2),
                                                          ('Data Modeling Basics', '2026-07-10 13:00:00', 3),
                                                          ('Advanced SQL Tuning', '2026-05-16 09:00:00', 1);

-- 4. Populate the Junction Table (Registrations)
-- (Referencing attendee_ids 1-4 and event_ids 1-4)
INSERT INTO event_attendee (attendee_id, event_id) VALUES
                                                       (1, 1), -- Alice attends the PostgreSQL Workshop
                                                       (1, 4), -- Alice also attends Advanced SQL Tuning
                                                       (2, 2), -- Bob attends the Cloud Architecture Summit
                                                       (3, 1), -- Charlie attends the PostgreSQL Workshop
                                                       (3, 3), -- Charlie also attends Data Modeling Basics
                                                       (4, 1), -- Diana attends the PostgreSQL Workshop
                                                       (4, 2); -- Diana also attends the Cloud Architecture Summit