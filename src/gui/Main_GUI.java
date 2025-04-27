package gui;

import database.ConnectDB;

public class Main_GUI {

    public static void main(String[] args) {
        try {
            ConnectDB.getInstance().connect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // new DangNhap_GUI();
        new TrangChu_GUI("CaSang");
    }
}
