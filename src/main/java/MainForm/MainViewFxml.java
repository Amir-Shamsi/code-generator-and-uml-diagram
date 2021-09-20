package MainForm;

import BodyDia.BodyDialog;
import BodyDia.BodyDialogViewFxml;
import ClassDia.ClassDialog;
import ClassDia.ClassDialogView;
import ClassDia.ClassDialogViewFxml;
import Convert.ClassInfo;
import Convert.CppConvertor;
import Convert.JavaConvertor;
import MethodDia.MethodDialog;
import MethodDia.MethodDialogViewFxml;
import com.jfoenix.controls.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.*;
import javafx.scene.layout.StackPane;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class MainViewFxml {
    ArrayList<ClassInfo> myClasses = new ArrayList<>();
    public JFXTextArea textArea;
    Main myMain;
    static StackPane cRoot, mRoot, bRoot;
    private static JFXDialog cDialog, mDialog, bDialog;
    private FXMLLoader loader;
    public JFXButton btnClass;
    public JFXButton btnFunc;
    public JFXButton btnArg;
    public JFXComboBox<String> cbxCho = new JFXComboBox<>();
    public JFXCheckBox cheboxSave;
    public JFXButton btnExp;

    public static void MethodRootInitialize(StackPane root) {
        mRoot = root;
    }
    public static void BodyRootInitialize(StackPane root) throws IOException {
        bRoot = root;
    }
    public static void classRootInitialize(StackPane root) {
        cRoot = root;
    }
    public void setMain(Main myMain, FXMLLoader loader){
        this.myMain = myMain;
        cbxCho.getItems().addAll("All", "C++", "Java");
        MainView.setTextArea(textArea);
        this.loader = loader;
    }


    public void pWhenDropped(DragEvent dragEvent) throws IOException {
        MainView.setTextArea(textArea);
        Dragboard dragboard = dragEvent.getDragboard();
        dragEvent.setDropCompleted(true);
        switch (dragboard.getString()) {
            case "Class":

                JFXDialog cDialog = new JFXDialog(cRoot, new ClassDialog().getView(myClasses), JFXDialog.DialogTransition.CENTER);
                ClassDialogViewFxml.setDialog(cDialog);
                cDialog.show();
                break;
            case "Method":
                JFXDialog mDialog = new JFXDialog(mRoot, new MethodDialog().getView(myClasses), JFXDialog.DialogTransition.CENTER);
                MethodDialogViewFxml.setDialog(mDialog);
                mDialog.show();
                break;
            case "Body":
                JFXDialog bDialog = new JFXDialog(bRoot, new BodyDialog().getView(myClasses), JFXDialog.DialogTransition.CENTER);
                BodyDialogViewFxml.setDialog(bDialog);
                bDialog.show();
                break;
        }
    }

    public void pDragOver(DragEvent dragEvent) {
        dragEvent.acceptTransferModes(TransferMode.ANY);
    }

    public void cDragDetect(MouseEvent mouseEvent) {
        Dragboard dragboard = btnClass.startDragAndDrop(TransferMode.ANY);
        ClipboardContent content = new ClipboardContent();
        content.putString("Class");
        dragboard.setContent(content);
    }

    public void mDragDetect(MouseEvent mouseEvent) {
        Dragboard dragboard = btnFunc.startDragAndDrop(TransferMode.ANY);
        ClipboardContent content = new ClipboardContent();
        content.putString("Method");
        dragboard.setContent(content);
    }

    public void bDragDetect(MouseEvent mouseEvent) {
        Dragboard dragboard = btnArg.startDragAndDrop(TransferMode.ANY);
        ClipboardContent content = new ClipboardContent();
        content.putString("Body");
        dragboard.setContent(content);
    }

    public void exportCode(ActionEvent actionEvent) throws IOException {
        if (cheboxSave.isSelected()){
            CppConvertor cppConvertor = new CppConvertor(myClasses);
            JavaConvertor javaConvertor = new JavaConvertor(myClasses);
            if (cbxCho.getValue().equals("C++")) {
                System.out.println("******************* C++ CODE *******************");
                FileWriter file = new FileWriter("code-in-cpp.cpp");
                file.write(cppConvertor.getCode());
                System.out.println(cppConvertor.getCode());
            } else if(cbxCho.getValue().equals("Java")){
                System.out.println("******************* JAVA CODE ******************");
                FileWriter file = new FileWriter("code-in-java.java");
                file.write(javaConvertor.getCode());
                System.out.println(javaConvertor.getCode());
            }
            else if (cbxCho.getValue().equals("All")){
                System.out.println("******************* C++ CODE *******************");
                FileWriter cFile = new FileWriter("code-in-cpp.cpp");
                System.out.println(cppConvertor.getCode());
                System.out.println("******************* JAVA CODE ******************");
                FileWriter jFile = new FileWriter("code-in-java.java");
                System.out.println(javaConvertor.getCode());
                cFile.write(cppConvertor.getCode());
                jFile.write(javaConvertor.getCode());
            }
        }

    }
}
