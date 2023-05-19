package service;

import model.Staff;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StaffService {
    public static List<Staff> readStaff() {
        List<Staff> staffs = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader("E:\\CaseStudyM2_Liberian\\caseStudyM2_Librian\\src\\main\\java\\data\\staff.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                String[] items = line.split(",");
                long IDSatff = Long.parseLong(items[0]);
                long phoneNumberStaff = Long.parseLong(items[4]);
                Staff staff = new Staff(IDSatff, items[1], items[2], items[3], phoneNumberStaff);
                staffs.add(staff);
            }
            fileReader.close();
            bufferedReader.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return staffs;
    }
}
