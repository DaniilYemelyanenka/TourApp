-- триггер на автоматическое создание expired_at
CREATE OR REPLACE FUNCTION set_auto_exipired_date()
RETURNS TRIGGER
LANGUAGE plpgsql
AS '
BEGIN
    IF NEW.expired_at IS NULL THEN
        NEW.expired_at := NEW.created_at + INTERVAL ''3 hours'';
    END IF;
    RETURN NEW;
END;
';

CREATE TRIGGER trigger_auto_expired_date
BEFORE INSERT ON bookings
FOR EACH ROW
EXECUTE FUNCTION set_auto_exipired_date();




-- труггер на проверку вместимости перед добавление
CREATE OR REPLACE FUNCTION check_tour_capacity_limit()
RETURNS TRIGGER
LANGUAGE plpgsql
AS '
DECLARE
    max_capacity INT;
    current_bookings_on_ts INT;
BEGIN
    SELECT capacity INTO max_capacity FROM tour_schedule WHERE id = NEW.tour_schedule_id;

    SELECT COUNT(bp.id) INTO current_bookings_on_ts FROM public.bookings b
    JOIN booking_passengers bp
	    ON bp.booking_id = b.id
    where b.tour_schedule_id = NEW.tour_schedule_id
    AND b.booking_status_id IN(1,2);

    IF current_bookings_on_ts >= max_capacity THEN
        RAISE EXCEPTION ''превышен лимит мест!'';
    END IF;

    RETURN NEW;

END;
';

CREATE TRIGGER trigger_check_tour_capacity
BEFORE INSERT ON bookings
FOR EACH ROW EXECUTE FUNCTION check_tour_capacity_limit();