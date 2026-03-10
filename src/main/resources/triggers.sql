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


-- триггер на автоматическое создание профиля пользователя или турагента при создании пользователя с соответмсвующими ролями
CREATE OR REPLACE FUNCTION create_profile_after_user_insert()
RETURNS TRIGGER
LANGUAGE plpgsql
AS '
DECLARE
    user_role_name TEXT;
BEGIN
    SELECT
       r.name INTO user_role_name FROM roles r
       JOIN users_roles ur ON ur.role_id = r.id
       WHERE ur.user_id = NEW.id
       LIMIT 1;

    IF user_role_name = ''CLIENT'' THEN
        INSERT INTO client_profiles (passport_number, date_of_birth, user_id)
        VALUES (''UNKNOWN'', ''2000-01-01'', NEW.id);
    END IF;

    IF user_role_name = ''TOUR_OPERATOR'' THEN
        INSERT INTO tour_operator_profiles (company_name, description, user_id)
        VALUES (''New Company'', ''Description'', NEW.id);
    END IF;

    RETURN NEW;
END;';

CREATE TRIGGER trg_create_profile
AFTER INSERT ON users
FOR EACH ROW
EXECUTE FUNCTION create_profile_after_user_insert();

--- триггер на запрет удаления тура с активным бронированием
CREATE OR REPLACE FUNCTION prevent_delete_tour_with_bookings()
RETURNS TRIGGER LANGUAGE plpgsql
AS '
DECLARE
       active_count INT;
BEGIN
    SELECT COUNT(*) INTO active_count
    FROM bookings b
    JOIN tour_schedule ts ON ts.id = b.tour_schedule_id
    WHERE ts.tour_id = OLD.id;

    IF active_count > 0 THEN
        RAISE EXCEPTION ''Невозможно удалить тур: существуют активные бронирования'';
    END IF;

    RETURN OLD;
END;';

CREATE TRIGGER trg_prevent_delete_tour
BEFORE DELETE ON tours
FOR EACH ROW
EXECUTE FUNCTION prevent_delete_tour_with_bookings();


--- триггер на автоматический пересчет рейтинга при добавлении отзыва
CREATE OR REPLACE FUNCTION update_tour_rating()
RETURN TRIGGER LANGUAGE plpgsql
AS  '
BEGIN
    UPDATE tours
    SET rating = (
        SELECT COALESCE(AVG(mark),0)
        FROM tour_reviews
        WHERE tour_id = COALESCE(NEW.tour_id, OLD.tour_id)
       )
    WHERE id = COALESCE(NEW.tour_id, OLD.tour_id);

    RETURN NULL;
END;'

CREATE TRIGGER trg_update_tour_rating
AFTER INSERT OR UPDATE OR DELETE
ON tour_reviews
FOR EACH ROW
EXECUTE FUNCTION update_tour_rating();


