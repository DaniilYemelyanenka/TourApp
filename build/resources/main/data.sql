-- Роли
INSERT INTO roles (name) VALUES
('CLIENT'),
('TOUR_OPERATOR'),
('ADMIN');

-- Климаты
INSERT INTO climats (name) VALUES
('Tropical'),
('Desert'),
('Temperate'),
('Arctic'),
('Mediterranean');

-- Локации
INSERT INTO locations (city_name, climate_id) VALUES
('Phuket', 1),
('Cairo', 2),
('Paris', 3),
('Reykjavik', 4),
('Barcelona', 5),
('Maldives', 1),
('Dubai', 2),
('Rome', 5),
('Berlin', 3),
('Moscow', 3);

-- Отели
INSERT INTO hotels (name, stars, location_id) VALUES
('Sunshine Resort', 5, 1),
('Desert Oasis', 4, 2),
('Eiffel Stay', 3, 3),
('Ice Lodge', 4, 4),
('Mediterranean Palace', 5, 5),
('Maldives Paradise', 5, 6),
('Dubai Grand', 4, 7),
('Rome Comfort', 3, 8),
('Berlin Central', 4, 9),
('Moscow Elite', 5, 10);

-- Типы туров
INSERT INTO tour_types (type) VALUES
('Excursion'),
('Relaxation'),
('Adventure'),
('Cruise'),
('Cultural'),
('Honeymoon');

-- Транспорт
INSERT INTO transport (name) VALUES
('Plane'),
('Bus'),
('Train'),
('Ship'),
('Car');

-- Пользователи
INSERT INTO users (first_name, last_name, phone, email) VALUES
('John', 'Doe', '+1234567890', 'john@example.com'),
('Alice', 'Smith', '+1234567891', 'alice@example.com'),
('Bob', 'Johnson', '+1234567892', 'bob@example.com'),
('Emma', 'Brown', '+1234567893', 'emma@example.com'),
('David', 'Wilson', '+1234567894', 'david@example.com'),
('Sophia', 'Taylor', '+1234567895', 'sophia@example.com'),
('Michael', 'Anderson', '+1234567896', 'michael@example.com'),
('Olivia', 'Thomas', '+1234567897', 'olivia@example.com'),
('William', 'Jackson', '+1234567898', 'william@example.com'),
('Isabella', 'White', '+1234567899', 'isabella@example.com'),
('TourOp1', 'Company', '+1234500001', 'tourop1@example.com'),
('TourOp2', 'Company', '+1234500002', 'tourop2@example.com'),
('TourOp3', 'Company1', '+1234500003', 'tourop3@example.com'),
('TourOp4', 'Company1', '+1234500004', 'tourop4@example.com'),
('TourOp5', 'Company1', '+1234500005', 'tourop5@example.com');


-- Клиентские профили
INSERT INTO client_profiles (passport_number, date_of_birth, user_id) VALUES
('AA1234567', '1990-05-10', 1),
('BB7654321', '1985-11-20', 2),
('CC9876543', '1992-07-15', 3),
('DD4567891', '1988-03-30', 4),
('EE1237890', '1995-01-05', 5),
('FF5678901', '1993-12-12', 6),
('GG6789012', '1991-08-22', 7),
('HH7890123', '1989-06-17', 8),
('II8901234', '1994-09-10', 9),
('JJ9012345', '1996-04-28', 10);

-- Профили тур операторов
INSERT INTO tour_operator_profiles (company_name, description, user_id) VALUES
('TravelFun', 'Tours worldwide', 11),
('AdventureCo', 'Adventure trips', 12),
('RelaxTours', 'Relaxing vacations', 13),
('CultureTrip', 'Cultural tours', 14),
('CruiseMaster', 'Cruises around the world', 15);


-- Назначение ролей пользователям
-- Клиенты
INSERT INTO users_roles (user_id, role_id) VALUES
(1, 1),(2,1),(3,1),(4,1),(5,1),(6,1),(7,1),(8,1),(9,1),(10,1);
-- Туроператоры
INSERT INTO users_roles (user_id, role_id) VALUES
(11,2),(12,2),(13,2),(14,2),(15,2);

-- Туры
INSERT INTO tours (name, hotel_id, tour_type_id, transport_id, tour_operator_id) VALUES
('Tropical Adventure', 1, 3, 1, 1),
('Desert Explorer', 2, 3, 2, 2),
('Romantic Paris', 3, 2, 1, 3),
('Icy Reykjavik', 4, 1, 1, 4),
('Mediterranean Relax', 5, 2, 1, 3),
('Maldives Paradise', 6, 2, 1, 1),
('Dubai Luxury', 7, 6, 1, 2),
('Rome Culture', 8, 5, 1, 4),
('Berlin Discovery', 9, 1, 3, 5),
('Moscow Elite Trip', 10, 4, 1, 5),
('Cruise Caribbean', 6, 4, 4, 5),
('Adventure Alps', 9, 3, 5, 2),
('Historic Egypt', 2, 5, 2, 4),
('Honeymoon Paris', 3, 6, 1, 3),
('Luxury Maldives', 6, 2, 1, 1);

-- Расписание туров
INSERT INTO tour_schedule (tour_id, start_date, end_date, capacity, price) VALUES
(1, '2026-06-01', '2026-06-10', 20, 1500.00),
(2, '2026-07-05', '2026-07-15', 15, 1200.00),
(3, '2026-08-10', '2026-08-17', 25, 2000.00),
(4, '2026-09-01', '2026-09-07', 10, 1800.00),
(5, '2026-06-15', '2026-06-22', 30, 1600.00),
(6, '2026-07-20', '2026-07-27', 20, 2500.00),
(7, '2026-08-05', '2026-08-12', 15, 3000.00),
(8, '2026-09-10', '2026-09-17', 20, 1900.00),
(9, '2026-06-25', '2026-07-02', 25, 1400.00),
(10, '2026-07-30', '2026-08-06', 20, 2200.00),
(11, '2026-08-15', '2026-08-22', 30, 2700.00),
(12, '2026-09-20', '2026-09-27', 20, 2000.00),
(13, '2026-10-01', '2026-10-10', 15, 1500.00),
(14, '2026-10-15', '2026-10-22', 15, 2100.00),
(15, '2026-11-01', '2026-11-10', 20, 2600.00);

-- Статусы бронирований
INSERT INTO booking_status (status) VALUES
('Pending'),
('Confirmed'),
('Cancelled');


-- Бронирования (рандомные)
INSERT INTO bookings (tour_schedule_id, user_id, price_at_booking) VALUES
(1, 1, 1500.00),
(2, 2, 1200.00),
(3, 3, 2000.00),
(4, 4, 1800.00),
(5, 5, 1600.00),
(6, 6, 2500.00),
(7, 7, 3000.00),
(8, 8, 1900.00),
(9, 9, 1400.00),
(10, 10, 2200.00);

-- Статусы оплаты
INSERT INTO payment_status (status) VALUES
('Pending'),
('Paid'),
('Failed');


-- Платежи
INSERT INTO payments (booking_id, payment_status_id, amount, created_at) VALUES
(1, 2, 1500.00, '2026-03-01 10:10'),
(2, 1, 1200.00, '2026-03-02 11:05'),
(3, 2, 2000.00, '2026-03-03 12:05'),
(4, 2, 1800.00, '2026-03-04 13:05'),
(5, 1, 1600.00, '2026-03-05 14:05');


-- Пассажиры
INSERT INTO booking_passengers (booking_id, first_name, last_name) VALUES
(1, 'John', 'Doe'),
(1, 'Alice', 'Smith'),
(2, 'Bob', 'Johnson'),
(2, 'Emma', 'Brown'),
(2, 'David', 'Wilson'),
(3, 'Sophia', 'Taylor');

-- Услуги
INSERT INTO services (name, description) VALUES
('Breakfast', 'Daily breakfast included'),
('Airport Transfer', 'Pick up and drop off at airport'),
('Guided Tour', 'Local guide included'),
('Spa', 'Relaxing spa treatments');

-- Тур-услуги
INSERT INTO tour_services (tour_id, service_id) VALUES
(1, 1),(1,2),(2,2),(2,3),(3,1),(3,3),(4,4),(5,1),(5,2),(6,1),(6,4);

-- Отзывы
INSERT INTO tour_reviews (user_id, tour_id, mark, text, created_at) VALUES
(1, 1, 5, 'Amazing trip!', '2026-03-11 15:00'),
(2, 2, 4, 'Great experience in the desert', '2026-03-12 16:00'),
(3, 3, 5, 'Paris was magical!', '2026-03-13 17:00'),
(4, 4, 4, 'Cold but beautiful Reykjavik', '2026-03-14 18:00'),
(5, 5, 5, 'Mediterranean vibes are amazing', '2026-03-15 19:00');