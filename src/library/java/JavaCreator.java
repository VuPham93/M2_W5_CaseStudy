package library.java;

import tools.FileReaderAndWriter;

import java.util.ArrayList;

public class JavaCreator {
    public static void main(String[] args) {
        ArrayList<JavaLibrary> javaLibraries =new ArrayList<>();
        String folderPath = "D:\\Downloads\\Codegym\\Slide\\Module2\\";
        javaLibraries.add(new JavaLibrary("1","JavaOverview","2Mb",folderPath + "Slide_1_2_3_JavaOverview.pdf","Giớ thiệu Về Java"));
        javaLibraries.add(new JavaLibrary("2","OOP","2Mb",folderPath + "Slide_4_OOP.pdf","Kế Thừa Trong Java"));
        javaLibraries.add(new JavaLibrary("3","AccessModifier","2Mb",folderPath + "Slide_5_AccessModifier.pdf","Mức Độ Truy Cập Trong Java"));
        javaLibraries.add(new JavaLibrary("4","Inheritance","2Mb",folderPath + "Slide_6_Inheritance.pdf","Inheritance"));
        javaLibraries.add(new JavaLibrary("5","Interface_and_Abstract_class","2Mb",folderPath + "Slide_7_Interface_and_Abstract_class.pdf","Lớp Abstract và Lớp interface trong Java"));
        javaLibraries.add(new JavaLibrary("6","Clean_Code","2Mb",folderPath + "Slide_8_Clean_Code.pdf","Làm Sạch Mã Nguồn"));
        javaLibraries.add(new JavaLibrary("7","Test_First_TDD","2Mb",folderPath + "Slide_9_Test_First_TDD.pdf","Kiểm Thử Giúp trương trình ít rủi do"));
        javaLibraries.add(new JavaLibrary("8","Danh_Sach","2Mb",folderPath + "Slide_10_DSA_Danh_Sach.pdf","ArrayList_LinkedList"));
        javaLibraries.add(new JavaLibrary("9","Stack_Queue","2Mb",folderPath + "Slide_11_DSA_Stack_Queue.pdf","Stack_Queue"));
        javaLibraries.add(new JavaLibrary("10","Map_Tree","2Mb",folderPath + "Slide_12_Map_Tree.pdf","Lưu trữ Giá trị trên Key_value "));
        javaLibraries.add(new JavaLibrary("11","Searching_Algorithms","2Mb",folderPath + "Slide_13_Searching_Algorithms.pdf","Thuật Toán Tìm Kiếm nhị Phân Và Tuần Tự"));
        javaLibraries.add(new JavaLibrary("12","Sorting_Algorithms","2Mb",folderPath + "Slide_14_Sorting_Algorithms.pdf","Thuật Toán Sắp Xếp: Chèn+Chọn+Nổi Bọt"));
        javaLibraries.add(new JavaLibrary("13","Debug","2Mb",folderPath + "Slide_15_Debug.pdf","Kiểm Tra Lỗi"));
        javaLibraries.add(new JavaLibrary("14","Exception_Handling","2Mb",folderPath + "Slide_15_Exception_Handling.pdf","Xử Lý Ngoại Lệ trong Java"));
        javaLibraries.add(new JavaLibrary("15","IO_Text_File","2Mb",folderPath + "Slide_16_IO_Text_File.pdf","Giới Thiệu Java.IO"));
        javaLibraries.add(new JavaLibrary("16","Binary_File_and_Serialization","2Mb",folderPath + "Slide_17_Binary_File_and_Serialization.pdf","file Nhi Phân và  cơ chế Serialization "));

        FileReaderAndWriter<JavaLibrary> fileReaderAndWriterJava = new FileReaderAndWriter<>();
        fileReaderAndWriterJava.writeToFile(javaLibraries, "/src/library/java/JavaLibrary.txt");
    }
}
