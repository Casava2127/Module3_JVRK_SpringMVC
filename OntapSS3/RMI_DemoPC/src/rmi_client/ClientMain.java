package rmi_client;

import rmi_interface.Student;
import rmi_interface.StudentService;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;

public class ClientMain {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("192.168.1.226", 1099);


            StudentService service = (StudentService) registry.lookup("StudentService");

            // Thêm sinh viên
            service.addStudent(new Student(1, "Nguyen Van A", 3.5));
            service.addStudent(new Student(2, "Tran Thi B", 3.8));

            // Lấy toàn bộ danh sách
            List<Student> list = service.getAllStudents();
            System.out.println("Danh sách sinh viên:");
            list.forEach(System.out::println);

            // Tìm sinh viên theo ID
            Student s = service.findStudentById(1);
            System.out.println("Tìm ID=1: " + s);

            // Xóa sinh viên
            boolean deleted = service.deleteStudent(2);
            System.out.println("Xóa ID=2: " + deleted);

            // Kiểm tra lại danh sách
            System.out.println("Danh sách sau khi xóa:");
            service.getAllStudents().forEach(System.out::println);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
