DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS client_profiles CASCADE;
DROP TABLE IF EXISTS tour_operator_profiles CASCADE;
DROP TABLE IF EXISTS roles CASCADE;
DROP TABLE IF EXISTS users_roles CASCADE;
DROP TABLE IF EXISTS climats CASCADE;
DROP TABLE IF EXISTS locations CASCADE;
DROP TABLE IF EXISTS hotels CASCADE;
DROP TABLE IF EXISTS tour_types CASCADE;
DROP TABLE IF EXISTS transport CASCADE;
DROP TABLE IF EXISTS tours CASCADE;
DROP TABLE IF EXISTS tour_schedule CASCADE;
DROP TABLE IF EXISTS booking_status CASCADE;
DROP TABLE IF EXISTS bookings CASCADE;
DROP TABLE IF EXISTS payment_status CASCADE;
DROP TABLE IF EXISTS payments CASCADE;
DROP TABLE IF EXISTS booking_passengers CASCADE;
DROP TABLE IF EXISTS services CASCADE;
DROP TABLE IF EXISTS tour_services CASCADE;
DROP TABLE IF EXISTS tour_reviews CASCADE;


-- создаем таблицу пользователи
CREATE TABLE users (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    phone VARCHAR(20) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE,
    password_hash varchar(100) NOT NULL
);


-- создаем таблицу профиль клиента
CREATE TABLE client_profiles (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    passport_number VARCHAR(100) NOT NULL UNIQUE,
    date_of_birth DATE,
    user_id BIGINT NOT NULL UNIQUE,
    -- имя ограничения для внешенего ключа
    CONSTRAINT fk_user_client_profile
           FOREIGN KEY (user_id)
           REFERENCES users(id)
           ON DELETE CASCADE
);

-- создаем таблицу профиль турагента
CREATE TABLE tour_operator_profiles (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    company_name VARCHAR(100),
    description TEXT,
    user_id BIGINT NOT NULL UNIQUE,
    -- имя ограничения внешего ключа профиля тур оператора
    CONSTRAINT fk_user_tour_operator_profile
       FOREIGN KEY (user_id)
       REFERENCES users(id)
       ON DELETE CASCADE
);

-- создаем таблицу ролей
CREATE TABLE roles(
    id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE
);

-- создаем таблицу связей юзеры-пользователи
CREATE TABLE users_roles (
    user_id BIGINT NOT NULL,
    role_id INT NOT NULL,

    PRIMARY KEY(user_id,role_id),

    -- имя ограничения внешнего ключа с юзером
    CONSTRAINT fk_users_roles_user_id
        FOREIGN KEY (user_id)
        REFERENCES users(id)
        ON DELETE CASCADE,

    -- имя ограничения внешнего ключа с ролью
    CONSTRAINT fk_user_roles_role_id
        FOREIGN KEY (role_id)
        REFERENCES roles(id)
        ON DELETE CASCADE
);

-- создаем таблицу климатов
CREATE TABLE climats (
    id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE
);

-- создаем тоблицу локаций
CREATE TABLE locations (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    city_name VARCHAR(100) NOT NULL UNIQUE,
    climate_id INT NOT NULL,
    -- имя ограничения локации связь с климатом
    CONSTRAINT fk_locations_climat_id
        FOREIGN KEY (climate_id)
        REFERENCES climats(id)
        ON DELETE RESTRICT
);

--создаем табдицу отелей
CREATE TABLE hotels (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    stars INT NOT NULL CHECK (stars BETWEEN 1 AND 5),
    location_id BIGINT NOT NULL,
    -- имя ограничения связи с локациями
    CONSTRAINT fk_hotels_location_id
        FOREIGN KEY (location_id)
        REFERENCES locations(id)
        ON DELETE RESTRICT
);

-- создаем таблицу типы туров
CREATE TABLE tour_types (
    id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    type VARCHAR(100) NOT NULL UNIQUE
);

-- создаем табилцу транпорта
CREATE TABLE transport (
    id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE
);

-- создаем таблицу туров
CREATE TABLE tours (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    rating NUMERIC(3,2) DEFAULT 0,
    hotel_id BIGINT NOT NULL,
    tour_type_id INT NOT NULL,
    transport_id INT NOT NULL,
    tour_operator_id BIGINT NOT NULL,
    -- имя оганичения связь тур и отель
    CONSTRAINT fk_tour_hotel_id
        FOREIGN KEY (hotel_id)
        REFERENCES hotels(id)
        ON DELETE RESTRICT,
    -- имя оганичения связь тур и тип тура
    CONSTRAINT fk_tour_tour_type_id
        FOREIGN KEY (tour_type_id)
        REFERENCES tour_types(id)
        ON DELETE RESTRICT,
    -- имя оганичения связь тур и транспорт
    CONSTRAINT fk_tour_transport_id
        FOREIGN KEY (transport_id)
        REFERENCES transport(id)
        ON DELETE RESTRICT,
    -- имя оганичения связь тур и тур оператор
    CONSTRAINT fk_tour_tour_operator_id
        FOREIGN KEY (tour_operator_id)
        REFERENCES tour_operator_profiles(id)
        ON DELETE RESTRICT
);
-- создаем таблицу для распиания туров
CREATE TABLE tour_schedule (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    tour_id BIGINT NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    capacity INT NOT NULL CHECK ( capacity BETWEEN 1 AND 50),
    price NUMERIC(10,2) NOT NULL CHECK (price > 0),
    -- имяя ограничения связь распиния туров с турами
    CONSTRAINT fk_tour_schedule_tour_id
        FOREIGN KEY (tour_id)
        REFERENCES tours(id)
        ON DELETE CASCADE
);
-- создаем таблицу для статусов бронирований
CREATE TABLE booking_status (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    status VARCHAR(50) NOT NULL UNIQUE
);
-- создаем таблицу для броней
CREATE TABLE bookings (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    tour_schedule_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    booking_status_id INT NOT NULL DEFAULT 1,
    price_at_booking NUMERIC(10,2) NOT NULL CHECK(price_at_booking > 0),
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    expired_at TIMESTAMP NOT NULL,
    -- имя ограничения для проверки больше ли истекает_в чем создано_в
    CONSTRAINT check_expired_after_created
        CHECK (expired_at > created_at),
    -- имя ограничения связь брони и расписания тура
    CONSTRAINT fk_booking_tour_schedule_id
        FOREIGN KEY (tour_schedule_id)
        REFERENCES tour_schedule(id)
        ON DELETE CASCADE,
    -- имя ограничения связь брони и пользователя
    CONSTRAINT fk_booking_user_id
        FOREIGN KEY (user_id)
        REFERENCES users(id)
        ON DELETE CASCADE,
    -- имя ограничения связь брони и статуса
    CONSTRAINT fk_booking_status_id
        FOREIGN KEY (booking_status_id)
        REFERENCES booking_status(id)
        ON DELETE CASCADE
);
-- статус оплаты
CREATE TABLE payment_status (
    id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    status VARCHAR(50) NOT NULL UNIQUE
);
-- создаем таблицу оплата
CREATE TABLE payments (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    booking_id BIGINT NOT NULL,
    payment_status_id INT NOT NULL DEFAULT 1,
    amount NUMERIC(10,2) NOT NULL CHECK (amount > 0),
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    -- имя ограничения связь оплаты и брони
    CONSTRAINT fk_payment_booking_id
        FOREIGN KEY (booking_id)
        REFERENCES bookings(id)
        ON DELETE CASCADE,
    -- имя ограничения связь брони и статуса
    CONSTRAINT fk_payment_status_id
        FOREIGN KEY (payment_status_id)
        REFERENCES payment_status(id)
        ON DELETE CASCADE
);
-- создаем таблицу брони пассажиры
CREATE TABLE booking_passengers (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    booking_id BIGINT NOT NULL,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    -- имя ограничения
    CONSTRAINT fk_booking_passengers_bookings
        FOREIGN KEY (booking_id)
        REFERENCES bookings(id)
        ON DELETE CASCADE
);
-- создаем таблицу услуг
CREATE TABLE services (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE,
    description TEXT NOT NULL
);
-- создаем таблицу тур услуги
CREATE TABLE tour_services (
    tour_id BIGINT NOT NULL,
    service_id BIGINT NOT NULL,
    PRIMARY KEY (tour_id,service_id),
    -- имя ограничения
    CONSTRAINT fk_tour_services_tour
        FOREIGN KEY (tour_id)
        REFERENCES tours(id)
        ON DELETE RESTRICT,
    -- имя ограничения
    CONSTRAINT fk_tour_service_id
        FOREIGN KEY (service_id)
        REFERENCES services(id)
        ON DELETE CASCADE
);
-- создаем таблиццу отзывы на тур
CREATE TABLE tour_reviews (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    user_id BIGINT NOT NULL,
    tour_id BIGINT NOT NULL,
    mark INT NOT NULL CHECK (mark BETWEEN 1 AND 5),
    text TEXT NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    --
    CONSTRAINT fk_tour_reviews_user
        FOREIGN KEY (user_id)
        REFERENCES users(id)
        ON DELETE CASCADE,
    --
    CONSTRAINT fk_tour_reviews_tour
        FOREIGN KEY (tour_id)
        REFERENCES tours(id)
        ON DELETE CASCADE,
    -- имя ограничения один пользователь один комментарий на тур
    CONSTRAINT unique_user_tour_review UNIQUE(user_id,tour_id)
);
