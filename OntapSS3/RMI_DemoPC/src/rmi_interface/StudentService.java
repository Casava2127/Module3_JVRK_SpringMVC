package rmi_interface;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface StudentService extends Remote {
    void addStudent(Student s) throws RemoteException;
    List<Student> getAllStudents() throws RemoteException;
    Student findStudentById(int id) throws RemoteException;
    boolean deleteStudent(int id) throws RemoteException;
}
