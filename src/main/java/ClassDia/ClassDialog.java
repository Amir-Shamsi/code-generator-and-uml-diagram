package ClassDia;

import Convert.ClassInfo;
import com.jfoenix.controls.JFXDialog;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Region;
import java.io.IOException;
import java.util.ArrayList;


public class ClassDialog {
    private ArrayList<ClassInfo> myClasses;
    public Region getView(ArrayList<ClassInfo> classes) throws IOException {
        this.myClasses = classes;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../ClassDialogView.fxml"));
        Region view = loader.load();
        ((ClassDialogViewFxml) loader.getController()).MethodDialogInitialize(this, myClasses);
        return view;
    }



    static void createClass(ArrayList<ClassInfo> myClasses, JFXDialog cDialog,
                            boolean abstractClass, String name, String access, ArrayList<String> parents) {
        ArrayList<ClassInfo> par = new ArrayList<>();
        ClassInfo classInfo = new ClassInfo();
        classInfo.cAbstract = abstractClass;
        classInfo.cName = name;
        classInfo.cAccess = access;
        classInfo.cBody = null;
        for(String item: parents){
            for (ClassInfo cls: myClasses){
                if(item.equals(cls.cName)){
                    par.add(cls);
                    cls.cChildren.add(classInfo);
                    break;
                }
            }
        }
        if (par.size() == 0)
            classInfo.cParents = null;
        else {
            classInfo.cParents = par;
            classInfo.cParent = par.get(0);
        }
        myClasses.add(classInfo);
        cDialog.close();
    }

}
