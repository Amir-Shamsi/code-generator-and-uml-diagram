package MainForm;

import Convert.ClassInfo;
import com.jfoenix.controls.JFXTextArea;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;

import java.util.ArrayList;

public class MainView {
    private static String code = "THE LISTS OF CLASSES AND METHOD & etc.\n\n";
    private static JFXTextArea textArea;
    private static AnchorPane anchorPane;
    private static int index = 0, index2 = 0;
    private static double width1 = 40, width2 = 40, width3 = 40;

    private static ArrayList<ClassInfo> fParent = new ArrayList<>();
    private static ArrayList<ClassInfo> sParent = new ArrayList<>();
    private static ArrayList<ClassInfo> tParent = new ArrayList<>();
    private static ArrayList<ClassInfo> myClasses;

    public static void setTextArea(JFXTextArea myText){
        textArea = myText;
    }
    public static void setAnchorPane(AnchorPane anchor){
        anchorPane = anchor;
    }
    public static void createClassView(ArrayList<ClassInfo> classes) {
        myClasses = classes;
        String text = "";
        int status = -1;
        if (classes.get(index).cAbstract) {
            text += "<Abstract>";
        }
        text += classes.get(index).cName + '\n' + "----------------------\n";
        Label lbl = new Label(text);
        lbl.setFont(Font.font(13));
        lbl.setPrefHeight(150);
        lbl.setPrefWidth(150);
        lbl.setAlignment(Pos.TOP_CENTER);
        lbl.setStyle("-fx-background-color: #fff999;");
        lbl.setWrapText(true);

        if (index < 5) {
            lbl.setLayoutX(width1);
            width1 += 200;
            lbl.setLayoutY(50);
            anchorPane.getChildren().add(lbl);
            fParent.add(classes.get(index));
            if(classes.get(index).cParents != null) {
                for (ClassInfo itm : classes.get(index).cParents) {

                    for (int count2 = 0; count2 < 5; count2++) {
                        try {
                            if (fParent.get(count2).cName.equals(itm.cName)) {
                                status = 1;
                            }
                        } catch (Exception ignored) {
                        }
                        if (status != -1) {
                            double fPoint = 40 + (double) (count2) * 200 + 75;
                            double sPoint = (width1) - 120;
                            Line line = new Line();
                            line.setStartX(fPoint);
                            line.setEndX(sPoint);

                            line.setStartY(200);

                            line.setEndY(50);
                            line.setStroke(Color.rgb(0, 239, 6));
                            anchorPane.getChildren().add(line);
                            status = -1;
                        }
                    }
                }
            }
            index++;
        }
        else if (index < 10) {
            sParent.add(classes.get(index));
            lbl.setLayoutX(width2);
            width2 += 200;
            lbl.setLayoutY(280);
            anchorPane.getChildren().add(lbl);
            if(classes.get(index).cParents != null) {

                for (ClassInfo itm : classes.get(index).cParents) {
                    for (int count2 = 0; count2 < 5; count2++) {
                        try {
                            if (sParent.get(count2).cName.equals(itm.cName)) {
                                status = 2;
                            }
                        } catch (Exception ignored) {
                        }
                        if (fParent.get(count2).cName.equals(itm.cName)) {
                            status = 1;
                        }
                        if (status != -1) {
                            double fPoint = 40 + (double) (count2) * 200 + 75;
                            double sPoint = (width2) - 120;
                            Line line = new Line();
                            line.setStartX(fPoint);
                            line.setEndX(sPoint);

                            switch (status) {
                                case 1:
                                    line.setStartY(200);
                                    break;
                                case 2:
                                    line.setStartY(430);
                                    break;
                                case 3:
                                    line.setStartY(860);
                                    break;
                            }
                            line.setEndY(280);
                            line.setStroke(Color.rgb(0, 239, 6));
                            anchorPane.getChildren().add(line);
                        }
                        status = -1;
                    }
                }
            }
            index++;
        }
        else if (index < 15) {
            lbl.setLayoutX(width3);
            width3 += 200;
            tParent.add(classes.get(index));
            lbl.setLayoutY(560);
            anchorPane.getChildren().add(lbl);
            if(classes.get(index).cParents != null) {

                for (ClassInfo itm : classes.get(index).cParents) {
                    for (int count2 = 0; count2 < 5; count2++) {
                        try {
                            if (tParent.get(count2).cName.equals(itm.cName)) {
                                status = 3;
                            }
                        } catch (Exception ignored) {
                        }
                        if (sParent.get(count2).cName.equals(itm.cName)) {
                            status = 2;
                        }
                        if (fParent.get(count2).cName.equals(itm.cName)) {
                            status = 1;
                        }
                        if (status != -1) {
                            double fPoint = 40 + (double) (count2) * 200 + 75;
                            double sPoint = (width3) - 120;
                            Line line = new Line();
                            line.setStartX(fPoint);
                            line.setEndX(sPoint);

                            switch (status) {
                                case 1:
                                    line.setStartY(200);
                                    break;
                                case 2:
                                    line.setStartY(430);
                                    break;
                                case 3:
                                    line.setStartY(860);
                                    break;
                            }
                            line.setEndY(560);
                            line.setStroke(Color.rgb(0, 239, 6));
                            anchorPane.getChildren().add(line);
                        }
                        status = -1;
                    }
                }
            }
            index++;
        }

    }
    public static void createMethodAndBodyView() {
        index2 = 0;
        double mwidth1 = 40, mwidth2 = 40, mwidth3 = 40;
        for (ClassInfo cc : myClasses) {
            String text = "";
            int status = -1;
            if (cc.cAbstract) {
                text += "<Abstract>";
            }
            text += cc.cName + '\n' + "----------------------\n";
            if(cc.cBody != null) {
                for (int g = 0; g < cc.cBody.bName.size(); g++) {
                    text += "-" + cc.cBody.bAccess.get(g) + ' ' + cc.cBody.bType.get(g) + ' '
                            + cc.cBody.bName.get(g) + '\n';
                }
            }
            for (ClassInfo.Method mm : cc.cMethods) {
                text += '-' + mm.mType + ' ' + mm.mName + "(" + mm.mParameters + ")\n";
                if (mm.mBody != null) {
                    for (int g = 0; g < mm.mBody.bAccess.size(); g++) {
                        text += "--" + mm.mBody.bAccess.get(g) + ' ' + mm.mBody.bType.get(g) + ' '
                                + mm.mBody.bName.get(g) + '\n';
                    }
                }
            }
            Label lbl = new Label(text);
            lbl.setFont(Font.font(13));
            lbl.setPrefHeight(150);
            lbl.setPrefWidth(150);
            lbl.setAlignment(Pos.TOP_CENTER);
            lbl.setStyle("-fx-background-color: #fff999;");
            lbl.setWrapText(true);

            if (index2 < 5) {
                lbl.setLayoutX(mwidth1);
                mwidth1 += 200;
                lbl.setLayoutY(50);
                anchorPane.getChildren().add(lbl);
                fParent.add(cc);
                if(myClasses.get(index2).cParents != null) {
                    for (ClassInfo itm : cc.cParents) {

                        for (int count2 = 0; count2 < 5; count2++) {
                            try {
                                if (fParent.get(count2).cName.equals(itm.cName)) {
                                    status = 1;
                                }
                            } catch (Exception ignored) {
                            }
                            if (status != -1) {
                                double fPoint = 40 + (double) (count2) * 200 + 75;
                                double sPoint = (mwidth1) - 120;
                                Line line = new Line();
                                line.setStartX(fPoint);
                                line.setEndX(sPoint);

                                line.setStartY(200);

                                line.setEndY(50);
                                line.setStroke(Color.rgb(0, 239, 6));
                                anchorPane.getChildren().add(line);
                                status = -1;
                            }
                        }
                    }
                }
                index2++;

            } else if (index2 < 10) {
                sParent.add(cc);
                lbl.setLayoutX(mwidth2);
                mwidth2 += 200;
                lbl.setLayoutY(280);
                anchorPane.getChildren().add(lbl);
                if(myClasses.get(index2).cParents != null) {

                    for (ClassInfo itm : cc.cParents) {
                        for (int count2 = 0; count2 < 5; count2++) {
                            try {
                                if (sParent.get(count2).cName.equals(itm.cName)) {
                                    status = 2;
                                }
                            } catch (Exception ignored) {
                            }
                            if (fParent.get(count2).cName.equals(itm.cName)) {
                                status = 1;
                            }
                            if (status != -1) {
                                double fPoint = 40 + (double) (count2) * 200 + 75;
                                double sPoint = (mwidth2) - 120;
                                Line line = new Line();
                                line.setStartX(fPoint);
                                line.setEndX(sPoint);

                                switch (status) {
                                    case 1:
                                        line.setStartY(200);
                                        break;
                                    case 2:
                                        line.setStartY(430);
                                        break;
                                    case 3:
                                        line.setStartY(860);
                                        break;
                                }
                                line.setEndY(280);
                                line.setStroke(Color.rgb(0, 239, 6));
                                anchorPane.getChildren().add(line);
                            }
                            status = -1;
                        }
                    }
                }
                index2++;
            } else if (index2 < 15) {
                lbl.setLayoutX(mwidth3);
                mwidth3 += 200;
                tParent.add(cc);
                lbl.setLayoutY(560);
                anchorPane.getChildren().add(lbl);
                if(myClasses.get(index2).cParents != null) {

                    for (ClassInfo itm : cc.cParents) {
                        for (int count2 = 0; count2 < 5; count2++) {
                            try {
                                if (tParent.get(count2).cName.equals(itm.cName)) {
                                    status = 3;
                                }
                            } catch (Exception ignored) {
                            }
                            if (sParent.get(count2).cName.equals(itm.cName)) {
                                status = 2;
                            }
                            if (fParent.get(count2).cName.equals(itm.cName)) {
                                status = 1;
                            }
                            if (status != -1) {
                                double fPoint = 40 + (double) (count2) * 200 + 75;
                                double sPoint = (width3) - 120;
                                Line line = new Line();
                                line.setStartX(fPoint);
                                line.setEndX(sPoint);

                                switch (status) {
                                    case 1:
                                        line.setStartY(200);
                                        break;
                                    case 2:
                                        line.setStartY(430);
                                        break;
                                    case 3:
                                        line.setStartY(860);
                                        break;
                                }
                                line.setEndY(560);
                                line.setStroke(Color.rgb(0, 239, 6));
                                anchorPane.getChildren().add(line);
                            }
                            status = -1;
                        }
                    }
                }
                index2++;
            }

        }
    }
}
