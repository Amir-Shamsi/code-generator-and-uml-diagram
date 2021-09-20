package BodyDia;

import Convert.ClassInfo;
import MainForm.MainView;
import com.jfoenix.controls.*;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

public class BodyDialogViewFxml {
    public JFXCheckBox CheckBAllocate;
    private ArrayList<ClassInfo> myClasses;
    public Label lblName1;
    public JFXTextField txtName;
    public Label lblName;
    public Label lblTitle;
    public JFXButton btnDone;
    public JFXButton btnCancel;
    public JFXCheckBox CheckBStatic;
    public JFXTextField txtValue;
    public Label lblName11;
    public JFXComboBox<String> cbxAccess = new JFXComboBox<>();
    public Label lblAccess;
    public JFXTextField txtType;
    public Label lblAccess11;
    public Label lblAccess1;
    public JFXComboBox<String> cbxClass = new JFXComboBox<>();
    public JFXComboBox<String> cbxMethod = new JFXComboBox<>();
    private BodyDialog bodyDialog;
    private static JFXDialog bDialog;
    private static String classChange = " ";

    public void BodyDialogInitialize(BodyDialog bodyDialog, ArrayList<ClassInfo> classes) {
        this.bodyDialog = bodyDialog;
        this.myClasses = classes;
        cbxAccess.getItems().add("protected");
        cbxAccess.getItems().add("public");
        cbxAccess.getItems().add("private");

        for(ClassInfo item: classes){
            cbxClass.getItems().add(item.cName);
        }
    }

    public void cancelAction(ActionEvent actionEvent) {
        bDialog.close();
        System.out.println("-body dialog process canceled");
        classChange = " ";
    }
    public static void setDialog(JFXDialog myDialog){
        bDialog = myDialog;
    }

    public void collectBody(ActionEvent actionEvent) {
        BodyDialog.createBody(myClasses, bDialog, txtName.getText(), txtType.getText(), cbxAccess.getValue()
                , txtValue.getText(), CheckBStatic.isSelected(), CheckBAllocate.isSelected(),
                cbxClass.getValue(), cbxMethod.getValue());
        MainView.createMethodAndBodyView();
        System.out.println("-body dialog process done");
        classChange = " ";
    }

    public void cbxMethodInitialize(MouseEvent actionEvent) {
        if(classChange != null) {
            if (!classChange.equals(cbxClass.getValue())) {
                cbxMethod.getItems().clear();
                classChange = cbxClass.getValue();
                cbxMethod.getItems().add("*to class*");
                for (ClassInfo item : myClasses) {
                    if (item.cName.equals(cbxClass.getValue())) {
                        for (ClassInfo.Method mm : item.cMethods) {
                            cbxMethod.getItems().add(mm.mName);
                        }
                    }
                }
            }
        }
    }
}
