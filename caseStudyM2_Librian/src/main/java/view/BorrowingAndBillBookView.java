package view;

import utils.ReadAndWrite;
import model.BorrowingAndBillBook;
import model.EPay;
import service.BorrowingAndBillBookService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static service.BorrowingAndBillBookService.*;
import static service.BorrowingAndBillBookService.clearBorrowingBook;

public class BorrowingAndBillBookView {
    public static final String path4 = "E:\\CaseStudyM2_Liberian\\caseStudyM2_Librian\\src\\main\\java\\data\\billandborrowing.csv";
    public static Scanner scanner = new Scanner(System.in);
    private static List<BorrowingAndBillBook> borowingBooks;
    public static BorrowingAndBillBookService borrowingAndBillBookService;

    static {
        borowingBooks = ReadAndWrite.readFile(path4, BorrowingAndBillBook.class);
        borrowingAndBillBookService = new BorrowingAndBillBookService();
    }

    public void BorrowingBookView() {
        boolean actionBill = false;
        while (!actionBill) {
            System.out.println("╔═════════════════════════════════════════╗");
            System.out.println("║                   Menu                  ║");
            System.out.println("╠═════════════════════════════════════════╣");
            System.out.println("║ [1] Xem toàn bộ oder                    ║");
            System.out.println("║ [2] Tìm kiếm order                      ║");
            System.out.println("║ [3] Sửa thông tin oder                  ║");
            System.out.println("║ [4] Xóa order khỏi danh sách            ║");
            System.out.println("║ [0] Exit                                ║");
            System.out.println("╚═════════════════════════════════════════╝");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    borrowingAndBillBookService.showBorrowingBook(findListOder());
                    break;
                case 2:
                    findBorrowingBook();
                    break;
                case 3:
                    borrowingAndBillBookService.fixBorrowingBook();
                    break;
                case 4:
                    clearBorrowingBook();
                    showBorrowingBook(findListOder());
                    break;
                case 0:
                    actionBill = true;
                    break;
                default:
                    System.out.println("Lựa chọn bạn không có trong danh sách, vui lòng lựa chọn lại!");
            }
        }
    }
    public void BorrowingBookViewID(long ID) {
        boolean actionBill = false;
        while (!actionBill) {
            System.out.println("╔═════════════════════════════════════════╗");
            System.out.println("║                   Menu                  ║");
            System.out.println("╠═════════════════════════════════════════╣");
            System.out.println("║ [1] Xem toàn bộ oder                    ║");
            System.out.println("║ [2] Tìm kiếm order                      ║");
            System.out.println("║ [3] Sửa thông tin oder                  ║");
            System.out.println("║ [4] Xóa order khỏi danh sách            ║");
            System.out.println("║ [0] Exit                                ║");
            System.out.println("╚═════════════════════════════════════════╝");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    borrowingAndBillBookService.showBorrowingBook(findListOderByID(ID));
                    break;
                case 2:
                    findBorrowingBook();
                    break;
                case 3:
                    borrowingAndBillBookService.fixBorrowingBookByID(ID);
                    break;
                case 4:
                    clearBorrowingBookID(ID);
                    showBorrowingBook(findListOderByID(ID));
                    break;
                case 0:
                    actionBill = true;
                    break;
                default:
                    System.out.println("Lựa chọn bạn không có trong danh sách, vui lòng lựa chọn lại!");
            }
        }
    }

    public void BillBooKView() {
        boolean actionBill = false;
        while (!actionBill) {
            System.out.println("╔════════════════════════════════╗");
            System.out.println("║              Menu              ║");
            System.out.println("╠════════════════════════════════╣");
            System.out.println("║ [1] Xem toàn toàn bộ hóa đơn   ║");
            System.out.println("║ [2] Tìm kiếm hóa đơn           ║");
            System.out.println("║ [3] Xem doanh thu              ║");
            System.out.println("║ [0] Exit                       ║");
            System.out.println("╚════════════════════════════════╝");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    System.out.println("Đây là danh sách hóa đơn của bạn:");
                    List<BorrowingAndBillBook> borrowingAndBillBookList = new ArrayList<>();
                    borrowingAndBillBookList.clear();
                    for (BorrowingAndBillBook borrowingAndBillBook : borowingBooks) {
                        if (borrowingAndBillBook.getePay().equals(EPay.PAID)) {
                            borrowingAndBillBookList.add(borrowingAndBillBook);
                        }
                    }
                    borrowingAndBillBookService.showBorrowingBook(borrowingAndBillBookList);
                    break;
                case 2:
                    borrowingAndBillBookService.findBill();
                    break;
                case 3:
                    borrowingAndBillBookService.seeRevenue();
                    break;
                case 0:
                    actionBill = true;
                    break;
                default:
                    System.out.println("Lựa chọn bạn không có trong danh sách, vui lòng lựa chọn lại!");
            }
        }
    }
}
