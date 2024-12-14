package BUS;

import DAO.SanPhamDAO;
import DTO.SanPhamDTO;
import DTO.loaiSP;
import java.util.ArrayList;

public class SanPhamBUS {

    private ArrayList<SanPhamDTO> dsSP;

    public SanPhamBUS() {
        list();
    }

    public ArrayList<SanPhamDTO> getDsSP() {
        return dsSP;
    }

    public void setDsSP(ArrayList<SanPhamDTO> dsSP) {
        this.dsSP = dsSP;
    }
    public void updateGiaBan(String maSP, double giaBanMoi) {
        SanPhamDAO spDAO=new SanPhamDAO();
        spDAO.updateGiaBan(maSP, giaBanMoi);
    }
    public double getPrice(String maSP) {
   SanPhamDAO spDAO=new SanPhamDAO();
   return spDAO.getPrice(maSP);
}

    public void list() {
        SanPhamDAO spDAO = new SanPhamDAO();
        dsSP = spDAO.list();
    }
    
    public ArrayList<SanPhamDTO> getFilteredProducts(loaiSPBUS lBUS) {
        ArrayList<SanPhamDTO> filteredList = new ArrayList<>();
        // Lặp qua danh sách sản phẩm
        for (SanPhamDTO sp : dsSP) {
            // Lặp qua danh sách loại sản phẩm trong loaiSPBUS
            for (loaiSP  loai: lBUS.getListFull()) {
                // Kiểm tra mã loại sản phẩm và tình trạng sản phẩm
                if (sp.getMaLoai().equals(loai.getMALOAI()) && 
                   (loai.getTINHTRANG() != 0 && sp.getTrangThai()!=0)) {
                    filteredList.add(sp);
                }
            }
        }

        return filteredList;
    }
    
    public void add(SanPhamDTO sp){
        dsSP.add(sp);
        SanPhamDAO spDAO = new SanPhamDAO();
        spDAO.add(sp);
    }
    
    public void set(SanPhamDTO sp){
        for(int i=0; i<dsSP.size(); i++){
            if(dsSP.get(i).getMaSP().equalsIgnoreCase(sp.getMaSP())){
                dsSP.set(i, sp);
                SanPhamDAO spDAO = new SanPhamDAO();
                spDAO.set(sp);
            }
        }
    }
    
    public void delete(String maSP, Boolean ktraHD){
        for(int i =0; i<dsSP.size(); i++){
            if(dsSP.get(i).getMaSP().equalsIgnoreCase(maSP)){
                dsSP.remove(i);
                SanPhamDAO spDAO = new SanPhamDAO();
                spDAO.delete(maSP, ktraHD);
                return;
            }
        }
    }
    
    public SanPhamDTO select_by_id(String MASP) {
    	SanPhamDTO sp = new SanPhamDTO();
    	for (SanPhamDTO h : dsSP) {
    		if (h.getMaSP().equals(MASP)) {
    			sp = h;
    		}
    	}
    	return sp;
    }
}
