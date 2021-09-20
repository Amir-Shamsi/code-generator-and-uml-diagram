package ClassDia;
import Convert.ClassInfo;
import MainForm.MainView;
import com.jfoenix.controls.*;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTreeCell;

import java.util.ArrayList;

public class ClassDialogViewFxml {
    public Label lblName1;
    private ArrayList<ClassInfo> myClasses;
    public JFXComboBox<String> cbxAccess = new JFXComboBox<>();
    public JFXTextField txtName;
    public Label lblName;
    public Label lblTitle;
    public TreeView<String> ChoiceBParents = new TreeView<>();
    public Label lblParent;
    public JFXButton btnDone;
    public JFXButton btnCancel;
    public JFXCheckBox CheckBAbstract;
    private ClassDialog classDialog;
    private static JFXDialog cDialog;


    public void MethodDialogInitialize(ClassDialog classDialog, ArrayList<ClassInfo> classes) {
        this.classDialog = classDialog;
        this.myClasses = classes;
        cbxAccess.getItems().add("protected");
        cbxAccess.getItems().add("public");
        cbxAccess.getItems().add("private");
        CheckBoxTreeItem<String> rootItem = new CheckBoxTreeItem<>("No Select");
        final ArrayList<CheckBoxTreeItem<String>> treeItems = new ArrayList<>(6);
        for (ClassInfo itm : classes) {
            CheckBoxTreeItem<String> item = new CheckBoxTreeItem<>(itm.cName);
            item.setIndependent(true);
            treeItems.add(item);
        }
        rootItem.getChildren().addAll(treeItems);
        rootItem.setExpanded(true);
        rootItem.setIndependent(true);
        ChoiceBParents.setRoot(rootItem);
        ChoiceBParents.setCellFactory((TreeView<String> item) -> {
            final CheckBoxTreeCell<String> cell = new CheckBoxTreeCell<>();
            cell.itemProperty().addListener((obs,s,s1)->{
                cell.disableProperty().unbind();
            });
            return cell;
        });
    }

    public void cancelAction(ActionEvent actionEvent) {
        cDialog.close();
        System.out.println("-class dialog process canceled");
    }
    private void findCheckedItems(CheckBoxTreeItem<?> item, ArrayList<String> parents) {
        if (item.isSelected()) {
            parents.add((String) item.getValue());
        }
        for (TreeItem<?> child : item.getChildren()) {
            findCheckedItems((CheckBoxTreeItem<?>) child, parents);
        }
    }
    public static void setDialog(JFXDialog myDialog){
        cDialog = myDialog;
    }
    public void collectClass(ActionEvent actionEvent) {
        ArrayList<String> parents = new ArrayList<>();
        findCheckedItems((CheckBoxTreeItem<?>) ChoiceBParents.getRoot(), parents);

        ClassDialog.createClass(myClasses, cDialog, CheckBAbstract.isSelected(),
                txtName.getText(), (String)cbxAccess.getValue(), parents);
        MainView.createClassView(myClasses);
        System.out.println("-class dialog process done");
    }
}
