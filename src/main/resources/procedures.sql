--  процедура создания бронирования
CREATE OR REPLACE PROCEDURE create_booking(
    p_user_id BIGINT,
    p_tour_schedule_id BIGINT,
    p_price_at_booking NUMERIC(10,2)
)
LANGUAGE plpgsql
AS '
BEGIN
INSERT INTO bookings(
    tour_schedule_id,
    user_id,
    booking_status_id,
    price_at_booking
)
VALUES(
          p_tour_schedule_id,
          p_user_id,
          1
      );
END;';


--- процедура добваления пассажира в бронь
CREATE OR REPLACE PROCEDURE add_passenger_to_booking(
    p_booking_id BIGINT,
    p_first_name VARCHAR,
    p_last_name VARCHAR
)
LANGUAGE plpgsql
AS '
BEGIN
INSERT INTO booking_passengers(
    booking_id,
    first_name,
    last_name
)
VALUES(
          p_booking_id,
          p_first_name,
          p_last_name
      );
END;';

-- процедура изменения статуса  брони
CREATE OR REPLACE PROCEDURE update_booking_status(
    p_booking_id BIGINT,
    p_status_id INT
)
LANGUAGE plpgsql
AS '
BEGIN
UPDATE bookings
SET booking_status_id = p_status_id
WHERE id = p_booking_id;
END;';


-- процедура добваления оплаты
CREATE OR REPLACE PROCEDURE add_payment(
    p_booking_id BIGINT,
    p_amount NUMERIC,
    p_payment_status_id INT
)
LANGUAGE plpgsql
AS '
BEGIN
INSERT INTO payments(
    booking_id,
    amount,
    payment_status_id
)
VALUES(
          p_booking_id,
          p_amount,
          p_payment_status_id
      );
END;';

-- процедура отмены бронирования
CREATE OR REPLACE PROCEDURE cancel_booking(
    p_booking_id BIGINT
)
LANGUAGE plpgsql
AS '
BEGIN
UPDATE bookings
SET booking_status_id = 3
WHERE id = p_booking_id;
END; ';