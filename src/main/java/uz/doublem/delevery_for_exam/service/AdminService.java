package uz.doublem.delevery_for_exam.service;

public class AdminService {




    private static AdminService adminService;

    public static AdminService getInstance() {
        if (adminService == null)
            synchronized (AdminService.class) {
                if (adminService == null) {
                    adminService = new AdminService();
                }
            }
        return adminService;
    }

}
