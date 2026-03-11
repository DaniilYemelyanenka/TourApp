--- ффункция подсччета полной стоймости бронирования
CREATE OR REPLACE FUNCTION calculate_total_booking(p_booking_id BIGINT)
RETURNS NUMERIC LANGUAGE plpgsql
AS '
    DECLARE
       passengers_count INT;
       price_per_person NUMERIC(10,2);
    BEGIN
        SELECT COUNT(*)
        INTO passengers_count
        FROM booking_passengers
        WHERE booking_id = p_booking_id;

        SELECT ts.price
        INTO price_per_person
        FROM bookings b
        JOIN tour_schedule ts ON ts.id = b.tour_schedule_id
        WHERE b.id = p_booking_id;

        RETURN  passengers_count * price_per_person;
    END;';

---  функция получения свободных мест на тур
CREATE OR REPLACE FUNCTION get_available_seats(p_tour_schedule_id BIGINT)
RETURNS INT LANGUAGE plpgsql
AS '
    DECLARE
        max_capacity INT;
        booked_count INT;
    BEGIN

        SELECT capacity
        INTO max_capacity
        FROM tour_schedule
        WHERE id = p_tour_schedule_id;

        SELECT COUNT(bp.id)
        INTO booked_count
        FROM bookings b
        JOIN booking_passengers bp ON bp.booking_id = b.id
        WHERE b.tour_schedule_id = p_tour_schedule_id
        AND b.booking_status_id IN (1,2);

    RETURN max_capacity - booked_count;

    END;';

-- функция среднего рейтинга тура
CREATE OR REPLACE FUNCTION get_tour_average_rating(p_tour_id BIGINT)
RETURNS NUMERIC LANGUAGE plpgsql
AS  '
DECLARE
avg_rating NUMERIC;
BEGIN

SELECT COALESCE(ROUND(AVG(mark),2),0)
INTO avg_rating
FROM tour_reviews
WHERE tour_id = p_tour_id;

RETURN avg_rating;

END;';

-- функция суммы оплаты по бронированию
CREATE OR REPLACE FUNCTION get_booking_paid_amount(p_booking_id BIGINT)
RETURNS NUMERIC LANGUAGE plpgsql
AS '
DECLARE
total_paid NUMERIC;
BEGIN

SELECT COALESCE(SUM(amount),0)
INTO total_paid
FROM payments
WHERE booking_id = p_booking_id;

RETURN total_paid;

END;';

--- функция получения количества бронирований тура
CREATE OR REPLACE FUNCTION get_tour_bookings_count(p_tour_id BIGINT)
RETURNS INT
LANGUAGE plpgsql
AS '
DECLARE
total_bookings INT;
BEGIN

SELECT COUNT(*)
INTO total_bookings
FROM bookings b
         JOIN tour_schedule ts ON ts.id = b.tour_schedule_id
WHERE ts.tour_id = p_tour_id;

RETURN total_bookings;

END;';