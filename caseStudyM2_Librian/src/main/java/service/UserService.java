package service;

import model.Reader;
import utils.ReadAndWrite;
import model.AdminAndUser;
import model.EAdminAndUser;

import java.io.*;
import java.util.List;
import java.util.Scanner;

public class UserService {
    public static final String path1 = "E:\\CaseStudyM2_Liberian\\caseStudyM2_Librian\\src\\main\\java\\data\\user.csv";
    private static List<AdminAndUser> adminAndUsers;

    static {
        adminAndUsers = ReadAndWrite.readFile(path1, AdminAndUser.class);
    }

    public static Scanner scanner = new Scanner(System.in);

    public static void writeFileAdminAndUser(List<AdminAndUser> adminAndUsers) {
        try {
            FileWriter fileWriter = new FileWriter(path1);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (AdminAndUser adminAndUser : adminAndUsers) {
                bufferedWriter.write(adminAndUser.toString() + "\n");
            }
            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public EAdminAndUser checkUserNameAndPassword(long ID, String password) {
        adminAndUsers = ReadAndWrite.readFile(path1, AdminAndUser.class);
        for (AdminAndUser adminAndUser : adminAndUsers) {
            if (adminAndUser.getID() == ID && adminAndUser.getPassword().equals(password)) {
                return adminAndUser.geteAdminAndUser();
            }
        }
        return null;
    }
    public static AdminAndUser findReaderInUserFile(long ID){
        adminAndUsers = ReadAndWrite.readFile(path1, AdminAndUser.class);
        for(AdminAndUser adminAndUser:adminAndUsers){
            if(adminAndUser.getID()==ID){
                return adminAndUser;
            }
        }
        return null;
    }
}

