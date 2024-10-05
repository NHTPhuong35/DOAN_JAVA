/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.ResultSet;
import BUS.loaiSPBUS;
import DTO.DoanhThuDTO;
import GUI.ThongKeGUI;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DoanhThuDAO {
    public ConnectDataBase mySQL;

    public DoanhThuDAO() {
        try {
            mySQL = new ConnectDataBase();
        } catch (SQLException e) {
            System.out.println("That bai");
        }
    }
    
    public ArrayList<DoanhThuDTO> listDoanhThu(ArrayList<String> data_filters){
        String tuNgay = data_filters.get(0);
        String denNgay = data_filters.get(1);
        String loai = data_filters.get(2);
        loaiSPBUS loaiBUS = new loaiSPBUS();
        for (int i = 0; i < loaiBUS.getListFull().size(); i++) {
            if (loaiBUS.getListFull().get(i).getTENLOAI().equals(loai)) {
                loai = loaiBUS.getListFull().get(i).getMALOAI();
            }
        }
        
        ArrayList<DoanhThuDTO> ds = new ArrayList<>();
        String query = "SELECT"
                + "    hd.NGAYHD AS NgayBan,"
                + "    SUM(ct.SOLUONG * ct.DONGIA) AS DoanhThu "
                + "FROM "
                + "    hoadon hd "
                + "INNER JOIN chitiethoadon ct ON hd.SOHD = ct.SOHD "
                + "INNER JOIN sanpham sp ON ct.MASP = sp.MASP "
                + "INNER JOIN loai l ON sp.MALOAI = l.MALOAI "
                + "WHERE ";
                if (!loai.equalsIgnoreCase("Tất cả")) {
                    query += "l.MALOAI ='" + loai + "' AND";
                }
                query += "    hd.NGAYHD BETWEEN '" + tuNgay + "' AND '" + denNgay +"' "
                + "GROUP BY NgayBan "
                + "ORDER BY NgayBan";
        
        System.out.println(query);
        try {
            mySQL.connect();
            ResultSet rs = mySQL.executeQuery(query);
            while (rs.next()) {
                  String ngayBan = rs.getString("NgayBan");
                  double doanhThu = rs.getDouble("DoanhThu");
                  DoanhThuDTO dt = new DoanhThuDTO(ngayBan, loai, doanhThu);
                  ds.add(dt);
//                ThongKeDTO thongKe = new ThongKeDTO(maSP, maLoai, tenSP, soLuong, thanhTien);
//                ds.add(thongKe);
            }
            rs.close();
            mySQL.disconnect();

        } catch (SQLException ex) {
        }
        return ds;
    }
    
    public static void main(String[] args) {
        ArrayList<String> currentday = new ArrayList<>();
        currentday.add("2024/09/01");
        currentday.add("2024/10/01");
        currentday.add("Tất cả");
        
        DoanhThuDAO dtDAO = new DoanhThuDAO();
        ArrayList<DoanhThuDTO> ds = dtDAO.listDoanhThu(currentday);
        System.out.println(ds);
    }
}