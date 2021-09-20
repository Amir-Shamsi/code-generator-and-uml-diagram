package Convert;

import java.util.ArrayList;

public class JavaConvertor extends Structure {
    private static String code = "";
    public ArrayList<String> cImplements = new ArrayList<String>();
    public boolean cAbstract;


    public JavaConvertor(ArrayList<ClassInfo> classesInfo) {
        classInfo = classesInfo;
        if (classInfo != null) {
            engine();
            return;
        }
        error("No INFO to build code");
    }

    public String getCode() {
        return code;
    }

    @Override
    protected void engine() {
        classInfo.forEach(this::addClass);
    }
    @Override
    void addClass(ClassInfo cls) {
        if (cls.cAbstract)
            code += "abstract ";
        code += cls.cAccess + ' ' + CLASS + ' ' + cls.cName;
        if (cls.cParent != null) {
            code += " extends " + cls.cParent.cName;
        }
        code += "{\n";
        if(cls.cBody != null){
            addBody(cls.cBody, true);
        }
        if (cls.cMethods != null) {
            cls.cMethods.forEach(this::addMethod);
        }
        code += "}\n";

    }
    @Override
    void addMethod(Method met) {
        code += '\t';
        if(met.mAbstract)
            code += "abstract ";
        if (met.mAccess != null){
            code += met.mAccess + ' ';
        }
        code += met.mType + ' ' + met.mName + '(';
        if (met.mParameters != null) {
            code += met.mParameters;
        }
        code += ") {\n";
        if (met.mBody != null) {
            addBody(met.mBody, false);
        }
        code += "\t}\n";
    }

    @Override
    void addBody(Method.Body bd, boolean status) {
        for (int index = 0; index < bd.bAccess.size(); index++) {
            code += '\t';
            if(status && bd.bAccess.get(index) != null) {
                code += bd.bAccess.get(index) + ' ';
            }
            if(!status){
                code += "\t";
            }
                if(bd.bStatic.get(index) != null && status){
                code += bd.bStatic.get(index) + ' ';
            }
            code += bd.bType.get(index) + ' ' + bd.bName.get(index);
            if (bd.bAllocate.get(index)){
                code += " = new " + bd.bType.get(index) + "()";
            }else {
                if (bd.bValue.get(index) != null) {
                    code += " = ";
                    if (bd.bType.get(index).equals("String") || bd.bType.get(index).equals("char")) {
                        code += '"' + bd.bValue.get(index) + '"';
                    } else {
                        code += bd.bValue.get(index);
                    }
                }
            }
            code += ";\n";
        }
    }
    @Override
    protected void error(Object err) {

    }

}
