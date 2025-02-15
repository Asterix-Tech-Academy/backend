CREATE DATABASE HomeworkPlatform;
USE HomeworkPlatform;

CREATE TABLE Users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password_hash TEXT NOT NULL,
    role ENUM('student', 'teacher', 'admin') NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    phone_number VARCHAR(15),
    address VARCHAR(100),
    subject VARCHAR(255),
    is_class_teacher BOOLEAN,
    qualification TEXT,
    class_id INT,
    CHECK (
    (role = 'student' AND class_id IS NOT NULL AND phone_number IS NOT NULL AND address IS NOT NULL) OR 
    (role = 'teacher' AND phone_number IS NOT NULL AND address IS NOT NULL AND 
        ((is_class_teacher = TRUE AND class_id IS NOT NULL) OR 
         (is_class_teacher = FALSE AND class_id IS NULL))) OR
    (role = 'admin' AND class_id IS NULL)
)
);

CREATE TABLE Classes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE,
    class_teacher_id INT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (class_teacher_id) REFERENCES Users(id) ON DELETE SET NULL
);

CREATE TABLE Enrollments (
    id INT AUTO_INCREMENT PRIMARY KEY,
    student_id INT NOT NULL,
    class_id INT NOT NULL,
    FOREIGN KEY (student_id) REFERENCES Users(id) ON DELETE CASCADE,
    FOREIGN KEY (class_id) REFERENCES Classes(id) ON DELETE CASCADE
);

CREATE TABLE Assignments (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255),
    description TEXT,
    assigned_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    deadline DATE,
    teacher_id INT NULL,
    class_id INT,
    student_id INT,
    description_file VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (teacher_id) REFERENCES Users(id),
    FOREIGN KEY (class_id) REFERENCES Classes(id),
    FOREIGN KEY (student_id) REFERENCES Users(id),
    CHECK ((class_id IS NOT NULL AND student_id IS NULL) OR (class_id IS NULL AND student_id IS NOT NULL))
);

CREATE TABLE Submission (
    id INT AUTO_INCREMENT PRIMARY KEY,
    assignment_id INT,
    student_id INT,
    submission_text TEXT,
    submission_file VARCHAR(255),
    submitted_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (assignment_id) REFERENCES Assignments(id),
    FOREIGN KEY (student_id) REFERENCES Users(id)
);

CREATE TABLE Grades (
    id INT AUTO_INCREMENT PRIMARY KEY,
    student_id INT,
    assignment_id INT,
    grade DECIMAL(5, 2),
    feedback TEXT,
    graded_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (student_id) REFERENCES Users(id),
    FOREIGN KEY (assignment_id) REFERENCES Assignments(id),
    UNIQUE(student_id, assignment_id) 
);

CREATE TABLE PlagiarismReport (
    id INT AUTO_INCREMENT PRIMARY KEY,
    submitted_id INT,
    similarity_percentage DECIMAL(5, 2),
    report_details TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (submitted_id) REFERENCES Submission(id) ON DELETE CASCADE
);

CREATE TABLE Reports (
    id INT AUTO_INCREMENT PRIMARY KEY,
    generated_by INT,
    report_type VARCHAR(255),
    generated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (generated_by) REFERENCES Users(id)
);

INSERT INTO Users (username, first_name, last_name, email, password_hash, role, created_at, phone_number, address, subject, is_class_teacher, qualification, class_id) VALUES
('user1', 'User1', 'Lastname1', 'user1@example.com', 'password123', 'student', NOW(), '1234567890', 'Address1', NULL, NULL, NULL, 1),
('user2', 'User2', 'Lastname2', 'user2@example.com', 'password123', 'teacher', NOW(), '1234567891', 'Address2', 'Math', TRUE, 'M.Sc.', 2),
('user3', 'User3', 'Lastname3', 'user3@example.com', 'password123', 'student', NOW(), '1234567892', 'Address3', NULL, NULL, NULL, 3),
('user4', 'User4', 'Lastname4', 'user4@example.com', 'password123', 'teacher', NOW(), '1234567893', 'Address4', 'Science', FALSE, 'PhD', NULL),
('user5', 'User5', 'Lastname5', 'user5@example.com', 'password123', 'admin', NOW(), '1234567894', 'Address5', NULL, NULL, NULL, NULL),
('user6', 'User6', 'Lastname6', 'user6@example.com', 'password123', 'student', NOW(), '1234567895', 'Address6', NULL, NULL, NULL, 4),
('user7', 'User7', 'Lastname7', 'user7@example.com', 'password123', 'teacher', NOW(), '1234567896', 'Address7', 'History', TRUE, 'M.A.', 5),
('user8', 'User8', 'Lastname8', 'user8@example.com', 'password123', 'student', NOW(), '1234567897', 'Address8', NULL, NULL, NULL, 2),
('user9', 'User9', 'Lastname9', 'user9@example.com', 'password123', 'teacher', NOW(), '1234567898', 'Address9', 'Physics', FALSE, 'M.Sc.', NULL),
('user10', 'User10', 'Lastname10', 'user10@example.com', 'password123', 'admin', NOW(), '1234567899', 'Address10', NULL, NULL, NULL, NULL);

INSERT INTO Classes (name, class_teacher_id, created_at) VALUES
('Class1', 2, NOW()),
('Class2', 7, NOW()),
('Class3', NULL, NOW()),
('Class4', NULL, NOW()),
('Class5', NULL, NOW());

INSERT INTO Enrollments (student_id, class_id) VALUES
(1, 1),
(3, 2),
(6, 3),
(8, 4),
(10, 5);

INSERT INTO Assignments (title, description, assigned_at, deadline, teacher_id, class_id, student_id, description_file) VALUES
('Assignment1', 'Description1', NOW(), '2025-12-31', 2, 1, NULL, 'file1.pdf'),
('Assignment2', 'Description2', NOW(), '2025-12-31', 7, 2, NULL, 'file2.pdf');
