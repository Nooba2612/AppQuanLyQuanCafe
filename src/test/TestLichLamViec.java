package test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import dao.CaLamViec_DAO;
import dao.LichLamViec_DAO;
import dao.NhanVien_DAO;
import database.ConnectDB;
import entity.CaLamViec;
import entity.LichLamViec;
import entity.NhanVien;

public class TestLichLamViec {
	private static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		try {
			ConnectDB.getInstance().connect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		LichLamViec_DAO llv_dao = new LichLamViec_DAO();
		NhanVien_DAO nv_dao = new NhanVien_DAO();
		CaLamViec_DAO clv_dao = new CaLamViec_DAO(); 
		
		boolean flag = true;
		int choice;
		
		do {
			System.out.println("\n-----------------------------");
			System.out.println("1. In danh sách lịch làm việc");
			System.out.println("2. Thêm lịch làm việc");
			System.out.println("3. Xóa lịch làm việc");
			System.out.println("4. Cập nhật lịch làm việc");
			System.out.println("5. Tìm lịch làm việc theo nhân viên");
			System.out.println("6. Tìm lịch làm việc theo ngày");
			System.out.println("7. Tìm lịch làm việc theo ca");
			System.out.println("Lựa chọn: ");
			choice = sc.nextInt();
			switch (choice) {
//			In danh sách lịch làm việc
			case 1: {
				ArrayList<LichLamViec> dsLLV = llv_dao.getDSLichLamViec();
				System.out.println(dsLLV);
				break;
			}
//			Thêm lịch làm việc
			case 2: {
				ArrayList<LichLamViec> dsLLV = llv_dao.getDSLichLamViec();
				sc.nextLine();
				System.out.println("Nhập mã nhân viên: ");
				String maNV = sc.nextLine();
				ArrayList<NhanVien> dsnv = nv_dao.getDSNhanVien();
				NhanVien nv = dsnv.stream().filter(x -> x.getMaNV().equalsIgnoreCase(maNV)).findFirst().orElse(null);
				if(nv!=null) {
					System.out.println("Nhập mã ca làm việc: ");
					int maCa = sc.nextInt();
					ArrayList<CaLamViec> dsCa = clv_dao.dsca();
					CaLamViec ca = dsCa.stream().filter(x -> x.getMaCa()==maCa).findFirst().orElse(null);
					if(ca!=null) {
						sc.nextLine();
						System.out.println("Nhập ngày làm việc (dd/MM/yyyy): ");
						String s = sc.nextLine();
						DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
						LocalDate ngayLamViec = LocalDate.parse(s, dtf);
						LichLamViec llv = dsLLV.stream()
								.filter(x -> x.getNv().getMaNV().equalsIgnoreCase(maNV) 
										&& x.getCa().getMaCa()==maCa 
										&& x.getNgayLamViec().equals(ngayLamViec))
								.findFirst().orElse(null);
						if(llv!=null)
							System.out.println("Lịch này đã tồn tại");
						else
							llv_dao.themLichLamViec(maNV, maCa, ngayLamViec);
					}
					else
						System.out.println("Không có ca mang mã " + maCa);
				}
				else
					System.out.println("Không có nhân viên mang mã " + maNV);
				break;
			}
//			Xóa lịch làm việc
			case 3: {
				System.out.println("Nhập mã lịch làm việc: ");
				int maLLV = sc.nextInt();
				ArrayList<LichLamViec> dsLLV = llv_dao.getDSLichLamViec();
				LichLamViec llv = dsLLV.stream().filter(x -> x.getMaLLV()==maLLV).findFirst().orElse(null);
				if(llv!=null)
					llv_dao.xoaLichLamViec(maLLV);
				else
					System.out.println("Có lịch nào mang mã " + maLLV + " đâu mà xóa :))))");
				break;
			}
//			Cập nhật lịch làm việc
			case 4: {
				System.out.println("Nhập mã lịch làm việc: ");
				int maLLV = sc.nextInt();
				ArrayList<LichLamViec> dsLLV = llv_dao.getDSLichLamViec();
				LichLamViec llv = dsLLV.stream().filter(x -> x.getMaLLV()==maLLV).findFirst().orElse(null);
				if(llv!=null) {
					sc.nextLine();
					System.out.println("Nhập mã nhân viên: ");
					String maNV = sc.nextLine();
					ArrayList<NhanVien> dsnv = nv_dao.getDSNhanVien();
					NhanVien nv = dsnv.stream().filter(x -> x.getMaNV().equalsIgnoreCase(maNV)).findFirst().orElse(null);
					if(nv!=null) {
						System.out.println("Nhập mã ca làm việc: ");
						int maCa = sc.nextInt();
						ArrayList<CaLamViec> dsCa = clv_dao.dsca();
						CaLamViec ca = dsCa.stream().filter(x -> x.getMaCa()==maCa).findFirst().orElse(null);
						if(ca!=null) {
							sc.nextLine();
							System.out.println("Nhập ngày làm việc (dd/MM/yyyy): ");
							String s = sc.nextLine();
							DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
							LocalDate ngayLamViec = LocalDate.parse(s, dtf);
							LichLamViec llvNew = new LichLamViec(maLLV, nv, ca, ngayLamViec);
							llv_dao.capNhatLichLamViec(llvNew);
						}
						else
							System.out.println("Không có ca mang mã " + maCa);
					}
					else
						System.out.println("Không có nhân viên mang mã " + maNV);
				}
				else
					System.out.println("Có lịch nào mang mã " + maLLV + " đâu mà cập nhật :))))");
				break;
			}
//			Tìm lịch làm việc theo nhân viên
			case 5: {
				sc.nextLine();
				System.out.println("Nhập mã nhân viên: ");
				String maNV = sc.nextLine();
				ArrayList<LichLamViec> dsLLV = llv_dao.timLichLamViecTheoNhanVien(maNV);
				if(dsLLV.size()==0)
					System.out.println("Không tìm thấy");
				else
					System.out.println(dsLLV);
				break;
			}
//			Tìm lịch làm việc theo ngày
			case 6: {
				sc.nextLine();
				System.out.println("Nhập ngày làm việc (dd/MM/yyyy): ");
				String s = sc.nextLine();
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				LocalDate ngayLamViec = LocalDate.parse(s, dtf);
				ArrayList<LichLamViec> dsLLV = llv_dao.timLichLamViecTheoNgay(ngayLamViec);
				if(dsLLV.size()==0)
					System.out.println("Không tìm thấy");
				else
					System.out.println(dsLLV);
				break;
			}
//			Tìm lịch làm việc theo ca
			case 7: {
				System.out.println("Nhập mã ca làm việc: ");
				int maCa = sc.nextInt();
				ArrayList<LichLamViec> dsLLV = llv_dao.timLichLamViecTheoCa(maCa);
				if(dsLLV.size()==0)
					System.out.println("Không tìm thấy");
				else
					System.out.println(dsLLV);
				break;
			}
			default:
				flag=false;
				break;
			}
		}while(flag==true);
	}
}
