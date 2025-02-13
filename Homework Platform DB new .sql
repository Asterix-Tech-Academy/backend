CREATE DATABASE HomeworkPlatform;
USE HomeworkPlatform;

CREATE TABLE Users (
    id INT AUTO_INCREMENT PRIMARY KEY,
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
    CHECK (
        (role = 'student' AND phone_number IS NOT NULL AND address IS NOT NULL) OR
        (role = 'teacher' AND subject IS NOT NULL AND is_class_teacher IS NOT NULL AND qualification IS NOT NULL) OR
        (role = 'admin')
    )
);

CREATE TABLE Classes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE,
    class_teacher_id INT NULL,  
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (class_teacher_id) REFERENCES Users(id) ON DELETE SET NULL
);

CREATE TABLE SchoolGroups (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    teacher_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (teacher_id) REFERENCES Users(id) ON DELETE CASCADE
);

CREATE TABLE Enrollments (
    id INT AUTO_INCREMENT PRIMARY KEY,
    student_id INT NOT NULL,
    class_id INT NOT NULL,
    FOREIGN KEY (student_id) REFERENCES Users(id) ON DELETE CASCADE,
    FOREIGN KEY (class_id) REFERENCES Classes(id) ON DELETE CASCADE
);

CREATE TABLE GroupMemberships (
    id INT AUTO_INCREMENT PRIMARY KEY,
    student_id INT NOT NULL,
    group_id INT NOT NULL,
    FOREIGN KEY (student_id) REFERENCES Users(id) ON DELETE CASCADE,
    FOREIGN KEY (group_id) REFERENCES SchoolGroups(id) ON DELETE CASCADE
);

CREATE TABLE Assignments (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255),
    description TEXT,
    assigned_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    deadline DATE,
    teacher_id INT, 
    class_id INT,    
    student_id INT,  
    description_file VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (teacher_id) REFERENCES Users(id),
    FOREIGN KEY (class_id) REFERENCES Classes(id),
    FOREIGN KEY (student_id) REFERENCES Users(id)
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
    grade DECIMAL(5, 2),
    feedback TEXT,
    graded_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (student_id) REFERENCES Users(id)
);

CREATE TABLE PlagiarismReport (
    id INT AUTO_INCREMENT PRIMARY KEY,
    submitted_id INT,
    similarity_percentage DECIMAL(5, 2),
    report_details TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (submitted_id) REFERENCES Submission(id)
);

CREATE TABLE Reports (
    id INT AUTO_INCREMENT PRIMARY KEY,
    generated_by INT,
    report_type VARCHAR(255),
    generated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (generated_by) REFERENCES Users(id)
);

INSERT INTO Users (first_name, last_name, email, password_hash, role, created_at, phone_number, address, subject, is_class_teacher, qualification) VALUES
('Tina', 'Brown', 'tina.brown@example.com', 'password123', 'student', '2020-01-04', '1234567890', '1234 Elm St', NULL, NULL, NULL),
('Chad', 'Johnson', 'chad.johnson@example.com', 'password123', 'teacher', '2022-03-15', '2345678901', '5678 Oak Ave', 'Math', TRUE, 'M.Sc. Mathematics'),
('Samantha', 'Williams', 'samantha.williams@example.com', 'password123', 'student', '2023-09-03', '3456789012', '9102 Pine Rd', NULL, NULL, NULL),
('Lynn', 'Smith', 'lynn.smith@example.com', 'password123', 'teacher', '2024-06-28', '4567890123', '1123 Maple Dr', 'Science', TRUE, 'PhD in Biology'),
('Lisa', 'Taylor', 'lisa.taylor@example.com', 'password123', 'student', '2022-11-06', '5678901234', '3456 Birch Blvd', NULL, NULL, NULL),
('Alyssa', 'Martinez', 'alyssa.martinez@example.com', 'password123', 'teacher', '2020-03-06', '6789012345', '7890 Cedar Ln', 'English', TRUE, 'M.A. in Literature'),
('Crystal', 'Davis', 'crystal.davis@example.com', 'password123', 'student', '2024-07-19', '7890123456', '4567 Fir St', NULL, NULL, NULL),
('Tina', 'Garcia', 'tina.garcia@example.com', 'password123', 'student', '2021-02-25', '8901234567', '6543 Spruce St', NULL, NULL, NULL),
('Louis', 'Rodriguez', 'louis.rodriguez@example.com', 'password123', 'teacher', '2022-01-10', '9012345678', '2345 Redwood Dr', 'History', FALSE, 'M.A. History'),
('Christopher', 'Martinez', 'christopher.martinez@example.com', 'password123', 'admin', '2024-05-26', '1234567899', '8765 Willow Ave', NULL, NULL, NULL);

INSERT INTO Classes (name, class_teacher_id, created_at) VALUES
('Math 101', 2, '2020-01-04'),
('Science 102', 3, '2022-03-15'),
('Literature 201', 4, '2023-09-03'),
('Biology 202', 5, '2024-06-28'),
('Physics 101', 6, '2022-11-06');

INSERT INTO SchoolGroups (name, teacher_id, created_at) VALUES
('Math Group 1', 2, '2020-01-04'),
('History Group A', 9, '2022-03-15'),
('Literature Group 2', 4, '2023-09-03');

INSERT INTO Enrollments (student_id, class_id) VALUES
(1, 1),
(3, 2),
(4, 3);

INSERT INTO GroupMemberships (student_id, group_id) VALUES
(1, 1),
(3, 2),
(4, 3);

INSERT INTO Assignments (title, description, assigned_at, deadline, teacher_id, class_id, student_id, description_file, created_at) VALUES
('Math Homework 1', 'Solve algebra problems.', '2022-01-01', '2022-01-15', 2, 1, 1, 'homework1.pdf', '2022-01-04'),
('Biology Paper', 'Write a paper on cell biology.', '2022-03-15', '2022-04-10', 4, 4, 3, 'biology_paper.docx', '2022-03-16');

INSERT INTO Submission (assignment_id, student_id, submission_text, submission_file, submitted_at) VALUES
(1, 1, 'Completed problems 1-10.', 'math_homework1.pdf', '2022-01-10'),
(2, 3, 'Detailed explanation of cell structures.', 'biology_paper_final.pdf', '2022-04-05');

INSERT INTO Grades (student_id, grade, feedback, graded_at) VALUES
(1, 5.0, 'Excellent work!', '2022-01-15'),
(3, 4.5, 'Good paper, needs more research.', '2022-04-12');

INSERT INTO PlagiarismReport (submitted_id, similarity_percentage, report_details, created_at) VALUES
(1, 0.0, 'No plagiarism detected.', '2022-01-12'),
(2, 35.2, 'Possible plagiarism from online sources.', '2022-04-07');

INSERT INTO Reports (generated_by, report_type, generated_at) VALUES
(10, 'Annual Report', '2024-05-26'),
(1, 'Progress Report', '2022-03-15');
