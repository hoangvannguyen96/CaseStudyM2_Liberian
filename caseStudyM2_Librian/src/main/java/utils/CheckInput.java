package utils;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class CheckInput {
    public static Scanner scanner = new Scanner(System.in);

    public static String checkPassword() {
        while (true) {
            try {
                System.out.println("Nhập mật khẩu đăng nhập?");
                String pass = scanner.nextLine();
                if (pass != null && !pass.trim().isEmpty() && pass.matches("[\\w]+")) {
                    return pass;
                } else {
                    System.out.println("Bạn đã nhập sai định dạng, vui lòng nhập lại!");
                }
            } catch (Exception e) {
                System.out.println("Bạn đã nhập sai định dạng, vui lòng nhập lại!");
            }
        }
    }

    public static String checkIDLogin() {
        while (true) {
            try {
                System.out.println("Nhập ID đăng nhập?");
                System.out.println("ID đăng nhập là 1 dãy gồm 10 chữ số liên tiếp nhau!");
                String IDLogin = scanner.nextLine();
                if (IDLogin != null && !IDLogin.trim().isEmpty() && IDLogin.matches("\\d{10}")) {
                    return IDLogin;
                } else {
                    System.out.println("Bạn đã nhập sai định dạng, vui lòng nhập lại!");
                }
            } catch (Exception e) {
                System.out.println("Bạn đã nhập sai định dạng, vui lòng nhập lại!");
            }
        }
    }
    public static String checkIDFind() {
        while (true) {
            try {
                System.out.println("ID là 1 dãy gồm 10 chữ số liên tiếp nhau!");
                String IDLogin = scanner.nextLine();
                if (IDLogin != null && !IDLogin.trim().isEmpty() && IDLogin.matches("\\d{10}")) {
                    return IDLogin;
                } else {
                    System.out.println("Bạn đã nhập sai định dạng, vui lòng nhập lại!");
                }
            } catch (Exception e) {
                System.out.println("Bạn đã nhập sai định dạng, vui lòng nhập lại!");
            }
        }
    }
    public static String checkNumber() {
        while (true) {
            try {
                String number = scanner.nextLine();
                if (number != null && !number.trim().isEmpty() && number.matches("\\d*")) {
                    int number2 = Integer.parseInt(number);
                    if(number2>0){
                        return number;
                    }else {
                        System.out.println("Số nhập vào phải lớn hơn 0, vui lòng nhập lại!");
                    }
                } else {
                    System.out.println("Bạn đã nhập sai định dạng, vui lòng nhập lại!");
                }
            } catch (Exception e) {
                System.out.println("Bạn đã nhập sai định dạng, vui lòng nhập lại!");
            }
        }
    }

    public static String checkDateOfBirth() {
        while (true) {
            try {
                System.out.println("Nhập ngày/tháng/năm sinh của đọc giả?");
                System.out.println("Ví dụ: 17/01/2000");
                String date = scanner.nextLine();
                if (date != null && !date.trim().isEmpty()) {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    LocalDate dateOfBirth = LocalDate.parse(date, formatter);
                    LocalDate now = LocalDate.now();
                    if (dateOfBirth.compareTo(now) <= 0) {
                        return date;
                    } else {
                        System.out.println("Ngày nhập vào không được quá ngày hôm nay");
                    }
                } else {
                    System.out.println("Bạn đã nhập sai định dạng, vui lòng nhập lại!");
                }
            } catch (Exception e) {
                System.out.println("Bạn đã nhập sai định dạng, vui lòng nhập lại!");
            }
        }
    }
    public static String checkDate() {
        while (true) {
            try {
                System.out.println("Ví dụ: 17/01/2000");
                String date = scanner.nextLine();
                if (date != null && !date.trim().isEmpty()) {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    LocalDate dateCheck = LocalDate.parse(date, formatter);
                    return date;
                } else {
                    System.out.println("Bạn đã nhập sai định dạng, vui lòng nhập lại!");
                }
            } catch (Exception e) {
                System.out.println("Bạn đã nhập sai định dạng, vui lòng nhập lại!");
            }
        }
    }
    public static String checkDateMM_yyyy() {
        while (true) {
            try {
                System.out.println("Ví dụ: 01/2000");
                String date = scanner.nextLine();
                if (date != null && !date.trim().isEmpty()) {
                    DateUtils.parseMM_yyyy(date);
                    return date;
                } else {
                    System.out.println("Bạn đã nhập sai định dạng hoặc tháng/năm bạn nhập không tìm thấy, vui lòng nhập lại!");
                }
            } catch (Exception e) {
                System.out.println("Bạn đã nhập sai định dạng hoặc tháng/năm bạn nhập không tìm thấy, vui lòng nhập lại!");
            }
        }
    }
}
