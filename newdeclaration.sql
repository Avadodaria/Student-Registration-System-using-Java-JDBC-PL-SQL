create or replace package dbprocedure as
    procedure show_students(stud_curs out sys_refcursor);
    
    procedure show_courses(stud_curs out sys_refcursor);

    procedure show_course_credit(stud_curs out sys_refcursor);

    procedure show_classes(stud_curs out sys_refcursor);
    
    procedure show_enrollments(stud_curs out sys_refcursor);

    procedure show_grades(stud_curs out sys_refcursor);

    procedure show_prerequisites(stud_curs out sys_refcursor);
    
    procedure show_logs(stud_curs out sys_refcursor);


	
	procedure get_prerequisites(dept_code_pre in prerequisites.dept_code%type, course#_pre in prerequisites.course#%type, r_cursor out sys_refcursor );
	
	procedure get_prerequisites_rev(dept_code_pre in prerequisites.dept_code%type, course#_pre in prerequisites.course#%type, r_cursor out sys_refcursor );
	
	procedure get_students_course_details(studentid_b# in students.b#%type,error_message out varchar2,r_cursor out sys_refcursor);

	procedure get_class_course_student(studentid_classid in classes.classid%type,message out varchar2,stud_curs out sys_refcursor);
    
	procedure drop_student(studentid_b# in students.b#%type,studentid_classid in classes.classid%type, message out varchar );
    
	procedure enroll_student(studentid_b# students.b#%type,studentid_classid classes.classid%type,message out varchar2);
	
	procedure delete_student(studentid_b# in students.b#%type,message out varchar);
	end;
/
show errors