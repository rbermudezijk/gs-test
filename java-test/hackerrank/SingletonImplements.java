class SingletonImplements {
    static SingletonImplements myclass;

    static {
        myclass = new SingletonImplements();
    }

    private SingletonImplements() {
    }

    public static SingletonImplements getInstance() {
        return myclass;
    }
}

class MyClass2 {
    private static final MyClass2 myClass = new MyClass2();

    private MyClass2() {
    }

    public static MyClass2 getInstance() {
        return myClass;
    }
}

class MyClass3 {
    private MyClass3() {
    }

    private static class LazyHolder {
        private static final MyClass3 myClass = new MyClass3();
    }

    public static MyClass3 getInstance() {
        return LazyHolder.myClass;
    }
}

class MyClass4 {
    private static MyClass4 singletonInstance;

    public static MyClass4 getInstance() {
        if (singletonInstance == null) {
            singletonInstance = new MyClass4();
        }
        return singletonInstance;
    }
}