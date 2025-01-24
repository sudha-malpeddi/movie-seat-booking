CREATE TABLE IF NOT EXISTS seat (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    booked BOOLEAN NOT NULL,
    version INT NOT NULL
);

INSERT INTO seat (booked, version) VALUES (false, 0);
INSERT INTO seat (booked, version) VALUES (false, 0);