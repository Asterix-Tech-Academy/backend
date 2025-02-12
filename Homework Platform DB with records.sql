CREATE DATABASE HomeworkPlatform;
USE HomeworkPlatform;

CREATE TABLE Roles (
    id INT AUTO_INCREMENT PRIMARY KEY,
    role_name ENUM('Student', 'Teacher', 'Administrator') NOT NULL
);

CREATE TABLE Users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password_hash TEXT NOT NULL,
    role_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (role_id) REFERENCES Roles(id) ON DELETE CASCADE
);

CREATE TABLE Classes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    teacher_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (teacher_id) REFERENCES Users(id)
);

CREATE TABLE Enrollments (
    id INT AUTO_INCREMENT PRIMARY KEY,
    student_id INT NOT NULL,
    class_id INT NOT NULL,
    UNIQUE(student_id, class_id),
    FOREIGN KEY (student_id) REFERENCES Users(id),
    FOREIGN KEY (class_id) REFERENCES Classes(id)
);

CREATE TABLE Assignments (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    deadline TIMESTAMP NOT NULL,
    teacher_id INT NOT NULL,
    class_id INT NOT NULL,
    description_file VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (teacher_id) REFERENCES Users(id),
    FOREIGN KEY (class_id) REFERENCES Classes(id)
);

CREATE TABLE Submissions (
    id INT AUTO_INCREMENT PRIMARY KEY,
    assignment_id INT NOT NULL,
    student_id INT NOT NULL,
    submission_text TEXT,
    submission_file VARCHAR(255),
    submitted_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (assignment_id) REFERENCES Assignments(id),
    FOREIGN KEY (student_id) REFERENCES Users(id)
);

CREATE TABLE Grades (
    id INT AUTO_INCREMENT PRIMARY KEY,
    submission_id INT NOT NULL,
    teacher_id INT NOT NULL,
    grade DECIMAL(5,2) NOT NULL,
    feedback TEXT,
    graded_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (submission_id) REFERENCES Submissions(id),
    FOREIGN KEY (teacher_id) REFERENCES Users(id)
);

CREATE TABLE PlagiarismReports (
    id INT AUTO_INCREMENT PRIMARY KEY,
    submission_id INT NOT NULL,
    similarity_percentage DECIMAL(5,2) NOT NULL,
    report_details TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (submission_id) REFERENCES Submissions(id)
);

CREATE TABLE Reports (
    id INT AUTO_INCREMENT PRIMARY KEY,
    generated_by INT NOT NULL,
    report_type VARCHAR(50) NOT NULL,
    generated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (generated_by) REFERENCES Users(id)
);


INSERT INTO Roles (role_name) VALUES 
('Student'), 
('Teacher'), 
('Administrator');


INSERT INTO Users (first_name, last_name, email, password_hash, role_id) VALUES 
('Иван', 'Иванов', 'ivan@example.com', 'hashedpassword1', 1),
('Мария', 'Петрова', 'maria@example.com', 'hashedpassword2', 2),
('Алекс', 'Димитров', 'alex@example.com', 'hashedpassword3', 3),
('Георги', 'Симеонов', 'georgi@example.com', 'hashedpassword4', 1),
('Анна', 'Тодорова', 'anna@example.com', 'hashedpassword5', 2),
('Никола', 'Петров', 'nikola@example.com', 'hashedpassword6', 1),
('Елена', 'Иванова', 'elena@example.com', 'hashedpassword7', 1),
('Петър', 'Михайлов', 'petar@example.com', 'hashedpassword8', 2),
('Даниела', 'Стоянова', 'daniela@example.com', 'hashedpassword9', 3),
('Симеон', 'Колев', 'simeon@example.com', 'hashedpassword10', 1);


INSERT INTO Classes (name, teacher_id) VALUES 
('Математика 9А', 2),
('Физика 10Б', 2),
('История 8В', 5),
('Информатика 11А', 2),
('Биология 9Б', 5),
('Химия 10А', 8),
('Английски език 12Б', 8),
('География 7В', 5),
('Литература 9В', 2),
('Физическо възпитание 10Г', 8);


INSERT INTO Enrollments (student_id, class_id) VALUES 
(1, 1), (1, 2), 
(4, 3), (4, 4), 
(6, 5), (6, 6), 
(7, 7), (7, 8), 
(10, 9), (10, 10);


INSERT INTO Assignments (title, description, deadline, teacher_id, class_id, description_file) VALUES 
('Домашно по математика', 'Решете задачи от 1 до 5', '2025-03-01 23:59:59', 2, 1, NULL),
('Лабораторна работа по физика', 'Изчислете гравитацията на Марс', '2025-03-05 23:59:59', 2, 2, NULL),
('Тест по история', 'Отговорете на въпросите', '2025-03-10 23:59:59', 5, 3, NULL),
('Кодиране на Java', 'Решете задачите в IntelliJ', '2025-03-15 23:59:59', 2, 4, NULL),
('Растения и животни', 'Опишете екосистемата', '2025-03-20 23:59:59', 5, 5, NULL),
('Решаване на химични уравнения', 'Попълнете таблицата', '2025-03-25 23:59:59', 8, 6, NULL),
('Превод на текст', 'Преведете абзаца', '2025-03-30 23:59:59', 8, 7, NULL),
('Континенти и океани', 'Маркирайте на картата', '2025-04-01 23:59:59', 5, 8, NULL),
('Анализ на стихотворение', 'Напишете коментар', '2025-04-05 23:59:59', 2, 9, NULL),
('Спортни упражнения', 'Практическо изпълнение', '2025-04-10 23:59:59', 8, 10, NULL);


INSERT INTO Submissions (assignment_id, student_id, submission_text, submission_file) VALUES 
(1, 1, 'Решил съм задачите.', NULL),
(2, 1, NULL, 'submission1.pdf'),
(3, 4, 'Отговори на въпросите', NULL),
(4, 4, NULL, 'submission2.zip'),
(5, 6, 'Описание на екосистемата', NULL),
(6, 6, NULL, 'chemistry.xlsx'),
(7, 7, 'Преведен текст', NULL),
(8, 7, NULL, 'map.png'),
(9, 10, 'Анализ на стихотворение', NULL),
(10, 10, NULL, 'exercise_video.mp4');

INSERT INTO Grades (submission_id, teacher_id, grade, feedback) VALUES 
(1, 2, 5.50, 'Добре направено!'),
(2, 2, 4.75, 'Можеше по-добре.'),
(3, 5, 6.00, 'Отлично!'),
(4, 2, 5.25, 'Добър код.'),
(5, 5, 4.00, 'Може да се подобри.'),
(6, 8, 3.75, 'Поправете грешките.'),
(7, 8, 5.80, 'Перфектно!'),
(8, 5, 4.50, 'Някои пропуски.'),
(9, 2, 6.00, 'Изключителен анализ.'),
(10, 8, 5.00, 'Добре изпълнено.');


INSERT INTO PlagiarismReports (submission_id, similarity_percentage, report_details) VALUES 
(1, 10.5, 'Леко сходство с друг ученик.'),
(2, 75.0, 'Висока вероятност за плагиатство.'),
(3, 0.0, 'Без съвпадения.'),
(4, 20.0, 'Няколко сходства.'),
(5, 50.0, 'Средно ниво на плагиатство.'),
(6, 5.0, 'Минимални прилики.'),
(7, 80.0, 'Много висока вероятност за плагиатство.'),
(8, 12.0, 'Малки прилики с външни източници.'),
(9, 30.0, 'Някои пасажи съвпадат.'),
(10, 100.0, 'Пълно съвпадение с друг файл.');


INSERT INTO Reports (generated_by, report_type) VALUES 
(3, 'Общ успех'),
(3, 'Плагиатство'),
(5, 'Домашни без оценка'),
(8, 'Анализ на резултати'),
(2, 'Статистика по предмети'),
(5, 'Отчети по класове'),
(8, 'Нарушения'),
(3, 'Отсъствия'),
(2, 'Оценки за срок'),
(8, 'Изпитни резултати');

