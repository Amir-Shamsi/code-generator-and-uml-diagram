package MethodDia;

import Convert.ClassInfo;
import MainForm.MainView;
import com.jfoenix.controls.*;
import javafx.event.ActionEvent;
import javafx.scene.control.*;

import java.util.ArrayList;

public class MethodDialogViewFxml {
    public JFXComboBox<String> cbxAccess = new JFXComboBox<>();
    public Label lblName1;
    public JFXTextField txtName;
    public Label lblName;
    public Label lblTitle;
    public Label lblAccess;
    public Label lblParent;
    public JFXButton btnDone;
    public JFXButton btnCancel;
    public JFXCheckBox CheckBAbstract;
    public JFXCheckBox CheckBStatic;
    public JFXComboBox<String> cbxClassName = new JFXComboBox<>();
    public Label lblName2;
    public JFXTextField txtParam;
    public JFXTextField txtType;
    private MethodDialog methodDialog;
    private static JFXDialog mDialog;
    private ArrayList<ClassInfo> myClasses;

    public void MethodDialogInitialize(MethodDialog methodDialog, ArrayList<ClassInfo> classes) {
        this.myClasses = classes;
        this.methodDialog = methodDialog;
        cbxAccess.getItems().add("protected");
        cbxAccess.getItems().add("public");
        cbxAccess.getItems().add("private");

        for(ClassInfo item: classes) {
            cbxClassName.getItems().add(item.cName);
        }
    }

    public void cancelAction(ActionEvent actionEvent) {
        mDialog.close();
        System.out.println("-method dialog process canceled");
    }
    public static void setDialog(JFXDialog myDialog){
        mDialog = myDialog;
    }

    public void collectMethod(ActionEvent actionEvent) {
        MethodDialog.createMethod(myClasses, mDialog, (String) cbxClassName.getValue(),CheckBAbstract.isSelected(),
               CheckBStatic.isSelected(), txtName.getText(), (String) cbxAccess.getValue(), txtType.getText(),
                txtParam.getText());
        MainView.createMethodAndBodyView();
        System.out.println("-method dialog process done");
    }

}
