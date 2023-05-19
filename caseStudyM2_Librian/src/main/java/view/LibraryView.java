package view;

import service.BorrowingAndBillBookService;
import utils.CheckInput;
import utils.ReadAndWrite;
import model.AdminAndUser;
import model.EAdminAndUser;
import model.Reader;
import service.UserService;
import service.ReaderService;

import java.util.List;
import java.util.Scanner;

import static service.UserService.writeFileAdminAndUser;

public class LibraryView {
    public static final String path1 = "E:\\CaseStudyM2_Liberian\\caseStudyM2_Librian\\src\\main\\java\\data\\user.csv";
    private static AdminAndUserView adminAndUserView;
    private static List<AdminAndUser> adminAndUsers;
    private static AdminAndUser adminAndUser;
    private static UserService userService;
    private static BorrowingAndBillBookService borrowingAndBillBookService;

    static {
        adminAndUsers = ReadAndWrite.readFile(path1, AdminAndUser.class);
        userService = new UserService();
        adminAndUser = new AdminAndUser();
        adminAndUserView = new AdminAndUserView();
        borrowingAndBillBookService=new BorrowingAndBillBookService();
    }

    public static Scanner scanner = new Scanner(System.in);

    public void libraryView() {
        boolean actionLoginOrRegister = false;
        while (!actionLoginOrRegister) {
            System.out.println("╔═════════════════════════════════════════╗");
            System.out.println("║                   Menu                  ║");
            System.out.println("╠═════════════════════════════════════════╣");
            System.out.println("║               [1] Đăng nhập             ║");
            System.out.println("║               [2] Đăng ký               ║");
            System.out.println("║               [0] Exit                  ║");
            System.out.println("╚═════════════════════════════════════════╝");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    boolean actionLogin = false;
                    while (!actionLogin) {
                        long ID = Long.parseLong(CheckInput.checkIDLogin());
                        String passAdmin = CheckInput.checkPassword();
                        if (userService.checkUserNameAndPassword(ID, passAdmin) == EAdminAndUser.ADMIN) {
                            System.out.println("Thông báo trả sách trong hôm nay");
                            if(borrowingAndBillBookService.InforByPayDate().isEmpty()){
                                System.out.println("Hôm nay không có sách đến hạn đươc trả");
                            }else {
                                borrowingAndBillBookService.showBorrowingBook(borrowingAndBillBookService.InforByPayDate());
                            }
                            adminAndUserView.adminView();
                            actionLogin = true;
                        } else if (userService.checkUserNameAndPassword(ID, passAdmin) == EAdminAndUser.USER) {
                            adminAndUserView.userView(ID);
                            actionLogin = true;
                        } else {
                            System.out.println("Đăng nhập thất bại! Hãy kiểm tra lại ID đăng nhập hoặc mật khẩu của bạn!");
                            actionLogin = false;
                        }
                    }
                    break;
                case 2:
                    Reader reader1 = ReaderService.addReader();
                    String password = CheckInput.checkPassword();
                    AdminAndUser adminAndUser = new AdminAndUser(reader1.getIDReader(), reader1.getNameReader(), password, EAdminAndUser.USER);
                    adminAndUsers.add(adminAndUser);
                    writeFileAdminAndUser(adminAndUsers);
                    System.out.println("Đăng ký hoàn tất!");
                    System.out.println("Thông Tin Tài Khoản");
                    System.out.printf("ID đăng nhập của bạn là: %s\n",adminAndUser.getID());
                    System.out.printf("Mật khẩu đăng nhập của bạn là: %s\n",password);
                    break;
                case 0:
                    actionLoginOrRegister=true;
                    break;
                default:
                    System.out.println("Lựa chọn bạn không có trong danh sách, vui lòng lựa chọn lại!");
            }
        }
    }
}
