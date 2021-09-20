package MethodDia;

import Convert.ClassInfo;
import com.jfoenix.controls.JFXDialog;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Region;
import java.io.IOException;
import java.util.ArrayList;


public class MethodDialog {
    private ArrayList<ClassInfo> myClasses;
    public Region getView(ArrayList<ClassInfo> classes) throws IOException {
        this.myClasses = classes;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../MethodDialogView.fxml"));
        Region view = loader.load();
        ((MethodDialogViewFxml) loader.getController()).MethodDialogInitialize(this, myClasses);
        return view;
    }



    static void createMethod(ArrayList<ClassInfo> myClasses, JFXDialog cDialog, String className,
                            boolean abstractM, boolean staticM, String name,
                             String access, String typeM, String parameters) {
        ClassInfo.Method method = new ClassInfo.Method();
        method.mAccess = access;
        method.mName = name;
        method.mParameters = parameters;
        method.mAbstract = abstractM;
        method.mStatic = staticM;
        method.mType = typeM;
        for (ClassInfo cls : myClasses){
            if(cls.cName.equals(className)){
                if(abstractM)
                    cls.cAbstract = true;
                cls.cMethods.add(method);
                break;
            }
        }
        cDialog.close();
    }

}
