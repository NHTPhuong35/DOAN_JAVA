/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import DTO.TaiKhoanDTO;
import DTO.phieunhap_DTO;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

public class frame_sua_pn  extends JFrame{
	private chitietphieunhap_GUI c;
	 public frame_sua_pn(int w,int h,phieunhap_DTO phieunhap_DTO,phieunhap_GUI phieunhap_GUI ) throws SQLException {
		 this.setSize(w,h);
		 this.setLocationRelativeTo(null);
		 this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 
		 
		 this.c = new chitietphieunhap_GUI(w, h, phieunhap_DTO, phieunhap_GUI);
                 c.che_do_sua();
		 this.add(c);
		 
		 
		 this.setUndecorated(true);
		 this.setVisible(true);
                
	 }
         
//	 public void sua_chitietphieunhap() {
//		 this.c.sua_chitietphieunhap();
//	 }
//	 
//	 
//	 public void add_phieunhap() {
//		 this.c.them_phieunhap();	
//	 }
//	 
//	 
////	 public void update_price() {
////		 this.c.update_price();
////	 }
//	 
//	 public void update_chitietsanpham() {
//            try {
//                this.c.update_chitietsanpham();
//            } catch (SQLException ex) {
//                Logger.getLogger(frame_them_phieunhap.class.getName()).log(Level.SEVERE, null, ex);
//            }
//	 }
//	 
//	 public void update_gia_thap_hon() {
//		 this.c.update_gia_thap_hon();
//	 }
//	 
//	 public panel_them_phieunhap panel_them_phieunhap() {
//		 return this.c;
//	 }
         
//        public static void main(String[] args) throws SQLException {
//    JFrame f = new JFrame();
//    f.setLocationRelativeTo(null);
//    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//    // Tạo đối tượng phiếu nhập và tài khoản
//    phieunhap_DTO pn = new phieunhap_DTO("PN001", "AD1", LocalDate.parse("2024-09-05"), 15100000, "NCC1");
//    TaiKhoanDTO tk = new TaiKhoanDTO("AD1", "AD1", "SangHard!", "2023-02-13", "QQLHT", 1);
//
//    // Tạo GUI phiếu nhập và chi tiết phiếu nhập
//    phieunhap_GUI phieunhap_GUI = new phieunhap_GUI(900, 600, tk);
//    chitietphieunhap_GUI fr=new chitietphieunhap_GUI(800, 600, pn, phieunhap_GUI);
//
//    // Gọi phương thức để chuyển sang chế độ sửa ngay khi khởi động
//    
//    fr.che_do_sua();
//    // Thêm giao diện vào JFrame và hiển thị
//    f.add(fr);
//    f.pack();
//    f.setVisible(true);
//}
}

