package entity;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class HoaDon {

    private int maHD;
    private NhanVien nv;
    private KhachHang kh;
    private int soBan;
    private Timestamp ngayLap;
    private String trangThai;

    private double tongTien;
    private double thueVAT;
    private double chiPhiKhac;
    private double tongThu;

    public HoaDon(int maHD, NhanVien nv, KhachHang kh, int soBan, Timestamp ngayLap, String trangThai, double tongTien,
            double thueVAT, double chiPhiKhac, double tongThu) {
        super();
        this.maHD = maHD;
        this.nv = nv;
        this.kh = kh;
        this.soBan = soBan;
        this.ngayLap = ngayLap;
        this.trangThai = trangThai;
        this.tongTien = tongTien;
        this.thueVAT = thueVAT;
        this.chiPhiKhac = chiPhiKhac;
        this.tongThu = tongThu;
    }

    public HoaDon(int maHD, NhanVien nv, KhachHang kh, int soBan, String trangThai,
            double tongTien, double thueVAT, double chiPhiKhac, double tongThu) {
        super();
        this.maHD = maHD;
        this.nv = nv;
        this.kh = kh;
        this.trangThai = trangThai;
        this.tongTien = tongTien;
        this.thueVAT = thueVAT;
        this.chiPhiKhac = chiPhiKhac;
        this.tongThu = tongThu;
    }

    public HoaDon(NhanVien nv, KhachHang kh, int soBan, String trangThai,
            double tongTien, double thueVAT, double chiPhiKhac, double tongThu) {
        super();
        this.nv = nv;
        this.kh = kh;
        this.soBan = soBan;
        this.trangThai = trangThai;
        this.tongTien = tongTien;
        this.thueVAT = thueVAT;
        this.chiPhiKhac = chiPhiKhac;
        this.tongThu = tongThu;
    }

    public HoaDon() {
        // TODO Auto-generated constructor stub
    }

    public int getMaHD() {
        return maHD;
    }

    public void setMaHD(int maHD) {
        this.maHD = maHD;
    }

    public NhanVien getNv() {
        return nv;
    }

    public void setNv(NhanVien nv) {
        this.nv = nv;
    }

    public KhachHang getKh() {
        return kh;
    }

    public void setKh(KhachHang kh) {
        this.kh = kh;
    }

    public int getBan() {
        return soBan;
    }

    public void setBan(int soBan) {
        this.soBan = soBan;
    }

    public Timestamp getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(Timestamp ngayLap) {
        this.ngayLap = ngayLap;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

//	public List<ChiTietHoaDon> getHangmuc() {
//		return hangmuc;
//	}
//
//	public void setHangmuc(List<ChiTietHoaDon> hangmuc) {
//		this.hangmuc = hangmuc;
//	}
    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }

    public double getThueVAT() {
        return thueVAT;
    }

    public void setThueVAT(double thueVAT) {
        this.thueVAT = thueVAT;
    }

    public double getChiPhiKhac() {
        return chiPhiKhac;
    }

    public void setChiPhiKhac(double chiPhiKhac) {
        this.chiPhiKhac = chiPhiKhac;
    }

    public double getTongThu() {
        return tongThu;
    }

    public void setTongThu(double tongThu) {
        this.tongThu = tongThu;
    }

//	public void themChiTietHoaDon(ChiTietHoaDon chiTiet) {
//        if (hangmuc == null) {
//            hangmuc = new ArrayList<ChiTietHoaDon>();
//        }
//        hangmuc.add(chiTiet);
//    }
//	public boolean ktHM() {
//        return hangmuc != null && !hangmuc.isEmpty();
//    }
    @Override
    public String toString() {
        return "HoaDon [maHD=" + maHD + ", nv=" + nv + ", kh=" + kh + ", soBan=" + soBan + ", ngayLap=" + ngayLap
                + ", trangThai=" + trangThai + ", tongTien=" + tongTien + ", thueVAT="
                + thueVAT + ", chiPhiKhac=" + chiPhiKhac + ", tongThu=" + tongThu + "]";
    }
}
