package uz.doublem.delevery_for_exam.contrloller.utilConfig;


import uz.doublem.delevery_for_exam.entity.Users;

public class Context {
    static ThreadLocal<Users> userThreadLocal = new ThreadLocal<>();
    private static Users user;
    public static Users getUser() {
        return userThreadLocal.get();
    }
    public static void setUser(Users user) {
        userThreadLocal.set(user);
    }
}
