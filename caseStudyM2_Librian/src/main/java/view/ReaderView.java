package view;

import service.ReaderService;
import utils.ReadAndWrite;
import model.Reader;

import java.util.List;
import java.util.Scanner;

import static service.ReaderService.*;

public class ReaderView {
    public static final String path5 = "E:\\CaseStudyM2_Liberian\\caseStudyM2_Librian\\src\\main\\java\\data\\reader.csv";
    private static List<Reader> readers;
    static {
        readers= ReadAndWrite.readFile(path5,Reader.class);
    }
    public static Scanner scanner=new Scanner(System.in);
    public void readerView(){
        boolean actionReaderView=false;
        while (!actionReaderView){
            System.out.println("╔═════════════════════════════════════════╗");
            System.out.println("║                  Menu                   ║");
            System.out.println("╠═════════════════════════════════════════╣");
            System.out.println("║ [1] Xem danh sách đọc giả               ║");
            System.out.println("║ [2] Thêm đọc giả                        ║");
            System.out.println("║ [3] Sửa thông tin đọc giả               ║");
            System.out.println("║ [4] Xóa đọc giả khỏi danh sách          ║");
            System.out.println("║ [5] Tìm kiếm thông tin đọc giả          ║");
            System.out.println("║ [0] Exit                                ║");
            System.out.println("╚═════════════════════════════════════════╝");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    readers= ReadAndWrite.readFile(path5,Reader.class);
                    showReader(ReadAndWrite.readFile(path5,Reader.class));
                    break;
                case 2:
                    addReader();
                    showReader(ReadAndWrite.readFile(path5,Reader.class));
                    break;
                case 3:
                    fixReader();
                    break;
                case 4:
                    showReader(clearReader());
                    break;
                case 5:
                    findReader();
                    break;
                case 0:
                    actionReaderView = true;
                    break;
                default:
                    System.out.println("Lựa chọn bạn không có trong danh sách, vui lòng lựa chọn lại!");
            }
        }
    }
}
