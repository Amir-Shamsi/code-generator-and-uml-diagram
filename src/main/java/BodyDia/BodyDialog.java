package BodyDia;

import Convert.ClassInfo;
import com.jfoenix.controls.JFXDialog;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Region;

import javax.print.attribute.standard.MediaSize;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;


public class BodyDialog {

    public Region getView(ArrayList<ClassInfo> classes) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../BodyDialogView.fxml"));
        Region view = loader.load();
        ((BodyDialogViewFxml) loader.getController()).BodyDialogInitialize(this, classes);
        return view;
    }



    static void createBody(ArrayList<ClassInfo> myClasses, JFXDialog cDialog,
                            String nameB, String typeB, String accessB, String valueB,
                           Boolean staticB, Boolean allocateB, String className, String methodName) {
        ClassInfo.Method.Body bb = new ClassInfo.Method.Body();

        for(ClassInfo cc : myClasses){
            if (cc.cName.equals(className)){
                if(methodName != null){
                    if(!methodName.equals("*to class*")) {
                        for (ClassInfo.Method mm : cc.cMethods) {
                            if (mm.mName.equals(methodName)) {
                                mm.mBody = new ClassInfo.Method.Body();
                                mm.mBody.bName.add(nameB);
                                mm.mBody.bName.add(nameB);
                                mm.mBody.bType.add(typeB);
                                mm.mBody.bAccess.add(accessB);
                                if(valueB.equals(""))
                                    mm.mBody.bValue.add(null);
                                else
                                    mm.mBody.bValue.add(valueB);
                                if (staticB)
                                    mm.mBody.bStatic.add("static");
                                else
                                    mm.mBody.bStatic.add(null);
                                mm.mBody.bAllocate.add(allocateB);
                                break;
                            }
                        }
                    } else {
                        cc.cBody = new ClassInfo.Method.Body();
                        cc.cBody.bName.add(nameB);
                        cc.cBody.bType.add(typeB);
                        cc.cBody.bAccess.add(accessB);
                        if(valueB.equals(""))
                            cc.cBody.bValue.add(null);
                        else
                            cc.cBody.bValue.add(valueB);
                        if (staticB)
                            cc.cBody.bStatic.add("static");
                        else
                            cc.cBody.bStatic.add(null);
                        cc.cBody.bAllocate.add(allocateB);
                    }
                }
            }
        }
        cDialog.close();
    }

}
