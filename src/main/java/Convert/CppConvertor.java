package Convert;

import java.util.ArrayList;

public class CppConvertor extends Structure {
    private static String code = "";

    public CppConvertor(ArrayList<ClassInfo> classesInfo) {
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
        code += "#include <iostream>\nusing namespace std;\n";
        classInfo.forEach(this::addClass);
    }

    @Override
    void addClass(ClassInfo cls) {
        code += CLASS + ' ' + cls.cName;
        if (cls.cParents != null) {
            code += " : ";
            for (int index = 0; index < cls.cParents.size(); index++) {
                if (cls.cPaAccess.get(index) != null) {
                    code += cls.cPaAccess.get(index) + ' ';
                }
                code += cls.cParents.get(index).cName;
                if (index != cls.cParents.size() - 1) {
                    code += ", ";
                }
            }
        }
        code += "{\n";
        if (cls.cBody != null) {
            addBody(cls.cBody, true);
        }
        if (cls.cMethods != null) {
            cls.cMethods.forEach(this::addMethod);
        }
        code += "};\n";
    }

    @Override
    void addMethod(Method met) {
        code += '\t';
        if (met.mAccess == null) {
            code += "private:";
        } else {
            code += met.mAccess + ":";
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
            if (status && bd.bAccess.get(index) != null) {
                code += bd.bAccess.get(index) + ":";
            } else if (status && bd.bAccess.get(index) == null) {
                code += "private:";
            }
            if (!status) {
                code += "\t";
            }
            code += bd.bType.get(index) + ' ' + bd.bName.get(index);
            if (bd.bAllocate.get(index)){
                code += " = new " + bd.bType.get(index) + "()";
            }else {
                if (bd.bValue.get(index) != null) {
                    code += " = ";
                    if (bd.bType.get(index).equals("string") || bd.bType.get(index).equals("char")) {
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
