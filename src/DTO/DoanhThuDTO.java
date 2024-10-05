package DTO;

import java.util.ArrayList;

public class DoanhThuDTO {

    private String date;
    private String loai;
    private double tongDoanhThu;
    
    public DoanhThuDTO(){
        
    }
    
    public DoanhThuDTO(String date, String loai, double tongDoanhthu){
        this.date = date;
        this.loai = loai;
        this.tongDoanhThu = tongDoanhthu;
    }
    
    public String getDate(){
        return this.date;
    }
    
    public double getTongDoanhthu(){
        return this.tongDoanhThu;
    }

}
