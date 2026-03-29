CREATE TABLE venues (
                        venue_id SERIAL PRIMARY KEY,
                        venue_name VARCHAR(255) NOT NULL,
                        location TEXT NOT NULL
);

CREATE TABLE attendees (
                           attendee_id SERIAL PRIMARY KEY,
                           attendee_name VARCHAR(255) NOT NULL,
                           email VARCHAR(255) UNIQUE NOT NULL
);

CREATE TABLE events (
                        event_id SERIAL PRIMARY KEY,
                        event_name VARCHAR(255) NOT NULL,
                        event_date TIMESTAMP NOT NULL, -- Used TIMESTAMP to account for dates and times
                        venue_id INTEGER NOT NULL,
                        CONSTRAINT fk_venue
                            FOREIGN KEY (venue_id)
                                REFERENCES venues(venue_id)
                                ON DELETE CASCADE -- If a venue is deleted, its events are also deleted
);

CREATE TABLE event_attendee (
                                attendee_id INTEGER NOT NULL,
                                event_id INTEGER NOT NULL,
                                PRIMARY KEY (attendee_id, event_id), -- Composite Primary Key
                                CONSTRAINT fk_attendee
                                    FOREIGN KEY (attendee_id)
                                        REFERENCES attendees(attendee_id)
                                        ON DELETE CASCADE,
                                CONSTRAINT fk_event
                                    FOREIGN KEY (event_id)
                                        REFERENCES events(event_id)
                                        ON DELETE CASCADE
);