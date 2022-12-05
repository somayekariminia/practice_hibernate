package service;

import Util.ValidationInputs;
import dao.StudentLoanRepository;
import dao.StudentRepository;
import model.entity.Student;
import model.enumes.Degree;

import java.time.LocalDateTime;

public class StudentServiceImpl {
    private StudentRepository studentRepository = StudentRepository.getInstance();
    private ValidationInputs validationInputs = ValidationInputs.getInstance();
    private StudentLoanRepository studentLoanRepository = StudentLoanRepository.getInstance();

    public void signUp(Student student) {
        String password = validationInputs.checkUserName(student.getInfoAccount().getUserName());
        if (password != null)
            studentRepository.save(student);
        else
            throw new RuntimeException("yourInformation is inValid");
    }

    public Student signIn(String userName, String password) {
        String passwordStudent = validationInputs.checkUserName(password);
        return studentRepository.findByUserNameAndPassword(userName, passwordStudent);
    }


    public boolean graduate(Student student) {
        LocalDateTime today = LocalDateTime.now();
        int graduateDate = 0;
        if (student.getDegree().equals(Degree.ContinueBachelor) && student.equals(Degree.DiscontinuousPHD))
            graduateDate = student.getEnterYear() + 4;
        else if (student.getDegree().equals(Degree.DiscontinuousMaster) && student.getDegree().equals(Degree.PostDiploma) && student.equals(Degree.DiscontinuousBachelor))
            graduateDate = student.getEnterYear() + 2;
        else if (student.getDegree().equals(Degree.ContinueMaster))
            graduateDate = student.getEnterYear() + 6;
        else if (student.equals(Degree.ContinuePHD))
            graduateDate = student.getEnterYear() + 8;
        if (graduateDate >= today.getYear())
            return true;
        else return false;
    }

    public Student findById(int id) {
        return studentRepository.getById(id);
    }
}
