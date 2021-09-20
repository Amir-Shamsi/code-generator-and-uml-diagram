package Convert;

import java.util.ArrayList;

public class ClassInfo {
    public ArrayList<Method> cMethods = new ArrayList<Method>();
    public ArrayList<ClassInfo> cParents = new ArrayList<ClassInfo>();
    public ArrayList<ClassInfo> cChildren = new ArrayList<ClassInfo>();
    public ArrayList<String> cPaAccess = new ArrayList<String>();
    public ClassInfo cParent;
    public Method.Body cBody = new Method.Body();
    public String cName;
    public String cAccess;
    public Boolean cAbstract;

    public static class Method {
        public Body mBody = new Body();
        public String mName;
        public Boolean mStatic;
        public String mType;
        public String mAccess;
        public String mParameters;
        public Boolean mAbstract;

        public static class Body {
            public ArrayList<Boolean> bAllocate = new ArrayList<Boolean>();
            public ArrayList<String> bName = new ArrayList<String>();
            public ArrayList<String> bType = new ArrayList<String>();
            public ArrayList<String> bAccess = new ArrayList<String>();
            public ArrayList<String> bValue = new ArrayList<String>();
            public ArrayList<String> bStatic = new ArrayList<String>();
        }
    }
}
