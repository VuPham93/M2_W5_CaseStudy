package library.javaScript;

import tools.FileReaderAndWriter;

import java.util.ArrayList;

public class JavaScriptCreator {
    public static void main(String[] args) {
        ArrayList<JavaScriptLibrary> javaScriptLibraries = new ArrayList<>();
        String folderPath = "D:\\Downloads\\Codegym\\Slide\\Module1\\";
        javaScriptLibraries.add(new JavaScriptLibrary("1", "Pseudo Code & Flowchart", "2Mb", folderPath + "Slide01-PseudoCodeFlowchart.pdf", "Nhập môn lập trình căn bản"));
        javaScriptLibraries.add(new JavaScriptLibrary("2", "Git & HTML", "2Mb", folderPath + "Slide02-GitHTML.pdf", "Nhập môn lập trình căn bản"));
        javaScriptLibraries.add(new JavaScriptLibrary("3", "HTML Form & Table", "2Mb", folderPath + "Slide03-HTMLFormTable.pdf", "Nhập môn lập trình căn bản"));
        javaScriptLibraries.add(new JavaScriptLibrary("4", "JavaScript Overview", "2Mb", folderPath + "Slide04-JavaScriptOverview.pdf", "Nhập môn lập trình căn bản"));
        javaScriptLibraries.add(new JavaScriptLibrary("5", "Variable and Data Types", "2Mb", folderPath + "Slide05-VariableandDataTypes.pdf", "Nhập môn lập trình căn bản"));
        javaScriptLibraries.add(new JavaScriptLibrary("6", "ConditionalStatements", "2Mb", folderPath + "Slide_06_ConditionalStatements.pdf", "Nhập môn lập trình căn bản"));
        javaScriptLibraries.add(new JavaScriptLibrary("7","LoopStatements","2Mb",folderPath + "Slide_07_LoopStatements.pdf","Nhập môn lập trình căn bản"));
        javaScriptLibraries.add(new JavaScriptLibrary("8","Arrays","2Mb",folderPath + "Slide_08_Arrays.pdf","Nhập môn lập trình căn bản"));
        javaScriptLibraries.add(new JavaScriptLibrary("9","Function","2Mb",folderPath + "Slide_09_Function.pdf","Nhập môn lập trình căn bản"));
        javaScriptLibraries.add(new JavaScriptLibrary("10","Introduction_to_OOP","2Mb",folderPath + "Slide_10_Introduction_to_OOP.pdf","Nhập môn lập trình căn bản"));
        javaScriptLibraries.add(new JavaScriptLibrary("11","Objects_and_Classes","2Mb",folderPath + "Slide_11_Objects_and_Classes.pdf","Nhập môn lập trình căn bản"));

        FileReaderAndWriter<JavaScriptLibrary> fileReaderAndWriter = new FileReaderAndWriter<>();
        fileReaderAndWriter.writeToFile(javaScriptLibraries, "/src/library/javaScript/JavaScriptLibrary.txt");
    }
}
