/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author hp
 */
public class nhacungcapDTO {
    private String MANCC;
    private String TENNCC;
    private String SDT;
    private int TRANGTHAI = 1;
    
    public nhacungcapDTO(String ma, String ten, String s){
        MANCC = ma;
        TENNCC = ten;
        SDT = s;
    }
    public nhacungcapDTO(String ten,String sdt){
        MANCC = "";
        TENNCC = ten;
        SDT = sdt;
    }
    public void setTRANGTHAI(int n){
        TRANGTHAI = n;
    }
    public int getTRANGTHAI(){
        return TRANGTHAI ;
    }
    public void setMANCC(String s){
        MANCC=s;
    }
    
    public void setTENNCC(String s){
        TENNCC=s;
    }
    
    public void setSDT(String s){
        SDT=s;
    }
    
    public String getMANCC(){
        return MANCC;
    }
    
    public String getTENNCC(){
        return TENNCC;
    }
    
     public String getSDT(){
         return SDT;
     }
 
     public boolean equals(nhacungcapDTO nccDTOnew){
         if(MANCC.equals(nccDTOnew.getMANCC()) && TENNCC.equals(nccDTOnew.getTENNCC()) && SDT == nccDTOnew.getSDT()) return true;
         return false;
     }
}
