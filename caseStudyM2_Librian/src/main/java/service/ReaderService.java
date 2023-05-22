package service;

import utils.CheckInput;
import utils.DateUtils;
import utils.ReadAndWrite;
import model.*;
import model.Reader;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReaderService {
    public static final String path5 = "E:\\CaseStudyM2_Liberian\\caseStudyM2_Librian\\src\\main\\java\\data\\reader.csv";
    public static final String path1 = "E:\\CaseStudyM2_Liberian\\caseStudyM2_Librian\\src\\main\\java\\data\\user.csv";
    private static List<Reader> readers;
    private static List<User> users;

    static {
        readers = ReadAndWrite.readFile(path5, Reader.class);
        users = ReadAndWrite.readFile(path1, User.class);
    }

    public static Scanner scanner = new Scanner(System.in);

    public static Reader addReader() {
        System.out.println("Nhập tên đọc giả?");
        String nameReader = scanner.nextLine();
        System.out.println("Nhập giới tính?");
        String sexReader = scanner.nextLine();
        String dateOfBirthReader = CheckInput.checkDateOfBirth();
        System.out.println("Nhập nghề nghiệp của đọc giả?");
        String jobReader = scanner.nextLine();
        System.out.println("Nhập địa chỉ của đọc giả?");
        String addressReader = scanner.nextLine();
        String password = CheckInput.checkPassword();
        Reader reader = new Reader(System.currentTimeMillis() / 1000, nameReader, sexReader, DateUtils.parse(dateOfBirthReader), jobReader, addressReader);
        readers.add(reader);
        ReadAndWrite.writeFile(path5, readers);
        User user = new User(System.currentTimeMillis() / 1000, nameReader, password, EUser.USER);
        users.add(user);
        UserService.writeFileAdminAndUser(users);
        return reader;
    }

    public static void fixReader() {
        showReader(readers);
        System.out.println("Nhập ID cần sửa?");
        long ID = Long.parseLong(CheckInput.checkIDFind());
        Reader reader = findRaederByID(ID);
        boolean actionFindReader = false;
        while (!actionFindReader) {
            if (reader == null) {
                System.out.println("ID bạn nhập không tồn tại, vui lòng thử lại với Id khác!");
                ID = Long.parseLong(scanner.nextLine());
                reader = findRaederByID(ID);
            } else {
                System.out.println("Đây là thông tin về đọc giả bạn muốn sửa");
                System.out.println(reader.toString() + "\n");
                boolean actionFixReader = false;
                while (!actionFixReader) {
                    System.out.println("Bạn muốn sửa thông tin gì?");
                    System.out.println("╔══════════════════════════════════════════╗");
                    System.out.println("║                   Menu                   ║");
                    System.out.println("╠══════════════════════════════════════════║");
                    System.out.println("║ [1] Sửa tên đọc giả                      ║");
                    System.out.println("║ [2] Sửa giới tính                        ║");
                    System.out.println("║ [3] Sửa ngày/tháng/năm sinh của đọc giả  ║");
                    System.out.println("║ [4] Sửa nghề nghiệp của đọc giả          ║");
                    System.out.println("║ [5] Sửa địa chỉ của đọc giả              ║");
                    System.out.println("║ [0] Exit                                 ║");
                    System.out.println("╚══════════════════════════════════════════╝");
                    int choice = Integer.parseInt(scanner.nextLine());
                    switch (choice) {
                        case 1:
                            System.out.println("Nhập tên đọc giả muốn sửa?");
                            String nameReader = scanner.nextLine();
                            reader.setNameReader(nameReader);
                            ReadAndWrite.writeFile(path5, readers);
                            System.out.println(reader.toString() + "\n");
                            break;
                        case 2:
                            System.out.println("Nhập giới tính đọc giả muốn sửa?");
                            String sexReader = scanner.nextLine();
                            reader.setSexReader(sexReader);
                            ReadAndWrite.writeFile(path5, readers);
                            System.out.println(reader.toString() + "\n");
                            break;
                        case 3:
                            String dateOfBirth = CheckInput.checkDateOfBirth();
                            reader.setDateOfBirthReader(DateUtils.parse(dateOfBirth));
                            ReadAndWrite.writeFile(path5, readers);
                            System.out.println(reader.toString() + "\n");
                            break;
                        case 4:
                            System.out.println("Nhập nghề nghiệp đọc giả muốn sửa?");
                            String jobReader = scanner.nextLine();
                            reader.setJobReader(jobReader);
                            ReadAndWrite.writeFile(path5, readers);
                            System.out.println(reader.toString() + "\n");
                            break;
                        case 5:
                            System.out.println("Nhập địa chỉ đọc giả muốn sửa?");
                            String addressReader = scanner.nextLine();
                            reader.setAddressReader(addressReader);
                            ReadAndWrite.writeFile(path5, readers);
                            System.out.println(reader.toString() + "\n");
                            break;
                        case 0:
                            actionFixReader = true;
                            break;
                        default:
                            System.out.println("Lựa chọn của bạn không có trong danh sách, vui lòng chọn lại!");
                    }
                }
            }
        }
    }

    public static List<Reader> clearReader() {
        showReader(readers);
        System.out.println("Nhập ID đọc giả cần xóa?");
        long ID = Long.parseLong(CheckInput.checkIDFind());
        Reader reader = findRaederByID(ID);
        boolean check = false;
        while (!check) {
            if (reader == null) {
                System.out.println("ID bạn nhập không tồn tại, vui lòng thử lại với ID khác!");
                ID = Long.parseLong(scanner.nextLine());
                reader = findRaederByID(ID);
            } else {
                System.out.println("Đây là thông tin đọc giả bạn muốn xóa");
                System.out.println(reader.toString() + "\n");
                System.out.println("Đây là danh sách đọc giả sau khi bạn xóa");
                readers.remove(reader);
                ReadAndWrite.writeFile(path5, readers);
                users.remove(UserService.findReaderInUserFile(ID));
                ReadAndWrite.writeFile(path1, users);
                check = true;
            }
        }
        return readers;
    }

    public static Reader findRaederByID(long ID) {
        for (Reader reader : readers) {
            if (reader.getIDReader() == ID) {
                return reader;
            }
        }
        return null;
    }

    public static void findReader() {
        List<Reader> reader1s = new ArrayList<>();
        boolean actionFindReader = false;
        while (!actionFindReader) {
            System.out.println("Bạn muốn tìm kiếm đọc giả theo tiêu chí gì?");
            System.out.println("╔══════════════════════════════════╗");
            System.out.println("║               Menu               ║");
            System.out.println("╠══════════════════════════════════║");
            System.out.println("║ [1] Tìm kiếm theo ID đọc giả     ║");
            System.out.println("║ [2] Tìm kiếm theo tên tác giả    ║");
            System.out.println("║ [0] Exit                         ║");
            System.out.println("╚══════════════════════════════════╝");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    boolean actionFindIDReader = false;
                    while (!actionFindIDReader) {
                        System.out.println("Nhập ID đọc giả bạn muốn tìm?");
                        long ID = Long.parseLong(CheckInput.checkIDFind());
                        reader1s.clear();
                        for (Reader reader : readers) {
                            if (reader.getIDReader() == ID) {
                                reader1s.add(reader);
                                actionFindIDReader = true;
                            }
                        }
                        if (reader1s.isEmpty()) {
                            System.out.println("ID đọc giả bạn đang tìm không có trong thư viện, hãy thử lại với ID khác! ");
                        } else {
                            showReader(reader1s);
                        }
                    }
                    break;
                case 2:
                    boolean actionFindNameReader = false;
                    while (!actionFindNameReader) {
                        System.out.println("Nhập tên đọc giả muốn tìm?");
                        String nameReader = scanner.nextLine();
                        reader1s.clear();
                        for (Reader reader : readers) {
                            if (reader.getNameReader().equals(nameReader)) {
                                reader1s.add(reader);
                                actionFindNameReader = true;
                            }
                        }
                        if (reader1s.isEmpty()) {
                            System.out.println("Tên đọc giả bạn đang tìm không có trong thư viện, hãy thử lại với tên khác! ");
                        } else {
                            showReader(reader1s);
                        }
                    }
                    break;
                case 0:
                    actionFindReader = true;
                    break;
                default:
                    System.out.println("Lựa chọn bạn không có trong danh sách, vui lòng lựa chọn lại!");
            }
        }
    }

    public static void showReader(List<Reader> readers) {
        System.out.printf("%-15s %-15s %-15s %-15s %-20s %-20s\n", "ID", "Tên đọc giả", "Giới tính",
                "Năm sinh", "Nghề nghiệp", "Địa chỉ");
        for (Reader reader : readers) {
            System.out.printf("%-15s %-15s %-15s %-15s %-20s %-20s\n", reader.getIDReader(), reader.getNameReader(),
                    reader.getSexReader(), DateUtils.format(reader.getDateOfBirthReader()), reader.getJobReader(), reader.getAddressReader());
        }
    }
}

