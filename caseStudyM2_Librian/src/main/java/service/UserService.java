package service;

import utils.ReadAndWrite;
import model.User;
import model.EUser;

import java.io.*;
import java.util.List;
import java.util.Scanner;

public class UserService {
    public static final String path1 = "E:\\CaseStudyM2_Liberian\\caseStudyM2_Librian\\src\\main\\java\\data\\user.csv";
    public static List<User> users;

    static {
        users = ReadAndWrite.readFile(path1, User.class);
    }

    public static Scanner scanner = new Scanner(System.in);

    public static void writeFileAdminAndUser(List<User> users) {
        try {
            FileWriter fileWriter = new FileWriter(path1);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (User user : users) {
                bufferedWriter.write(user.toString() + "\n");
            }
            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public EUser checkUserNameAndPassword(long ID, String password) {
        users = ReadAndWrite.readFile(path1, User.class);
        for (User user : users) {
            if (user.getID() == ID && user.getPassword().equals(password)) {
                return user.geteAdminAndUser();
            }
        }
        return null;
    }
    public static User findReaderInUserFile(long ID){
        users = ReadAndWrite.readFile(path1, User.class);
        for(User user : users){
            if(user.getID()==ID){
                return user;
            }
        }
        return null;
    }
}

